/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;

/**
 */
public class PreferenceField extends BooleanFieldEditor {

  protected static final String POINT_SEPARATOR = "."; //$NON-NLS-1$

  /**
   * Creates a boolean field editor in the default style.
   * @param name the name of the preference this field editor works on
   * @param label the label text of the field editor
   * @param parent the parent of the field editor's control
   */
  public PreferenceField(String name, String label, Composite parent) {
    super(name, label, DEFAULT, parent);
  }

  /**
   * @see org.eclipse.jface.preference.BooleanFieldEditor#getChangeControl(Composite)
   */
  @Override
  public Button getChangeControl(Composite parent) {
    return super.getChangeControl(parent);
  }

  @Override
  protected void valueChanged(boolean oldValue_p, boolean newValue_p) {
    super.valueChanged(oldValue_p, newValue_p);
  }

  @Override
  protected void fireValueChanged(String property_p, Object oldValue_p, Object newValue_p) {
    super.fireValueChanged(property_p, oldValue_p, newValue_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPreferenceName() {
    IProject selectedProject =
        PreferencesHelper.getSelectedEclipseProject() != null ? PreferencesHelper.getSelectedEclipseProject() : PreferencesHelper.getSelectedCapellaProject();
    String preferenceName = super.getPreferenceName();

    return preferenceName;
  }


  /**
   * @param selectedProject_p
   * @param key_p
   */
  private void doLoadDefautlPrefrence(IProject selectedProject_p, String key_p) {
    PreferencesHelper.getProjectScope(selectedProject_p);
  }

  /**
   * Set whether or not the controls in the field editor are enabled.
   * @param enabled The enabled state.
   * @param parent The parent of the controls in the group. Used to create the controls if required.
   */
  @Override
  public void setEnabled(boolean enabled, Composite parent) {
    parent.setEnabled(enabled);
  }
}
