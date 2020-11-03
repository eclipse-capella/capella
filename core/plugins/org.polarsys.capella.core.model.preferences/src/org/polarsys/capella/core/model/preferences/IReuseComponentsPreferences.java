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
public interface IReuseComponentsPreferences {
  /**
   * Is reuse of components allowed or not
   */
  public static final String PREFS_ALLOW_REUSE_COMPONENTS = "reuse.components.allowed"; //$NON-NLS-1$

  /**
   * Default value for reuse of components preference
   */
  public static final Boolean PREFS_ALLOW_REUSE_COMPONENTS_DEFAULT = Boolean.FALSE;
}
