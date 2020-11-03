/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.move;

import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.helpers.move.Stage;

public class SendToTransferViewHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
    Collection<EObject> toAdd = CapellaAdapterHelper.resolveBusinessObjects(HandlerUtil.getCurrentStructuredSelection(event).toList());
    EditingDomain domain = EcoreUtil2.getEditingDomain(toAdd);
    if (domain != null) {
      try {
        MoveStagingView view = (MoveStagingView) window.getActivePage().showView(MoveStagingView.VIEW_ID);
        Stage stage = view.getStage();
        if (stage != null) {
          if (stage.getEditingDomain() == domain) {
            stage.addAll(toAdd);
          }
        } else { 
          view.init(toAdd);
        }
      } catch (PartInitException e) {
        throw new ExecutionException(e.getLocalizedMessage(), e);
      }
    }

    return null;
  }

}
