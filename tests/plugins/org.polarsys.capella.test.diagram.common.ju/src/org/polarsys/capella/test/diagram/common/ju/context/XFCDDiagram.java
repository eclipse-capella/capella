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
package org.polarsys.capella.test.diagram.common.ju.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.ControlNodeKind;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SequenceLinkEndExt;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeWithSelectionTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XFCDDiagram extends CommonDiagram {
  protected enum ControlNodeContainer {
    DIAGRAM, SEQUENCE_LINK
  };

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

  public String createControlNode(String containerId, ControlNodeKind kind) {
    String toolName = getControlNodeToolName(kind);
    DNode graphicalElement = new CreateControlNodeTool(this, toolName, containerId, kind).run();
    return getSemanticIdFromView(graphicalElement);
  }

  public String createControlNode(String targetId, String containerId, ControlNodeKind kind) {
    String toolName = getControlNodeToolName(kind);
    DNode graphicalElement = new CreateControlNodeTool(this, toolName, targetId, containerId, kind).run();
    return getSemanticIdFromView(graphicalElement);
  }

  public String createConstructControlNode(String containerId, ControlNodeKind kind) {
    String toolName = getConstructControlNodeToolName(kind);
    DNode graphicalElement = new CreateConstructControlNodeTool(this, toolName, containerId, kind).run();
    return getSemanticIdFromView(graphicalElement);
  }

  public String createConstructControlNode(String targetId, String containerId, ControlNodeKind kind) {
    String toolName = getConstructControlNodeToolName(kind);
    DNode graphicalElement = new CreateConstructControlNodeTool(this, toolName, targetId, containerId, kind).run();
    return getSemanticIdFromView(graphicalElement);
  }

  public String createSequenceLink(String source, String target) {
    String toolName = IToolNameConstants.TOOL_CREATE_SEQUENCE_LINK;
    DEdge graphicalElement = new CreateSequenceLinkTool(this, toolName, source, target).run();
    return getSemanticIdFromView(graphicalElement);
  }

  public void cannotCreateSequenceLink(String source, String target) {
    String toolName = IToolNameConstants.TOOL_CREATE_SEQUENCE_LINK;
    new CreateSequenceLinkTool(this, toolName, source, target).cannotRun();
  }

  public String createFunctionOnSequenceLink(String functionId, String targetId) {
    String toolName = IToolNameConstants.TOOL_CREATE_FUNCTION_ON_SEQUENCE_LINK;
    DNode graphicalElement = new CreateFunctionOnSequenceLinkTool(this, toolName, functionId, targetId,
        this.getDiagramId()).run();
    return getSemanticIdFromView(graphicalElement);
  }

  public String createExchangeWithSequenceLink(String sourceId, String targetId, String functionalExchangeId) {
    String toolName = IToolNameConstants.TOOL_CREATE_EXCHANGE_WITH_SEQUENCE_LINK;
    DEdge graphicalElement = new CreateExchangeWithSequenceLinkTool(this, toolName, sourceId, targetId,
        functionalExchangeId).run();
    return getSemanticIdFromView(graphicalElement);
  }

  public void cannotCreateExchangeWithSequenceLink(String sourceId, String targetId) {
    String toolName = IToolNameConstants.TOOL_CREATE_EXCHANGE_WITH_SEQUENCE_LINK;
    new CreateExchangeWithSequenceLinkTool(this, toolName, sourceId, targetId, null).cannotRun();
  }

  public void invalidCreateExchangeWithSequenceLink(String sourceId, String targetId) {
    String toolName = IToolNameConstants.TOOL_CREATE_EXCHANGE_WITH_SEQUENCE_LINK;
    new CreateExchangeWithSequenceLinkTool(this, toolName, sourceId, targetId, null).shouldFail();
  }

  public String associateSequenceLinkWithExchange(String sourceId, String targetId, String sequenceLinkId) {
    String toolName = IToolNameConstants.TOOL_ASSOCIATE_SEQUENCE_LINK_WITH_EXCHANGE;
    DEdge graphicalElement = new AssociateSequenceLinkToExchangeTool(this, toolName, sourceId, targetId, sequenceLinkId)
        .run();
    return getSemanticIdFromView(graphicalElement);
  }

  public void cannotAssociateSequenceLinkWithExchange(String sourceId, String targetId) {
    String toolName = IToolNameConstants.TOOL_ASSOCIATE_SEQUENCE_LINK_WITH_EXCHANGE;
    new AssociateSequenceLinkToExchangeTool(this, toolName, sourceId, targetId, null).cannotRun();
  }

  private String getControlNodeToolName(ControlNodeKind kind) {
    String name = "";
    switch (kind) {
    case AND:
      name = IToolNameConstants.TOOL_CREATE_CONTROL_NODE_AND;
      break;
    case OR:
      name = IToolNameConstants.TOOL_CREATE_CONTROL_NODE_OR;
      break;
    case ITERATE:
      name = IToolNameConstants.TOOL_CREATE_CONTROL_NODE_IT;
      break;
    default:
      break;
    }

    return name;
  }

  private String getConstructControlNodeToolName(ControlNodeKind kind) {
    String name = "";
    switch (kind) {
    case AND:
      name = IToolNameConstants.TOOL_CREATE_CONSTRUCT_CONTROL_NODE_AND;
      break;
    case OR:
      name = IToolNameConstants.TOOL_CREATE_CONSTRUCT_CONTROL_NODE_OR;
      break;
    case ITERATE:
      name = IToolNameConstants.TOOL_CREATE_CONSTRUCT_CONTROL_NODE_IT;
      break;
    default:
      break;
    }

    return name;
  }

  /*
   * Tools classes
   */
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

  protected class AccelerateSequenceLinkFromInvolvementLink extends AbstractToolStep<DEdge> {

    protected String selectedFunctionalChainInvolvementLink;
    protected int initialNumberOfSequenceLinks;
    protected SequenceLink createdSequenceLink;

    public AccelerateSequenceLinkFromInvolvementLink(DiagramContext diagramContext, String toolName,
        String selectedFunctionalChainInvolvementLink) {

      super(diagramContext, toolName);

      this.selectedFunctionalChainInvolvementLink = selectedFunctionalChainInvolvementLink;
    }

    @Override
    protected void initToolArguments() {
      _toolWrapper.setArgumentValue(ArgumentType.CONTAINER,
          getSessionContext().getSemanticElement(this.selectedFunctionalChainInvolvementLink));
      _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW,
          getDiagramContext().getView(selectedFunctionalChainInvolvementLink));
    }

    @Override
    protected void preRunTest() {

      if (getDiagramContext().getView(this.selectedFunctionalChainInvolvementLink) == null) {
        Assert.fail("The selected FunctionalChainInvolvementLink is not present on the diagram");
      }

      this.initialNumberOfSequenceLinks = getDiagram().getEdges().stream().map(DEdge::getTarget)
          .filter(SequenceLink.class::isInstance).collect(Collectors.toList()).size();
      super.preRunTest();
    }

    @Override
    protected void postRunTest() {

      DiagramHelper.refreshDiagram(getDiagram());

      FunctionalChainInvolvementLink semanticSelectedLink = (FunctionalChainInvolvementLink) getSessionContext()
          .getSemanticElement(this.selectedFunctionalChainInvolvementLink);

      /*
       * Check that the newly created SequenceLink is associated with the selected link
       */
      List<EObject> sequenceLinksAfterTest = getDiagram().getEdges().stream().map(DEdge::getTarget)
          .filter(SequenceLink.class::isInstance).collect(Collectors.toList());

      // Accelerator will also link the new SequenceLink and the target FunctionChainInvolvementLink
      // with another SequenceLink. So 2 new Edges will be created by this tool.
      int numberOfSequenceLinksAfterTest = sequenceLinksAfterTest.size();
      if (numberOfSequenceLinksAfterTest - this.initialNumberOfSequenceLinks != 2) {
        Assert.fail("The number of new sequence links " + numberOfSequenceLinksAfterTest + " is not equal to 2!");
      }

      // Get the SequenceLink that we just created, it is the last link of the list
      int lastIndexOfTheSequencLinkList = numberOfSequenceLinksAfterTest - 1;
      createdSequenceLink = (SequenceLink) sequenceLinksAfterTest.get(lastIndexOfTheSequencLinkList);
      if (!createdSequenceLink.getLinks().contains(semanticSelectedLink)) {
        Assert.fail("The new SequenceLink is not associated with the selected FunctionalChainInvolvementLink!");
      }
    }

    @Override
    public DEdge getResult() {
      return (DEdge) getDiagramContext().getView(createdSequenceLink);
    }
  }

  protected class AccelerateInvolvementLinkFromSequenceLink extends AbstractToolStep<DEdge> {

    protected String targetFunctionalExchange;
    protected String selectedSequenceLink;
    protected String createdFunctionalChainInvolvementLink;

    protected int initialNumberOfSequenceLinks;
    protected int initialNumberOfFunctionalChainInvolvementLinks;
    protected int initialNumberOfAssociatedFCILForSelectedSeqLink;

    protected boolean isTargetFEOnDiagram = false;
    protected boolean isTargetFESelectedInDialog;

    protected String feSource;
    protected String feTarget;

    public AccelerateInvolvementLinkFromSequenceLink(DiagramContext diagramContext, String toolName,
        String targetFunctionalExchange, String selectedSequenceLink) {

      super(diagramContext, toolName);

      this.targetFunctionalExchange = targetFunctionalExchange;
      this.selectedSequenceLink = selectedSequenceLink;
      this.isTargetFESelectedInDialog = true;
    }

    public AccelerateInvolvementLinkFromSequenceLink(DiagramContext diagramContext, String toolName,
        String selectedSequenceLink, String feSource, String feTarget) {

      super(diagramContext, toolName);

      this.selectedSequenceLink = selectedSequenceLink;
      this.feSource = feSource;
      this.feTarget = feTarget;
      this.isTargetFESelectedInDialog = false;
    }

    private IHeadlessResult createOperation() {

      return new IHeadlessResult() {

        @Override
        public Object getResult(Collection<? extends EObject> selections, Map<String, Object> parameters) {

          Object result;

          if (isTargetFESelectedInDialog) {
            result = getSessionContext().getSemanticElement(targetFunctionalExchange);
          } else {
            result = Arrays.asList(getSessionContext().getSemanticElement(feSource),
                getSessionContext().getSemanticElement(feTarget));
          }

          return result;
        }
      };
    }

    @Override
    protected void preRunTest() {

      if (getDiagramContext().getView(this.selectedSequenceLink) == null) {
        Assert.fail("The selected SequenceLink is not present on the diagram");
      }

      if (this.isTargetFESelectedInDialog) {
        FunctionalExchange targetExchange = getSessionContext().getSemanticElement(targetFunctionalExchange);
        for (DEdge edge : getDiagram().getEdges()) {

          if (edge.getTarget() instanceof FunctionalChainInvolvementLink
              && ((FunctionalChainInvolvementLink) edge.getTarget()).getInvolved().equals(targetExchange)) {

            isTargetFEOnDiagram = true;
            break;
          }
        }
      } else {
        this.initialNumberOfAssociatedFCILForSelectedSeqLink = ((SequenceLink) getSessionContext()
            .getSemanticElement(selectedSequenceLink)).getLinks().size();
      }

      this.initialNumberOfSequenceLinks = getDiagram().getEdges().stream().map(DEdge::getTarget)
          .filter(SequenceLink.class::isInstance).collect(Collectors.toList()).size();

      this.initialNumberOfFunctionalChainInvolvementLinks = getDiagram().getEdges().stream().map(DEdge::getTarget)
          .filter(FunctionalChainInvolvementLink.class::isInstance).collect(Collectors.toList()).size();

      HeadlessResultOpProvider.INSTANCE.setCurrentOp(createOperation());
      super.preRunTest();
    }

    @Override
    protected void postRunTest() {

      DiagramHelper.refreshDiagram(getDiagram());

      SequenceLink semanticSelectedLink = (SequenceLink) getSessionContext()
          .getSemanticElement(this.selectedSequenceLink);

      List<EObject> sequenceLinksAfterTest = getDiagram().getEdges().stream().map(DEdge::getTarget)
          .filter(SequenceLink.class::isInstance).collect(Collectors.toList());

      int numberOfSequenceLinksAfterTest = sequenceLinksAfterTest.size();
      int numberOfNewSequenceLinks = numberOfSequenceLinksAfterTest - this.initialNumberOfSequenceLinks;
      if (numberOfNewSequenceLinks != 1) {
        Assert.fail("The number of new sequence links " + numberOfNewSequenceLinks + " is not equal to 1!");
      }

      if (!isTargetFEOnDiagram) {

        List<EObject> FunctionalChainInvolvementLinksAfterTest = getDiagram().getEdges().stream().map(DEdge::getTarget)
            .filter(FunctionalChainInvolvementLink.class::isInstance).collect(Collectors.toList());

        int numberOfFCILinksAfterTest = FunctionalChainInvolvementLinksAfterTest.size();
        if (numberOfFCILinksAfterTest - this.initialNumberOfFunctionalChainInvolvementLinks != 1) {
          Assert.fail("The number of new FCI links " + numberOfFCILinksAfterTest + " is not equal to 1!");
        }
      }

      EList<FunctionalChainInvolvementLink> associatedFCILinks = semanticSelectedLink.getLinks();

      if (isTargetFESelectedInDialog && !associatedFCILinks.get(associatedFCILinks.size() - 1).getInvolved().getId()
          .equals(targetFunctionalExchange)) {
        Assert.fail("The selected SequenceLink is not associated with the selected FunctionalChainInvolvementLink!");
      } else if (!isTargetFEOnDiagram) {
        int currentNumberOfAssociatedLinks = associatedFCILinks.size();
        if (currentNumberOfAssociatedLinks - this.initialNumberOfAssociatedFCILForSelectedSeqLink != 1) {
          Assert.fail("No new FunctionalChainInvolvementLinks have been associated with the selected SequenceLink");
        }
      }

      this.createdFunctionalChainInvolvementLink = associatedFCILinks.get(associatedFCILinks.size() - 1).getId();
    }

    @Override
    protected void initToolArguments() {
      _toolWrapper.setArgumentValue(ArgumentType.CONTAINER,
          getSessionContext().getSemanticElement(this.selectedSequenceLink));
      _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW,
          getDiagramContext().getView(this.selectedSequenceLink));
    }

    @Override
    public DEdge getResult() {
      return (DEdge) getDiagramContext().getView(createdFunctionalChainInvolvementLink);
    }
  }

  protected class CreateControlNodeTool extends CreateAbstractDNodeTool<DNode> {
    protected ControlNodeContainer containerType = ControlNodeContainer.DIAGRAM;
    protected ControlNodeKind kind = ControlNodeKind.OR;

    public CreateControlNodeTool(DiagramContext context, String toolName, String containerView, ControlNodeKind kind) {
      super(context, toolName, containerView, DNode.class);
      this.kind = kind;
    }

    public CreateControlNodeTool(DiagramContext context, String toolName, String targetView, String containerView,
        ControlNodeKind kind) {
      super(context, toolName, targetView, containerView, null, DNode.class);
      containerType = ControlNodeContainer.SEQUENCE_LINK;
      this.kind = kind;
    }

    protected Collection<DDiagramElement> getDiagramElements(DSemanticDecorator element) {
      return DiagramServices.getDiagramServices().getFlatOwnedDiagramElements(element);
    }

    @Override
    protected void preRunTest() {
      super.preRunTest();
      DDiagram diagram = getDiagram();
      DiagramHelper.refreshDiagram(diagram);
      DSemanticDecorator element = getContainerView();
      elements = getDiagramElements(element);
    }

    @Override
    protected void postRunTest() {
      newElements = getDiagramElements(getContainerView());
      newElements.removeAll(elements);

      ControlNode controlNode = null;
      for (DDiagramElement element : newElements) {
        if (element.getTarget() instanceof ControlNode) {
          controlNode = (ControlNode) (element.getTarget());

          if (containerType == ControlNodeContainer.SEQUENCE_LINK) {
            List<SequenceLink> listIn = SequenceLinkEndExt.getIncomingSequenceLinks(controlNode);
            List<SequenceLink> listOut = SequenceLinkEndExt.getOutgoingSequenceLinks(controlNode);

            assertTrue("ControlNode should have one IN link", listIn.size() == 1);
            assertTrue("ControlNode should have one OUT link", listOut.size() == 1);
          }
        }
      }
      assertTrue("A new ControlNode is expected to be created", controlNode != null);
    }
  }

  protected class CreateConstructControlNodeTool extends CreateControlNodeTool {
    public CreateConstructControlNodeTool(DiagramContext context, String toolName, String containerView,
        ControlNodeKind kind) {
      super(context, toolName, containerView, kind);
    }

    public CreateConstructControlNodeTool(DiagramContext context, String toolName, String targetView,
        String containerView, ControlNodeKind kind) {
      super(context, toolName, targetView, containerView, kind);
    }

    @Override
    protected void postRunTest() {
      newElements = getDiagramElements(getContainerView());
      newElements.removeAll(elements);

      Set<DDiagramElement> nodes = newElements.stream().filter(x -> x.getTarget() instanceof ControlNode)
          .collect((Collectors.toSet()));

      assertTrue("Two new ControlNode are expected to be created", nodes.size() == 2);

      Iterator<DDiagramElement> it = nodes.iterator();
      ControlNode controlNode1 = (ControlNode) it.next().getTarget();
      ControlNode controlNode2 = (ControlNode) it.next().getTarget();

      assertTrue("ControlNode should be of kind " + kind, controlNode1.getKind() == kind);
      assertTrue("ControlNode should be of kind " + kind, controlNode2.getKind() == kind);

      // we don't know which one is the start of the construct and which one is the end
      List<SequenceLink> listcn1In = SequenceLinkEndExt.getIncomingSequenceLinks(controlNode1);
      List<SequenceLink> listcn1Out = SequenceLinkEndExt.getOutgoingSequenceLinks(controlNode1);
      List<SequenceLink> listcn2In = SequenceLinkEndExt.getIncomingSequenceLinks(controlNode2);
      List<SequenceLink> listcn2Out = SequenceLinkEndExt.getOutgoingSequenceLinks(controlNode2);

      String messagePattern = "Invalid ControlNode {4} contruct created, inserted ControlNode1: (count IN, {0}), (count OUT, {1}); ControlNode2: (count IN, {2}), (count OUT, {3}).";
      String message = NLS.bind(messagePattern,
          new Object[] { listcn1In.size(), listcn1Out.size(), listcn2In.size(), listcn2Out.size(), kind.toString() });

      boolean checkHasOneIN = false;
      // if listcn1In < 2 and listcn1Out = 2, controlNode1 is the start of the construct
      if (kind == ControlNodeKind.ITERATE) {
        assertTrue(message,
            listcn1In.size() >= 1 && listcn1Out.size() >= 1 && listcn2In.size() >= 1 && listcn2Out.size() >= 1);
      } else {
        if (listcn1In.size() < 2 && listcn1Out.size() == 2) {
          // check if listcn2In = 2
          assertTrue(message, listcn2In.size() == 2);
          checkHasOneIN = true;
        } else if (listcn2In.size() < 2 && listcn2Out.size() == 2) {
          // check if listcn2In = 2
          assertTrue(message, listcn1In.size() == 2);
        } else {
          assertTrue(message, false);
        }

        if (containerType == ControlNodeContainer.SEQUENCE_LINK) {
          if (checkHasOneIN) {
            assertTrue("Invalid start ControlNode " + kind + " created, IN link missing", listcn1In.size() == 1);
            assertTrue("Invalid start ControlNode created, IN link missing", listcn2Out.size() == 1);
          } else {
            assertTrue("Invalid end ControlNode" + kind + " created, IN link missing", listcn1Out.size() == 1);
            assertTrue("Invalid end ControlNode" + kind + " created, IN link missing", listcn2In.size() == 1);
          }
        }
      }
    }
  }

  protected class CreateSequenceLinkTool extends CreateDEdgeTool {
    public CreateSequenceLinkTool(DiagramContext context, String toolName, String sourceViewId, String targetViewId) {
      super(context, toolName, sourceViewId, targetViewId, null, null, null);
    }

    @Override
    protected void postRunTest() {
      super.postRunTest();
      Set<DDiagramElement> newLinks = _newEdgesElements.stream().filter(x -> {
        if (x.getTarget() instanceof SequenceLink) {
          SequenceLink link = (SequenceLink) x.getTarget();
          return (link.getSource().getId().equals(_sourceView) && link.getTarget().getId().equals(_targetView));
        }
        return false;
      }).collect(Collectors.toSet());
      assertTrue("A new edge is expected to be created", !newLinks.isEmpty());
    }
  }

  protected class CreateFunctionOnSequenceLinkTool extends CreateAbstractDNodeWithSelectionTool<DNode> {
    public CreateFunctionOnSequenceLinkTool(DiagramContext context, String toolName, String functionId,
        String targetView, String containerView) {
      super(context, toolName, targetView, containerView, functionId, DNode.class, SequenceLink.class);
    }

    protected Collection<DDiagramElement> getDiagramElements(DSemanticDecorator element) {
      return DiagramServices.getDiagramServices().getFlatOwnedDiagramElements(element);
    }

    @Override
    protected void preRunTest() {
      super.preRunTest();
      DDiagram diagram = getDiagram();
      DiagramHelper.refreshDiagram(diagram);
      DSemanticDecorator element = getContainerView();
      elements = getDiagramElements(element);
    }

    @Override
    protected void postRunTest() {
      newElements = getDiagramElements(getContainerView());
      newElements.removeAll(elements);

      Set<DDiagramElement> newFunctions = newElements.stream().filter(x -> {
        if (x.getTarget() instanceof FunctionalChainInvolvementFunction) {
          return ((FunctionalChainInvolvementFunction) (x.getTarget())).getInvolved().getId().equals(selectedId);
        }
        return false;
      }).collect(Collectors.toSet());

      assertTrue("A new FCIFunction is expected to be created", !newFunctions.isEmpty());

      FunctionalChainInvolvementFunction function = (FunctionalChainInvolvementFunction) (newFunctions.iterator().next()
          .getTarget());

      List<SequenceLink> sequenceLinksSource = SequenceLinkEndExt.getIncomingSequenceLinks(function);

      assertTrue("Function should be referenced by incoming SequenceLinks", !sequenceLinksSource.isEmpty());

      Set<SequenceLink> sourceLinks = sequenceLinksSource.stream().filter(x -> x.getId().equals(containerViewTarget))
          .collect(Collectors.toSet());

      assertTrue("Function should be referenced by the given SequenceLink", !sourceLinks.isEmpty());

      List<SequenceLink> sequenceLinksTarget = SequenceLinkEndExt.getOutgoingSequenceLinks(function);

      assertTrue("Function should be referenced by outgoing SequenceLinks", !sequenceLinksTarget.isEmpty());
    }
  }

  protected class CreateExchangeWithSequenceLinkTool extends CreateDEdgeTool {
    protected String selectedId;

    public CreateExchangeWithSequenceLinkTool(DiagramContext context, String toolName, String sourceViewId,
        String targetViewId, String selectedId) {
      super(context, toolName, sourceViewId, targetViewId, null, null, null);
      this.selectedId = selectedId;
    }

    @Override
    protected void preRunTest() {
      super.preRunTest();
      HeadlessResultOpProvider.INSTANCE.setCurrentOp(new IHeadlessResult() {
        @Override
        public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
          return getSessionContext().getSemanticElements(selectedId);
        }
      });
    }

    @Override
    protected void postRunTest() {
      super.postRunTest();
      Set<DDiagramElement> newLinks = _newEdgesElements.stream().filter(x -> {
        if (x.getTarget() instanceof SequenceLink) {
          SequenceLink link = (SequenceLink) x.getTarget();
          return (link.getSource().getId().equals(_sourceView) && link.getTarget().getId().equals(_targetView));
        }
        return false;
      }).collect(Collectors.toSet());

      assertTrue("SequenceLink and association should have been created", newLinks.size() >= 2);

      Set<DDiagramElement> newFEs = _newEdgesElements.stream().filter(x -> {
        if (x.getTarget() instanceof FunctionalChainInvolvementLink) {
          FunctionalChainInvolvementLink fe = (FunctionalChainInvolvementLink) x.getTarget();
          return (fe.getInvolved().getId().equals(selectedId) && fe.getSource().getId().equals(_sourceView)
              && fe.getTarget().getId().equals(_targetView));
        }
        return false;
      }).collect(Collectors.toSet());

      assertTrue("FunctionalExchange should have been created", !newFEs.isEmpty());
    }
  }

  protected class AssociateSequenceLinkToExchangeTool extends CreateDEdgeTool {
    protected String sequenceLinkId;

    public AssociateSequenceLinkToExchangeTool(DiagramContext context, String toolName, String sourceViewId,
        String targetViewId, String sequenceLinkId) {
      super(context, toolName, sourceViewId, targetViewId);
      this.sequenceLinkId = sequenceLinkId;
    }

    @Override
    protected void postRunTest() {
      super.postRunTest();

      Set<DDiagramElement> newLinks = _newEdgesElements.stream().filter(x -> {
        if (x.getTarget() instanceof SequenceLink) {
          SequenceLink link = (SequenceLink) x.getTarget();
          return (link.getId().equals(sequenceLinkId));
        }
        return false;
      }).collect(Collectors.toSet());
      assertTrue("A new edge is expected to be created", !newLinks.isEmpty());

      SequenceLink sequenceLink = getSessionContext().getSemanticElement(sequenceLinkId);
      EList<FunctionalChainInvolvementLink> associatedLinks = sequenceLink.getLinks();
      assertTrue("SequenceLink does not have associated links", !associatedLinks.isEmpty());
    }
  }

  public String accelerateOnFunctionalChainInvolvementLink(String fcilID) {

    DEdge sequenceLink = new AccelerateSequenceLinkFromInvolvementLink(this,
        IToolNameConstants.TOOL_SEQUENCE_LINK_FROM_EXCHANGE, fcilID).run();

    return getSemanticIdFromView(sequenceLink);
  }

  public String accelerateOnSequenceLinkWithSelect(String selectedSeqLink, String targetFCILinkID) {

    DEdge fciLink = new AccelerateInvolvementLinkFromSequenceLink(this,
        IToolNameConstants.TOOL_EXCHANGE_FROM_SEQUENCE_LINK, targetFCILinkID, selectedSeqLink).run();

    return getSemanticIdFromView(fciLink);
  }

  public String accelerateOnSequenceLinkWithCreate(String selectedSeqLink, String feSource, String feTarget) {

    DEdge fciLink = new AccelerateInvolvementLinkFromSequenceLink(this,
        IToolNameConstants.TOOL_EXCHANGE_FROM_SEQUENCE_LINK, selectedSeqLink, feSource, feTarget).run();

    return getSemanticIdFromView(fciLink);
  }

}
