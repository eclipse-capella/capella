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
package org.polarsys.capella.core.commands.preferences.service;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class PreferenceIntegerFieldEditor extends IntegerFieldEditor {
  /** For internal use */
  private int txtWidth;

  /**
   * Constructor
   */
  public PreferenceIntegerFieldEditor(String name, String labelText, Composite parent, int textLimit, int txtWidth) {

    this.txtWidth = txtWidth;

    // e.g. super(...)
    init(name, labelText);
    setTextLimit(textLimit);
    setEmptyStringAllowed(false);
    setErrorMessage(JFaceResources.getString("IntegerFieldEditor.errorMessage"));//$NON-NLS-1$
    createControl(parent);
  }

  /**
   * Constructor
   */
  public PreferenceIntegerFieldEditor(String name, String labelText, Composite parent, int textLimit) {
    this(name, labelText, parent, textLimit, StringFieldEditor.UNLIMITED);
  }

  @Override
  protected void doFillIntoGrid(Composite parent, int numColumns) {
    super.doFillIntoGrid(parent, numColumns);

    if (StringFieldEditor.UNLIMITED != txtWidth) {
      Text text = getTextControl();
      GridData gd = new GridData();
      gd.widthHint = txtWidth;
      text.setLayoutData(gd);
    }

    return;
  }

  @Override
  public IPreferenceStore getPreferenceStore() {

    return Activator.getDefault().getPreferenceStore();
  };

  @Override
  protected void fireValueChanged(String property, Object oldValue, Object newValue) {
    super.fireValueChanged(property, oldValue, newValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPreferenceName() {
    IProject selectedProject =
        PreferencesHelper.getSelectedEclipseProject() != null ? PreferencesHelper.getSelectedEclipseProject() : PreferencesHelper.getSelectedCapellaProject();
    if (selectedProject != null) {
      return selectedProject.getName() + "." + super.getPreferenceName();
    }
    return super.getPreferenceName();
  }
}
