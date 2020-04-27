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
package org.polarsys.capella.core.platform.sirius.ui.project.provider;

/**
 * Implementors can locate Capella projects wherever they want.
 */
public interface IDefaultProjectLocationProvider {
  /**
   * Get the default project location.
   * @return can be <code>null</code>.
   */
  public String getDefaultProjectLocation();

  /**
   * Get the error message to display when {@link #getDefaultProjectLocation()} returns <code>null</code>.
   * @return not <code>null</code> if no error; the error message otherwise.
   */
  public String getErrorMessage();
}
