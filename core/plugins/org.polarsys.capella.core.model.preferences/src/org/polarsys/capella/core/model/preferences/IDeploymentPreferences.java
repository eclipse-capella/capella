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
public class IDeploymentPreferences {
  /**
   * Is multiple deployment allowed or not
   */
  public static final String PREFS_ALLOW_MULTIPLE_DEPLOYMENT = "deployment.allowed"; //$NON-NLS-1$

  /**
   * Default value for multiple deployment preference
   */
  public static final Boolean PREFS_ALLOW_MULTIPLE_DEPLOYMENT_DEFAULT = Boolean.FALSE;
}
