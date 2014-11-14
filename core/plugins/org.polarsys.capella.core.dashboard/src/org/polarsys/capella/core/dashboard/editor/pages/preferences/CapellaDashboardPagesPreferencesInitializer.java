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
package org.polarsys.capella.core.dashboard.editor.pages.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.dashboard.CapellaDashboardActivator;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesInitializer;

/**
 * Capella Dashboard pages preferences initializer.
 */
public class CapellaDashboardPagesPreferencesInitializer extends AbstractPreferencesInitializer {
  /**
   * @param pluginID_p
   */
  public CapellaDashboardPagesPreferencesInitializer() {
    super(CapellaDashboardActivator.PLUGIN_ID);
    new CapellaModelPreferencesInitializer();
  }

  /**
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  // @Override
  public void initializeDefaultScopePreferences() {

    IPreferenceStore preferenceStore = new ScopedPreferenceStore(new DefaultScope(), CapellaDashboardActivator.getDefault().getBundle().getSymbolicName()); // CapellaDashboardActivator.getDefault().getPreferenceStore();
    // Transverse preferences.
    preferenceStore.setDefault(ITransversePreferences.PREFERENCE_FUNCTIONAL_ANALYSIS_ACTIVITIES_SECTIONS, true);
    preferenceStore.setDefault(ITransversePreferences.PREFERENCE_INTERFACE_ENGINEERING_ACTIVITIES_SECTIONS, true);

    // Operational Analysis dashboard default preferences.
    preferenceStore.setDefault(IOperationalAnalysisPreferences.PREFERENCE_SECTION_DEFINE_OPERATIONAL_ENTITIES_CAPABILITIES, true);
    preferenceStore.setDefault(IOperationalAnalysisPreferences.PREFERENCE_SECTION_DEFINE_OPERATIONAL_ACTIVITIES_INTERACTION, true);
    preferenceStore.setDefault(IOperationalAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_OPERATIONAL_ACTIVITIES_TO_ACTORS_ENTITIES_ROLES, true);
    preferenceStore.setDefault(IOperationalAnalysisPreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true);

    // System Analysis dashboard default preferences.
    preferenceStore.setDefault(ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSITION_FROM_OA, true);
    preferenceStore.setDefault(ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_ACTORS_MISSIONS_CAPABILITIES, true);
    preferenceStore.setDefault(ISystemAnalysisPreferences.PREFERENCE_SECTION_REFINE_SYSTEM_FUNCTIONS_DESCRIBE_FE, true);
    preferenceStore.setDefault(ISystemAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_SYSTEM_FUNCTION_TO_SYSTEM_AND_ACTORS, true);
    preferenceStore.setDefault(ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_INTERFACES_DESCRIBE_SCENARIOS, true);
    preferenceStore.setDefault(ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true);

    // Logical Architecture dashboard default preferences.
    preferenceStore.setDefault(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_SF, true);
    preferenceStore.setDefault(ILogicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_LOGICAL_FUNCTIONS_DESCRIBE_FE, true);
    preferenceStore.setDefault(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DEFINE_LOGICAL_COMPONENTS_ACTORS, true);
    preferenceStore.setDefault(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_LOGICAL_FUNCTION_TO_LOGICAL_COMPONENTS, true);
    preferenceStore.setDefault(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_SYSTEM_INTERFACES_AND_CREATE_LOGICAL_INTERFACES, true);
    preferenceStore.setDefault(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_LOGICAL_SCENARIO, true);
    preferenceStore.setDefault(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true);

    // Physical Architecture dashboard default preferences.
    preferenceStore.setDefault(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_LF, true);
    preferenceStore.setDefault(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_PHYSICAL_FUNCTIONS_DESCRIBE_FE, true);
    preferenceStore.setDefault(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DEFINE_PHYSICAL_COMPONENTS_ACTORS, true);
    preferenceStore.setDefault(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_PHYSICAL_FUNCTION_TO_PHYSICAL_COMPONENTS, true);
    preferenceStore.setDefault(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_LOGICAL_INTERFACES_AND_CREATE_PHYSICAL_INTERFACES, true);
    preferenceStore.setDefault(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_PHYSICAL_SCENARIO, true);
    preferenceStore.setDefault(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true);

    // EPBS Architecture dashboard default preferences.
    preferenceStore.setDefault(IEpbsArchitecturePreferences.PREFERENCE_SECTION_DEFINE_CI_COMPONENTS, true);
    preferenceStore.setDefault(IEpbsArchitecturePreferences.PREFERENCE_SECTION_REFINE_SYSTEM_CAPABILITY_TO_EPBS_ARCHITECTURE, true);
  }

  @Override
  public void initializeDefaultPreferences() {

    // initializeDefaultScopePreferences();
    putBoolean(ITransversePreferences.PREFERENCE_FUNCTIONAL_ANALYSIS_ACTIVITIES_SECTIONS, true, ProjectScope.class);
    putBoolean(ITransversePreferences.PREFERENCE_INTERFACE_ENGINEERING_ACTIVITIES_SECTIONS, true, ProjectScope.class);

    // Operational Analysis dashboard default preferences.
    putBoolean(IOperationalAnalysisPreferences.PREFERENCE_SECTION_DEFINE_OPERATIONAL_ENTITIES_CAPABILITIES, true, ProjectScope.class);
    putBoolean(IOperationalAnalysisPreferences.PREFERENCE_SECTION_DEFINE_OPERATIONAL_ACTIVITIES_INTERACTION, true, ProjectScope.class);
    putBoolean(IOperationalAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_OPERATIONAL_ACTIVITIES_TO_ACTORS_ENTITIES_ROLES, true, ProjectScope.class);
    putBoolean(IOperationalAnalysisPreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true, ProjectScope.class);

    // System Analysis dashboard default preferences.
    putBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSITION_FROM_OA, true, ProjectScope.class);
    putBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_ACTORS_MISSIONS_CAPABILITIES, true, ProjectScope.class);
    putBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_REFINE_SYSTEM_FUNCTIONS_DESCRIBE_FE, true, ProjectScope.class);
    putBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_SYSTEM_FUNCTION_TO_SYSTEM_AND_ACTORS, true, ProjectScope.class);
    putBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_INTERFACES_DESCRIBE_SCENARIOS, true, ProjectScope.class);
    putBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true, ProjectScope.class);

    // Logical Architecture dashboard default preferences.
    putBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_SF, true, ProjectScope.class);
    putBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_LOGICAL_FUNCTIONS_DESCRIBE_FE, true, ProjectScope.class);
    putBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DEFINE_LOGICAL_COMPONENTS_ACTORS, true, ProjectScope.class);
    putBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_LOGICAL_FUNCTION_TO_LOGICAL_COMPONENTS, true, ProjectScope.class);
    putBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_SYSTEM_INTERFACES_AND_CREATE_LOGICAL_INTERFACES, true, ProjectScope.class);
    putBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_LOGICAL_SCENARIO, true, ProjectScope.class);
    putBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true, ProjectScope.class);

    // Physical Architecture dashboard default preferences.
    putBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_LF, true, ProjectScope.class);
    putBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_PHYSICAL_FUNCTIONS_DESCRIBE_FE, true, ProjectScope.class);
    putBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DEFINE_PHYSICAL_COMPONENTS_ACTORS, true, ProjectScope.class);
    putBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_PHYSICAL_FUNCTION_TO_PHYSICAL_COMPONENTS, true, ProjectScope.class);
    putBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_LOGICAL_INTERFACES_AND_CREATE_PHYSICAL_INTERFACES, true, ProjectScope.class);
    putBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_PHYSICAL_SCENARIO, true, ProjectScope.class);
    putBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true, ProjectScope.class);

    // EPBS Architecture dashboard default preferences.
    putBoolean(IEpbsArchitecturePreferences.PREFERENCE_SECTION_DEFINE_CI_COMPONENTS, true, ProjectScope.class);
    putBoolean(IEpbsArchitecturePreferences.PREFERENCE_SECTION_REFINE_SYSTEM_CAPABILITY_TO_EPBS_ARCHITECTURE, true, ProjectScope.class);

  }

}
