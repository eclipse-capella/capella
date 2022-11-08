/*******************************************************************************
 * Copyright (c) 2020, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;

public class TitleBlockPreferencePage extends AbstractDefaultPreferencePage {

  public static final String PAGE_ID = "org.polarsys.capella.core.platform.sirius.ui.actions.preferences.TitleBlockPage"; //$NON-NLS-1$

  public static final String TABLE_CONTENT_PREFERENCE_STORE = "tableTitleBlock"; //$NON-NLS-1$

  public static final String COLUMNS_NUMBER_PREFERENCE_STORE = "columnsNumberTitleBlock"; //$NON-NLS-1$

  public static final String LINES_NUMBER_PREFERENCE_STORE = "linesNumberTitleBlock"; //$NON-NLS-1$

  public static final String DEFAULT_TITLEBLOCK_PREFERENCE_STORE = "defaultTitleBlock"; //$NON-NLS-1$

  public static final String SEPARATOR = "SEPARATOR"; //$NON-NLS-1$

  public static final String DEFAULT_TABLE = "Name" + SEPARATOR + "feature:name" + SEPARATOR + "Last change date"
      + SEPARATOR + "aql:self.getLastModificationDate()" + SEPARATOR + "Summary" + SEPARATOR + "aql:self.target.summary"
      + SEPARATOR + "Description" + SEPARATOR + "feature:documentation";

  private PreferenceField defaultTitleBlockFieldEditor;

  private StringFieldEditor defaultTitleBlockTimeZoneEditor;

  private FieldEditor titleBlockEditor;

  public TitleBlockPreferencePage() {
    super(PAGE_ID);
  }

  @Override
  protected String getPageTitle() {
    return Messages.TitleBlockPreferencePage_Title;
  }

  @Override
  protected String getPageDescription() {
    return Messages.TitleBlockPreferencePage_Description;
  }

  @Override
  protected void createFieldEditors() {
    // createTimeZoneField();
    createCheckBox();
    createTable();
  }

  private void createCheckBox() {
    defaultTitleBlockFieldEditor = new PreferenceField(DEFAULT_TITLEBLOCK_PREFERENCE_STORE,
        Messages.TitleBlockPreferencePage_AddDefaultTitleBlock, getFieldEditorParent());
    addField(defaultTitleBlockFieldEditor, UserProfileModeEnum.Expert, getFieldEditorParent(), ProjectScope.class);
  }

  private void createTable() {
    titleBlockEditor = new TitleBlockPreferenceField(getFieldEditorParent());
    addField(titleBlockEditor, UserProfileModeEnum.Expert, getFieldEditorParent(), ProjectScope.class);
  }

  // private void createTimeZoneField() {
  // defaultTitleBlockTimeZoneEditor = new StringFieldEditor(DEFAULT_TITLEBLOCK_TIME_ZONE_PREFERENCE_STORE,
  // Messages.TitleBlockPreferencePage_TimeZone, 10, getFieldEditorParent());
  //
  // addField(defaultTitleBlockTimeZoneEditor, UserProfileModeEnum.Expert, getFieldEditorParent(), ProjectScope.class);
  // }
}
