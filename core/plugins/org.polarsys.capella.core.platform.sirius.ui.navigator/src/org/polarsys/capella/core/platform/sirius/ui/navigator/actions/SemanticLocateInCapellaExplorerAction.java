/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ui.actions.LocateFilteredElementsInCommonNavigatorAction;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate.NavigationAdvisor;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * The action to locate semantically a Capella model element into the Capella explorer from the diagram view.
 */
public class SemanticLocateInCapellaExplorerAction extends LocateInCapellaExplorerAction {

  /**
   * Is given selection compatible with this action ?
   * @param selection
   * @return
   */
  protected boolean isEnabled(ISelection selection) {
    boolean result = false;
    if (!selection.isEmpty()) {
      Object element = getElement(getFirstSelectedElement(selection));
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
  public void run(IAction action) {
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
    else {
      LocateFilteredElementsInCommonNavigatorAction locateFilteredElementsInCommonNavigatorAction = new LocateFilteredElementsInCommonNavigatorAction(CapellaCommonNavigator.ID);
      locateFilteredElementsInCommonNavigatorAction.run(new StructuredSelection(navigableElements.toArray()));
    }
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    super.selectionChanged(action, selection);
    action.setEnabled(isEnabled(selection));
  }
}
