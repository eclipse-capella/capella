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
import java.util.Hashtable;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.util.LazyCrossReferencer;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.queries.debug.DefaultPrinter;
import org.polarsys.capella.common.queries.debug.LoggerHelpers;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.core.libraries.capellaModel.CapellaModel;
import org.polarsys.capella.core.libraries.capellaModel.CapellaProject;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 */
public class LibraryManagerCache {

  protected List<IAbstractModel> loadedModels = new ArrayList<IAbstractModel>();
  protected Hashtable<String, IAbstractModel> projectId2AbstractModel = new Hashtable<String, IAbstractModel>();
  protected Hashtable<IAbstractModel, List<IAbstractLibrary>> model2referencedLibraries = new Hashtable<IAbstractModel, List<IAbstractLibrary>>();
  protected Hashtable<IAbstractModel, List<IAbstractLibrary>> model2allReferencedLibraries = new Hashtable<IAbstractModel, List<IAbstractLibrary>>();

  public List<IAbstractModel> getLoadedModels() {
    return new ArrayList<IAbstractModel>(loadedModels);
  }

  public void deleteCapellaModel(final CapellaModel model) {
    try {
	    MDEAdapterFactory.getEditingDomain().runExclusive(new Runnable() {
	      public void run() {
			loadedModels.remove(model);
		    // FIXME workaround, to be deleted once Cappela will be aligned on Sirius
		    List<Object> keys = new ArrayList<Object>();
		    for (String projectId : projectId2AbstractModel.keySet()) {
		      if (projectId2AbstractModel.get(projectId) == model) {
		        keys.add(projectId);
		      }
		    }
		    for (Object key : keys) {
		      projectId2AbstractModel.remove(key);
		    }
		    // because we do not unload resource in close session action, we must in that case unload it
		    // Otherwise, getSession of SessionManager will return the old session for a project with the same name of this one.
		    // model.session.close(new NullProgressMonitor());
		    if (model.getSession() != null) {
		      cleanSession(model.getSession());
		    }
		    MDEAdapterFactory.getResourceSet().getResources().remove(model.getProject().eResource());
	      }
	    });
	  } catch (InterruptedException exception_p) {
	    exception_p.printStackTrace();
	  }		    
  }

  // Ensure proper close of session because when a session is not opened but have been got (by SessionManager.getSession), the close does not unload the aird
  // resource.
  // Workaround until Capella will be aligned on Sirius
  private static List<AirDCrossReferenceAdapter> disableCrossReferenceAdaptersResolution(Iterable<AirdResource> resources) {
    List<AirDCrossReferenceAdapter> airdCrossReferenceAdapters = Lists.newArrayList();
    for (AirdResource representationsFileResource : resources) {
      Option<AirDCrossReferenceAdapter> optionalAirdCrossReferenceAdapter = new AirDResouceQuery(representationsFileResource).getAirDCrossReferenceAdapter();
      if (optionalAirdCrossReferenceAdapter.some()) {
        airdCrossReferenceAdapters.add(optionalAirdCrossReferenceAdapter.get());
        optionalAirdCrossReferenceAdapter.get().disableResolve();
      }
    }
    return airdCrossReferenceAdapters;
  }

