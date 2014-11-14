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
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import org.eclipse.sirius.business.api.session.Session;

/**
 * Session listener that allow to handle session events trapped by the Capella Common Navigator.<br>
 * This one must be displayed (its view must be loaded) to allow implementors to be notified.<br>
 * The session manager from the Capella Common Navigator is used as a router for session events.
 */
public interface ISessionListener {
  /**
   * Sent when the session is opening.<br>
   * Not sent from the UI thread.
   * @param session_p
   */
  public void sessionOpening(Session session_p);

  /**
   * Sent when the session is open.<br>
   * Not sent from the UI thread.
   * @param session_p
   */
  public void sessionOpened(Session session_p);

  /**
   * Sent when the session is closing.<br>
   * Not sent from the UI thread.
   * @param session_p
   */
  public void sessionClosing(Session session_p);

  /**
   * Sent when the session is closed.<br>
   * Not sent from the UI thread.
   * @param session_p
   */
  public void sessionClosed(Session session_p);
}
