/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.refresh.RefreshDiagramAction;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;

public class CreateCategoriesWizardAction extends AbstractTigAction {
  private CreateCategoriesController _createCatController;

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IAction action) {
    // retrieve all the selected elements
    //
    final List<EObject> selection = WizardActionHelper.converToEObjectList(getSelectedElements());
    if (selection.isEmpty()) {
      return;
    }
    // if not valid selection return warning message
    if (UpdateCategoriesController.isValidSelection(selection)) {

      // create a Category with links common ancestor as container
      // fall down: set category container to root package

      AbstractReadWriteCommand doModelUpdateCmd = new AbstractReadWriteCommand() {
        /**
         * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
         */
        @Override
        public String getName() {
          return "Create Category"; //$NON-NLS-1$
        }

        /**
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
          handleChanges(selection);
        }

      };
      getExecutionManager().execute(doModelUpdateCmd);

      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        public void run() {
          ISelection diagramSelection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
          RefreshDiagramAction.refresh(diagramSelection);
        }
      });

    } else {
      WizardActionHelper.createMessageBox(getActiveShell(), Messages.UpdateCategoriesWizardAction_Warning_Message, SWT.ICON_INFORMATION);
    }

  }

  /**
   * @param selection
   */
  void handleChanges(final List<EObject> selection) {
    _createCatController = CreateCategoriesController.createCreateCategoriesController(selection);
    _createCatController.createAndAttachCategory(selection);
  }
}
