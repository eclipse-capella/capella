package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.preferences.Activator;

public class PreferencesInitilizer extends AbstractPreferencesInitializer {

  public PreferencesInitilizer() {
    super("org.polarsys.capella.core.platform.sirius.ui.actions");
  }

  @Override
  public void initializeDefaultPreferences() {
    // TODO Auto-generated method stub
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    // Set default delay value.
    preferenceStore.setDefault("delayField", 2);
    preferenceStore.setDefault("EnableFileSyncMonitoring", false);
  }

}
