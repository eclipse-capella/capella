/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;

import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.kitalpha.emde.model.ElementExtension;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.util.CapellamodellerResourceImpl;
import org.polarsys.capella.core.libraries.commands.internal.AddModelInformationToProjectCommand;
import org.polarsys.capella.core.libraries.commands.internal.RemoveSemanticResourceForLibraryCommand;
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.core.libraries.capellaModel.CapellaModel;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;

/**
 */
public class LibraryManagerExt {

  public static List<IAbstractLibrary> getNonActiveLibrariesIn(Collection<IAbstractLibrary> libraries, IAbstractModel contextModel) {
    List<IAbstractLibrary> nonActiveLibraries = new ArrayList<IAbstractLibrary>();
    for (IAbstractLibrary library : libraries) {
      if (!ILibraryManager.INSTANCE.isActiveLibrary(library, contextModel)) {
        nonActiveLibraries.add(library);
      }
    }
    return nonActiveLibraries;
  }

  public static void cleanResourceSetForProjectContext(Project project) {
    List<Resource> resourcesToRemove = new ArrayList<Resource>();
    for (Resource res : MDEAdapterFactory.getResourceSet().getResources()) {
      if (res.getErrors().size() > 0) {
        resourcesToRemove.add(res);
      }
    }
    for (Resource resource : resourcesToRemove) {
      MDEAdapterFactory.getResourceSet().getResources().remove(resource);
    }
  }  
  
  public static Project getProjectInCapellaModellerResource(URI capellaModellerResourceURI) {
    Resource mmResource = MDEAdapterFactory.getResourceSet().getResource(capellaModellerResourceURI, true);
    Project project = getProjectInCapellaModellerResource(mmResource);
    cleanResourceSetForProjectContext(project);
    return project;
  }
  

  public static Project getProjectInCapellaModellerResource(Resource capellaModellerResource) {
    List<EObject> contents = capellaModellerResource.getContents();
    if (contents.size() > 0) {
      EObject root = contents.get(0);
      if (root instanceof Project) {
        return (Project) root;
      }
    }
    return null;
  }

  /** Get the LibraryReference if exists. */
  public static LibraryReference getLibraryReference(CapellaModel model, CapellaLibrary library) {
    ModelInformation modelInfo = getModelInformationFromModel(model);
    if (modelInfo != null) {
      for (LibraryReference ref : modelInfo.getOwnedReferences()) {
        ModelInformation modelInfoTarget = getModelInformationFromModel(library);
        if ((modelInfoTarget != null) && ref.getLibrary().getId().equals(modelInfoTarget.getId())) {
          return ref;
        }
      }
    }
    return null;
  }

  private static final String[] CURRENT_CAPELLA_VERSION = StringUtils.split(FeatureHelper.getCapellaVersion(false), '.');

  public static boolean isCurrentVersion(String version) {
    if (version != null) {
      String[] projectVersionTab = StringUtils.split(version, '.');
      return (projectVersionTab.length > 1) && (CURRENT_CAPELLA_VERSION.length > 1) && projectVersionTab[0].equals(CURRENT_CAPELLA_VERSION[0])
             && projectVersionTab[1].equals(CURRENT_CAPELLA_VERSION[1]);
    }
    return false;
  }

  /** Returns the heaviest access policy. */
  public static AccessPolicy resolveAccessPolicy(AccessPolicy policy1, AccessPolicy policy2) {
    if ((policy1 == AccessPolicy.READ_AND_WRITE) || (policy2 == AccessPolicy.READ_AND_WRITE)) {
      return AccessPolicy.READ_AND_WRITE;
    }
    return AccessPolicy.READ_ONLY;
  }

  /** Returns the resource in the given list whose the uri equals to the given one. */
  public static Resource getResourceInListFromUri(Collection<Resource> resourceList, URI resourceUri) {
    if (resourceUri != null) {
      String decodedUri = decodeUri(resourceUri);
      for (Resource item : resourceList) {
        if (item != null) {
          URI itemUri = item.getURI();
          if (itemUri != null) {
            String itemDecodedUri = decodeUri(itemUri);
            if ((itemDecodedUri != null) && itemDecodedUri.equals(decodedUri)) {
              return item;
            }
          }
        }
      }
    }
    return null;
  }

  public static String decodeUri(URI uri) {
    StringBuffer result = new StringBuffer();
    if (!uri.isRelative()) {
      result.append(uri.scheme());
      result.append(":");
    }

    if (uri.isHierarchical()) {
      if (uri.hasAuthority()) {
        if (!uri.isArchive()) {
          result.append("//");
        }
        result.append(uri.authority());
      }

      if (uri.hasDevice()) {
        result.append('/');
        result.append(uri.device());
      }

      if (uri.hasAbsolutePath()) {
        result.append('/');
      }

      for (int i = 0, len = uri.segments().length; i < len; i++) {
        if (i != 0) {
          result.append('/');
        }
        result.append(URI.decode(uri.segments()[i]));
      }

      if (uri.hasQuery()) {
        result.append('?');
        result.append(uri.query());
      }
    } else {
      result.append(uri.authority());
    }

    if (uri.hasFragment()) {
      result.append('#');
      result.append(uri.fragment());
    }
    return result.toString();
  }

