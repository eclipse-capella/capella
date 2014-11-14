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
package org.polarsys.capella.core.model.preferences;

/**
 * @deprecated Use org.polarsys.capella.core.platform.sirius.ui.preferences.IDeletePreferences
 */
public class IDeletePreferences {
  /**
   * Confirm delete action preference.
   * @deprecated
   */
  public static final String PREFERENCE_CONFIRM_DELETE = "Confirm_Delete"; //$NON-NLS-1$
  /**
   * Default value for confirm delete action preference.
   * @deprecated
   */
  public static final Boolean PREFERENCE_CONFIRM_DELETE_DEFAULT = Boolean.TRUE;

  /**
   * Delete parts preference.
   * @deprecated
   */
  public static final String PREFERENCE_DELETE_PARTS = "Delete_Parts"; //$NON-NLS-1$

  /**
   * Default value for delete parts preference.
   * @deprecated
   */
  public static final Boolean PREFERENCE_DELETE_PARTS_DEFAULT = Boolean.FALSE;
}
