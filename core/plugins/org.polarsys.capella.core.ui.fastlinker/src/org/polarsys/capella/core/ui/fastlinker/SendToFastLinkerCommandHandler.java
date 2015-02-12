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
package org.polarsys.capella.core.ui.fastlinker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Command handler used to send an element from a Capella part (viewers, diagrams) to the FastLinker.
 */
public class SendToFastLinkerCommandHandler extends AbstractHandler {
  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    // Get selection.
    ISelection selection = getCurrentSelection(event_p.getApplicationContext());
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
   * @param evaluationContext_p
   * @return
   */
  public ISelection getCurrentSelection(Object evaluationContext_p) {
    // Get the active part.
    IWorkbenchPart activePart = (IWorkbenchPart) HandlerUtil.getVariable(evaluationContext_p, ISources.ACTIVE_PART_NAME);
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
   * @param selection_p an {@link IStructuredSelection} containing an {@link IDiagramElementEditPart} or an {@link EObject} as first element.
   * @return the target element of the {@link IDiagramElementEditPart} or the EObject contained in the given selection or <code>null</code> if the selection is
   *         empty or if it doesn't contain an {@link IDiagramElementEditPart} or an {@link EObject} as first element.
   */
  public Collection<EObject> getSelectedModelElement(ISelection selection_p) {
		if (!(selection_p instanceof IStructuredSelection)) {
			return null;
		}
		Collection<EObject> ret = new ArrayList<EObject>();
		List selectedElements = ((IStructuredSelection) selection_p).toList();
		for (Object selectedElement : selectedElements)
			if ((selectedElement instanceof IAbstractDiagramNodeEditPart) || (selectedElement instanceof IDiagramEdgeEditPart)) {
				IDiagramElementEditPart diagramElement = (IDiagramElementEditPart) selectedElement;
				ret.add(diagramElement.resolveTargetSemanticElement());
			} else if (selectedElement instanceof EObject) {
				ret.add((EObject) selectedElement);
			}
		if (selectedElements.isEmpty())
			return null;
		return ret;
	}

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(Object evaluationContext_p) {
    // Get current selection.
	  ISelection selection = getCurrentSelection(evaluationContext_p);
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
