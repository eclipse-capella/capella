/*******************************************************************************
 * Copyright (c) 2006, 2017, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.handler.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.activator.SolFaCommonActivator;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;

/**
 * Helper that deal with Capella resources.
 */
public class CapellaResourceHelper {

  public static final String CAPELLA_MODEL_FILE_EXTENSION = "capella"; //$NON-NLS-1$

  public static final String CAPELLA_FRAGMENT_FILE_EXTENSION = "capellafragment"; //$NON-NLS-1$

  public static final String LEGACY_CAPELLA_MODEL_FILE_EXTENSION = "melodymodeller"; //$NON-NLS-1$

  public static final String LEGACY_CAPELLA_FRAGMENT_FILE_EXTENSION = "melodyfragment"; //$NON-NLS-1$

  public static final Map<String, String> LEGACY_TO_MODERN_FILE_EXTENSIONS;

  static {
    Map<String, String> temp = new HashMap<>();

    temp.put(LEGACY_CAPELLA_FRAGMENT_FILE_EXTENSION, CAPELLA_FRAGMENT_FILE_EXTENSION);
    temp.put(LEGACY_CAPELLA_MODEL_FILE_EXTENSION, CAPELLA_MODEL_FILE_EXTENSION);

    LEGACY_TO_MODERN_FILE_EXTENSIONS = Collections.unmodifiableMap(temp);
  }

  public static final String AIRD_FRAGMENT_FILE_EXTENSION = "airdfragment"; //$NON-NLS-1$

  public static final String AIRD_SRM_FILE_EXTENSION = SiriusUtil.REPRESENTATION_FILE_EXTENSION; // $NON-NLS-1$

  public static final String AIRD_FILE_EXTENSION = SiriusUtil.SESSION_RESOURCE_EXTENSION;

  public static final String AFM_FILE_EXTENSION = "afm";

  public static final String FRAGMENTS_DEFAULT_FOLDER = "fragments"; //$NON-NLS-1$

  private static boolean __delegatedCapellaResourceHelperLoaded = false;

  private static ICapellaResourceHelper __delegatedCapellaResourceHelper;

  public static final String CAPELLA_PROJECT_NATURE = "org.polarsys.capella.project.nature"; //$NON-NLS-1$

  public static final String CAPELLA_LIBRARY_PROJECT_NATURE = "org.polarsys.capella.library.nature";//$NON-NLS-1$

  public static final String CAPELLA_CONFIGURATION_PROJECT_NATURE = "org.polarsys.capella.core.preferences.project.nature.configNature"; //$NON-NLS-1$

  /**
   * Ensure given resource is writable.<br>
   * If the resource is in read only, a dialog is prompted to ask the end-user to make the resource writable.
   * 
   * @param resource
   * @return <code>true</code> means writable; <code>false</code> means read only and the end-user turns down to make
   *         the resource writable.
   */
  public static boolean ensureResourceIsWritable(Resource resource) {
    boolean result = false;
    // Precondition.
    if (null == resource) {
      return result;
    }
    IFile file = EcoreUtil2.getFile(resource);
    // Is a read only resource ?
    if ((null != file) && file.isReadOnly()) {
      // Try making the file writable.
      result = SolFaCommonActivator.getDefault().getUserEnforcedHelper().makeFileWritable(file).isOK();
    } else {
      // Resource is writable.
      result = true;
    }
    return result;
  }

  /**
   * Whether or not specified resource has provided file extension.
   * 
   * @param resource
   * @param ignoreFragment
   * @param fragmentFileExtension
   * @param modelFileExtension
   * @return
   */
  private static boolean hasFileExtension(IResource resource, boolean ignoreFragment, String fragmentFileExtension,
      String modelFileExtension) {
    boolean result = false;
    // Precondition.
    if ((null == resource) || (IResource.FILE != resource.getType())) {
      return result;
    }
    String fileExtension = resource.getFileExtension();
    result = modelFileExtension.equals(fileExtension);
    if (!result && !ignoreFragment) {
      result = fragmentFileExtension.equals(fileExtension);
    }
    return result;
  }

