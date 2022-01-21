package org.polarsys.capella.core.ui.semantic.browser.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserPreferences;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

    private String bundleName;
    
    public PreferenceInitializer() {
        bundleName = FrameworkUtil.getBundle(this.getClass()).getSymbolicName();
    }

    @Override
    public void initializeDefaultPreferences() {
        DefaultScope.INSTANCE.getNode(bundleName).putBoolean(CapellaBrowserPreferences.PREFS_DISABLE_SEMANTIC_BROWSER_SYNC_ON_STARTUP, false);
    }

}
