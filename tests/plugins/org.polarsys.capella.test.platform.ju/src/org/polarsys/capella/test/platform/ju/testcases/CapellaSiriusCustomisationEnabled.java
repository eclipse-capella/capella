/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testcases;

import org.eclipse.jface.preference.IPreferenceStore;
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

    IPreferenceStore store = SiriusEditPlugin.getPlugin().getPreferenceStore();

    assertTrue("Preference value for 'Prompt when saveable still open' is not the intended one", PlatformUI.getPreferenceStore()
        .getBoolean(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN) == false);

    assertTrue("Preference value for 'Defensive edit validation' is not the intended one", SiriusTransPlugin.getPlugin().getPreferenceStore()
        .getBoolean(CommonPreferencesConstants.PREF_DEFENSIVE_EDIT_VALIDATION) == false);

    assertTrue("Preference value for 'Empty aird fragmentation' is not the intended one",
        store.getBoolean(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name()) == true);

    assertTrue("Preference value for 'Save when no editor' is not the intended one",
        store.getBoolean(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name()) == false);
    
    assertTrue("Preference value for 'Reload on last editor close' is not the intended one",
        store.getBoolean(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name()) == false);

    assertTrue("Preference value for 'Scale level on diagram export' is not the intended one",
        store.getInt(SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT.name()) == 2);

    assertTrue("Preference value for 'Use viewpoint colors' is not the intended one",
        store.getBoolean(SiriusUIPreferencesKeys.PREF_DISPLAY_VSM_USER_FIXED_COLOR_IN_PALETTE.name()) == false);

  }

}
