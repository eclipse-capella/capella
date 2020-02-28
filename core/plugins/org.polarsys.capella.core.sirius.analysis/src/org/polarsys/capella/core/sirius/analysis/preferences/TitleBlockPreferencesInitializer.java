/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;
import org.polarsys.capella.core.preferences.Activator;

public class TitleBlockPreferencesInitializer extends AbstractPreferencesInitializer {

  public TitleBlockPreferencesInitializer() {
    super(CapellaActionsActivator.PLUGIN_ID);
  }

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    preferenceStore.setDefault("columnFieldTitleBlock", 2);
    preferenceStore.setDefault("rowFieldTitleBlock", 2);
    preferenceStore.setDefault("tableTitleBlock",
        "Name+aql:name+Description+aql:description+Summary+aql:summary+Documentation+aql:documentation");

    preferenceStore.setDefault("defaultTitleBlock", true);
  }

}
