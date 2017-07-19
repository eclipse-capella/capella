/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.common.ui.toolkit.fields.SpacerFieldEditor;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;

/**
 * Delete preferences page.
 */
public class DeletePreferencePage extends AbstractDefaultPreferencePage {

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.platform.sirius.ui.actions.deletion.page"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Messages.DeletePreferencePage_Description;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getPageId() {
    return PROPERTY_PAGE_ID;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.DeletePreferencePage_Title;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {
    Group confirmDeletionGroup =
        createGroup(Messages.DeletePreferencePage_DeleteGroup_Title, Messages.DeletePreferencePage_DeleteGroup_Message, getFieldEditorParent());
    addField(new BooleanFieldEditor(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, Messages.DeletePreferencePage_DeleteChoice, confirmDeletionGroup),
        UserProfileModeEnum.Expert, confirmDeletionGroup);
    addField(new PreferenceField(IDeletePreferences.PREFERENCE_DELETE_PARTS, Messages.DeletePreferencePage_DeletePartsChoice, confirmDeletionGroup),
        UserProfileModeEnum.Expert, confirmDeletionGroup, ProjectScope.class);
    // Add space.
    addField(new SpacerFieldEditor(getFieldEditorParent()));
    Group deleteRestrictionGroup =
        createGroup(Messages.DeletePreferencePage_ProtectedElementsGroup_Title, Messages.DeletePreferencePage_ProtectedElementsGroup_Message,
            getFieldEditorParent());
    addDeleteRestrictionPreferences(deleteRestrictionGroup);
  }

  /**
   * Add delete restrictions preferences.
   * @param parentGroup
   */
  private void addDeleteRestrictionPreferences(Group parentGroup) {
    IPreferenceStore preferenceStore = doGetPreferenceStore();

    int count = 0;
    String preferenceKey = CapellaModelPreferencesPlugin.getDefault().getPreference(count);
    while (preferenceStore.contains(preferenceKey)) {
      addField(new PreferenceField(preferenceKey, preferenceStore.getString(CapellaModelPreferencesPlugin.getDefault().getPreferenceTitle(count)), parentGroup),
          UserProfileModeEnum.Expert, parentGroup, ProjectScope.class);
      // Increment to compute the next key.
      count++;
      preferenceKey = CapellaModelPreferencesPlugin.getDefault().getPreference(count);
    }
  }
}
