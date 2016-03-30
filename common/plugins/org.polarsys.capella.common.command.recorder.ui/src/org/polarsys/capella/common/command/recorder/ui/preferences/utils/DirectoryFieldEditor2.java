/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.command.recorder.ui.preferences.utils;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.variables.IStringVariableManager;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;

/**
 * {@link DirectoryFieldEditor} with variable substitution
 */
public class DirectoryFieldEditor2 extends DirectoryFieldEditor {

  private File _filterPath = null;
  
  /**
   * @param name
   * @param labelText
   * @param parent
   */
  public DirectoryFieldEditor2(String name, String labelText, Composite parent) {
    super(name, labelText, parent);
  }

  @Override
  protected boolean doCheckState() {
    
    String fileName = getText();
        
    fileName = fileName.trim();
    if (fileName.length() == 0 && isEmptyStringAllowed()) {
      return true;
    }
    
    File file = new File(fileName);
    return file.isDirectory();
  }
  
  /**
   * 
   * {@inheritDoc}
   */
  @Override
  protected String changePressed() {
    
    File f = new File(getText());
    if (!f.exists()) {
      f = null;
    }
    
    File d = getDirectory2(f);

    return null == d? null : d.getAbsolutePath();
  }
  
  /**
   * 
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
    }else if (_filterPath != null) {
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
    } catch (CoreException exception) {
      fileName = getTextControl().getText();
    }
    
    return fileName;
  }
  
}
