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
package org.polarsys.capella.common.platform.sirius.tig.ef;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener2;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Used to monitor sessions to enable / disable the Capella Cross referencing and the Data Notifier.
 */
public class SiriusSessionListener implements SessionManagerListener2 {
  private boolean _isClosing = false;
  private boolean _isOpening = false;

  private static SiriusSessionListener _instance;

  public static SiriusSessionListener getInstance() {
    if (null == _instance) {
      _instance = new SiriusSessionListener();
    }
    return _instance;
  }

  /**
   * Constructor.
   */
  private SiriusSessionListener() {
    // do nothing
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener2#notify(org.eclipse.sirius.business.api.session.Session, int)
   */
  public void notify(Session updatedSession_p, int notification_p) {
    switch (notification_p) {
      case SessionListener.OPENING:
        _isOpening = true;
      break;
      case SessionListener.OPENED:
        _isOpening = false;
      break;
      case SessionListener.CLOSING:
        _isClosing = true;
      break;
      case SessionListener.CLOSED:
        _isClosing = false;
    }
  }

  public void register() {
    if (null != _instance) {
      SessionManager.INSTANCE.addSessionsListener(_instance);
    }
  }

  public boolean isClosingSession() {
    return _isClosing;
  }

  public boolean isOpeningSession() {
    return _isOpening;
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
