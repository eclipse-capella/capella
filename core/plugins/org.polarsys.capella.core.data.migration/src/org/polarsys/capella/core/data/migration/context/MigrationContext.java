/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.context;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Version;

/**
 *
 */
public class MigrationContext {

  private boolean backUpModel = true;
  
  private boolean _skipConfirmation = false;

  private Shell _shell;

  private IProgressMonitor _monitor = null;

  private String _name;

  private IFile _resource;

  private HashMap<IPath, Version> fileVersions = new HashMap<IPath, Version>();

  public Version getCurrentVersion() {
    return getFileVersion(_resource);
  }
  
  public Version getFileVersion(IFile file) {
    IPath path = file.getFullPath().removeFileExtension();
    Version currentVersion =  fileVersions.containsKey(path) ? fileVersions.get(path) : Version.emptyVersion;
    return currentVersion;
  }

  public void setFileVersion(IFile file, Version currentVersion) {
    IPath path = file.getFullPath().removeFileExtension();
    this.fileVersions.put(path, currentVersion);
  }

  public MigrationContext() {
  }

  public MigrationContext(MigrationContext context) {
  }

  public boolean isSkipConfirmation() {
    return _skipConfirmation;
  }

  public void setSkipConfirmation(boolean skipConfirmation) {
    this._skipConfirmation = skipConfirmation;
  }

  public boolean isBackupModel() {
    return backUpModel;
  }

  public void setBackupModel(boolean backupModel) {
    this.backUpModel = backupModel;
  }
  
  public void setProgressMonitor(IProgressMonitor monitor) {
    this._monitor = monitor;
  }

  public void setShell(Shell shell) {
    this._shell = shell;
  }

  public IProgressMonitor getProgressMonitor() {
    return _monitor;
  }

  public Shell getShell() {
    return _shell;
  }

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    this._name = name;
  }

  public IFile getResource() {
    return _resource;
  }
  
  public String getResourceName() {
    return getResource() == null ? getName() : getResource().getName();
  }

  public void setResource(IFile file) {
    this._resource = file;

  }
}
