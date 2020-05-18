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
 * <code>ViewCapellaModelWizardDialog</code> is the basic editor dialog used by MDTrace
 * 
 * @deprecated use CapellaWizardDialog instead
 */
@Deprecated
public class ViewCapellaModelWizardDialog extends WizardDialog {

  /**
   * Constructs the basic editor dialog. Removes the modal feature of the wizard dialog.
   * 
   * @param parentShell_p The shell.
   * @param newWizard_p The editor to display.
   */
  public ViewCapellaModelWizardDialog(Shell parentShell_p, IWizard newWizard_p) {
    super(parentShell_p, newWizard_p);

    // Applies the parent shell style without SWT.APPLICATION_MODAL.
    if (0 != (getShellStyle() & SWT.APPLICATION_MODAL)) {
      int newShellStyle = getShellStyle() - SWT.APPLICATION_MODAL;
      setShellStyle(newShellStyle);
    }
    setBlockOnOpen(true);
  }

  
}
