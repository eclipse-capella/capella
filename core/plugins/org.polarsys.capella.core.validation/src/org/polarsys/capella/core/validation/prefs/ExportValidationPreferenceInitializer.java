/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.validation.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import org.polarsys.capella.core.validation.CapellaValidationActivator;

/**
 * Preference initializer for this plugin
 *
 */
public class ExportValidationPreferenceInitializer extends AbstractPreferenceInitializer {

  public ExportValidationPreferenceInitializer() {
  }

  @Override
  public void initializeDefaultPreferences() {

    
    IEclipsePreferences node = new DefaultScope().getNode(CapellaValidationActivator.getDefault().getPluginId());
    
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
