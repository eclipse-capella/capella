package org.polarsys.capella.core.commands.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.core.commands.preferences.ui.sirius.CapellaDiagramPreferencePage;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Initializes default values for @CapellaDiagramPreferencePage preferences
 * 
 * @author etraisnel
 *
 */
public class CapellaDiagramPreferencesInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(CapellaDiagramPreferencePage.NAME_PREF_DISPLAY_NAVIGATE_ON_DOUBLECLICK, true);
	}

}
