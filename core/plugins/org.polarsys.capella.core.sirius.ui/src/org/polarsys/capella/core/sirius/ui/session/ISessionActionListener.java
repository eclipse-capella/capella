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
package org.polarsys.capella.core.sirius.ui.session;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.sirius.business.api.session.Session;

public interface ISessionActionListener {

  /**
   * Notification before the session is opened
   */
  public IStatus preOpenSession(Session session_p);

  /**
   * Notification after the session is opened
   */
  public IStatus postOpenSession(Session session_p);

  /**
   * Notification before the session is closed
   */
  public IStatus preCloseSession(Session session_p);

  /**
   * Notification after the session is closed
   */
  public IStatus postCloseSession(Session session_p);

}
