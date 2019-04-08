/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.accelerators;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;

public class AcceleratorOnFCILinkHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ISelection selection = HandlerUtil.getActiveMenuSelection(event);
    FunctionalChainInvolvementLink selectedFciLink = getFCILFromSelection(selection);
    if (selectedFciLink != null) {
      ExecutionManager manager = TransactionHelper.getExecutionManager(selectedFciLink);

      manager.execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          FunctionalChainExt.createSequenceLink(selectedFciLink);
        }
      });
    }
    return null;
  }

  private FunctionalChainInvolvementLink getFCILFromSelection(ISelection selection) {
    if (selection instanceof StructuredSelection) {
      StructuredSelection structuredSelection = (StructuredSelection) selection;
      if (structuredSelection.size() == 1) {
        Object selectedElement = structuredSelection.getFirstElement();
        if (selectedElement instanceof IDiagramEdgeEditPart) {
          ModelElement capellaElement = ModelAdaptation.adaptToCapella(selectedElement);
          if (capellaElement instanceof FunctionalChainInvolvementLink) {
            return (FunctionalChainInvolvementLink) capellaElement;
          }
        }
      }
    }
    return null;
  }

  @Override
  public boolean isEnabled() {
    IWorkbench workbench = PlatformUI.getWorkbench();
    IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
    IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
    ISelection selection = activePage.getSelection();

    FunctionalChainInvolvementLink selectedFCIL = getFCILFromSelection(selection);
    return selectedFCIL != null && !(selectedFCIL.getInvolved() instanceof AbstractFunction);
  }
}
