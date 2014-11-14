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
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.emf.ecore.EClass;

/**
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
  final Boolean PREFERENCE_CONFIRM_DELETE_DEFAULT = Boolean.TRUE;

  /**
   * Delete parts preference.
   */
  final String PREFERENCE_DELETE_PARTS = "Delete_Parts"; //$NON-NLS-1$

  /**
   * Default value for delete parts preference.
   */
  final Boolean PREFERENCE_DELETE_PARTS_DEFAULT = Boolean.FALSE;

  /**
   * Should a delete be confirmed by the user?
   */
  public boolean isConfirmationRequired();

  /**
   * Are we deleting a part's type in multi-part projects?
   */
  public boolean isDeletingPartType();

  /**
   * May the user delete instances of the given class?
   */
  public boolean isMetaclassProtected(EClass clazz);

}
