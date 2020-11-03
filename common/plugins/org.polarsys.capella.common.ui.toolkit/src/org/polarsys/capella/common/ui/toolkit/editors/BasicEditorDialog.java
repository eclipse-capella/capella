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

package org.polarsys.capella.common.ui.toolkit.editors;

import org.apache.log4j.Logger;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.ui.services.UIUtil;

/**
 * The basic editor dialog. This editor type is not application modal.
 */
public class BasicEditorDialog extends WizardDialog {
	
  private static final Logger logger = Logger.getLogger(BasicEditorDialog.class.getName());

  /**
   * Constructs the basic editor dialog. Removes the modal feature of the wizard dialog.
   * @param shell
   *          The shell.
   * @param editor
   *          The editor to display.
   */
  public BasicEditorDialog(Shell shell, IWizard editor) {
    super(shell, editor);

    // Applies the parent shell style without SWT.APPLICATION_MODAL.
    if (0 != (getShellStyle() & SWT.APPLICATION_MODAL)) {
      int newShellStyle = getShellStyle() - SWT.APPLICATION_MODAL;
      setShellStyle(newShellStyle);
    }
    setBlockOnOpen(true);
  }

  /**
   * @see org.eclipse.jface.wizard.WizardDialog#close()
   */
  @Override
  public boolean close() {
    UIUtil.setDialogOpen(false);
    return super.close();
  }

  /**
   * @see org.eclipse.jface.window.Window#open()
   */
  @Override
  public int open() {
    try {
      UIUtil.setDialogOpen(true);
      return super.open();

    } catch (Exception ex) {
      logger.error(ex.getMessage());
      UIUtil.setDialogOpen(false);
      return getReturnCode();
    }
  }

  /**
   * @see org.eclipse.jface.wizard.WizardDialog#finishPressed()
   */
  @Override
  protected void finishPressed() {
    try {
      UIUtil.setDialogOpen(false);
      super.finishPressed();
    } catch (Exception ex) {
      UIUtil.setDialogOpen(false);
    } finally {
      UIUtil.setDialogOpen(false);
    }
  }
}
