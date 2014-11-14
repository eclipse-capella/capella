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

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener2;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import org.polarsys.capella.core.model.handler.post.commit.listener.ProxyResolutionResourceSetListener;
import org.polarsys.capella.core.model.handler.pre.commit.listener.FileModificationPreCommitListener;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 * Used to monitor sessions to enable / disable the Capella pre commit listener on SessionListener.ABOUT_TO_BE_REPLACED / SessionListener.REPLACED and to install
 * a saving policy.
 */
public class CapellaSessionListener implements SessionManagerListener2 {
  private CapellaSavingPolicy _savingPolicy;
  private FileModificationPreCommitListener _preCommitListener;
  private ProxyResolutionResourceSetListener _proxyListener;

  /**
   * Constructor.
   * @param preCommitListener_p
   */
  public CapellaSessionListener(FileModificationPreCommitListener preCommitListener_p) {
    _preCommitListener = preCommitListener_p;
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener2#notify(org.eclipse.sirius.business.api.session.Session, int)
   */
  public void notify(Session updatedSession_p, int notification_p) {
    switch (notification_p) {
      case SessionListener.ABOUT_TO_BE_REPLACED:
        // Deactivate the validateEdit check as the session is unloading / reloading some fragments.
        _preCommitListener.setDisableValidateEdit(true);
      break;
      case SessionListener.REPLACED:
        // Activate the validateEdit check as the session completed unloading / reloading some fragments.
        _preCommitListener.setDisableValidateEdit(false);
      break;
      case SessionListener.OPENING:
        if (null == _savingPolicy) {
          _savingPolicy = new CapellaSavingPolicy(MDEAdapterFactory.getEditingDomain());
        }

        // Find maybe a better way to register it
        if (null == _proxyListener) {
          _proxyListener = new ProxyResolutionResourceSetListener();
          MDEAdapterFactory.getEditingDomain().addResourceSetListener(_proxyListener);
        }

        // Set a custom implementation of the saving policy to avoid temporary file creation to detect real changes.
        updatedSession_p.setSavingPolicy(_savingPolicy);

        if (updatedSession_p instanceof DAnalysisSessionImpl) {
          ((DAnalysisSessionImpl) updatedSession_p).setDisposeEditingDomainOnClose(false);
        }
      break;
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(org.eclipse.sirius.description.Viewpoint)
   */
  @SuppressWarnings("deprecation")
  public void viewpointDeselected(Viewpoint deselectedViewpoint_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(org.eclipse.sirius.description.Viewpoint)
   */
  @SuppressWarnings("deprecation")
  public void viewpointSelected(Viewpoint selectedViewpoint_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyAddSession(org.eclipse.sirius.business.api.session.Session)
   */
  @SuppressWarnings("deprecation")
  public void notifyAddSession(Session newSession_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(org.eclipse.sirius.business.api.session.Session)
   */
  @SuppressWarnings("deprecation")
  public void notifyRemoveSession(Session removedSession_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyUpdatedSession(org.eclipse.sirius.business.api.session.Session)
   */
  @SuppressWarnings("deprecation")
  public void notifyUpdatedSession(Session updated_p) {
    // Do nothing.
  }
}
