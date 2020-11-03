/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate.NavigationAdvisor;

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
      Object element = CapellaAdapterHelper.resolveSemanticObject(getFirstSelectedElement(selection));
      if (null != element) {
        Set<EObject> navigableElements = NavigationAdvisor.getInstance().getNavigableElements(element);
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
    // Instead of calculating navigable elements for only the first selected element,
    // we must calculate for all selected ones.
    // Bug 2150. If not, on clicking "Show All", only one element will be selected.
    
    // The semantic elements to select.
    Set<EObject> navigableElements = new HashSet<>();
    
    if (getSelection() instanceof IStructuredSelection) {
      for (Object selectedElement : ((IStructuredSelection) getSelection()).toList()) {
        navigableElements.addAll(NavigationAdvisor.getInstance().getNavigableElements(selectedElement));
      }
    }
    
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
  public void selectionChanged(IAction action, ISelection selection) {
    super.selectionChanged(action, selection);
    if (action != null) {
      action.setEnabled(isEnabled(selection));
    }
  }
}
