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
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.common.ui.services.commands.AbstractLocateInViewPartHandler;
import org.polarsys.capella.common.ui.services.commands.ActionCommandDelegate;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * Handler to locate active part selection in Project Explorer.
 */
public class LocateInCapellaExplorerHandler extends AbstractLocateInViewPartHandler {
  /**
   * @see org.polarsys.capella.common.ui.services.commands.AbstractLocateInViewPartHandler#getTargetedPartId()
   */
  @Override
  protected String getTargetedPartId() {
    return CapellaCommonNavigator.ID;
  }

  /**
   * @see org.polarsys.capella.common.ui.services.commands.AbstractLocateInViewPartHandler#handleSelection(org.eclipse.jface.viewers.ISelection,
   *      org.eclipse.ui.IWorkbenchPart, org.eclipse.core.commands.ExecutionEvent)
   */
  @Override
  protected IViewPart handleSelection(ISelection selection, IWorkbenchPart activePart, ExecutionEvent event) {
    LocateInCapellaExplorerAction relatedAction = new LocateInCapellaExplorerAction();
    ActionCommandDelegate delegate = new ActionCommandDelegate(event);
    
    // - Calculate the semantic elements to be selected in the navigator here, instead of inside the CapellaCommonNavigator;
    // The CapellaCommonNavigator is used for all type of selection in the navigator, so handling 
    // the semantic elements in it is too late.
    // It causes the selection of Part always points to its AbstractType (Actor i.e)
    
    // - Bug 2150: Should use the Set to avoid duplicating elements
    // If not, in LocateFilteredElementsInCommonNavigatorAction.isSetSelection() will give False even though all
    // semantic elements were selected.
    Set<Object> semanticElementsToSelect = new HashSet<>();
    if (selection instanceof IStructuredSelection) {
      Object[] selectedElements = ((IStructuredSelection) selection).toArray();
      for (Object element : selectedElements) {
        Object semanticElement = LocateInCapellaExplorerAction.getElement(element);
        if (semanticElement != null) {
          semanticElementsToSelect.add(semanticElement);
        }
      }
    }
    
    relatedAction.selectionChanged(delegate, new StructuredSelection(semanticElementsToSelect.toArray()));
    relatedAction.setActivePart(delegate, activePart);
    relatedAction.run(delegate);
    return null;
  }
}
