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
