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
 * Preference page related the EPBS Architecture dashboard page.
 */
public class EpbsArchitectureDashboardPreferencePage extends AbstractCapellaArchitectureDashboardPreferencePage {

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.dashboard.epbs.page"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.preferences.AbstractCapellaArchitectureDashboardPreferencePage#createFieldEditors(org.eclipse.swt.widgets.Group)
   */
  @Override
  protected void createFieldEditors(Group sectionsGroup_p) {
    // Create Define CI Components Section.
    addField(new PreferenceField(IEpbsArchitecturePreferences.PREFERENCE_SECTION_DEFINE_CI_COMPONENTS,
        Messages.EpbsArchitectureDashboardPage_DefineCiComponentsSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert, sectionsGroup_p,
        ProjectScope.class);
    // Refine System Capability to EPBS Architecture Section.
    addField(new PreferenceField(IEpbsArchitecturePreferences.PREFERENCE_SECTION_REFINE_SYSTEM_CAPABILITY_TO_EPBS_ARCHITECTURE,
        Messages.EpbsArchitectureDashboardPage_RefineSystemCapabilityToEpbsArchitectureSection_Title, sectionsGroup_p), UserProfileModeEnum.Expert,
        sectionsGroup_p, ProjectScope.class);
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.EpbsArchitectureDashboardPreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.EpbsArchitectureDashboardPage_Title;
  }

  @Override
  protected String getPageId() {
    return PROPERTY_PAGE_ID;
  }
}
