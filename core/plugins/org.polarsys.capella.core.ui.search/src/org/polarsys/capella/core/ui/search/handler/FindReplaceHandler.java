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
package org.polarsys.capella.core.ui.search.handler;

import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.ui.search.FindAndReplaceDialog;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class FindReplaceHandler extends AbstractHandler {

  @SuppressWarnings("unchecked")
  @Override
  public Object execute(ExecutionEvent event_p) throws ExecutionException {

    // get the selected element
    ISelection selection = HandlerUtil.getCurrentSelection(event_p);
    final ModelElement modelElement = getSelectedModelElement(selection);

    // init and open the find/replace dialog

    SystemEngineering root = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(modelElement);
    if (null == root) {
      return null;
    }

    HashSet<EObject> rootContents = (HashSet<EObject>) EcoreUtil2.getAllContents(Collections.singletonList(root));

    // TODO: selection should be Object[]
    FindAndReplaceDialog dialog = new FindAndReplaceDialog(HandlerUtil.getActiveWorkbenchWindow(event_p).getShell(), root, rootContents, selection, 2);
    // AbstractTreeViewer.ALL_LEVELS
    dialog.open();

    return null;
  }

  public ModelElement getSelectedModelElement(ISelection selection_p) {
    if (!(selection_p instanceof IStructuredSelection)) {
      return null;
    }
    Object selectedElement = ((IStructuredSelection) selection_p).getFirstElement();
    if ((selectedElement instanceof ModelElement)) {
      return (ModelElement) selectedElement;
    }
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

  @Override
  public void setEnabled(Object evaluationContext_p) {
    // Get current selection.
    ISelection selection = getCurrentSelection(evaluationContext_p);
    // Extract selected model element from selection.
    EObject selectedElement = getSelectedModelElement(selection);
    if ((null != selectedElement) && (selectedElement instanceof CapellaElement)) {
      setBaseEnabled(true);
      return;
    }
    setBaseEnabled(false);
  }

}
