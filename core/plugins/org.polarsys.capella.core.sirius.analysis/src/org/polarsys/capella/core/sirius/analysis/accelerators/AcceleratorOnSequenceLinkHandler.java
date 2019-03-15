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

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;

public class AcceleratorOnSequenceLinkHandler extends AbstractHandler {
  FunctionalChainServices functionalChainServices = FunctionalChainServices.getFunctionalChainServices();

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ISelection selection = HandlerUtil.getActiveMenuSelection(event);

    DEdge selectedSeqLinkEdge = getDiagramElementFromSelection(selection);
    if (selectedSeqLinkEdge != null) {
      ExecutionManager manager = TransactionHelper.getExecutionManager(selectedSeqLinkEdge);

      manager.execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          functionalChainServices.accelerateOnSequenceLinkEdge(selectedSeqLinkEdge);
        }
      });
    }
    return null;
  }

  private DEdge getDiagramElementFromSelection(ISelection selection) {
    if (selection instanceof StructuredSelection) {
      StructuredSelection structuredSelection = (StructuredSelection) selection;
      if (structuredSelection.size() == 1) {
        Object selectedElement = structuredSelection.getFirstElement();
        if (selectedElement instanceof IDiagramEdgeEditPart) {
          return ((IDiagramElementEditPart) selectedElement).getAdapter(DEdge.class);
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

    DDiagramElement diagramElement = getDiagramElementFromSelection(selection);

    if (diagramElement instanceof DEdge && diagramElement.getTarget() instanceof SequenceLink) {
      
      DEdge seqLinkEdge = (DEdge) diagramElement;
      List<DNode> closestSourceFCIFViews = functionalChainServices.findFlatClosestFCIFunctionViewsAsSource(seqLinkEdge);

      if (closestSourceFCIFViews.isEmpty()) {
        return false;
      }
      // if the sources are empty, then stop now instead of continuing for target
      List<DNode> closestTargetFCIFViews = functionalChainServices.findFlatClosestFCIFunctionViewsAsTarget(seqLinkEdge);

      return !closestTargetFCIFViews.isEmpty();
    }
    return false;
  }

}
