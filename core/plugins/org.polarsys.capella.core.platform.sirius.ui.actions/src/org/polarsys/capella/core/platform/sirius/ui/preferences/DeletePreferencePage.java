/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.common.ui.toolkit.fields.SpacerFieldEditor;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
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

    Composite fieldEditorParent = getFieldEditorParent();

    addField(
        new PreferenceField(IDeletePreferences.PREFERENCE_CONFIRM_DELETE,
            Messages.DeletePreferencePage_ConfirmDeleteTitle, fieldEditorParent),
        UserProfileModeEnum.Expert, fieldEditorParent, ProjectScope.class);

    addField(
        new PreferenceField(IDeletePreferences.PREFERENCE_DELETE_PROTECTED_ELEMENTS,
            Messages.DeletePreferencePage_ProtectedElements_Title, fieldEditorParent),
        UserProfileModeEnum.Expert, fieldEditorParent, ProjectScope.class);

    addField(new SpacerFieldEditor(getFieldEditorParent()));

    Group multiPartGroup = createGroup(Messages.DeletePreferencePage_MultipartGroup_Title,
        Messages.DeletePreferencePage_MultipartGroup_Description, getFieldEditorParent());

    addField(
        new PreferenceField(IDeletePreferences.PREFERENCE_DELETE_PARTS,
            Messages.DeletePreferencePage_DeletePartsChoiceTitle, multiPartGroup),
        UserProfileModeEnum.Expert, multiPartGroup, ProjectScope.class);

  }
}
