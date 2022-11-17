/*******************************************************************************
 * Copyright (c) 2018, 2020, THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.ui.dialogs;

import org.eclipse.emf.diffmerge.ui.util.DiffMergeDialog;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.core.transition.common.ui.handlers.merge.MergeEMFDiffNode;
import org.polarsys.capella.core.transition.common.ui.handlers.merge.Messages;

public class TransitionDiffMergeDialog extends DiffMergeDialog {

  public static final int ID_APPLY_ALL_CHANGES = IDialogConstants.CLIENT_ID + 1;
  private boolean forceOkButtonEnablement;

  public TransitionDiffMergeDialog(Shell shell, String title, EMFDiffNode input) {
    super(shell, title, input);
    this.forceOkButtonEnablement = false;
  }

  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    // add the apply all changes button only if the right model can be editable
    if (_input.isEditionPossible(false)) {
      Button applyAllChangesButton = createButton(parent, ID_APPLY_ALL_CHANGES,
          Messages.MergeUIDifferencesHandler_ApplyAllChanges, false);
      applyAllChangesButton.setEnabled(((MergeEMFDiffNode) _input).isMergeAllEnabled(true));

    }

    Button okButton = createOKButton(parent);
    if (forceOkButtonEnablement) {
      okButton.setEnabled(true);
    }

  }

  /**
   * This method will force the OK button of the DiffMergeDialog to be enabled.
   * 
   * @param forced
   *          forced the OK button to be enabled.
   */
  public void forceOkButtonEnablement(boolean forced) {
    this.forceOkButtonEnablement = forced;
  }

}
