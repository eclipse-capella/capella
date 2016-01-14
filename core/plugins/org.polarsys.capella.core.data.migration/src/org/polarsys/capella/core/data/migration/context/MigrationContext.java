/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.context;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Shell;

/**
 *
 */
public class MigrationContext {

  private boolean _skipConfirmation = false;

  private Shell _shell;

  private IProgressMonitor _monitor = null;

  private MigrationContext arent;

  private String _name;

  public MigrationContext() {
  }

  public MigrationContext(MigrationContext context) {
    arent = context;
  }

  public boolean isSkipConfirmation() {
    return _skipConfirmation;
  }

  public void setSkipConfirmation(boolean skipConfirmation) {
    _skipConfirmation = skipConfirmation;
  }

  public void setProgressMonitor(IProgressMonitor monitor) {
    _monitor = monitor;
  }

  public void setShell(Shell shell) {
    _shell = shell;
  }

  public IProgressMonitor getProgressMonitor() {
    return _monitor;
  }

  /**
   * @return
   */
  public Shell getShell() {
    return _shell;
  }

  /**
   * @return
   */
  public String getName() {
    return _name;
  }

  /**
   * @param migrationAction_Title
   */
  public void setName(String name) {
    _name = name;
  }
}
