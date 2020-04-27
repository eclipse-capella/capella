/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.sirius.SiriusScope;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.util.MiscUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaFeatureHelper;
import org.polarsys.capella.core.sirius.ui.listener.FileModificationPreCommitListener;


/**
 * A scope for fragmented Capella files including diagrams.
 */
public class CapellaScope extends SiriusScope {
  
  /** Whether Capella version compatibility must be checked or ignored */
  protected boolean _ignoreCapellaVersions;
  
  
  /**
   * Constructor
   * @param uri a non-null URI
   * @param domain a non-null editing domain
   * @param readOnly whether the scope is read-only
   */
  public CapellaScope(URI uri, EditingDomain domain, boolean readOnly) {
    super(uri, domain, readOnly);
    // Do not explicitly assign _ignoreCapellaVersions: it may be assigned via super constructor
  }
  
  /**
   * Constructor
   * @param uri a non-null URI
   * @param resourceSet a non-null resource set
   * @param readOnly whether the scope is read-only
   */
  public CapellaScope(URI uri, ResourceSet resourceSet, boolean readOnly) {
    super(uri, resourceSet, readOnly);
    // Do not explicitly assign _ignoreCapellaVersions: it may be assigned via super constructor
  }
  
  /**
   * Constructor
   * @param uris a non-null collection of URIs of resources to load as roots
   * @param editingDomain a non-null editing domain that encompasses the scope
   * @param readOnly whether the scope should be read-only, if supported
   */
  public CapellaScope(Collection<URI> uris, EditingDomain editingDomain, boolean readOnly) {
    super(uris, editingDomain, readOnly);
    // Do not explicitly assign _ignoreCapellaVersions: it may be assigned via super constructor
  }
  
