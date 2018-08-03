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
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate.NavigationAdvisor;

/**
 * Handler used to provide a Goto sub menu manager from Sirius diagram editor.
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
    ISelection selection =  service.getSelection();
    if ((selection != null) && (selection instanceof IStructuredSelection)) {

      // Preconditions
      Object element = getModelElement(((IStructuredSelection) selection).getFirstElement());
      if (null == element) {
        return NO_CONTRIBUTION_ITEM;
      }
      List<IContributionItem> result = new ArrayList<IContributionItem>(0);
      Collection<EObject> navigableElements = getRelatedElements(element);
      for (EObject currentModelElement : navigableElements) {
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
    return  NavigationAdvisor.getInstance().getNavigableElements(element);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void fill(Menu menu_p, int index_p) {
    super.fill(menu_p, index_p);
    menu_p.getParentItem().setImage(CapellaNavigatorPlugin.getDefault().getImage(IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));
  }

  /**
   * Get the related model element.
   * @param uiSelectedElement_p
   * @return
   */
  protected Object getModelElement(Object uiSelectedElement_p) {
    Object semanticElement = LocateInCapellaExplorerAction.getElement(uiSelectedElement_p);
    return semanticElement;
  }


  
}
