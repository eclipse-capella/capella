/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.common.ui.toolkit.fields.SpacerFieldEditor;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class TransfertViewerPreferencePage extends AbstractDefaultPreferencePage {

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.ui.properties.TransfertViewerPreferencePage"; //$NON-NLS-1$

  /**
   * 
   */
  public TransfertViewerPreferencePage() {
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
    return Messages.TransfertViewerPreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.TransfertViewerPreferencePage_Title;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {
    Composite fieldEditorParent = getFieldEditorParent();

    addField(new PreferenceField(ITransfertViewerPreferences.PREFS_DISABLE_LABEL_COMPUTATION, Messages.DisableLabelComputation, fieldEditorParent),
        UserProfileModeEnum.User, fieldEditorParent, ProjectScope.class);
    addField(new SpacerFieldEditor(fieldEditorParent));
    
    Group group1 =
        createGroup(Messages.MultipleSelectionDialogPreferencePage_Group_Title, Messages.MultipleSelectionDialogPreferencePage_Group_Title, fieldEditorParent);
    addField(new PreferenceField(ITransfertViewerPreferences.PREFS_EXPAND_LEFT_VIEWER_CONTENT, Messages.LeftTransfertViewerPreferencePage_Description, group1),
        UserProfileModeEnum.User, group1, ProjectScope.class);
    addField(
        new PreferenceField(ITransfertViewerPreferences.PREFS_EXPAND_RIGHT_VIEWER_CONTENT, Messages.RightTransfertViewerPreferencePage_Description, group1),
        UserProfileModeEnum.User, group1, ProjectScope.class);

    addField(new SpacerFieldEditor(fieldEditorParent));

    Group group2 =
        createGroup(Messages.SingleSelectionDialogPreferencePage_Group_Title, Messages.SingleSelectionDialogPreferencePage_Group_Title, fieldEditorParent);
    addField(new PreferenceField(ITransfertViewerPreferences.PREFS_EXPAND_SINGLE_VIEWER_CONTENT, Messages.SingleTransfertViewerPreferencePage_Description,
        group2), UserProfileModeEnum.User, group2, ProjectScope.class);
  }
}
