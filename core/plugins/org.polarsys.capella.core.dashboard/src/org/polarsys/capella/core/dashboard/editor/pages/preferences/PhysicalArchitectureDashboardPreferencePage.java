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
import org.eclipse.swt.widgets.Group;

import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.dashboard.editor.pages.Messages;

/**
 * Preference page related the Physical Architecture dashboard page.
 */
public class PhysicalArchitectureDashboardPreferencePage extends AbstractCapellaArchitectureDashboardPreferencePage {

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.dashboard.pa.page"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.preferences.AbstractCapellaArchitectureDashboardPreferencePage#createFieldEditors(org.eclipse.swt.widgets.Group)
   */
  @Override
  protected void createFieldEditors(Group sectionsGroup_p) {
    // Create Transition from System Functions Section.
    addField(new PreferenceField(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_LF,
        Messages.PhysicalArchitectureDashboardPage_TransitionFromLFSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
    // Create Refine Physical functions, describe dataflows Section.
    addField(new PreferenceField(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_PHYSICAL_FUNCTIONS_DESCRIBE_FE,
        Messages.PhysicalArchitectureDashboardPage_RefinePhysicalFunctionsDescribeFunctionalExchangesSection_Title, sectionsGroup_p),
        UserProfileModeEnum.Expert, sectionsGroup_p, ProjectScope.class);
    // Create Define Physical Components and Actors Section.
    addField(new PreferenceField(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DEFINE_PHYSICAL_COMPONENTS_ACTORS,
        Messages.PhysicalArchitectureDashboardPage_DefinePhysicalComponentsActorsSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
    // Create Allocate Physical function to Physical components Section.
    addField(new PreferenceField(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_PHYSICAL_FUNCTION_TO_PHYSICAL_COMPONENTS,
        Messages.PhysicalArchitectureDashboardPage_AllocatePhysicalFunctionToPhysicalComponentsSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert,
        sectionsGroup_p, ProjectScope.class);
    // Create Delegate System Interfaces and create Physical Interfaces Section.
    addField(new PreferenceField(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_LOGICAL_INTERFACES_AND_CREATE_PHYSICAL_INTERFACES,
        Messages.PhysicalArchitectureDashboardPage_DelegateLogicalInterfacesCreatePhysicalInterfacesSection_Title, sectionsGroup_p),
        UserProfileModeEnum.Expert, sectionsGroup_p, ProjectScope.class);
    // Enrich Physical scenario Section.
    addField(new PreferenceField(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_PHYSICAL_SCENARIO,
        Messages.PhysicalArchitectureDashboardPage_EnrichPhysicalScenarioSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
    // Create Transverse modeling.
    addField(new PreferenceField(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING,
        Messages.AbstractCapellaArchitectureDashboardPage_TransverseModelingSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.PhysicalArchitectureDashboardPreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.PhysicalArchitectureDashboardPage_Title;
  }

  @Override
  protected String getPageId() {
    return PROPERTY_PAGE_ID;
  }
}
