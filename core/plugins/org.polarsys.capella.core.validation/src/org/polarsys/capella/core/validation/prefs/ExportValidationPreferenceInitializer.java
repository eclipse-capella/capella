/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.osgi.framework.FrameworkUtil;

/**
 * Preference initializer for this plugin
 *
 */
public class ExportValidationPreferenceInitializer extends AbstractPreferenceInitializer {

  public ExportValidationPreferenceInitializer() {
  }

  @Override
  public void initializeDefaultPreferences() {

    
    IEclipsePreferences node = DefaultScope.INSTANCE.getNode(FrameworkUtil.getBundle(this.getClass()).getSymbolicName());
    
    node.put(
        IExportValidationConstants.EXPORT_ONLY_CAPELLA_PREF_ID,
        String.valueOf(IExportValidationConstants.EXPORT_ONLY_CAPELLA_PREF_DEFAULT_VALUE)
    );
    
    node.put(
            IExportValidationConstants.EXPORT_ONLY_ACTIVE_PREF_ID,
            String.valueOf(IExportValidationConstants.EXPORT_ONLY_ACTIVE_PREF_DEFAULT_VALUE)
    );        

    return;
  }

}
