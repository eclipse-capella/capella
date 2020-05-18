/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
 *
 */
public interface IDeletePreferences {

  public static final IDeletePreferences INSTANCE = new DeletePreferences();

  /**
   * Confirm delete action preference.
   */
  final String PREFERENCE_CONFIRM_DELETE = "Confirm_Delete";//$NON-NLS-1$

  /**
   * Default value for confirm delete action preference.
   */
  final boolean PREFERENCE_CONFIRM_DELETE_DEFAULT = true;

  /**
   * Delete parts preference.
   */
  final String PREFERENCE_DELETE_PARTS = "Delete_Parts"; //$NON-NLS-1$

  /**
   * Default value for delete parts preference.
   */
  final boolean PREFERENCE_DELETE_PARTS_DEFAULT = false;

  final String PREFERENCE_DELETE_PROTECTED_ELEMENTS = "Delete_Protected_Elements"; //$NON-NLS-1$

  final boolean PREFERENCE_DELETE_PREOTECTED_ELEMENTS_DEFAULT = false;

  /**
   * Should a delete be confirmed by the user?
   */
  public boolean isConfirmationRequired();

  /**
   * Are we deleting a part's type in multi-part projects?
   */
  public boolean isDeletingPartType();

  /**
   * May the user delete the current element.
   */
  public boolean isDeleteProtectedElements();

}
