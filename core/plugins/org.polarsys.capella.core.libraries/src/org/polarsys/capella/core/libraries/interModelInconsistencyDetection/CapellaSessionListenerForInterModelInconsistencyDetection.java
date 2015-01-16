/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.interModelInconsistencyDetection;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;

/**
 * Session listener used to set up inter-model inconsistency detection.
 * Each time a session is opened, this listener add to it a new resource set listener (see {@link ResourceSetListenerForInterModelInconsistencyDetection}).
 * 
 * @author Erwan Brottier
 */
public class CapellaSessionListenerForInterModelInconsistencyDetection extends SessionManagerListener.Stub {
  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notify(org.eclipse.sirius.business.api.session.Session, int)
   */
  @Override
  public void notify(Session session, int notification_p) {
    switch (notification_p) {
      case SessionListener.OPENED:
        session.getTransactionalEditingDomain().addResourceSetListener(new ResourceSetListenerForInterModelInconsistencyDetection());
      break;
    }
  }
}