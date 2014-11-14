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
 * Preference page related the Logical Architecture dashboard page.
 */
public class LogicalArchitectureDashboardPreferencePage extends AbstractCapellaArchitectureDashboardPreferencePage {

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.dashboard.la.page"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.preferences.AbstractCapellaArchitectureDashboardPreferencePage#createFieldEditors(org.eclipse.swt.widgets.Group)
   */
  @Override
  protected void createFieldEditors(Group sectionsGroup_p) {
    // Create Transition from System Functions Section.
    addField(new PreferenceField(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_SF,
        Messages.LogicalArchitectureDashboardPage_TransitionFromSFSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
    // Create Refine logical functions, describe dataflows Section.
    addField(new PreferenceField(ILogicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_LOGICAL_FUNCTIONS_DESCRIBE_FE,
        Messages.LogicalArchitectureDashboardPage_RefineLogicalFunctionsDescribeFunctionalExchangesSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert,
        sectionsGroup_p, ProjectScope.class);
    // Create Define Logical Components and Actors Section.
    addField(new PreferenceField(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DEFINE_LOGICAL_COMPONENTS_ACTORS,
        Messages.LogicalArchitectureDashboardPage_DefineLogicalComponentsActorsSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
    // Create Allocate logical function to logical components Section.
    addField(new PreferenceField(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_LOGICAL_FUNCTION_TO_LOGICAL_COMPONENTS,
        Messages.LogicalArchitectureDashboardPage_AllocateLogicalFunctionToLogicalComponentsSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert,
        sectionsGroup_p, ProjectScope.class);
    // Create Delegate System Interfaces and create Logical Interfaces Section.
    addField(new PreferenceField(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_SYSTEM_INTERFACES_AND_CREATE_LOGICAL_INTERFACES,
        Messages.LogicalArchitectureDashboardPage_DelegateSystemInterfacesCreateLogicalInterfacesSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert,
        sectionsGroup_p, ProjectScope.class);
    // Enrich logical scenario Section.
    addField(new PreferenceField(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_LOGICAL_SCENARIO,
        Messages.LogicalArchitectureDashboardPage_EnrichLogicalScenarioSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
    // Create Transverse modeling.
    addField(new PreferenceField(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING,
        Messages.AbstractCapellaArchitectureDashboardPage_TransverseModelingSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.LogicalArchitectureDashboardPreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.LogicalArchitectureDashboardPage_Title;
  }

  @Override
  protected String getPageId() {
    return PROPERTY_PAGE_ID;
  }
}