  /**
   * Whether or not given resource is an AIRD one i.e a model or a fragment depending on ignoreAirdFragment.
   * 
   * @param resource
   * @param ignoreAirdFragment
   *          <code>true</code> means files with {@link #AIRD_FRAGMENT_FILE_EXTENSION} file extension are ignored.
   * @return <code>true</code> means given resource is a Capella one.
   * @see {@link #AIRD_FRAGMENT_FILE_EXTENSION}, {@link #AIRD_FILE_EXTENSION}
   */
  public static boolean isAirdResource(IResource resource, boolean ignoreAirdFragment) {
    return hasFileExtension(resource, ignoreAirdFragment, AIRD_FRAGMENT_FILE_EXTENSION, AIRD_FILE_EXTENSION);
  }

  /**
   * Whether or not given URI represents an AIRD one i.e a model or a fragment.
   * 
   * @param uri
   * @return <code>true</code> means the file extension is either {@link #AIRD_FRAGMENT_FILE_EXTENSION} or
   *         {@link #AIRD_FILE_EXTENSION}.
   * @see {@link #AIRD_FRAGMENT_FILE_EXTENSION}, {@link #AIRD_FILE_EXTENSION}
   */
  public static boolean isAirdResource(URI uri) {
    return (uri != null) && (AIRD_FILE_EXTENSION.equals(uri.fileExtension())
        || AIRD_FRAGMENT_FILE_EXTENSION.equals(uri.fileExtension()));
  }

  /**
   * Whether or not given URI represents a representation resource.
   * 
   * @param uri
   * @return <code>true</code> means the file extension is either {@link #AIRD_SRM_FILE_EXTENSION}.
   * @see {@link #AIRD_SRM_FILE_EXTENSION}
   */
  public static boolean isRepresentationResource(URI uri) {
    return (uri != null) && AIRD_SRM_FILE_EXTENSION.equals(uri.fileExtension());
  }

  /**
   * Whether or not given resource is a Capella fragment.
   * 
   * @param uri
   * @return <code>true</code> means given resource is a Capella fragment.
   * @see #CAPELLA_FRAGMENT_FILE_EXTENSION
   */
  public static boolean isCapellaFragment(URI uri) {
    return (null != uri) ? CAPELLA_FRAGMENT_FILE_EXTENSION.equals(uri.fileExtension()) : false;
  }

  /**
   * Whether or not given resource is a Capella one i.e a model or a fragment.
   * 
   * @param resource
   * @return <code>true</code> means given resource is a Capella one.
   * @see {@link #CAPELLA_FRAGMENT_FILE_EXTENSION}, {@link #CAPELLA_MODEL_FILE_EXTENSION}
   */
  public static boolean isCapellaResource(IResource resource) {
    return isCapellaResource(resource, false);
  }

  /**
   * Whether or not given resource is a <u>LEGACY</u> Capella one.
   * 
   * @param resource
   *          the resource
   * @return <code>true</code> means given resource is a <u>LEGACY</u> Capella one
   */
  public static boolean isLegacyCapellaResource(IResource resource) {
    return isLegacyCapellaResource(resource, false);
  }

  /**
   * Whether or not given resource is a Capella one i.e a model or a fragment depending on ignoreCapellaFragment.
   * 
   * @param resource
   * @param ignoreCapellaFragment
   *          <code>true</code> means files with {@link #CAPELLA_FRAGMENT_FILE_EXTENSION} file extension are ignored.
   * @return <code>true</code> means given resource is a Capella one.
   * @see {@link #CAPELLA_FRAGMENT_FILE_EXTENSION}, {@link #CAPELLA_MODEL_FILE_EXTENSION}
   */
  public static boolean isCapellaResource(IResource resource, boolean ignoreCapellaFragment) {
    return hasFileExtension(resource, ignoreCapellaFragment, CAPELLA_FRAGMENT_FILE_EXTENSION,
        CAPELLA_MODEL_FILE_EXTENSION);
  }

  /**
   * Whether or not the given resource is a <u>LEGACY</u> Capella one.
   * 
   * @param resource
   *          the resource
   * @param ignoreCapellaFragment
   *          <code>true</code> means files with {@link #CAPELLA_FRAGMENT_FILE_EXTENSION} file extension are ignored
   * @return <code>true</code> means given resource is a <u>LEGACY</u> Capella one
   * @see {@link #LEGACY_CAPELLA_FRAGMENT_FILE_EXTENSION}, {@link #LEGACY_CAPELLA_MODEL_FILE_EXTENSION},
   *      {@link #LEGACY_CAPELLA_CONNECTOR_FILE_EXTENSION}
   */
  public static boolean isLegacyCapellaResource(IResource resource, boolean ignoreCapellaFragment) {
    return hasFileExtension(resource, ignoreCapellaFragment, LEGACY_CAPELLA_FRAGMENT_FILE_EXTENSION,
        LEGACY_CAPELLA_MODEL_FILE_EXTENSION);
  }

