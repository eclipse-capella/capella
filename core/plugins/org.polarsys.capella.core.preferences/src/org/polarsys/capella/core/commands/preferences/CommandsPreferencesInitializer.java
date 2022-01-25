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
package org.polarsys.capella.core.commands.preferences;

import java.util.Collection;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;
import org.polarsys.capella.core.commands.preferences.service.PreferencesItemsRegistry;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class CommandsPreferencesInitializer extends AbstractPreferenceInitializer {

  /**
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {

    Collection<IItemDescriptor> constraints = PreferencesItemsRegistry.getInstance().getAllDescriptors();

    IEclipsePreferences eclipsePreferenceNode = new DefaultScope().getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName());

    for (IItemDescriptor capellaPreferenceNode : constraints) {
      eclipsePreferenceNode.put(capellaPreferenceNode.getId(), String.valueOf(capellaPreferenceNode.isEnabledByDefault()));
    }
    
    IPreferenceStore store = DiagramUIPlugin.getPlugin().getPreferenceStore();
    store.setDefault(IPreferenceConstants.PREF_RULER_UNITS, RulerProvider.UNIT_PIXELS);
    store.setDefault(IPreferenceConstants.PREF_GRID_SPACING, 10);
  }
}
