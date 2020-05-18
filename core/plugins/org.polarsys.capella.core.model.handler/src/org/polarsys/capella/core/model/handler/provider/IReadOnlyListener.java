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
package org.polarsys.capella.core.model.handler.provider;

/**
 * Handler to delegate the read only section behavior.
 */
public interface IReadOnlyListener {
  /**
   * This method MUST be called from the UI Thread or an 'InvalidThreadAccess' exception may occur
   * @param enabled_p
   */
  void setEnabled(boolean enabled_p);

  /**
   * This method MUST be called from the UI Thread or an 'InvalidThreadAccess' exception may occur
   */
  void refreshTitleBar();
}
