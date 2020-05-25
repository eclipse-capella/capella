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
package org.polarsys.capella.core.model.semantic.prefs;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.InstanceScope;

import org.polarsys.capella.core.model.semantic.internal.SemanticModelActivator;

/**
 */
public class SemanticModelPreferences extends AbstractPreferenceInitializer implements ISemanticModelPreferences {

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeDefaultPreferences() {
    new DefaultScope().getNode(SemanticModelActivator.PLUGIN_ID).putBoolean(ISemanticModelPreferences.KEY_SEMANTIC_MODE, true);
  }

  /**
   * {@inheritDoc}
   */
  public void setSemanticMode(boolean enabled_p) {
    new InstanceScope().getNode(SemanticModelActivator.PLUGIN_ID).putBoolean(ISemanticModelPreferences.KEY_SEMANTIC_MODE, enabled_p);
  }

  /**
   * {@inheritDoc}
   */
  public boolean isSemanticMode() {
    return Platform.getPreferencesService().getBoolean(SemanticModelActivator.PLUGIN_ID, ISemanticModelPreferences.KEY_SEMANTIC_MODE, false, null);
  }

}
