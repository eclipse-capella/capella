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
package org.polarsys.capella.core.commands.preferences.properties;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;
import org.polarsys.capella.core.commands.preferences.service.PreferencesItemsRegistry;
import org.polarsys.capella.core.preferences.Activator;

public class CapellaCommandsPreferencesPropertyTester extends PropertyTester {

  /*
   * 
   */
  private final String CAPELLA_COMMANDS_PREFERENCES_PROPERTY = "capellaCommandsPreferences"; //$NON-NLS-1$

  /*
   * 
   */
  private final String CAPELLA_CMD_MODELING_PREFERENCES_PROPERTY = "capellaCommandsModelingPreferences"; //$NON-NLS-1$

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

    if ((expectedValue == null) || !(expectedValue instanceof String)) {
      throw new IllegalArgumentException("expected value cannot be null and must be an instance of String"); //$NON-NLS-1$
    }

    if (CAPELLA_COMMANDS_PREFERENCES_PROPERTY.equals(property)
        || CAPELLA_CMD_MODELING_PREFERENCES_PROPERTY.equals(property)) {
      IItemDescriptor itemDescriptor = PreferencesItemsRegistry.getInstance().getDescriptor((String) expectedValue);
      boolean defaultValue = itemDescriptor != null ? itemDescriptor.isEnabledByDefault() : true;
      IEclipsePreferences commandsPreferences = InstanceScope.INSTANCE.getNode(FrameworkUtil.getBundle(Activator.class).getSymbolicName());
      return commandsPreferences.getBoolean((String) expectedValue, defaultValue);
    }
    return false;
  }

}
