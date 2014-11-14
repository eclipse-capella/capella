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
package org.polarsys.capella.core.validation.prefs;

/**
 *
 */
public interface IExportValidationConstants {

  /** Id of the preference for export option 1 */
  public final static String EXPORT_ONLY_CAPELLA_PREF_ID ="validation.rules.export.capella.only.exe"; //$NON-NLS-1$

  /** export option 1 default value */
  public final static boolean EXPORT_ONLY_CAPELLA_PREF_DEFAULT_VALUE = false;
  
  /** Id of the preference for export option 2 */
  public final static String EXPORT_ONLY_ACTIVE_PREF_ID ="validation.rules.export.active.only.exe"; //$NON-NLS-1$

  /** export option 2 default value */
  public final static boolean EXPORT_ONLY_ACTIVE_PREF_DEFAULT_VALUE = false;

}
