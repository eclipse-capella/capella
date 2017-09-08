/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.usage.preferences;

public interface IUsagePreferences {
  /**
   * Is usage monitoring activated or not
   */
  public static final String PREFERENCES_ACTIVATE_USAGE_MONITORING = "usage.activated"; //$NON-NLS-1$
  
  public final Boolean PREFERENCES_ACTIVATE_USAGE_MONITORING_DEFAULT = Boolean.TRUE;
}
