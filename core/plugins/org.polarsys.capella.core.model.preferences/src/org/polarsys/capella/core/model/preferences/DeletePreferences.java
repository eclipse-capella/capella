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

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

/**
 */
public class DeletePreferences extends AbstractPreferencesInitializer implements IDeletePreferences {

  /**
   * @param pluginID
   */
  public DeletePreferences() {
    super(CapellaModelPreferencesPlugin.PLUGIN_ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeDefaultPreferences() {
    putBoolean(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, true, DefaultScope.class);
    putBoolean(IDeletePreferences.PREFERENCE_DELETE_PARTS, false, ProjectScope.class);
    putBoolean(IDeletePreferences.PREFERENCE_DELETE_PROTECTED_ELEMENTS, true, ProjectScope.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isConfirmationRequired() {
    return getBoolean(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDeletingPartType() {
    return getBoolean(IDeletePreferences.PREFERENCE_DELETE_PARTS, true);
  }

  @Override
  public boolean isDeleteProtectedElements() {
    return getBoolean(IDeletePreferences.PREFERENCE_DELETE_PROTECTED_ELEMENTS, true);
  }

}
