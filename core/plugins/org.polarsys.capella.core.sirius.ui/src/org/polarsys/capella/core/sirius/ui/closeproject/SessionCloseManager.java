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
package org.polarsys.capella.core.sirius.ui.closeproject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 */
public class SessionCloseManager {

  public static void saveSession(Session session) {
    session.save(new NullProgressMonitor());
  }

  public static IEditingSession getUISession(Session session) {
    return SessionUIManager.INSTANCE.getUISession(session);
  }

  public static void closeUISession(IEditingSession uiSession, boolean saveIsNeeded) {
    uiSession.close(saveIsNeeded);
    SessionUIManager.INSTANCE.remove(uiSession);
  }

  /**
   * @param session_p
   * @return
   */
  public static boolean isDirty(Session session) {
    return SessionStatus.DIRTY.equals(session.getStatus());
  }

  public static void closeSession(Session session) {
    closeSession(session, new NullProgressMonitor());
  }

  public static void closeSession(Session session, IProgressMonitor monitor) {
    session.close(monitor);
  }

  // Ensure proper close of session because when a session is not opened but have been getted (by SessionManager.getSession), the close does not unload the aird
  // resource.
  // Workaround until TIG will be aligned on Sirius (fix since v0.8.x)
  @Deprecated
  public static void cleanSession(Session session) {
    TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(session);

    if (session instanceof DAnalysisSessionImpl) {
      SiriusCrossReferenceAdapter cross = (((DAnalysisSessionImpl) session).getSemanticCrossReferencer());
      cross.disableResolveProxy();
    }

    try {
      for (Resource resource : session.getAllSessionResources()) {
        resource.unload();
        editingDomain.getResourceSet().getResources().remove(resource);
      }
      for (Resource resource : session.getSemanticResources()) {
        resource.unload();
        editingDomain.getResourceSet().getResources().remove(resource);
      }
    } finally {
      if (session instanceof DAnalysisSessionImpl) {
        SiriusCrossReferenceAdapter cross = (((DAnalysisSessionImpl) session).getSemanticCrossReferencer());
        cross.enableResolveProxy();
      }
    }
    SessionManager.INSTANCE.remove(session);
  }
}
