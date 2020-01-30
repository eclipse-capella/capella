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
package org.polarsys.capella.core.platform.sirius.ui.project.internal.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class ScmPreferencePage extends AbstractDefaultPreferencePage {

  /*
   * 
   */
  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.platform.sirius.ui.project.page1"; //$NON-NLS-1$

  /*
   * 
   */
  private IntegerFieldEditor _delayFieldEditor;

  /**
   * 
   */
  public ScmPreferencePage() {
    super(PROPERTY_PAGE_ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createFieldEditors() {
    final Group scmGroup = createGroup(Messages.ScmPreferencePage_Group_Title, Messages.ScmPreferencePage_Group_Tooltip,
        getFieldEditorParent());
    // Enable monitoring editor.
    BooleanFieldEditor enableMonitoringFieldEditor = new BooleanFieldEditor(
        IMonitorFileSyncPreferences.PREFERENCE_ENABLE_FILE_SYNC_MONITORING,
        Messages.ScmPreferencePage_EnableMonitoring_Title, scmGroup) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      protected void valueChanged(boolean oldValue_p, boolean newValue_p) {
        super.valueChanged(oldValue_p, newValue_p);
        _delayFieldEditor.setEnabled(newValue_p, scmGroup);
      }
    };
    addField(enableMonitoringFieldEditor, UserProfileModeEnum.Expert, scmGroup);
    _delayFieldEditor = new IntegerFieldEditor(IMonitorFileSyncPreferences.PREFERENCE_FILE_SYNC_MONITORING_DELAY,
        Messages.ScmPreferencePage_Delay_Title, scmGroup, 3);
    _delayFieldEditor.setValidRange(1, 999);
    addField(_delayFieldEditor, UserProfileModeEnum.Expert, scmGroup);
    // Customize label layout data.
    Label labelControl = _delayFieldEditor.getLabelControl(scmGroup);
    GridData layoutData = new GridData(SWT.FILL, SWT.FILL, false, false);
    labelControl.setLayoutData(layoutData);
    layoutData.horizontalIndent = 15;
    // Depending on enablement of enableMonitoring editor, the text is editable or not.
    _delayFieldEditor.setEnabled(
        doGetPreferenceStore().getBoolean(IMonitorFileSyncPreferences.PREFERENCE_ENABLE_FILE_SYNC_MONITORING),
        scmGroup);

    scmGroup.pack();
  }

  /**
   * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
   */
  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getPageDescription() {
    return Messages.ScmPreferencePage_Description;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getPageTitle() {
    return Messages.ScmPreferencePage_Title;
  }
}
