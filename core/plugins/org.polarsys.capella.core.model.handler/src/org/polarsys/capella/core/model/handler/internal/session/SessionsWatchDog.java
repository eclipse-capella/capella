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
package org.polarsys.capella.core.model.handler.internal.session;

import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener2;

import org.polarsys.capella.core.model.handler.pre.commit.listener.FileModificationPreCommitListener;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.libraries.ResourceAccessPolicyListener;

/**
 * Install in {@link SessionManager} a {@link SessionManagerListener2} to monitor sessions.
 */
public class SessionsWatchDog {

  private static ResourceAccessPolicyListener libraryListener;

  /**
   * Enable session monitoring
   * @param listener_p
   */
  public static void enableSessionMonitoring(FileModificationPreCommitListener listener_p) {
    CapellaSessionListener capellaSessionListener = new CapellaSessionListener(listener_p);
    SessionManager.INSTANCE.addSessionsListener(capellaSessionListener);
    // TODO must be refactor when 1 session=1 editingDomain
    libraryListener = ResourceAccessPolicyListener.getInstance();
    MDEAdapterFactory.getEditingDomain().addResourceSetListener(libraryListener);
    // initialize the corresponding model
    // This code should be used but event opened is triggered while the melodymodeller file has not been created (in the case of a project
    // creation) ... so the getAbstractModel(session) is done for now in OpenSessionAction, NewProjectWizard.
    // ----------------------------
    // SessionManager.INSTANCE.addSessionsListener(libraryListeners);
  }

  public static ResourceAccessPolicyListener getResourceAccessPolicyListener() {
    return libraryListener;
  }
}
