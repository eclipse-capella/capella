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
package org.polarsys.capella.core.command.recorder.ui.handler;

import org.eclipse.sirius.business.api.session.Session;

import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.ISessionListener;

/**
 */
public class CapellaDummySessionListener implements ISessionListener {

  
  private static boolean _done = false;
  
  /**
   * {@inheritDoc}
   */
  public void sessionOpening(Session session_p) {
    // Do nothing
    return;
  }

  /**
   * {@inheritDoc}
   */
  public void sessionOpened(Session session_p) {
    if (! _done ) {
      CapellaRecorderHandler.INSTANCE.startUp();
      _done = true;
    }
    return;
  }

  /**
   * {@inheritDoc}
   */
  public void sessionClosing(Session session_p) {
    //Do nothing
    return;
  }

  /**
   * {@inheritDoc}
   */
  public void sessionClosed(Session session_p) {
    //Do nothing
    return;
  }

}
