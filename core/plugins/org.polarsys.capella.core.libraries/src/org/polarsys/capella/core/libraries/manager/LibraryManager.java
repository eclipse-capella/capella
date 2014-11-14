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
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.Activator;
import org.polarsys.capella.core.libraries.commands.internal.AddLibraryReferenceInCapellaModellerCommand;
import org.polarsys.capella.core.libraries.commands.internal.RemoveLibraryReferenceInCapellaModellerCommand;
import org.polarsys.capella.core.libraries.commands.internal.SetAccessPolicyToLibraryReferenceCommand;
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.core.libraries.capellaModel.CapellaModel;
import org.polarsys.capella.core.libraries.nature.LibraryNature;
import org.polarsys.capella.core.libraries.utils.IFileRequestor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaFeatureHelper;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.queries.debug.FormatedLogger;
import org.polarsys.capella.common.queries.debug.Log;

/**
 */
public class LibraryManager extends ILibraryManager {

  private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);
  private static final String ACTIVE_STATE_PREF_ID = "LIBRARY_ACTIVE_STATE_INFORMATIONS"; //$NON-NLS-1$

  protected Preferences activeLibraryPrefInfos = null;
  // TODO changed to protected, used currently for workaround
  public LibraryManagerCache cache = new LibraryManagerCache();

  public LibraryManager() {
    activeLibraryPrefInfos = new ConfigurationScope().getNode(Activator.PLUGIN_ID).node(ACTIVE_STATE_PREF_ID);
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    workspace.addResourceChangeListener(new ResourceChangeListener(this));
  }

  public void resetCacheForReferences() {
    cache.resetCacheForReferences();
  }

  @Override
  public boolean isActiveLibrary(IAbstractLibrary library, IAbstractModel modelContext) {
    Preferences libraryContextPref = activeLibraryPrefInfos.node(modelContext.getName());
    return libraryContextPref.getBoolean(library.getName(), getDefaultActiveState(library, modelContext));
  }

  @Override
  public Collection<IAbstractLibrary> getAllLibrariesInWorkspace() {
    List<IAbstractLibrary> libraries = new ArrayList<IAbstractLibrary>();
    for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
      if (project.isOpen() && isLibraryProject(project)) {
        for (IAbstractModel lib : getAbstractModel(project)) {
          libraries.add((IAbstractLibrary) lib);
        }
      }
    }
    return libraries;
  }

  @Override
  public Collection<IAbstractModel> getAllModelsInWorkspace() {
    List<IAbstractModel> models = new ArrayList<IAbstractModel>();
    for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
      if (project.isOpen()) {
        models.addAll(getAbstractModel(project));
      }
    }
    return models;
  }

  @Override
  public List<IAbstractModel> getAbstractModel(String eclipseProjectName) {
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(eclipseProjectName);
    if ((project != null)) {
      return getAbstractModel(project);
    }
    return null;
  }

  @Override
  public IAbstractModel getAbstractModel(Session session) {
    Project project = LibraryManagerExt.getProjectFromSession(session);
    CapellaModel model = (CapellaModel) cache.getOrCreateCapellaModel(project);
    if (model != null) {
      model.setSession(session);
    }
    return model;
  }

  @Override
  public List<IAbstractModel> getAbstractModel(IProject eclipseProject) {
    return getAbstractModel(eclipseProject, true);
  }

  @SuppressWarnings("nls")
  public IAbstractModel getAbstractModel(IFile capellaModellerFile) {
    String projectVersion = CapellaFeatureHelper.getDetectedVersion(capellaModellerFile);
    if (LibraryManagerExt.isCurrentVersion(projectVersion)) {
      URI capellaModellerURI = URI.createPlatformResourceURI(capellaModellerFile.getFullPath().toString(), true);
      Project project = LibraryManagerExt.getProjectInCapellaModellerResource(capellaModellerURI);
      if (project != null) {
        return cache.getOrCreateCapellaModel(project);
      }
    } else {
      logger
          .error(new EmbeddedMessage(
              "Wrong model version detected: " + projectVersion + " (" + capellaModellerFile.getName() + ") Please have a look at the Capella SCOM about project migration.", //$NON-NLS-1$
              IReportManagerDefaultComponents.UI));
    }
    return null;
  }

  @SuppressWarnings("nls")
  public List<IAbstractModel> getAbstractModel(IProject eclipseProject, boolean createIsAuthorized) {
    List<IAbstractModel> models = new ArrayList<IAbstractModel>();
    if (eclipseProject.isOpen()) {
      // for all semantic models in the eclipse project, ...
      List<IFile> melodymodellers = new IFileRequestor().search(eclipseProject, CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION, false);
      if (melodymodellers.size() > 0) {
        // nominal case
        for (IFile mmFile : melodymodellers) {
          IAbstractModel model = getAbstractModel(mmFile);
          if (model != null) {
            models.add(model);
          }
        }
      } else {
        List<IFile> airdFiles = new IFileRequestor().search(eclipseProject, CapellaResourceHelper.AIRD_FILE_EXTENSION, false);
        if (airdFiles.size() > 0) {
          // CDO case (there is only a local aird)
          IFile airdFile = airdFiles.get(0);
          URI uri = URI.createURI(StringUtils.replace(airdFile.toString(), "L/", "platform:/resource/"));
          Session session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());
          Project project = LibraryManagerExt.getProjectFromSession(session);
          IAbstractModel model = cache.getOrCreateCapellaModel(project);
          if (model != null) {
            models.add(model);
          }
        }
      }
    }
    return models;
  }

  @Override
  public IAbstractModel getAbstractModel(EObject modelElement_p) {
    if (modelElement_p == null) {
      return null;
    }
    EObject semanticElement = modelElement_p;
    if (!(CapellaResourceHelper.isSemanticElement(semanticElement))) {
      semanticElement = ModelAdaptation.adaptToCapella(semanticElement);
    }

    if (semanticElement == null) {
      return null;
    }

    Project project = null;
    if (semanticElement instanceof Project) {
      project = (Project) semanticElement;
    } else {
      project = (Project) EcoreUtil2.getFirstContainer(semanticElement, CapellamodellerPackage.Literals.PROJECT);
    }

    return cache.getOrCreateCapellaModel(project);
  }

  @Override
  public boolean addReferenceToLibrary(IAbstractModel referencingModel_p, IAbstractLibrary referencedLibrary_p) {
    CapellaModel referencingModel = (CapellaModel) referencingModel_p;
    CapellaLibrary referencedLibrary = (CapellaLibrary) referencedLibrary_p;
    LibraryReference ref = LibraryManagerExt.getLibraryReference(referencingModel, referencedLibrary);
    if (ref != null) {
      // the library reference already exists ...
      logger.warn(new EmbeddedMessage(
          "warning in LibraryManager.addReferenceToLibrary(..) : try to add library reference in a project that already refers to it.", //$NON-NLS-1$
          IReportManagerDefaultComponents.UI));
      return false;
    }
    // add the reference in cache (do a get first to be sure that a value exists in cache)
    addReferenceInCache(referencingModel, referencedLibrary);
    Collection<IAbstractLibrary> allRefToLib = getAllReferencedLibraries(referencingModel, false);
    if (allRefToLib.contains(referencingModel)) {
      // a cycle has been detected ...
      removeReferenceInCache(referencingModel, referencedLibrary);
      logger
          .warn(new EmbeddedMessage(
              "warning in LibraryManager.addReferenceToLibrary(..) : a cycle was detected when adding library reference in a model. The reference has not been added.", //$NON-NLS-1$
              IReportManagerDefaultComponents.UI));
      return false;
    }
    // add the reference in the capellaModeller
    ModelInformation modelInfo = LibraryManagerExt.getOrCreateModelInformationFromModel(referencingModel);
    ModelInformation libraryInfo = LibraryManagerExt.getOrCreateModelInformationFromModel(referencedLibrary);
    AddLibraryReferenceInCapellaModellerCommand cmd = new AddLibraryReferenceInCapellaModellerCommand(modelInfo, referencedLibrary, libraryInfo);
    MDEAdapterFactory.getExecutionManager().execute(cmd);
    ref = cmd.getResult();
    // add the reference in the session
    LibraryManagerExt.updateAirdReferences(referencingModel, cache);
    return true;
  }

  @Override
  public boolean removeReferenceToLibrary(IAbstractModel referencingModel_p, IAbstractLibrary referencedLibrary_p) {
    CapellaModel referencingModel = (CapellaModel) referencingModel_p;
    CapellaLibrary referencedLibrary = (CapellaLibrary) referencedLibrary_p;
    LibraryReference ref = LibraryManagerExt.getLibraryReference(referencingModel, referencedLibrary);
    if (ref == null) {
      // the library reference does not exist ...
      logger.warn(new EmbeddedMessage(
          "warning in LibraryManager.removeReferenceToLibrary(..) : try to remove a library reference in a project that does not refers to it.", //$NON-NLS-1$
          IReportManagerDefaultComponents.UI));
      return false;
    }
    // remove the reference in cache (same approach as in addReferenceToLibrary, see more commentaries in addReferenceToLibrary)
    Collection<IAbstractLibrary> oldAllReferences = ILibraryManager.INSTANCE.getAllReferencedLibraries(referencingModel, false);
    removeReferenceInCache(referencingModel, referencedLibrary);
    // remove the reference in the capellaModeller
    ModelInformation modelInfo = LibraryManagerExt.getOrCreateModelInformationFromModel(referencingModel);
    RemoveLibraryReferenceInCapellaModellerCommand cmd = new RemoveLibraryReferenceInCapellaModellerCommand(modelInfo, ref);
    MDEAdapterFactory.getExecutionManager().execute(cmd);
    // remove the reference in the session
    LibraryManagerExt.updateAirdReferences(referencingModel, cache);
    // set the default active state for the library in the context model
    Collection<IAbstractLibrary> newAllReferences = ILibraryManager.INSTANCE.getAllReferencedLibraries(referencingModel, false);
    oldAllReferences.removeAll(newAllReferences);
    for (IAbstractLibrary removedReferencedLibrary : oldAllReferences) {
      setLibraryActiveState(removedReferencedLibrary, referencingModel, getDefaultActiveState(referencedLibrary, referencingModel));
    }
    return true;
  }

  @Override
  public Collection<IAbstractLibrary> getReferencedLibraries(IAbstractModel referencingModel_p, boolean onlyActiveOnes) {
    if (referencingModel_p == null) {// Shield
      Logger.getLogger(IReportManagerDefaultComponents.MODEL).error("getReferencedLibraries reveives null as the value of the referencingModel_p parameter", //$NON-NLS-1$
          null);
      return new ArrayList<IAbstractLibrary>();
    }
    CapellaModel referencingModel = (CapellaModel) referencingModel_p;
    List<IAbstractLibrary> res = null;
    // List<IAbstractLibrary> cacheValue = model2referencedLibraries.get(referencingModel);
    if (cache.isReferencedLibrariesInCacheFor(referencingModel)) {
      // get from cache
      res = cache.getReferencedLibraries(referencingModel);
    } else {
      // calculate referenced libraries
      res = new ArrayList<IAbstractLibrary>();
      ModelInformation modelInformation = LibraryManagerExt.getModelInformationFromModel(referencingModel);
      if (modelInformation != null) {
        for (LibraryReference ref : modelInformation.getOwnedReferences()) {
          if ((ref.getLibrary() == null) || (ref.getLibrary().eContainer() == null)) {// BLINDAGE
            Logger.getLogger(IReportManagerDefaultComponents.MODEL).error("one referencedLibrary is corrupted", null); //$NON-NLS-1$
          } else {
            ModelInformation libModelInfo = ref.getLibrary();
            Project project = (Project) libModelInfo.eContainer();
            IAbstractLibrary library = (IAbstractLibrary) getAbstractModel(project);
            res.add(library);
          }
        }
      }
      // cache the value
      cache.setReferencedLibraries(referencingModel, res);
    }
    if (onlyActiveOnes) {
      res.removeAll(LibraryManagerExt.getNonActiveLibrariesIn(res, referencingModel));
    }
    return res;
  }

  @Override
  public Collection<IAbstractLibrary> getAllReferencedLibraries(IAbstractModel referencingModel, boolean onlyActiveOnes) {
    List<IAbstractLibrary> res = null;
    if (cache.isAllReferencedLibrariesInCacheFor(referencingModel)) {
      // get from cache
      res = cache.getAllReferencedLibraries(referencingModel);
    } else {
      // calculate referenced libraries
      res = new ArrayList<IAbstractLibrary>();
      computeAllReferencedLibraries(referencingModel, res);
      // cache the value
      cache.setAllReferencedLibraries(referencingModel, res);
    }
    if (onlyActiveOnes) {
      res.removeAll(LibraryManagerExt.getNonActiveLibrariesIn(res, referencingModel));
    }
    return res;
  }

  @Override
  public boolean isLibraryProject(IProject eclipseProject) {
    try {
      return eclipseProject.hasNature(LibraryNature.ID);
    } catch (CoreException exception_p) {
      return false;
    }
  }

  @Override
  public boolean isLibrary(IAbstractModel model) {
    return model instanceof IAbstractLibrary;
  }

  @Override
  public boolean getDefaultActiveState(IAbstractLibrary library, IAbstractModel contextModel) {
    return true;
  }

  @Override
  public void setLibraryActiveState(IAbstractLibrary library, IAbstractModel modelContext, boolean activeState) {
    Preferences libraryContextPref = activeLibraryPrefInfos.node(modelContext.getName());
    libraryContextPref.putBoolean(library.getName(), activeState);
    try {
      activeLibraryPrefInfos.flush();
    } catch (BackingStoreException e) {
      e.printStackTrace();
    }
    // update aird references
    LibraryManagerExt.updateAirdReferences(modelContext, cache);
  }

  @Override
  public AccessPolicy getDefaultAccessPolicy(IAbstractLibrary referencedLibrary) {
    return AccessPolicy.READ_AND_WRITE;
  }

  @Override
  public AccessPolicy getAccessPolicy(IAbstractModel referencingModel_p, IAbstractLibrary referencedLibrary_p, boolean transitivityAllowed) {
    CapellaModel referencingModel = (CapellaModel) referencingModel_p;
    CapellaLibrary referencedLibrary = (CapellaLibrary) referencedLibrary_p;
    if (!transitivityAllowed) {
      LibraryReference ref = LibraryManagerExt.getLibraryReference(referencingModel, referencedLibrary);
      if (ref != null) {
        return ref.getAccessPolicy();
      }
      return null;
    }
    List<AccessInfo> accessInfos = new ArrayList<LibraryManager.AccessInfo>();
    calculateAccessPolicy(referencingModel, referencedLibrary, accessInfos, 0);
    if (accessInfos.size() > 0) {
      AccessInfo accessInfo = accessInfos.get(0);
      int currentLevel = accessInfo.level;
      AccessPolicy result = accessInfo.accessPolicy;
      for (int i = 1; i < accessInfos.size(); i++) {
        AccessInfo elt = accessInfos.get(i);
        if (elt.level != currentLevel) {
          break;
        }
        result = LibraryManagerExt.resolveAccessPolicy(result, elt.accessPolicy);
      }
      return result;
    }
    return null;
  }

  @Override
  public boolean setAccessPolicy(IAbstractModel referencingModel_p, IAbstractLibrary referencedLibrary_p, AccessPolicy newAccessPolicy) {
    CapellaModel referencingModel = (CapellaModel) referencingModel_p;
    CapellaLibrary referencedLibrary = (CapellaLibrary) referencedLibrary_p;
    LibraryReference ref = LibraryManagerExt.getLibraryReference(referencingModel, referencedLibrary);
    if (ref != null) {
      SetAccessPolicyToLibraryReferenceCommand cmd = new SetAccessPolicyToLibraryReferenceCommand(ref, newAccessPolicy);
      MDEAdapterFactory.getExecutionManager().execute(cmd);
      return true;
    }
    return false;
  }

  @Override
  public void promoteRecInLibrary() {
    throw new UnsupportedOperationException();
  }

  @SuppressWarnings("nls")
  @Override
  public boolean doesEditionIsAuthorized(EObject object_p) {
    boolean editionIsAuthorized = true;
    FormatedLogger.incIndent(Log.RESOURCE_ACCESS_POLICY_LISTENER);
    try {
      // get the model containing the object that is going to be modified
      IAbstractModel containmentModel = getAbstractModel(object_p);
      FormatedLogger.addTextLn("containmentModel : " + containmentModel, Log.RESOURCE_ACCESS_POLICY_LISTENER);
      if (containmentModel != null) {
        // get the model where edition is done
        Project project = LibraryManagerExt.getProjectFromSession(SessionManager.INSTANCE.getSession(object_p));
        if (project != null) {
          IAbstractModel editionContextModel = cache.getAbstractModel(project);
                                                                               
          FormatedLogger.addTextLn("editionContextModel : " + editionContextModel, Log.RESOURCE_ACCESS_POLICY_LISTENER);
          if (editionContextModel != null) {
            // edition is not authorized if the containment model is a library whose the access policy is read-only in the context of the edition context model
            if ((containmentModel != editionContextModel) && (containmentModel instanceof IAbstractLibrary)) {
              AccessPolicy accessPolicy = getAccessPolicy(editionContextModel, (IAbstractLibrary) containmentModel, true);
              FormatedLogger.addTextLn("access policy : " + accessPolicy, Log.RESOURCE_ACCESS_POLICY_LISTENER);
              editionIsAuthorized = accessPolicy == AccessPolicy.READ_AND_WRITE;
              if (!editionIsAuthorized) {
                logger.warn(new EmbeddedMessage("Edition is not allowed (Access policy of library " //$NON-NLS-1$
                                                + containmentModel.getName() + " is " + accessPolicy //$NON-NLS-1$
                                                + " in the context of " + editionContextModel.getName() + ")", // $NON-NLS-1$
                    IReportManagerDefaultComponents.UI));
              }
            }
          }
        }
      }
    } catch (Exception e) {
      // do nothing
    }
    FormatedLogger.decIndent(Log.RESOURCE_ACCESS_POLICY_LISTENER);
    return editionIsAuthorized;
  }

  /*--------------------*/
  /*- PRIVATE METHODS -*/
  /*------------------*/

  private void computeAllReferencedLibraries(IAbstractModel referencingModel, Collection<IAbstractLibrary> result) {
    for (IAbstractLibrary library : getReferencedLibraries(referencingModel, false)) {
      if (!result.contains(library)) {
        result.add(library);
        computeAllReferencedLibraries(library, result);
      }
    }
  }

  private void calculateAccessPolicy(IAbstractModel model_p, IAbstractLibrary library_p, List<AccessInfo> policies, int level) {
    CapellaModel model = (CapellaModel) model_p;
    CapellaLibrary library = (CapellaLibrary) library_p;
    Collection<IAbstractLibrary> referencedLibraries = getReferencedLibraries(model, false);
    if (referencedLibraries.contains(library)) {
      policies.add(new AccessInfo(level, LibraryManagerExt.getLibraryReference(model, library).getAccessPolicy()));
    } else {
      for (IAbstractLibrary referencedLibrary : referencedLibraries) {
        calculateAccessPolicy(referencedLibrary, library, policies, level + 1);
      }
    }
  }

  private class AccessInfo {

    public int level;
    public AccessPolicy accessPolicy;

    public AccessInfo(int level_p, AccessPolicy accessPolicy_p) {
      level = level_p;
      accessPolicy = accessPolicy_p;
    }
  }

  private void addReferenceInCache(CapellaModel referencingModel, CapellaLibrary referencedLibrary) {
    // set the cache if necessary ...
    if (!cache.isReferencedLibrariesInCacheFor(referencingModel)) {
      getReferencedLibraries(referencingModel, false);
    }
    // ... and delegate the action to the cache itself
    cache.addReference(referencingModel, referencedLibrary);
  }

  private void removeReferenceInCache(CapellaModel referencingModel, CapellaLibrary referencedLibrary) {
    // set the cache if necessary ...
    if (!cache.isReferencedLibrariesInCacheFor(referencingModel)) {
      getReferencedLibraries(referencingModel, false);
    }
    // ... and delegate the action to the cache itself
    cache.removeReference(referencingModel, referencedLibrary);
  }

}