  /**
   * Returns the modern extension equivalent for a legacy extension.
   * 
   * @param legacyExtension
   *          the legacy extension
   * @return the modern extension equivalent for a legacy extension or null if unrecognized
   */
  public static String getModernResourceExtension(String legacyExtension) {
    return LEGACY_TO_MODERN_FILE_EXTENSIONS.get(legacyExtension);
  }

  /**
   * Whether or not the given resource path is a <u>LEGACY</u> Capella one.
   * 
   * @param path
   *          the path.
   * @return whether or not the given resource path is a <u>LEGACY</u> Capella one
   */
  public static boolean isLegacyCapellaResourcePath(IPath path) {
    String fileExtension = path.getFileExtension();
    return getModernResourceExtension(fileExtension) != null;
  }

  /**
   * Converts the <u>LEGACY</u> resource path to a modern path.
   * 
   * @param path
   *          the legacy path
   * @return the modern path
   */
  public static IPath convertLegacyResourcePathToModern(IPath path) {
    String legacyExtension = path.getFileExtension();
    String modernExtension = getModernResourceExtension(legacyExtension);

    if (modernExtension != null) {
      return path.removeFileExtension().addFileExtension(modernExtension);

    }
    return path;
  }

  /**
   * Renames the <u>LEGACY</u> resource to a modern resource. If the resource is not <u>LEGACY</u>, no action is
   * performed.
   * 
   * @param capellaResource
   *          the capella resource
   * @return the modern resource
   */
  public static IResource renameLegacyResource(IResource capellaResource) {
    if (isLegacyCapellaResource(capellaResource)) {
      IFile legacyFile = capellaResource.getAdapter(IFile.class);

      if (legacyFile != null) {
        IWorkspace workspace = legacyFile.getWorkspace();
        IPath legacyPath = capellaResource.getFullPath();
        IPath modernPath = convertLegacyResourcePathToModern(legacyPath);

        FileHelper.moveResource(legacyFile, modernPath);

        return workspace.getRoot().getFile(modernPath);
      }

    }
    return capellaResource;
  }

  /**
   * Whether or not given object is a Capella resource i.e a model or a fragment.
   * 
   * @param object
   * @return
   */
  public static boolean isCapellaResource(Object object) {
    // Must be a EMF resource and a capella one.
    // DO NOT TEST CapellaResourceImpl for CDO compatibility.
    return (object instanceof Resource) && isCapellaResource(((Resource) object));
  }

  /**
   * Whether or not given resource is a Capella one i.e a model or a fragment.
   * 
   * @param resource
   * @return <code>true</code> means given resource is a Capella one.
   * @see {@link #CAPELLA_FRAGMENT_FILE_EXTENSION}, {@link #CAPELLA_MODEL_FILE_EXTENSION}
   */
  public static boolean isCapellaResource(Resource resource) {
    return (null != resource) ? isCapellaResource(resource.getURI()) : false;
  }

  /**
   * Whether or not given resource is a Capella one i.e a model or a fragment.
   * 
   * @param uri
   * @return <code>true</code> means given resource is a Capella one.
   * @see {@link #CAPELLA_FRAGMENT_FILE_EXTENSION}, {@link #CAPELLA_MODEL_FILE_EXTENSION}
   */
  public static boolean isCapellaResource(URI uri) {
    // Precondition.
    if (null == uri) {
      return false;
    }
    if (!__delegatedCapellaResourceHelperLoaded) {
      __delegatedCapellaResourceHelper = loadDelegatedCapellaResourceHelper();
    }

    // Call the delegation if any.
    boolean isCapellaResource = (null != __delegatedCapellaResourceHelper)
        ? __delegatedCapellaResourceHelper.isCapellaResource(uri)
        : false;
    return isCapellaResource || CAPELLA_MODEL_FILE_EXTENSION.equals(uri.fileExtension()) || isCapellaFragment(uri);
  }

