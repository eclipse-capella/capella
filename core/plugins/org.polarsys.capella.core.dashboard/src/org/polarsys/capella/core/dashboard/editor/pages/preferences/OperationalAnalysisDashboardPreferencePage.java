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
 * Preference page related the Operational Analysis dashboard page.
 */
public class OperationalAnalysisDashboardPreferencePage extends AbstractCapellaArchitectureDashboardPreferencePage {

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.dashboard.oa.page"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.preferences.AbstractCapellaArchitectureDashboardPreferencePage#createFieldEditors(org.eclipse.swt.widgets.Group)
   */
  @Override
  protected void createFieldEditors(Group sectionsGroup_p) {
    // Create Define Actors, Operational Entities and Operational Capabilities Section.
    addField(new PreferenceField(IOperationalAnalysisPreferences.PREFERENCE_SECTION_DEFINE_OPERATIONAL_ENTITIES_CAPABILITIES,
        Messages.OperationalAnalysisDashboardPage_DefineActorsOperationalEntitiesCapabilitiesSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert,
        sectionsGroup_p, ProjectScope.class);
    // Create Define operational activities and describe interactions Section.
    addField(new PreferenceField(IOperationalAnalysisPreferences.PREFERENCE_SECTION_DEFINE_OPERATIONAL_ACTIVITIES_INTERACTION,
        Messages.OperationalAnalysisDashboardPage_DefineOperationalActivitiesInteractionSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert,
        sectionsGroup_p, ProjectScope.class);
    // Create Allocate operational activities to operational actors, entities or roles Section.
    addField(new PreferenceField(IOperationalAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_OPERATIONAL_ACTIVITIES_TO_ACTORS_ENTITIES_ROLES,
        Messages.OperationalAnalysisDashboardPage_AllocateOperationalActivitiesToActorsEntitiesRolesSection_Title, sectionsGroup_p),
        UserProfileModeEnum.Expert, sectionsGroup_p, ProjectScope.class);
    // Create Transverse modeling.
    addField(new PreferenceField(IOperationalAnalysisPreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING,
        Messages.AbstractCapellaArchitectureDashboardPage_TransverseModelingSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.OperationalAnalysisDashboardPreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.OperationalAnalysisDashboardPage_Title;
  }

  @Override
  protected String getPageId() {
    return PROPERTY_PAGE_ID;
  }
}
