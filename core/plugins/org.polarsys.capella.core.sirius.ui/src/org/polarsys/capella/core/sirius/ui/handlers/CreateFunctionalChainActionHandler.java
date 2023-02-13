/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;

public class CreateFunctionalChainActionHandler extends AbstractDiagramCommandHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IStructuredSelection selection = getSelection();

    List<AbstractGraphicalEditPart> edgeEditParts = new ArrayList<AbstractGraphicalEditPart>();
    for (Object elem : selection.toList()) {
      if (elem instanceof AbstractGraphicalEditPart) {
        edgeEditParts.add((AbstractGraphicalEditPart) elem);
      }
    }
    if (!edgeEditParts.isEmpty()) {
      List<EObject> diagramElements = edgeEditParts.stream().map(edgeEditPart -> edgeEditPart.getModel())
          .map(View.class::cast).map(model -> model.getElement()).collect(Collectors.toList());

      EObject context = ((DDiagramElement) diagramElements.get(0)).getTarget();
      DDiagram diagram = CapellaServices.getService().getDiagramContainer((EObject) edgeEditParts.get(0).getModel());

      AbstractReadWriteCommandWithResult customCommand = new AbstractReadWriteCommandWithResult(diagram, context,
          diagramElements);

      ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(context))
          .execute(customCommand);

      EObject result = customCommand.getResult();
      return result;

    }
    return null;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  private class AbstractReadWriteCommandWithResult extends AbstractReadWriteCommand {
    DNode result = null;
    EObject context = null;
    List<EObject> diagramElements = new ArrayList<EObject>();
    DDiagram diagram = null;

    public AbstractReadWriteCommandWithResult(DDiagram diagram, EObject context, List<EObject> diagramElements) {
      this.context = context;
      this.diagramElements = diagramElements;
      this.diagram = diagram;
    }

    public DNode getResult() {
      return result;
    }

    public void run() {
      EObject fc = FunctionalChainServices.getFunctionalChainServices().createFunctionalChain(context, diagramElements);

      String mappingName = MappingConstantsHelper.getMappingFunctionalChainEnd(diagram);

      NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
      result = DiagramServices.getDiagramServices().createNode(mapping, fc, diagram, diagram);

      DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
    }
  }

}
