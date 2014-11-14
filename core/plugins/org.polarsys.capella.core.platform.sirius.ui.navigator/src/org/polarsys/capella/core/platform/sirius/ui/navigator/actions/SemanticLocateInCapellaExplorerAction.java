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

package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate.NavigationAdvisor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * The action to locate semantically a Capella model element into the Capella explorer from the diagram view.
 */
public class SemanticLocateInCapellaExplorerAction extends LocateInCapellaExplorerAction {

  /**
   * Is given selection compatible with this action ?
   * @param selection_p
   * @return
   */
  protected boolean isEnabled(ISelection selection_p) {
    boolean result = false;
    if (!selection_p.isEmpty()) {
      Object element = getElement(getFirstSelectedElement(selection_p));
      if ((null != element) && (CapellaResourceHelper.isSemanticElement(element))) {
        Set<EObject> navigableElements = NavigationAdvisor.getInstance().getNavigableElements((ModelElement) element);
        result = !navigableElements.isEmpty();
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  @Override
  public void run(IAction action_p) {
    Object object = getElement(getFirstSelectedElement(getSelection()));
    if (!(CapellaResourceHelper.isSemanticElement(object))) {
      // Must not be there, as isEnabled answered true.
      return;
    }
    EObject modelElement = (EObject) object;
    // The new semantic object to select.
    Set<EObject> navigableElements = NavigationAdvisor.getInstance().getNavigableElements(modelElement);
    // If the navigation returns something else, select it.
    if (!navigableElements.isEmpty()) {
      selectElementInCapellaExplorer(new StructuredSelection(navigableElements.toArray()));
    }
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void selectionChanged(IAction action_p, ISelection selection_p) {
    super.selectionChanged(action_p, selection_p);
    action_p.setEnabled(isEnabled(selection_p));
  }
}
