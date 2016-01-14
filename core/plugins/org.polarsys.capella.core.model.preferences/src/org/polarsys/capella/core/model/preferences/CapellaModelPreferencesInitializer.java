/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

/**
 */
public class CapellaModelPreferencesInitializer extends AbstractPreferencesInitializer {

  /**
   * Default constructor.
   */
  public CapellaModelPreferencesInitializer() {
    super(CapellaModelPreferencesPlugin.PLUGIN_ID);
  }

  @Override
  public void initializeDefaultPreferences() {

    putString(IReuseComponentsPreferences.PREFS_ALLOW_REUSE_COMPONENTS,
        IReuseComponentsPreferences.PREFS_ALLOW_REUSE_COMPONENTS_DEFAULT.toString(), ProjectScope.class);

    putString(IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE,
        IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE_DEFAULT.toString(), ProjectScope.class);

    putString(IInheritancePreferences.PREFS_ALLOW_COMPONENT_INHERITANCE,
        IInheritancePreferences.PREFS_ALLOW_COMPONENT_INHERITANCE_DEFAULT.toString(), ProjectScope.class);

    putString(IDeploymentPreferences.PREFS_ALLOW_MULTIPLE_DEPLOYMENT,
        IDeploymentPreferences.PREFS_ALLOW_MULTIPLE_DEPLOYMENT_DEFAULT.toString(), ProjectScope.class);
    putString(IDataPreferences.PREFS_ALLOW_PRIMITIVE_SYNCHRONIZATION,
        IDataPreferences.PREFS_ALLOW_PRIMITIVE_SYNCHRONIZATION_DEFAULT.toString(), ProjectScope.class);

    // Set synchronization preferences
    putBoolean(ISynchronizationPreferences.PREFS_ALLOW_SYNC_COMPONENTPORT_TO_FUNCTIONPORT,
        ISynchronizationPreferences.PREFS_ALLOW_SYNC_COMPONENTPORT_TO_FUNCTIONPORT_DEFAULT.booleanValue(),
        ProjectScope.class);
    putBoolean(ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALLINK,
        ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALLINK_DEFAULT
            .booleanValue(), ProjectScope.class);
    putBoolean(ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALPATH,
        ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALPATH_DEFAULT
            .booleanValue(), ProjectScope.class);

    // Set inter-model integrity preferences
    putBoolean(IInterModelIntegrityPreferences.PREFS_PREVENT_ON_THE_FLY_DEPENDENCY_VIOLATION,
        IInterModelIntegrityPreferences.PREFS_PREVENT_ON_THE_FLY_DEPENDENCY_VIOLATION_DEFAULT.booleanValue(),
        ProjectScope.class);

    // Set Mode & State management preferences
    putBoolean(IModeAndStateManagementPreferences.PREFS_MIXED_MODE_STATE_ALLOWED,
        IModeAndStateManagementPreferences.PREFS_MIXED_MODE_STATE_ALLOWED_DEFAULT.booleanValue(), ProjectScope.class);
  }

}
