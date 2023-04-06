/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.activity.Pin;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ExchangeItemExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.sirius.analysis.cache.DEdgeIconCache;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABComponent;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABComponentExchange;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideExchangeCategory;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideFunction;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideFunctionalExchange;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideInvisibleExchangeCategory;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * Services for Functional Analysis Elements
 */
public class FaServices {

  private static FaServices singleton = null;

  public static FaServices getFaServices() {
    if (singleton == null) {
      singleton = new FaServices();
    }
    return singleton;
  }

  private Object getSafeName(AbstractNamedElement fe) {
    if ("".equals(fe.getName()) || (null == fe.getName())) { //$NON-NLS-1$
      return "<undefined>"; //$NON-NLS-1$
    }
    return EObjectExt.getText(fe);
  }

  /**
   * @param context
   *          current context
   * @param source
   *          selected source of the edge
   * @return if a FunctionalExchange can be created from source
   */
  public boolean isValidCreationFunctionalExchange(EObject context, EObject source) {

    if ((source instanceof InputPin) || !(source instanceof ActivityNode)) {
      return false;
    }

    AbstractFunction sourceFunction = FunctionExt.getRelatedFunction((ActivityNode) source);

    if (FunctionExt.isControlNodeOneOutput(sourceFunction)) {
      if (!(FunctionExt.getOutGoingExchange(sourceFunction).isEmpty()
          || FunctionExt.getOutGoingExchange(sourceFunction).contains(context))) {
        return false;
      }
      if (!((source instanceof OutputPin) || sourceFunction.getOutputs().isEmpty())) {
        return false;
      }
    }

    return true;
  }

  /**
   * @param context
   *          current context
   * @param source
   *          selected source of the edge
   * @param target
   *          selected target of the edge
   * @return if a FunctionalExchange can be created between source and target
   */
  public boolean isValidCreationFunctionalExchange(EObject context, EObject source, EObject target) {

    if ((target instanceof OutputPin) || !(target instanceof ActivityNode)) {
      return false;
    }

    AbstractFunction sourceFunction = FunctionExt.getRelatedFunction((ActivityNode) source);
    AbstractFunction targetFunction = FunctionExt.getRelatedFunction((ActivityNode) target);
    if (sourceFunction.equals(targetFunction)) {
      return false;
    }
    if (EcoreUtil.isAncestor(sourceFunction, targetFunction)) {
      return false;
    }
    if (EcoreUtil.isAncestor(targetFunction, sourceFunction)) {
      return false;
    }

    if (FunctionExt.isControlNodeOneInput(targetFunction)) {
      if (!(FunctionExt.getIncomingExchange(targetFunction).isEmpty()
          || FunctionExt.getIncomingExchange(targetFunction).contains(context))) {
        return false;
      }
      if (!((target instanceof InputPin) || targetFunction.getInputs().isEmpty())) {
        return false;
      }
    }

    return isValidCreationFunctionalExchange(context, source);
  }

  /**
   * @param context
   * @return used in logical.odesign oa.odesign, physical.odesign called by show/hide FunctionalExchanges tools
   *         (DataFlow Blank Diagrams)
   */
  public List<FunctionalExchange> getAvailableFunctionalExchangesToInsert(DSemanticDecorator context) {

    List<FunctionalExchange> returnedList = new ArrayList<>();
    List<FunctionalExchange> allFunctionalExchanges = new ArrayList<>();
    AbstractFunction selectedFunction = null;

    if (!(context instanceof AbstractDNode)) {
      return returnedList;
    }

    if (context.getTarget() instanceof AbstractFunction) {
      selectedFunction = (AbstractFunction) context.getTarget();
      for (AbstractFunction currentFunction : getCache(FunctionExt::getAllAbstractFunctions, selectedFunction)) {
        allFunctionalExchanges.addAll(FunctionExt.getIncomingExchange(currentFunction));
        allFunctionalExchanges.addAll(FunctionExt.getOutGoingExchange(currentFunction));
      }
    }

    // if the following part is not commented => you must modify the
    // beforeRefresh for the dataFlow
    for (FunctionalExchange aFunctionalExchange : allFunctionalExchanges) {
      if ((aFunctionalExchange.getTarget() == null) || (aFunctionalExchange.getSource() == null)
          || (EcoreUtil.isAncestor(selectedFunction, aFunctionalExchange.getSource())
              && EcoreUtil.isAncestor(selectedFunction, aFunctionalExchange.getTarget()))) {
        continue;
      }
      returnedList.add(aFunctionalExchange);
    }
    return returnedList;
  }

  /**
   * @param selectedElement
   * @return displayed incoming and outgoing functional Exchanges called by show/hide FunctionalExchanges tools
   *         (DataFlow Blank Diagrams) used in oa, logical, context, physical
   */
  public Collection<FunctionalExchange> getDisplayedFunctionalExchanges(DSemanticDecorator selectedElement) {
    Collection<FunctionalExchange> result = new HashSet<>();
    // current DiagramElements
    if (selectedElement instanceof AbstractDNode) {
      result = getDisplayedFunctionalExchangesFromAbstractDNode((AbstractDNode) selectedElement);

      // Consider Sub Containers of current DiagramElement
      for (AbstractDNode dNodeContainer : DiagramServices.getDiagramServices().getAllNodeContainers(selectedElement)) {
        Collection<FunctionalExchange> subFunctionEdges = getDisplayedFunctionalExchangesFromAbstractDNode(
            dNodeContainer);
        if (!subFunctionEdges.isEmpty()) {
          result.addAll(subFunctionEdges);
        }
      }

      // Consider Sub Nodes of current DiagramElement
      List<DNode> allNodes = DiagramServices.getDiagramServices().getAllNodes(selectedElement);
      for (DNode aDNode : allNodes) {
        Collection<FunctionalExchange> subFunctionEdges = getDisplayedFunctionalExchangesFromAbstractDNode(aDNode);
        if (!subFunctionEdges.isEmpty()) {
          result.addAll(subFunctionEdges);
        }
      }
    }

    // This cast to ArrayList is necessary since this method is also used in
    // Transfer Wizard service which expects a
    // List as parameter
    return new ArrayList<>(result);
  }

  /**
   * get Functional Exchange edges from current AbstractNode
   * 
   * @param selectedElement
   * @return
   */
  public Collection<FunctionalExchange> getDisplayedFunctionalExchangesFromAbstractDNode(
      AbstractDNode selectedElement) {
    Collection<FunctionalExchange> returnedSet = new HashSet<FunctionalExchange>();
    Collection<DEdge> incomingOutgoingEdges = new HashSet<DEdge>();

    if (selectedElement.getTarget() instanceof AbstractFunction) {
      // consider the boarder nodes (inputpin, outputpin)
      for (DNode aNode : selectedElement.getOwnedBorderedNodes()) {
        if ((aNode.getTarget() instanceof InputPin)) {
          incomingOutgoingEdges.addAll(CapellaServices.getService().getIncomingEdges(aNode));
        }
        if ((aNode.getTarget() instanceof OutputPin)) {
          incomingOutgoingEdges.addAll(CapellaServices.getService().getOutgoingEdges(aNode));
        }
      }
      // consider operational activity
      if (selectedElement.getTarget() instanceof OperationalActivity) {
        incomingOutgoingEdges.addAll(CapellaServices.getService().getIncomingEdges((EdgeTarget) selectedElement));
        incomingOutgoingEdges.addAll(CapellaServices.getService().getOutgoingEdges((EdgeTarget) selectedElement));
      }
    }
    // filter functionalExchanges
    for (DEdge anEdge : incomingOutgoingEdges) {
      if (anEdge.getTarget() instanceof FunctionalExchange) {
        returnedSet.add((FunctionalExchange) anEdge.getTarget());
      }
    }
    return returnedSet;
  }

  public boolean isOriented(ComponentExchange connection) {
    return connection.isOriented();
  }

  @Deprecated
  public Collection<ComponentExchange> getDisplayedComponentExchanges(DNodeContainer selectedElement) {
    return Collections.emptySet();
  }

  @Deprecated
  public Collection<ComponentExchange> getAvailableComponentExchangesToInsert(DNodeContainer context) {
    return Collections.emptySet();
  }

  /**
   * used in oa, logical, context, physical
   * 
   * @param selectedElement
   * @return called by show/hide ComponentExchanges tools (Architecture Blank Diagrams)
   */
  public Collection<ComponentExchange> getDisplayedConnections(DNodeContainer selectedElement) {
    Collection<ComponentExchange> returnedList = new HashSet<>();
    List<DEdge> edges = new ArrayList<>();

    for (DNode aNode : selectedElement.getOwnedBorderedNodes()) {
      if (aNode.getTarget() instanceof ComponentPort) {
        edges.addAll(CapellaServices.getService().getIncomingEdges(aNode));
        edges.addAll(CapellaServices.getService().getOutgoingEdges(aNode));
      }
    }

    edges.addAll(CapellaServices.getService().getIncomingEdges(selectedElement));
    edges.addAll(CapellaServices.getService().getOutgoingEdges(selectedElement));

    for (DEdge anEdge : edges) {
      String edgeMappingName = anEdge.getMapping().getName();
      if (anEdge.getTarget() instanceof ComponentExchange
          && !(edgeMappingName.equals(IMappingNameConstants.PAB_COMPUTED_COMPONENT_EXCHANGE)
              || edgeMappingName.equals(IMappingNameConstants.LAB_COMPUTED_COMPONENT_EXCHANGE))) {
        if (DiagramServices.getDiagramServices().isVisible(anEdge)) {
          returnedList.add((ComponentExchange) anEdge.getTarget());
        }
      }
    }
    return returnedList;
  }

  /**
   * used in oa, logical, context, physical
   * 
   * @param context
   * @return called by show/hide ComponentExchanges tools (Architecture Blank Diagrams)
   */
  public Collection<ComponentExchange> getAvailableConnectionsToInsert(DNodeContainer context) {
    List<ComponentExchange> returnedList = new ArrayList<>();
    List<ComponentExchange> allComponentExchanges = new ArrayList<>();

    EObject currentComponent = null;
    Part currentPart = null;

    if (context.getTarget() instanceof Component) {
      currentComponent = context.getTarget();
    } else if (context.getTarget() instanceof Part) {
      currentPart = (Part) context.getTarget();
      currentComponent = CsServices.getService().getComponentType((Part) context.getTarget());
    }

    if (currentComponent instanceof Component) {

      if (currentComponent instanceof Component) {
        for (ComponentPort aPort : ComponentExt.getOwnedComponentPort((Component) currentComponent)) {
          allComponentExchanges.addAll(aPort.getComponentExchanges());
        }
      }

      if (context.getTarget() instanceof InformationsExchanger) {

        for (AbstractInformationFlow aFlow : ((InformationsExchanger) context.getTarget()).getInformationFlows()) {
          if (aFlow instanceof ComponentExchange) {
            allComponentExchanges.add((ComponentExchange) aFlow);
          }
        }
      }

    }

    for (ComponentExchange connection : allComponentExchanges) {
      // Add connection if is related to the current part if any
      if (!CsServices.getService().isMultipartMode(connection)
          || !((currentPart != null) && !FunctionalExt.getRelatedParts(connection).contains(currentPart))
          || (currentComponent != null && isBetweenTypes(connection, currentComponent))) {
        returnedList.add(connection);
      }
    }
    return returnedList;
  }

  private boolean isBetweenTypes(ComponentExchange connection, EObject currentComponent) {
    return ComponentExchangeExt.isConnectionBetweenTypes(connection)
        && (connection.getSourcePort().eContainer() == currentComponent
            || connection.getTargetPort().eContainer() == currentComponent);
  }

  /**
   * used in oa, logical, context, physical
   * 
   * @param context
   * @return called by show/hide FunctionalExchanges tools (Architecture Blank Diagrams)
   */
  public List<FunctionalExchange> getAvailableFunctionalExchangesToInsertInArchitectureBlank(AbstractDNode context) {
    List<FunctionalExchange> returnedList = new ArrayList<>();
    List<FunctionalExchange> allFunctionalExchanges = getAvailableFunctionalExchangesToInsert(context);
    AbstractFunction selectedFunction = (AbstractFunction) context.getTarget();
    for (FunctionalExchange anExchange : allFunctionalExchanges) {
      AbstractFunction targetFunction;
      if (anExchange.getTarget().equals(selectedFunction)
          || anExchange.getTarget().eContainer().equals(selectedFunction)) {
        targetFunction = FunctionExt.getRelatedFunction(anExchange.getSource());
      } else {
        targetFunction = FunctionExt.getRelatedFunction(anExchange.getTarget());
      }
      if (!targetFunction.getAllocationBlocks().isEmpty()) {
        returnedList.add(anExchange);
      } else {
        // it could be allocated by a role (which is not a block)
        EList<AbstractTrace> incomingTraces = targetFunction.getIncomingTraces();
        for (AbstractTrace abstractTrace : incomingTraces) {
          if (abstractTrace instanceof ActivityAllocation) {
            returnedList.add(anExchange);
            break;
          }
        }
      }
    }
    return returnedList;
  }

  private DNodeContainer getDisplayedFunctionContainer(EObject function, Collection<DNodeContainer> containers) {
    EObject currentFunction = function;
    while (currentFunction instanceof AbstractFunction) {
      for (DNodeContainer aContainer : containers) {
        if (aContainer.getTarget().equals(currentFunction)) {
          return aContainer;
        }
      }
      currentFunction = currentFunction.eContainer();
    }
    return null;
  }

