/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.variables.IStringVariableManager;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;

import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class PreferenceDirectoryFieldEditor extends DirectoryFieldEditor {

  private File _filterPath = null;

  /**
   * @param recorderRootPathPrefId_p
   * @param recorderPreferencePage_RecordsLocation_Lbl_p
   * @param historyGroup_p
   */
  public PreferenceDirectoryFieldEditor(String name, String labelText, Composite parent) {
    super(name, labelText, parent);
  }

  @Override
  protected boolean doCheckState() {

    String fileName = getText();

    fileName = fileName.trim();
    if ((fileName.length() == 0) && isEmptyStringAllowed()) {
      return true;
    }

    File file = new File(fileName);
    return file.isDirectory();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String changePressed() {

    File f = new File(getText());
    if (!f.exists()) {
      f = null;
    }

    File d = getDirectory2(f);

    return null == d ? null : d.getAbsolutePath();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFilterPath(File path) {
    super.setFilterPath(path);
    _filterPath = path;
  }

  /**
   * @see DirectoryFieldEditor#getDirectory
   */
  private File getDirectory2(File startingDirectory) {

    DirectoryDialog fileDialog = new DirectoryDialog(getShell(), SWT.OPEN | SWT.SHEET);
    if (startingDirectory != null) {
      fileDialog.setFilterPath(startingDirectory.getPath());
    } else if (_filterPath != null) {
      fileDialog.setFilterPath(_filterPath.getPath());
    }

    String dir = fileDialog.open();
    if (dir != null) {
      dir = dir.trim();
      if (dir.length() > 0) {
        return new File(dir);
      }
    }

    return null;
  }

  private String getText() {

    IStringVariableManager svm = VariablesPlugin.getDefault().getStringVariableManager();

    String fileName;

    try {
      fileName = svm.performStringSubstitution(getTextControl().getText());
    } catch (CoreException exception_p) {
      fileName = getTextControl().getText();
    }

    return fileName;
  }

  @Override
  public IPreferenceStore getPreferenceStore() {

    return Activator.getDefault().getPreferenceStore();
  };

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
    if (selectedProject != null) {
      return selectedProject.getName() + "." + super.getPreferenceName();
    }
    return super.getPreferenceName();
  }
}
