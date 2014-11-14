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
