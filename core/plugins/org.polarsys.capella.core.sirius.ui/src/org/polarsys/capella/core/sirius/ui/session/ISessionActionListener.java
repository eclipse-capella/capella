/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.session;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.sirius.business.api.session.Session;

/**
 * @deprecated shall not be used anymore
 */
@Deprecated
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
