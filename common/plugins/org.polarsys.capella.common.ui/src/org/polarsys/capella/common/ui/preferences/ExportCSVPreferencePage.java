/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;

/**
 * Export CSV preferences page.
 */
public class ExportCSVPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  private StringFieldEditor otherFieldEditor;
  private boolean otherEnabled = false;

  public ExportCSVPreferencePage() {
    super(GRID);
    setTitle(Messages.ExportCSVPreferencePage_Title);
    setDescription(Messages.ExportCSVPreferencePage_Description);
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {
    Composite parent = getFieldEditorParent();

    otherEnabled = getPreferenceStore().getString(IExportCSVPreferences.DELIMITER_KEY)
        .equals(IExportCSVPreferences.DELIMITER_VALUE_OTHER);

    RadioGroupFieldEditor groupRadios = new RadioGroupFieldEditor(IExportCSVPreferences.DELIMITER_KEY, Messages.ExportCSVPreferencePage_Delimiters, 1,
        new String[][] { { IExportCSVPreferences.DELIMITER_VALUE_TAB, IExportCSVPreferences.DELIMITER_VALUE_TAB },
            { IExportCSVPreferences.DELIMITER_VALUE_SEMICOLON, IExportCSVPreferences.DELIMITER_VALUE_SEMICOLON },
            { IExportCSVPreferences.DELIMITER_VALUE_COMMA, IExportCSVPreferences.DELIMITER_VALUE_COMMA },
            { IExportCSVPreferences.DELIMITER_VALUE_SPACE, IExportCSVPreferences.DELIMITER_VALUE_SPACE },
            { IExportCSVPreferences.DELIMITER_VALUE_OTHER, IExportCSVPreferences.DELIMITER_VALUE_OTHER } },
        parent, false) {
      @Override
      protected void fireValueChanged(String property, Object oldValue, Object newValue) {
        super.fireValueChanged(property, oldValue, newValue);
        if (newValue.toString().equals(IExportCSVPreferences.DELIMITER_VALUE_OTHER)) {
          otherFieldEditor.setEnabled(true, parent);
          otherEnabled = true;
          otherFieldEditor.setErrorMessage(Messages.ExportCSVPreferencePage_ErrorMessage);
          otherFieldEditor.getTextControl(parent).setText(ICommonConstants.EMPTY_STRING);
        } else {
          otherFieldEditor.setEnabled(false, parent);
          otherEnabled = false;
          otherFieldEditor.loadDefault();
        }
      }
    };
    addField(groupRadios);

    otherFieldEditor = new StringFieldEditor(IExportCSVPreferences.OTHER_DELIMITER_KEY, ICommonConstants.EMPTY_STRING, 10, parent) {
      @Override
      protected boolean doCheckState() {
        String txt = getTextControl().getText();
        boolean restoreDefaults = txt.equals(IExportCSVPreferences.OTHER_DELIMITER_VALUE_DEFAULT);
        if (restoreDefaults) {
          setEnabled(false, parent);
          otherFieldEditor.getTextControl(parent).setText(ICommonConstants.EMPTY_STRING);
          otherEnabled = false;
        }
        return !otherEnabled || restoreDefaults || (txt != null && txt.length() == 1);
      }
    };

    if (!otherEnabled) {
      otherFieldEditor.setEnabled(false, parent);
      otherFieldEditor.loadDefault();
    }
    addField(otherFieldEditor);
  }

  @Override
  public void init(IWorkbench workbench) {
    setPreferenceStore(MdeCommonUiActivator.getDefault().getPreferenceStore());
  }

}
