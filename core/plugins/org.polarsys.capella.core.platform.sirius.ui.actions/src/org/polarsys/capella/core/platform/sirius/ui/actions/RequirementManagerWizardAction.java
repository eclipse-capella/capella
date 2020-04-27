/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IActionDelegate;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.core.business.queries.capellacore.CapellaElement_OutgoingRequirement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.requirement.RequirementFactory;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;

public class RequirementManagerWizardAction extends AbstractTigAction {
  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.actions.AbstractTigAction#getSelectedElement()
   */
  @Override
  protected CapellaElement getSelectedElement() {
    return (CapellaElement) super.getSelectedElement();
  }

  /**
   * @param selectedCapellaElement
   * @param selectedElements
   */
  void handleChanges(CapellaElement selectedCapellaElement, List<Object> selectedElements) {
    List<AbstractTrace> eltsToDestroy = new ArrayList<AbstractTrace>(0);
    for (AbstractTrace trace : selectedCapellaElement.getOutgoingTraces()) {
      if ((trace instanceof RequirementsTrace) && !selectedElements.contains(trace.getTargetElement())) {
        eltsToDestroy.add(trace);
      }
    }
    for (AbstractTrace trace : eltsToDestroy) {
      trace.destroy();
    }
    for (Object currentElement : selectedElements) {
      boolean alreadyTraced = false;
      for (AbstractTrace trace : selectedCapellaElement.getOutgoingTraces()) {
        if ((trace instanceof RequirementsTrace) && (trace.getTargetElement() == currentElement)) {
          alreadyTraced = true;
        }
      }

      if (!alreadyTraced) {
        Namespace ns = null;
        if (selectedCapellaElement instanceof Namespace) {
          ns = (Namespace) selectedCapellaElement;
        } else {
          ns = (Namespace) EcoreUtil2.getFirstContainer(selectedCapellaElement, CapellacorePackage.Literals.NAMESPACE);
        }
        if (ns != null) {
          RequirementsTrace trace = RequirementFactory.eINSTANCE.createRequirementsTrace();
          trace.setSourceElement(selectedCapellaElement);
          trace.setTargetElement((TraceableElement) currentElement);
          ns.getOwnedTraces().add(trace);
        }
      }
    }
  }

  /**
   * @see IActionDelegate#run(IAction)
   */
  public void run(IAction action) {
    final CapellaElement selectedCapellaElement = getSelectedElement();
    // Get available elements.
    final List<EObject> availableElements = new ArrayList<EObject>(0);
    // Get current elements.
    final List<EObject> currentElements = new ArrayList<EObject>(0);
    AbstractReadOnlyCommand collectElementsCommand = new AbstractReadOnlyCommand() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        CapellaElement_OutgoingRequirement query = new CapellaElement_OutgoingRequirement();
        availableElements.addAll(query.getAvailableElements(selectedCapellaElement));
        currentElements.addAll(query.getCurrentElements(selectedCapellaElement, false /* not important here */));
      }
    };
    getExecutionManager().execute(collectElementsCommand);
    // Open a Transfer Dialog.
    TransferTreeListDialog dialog = new TransferTreeListDialog(getActiveShell(),
        Messages.RequirementManagerWizardAction_Title, Messages.RequirementManagerWizardAction_Message);
    dialog.setLeftInput(availableElements, null /* no context */);
    dialog.setRightInput(currentElements, null /* no context */);
    if (Window.OK == dialog.open()) {
      final List<Object> selectedElements = MiscHelper.asList(dialog.getResult());
      // Create a command to perform the model changes.
      AbstractReadWriteCommand performedChangesCommand = new AbstractReadWriteCommand() {
        /**
         * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
         */
        @Override
        public String getName() {
          return Messages.RequirementManagerWizardAction_Command_Label;
        }

        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          handleChanges(selectedCapellaElement, selectedElements);
        }
      };
      getExecutionManager().execute(performedChangesCommand);
    }
  }
}