  // Ensure proper close of session because when a session is not opened but have been getted (by SessionManager.getSession), the close does not unload the aird
  // resource.
  // Workaround until Capella will be aligned on Sirius
  public static void cleanSession(Session session) {
    boolean value = CrossReferencerHelper.resolutionEnabled();

    List<AirDCrossReferenceAdapter> airdCrossReferenceAdapters =
        disableCrossReferenceAdaptersResolution(Iterables.filter(session.getAllSessionResources(), AirdResource.class));

    if (session instanceof DAnalysisSessionImpl) {
      ECrossReferenceAdapter cross = (((DAnalysisSessionImpl) session).getSemanticCrossReferencer());
      if (cross instanceof LazyCrossReferencer) {
        ((LazyCrossReferencer) cross).disableResolve();
      }
    }

    try {
      CrossReferencerHelper.enableResolution(false);
      for (Resource resource : session.getAllSessionResources()) {
        resource.unload();
        MDEAdapterFactory.getResourceSet().getResources().remove(resource);
      }
      for (Resource resource : session.getSemanticResources()) {
        resource.unload();
        MDEAdapterFactory.getResourceSet().getResources().remove(resource);
      }
    } finally {
      CrossReferencerHelper.enableResolution(value);

      if (session instanceof DAnalysisSessionImpl) {
        ECrossReferenceAdapter cross = (((DAnalysisSessionImpl) session).getSemanticCrossReferencer());
        if (cross instanceof LazyCrossReferencer) {
          ((LazyCrossReferencer) cross).enableResolve();
        }
      }

      // Enable resolution for all airdCrossReferenceAdapter of session
      // resources after the closing
      for (AirDCrossReferenceAdapter airDCrossReferenceAdapter : airdCrossReferenceAdapters) {
        airDCrossReferenceAdapter.enableResolve();
      }
    }
    SessionManager.INSTANCE.remove(session);
  }

  /*-------------------------------*/
  /*- AIRD URI TO ABSTRACT MODEL -*/
  /*-----------------------------*/

  /**
   * Retrieve or create the capellaModel for the given project
   */
  public IAbstractModel getOrCreateCapellaModel(Project project) {
    CapellaModel res = null;
    if (project != null) {
      String key = project.getId();
      res = (CapellaModel) projectId2AbstractModel.get(key);
      if (res == null) {
        if (project instanceof Library) {
          res = new CapellaLibrary(project);
        } else {
          res = new CapellaProject(project);
        }
        projectId2AbstractModel.put(key, res);
        loadedModels.add(res);
        LibraryManagerExt.updateAirdReferences(res, this);
      } else {
        res.setProject(project);
      }
    }
    return res;
  }

  public IAbstractModel getAbstractModel(Project project) {
    return projectId2AbstractModel.get(project.getId());
  }

  /*---------------------------------*/
  /*- REFERENCED LIBRARIES IN AIRD -*/
  /*-------------------------------*/

  protected Hashtable<IAbstractModel, Collection<IAbstractLibrary>> model2referencedLibrariesInAird =
      new Hashtable<IAbstractModel, Collection<IAbstractLibrary>>();

  public Collection<IAbstractLibrary> getReferencedLibrariesInAird(IAbstractModel model) {
    if (model2referencedLibrariesInAird.containsKey(model)) {
      return new ArrayList<IAbstractLibrary>(model2referencedLibrariesInAird.get(model));
    }
    return null;
  }

  public void setReferencedLibrariesInAird(IAbstractModel model, Collection<IAbstractLibrary> referencedLibrariesInAird) {
    model2referencedLibrariesInAird.put(model, new ArrayList<IAbstractLibrary>(referencedLibrariesInAird));
  }

  /*-------------------------*/
  /*- REFERENCED LIBRARIES -*/
  /*-----------------------*/

  public List<IAbstractLibrary> getReferencedLibraries(IAbstractModel model) {
    if (model2referencedLibraries.containsKey(model)) {
      return new ArrayList<IAbstractLibrary>(model2referencedLibraries.get(model));
    }
    return null;
  }

  public void setReferencedLibraries(IAbstractModel model, List<IAbstractLibrary> referencedLibraries) {
    model2referencedLibraries.put(model, new ArrayList<IAbstractLibrary>(referencedLibraries));
  }

  public boolean isReferencedLibrariesInCacheFor(IAbstractModel model) {
    return model2referencedLibraries.containsKey(model);
  }