  /** Returns the project corresponding to the given session. */
  public static Project getProjectFromSession(Session session) {
    if ((session != null) && (session.getSessionResource() != null) && (session.getSessionResource().getURI() != null)) {
      Resource capellaModellerResource = getProjectResourceFromSession(session);
      if ((capellaModellerResource != null) && !capellaModellerResource.getContents().isEmpty()) {
        return (Project) capellaModellerResource.getContents().get(0);
      }
    }
    return null;
  }

  private static Resource getProjectResourceFromSession(Session session) {
    List<Resource> candidates = new ArrayList<Resource>();
    String sessionName = getFileNameFromURI(session.getSessionResource().getURI());
    for (Resource resource : session.getSemanticResources()) {
      if (CapellaResourceHelper.isCapellaResource(resource) && !(CapellaResourceHelper.isCapellaFragment(resource.getURI()))) {
        List<EObject> objects = resource.getContents();
        if (objects.size() > 0) {
          EObject object = EcoreUtil.getRootContainer(objects.get(0));
          if (object instanceof Project) {
            String resourceName = getFileNameFromURI(resource.getURI());
            candidates.add(resource);
            if (sessionName.equals(resourceName)) {
              return resource;
            }
          }
        }
      }
    }
    // If we are here, it's because aird and semantic model files are different (rename case). So we don't know which resource in the semantics resources of the
    // session is the good semantic model (and not a referenced library). So, we use the eclipse project containment as an heuristic.
    IProject project = EcoreUtil2.getFile(session.getSessionResource()).getProject();
    if (candidates.size() > 0) {
      for (Resource resource : candidates) {
        if (resource instanceof CapellamodellerResourceImpl) {
          IProject projectCandidate = EcoreUtil2.getFile(resource).getProject();
          if (projectCandidate.equals(project)) {
            return resource;
          }
        }
      }
      return candidates.get(0);
    }
    return null;
  }

  private static String getFileNameFromURI(URI fileUri) {
    return URI.decode(StringUtils.split(fileUri.lastSegment(), '.')[0]);
  }

  public static Project getProjectInSessionFromProjectId(Session session, String id) {
    for (Resource resource : session.getSemanticResources()) {
      if (resource != null) {
        EObject obj = resource.getEObject(id);
        if (obj != null) {
          return (Project) obj;
        }
      }
    }
    return null;
  }

  public static void updateAirdReferences(IAbstractModel model, LibraryManagerCache cache) {
    Session session = ((CapellaModel) model).getSession();
    if (session != null) {
      // get current and new references
      Collection<IAbstractLibrary> newReferencedAndActiveLibraries = ILibraryManager.INSTANCE.getAllReferencedLibraries(model, true);
      Collection<IAbstractLibrary> currentReferencedLibrariesInAird = getReferencedLibraryInAird(model, cache);
      CompoundCommand command = new CompoundCommand();
      // prepare remove commands
      for (IAbstractLibrary library : currentReferencedLibrariesInAird) {
        if (!newReferencedAndActiveLibraries.contains(library)) {
          URI semanticModelURI = library.getCapellaModellerUri();
          Collection<Resource> resources = session.getSemanticResources();
          Resource existingResource = LibraryManagerExt.getResourceInListFromUri(resources, semanticModelURI);
          command.append(new RemoveSemanticResourceForLibraryCommand(session, existingResource));
        }
      }
      // prepare add commands
      for (IAbstractLibrary library : newReferencedAndActiveLibraries) {
        if (!currentReferencedLibrariesInAird.contains(library)) {
          URI semanticModelURI = library.getCapellaModellerUri();
          command.append(new AddSemanticResourceCommand(session, semanticModelURI, new NullProgressMonitor()));
        }
      }
      if (!command.isEmpty()) {
        // launch the commands
        TransactionalEditingDomain transDomain = session.getTransactionalEditingDomain();
        // Workaround Sirius
        ResourceSetSync sync = ResourceSetSync.getOrInstallResourceSetSync(transDomain);
        sync.statusChanged(session.getSessionResource(), ResourceStatus.SYNC, ResourceStatus.UNKNOWN);
        // END Workaround
        transDomain.getCommandStack().execute(command);
        // update cache table
        cache.setReferencedLibrariesInAird(model, newReferencedAndActiveLibraries);
        //
        if (model instanceof IAbstractLibrary) {
          for (IAbstractModel model2 : getModelsThatReferenceLibrary((IAbstractLibrary) model, cache)) {
            updateAirdReferences(model2, cache);
          }
        }
      }
    }
  }

