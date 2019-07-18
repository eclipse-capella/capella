/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.model.preferences.ProtectedElementsPreferences;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class ExportCSVPreferences extends AbstractPreferencesInitializer implements IExportCSVPreferences {
  /**
   * @param pluginID
   */
  public ExportCSVPreferences() {
    super(CapellaModelPreferencesPlugin.PLUGIN_ID);
    new ProtectedElementsPreferences();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeDefaultPreferences() {
    putString(DELIMITER_NAME_CURRENT, DELIMITER_NAME_COMMA, ProjectScope.class);
    putString(DELIMITER_VALUE_OTHER, DELIMITER_VALUE_OTHER_DEFAULT, ProjectScope.class);

    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    preferenceStore.setDefault(DELIMITER_NAME_CURRENT, IExportCSVPreferences.DELIMITER_NAME_COMMA);
    preferenceStore.setDefault(DELIMITER_VALUE_OTHER, DELIMITER_VALUE_OTHER_DEFAULT);
  }

  @Override
  public char getDelimiterCurrentValue() {
    String delimiterName = getDelimiterCurrentName();
    switch (delimiterName) {
    case IExportCSVPreferences.DELIMITER_NAME_TAB:
      return ICommonConstants.TAB_CHARACTER;
    case IExportCSVPreferences.DELIMITER_NAME_SEMICOLON:
      return ICommonConstants.SEMICOLON_CHARACTER;
    case IExportCSVPreferences.DELIMITER_NAME_COMMA:
      return ICommonConstants.COMMA_CHARACTER;
    case IExportCSVPreferences.DELIMITER_NAME_SPACE:
      return ICommonConstants.WHITE_SPACE_CHARACTER;
    case IExportCSVPreferences.DELIMITER_NAME_OTHER:
      String valueOther = Activator.getDefault().getPreferenceStore()
          .getString(IExportCSVPreferences.DELIMITER_VALUE_OTHER);
      if (valueOther != null) {
        return valueOther.charAt(0);
      }
      return DELIMITER_VALUE_DEFAULT;
    }
    return DELIMITER_VALUE_DEFAULT;
  }

  @Override
  public String getDelimiterOtherCurrentValue() {
    return getString(IExportCSVPreferences.DELIMITER_VALUE_OTHER, false);
  }

  @Override
  public String getDelimiterCurrentName() {
    return getString(IExportCSVPreferences.DELIMITER_NAME_CURRENT, false);
  }
}
