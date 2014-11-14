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
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;

/**
 */
public class CapellaDummySessionListener extends SessionManagerListener.Stub {

  private static boolean _done = false;

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notify(org.eclipse.sirius.business.api.session.Session, int)
   */
  @Override
  public void notify(Session updatedSession_p, int notification_p) {
    if (SessionListener.OPENED == notification_p) {
      if (! _done ) {
        CapellaRecorderHandler.INSTANCE.startUp();
        _done = true;
      }
    }
  }
}
