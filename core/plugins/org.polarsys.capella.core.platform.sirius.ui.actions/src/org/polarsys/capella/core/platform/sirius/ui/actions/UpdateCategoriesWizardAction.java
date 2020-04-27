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
package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.refresh.RefreshDiagramAction;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

public class UpdateCategoriesWizardAction extends AbstractTigAction implements IHandler {

  private UpdateCategoriesController updateCatController;

  @Override
  public void run(IAction action) {
    // // instance of allocation data
    // _dataInstance = UpdateCategoriesData.getInstance();

    // retrieve all the selected elements
    //
    final List<EObject> selection = WizardActionHelper.converToEObjectList(getSelectedElements());
    if (selection.isEmpty()) {
      return;
    }
    // if not valid selection return warning message
    if (UpdateCategoriesController.isValidSelection(selection)) {
      // Get available elements.
      updateCatController = UpdateCategoriesController.createUpdateCategoriesController(selection);

      List<EObject> availableElements = updateCatController.getAvailableCategories(selection);
      List<EObject> commonCategories = updateCatController.getCommonCategories(selection);

      // open transfert dialog
      final List<EObject> wizardSelections = SelectionDialogHelper.multiplePropertyTransfertDialogWizard(getActiveShell(), Messages.UpdateCategoriesWizardAction_Title,
          Messages.UpdateCategoriesWizardAction_msg, availableElements, commonCategories);

      if (wizardSelections != null) {

        // categories to remove = (common categories) - (wizard selection)
        final List<EObject> catToRemove = new ArrayList<EObject>();
        catToRemove.addAll(commonCategories);
        catToRemove.removeAll(wizardSelections);

        // categories to remove = (wizard selection) - (common categories)
        final List<EObject> catToAdd = new ArrayList<EObject>();
        catToAdd.addAll(wizardSelections);
        catToAdd.removeAll(commonCategories);

        // Create a command to perform the model changes.
        AbstractReadWriteCommand performedChangesCommand = new AbstractReadWriteCommand() {
          /**
           * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
           */
          @Override
          public String getName() {
            return Messages.AllocationManagementWizardAction_Command_Label;
          }

          /**
           * @see java.lang.Runnable#run()
           */
          public void run() {
            handleChanges(selection, catToAdd, catToRemove);
          }

        };
        getExecutionManager().execute(performedChangesCommand);

        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            ISelection diagramSelection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
            RefreshDiagramAction.refresh(diagramSelection);
          }
        });

      }

    } else {
      WizardActionHelper.createMessageBox(getActiveShell(), Messages.AllocationManagementWizardAction_Warning_Message, SWT.ICON_INFORMATION);
    }
  }

  /**
   * @param selectedElements
   * @param categoriesToAdd
   * @param categoriesToRemove
   */
  protected void handleChanges(List<EObject> selectedElements, List<EObject> categoriesToAdd, List<EObject> categoriesToRemove) {
    updateCatController.updateCategories(selectedElements, categoriesToAdd, categoriesToRemove);

  }
}
