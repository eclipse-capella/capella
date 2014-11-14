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
package org.polarsys.capella.common.command.recorder.ui.preferences;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import org.polarsys.capella.common.command.recorder.core.output.OutputHelper;
import org.polarsys.capella.common.command.recorder.core.preferences.IRecorderCorePreferenceConstants;
import org.polarsys.capella.common.command.recorder.core.preferences.RecorderCorePreferenceServices;
import org.polarsys.capella.common.command.recorder.ui.messages.RecorderUIMessages;
import org.polarsys.capella.common.command.recorder.ui.preferences.utils.DirectoryFieldEditor2;
import org.polarsys.capella.common.command.recorder.ui.preferences.utils.IntegerFieldEditor2;
import org.polarsys.capella.common.ui.toolkit.fields.LabelFieldEditor;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Preference page for Recorder.
 */
public class RecorderPreferencePage extends AbstractDefaultPreferencePage {

  protected BooleanFieldEditor _state;
  Map<FieldEditor, Composite> _dynFieldEditors;

  // For internal use
  final protected int MAX_SIZE_FOR_INTEGER_ENTRY = 3;
  final protected int WIDTH_FOR_INTEGER_ENTRY = 70;

  final protected int NUMBER_OF_DAY_MIN = 1;
  final protected int NUMBER_OF_DAY_MAX = 30;

  static protected int MAX_FILE_SIZE = 20;

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.command.recorder.ui.main"; //$NON-NLS-1$