  /**
   * Adds the given reference in the cache (update algorithm for references and allReferences tables).
   * @precond the cache for the given model has been previously set.
   */
  public void addReference(IAbstractModel model, IAbstractLibrary referencedLibrary) {
    // update references table
    List<IAbstractLibrary> cacheValue = model2referencedLibraries.get(model);
    cacheValue.add(referencedLibrary);
    // update all references table
    List<IAbstractLibrary> allRefsInCache = model2allReferencedLibraries.get(model);
    if (allRefsInCache == null) {
      ILibraryManager.INSTANCE.getAllReferencedLibraries(model, false);// call for compute
    }
    // update all refs
    List<IAbstractLibrary> allRefsToAdd = new ArrayList<IAbstractLibrary>(ILibraryManager.INSTANCE.getAllReferencedLibraries(referencedLibrary, false));
    allRefsToAdd.add(referencedLibrary);
    for (IAbstractModel model2 : model2allReferencedLibraries.keySet()) {
      Collection<IAbstractLibrary> list = model2allReferencedLibraries.get(model2);
      if ((model2 == model) || list.contains(model)) {
        for (IAbstractLibrary lib : allRefsToAdd) {
          if (!list.contains(lib)) {
            list.add(lib);
          }
        }
      }
    }
  }

  /**
   * Removes the given reference in the cache (update algorithm for references and allReferences tables).
   * @precond the cache for the given model has been previously set.
   */
  public void removeReference(IAbstractModel model, IAbstractLibrary referencedLibrary) {
    // update references table
    List<IAbstractLibrary> cacheValue = model2referencedLibraries.get(model);
    cacheValue.remove(referencedLibrary);
    // update all references table
    List<IAbstractModel> keysToRemove = new ArrayList<IAbstractModel>();
    for (IAbstractModel modelKey : model2allReferencedLibraries.keySet()) {
      if (model2allReferencedLibraries.get(modelKey).contains(referencedLibrary)) {
        keysToRemove.add(modelKey);
      }
    }
    for (IAbstractModel keyToRemove : keysToRemove) {
      model2allReferencedLibraries.remove(keyToRemove);
    }
  }

  /*-----------------------------*/
  /*- ALL REFERENCED LIBRARIES -*/
  /*---------------------------*/

  public List<IAbstractLibrary> getAllReferencedLibraries(IAbstractModel model) {
    if (model2allReferencedLibraries.containsKey(model)) {
      return new ArrayList<IAbstractLibrary>(model2allReferencedLibraries.get(model));
    }
    return null;
  }

  public void setAllReferencedLibraries(IAbstractModel model, List<IAbstractLibrary> allReferencedLibraries) {
    model2allReferencedLibraries.put(model, new ArrayList<IAbstractLibrary>(allReferencedLibraries));
  }

  public boolean isAllReferencedLibrariesInCacheFor(IAbstractModel model) {
    return model2allReferencedLibraries.containsKey(model);
  }

  public void resetCacheForReferences() {
    model2allReferencedLibraries = new Hashtable<IAbstractModel, List<IAbstractLibrary>>();
    model2referencedLibraries = new Hashtable<IAbstractModel, List<IAbstractLibrary>>();
    model2referencedLibrariesInAird = new Hashtable<IAbstractModel, Collection<IAbstractLibrary>>();
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
	DefaultPrinter printer = new DefaultPrinter();
    System.out.println("---- loadedModels ----");
    System.out.println(LoggerHelpers.printCollection(loadedModels, printer));
    System.out.println("---- projectId2AbstractModel ----");
    System.out.println(LoggerHelpers.printHashTable(projectId2AbstractModel, printer, printer));
    System.out.println("---- model2allReferencedLibraries ----");
    System.out.println(LoggerHelpers.printHashTable(model2allReferencedLibraries, printer, printer));
    System.out.println("---- model2referencedLibraries ----");
    System.out.println(LoggerHelpers.printHashTable(model2referencedLibraries, printer, printer));
    System.out.println("---- model2referencedLibrariesInAird ----");
    System.out.println(LoggerHelpers.printHashTable(model2referencedLibrariesInAird, printer, printer));
    return b.toString();
  }

}
