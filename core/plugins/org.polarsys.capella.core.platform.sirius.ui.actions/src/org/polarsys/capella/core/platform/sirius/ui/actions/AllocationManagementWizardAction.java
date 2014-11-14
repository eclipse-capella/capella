/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IActionDelegate;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.platform.sirius.ui.actions.AllocationManagementData.AllocationSelectionType;

/**
 */
public class AllocationManagementWizardAction extends AbstractTigAction {

  /**
   * instance to perform allocation action
   */
  AllocationManagementAction _actionInstance;

  /**
   * instance to collect data available for allocation, and perform validation of selected elements
   */
  AllocationManagementData _dataInstance;

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
    // instance of allocation action
    _actionInstance = AllocationManagementAction.getInstance();
    // instance of allocation data
    _dataInstance = AllocationManagementData.getInstance();

    // retrieve all the selected elements
    //
    final List<EObject> contextualMenuSelections = WizardActionHelper.converToEObjectList(getSelectedElements());

    // if not valid selection return warning message
    if (_dataInstance.isValidSelection(contextualMenuSelections)) {

      // Get available elements.
      final List<CapellaElement> availableElements = new ArrayList<CapellaElement>(0);
      AbstractReadOnlyCommand collectElementsCommand = new AbstractReadOnlyCommand() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          if (_dataInstance.isValidSelection(contextualMenuSelections)) {
            availableElements.addAll(_dataInstance.getCorrespondingData(contextualMenuSelections));
          }
        }
      };

      // execute the command
      getExecutionManager().execute(collectElementsCommand);

      if (!_dataInstance.isSourceDataVoid()) {
        // Open a Transfer Dialog.
        SelectElementsDialog dialog =
            new SelectElementsDialog(getActiveShell(), MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory(),
                Messages.AllocationManagementWizardAction_Title, _dataInstance.getDataMessage(), availableElements,
                _dataInstance.isMultiSelection(contextualMenuSelections), null);
        if (Window.OK == dialog.open()) {
          final List<EObject> wizardSelections = new ArrayList<EObject>();
          List<? extends EObject> result = dialog.getResult();
          for (EObject object : result) {
            wizardSelections.add(object);
          }

          // Create a command to perform the model changes.
          AbstractReadWriteCommand performedChangesCommand = new AbstractReadWriteCommand() {
            /**
             * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#getName()
             */
            @Override
            public String getName() {
              return Messages.AllocationManagementWizardAction_Command_Label;
            }

            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
              handleChanges(contextualMenuSelections, wizardSelections);
            }
          };
          getExecutionManager().execute(performedChangesCommand);
        }

      } else {
        WizardActionHelper.createMessageBox(getActiveShell(), Messages.AllocationManagementWizardAction_Warning_Message, SWT.ICON_INFORMATION);
      }

    }
  }

  /**
   * Perform action : allocation of elements, deployment
   * @param selectedCapellaElement_p
   * @param selectedElements_p
   */
  void handleChanges(List<EObject> selectedCapellaElement_p, List<EObject> selectedElements_p) {
    if (selectedElements_p.isEmpty()) {
      return;
    }

    if (_dataInstance.getAllocationType() == AllocationSelectionType.FUNCTION_ALLOCATION) {
      // note : Function can only be allocated by one Component
      _actionInstance.allocatingFunctionsToComponent(selectedCapellaElement_p, selectedElements_p.get(0));
    } else if (_dataInstance.getAllocationType() == AllocationSelectionType.EXCHANGE_ITEM_ALLOCATION) {
      _actionInstance.allocatingExchangeItemsToInterfaces(selectedCapellaElement_p, selectedElements_p);
      // note : exchange items can be allocated by multiple interfaces
    } else if (_dataInstance.getAllocationType() == AllocationSelectionType.FUNCTIONAL_EXCHANGE_ALLOCATION) {
      // note : Functional Exchange can only allocated by one Component Exchange
      _actionInstance.allocatingFEsToComponentExchanges(selectedCapellaElement_p, selectedElements_p.get(0));
    } else if (_dataInstance.getAllocationType() == AllocationSelectionType.COMPONENT_EXCHANGE_ALLOCATION) {
      // note : Component Exchange can only allocated by one Physical link
      _actionInstance.allocatingCEsToPhysicalLinks(selectedCapellaElement_p, selectedElements_p.get(0));
    } else if (_dataInstance.getAllocationType() == AllocationSelectionType.PHYSICAL_PART_DEPLOYMENT) {
      // note : part can only be allocated by one part
      _actionInstance.allocatingPCPartsToPCPart(selectedCapellaElement_p, selectedElements_p.get(0));
    }
  }

}
