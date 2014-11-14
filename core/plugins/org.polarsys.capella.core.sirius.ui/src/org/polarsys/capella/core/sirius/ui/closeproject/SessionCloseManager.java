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
package org.polarsys.capella.core.sirius.ui.closeproject;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.business.internal.session.danalysis.ResourceSaveDiagnose;
import org.eclipse.sirius.common.tools.api.util.LazyCrossReferencer;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.polarsys.capella.core.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.core.libraries.capellaModel.CapellaModel;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 */
public class SessionCloseManager {

  private static boolean DEBUG = false;

  public static boolean isSaveable(Session session, Collection<IFile> unsaveableFiles) {
    if (DEBUG) {
      System.out.println("> isSaveable");
      System.out.println("  >> session : " + session);
    }
    boolean result = SessionHelper.areSessionResourcesSaveable(session, unsaveableFiles);
    if (DEBUG) {
      System.out.println("  >< unsaveableFiles : " + unsaveableFiles);
      System.out.println("  << " + result);
    }
    return result;
  }

  public static boolean doesContainModifiedResources(CapellaModel model) {
    if (DEBUG) {
      System.out.println("> doesContainModifiedResources");
      System.out.println("  >> model : " + model);
    }
    List<Resource> ownedResources = model.getOwnedResources();
    boolean changesFound = false;
    for (int i = 0; !changesFound && (i < ownedResources.size()); i++) {
      Resource resource = ownedResources.get(i);
      if (hasChangesToSave(resource)) {
        changesFound = true;
      }
    }
    if (DEBUG) {
      System.out.println("  << " + changesFound);
    }
    return changesFound;
  }

  // COPIED FROM CapellaSavingPolicy (part of PATCH DUE TO TIG LIMITATIONS (LIBRARIES CONTEXT))
  private static Map<?, ?> saveOptions;

  private static boolean hasChangesToSave(final Resource resource) {
    boolean hasChangesToSave = false;
    final ResourceSaveDiagnose diagnose = new ResourceSaveDiagnose(resource);
    try {
      Map<Object, Object> mergedOptions = new HashMap<Object, Object>(getDefaultSaveOptions());
      if (saveOptions != null) {
        mergedOptions.putAll(saveOptions);
      }
      hasChangesToSave = diagnose.isSaveable() && diagnose.hasDifferentSerialization(mergedOptions);
    } catch (final IOException e) {
      SiriusPlugin.getDefault().error("Error saving resource", e);
    }
    return hasChangesToSave;
  }

  private static Map<?, ?> getDefaultSaveOptions() {
    final Map<Object, Object> defaultSaveOptions = new HashMap<Object, Object>();
    defaultSaveOptions.put(XMLResource.OPTION_FLUSH_THRESHOLD, Integer.valueOf(0x01000000));
    defaultSaveOptions.put(XMLResource.OPTION_USE_FILE_BUFFER, Boolean.TRUE);
    return defaultSaveOptions;
  }

  // END COPIED FROM CapellaSavingPolicy

  public static void saveSession(Session session) {
    if (DEBUG) {
      System.out.println("> saveSession");
      System.out.println("  >> session : " + session);
    }
    session.save(new NullProgressMonitor());
    if (DEBUG) {
      System.out.println("  <<");
    }
  }

  public static IEditingSession getUISession(Session session) {
    if (DEBUG) {
      System.out.println("> getUISession");
      System.out.println("  >> session : " + session);
    }
    IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);
    if (DEBUG) {
      System.out.println("  << " + editingSession);
    }
    return editingSession;
  }

  public static void closeUISession(IEditingSession uiSession, boolean saveIsNeeded) {
    if (DEBUG) {
      System.out.println("> closeUISession");
      System.out.println("  >> uiSession : " + uiSession);
      System.out.println("  >> saveIsNeeded : " + saveIsNeeded);
    }
    uiSession.close(saveIsNeeded);
    if (DEBUG) {
      System.out.println("  <<");
    }
  }

  public static void removeUiSession(IEditingSession uiSession) {
    if (DEBUG) {
      System.out.println("> removeUiSession");
      System.out.println("  >> uiSession : " + uiSession);
    }
    SessionUIManager.INSTANCE.remove(uiSession);
    if (DEBUG) {
      System.out.println("  <<");
    }
  }

  /**
   * @param session_p
   * @return
   */
  public static boolean isDirty(Session session) {
    if (DEBUG) {
      System.out.println("> isDirty");
      System.out.println("  >> session : " + session);
    }
    boolean result = SessionStatus.DIRTY.equals(session.getStatus());
    if (DEBUG) {
      System.out.println("  << " + result);
    }
    return result;
  }

  /** calculate the set of all models (libraries and project) that reference or are referenced by the current model */
  public static List<CapellaModel> getLibraryGraphSet(CapellaModel currentModel) {
    if (DEBUG) {
      System.out.println("> getLibraryGraphSet");
      System.out.println("  >> currentModel : " + currentModel);
    }
    List<CapellaModel> models = LibraryManagerExt.computeGraphSet(currentModel);
    if (DEBUG) {
      System.out.println("  << ");
      for (CapellaModel model : models) {
        System.out.println("     " + model);
      }
    }
    return models;
  }

  public static void closeSession(Session session) {
    closeSession(session, null);
  }

  public static void closeSession(Session session, IProgressMonitor monitor) {
    if (DEBUG) {
      System.out.println("> closeSession");
      System.out.println("  >> session : " + session);
    }
    session.close(monitor);
    if (DEBUG) {
      System.out.println("  <<");
    }
  }

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
  // Workaround until TIG will be aligned on Sirius
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
}
