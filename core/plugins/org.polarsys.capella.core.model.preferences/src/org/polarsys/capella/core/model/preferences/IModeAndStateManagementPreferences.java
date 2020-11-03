/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.preferences;

/**
 */
public interface IModeAndStateManagementPreferences {

  public static final String PREFS_MIXED_MODE_STATE_ALLOWED = "MSManagement.ModeStateMixed.Allowed"; //$NON-NLS-1$

  /**
   * Default value for prevent mixed hierarchy of Mode and State
   */
  public static final Boolean PREFS_MIXED_MODE_STATE_ALLOWED_DEFAULT = Boolean.FALSE;
}
