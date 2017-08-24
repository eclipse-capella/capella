/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.tools.internal.providers.SiriusStatusLineContributionItemProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorLabelProvider;

public class CapellaStatusLineContributionItemProvider extends SiriusStatusLineContributionItemProvider {

  public class CapellaStatusLineMessageContributionItem extends SiriusStatusLineMessageContributionItem {
    @Override
    public String getText(Object element) {
      CapellaNavigatorLabelProvider semanticBrowserLabelProvider = new CapellaNavigatorLabelProvider(
          CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());

      if (element instanceof IStructuredSelection) {
        element = ((IStructuredSelection) element).getFirstElement();
      }
      
      if (element instanceof IGraphicalEditPart)
        element = LocateInCapellaExplorerAction.getElement(element);

      if (element != null && element instanceof EObject) {
        return semanticBrowserLabelProvider.getDescription(element);
      }

      return ICommonConstants.EMPTY_STRING;
    }


    @Override
    public Image getImage(Object element) {
      CapellaNavigatorLabelProvider semanticBrowserLabelProvider = new CapellaNavigatorLabelProvider(
          CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
      if (element instanceof IStructuredSelection) {
        element = ((IStructuredSelection) element).getFirstElement();
      }

      if (element instanceof IGraphicalEditPart) {
        element = LocateInCapellaExplorerAction.getElement(element);
      }

      if (element != null && element instanceof EObject) {
        return semanticBrowserLabelProvider.getImage(element);
      }
      return null;
    }

  }

  public CapellaStatusLineContributionItemProvider() {
  }

  @Override
  public List<IContributionItem> getStatusLineContributionItems(IWorkbenchPage workbenchPage) {
    List<IContributionItem> a = new ArrayList<IContributionItem>(1);
    a.add(new CapellaStatusLineMessageContributionItem());
    return a;
  }

  @Override
  public boolean provides(IOperation operation) {
    return true;
  }
}
