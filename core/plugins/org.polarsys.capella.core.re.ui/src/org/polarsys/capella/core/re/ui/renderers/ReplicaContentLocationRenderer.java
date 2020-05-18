/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.ui.renderers;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.TransferData;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.helpers.move.CapellaMoveHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.drop.BasicDropConstraints;
import org.polarsys.capella.core.platform.sirius.ui.navigator.drop.ExplorerDropAdapterAssistant;

/**
 *
 */
public class ReplicaContentLocationRenderer extends org.polarsys.capella.common.re.ui.renderers.ReplicaContentLocationRenderer {

  @Override
  protected AdapterFactory getAdapterFactory(IRendererContext context_p) {
    return CapellaAdapterFactoryProvider.getInstance().getAdapterFactory();
  }

  @Override
  public IStatus validateDrop(Object target_p, int operation_p, TransferData transferType_p) {

    // We check with capella drop mechanism if we can allow a drop of selection into target destination.
    // Before that, we need to adapt any CatalogElementLink to their Target elements, cause we check drag/drop of their targets.

    IStatus isValid = super.validateDrop(target_p, operation_p, transferType_p);
    if (isValid.isOK()) {
      ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
      if (selection instanceof IStructuredSelection) {
        List<EObject> adaptLinks = new LinkedList<EObject>();
        for (Object object : ((IStructuredSelection) selection).toList()) {
          if (object instanceof CatalogElementLink) {
            adaptLinks.add(((CatalogElementLink) object).getTarget());
          }
        }
        Object adaptTarget = target_p;
        if (adaptTarget instanceof CatalogElementLink) {
          adaptTarget = ((CatalogElementLink) adaptTarget).getTarget();
        }
        ISelection adaptSelection = new StructuredSelection(adaptLinks);
        try {
          ExplorerDropAdapterAssistant assistant = new ExplorerDropAdapterAssistant(new CapellaMoveHelper()) {

            @Override
            protected BasicDropConstraints getBasicConstraints() {
              // We override basic constraints since we make a copy of element. So we allow to drag into parent of the element.
              if (_basicDropConstraints == null) {
                _basicDropConstraints = new BasicDropConstraints() {

                  @Override
                  protected boolean isCopy(int operation_p) {
                    return true;
                  }

                  @Override
                  protected boolean allowInside(int operation_p) {
                    return false;
                  }

                };
              }
              return super.getBasicConstraints();
            }

          };
          LocalSelectionTransfer.getTransfer().setSelection(adaptSelection);
          isValid = assistant.validateDrop(adaptTarget, operation_p, transferType_p);
        } finally {
          LocalSelectionTransfer.getTransfer().setSelection(selection);
        }
      }
    }
    return isValid;
  }
}
