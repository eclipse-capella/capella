/*******************************************************************************
 * Copyright (c) 2018, 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CapellaSiriusCustomisationEnabled extends BasicTestCase {

  @Override
  public void test() throws Exception {

    // Ensure that Sirius overridden preference values are the intended ones.
    // It can imply that loading plugin order is not the expected one or default values are re-overridden by Sirius
    
    assertEclipseDefaultPreferences();
    assertSiriusPluginPreferences();
    assertSiriusUIPluginPreferences();
    assertSiriusCommonUIPluginPreferences();
  }
  
  private void assertEclipseDefaultPreferences() {
    assertFalse("Preference value for 'Prompt when saveable still open' is not the intended one",
        PlatformUI.getPreferenceStore().getBoolean(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN));
  }

  private void assertSiriusPluginPreferences() {
    IEclipsePreferences defaultScope = DefaultScope.INSTANCE.getNode(org.eclipse.sirius.tools.api.SiriusPlugin.ID);
    
    assertTrue("Preference value for 'Empty aird fragmentation' is not the intended one",
        defaultScope.getBoolean(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), false));
  }

  private void assertSiriusUIPluginPreferences() {
    IEclipsePreferences defaultScope = DefaultScope.INSTANCE.getNode(SiriusEditPlugin.ID);

    assertFalse("Preference value for 'Save when no editor' is not the intended one",
        defaultScope.getBoolean(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), true));

    assertFalse("Preference value for 'Reload on last editor close' is not the intended one",
        defaultScope.getBoolean(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), true));

    assertTrue("Preference value for 'Scale level on diagram export' is not the intended one",
        defaultScope.getInt(SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT.name(), 99) == 2);

    assertFalse("Preference value for 'Use viewpoint colors' is not the intended one",
        defaultScope.getBoolean(SiriusUIPreferencesKeys.PREF_DISPLAY_VSM_USER_FIXED_COLOR_IN_PALETTE.name(), true));
    
    assertTrue("Preference value for 'Export semantic id on SVG' is not the intended one",
        defaultScope.getBoolean(SiriusUIPreferencesKeys.PREF_EXPORT_SEMANTIC_TRACEABILITY.name(), false));

  }

  private void assertSiriusCommonUIPluginPreferences() {
    IEclipsePreferences defaultScope = DefaultScope.INSTANCE.getNode(SiriusTransPlugin.PLUGIN_ID);

    assertFalse("Preference value for 'Defensive edit validation' is not the intended one",
        defaultScope.getBoolean(CommonPreferencesConstants.PREF_DEFENSIVE_EDIT_VALIDATION, true));
  }

}
