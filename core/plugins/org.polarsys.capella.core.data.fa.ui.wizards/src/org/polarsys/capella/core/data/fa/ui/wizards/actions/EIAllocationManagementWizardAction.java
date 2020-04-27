/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.fa.ui.wizards.actions;

import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IActionDelegate;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.EIAllocationModelHelpers;
import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.EIAllocationTransfertDlg;
import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.Messages;

/**
 */
public class EIAllocationManagementWizardAction extends AbstractTigAction {

  /**
   * Get list of selected Elements
   * @see org.polarsys.capella.core.platform.sirius.ui.actions.AbstractTigAction#getSelectedElements()
   */
  @Override
  protected List<ModelElement> getSelectedElements() {
    return super.getSelectedElements();
  }

  /**
   * @see IActionDelegate#run(IAction)
   */
  public void run(IAction action) {
    if (!isValidSelection(getSelectedElements())) {
      createMessageBox(Messages.EIAllocationManagementWizardAction_Warning_Message, SWT.ICON_INFORMATION);
    } else {
      try {
        TransactionHelper.getExecutionManager(getSelectedElements()).execute(new AbstractReadWriteCommand() {
          @SuppressWarnings("synthetic-access")
          @Override
          public void run() {
            EIAllocationTransfertDlg dialog = new EIAllocationTransfertDlg(getActiveShell(),
              Messages.EIAllocationTransfertDlg_Title, Messages.EIAllocationTransfertDlg_Message, Messages.EIAllocationTransfertDlg_ShellTitle);
            dialog.setSelection(getSelectedElements(), EIAllocationModelHelpers.getTransitionedElements(getSelectedElements()));
            if (Window.OK != dialog.open()) {
              throw new OperationCanceledException(); // rollback
            }
          }
        });
      } catch (OperationCanceledException ex) {
    	// Catch exception silently,
      }
    }
  }

  /**
   * decides weather list of elements are valid or not for allocation action
   * @param elements
   * @return true if all the elements in the list are of type AbstractFunction, false otherwise
   */
  public boolean isValidSelection(List<? extends EObject> elements) {
    for (EObject object : elements) {
      if (!(object instanceof AbstractFunction)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Create message box
   * @param message
   * @param icon_status
   */
  private void createMessageBox(String message, int icon_status) {
    MessageBox messageBox = new MessageBox(getActiveShell(), icon_status);
    messageBox.setText(Messages.EIAllocationManagementWizardAction_Warning_Title);
    messageBox.setMessage(message);
    messageBox.open();
  }
}
