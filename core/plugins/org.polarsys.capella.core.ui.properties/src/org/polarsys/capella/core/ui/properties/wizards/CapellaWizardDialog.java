/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.wizards;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.common.ui.toolkit.dialogs.IDialog;

/**
 */
public class CapellaWizardDialog extends WizardDialog implements IDialog {

  private Button _finishButton;

  /**
   * @param shell
   * @param wizard
   */
  public CapellaWizardDialog(Shell shell, IWizard wizard) {
    super(shell, wizard);
  }

  /**
   * @see org.eclipse.jface.wizard.WizardDialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    super.createButtonsForButtonBar(parent);

    Button btn = getButton(IDialogConstants.CANCEL_ID);
    if (null != btn) {
      btn.setText("&" + IDialogConstants.CANCEL_LABEL); //$NON-NLS-1$
    }
  }

  /**
   * @see org.eclipse.emf.eef.runtime.ui.wizards.EEFWizardDialog#getInitialSize()
   */
  @Override
  protected Point getInitialSize() {
    return super.getInitialSize();
  }

  @Override
  protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
    _finishButton = super.createButton(parent, id, label, false);
    // Set as default button to get the focus in the finish button
    parent.getShell().setDefaultButton(_finishButton);
    return _finishButton;
  }

  @Override
  public void updateButtons() {
    boolean canFinish = getWizard().canFinish();
    _finishButton.setEnabled(canFinish);
  }
}
