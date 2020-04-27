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
package org.polarsys.capella.core.ui.fastlinker;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;

/**
 * Command handler used to send an element from a Capella part (viewers, diagrams) to the FastLinker.
 */
public class SendToFastLinkerCommandHandler extends AbstractHandler {
  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    // Get selection.
    ISelection selection = getCurrentSelection(event.getApplicationContext());
    // Get selected model element.
    Collection selectedModelElement = getSelectedModelElement(selection);
    if (null == selectedModelElement) {
      return null;
    }
    // Put it in FastLinker.
    FastLinkerActivator.getDefault().getFastLinkerManager().putElementInFastLinker(selectedModelElement);
    return null;
  }

  /**
   * Gets current selection for this command.<br>
   * @param evaluationContext
   * @return
   */
  public ISelection getCurrentSelection(Object evaluationContext) {
    // Get the active part.
    IWorkbenchPart activePart = (IWorkbenchPart) HandlerUtil.getVariable(evaluationContext, ISources.ACTIVE_PART_NAME);
    // Precondition.
    if (null == activePart) {
      return StructuredSelection.EMPTY;
    }
    if (null == activePart.getSite().getSelectionProvider()) {
      return StructuredSelection.EMPTY;
    }
    // Get current selection.
    return activePart.getSite().getSelectionProvider().getSelection();
  }

  /**
   * Get the first element from given selection.
   * @param selection an {@link IStructuredSelection} containing an {@link IDiagramElementEditPart} or an {@link EObject} as first element.
   * @return the target element of the {@link IDiagramElementEditPart} or the EObject contained in the given selection or <code>null</code> if the selection is
   *         empty or if it doesn't contain an {@link IDiagramElementEditPart} or an {@link EObject} as first element.
   */
  public Collection<EObject> getSelectedModelElement(ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) {
			return null;
		}
		Collection<EObject> ret = new ArrayList<>();
		for (Object selectedElement : ((IStructuredSelection) selection).toList()) {
		  EObject semanticElement = CapellaAdapterHelper.resolveBusinessObject(selectedElement);
		  if (semanticElement != null) {
				ret.add(semanticElement);
			}
		}
		if (ret.isEmpty())
			return null;
		return ret;
	}

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(Object evaluationContext) {
    // Get current selection.
	  ISelection selection = getCurrentSelection(evaluationContext);
		// Extract selected model element from selection.
		Collection selectedElement = getSelectedModelElement(selection);
		if (null == selectedElement || selectedElement.isEmpty()) {
			setBaseEnabled(false);
			return;

		}
		// Ask FastLinkerManager if it would accept the model element.
		boolean isAccepted = FastLinkerActivator.getDefault()
				.getFastLinkerManager()
				.acceptElementInFastLinker(selectedElement);
		setBaseEnabled(isAccepted);
  }
}