  /**
   * Returns whether an object is a semantic element. Such element benefit of all basic tooling provided by capella
   */
  public static boolean isSemanticElement(Object object) {
    return (object instanceof EObject) && !isSiriusElement(object);
  }

  /**
   * Returns whether an object is a Sirius element.
   */
  private static boolean isSiriusElement(Object object) {
    if (object instanceof EObject) {
      EObject o = (EObject) object;
      // GMF
      if (o.eClass().getEPackage() == NotationPackage.eINSTANCE) {
        return true;
      }
    }

    return (object instanceof DRefreshable) || (object instanceof DRepresentationDescriptor);
  }

  /**
   * Returns whether objects are semantic elements. Such element benefit of all basic tooling provided by capella
   */
  public static boolean isSemanticElements(Collection<?> objects) {
    for (Object o : objects) {
      if (!isSemanticElement(o)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 
   * @param eObject
   * @return Returns whether eObject is aird element
   */
  public static boolean isAirdElement(EObject eObject) {
    if (eObject instanceof InternalEObject) {
      InternalEObject internalEObject = (InternalEObject) eObject;
      if (internalEObject.eIsProxy()) {
        if (isAirdResource(internalEObject.eProxyURI()) || isRepresentationResource(internalEObject.eProxyURI())) {
          return true;
        }
      }
      Resource resource = eObject.eResource();
      if (resource != null && resource.getURI() != null) {
        if (isAirdResource(resource.getURI()) || isRepresentationResource(resource.getURI())) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Create a new capella resource and loads it in the given editing domain
   */
  public static Resource createCapellaResource(IProject project, String filename, TransactionalEditingDomain domain) {
    // Creates the XMI serialization file.
    String fullPath = project.getFullPath().toString() + ICommonConstants.SLASH_CHARACTER + filename
        + ICommonConstants.POINT_CHARACTER + CAPELLA_MODEL_FILE_EXTENSION;
    URI capellaModelURI = URI.createPlatformResourceURI(fullPath, true);

    // create a resource
    ResourceSet set = domain.getResourceSet();
    Resource xmiResource = set.createResource(capellaModelURI);

    return xmiResource;
  }

  /**
   * Load the unique delegated Capella Resource Helper.
   */
  private static ICapellaResourceHelper loadDelegatedCapellaResourceHelper() {
    ICapellaResourceHelper delegatedHelper = null;
    // Load SemanticEditingDomain providers if any.
    IConfigurationElement[] configurationElements = ExtensionPointHelper
        .getConfigurationElements("org.polarsys.capella.core.model.handler", "delegatedCapellaResourceHelper"); //$NON-NLS-1$ //$NON-NLS-2$
    // Loop over contributed SemanticEditingDomain providers, must be only
    // one.
    if (configurationElements.length > 0) {
      delegatedHelper = (ICapellaResourceHelper) ExtensionPointHelper.createInstance(configurationElements[0],
          ExtensionPointHelper.ATT_CLASS);
    }
    __delegatedCapellaResourceHelperLoaded = true;
    return delegatedHelper;
  }

  /**
   * Retrieve editable attribute
   * 
   * @param selectedElement
   * @return a changeable, no-transient, no-derived, no-isMany attribute, or null
   */
  public static EAttribute getEditableAttribute(EObject selectedElement) {
    EAttribute attribute = null;
    if (selectedElement != null) {

      // We should externalize that when Project Explorer will be MDK
      // compliant
      if (selectedElement instanceof AbstractNamedElement) {
        attribute = ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME;
        // } else if (.....) {
        // FIXME should read some extension point somewhere
      } else if (selectedElement.eClass() != null) {
        // try default feature if exist
        EStructuralFeature feature = selectedElement.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
        if ((feature != null) && (feature instanceof EAttribute)) {
          attribute = (EAttribute) feature;
        }
      }
    }
    return attribute;
  }

  /**
   * Returns whether the given project is a Capella project (project or library)
   */
  public static boolean isCapellaProject(IProject targetProject) {
    try {
      return targetProject.hasNature(CAPELLA_PROJECT_NATURE) || targetProject.hasNature(CAPELLA_LIBRARY_PROJECT_NATURE);
    } catch (CoreException exception) {
      return false;
    }
  }

  /**
   * Returns whether the given resource belongs to a Capella project (project or library)
   */
  public static boolean isCapellaProject(Resource resource) {
    IFile file = WorkspaceSynchronizer.getFile(resource);
    if (file != null) {
      IProject project = file.getProject();
      if (project != null) {
        return isCapellaProject(project);
      }
    }
    return false;
  }

  /**
   * Returns for the given URI if it is located into a Capella project
   */
  public static boolean isCapellaProject(URI uri) {
    try {
      if (uri.isPlatformResource()) {
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
        return CapellaResourceHelper.isCapellaProject(file.getProject());
      }
    } catch (Exception ex) {
      // This is not a valid local project
    }

    return false;
  }

  /**
   * @param eObject
   * @return the main model resource if eObject is in a fragmented resource
   */
  public static Resource getMainModelResource(EObject eObject) {
    Resource objRes = eObject.eResource();
    if (objRes != null) {
      // If the resource is a fragment, find the resource of the
      // main model
      if (CapellaResourceHelper.isCapellaFragment(objRes.getURI())) {
        Resource mainModelResource = EcoreUtil.getRootContainer(eObject, true).eResource();
        if (mainModelResource != null) {
          return mainModelResource;
        }
      } else {
        return objRes;
      }
    }
    return null;
  }

  /**
   * Check whether a {@link IProject} nature is of given type.
   * 
   * @param project
   *          the target {@link IProject}
   * @param natureIdsToMatch
   *          the nature to match ids.
   * @return <code>true</code> whether no nature was defined (e.g. empty or <code>null</code> value as argument.)
   */
  public static boolean isProjectOfType(final IProject project, final Collection<String> natureIdsToMatch) {

    boolean result = false;

    if ( // No filtering on nature
    null == natureIdsToMatch || natureIdsToMatch.isEmpty()) {
      return true;
    }

    try {
      String[] projectNatures = project.getDescription().getNatureIds();
      result = matche(projectNatures, natureIdsToMatch);
    } catch (CoreException exception) {
      result = false;
    }

    return result;
  }

  /**
   * Check whether a {@link IProject} nature is of given type.
   * 
   * @param project
   *          the target {@link IProject}
   * @param natureIdToMatch
   *          the nature to match id.
   * @return <code>true</code> whether no nature was defined (e.g. empty or <code>null</code> value as argument.)
   */
  public static boolean isProjectOfType(final IProject project, final String natureIdToMatch) {
    return null == natureIdToMatch || natureIdToMatch.equals(ICommonConstants.EMPTY_STRING) ? true
        : isProjectOfType(project, Collections.singleton(natureIdToMatch));
  }

  /**
   * Get all accessible project projects on workspace of a given nature.
   * 
   * @param natureIdToMatch
   *          the type of project id (or <code>null</code>)
   * @return an empty {@link Collection} whether no result was found.
   */
  public static Collection<IProject> getAllProjectsOfType(String natureIdToMatch) {
    return getAllProjectsOfType(Collections.singleton(natureIdToMatch));
  }

  /**
   * Get all accessible project projects on workspace of a given nature.
   * 
   * @param natureIdsToMatch
   *          the type of project ids (or <code>null</code>)
   * @return an empty {@link Collection} whether no result was found.
   */
  public static Collection<IProject> getAllProjectsOfType(final Collection<String> natureIdsToMatch) {

    Set<IProject> result = new HashSet<IProject>();

    IWorkspace root = ResourcesPlugin.getWorkspace();
    IProject[] projects = root.getRoot().getProjects();

    boolean shouldBeFilteredWithNature = null != natureIdsToMatch && !natureIdsToMatch.isEmpty();

    IProject project = null;
    boolean keep = false;
    for (int i = 0; i < projects.length; i++) {
      keep = false;
      project = projects[i];

      keep = project.isAccessible(); // Project open

      if (keep && shouldBeFilteredWithNature) { // Nature of the project ok.
        keep = isProjectOfType(project, natureIdsToMatch);
      }

      // To conclude
      if (keep) {
        result.add(project);
      }

    }

    return result;
  }

  /** for internal use */
  static private boolean matche(final String[] a, final Collection<String> b) {
    boolean result = false;

    for (String str : a) {
      if (b.contains(str)) {
        result = true;
        break;
      }
    }

    return result;
  }
}