  /**
   * Constructor
   * @param uris a non-null collection of URIs of resources to load as roots
   * @param resourceSet a non-null resource set where the resources must be loaded
   * @param readOnly whether the scope is in read-only mode, if applicable
   */
  public CapellaScope(Collection<URI> uris, ResourceSet resourceSet, boolean readOnly) {
    super(uris, resourceSet, readOnly);
    // Do not explicitly assign _ignoreCapellaVersions: it may be assigned via super constructor
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.sirius.SiriusScope#add(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject source, EReference reference, EObject value) {
    EObject actualValue = getLocalElement(value);
    return super.add(source, reference, actualValue);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#addNewResource(org.eclipse.emf.ecore.resource.Resource)
   */
  @Override
  protected void addNewResource(Resource resource) {
    if (!_ignoreCapellaVersions) // Automatically initialized to false
      checkCapellaVersion(resource);
    super.addNewResource(resource);
    if (!resource.isTrackingModification())
      resource.setTrackingModification(true);
  }
  
  /**
   * Return whether models made with either version of the tool can be loaded
   * in the tool of the other given version
   * @param toolVersion1 a potentially null short version (in format "X.Y.Z")
   * @param toolVersion2 a potentially null short version (in format "X.Y.Z")
   */
  protected boolean areCompatible(String toolVersion1, String toolVersion2) {
    boolean result = true;
    if (toolVersion1 != null && toolVersion2 != null) {
      result = false;
      String pattern = Pattern.quote(
          ICommonConstants.EMPTY_STRING + ICommonConstants.POINT_CHARACTER);
      String[] segments1 = toolVersion1.split(pattern);
      String[] segments2 = toolVersion2.split(pattern);
      if (segments1.length >= 2 && segments2.length >=2) {
        if (segments1[0].equals(segments2[0]))
          result = segments1[1].equals(segments2[1]);
      }
    }
    return result;
  }
  
  /**
   * Throw an exception if the given resource is a Capella resource with an
   * incorrect version and the user does not confirm that comparison must be carried out
   * @param resource a potentially null resource
   */
  protected void checkCapellaVersion(final Resource resource) {
    if (resource != null && CapellaResourceHelper.isCapellaResource(resource)) {
      // Checking Capella version of Capella resource
      final IFile capellaFile = getFileFor(resource);
      if (capellaFile != null) {
        String capellaVersion =
          FeatureHelper.getCapellaVersion(false);
        String rawVersion = CapellaFeatureHelper.getDetectedVersion(capellaFile);
        final String fileVersion = (rawVersion == null || rawVersion.length()<5)? null:
          rawVersion.substring(0, 5);
        if (!areCompatible(fileVersion, capellaVersion)) {
          final Collection<Object> emptyIfProceed = new ArrayList<Object>(1);
          PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
              String msg = String.format(Messages.CapellaScope_DifferentVersion,
                  fileVersion, capellaFile.getFullPath().toString());
              boolean proceed = MessageDialog.openQuestion(
                  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                  EMFDiffMergeUIPlugin.LABEL,
                  msg);
              if (!proceed)
                emptyIfProceed.add(new Object());
            }
          });
          if (!emptyIfProceed.isEmpty())
            throw new RuntimeException(Messages.CapellaScope_DifferentVersionInterruption);
          _ignoreCapellaVersions = true;
        }
      }
    }
  }
  
  /**
   * Return whether write permission is acquired for the modified files
   */
  protected boolean checkWritePermission() {
    // Building the list of modified resources
    int nbResources = getResources().size();
    List<Resource> modifiedResources = new ArrayList<Resource>(nbResources);
    for (Resource currentRes : getResources()) {
    	if (currentRes.isModified())
    		modifiedResources.add(currentRes);
    }
    final TransactionalEditingDomain domain = findEditingDomain(modifiedResources);
    boolean result = domain == null; // If no editing domain, consider permission is ok
    if (!result) {
      // Building the list of modified resource files
      final List<IFile> modifiedFiles = new ArrayList<IFile>(modifiedResources.size());
      for (Resource currentRes : modifiedResources) {
    	IFile file = getFileFor(currentRes);
    	if (file != null) {
    		modifiedFiles.add(file);
    		try {
    			file.touch(null); // Trigger file modification validators if any
    		} catch (CoreException e) {
    			// Proceed
    		}
    	}
      }
      // Checking ability to write in resource files
      final List<Exception> emptyIfOk = new ArrayList<Exception>(1);
      MiscUtil.executeOnDomain(
          domain, Messages.CapellaScope_PermissionCommandName, new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          try {
            FileModificationPreCommitListener.makeFilesWritable(domain, modifiedFiles);
          } catch (Exception e) {
            emptyIfOk.add(e);
          }
        }
      });
      result = emptyIfOk.isEmpty();
    }
    return result;
  }
  
  /**
   * Find and return a transactional editing domain from a list of resources, if possible
   * @param resources a non-null set of resources
   * @return a potentially null editing domain
   */
  protected TransactionalEditingDomain findEditingDomain(Collection<Resource> resources) {
    for (Resource resource : resources) {
      TransactionalEditingDomain result = TransactionUtil.getEditingDomain(resource);
      if (result != null)
        return result;
    }
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.sirius.SiriusScope#getCrossReferencesInScope(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Collection<EReference> getCrossReferencesInScope(EObject element) {
    Collection<EReference> result = super.getCrossReferencesInScope(element);
    if (element instanceof LibraryReference) {
      // From Model/Library to referenced Libraries
      result.add(LibrariesPackage.eINSTANCE.getLibraryReference_Library());
    }
    return result;
  }
  
  /**
   * Return the Eclipse file that holds the given resource, if any.
   * If the file is outside the workspace, create a link to it in a dedicated Eclipse project.
   * @param resource a non-null resource
   * @return a potentially null Eclipse file
   */
  protected IFile getFileFor(Resource resource) {
    IFile result = null;
    URI uri = resource.getURI();
    if (uri != null) {
      if (uri.isPlatformResource()) {
        // Resource in workspace
        String platformResourcePath = uri.toPlatformString(true);
        result = ResourcesPlugin.getWorkspace().getRoot().getFile(
            new Path(platformResourcePath));
      } else {
        // Resource from external file
        String fileString = uri.toFileString();
        if (fileString != null) {
          IPath path = new Path(fileString);
          IProject proxyProject = CapellaComparePlugin.getDefault().getProxyProject(this, path);
          if (proxyProject != null) {
            try {
              String linkName = makeLinkName(uri);
              result = proxyProject.getFile(linkName);
              result.createLink(path, IResource.REPLACE, null);
            } catch (CoreException e) {
              // Just proceed
            }
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Return a scope-local element that corresponds to the given one, if any, otherwise return
   * the given element
   * @param element a potentially null element
   * @return an element that is null if and only if the given element is null
   */
  protected EObject getLocalElement(EObject element) {
    EObject result = element;
    ResourceSet localResourceSet = _resourceSet;
    if (element != null && localResourceSet != null ) {
      Resource valueResource = element.eResource();
      if (valueResource != null && localResourceSet != valueResource.getResourceSet()) {
        // Element belongs to another resource set
        URI valueResourceURI = valueResource.getURI();
        if (valueResourceURI != null && valueResourceURI.isPlatformPlugin()) {
          // And element belongs to a platform plug-in
          URI valueURI = EcoreUtil.getURI(element);
          if (valueURI != null) {
            // Try and get the same element (URI-based) in the local resource set
            EObject localElement = localResourceSet.getEObject(valueURI, false);
            if (localElement != null) {
              result = localElement;
            }
          }
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#getRelevantReferencedResources(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected List<Resource> getRelevantReferencedResources(EObject element) {
    // Filter out metamodels because of Sirius bug that adds eMDE.ecore to the models
    // referenced by DAnalysis (DAnalysis_Models)
    List<Resource> result = super.getRelevantReferencedResources(element);
    for (Resource resource : new ArrayList<Resource>(result)) {
      if (EcorePackage.eNAME.equals(resource.getURI().fileExtension()))
        result.remove(resource);
    }
    return result;
  }
  
  /**
   * Return a file link name for this scope
   * @param uri a non-null URI
   * @return a non-null string
   */
  protected String makeLinkName(URI uri) {
    URI truncated = uri.trimFileExtension();
    String extension = uri.fileExtension();
    String segment = truncated.lastSegment();
    // A suffix could be added to segment, which is why we decompose so much
    URI result = truncated.trimSegments(1).appendSegment(segment);
    result = result.appendFileExtension(extension);
    return result.lastSegment();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#save()
   */
  @Override
  public boolean save() throws Exception {
    boolean result = checkWritePermission();
    if (result)
      result = super.save();
    return result;
  }
  
}
