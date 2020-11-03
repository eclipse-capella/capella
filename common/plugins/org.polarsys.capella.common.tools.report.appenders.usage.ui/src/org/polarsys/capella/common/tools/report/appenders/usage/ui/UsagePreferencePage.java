/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.tools.report.appenders.usage.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.common.tools.report.appenders.usage.preferences.IUsagePreferences;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.preferences.Activator;

/**
 * 
 */
public class UsagePreferencePage extends AbstractDefaultPreferencePage {

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.common.tools.report.appenders.usage.preferences.usagePage"; //$NON-NLS-1$

  /**
   * 
   */
  public UsagePreferencePage() {
    super(PROPERTY_PAGE_ID);
  }

  /**
   * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
   */
  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.UsagePreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.UsagePreferencePage_Title;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {
    Composite fieldEditorParent = getFieldEditorParent();

    Group group0 = createGroup(Messages.UsageMonitoringPreferencePage_Group_Title,
        Messages.UsageMonitoringPreferencePage_Group_Title, fieldEditorParent);
    addField(new PreferenceField(IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING,
        Messages.UsageMonitoring_Activated_Title, group0), UserProfileModeEnum.Expert, group0);
  }
}
