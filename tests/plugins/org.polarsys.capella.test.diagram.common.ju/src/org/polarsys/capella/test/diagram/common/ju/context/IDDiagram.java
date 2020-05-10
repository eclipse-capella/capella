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
package org.polarsys.capella.test.diagram.common.ju.context;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeWithSelectionTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * 
 */
public class IDDiagram extends CommonDiagram {

  public IDDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static IDDiagram createDiagram(SessionContext executionContext, String diagramKind, String targetIdentifier) {
    if (!IDiagramNameConstants.INTERFACE_DIAGRAM_NAME.equals(diagramKind)) {
      throw new RuntimeException("this diagram is not compatible with IDDiagram API");
    }

    return (IDDiagram) new CreateDiagramStep(executionContext, targetIdentifier, diagramKind) {
      @Override
      public DiagramContext getResult() {
        return new IDDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static IDDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    return createDiagram(executionContext, IDiagramNameConstants.INTERFACE_DIAGRAM_NAME, targetIdentifier);
  }

  public static IDDiagram openDiagram(SessionContext executionContext, String name) {
    return (IDDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new IDDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public String createExchangeItem(String containerId, ExchangeMechanism eiType) {
    return createNodeListElement(containerId, getExchangeItemName(eiType));
  }

  public void createExchangeItemElement(String exchangeItemId, String dataTypeId) {
    new CreateExchangeItemElementTool(this, IToolNameConstants.TOOL_ID_CREATE_EXCHANGEITEMELEMENT, exchangeItemId,
        exchangeItemId, dataTypeId, 0).run();
  }

  public void createExchangeItemElement(String exchangeItemId, String dataTypeId, Integer numExistingAllocations) {
    new CreateExchangeItemElementTool(this, IToolNameConstants.TOOL_ID_CREATE_EXCHANGEITEMELEMENT, exchangeItemId,
        exchangeItemId, dataTypeId, numExistingAllocations).run();
  }

  public void dragAndDropExchangeItem(String interfaceId, String exchangeItemId) {
    new DragAndDropExchangeItemTool(this, IDNDToolNameConstants.TOOL_ID_DND_EXCHANGEITEM_ALLOCATION_FROM_EXPLORER,
        exchangeItemId, interfaceId).run();
  }

  private String getExchangeItemName(ExchangeMechanism eiType) {
    switch (eiType) {
    case EVENT:
      return IToolNameConstants.TOOL_ID_CREATE_EVENT;
    case OPERATION:
      return IToolNameConstants.TOOL_ID_CREATE_OPERATION;
    case FLOW:
      return IToolNameConstants.TOOL_ID_CREATE_FLOW;
    case SHARED_DATA:
      return IToolNameConstants.TOOL_ID_CREATE_DATA;
    case UNSET:
      return IToolNameConstants.TOOL_ID_CREATE_UNDEFINED_EXCHANGE_ITEM;
    default:
      break;
    }
    return null;
  }

  protected class CreateExchangeItemElementTool extends CreateAbstractDNodeWithSelectionTool<DNodeListElement> {
    protected List<ExchangeItemAllocation> ownedExchngeItemAllocations;
    protected ExchangeItemAllocation allocation;
    protected ExchangeItem exchangeItem;
    protected Integer numExistingAllocations;

    public CreateExchangeItemElementTool(DiagramContext context, String toolName, String targetContainerId,
        String containerId, String selectedId, Integer numExistingAllocations) {
      super(context, toolName, targetContainerId, containerId, selectedId, null, null);
      this.numExistingAllocations = numExistingAllocations;
    }

    @Override
    protected void preRunTest() {
      super.preRunTest();
      DSemanticDecorator element = getContainerView();
      assertTrue(element.getTarget() instanceof ExchangeItemAllocation);
      allocation = (ExchangeItemAllocation) element.getTarget();
      exchangeItem = allocation.getAllocatedItem();
      ownedExchngeItemAllocations = new ArrayList<ExchangeItemAllocation>();
      ownedExchngeItemAllocations
          .addAll((Collection<? extends ExchangeItemAllocation>) exchangeItem.getOwnedElements());

      assertTrue(ownedExchngeItemAllocations.size() == numExistingAllocations);
    }

    @Override
    protected void postRunTest() {
      List<ExchangeItemAllocation> newOwnedParameters = new ArrayList<ExchangeItemAllocation>();
      newOwnedParameters.addAll((Collection<? extends ExchangeItemAllocation>) exchangeItem.getOwnedElements());

      assertTrue(newOwnedParameters.size() == numExistingAllocations + 1);

      newOwnedParameters.removeAll(ownedExchngeItemAllocations);

      assertTrue(newOwnedParameters.size() == 1);
    }

    @Override
    public DNodeListElement getResult() {
      return null;
    }
  }

  protected class DragAndDropExchangeItemTool extends DragAndDropTool {
    Interface interf;
    ArrayList<ExchangeItemAllocation> ownedEIAllocations;

    public DragAndDropExchangeItemTool(DiagramContext context, String toolName, String elementView,
        String containerView) {
      super(context, toolName, elementView, containerView);
    }

    @Override
    protected void initToolArguments() {
      EObject droppedElement = getDiagramContext().getSessionContext().getSemanticElement(elementView);
      DSemanticDecorator element = getDiagramContext().getView(containerView);

      _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, element);
      _toolWrapper.setArgumentValue(ArgumentType.DROPPEDELEMENT, droppedElement);
    }

    @Override
    protected void preRunTest() {
      super.preRunTest();
      DSemanticDecorator element = getDiagramContext().getView(containerView);
      assertTrue(element.getTarget() instanceof Interface);
      interf = (Interface) element.getTarget();
      ownedEIAllocations = new ArrayList<ExchangeItemAllocation>();
      ownedEIAllocations.addAll(interf.getOwnedExchangeItemAllocations());
    }

    @Override
    protected void postRunTest() {
      List<ExchangeItemAllocation> insertedEIAllocations = new ArrayList<ExchangeItemAllocation>();
      insertedEIAllocations.addAll(interf.getOwnedExchangeItemAllocations());

      insertedEIAllocations.removeAll(ownedEIAllocations);

      assertTrue(insertedEIAllocations.size() == 1);
    }

    @Override
    public DDiagramElement getResult() {
      return null;
    }
  }

}
