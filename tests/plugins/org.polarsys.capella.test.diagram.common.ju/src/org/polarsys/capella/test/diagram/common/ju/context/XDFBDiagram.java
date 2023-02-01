/*******************************************************************************
 * Copyright (c) 2016, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.context;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.helpers.ToolProviderHelper;
import org.polarsys.capella.core.sirius.ui.handlers.CreateFunctionalChainActionHandler;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateContainerTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateEdgeTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateNodeTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBInsertRemoveTools;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InitializationFromExistingDiagramTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.ReconnectTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SelectFromListTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SwitchTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBDiagram extends CommonDiagram {

  BlockArchitectureExt.Type type = null;

  public XDFBDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(context, diagram);
    this.type = type;
  }

  public static XDFBDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    String name = null;
    if (type == Type.OA) {
      name = IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME;
    } else if (type == Type.SA) {
      name = IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME;
    } else if (type == Type.LA) {
      name = IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;
    } else if (type == Type.PA) {
      name = IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;
    }

    return (XDFBDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new XDFBDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static XDFBDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (XDFBDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new XDFBDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  @Deprecated
  public void createFunction(String elementId, String containerId) {

    new CreateContainerTool(this, IToolNameConstants.TOOL_CREATE_FUNCTION, containerId, elementId).run();
  }

  @Deprecated
  public void createFunction(String elementId) {
    createFunction(elementId, getDiagramId());
  }

  public String createNode(String containerId, XDFBCreateNodeTools toolName) {

    DNode graphicalElement = new CreateAbstractDNodeTool<DNode>(this, toolName.getToolName(), containerId).run();

    customVerificationOnCreatedNode(toolName, graphicalElement, containerId);

    return getSemanticIdFromView(graphicalElement);
  }

  public String createContainer(String containerId, XDFBCreateContainerTools toolName) {

    DDiagramElementContainer graphicalElement = new CreateContainerTool(this, toolName.getToolName(), containerId)
        .run();

    customVerificationOnCreatedContainer(toolName, graphicalElement, containerId);

    return getSemanticIdFromView(graphicalElement);
  }

  public String createEdge(String sourceViewId, String targetViewId, XDFBCreateEdgeTools toolName) {

    DEdge graphicalElement = new CreateDEdgeTool(this, toolName.getToolName(), sourceViewId, targetViewId, null, null,
        null).run();

    customVerificationOnCreatedEdge(toolName, graphicalElement, sourceViewId, targetViewId);

    return getSemanticIdFromView(graphicalElement);
  }

  @Deprecated
  public void createFunctionalExchange(String id, String sourceViewId, String targetViewId, String newSourceIdentifier,
      String newTargetIdentifier) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_CREATE_FUNCTIONAL_EXCHANGE, sourceViewId, targetViewId, id,
        newSourceIdentifier, newTargetIdentifier).run();
  }

  @Deprecated
  public void createFunctionalExchange(String id, String sourceViewId, String targetViewId) {
    createFunctionalExchange(id, sourceViewId, targetViewId, null, null);
  }

  // The CreatePathTool has inconsistencies in comparison to other creation tools
  // TODO fix this
  public void createFunctionalChain(String path, String... links) {
    List<DSemanticDecorator> decorators = Arrays.stream(links).map(string -> getView(string))
        .collect(Collectors.toList());
    List<EditPart> correspondingEditPart = decorators.stream()
        .map(decorator -> DiagramServices.getDiagramServices().getEditPart((DDiagramElement) decorator))
        .collect(Collectors.toList());

    ExecutionEvent event = createExecutionEvent(correspondingEditPart);
    CreateFunctionalChainActionHandler handler = new CreateFunctionalChainActionHandler() {
      @Override
      protected IStructuredSelection getSelection() {
        StructuredSelection selection = new StructuredSelection(correspondingEditPart);
        return selection;
      }
    };
    Object result = null;
    try {
      result =  handler.execute(event);
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    putView(path, (DDiagramElement) result);
    getSessionContext().putSemanticElement(path, ((DDiagramElement) result).getTarget());
  }

  public void showElements(String containerId, XDFBInsertRemoveTools toolName, String... elementsToBeInsertedIds) {
    new InsertRemoveTool(this, toolName.getToolName(), containerId).insert(elementsToBeInsertedIds);
  }

  public void hideElements(String containerId, XDFBInsertRemoveTools toolName, String... elementsToBeRemovedIds) {
    new InsertRemoveTool(this, toolName.getToolName(), containerId).remove(elementsToBeRemovedIds);
  }

  public void switchInFunctionalExchangesCategories(String id) {
    new SwitchTool(this, IToolNameConstants.TOOL_SWITCH_FUNCTIONAL_EXCHANGE_CATEGORY).insert(id);
  }

  public void switchOutFunctionalExchangesCategories(String id) {
    new SwitchTool(this, IToolNameConstants.TOOL_SWITCH_FUNCTIONAL_EXCHANGE_CATEGORY).remove(id);
  }

  public void reconnectFunctionalExchange(String edgeId, String oldSourceId, String newSourceId) {
    new ReconnectTool(this, ToolProviderHelper.getToolReconnectComponentExchangeSource(getDiagram()), edgeId,
        oldSourceId, newSourceId).run();
  }

  public void cannotReconnectFunctionalExchange(String edgeId, String oldSourceId, String newSourceId) {
    new ReconnectTool(this, ToolProviderHelper.getToolReconnectComponentExchangeSource(getDiagram()), edgeId,
        oldSourceId, newSourceId).shouldFail();
  }

  public void initializationFromExistingDiagram(DiagramContext existingContext) {
    new InitializationFromExistingDiagramTool(this, IToolNameConstants.TOOL_INITIALIZATION_FROM_EXISTING_DIAGRAM,
        existingContext).insert(existingContext.getDiagramId());
  }

  public void insertElementsFromScenario(String[] selectedScenarioIds, String... inserted) {
    new SelectFromListTool(this, IToolNameConstants.TOOL_COMMON_ELEMENTS_FROM_SCENARIO, this.getDiagramId(), inserted)
        .select(selectedScenarioIds);
  }

  public void insertElementsFromModeStateMachine(String[] selectedModesIds, String... inserted) {

    String name = null;
    switch (this.type) {

    case OA:
      name = IToolNameConstants.TOOL_OAIB_INSERT_ACTIVITIES_FROM_MODE_STATE;
      break;

    default:
      name = IToolNameConstants.TOOL_COOMON_FUNCTIONS_FROM_MODE_STATE;
    }

    new SelectFromListTool(this, name, this.getDiagramId(), inserted).select(selectedModesIds);
  }

  public void dragAndDropFunctionPort(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_XDFB_DND_FUNCTIONPORT);
  }

  public void dragAndDropAbstractFunction(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, getDragAndDropAbstractFunctionToolName());
  }

  public void dragAndDropFunctionFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, getDragAndDropFunctionFromExplorerToolName());
  }

  private String getDragAndDropAbstractFunctionToolName() {
    if (type == Type.OA) {
      return IDNDToolNameConstants.TOOL_OAIB_DND_ABSTRACTFUNCTION;
    }

    return IDNDToolNameConstants.TOOL_XDFB_DND_ABSTRACTFUNCTION;
  }

  private String getDragAndDropFunctionFromExplorerToolName() {
    if (type == Type.OA) {
      return IDNDToolNameConstants.TOOL_OAIB_DND_OPERATIONALACTIVITY_FROM_EXPLORER;
    }

    return IDNDToolNameConstants.TOOL_XDFB_DND_FUNCTION_FROM_EXPLORER;
  }

  private void customVerificationOnCreatedNode(XDFBCreateNodeTools toolName, DDiagramElement view, String containerId) {

    switch (toolName) {

    case CREATE_INPUT_PORT:
      checkClassOfDiagramElement(view, FaPackage.Literals.FUNCTION_INPUT_PORT);
      checkParentOfCreatedElement(view, containerId);
      break;

    case CREATE_OUTPUT_PORT:
      checkClassOfDiagramElement(view, FaPackage.Literals.FUNCTION_OUTPUT_PORT);
      checkParentOfCreatedElement(view, containerId);
      break;

    case CREATE_CONSTRAINT:
      checkClassOfDiagramElement(view, CapellacorePackage.Literals.CONSTRAINT);
      break;

    default:
      break;
    }
  }

  private void customVerificationOnCreatedContainer(XDFBCreateContainerTools toolName, DDiagramElement view,
      String containerId) {

    switch (toolName) {

    default:
      checkClassOfFunctionType(view);
      checkParentOfCreatedElement(view, containerId);
      break;
    }
  }

  private void customVerificationOnCreatedEdge(XDFBCreateEdgeTools toolName, DDiagramElement view, String sourceId,
      String targetId) {

    switch (toolName) {

    case CREATE_FUNCTIONAL_EXCHANGE:
      checkClassOfDiagramElement(view, FaPackage.Literals.FUNCTIONAL_EXCHANGE);
      checkSourceAndTargetOfFunctionalExchange(view, sourceId, targetId);
      break;

    case CREATE_CONSTRAINT_LINK:
      checkClassOfDiagramElement(view, CapellacorePackage.Literals.CONSTRAINT);
      checkConstraintOnElement(view, targetId);
      break;

    default:
      break;
    }
  }

  private void checkClassOfDiagramElement(DDiagramElement view, EClass expectedClass) {

    EObject semanticElement = view.getTarget();
    boolean result = expectedClass == semanticElement.eClass();

    Assert.assertTrue(
        NLS.bind("Created Object has the WRONG class. Expected Class: " + expectedClass.getName() + " ; Actual Class:"
            + semanticElement.getClass().getSimpleName(), EObjectLabelProviderHelper.getText(semanticElement)),
        result);
  }

  private void checkParentOfCreatedElement(DDiagramElement view, String containerId) {

    EObject semanticElement = view.getTarget();
    EObject containerElement = getSessionContext().getSemanticElement(containerId);

    // The only containers we have (besides the diagram) are functions in XDFB Diagrams
    if (containerElement instanceof AbstractFunction) {

      AbstractFunction functionContainer = (AbstractFunction) containerElement;

      boolean result = functionContainer.getSubFunctions().contains(semanticElement);

      // If the created element was not a function, we search among input and output ports
      if (!result) {

        result = functionContainer.getInputs().contains(semanticElement);

        if (!result) {
          result = functionContainer.getOutputs().contains(semanticElement);
        }
      }

      Assert.assertTrue(
          NLS.bind("Created Element has the WRONG parent.", EObjectLabelProviderHelper.getText(semanticElement)),
          result);
    }
  }

  private void checkClassOfFunctionType(DDiagramElement view) {

    EObject semanticElement = view.getTarget();
    EClass expectedClass = null;

    switch (type) {

    case OA:
      expectedClass = OaPackage.Literals.OPERATIONAL_ACTIVITY;
      break;

    case SA:
      expectedClass = CtxPackage.Literals.SYSTEM_FUNCTION;
      break;

    case LA:
      expectedClass = LaPackage.Literals.LOGICAL_FUNCTION;
      break;

    case PA:
      expectedClass = PaPackage.Literals.PHYSICAL_FUNCTION;
      break;

    default:
      break;
    }

    boolean result = expectedClass == semanticElement.eClass();

    Assert.assertTrue(
        NLS.bind("Created Function has the WRONG class. Expected Class: " + expectedClass.getName() + " ; Actual Class:"
            + semanticElement.getClass().getSimpleName(), EObjectLabelProviderHelper.getText(semanticElement)),
        result);
  }

  private void checkSourceAndTargetOfFunctionalExchange(DDiagramElement view, String sourceId, String targetId) {

    EObject source = getSessionContext().getSemanticElement(sourceId);
    EObject target = getSessionContext().getSemanticElement(targetId);

    FunctionalExchange exchange = (FunctionalExchange) view.getTarget();

    if (type != Type.OA) {

      FunctionInputPort inputPort = (FunctionInputPort) exchange.getTarget();
      FunctionOutputPort outputPort = (FunctionOutputPort) exchange.getSource();

      // Cast to AbstractAction to gain access to getInputs/getOutputs without casting to level specific function class
      boolean inputResult = false;
      if (target instanceof AbstractAction) {
        inputResult = ((AbstractAction) target).getInputs().contains(inputPort);
      } else if (target instanceof FunctionInputPort) {
        inputResult = ((FunctionInputPort) target) == inputPort;
      }

      Assert.assertTrue(
          NLS.bind("Target Element is not equal to the expected one", EObjectLabelProviderHelper.getText(target)),
          inputResult);

      boolean outputResult = false;
      if (source instanceof AbstractAction) {
        outputResult = ((AbstractAction) source).getOutputs().contains(outputPort);
      } else if (source instanceof FunctionOutputPort) {
        outputResult = ((FunctionOutputPort) source) == outputPort;
      }

      Assert.assertTrue(
          NLS.bind("Source Element is not equal to the expected one", EObjectLabelProviderHelper.getText(source)),
          outputResult);
    }

    else {

      OperationalActivity sourceOA = (OperationalActivity) exchange.getSource();
      OperationalActivity targetOA = (OperationalActivity) exchange.getTarget();

      boolean inputResult = (sourceOA == source);
      Assert.assertTrue(
          NLS.bind("Source OA of Interaction is not the one provided", EObjectLabelProviderHelper.getText(source)),
          inputResult);

      boolean outputResult = (targetOA == target);
      Assert.assertTrue(
          NLS.bind("Target OA of Interaction is not the one provided", EObjectLabelProviderHelper.getText(source)),
          outputResult);
    }
  }

  private void checkConstraintOnElement(DDiagramElement view, String targetId) {

    Constraint semanticElement = (Constraint) view.getTarget();
    EObject constraintTarget = getSessionContext().getSemanticElement(targetId);

    boolean result = semanticElement.getConstrainedElements().contains(constraintTarget);
    Assert.assertTrue(NLS.bind("Target element does not have the Constraint attached",
        EObjectLabelProviderHelper.getText(semanticElement)), result);
  }

  public Type getDiagramType() {
    return this.type;
  }

}
