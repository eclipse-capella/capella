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

import org.eclipse.core.resources.IProject;

/**
 */
public interface ISessionAdvisor {
  /***
   * Performs arbitrary actions before the session for given project is opened.
   * @param containingProject_p
   * @param callbackRunnable_p
   */
  public void preSessionOpen(IProject containingProject_p, Runnable callbackRunnable_p);

  /***
   * Performs arbitrary actions before the session for given project is closed.
   * @param containingProject_p
   */
  public void postSessionClosed(IProject containingProject_p);
}
