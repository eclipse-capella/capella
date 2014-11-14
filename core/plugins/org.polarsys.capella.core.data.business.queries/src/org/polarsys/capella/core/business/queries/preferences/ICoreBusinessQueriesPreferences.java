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
package org.polarsys.capella.core.business.queries.preferences;

/**
 * Interface storing the preferences for the core business queries
 */
public interface ICoreBusinessQueriesPreferences {
  /**
   * Id of the "avoid already owned min, max , null, max length,... values" preference.
   */
  public static final String PREF_SKIP_OWNED_MIN_MAX_NULL_DEFAULT_VALUES = "Skip_owned_min_max_null_default_values"; //$NON-NLS-1$

}
