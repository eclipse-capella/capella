/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;

/**
 */
public class ExportCSVPreferences extends AbstractPreferenceInitializer implements IExportCSVPreferences {
  public ExportCSVPreferences() {
  }

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = MdeCommonUiActivator.getDefault().getPreferenceStore();
    preferenceStore.setDefault(DELIMITER_KEY, IExportCSVPreferences.DELIMITER_VALUE_DEFAULT);
    preferenceStore.setDefault(OTHER_DELIMITER_KEY, OTHER_DELIMITER_VALUE_DEFAULT);
  }

  @Override
  public char getDelimiterCurrentValue() {
    String delimiterName = getDelimiterCurrentName();
    if (DELIMITER_VALUE_TAB.equals(delimiterName)) {
      return ICommonConstants.TAB_CHARACTER;
    } else if (DELIMITER_VALUE_SEMICOLON.equals(delimiterName)) {
      return ICommonConstants.SEMICOLON_CHARACTER;
    } else if (DELIMITER_VALUE_COMMA.equals(delimiterName)) {
      return ICommonConstants.COMMA_CHARACTER;
    } else if (DELIMITER_VALUE_SPACE.equals(delimiterName)) {
      return ICommonConstants.WHITE_SPACE_CHARACTER;
    } else if (DELIMITER_VALUE_TAB.equals(delimiterName)) {
      return ICommonConstants.TAB_CHARACTER;
    } else {
      String valueOther = MdeCommonUiActivator.getDefault().getPreferenceStore()
          .getString(IExportCSVPreferences.OTHER_DELIMITER_KEY);
      if (valueOther != null && !valueOther.isEmpty()) {
        return valueOther.charAt(0);
      }
    }
    return ICommonConstants.COMMA_CHARACTER;
  }

  @Override
  public String getDelimiterOtherCurrentValue() {
    IPreferenceStore preferenceStore = MdeCommonUiActivator.getDefault().getPreferenceStore();
    return preferenceStore.getString(IExportCSVPreferences.OTHER_DELIMITER_KEY);
  }

  @Override
  public String getDelimiterCurrentName() {
    IPreferenceStore preferenceStore = MdeCommonUiActivator.getDefault().getPreferenceStore();
    return preferenceStore.getString(IExportCSVPreferences.DELIMITER_KEY);
  }
}
