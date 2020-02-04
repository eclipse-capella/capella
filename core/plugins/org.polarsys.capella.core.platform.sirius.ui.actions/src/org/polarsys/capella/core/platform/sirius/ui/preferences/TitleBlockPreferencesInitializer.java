package org.polarsys.capella.core.platform.sirius.ui.preferences;

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
    // TODO Auto-generated method stub
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    // Set default delay value.
    preferenceStore.setDefault("columnField", 2);
    preferenceStore.setDefault("EnableFileSyncMonitoring", false);
  }

}