  /**
     * 
     */
  public RecorderPreferencePage() {
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
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {

    // fix the problem for new workspace
    checkRecorderDir();

    final Composite fieldEditorParent = getFieldEditorParent();

    _dynFieldEditors = new HashMap<FieldEditor, Composite>();

    //
    // Main group
    //

    Group mainGroup =
        createGroup(RecorderUIMessages.recorderPreferencePage_MainGroup_Lbl, RecorderUIMessages.recorderPreferencePage_MainGroup_ToolTip, fieldEditorParent);

    // State of the model change recorder

    _state =
        new BooleanFieldEditor(IRecorderCorePreferenceConstants.RECORDER_STATE_PREF_ID, RecorderUIMessages.recorderPreferencePage_ActivateRecording_Lbl,
            mainGroup);

    addField(_state, UserProfileModeEnum.Expert, mainGroup);

    LabelFieldEditor lfe = new LabelFieldEditor(ICommonConstants.EMPTY_STRING, fieldEditorParent);
    addField(lfe);

    //
    // History group
    //

    Group historyGroup =
        createGroup(RecorderUIMessages.recorderPreferencePage_HistoryGroup_Lbl, RecorderUIMessages.recorderPreferencePage_HistoryGroup_ToolTip,
            fieldEditorParent);

    // policy on recorder launch
    BooleanFieldEditor bfe =
        new BooleanFieldEditor(IRecorderCorePreferenceConstants.RECORDER_CREATE_NEW_FILE_ON_START_PREF_ID,
            RecorderUIMessages.recorderPreferencePage_CreateNewFileOnStart_Lbl, historyGroup);

    // policy on project deletion

    BooleanFieldEditor bfe2 =
        new BooleanFieldEditor(IRecorderCorePreferenceConstants.RECORDER_DELETE_RECORDS_WITH_PROJECTS_PREF_ID,
            RecorderUIMessages.recorderPreferencePage_DeleteRecordsOnProjectDeletion_Lbl, historyGroup);

    addField(bfe2, UserProfileModeEnum.Expert, historyGroup);
    _dynFieldEditors.put(bfe2, historyGroup);

    addField(bfe, UserProfileModeEnum.Expert, historyGroup);
    _dynFieldEditors.put(bfe, historyGroup);

    // policy for keeping history file

    IntegerFieldEditor2 ife =
        new IntegerFieldEditor2(IRecorderCorePreferenceConstants.RECORDER_HISTORY_IN_DAY_PREF_ID, RecorderUIMessages.recorderPreferencePage_HistoryInDay_Lbl,
            historyGroup, MAX_SIZE_FOR_INTEGER_ENTRY, WIDTH_FOR_INTEGER_ENTRY);
    ife.setValidRange(NUMBER_OF_DAY_MIN, NUMBER_OF_DAY_MAX);

    addField(ife, UserProfileModeEnum.Expert, historyGroup);
    _dynFieldEditors.put(ife, historyGroup);

    // history file size (upper limit of)

    IntegerFieldEditor2 ife2 =
        new IntegerFieldEditor2(IRecorderCorePreferenceConstants.RECORDER_MAX_FILE_SIZE_PREF_ID, RecorderUIMessages.recorderPreferencePage_MaxFileSize_Lbl,
            historyGroup, MAX_SIZE_FOR_INTEGER_ENTRY, WIDTH_FOR_INTEGER_ENTRY);

    ife2.setValidRange(1, MAX_FILE_SIZE);

    addField(ife2, UserProfileModeEnum.Expert, historyGroup);
    _dynFieldEditors.put(ife2, historyGroup);

    // path to records

    DirectoryFieldEditor2 dfe =
        new DirectoryFieldEditor2(IRecorderCorePreferenceConstants.RECORDER_ROOT_PATH_PREF_ID, RecorderUIMessages.recorderPreferencePage_RecordsLocation_Lbl,
            historyGroup);

    addField(dfe, UserProfileModeEnum.Expert, historyGroup);
    _dynFieldEditors.put(dfe, historyGroup);

    LabelFieldEditor lfe2 = new LabelFieldEditor(ICommonConstants.EMPTY_STRING, fieldEditorParent);
    addField(lfe2);

    //
    // Content group
    //

    Group contentGroup =
        createGroup(RecorderUIMessages.recorderPreferencePage_ContentGroup_Lbl, RecorderUIMessages.recorderPreferencePage_ContentGroup_ToolTip,
            fieldEditorParent);

    BooleanFieldEditor bfe3 =
        new BooleanFieldEditor(IRecorderCorePreferenceConstants.RECORDER_EXTRA_DATA_FOR_EOBJECT_PREF_ID,
            RecorderUIMessages.recorderPreferencePage_ExtraData_Lbl, contentGroup);

    addField(bfe3, UserProfileModeEnum.Expert, contentGroup);
    _dynFieldEditors.put(bfe3, contentGroup);

    //
    // field editors state
    //
    enableDisableOptions(RecorderCorePreferenceServices.isRecorderShouldBeRunning());

    return;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return RecorderUIMessages.recorderPreferencePage_Desc;
  }

  @Override
  protected String getPageId() {
    return PROPERTY_PAGE_ID;
  }

  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return null;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
   */
  @Override
  public void propertyChange(PropertyChangeEvent event_p) {
    super.propertyChange(event_p);

    if ((event_p.getSource() instanceof FieldEditor)
        && ((FieldEditor) event_p.getSource()).getPreferenceName().equals(IRecorderCorePreferenceConstants.RECORDER_STATE_PREF_ID)) {
      enableDisableOptions(((Boolean) event_p.getNewValue()).booleanValue());
    }

    return;
  }

  @Override
  protected void performDefaults() {
    super.performDefaults();
    enableDisableOptions(IRecorderCorePreferenceConstants.RECORDER_STATE_PREF_DEFAULT_VALUE);

    return;
  }

  /** state of the field editors */
  protected void enableDisableOptions(boolean state_p) {

    for (Map.Entry<FieldEditor, Composite> entry : _dynFieldEditors.entrySet()) {
      entry.getKey().setEnabled(state_p, entry.getValue());
    }

    return;
  }

  /** for internal use */
  private void checkRecorderDir() {

    File dir = OutputHelper.getRootDirectoryForStorage();

    if (dir == null) {
      // do nothing
      // means that preference for recorder path must be changed.
    }

    if (!dir.exists()) {
      dir.mkdir();
    }

    return;
  }

}
