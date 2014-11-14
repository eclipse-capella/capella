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
package org.polarsys.capella.core.commands.preferences.properties;

import java.util.Set;

import org.eclipse.core.commands.Command;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

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

  /*
   * 
   */
  private IEclipsePreferences commandsPreferences;

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

    commandsPreferences = new InstanceScope().getNode(Activator.PLUGIN_ID);
    if ((expectedValue == null) || !(expectedValue instanceof String)) {
      throw new IllegalArgumentException("expected value canot be nulle and must be an instance of String"); //$NON-NLS-1$
    }

    if (CAPELLA_COMMANDS_PREFERENCES_PROPERTY.equals(property) || CAPELLA_CMD_MODELING_PREFERENCES_PROPERTY.equals(property)) {
      IItemDescriptor itemDescriptor = PreferencesItemsRegistry.getInstance().getDescriptor((String) expectedValue);
      boolean defaultValue = itemDescriptor != null ? itemDescriptor.isEnabledByDefault() : true;

      ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);

      Set<String> definedCommands = Activator.preferencedCommands;
      for (Object object : definedCommands) {
        String currentId = (String) object;
        Command currentCommand = commandService.getCommand(currentId);
        PreferencesHandler.getInstance(currentCommand);
        commandService.refreshElements(currentCommand.getId(), null);
      }

      return commandsPreferences.getBoolean((String) expectedValue, defaultValue);
    }
    return false;
  }

}
