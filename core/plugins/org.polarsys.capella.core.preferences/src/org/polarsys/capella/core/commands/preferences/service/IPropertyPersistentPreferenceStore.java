/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.service;

import java.io.IOException;

/**
 */
public interface IPropertyPersistentPreferenceStore {

  /**
   * Saves the non-default-valued preferences known to this preference store to the file from which they were originally loaded.
   * @exception java.io.IOException if there is a problem saving this store
   */
  public void save() throws IOException;

  /**
   * 
   */
  public void initilizeGuestListeners();

}
