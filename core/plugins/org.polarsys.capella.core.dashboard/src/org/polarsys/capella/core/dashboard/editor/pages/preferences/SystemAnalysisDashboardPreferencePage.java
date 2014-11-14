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
 * Preference page related the System Analysis dashboard page.
 */
public class SystemAnalysisDashboardPreferencePage extends AbstractCapellaArchitectureDashboardPreferencePage {

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.dashboard.sa.page"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.preferences.AbstractCapellaArchitectureDashboardPreferencePage#createFieldEditors(org.eclipse.swt.widgets.Group)
   */
  @Override
  protected void createFieldEditors(Group sectionsGroup_p) {
    // Transition From Operational Analysis Section.
    addField(new PreferenceField(ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSITION_FROM_OA,
        Messages.SystemAnalysisDashboardPage_TransitionFromOASection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p, ProjectScope.class);
    // Create Define Actors, Missions and Capabilities Section.
    addField(new PreferenceField(ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_ACTORS_MISSIONS_CAPABILITIES,
        Messages.SystemAnalysisDashboardPage_DefineActorsMissionsCapabilitiesSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
    // Create Refine system functions, describe dataflows Section.
    addField(new PreferenceField(ISystemAnalysisPreferences.PREFERENCE_SECTION_REFINE_SYSTEM_FUNCTIONS_DESCRIBE_FE,
        Messages.SystemAnalysisDashboardPage_RefineSystemFunctionsDescribeFunctionalExchangesSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert,
        sectionsGroup_p, ProjectScope.class);
    // Create Allocate system function to system and actors Section.
    addField(new PreferenceField(ISystemAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_SYSTEM_FUNCTION_TO_SYSTEM_AND_ACTORS,
        Messages.SystemAnalysisDashboardPage_AllocateSystemFunctionToSystemAndActorsSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert,
        sectionsGroup_p, ProjectScope.class);
    // Create Define Interfaces and describe scenarios.
    addField(new PreferenceField(ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_INTERFACES_DESCRIBE_SCENARIOS,
        Messages.SystemAnalysisDashboardPage_DefineInterfacesDescribeScenariosSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
    // Create Transverse modeling.
    addField(new PreferenceField(ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING,
        Messages.AbstractCapellaArchitectureDashboardPage_TransverseModelingSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.SystemAnalysisDashboardPreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.SystemAnalysisDashboardPage_Title;
  }

  @Override
  protected String getPageId() {
    return PROPERTY_PAGE_ID;
  }

}
