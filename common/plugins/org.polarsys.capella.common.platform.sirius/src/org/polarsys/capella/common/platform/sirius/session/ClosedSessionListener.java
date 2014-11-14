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
package org.polarsys.capella.common.platform.sirius.session;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;

/**
 */
public class ClosedSessionListener implements SessionListener {
  /**
   * Monitored session.
   */
  private Session _monitoredSession;

  /**
   * Constructor.
   * @param session_p
   */
  public ClosedSessionListener(Session session_p) {
    _monitoredSession = session_p;
  }

  /**
   * Get monitored session.
   * @return <code>null</code> {@link #handleClosedSession(Session)} is called.
   */
  protected Session getMonitoredSession() {
    return _monitoredSession;
  }

  /**
   * Call when notified for Closed session event i.e {@link SessionListener#CLOSED}.<br>
   * Default implementation automatically unregisters this listener from closed session.
   * @param monitoredSession_p
   */
  protected void handleClosedSession(Session monitoredSession_p) {
    // Unregister from the session
    _monitoredSession.removeListener(this);
    _monitoredSession = null;
  }

  /**
   * Call when notified for Closing session event i.e {@link SessionListener#CLOSING}.<br>
   * Default implementation does nothing.
   * @param monitoredSession_p
   */
  protected void handleClosingSession(Session monitoredSession_p) {
    // Do nothing.
  }

  /**
   * {@inheritDoc}
   */
  public void notify(int changeKind_p) {
    switch (changeKind_p) {
      case SessionListener.CLOSING:
        handleClosingSession(_monitoredSession);
      break;
      case SessionListener.CLOSED:
        handleClosedSession(_monitoredSession);
      break;
    }
  }
}
