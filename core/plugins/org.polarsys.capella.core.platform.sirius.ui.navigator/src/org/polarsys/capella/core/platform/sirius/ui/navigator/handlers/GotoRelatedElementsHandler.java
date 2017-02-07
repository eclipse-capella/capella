/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.NavigateAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate.NavigationAdvisor;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * Handler used to provide a Goto sub menu manager from Sirius diagram editor.
 */
public class GotoRelatedElementsHandler extends CompoundContributionItem {
  /**
   * No contribution.
   */
  private static final IContributionItem[] NO_CONTRIBUTION_ITEM = new IContributionItem[0];

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContributionItem[] getContributionItems() {
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    IWorkbenchPart activePart = activePage.getActivePart();
    CapellaCommonNavigator capellaCommonNavigator = (CapellaCommonNavigator) activePage.findView(CapellaCommonNavigator.ID);
    if ((null == activePart) || (null == capellaCommonNavigator)) {
      return NO_CONTRIBUTION_ITEM;
    }
    ISelection selection = activePart.getSite().getSelectionProvider().getSelection();
    if ((selection != null) && (selection instanceof IStructuredSelection)) {

      // Preconditions
      EObject element = getModelElement(((IStructuredSelection) selection).getFirstElement());
      if (null == element) {
        return NO_CONTRIBUTION_ITEM;
      }
      if (!CapellaResourceHelper.isSemanticElement(element)) {
        return NO_CONTRIBUTION_ITEM;
      }

      List<IContributionItem> result = new ArrayList<IContributionItem>(0);
      Set<EObject> navigableElements = NavigationAdvisor.getInstance().getNavigableElements(element);
      for (EObject currentModelElement : navigableElements) {
        // Create a navigate action that enables this navigation.
        NavigateAction action = new NavigateAction(currentModelElement, capellaCommonNavigator.getCommonViewer());
        action.setText(EObjectLabelProviderHelper.getText(currentModelElement));
        action.setImageDescriptor(ImageDescriptor.createFromImage(EObjectImageProviderHelper.getImage(currentModelElement)));
        result.add(new ActionContributionItem(action));
      }
      return result.toArray(new IContributionItem[result.size()]);
    }

    return NO_CONTRIBUTION_ITEM;
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
  protected EObject getModelElement(Object uiSelectedElement_p) {
    Object semanticElement = LocateInCapellaExplorerAction.getElement(uiSelectedElement_p);
    return (semanticElement instanceof EObject) ? (EObject) semanticElement : null;
  }
}
