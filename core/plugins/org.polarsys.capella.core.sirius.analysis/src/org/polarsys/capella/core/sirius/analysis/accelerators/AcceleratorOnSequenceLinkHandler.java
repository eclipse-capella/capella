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

import java.net.URL;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.provider.FunctionalChainInvolvementLinkItemProviderDecorator;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;

public class AcceleratorOnSequenceLinkHandler extends AbstractHandler implements IElementUpdater {
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

  private DDiagramElement getDiagramElementFromActiveSelection() {
    IWorkbench workbench = PlatformUI.getWorkbench();
    IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
    IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
    ISelection selection = activePage.getSelection();

    return getDiagramElementFromSelection(selection);
  }

  @Override
  public boolean isEnabled() {
    DDiagramElement diagramElement = getDiagramElementFromActiveSelection();
    return (diagramElement instanceof DEdge && diagramElement.getTarget() instanceof SequenceLink);
  }

  @Override
  public void updateElement(UIElement element, Map parameters) {
    DDiagramElement diagramElement = getDiagramElementFromActiveSelection();
    if ((diagramElement instanceof DEdge && diagramElement.getTarget() instanceof SequenceLink)) {
      SequenceLink seqLink = (SequenceLink) diagramElement.getTarget();
      EObject fc = seqLink.eContainer();
      if (fc instanceof OperationalProcess) {
        element.setText("Create new Interaction");
        URL url = (URL) CapellaModellerEditPlugin.INSTANCE
            .getImage(FunctionalChainInvolvementLinkItemProviderDecorator.ICON_PATH_FCIL_EXCHANGE_OA);
        element.setIcon(ImageDescriptor.createFromURL(url));
      } else if (fc instanceof FunctionalChain) {
        element.setText("Create new Exchange");
        URL url = (URL) CapellaModellerEditPlugin.INSTANCE
            .getImage(FunctionalChainInvolvementLinkItemProviderDecorator.ICON_PATH_FCIL_EXCHANGE);
        element.setIcon(ImageDescriptor.createFromURL(url));
      }
    }

  }

}