  public static List<IAbstractModel> getModelsThatReferenceLibrary(IAbstractLibrary library, LibraryManagerCache cache) {
    List<IAbstractModel> res = new ArrayList<IAbstractModel>();
    for (IAbstractModel model : cache.getLoadedModels()) {
      List<IAbstractLibrary> allRefs = cache.getAllReferencedLibraries(model);
      if ((allRefs != null) && allRefs.contains(library)) {
        res.add(model);
      }
    }
    return res;
  }

  public static Collection<IAbstractLibrary> getReferencedLibraryInAird(IAbstractModel model, LibraryManagerCache cache) {
    Collection<IAbstractLibrary> res = cache.getReferencedLibrariesInAird(model);
    if (res == null) {
      res = new ArrayList<IAbstractLibrary>();
      Collection<Resource> resources = ((CapellaModel) model).getSession().getSemanticResources();
      Resource existingResource = LibraryManagerExt.getResourceInListFromUri(resources, model.getCapellaModellerUri());
      for (Resource resource : resources) {
        if (resource != existingResource) {
          EObject object = resource.getContents().get(0);
          if (object instanceof Project) {// discard other artefacts like fragments
            Project project = (Project) object;
            IAbstractLibrary library = (IAbstractLibrary) ILibraryManager.INSTANCE.getAbstractModel(project);
            res.add(library);
          }
        }
      }
      // cache the value
      cache.setReferencedLibrariesInAird(model, res);
    }
    return res;
  }

  /** Get the ModelInformation corresponding to one model. Returns null if it does not exist. */
  public static ModelInformation getModelInformationFromModel(CapellaModel model) {
    Project project = model.getProject();
    for (ElementExtension extension : project.getOwnedExtensions()) {
      if (extension instanceof ModelInformation) {
        return (ModelInformation) extension;
      }
    }
    return null;
  }

  /** Get the ModelInformation corresponding to one model. Create and return a new one if it does not exist. */
  public static ModelInformation getOrCreateModelInformationFromModel(CapellaModel model) {
    ModelInformation modelInfo = getModelInformationFromModel(model);
    if (modelInfo == null) {
      Project project = model.getProject();
      AddModelInformationToProjectCommand cmd = new AddModelInformationToProjectCommand(project);
      MDEAdapterFactory.getExecutionManager().execute(cmd);
      modelInfo = cmd.getResult();
    }
    return modelInfo;
  }  

  public static void openEclipseProjectIfClosed(URI airdFileUri) {
    String airdUriString = airdFileUri.toString();
    if (airdUriString.startsWith("platform")) {
      String sessionName = getFileNameFromURI(airdFileUri);
      IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(sessionName);
      if ((project != null) && !project.isOpen()) {
        try {
          project.open(null);
        } catch (CoreException exception_p) {
        }
      }
    }
  }

  public static List<CapellaModel> computeGraphSet(CapellaModel currentModel) {
    List<CapellaModel> bases = new ArrayList<CapellaModel>();
    // get all base models in the graph (models that does not refer to any libraries)
    for (IAbstractModel model : currentModel.getAllReferencedLibraries(false)) {
      if (model.getReferencedLibraries(false).isEmpty()) {
        bases.add((CapellaModel) model);
      }
    }
    if (bases.size() == 0) {
      bases.add(currentModel);
    }
    // get all the graph roots (models that are not referenced by any models)
    List<CapellaModel> allRoots = new ArrayList<CapellaModel>();
    Collection<IAbstractModel> allModels = ILibraryManager.INSTANCE.getAllModelsInWorkspace();
    for (CapellaModel model : bases) {
      for (CapellaModel root : computeGraphRoots(Arrays.asList(new CapellaModel[] { model }), allModels)) {
        if (!allRoots.contains(root)) {
          allRoots.add(root);
        }
      }
    }
    // calculate the graph
    List<CapellaModel> modelsInGraph = new ArrayList<CapellaModel>();
    modelsInGraph.addAll(allRoots);
    for (CapellaModel m : allRoots) {
      for (IAbstractModel model : m.getAllReferencedLibraries(false)) {
        if (!modelsInGraph.contains(model)) {
          modelsInGraph.add((CapellaModel) model);
        }
      }
    }
    return modelsInGraph;
  }

  private static List<CapellaModel> computeGraphRoots(List<CapellaModel> models, Collection<IAbstractModel> allModels) {
    List<CapellaModel> newModels = new ArrayList<CapellaModel>();
    boolean moreCheck = false;
    for (CapellaModel model : models) {
      boolean referencersFound = false;
      for (IAbstractModel modelInWs : ILibraryManager.INSTANCE.getAllModelsInWorkspace()) {
        if (modelInWs.getReferencedLibraries(false).contains(model)) {
          referencersFound = true;
          moreCheck = true;
          newModels.add((CapellaModel) modelInWs);
        }
      }
      if (!referencersFound && !newModels.contains(model)) {
        newModels.add(model);
      }
    }
    if (moreCheck) {
      return computeGraphRoots(newModels, allModels);
    }
    return newModels;
  }
}