  /**
   * @param semantic
   * @param dDiagram
   * @return
   */
  @Deprecated
  public DiagramElementMapping getMappingABRole(Role semantic, DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABRole(diagram);
    return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);

  }

  public ContainerMapping getMappingABComponent(EObject eObject, DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABComponent(eObject, diagram);
    return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
  }

  @Deprecated
  public AbstractNodeMapping getMappingFunction(AbstractFunction function, DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingFunction(diagram);
    return DiagramServices.getDiagramServices().getAbstractNodeMapping(diagram, mappingName);
  }

  @Deprecated
  public AbstractNodeMapping getMappingFunctionPort(FunctionPort port, DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingFunctionPort(diagram);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
  }

  @Deprecated
  public EdgeMapping getMappingFunctionalExchange(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingFunctionalExchange(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  @Deprecated
  public NodeMapping getMappingABAbstractFunction(AbstractFunction function, DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABAbstractFunction(diagram);
    return DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
  }

  @Deprecated
  public NodeMapping getMappingABFunctionPort(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABFunctionPort(diagram);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
  }

  @Deprecated
  public EdgeMapping getMappingABFunctionalExchange(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABFunctionalExchange(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  @Deprecated
  public EdgeMapping getMappingABComponentPortAllocation(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABComponentPortAllocation(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  @Deprecated
  public EdgeMapping getMappingABPortAllocation(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABPortAllocation(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  @Deprecated
  public EdgeMapping getMappingABConnection(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABConnection(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  @Deprecated
  public EdgeMapping getMappingABPhysicalLink(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABPhysicalLink(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  /**
   * used in an extension ...
   * 
   * @param chains
   * @param view
   */
  public void showInvolvedElementsInDataFlowBlank(List<FunctionalChain> functionalChains, DSemanticDecorator view) {

    // A chain have some involved elements. Try to find them or there
    // containers in the shortest delay

    Set<EObject> involvedElements = new HashSet<>();
    Set<AbstractFunction> involvedFunctions = new HashSet<>();
    Set<FunctionalExchange> involvedExchanges = new HashSet<>();

    // Find all involved elements of a functional chain, and separate them into involved functions and involved
    // exchanges.
    for (FunctionalChain chain : functionalChains) {
      involvedElements.add(chain);

      for (FunctionalChainInvolvement involvment : FunctionalChainExt.getFlatInvolvements(chain)) {
        if (involvment == null) {
          continue;
        }
        InvolvedElement involved = involvment.getInvolved();

        involvedElements.add(involved);

        if (involved instanceof AbstractFunction) {
          involvedFunctions.add((AbstractFunction) involved);

        } else if (involved instanceof FunctionalExchange) {
          FunctionalExchange exchange = (FunctionalExchange) involved;
          involvedExchanges.add(exchange);

          ActivityNode nodeSource = exchange.getSource();
          involvedElements.add(nodeSource);
          if ((nodeSource instanceof Port) && (nodeSource.eContainer() instanceof AbstractFunction)) {
            // in case where functional chain is not valid, add
            // function related to the port
            involvedElements.add(nodeSource.eContainer());
            involvedFunctions.add((AbstractFunction) nodeSource.eContainer());
          }

          ActivityNode nodeTarget = exchange.getTarget();
          involvedElements.add(nodeTarget);
          if ((nodeTarget instanceof Port) && (nodeTarget.eContainer() instanceof AbstractFunction)) {
            // in case where functional chain is not valid, add
            // function related to the port
            involvedElements.add(nodeTarget.eContainer());
            involvedFunctions.add((AbstractFunction) nodeTarget.eContainer());
          }
        }
      }
    }

    Map<EObject, DDiagramElement> elements = new HashMap<>();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);

    // Get the existing views of all involved elements
    for (EObject involvedElement : involvedElements) {
      DDiagramElement involvedView = DiagramServices.getDiagramServices().getDiagramElement(diagram, involvedElement);
      if (involvedView != null) {
        elements.put(involvedElement, involvedView);
      }
    }

    // Create views for involved elements that do not have a view (on the diagram root, since refresh
    // replace correctly theses elements
    for (AbstractFunction function : involvedFunctions) {
      if (!elements.containsKey(function)) {
        ContainerMapping mapping = getMappingDFFunction(function, diagram);
        elements.put(function,
            DiagramServices.getDiagramServices().createContainer(mapping, function, diagram, diagram));
      }
    }

    // Create exchanges and required ports
    for (FunctionalExchange exchange : involvedExchanges) {

      ActivityNode nodeSource = exchange.getSource();
      ActivityNode nodeTarget = exchange.getTarget();

      NodeMapping portMapping = getMappingDFFunctionPort(diagram);

      // Create source port
      if (!elements.containsKey(nodeSource)) {
        DDiagramElementContainer containerView = (DDiagramElementContainer) elements.get(nodeSource.eContainer());
        elements.put(nodeSource,
            DiagramServices.getDiagramServices().createBorderedNode(portMapping, nodeSource, containerView, diagram));
      }
      // Create target port
      if (!elements.containsKey(nodeTarget)) {
        DDiagramElementContainer containerView = (DDiagramElementContainer) elements.get(nodeTarget.eContainer());
        elements.put(nodeTarget,
            DiagramServices.getDiagramServices().createBorderedNode(portMapping, nodeTarget, containerView, diagram));
      }
      // Create exchange
      if (!elements.containsKey(exchange)) {
        EdgeMapping edgeMapping = getMappingDFFunctionalExchange(diagram);
        EdgeTarget sourceView = (EdgeTarget) elements.get(nodeSource);
        EdgeTarget targetView = (EdgeTarget) elements.get(nodeTarget);
        DiagramServices.getDiagramServices().createEdge(edgeMapping, sourceView, targetView, exchange);
      }
    }

    // Display all chains (create a end-node and its edge)
    NodeMapping endMapping = getMappingFunctionalChainEnd(diagram);
    for (FunctionalChain chain : functionalChains) {
      if (!elements.containsKey(chain)) {
        elements.put(chain, DiagramServices.getDiagramServices().createNode(endMapping, chain, diagram, diagram));
      }
    }

  }

  /**
   * @param diagram
   * @return
   */
  @Deprecated
  private NodeMapping getMappingFunctionalChainEnd(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingFunctionalChainEnd(diagram);
    return DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
  }

  /**
   * @param function
   * @param diagram
   * @return
   */
  @Deprecated
  public ContainerMapping getMappingDFFunction(AbstractFunction function, DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingDFFunction(diagram);
    return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
  }

  /**
   * @param diagram
   * @return
   */
  @Deprecated
  EdgeMapping getMappingDFFunctionalExchange(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingDFFunctionalExchange(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  @Deprecated
  private NodeMapping getMappingDFFunctionPort(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingDFFunctionPort(diagram);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
  }

  /**
   * used in context, oa, logical, physical
   * 
   * @param current
   * @return
   */
  public List<AbstractFunction> getAvailableAbstractFunctionsToInsertInDataFlowBlank(DSemanticDecorator current) {
    List<AbstractFunction> returnedFunctions = new ArrayList<>();
    AbstractFunction currentFunction = null;
    EObject target = current.getTarget();
    if (current instanceof DDiagram) {
      if (current.getTarget() instanceof AbstractFunction) {
        currentFunction = (AbstractFunction) current.getTarget();
      } else {
        currentFunction = getRootFunction(target);
      }
      returnedFunctions.addAll(CapellaServices.getService().getAvailableFunctionsInDataFlowBlank(currentFunction));
    }
    if ((null != target) && (current instanceof DNodeContainer) && (target instanceof AbstractFunction)) {
      currentFunction = (AbstractFunction) target;
      returnedFunctions.addAll(getCache(FunctionExt::getAllAbstractFunctions, currentFunction));
    }
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(current);
    for (AbstractDNode aContainer : currentDiagram.getContainers()) {
      if (aContainer.getTarget() instanceof AbstractFunction) {
        returnedFunctions.remove(aContainer.getTarget());
      }
    }

    // remove the rootfunction
    if (null != currentFunction) {
      AbstractFunction rootFunction = getRootFunction(currentFunction);
      if ((null != rootFunction) && returnedFunctions.contains(rootFunction)) {
        returnedFunctions.remove(rootFunction);
      }
    }

    return returnedFunctions;
  }

  /**
   * used in context, logical, physical
   * 
   * @param context
   * @param diagram
   * @param selectExchangeCategories
   *          exchanges categories to show (from wizard selection)
   * @return the context
   */
  public EObject switchFunctionalExchangesCategories(EObject context, List<ExchangeCategory> selectedExchangeCategories,
      DDiagram diagram) {
    Set<DEdge> exchangeCategoryEdgesToRemove = new HashSet<>(); // exchange
    // category
    // edges
    // to
    // remove
    Set<DEdge> functionalExchangeEdgesToRemove = new HashSet<>(); // functional
    // exchange
    // edges
    // to
    // remove
    Set<EdgeTarget> borderedNodesToCheck = new HashSet<>(); // borderedNodes
    // of
    // edges
    // to
    // remove
    Set<ExchangeCategory> categoryToDisplay = new HashSet<>(); // categories
    // to
    // display
    Set<ExchangeCategory> categoryToHide = new HashSet<>(); // categories
    // to
    // hide
    Set<ExchangeCategory> displayedCategories = getDisplayedExchangeCategoriesInDiagram(diagram);
    Map<FunctionalExchange, DEdge> functionalExchangesInDiagram = new HashMap<>(); // functional
    // exchanges
    // in
    // diagram

    // init categoryToDisplay
    for (ExchangeCategory aCategory : selectedExchangeCategories) {
      categoryToDisplay.add(aCategory);
    }
    // init categoryToHide
    for (ExchangeCategory aCategory : displayedCategories) {
      if (!selectedExchangeCategories.contains(aCategory)) {
        categoryToHide.add(aCategory);
      }
    }

    // init edges category to remove
    for (DEdge anEdge : diagram.getEdges()) {
      if ((anEdge.getTarget() instanceof ExchangeCategory) && (categoryToHide.contains(anEdge.getTarget()))) {
        exchangeCategoryEdgesToRemove.add(anEdge);
        borderedNodesToCheck.add(anEdge.getSourceNode());
        borderedNodesToCheck.add(anEdge.getTargetNode());
      }
      if (anEdge.getTarget() instanceof FunctionalExchange) {
        FunctionalExchange currentFunctionalExchange = (FunctionalExchange) anEdge.getTarget();
        functionalExchangesInDiagram.put(currentFunctionalExchange, anEdge);

        // we can not display categories for functional Exchanges that
        // do not rely bordered nodes
        if ((anEdge.getSourceNode() instanceof DNode) && (anEdge.getTargetNode() instanceof DNode)
            && DiagramServices.getDiagramServices().isABorderedNode((DNode) anEdge.getSourceNode())
            && DiagramServices.getDiagramServices().isABorderedNode((DNode) anEdge.getTargetNode())) {
          for (ExchangeCategory aCategory : currentFunctionalExchange.getCategories()) {
            if (categoryToDisplay.contains(aCategory)) {
              functionalExchangeEdgesToRemove.add(anEdge);
              borderedNodesToCheck.add(anEdge.getSourceNode());
              borderedNodesToCheck.add(anEdge.getTargetNode());
              break;
            }
          }
        }
      }
    }

    // avoid double creation of functional exchanges
    Set<FunctionalExchange> alreadyCreated = new HashSet<>();

    // create or show functional exchange views and remove exchange category
    // views
    for (DEdge anEdge : exchangeCategoryEdgesToRemove) {
      AbstractDNode sourceFunctionView = (AbstractDNode) anEdge.getSourceNode().eContainer();
      AbstractDNode targetFunctionView = (AbstractDNode) anEdge.getTargetNode().eContainer();
      AbstractFunction sourceFunction = (AbstractFunction) sourceFunctionView.getTarget();
      AbstractFunction targetFunction = (AbstractFunction) targetFunctionView.getTarget();
      List<FunctionalExchange> functionalExchangesOfCategory = ((ExchangeCategory) anEdge.getTarget()).getExchanges();
      Set<FunctionalExchange> exchangesToDisplay = getAvailableFunctionalExchangesBetweeen2Functions(
          functionalExchangesOfCategory, sourceFunction, targetFunction);
      for (FunctionalExchange anExchange : exchangesToDisplay) {

        boolean toRemove = false;
        for (ExchangeCategory aCategory : anExchange.getCategories()) {
          if (categoryToDisplay.contains(aCategory)) {
            toRemove = true;
          }
        }
        if (toRemove) {
          continue;
        }

        if (!alreadyCreated.contains(anExchange)) {
          if (functionalExchangesInDiagram.containsKey(anExchange)) {
            DEdge currentExchangeView = functionalExchangesInDiagram.get(anExchange);
            CapellaServices.getService().show((DDiagramElement) currentExchangeView.getSourceNode());
            CapellaServices.getService().show((DDiagramElement) currentExchangeView.getTargetNode());
            CapellaServices.getService().show(currentExchangeView);
          } else {
            createViewFunctionalExchange(anExchange, sourceFunctionView, targetFunctionView, diagram);
            alreadyCreated.add(anExchange);
          }
        }
      }
      DiagramServices.getDiagramServices().removeEdgeView(anEdge);
    }

    // create exchange category views and remove functional exchange views
    for (DEdge anEdge : functionalExchangeEdgesToRemove) {
      AbstractDNode sourceFunctionView = (AbstractDNode) anEdge.getSourceNode().eContainer();
      AbstractDNode targetFunctionView = (AbstractDNode) anEdge.getTargetNode().eContainer();
      FunctionalExchange currentExchange = (FunctionalExchange) anEdge.getTarget();
      for (ExchangeCategory aCategory : currentExchange.getCategories()) {
        if (categoryToDisplay.contains(aCategory)) {
          createViewExchangeCategory(aCategory, sourceFunctionView, targetFunctionView, diagram);
        }
      }
      if (!diagram.isSynchronized()) {
        DiagramServices.getDiagramServices().removeEdgeView(anEdge);
      } else {
        CapellaServices.getService().hide(anEdge);
      }
    }

    Map<AbstractFunction, AbstractDNode> allFunctionsInDiagram = new HashMap<>();

    // get all displayed functions in the diagram
    for (DDiagramElement aContainer : DiagramServices.getDiagramServices().getDiagramElements(diagram)) {
      if ((aContainer != null) && (aContainer.getTarget() != null) && (aContainer instanceof AbstractDNode)
          && FaServices.getFaServices().isAbstractFunctionVisibleInDFB((AbstractDNode) aContainer, diagram)) {
        if (aContainer.getTarget() instanceof AbstractFunction) {
          allFunctionsInDiagram.put((AbstractFunction) aContainer.getTarget(), (AbstractDNode) aContainer);
        }
      }
    }

    // display category for invisible exchanges
    for (ExchangeCategory currentCategory : categoryToDisplay) {
      for (FunctionalExchange currentExchange : currentCategory.getExchanges()) {
        AbstractDNode sourceFunctionView = getBestFunctionContainer(currentExchange.getSource(), allFunctionsInDiagram);
        AbstractDNode targetFunctionView = getBestFunctionContainer(currentExchange.getTarget(), allFunctionsInDiagram);

        if (!(functionalExchangesInDiagram.containsKey(currentExchange))) {
          if (isValidCreationCategoryBetweenViews(currentExchange, sourceFunctionView, targetFunctionView)) {
            for (ExchangeCategory aCategory : currentExchange.getCategories()) {
              if (categoryToDisplay.contains(aCategory)) {
                createViewExchangeCategory(aCategory, sourceFunctionView, targetFunctionView, diagram);
              }
            }
          }
        }
      }
    }

    for (EdgeTarget aNode : borderedNodesToCheck) {
      boolean incoming = DiagramServices.getDiagramServices().getIncomingEdges(aNode, diagram).isEmpty();
      boolean outgoing = DiagramServices.getDiagramServices().getOutgoingEdges(aNode, diagram).isEmpty();

      if (!incoming) {
        incoming = true;
        for (DEdge edge : DiagramServices.getDiagramServices().getIncomingEdges(aNode, diagram)) {
          incoming = (incoming)
              ? (exchangeCategoryEdgesToRemove.contains(edge) || functionalExchangeEdgesToRemove.contains(edge))
              : false;
        }
      }
      if (!outgoing) {
        outgoing = true;
        for (DEdge edge : DiagramServices.getDiagramServices().getOutgoingEdges(aNode, diagram)) {
          outgoing = (outgoing)
              ? (exchangeCategoryEdgesToRemove.contains(edge) || functionalExchangeEdgesToRemove.contains(edge))
              : false;
        }
      }

      if (incoming && outgoing) {
        if ((((DNode) aNode).getTarget() instanceof ExchangeCategory) || !diagram.isSynchronized()) {
          DiagramServices.getDiagramServices().removeNodeView((DNode) aNode);
        } else {
          if (aNode.eContainer() != null) {
            CapellaServices.getService().hide((DNode) aNode);
          }
        }
      }
    }

    return context;
  }

  /**
   * Returns whether an exchange category can be displayed between both views
   * 
   * @param sourceFunctionView
   * @param targetFunctionView
   * @return
   */
  public boolean isValidCreationCategoryBetweenViews(FunctionalExchange exchange, AbstractDNode sourceFunctionView,
      AbstractDNode targetFunctionView) {
    if ((sourceFunctionView != null) && (targetFunctionView != null)) {
      if ((sourceFunctionView.getTarget() != null) && (targetFunctionView.getTarget() != null)) {
        if (sourceFunctionView != targetFunctionView) {
          // remove displaying categories from a function to one of
          // its parents
          if (!EcoreUtil2.isContainedBy(sourceFunctionView.getTarget(), targetFunctionView.getTarget())) {
            if (!EcoreUtil2.isContainedBy(targetFunctionView.getTarget(), sourceFunctionView.getTarget())) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * @param aCategory
   * @param sourceFunctionView
   * @param targetFunctionView
   */
  private void createViewExchangeCategory(ExchangeCategory aCategory, AbstractDNode sourceFunctionView,
      AbstractDNode targetFunctionView, DDiagram diagram) {
    DNode sourceView = null;
    DNode targetView = null;

    String outputMappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(diagram);
    String inputMappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryInputPin(diagram);

    // check if the source Function already contains the port Category
    for (DNode aNode : sourceFunctionView.getOwnedBorderedNodes()) {
      if (aNode.getTarget().equals(aCategory) && aNode.getActualMapping().getName().equals(outputMappingName)) {
        sourceView = aNode;
      }
    }

    // check if the target Function already contains the port Category
    for (DNode aNode : targetFunctionView.getOwnedBorderedNodes()) {
      if (aNode.getTarget().equals(aCategory) && aNode.getActualMapping().getName().equals(inputMappingName)) {
        targetView = aNode;
      }
    }

    // test if an edge representing the category already exists between the
    // 2 functions
    if ((null != sourceView) && (null != targetView)) {
      for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(sourceView, diagram)) {
        if (anEdge.getTargetNode().equals(targetView)) {
          // the edge already exists => show the edge
          CapellaServices.getService().show((DDiagramElement) anEdge.getSourceNode());
          CapellaServices.getService().show((DDiagramElement) anEdge.getTargetNode());
          CapellaServices.getService().show(anEdge);
          return;
        }
      }
    }

    if (null == sourceView) {
      // create source Port View
      sourceView = createViewOutputPinCategory(aCategory, sourceFunctionView, diagram);
    }
    if (null == targetView) {
      // create target Port View
      targetView = createViewInputPinCategory(aCategory, targetFunctionView, diagram);
    }

    EdgeMapping mapping = getMappingExchangeCategory(diagram);
    // create Edge
    DiagramServices.getDiagramServices().createEdge(mapping, sourceView, targetView, aCategory);
  }

  /**
   * @param anExchange
   * @param sourceFunctionView
   * @param targetFunctionView
   */
  public DEdge createViewFunctionalExchange(FunctionalExchange anExchange, AbstractDNode sourceFunctionView,
      AbstractDNode targetFunctionView, DDiagram diagram) {
    ActivityNode source = anExchange.getSource();
    ActivityNode target = anExchange.getTarget();
    EdgeTarget sourceView = null;
    EdgeTarget targetView = null;

    // check if the sourceView already exists

    if (source.equals(sourceFunctionView.getTarget()) && (sourceFunctionView instanceof EdgeTarget)) {
      sourceView = (EdgeTarget) sourceFunctionView;
    }
    if (null == sourceView) {
      for (DNode aBorderedNode : sourceFunctionView.getOwnedBorderedNodes()) {
        if ((aBorderedNode.getTarget() != null) && aBorderedNode.getTarget().equals(source)) {
          sourceView = aBorderedNode;
        }
      }
    }

    // if it does not exist, create view
    if (null == sourceView) {
      sourceView = createViewPin(source, sourceFunctionView, diagram);
    }

    if (target.equals(targetFunctionView.getTarget()) && (targetFunctionView instanceof EdgeTarget)) {
      targetView = (EdgeTarget) targetFunctionView;
    }

    // check if the targetView already exists

    if (null == targetView) {
      for (DNode aBorderedNode : targetFunctionView.getOwnedBorderedNodes()) {
        if ((aBorderedNode.getTarget() != null) && aBorderedNode.getTarget().equals(target)) {
          targetView = aBorderedNode;
        }
      }
    }

    // if it does not exist, create view
    if (null == targetView) {
      targetView = createViewPin(target, targetFunctionView, diagram);
    }

    // create functionalExchangeView
    EdgeMapping mapping = getMappingDFFunctionalExchange(diagram);
    if (mapping == null) {
      mapping = getMappingABFunctionalExchange(diagram);
    }
    CapellaServices.getService().show((DDiagramElement) sourceView);
    CapellaServices.getService().show((DDiagramElement) targetView);
    return DiagramServices.getDiagramServices().createEdge(mapping, sourceView, targetView, anExchange);

  }

  private DNode createViewPin(ActivityNode modelElement, AbstractDNode containerView, DDiagram diagram) {
    NodeMapping mapping = getMappingDFFunctionPort(diagram);
    if (mapping == null) {
      mapping = getMappingABFunctionPort(diagram);
    }
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, modelElement,
        (DragAndDropTarget) containerView, diagram);
  }

  private DNode createViewInputPinCategory(ExchangeCategory category, AbstractDNode containerView, DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryInputPin(diagram);
    NodeMapping mapping = DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, category, (DragAndDropTarget) containerView,
        diagram);
  }

  @Deprecated
  String getMappingNameInputPinCategory(DDiagram diagram) {
    return MappingConstantsHelper.getMappingFunctionalExchangeCategoryInputPin(diagram);
  }

  /**
   * @param context
   * @param aOperation
   * @param diagram
   */

  @Deprecated
  public AbstractFunction createForkFunction(EObject container) {
    return createDuplicateFunction(container);
  }

  public boolean isControlNode(EObject element) {
    return (element instanceof AbstractFunction) && FunctionExt.isControlNode((AbstractFunction) element);
  }

  public boolean isDuplicateFunction(EObject element) {
    return (element instanceof AbstractFunction) && FunctionExt.isDuplicateFunction((AbstractFunction) element);
  }

  public String computeFunctionLabel(DSemanticDecorator decorator) {
    if (decorator instanceof DDiagramElement) {
      EObject target = decorator.getTarget();
      if (target instanceof FunctionalChainInvolvement) {
        target = ((FunctionalChainInvolvement) target).getInvolved();
      }
      if (isControlNode(target)) {
        return ICommonConstants.EMPTY_STRING;
      }
      return EObjectExt.getText(target);
    }
    return ICommonConstants.EMPTY_STRING;
  }

  public boolean isGatherFunction(EObject element) {
    return (element instanceof AbstractFunction) && FunctionExt.isGatherFunction((AbstractFunction) element);
  }

  public boolean isRouteFunction(EObject element) {
    return (element instanceof AbstractFunction) && FunctionExt.isRouteFunction((AbstractFunction) element);
  }

  public boolean isSelectFunction(EObject element) {
    return (element instanceof AbstractFunction) && FunctionExt.isSelectFunction((AbstractFunction) element);
  }

  public boolean isSplitFunction(EObject element) {
    return (element instanceof AbstractFunction) && FunctionExt.isSplitFunction((AbstractFunction) element);
  }

  public AbstractFunction createDuplicateFunction(EObject container) {
    return createFunction(container, FunctionKind.DUPLICATE);
  }

  public AbstractFunction createGatherFunction(EObject container) {
    return createFunction(container, FunctionKind.GATHER);
  }

  public AbstractFunction createRouteFunction(EObject container) {
    return createFunction(container, FunctionKind.ROUTE);
  }

  public AbstractFunction createSelectFunction(EObject container) {
    return createFunction(container, FunctionKind.SELECT);
  }

  public AbstractFunction createSplitFunction(EObject container) {
    return createFunction(container, FunctionKind.SPLIT);
  }

  public AbstractFunction getParentFunctionContainer(EObject container) {
    return getFunctionContainer(container.eContainer());
  }

  public AbstractFunction getFunctionContainer(EObject container) {
    AbstractFunction owner = null;
    if ((container instanceof AbstractFunction)) {
      owner = (AbstractFunction) container;
    } else {
      owner = (AbstractFunction) EcoreUtil2.getFirstContainer(container, FaPackage.Literals.ABSTRACT_FUNCTION);
    }

    if (owner == null) {
      owner = BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(container));
    }
    return owner;
  }

  public AbstractFunction allocateToComponent(AbstractFunction function, Component component) {
    ComponentFunctionalAllocation allocation = FaFactory.eINSTANCE.createComponentFunctionalAllocation();
    if (component != null) {
      allocation.setSourceElement(component);
      allocation.setTargetElement(function);
      component.getOwnedFunctionalAllocation().add(allocation);
    }
    return function;
  }

  public AbstractFunction allocateToRole(AbstractFunction function, Role component) {
    ActivityAllocation allocation = OaFactory.eINSTANCE.createActivityAllocation();
    if (component != null) {
      allocation.setSourceElement(component);
      allocation.setTargetElement(function);
      component.getOwnedActivityAllocations().add(allocation);
    }
    return function;
  }

  public AbstractFunction allocateToCapability(AbstractFunction function, DSemanticDecorator containerView) {
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(containerView);
    if ((diagram != null) && (diagram.getTarget() != null) && (diagram.getTarget() instanceof AbstractCapability)) {
      AbstractCapability capability = (AbstractCapability) diagram.getTarget();
      createAbstractFunctionAbstractCapabilityInvolvement(capability, function);
    }
    return function;
  }

  public AbstractFunction allocateToNewActor(AbstractFunction function) {
    Component actor = CsServices.getService().createActor(BlockArchitectureExt.getRootBlockArchitecture(function), true,
        null);
    allocateToComponent(function, actor);
    return function;
  }

  public AbstractFunction createActorFunction(EObject container) {
    return createFunction(container, "A ", FunctionKind.FUNCTION); //$NON-NLS-1$
  }

  /**
   * Returns functions owned by the function or owned function pkg
   * 
   * @return
   */
  public Collection<AbstractFunction> getFirstLevelAbstractFunctions(AbstractFunction function) {
    return getCache(FunctionExt::getFirstLevelAbstractFunctions, function);
  }

  /**
   * Returns functions owned by the function or owned function pkg
   * 
   * @return
   */
  public Collection<AbstractFunction> getFirstLevelAbstractFunctions(FunctionPkg container) {
    return FunctionPkgExt.getFirstLevelAbstractFunctions(container);
  }

  public static boolean isControlNodeOneOutput(AbstractFunction function) {
    return FunctionExt.isControlNodeOneOutput(function);
  }

  public static boolean isControlNodeOneInput(AbstractFunction function) {
    return FunctionExt.isControlNodeOneInput(function);
  }

  public boolean isValidCreationFunctionInputPort(DSemanticDecorator decorator) {
    EObject target = decorator.getTarget();
    AbstractFunction function = FunctionExt.getRelatedFunction((ActivityNode) target);

    if (FunctionExt.isControlNodeOneInput(function)) {
      return function.getInputs().isEmpty();
    }

    return true;
  }

  public boolean isValidCreationFunctionOutputPort(DSemanticDecorator decorator) {
    EObject target = decorator.getTarget();
    AbstractFunction function = FunctionExt.getRelatedFunction((ActivityNode) target);

    if (FunctionExt.isControlNodeOneOutput(function)) {
      return function.getOutputs().isEmpty();
    }

    return true;
  }

  public AbstractFunction createFunction(EObject container, String prefix, FunctionKind kind) {
    AbstractFunction function = null;
    AbstractFunction owner = getFunctionContainer(container);

    if (owner != null) {
      if (owner instanceof OperationalActivity) {
        function = OaFactory.eINSTANCE.createOperationalActivity();

      } else if (owner instanceof SystemFunction) {
        function = CtxFactory.eINSTANCE.createSystemFunction();

      } else if (owner instanceof LogicalFunction) {
        function = LaFactory.eINSTANCE.createLogicalFunction();

      } else if (owner instanceof PhysicalFunction) {
        function = PaFactory.eINSTANCE.createPhysicalFunction();
      }

      if (function != null) {
        owner.getOwnedFunctions().add(function);
        function.setKind(kind);
        if (prefix != null) {
          CapellaServices.getService().creationService(function, prefix);
        } else {
          CapellaServices.getService().creationService(function);
        }
      }
    }
    return function;
  }

  /**
   * @param context
   * @param aOperation
   * @param diagram
   */
  public AbstractFunction createFunction(EObject container) {
    return createFunction(container, null, FunctionKind.FUNCTION);
  }

  private AbstractFunction createFunction(EObject container, FunctionKind kind) {
    return createFunction(container, null, kind);
  }

  private DNode createViewOutputPinCategory(ExchangeCategory category, AbstractDNode containerView, DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(diagram);
    NodeMapping mapping = DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, category, (DragAndDropTarget) containerView,
        diagram);
  }

  @Deprecated
  String getMappingNameOutputPinCategory(DDiagram diagram) {
    return MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(diagram);
  }

  @Deprecated
  private EdgeMapping getMappingExchangeCategory(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategory(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  private Set<FunctionalExchange> getAvailableFunctionalExchangesBetweeen2Functions(List<FunctionalExchange> exchanges,
      AbstractFunction sourceFunction, AbstractFunction targetFunction) {
    Set<FunctionalExchange> returnedSet = new HashSet<>();
    for (FunctionalExchange anExchange : exchanges) {
      if (EcoreUtil.isAncestor(sourceFunction, anExchange.getSource())
          && EcoreUtil.isAncestor(targetFunction, anExchange.getTarget())) {
        returnedSet.add(anExchange);
      }
    }
    return returnedSet;
  }

  private Set<ExchangeCategory> getVisibleExchangeCategoriesInDiagram(DDiagram diagram) {
    Set<ExchangeCategory> returnedList = new HashSet<>();
    for (DEdge anEdge : diagram.getEdges()) {
      if ((anEdge.getTarget() instanceof ExchangeCategory) && (!returnedList.contains(anEdge.getTarget()))
          && (anEdge.isVisible())) {
        returnedList.add((ExchangeCategory) anEdge.getTarget());
      }
    }
    return returnedList;
  }

  private Set<ExchangeCategory> getDisplayedExchangeCategoriesInDiagram(DDiagram diagram) {
    Set<ExchangeCategory> returnedList = new HashSet<>();
    for (DEdge anEdge : diagram.getEdges()) {
      if ((anEdge.getTarget() instanceof ExchangeCategory) && (!returnedList.contains(anEdge.getTarget()))) {
        returnedList.add((ExchangeCategory) anEdge.getTarget());
      }
    }
    return returnedList;
  }

  /**
   * used in context, logical, physical
   * 
   * @param o
   * @param source
   * @param target
   * @return
   */
  public boolean isACategoryDisplayed(EObject o, DSemanticDecorator source, DSemanticDecorator target) {
    if (((source instanceof DNode) && !(DiagramServices.getDiagramServices().isABorderedNode((DNode) source)))) {
      return false;
    }
    if (((target instanceof DNode) && !(DiagramServices.getDiagramServices().isABorderedNode((DNode) target)))) {
      return false;
    }

    AbstractDNode sc = (AbstractDNode) source;
    AbstractDNode tc = (AbstractDNode) target;
    if (!(source.getTarget() instanceof AbstractFunction)) {
      sc = (AbstractDNode) source.eContainer();
    }
    if (!(target.getTarget() instanceof AbstractFunction)) {
      tc = (AbstractDNode) target.eContainer();
    }

    FunctionalExchange exchange = null;
    if (o instanceof FunctionalExchange) {
      exchange = (FunctionalExchange) o;
    }

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(source);

    if (exchange == null) {
      return false;
    }
    for (DNode node : sc.getOwnedBorderedNodes()) {
      if (node.getTarget() instanceof ExchangeCategory) {
        if (node.getActualMapping().getName()
            .equals(MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(diagram))) {
          for (DEdge edge : node.getOutgoingEdges()) {

            if (edge.isVisible() && (edge.getTarget() instanceof ExchangeCategory)) {
              if (exchange.getCategories().contains(edge.getTarget())) {
                EdgeTarget targetNode = edge.getTargetNode();
                if (targetNode != null) {
                  if (!((targetNode instanceof DSemanticDecorator)
                      && (((DSemanticDecorator) targetNode).getTarget() instanceof AbstractFunction))) {
                    if (targetNode.eContainer() instanceof EdgeTarget) {
                      targetNode = (EdgeTarget) targetNode.eContainer();
                    }
                  }
                  if ((targetNode != null) && targetNode.equals(tc)) {
                    return true;
                  }
                }
              }
            }
          }
        }

      }
    }
    return false;
  }

  List<DNodeContainer> getOwnedVisibleFunctionContainersInDataFlowBlank(DNodeContainer container, DDiagram diagram) {
    List<DNodeContainer> returnedList = new ArrayList<>();
    Set<AbstractDNode> containers = new HashSet<>();
    if (null == container) {
      containers.addAll(diagram.getContainers());
    } else {
      containers.addAll(container.getContainers());
    }
    for (AbstractDNode aSubContainer : containers) {
      if ((aSubContainer instanceof DNodeContainer) && (aSubContainer.getTarget() != null)
          && (aSubContainer.getTarget() instanceof AbstractFunction)
          && isAbstractFunctionVisibleInDFB(aSubContainer, diagram)) {
        returnedList.add((DNodeContainer) aSubContainer);
      }
    }
    return returnedList;
  }

  boolean isAbstractFunctionVisibleInDFB(AbstractDNode abstractFunction, DDiagram diagram) {
    return CapellaServices.getService().isVisibleInDiagram(diagram, abstractFunction);
  }

  /**
   * used in context, logical, oa, physical
   * 
   * @param view
   *          a nodeContainer or a diagram
   * @return the list of containers/nodes (whose target is a Function/ControlNode) displayed in the current view
   */
  public List<AbstractDNode> getDisplayedAbstractFunctionViews(DSemanticDecorator view) {
    List<AbstractDNode> returnedList = new ArrayList<>();
    if (view instanceof DDiagram) {
      DDiagram diagram = (DDiagram) view;
      for (AbstractDNode aContainer : diagram.getContainers()) {
        if (aContainer.getTarget() instanceof AbstractFunction) {
          returnedList.add(aContainer);
        }
      }
    }
    if (view instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) view;
      for (AbstractDNode aContainer : currentContainer.getContainers()) {
        if (aContainer.getTarget() instanceof AbstractFunction) {
          returnedList.add(aContainer);
        }
      }
    }
    return returnedList;
  }

  /**
   * used in context, logical, oa physical
   * 
   * @param view
   *          a nodeContainer or a diagram
   * @return the list of Functions/ControlNodes displayed in the current view
   */
  public List<AbstractFunction> getDisplayedAbstractFunctions(DSemanticDecorator view) {
    List<AbstractFunction> returnedList = new ArrayList<>();
    for (AbstractDNode aView : getDisplayedAbstractFunctionViews(view)) {
      returnedList.add((AbstractFunction) aView.getTarget());
    }
    return returnedList;
  }

  /**
   * used by external action
   * 
   * @param view
   * @param selectedFunctions
   * @param visibleFunctions
   * @param visibleFunctionViews
   * @return
   */
  public EObject showHideFunctionsInDataFlowBlank(DSemanticDecorator view, List<AbstractFunction> selectedFunctions,
      List<AbstractFunction> visibleFunctions, List<AbstractDNode> visibleFunctionViews) {

    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(view);
    DDiagramContents content = new DDiagramContents(diagram);

    Set<AbstractDNode> toBeRemoved = new HashSet<>();

    for (AbstractDNode aView : visibleFunctionViews) {
      if (!selectedFunctions.contains(aView.getTarget())) {
        toBeRemoved.add(aView);
      }
    }

    // move visible functions if the container has to be removed and it's not to be
    // removed
    for (AbstractDNode aView : visibleFunctionViews) {
      if (toBeRemoved.contains(aView.eContainer()) && !toBeRemoved.contains(aView)) {
        diagram.getOwnedDiagramElements().add(aView);
      }
    }

    // move borderedNodes Ports if possible
    for (AbstractDNode aView : toBeRemoved) {
      Set<DNode> borderedNodes = new HashSet<>();
      borderedNodes.addAll(aView.getOwnedBorderedNodes());
      for (DNode aBorderedNode : borderedNodes) {
        if ((aBorderedNode.getTarget() != null) && ((aBorderedNode.getTarget() instanceof FunctionPort)
            || (aBorderedNode.getTarget() instanceof ExchangeCategory))) {
          moveBorderedNodeIfPossible(aBorderedNode, toBeRemoved, diagram);
        }
      }
    }

    // case if the diagram is contained in an AbstractCapability
    if (!(diagram.getTarget() instanceof AbstractFunction)) {

      for (AbstractDNode aView : toBeRemoved) {
        removeAbstractFunctionAbstractCapabilityInvolvement((AbstractCapability) diagram.getTarget(),
            aView.getTarget());
      }

      for (AbstractFunction aFunction : selectedFunctions) {
        createAbstractFunctionAbstractCapabilityInvolvement((AbstractCapability) diagram.getTarget(), aFunction);
      }
    }

    AbstractShowHide shService = new ShowHideFunction(content);

    for (AbstractDNode element : toBeRemoved) {
      DiagramContext context = shService.new DiagramContext();
      shService.hide(element.getTarget(), context);
    }

    Map<AbstractFunction, AbstractDNode> showHideCategories = new HashMap<>();

    // add views
    for (AbstractFunction aFunction : selectedFunctions) {
      if (!visibleFunctions.contains(aFunction)) {

        DiagramContext context = shService.new DiagramContext();
        Collection<DSemanticDecorator> views = shService.showWithResult(aFunction, context);

        // retrieve newly created views
        for (DSemanticDecorator aView : views) {
          if (aFunction.equals(aView.getTarget()) && (aView instanceof AbstractDNode)) {
            showHideCategories.put(aFunction, (AbstractDNode) aView);
          }
        }
      }
    }

    // and create exchange category links for all newly created views
    Set<ExchangeCategory> categories = getVisibleExchangeCategoriesInDiagram(diagram);
    for (AbstractFunction aFunction : selectedFunctions) {
      if (!visibleFunctions.contains(aFunction)) {
        AbstractDNode node = showHideCategories.get(aFunction);
        if ((node != null) && CapellaServices.getService().isSynchronized(diagram)) {
          HashMapSet<ExchangeCategory, EObject> scope = (HashMapSet) getAvailableCategoriesAndFunctionsToInsertInDataFlowBlank(
              node, content);
          HashMapSet<EObject, EObject> selectedElements = new HashMapSet<EObject, EObject>((Map) scope);
          HashMapSet<EObject, EObject> initialSelection = (HashMapSet) FaServices.getFaServices()
              .getCategoriesAndFunctionsInitialSelectionInDataFlowBlank((DNodeContainer) node, content);

          for (ExchangeCategory category : new ArrayList<ExchangeCategory>(scope.keySet())) {
            if (!categories.contains(category)) {
              selectedElements.remove(category);
            }
          }

          showFECategories(node, (HashMapSet) scope, initialSelection, selectedElements);
        }
      }
    }

    return view;
  }

  public void removeAbstractFunctionAbstractCapabilityInvolvement(AbstractCapability capability, EObject target) {
    Set<AbstractFunctionAbstractCapabilityInvolvement> toRemove = new HashSet<>();
    for (AbstractFunctionAbstractCapabilityInvolvement inv : capability
        .getOwnedAbstractFunctionAbstractCapabilityInvolvements()) {
      if (inv.getInvolved().equals(target)) {
        toRemove.add(inv);
      }
    }
    for (AbstractFunctionAbstractCapabilityInvolvement involvement : toRemove) {
      involvement.destroy();
    }
  }

  public AbstractFunctionAbstractCapabilityInvolvement createAbstractFunctionAbstractCapabilityInvolvement(
      AbstractCapability capability, AbstractFunction target) {
    for (AbstractFunctionAbstractCapabilityInvolvement inv : capability
        .getOwnedAbstractFunctionAbstractCapabilityInvolvements()) {
      if (inv.getInvolved().equals(target)) {
        return inv;
      }
    }
    AbstractFunctionAbstractCapabilityInvolvement newInv = InteractionFactory.eINSTANCE
        .createAbstractFunctionAbstractCapabilityInvolvement();
    newInv.setInvolved(target);
    capability.getOwnedAbstractFunctionAbstractCapabilityInvolvements().add(newInv);
    CapellaServices.getService().creationService(newInv);
    return newInv;
  }

  /**
   * @param aBorderedNode
   * @param aView
   */
  private void moveBorderedNodeIfPossible(DNode aBorderedNode, Set<AbstractDNode> toBeRemoved, DDiagram diagram) {
    EObject container = aBorderedNode.eContainer();
    while (container instanceof DNodeContainer) {
      if (!toBeRemoved.contains(container)) {
        boolean canMove = true;

        for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(aBorderedNode, diagram)) {
          if (EcoreUtil.isAncestor(container, anEdge.getSourceNode())) {
            // not a internal exchanges
            canMove = false;
            break;
          }
        }
        for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(aBorderedNode, diagram)) {
          if (EcoreUtil.isAncestor(container, anEdge.getTargetNode())) {
            // not a internal exchanges
            canMove = false;
            break;
          }
        }

        if (canMove) {
          if (aBorderedNode.getTarget() instanceof ExchangeCategory) {
            for (DNode node : ((DNodeContainer) container).getOwnedBorderedNodes()) {
              if (node.getTarget() == aBorderedNode.getTarget()) {
                aBorderedNode.getIncomingEdges().addAll(aBorderedNode.getIncomingEdges());
                aBorderedNode.getOutgoingEdges().addAll(aBorderedNode.getOutgoingEdges());
                return;
              }
            }
          }
          // not a internal exchanges
          ((DNodeContainer) container).getOwnedBorderedNodes().add(aBorderedNode);
        }
        return;
      }
      container = container.eContainer();
    }
  }

  /**
   * used
   * 
   * @param currentFunctionView
   * @param scope
   * @param selectedExchanges
   * @return
   */
  public EObject showDFFunctionalExchange(AbstractDNode currentFunctionView, List<FunctionalExchange> scope,
      List<FunctionalExchange> selectedExchanges) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(currentFunctionView);
    DDiagramContents content = getDDiagramContents(currentDiagram);
    Set<FunctionalExchange> scopeSet = new HashSet<>();
    scopeSet.addAll(scope);
    Set<FunctionalExchange> selectedExchangesSet = new HashSet<>();
    selectedExchangesSet.addAll(selectedExchanges);

    AbstractShowHide shService = new ShowHideFunctionalExchange(content);
    DiagramContext context = shService.new DiagramContext();

    Collection<FunctionalExchange> exchanges = getDisplayedFunctionalExchanges(currentFunctionView);
    for (FunctionalExchange exchange : exchanges) {
      if (selectedExchangesSet.contains(exchange)) {
        selectedExchangesSet.remove(exchange);
      } else {
        shService.hide(exchange, context);
      }
    }

    for (FunctionalExchange exchange : selectedExchangesSet) {
      shService.show(exchange, context);
    }

    reorderFAElements(currentDiagram);

    return currentFunctionView;
  }

  public void showDFFunctionalExchange(AbstractDNode currentFunctionView, FunctionalExchange exchange,
      DDiagramContents context, boolean checkValid) {
    DEdge edge = context.getEdge(exchange);

    if (edge == null) {

      // Reveal source element
      ActivityNode source = exchange.getSource();
      ActivityNode target = exchange.getTarget();

      AbstractFunction sourceFunction = FunctionExt.getRelatedFunction(source);
      AbstractFunction targetFunction = FunctionExt.getRelatedFunction(target);

      Collection<DDiagramElement> sourceViews = context.getDiagramElements(sourceFunction);
      Collection<DDiagramElement> targetViews = context.getDiagramElements(targetFunction);

      AbstractDNode sourceView = null;
      if (!sourceViews.isEmpty()) {
        sourceView = (AbstractDNode) sourceViews.iterator().next();
      }
      AbstractDNode targetView = null;
      if (!targetViews.isEmpty()) {
        targetView = (AbstractDNode) targetViews.iterator().next();
      }

      // Don't display functional exchange from/to internal exchanges
      if (currentFunctionView != null) {
        boolean sourceIsChild = (EcoreUtil.isAncestor(currentFunctionView.getTarget(), source));
        boolean targetIsChild = (EcoreUtil.isAncestor(currentFunctionView.getTarget(), target));
        if (sourceIsChild && targetIsChild) {
          return;
        }
      }

      DragAndDropTarget sourceContainerView = context.getBestContainer(sourceFunction);
      DragAndDropTarget targetContainerView = context.getBestContainer(targetFunction);

      // If there is a hierarchy between both preferred bounds of functional exchange, we need to display both instead
      // hierarchy link
      if ((sourceContainerView instanceof DDiagramElement) && (targetContainerView instanceof DDiagramElement)) {
        DDiagramElement aView = (DDiagramElement) sourceContainerView;
        DDiagramElement bView = (DDiagramElement) targetContainerView;

        if (EcoreUtil.isAncestor(aView, bView)) {
          EObject element = context.getElement(sourceFunction, exchange);
          if (element instanceof AbstractFunction) {
            if (!context.getDiagramElements(element).contains(aView)) {
              sourceContainerView = context.getDDiagram();
            }
          }
        }
        if (EcoreUtil.isAncestor(bView, aView)) {
          EObject element = context.getElement(targetFunction, exchange);
          if (element instanceof AbstractFunction) {
            if (!context.getDiagramElements(element).contains(bView)) {
              targetContainerView = context.getDDiagram();
            }
          }
        }
      }

      // If preferred source is not a node (null, or diagram), we need to
      // display best parent function of the source
      // (port or OperationalActivity)
      if (sourceView == null) {
        if (sourceContainerView instanceof AbstractDNode) {
          sourceView = (AbstractDNode) sourceContainerView;
        } else {
          EObject element = context.getElement(sourceFunction, exchange);
          if (element instanceof AbstractFunction) {
            sourceView = showDFAbstractFunction((AbstractFunction) element, context.getBestContainer(element), context);
          }
        }
      }

      // If preferred source is not a node (null, or diagram), we need to
      // display best parent function of the source
      // (port or OperationalActivity)
      if (targetView == null) {
        if (targetContainerView instanceof AbstractDNode) {
          targetView = (AbstractDNode) targetContainerView;
        } else {
          EObject element = context.getElement(targetFunction, exchange);
          if (element instanceof AbstractFunction) {
            targetView = showDFAbstractFunction((AbstractFunction) element, context.getBestContainer(element), context);
          }

        }
      }

      if (checkValid && (!DFServices.getService().isValidDFFunctionalExchangeEdgeFromInternalTool(exchange, sourceView,
          targetView))) {
        return;
      }

      if (source instanceof FunctionPort) {
        sourceView = showDFFunctionPort(source, sourceView, context);
      }
      if (target instanceof FunctionPort) {
        targetView = showDFFunctionPort(target, targetView, context);
      }

      // Create an edge between both source and target
      if ((sourceView != null) && (targetView != null)) {
        DiagramElementMapping mapping = context
            .getMapping(MappingConstantsHelper.getMappingDFFunctionalExchange(context.getDDiagram()));
        edge = DiagramServices.getDiagramServices().createEdge((EdgeMapping) mapping, (EdgeTarget) sourceView,
            (EdgeTarget) targetView, exchange);
        context.addView(edge);
      }
    }
  }

  /**
   * @param source
   * @param sourceFunction
   * @param context
   */
  private AbstractDNode showDFFunctionPort(ActivityNode port, AbstractDNode sourceFunction, DDiagramContents context) {
    for (DDiagramElement element : sourceFunction.getOwnedBorderedNodes()) {
      if ((element.getTarget() != null) && element.getTarget().equals(port)) {
        return (AbstractDNode) element;
      }
    }

    if (sourceFunction.getTarget() instanceof AbstractFunction) {
      AbstractFunction function = (AbstractFunction) sourceFunction.getTarget();
      if (CapellaServices.getService().getAvailablePins(function, context.getDDiagram(), sourceFunction)
          .contains(port)) {
        NodeMapping mapping = (NodeMapping) context
            .getMapping(MappingConstantsHelper.getMappingFunctionPort(context.getDDiagram()));
        AbstractDNode element = DiagramServices.getDiagramServices().createBorderedNode(mapping, port,
            (DragAndDropTarget) sourceFunction, context._currentDiagram);
        context.addView(element);
        return element;
      }
    }
    return null;
  }

  /**
   * @param function
   * @param dContainer
   * @param context
   * @return
   */
  public AbstractDNode showDFAbstractFunction(AbstractFunction function, DragAndDropTarget dContainer,
      DDiagramContents context) {
    ContainerMapping mapping = (ContainerMapping) context
        .getMapping(MappingConstantsHelper.getMappingDFFunction(context.getDDiagram()));
    Collection<DDiagramElement> views = context.getDiagramElements(function, mapping);
    if (!views.isEmpty()) {
      return (AbstractDNode) views.iterator().next();
    }

    AbstractDNode element = DiagramServices.getDiagramServices().createContainer(mapping, function,
        context.getDDiagram(), context.getDDiagram());
    DiagramServices.getDiagramServices().getOwnedDiagramElements(dContainer).add(element);
    context.addView(element);
    return element;
  }

  private void hideDFFunctionalExchange(FunctionalExchange exchange, DDiagramContents context) {
    if (!CapellaServices.getService().isSynchronized(context._currentDiagram)) {
      DEdge edge = context.getEdge(exchange);
      if (edge != null) {
        EdgeTarget source = edge.getSourceNode();
        EdgeTarget target = edge.getTargetNode();

        // Hide source port if necessary
        if (source instanceof DNode) {
          DNode sourceNode = ((DNode) source);
          if (DiagramServices.getDiagramServices().isABorderedNode(sourceNode)) {
            boolean incoming = sourceNode.getIncomingEdges().isEmpty();
            boolean outgoing = sourceNode.getOutgoingEdges().isEmpty();
            if ((sourceNode.getIncomingEdges().size() == 1) && sourceNode.getIncomingEdges().contains(edge)) {
              incoming = true;
            }
            if ((sourceNode.getOutgoingEdges().size() == 1) && sourceNode.getOutgoingEdges().contains(edge)) {
              outgoing = true;
            }
            if (incoming && outgoing) {
              CapellaServices.getService().deleteView(sourceNode);
            }
          }
        }

        // Hide target port if necessary
        if (target instanceof DNode) {
          DNode targetNode = ((DNode) target);
          if (DiagramServices.getDiagramServices().isABorderedNode(targetNode)) {
            boolean incoming = targetNode.getIncomingEdges().isEmpty();
            boolean outgoing = targetNode.getOutgoingEdges().isEmpty();
            if ((targetNode.getIncomingEdges().size() == 1) && targetNode.getIncomingEdges().contains(edge)) {
              incoming = true;
            }
            if ((targetNode.getOutgoingEdges().size() == 1) && targetNode.getOutgoingEdges().contains(edge)) {
              outgoing = true;
            }
            if (incoming && outgoing) {
              CapellaServices.getService().deleteView(targetNode);
            }
          }
        }
        DiagramServices.getDiagramServices().removeEdgeView(edge);

      }
    }
  }

  /**
   * @param aFunction
   * @param diagram
   */
  @Deprecated
  protected DNode createViewFunctionPort(ActivityNode port, DragAndDropTarget container, DDiagram diagram) {
    NodeMapping mapping = getMappingDFFunctionPort(diagram);
    if (mapping == null) {
      mapping = getMappingABFunctionPort(diagram);
    }
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, port, container, diagram);
  }

  /**
   * @param aFunction
   * @param diagram
   */
  @Deprecated
  protected AbstractDNode createViewDFAbstractFunction(AbstractFunction aFunction, DDiagram diagram) {
    return createViewDFFunction(aFunction, diagram);
  }

  /**
   * @param aFunction
   * @param diagram
   */
  protected DNode createViewABAbstractFunction(AbstractFunction aFunction, DragAndDropTarget container,
      DDiagram diagram) {
    NodeMapping mapping = getMappingABAbstractFunction(aFunction, diagram);
    return DiagramServices.getDiagramServices().createNode(mapping, aFunction, container, diagram);
  }

  /**
   * @param aFunction
   * @param diagram
   * @return
   */
  protected DNodeContainer createViewDFFunction(AbstractFunction aFunction, DDiagram diagram) {
    ContainerMapping mapping = getMappingDFFunction(aFunction, diagram);
    return DiagramServices.getDiagramServices().createContainer(mapping, aFunction, diagram, diagram);
  }

  /**
   * @param aFunction
   * @param diagram
   * @return
   */
  protected DNode createViewComponentPort(Port port, DragAndDropTarget container, DDiagram diagram) {
    NodeMapping mapping = getMappingABComponentPort(port, diagram);
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, port, container, diagram);
  }

  /**
   * @param aFunction
   * @param diagram
   * @return
   */
  protected DNode createViewPhysicalPort(Port port, DragAndDropTarget container, DDiagram diagram) {
    NodeMapping mapping = getMappingABPhysicalPort(port, diagram);
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, port, container, diagram);
  }

  /**
   * Unused
   */
  @Deprecated
  public DNodeContainer createViewPart(EObject target, DragAndDropTarget parent, DDiagram parentDiagram) {
    Component cpnt = null;
    if (target instanceof Component) {
      cpnt = (Component) target;
    } else if (target instanceof Part) {
      cpnt = (Component) ((Part) target).getAbstractType();
    }
    ContainerMapping mapping = getMappingABComponent(cpnt, parentDiagram);
    return DiagramServices.getDiagramServices().createContainer(mapping, target, parent, parentDiagram);
  }

  public NodeMapping getMappingABComponentPort(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABComponentPort(diagram);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
  }

  public List<NodeMapping> getMappingABPorts(DDiagram diagram) {
    List<String> mappingNames = MappingConstantsHelper.getMappingABPorts(diagram);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingNames);
  }

  /**
   * Returns mapping of component port
   */
  @Deprecated
  public NodeMapping getMappingABComponentPort(Port port, DDiagram diagram) {
    return getMappingABComponentPort(diagram);
  }

  @Deprecated
  public NodeMapping getMappingABPhysicalPort(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABPhysicalPort(diagram);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
  }

  @Deprecated
  public NodeMapping getMappingABPhysicalPort(Port port, DDiagram diagram) {
    return getMappingABPhysicalPort(diagram);
  }

  /**
   * Perform a dnd of a function port.
   * 
   * @param port
   * @param oldContainer
   * @param newContainer
   */
  public EObject dndDFFunctionPort(FunctionPort port, NamedElement oldContainer, NamedElement newContainer) {

    if (newContainer instanceof AbstractFunction) {
      AbstractFunction newFunction = (AbstractFunction) newContainer;
      AbstractFunction oldFunction = (AbstractFunction) port.eContainer();
      if (port instanceof FunctionInputPort) {
        for (ActivityEdge anEdge : ((FunctionInputPort) port).getIncoming()) {
          if (anEdge instanceof FunctionalExchange) {
            updateFunctionaChainInvolvementsOfFunctionalExchange((FunctionalExchange) anEdge, oldFunction, newFunction);
          }
        }
        newFunction.getInputs().add((FunctionInputPort) port);
      } else {
        for (ActivityEdge anEdge : ((FunctionOutputPort) port).getOutgoing()) {
          if (anEdge instanceof FunctionalExchange) {
            updateFunctionaChainInvolvementsOfFunctionalExchange((FunctionalExchange) anEdge, oldFunction, newFunction);
          }
        }
        newFunction.getOutputs().add((FunctionOutputPort) port);
      }
    }
    return port;
  }

  public Collection<? extends EObject> getFBDSemanticFunctions(AbstractFunction root) {
    Collection<? extends EObject> result = getFBDSemanticAbstractFunctions(root);
    CapellaServices.getService().filter(result, FaPackage.Literals.ABSTRACT_FUNCTION);
    return result;
  }

  @Deprecated
  public Collection<? extends EObject> getFBDSemanticControlNodes(AbstractFunction root) {
    return Collections.emptyList();
  }

  /** Returns all owned functions used in breakdown diagrams */
  public Collection<? extends EObject> getFBDSemanticAbstractFunctions(AbstractFunction root) {
    Collection<? extends AbstractFunction> result = new ArrayList<>(getAllAbstractFunctions(root));
    EObject container = root.eContainer();
    if (container instanceof FunctionPkg) {
      container = container.eContainer();
      // Remove the root function
      if (container instanceof BlockArchitecture) {
        result.remove(root);
      }
    }
    return result;
  }

  public Collection<AbstractFunction> getAllAbstractFunctions(AbstractFunction root) {
    return getCache(FunctionExt::getAllAbstractFunctions, root);
  }

  public Collection<AbstractFunction> getAllAbstractFunctions(BlockArchitecture root) {
    return FunctionExt.getAllAbstractFunctions(root);
  }

  public Collection<AbstractFunction> getAllAbstractFunctions(FunctionPkg root) {
    return FunctionPkgExt.getAllAbstractFunctions(root);
  }

  public EObject getFBDParentFunction(AbstractFunction root) {
    return EcoreUtil2.getFirstContainer(root, FaPackage.Literals.ABSTRACT_FUNCTION);
  }

  public EObject updateFunctionaChainInvolvementsOfFunctionalExchange(FunctionalExchange fe,
      AbstractFunction oldFunction, AbstractFunction newFunction) {
    for (Involvement anInvolvement : fe.getInvolvingInvolvements()) {
      if (anInvolvement instanceof FunctionalChainInvolvementLink) {

        FunctionalChainInvolvementLink currentInvolvementLink = (FunctionalChainInvolvementLink) anInvolvement;
        FunctionalChain currentFunctionalChain = (FunctionalChain) currentInvolvementLink.eContainer();

        Set<FunctionalChainInvolvement> newFunctionInvolvements = FunctionalChainExt
            .getInvolvementsOf(currentFunctionalChain, newFunction);
        FunctionalChainInvolvementFunction newFunctionInv;
        if (newFunctionInvolvements.isEmpty()) {
          // we add the new Function to the functional chain
          newFunctionInv = FunctionalChainExt.createInvolvementFunction(currentFunctionalChain, newFunction);
        } else {
          newFunctionInv = (FunctionalChainInvolvementFunction) newFunctionInvolvements.iterator().next();
        }
        if (!FunctionExt.getIncomingAbstractFunction(fe).equals(oldFunction)) {
          // the target of the exchange has changed
          currentInvolvementLink.setTarget(newFunctionInv);
        } else {
          // the source of the exchange has changed
          currentInvolvementLink.setSource(newFunctionInv);
        }
      }
    }
    return fe;
  }

  /**
   * Performs semantic operation for a reconnect of source from a component exchange edge
   * 
   * @param componentExchange
   * @param edge
   * @param oldNode
   * @param newNode
   */
  public EObject reconnectDFFunctionalExchangeSource(EObject functionalExchange, DSemanticDecorator edge,
      DSemanticDecorator oldNode, DSemanticDecorator newNode) {
    if (edge instanceof DEdge && functionalExchange instanceof FunctionalExchange) {
      reconnectDFFunctionalExchange((FunctionalExchange) functionalExchange,
          ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE, (DEdge) edge, oldNode, newNode);
    }
    return functionalExchange;
  }

  /**
   * Performs semantic operation for a reconnect of source from a component exchange edge
   * 
   * @param componentExchange
   * @param edge
   * @param oldNode
   * @param newNode
   */
  public EObject reconnectDFFunctionalExchangeTarget(EObject functionalExchange, DSemanticDecorator edge,
      DSemanticDecorator oldNode, DSemanticDecorator newNode) {
    if (edge instanceof DEdge && functionalExchange instanceof FunctionalExchange) {
      reconnectDFFunctionalExchange((FunctionalExchange) functionalExchange,
          ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE, (DEdge) edge, oldNode, newNode);
    }
    return functionalExchange;
  }

  /**
   * @param functionalExchange
   * @param activityEdgeSource
   * @param edge
   * @param oldNode
   * @param newNode
   */
  private void reconnectDFFunctionalExchange(FunctionalExchange functionalExchange, EReference activityEdgeSource,
      DEdge edge, DSemanticDecorator oldNode, DSemanticDecorator newNode) {
    //
  }

  public EObject reconnectFunctionalExchange(FunctionalExchange fe, ActivityNode source, ActivityNode target) {
    EObject oldFunction = FunctionExt.getRelatedFunction(source);
    EObject newFunction = FunctionExt.getRelatedFunction(target);

    updateFunctionaChainInvolvementsOfFunctionalExchange(fe, (AbstractFunction) oldFunction,
        (AbstractFunction) newFunction);
    if (fe.getSource().equals(source)) {
      fe.setSource(target);
    } else {
      fe.setTarget(target);
    }
    EObject commonAncestor = CapellaServices.getService().getCommonAncestor(fe.getSource(), fe.getTarget());
    if (commonAncestor instanceof AbstractFunction && (!commonAncestor.equals(fe.eContainer()))) {
      ((AbstractFunction) commonAncestor).getOwnedFunctionalExchanges().add(fe);
    }
    return fe;
  }

  public boolean isFunctionalExchangeReconnectable(FunctionalExchange fe, DDiagram diagram, EObject source,
      EObject target) {
    if ((source instanceof InputPin) && (target instanceof OutputPin)) {
      return false;
    }
    if ((source instanceof OutputPin) && (target instanceof InputPin)) {
      return false;
    }
    if ((target instanceof AbstractFunction) && !CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(fe)) {
      return false;
    }
    if (!(target instanceof AbstractFunction) && CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(fe)) {
      return false;
    }

    // move source ?
    if (fe.getSource().equals(source) && !isValidCreationFunctionalExchange(fe, target, fe.getTarget())) {
      return false;
    }
    // move target ?
    if (fe.getTarget().equals(source) && !isValidCreationFunctionalExchange(fe, fe.getSource(), target)) {
      return false;
    }
    return true;
  }

  public boolean isValidDndABComponentPort(EObject element, EObject newContainer) {
    return true;
  }

  /**
   * Perform model modifications on dnd of an abstract function.
   * 
   * @param function
   *          the given abstractFunction
   * @param oldContainer
   *          the given namedElement
   * @param newContainer
   *          the given namedElement
   * @return the EObject
   */
  public EObject dndABAbstractFunctionAllocation(AbstractFunction function, NamedElement oldContainer,
      NamedElement newContainer) {
    if (oldContainer.equals(newContainer)) {
      return function;
    }
    Collection<AbstractFunction> functions = getCache(FunctionExt::getAllAbstractFunctions, function);

    Component oldComponent = null;
    Component newComponent = null;

    if (oldContainer instanceof Part) {
      oldComponent = (Component) CsServices.getService().getComponentType((Part) oldContainer);
    } else if (oldContainer instanceof Component) {
      oldComponent = (Component) oldContainer;
    }

    if (newContainer instanceof Part) {
      newComponent = (Component) CsServices.getService().getComponentType((Part) newContainer);
    } else if (newContainer instanceof Component) {
      newComponent = (Component) newContainer;
    }

    if ((oldComponent != null) && (newComponent != null)) {
      // Move all related allocations
      for (ComponentFunctionalAllocation allocation : new ArrayList<ComponentFunctionalAllocation>(
          oldComponent.getOwnedFunctionalAllocation())) {
        if (functions.contains(allocation.getTargetElement())) {
          allocation.setSourceElement(newComponent);
          newComponent.getOwnedFunctionalAllocation().add(allocation);
        }
      }
    } else if ((oldContainer instanceof Role) && (newContainer instanceof Role)) {
      Role oldRole = (Role) oldContainer;
      Role newRole = (Role) newContainer;

      // Move all related allocations
      for (ActivityAllocation allocation : new ArrayList<ActivityAllocation>(oldRole.getOwnedActivityAllocations())) {
        if (functions.contains(allocation.getTargetElement())) {
          allocation.setSourceElement(newRole);
          newRole.getOwnedActivityAllocations().add(allocation);
        }
      }
    }
    // old container is ROLE and new container is Component
    else if ((oldContainer instanceof Role) && (newComponent != null)) {
      Role oldRole = (Role) oldContainer;
      // remove activity allocation toward function
      List<ActivityAllocation> removeAllocationLink = new ArrayList<>();
      EList<ActivityAllocation> allocations = oldRole.getActivityAllocations();
      for (ActivityAllocation activityAllocation : allocations) {
        OperationalActivity activity = activityAllocation.getActivity();
        if ((null != activity) && activity.equals(function)) {
          removeAllocationLink.add(activityAllocation);
        }
      }
      CapellaServices.getService().removeElements(removeAllocationLink);

      // create new Component Functional Allocation allocation in new
      // container(that is component)
      allocateToComponent(function, newComponent);
    }
    // old container is Component and new container is ROLE
    else if ((oldComponent != null) && (newContainer instanceof Role)) {
      Role newRole = (Role) newContainer;
      // remove Component Functional Allocation toward function
      List<ComponentFunctionalAllocation> removeAllocationLink = new ArrayList<>();
      EList<ComponentFunctionalAllocation> allocations = oldComponent.getOwnedFunctionalAllocation();
      for (ComponentFunctionalAllocation activityAllocation : allocations) {
        AbstractFunction fct = activityAllocation.getFunction();
        if ((null != fct) && fct.equals(function)) {
          removeAllocationLink.add(activityAllocation);
        }
      }
      CapellaServices.getService().removeElements(removeAllocationLink);

      // create new Activity Allocation allocation in new container (that
      // is role)
      allocateToRole(function, newRole);
    }

    // remove useless portRealization of "all functions" and inComing and
    // outGoing functiaonalExchages(component
    // Exchange Allocation)
    if (!oldContainer.equals(newComponent)) {
      removeUseLessPortRealizationAndComponentExchangeAllocation(function, functions);
    }

    return function;
  }

  private void removeUseLessPortRealizationAndComponentExchangeAllocation(AbstractFunction function,
      Collection<AbstractFunction> functions) {
    Collection<FunctionalExchange> functionalExchanges = new HashSet<>();
    for (AbstractFunction fct : functions) {
      // Find all PortRealization and component exchanges to be deleted
      for (Port port : FunctionExt.getOwnedFunctionPorts(fct)) {
        removeUselessPortRealizations(port, true, true, false, false);
      }
    }
    // consider incoming and outgoing functionalExchanges of target function
    functionalExchanges.addAll(FunctionExt.getIncomingExchange(function));
    functionalExchanges.addAll(FunctionExt.getOutGoingExchange(function));

    removeComponentExchangeAllocations(functionalExchanges);
  }

  private void removeComponentExchangeAllocations(Collection<FunctionalExchange> functionalExchanges) {
    Collection<ComponentExchangeFunctionalExchangeAllocation> allocations = new HashSet<>();

    // Remove components exchanges realizing the functional exchanges
    for (FunctionalExchange exchange : functionalExchanges) {
      for (ComponentExchange ce : exchange.getAllocatingComponentExchanges()) {
        for (ComponentExchangeFunctionalExchangeAllocation alloc : ce
            .getOutgoingComponentExchangeFunctionalExchangeAllocations()) {
          if (alloc.getAllocatedFunctionalExchange().equals(exchange)) {
            allocations.add(alloc);
          }
        }
      }
    }

    // Delete all allocations
    CapellaServices.getService().removeElements(allocations);
  }

  /**
   * @param realization
   * @return
   */
  private boolean isValidAllocation(Allocation realization) {
    return (realization.getSourceElement() != null) && (realization.getTargetElement() != null)
        && (realization.getSourceElement() instanceof CapellaElement)
        && (realization.getTargetElement() instanceof CapellaElement)
        && CapellaElementExt.areInSameDecompositionAlternative((CapellaElement) realization.getSourceElement(),
            (CapellaElement) realization.getTargetElement());
  }

  public HashMapSet<ExchangeCategory, AbstractFunction> getAvailableCategoriesAndFunctionsToInsertInDataFlowBlank(
      AbstractDNode functionView, DDiagramContents content) {

    HashMapSet<ExchangeCategory, AbstractFunction> returnedMap = new HashMapSet<>();
    List<DNodeContainer> functionContainersInDiagram = new ArrayList<>();

    for (DDiagramElement element : DiagramServices.getDiagramServices().getDiagramElements(content.getDDiagram())) {
      // TODO We need to update this using DDiagramContent, but it must be consistent
      // with all added views while S/H
      if (element instanceof AbstractDNode) {
        AbstractDNode aContainer = (AbstractDNode) element;
        if (aContainer.getTarget() instanceof AbstractFunction && aContainer instanceof DNodeContainer) {
          functionContainersInDiagram.add((DNodeContainer) aContainer);
        }
      }
    }

    AbstractFunction function = (AbstractFunction) functionView.getTarget();
    for (FunctionalExchange anExchange : getAvailableFunctionalExchangesToInsert(functionView)) {
      AbstractFunction targetFunction = null;
      if (EcoreUtil.isAncestor(function, anExchange.getSource())
          && anExchange.getTarget().eContainer() instanceof AbstractFunction) {
        targetFunction = (AbstractFunction) anExchange.getTarget().eContainer();
      } else if (anExchange.getSource().eContainer() instanceof AbstractFunction) {
        targetFunction = (AbstractFunction) anExchange.getSource().eContainer();
      }

      DNodeContainer visibleFunctionInDiagram = getDisplayedFunctionContainer(targetFunction,
          functionContainersInDiagram);
      if (visibleFunctionInDiagram != null) {
        if (isValidCreationCategoryBetweenViews(anExchange, functionView, visibleFunctionInDiagram)) {
          targetFunction = (AbstractFunction) visibleFunctionInDiagram.getTarget();
        } else {
          targetFunction = null;
        }
      }

      if (targetFunction != null) {
        for (ExchangeCategory aCategory : anExchange.getCategories()) {
          returnedMap.put(aCategory, targetFunction);
        }
      }
    }
    return returnedMap;
  }

  public HashMapSet<ExchangeCategory, AbstractFunction> getCategoriesAndFunctionsInitialSelectionInDataFlowBlank(
      DNodeContainer functionView, DDiagramContents content) {
    HashMapSet<ExchangeCategory, AbstractFunction> returnedMap = new HashMapSet<>();
    List<DNodeContainer> functionContainersInDiagram = new ArrayList<>();

    for (DDiagramElement element : DiagramServices.getDiagramServices().getDiagramElements(content.getDDiagram())) { // TODO
      // We
      // need
      // to
      // update
      // this
      // using
      // DDiagramContent, but it must be
      // consistent with all added views while
      // S/H
      if (element instanceof AbstractDNode) {
        AbstractDNode aContainer = (AbstractDNode) element;
        if (aContainer.getTarget() instanceof AbstractFunction && (aContainer instanceof DNodeContainer)) {
          functionContainersInDiagram.add((DNodeContainer) aContainer);
        }
      }
    }

    for (DNode aBorderedNode : functionView.getOwnedBorderedNodes()) {
      if (aBorderedNode.getTarget() instanceof ExchangeCategory) {
        ExchangeCategory aCategory = (ExchangeCategory) aBorderedNode.getTarget();
        for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(aBorderedNode,
            content.getDDiagram())) {
          if (anEdge.isVisible()) {
            AbstractFunction sourceFunction = (AbstractFunction) ((DNodeContainer) anEdge.getSourceNode().eContainer())
                .getTarget();
            // find if a function in the diagram may be a source
            // Function of the functional Exchange
            DNodeContainer visibleFunctionInDiagram = getDisplayedFunctionContainer(sourceFunction,
                functionContainersInDiagram);
            if (visibleFunctionInDiagram != null) {
              sourceFunction = (AbstractFunction) visibleFunctionInDiagram.getTarget();
            }
            returnedMap.put(aCategory, sourceFunction);
          }
        }
        for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(aBorderedNode,
            content.getDDiagram())) {
          if (anEdge.isVisible()) {
            AbstractFunction targetFunction = (AbstractFunction) ((DNodeContainer) anEdge.getTargetNode().eContainer())
                .getTarget();
            // find if a function in the diagram may be a target
            // Function of the functional Exchange
            DNodeContainer visibleFunctionInDiagram = getDisplayedFunctionContainer(targetFunction,
                functionContainersInDiagram);
            if (visibleFunctionInDiagram != null) {
              targetFunction = (AbstractFunction) visibleFunctionInDiagram.getTarget();
            }
            returnedMap.put(aCategory, targetFunction);
          }
        }
      }
    }
    return returnedMap;
  }

  /**
   * Create a component exchange between two parts, without creating any ports
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createABComponentExchangeWithoutPorts(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);

    InformationsExchanger sourcePart = CsServices.getService().getRelatedPart(sourceView);
    InformationsExchanger targetPart = CsServices.getService().getRelatedPart(targetView);

    EdgeTarget nodeSource = (EdgeTarget) sourceView;
    EdgeTarget nodeTarget = (EdgeTarget) targetView;

    // Create component exchange
    ComponentExchange exchange = FaFactory.eINSTANCE.createComponentExchange();
    exchange.setKind(ComponentExchangeKind.ASSEMBLY);

    // Attach source and target
    exchange.setSource(sourcePart);
    exchange.setTarget(targetPart);

    // Attach to parent
    ComponentExchangeExt.attachToDefaultContainer(exchange);

    CapellaServices.getService().creationService(exchange);
    DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingABConnection(diagram),
        nodeSource, nodeTarget, exchange);
    return context;
  }

  /**
   * @param sourceView
   * @return
   */
  public ActivityNode getRelatedActivityNode(DSemanticDecorator sourceView) {
    if (sourceView != null) {
      if (sourceView.getTarget() instanceof ActivityNode) {
        return (ActivityNode) sourceView.getTarget();
      }
      if (sourceView.eContainer() instanceof DSemanticDecorator) {
        return getRelatedActivityNode((DSemanticDecorator) sourceView.eContainer());
      }
    }
    return null;
  }

  /**
   * Create a component exchange in an architecture blank diagram. Create port if selected views are not targeting port
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createABComponentExchange(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {
    return createABComponentExchangeWithOption(context, sourceView, targetView, false);
  }

  /**
   * Create a component exchange in an architecture blank diagram. Create port if selected views are not targeting port
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createABComponentExchange(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView, boolean createComponentExchageOnType) {
    return createABComponentExchangeWithOption(context, sourceView, targetView, createComponentExchageOnType);
  }

  /**
   * Create a component exchange in an architecture blank diagram. Create port if selected views are not targeting port
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createABComponentExchangeWithOption(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView, boolean createComponentExchageOnType) {
    EObject sourceTarget = sourceView.getTarget();
    EObject targetTarget = targetView.getTarget();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);

    EdgeTarget nodeSource = null;
    EdgeTarget nodeTarget = null;

    ComponentExchange exchange = null;

    if ((sourceTarget instanceof Entity) && (targetTarget instanceof Entity)) {

      exchange = OaFactory.eINSTANCE.createCommunicationMean();
      exchange.setSource((Entity) sourceTarget);
      exchange.setTarget((Entity) targetTarget);

      nodeSource = (EdgeTarget) sourceView;
      nodeTarget = (EdgeTarget) targetView;

    } else {

      // Create or retrieve sourcePort
      ComponentPort sourcePort = null;
      if (sourceTarget instanceof ComponentPort) {
        sourcePort = (ComponentPort) sourceTarget;
        nodeSource = (EdgeTarget) sourceView;

      } else {
        Component sourceComponent = (Component) CsServices.getService().getComponentType(sourceView);
        if (sourceComponent != null) {
          sourcePort = FaFactory.eINSTANCE.createComponentPort();
          sourceComponent.getOwnedFeatures().add(sourcePort);

          if (targetTarget instanceof ComponentPort) {
            sourcePort.setKind(((ComponentPort) targetTarget).getKind());
          } else {
            sourcePort.setKind(ComponentPortKind.FLOW);
          }
          if (sourcePort.getKind() == ComponentPortKind.FLOW) {
            sourcePort.setOrientation(OrientationPortKind.OUT);
          }
          CapellaServices.getService().creationService(sourcePort);

          if (sourceView instanceof DNodeContainer) {
            nodeSource = CsServices.getService().createViewOrGetPort((DNodeContainer) sourceView, sourcePort).getKey();
          }
        }
      }

      // Create or retrieve targetPort
      ComponentPort targetPort = null;
      if (targetTarget instanceof ComponentPort) {
        targetPort = (ComponentPort) targetTarget;
        nodeTarget = (EdgeTarget) targetView;

      } else {
        Component targetComponent = (Component) CsServices.getService().getComponentType(targetView);
        if (targetComponent != null) {
          targetPort = FaFactory.eINSTANCE.createComponentPort();
          targetComponent.getOwnedFeatures().add(targetPort);

          if (sourcePort != null) {
            targetPort.setKind(sourcePort.getKind());
          }
          if (targetPort.getKind() == ComponentPortKind.FLOW) {
            targetPort.setOrientation(OrientationPortKind.IN);
          }

          CapellaServices.getService().creationService(targetPort);
          if (targetView instanceof DNodeContainer) {
            nodeTarget = CsServices.getService().createViewOrGetPort((DNodeContainer) targetView, targetPort).getKey();
          }
        }
      }

      if (sourcePort != null && targetPort != null) {
        // Create component exchange
        exchange = FaFactory.eINSTANCE.createComponentExchange();
        if ((sourcePort.getKind() == ComponentPortKind.STANDARD)
            || (targetPort.getKind() == ComponentPortKind.STANDARD)) {
          exchange.setKind(ComponentExchangeKind.ASSEMBLY);
        } else {
          exchange.setKind(ComponentExchangeKind.FLOW);
        }

        // Set source
        if (CsServices.getService().isMultipartMode((ModelElement) sourceTarget) && !createComponentExchageOnType) {
          ComponentExchangeEnd end = FaFactory.eINSTANCE.createComponentExchangeEnd();
          InformationsExchanger sourceRelatedPart = CsServices.getService().getRelatedPart(sourceView);
          if (sourceRelatedPart instanceof Part) {
            end.setPart((Part) sourceRelatedPart);
          }
          end.setPort(sourcePort);
          exchange.setSource(end);
          exchange.getOwnedComponentExchangeEnds().add(end);
          CapellaServices.getService().creationService(end);
        } else {
          exchange.setSource(sourcePort);
        }

        // Set target
        if (CsServices.getService().isMultipartMode((ModelElement) sourceTarget) && !createComponentExchageOnType) {
          ComponentExchangeEnd end = FaFactory.eINSTANCE.createComponentExchangeEnd();
          InformationsExchanger targetRelatedPart = CsServices.getService().getRelatedPart(targetView);
          if (targetRelatedPart instanceof Part) {
            end.setPart((Part) targetRelatedPart);
          }
          end.setPort(targetPort);
          exchange.setTarget(end);
          exchange.getOwnedComponentExchangeEnds().add(end);
          CapellaServices.getService().creationService(end);
        } else {
          exchange.setTarget(targetPort);
        }
      }
    }

    if (exchange != null) {
      // Attach to parent
      ComponentExchangeExt.attachToDefaultContainer(exchange);
      CapellaServices.getService().creationService(exchange);
      DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingABConnection(diagram),
          nodeSource, nodeTarget, exchange);
    }

    CsServices.getService().setInterpreterVariable(context, "result", exchange);
    return context;
  }

  /**
   * Create a delegation in an interface blank diagram. Create port if selected views are not targeting port
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createIBDelegation(EObject context, DSemanticDecorator sourceView, DSemanticDecorator targetView) {
    return createABDelegation(context, sourceView, targetView);
  }

  /**
   * Create a delegation in an architecture blank diagram. Create port if selected views are not targeting port
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createABDelegation(EObject context, DSemanticDecorator sourceView, DSemanticDecorator targetView) {
    EObject sourceTarget = sourceView.getTarget();
    EObject targetTarget = targetView.getTarget();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);

    EObject sourceRelatedPart = CsServices.getService().getRelatedPart(sourceView);
    EObject targetRelatedPart = CsServices.getService().getRelatedPart(targetView);

    Part sourcePart = null;
    Part targetPart = null;
    if (sourceRelatedPart instanceof Part) {
      sourcePart = (Part) sourceRelatedPart;
    }
    if (targetRelatedPart instanceof Part) {
      targetPart = (Part) targetRelatedPart;
    }

    if (sourcePart == null) {
      EObject sourceComponent = CsServices.getService().getComponentType(sourceView);
      if (sourceComponent instanceof Component && !((Component) sourceComponent).getRepresentingParts().isEmpty()
          && (((Component) sourceComponent).getRepresentingParts().get(0) instanceof Part)) {
        sourcePart = ((Component) sourceComponent).getRepresentingParts().get(0);
      }
    }
    if (targetPart == null) {
      EObject targetComponent = CsServices.getService().getComponentType(targetView);
      if (targetComponent instanceof Component && !((Component) targetComponent).getRepresentingParts().isEmpty()
          && (((Component) targetComponent).getRepresentingParts().get(0) instanceof Part)) {
        targetPart = ((Component) targetComponent).getRepresentingParts().get(0);
      }
    }
    if ((sourcePart == null) || (targetPart == null)) {
      return context;
    }

    EdgeTarget nodeSource = null;
    EdgeTarget nodeTarget = null;

    // Create or retrieve sourcePort
    Port sourcePort = null;
    if (sourceTarget instanceof Port) {
      sourcePort = (Port) sourceTarget;
      nodeSource = (EdgeTarget) sourceView;

    } else {

      // Create a component port or a physical port if from physical
      // component
      if ((sourcePart.getAbstractType() instanceof PhysicalComponent)
          && (((PhysicalComponent) sourcePart.getAbstractType()).getNature() == PhysicalComponentNature.NODE)) {
        sourcePort = CsFactory.eINSTANCE.createPhysicalPort();
        ((Component) sourcePart.getType()).getOwnedFeatures().add((PhysicalPort) sourcePort);

      } else {
        sourcePort = FaFactory.eINSTANCE.createComponentPort();
        ComponentPort sourceCPort = (ComponentPort) sourcePort;
        ((Component) sourcePart.getType()).getOwnedFeatures().add(sourceCPort);

        if (targetTarget instanceof ComponentPort) {
          ComponentPort targetPort = (ComponentPort) targetTarget;
          sourceCPort.setKind(targetPort.getKind());
          sourceCPort.setOrientation(targetPort.getOrientation());
        } else {
          sourceCPort.setKind(ComponentPortKind.FLOW);
          sourceCPort.setOrientation(OrientationPortKind.IN);
        }
      }

      CapellaServices.getService().creationService(sourcePort);
      if (sourceView instanceof DNodeContainer) {
        nodeSource = CsServices.getService().createViewOrGetPort((DNodeContainer) sourceView, sourcePort).getKey();
      }
    }

    // Create or retrieve targetPort
    Port targetPort = null;
    if (targetTarget instanceof ComponentPort) {
      targetPort = (ComponentPort) targetTarget;
      nodeTarget = (EdgeTarget) targetView;
    } else {
      targetPort = FaFactory.eINSTANCE.createComponentPort();
      ComponentPort targetCPort = (ComponentPort) targetPort;
      if (sourcePort instanceof ComponentPort) {
        targetCPort.setKind(((ComponentPort) sourcePort).getKind());
        targetCPort.setOrientation(((ComponentPort) sourcePort).getOrientation());
      } else {
        targetCPort.setKind(ComponentPortKind.FLOW);
        targetCPort.setOrientation(OrientationPortKind.IN);
      }
      ((Component) targetPart.getType()).getOwnedFeatures().add(targetCPort);

      CapellaServices.getService().creationService(targetPort);
      if (targetView instanceof DNodeContainer) {
        nodeTarget = CsServices.getService().createViewOrGetPort((DNodeContainer) targetView, targetPort).getKey();
      }
    }

    // Create component exchange
    ComponentExchange exchange = FaFactory.eINSTANCE.createComponentExchange();
    exchange.setKind(ComponentExchangeKind.DELEGATION);

    // Set source
    exchange.setSource((InformationsExchanger) sourcePort);

    // Set target
    if (CsServices.getService().isMultipartMode((ModelElement) sourceTarget)) {
      ComponentExchangeEnd end = FaFactory.eINSTANCE.createComponentExchangeEnd();
      end.setPart(targetPart);
      end.setPort(targetPort);
      exchange.setTarget(end);
      exchange.getOwnedComponentExchangeEnds().add(end);
      CapellaServices.getService().creationService(end);
    } else {
      exchange.setTarget((InformationsExchanger) targetPort);
    }

    // Attach to parent
    ComponentExchangeExt.attachToDefaultContainer(exchange);

    CapellaServices.getService().creationService(exchange);
    DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingABConnection(diagram),
        nodeSource, nodeTarget, exchange);
    CsServices.getService().setInterpreterVariable(context, "result", exchange);
    return context;
  }

  /**
   * Create a component exchange between parents part of sourceView and targetView considered as "brothers" and create
   * delegations from theses parts and sourceView and targetView
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createABComponentExchangeThroughDelegation(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {
    InformationsExchanger sourcePart = CsServices.getService().getRelatedPart(sourceView);
    InformationsExchanger targetPart = CsServices.getService().getRelatedPart(targetView);

    ComponentPort sourcePort = null;
    if (sourceView.getTarget() instanceof ComponentPort) {
      sourcePort = (ComponentPort) sourceView.getTarget();
    }

    ComponentPort targetPort = null;
    if (targetView.getTarget() instanceof ComponentPort) {
      targetPort = (ComponentPort) targetView.getTarget();
    }

    if ((sourcePart instanceof Part) && (targetPart instanceof Part)) {
      Collection<EObject> createdElements = ComponentExt.createComponentExchangeThroughDelegations((Part) sourcePart,
          sourcePort, (Part) targetPart, targetPort);
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
      CsServices.getService().showABComponentExchange(createdElements, (DSemanticDecorator) diagram);
      CsServices.getService().setInterpreterVariable(context, "result", createdElements);
    }
    return context;
  }

  public Collection<ComponentExchange> getRelatedComponentExchanges(NamedElement componentOrPart) {
    Collection<ComponentExchange> relatedExchanges = new ArrayList<>();

    // Retrieve all related component exchanges
    if (componentOrPart instanceof Component) {
      relatedExchanges.addAll(getCache(ComponentExt::getAllRelatedComponentExchange, (Component) componentOrPart));

    } else if (componentOrPart instanceof Part) {
      Part part = (Part) componentOrPart;
      if (part.getAbstractType() instanceof Component) {
        Component component = (Component) ((Part) componentOrPart).getAbstractType();

        if (CsServices.getService().isMultipartMode(componentOrPart)) {
          for (ComponentExchange exchange : ComponentExt.getAllRelatedComponentExchange(component)) {
            if (part.equals(ComponentExchangeExt.getSourcePart(exchange))
                || part.equals(ComponentExchangeExt.getTargetPart(exchange))) {
              relatedExchanges.add(exchange);
            }
          }
        } else {
          relatedExchanges.addAll(ComponentExt.getAllRelatedComponentExchange(component));
        }
      }
    }
    return relatedExchanges;
  }

  /**
   * @param component
   */
  void removeUselessExchanges(NamedElement componentOrPart) {

    Collection<ComponentExchange> relatedExchanges = getRelatedComponentExchanges(componentOrPart);

    // remove all delegation links
    // remove all incoming traceLinks from PhysicalLink
    Collection<ModelElement> elements = new HashSet<>();
    for (ComponentExchange connection : relatedExchanges) {
      // remove the tract link coming from physicaLink [specific]
      EList<AbstractTrace> incomingTraces = connection.getIncomingTraces();
      for (AbstractTrace abstractTrace : incomingTraces) {
        TraceableElement sourceElement = abstractTrace.getSourceElement();
        if (sourceElement instanceof PhysicalLink) {
          // remove the tractLink
          elements.add(abstractTrace);
        }
      }
    }
    CapellaServices.getService().removeElements(elements);
  }

  public EObject dndABDeployment(NamedElement pcMoved, NamedElement oldContainer, NamedElement newContainer) {
    if ((pcMoved instanceof PhysicalComponent) && (newContainer instanceof PhysicalComponent)) {
      PhysicalComponent component = (PhysicalComponent) pcMoved;
      PhysicalComponent newComponent = (PhysicalComponent) newContainer;

      // move all exchanges
      moveComponentExchanges(component);

      // Remove all port outgoing allocations
      for (Port port : ComponentExt.getOwnedComponentPort(component)) {
        removeUselessPortRealizations(port, false, true, false, false);
      }

      // move all deploying links
      for (AbstractDeploymentLink link : new ArrayList<AbstractDeploymentLink>(component.getDeployingLinks())) {
        link.setLocation(newComponent);
        newComponent.getOwnedDeploymentLinks().add(link);
      }

      removeUselessExchanges(component);
    }
    if ((pcMoved instanceof Part) && (newContainer instanceof Part)) {
      Part currentPart = (Part) pcMoved;
      Part newPart = (Part) newContainer;
      Component component = (Component) currentPart.getAbstractType();

      // move all exchanges
      moveComponentExchanges(component);

      // Remove all port outgoing allocations
      for (Port port : ComponentExt.getOwnedComponentPort(component)) {
        removeUselessPortRealizations(port, false, true, false, false);
      }

      // move all deploying links
      for (AbstractDeploymentLink link : new ArrayList<AbstractDeploymentLink>(currentPart.getDeployingLinks())) {
        link.setLocation(newPart);
        newPart.getOwnedDeploymentLinks().add(link);
      }

      removeUselessExchanges(currentPart);

    }

    return pcMoved;
  }

  /**
   * Perform a dnd of a port.
   * 
   * @param port
   */
  EObject removeUselessPortRealizations(Port port, boolean includeFunctionalRealization,
      boolean includeComponentRealization, boolean topDelegation, boolean bottomDelegation) {
    Collection<EObject> elements = new HashSet<>();

    if (port instanceof ComponentPort) {
      // Retrieve delegation to delete, according to parameters
      for (ComponentExchange exchange : PortExt.getDelegationComponentExchanges((ComponentPort) port)) {
        Component sourceComponent = ComponentExchangeExt.getSourceComponent(exchange);
        Component targetComponent = ComponentExchangeExt.getTargetComponent(exchange);
        Component containingPort = targetComponent;
        Component delegatedPort = sourceComponent;
        if (port.eContainer() != null) {
          if (port.eContainer().equals(sourceComponent)) {
            containingPort = sourceComponent;
            delegatedPort = targetComponent;
          }
        }

        if (topDelegation && CsServices.getService().getContainersOfParts(containingPort).contains(delegatedPort)) {
          elements.add(exchange);
        } else if (bottomDelegation
            && CsServices.getService().getContainersOfParts(delegatedPort).contains(containingPort)) {
          elements.add(exchange);
        }
      }
    }

    Collection<Allocation> allocations = new HashSet<>();
    allocations.addAll(port.getIncomingPortAllocations());
    allocations.addAll(port.getOutgoingPortAllocations());
    allocations.addAll(port.getIncomingPortRealizations());
    allocations.addAll(port.getOutgoingPortRealizations());

    // Retrieve port allocation according to parameters
    for (Allocation realization : allocations) {
      if (isValidAllocation(realization)) {
        if ((realization.getSourceElement() != null) && (realization.getTargetElement() != null)) {

          if (includeFunctionalRealization && (realization.getTargetElement() instanceof FunctionPort)) {
            elements.add(realization);
          }

          if ((port instanceof FunctionPort) && includeComponentRealization
              && (realization.getSourceElement() instanceof FunctionPort)) {
            elements.add(realization);
          }

          if (includeComponentRealization && ((realization.getTargetElement() instanceof ComponentPort)
              || (realization.getTargetElement() instanceof PhysicalPort))) {
            elements.add(realization);
          }
        }
      }
    }

    CapellaServices.getService().removeElements(elements);
    return port;
  }

  /**
   * Perform a dnd of a function port.
   * 
   * @param port
   * @param oldContainer
   * @param newContainer
   */
  public EObject dndABFunctionPort(FunctionPort port, NamedElement oldContainer, NamedElement newContainer) {

    removeUselessPortRealizations(port, true, true, false, false);

    // move the port in the new function container
    if (newContainer instanceof AbstractFunction) {
      AbstractFunction newFunction = (AbstractFunction) newContainer;
      AbstractFunction oldFunction = (AbstractFunction) port.eContainer();
      if (port instanceof FunctionInputPort) {
        for (ActivityEdge anEdge : ((FunctionInputPort) port).getIncoming()) {
          if (anEdge instanceof FunctionalExchange) {
            updateFunctionaChainInvolvementsOfFunctionalExchange((FunctionalExchange) anEdge, oldFunction, newFunction);
          }
        }
        newFunction.getInputs().add((FunctionInputPort) port);
      } else {
        for (ActivityEdge anEdge : ((FunctionOutputPort) port).getOutgoing()) {
          if (anEdge instanceof FunctionalExchange) {
            updateFunctionaChainInvolvementsOfFunctionalExchange((FunctionalExchange) anEdge, oldFunction, newFunction);
          }
        }
        newFunction.getOutputs().add((FunctionOutputPort) port);
      }
    }

    moveFunctionalExchanges(port);
    removeComponentExchangeAllocations(getFunctionalExchanges(port));

    return port;
  }

  /**
   * Perform a dnd of a physical port
   * 
   * @param port
   *          the given port
   * @param oldContainer
   *          the old view container
   * @param newContainer
   *          the new view container
   * @return the EObject
   */
  public EObject dndABPhysicalPort(PhysicalPort port, Part oldContainer, Part newContainer) {

    if (!port.eContainer().equals(newContainer.getType())) {
      removeUselessExchanges(port);
      removeUselessPortRealizations(port, true, true, false, false);

      ((Component) newContainer.getType()).getOwnedFeatures().add(port);
      updateExchanges(port, oldContainer, newContainer);
    }

    return port;
  }

  /**
   * Perform a dnd of a component port (standard port or flow port)
   * 
   * @param port
   *          the given port
   * @param oldContainer
   *          the old view container
   * @param newContainer
   *          the new view container
   * @return the EObject
   */
  public EObject dndABComponentPort(ComponentPort port, Part oldContainer, Part newContainer) {

    if (!port.eContainer().equals(newContainer.getType())) {
      removeUselessExchanges(port);
      removeUselessPortRealizations(port, true, true, false, false);

      ((Component) newContainer.getType()).getOwnedFeatures().add(port);
      updateComponentExchanges(port, oldContainer, newContainer);
    }

    return port;
  }

  /**
   * Move a component in the new container component
   * 
   * @param component
   *          the given component
   * @param container
   *          the given container component
   */
  protected void moveComponent(Component component, Component container) {
    if ((container instanceof Entity) && (component instanceof Entity)) {
      ((Entity) container).getOwnedEntities().add((Entity) component);
    } else if ((container instanceof SystemComponent) && (component instanceof SystemComponent)) {
      ((SystemComponent) container).getOwnedSystemComponents().add((SystemComponent) component);
    } else if ((container instanceof LogicalComponent) && (component instanceof LogicalComponent)) {
      ((LogicalComponent) container).getOwnedLogicalComponents().add((LogicalComponent) component);
    } else if ((container instanceof PhysicalComponent) && (component instanceof PhysicalComponent)) {
      ((PhysicalComponent) container).getOwnedPhysicalComponents().add((PhysicalComponent) component);
    }
  }

  /**
   * Move a component in the new container component package
   * 
   * @param component
   *          the given component
   * @param container
   *          the given container component package
   */
  protected void moveComponent(Component component, ComponentPkg container) {
    if ((container instanceof EntityPkg) && (component instanceof Entity)) {
      ((EntityPkg) container).getOwnedEntities().add((Entity) component);
    } else if ((container instanceof SystemComponentPkg) && (component instanceof SystemComponent)) {
      ((SystemComponentPkg) container).getOwnedSystemComponents().add((SystemComponent) component);
    } else if ((container instanceof LogicalComponentPkg) && (component instanceof LogicalComponent)) {
      ((LogicalComponentPkg) container).getOwnedLogicalComponents().add((LogicalComponent) component);
    } else if ((container instanceof PhysicalComponentPkg) && (component instanceof PhysicalComponent)) {
      ((PhysicalComponentPkg) container).getOwnedPhysicalComponents().add((PhysicalComponent) component);
    }
  }

  /**
   * Move an entity in the new container component package
   * 
   * @param entity
   *          the given entity
   * @param container
   *          the given container component package
   */
  protected void moveEntity(Entity entity, ComponentPkg container) {
    if ((container instanceof EntityPkg) && (entity instanceof Entity)) {
      ((EntityPkg) container).getOwnedEntities().add(entity);
    }
  }

  /**
   * Move an exchange in a visible place on both source/target
   * 
   * @param exchange
   *          the given componentExchange
   */
  protected void moveComponentExchange(ComponentExchange exchange) {
    ComponentExchangeExt.attachToDefaultContainer(exchange);
  }

  /**
   * Move an exchange in a visible place on both source/target
   * 
   * @param exchange
   *          the given functionalExchange
   */
  protected void moveFunctionalExchange(FunctionalExchange exchange) {
    EObject ancestor = CapellaServices.getService().getCommonAncestor(exchange.getSource(), exchange.getTarget());
    if (ancestor != exchange.eContainer() && ancestor instanceof AbstractFunction) {
      ((AbstractFunction) ancestor).getOwnedFunctionalExchanges().add(exchange);
    }
  }

  /**
   * Move an exchange in a visible place on both source/target
   * 
   * @param exchange
   *          the given PhysicalLink
   */
  protected void movePhysicalLink(PhysicalLink exchange) {
    EObject source = PhysicalLinkExt.getSourcePart(exchange);
    if (source == null) {
      source = PhysicalLinkExt.getSourcePort(exchange);
    }

    EObject target = PhysicalLinkExt.getTargetPart(exchange);
    if (target == null) {
      target = PhysicalLinkExt.getTargetPort(exchange);
    }

    EObject container = ComponentExt.getFirstCommonComponentOrPkgAncestor(source, target);
    if (!(container instanceof PhysicalComponent) && !(container instanceof ComponentPkg)) {
      container = BlockArchitectureExt.getComponentPkg(ComponentExt.getRootBlockArchitecture(exchange), true);
    }

    if (container != exchange.eContainer()) {
      ((PhysicalComponent) container).getOwnedPhysicalLinks().add(exchange);
    }

  }

  /**
   * Move exchanges related to a flow port
   * 
   * @param port
   *          the given flowPort
   */
  protected void updateExchanges(PhysicalPort port, Part oldPart, Part newPart) {
    for (PhysicalLink exchange : getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, port)) {
      if (!exchange.getOwnedPhysicalLinkEnds().isEmpty()) {
        for (PhysicalLinkEnd anEnd : exchange.getOwnedPhysicalLinkEnds()) {
          if (anEnd.getPort().equals(port) && anEnd.getPart().equals(oldPart)) {
            anEnd.setPart(newPart);
          }
        }
      }
      movePhysicalLink(exchange);
    }
  }

  /**
   * Move exchanges related to a flow port
   * 
   * @param port
   *          the given flowPort
   */
  protected void moveComponentExchanges(ComponentPort port) {
    for (ComponentExchange exchange : port.getComponentExchanges()) {
      moveComponentExchange(exchange);
    }
  }

  protected void moveComponentExchanges(Component component) {
    for (ComponentPort port : ComponentExt.getOwnedComponentPort(component)) {
      moveComponentExchanges(port);
    }
  }

  /**
   * Move exchanges related to a flow port
   * 
   * @param port
   *          the given flowPort
   */
  protected void updateComponentExchanges(ComponentPort port, Part oldPart, Part newPart) {
    for (ComponentExchange exchange : port.getComponentExchanges()) {
      if (!exchange.getOwnedComponentExchangeEnds().isEmpty()) {
        for (ComponentExchangeEnd anEnd : exchange.getOwnedComponentExchangeEnds()) {
          if ((anEnd.getPort() != null) && anEnd.getPort().equals(port) && (anEnd.getPart() != null)
              && anEnd.getPart().equals(oldPart)) {
            anEnd.setPart(newPart);
          }
        }
      }
      if ((oldPart != null) && oldPart.equals(exchange.getSource())) {
        exchange.setSource(newPart);
      }
      if ((oldPart != null) && oldPart.equals(exchange.getTarget())) {
        exchange.setTarget(newPart);
      }
      moveComponentExchange(exchange);
    }
  }

  /**
   * Move exchanges related to the function port
   * 
   * @param port
   *          the given functionPort
   */
  protected void moveFunctionalExchanges(FunctionPort port) {
    List<ActivityEdge> edges;
    List<FunctionalExchange> functionalExchanges = new ArrayList<>();

    if (port instanceof FunctionInputPort) {
      edges = ((FunctionInputPort) port).getIncoming();
    } else {
      edges = ((FunctionOutputPort) port).getOutgoing();
    }

    for (ActivityEdge edge : new ArrayList<ActivityEdge>(edges)) {
      if (edge instanceof FunctionalExchange) {
        functionalExchanges.add((FunctionalExchange) edge);
      }
    }

    for (FunctionalExchange exchange : functionalExchanges) {
      moveFunctionalExchange(exchange);
    }

    removeComponentExchangeAllocations(functionalExchanges);
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends AbstractFunction> getTableRootAbstractFunctions(BlockArchitecture architecture) {
    return getOwnedAbstractFunctions(getRootFunction(architecture));
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends FunctionPkg> getTableRootAbstractFunctionPkgs(BlockArchitecture architecture) {
    return getOwnedAbstractFunctionPkgs(getRootFunction(architecture));
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends AbstractFunction> getOwnedAbstractFunctions(AbstractFunction function) {
    return function.getOwnedFunctions();
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends AbstractFunction> getOwnedAbstractFunctions(FunctionPkg functionPkg) {
    return FunctionPkgExt.getOwnedFunctions(functionPkg);
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends FunctionPkg> getOwnedAbstractFunctionPkgs(FunctionPkg function) {
    return FunctionPkgExt.getOwnedFunctionPkgs(function);
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends FunctionPkg> getOwnedAbstractFunctionPkgs(AbstractFunction function) {
    return FunctionExt.getOwnedFunctionPkgs(function);
  }

  /**
   * Returns the root function of current architecture of the given element and create it if not found
   */
  public AbstractFunction getTableRootFunction(EObject element) {
    return getRootFunction(element);
  }

  /**
   * Returns the root function of current architecture of the given element and create it if not found
   */
  public AbstractFunction getRootFunction(EObject element) {
    return BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(element));
  }

  /**
   * Gets the functional exchanges related to the port
   * 
   * @param port
   *          the given port
   * @return the functional exchanges
   */
  protected Collection<FunctionalExchange> getFunctionalExchanges(Port port) {
    Collection<FunctionalExchange> exchanges = new HashSet<>();

    if (port instanceof ComponentPort) {
      exchanges = FunctionalExt.getFunctionalExchanges((ComponentPort) port);

    } else if (port instanceof FunctionPort) {

      Collection<ActivityEdge> edges;
      if (port instanceof FunctionInputPort) {
        edges = ((FunctionInputPort) port).getIncoming();
      } else {
        edges = ((FunctionOutputPort) port).getOutgoing();
      }

      for (ActivityEdge edge : new ArrayList<ActivityEdge>(edges)) {
        if (edge instanceof FunctionalExchange) {
          exchanges.add((FunctionalExchange) edge);
        }
      }
    }

    return exchanges;
  }

  /**
   * Returns whether the context decorator is from the given node eclass and is linked to edges from given edge eclass
   * 
   * @param context
   * @param diagram
   * @param node
   * @param edge
   * @return
   */
  public boolean isNodeWithoutEdge(EObject context, DDiagram diagram, EClass node, EClass edge) {
    if (diagram != null && context instanceof DSemanticDecorator) {
      DSemanticDecorator decorator = (DSemanticDecorator) context;
      if (!((decorator.getTarget() != null) && node.isInstance(decorator.getTarget()))) {
        return true;
      }

      if (context instanceof EdgeTarget) {
        EdgeTarget target = (EdgeTarget) context;
        for (DEdge incoming : target.getIncomingEdges()) {
          if ((incoming.getTarget() != null) && edge.isInstance(incoming.getTarget())) {
            return true;
          }
        }
        for (DEdge outgoing : target.getOutgoingEdges()) {
          if ((outgoing.getTarget() != null) && edge.isInstance(outgoing.getTarget())) {
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * Hide Component Port without Interfaces in Diagram
   * 
   * @param context
   *          : Function Port
   * @param diagram
   *          : DDiagram
   * @return true if PhysicalPort has PhysicalLink displayed in diagram.
   */
  public boolean isComponentPortWithoutInterfaces(EObject context, DDiagram diagram) {
    return isNodeWithoutEdge(context, diagram, FaPackage.Literals.COMPONENT_PORT, CsPackage.Literals.INTERFACE);
  }

  /**
   * Hide Physical Port without Exchanges in Diagram
   * 
   * @param context
   *          : Function Port
   * @param diagram
   *          : DDiagram
   * @return true if PhysicalPort has PhysicalLink displayed in diagram.
   */
  public boolean isPhysicalPortWithoutLinks(EObject context, DDiagram diagram) {
    return isNodeWithoutEdge(context, diagram, CsPackage.Literals.PHYSICAL_PORT, CsPackage.Literals.PHYSICAL_LINK);
  }

  /**
   * Hide Component Port without Exchanges in Diagram
   * 
   * @param context
   *          : Function Port
   * @param diagram
   *          : DDiagram
   * @return true if ComponentPort has ComponentExchange displayed in diagram.
   */
  public boolean isComponentPortWithoutExchanges(EObject context, DDiagram diagram) {
    return isNodeWithoutEdge(context, diagram, FaPackage.Literals.COMPONENT_PORT,
        FaPackage.Literals.COMPONENT_EXCHANGE);
  }

  /**
   * Hide Function Port without Exchanges in Diagram
   * 
   * @param context
   *          : Function Port
   * @param diagram
   *          : DDiagram
   * @return true if FunctionPort[input/output] has (incoming/outgoing)FunctionExchange displayed in diagram.
   */
  public boolean isFunctionPortWithoutExchanges(EObject context, DDiagram diagram) {
    return isNodeWithoutEdge(context, diagram, FaPackage.Literals.FUNCTION_PORT,
        FaPackage.Literals.FUNCTIONAL_EXCHANGE);
  }

  /**
   * This class contains useful data of a Node Container that represents a Function in a Data Flow Diagram
   */
  public class FunctionContainer {

    DNodeContainer container; // node container
    FunctionContainer parent; // parent of the functionContainer
    AbstractFunction function; // target function of the container
    Map<ExchangeCategory, DNode> incomingExchangeCategories; // incoming
    // exchange
    // categories
    // bordered
    // node on
    // the
    // container
    Map<ExchangeCategory, DNode> outgoingExchangeCategories; // outgoing
    // exchange
    // categories
    // bordered
    // node on
    // the
    // container
    Set<DNode> categoryNodesToRemove; // ExchangeCategory BorderedNodes to
    // remove

    Map<FunctionPort, DNode> functionPorts; // function ports on the

    // container

    public FunctionContainer(DNodeContainer container, FunctionContainer parent, DDiagram diagram,
        Map<AbstractFunction, AbstractDNode> functions) {
      this.container = container;
      this.parent = parent;
      function = (AbstractFunction) container.getTarget();
      incomingExchangeCategories = new HashMap<>();
      outgoingExchangeCategories = new HashMap<>();
      functionPorts = new HashMap<>();
      categoryNodesToRemove = new HashSet<>();

      String outputMappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(diagram);
      String inputMappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryInputPin(diagram);

      for (DNode aNode : container.getOwnedBorderedNodes()) {
        if (aNode.getTarget() instanceof FunctionPort) {
          functionPorts.put((FunctionPort) aNode.getTarget(), aNode);
        }
        if ((aNode.getTarget() != null) && (aNode.getActualMapping().getName().equals(inputMappingName))) {
          incomingExchangeCategories.put((ExchangeCategory) aNode.getTarget(), aNode);
        }
        if ((aNode.getTarget() != null) && (aNode.getActualMapping().getName().equals(outputMappingName))) {
          outgoingExchangeCategories.put((ExchangeCategory) aNode.getTarget(), aNode);
        }
      }

    }

    public void initCategoryNodesToRemove(Map<AbstractFunction, AbstractDNode> functions) {
      // init list of exchangeCategory Nodes to Remove
      for (Entry<ExchangeCategory, DNode> me : this.incomingExchangeCategories.entrySet()) {
        boolean toRemove = true;
        // check for each exchange of the category if it has the current
        // container as target function and if the source
        // Function is in the diagram
        for (FunctionalExchange anExchange : me.getKey().getExchanges()) {
          AbstractDNode targetPortContainer = getBestFunctionContainer(anExchange.getTarget(), functions);
          AbstractDNode sourcePortContainer = getBestFunctionContainer(anExchange.getSource(), functions);
          if ((targetPortContainer != null) && (sourcePortContainer != null)
              && targetPortContainer.equals(this.container)) {
            toRemove = false;
            break;
          }
        }
        if (toRemove) {
          categoryNodesToRemove.add(me.getValue());
        }
      }
      for (Entry<ExchangeCategory, DNode> me : this.outgoingExchangeCategories.entrySet()) {
        boolean toRemove = true;
        // check for each exchange of the category if it has the current
        // container as source function and if the target
        // Function is in the diagram
        for (FunctionalExchange anExchange : me.getKey().getExchanges()) {
          AbstractDNode targetPortContainer = getBestFunctionContainer(anExchange.getTarget(), functions);
          AbstractDNode sourcePortContainer = getBestFunctionContainer(anExchange.getSource(), functions);
          if ((targetPortContainer != null) && (sourcePortContainer != null)
              && sourcePortContainer.equals(this.container)) {
            toRemove = false;
            break;
          }
        }
        if (toRemove) {
          categoryNodesToRemove.add(me.getValue());
        }
      }
    }

    /**
     * @return the parent
     */
    public FunctionContainer getParent() {
      return parent;
    }

    /**
     * @return the container
     */
    public DNodeContainer getContainer() {
      return container;
    }

    /**
     * @return the function
     */
    public AbstractFunction getFunction() {
      return function;
    }

    /**
     * @return the exchangeCategories
     */
    public Map<ExchangeCategory, DNode> getIncomingExchangeCategories() {
      return incomingExchangeCategories;
    }

    /**
     * @return the exchangeCategories
     */
    public Map<ExchangeCategory, DNode> getOutgoingExchangeCategories() {
      return outgoingExchangeCategories;
    }

    public Set<DNode> getCategoryNodes() {
      Set<DNode> nodes = new HashSet<>();

      nodes.addAll(getIncomingExchangeCategories().values());
      nodes.addAll(getOutgoingExchangeCategories().values());
      return nodes;
    }

    /**
     * @return the categoryNodesToRemove
     */
    public Set<DNode> getCategoryNodesToRemove() {
      return categoryNodesToRemove;
    }

    public void setIncomingOutgoingExchangeCategories(DDiagram diagram) {
      this.incomingExchangeCategories = new HashMap<>();
      this.outgoingExchangeCategories = new HashMap<>();

      String outputMappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(diagram);
      String inputMappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryInputPin(diagram);

      for (DNode aNode : this.getContainer().getOwnedBorderedNodes()) {
        if ((aNode.getTarget() != null) && (aNode.getActualMapping().getName().equals(inputMappingName))) {
          incomingExchangeCategories.put((ExchangeCategory) aNode.getTarget(), aNode);
        }
        if ((aNode.getTarget() != null) && (aNode.getActualMapping().getName().equals(outputMappingName))) {
          outgoingExchangeCategories.put((ExchangeCategory) aNode.getTarget(), aNode);
        }
      }
    }

    /**
     * @return the functionPorts
     */
    public Map<FunctionPort, DNode> getFunctionPorts() {
      return functionPorts;
    }

  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  public void reorderFAElements(DDiagram diagram) {

    // all displayed elements in the diagram
    Map<EObject, DDiagramElement> elementsInDiagram = new HashMap<>();
    // diagram elements to be moved
    Set<DDiagramElement> toBeMoved;
    Map<AbstractFunction, AbstractDNode> allFunctionsInDiagram = new HashMap<>();
    DDiagramContents content = new DDiagramContents(diagram);

    // get all displayed functions and all displayed elements in the diagram

    for (DDiagramElement aContainer : diagram.getContainers()) {
      if ((aContainer != null) && (aContainer.getTarget() != null)
          && FaServices.getFaServices().isAbstractFunctionVisibleInDFB((AbstractDNode) aContainer, diagram)
          && !(aContainer.getTarget() instanceof PropertyValueGroup)
          && !(TitleBlockServices.getService().isAnnotation(aContainer.getTarget()))) {
        elementsInDiagram.put(aContainer.getTarget(), aContainer);
        if (aContainer.getTarget() instanceof AbstractFunction) {
          allFunctionsInDiagram.put((AbstractFunction) aContainer.getTarget(), (AbstractDNode) aContainer);
        }
      }
    }

    // The algorithm checks if the Control Nodes and Functions have to be
    // moved
    // it needs two iterations

    // first iteration (to avoid null container)
    // the elements to be moved are temporarily placed in the diagram
    toBeMoved = getAllElementToBeMoved(diagram, elementsInDiagram);

    // add to be moved elements to diagram
    for (DDiagramElement anElement : toBeMoved) {
      diagram.getOwnedDiagramElements().add(anElement);
    }

    // second iteration
    // the elements are correctly moved
    moveCorrectlyElements(elementsInDiagram, toBeMoved);

    // update all bordered Nodes
    updateAllBorderedNodes(diagram, content, allFunctionsInDiagram);

  }

  private void updateAllBorderedNodes(DDiagram diagram, DDiagramContents content,
      Map<AbstractFunction, AbstractDNode> allFunctionsInDiagram) {

    if (((DSemanticDiagram) diagram).getTarget() instanceof OperationalActivity)
      return;

    for (DDiagramElement anElement : diagram.getOwnedDiagramElements()) {
      if ((anElement instanceof DNodeContainer) && (anElement.getTarget() instanceof AbstractFunction)
          && (FaServices.getFaServices().isAbstractFunctionVisibleInDFB((DNodeContainer) anElement, diagram))) {
        updateBorderedNodes((DNodeContainer) anElement, content, null, allFunctionsInDiagram);
      }
    }

  }

  private void moveCorrectlyElements(Map<EObject, DDiagramElement> elementsInDiagram, Set<DDiagramElement> toBeMoved) {
    for (DDiagramElement anElement : toBeMoved) {
      // for each parent of the function to be moved, we tests if a
      // diagramElement representing the parent appears in
      // the diagram
      // When a parent is found in the diagram, we moved the function and
      // stop.
      EObject parent = anElement.getTarget().eContainer();
      while ((parent instanceof AbstractFunction) || (parent instanceof FunctionPkg)) {
        DDiagramElement parentGraphicalElement = elementsInDiagram.get(parent);
        if (parentGraphicalElement instanceof DNodeContainer) {
          DNodeContainer nodeContainer = (DNodeContainer) parentGraphicalElement;
          if (!nodeContainer.getOwnedDiagramElements().contains(anElement)) {
            nodeContainer.getOwnedDiagramElements().add(anElement);
            break;
          }
        }
        parent = parent.eContainer();
      }
    }

  }

  private Set<DDiagramElement> getAllElementToBeMoved(DDiagram diagram,
      Map<EObject, DDiagramElement> elementsInDiagram) {
    Set<DDiagramElement> toBeMoved = new HashSet<>();
    for (DDiagramElement anElement : elementsInDiagram.values()) {
      EObject parent = anElement.getTarget().eContainer();
      // case if the actual container is not available any more :
      // test if the container (A Function) is an ancestor of the current
      // AbstractFunction
      if (anElement.eContainer() instanceof DNodeContainer) {
        EObject actualParentContainer = ((DNodeContainer) anElement.eContainer()).getTarget();
        if (!org.eclipse.emf.ecore.util.EcoreUtil.isAncestor(actualParentContainer, anElement.getTarget())) {
          toBeMoved.add(anElement);
          continue;
        }
      }

      // case if the actual container is available for the current
      // AbstractFunction
      // but there is a better available container which is a closer
      // parent of the Abstract Function
      // example : in the model : A contains B. B Contains C.
      // in the diagram : A contains B and C. B contains nothing. => C
      // must be moved in container B

      // for each parent of the current function, we tests if a
      // diagramElement representing the parent appears in the
      // diagram
      // and if it contains the diagram element representing the current
      // function
      while ((parent instanceof AbstractFunction) || (parent instanceof FunctionPkg)) {
        // test if a parent of the function appears in the diagram
        DDiagramElement parentGraphicalElement = elementsInDiagram.get(parent);
        if (parentGraphicalElement instanceof DNodeContainer
            && CapellaServices.getService().isVisibleInDiagram(diagram, parentGraphicalElement)
            && canContainSubContainer(parentGraphicalElement)) {
          DNodeContainer nodeContainer = (DNodeContainer) parentGraphicalElement;
          // if the parent (diagramElement) does not contain the
          // current function (diagramElement)
          // the current function (diagramElement) must be moved
          if (!nodeContainer.getOwnedDiagramElements().contains(anElement)) {
            toBeMoved.add(anElement);
          }
          break;
        }
        parent = parent.eContainer();
      }
    }
    return toBeMoved;
  }

  /**
   * This method is necessary because of contextual Data Flow diagrams In contextual diagrams, only the current
   * contextual function can contain subContainers
   * 
   * @param aContainer
   *          a container
   * @return true if the container can contain sub containers
   */
  public boolean canContainSubContainer(DDiagramElement aContainer) {
    if (!(aContainer instanceof DNodeContainer)) {
      return false;
    }
    DSemanticDiagram currentDiagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(aContainer);
    if (currentDiagram.getDescription().getName().contains("Contextual")) { //$NON-NLS-1$
      return (aContainer.getTarget().equals(currentDiagram.getTarget()));
    }
    return true;
  }

  /**
   * remove/move recursively borderedNodes representing FunctionPorts or ExchangeCategories
   * 
   * @param container
   *          current container
   * @param diagram
   *          current diagram
   * @param parentContainer
   *          parent container
   * @param functions
   *          functions in diagram
   * @return
   */
  protected FunctionContainer updateBorderedNodes(DNodeContainer container, DDiagramContents content,
      FunctionContainer parentContainer, Map<AbstractFunction, AbstractDNode> functions) {

    FunctionContainer currentContainer = new FunctionContainer(container, parentContainer, content.getDDiagram(),
        functions);
    DDiagram diagram = content.getDDiagram();

    // function ports
    if (null != currentContainer.getParent()) {
      // for all parent port, if the port should be display in the
      // function, move or create it
      for (Map.Entry<FunctionPort, DNode> me : currentContainer.getParent().getFunctionPorts().entrySet()) {
        if (((me.getValue().eContainer() != null)
            && (((DDiagramElement) me.getValue().eContainer()).getTarget() != null))) {
          AbstractFunction best = getBestPortFunctionContainer((Pin) me.getKey(),
              (AbstractFunction) ((DDiagramElement) me.getValue().eContainer()).getTarget(),
              currentContainer.getFunction());

          if ((best != null) && best.equals(currentContainer.getFunction())) {
            if (currentContainer.getFunctionPorts().containsKey(me.getKey())) {
              // delete the bordered node on parent function if it
              // already exists on a child function
              removeNodeAndMoveEdges(me.getValue(), currentContainer.getFunctionPorts().get(me.getKey()),
                  content.getDDiagram());
            } else {
              // move the bordered Node on the child function
              currentContainer.getContainer().getOwnedBorderedNodes().add(me.getValue());
              currentContainer.getFunctionPorts().put(me.getKey(), me.getValue());
            }
          }
        }
      }
    }

    // Exchange Category Management
    if (parentContainer != null) {
      parentContainer.setIncomingOutgoingExchangeCategories(content.getDDiagram());

      // TODO BUG: In some tricky case where parent category is hidden,
      // categories on children are created but shouldn't
      HashMapSet<ExchangeCategory, AbstractFunction> availableCategories = FaServices.getFaServices()
          .getAvailableCategoriesAndFunctionsToInsertInDataFlowBlank(currentContainer.getContainer(), content);

      // For all categories of the parentContainer, propagate to children.
      // If not synchronized, create category exchanges in children only
      // if category
      // of parent container need to be removed.
      String outputMappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(diagram);
      String inputMappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryInputPin(diagram);

      for (DNode aNode : parentContainer.getCategoryNodes()) {
        if (!CapellaServices.getService().isSynchronized(content.getDDiagram())) {
          if (!parentContainer.getCategoryNodesToRemove().contains(aNode)) {
            continue;
          }
        }
        // avoid creation of category in child if parent node was hidden
        if (!aNode.isVisible()) {
          continue;
        }

        ExchangeCategory currentCategory = (ExchangeCategory) aNode.getTarget();
        if (availableCategories.containsKey(currentCategory)) {
          if (aNode.getActualMapping().getName().equals(outputMappingName)) {
            for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(aNode, diagram)) {
              if ((anEdge.getTargetNode() != null) && (anEdge.getTargetNode().eContainer() != null)) {
                DNodeContainer targetFunctionContainer = (DNodeContainer) anEdge.getTargetNode().eContainer();
                if (availableCategories.get(currentCategory).contains(targetFunctionContainer.getTarget())) {
                  FaServices.getFaServices().createViewExchangeCategory(currentCategory, container,
                      targetFunctionContainer, diagram);
                  if (!currentContainer.getOutgoingExchangeCategories().containsKey(currentCategory)) {
                    currentContainer.getOutgoingExchangeCategories().put(currentCategory,
                        getBorderedNode(container, currentCategory, outputMappingName));
                  }
                }
              }
            }
          }
          if (aNode.getActualMapping().getName().equals(inputMappingName)) {
            for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(aNode, diagram)) {
              if ((anEdge.getSourceNode() != null) && (anEdge.getSourceNode().eContainer() != null)) {
                DNodeContainer sourceFunctionContainer = (DNodeContainer) anEdge.getSourceNode().eContainer();
                if (availableCategories.get(currentCategory).contains(sourceFunctionContainer.getTarget())) {
                  FaServices.getFaServices().createViewExchangeCategory(currentCategory, sourceFunctionContainer,
                      container, diagram);
                  if (!currentContainer.getIncomingExchangeCategories().containsKey(currentCategory)) {
                    currentContainer.getIncomingExchangeCategories().put(currentCategory,
                        getBorderedNode(container, currentCategory, inputMappingName));
                  }
                }
              }
            }
          }
        }
      }
    }
    currentContainer.initCategoryNodesToRemove(functions);

    for (DDiagramElement anElement : container.getOwnedDiagramElements()) {
      // recursively update bordered nodes on contained visible
      // NodeContainers
      if ((anElement instanceof DNodeContainer) && (anElement.getTarget() instanceof AbstractFunction)
          && FaServices.getFaServices().isAbstractFunctionVisibleInDFB((DNodeContainer) anElement, diagram)) {
        updateBorderedNodes((DNodeContainer) anElement, content, currentContainer, functions);
      }
      // move up borderedNodes of invisible sub functions
      if ((anElement instanceof DNodeContainer) && (anElement.getTarget() != null)
          && (anElement.getTarget() instanceof AbstractFunction)
          && !FaServices.getFaServices().isAbstractFunctionVisibleInDFB((DNodeContainer) anElement, diagram)) {
        moveUpBorderedNodes((DNodeContainer) anElement, diagram, currentContainer);
      }
    }

    // remove Category Nodes
    for (DNode aNode : currentContainer.getCategoryNodesToRemove()) {
      DiagramServices.getDiagramServices().removeNodeView(aNode);
    }

    return currentContainer;
  }

  /**
   * move up borderedNodes of invisible functions
   * 
   * @param anElement
   * @param diagram
   * @param currentContainer
   * @param functions
   */
  public void moveUpBorderedNodes(DNodeContainer container, DDiagram diagram, FunctionContainer parentContainer) {

    Set<DDiagramElement> ownedDiagramElements = new HashSet<>();
    ownedDiagramElements.addAll(container.getOwnedDiagramElements());

    Set<DNode> ownedBorderedNodes = new HashSet<>();
    ownedBorderedNodes.addAll(container.getOwnedBorderedNodes());

    String outputMappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(diagram);
    String inputMappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryInputPin(diagram);

    // move up controlNodes as Ports and function ports
    for (DNode aBorderedNode : ownedBorderedNodes) {
      if (aBorderedNode.getTarget() instanceof FunctionPort
          && CapellaServices.getService().isVisibleInDiagram(diagram, aBorderedNode)
          && !CapellaServices.getService().isSynchronized(diagram)) {
        if (parentContainer.getFunctionPorts().containsKey(aBorderedNode.getTarget())) {
          // delete the bordered node on current function if it
          // already exists on the parent function
          removeNodeAndMoveEdges(aBorderedNode, parentContainer.getFunctionPorts().get(aBorderedNode.getTarget()),
              diagram);
        } else {
          // move the bordered Node on the parent function
          parentContainer.getContainer().getOwnedBorderedNodes().add(aBorderedNode);
          parentContainer.getFunctionPorts().put((FunctionPort) aBorderedNode.getTarget(), aBorderedNode);
        }
      }
      if (aBorderedNode.getTarget() instanceof ExchangeCategory
          && CapellaServices.getService().isVisibleInDiagram(diagram, aBorderedNode)) {
        if (aBorderedNode.getActualMapping().getName().equals(inputMappingName)) {
          if (!parentContainer.getIncomingExchangeCategories().containsKey(aBorderedNode.getTarget())) {
            // create borderedNode view on parent function
            DNode newNode = FaServices.getFaServices().createViewInputPinCategory(
                (ExchangeCategory) aBorderedNode.getTarget(), parentContainer.getContainer(), diagram);
            parentContainer.getIncomingExchangeCategories().put((ExchangeCategory) aBorderedNode.getTarget(), newNode);
          }
          // delete the bordered node on current function if it
          // already exists on the parent function and move edges
          removeNodeAndMoveEdges(aBorderedNode,
              parentContainer.getIncomingExchangeCategories().get(aBorderedNode.getTarget()), diagram);
        }
        if (aBorderedNode.getActualMapping().getName().equals(outputMappingName)) {
          if (!parentContainer.getOutgoingExchangeCategories().containsKey(aBorderedNode.getTarget())) {
            // create borderedNode view on parent function
            DNode newNode = FaServices.getFaServices().createViewOutputPinCategory(
                (ExchangeCategory) aBorderedNode.getTarget(), parentContainer.getContainer(), diagram);
            parentContainer.getOutgoingExchangeCategories().put((ExchangeCategory) aBorderedNode.getTarget(), newNode);
          }
          // delete the bordered node on current function if it
          // already exists on the parent function and move edges
          removeNodeAndMoveEdges(aBorderedNode,
              parentContainer.getOutgoingExchangeCategories().get(aBorderedNode.getTarget()), diagram);
        }
      }
    }

  }

  /**
   * @param port
   *          a FunctionPort or ControlNode
   * @param function1
   * @param function2
   * @return the function that must contain graphically the functionPort or controlNode
   */
  protected AbstractFunction getBestPortFunctionContainer(ActivityNode port, AbstractFunction function1,
      AbstractFunction function2) {
    EObject container = port.eContainer();
    while (container instanceof AbstractFunction) {
      if (container.equals(function1)) {
        return function1;
      }
      if (container.equals(function2)) {
        return function2;
      }
      container = container.eContainer();
    }
    return null;
  }

  /**
   * remove a node and move incoming and outgoing edges on the existing child/parent node
   * 
   * @param toBeRemoved
   *          the bordered node to remove
   * @param existingChildNode
   *          the existing child/parent borderedNode
   * @param diagram
   *          the current diagram
   */
  protected void removeNodeAndMoveEdges(DNode toBeRemoved, DNode existingChildNode, DDiagram diagram) {
    Set<DEdge> incomingExchanges = new HashSet<>();
    Set<DEdge> outgoingExchanges = new HashSet<>();
    incomingExchanges.addAll(DiagramServices.getDiagramServices().getIncomingEdges(existingChildNode, diagram));
    outgoingExchanges.addAll(DiagramServices.getDiagramServices().getOutgoingEdges(existingChildNode, diagram));

    for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(toBeRemoved, diagram)) {
      // move edge if necessary
      boolean toMove = true;
      for (DEdge anExistingEdge : incomingExchanges) {
        if ((anExistingEdge.getTarget() != null) && (anExistingEdge.getSourceNode() != null)
            && (anExistingEdge.getTarget().equals(anEdge.getTarget()))
            && (anExistingEdge.getSourceNode().equals(anEdge.getSourceNode()))) {
          toMove = false;
        }
      }
      if (toMove) {
        anEdge.setTargetNode(existingChildNode);
      } else {
        DiagramServices.getDiagramServices().removeEdgeView(anEdge);
      }
    }
    for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(toBeRemoved, diagram)) {
      // move edge if necessary
      boolean toMove = true;
      for (DEdge anExistingEdge : outgoingExchanges) {
        if ((anExistingEdge.getTarget() != null) && (anExistingEdge.getTargetNode() != null)
            && (anExistingEdge.getTarget().equals(anEdge.getTarget()))
            && (anExistingEdge.getTargetNode().equals(anEdge.getTargetNode()))) {
          toMove = false;
        }
      }
      if (toMove) {
        anEdge.setSourceNode(existingChildNode);
      } else {
        DiagramServices.getDiagramServices().removeEdgeView(anEdge);
      }
    }
    DiagramServices.getDiagramServices().removeNodeView(toBeRemoved);
  }

  /**
   * @param port
   * @param functions
   *          Map of visible containers in the diagram
   * @return the visible container in the diagram that must contain the port port
   */
  public AbstractDNode getBestFunctionContainer(ActivityNode port, Map<AbstractFunction, AbstractDNode> functions) {
    if ((port == null) || (port.eContainer() == null)) {
      return null;
    }
    EObject functionContainer = port.eContainer();
    while (functionContainer instanceof AbstractFunction) {
      if (functions.containsKey(functionContainer)) {
        return functions.get(functionContainer);
      }
      functionContainer = functionContainer.eContainer();
    }
    return null;
  }

  public DNode getBorderedNode(DNodeContainer container, EObject target, String mappingName) {
    DNode returnedNode = null;
    for (DNode aBorderedNode : container.getOwnedBorderedNodes()) {
      if ((aBorderedNode.getTarget() != null) && aBorderedNode.getTarget().equals(target)
          && aBorderedNode.getActualMapping().getName().equals(mappingName)) {
        return aBorderedNode;
      }
    }
    return returnedNode;
  }

  public List<AbstractFunction> getShowableParentOfAllocatedFunctions(AbstractFunction parent,
      List<AbstractFunction> showableLeaves) {
    return showableLeaves;

  }

  public List<AbstractFunction> getShowableAllocatedFunctions(EObject componentOrPart, DNodeContainer containerView) {

    // showable functions are:
    List<AbstractFunction> showableFunctions = new ArrayList<>();

    if (componentOrPart instanceof Part) {
      // - allocated functions of this components
      showableFunctions.addAll(
          ((Component) CsServices.getService().getComponentType((Part) componentOrPart)).getAllocatedFunctions());

    } else if (componentOrPart instanceof Component) {
      // - allocated functions of this components
      showableFunctions.addAll(((Component) componentOrPart).getAllocatedFunctions());
    }

    // - parent functions where all of their children are in this component or in
    // child components not displayed
    Set<AbstractFunction> leaves = getLeavesFunctionsOfSubComponentsNotDisplayed(componentOrPart, containerView);
    Set<AbstractFunction> allFunctions = AbstractFunctionExt.getRecursiveAllocatedFunctions(leaves, leaves);
    showableFunctions.addAll(allFunctions);

    return showableFunctions;
  }

  protected Set<AbstractFunction> getLeavesFunctionsOfSubComponentsNotDisplayed(EObject componentOrPart,
      DNodeContainer containerView) {
    Set<AbstractFunction> leaveFunctions = new HashSet<>();

    // Retrieve all allocated functions of this component or entities (including
    // roles)
    if (componentOrPart instanceof Part && ((Part) componentOrPart).getAbstractType() != null) {
      leaveFunctions.addAll(((Component) (((Part) componentOrPart).getAbstractType())).getAllocatedFunctions());

    } else if (componentOrPart instanceof Component) {
      leaveFunctions.addAll(((Component) componentOrPart).getAllocatedFunctions());
    }

    if (componentOrPart instanceof Entity) {
      Entity entity = (Entity) componentOrPart;
      for (Role role : entity.getAllocatedRoles()) {
        if (!DiagramServices.getDiagramServices().isOnDiagram(containerView, role)) {
          leaveFunctions.addAll(role.getAllocatedOperationalActivities());
        }
      }
    }

    // Add leaves of sub components only if it is not displayed, recursively
    Set<EObject> subComponents = new HashSet<>();
    if (componentOrPart instanceof Component) {
      subComponents.addAll(ComponentExt.getSubUsedComponents((Component) componentOrPart));

    } else if (componentOrPart instanceof Part) {
      subComponents.addAll(PartExt.getSubUsedAndDeployedParts((Part) componentOrPart));
    }

    for (EObject subComponent : subComponents) {
      if (!DiagramServices.getDiagramServices().isOnDiagram(containerView, subComponent)) {
        leaveFunctions.addAll(getLeavesFunctionsOfSubComponentsNotDisplayed(subComponent, containerView));
      }
    }

    return leaveFunctions;
  }

  public List<AbstractFunction> getShowableAllocatedOperationalActivities(Role role) {
    return AbstractFunctionExt.getAllocatedOperationalActivities(role);
  }

  /**
   * Unused
   */
  @Deprecated
  public DNodeContainer createViewDeployedPart(EObject target, DragAndDropTarget parent, DDiagram parentDiagram) {
    ContainerMapping mapping = getMappingABDeployedElement(parentDiagram);
    return DiagramServices.getDiagramServices().createContainer(mapping, target, parent, parentDiagram);
  }

  @Deprecated
  public ContainerMapping getMappingABDeployedElement(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABDeployedElement(diagram);
    return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
  }

  public boolean isLeaf(EObject function) {
    return FunctionExt.isLeaf((AbstractFunction) function);
  }

  /**
   * Get all the leaf Functions
   * 
   * @param arch
   * @return : List of leaf Functions
   */
  public List<AbstractFunction> getAllLeafAbstractFunctions(BlockArchitecture arch) {
    return getCache(FunctionExt::getAllLeafAbstractFunctions, arch);
  }

  public AbstractFunction getOutgoingAbstractFunction(FunctionalExchange fe) {
    return FunctionExt.getOutGoingAbstractFunction(fe);
  }

  public AbstractFunction getIncomingAbstractFunction(FunctionalExchange fe) {
    return FunctionExt.getIncomingAbstractFunction(fe);
  }

  public boolean isFunctionTargetOfExchange(AbstractFunction targetFunction, FunctionalExchange functionalExchange) {
    return getOutgoingAbstractFunction(functionalExchange) == targetFunction;
  }

  public boolean isFunctionSourceOfExchange(AbstractFunction sourceFunction, FunctionalExchange functionalExchange) {
    return getIncomingAbstractFunction(functionalExchange) == sourceFunction;
  }

  public EObject insertRemoveAllocatedFunctions(DNodeContainer containerView,
      List<AbstractFunction> selectedFunctions) {
    HashMap<AbstractFunction, DNode> visibleFunctions = new HashMap<>();

    for (DDiagramElement aElement : containerView.getOwnedDiagramElements()) {
      if (aElement.getTarget() instanceof AbstractFunction && aElement instanceof DNode) {
        visibleFunctions.put((AbstractFunction) aElement.getTarget(), (DNode) aElement);
      }
    }
    // delete not selected functions if they are displayed in the container
    // view
    for (Entry<AbstractFunction, DNode> me : visibleFunctions.entrySet()) {
      if (!selectedFunctions.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeNodeView(me.getValue());
      } else {
        for (AbstractFunction aParentFunction : FunctionExt.getParentFunctions(me.getKey())) {
          if (selectedFunctions.contains(aParentFunction)) {
            DiagramServices.getDiagramServices().removeNodeView(me.getValue());
            break;
          }
        }
      }
    }
    // create view for selected elements if they do not exist
    for (AbstractFunction aSelectedFunction : selectedFunctions) {
      if (!visibleFunctions.containsKey(aSelectedFunction)) {
        boolean toAdd = true;
        // test if a parent function is already displayed
        for (AbstractFunction aParentFunction : FunctionExt.getParentFunctions(aSelectedFunction)) {
          if (selectedFunctions.contains(aParentFunction)) {
            toAdd = false;
            break;
          }
        }
        if (toAdd) {
          createViewABAbstractFunction(aSelectedFunction, containerView,
              CapellaServices.getService().getDiagramContainer(containerView));
        }
      }
    }
    return containerView;
  }

  /**
   * Return all the leaf functions from given Block Architecture
   */
  public List<AbstractFunction> getAllLeafFunctions(BlockArchitecture blockArchitecture) {
    List<AbstractFunction> allAbstractFunctions = getCache(FunctionExt::getAllLeafAbstractFunctions, blockArchitecture);
    if (!allAbstractFunctions.isEmpty()) {
      return allAbstractFunctions;
    }

    return new ArrayList<>(0);
  }

  public List<AbstractFunction> getAllLeafFunctions(ModellingBlock block) {
    BlockArchitecture archi = BlockArchitectureExt.getRootBlockArchitecture(block);
    return getAllLeafFunctions(archi);
  }

  /**
   * Return all the recursive functions from given Block Architecture
   */
  public List<FunctionalChain> getAllFunctionalChains(BlockArchitecture blockArchitecture) {
    List<FunctionalChain> functionalChains = new ArrayList<>(0);

    // collect all functions
    List<AbstractFunction> allAbstractFunctions = FunctionExt.getAllAbstractFunctions(blockArchitecture);
    for (AbstractFunction abstractFunction : allAbstractFunctions) {
      functionalChains.addAll(abstractFunction.getOwnedFunctionalChains());
    }

    // collect all capabilities

    TableCapabilitiesServices cap = new TableCapabilitiesServices();
    Collection<AbstractCapability> allCapabilities = cap.getAllCapabilities(blockArchitecture);
    for (AbstractCapability abstractCapability : allCapabilities) {
      functionalChains.addAll(abstractCapability.getOwnedFunctionalChains());
    }

    return functionalChains;
  }

  public List<FunctionalChain> getAllFunctionalChains(ModellingBlock block) {
    BlockArchitecture archi = BlockArchitectureExt.getRootBlockArchitecture(block);
    return getAllFunctionalChains(archi);
  }

  /**
   * Perform dnd from diagram to diagram of a physical artifacts.
   * 
   * @param node
   *          the given physical artifacts
   * @param oldContainer
   *          the given namedElement
   * @param newContainer
   *          the given namedElement
   * @return the EObject
   */
  public EObject dndABPhysicalArtifacts(CapellaElement node, NamedElement oldContainer, NamedElement newContainer) {
    // Physical Artifacts == Physical Component, Physical Link and Physical
    // Port

    if (oldContainer.equals(newContainer)) {
      return node;
    }

    // Get root architecture, and make sure that its physical architecture
    BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(node);
    if (!(arch instanceof PhysicalArchitecture)) {
      return node;
    }

    // Collect all physical artifacts
    List<CapellaElement> physicalArtifacts = new ArrayList<>(0);
    List<PhysicalComponent> allPhysicalComponents = SystemEngineeringExt.getAllPhysicalComponents(arch);
    for (PhysicalComponent physicalComponent : allPhysicalComponents) {
      physicalArtifacts.add(physicalComponent);
      // collect all physical links
      physicalArtifacts.addAll(physicalComponent.getOwnedPhysicalLinks());
      // collect all physical ports
      physicalArtifacts.addAll(ComponentExt.getOwnedPhysicalPort(physicalComponent));
    }

    ConfigurationItem oldComponent = null;
    ConfigurationItem newComponent = null;

    if (oldContainer instanceof Part) {
      EObject componentType = CsServices.getService().getComponentType((Part) oldContainer);
      if (componentType instanceof ConfigurationItem) {
        oldComponent = (ConfigurationItem) componentType;
      }
    } else if (oldContainer instanceof ConfigurationItem) {
      oldComponent = (ConfigurationItem) oldContainer;
    }

    if (newContainer instanceof Part) {
      EObject componentType = CsServices.getService().getComponentType((Part) newContainer);
      if (componentType instanceof ConfigurationItem) {
        newComponent = (ConfigurationItem) componentType;
      }
    } else if (newContainer instanceof ConfigurationItem) {
      newComponent = (ConfigurationItem) newContainer;
    }

    if ((oldComponent != null) && (newComponent != null)) {
      // Move all related realization
      EList<PhysicalArtifactRealization> oldArtifactRealizations = oldComponent.getOwnedPhysicalArtifactRealizations();
      EList<PhysicalArtifactRealization> newArtifactRealizations = newComponent.getOwnedPhysicalArtifactRealizations();
      // list of target artifacts in newConatiner
      List<TraceableElement> newArtifactRealizationsTarget = new ArrayList<>();
      for (PhysicalArtifactRealization artifactRealization : newArtifactRealizations) {
        TraceableElement targetElement = artifactRealization.getTargetElement();
        if ((null != targetElement)) {
          newArtifactRealizationsTarget.add(targetElement);
        }
      }

      Iterator<PhysicalArtifactRealization> iterator = oldArtifactRealizations.iterator();
      while (iterator.hasNext()) {
        PhysicalArtifactRealization artifactRealization = iterator.next();
        TraceableElement targetElement = artifactRealization.getTargetElement();
        // check if already exist in newContainer, only move which is
        // asked to
        if ((null != targetElement) && physicalArtifacts.contains(targetElement) && node.equals(targetElement)
            && !newArtifactRealizationsTarget.contains(targetElement)) {
          artifactRealization.setSourceElement(newComponent);
          newComponent.getOwnedPhysicalArtifactRealizations().add(artifactRealization);
        }
      }
    }

    return node;
  }

  /**
   * @used context.odesign returns display name of functional exchange
   * @param exchange
   *          the functional exchange
   * @return display name of the functional exchange
   */
  public String getComponentExchangeLabel(ComponentExchange exchange, DDiagram diagram) {
    if ((exchange == null) || isHideComponentExchangesNamesEnable(exchange, diagram)
        || isHideCommunicationMeansNamesEnable(exchange, diagram)) {
      return ICommonConstants.EMPTY_STRING;
    }

    boolean showExchangeItems = false;
    boolean showFunctionalExchangeName = false;
    boolean showExchangeItemsWithOutFE = false;
    // check the activation of the filters
    for (FilterDescription filter : diagram.getActivatedFilters()) {
      if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_ITEMS_ON_CE)) {
        showExchangeItems = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FE_ON_CE)) {
        showFunctionalExchangeName = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_ITEMS_ON_CE_WITH_OUT_FE)) {
        showExchangeItemsWithOutFE = true;
      }
    }
    StringBuilder result = new StringBuilder();

    if (showFunctionalExchangeName) {
      ArrayList<FunctionalExchange> fes = new ArrayList<>();
      for (ComponentExchangeFunctionalExchangeAllocation allocation : exchange
          .getOutgoingComponentExchangeFunctionalExchangeAllocations()) {
        if ((allocation.getAllocatedFunctionalExchange() != null)
            && !fes.contains(allocation.getAllocatedFunctionalExchange())) {
          fes.add(allocation.getAllocatedFunctionalExchange());
        }
      }
      int index = 0;
      if (!showExchangeItemsWithOutFE) {
        for (FunctionalExchange fe : fes) {
          index++;
          getExchangeWithExchangeItemsLabel(fe, false, true, showExchangeItems, showExchangeItemsWithOutFE, result,
              getRelatedExchangeItems(fe));
          if (index < fes.size()) {
            result.append(", "); //$NON-NLS-1$
          }
        }
      }
      if (fes.isEmpty()) {
        List<AbstractExchangeItem> exchangedItems = getRelatedExchangeItems(exchange, false);
        getExchangeWithExchangeItemsLabel(exchange, false,
            (!showExchangeItems && !showExchangeItemsWithOutFE) || exchangedItems.isEmpty(), showExchangeItems,
            showExchangeItemsWithOutFE, result, exchangedItems);
      }

    } else {
      // create unique list
      List<AbstractExchangeItem> exchangedItems = new UniqueEList<>(0);
      // showExchangeItems
      if (showExchangeItems) {
        exchangedItems.addAll(getRelatedExchangeItems(exchange, true));
      }
      // showExchangeItemsWithOutFE
      if (showExchangeItemsWithOutFE) {
        exchangedItems.addAll(getRelatedExchangeItems(exchange, false));
      }
      getExchangeWithExchangeItemsLabel(exchange, false, (!showExchangeItems && !showExchangeItemsWithOutFE),
          showExchangeItems, showExchangeItemsWithOutFE, result, exchangedItems);

    }
    return result.toString();
  }

  /**
   * @used context.odesign returns display name of functional exchange
   * @param exchange
   *          the functional exchange
   * @return display name of the functional exchange
   */
  public String getFunctionalExchangeLabel(FunctionalExchange exchange, DDiagram diagram) {
    if (exchange == null) {
      return ICommonConstants.EMPTY_STRING;
    }
    if (isHideFunctionalExchangesNamesEnable(exchange, diagram) || isHideInteractionsNamesEnable(exchange, diagram)) {
      return Character.toString(ICommonConstants.WHITE_SPACE_CHARACTER);
    }

    boolean showExchangeItems = false;
    boolean showExchangeItemsParameters = false;
    boolean showFunctionalExchanges = false;
    boolean showFEEI = false;
    boolean showFEParams = false;
    boolean showFEEIParams = false;

    // check the activation of the filters
    for (FilterDescription filter : diagram.getActivatedFilters()) {
      if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_ITEMS)) {
        showExchangeItems = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_ITEMS_PARAMETERS)) {
        showExchangeItemsParameters = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES)) {
        showFunctionalExchanges = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS)) {
        showFEEI = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES_PARAMS)) {
        showFEParams = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS_PARAMS)) {
        showFEEIParams = true;
      }
    }

    if (showFEEI) {
      return getFEEIMessageName(exchange);
    }
    if (showFEEIParams || showFEParams) {
      return showFeEiParams(exchange, showFEEIParams);
    }

    StringBuilder result = new StringBuilder();

    // exchangeItems or name
    int indice = 0;
    if (showFunctionalExchanges || showExchangeItems || showExchangeItemsParameters) {
      if (showFunctionalExchanges) {
        result.append(getSafeName(exchange));
      }
      if (showExchangeItems || showExchangeItemsParameters) {
        List<? extends AbstractExchangeItem> selectEIList = exchange.getExchangedItems();
        if (!selectEIList.isEmpty()) {
          result.append("["); //$NON-NLS-1$
        }
        for (AbstractExchangeItem ei : selectEIList) {
          result.append(InformationServices.getEILabel(ei, showExchangeItemsParameters));
          indice++;
          if (indice < selectEIList.size()) {
            result.append(", "); //$NON-NLS-1$
          }
        }
        if (!selectEIList.isEmpty()) {
          result.append("]"); //$NON-NLS-1$
        }
      }
    } else {
      result.append(EObjectExt.getText(exchange));
    }
    return result.toString();
  }

  private String showFeEiParams(FunctionalExchange exchange, boolean showEIName) {
    StringBuilder result = new StringBuilder();
    List<? extends AbstractExchangeItem> selectEIList;
    selectEIList = exchange.getExchangedItems();

    result.append(getSafeName(exchange));

    int indice = 0;
    if (showEIName) {
      result.append(" "); //$NON-NLS-1$
      result.append("["); //$NON-NLS-1$
      for (AbstractExchangeItem ei : selectEIList) {
        result.append(InformationServices.getEILabel(ei, true));
        indice++;
        if (indice < selectEIList.size()) {
          result.append(", "); //$NON-NLS-1$
        }
      }
      result.append("]"); //$NON-NLS-1$

    } else {
      result.append("("); //$NON-NLS-1$
      // looking for information
      List<ExchangeItemElement> eies = new ArrayList<>();
      for (AbstractExchangeItem aei : selectEIList) {
        if (aei instanceof ExchangeItem) {
          ExchangeItem ei = (ExchangeItem) aei;
          eies.addAll(ei.getOwnedElements());
        }
      }
      // using gathered information
      for (ExchangeItemElement eie : eies) {
        AbstractType type = eie.getAbstractType();
        if (type != null) {
          result.append(getSafeName(type));
        } else {
          result.append("<undefined>"); //$NON-NLS-1$
        }
        indice++;
        if (indice < eies.size()) {
          result.append(", "); //$NON-NLS-1$
        }

      }
      result.append(")"); //$NON-NLS-1$
    }

    return result.toString();
  }

  private String getFEEIMessageName(FunctionalExchange exchange) {
    StringBuilder result = new StringBuilder();
    List<? extends AbstractExchangeItem> selectEIList;
    selectEIList = exchange.getExchangedItems();
    result.append(EObjectExt.getText(exchange));
    result.append(" "); //$NON-NLS-1$
    result.append("["); //$NON-NLS-1$
    int indice = 0;

    for (AbstractExchangeItem ei : selectEIList) {
      result.append(InformationServices.getEILabel(ei, false));
      indice++;
      if (indice < selectEIList.size()) {
        result.append(", "); //$NON-NLS-1$
      }
    }

    result.append("]"); //$NON-NLS-1$

    return result.toString();
  }

  private List<AbstractExchangeItem> getRelatedExchangeItems(ComponentExchange exchange, boolean inDepth) {
    // exchangeItems
    List<AbstractExchangeItem> exchangedItems = new ArrayList<>();
    exchangedItems.addAll(exchange.getConvoyedInformations());
    if (inDepth) {
      for (ComponentExchangeFunctionalExchangeAllocation allocation : exchange
          .getOutgoingComponentExchangeFunctionalExchangeAllocations()) {
        if (allocation.getAllocatedFunctionalExchange() != null) {
          for (AbstractExchangeItem abstractExchangeItem : getRelatedExchangeItems(
              allocation.getAllocatedFunctionalExchange())) {
            if (!exchangedItems.contains(abstractExchangeItem)) {
              exchangedItems.add(abstractExchangeItem);
            }
          }
        }
      }
    }

    return exchangedItems;
  }

  private List<AbstractExchangeItem> getRelatedExchangeItems(FunctionalExchange exchange) {
    // exchangeItems
    List<AbstractExchangeItem> exchangedItems = new ArrayList<>();

    exchangedItems.addAll(exchange.getExchangedItems());
    if (exchangedItems.isEmpty()) {
      for (CapellaElement capellaElement : AbstractFunctionExt.getExchangeSourceAndTargetPorts(exchange)) {
        if (capellaElement instanceof FunctionInputPort) {
          FunctionInputPort inPutPort = (FunctionInputPort) capellaElement;
          // collect exchange items of ports
          for (ExchangeItem abstractExchangeItem : inPutPort.getIncomingExchangeItems()) {
            if (!exchangedItems.contains(abstractExchangeItem)) {
              exchangedItems.add(abstractExchangeItem);
            }
          }
        } else if (capellaElement instanceof FunctionOutputPort) {
          FunctionOutputPort inPutPort = (FunctionOutputPort) capellaElement;
          // collect exchange items of ports
          for (ExchangeItem abstractExchangeItem : inPutPort.getOutgoingExchangeItems()) {
            if (!exchangedItems.contains(abstractExchangeItem)) {
              exchangedItems.add(abstractExchangeItem);
            }
          }
        }
      }
    }
    return exchangedItems;
  }

  private void getExchangeWithExchangeItemsLabel(AbstractNamedElement exchange, boolean showExchangeItemsParameters,
      boolean showName, boolean showExchangeItems, boolean showExchangeItemsWithOutFE, StringBuilder result,
      List<AbstractExchangeItem> exchangedItems) {
    int indice = 0;
    if (showName) {
      result.append(EObjectExt.getText(exchange));
    }

    if (showExchangeItems || showExchangeItemsWithOutFE) {
      if (showName && !exchangedItems.isEmpty()) {
        result.append(" ("); //$NON-NLS-1$
      }
      for (AbstractExchangeItem ei : exchangedItems) {
        result.append(ExchangeItemExt.getEILabel(ei, showExchangeItemsParameters));
        indice++;
        if (indice < exchangedItems.size()) {
          result.append(", "); //$NON-NLS-1$
        }
      }
      if (showName && !exchangedItems.isEmpty()) {
        result.append(")"); //$NON-NLS-1$
      }
    }

  }

  public DDiagramContents getDDiagramContents(DDiagram diagram) {
    return new DDiagramContents(diagram);
  }

  /**
   * @param diagram
   * @param chains
   */
  public void showABFunctionalChains(DDiagram diagram, Collection<EObject> chains) {
    showABFunctionalChains(diagram, chains, getDDiagramContents(diagram));
  }

  /**
   * @param diagram
   * @param chains
   */
  public void showDFFunctionalChains(DDiagram diagram, Collection<EObject> chains) {
    showDFFunctionalChains(diagram, chains, getDDiagramContents(diagram));
  }

  /**
   * @param diagram
   * @param chains
   */
  public void showABFunctionalChains(DDiagram diagram, Collection<EObject> chains, DDiagramContents context) {
    for (EObject object : chains) {
      if (context.getNode(object) == null) {
        createViewABFunctionalChain(diagram, object);
      }
    }
  }

  /**
   * @param diagram
   * @param chains
   */
  public void showDFFunctionalChains(DDiagram diagram, Collection<EObject> chains, DDiagramContents context) {
    for (EObject object : chains) {
      if (context.getNode(object) == null) {
        createViewDFFunctionalChain(diagram, object);
      }
    }
  }

  /**
   * @param diagram
   * @param object
   */
  private DNode createViewABFunctionalChain(DDiagram diagram, EObject object) {
    NodeMapping mapping = getMappingABFunctionalChain(object, diagram);
    return DiagramServices.getDiagramServices().createNode(mapping, object, diagram, diagram);
  }

  /**
   * @param diagram
   * @param object
   */
  private DNode createViewDFFunctionalChain(DDiagram diagram, EObject object) {
    NodeMapping mapping = getMappingDFFunctionalChain(object, diagram);
    return DiagramServices.getDiagramServices().createNode(mapping, object, diagram, diagram);
  }

  /**
   * @param port
   * @param diagram
   * @return
   */
  public NodeMapping getMappingABFunctionalChain(EObject port, DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABFunctionalChain(diagram);
    return DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
  }

  /**
   * @param port
   * @param diagram
   * @return
   */
  public NodeMapping getMappingDFFunctionalChain(EObject port, DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingDFFunctionalChain(diagram);
    return DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
  }

  /**
   * @param diagram
   * @param contextualElements
   */
  public void showDFContextualElements(DDiagramContents diagramContent, Collection<EObject> contextualElements) {
    Collection<AbstractFunction> contextualFunctions = new HashSet<>();
    Collection<FunctionalExchange> contextualFunctionalExchanges = new HashSet<>();
    Collection<EObject> contextualFunctionalChains = new HashSet<>();
    Collection<EObject> contextualModes = new HashSet<>();
    Collection<EObject> contextualScenarios = new HashSet<>();

    for (EObject contextualElement : contextualElements) {
      if (contextualElement instanceof AbstractFunction) {
        contextualFunctions.add((AbstractFunction) contextualElement);

        for (AbstractFunction function : getCache(FunctionExt::getAllAbstractFunctions,
            (AbstractFunction) contextualElement)) {
          for (FunctionalExchange exchange : FunctionExt.getIncomingExchange(function)) {
            AbstractFunction source = FunctionalExchangeExt.getSourceFunction(exchange);
            AbstractFunction target = FunctionalExchangeExt.getTargetFunction(exchange);
            // Restrict to outside exchanges
            if ((source != null) && (target != null)) {
              if (!(EcoreUtil2.isContainedBy(source, contextualElement)
                  && (EcoreUtil2.isContainedBy(target, contextualElement)))) {
                contextualFunctionalExchanges.add(exchange);
              }
            }
          }
          for (FunctionalExchange exchange : FunctionExt.getOutGoingExchange(function)) {
            AbstractFunction source = FunctionalExchangeExt.getSourceFunction(exchange);
            AbstractFunction target = FunctionalExchangeExt.getTargetFunction(exchange);
            // Restrict to outside exchanges
            if ((source != null) && (target != null)) {
              if (!(EcoreUtil2.isContainedBy(source, contextualElement)
                  && (EcoreUtil2.isContainedBy(target, contextualElement)))) {
                contextualFunctionalExchanges.add(exchange);
              }
            }
          }
        }
      } else if (contextualElement instanceof FunctionalChain) {
        FunctionalChain functionalChain = (FunctionalChain) contextualElement;
        contextualFunctionalChains.add(functionalChain);

        // get all the involvements, including those from involved functional chains
        Collection<FunctionalChainInvolvement> flatInvolvements = FunctionalChainExt
            .getFlatInvolvements(functionalChain);

        for (FunctionalChainInvolvement involvement : flatInvolvements) {
          InvolvedElement involved = involvement.getInvolved();
          if (involved instanceof AbstractFunction) {
            contextualFunctions.add((AbstractFunction) involved);
          } else if (involved instanceof FunctionalExchange) {
            contextualFunctionalExchanges.add((FunctionalExchange) involved);
          }
        }

      } else if (contextualElement instanceof Scenario) {
        contextualScenarios.add(contextualElement);

      } else if (contextualElement instanceof State) {
        contextualModes.add(contextualElement);

      }
    }

    // Display all contextual functions
    for (AbstractFunction function : contextualFunctions) {
      showDFAbstractFunction(function, diagramContent.getBestContainer(function), diagramContent);
    }

    // Create a customized diagram contents to retrieve brothers of diagram
    // elements instead of best container
    DDiagramContents extendedContent = new DDiagramContents(diagramContent) {

      @Override
      public EObject getElement(EObject object, EObject context) {
        if (context instanceof FunctionalExchange) {
          FunctionalExchange exchange = (FunctionalExchange) context;
          AbstractFunction sourceExchange = FunctionalExchangeExt.getSourceFunction(exchange);
          AbstractFunction targetExchange = FunctionalExchangeExt.getTargetFunction(exchange);

          AbstractFunction brother = null;
          AbstractFunction brother2 = null;

          if (object.equals(sourceExchange)) {
            brother = targetExchange;
            brother2 = sourceExchange;

          } else if (object.equals(targetExchange)) {
            brother = sourceExchange;
            brother2 = targetExchange;
          }

          if (brother != null) {
            EObject parent = brother;
            while (parent != null) {
              EObject parent2 = brother2;
              while (parent2 != null) {
                if (isVisible(parent, parent2)) {
                  return parent2;
                }
                parent2 = parent2.eContainer();
              }
              parent = parent.eContainer();
            }
          }
        }
        return object;
      }

      private boolean isVisible(EObject brother, EObject brother2) {
        EObject parent = brother.eContainer();
        if ((parent instanceof AbstractFunction) && (brother2 instanceof AbstractFunction)) {
          if (getCache(FunctionExt::getFirstLevelAbstractFunctions, (AbstractFunction) parent).contains(brother2)) {
            return true;
          }
        }
        return false;
      }

    };

    // Display all contextual functionalExchanges
    for (FunctionalExchange functionalExchange : contextualFunctionalExchanges) {
      showDFFunctionalExchange(null, functionalExchange, extendedContent, true);
    }

    showDFFunctionalChains(diagramContent.getDDiagram(), contextualFunctionalChains, diagramContent);

    DFServices.getService().showDFScenarios((DSemanticDecorator) diagramContent.getDDiagram(), contextualScenarios);
    DFServices.getService().showDFStateModes((DSemanticDecorator) diagramContent.getDDiagram(), contextualModes);
  }

  /**
   * is [FUNCTIONKIND = FUNCTION] and not AcotorFunction or ControlNode
   * 
   * @param element
   * @return
   */
  public boolean isFunction(AbstractFunction element) {
    return FunctionExt.isFunction(element);
  }

  /**
   * is [FUNCTIONKIND = FUNCTION] and not AcotorFunction or ControlNode
   * 
   * @param element
   * @return
   */
  public boolean isActorFunction(AbstractFunction element) {
    return FunctionExt.isActorFunction(element);
  }

  public boolean isAllLeavesFunctionActorALlocated(AbstractFunction element) {
    int i, j = 0;
    if ((element == null) || isLeaf(element)) {
      return false;
    }
    List<AbstractFunction> leaves = getCache(FunctionExt::getAllLeafAbstractFunctions, element);
    i = leaves.size();
    for (AbstractFunction af : leaves) {
      if (FunctionExt.isActorFunction(af)) {
        j++;
      }
    }
    return (i != 0) && (i == j);

  }

  private boolean isDiagramFilterEnable(EObject exchange, EObject diagram, String filterName) {
    if (null != diagram) {
      // get Diagram
      DDiagram diag = CapellaServices.getService().getDiagramContainer(diagram);
      if (diag != null) {
        EList<FilterDescription> activatedFilters = diag.getActivatedFilters();
        for (FilterDescription filterDescription : activatedFilters) {
          // if given filter is enable return true
          if ((null != filterDescription) && filterDescription.getName().equalsIgnoreCase(filterName)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isHideFunctionalExchangesNamesEnable(EObject fe, DDiagram diagram) {
    return isDiagramFilterEnable(fe, diagram, IMappingNameConstants.HIDE_FUNCTIONAL_EXCHANGES_NAMES);
  }

  public boolean isHideComponentExchangesNamesEnable(EObject ce, EObject view) {
    return isDiagramFilterEnable(ce, view, IMappingNameConstants.HIDE_COMPONENT_EXCHANGES_NAMES);
  }

  public boolean isHideCommunicationMeansNamesEnable(EObject ce, EObject view) {
    return isDiagramFilterEnable(ce, view, IMappingNameConstants.HIDE_COMMUNICATION_MEANS_NAMES);
  }

  public boolean isHideInteractionsNamesEnable(EObject fe, DDiagram diagram) {
    return isDiagramFilterEnable(fe, diagram, IMappingNameConstants.HIDE_INTERACTIONS_NAMES);
  }

  public boolean isHidePhysicalLinksNamesEnable(EObject pl, EObject view) {
    return isDiagramFilterEnable(pl, view, IMappingNameConstants.HIDE_PHYSICAL_LINKS_NAMES);
  }

  public boolean isHideOverlappedPhysicalPathsIconEnable(EObject pl, EObject view) {
    return isDiagramFilterEnable(pl, view, IMappingNameConstants.HIDE_OVERLAPPED_PHYSICAL_PATHS_ICON);
  }
  
  public boolean isHideOverlappedPhysicalPathsLabelEnable(EObject pl, EObject view) {
    return isDiagramFilterEnable(pl, view, IMappingNameConstants.HIDE_OVERLAPPED_PHYSICAL_PATHS_LABEL);
  }
  
  public boolean isHideOverlappedFunctionalChainsIconEnable(EObject pl, EObject view) {
    return isDiagramFilterEnable(pl, view, IMappingNameConstants.HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_ICON);
  }
  
  public boolean isHideOverlappedFunctionalChainsLabelEnable(EObject pl, EObject view) {
    return isDiagramFilterEnable(pl, view, IMappingNameConstants.HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_LABEL);
  }
  
  public String getExchangeCenterLabel(EObject exchange, DDiagram diagram) {
    // why white space char
    // The manual refresh of the diagram does not take into account the
    // EmptySting
    String centerLabel = Character.toString(ICommonConstants.WHITE_SPACE_CHARACTER);
    if (exchange instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) exchange;
      if (!isHideFunctionalExchangesNamesEnable(exchange, diagram)) {
        return fe.getName();
      }
    }
    return centerLabel;
  }

  public String getPhysicalLinkCenterLabel(EObject exchange, DDiagram diagram) {
    // why white space char
    // The manual refresh of the diagram does not take into account the
    // EmptySting
    String centerLabel = Character.toString(ICommonConstants.WHITE_SPACE_CHARACTER);
    if (exchange instanceof PhysicalLink) {
      PhysicalLink pl = (PhysicalLink) exchange;
      if (!isHidePhysicalLinksNamesEnable(exchange, diagram)) {
        return EObjectExt.getText(pl);
      }
    }
    return centerLabel;
  }

  public String getOverlappedPhysicalPathsLabel(EObject exchange, EObject view, DDiagram diagram) {
    if (exchange instanceof PhysicalLink && view instanceof DEdge) {
      DEdge edge = (DEdge) view;
      String oldLabel = edge.getBeginLabel();
      // Label calculation is called when the cache is not ready (outside of the refresh), return the old label
      if (DEdgeIconCache.getInstance().getLabel(edge) == null) {
        return oldLabel;
      }
      if (isHideOverlappedPhysicalPathsLabelEnable(exchange, diagram)) {
        return Character.toString(ICommonConstants.POINT_CHARACTER);
      }
      return DEdgeIconCache.getInstance().getLabel(edge);
    }
    return ICommonConstants.EMPTY_STRING;
  }
  
  public String getOverlappedFunctionalChainsLabel(EObject exchange, EObject view, DDiagram diagram) {
    if (exchange instanceof FunctionalExchange && view instanceof DEdge) {
      DEdge edge = (DEdge) view;
      String oldLabel = edge.getBeginLabel();
      // Label calculation is called when the cache is not ready (outside of the refresh), return the old label
      if (DEdgeIconCache.getInstance().getLabel(edge) == null) {
        return oldLabel;
      }
      if (isHideOverlappedFunctionalChainsLabelEnable(exchange, diagram)) {
        return Character.toString(ICommonConstants.POINT_CHARACTER);
      }
      return DEdgeIconCache.getInstance().getLabel(edge);
    }
    return ICommonConstants.EMPTY_STRING;
  }
  
  /**
   * Get the Node mapping for Functional Exchange category pin
   * 
   * @param diagram
   * @return
   */
  @Deprecated
  public NodeMapping getMappingFECategoryOutputPin(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(diagram);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
  }

  @Deprecated
  public NodeMapping getMappingFECategoryInputPin(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryInputPin(diagram);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
  }

  /**
   * Get the edge mapping for the Functional Exchange category
   * 
   * @param diagram
   * @return
   */
  @Deprecated
  public EdgeMapping getMappingFECategory(DDiagram diagram) {
    return getMappingExchangeCategory(diagram);
  }

  /**
   * Do a switch Functional Exchange / Category
   * 
   * @param context
   * @param scope
   * @param initialSelection
   * @param selectedExchangeCategories
   *          in tool, the categories chosen by the user, in refresh, the displayed categories
   * @return
   */
  public EObject switchFECategories(DSemanticDecorator context, Collection<EObject> scope,
      Collection<EObject> initialSelection, Collection<ExchangeCategory> selectedExchangeCategories) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    return switchFECategories(content, context, selectedExchangeCategories, true);
  }

  public EObject switchFEInvisibleCategories(DDiagramContents content, DSemanticDecorator context,
      Collection<ExchangeCategory> selectedElements) {

    DDiagram currentDiagram = content.getDDiagram();
    Collection<DDiagramElement> invisibleCategoryEdges = new HashSet<DDiagramElement>();
    for (DDiagramElement element : content.getDiagramElements(
        content.getMapping(MappingConstantsHelper.getMappingFunctionalExchangeCategory(currentDiagram)))) {
      if (!element.isVisible()) {
        invisibleCategoryEdges.add(element);
      }
    }

    for (DDiagramElement categoryEdge : invisibleCategoryEdges) {
      EObject categoryObj = categoryEdge.getTarget();
      EObject srcFunc = ((DDiagramElement) ((DEdge) categoryEdge).getSourceNode().eContainer()).getTarget();
      EObject tarFunc = ((DDiagramElement) ((DEdge) categoryEdge).getTargetNode().eContainer()).getTarget();

      if (categoryObj instanceof ExchangeCategory) {
        AbstractShowHide invCatSwitch = new ShowHideInvisibleExchangeCategory(content);
        DiagramContext ctx = invCatSwitch.new DiagramContext();
        if (selectedElements.contains(categoryObj)) {
          showFECategory(invCatSwitch, ctx, (ExchangeCategory) categoryObj, srcFunc, tarFunc, true);
        } else {
          showFECategory(invCatSwitch, ctx, (ExchangeCategory) categoryObj, srcFunc, tarFunc, false);
        }
      }
    }

    content.commitDeferredActions();
    return context;
  }

  @Deprecated
  public EObject switchFECategories(DDiagramContents content, DSemanticDecorator context,
      Collection<ExchangeCategory> selectedElements) {
    return switchFECategories(content, context, selectedElements, true);
  }

  public EObject switchFECategories(DDiagramContents content, DSemanticDecorator context,
      Collection<ExchangeCategory> selectedExchangeCategories, boolean showHiddenExchanges) {

    switchFEInvisibleCategories(content, context, selectedExchangeCategories);

    DDiagram currentDiagram = content.getDDiagram();

    Collection<DDiagramElement> functionRelatedDiagramElements = new HashSet<>();
    for (DDiagramElement element : content
        .getDiagramElements(content.getMapping(MappingConstantsHelper.getMappingABAbstractFunction(currentDiagram)))) {
      functionRelatedDiagramElements.add(element);
    }

    AbstractShowHide showHideExchangeCategoryService = new ShowHideExchangeCategory(content);

    List<AbstractFunction> abstractFunctions = functionRelatedDiagramElements.stream().map(DDiagramElement::getTarget)
        .distinct().filter(AbstractFunction.class::isInstance).map(AbstractFunction.class::cast)
        .collect(Collectors.toList());

    // 1. SHOW / HIDE EDGES OF EXCHANGE CATEGORIES
    // Display the categories between parts if they are part of selectedElements, or
    // hide them
    showHideExchangeCategoryEdges(content, abstractFunctions, selectedExchangeCategories,
        showHideExchangeCategoryService);

    // 2. SHOW / HIDE EDGES OF FUNCTIONAL EXCHANGES
    showHideFunctionalExchanges(abstractFunctions, selectedExchangeCategories, showHideExchangeCategoryService,
        showHiddenExchanges);

    // 3.
    content.commitDeferredActions();

    return context;
  }

  private void showHideExchangeCategoryEdges(DDiagramContents content, List<AbstractFunction> abstractFunctions,
      Collection<ExchangeCategory> selectedExchangeCategories, AbstractShowHide showHideExchangeCategoryService) {

    DiagramContext ctx = showHideExchangeCategoryService.new DiagramContext();

    for (AbstractFunction targetFunction : abstractFunctions) {

      Map<ExchangeCategory, Set<Map.Entry<AbstractFunction, AbstractFunction>>> categoryToSourceTargetMap = getExchangeCategoryToSourceTargetMap(
          getCache(FunctionExt::getAllExchanges, targetFunction));

      for (Entry<ExchangeCategory, Set<Map.Entry<AbstractFunction, AbstractFunction>>> entry : categoryToSourceTargetMap
          .entrySet()) {
        ExchangeCategory category = entry.getKey();
        Set<Map.Entry<AbstractFunction, AbstractFunction>> sourceTargetSet = entry.getValue();

        for (Map.Entry<AbstractFunction, AbstractFunction> sourceTarget : sourceTargetSet) {
          AbstractFunction source = sourceTarget.getKey();
          AbstractFunction target = sourceTarget.getValue();

          if (selectedExchangeCategories.contains(category)) {
            // Show the exchange category edge
            showFECategory(showHideExchangeCategoryService, ctx, category, source, target, true);
          } else {

            // TODO ADD getBestContainer to cache

            // Hide the exchange category edge
            showFECategory(showHideExchangeCategoryService, ctx, category, getBestFunctionContainer(source, content),
                getBestFunctionContainer(target, content), false);
          }
        }
      }
    }
  }

  private void showHideFunctionalExchanges(List<AbstractFunction> abstractFunctions,
      Collection<ExchangeCategory> selectedExchangeCategories, AbstractShowHide showHideExchangeCategoryService,
      boolean showHiddenExchanges) {
    // In tool (showHiddenExchanges==true), user may have removed some categories,
    // so he wants to display hidden
    // exchanges associated to them.
    // In refresh (showHiddenExchanges==false), categories haven't been changed by
    // the user, so he doesn't want to
    // display hidden exchanges,
    // he just want to hide new exchanges associated to displayed categories.
    DiagramContext ctx = showHideExchangeCategoryService.new DiagramContext();

    for (AbstractFunction targetFunction : abstractFunctions) {
      List<FunctionalExchange> allExchanges = getCache(FunctionExt::getAllExchanges, targetFunction);
      Map<ExchangeCategory, Set<Map.Entry<AbstractFunction, AbstractFunction>>> categoryToSourceTargetMap = getExchangeCategoryToSourceTargetMap(
          allExchanges);

      for (Entry<ExchangeCategory, Set<Map.Entry<AbstractFunction, AbstractFunction>>> entry : categoryToSourceTargetMap
          .entrySet()) {
        ExchangeCategory category = entry.getKey();

        for (FunctionalExchange functionalExchange : allExchanges) {
          if (functionalExchange.getCategories().contains(category)) {
            if (selectedExchangeCategories.contains(category)) {
              // Hide the functional exchange edge
              showHideExchangeCategoryService.hide(functionalExchange, ctx);
            } else {
              if (showHiddenExchanges) {
                // Show the functional exchange edge
                // Only in tool when user switches functional exchange vs exchange category
                showHideExchangeCategoryService.show(functionalExchange, ctx);
              }
            }
          }
        }
      }
    }
  }

  /**
   * 
   * @param abstractFunction
   * @param content
   * @return The best container for a function, taking into account hidden functions
   */
  public static EObject getBestFunctionContainer(EObject abstractFunction, DDiagramContents content) {

    if (abstractFunction instanceof AbstractFunction) {

      for (DDiagramElement element : content.getDiagramElements(abstractFunction)) {
        if (element.isVisible()) {
          // If there is at least one representative view for the function, return the
          // function itself
          return abstractFunction;

        }
      }

      // Else, return its best container
      DragAndDropTarget node = content.getBestContainer(abstractFunction);
      if (node instanceof DDiagram) {
        return null;
      } else if (node instanceof DSemanticDecorator) {
        return ((DSemanticDecorator) node).getTarget();
      } else {
        return abstractFunction.eContainer();
      }
    }

    return null;
  }

  /**
   * Retrieve a map<ExchangeCategory, FunctionalExchange> of available category to display from the given source view
   * 
   * @param context
   * @return
   */
  public HashMapSet<EObject, EObject> getShowHideFECategoriesScope(DSemanticDecorator context) {
    HashMapSet<EObject, EObject> result = new HashMapSet<>();
    EObject abstractFunction = context.getTarget();
    if (abstractFunction instanceof AbstractFunction) {
      for (FunctionalExchange fe : FunctionExt.getOutGoingExchange((AbstractFunction) abstractFunction)) {
        for (ExchangeCategory value : fe.getCategories()) {
          result.put(value, FunctionExt.getOutGoingAbstractFunction(fe));
        }
      }

      for (FunctionalExchange fe : FunctionExt.getIncomingExchange((AbstractFunction) abstractFunction)) {
        for (ExchangeCategory value : fe.getCategories()) {
          result.put(value, FunctionExt.getIncomingAbstractFunction(fe));
        }
      }
    }
    return result;
  }

  private Map<ExchangeCategory, Set<Map.Entry<AbstractFunction, AbstractFunction>>> getExchangeCategoryToSourceTargetMap(
      List<FunctionalExchange> functionalExchanges) {
    HashMap<ExchangeCategory, Set<Map.Entry<AbstractFunction, AbstractFunction>>> result = new HashMap<>();
    for (FunctionalExchange fe : functionalExchanges) {
      for (ExchangeCategory category : fe.getCategories()) {
        Map.Entry<AbstractFunction, AbstractFunction> sourceTarget = new AbstractMap.SimpleEntry<>(
            FunctionExt.getIncomingAbstractFunction(fe), FunctionExt.getOutGoingAbstractFunction(fe));
        Set<Entry<AbstractFunction, AbstractFunction>> sourceTargetSet = result.computeIfAbsent(category,
            k -> new HashSet<>());
        sourceTargetSet.add(sourceTarget);
      }
    }
    return result;
  }

  public Collection<EObject> getSwitchFECategoriesScope(DSemanticDecorator context) {
    if (context instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<>();
      DDiagramContents ctx = new DDiagramContents((DDiagram) context);

      EdgeMapping dfFEMapping = getMappingDFFunctionalExchange(ctx.getDDiagram());
      EdgeMapping abFEMapping = getMappingABFunctionalExchange(ctx.getDDiagram());

      for (DDiagramElement element : ctx.getDiagramElements(dfFEMapping)) {
        if (element.getTarget() instanceof FunctionalExchange) {
          values.addAll(((FunctionalExchange) element.getTarget()).getCategories());
        }
      }

      for (DDiagramElement element : ctx.getDiagramElements(abFEMapping)) {
        if (element.getTarget() instanceof FunctionalExchange) {
          values.addAll(((FunctionalExchange) element.getTarget()).getCategories());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> scope = getShowHideFECategoriesScope(context);
    return scope.keySet();

  }

  /**
   * Retrieve all Functional Exchange for the related Category
   * 
   * @param element
   * @return
   */
  public Collection<FunctionalExchange> getRelatedFunctionalExchanges(EObject element) {
    if (element instanceof ExchangeCategory) {
      return ((ExchangeCategory) element).getExchanges();

    }
    return Collections.emptyList();
  }

  /**
   * Show/Hide a FE category from the source to the target
   * 
   * @param categories
   * @param context
   * @param key
   * @param source
   * @param target
   * @param b
   */
  private void showFECategory(AbstractShowHide categories, DiagramContext context, ExchangeCategory key, EObject source,
      EObject target, boolean b) {

    context.setVariable(ShowHideABComponent.SOURCE_PARTS, Collections.singletonList(source));
    context.setVariable(ShowHideABComponent.TARGET_PARTS, Collections.singletonList(target));

    if (b) {
      categories.show(key, context);
    } else {
      categories.hide(key, context);
    }
  }

  /**
   * Show all FE categories in the scope
   * 
   * @param context
   * @param scope
   * @param initialSelection
   * @param selectedElements
   * @return
   */
  public EObject showFECategories(DSemanticDecorator context, HashMapSet<EObject, EObject> scope,
      HashMapSet<EObject, EObject> initialSelection, final HashMapSet<EObject, EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    EObject source = context.getTarget();

    AbstractShowHide shService = new ShowHideExchangeCategory(content);
    DiagramContext ctx = shService.new DiagramContext();
    if (context instanceof DDiagramElement) {
      ctx.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context));
    }

    for (EObject key : scope.keySet()) {
      for (EObject value : scope.get(key)) {
        // If the category is not in the the list of selected
        // categories, hide it
        if (!selectedElements.containsKey(key) || !selectedElements.get(key).contains(value)) {
          if (initialSelection.containsKey(key) && initialSelection.get(key).contains(value)) {
            showFECategory(shService, ctx, (ExchangeCategory) key, source, value, false);
            showFECategory(shService, ctx, (ExchangeCategory) key, value, source, false);
          }
        }
      }
    }

    for (EObject key : selectedElements.keySet()) {
      for (EObject target : selectedElements.get(key)) {
        showFECategory(shService, ctx, (ExchangeCategory) key, source, target, true);
        showFECategory(shService, ctx, (ExchangeCategory) key, target, source, true);
      }

    }

    content.commitDeferredActions();

    return context;
  }

  /**
   * Retrieve the initial selection of displayed category for the given source view
   * 
   * @param context
   * @return
   */
  public Collection<EObject> getSwitchFECategoriesInitialSelection(DSemanticDecorator context) {
    if (context instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<>();
      DDiagramContents ctx = new DDiagramContents((DDiagram) context);
      for (DDiagramElement element : ctx.getDiagramElements(getMappingExchangeCategory(ctx.getDDiagram()))) {
        if (element.getTarget() instanceof CapellaElement) {
          values.add(element.getTarget());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> result = getShowHideFECategoriesInitialSelection(context);
    return result.keySet();
  }

  /**
   * Retrieve the initial selection of displayed category for the given source view
   * 
   * @param context
   * @return
   */
  public HashMapSet<EObject, EObject> getShowHideFECategoriesInitialSelection(DSemanticDecorator context) {
    HashMapSet<EObject, EObject> scope = getShowHideFECategoriesScope(context);
    HashMapSet<EObject, EObject> result = new HashMapSet<>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(context);
    EdgeMapping edgeMapping = getMappingExchangeCategory(diagram);
    DDiagramContents content = new DDiagramContents(diagram);

    for (EObject key : scope.keySet()) {
      for (EObject targetPart : scope.get(key)) {
        for (DDiagramElement elementView : content.getDiagramElements(key, edgeMapping)) {
          if (elementView instanceof DEdge) {
            result.put(key, targetPart);
          }
        }
      }
    }
    return result;
  }

  /**
   * Check if the input is a FunctionOutputPort
   * 
   * @param activityNode
   * @return
   */
  public boolean isAFunctionOutputPort(EObject activityNode) {
    return activityNode instanceof FunctionOutputPort;
  }

  /**
   * Check is the input is not a leaf and if all of their leaves are FunctionActor allocated
   * 
   * @param function
   * @return
   */
  public boolean isNotLeafAndisAllLeavesFunctionActorAllocated(AbstractFunction function) {
    return !isLeaf(function) && isAllLeavesFunctionActorALlocated(function);
  }

  /**
   * Check if the input is an OutputPin
   * 
   * @param portAllocation
   * @return
   */
  public boolean isAOutputPin(PortAllocation portAllocation) {
    return portAllocation.getAllocatedPort() instanceof OutputPin;
  }

  /**
   * Check if the input is not a leaf
   * 
   * @param systemFunction
   * @return
   */
  public boolean isNotLeaf(EObject systemFunction) {
    return !isLeaf(systemFunction);
  }

  public List<PortAllocation> getDisplayedPortAllocations(DNodeContainer selectedElement) {
    List<PortAllocation> result = new ArrayList<>();
    List<PortAllocation> allAllocations = getAllPortAllocationAvailable(selectedElement);

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(selectedElement);
    for (PortAllocation portAllocation : allAllocations) {
      if (DiagramServices.getDiagramServices().getDiagramElement(diagram, portAllocation) != null)
        result.add(portAllocation);
    }
    return result;
  }

  /**
   * @param context
   * @return called by show/hide Port Allocations tool (Physical Architecture Blank Diagram)
   */
  public List<PortAllocation> getAllPortAllocationAvailable(DNodeContainer selectedElement) {
    List<PortAllocation> result = new ArrayList<>();

    for (DNode dNode : selectedElement.getNodes()) {
      if (dNode.getTarget() instanceof ComponentPort) {
        EObject target = dNode.getTarget();
        for (AbstractTrace trace : ((ComponentPort) target).getOutgoingTraces()) {
          if (trace instanceof PortAllocation) {
            result.add((PortAllocation) trace);
          }
        }
      } else if (dNode.getTarget() instanceof FunctionPort) {
        EObject target = dNode.getTarget();
        for (AbstractTrace trace : ((FunctionPort) target).getIncomingTraces()) {
          if (trace instanceof PortAllocation) {
            result.add((PortAllocation) trace);
          }
        }
      }
    }
    return result;
  }

  /**
   * @param context
   * @return called by show/hide Port Allocations tool (Physical Architecture Blank Diagram)
   */
  public List<PortAllocation> getAvailablePortAllocationToInsert(DNodeContainer selectedElement, DDiagram diagram) {
    List<PortAllocation> allAllocations = getAllPortAllocationAvailable(selectedElement);
    List<PortAllocation> existingAllocations = getDisplayedPortAllocations(selectedElement);

    allAllocations.removeAll(existingAllocations);
    return allAllocations;
  }
}
