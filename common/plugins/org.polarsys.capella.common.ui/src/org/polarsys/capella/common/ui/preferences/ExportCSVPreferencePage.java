/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.preferences;

import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;

/**
 * Delete preferences page.
 */
public class ExportCSVPreferencePage extends AbstractDefaultPreferencePage {

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.common.ui.preferences.exportcsv";

  private StringFieldEditor _otherFieldEditor;
  private boolean _otherEnabled = false;

  public static final String Description = "Preferences related to export csv";
  public static final String PageTitle = "Export csv";
  public static final String Delimiters = "Delimiters";
  public static final String ErrorMessage = "Please enter a character!";
  /**
   * @see org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage#getPageDescription()
   */
  @Override
  protected String getPageDescription() {
    return Description;
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
    return PageTitle;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
   */
  @Override
  protected void createFieldEditors() {
    Composite parent = getFieldEditorParent();
    _otherEnabled = doGetPreferenceStore()
        .getString(IExportCSVPreferences.DELIMITER_NAME_CURRENT)
        .toString().equals(IExportCSVPreferences.DELIMITER_NAME_OTHER);

    RadioGroupFieldEditor groupRadios = new RadioGroupFieldEditor(IExportCSVPreferences.DELIMITER_NAME_CURRENT,
        Delimiters, 1,
        new String[][] { { IExportCSVPreferences.DELIMITER_NAME_TAB, IExportCSVPreferences.DELIMITER_NAME_TAB },
            { IExportCSVPreferences.DELIMITER_NAME_SEMICOLON, IExportCSVPreferences.DELIMITER_NAME_SEMICOLON },
            { IExportCSVPreferences.DELIMITER_NAME_COMMA, IExportCSVPreferences.DELIMITER_NAME_COMMA },
            { IExportCSVPreferences.DELIMITER_NAME_SPACE, IExportCSVPreferences.DELIMITER_NAME_SPACE },
            { IExportCSVPreferences.DELIMITER_NAME_OTHER, IExportCSVPreferences.DELIMITER_NAME_OTHER } },
        parent, false) {
      @Override
      protected void fireValueChanged(String property, Object oldValue, Object newValue) {
        super.fireValueChanged(property, oldValue, newValue);
        if (newValue.toString().equals(IExportCSVPreferences.DELIMITER_NAME_OTHER)) {
          _otherFieldEditor.setEnabled(true, parent);
          _otherEnabled = true;
          _otherFieldEditor.setErrorMessage(ErrorMessage);
          _otherFieldEditor.getTextControl(parent).setText("");
        } else {
          _otherFieldEditor.setEnabled(false, parent);
          _otherEnabled = false;
          _otherFieldEditor.loadDefault();
        }
      }
    };
    addField(groupRadios);

    _otherFieldEditor = new StringFieldEditor(IExportCSVPreferences.DELIMITER_VALUE_OTHER, "", 10, parent) {
      @Override
      protected boolean doCheckState() {
        String txt = getTextControl().getText();
        boolean restoreDefaults = txt.equals(IExportCSVPreferences.DELIMITER_VALUE_OTHER_DEFAULT);
        if (restoreDefaults) {
          setEnabled(false, parent);
          _otherFieldEditor.getTextControl(parent).setText("");
          _otherEnabled = false;
        }
        return !_otherEnabled || restoreDefaults || (txt != null && txt.length() == 1);
      }
    };

    if (!_otherEnabled) {
      _otherFieldEditor.setEnabled(false, parent);
      _otherFieldEditor.loadDefault();
    }

    addField(_otherFieldEditor);
  }

  @Override
  protected void performDefaults() {
    super.performDefaults();

    return;
  }
}
