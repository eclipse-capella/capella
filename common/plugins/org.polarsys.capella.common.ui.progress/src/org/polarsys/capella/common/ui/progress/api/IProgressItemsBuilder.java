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
package org.polarsys.capella.common.ui.progress.api;

/**
 * This interface should be implemented by platform specific factories allowing to create modal progress dialogs handlers.
 * @see IModalProgressDialogHandler
 */
public interface IProgressItemsBuilder {
  /**
   * The corresponding extension point ID.
   */
  public static String EXTENSION_POINT_ID = "ProgressItemsBuilder"; //$NON-NLS-1$

  /**
   * returns a platform specific modal progress dialog handler.
   * @return an instance of a platform specific class implementing IModalProgressDialogHandler.
   */
  public IModalProgressDialogHandler getModalProgressDialogHandler();
}
