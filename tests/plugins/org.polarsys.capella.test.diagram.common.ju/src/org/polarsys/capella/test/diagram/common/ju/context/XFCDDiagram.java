/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XFCDDiagram extends CommonDiagram {

  protected class InsertInvolvementTool extends InsertRemoveTool {

    protected Set<DDiagramElement> preTestDiagramElements;
    protected Set<DDiagramElement> postTestDiagramElements;
    protected DDiagramElement resultDiagramElement;
    protected DSemanticDecorator containerView;

    public InsertInvolvementTool(DiagramContext context, String toolName, String targetId) {
      super(context, toolName, targetId);
      DSemanticDecorator targetView = getView(targetId);

      preTestDiagramElements = Collections.emptySet();
      postTestDiagramElements = Collections.emptySet();

      if (targetView instanceof DNodeContainer || targetView instanceof DDiagram) {
        containerView = targetView;
      } else {
        containerView = (DSemanticDecorator) targetView.eContainer();
      }
    }

    @Override
    protected void checkPreconditions() {
      // No need to check if the view exists beforehand since we can reinsert the same involvements many times
    }

    @Override
    protected void preRunTest() {
      super.preRunTest();
      preTestDiagramElements = new HashSet<>(
          DiagramServices.getDiagramServices().getOwnedDiagramElements(containerView));

    }

    @Override
    protected void postRunTest() {
      postTestDiagramElements = new HashSet<>(
          DiagramServices.getDiagramServices().getOwnedDiagramElements(containerView));
      postTestDiagramElements.removeAll(preTestDiagramElements);

      Set<String> insertedInvolvementTargetIds = Stream.of(insertedElements).collect(Collectors.toSet());

      for (DDiagramElement newElement : postTestDiagramElements) {
        EObject target = newElement.getTarget();

        if (target instanceof FunctionalChainInvolvement) {
          FunctionalChainInvolvement involvement = (FunctionalChainInvolvement) target;

          InvolvedElement involvedElement = involvement instanceof FunctionalChainReference
              ? ((FunctionalChainReference) involvement).getReferencedFunctionalChain()
              : involvement.getInvolved();

          // all the new elements must contain the target elements
          if (insertedInvolvementTargetIds.remove(involvedElement.getId())) {
            resultDiagramElement = newElement;
            break;
          }
        }
      }
      assertTrue(insertedInvolvementTargetIds.isEmpty());
    }

    @Override
    public DDiagramElement getResult() {
      return resultDiagramElement;
    }

  }

  protected class ConnectFunctionsTool extends CreateDEdgeTool {

    public ConnectFunctionsTool(DiagramContext context, String toolName, String sourceView, String targetView) {
      super(context, toolName, sourceView, targetView);
    }

    @Override
    protected void postRunTest() {
      super.postRunTest();

      DEdge edge = getResult();

      DNode sourceNode = (DNode) edge.getSourceNode();
      EObject sourceNodeTarget = sourceNode.getTarget();

      assertTrue(sourceNodeTarget instanceof FunctionalChainInvolvementFunction);
      FunctionalChainInvolvementFunction sourceInvolvementFunction = ((FunctionalChainInvolvementFunction) sourceNodeTarget);

      EObject resultTarget = edge.getTarget();
      assertTrue(resultTarget instanceof FunctionalChainInvolvementLink);
      FunctionalChainInvolvementLink involvementLink = (FunctionalChainInvolvementLink) resultTarget;

      assertEquals(sourceInvolvementFunction.getInvolved(), involvementLink.getInvolved());
    }

  }

  protected class ExchangeTool extends CreateDEdgeTool {

    protected String functionalExchangeId;
    protected EObject functionalExchange;

    public ExchangeTool(DiagramContext context, String toolName, String sourceView, String targetView,
        String functionalExchangeid) {
      super(context, toolName, sourceView, targetView);
      this.functionalExchangeId = functionalExchangeid;
    }

    @Override
    protected void preRunTest() {
      functionalExchange = getSessionContext().getSemanticElement(functionalExchangeId);
      assertTrue(functionalExchange instanceof FunctionalExchange);

      HeadlessResultOpProvider.INSTANCE.setCurrentOp(createOperation());
      super.preRunTest();
    }

    private IHeadlessResult createOperation() {
      return new IHeadlessResult() {

        @Override
        public Object getResult(Collection<? extends EObject> selections, Map<String, Object> parameters) {
          return Arrays.asList(functionalExchange);
        }
      };
    }

    @Override
    protected void postRunTest() {
      super.postRunTest();

      DEdge edge = getResult();
      EObject resultTarget = edge.getTarget();
      assertTrue(resultTarget instanceof FunctionalChainInvolvementLink);
      FunctionalChainInvolvementLink involvementLink = (FunctionalChainInvolvementLink) resultTarget;

      assertEquals(functionalExchange, involvementLink.getInvolved());
    }

  }

  public XFCDDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static XFCDDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    String name = null;

    switch (type) {
    case OA:
      name = IDiagramNameConstants.OPERATIONAL_PROCESS_DESCRIPTION_DIAGRAM_NAME;
      break;
    default:
      name = IDiagramNameConstants.FUNCTIONAL_CHAIN_DIAGRAM_NAME;
      break;
    }

    return (XFCDDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new XFCDDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static XFCDDiagram getDiagram(SessionContext executionContext, String targetIdentifier) {

    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(
        executionContext.getSession().getTransactionalEditingDomain().getResourceSet(), targetIdentifier);
    return new XFCDDiagram(executionContext, ((DDiagram) descriptor.getRepresentation()));
  }

  public String getTargetId(DDiagramElement element) {
    return ((FunctionalChainInvolvement) element.getTarget()).getId();
  }

  public String involveFunction(String containerId, String selectedFunction) {
    InsertInvolvementTool insertInvolvementTool = new InsertInvolvementTool(this,
        IToolNameConstants.TOOL_FCD_INVOLVE_FUNCTION, containerId);
    insertInvolvementTool.insert(selectedFunction);

    DDiagramElement result = insertInvolvementTool.getResult();
    return getTargetId(result);
  }

  public String involveFunctionalChain(String containerId, String selectedFunctionalChainId) {
    InsertInvolvementTool insertInvolvementTool = new InsertInvolvementTool(this,
        IToolNameConstants.TOOL_FCD_INVOLVE_FUNCTIONAL_CHAIN, containerId);
    insertInvolvementTool.insert(selectedFunctionalChainId);

    DDiagramElement result = insertInvolvementTool.getResult();
    return getTargetId(result);
  }

  public void involveExchangeAndFunction(String functionInvolvementId, String functionalExchangeId) {
    new InsertInvolvementTool(this, IToolNameConstants.TOOL_FCD_INVOLVE_EXCHANGE_AND_FUNCTION, functionInvolvementId)
        .insert(functionalExchangeId);
  }

  public String connectFunctions(String functionInvolvementSourceId, String functionInvolvementTargetId) {
    DDiagramElement result = new ConnectFunctionsTool(this, IToolNameConstants.TOOL_FCD_CONNECT_FUNCTIONS,
        functionInvolvementSourceId, functionInvolvementTargetId).run();

    return getTargetId(result);
  }

  public String involveExchange(String sourceViewId, String targetViewId, String functionalExchangeId) {

    DDiagramElement result = new ExchangeTool(this, IToolNameConstants.TOOL_FCD_INVOLVE_EXCHANGE, sourceViewId,
        targetViewId, functionalExchangeId).run();

    return getTargetId(result);
  }

}
