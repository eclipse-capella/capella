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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

/**
 * <code>MainWizardDialog</code> is the basic editor dialog used by MDTrace
 * 
 * @deprecated use CapellaWizardDialog instead
 */
@Deprecated 
public class MainWizardDialog extends WizardDialog {

  /**
   * Constructs the basic editor dialog. Removes the modal feature of the wizard dialog.
   * 
   * @param shell The shell.
   * @param editor_p The editor to display.
   */
  public MainWizardDialog(Shell shell_p, IWizard editor_p) {
    super(shell_p, editor_p);
    // Applies the parent shell style without SWT.APPLICATION_MODAL.
    if (0 != (getShellStyle() & SWT.APPLICATION_MODAL)) {
      int newShellStyle = getShellStyle() - SWT.APPLICATION_MODAL;
      setShellStyle(newShellStyle);
    }
    setBlockOnOpen(true);
  }
  
}
