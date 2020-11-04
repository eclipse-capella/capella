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
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate.NavigationAdvisor;

/**
 * Handler used to provide a Dynamic menu listing all navigable elements.
 */
public class GotoRelatedElementsHandler extends CompoundContributionItem implements IWorkbenchContribution {

  /**
   * No contribution.
   */
  private static final IContributionItem[] NO_CONTRIBUTION_ITEM = new IContributionItem[0];

  IServiceLocator locator = null;

  @Override
  public void initialize(IServiceLocator serviceLocator) {
    locator = serviceLocator;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContributionItem[] getContributionItems() {
    ISelectionService service = locator.getService(ISelectionService.class);
    ISelection selection = service.getSelection();

    if ((selection != null) && (selection instanceof IStructuredSelection)) {

      // Preconditions
      Object firstElement = ((IStructuredSelection) selection).getFirstElement();
      Object element = getModelElement(firstElement);
      if (null == element) {
        return NO_CONTRIBUTION_ITEM;
      }

      List<IContributionItem> result = new ArrayList<IContributionItem>(0);
      Object[] elements = ((IStructuredSelection) selection).toArray();

      Collection<EObject> items = new HashSet<EObject>();
      for (int i = 0; i < elements.length; i++) {
        Collection<EObject> navigableElements = getRelatedElements(elements[i]);
        items.addAll(navigableElements);
      }
      
      items = new ArrayList<EObject>(items);
      Collections.sort((ArrayList<EObject>) items, new Comparator<EObject>() {
        @Override
        public int compare(EObject o1, EObject o2) {
          try {
            int value = o1.eClass().getName().compareTo(o2.eClass().getName());
            if (value == 0) {
              if (o1 instanceof AbstractNamedElement && o2 instanceof AbstractNamedElement) {
                value = ((AbstractNamedElement) o1).getName().compareTo(((AbstractNamedElement) o2).getName());
              }
              if (value == 0) {
                value = o1.toString().compareTo(o2.toString());
              }
            }
            return value;
          } catch (Exception e) {
            return 0;
          }
        }
      });

      for (EObject currentModelElement : items) {
        result.add(createNavigationTowards(currentModelElement));
      }
      return result.toArray(new IContributionItem[result.size()]);
    }

    return NO_CONTRIBUTION_ITEM;
  }

  protected IContributionItem createNavigationTowards(EObject object) {
    IAction action = LocateInCapellaExplorerAction.createLocateTowards(object, Messages.GotoRelatedElementsHandler_name, true);
    return new ActionContributionItem(action);
  }
  
  protected Collection<EObject> getRelatedElements(Object element) {
    return NavigationAdvisor.getInstance().getNavigableElements(element);
  }
  
  /**
   * Get the related model element.
   * @param uiSelectedElement_p
   * @return
   */
  protected Object getModelElement(Object uiSelectedElement) {
    return CapellaAdapterHelper.resolveSemanticObject(uiSelectedElement);
  }
  
}
