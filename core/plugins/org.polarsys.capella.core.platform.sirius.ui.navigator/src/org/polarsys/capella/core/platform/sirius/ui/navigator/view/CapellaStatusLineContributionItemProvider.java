/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.tools.internal.providers.SiriusStatusLineContributionItemProvider;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

public class CapellaStatusLineContributionItemProvider extends SiriusStatusLineContributionItemProvider {

  public class CapellaStatusLineMessageContributionItem extends SiriusStatusLineMessageContributionItem {
    @Override
    public String getText(Object element) {
      if (element instanceof IStructuredSelection) {
        element = ((IStructuredSelection) element).getFirstElement();
      }

      // Handle firstly for representation
      EObject eObject = CapellaAdapterHelper.resolveEObject(element);
      if (eObject instanceof DRepresentation) {
        DRepresentationDescriptor descriptor = RepresentationHelper
            .getRepresentationDescriptor((DRepresentation) eObject);
        return RepresentationHelper.getRepresentationFullPathText(descriptor);
      }

      if (eObject instanceof DRepresentationDescriptor) {
        return RepresentationHelper.getRepresentationFullPathText((DRepresentationDescriptor) eObject);
      }

      // Handle for semantic element
      EObject semanticElement = CapellaAdapterHelper.resolveBusinessObject(element);
      if (semanticElement != null) {
        return EObjectLabelProviderHelper.getFullPathText(semanticElement);
      }
      return "";
    }

    @Override
    public Image getImage(Object element) {
      if (element instanceof IStructuredSelection) {
        element = ((IStructuredSelection) element).getFirstElement();
      }
      element = CapellaAdapterHelper.resolveBusinessObject(element);
      if (element instanceof EObject) {
        return ExtendedImageRegistry.getInstance().getImage(EObjectLabelProviderHelper.getImage((EObject) element));
      }
      return null;
    }

  }

  @Override
  public List<IContributionItem> getStatusLineContributionItems(IWorkbenchPage workbenchPage) {
    List<IContributionItem> a = new ArrayList<>(1);
    a.add(new CapellaStatusLineMessageContributionItem());
    return a;
  }

  @Override
  public boolean provides(IOperation operation) {
    return true;
  }
}
