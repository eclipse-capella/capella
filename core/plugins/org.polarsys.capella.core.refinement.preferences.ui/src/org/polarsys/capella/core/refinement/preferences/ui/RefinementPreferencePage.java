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
package org.polarsys.capella.core.refinement.preferences.ui;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.refinement.preferences.IRefinementPreferences;
import org.polarsys.capella.core.refinement.preferences.services.RefinementPrefServices;
import org.polarsys.capella.core.refinement.preferences.ui.messages.RefinementPrefUIMessages;

/**
 */
public class RefinementPreferencePage extends AbstractDefaultPreferencePage {

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.common.ui.resources.prefs.rafinnementPage"; //$NON-NLS-1$

  /*
   * 
   */
  private Button _dynbutton = null;

  /**
   * 
   */
  public RefinementPreferencePage() {
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
    return RefinementPrefUIMessages.RefinementPreferencePage_Description;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return RefinementPrefUIMessages.RefinementPreferencePage_Title;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {

    final Composite fieldEditorParent = getFieldEditorParent();

    //
    // diagram creation/opening preferences
    //

    Group group =
        createGroup(RefinementPrefUIMessages.RefinedDiagramManagementPreferencePage_Group_Title,
            RefinementPrefUIMessages.RefinedDiagramManagementPreferencePage_Group_Title, fieldEditorParent);

    BooleanFieldEditor diagramCreationFieldEditor =
        new PreferenceField(IRefinementPreferences.PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_CREATION,
            RefinementPrefUIMessages.RefinedDiagramManagementPreferencePage_Creation_Title, group);
    addField(diagramCreationFieldEditor, UserProfileModeEnum.Expert, group, ProjectScope.class);

    BooleanFieldEditor diagramOpeningFieldEditor =
        new PreferenceField(IRefinementPreferences.PREFS_ALLOW_REFINED_SCENARIO_DIAGRAM_OPENING,
            RefinementPrefUIMessages.RefinedDiagramManagementPreferencePage_Opening_Title, group);
    addField(diagramOpeningFieldEditor, UserProfileModeEnum.Expert, group, ProjectScope.class);

    //
    // pre validation before the merge step preferences
    //
    Group group1 =
        createGroup(RefinementPrefUIMessages.RefinedDiagramManagementPreferencePage_GroupMerge_Title,
            RefinementPrefUIMessages.RefinedDiagramManagementPreferencePage_GroupMerge_Tooltip, fieldEditorParent);

    PreferenceField preValidationActivationFieldEditor =
        new PreferenceField(IRefinementPreferences.PREFS_MERGE_PRE_VALIDATION_ACTIVATION,
            RefinementPrefUIMessages.RefinedDiagramManagementPreferencePage_Merge_AllowPreValidation_Label, group1);
    addField(preValidationActivationFieldEditor, UserProfileModeEnum.Expert, group1, ProjectScope.class);

    PreferenceField stopOnValidationErrorFieldEditor =
        new PreferenceField(IRefinementPreferences.PREFS_MERGE_STOP_ON_ERROR_DURING_PRE_VALIDATION,
            RefinementPrefUIMessages.RefinedDiagramManagementPreferencePage_Merge_StopOnErrorDuringPreValidation_Label, group1);

    addField(stopOnValidationErrorFieldEditor, UserProfileModeEnum.Expert, group1, ProjectScope.class);

    _dynbutton = stopOnValidationErrorFieldEditor.getChangeControl(group1);
    // First pass initialization
    _dynbutton.setEnabled(RefinementPrefServices.isPreValidationForMergeActivated());

    return;
  }

  @Override
  protected void performDefaults() {
    super.performDefaults();

    _dynbutton.setEnabled(IRefinementPreferences.PREFS_MERGE_PRE_VALIDATION_ACTIVATION_DEFAULT);

    return;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
   */
  @Override
  public void propertyChange(PropertyChangeEvent event_p) {
    super.propertyChange(event_p);

    if ((event_p.getSource() instanceof FieldEditor)
        && ((FieldEditor) event_p.getSource()).getPreferenceName().equals(IRefinementPreferences.PREFS_MERGE_PRE_VALIDATION_ACTIVATION)) {
      boolean value = ((Boolean) event_p.getNewValue()).booleanValue();
      _dynbutton.setEnabled(value);
    }

  }

}
