/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;

/**
 */
public class DeletePreferences extends AbstractPreferencesInitializer implements IDeletePreferences {

  /**
   * @param pluginID
   */
  public DeletePreferences() {
    super(CapellaActionsActivator.PLUGIN_ID);
    new ProtectedElementsPreferences();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeDefaultPreferences() {
    putBoolean(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, true, DefaultScope.class);
    putBoolean(IDeletePreferences.PREFERENCE_DELETE_PARTS, false, ProjectScope.class);
  }

  /**
   * {@inheritDoc}
   */
  public boolean isConfirmationRequired() {
    return getBoolean(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, false);
  }

  /**
   * {@inheritDoc}
   */
  public boolean isDeletingPartType() {
    return getBoolean(IDeletePreferences.PREFERENCE_DELETE_PARTS, true);
  }

  /**
   * {@inheritDoc}
   */
  public boolean isMetaclassProtected(EClass clazz) {
    return CapellaActionsActivator.getDefault().isMetaclassProtected(clazz);
  }

}
