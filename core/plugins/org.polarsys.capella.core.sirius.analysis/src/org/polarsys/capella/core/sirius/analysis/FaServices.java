/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
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
import org.eclipse.sirius.diagram.description.Layer;
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
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.sirius.decorators.loader.SiriusDecoratorsManager;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.CtxFactory;
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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ExchangeItemExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
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
@SuppressWarnings("deprecation")
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
    return fe.getName();
  }

  /**
   * @param context_p
   *          current context
   * @param source_p
   *          selected source of the edge
   * @return if a FunctionalExchange can be created from source_p
   */
  public boolean isValidCreationFunctionalExchange(EObject context_p, EObject source_p) {

    if ((source_p instanceof InputPin) || !(source_p instanceof ActivityNode)) {
      return false;
    }

    AbstractFunction sourceFunction = FunctionExt.getRelatedFunction((ActivityNode) source_p);

    if (FunctionExt.isControlNodeOneOutput(sourceFunction)) {
      if (!(FunctionExt.getOutGoingExchange(sourceFunction).isEmpty() || FunctionExt.getOutGoingExchange(sourceFunction).contains(context_p))) {
        return false;
      }
      if (!((source_p instanceof OutputPin) || sourceFunction.getOutputs().isEmpty())) {
        return false;
      }
    }

    return true;
  }

  /**
   * @param context_p
   *          current context
   * @param source_p
   *          selected source of the edge
   * @param target_p
   *          selected target of the edge
   * @return if a FunctionalExchange can be created between source_p and target_p
   */
  public boolean isValidCreationFunctionalExchange(EObject context_p, EObject source_p, EObject target_p) {

    if ((target_p instanceof OutputPin) || !(target_p instanceof ActivityNode)) {
      return false;
    }

    AbstractFunction sourceFunction = FunctionExt.getRelatedFunction((ActivityNode) source_p);
    AbstractFunction targetFunction = FunctionExt.getRelatedFunction((ActivityNode) target_p);
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
      if (!(FunctionExt.getIncomingExchange(targetFunction).isEmpty() || FunctionExt.getIncomingExchange(targetFunction).contains(context_p))) {
        return false;
      }
      if (!((target_p instanceof InputPin) || targetFunction.getInputs().isEmpty())) {
        return false;
      }
    }

    return isValidCreationFunctionalExchange(context_p, source_p);
  }

  /**
   * @param context_p
   * @return used in logical.odesign oa.odesign, physical.odesign called by show/hide FunctionalExchanges tools
   *         (DataFlow Blank Diagrams)
   */
  public List<FunctionalExchange> getAvailableFunctionalExchangesToInsert(DSemanticDecorator context_p) {
    List<FunctionalExchange> returnedList = new ArrayList<FunctionalExchange>();
    List<FunctionalExchange> allFunctionalExchanges = new ArrayList<FunctionalExchange>();
    List<FunctionalExchange> existingExchangesInDiagram = new ArrayList<FunctionalExchange>();
    AbstractFunction selectedFunction = null;

    if (!(context_p instanceof AbstractDNode)) {
      return returnedList;
    }

    for (DEdge anEdge : CapellaServices.getService().getDiagramContainer(context_p).getEdges()) {
      if (anEdge.getTarget() instanceof FunctionalExchange) {
        existingExchangesInDiagram.add((FunctionalExchange) anEdge.getTarget());
      }
    }
    if (context_p.getTarget() instanceof AbstractFunction) {
      selectedFunction = (AbstractFunction) context_p.getTarget();
      for (AbstractFunction currentFunction : FunctionExt.getAllAbstractFunctions(selectedFunction)) {
        allFunctionalExchanges.addAll(FunctionExt.getIncomingExchange(currentFunction));
        allFunctionalExchanges.addAll(FunctionExt.getOutGoingExchange(currentFunction));
      }
    }

    // if the following part is not commented => you must modify the
    // beforeRefresh for the dataFlow
    for (FunctionalExchange aFunctionalExchange : allFunctionalExchanges) {
      if ((aFunctionalExchange.getTarget() == null) || (aFunctionalExchange.getSource() == null)
          || (EcoreUtil.isAncestor(selectedFunction, aFunctionalExchange.getSource()) && EcoreUtil.isAncestor(selectedFunction, aFunctionalExchange.getTarget()))) {
        continue;
      }
      returnedList.add(aFunctionalExchange);
    }
    return returnedList;
  }

  /**
   * @param selectedElement_p
   * @return displayed incoming and outgoing functional Exchanges called by show/hide FunctionalExchanges tools
   *         (DataFlow Blank Diagrams) used in oa, logical, context, physical
   */
  public List<FunctionalExchange> getDisplayedFunctionalExchanges(DSemanticDecorator selectedElement_p) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    // current DiagramElements
    if (selectedElement_p instanceof AbstractDNode) {
      result = getDisplayedFunctionalExchangesFromAbstractDNode((AbstractDNode) selectedElement_p);

      // Consider Sub Containers of current DiagramElement
      List<DNodeContainer> allContainers = DiagramServices.getDiagramServices().getAllContainers(selectedElement_p);
      for (DNodeContainer dNodeContainer : allContainers) {
        List<FunctionalExchange> subFunctionEdges = getDisplayedFunctionalExchangesFromAbstractDNode(dNodeContainer);
        if (!subFunctionEdges.isEmpty()) {
          result.addAll(subFunctionEdges);
        }
      }

      // Consider Sub Nodes of current DiagramElement
      List<DNode> allNodes = DiagramServices.getDiagramServices().getAllNodes(selectedElement_p);
      for (DNode aDNode : allNodes) {
        List<FunctionalExchange> subFunctionEdges = getDisplayedFunctionalExchangesFromAbstractDNode(aDNode);
        if (!subFunctionEdges.isEmpty()) {
          result.addAll(subFunctionEdges);
        }
      }
    }

    return result;
  }

  /**
   * get Functional Exchange edges from current AbstractNode
   * 
   * @param selectedElement_p
   * @return
   */
  public List<FunctionalExchange> getDisplayedFunctionalExchangesFromAbstractDNode(AbstractDNode selectedElement_p) {
    List<FunctionalExchange> returnedList = new ArrayList<FunctionalExchange>();
    List<DEdge> incomingOutgoingEdges = new ArrayList<DEdge>();

    if (selectedElement_p.getTarget() instanceof AbstractFunction) {
      // consider the boarder nodes (inputpin, outputpin)
      for (DNode aNode : selectedElement_p.getOwnedBorderedNodes()) {
        if ((aNode.getTarget() instanceof InputPin)) {
          incomingOutgoingEdges.addAll(CapellaServices.getService().getIncomingEdges(aNode));
        }
        if ((aNode.getTarget() instanceof OutputPin)) {
          incomingOutgoingEdges.addAll(CapellaServices.getService().getOutgoingEdges(aNode));
        }
      }
      // consider operational activity
      if (selectedElement_p.getTarget() instanceof OperationalActivity) {
        incomingOutgoingEdges.addAll(CapellaServices.getService().getIncomingEdges((EdgeTarget) selectedElement_p));
        incomingOutgoingEdges.addAll(CapellaServices.getService().getOutgoingEdges((EdgeTarget) selectedElement_p));
      }
    }
    // filter functionalExchanges
    for (DEdge anEdge : incomingOutgoingEdges) {
      if (anEdge.getTarget() instanceof FunctionalExchange) {
        returnedList.add((FunctionalExchange) anEdge.getTarget());
      }
    }
    return returnedList;
  }

  public boolean isOriented(ComponentExchange connection) {
    return connection.isOriented();
  }

  @Deprecated
  public Collection<ComponentExchange> getDisplayedComponentExchanges(DNodeContainer selectedElement_p) {
    return getDisplayedComponentExchanges(selectedElement_p);
  }

  @Deprecated
  public Collection<ComponentExchange> getAvailableComponentExchangesToInsert(DNodeContainer context_p) {
    return getAvailableComponentExchangesToInsert(context_p);
  }

  /**
   * used in oa, logical, context, physical
   * 
   * @param selectedElement_p
   * @return called by show/hide ComponentExchanges tools (Architecture Blank Diagrams)
   */
  public Collection<ComponentExchange> getDisplayedConnections(DNodeContainer selectedElement_p) {
    Collection<ComponentExchange> returnedList = new HashSet<ComponentExchange>();
    List<DEdge> edges = new ArrayList<DEdge>();

    for (DNode aNode : selectedElement_p.getOwnedBorderedNodes()) {
      if (aNode.getTarget() instanceof ComponentPort) {
        edges.addAll(CapellaServices.getService().getIncomingEdges(aNode));
        edges.addAll(CapellaServices.getService().getOutgoingEdges(aNode));
      }
    }

    edges.addAll(CapellaServices.getService().getIncomingEdges(selectedElement_p));
    edges.addAll(CapellaServices.getService().getOutgoingEdges(selectedElement_p));

    for (DEdge anEdge : edges) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof ComponentExchange)) {
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
   * @param context_p
   * @return called by show/hide ComponentExchanges tools (Architecture Blank Diagrams)
   */
  public Collection<ComponentExchange> getAvailableConnectionsToInsert(DNodeContainer context_p) {
    List<ComponentExchange> returnedList = new ArrayList<ComponentExchange>();
    List<ComponentExchange> allComponentExchanges = new ArrayList<ComponentExchange>();

    EObject currentComponent = null;
    Part currentPart = null;

    if (context_p.getTarget() instanceof Component) {
      currentComponent = context_p.getTarget();
    } else if (context_p.getTarget() instanceof Part) {
      currentPart = (Part) context_p.getTarget();
      currentComponent = CsServices.getService().getComponentType((Part) context_p.getTarget());
    }

    if ((currentComponent != null) && (currentComponent instanceof Component)) {

      if (currentComponent instanceof Component) {
        for (ComponentPort aPort : ComponentExt.getOwnedComponentPort((Component) currentComponent)) {
          allComponentExchanges.addAll(aPort.getComponentExchanges());
        }
      }

      if (context_p.getTarget() instanceof InformationsExchanger) {

        for (AbstractInformationFlow aFlow : ((InformationsExchanger) context_p.getTarget()).getInformationFlows()) {
          if (aFlow instanceof ComponentExchange) {
            allComponentExchanges.add((ComponentExchange) aFlow);
          }
        }
      }

    }

    for (ComponentExchange connection : allComponentExchanges) {
      // Add connection if is related to the current part if any
      if (!CsServices.getService().isMultipartMode(connection) || !((currentPart != null) && !FunctionalExt.getRelatedParts(connection).contains(currentPart))) {
        returnedList.add(connection);
      }
    }
    return returnedList;
  }

  /**
   * used in oa, logical, context, physical
   * 
   * @param context_p
   * @return called by show/hide FunctionalExchanges tools (Architecture Blank Diagrams)
   */
  public List<FunctionalExchange> getAvailableFunctionalExchangesToInsertInArchitectureBlank(AbstractDNode context_p) {
    List<FunctionalExchange> returnedList = new ArrayList<FunctionalExchange>();
    List<FunctionalExchange> allFunctionalExchanges = getAvailableFunctionalExchangesToInsert(context_p);
    AbstractFunction selectedFunction = (AbstractFunction) context_p.getTarget();
    for (FunctionalExchange anExchange : allFunctionalExchanges) {
      AbstractFunction targetFunction;
      if (anExchange.getTarget().equals(selectedFunction) || anExchange.getTarget().eContainer().equals(selectedFunction)) {
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

  private DNodeContainer getDisplayedFunctionContainer(EObject function_p, Collection<DNodeContainer> containers_p) {
    EObject currentFunction = function_p;
    while ((currentFunction != null) && (currentFunction instanceof AbstractFunction)) {
      for (DNodeContainer aContainer : containers_p) {
        if (aContainer.getTarget().equals(currentFunction)) {
          return aContainer;
        }
      }
      currentFunction = currentFunction.eContainer();
    }
    return null;
  }

  /**
   * @param semantic_p
   * @param dDiagram_p
   * @return
   */
  public DiagramElementMapping getMappingABRole(Role semantic_p, DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_ROLE_MAPPING_NAME;

    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.ORB_ROLE_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);

  }

  public ContainerMapping getMappingABComponent(EObject component_p, DDiagram diagram_p) {
    EObject component = component_p;
    if ((component != null) && (component instanceof Part)) {
      component = CsServices.getService().getComponentType((Part) component_p);
    }

    EClass clazz = CsPackage.Literals.COMPONENT;
    if (component != null) {
      clazz = component.eClass();
    }
    return getMappingABComponent(clazz, diagram_p);
  }

  public ContainerMapping getMappingABComponent(EClass clazz_p, DDiagram diagram_p) {
    EClass absActor = CsPackage.Literals.ABSTRACT_ACTOR;

    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME;
      if (absActor.isSuperTypeOf(clazz_p)) {
        mappingName = IMappingNameConstants.PAB_PHYSICAL_ACTOR_MAPPING_NAME;
      }
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME;
      if (absActor.isSuperTypeOf(clazz_p)) {
        mappingName = IMappingNameConstants.LAB_LOGICAL_ACTOR_MAPPING_NAME;
      }
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_SYSTEM_MAPPING_NAME;
      if (absActor.isSuperTypeOf(clazz_p)) {
        mappingName = IMappingNameConstants.SAB_ACTOR_MAPPING_NAME;
      }
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_ENTITY_MAPPING_NAME;
      if (absActor.isSuperTypeOf(clazz_p)) {
        mappingName = IMappingNameConstants.OAB_ENTITY_MAPPING_NAME;
      }
    }
    return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);

  }

  public AbstractNodeMapping getMappingFunction(AbstractFunction function_p, DDiagram diagram_p) {

    if (DiagramHelper.getService().isArchitectureBlank(diagram_p)) {
      return FaServices.getFaServices().getMappingABAbstractFunction((AbstractFunction) function_p, diagram_p);
    }

    return FaServices.getFaServices().getMappingDFFunction((AbstractFunction) function_p, diagram_p);

  }

  public AbstractNodeMapping getMappingFunctionPort(FunctionPort port_p, DDiagram diagram_p) {

    if (DiagramHelper.getService().isArchitectureBlank(diagram_p)) {
      return FaServices.getFaServices().getMappingABFunctionPort(diagram_p);
    }

    return FaServices.getFaServices().getMappingDFFunctionPort(diagram_p);

  }

  public EdgeMapping getMappingFunctionalExchange(DDiagram diagram_p) {

    if (DiagramHelper.getService().isArchitectureBlank(diagram_p)) {
      return FaServices.getFaServices().getMappingABFunctionalExchange(diagram_p);
    }

    return FaServices.getFaServices().getMappingDFFunctionalExchange(diagram_p);

  }

  public NodeMapping getMappingABAbstractFunction(AbstractFunction function_p, DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_FUNCTION_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_LOGICAL_FUNCTION_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_SYSTEM_FUNCTION_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_FUNCTION_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.ORB_OPERATIONAL_ACTIVITY_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
  }

  public NodeMapping getMappingABFunctionPort(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_FUNCTION_PORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_FUNCTION_PORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_FUNCTION_PORT_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
  }

  public EdgeMapping getMappingABFunctionalExchange(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OEB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.ORB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  public EdgeMapping getMappingABComponentPortAllocation(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_COMPONENT_PORT_ALLOCATION_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  public EdgeMapping getMappingABConnection(DDiagram diagram_p) {
    String mappingName = null;

    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_CONNECTION_MAPPING_NAME;

    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_CONNECTION_MAPPING_NAME;

    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_CONNECTION_MAPPING_NAME;

    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_COMMUNICATION_MEAN_MAPPING_NAME;

    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CCII_COMPONENT_EXCHANGE_MAPPING_NAME;

    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.IDB_COMPONENT_EXCHANGE_MAPPING_NAME;

    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.COC_COMMUNICATION_MEAN_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  public EdgeMapping getMappingABPhysicalLink(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_PHYSICALLINK_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_PHYSICALLINK_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICALLINK_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  /**
   * used in an extension ...
   * 
   * @param chains
   * @param view
   */
  public void showInvolvedElementsInDataFlowBlank(List<FunctionalChain> chains, DSemanticDecorator view) {

    // A chain have some involved elements. Try to find them or there
    // containers in the shortest delay

    HashMap<EObject, DDiagramElement> elements = new HashMap<EObject, DDiagramElement>();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);

    // Find all involved elements of a functional chain
    HashSet<EObject> involveds = new HashSet<EObject>();
    HashSet<AbstractFunction> involvedFunctions = new HashSet<AbstractFunction>();
    HashSet<FunctionalExchange> involvedExchanges = new HashSet<FunctionalExchange>();

    for (FunctionalChain chain : chains) {
      involveds.add(chain);

      for (FunctionalChainInvolvement involvment : FunctionalChainExt.getFlatInvolvements(chain)) {
        if (involvment == null) {
          continue;
        }
        InvolvedElement involved = involvment.getInvolved();

        involveds.add(involved);

        if (involved instanceof AbstractFunction) {
          involvedFunctions.add((AbstractFunction) involved);

        } else if (involved instanceof FunctionalExchange) {
          FunctionalExchange exchange = (FunctionalExchange) involved;
          involvedExchanges.add(exchange);

          ActivityNode nodeSource = exchange.getSource();
          involveds.add(nodeSource);
          if ((nodeSource instanceof Port) && (nodeSource.eContainer() instanceof AbstractFunction)) {
            // in case where functional chain is not valid, add
            // function related to the port
            involveds.add(nodeSource.eContainer());
            involvedFunctions.add((AbstractFunction) nodeSource.eContainer());
          }

          ActivityNode nodeTarget = exchange.getTarget();
          involveds.add(nodeTarget);
          if ((nodeTarget instanceof Port) && (nodeTarget.eContainer() instanceof AbstractFunction)) {
            // in case where functional chain is not valid, add
            // function related to the port
            involveds.add(nodeTarget.eContainer());
            involvedFunctions.add((AbstractFunction) nodeTarget.eContainer());
          }
        }
      }
    }

    // Find in diagram elements any involved elements and them containers
    for (DDiagramElement element : DiagramServices.getDiagramServices().getDiagramElements(diagram)) {
      if (element instanceof AbstractDNode) {
        EObject target = element.getTarget();
        if (target == null) {
          continue;
        }
        if (involveds.contains(target)) {
          elements.put(target, element);
        } else if (involveds.contains(target.eContainer())) {
          elements.put(target.eContainer(), element);
        }
      } else if (element instanceof DEdge) {
        EObject target = element.getTarget();
        elements.put(target, element);
      }
    }

    // Create functions and nodes (on the diagram root, since refresh
    // replace correctly theses elements
    for (AbstractFunction function : involvedFunctions) {
      if (!elements.containsKey(function)) {
        ContainerMapping mapping = getMappingDFFunction(function, diagram);
        elements.put(function, DiagramServices.getDiagramServices().createContainer(mapping, function, diagram, diagram));
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
        elements.put(nodeSource, DiagramServices.getDiagramServices().createBorderedNode(portMapping, nodeSource, containerView, diagram));
      }
      // Create target port
      if (!elements.containsKey(nodeTarget)) {
        DDiagramElementContainer containerView = (DDiagramElementContainer) elements.get(nodeTarget.eContainer());
        elements.put(nodeTarget, DiagramServices.getDiagramServices().createBorderedNode(portMapping, nodeTarget, containerView, diagram));
      }
      // Create exchange
      if (!elements.containsKey(exchange)) {
        EdgeMapping edgeMapping = getMappingDFFunctionalExchange(diagram);
        EdgeTarget sourceView = (EdgeTarget) elements.get(nodeSource);
        EdgeTarget targetView = (EdgeTarget) elements.get(nodeTarget);
        DiagramServices.getDiagramServices().createEdge(edgeMapping, sourceView, targetView, exchange);
      }
    }

    // Display all chains (create a end-node and its edge
    NodeMapping endMapping = getMappingFunctionalChainEnd(diagram);
    for (FunctionalChain chain : chains) {
      if (!elements.containsKey(chain)) {
        elements.put(chain, DiagramServices.getDiagramServices().createNode(endMapping, chain, diagram, diagram));
      }
    }

  }

  /**
   * @param diagram_p
   * @return
   */
  private NodeMapping getMappingFunctionalChainEnd(DDiagram diagram_p) {
    String mappingName = null;

    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAIB_OPERATIONAL_PROCESS_END_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
  }

  /**
   * @param function_p
   * @param diagram_p
   * @return
   */

  public ContainerMapping getMappingDFFunction(AbstractFunction function_p, DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_FUNCTION_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_FUNCTION_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_FUNCTION_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_FUNCTION_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_FUNCTION_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_FUNCTION_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAIB_FUNCTION_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OEB_FUNCTION_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
  }

  /**
   * Returns mapping on data flow diagrams for the given abstract function
   * 
   * @param function_p
   * @param diagram_p
   * @return
   */
  private DiagramElementMapping getMappingDFAbstractFunction(AbstractFunction function_p, DDiagram diagram_p) {
    return getMappingDFFunction(function_p, diagram_p);
  }

  /**
   * @param diagram_p
   * @return
   */
  EdgeMapping getMappingDFFunctionalExchange(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_ACTIVITY_INTERACTION_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.COAI_INTERACTION_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAIB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OEB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.ORB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  private NodeMapping getMappingDFFunctionPort(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_PIN_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_PIN_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_PIN_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_PIN_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_PIN_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_PIN_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
  }

  /**
   * used in context, oa, logical, physical
   * 
   * @param current_p
   * @return
   */
  public List<AbstractFunction> getAvailableAbstractFunctionsToInsertInDataFlowBlank(DSemanticDecorator current_p) {
    List<AbstractFunction> returnedFunctions = new ArrayList<AbstractFunction>();
    AbstractFunction currentFunction = null;
    EObject target = current_p.getTarget();
    if (current_p instanceof DDiagram) {
      if (current_p.getTarget() instanceof AbstractFunction) {
        currentFunction = (AbstractFunction) current_p.getTarget();
      } else {
        currentFunction = getRootFunction(target);
      }
      returnedFunctions.addAll(CapellaServices.getService().getAvailableFunctionsInDataFlowBlank(currentFunction));
    }
    if ((null != target) && (current_p instanceof DNodeContainer) && (target instanceof AbstractFunction)) {
      currentFunction = (AbstractFunction) target;
      returnedFunctions.addAll(FunctionExt.getAllAbstractFunctions(currentFunction));
    }
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(current_p);
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
   * @param context_p
   * @param diagram_p
   * @param selectExchangeCategories_p
   *          exchanges categories to show (from wizard selection)
   * @return the context
   */
  public EObject switchFunctionalExchangesCategories(EObject context_p, List<ExchangeCategory> selectedExchangeCategories_p, DDiagram diagram_p) {
    Set<DEdge> exchangeCategoryEdgesToRemove = new HashSet<DEdge>(); // exchange
    // category
    // edges
    // to
    // remove
    Set<DEdge> functionalExchangeEdgesToRemove = new HashSet<DEdge>(); // functional
    // exchange
    // edges
    // to
    // remove
    Set<EdgeTarget> borderedNodesToCheck = new HashSet<EdgeTarget>(); // borderedNodes
    // of
    // edges
    // to
    // remove
    Set<ExchangeCategory> categoryToDisplay = new HashSet<ExchangeCategory>(); // categories
    // to
    // display
    Set<ExchangeCategory> categoryToHide = new HashSet<ExchangeCategory>(); // categories
    // to
    // hide
    Set<ExchangeCategory> displayedCategories = getDisplayedExchangeCategoriesInDiagram(diagram_p);
    Map<FunctionalExchange, DEdge> functionalExchangesInDiagram = new HashMap<FunctionalExchange, DEdge>(); // functional
    // exchanges
    // in
    // diagram

    // init categoryToDisplay
    for (ExchangeCategory aCategory : selectedExchangeCategories_p) {
      categoryToDisplay.add(aCategory);
    }
    // init categoryToHide
    for (ExchangeCategory aCategory : displayedCategories) {
      if (!selectedExchangeCategories_p.contains(aCategory)) {
        categoryToHide.add(aCategory);
      }
    }

    // init edges category to remove
    for (DEdge anEdge : diagram_p.getEdges()) {
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
    Set<FunctionalExchange> alreadyCreated = new HashSet<FunctionalExchange>();

    // create or show functional exchange views and remove exchange category
    // views
    for (DEdge anEdge : exchangeCategoryEdgesToRemove) {
      AbstractDNode sourceFunctionView = (AbstractDNode) anEdge.getSourceNode().eContainer();
      AbstractDNode targetFunctionView = (AbstractDNode) anEdge.getTargetNode().eContainer();
      AbstractFunction sourceFunction = (AbstractFunction) sourceFunctionView.getTarget();
      AbstractFunction targetFunction = (AbstractFunction) targetFunctionView.getTarget();
      List<FunctionalExchange> functionalExchangesOfCategory = ((ExchangeCategory) anEdge.getTarget()).getExchanges();
      Set<FunctionalExchange> exchangesToDisplay = getAvailableFunctionalExchangesBetweeen2Functions(functionalExchangesOfCategory, sourceFunction, targetFunction);
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
            createViewFunctionalExchange(anExchange, sourceFunctionView, targetFunctionView, diagram_p);
            alreadyCreated.add(anExchange);
          }
        }
      }
      if (!diagram_p.isSynchronized()) {
        DiagramServices.getDiagramServices().removeEdgeView(anEdge);
      } else {
        DiagramServices.getDiagramServices().removeEdgeView(anEdge);
      }
    }

    // create exchange category views and remove functional exchange views
    for (DEdge anEdge : functionalExchangeEdgesToRemove) {
      AbstractDNode sourceFunctionView = (AbstractDNode) anEdge.getSourceNode().eContainer();
      AbstractDNode targetFunctionView = (AbstractDNode) anEdge.getTargetNode().eContainer();
      FunctionalExchange currentExchange = (FunctionalExchange) anEdge.getTarget();
      for (ExchangeCategory aCategory : currentExchange.getCategories()) {
        if (categoryToDisplay.contains(aCategory)) {
          createViewExchangeCategory(aCategory, sourceFunctionView, targetFunctionView, diagram_p);
        }
      }
      if (!diagram_p.isSynchronized()) {
        DiagramServices.getDiagramServices().removeEdgeView(anEdge);
      } else {
        CapellaServices.getService().hide(anEdge);
      }
    }

    Map<AbstractFunction, AbstractDNode> allFunctionsInDiagram = new HashMap<AbstractFunction, AbstractDNode>();

    // get all displayed functions in the diagram
    for (DDiagramElement aContainer : DiagramServices.getDiagramServices().getDiagramElements(diagram_p)) {
      if ((aContainer != null) && (aContainer.getTarget() != null) && (aContainer instanceof AbstractDNode)
          && FaServices.getFaServices().isAbstractFunctionVisibleInDFB((AbstractDNode) aContainer, diagram_p)) {
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
                createViewExchangeCategory(aCategory, sourceFunctionView, targetFunctionView, diagram_p);
              }
            }
          }
        }
      }
    }

    for (EdgeTarget aNode : borderedNodesToCheck) {
      boolean incoming = DiagramServices.getDiagramServices().getIncomingEdges(aNode, diagram_p).isEmpty();
      boolean outgoing = DiagramServices.getDiagramServices().getOutgoingEdges(aNode, diagram_p).isEmpty();

      if (!incoming) {
        incoming = true;
        for (DEdge edge : DiagramServices.getDiagramServices().getIncomingEdges(aNode, diagram_p)) {
          incoming = (incoming) ? (exchangeCategoryEdgesToRemove.contains(edge) || functionalExchangeEdgesToRemove.contains(edge)) : false;
        }
      }
      if (!outgoing) {
        outgoing = true;
        for (DEdge edge : DiagramServices.getDiagramServices().getOutgoingEdges(aNode, diagram_p)) {
          outgoing = (outgoing) ? (exchangeCategoryEdgesToRemove.contains(edge) || functionalExchangeEdgesToRemove.contains(edge)) : false;
        }
      }

      if (incoming && outgoing) {
        if ((((DNode) aNode).getTarget() instanceof ExchangeCategory) || !diagram_p.isSynchronized()) {
          DiagramServices.getDiagramServices().removeNodeView((DNode) aNode);
        } else {
          if (aNode.eContainer() != null) {
            CapellaServices.getService().hide((DNode) aNode);
          }
        }
      }
    }

    return context_p;
  }

  /**
   * Returns whether an exchange category can be displayed between both views
   * 
   * @param sourceFunctionView_p
   * @param targetFunctionView_p
   * @return
   */
  public boolean isValidCreationCategoryBetweenViews(FunctionalExchange exchange, AbstractDNode sourceFunctionView_p, AbstractDNode targetFunctionView_p) {
    if ((sourceFunctionView_p != null) && (targetFunctionView_p != null)) {
      if ((sourceFunctionView_p.getTarget() != null) && (targetFunctionView_p.getTarget() != null)) {
        if (sourceFunctionView_p != targetFunctionView_p) {
          // remove displaying categories from a function to one of
          // its parents
          if (!EcoreUtil2.isContainedBy(sourceFunctionView_p.getTarget(), targetFunctionView_p.getTarget())) {
            if (!EcoreUtil2.isContainedBy(targetFunctionView_p.getTarget(), sourceFunctionView_p.getTarget())) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * @param aCategory_p
   * @param sourceFunctionView_p
   * @param targetFunctionView_p
   */
  private void createViewExchangeCategory(ExchangeCategory aCategory_p, AbstractDNode sourceFunctionView_p, AbstractDNode targetFunctionView_p, DDiagram diagram_p) {
    DNode sourceView = null;
    DNode targetView = null;

    // check if the source Function already contains the port Category
    for (DNode aNode : sourceFunctionView_p.getOwnedBorderedNodes()) {
      if (aNode.getTarget().equals(aCategory_p) && aNode.getActualMapping().getName().equals(getMappingNameOutputPinCategory(diagram_p))) {
        sourceView = aNode;
      }
    }

    // check if the target Function already contains the port Category
    for (DNode aNode : targetFunctionView_p.getOwnedBorderedNodes()) {
      if (aNode.getTarget().equals(aCategory_p) && aNode.getActualMapping().getName().equals(getMappingNameInputPinCategory(diagram_p))) {
        targetView = aNode;
      }
    }

    // test if an edge representing the category already exists between the
    // 2 functions
    if ((null != sourceView) && (null != targetView)) {
      for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(sourceView, diagram_p)) {
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
      sourceView = createViewOutputPinCategory(aCategory_p, sourceFunctionView_p, diagram_p);
    }
    if (null == targetView) {
      // create target Port View
      targetView = createViewInputPinCategory(aCategory_p, targetFunctionView_p, diagram_p);
    }

    EdgeMapping mapping = getMappingExchangeCategory(diagram_p);
    // create Edge
    DiagramServices.getDiagramServices().createEdge(mapping, sourceView, targetView, aCategory_p);
  }

  /**
   * @param anExchange_p
   * @param sourceFunctionView_p
   * @param targetFunctionView_p
   */
  public DEdge createViewFunctionalExchange(FunctionalExchange anExchange_p, AbstractDNode sourceFunctionView_p, AbstractDNode targetFunctionView_p, DDiagram diagram_p) {
    ActivityNode source = anExchange_p.getSource();
    ActivityNode target = anExchange_p.getTarget();
    EdgeTarget sourceView = null;
    EdgeTarget targetView = null;

    // check if the sourceView already exists

    if (source.equals(sourceFunctionView_p.getTarget()) && (sourceFunctionView_p instanceof EdgeTarget)) {
      sourceView = (EdgeTarget) sourceFunctionView_p;
    }
    if (null == sourceView) {
      for (DNode aBorderedNode : sourceFunctionView_p.getOwnedBorderedNodes()) {
        if ((aBorderedNode.getTarget() != null) && aBorderedNode.getTarget().equals(source)) {
          sourceView = aBorderedNode;
        }
      }
    }

    // if it does not exist, create view
    if (null == sourceView) {
      sourceView = createViewPin(source, sourceFunctionView_p, diagram_p);
    }

    if (target.equals(targetFunctionView_p.getTarget()) && (targetFunctionView_p instanceof EdgeTarget)) {
      targetView = (EdgeTarget) targetFunctionView_p;
    }

    // check if the targetView already exists

    if (null == targetView) {
      for (DNode aBorderedNode : targetFunctionView_p.getOwnedBorderedNodes()) {
        if ((aBorderedNode.getTarget() != null) && aBorderedNode.getTarget().equals(target)) {
          targetView = aBorderedNode;
        }
      }
    }

    // if it does not exist, create view
    if (null == targetView) {
      targetView = createViewPin(target, targetFunctionView_p, diagram_p);
    }

    // create functionalExchangeView
    EdgeMapping mapping = getMappingDFFunctionalExchange(diagram_p);
    if (mapping == null) {
      mapping = getMappingABFunctionalExchange(diagram_p);
    }
    CapellaServices.getService().show((DDiagramElement) sourceView);
    CapellaServices.getService().show((DDiagramElement) targetView);
    return DiagramServices.getDiagramServices().createEdge(mapping, sourceView, targetView, anExchange_p);

  }

  private DNode createViewPin(ActivityNode modelElement_p, AbstractDNode containerView_p, DDiagram diagram_p) {
    NodeMapping mapping = getMappingDFFunctionPort(diagram_p);
    if (mapping == null) {
      mapping = getMappingABFunctionPort(diagram_p);
    }
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, modelElement_p, (DragAndDropTarget) containerView_p, diagram_p);
  }

  private DNode createViewInputPinCategory(ExchangeCategory category_p, AbstractDNode containerView_p, DDiagram diagram_p) {
    String mappingName = getMappingNameInputPinCategory(diagram_p);
    NodeMapping mapping = DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, category_p, (DragAndDropTarget) containerView_p, diagram_p);
  }

  String getMappingNameInputPinCategory(DDiagram diagram_p) {
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      return IMappingNameConstants.CSDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      return IMappingNameConstants.CLDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      return IMappingNameConstants.CPDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.SAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.PAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.LAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    return null;
  }

  /**
   * @param context_p
   * @param aOperation_p
   * @param diagram_p
   */

  @Deprecated
  public AbstractFunction createForkFunction(EObject container_p) {
    return createDuplicateFunction(container_p);
  }

  public boolean isControlNode(EObject element_p) {
    return (element_p instanceof AbstractFunction) && FunctionExt.isControlNode((AbstractFunction) element_p);
  }

  public boolean isDuplicateFunction(EObject element_p) {
    return (element_p instanceof AbstractFunction) && FunctionExt.isDuplicateFunction((AbstractFunction) element_p);
  }

  public String computeFunctionLabel(DSemanticDecorator decorator_p) {
    if (decorator_p instanceof DDiagramElement) {
      EObject target = decorator_p.getTarget();
      if (target instanceof FunctionalChainInvolvement) {
        target = ((FunctionalChainInvolvement) target).getInvolved();
      }
      if (isControlNode(target)) {
        return decorateString(ICommonConstants.EMPTY_STRING, decorator_p);
      }
      return decorateString(EObjectLabelProviderHelper.getText(target), decorator_p);
    }
    return decorateString(ICommonConstants.EMPTY_STRING, decorator_p);
  }

  private String decorateString(String text, Object element) {
    for (ILabelDecorator decorator : SiriusDecoratorsManager.getDecorators()) {
      text = decorator.decorateText(text, element);
    }
    return text;
  }

  public boolean isGatherFunction(EObject element_p) {
    return (element_p instanceof AbstractFunction) && FunctionExt.isGatherFunction((AbstractFunction) element_p);
  }

  public boolean isRouteFunction(EObject element_p) {
    return (element_p instanceof AbstractFunction) && FunctionExt.isRouteFunction((AbstractFunction) element_p);
  }

  public boolean isSelectFunction(EObject element_p) {
    return (element_p instanceof AbstractFunction) && FunctionExt.isSelectFunction((AbstractFunction) element_p);
  }

  public boolean isSplitFunction(EObject element_p) {
    return (element_p instanceof AbstractFunction) && FunctionExt.isSplitFunction((AbstractFunction) element_p);
  }

  public AbstractFunction createDuplicateFunction(EObject container_p) {
    return createFunction(container_p, FunctionKind.DUPLICATE);
  }

  public AbstractFunction createGatherFunction(EObject container_p) {
    return createFunction(container_p, FunctionKind.GATHER);
  }

  public AbstractFunction createRouteFunction(EObject container_p) {
    return createFunction(container_p, FunctionKind.ROUTE);
  }

  public AbstractFunction createSelectFunction(EObject container_p) {
    return createFunction(container_p, FunctionKind.SELECT);
  }

  public AbstractFunction createSplitFunction(EObject container_p) {
    return createFunction(container_p, FunctionKind.SPLIT);
  }

  public AbstractFunction getParentFunctionContainer(EObject container_p) {
    return getFunctionContainer(container_p.eContainer());
  }

  public AbstractFunction getFunctionContainer(EObject container_p) {
    AbstractFunction container = null;
    if ((container_p instanceof AbstractFunction)) {
      container = (AbstractFunction) container_p;
    } else {
      container = (AbstractFunction) EcoreUtil2.getFirstContainer(container_p, FaPackage.Literals.ABSTRACT_FUNCTION);
    }

    if (container == null) {
      container = BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(container_p));
    }
    return container;
  }

  public AbstractFunction allocateToComponent(AbstractFunction function_p, Component component_p) {
    ComponentFunctionalAllocation allocation = FaFactory.eINSTANCE.createComponentFunctionalAllocation();
    if (component_p != null) {
      allocation.setSourceElement(component_p);
      allocation.setTargetElement(function_p);
      component_p.getOwnedFunctionalAllocation().add(allocation);
    }
    return function_p;
  }

  public AbstractFunction allocateToRole(AbstractFunction function_p, Role component_p) {
    ActivityAllocation allocation = OaFactory.eINSTANCE.createActivityAllocation();
    if (component_p != null) {
      allocation.setSourceElement(component_p);
      allocation.setTargetElement(function_p);
      component_p.getOwnedActivityAllocations().add(allocation);
    }
    return function_p;
  }

  public AbstractFunction allocateToCapability(AbstractFunction function_p, DSemanticDecorator containerView_p) {
    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(containerView_p);
    if ((diagram != null) && (diagram.getTarget() != null) && (diagram.getTarget() instanceof AbstractCapability)) {
      AbstractCapability capability = (AbstractCapability) diagram.getTarget();
      createAbstractFunctionAbstractCapabilityInvolvement(capability, function_p);
    }
    return function_p;
  }

  public AbstractFunction allocateToNewActor(AbstractFunction function_p) {
    Component actor = CsServices.getService().createActor(BlockArchitectureExt.getRootBlockArchitecture(function_p), true, null);
    allocateToComponent(function_p, actor);
    return function_p;
  }

  public AbstractFunction createActorFunction(EObject container_p) {
    return createFunction(container_p, "A ", FunctionKind.FUNCTION); //$NON-NLS-1$
  }

  /**
   * Returns functions owned by the function or owned function pkg
   * 
   * @return
   */
  public Collection<AbstractFunction> getFirstLevelAbstractFunctions(AbstractFunction function_p) {
    return FunctionExt.getFirstLevelAbstractFunctions(function_p);
  }

  /**
   * Returns functions owned by the function or owned function pkg
   * 
   * @return
   */
  public Collection<AbstractFunction> getFirstLevelAbstractFunctions(FunctionPkg container_p) {
    return FunctionPkgExt.getFirstLevelAbstractFunctions(container_p);
  }

  public static boolean isControlNodeOneOutput(AbstractFunction function_p) {
    return FunctionExt.isControlNodeOneOutput(function_p);
  }

  public static boolean isControlNodeOneInput(AbstractFunction function_p) {
    return FunctionExt.isControlNodeOneInput(function_p);
  }

  public boolean isValidCreationFunctionInputPort(DSemanticDecorator decorator_p) {
    EObject target = decorator_p.getTarget();
    AbstractFunction function = FunctionExt.getRelatedFunction((ActivityNode) target);

    if (FunctionExt.isControlNodeOneInput(function)) {
      return function.getInputs().isEmpty();
    }

    return true;
  }

  public boolean isValidCreationFunctionOutputPort(DSemanticDecorator decorator_p) {
    EObject target = decorator_p.getTarget();
    AbstractFunction function = FunctionExt.getRelatedFunction((ActivityNode) target);

    if (FunctionExt.isControlNodeOneOutput(function)) {
      return function.getOutputs().isEmpty();
    }

    return true;
  }

  public AbstractFunction createFunction(EObject container_p, String prefix, FunctionKind kind_p) {
    AbstractFunction function = null;
    AbstractFunction container = getFunctionContainer(container_p);

    if (container != null) {
      if (container instanceof OperationalActivity) {
        function = OaFactory.eINSTANCE.createOperationalActivity();

      } else if (container instanceof SystemFunction) {
        function = CtxFactory.eINSTANCE.createSystemFunction();

      } else if (container instanceof LogicalFunction) {
        function = LaFactory.eINSTANCE.createLogicalFunction();

      } else if (container instanceof PhysicalFunction) {
        function = PaFactory.eINSTANCE.createPhysicalFunction();
      }

      if (function != null) {
        container.getOwnedFunctions().add(function);
        function.setKind(kind_p);
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
   * @param context_p
   * @param aOperation_p
   * @param diagram_p
   */
  public AbstractFunction createFunction(EObject container_p) {
    return createFunction(container_p, null, FunctionKind.FUNCTION);
  }

  private AbstractFunction createFunction(EObject container_p, FunctionKind kind_p) {
    return createFunction(container_p, null, kind_p);
  }

  private DNode createViewOutputPinCategory(ExchangeCategory category_p, AbstractDNode containerView_p, DDiagram diagram_p) {
    String mappingName = getMappingNameOutputPinCategory(diagram_p);
    NodeMapping mapping = DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, category_p, (DragAndDropTarget) containerView_p, diagram_p);
  }

  String getMappingNameOutputPinCategory(DDiagram diagram_p) {
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      return IMappingNameConstants.CSDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      return IMappingNameConstants.CLDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      return IMappingNameConstants.CPDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.SAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.PAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return IMappingNameConstants.LAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    return null;
  }

  private EdgeMapping getMappingExchangeCategory(DDiagram diagram_p) {
    String mappingName = null;

    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_EXCHANGE_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_EXCHANGE_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_EXCHANGE_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_EXCHANGE_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_EXCHANGE_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_EXCHANGE_CATEGORY_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);

  }

  private Set<FunctionalExchange> getAvailableFunctionalExchangesBetweeen2Functions(List<FunctionalExchange> exchanges_p, AbstractFunction sourceFunction_p,
      AbstractFunction targetFunction_p) {
    Set<FunctionalExchange> returnedSet = new HashSet<FunctionalExchange>();
    for (FunctionalExchange anExchange : exchanges_p) {
      if (EcoreUtil.isAncestor(sourceFunction_p, anExchange.getSource()) && EcoreUtil.isAncestor(targetFunction_p, anExchange.getTarget())) {
        returnedSet.add(anExchange);
      }
    }
    return returnedSet;
  }

  private Set<ExchangeCategory> getVisibleExchangeCategoriesInDiagram(DDiagram diagram_p) {
    Set<ExchangeCategory> returnedList = new HashSet<ExchangeCategory>();
    for (DEdge anEdge : diagram_p.getEdges()) {
      if ((anEdge.getTarget() instanceof ExchangeCategory) && (!returnedList.contains(anEdge.getTarget())) && (anEdge.isVisible())) {
        returnedList.add((ExchangeCategory) anEdge.getTarget());
      }
    }
    return returnedList;
  }

  private Set<ExchangeCategory> getDisplayedExchangeCategoriesInDiagram(DDiagram diagram_p) {
    Set<ExchangeCategory> returnedList = new HashSet<ExchangeCategory>();
    for (DEdge anEdge : diagram_p.getEdges()) {
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
    // return false;
    DSemanticDecorator source_d = source;
    DSemanticDecorator target_d = target;

    if (((source_d instanceof DNode) && !(DiagramServices.getDiagramServices().isABorderedNode((DNode) source_d) && ((DNode) source_d).isVisible()))) {
      return false;
    }
    if (((target_d instanceof DNode) && !(DiagramServices.getDiagramServices().isABorderedNode((DNode) target_d) && ((DNode) target_d).isVisible()))) {
      return false;
    }

    AbstractDNode sc = (AbstractDNode) source_d;
    AbstractDNode tc = (AbstractDNode) target_d;
    if (!(source_d.getTarget() instanceof AbstractFunction)) {
      sc = (AbstractDNode) source_d.eContainer();
    }
    if (!(target_d.getTarget() instanceof AbstractFunction)) {
      tc = (AbstractDNode) target_d.eContainer();
    }

    FunctionalExchange exchange = null;
    if (o instanceof FunctionalExchange) {
      exchange = (FunctionalExchange) o;
    }

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(source_d);

    if (exchange == null) {
      return false;
    }
    for (DNode node : sc.getOwnedBorderedNodes()) {
      if (node.getTarget() instanceof ExchangeCategory) {
        if (node.getActualMapping().getName().equals(getMappingNameOutputPinCategory(diagram))) {
          for (DEdge edge : node.getOutgoingEdges()) {

            if (edge.isVisible() && (edge.getTarget() instanceof ExchangeCategory)) {
              if (exchange.getCategories().contains(edge.getTarget())) {
                EdgeTarget targetNode = edge.getTargetNode();
                if (targetNode != null) {
                  if (!((targetNode instanceof DSemanticDecorator) && (((DSemanticDecorator) targetNode).getTarget() instanceof AbstractFunction))) {
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

  List<DNodeContainer> getOwnedVisibleFunctionContainersInDataFlowBlank(DNodeContainer container_p, DDiagram diagram_p) {
    List<DNodeContainer> returnedList = new ArrayList<DNodeContainer>();
    Set<AbstractDNode> containers = new HashSet<AbstractDNode>();
    if (null == container_p) {
      containers.addAll(diagram_p.getContainers());
    } else {
      containers.addAll(container_p.getContainers());
    }
    for (AbstractDNode aSubContainer : containers) {
      if ((aSubContainer instanceof DNodeContainer) && (aSubContainer.getTarget() != null) && (aSubContainer.getTarget() instanceof AbstractFunction)
          && isAbstractFunctionVisibleInDFB(aSubContainer, diagram_p)) {
        returnedList.add((DNodeContainer) aSubContainer);
      }
    }
    return returnedList;
  }

  boolean isAbstractFunctionVisibleInDFB(AbstractDNode abstractFunction_p, DDiagram diagram_p) {
    // special case for sub functions in contextual data flow blank
    // we can not call the display service manager to know if sub functions
    // are visible
    // if we activate/deactivate a layer
    if (abstractFunction_p.getMapping().getName().contains(IMappingNameConstants.SUB_CONTROL_NODE_MAPPING_NAME)
        || abstractFunction_p.getMapping().getName().contains(IMappingNameConstants.SUB_FUNCTION_MAPPING_NAME)) {
      for (Layer activatedLayer : diagram_p.getActivatedLayers()) {
        if (activatedLayer.getName().contains(IMappingNameConstants.INTERNAL_DATA_FLOW_LAYER_NAME)) {
          // if the internal layer is active, the sub function is
          // visible
          return true;
        }
      }
      return false;
    }
    return CapellaServices.getService().isVisibleInDiagram(diagram_p, abstractFunction_p);
  }

  /**
   * used in context, logical, oa, physical
   * 
   * @param view_p
   *          a nodeContainer or a diagram
   * @return the list of containers/nodes (whose target is a Function/ControlNode) displayed in the current view
   */
  public List<AbstractDNode> getDisplayedAbstractFunctionViews(DSemanticDecorator view_p) {
    List<AbstractDNode> returnedList = new ArrayList<AbstractDNode>();
    if (view_p instanceof DDiagram) {
      DDiagram diagram = (DDiagram) view_p;
      for (AbstractDNode aContainer : diagram.getContainers()) {
        if ((aContainer.getTarget() != null) && (aContainer.getTarget() instanceof AbstractFunction)) {
          returnedList.add(aContainer);
        }
      }
    }
    if (view_p instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) view_p;
      for (AbstractDNode aContainer : currentContainer.getContainers()) {
        if ((aContainer.getTarget() != null) && (aContainer.getTarget() instanceof AbstractFunction)) {
          returnedList.add(aContainer);
        }
      }
    }
    return returnedList;
  }

  /**
   * used in context, logical, oa physical
   * 
   * @param view_p
   *          a nodeContainer or a diagram
   * @return the list of Functions/ControlNodes displayed in the current view
   */
  public List<AbstractFunction> getDisplayedAbstractFunctions(DSemanticDecorator view_p) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();
    for (AbstractDNode aView : getDisplayedAbstractFunctionViews(view_p)) {
      returnedList.add((AbstractFunction) aView.getTarget());
    }
    return returnedList;
  }

  /**
   * used by external action
   * 
   * @param view_p
   * @param selectedFunctions
   * @param visibleFunctions
   * @param visibleFunctionViews
   * @return
   */
  public EObject showHideFunctionsInDataFlowBlank(DSemanticDecorator view_p, List<AbstractFunction> selectedFunctions, List<AbstractFunction> visibleFunctions,
      List<AbstractDNode> visibleFunctionViews) {

    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(view_p);
    DDiagramContents content = new DDiagramContents(diagram);

    Set<AbstractDNode> toBeRemoved = new HashSet<AbstractDNode>();

    for (AbstractDNode aView : visibleFunctionViews) {
      if (!selectedFunctions.contains(aView.getTarget())) {
        toBeRemoved.add(aView);
      }
    }

    // move visible functions if the container has to be removed
    for (AbstractDNode aView : visibleFunctionViews) {
      if (toBeRemoved.contains(aView.eContainer())) {
        diagram.getOwnedDiagramElements().add(aView);
      }
    }

    // move borderedNodes Ports if possible
    for (AbstractDNode aView : toBeRemoved) {
      HashSet<DNode> borderedNodes = new HashSet<DNode>();
      borderedNodes.addAll(aView.getOwnedBorderedNodes());
      for (DNode aBorderedNode : borderedNodes) {
        if ((aBorderedNode.getTarget() != null) && ((aBorderedNode.getTarget() instanceof FunctionPort) || (aBorderedNode.getTarget() instanceof ExchangeCategory))) {
          moveBorderedNodeIfPossible(aBorderedNode, toBeRemoved, diagram);
        }
      }
    }

    // case if the diagram is contained in an AbstractCapability
    if (!(diagram.getTarget() instanceof AbstractFunction)) {

      for (AbstractDNode aView : toBeRemoved) {
        removeAbstractFunctionAbstractCapabilityInvolvement((AbstractCapability) diagram.getTarget(), aView.getTarget());
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

    HashMap<AbstractFunction, AbstractDNode> showHideCategories = new HashMap<AbstractFunction, AbstractDNode>();

    // add views
    for (AbstractFunction aFunction : selectedFunctions) {
      if (!visibleFunctions.contains(aFunction)) {

        DiagramContext context = shService.new DiagramContext();
        Collection<DSemanticDecorator> views = shService.showWithResult(aFunction, context);

        // retrieve newly created views
        for (DSemanticDecorator view : views) {
          if (aFunction.equals(view.getTarget()) && view instanceof AbstractDNode) {
            showHideCategories.put(aFunction, (AbstractDNode) view);
          }
        }

      }
    }

    // and create exchange category links for all newly created views
    Set<ExchangeCategory> categories = getVisibleExchangeCategoriesInDiagram(diagram);
    for (AbstractFunction aFunction : selectedFunctions) {
      if (!visibleFunctions.contains(aFunction)) {
        AbstractDNode node = showHideCategories.get(aFunction);
        if (node != null && CapellaServices.getService().isSynchronized(diagram)) {
          HashMapSet<ExchangeCategory, EObject> scope = (HashMapSet) getAvailableCategoriesAndFunctionsToInsertInDataFlowBlank(node, content);
          HashMapSet<EObject, EObject> selectedElements = new HashMapSet<EObject, EObject>((Map) scope);
          HashMapSet<EObject, EObject> initialSelection = (HashMapSet) FaServices.getFaServices().getCategoriesAndFunctionsInitialSelectionInDataFlowBlank((DNodeContainer) node,
              content);

          for (ExchangeCategory category : new ArrayList<ExchangeCategory>(scope.keySet())) {
            if (!categories.contains(category)) {
              selectedElements.remove(category);
            }
          }

          showFECategories(node, (HashMapSet) scope, initialSelection, selectedElements);
        }
      }
    }

    return view_p;
  }

  public void removeAbstractFunctionAbstractCapabilityInvolvement(AbstractCapability capability, EObject target) {
    Set<AbstractFunctionAbstractCapabilityInvolvement> toRemove = new HashSet<AbstractFunctionAbstractCapabilityInvolvement>();
    for (AbstractFunctionAbstractCapabilityInvolvement inv : capability.getOwnedAbstractFunctionAbstractCapabilityInvolvements()) {
      if (inv.getInvolved().equals(target)) {
        toRemove.add(inv);
      }
    }
    for (AbstractFunctionAbstractCapabilityInvolvement involvement : toRemove) {
      involvement.destroy();
    }
  }

  public AbstractFunctionAbstractCapabilityInvolvement createAbstractFunctionAbstractCapabilityInvolvement(AbstractCapability capability, AbstractFunction target) {
    for (AbstractFunctionAbstractCapabilityInvolvement inv : capability.getOwnedAbstractFunctionAbstractCapabilityInvolvements()) {
      if (inv.getInvolved().equals(target)) {
        return inv;
      }
    }
    AbstractFunctionAbstractCapabilityInvolvement newInv = InteractionFactory.eINSTANCE.createAbstractFunctionAbstractCapabilityInvolvement();
    newInv.setInvolver(capability);
    newInv.setInvolved(target);
    capability.getOwnedAbstractFunctionAbstractCapabilityInvolvements().add(newInv);
    CapellaServices.getService().creationService(newInv);
    return newInv;
  }

  /**
   * @param aBorderedNode_p
   * @param aView_p
   */
  private void moveBorderedNodeIfPossible(DNode aBorderedNode_p, Set<AbstractDNode> toBeRemoved_p, DDiagram diagram_p) {
    EObject container = aBorderedNode_p.eContainer();
    while ((container != null) && (container instanceof DNodeContainer)) {
      if (!toBeRemoved_p.contains(container)) {
        boolean canMove = true;

        for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(aBorderedNode_p, diagram_p)) {
          if (EcoreUtil.isAncestor(container, anEdge.getSourceNode())) {
            // not a internal exchanges
            canMove = false;
            break;
          }
        }
        for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(aBorderedNode_p, diagram_p)) {
          if (EcoreUtil.isAncestor(container, anEdge.getTargetNode())) {
            // not a internal exchanges
            canMove = false;
            break;
          }
        }

        if (canMove) {
          if (aBorderedNode_p.getTarget() instanceof ExchangeCategory) {
            for (DNode node : ((DNodeContainer) container).getOwnedBorderedNodes()) {
              if (node.getTarget() == aBorderedNode_p.getTarget()) {
                aBorderedNode_p.getIncomingEdges().addAll(aBorderedNode_p.getIncomingEdges());
                aBorderedNode_p.getOutgoingEdges().addAll(aBorderedNode_p.getOutgoingEdges());
                return;
              }
            }
          }
          // not a internal exchanges
          ((DNodeContainer) container).getOwnedBorderedNodes().add(aBorderedNode_p);
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
  public EObject showDFFunctionalExchange(AbstractDNode currentFunctionView, List<FunctionalExchange> scope, List<FunctionalExchange> selectedExchanges) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(currentFunctionView);
    DDiagramContents content = getDDiagramContents(currentDiagram);
    Set<FunctionalExchange> scopeSet = new HashSet<FunctionalExchange>();
    scopeSet.addAll(scope);
    Set<FunctionalExchange> selectedExchangesSet = new HashSet<FunctionalExchange>();
    selectedExchangesSet.addAll(selectedExchanges);

    AbstractShowHide shService = new ShowHideFunctionalExchange(content);
    DiagramContext context = shService.new DiagramContext();

    List<FunctionalExchange> exchanges = getDisplayedFunctionalExchanges(currentFunctionView);
    for (FunctionalExchange exchange : exchanges) {
      if (selectedExchangesSet.contains(exchange)) {
        shService.show(exchange, context);

        // showDFFunctionalExchange(shService, currentFunctionView,
        // exchange, context, false);
        selectedExchangesSet.remove(exchange);
      } else {
        shService.hide(exchange, context);

        // hideDFFunctionalExchange(exchange, context);
      }
    }

    int i = 0;
    for (FunctionalExchange exchange : selectedExchangesSet) {
      // showDFFunctionalExchange(shService, currentFunctionView,
      // exchange, context, false);
      shService.show(exchange, context);

      i++;
    }

    reorderFAElements(currentDiagram);

    return currentFunctionView;
  }

  //
  // public void showDFFunctionalExchange(AbstractShowHide categories_p,
  // AbstractDNode currentFunctionView,
  // FunctionalExchange exchange_p, DiagramContext context_p,
  // boolean checkValid_p) {
  // categories_p.show(exchange_p, context_p);
  //
  // }

  public void showDFFunctionalExchange(AbstractDNode currentFunctionView, FunctionalExchange exchange_p, DDiagramContents context_p, boolean checkValid_p) {
    DEdge edge = context_p.getEdge(exchange_p);

    if (edge != null) {
      if (checkValid_p) {
        if (!DFServices.getService().isValidDFFunctionalExchangeEdgeFromInternalTool(exchange_p, (DSemanticDecorator) edge.getSourceNode(),
            (DSemanticDecorator) edge.getTargetNode())) {
          return;
        }
      }

    } else {

      // Reveal source element
      ActivityNode source = exchange_p.getSource();
      ActivityNode target = exchange_p.getTarget();

      AbstractFunction sourceFunction = FunctionExt.getRelatedFunction(source);
      AbstractFunction targetFunction = FunctionExt.getRelatedFunction(target);

      Collection<DDiagramElement> sourceViews = context_p.getDiagramElements(sourceFunction);
      Collection<DDiagramElement> targetViews = context_p.getDiagramElements(targetFunction);

      AbstractDNode sourceView = null;
      if (sourceViews.size() > 0) {
        sourceView = (AbstractDNode) sourceViews.iterator().next();
      }
      AbstractDNode targetView = null;
      if (targetViews.size() > 0) {
        targetView = (AbstractDNode) targetViews.iterator().next();
      }

      // Don't display functional exchange from/to internal exchanges
      if (currentFunctionView != null) {
        boolean sourceIsChild = (EcoreUtil.isAncestor(((DSemanticDecorator) currentFunctionView).getTarget(), source));
        boolean targetIsChild = (EcoreUtil.isAncestor(((DSemanticDecorator) currentFunctionView).getTarget(), target));
        if (sourceIsChild && targetIsChild) {
          return;
        }
      }

      DragAndDropTarget sourceContainerView = context_p.getBestContainer(sourceFunction);
      DragAndDropTarget targetContainerView = context_p.getBestContainer(targetFunction);

      // If there is a hierarchy between both preferred bounds of
      // functional exchange, we need to display both instead
      // hierarchy link
      if ((sourceContainerView instanceof DDiagramElement) && (targetContainerView instanceof DDiagramElement)) {
        DragAndDropTarget aView = sourceContainerView;
        DragAndDropTarget bView = targetContainerView;

        if (EcoreUtil.isAncestor(aView, bView)) {
          EObject element = context_p.getElement(sourceFunction, exchange_p);
          if ((element != null) && (element instanceof AbstractFunction)) {
            if (!context_p.getDiagramElements(element).contains(sourceContainerView)) {
              sourceContainerView = context_p.getDDiagram();
            }
          }
        }
        if (EcoreUtil.isAncestor(bView, aView)) {
          EObject element = context_p.getElement(targetFunction, exchange_p);
          if ((element != null) && (element instanceof AbstractFunction)) {
            if (!context_p.getDiagramElements(element).contains(targetContainerView)) {
              targetContainerView = context_p.getDDiagram();
            }
          }
        }
      }

      // If preferred source is not a node (null, or diagram), we need to
      // display best parent function of the source
      // (port or OperationalActivity)
      if (sourceView == null) {
        if ((sourceContainerView != null) && (sourceContainerView instanceof AbstractDNode)) {
          sourceView = (AbstractDNode) sourceContainerView;
        } else {
          EObject element = context_p.getElement(sourceFunction, exchange_p);
          if ((element != null) && (element instanceof AbstractFunction)) {
            sourceView = showDFAbstractFunction((AbstractFunction) element, context_p.getBestContainer(element), context_p);
          }
        }
      }

      // If preferred source is not a node (null, or diagram), we need to
      // display best parent function of the source
      // (port or OperationalActivity)
      if (targetView == null) {
        if ((targetContainerView != null) && (targetContainerView instanceof AbstractDNode)) {
          targetView = (AbstractDNode) targetContainerView;
        } else {
          EObject element = context_p.getElement(targetFunction, exchange_p);
          if ((element != null) && (element instanceof AbstractFunction)) {
            targetView = showDFAbstractFunction((AbstractFunction) element, context_p.getBestContainer(element), context_p);
          }
        }
      }

      if (checkValid_p) {
        if (!DFServices.getService().isValidDFFunctionalExchangeEdgeFromInternalTool(exchange_p, sourceView, targetView)) {
          return;
        }
      }

      if (source instanceof FunctionPort) {
        sourceView = showDFFunctionPort(source, sourceView, context_p);
      }
      if (target instanceof FunctionPort) {
        targetView = showDFFunctionPort(target, targetView, context_p);
      }

      // Create an edge between both source and target
      if ((sourceView != null) && (targetView != null)) {
        edge = DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingDFFunctionalExchange(context_p.getDDiagram()), (EdgeTarget) sourceView,
            (EdgeTarget) targetView, exchange_p);
        context_p.addView(edge);
      }
    }
  }

  /**
   * @param source_p
   * @param sourceFunction_p
   * @param context_p
   */
  private AbstractDNode showDFFunctionPort(ActivityNode port_p, AbstractDNode sourceFunction_p, DDiagramContents context_p) {
    for (DDiagramElement element : sourceFunction_p.getOwnedBorderedNodes()) {
      if ((element.getTarget() != null) && element.getTarget().equals(port_p)) {
        return (AbstractDNode) element;
      }
    }

    if (sourceFunction_p.getTarget() instanceof AbstractFunction) {
      AbstractFunction function = (AbstractFunction) sourceFunction_p.getTarget();
      if (CapellaServices.getService().getAvailablePins(function, context_p.getDDiagram(), sourceFunction_p).contains(port_p)) {
        AbstractDNode element = createViewFunctionPort(port_p, (DragAndDropTarget) sourceFunction_p, context_p._currentDiagram);
        context_p.addView(element);
        return element;
      }
    }
    return null;
  }

  /**
   * @param function_p
   * @param dContainer_p
   * @param context_p
   * @return
   */
  public AbstractDNode showDFAbstractFunction(AbstractFunction function_p, DragAndDropTarget dContainer_p, DDiagramContents context_p) {
    DiagramElementMapping mapping = getMappingDFAbstractFunction(function_p, context_p.getDDiagram());
    Collection<DDiagramElement> views = context_p.getDiagramElements(function_p, mapping);
    if (views.size() > 0) {
      return (AbstractDNode) views.iterator().next();
    }

    AbstractDNode element = createViewDFAbstractFunction(function_p, context_p.getDDiagram());
    DiagramServices.getDiagramServices().getOwnedDiagramElements(dContainer_p).add(element);

    context_p.addView(element);
    return element;
  }

  private void hideDFFunctionalExchange(FunctionalExchange exchange_p, DDiagramContents context_p) {
    if (!CapellaServices.getService().isSynchronized(context_p._currentDiagram)) {
      DEdge edge = context_p.getEdge(exchange_p);
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
   * @param aFunction_p
   * @param diagram_p
   */
  protected DNode createViewFunctionPort(ActivityNode port, DragAndDropTarget container, DDiagram diagram_p) {
    NodeMapping mapping = getMappingDFFunctionPort(diagram_p);
    if (mapping == null) {
      mapping = getMappingABFunctionPort(diagram_p);
    }
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, port, container, diagram_p);
  }

  /**
   * @param aFunction_p
   * @param diagram_p
   */
  protected AbstractDNode createViewDFAbstractFunction(AbstractFunction aFunction_p, DDiagram diagram_p) {
    return createViewDFFunction(aFunction_p, diagram_p);
  }

  /**
   * @param aFunction_p
   * @param diagram_p
   */
  protected DNode createViewABAbstractFunction(AbstractFunction aFunction_p, DragAndDropTarget container, DDiagram diagram_p) {
    NodeMapping mapping = getMappingABAbstractFunction(aFunction_p, diagram_p);
    return DiagramServices.getDiagramServices().createNode(mapping, aFunction_p, container, diagram_p);
  }

  /**
   * @param aFunction_p
   * @param diagram_p
   * @return
   */
  protected DNodeContainer createViewDFFunction(AbstractFunction aFunction_p, DDiagram diagram_p) {
    ContainerMapping mapping = getMappingDFFunction(aFunction_p, diagram_p);
    return DiagramServices.getDiagramServices().createContainer(mapping, aFunction_p, diagram_p, diagram_p);
  }

  /**
   * @param aFunction_p
   * @param diagram_p
   * @return
   */
  protected DNode createViewComponentPort(Port port_p, DragAndDropTarget container, DDiagram diagram_p) {
    NodeMapping mapping = getMappingABComponentPort(port_p, diagram_p);
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, port_p, container, diagram_p);
  }

  /**
   * @param aFunction_p
   * @param diagram_p
   * @return
   */
  protected DNode createViewPhysicalPort(Port port_p, DragAndDropTarget container, DDiagram diagram_p) {
    NodeMapping mapping = getMappingABPhysicalPort(port_p, diagram_p);
    return DiagramServices.getDiagramServices().createBorderedNode(mapping, port_p, container, diagram_p);
  }

  public DNodeContainer createViewPart(EObject target_p, DragAndDropTarget parent_p, DDiagram parentDiagram_p) {
    Component parent = null;
    if (target_p instanceof Component) {
      parent = (Component) target_p;
    } else if (target_p instanceof Part) {
      parent = (Component) ((Part) target_p).getAbstractType();
    }
    ContainerMapping mapping = getMappingABComponent(parent, parentDiagram_p);
    return DiagramServices.getDiagramServices().createContainer(mapping, target_p, parent_p, parentDiagram_p);
  }

  public NodeMapping getMappingABComponentPort(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_COMPONENT_PORT_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_COMPONENT_PORT_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_COMPONENT_PORT_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CCII_PORT_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.IDB_PORT_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
  }

  public List<NodeMapping> getMappingABPorts(DDiagram diagram_p) {
    List<String> mappingNames = new ArrayList<String>();
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.PAB_COMPONENT_PORT_MAPPING_NAME);
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.LAB_COMPONENT_PORT_MAPPING_NAME);
      mappingNames.add(IMappingNameConstants.LAB_PHYSICAL_PORT_MAPPING_NAME);
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.SAB_COMPONENT_PORT_MAPPING_NAME);
      mappingNames.add(IMappingNameConstants.SAB_PHYSICAL_PORT_MAPPING_NAME);
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.CCII_PORT_MAPPING_NAME);
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.IDB_PORT_MAPPING_NAME);
    }
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingNames);
  }

  /**
   * Returns mapping of component port
   */
  @Deprecated
  public NodeMapping getMappingABComponentPort(Port port_p, DDiagram diagram_p) {
    return getMappingABComponentPort(diagram_p);
  }

  @Deprecated
  public NodeMapping getMappingABPhysicalPort(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_PORT_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
  }

  public NodeMapping getMappingABPhysicalPort(Port port_p, DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_PHYSICAL_PORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_PHYSICAL_PORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_PORT_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
  }

  /**
   * Perform a dnd of a function port.
   * 
   * @param port_p
   * @param oldContainer_p
   * @param newContainer_p
   */
  public EObject dndDFFunctionPort(FunctionPort port_p, NamedElement oldContainer_p, NamedElement newContainer_p) {

    if (newContainer_p instanceof AbstractFunction) {
      AbstractFunction newFunction = (AbstractFunction) newContainer_p;
      AbstractFunction oldFunction = (AbstractFunction) port_p.eContainer();
      if (port_p instanceof FunctionInputPort) {
        for (ActivityEdge anEdge : ((FunctionInputPort) port_p).getIncoming()) {
          if (anEdge instanceof FunctionalExchange) {
            updateFunctionaChainInvolvementsOfFunctionalExchange((FunctionalExchange) anEdge, oldFunction, newFunction);
          }
        }
        newFunction.getInputs().add((FunctionInputPort) port_p);
      } else {
        for (ActivityEdge anEdge : ((FunctionOutputPort) port_p).getOutgoing()) {
          if (anEdge instanceof FunctionalExchange) {
            updateFunctionaChainInvolvementsOfFunctionalExchange((FunctionalExchange) anEdge, oldFunction, newFunction);
          }
        }
        newFunction.getOutputs().add((FunctionOutputPort) port_p);
      }
    }
    return port_p;
  }

  public Collection<? extends EObject> getFBDSemanticFunctions(AbstractFunction root_p) {
    Collection<? extends EObject> result = getFBDSemanticAbstractFunctions(root_p);
    CapellaServices.getService().filter(result, FaPackage.Literals.ABSTRACT_FUNCTION);
    return result;
  }

  @Deprecated
  public Collection<? extends EObject> getFBDSemanticControlNodes(AbstractFunction root_p) {
    return Collections.emptyList();
  }

  /** Returns all owned functions used in breakdown diagrams */
  public Collection<? extends EObject> getFBDSemanticAbstractFunctions(AbstractFunction root_p) {
    Collection<? extends AbstractFunction> result = getAllAbstractFunctions(root_p);
    EObject container = root_p.eContainer();
    if ((container != null) && (container instanceof FunctionPkg)) {
      container = container.eContainer();
      // Remove the root function
      if ((container != null) && (container instanceof BlockArchitecture)) {
        result.remove(root_p);
      }
    }
    return result;
  }

  public Collection<AbstractFunction> getAllAbstractFunctions(AbstractFunction root_p) {
    return FunctionExt.getAllAbstractFunctions(root_p);
  }

  public Collection<AbstractFunction> getAllAbstractFunctions(BlockArchitecture root_p) {
    return FunctionExt.getAllAbstractFunctions(root_p);
  }

  public Collection<AbstractFunction> getAllAbstractFunctions(FunctionPkg root_p) {
    return FunctionPkgExt.getAllAbstractFunctions(root_p);
  }

  public EObject getFBDParentFunction(AbstractFunction root_p) {
    return EcoreUtil2.getFirstContainer(root_p, FaPackage.Literals.ABSTRACT_FUNCTION);
  }

  public EObject updateFunctionaChainInvolvementsOfFunctionalExchange(FunctionalExchange fe_p, AbstractFunction oldFunction, AbstractFunction newFunction_p) {
    for (Involvement anInvolvement : fe_p.getInvolvingInvolvements()) {
      if (anInvolvement instanceof FunctionalChainInvolvement) {

        FunctionalChainInvolvement currentInvolvement = (FunctionalChainInvolvement) anInvolvement;
        FunctionalChain currentFunctionalChain = (FunctionalChain) currentInvolvement.eContainer();

        Set<FunctionalChainInvolvement> newFunctionInvolvements = FunctionalChainExt.getInvolvementsOf(currentFunctionalChain, newFunction_p);
        FunctionalChainInvolvement newFunctionInv;
        if (newFunctionInvolvements.isEmpty()) {
          // we add the new Function to the functional chain
          newFunctionInv = FunctionalChainExt.createInvolvement(currentFunctionalChain, newFunction_p);
        } else {
          newFunctionInv = newFunctionInvolvements.iterator().next();
        }
        if (!FunctionExt.getIncomingAbstractFunction(fe_p).equals(oldFunction)) {
          // the target of the exchange has changed
          currentInvolvement.getNextFunctionalChainInvolvements().clear();
          currentInvolvement.getNextFunctionalChainInvolvements().add(newFunctionInv);
        } else {
          // the source of the exchange has changed
          FunctionalChainInvolvement previousInv = currentInvolvement.getPreviousFunctionalChainInvolvements().iterator().next();
          previousInv.getNextFunctionalChainInvolvements().remove(currentInvolvement);
          newFunctionInv.getNextFunctionalChainInvolvements().add(currentInvolvement);
        }
      }
    }
    return fe_p;
  }

  /**
   * Performs semantic operation for a reconnect of source from a component exchange edge
   * 
   * @param componentExchange_p
   * @param edge_p
   * @param oldNode_p
   * @param newNode_p
   */
  public EObject reconnectDFFunctionalExchangeSource(EObject functionalExchange_p, DSemanticDecorator edge_p, DSemanticDecorator oldNode_p, DSemanticDecorator newNode_p) {
    if (edge_p instanceof DEdge) {
      if (functionalExchange_p instanceof FunctionalExchange) {
        reconnectDFFunctionalExchange((FunctionalExchange) functionalExchange_p, ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE, (DEdge) edge_p, oldNode_p, newNode_p);
      }
    }
    return functionalExchange_p;
  }

  /**
   * Performs semantic operation for a reconnect of source from a component exchange edge
   * 
   * @param componentExchange_p
   * @param edge_p
   * @param oldNode_p
   * @param newNode_p
   */
  public EObject reconnectDFFunctionalExchangeTarget(EObject functionalExchange_p, DSemanticDecorator edge_p, DSemanticDecorator oldNode_p, DSemanticDecorator newNode_p) {
    if (edge_p instanceof DEdge) {
      if (functionalExchange_p instanceof FunctionalExchange) {
        reconnectDFFunctionalExchange((FunctionalExchange) functionalExchange_p, ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE, (DEdge) edge_p, oldNode_p, newNode_p);
      }
    }
    return functionalExchange_p;
  }

  /**
   * @param functionalExchange_p
   * @param activityEdgeSource_p
   * @param edge_p
   * @param oldNode_p
   * @param newNode_p
   */
  private void reconnectDFFunctionalExchange(FunctionalExchange functionalExchange_p, EReference activityEdgeSource_p, DEdge edge_p, DSemanticDecorator oldNode_p,
      DSemanticDecorator newNode_p) {
    //
  }

  public EObject reconnectFunctionalExchange(FunctionalExchange fe_p, ActivityNode source_p, ActivityNode target_p) {
    EObject oldFunction = FunctionExt.getRelatedFunction(source_p);
    EObject newFunction = FunctionExt.getRelatedFunction(target_p);

    updateFunctionaChainInvolvementsOfFunctionalExchange(fe_p, (AbstractFunction) oldFunction, (AbstractFunction) newFunction);
    if (fe_p.getSource().equals(source_p)) {
      fe_p.setSource(target_p);
    } else {
      fe_p.setTarget(target_p);
    }
    EObject commonAncestor = CapellaServices.getService().getCommonAncestor(fe_p.getSource(), fe_p.getTarget());
    if ((commonAncestor != null) && (commonAncestor instanceof AbstractFunction) && (!commonAncestor.equals(fe_p.eContainer()))) {
      ((AbstractFunction) commonAncestor).getOwnedFunctionalExchanges().add(fe_p);
    }
    return fe_p;
  }

  public boolean isFunctionalExchangeReconnectable(FunctionalExchange fe_p, DDiagram diagram_p, EObject source_p, EObject target_p) {

    Set<FunctionalChain> visibleFC = new HashSet<FunctionalChain>();
    for (DNode aNode : diagram_p.getNodes()) {
      if ((aNode.getTarget() != null) && (aNode.getTarget() instanceof FunctionalChain)) {
        visibleFC.add((FunctionalChain) aNode.getTarget());
      }
    }
    for (FunctionalChain aFC : fe_p.getInvolvingFunctionalChains()) {
      if (visibleFC.contains(aFC)) {
        return true;
      }
    }
    if ((source_p instanceof InputPin) && (target_p instanceof OutputPin)) {
      return false;
    }
    if ((source_p instanceof OutputPin) && (target_p instanceof InputPin)) {
      return false;
    }
    if ((target_p instanceof AbstractFunction) && !CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(fe_p)) {
      return false;
    }
    if (!(target_p instanceof AbstractFunction) && CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(fe_p)) {
      return false;
    }

    // move source ?
    if (fe_p.getSource().equals(source_p) && !isValidCreationFunctionalExchange(fe_p, target_p, fe_p.getTarget())) {
      return false;
    }
    // move target ?
    if (fe_p.getTarget().equals(source_p) && !isValidCreationFunctionalExchange(fe_p, fe_p.getSource(), target_p)) {
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
   * @param function_p
   *          the given abstractFunction
   * @param oldContainer
   *          the given namedElement
   * @param newContainer
   *          the given namedElement
   * @return the EObject
   */
  public EObject dndABAbstractFunctionAllocation(AbstractFunction function_p, NamedElement oldContainer, NamedElement newContainer) {
    if (oldContainer.equals(newContainer)) {
      return function_p;
    }
    Collection<AbstractFunction> functions = FunctionExt.getAllAbstractFunctions(function_p);

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
      for (ComponentFunctionalAllocation allocation : new ArrayList<ComponentFunctionalAllocation>(oldComponent.getOwnedFunctionalAllocation())) {
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
      // remove activity allocation toward function_p
      List<ActivityAllocation> removeAllocationLink = new ArrayList<ActivityAllocation>();
      EList<ActivityAllocation> allocations = oldRole.getActivityAllocations();
      for (ActivityAllocation activityAllocation : allocations) {
        OperationalActivity activity = activityAllocation.getActivity();
        if ((null != activity) && activity.equals(function_p)) {
          removeAllocationLink.add(activityAllocation);
        }
      }
      CapellaServices.getService().removeElements(removeAllocationLink);

      // create new Component Functional Allocation allocation in new
      // container(that is component)
      allocateToComponent(function_p, newComponent);
    }
    // old container is Component and new container is ROLE
    else if ((oldComponent != null) && (newContainer instanceof Role)) {
      Role newRole = (Role) newContainer;
      // remove Component Functional Allocation toward function_p
      List<ComponentFunctionalAllocation> removeAllocationLink = new ArrayList<ComponentFunctionalAllocation>();
      EList<ComponentFunctionalAllocation> allocations = oldComponent.getOwnedFunctionalAllocation();
      for (ComponentFunctionalAllocation activityAllocation : allocations) {
        AbstractFunction function = activityAllocation.getFunction();
        if ((null != function) && function.equals(function_p)) {
          removeAllocationLink.add(activityAllocation);
        }
      }
      CapellaServices.getService().removeElements(removeAllocationLink);

      // create new Activity Allocation allocation in new container (that
      // is role)
      allocateToRole(function_p, newRole);
    }

    // remove useless portRealization of "all functions" and inComing and
    // outGoing functiaonalExchages(component
    // Exchange Allocation)
    if (!oldContainer.equals(newComponent)) {
      removeUseLessPortRealizationAndComponentExchangeAllocation(function_p, functions);
    }

    return function_p;
  }

  private void removeUseLessPortRealizationAndComponentExchangeAllocation(AbstractFunction function_p, Collection<AbstractFunction> functions) {
    Collection<FunctionalExchange> functionalExchanges = new HashSet<FunctionalExchange>();
    for (AbstractFunction function : functions) {
      // Find all PortRealization and component exchanges to be deleted
      for (Port port : FunctionExt.getOwnedFunctionPorts(function)) {
        removeUselessPortRealizations(port, true, true, false, false);
      }
    }
    // consider incoming and outgoing functionalExchanges of target function
    functionalExchanges.addAll(FunctionExt.getIncomingExchange(function_p));
    functionalExchanges.addAll(FunctionExt.getOutGoingExchange(function_p));

    removeComponentExchangeAllocations(functionalExchanges);
  }

  private void removeComponentExchangeAllocations(Collection<FunctionalExchange> functionalExchanges) {
    Collection<ComponentExchangeFunctionalExchangeAllocation> allocations = new HashSet<ComponentExchangeFunctionalExchangeAllocation>();

    // Remove components exchanges realizing the functional exchanges
    for (FunctionalExchange exchange : functionalExchanges) {
      for (ComponentExchange ce : exchange.getAllocatingComponentExchanges()) {
        for (ComponentExchangeFunctionalExchangeAllocation alloc : ce.getOutgoingComponentExchangeFunctionalExchangeAllocations()) {
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
   * @param realization_p
   * @return
   */
  private boolean isValidAllocation(Allocation realization_p) {
    return (realization_p.getSourceElement() != null) && (realization_p.getTargetElement() != null) && (realization_p.getSourceElement() instanceof CapellaElement)
        && (realization_p.getTargetElement() instanceof CapellaElement)
        && CapellaElementExt.areInSameDecompositionAlternative((CapellaElement) realization_p.getSourceElement(), (CapellaElement) realization_p.getTargetElement());
  }

  public HashMapSet<ExchangeCategory, AbstractFunction> getAvailableCategoriesAndFunctionsToInsertInDataFlowBlank(AbstractDNode functionView_p, DDiagramContents content_p) {

    HashMapSet<ExchangeCategory, AbstractFunction> returnedMap = new HashMapSet<ExchangeCategory, AbstractFunction>();
    List<DNodeContainer> functionContainersInDiagram = new ArrayList<DNodeContainer>();

    for (DDiagramElement element : DiagramServices.getDiagramServices().getDiagramElements(content_p.getDDiagram())) { // TODO
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
        if ((aContainer.getTarget() != null) && (aContainer.getTarget() instanceof AbstractFunction) && (aContainer instanceof DNodeContainer)) {
          functionContainersInDiagram.add((DNodeContainer) aContainer);
        }
      }
    }

    AbstractFunction function = (AbstractFunction) functionView_p.getTarget();
    for (FunctionalExchange anExchange : getAvailableFunctionalExchangesToInsert(functionView_p)) {
      AbstractFunction targetFunction = null;
      if (EcoreUtil.isAncestor(function, anExchange.getSource())) {
        targetFunction = (AbstractFunction) anExchange.getTarget().eContainer();
      } else {
        targetFunction = (AbstractFunction) anExchange.getSource().eContainer();
      }
      DNodeContainer visibleFunctionInDiagram = getDisplayedFunctionContainer(targetFunction, functionContainersInDiagram);
      if (visibleFunctionInDiagram != null) {
        if (isValidCreationCategoryBetweenViews(anExchange, functionView_p, visibleFunctionInDiagram)) {
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

  public HashMapSet<ExchangeCategory, AbstractFunction> getCategoriesAndFunctionsInitialSelectionInDataFlowBlank(DNodeContainer functionView_p, DDiagramContents content_p) {
    HashMapSet<ExchangeCategory, AbstractFunction> returnedMap = new HashMapSet<ExchangeCategory, AbstractFunction>();
    List<DNodeContainer> functionContainersInDiagram = new ArrayList<DNodeContainer>();

    for (DDiagramElement element : DiagramServices.getDiagramServices().getDiagramElements(content_p.getDDiagram())) { // TODO
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
        if ((aContainer.getTarget() != null) && (aContainer.getTarget() instanceof AbstractFunction) && (aContainer instanceof DNodeContainer)) {
          functionContainersInDiagram.add((DNodeContainer) aContainer);
        }
      }
    }

    for (DNode aBorderedNode : functionView_p.getOwnedBorderedNodes()) {
      if ((aBorderedNode.getTarget() != null) && (aBorderedNode.getTarget() instanceof ExchangeCategory)) {
        ExchangeCategory aCategory = (ExchangeCategory) aBorderedNode.getTarget();
        for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(aBorderedNode, content_p.getDDiagram())) {
          if (anEdge.isVisible()) {
            AbstractFunction sourceFunction = (AbstractFunction) ((DNodeContainer) anEdge.getSourceNode().eContainer()).getTarget();
            // find if a function in the diagram may be a source
            // Function of the functional Exchange
            DNodeContainer visibleFunctionInDiagram = getDisplayedFunctionContainer(sourceFunction, functionContainersInDiagram);
            if (visibleFunctionInDiagram != null) {
              sourceFunction = (AbstractFunction) visibleFunctionInDiagram.getTarget();
            }
            returnedMap.put(aCategory, sourceFunction);
          }
        }
        for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(aBorderedNode, content_p.getDDiagram())) {
          if (anEdge.isVisible()) {
            AbstractFunction targetFunction = (AbstractFunction) ((DNodeContainer) anEdge.getTargetNode().eContainer()).getTarget();
            // find if a function in the diagram may be a target
            // Function of the functional Exchange
            DNodeContainer visibleFunctionInDiagram = getDisplayedFunctionContainer(targetFunction, functionContainersInDiagram);
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
   * @param context_p
   * @param sourceView_p
   * @param targetView_p
   * @return
   */
  public EObject createABComponentExchangeWithoutPorts(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);

    InformationsExchanger sourcePart = CsServices.getService().getRelatedPart(sourceView_p);
    InformationsExchanger targetPart = CsServices.getService().getRelatedPart(targetView_p);

    EdgeTarget nodeSource = (EdgeTarget) sourceView_p;
    EdgeTarget nodeTarget = (EdgeTarget) targetView_p;

    // Create component exchange
    ComponentExchange exchange = FaFactory.eINSTANCE.createComponentExchange();
    exchange.setKind(ComponentExchangeKind.ASSEMBLY);

    // Attach source and target
    exchange.setSource(sourcePart);
    exchange.setTarget(targetPart);

    // Attach to parent
    ComponentExchangeExt.attachToDefaultContainer(exchange);

    CapellaServices.getService().creationService(exchange);
    DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingABConnection(diagram), nodeSource, nodeTarget, exchange);
    return context_p;
  }

  /**
   * @param sourceView_p
   * @return
   */
  public ActivityNode getRelatedActivityNode(DSemanticDecorator sourceView_p) {
    if (sourceView_p != null) {
      if ((sourceView_p.getTarget() != null) && (sourceView_p.getTarget() instanceof ActivityNode)) {
        return (ActivityNode) sourceView_p.getTarget();
      }
      if ((sourceView_p.eContainer() != null) && (sourceView_p.eContainer() instanceof DSemanticDecorator)) {
        return getRelatedActivityNode((DSemanticDecorator) sourceView_p.eContainer());
      }
    }
    return null;
  }

  /**
   * Create a component exchange in an architecture blank diagram. Create port if selected views are not targeting port
   * 
   * @param context_p
   * @param sourceViewk
   * @param targetView_p
   * @return
   */
  public EObject createABComponentExchange(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    return createABComponentExchangeWithOption(context_p, sourceView_p, targetView_p, false);
  }

  /**
   * Create a component exchange in an architecture blank diagram. Create port if selected views are not targeting port
   * 
   * @param context_p
   * @param sourceViewk
   * @param targetView_p
   * @return
   */
  public EObject createABComponentExchange(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p, boolean createComponentExchageOnType_p) {
    return createABComponentExchangeWithOption(context_p, sourceView_p, targetView_p, createComponentExchageOnType_p);
  }

  /**
   * Create a component exchange in an architecture blank diagram. Create port if selected views are not targeting port
   * 
   * @param context_p
   * @param sourceViewk
   * @param targetView_p
   * @return
   */
  public EObject createABComponentExchangeWithOption(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p, boolean createComponentExchageOnType_p) {
    EObject sourceTarget = sourceView_p.getTarget();
    EObject targetTarget = targetView_p.getTarget();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);

    EdgeTarget nodeSource = null;
    EdgeTarget nodeTarget = null;

    ComponentExchange exchange = null;

    if ((sourceTarget instanceof Entity) && (targetTarget instanceof Entity)) {

      exchange = OaFactory.eINSTANCE.createCommunicationMean();
      exchange.setSource((Entity) sourceTarget);
      exchange.setTarget((Entity) targetTarget);

      nodeSource = (EdgeTarget) sourceView_p;
      nodeTarget = (EdgeTarget) targetView_p;
    } else {

      InformationsExchanger sourceRelatedPart = CsServices.getService().getRelatedPart(sourceView_p);
      InformationsExchanger targetRelatedPart = CsServices.getService().getRelatedPart(targetView_p);

      Part sourcePart = null;
      Part targetPart = null;
      if (sourceRelatedPart instanceof Part) {
        sourcePart = (Part) sourceRelatedPart;
      }
      if (targetRelatedPart instanceof Part) {
        targetPart = (Part) targetRelatedPart;
      }

      // Create or retrieve sourcePort
      ComponentPort sourcePort = null;
      if (sourceTarget instanceof ComponentPort) {
        sourcePort = (ComponentPort) sourceTarget;
        nodeSource = (EdgeTarget) sourceView_p;

      } else {
        sourcePort = FaFactory.eINSTANCE.createComponentPort();
        ((Component) sourcePart.getType()).getOwnedFeatures().add(sourcePort);

        if (targetTarget instanceof ComponentPort) {
          sourcePort.setKind(((ComponentPort) targetTarget).getKind());
        } else {
          sourcePort.setKind(ComponentPortKind.FLOW);
        }
        if (sourcePort.getKind() == ComponentPortKind.FLOW) {
          sourcePort.setOrientation(OrientationPortKind.OUT);
        }

        CapellaServices.getService().creationService(sourcePort);
        if (sourceView_p instanceof DNodeContainer) {
          nodeSource = CsServices.getService().createViewOrGetPort((DNodeContainer) sourceView_p, sourcePort).getKey();
        }
      }

      // Create or retrieve targetPort
      ComponentPort targetPort = null;
      if (targetTarget instanceof ComponentPort) {
        targetPort = (ComponentPort) targetTarget;
        nodeTarget = (EdgeTarget) targetView_p;
      } else {
        targetPort = FaFactory.eINSTANCE.createComponentPort();
        ((Component) targetPart.getType()).getOwnedFeatures().add(targetPort);

        targetPort.setKind(sourcePort.getKind());
        if (targetPort.getKind() == ComponentPortKind.FLOW) {
          targetPort.setOrientation(OrientationPortKind.IN);
        }

        CapellaServices.getService().creationService(targetPort);
        if (targetView_p instanceof DNodeContainer) {
          nodeTarget = CsServices.getService().createViewOrGetPort((DNodeContainer) targetView_p, targetPort).getKey();
        }
      }

      // Create component exchange
      exchange = FaFactory.eINSTANCE.createComponentExchange();
      if ((sourcePort.getKind() == ComponentPortKind.STANDARD) || (targetPort.getKind() == ComponentPortKind.STANDARD)) {
        exchange.setKind(ComponentExchangeKind.ASSEMBLY);
      } else {
        exchange.setKind(ComponentExchangeKind.FLOW);
      }

      // Set source
      if (CsServices.getService().isMultipartMode((ModelElement) sourceTarget) && !createComponentExchageOnType_p) {
        ComponentExchangeEnd end = FaFactory.eINSTANCE.createComponentExchangeEnd();
        end.setPart(sourcePart);
        end.setPort(sourcePort);
        exchange.setSource(end);
        exchange.getOwnedComponentExchangeEnds().add(end);
        CapellaServices.getService().creationService(end);
      } else {
        exchange.setSource(sourcePort);
      }

      // Set target
      if (CsServices.getService().isMultipartMode((ModelElement) sourceTarget) && !createComponentExchageOnType_p) {
        ComponentExchangeEnd end = FaFactory.eINSTANCE.createComponentExchangeEnd();
        end.setPart(targetPart);
        end.setPort(targetPort);
        exchange.setTarget(end);
        exchange.getOwnedComponentExchangeEnds().add(end);
        CapellaServices.getService().creationService(end);
      } else {
        exchange.setTarget(targetPort);
      }
    }

    // Attach to parent
    ComponentExchangeExt.attachToDefaultContainer(exchange);

    CapellaServices.getService().creationService(exchange);
    DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingABConnection(diagram), nodeSource, nodeTarget, exchange);
    return context_p;
  }

  /**
   * Create a delegation in an interface blank diagram. Create port if selected views are not targeting port
   * 
   * @param context_p
   * @param sourceView_p
   * @param targetView_p
   * @return
   */
  public EObject createIBDelegation(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    return createABDelegation(context_p, sourceView_p, targetView_p);
  }

  /**
   * Create a delegation in an architecture blank diagram. Create port if selected views are not targeting port
   * 
   * @param context_p
   * @param sourceView_p
   * @param targetView_p
   * @return
   */
  public EObject createABDelegation(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    EObject sourceTarget = sourceView_p.getTarget();
    EObject targetTarget = targetView_p.getTarget();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);

    EObject sourceRelatedPart = CsServices.getService().getRelatedPart(sourceView_p);
    EObject targetRelatedPart = CsServices.getService().getRelatedPart(targetView_p);

    Part sourcePart = null;
    Part targetPart = null;
    if (sourceRelatedPart instanceof Part) {
      sourcePart = (Part) sourceRelatedPart;
    }
    if (targetRelatedPart instanceof Part) {
      targetPart = (Part) targetRelatedPart;
    }

    if (sourcePart == null) {
      EObject sourceComponent = CsServices.getService().getComponentType(sourceView_p);
      if ((sourceComponent != null) && (sourceComponent instanceof Component)) {
        if ((((Component) sourceComponent).getRepresentingPartitions().size() > 0) && (((Component) sourceComponent).getRepresentingPartitions().get(0) instanceof Part)) {
          sourcePart = (Part) ((Component) sourceComponent).getRepresentingPartitions().get(0);
        }
      }
    }
    if (targetPart == null) {
      EObject targetComponent = CsServices.getService().getComponentType(targetView_p);
      if ((targetComponent != null) && (targetComponent instanceof Component)) {
        if ((((Component) targetComponent).getRepresentingPartitions().size() > 0) && (((Component) targetComponent).getRepresentingPartitions().get(0) instanceof Part)) {
          targetPart = (Part) ((Component) targetComponent).getRepresentingPartitions().get(0);
        }
      }
    }
    if ((sourcePart == null) || (targetPart == null)) {
      return context_p;
    }

    EdgeTarget nodeSource = null;
    EdgeTarget nodeTarget = null;

    // Create or retrieve sourcePort
    Port sourcePort = null;
    if (sourceTarget instanceof Port) {
      sourcePort = (Port) sourceTarget;
      nodeSource = (EdgeTarget) sourceView_p;

    } else {

      // Create a component port or a physical port if from physical
      // component
      if ((sourcePart.getAbstractType() instanceof PhysicalComponent) && (((PhysicalComponent) sourcePart.getAbstractType()).getNature() == PhysicalComponentNature.NODE)) {
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
      if (sourceView_p instanceof DNodeContainer) {
        nodeSource = CsServices.getService().createViewOrGetPort((DNodeContainer) sourceView_p, sourcePort).getKey();
      }
    }

    // Create or retrieve targetPort
    Port targetPort = null;
    if (targetTarget instanceof ComponentPort) {
      targetPort = (ComponentPort) targetTarget;
      nodeTarget = (EdgeTarget) targetView_p;
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
      if (targetView_p instanceof DNodeContainer) {
        nodeTarget = CsServices.getService().createViewOrGetPort((DNodeContainer) targetView_p, targetPort).getKey();
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
    DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingABConnection(diagram), nodeSource, nodeTarget, exchange);
    return context_p;
  }

  /**
   * Create a component exchange between parents part of sourceView and targetView considered as "brothers" and create
   * delegations from theses parts and sourceView and targetView
   * 
   * @param context_p
   * @param sourceView_p
   * @param targetView_p
   * @return
   */
  public EObject createABComponentExchangeThroughDelegation(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    InformationsExchanger sourcePart = CsServices.getService().getRelatedPart(sourceView_p);
    InformationsExchanger targetPart = CsServices.getService().getRelatedPart(targetView_p);

    ComponentPort sourcePort = null;
    if ((sourceView_p.getTarget() != null) && (sourceView_p.getTarget() instanceof ComponentPort)) {
      sourcePort = (ComponentPort) sourceView_p.getTarget();
    }

    ComponentPort targetPort = null;
    if ((targetView_p.getTarget() != null) && (targetView_p.getTarget() instanceof ComponentPort)) {
      targetPort = (ComponentPort) targetView_p.getTarget();
    }

    if ((sourcePart instanceof Part) && (targetPart instanceof Part)) {
      Collection<EObject> createdElements = ComponentExt.createComponentExchangeThroughDelegations((Part) sourcePart, sourcePort, (Part) targetPart, targetPort);
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);
      CsServices.getService().showABComponentExchange(createdElements, (DSemanticDecorator) diagram);
    }
    return context_p;
  }

  public Collection<ComponentExchange> getRelatedComponentExchanges(NamedElement componentOrPart_p) {
    Collection<ComponentExchange> relatedExchanges = new ArrayList<ComponentExchange>();

    // Retrieve all related component exchanges
    if (componentOrPart_p instanceof Component) {
      relatedExchanges.addAll(ComponentExt.getAllRelatedComponentExchange((Component) componentOrPart_p));

    } else if (componentOrPart_p instanceof Part) {
      Part part = (Part) componentOrPart_p;
      if ((part.getAbstractType() != null) && (part.getAbstractType() instanceof Component)) {
        Component component = (Component) ((Part) componentOrPart_p).getAbstractType();

        if (CsServices.getService().isMultipartMode(componentOrPart_p)) {
          for (ComponentExchange exchange : ComponentExt.getAllRelatedComponentExchange(component)) {
            if (part.equals(ComponentExchangeExt.getSourcePart(exchange)) || part.equals(ComponentExchangeExt.getTargetPart(exchange))) {
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
   * @param component_p
   */
  void removeUselessExchanges(NamedElement componentOrPart_p) {

    Collection<ComponentExchange> relatedExchanges = getRelatedComponentExchanges(componentOrPart_p);

    // remove all delegation links
    // remove all incoming traceLinks from PhysicalLink
    Collection<ModelElement> elements = new HashSet<ModelElement>();
    for (ComponentExchange connection : relatedExchanges) {
      // remove the tract link coming from physicaLink [specific]
      EList<AbstractTrace> incomingTraces = connection.getIncomingTraces();
      for (AbstractTrace abstractTrace : incomingTraces) {
        TraceableElement sourceElement = abstractTrace.getSourceElement();
        if ((null != sourceElement) && (sourceElement instanceof PhysicalLink)) {
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
   * @param port_p
   */
  EObject removeUselessPortRealizations(Port port_p, boolean includeFunctionalRealization, boolean includeComponentRealization, boolean topDelegation, boolean bottomDelegation) {
    Collection<EObject> elements = new HashSet<EObject>();

    if (port_p instanceof ComponentPort) {
      // Retrieve delegation to delete, according to parameters
      for (ComponentExchange exchange : PortExt.getDelegationComponentExchanges((ComponentPort) port_p)) {
        Component sourceComponent = ComponentExchangeExt.getSourceComponent(exchange);
        Component targetComponent = ComponentExchangeExt.getTargetComponent(exchange);
        Component containingPort = targetComponent;
        Component delegatedPort = sourceComponent;
        if (port_p.eContainer() != null) {
          if (port_p.eContainer().equals(sourceComponent)) {
            containingPort = sourceComponent;
            delegatedPort = targetComponent;
          }
        }

        if (topDelegation && CsServices.getService().getContainersOfParts(containingPort).contains(delegatedPort)) {
          elements.add(exchange);
        } else if (bottomDelegation && CsServices.getService().getContainersOfParts(delegatedPort).contains(containingPort)) {
          elements.add(exchange);
        }
      }
    }

    Collection<Allocation> allocations = new HashSet<Allocation>();
    allocations.addAll(port_p.getIncomingPortAllocations());
    allocations.addAll(port_p.getOutgoingPortAllocations());
    allocations.addAll(port_p.getIncomingPortRealizations());
    allocations.addAll(port_p.getOutgoingPortRealizations());

    // Retrieve port allocation according to parameters
    for (Allocation realization : allocations) {
      if (isValidAllocation(realization)) {
        if ((realization.getSourceElement() != null) && (realization.getTargetElement() != null)) {

          if (includeFunctionalRealization && (realization.getTargetElement() instanceof FunctionPort)) {
            elements.add(realization);
          }

          if ((port_p instanceof FunctionPort) && includeComponentRealization && (realization.getSourceElement() instanceof FunctionPort)) {
            elements.add(realization);
          }

          if (includeComponentRealization && ((realization.getTargetElement() instanceof ComponentPort) || (realization.getTargetElement() instanceof PhysicalPort))) {
            elements.add(realization);
          }
        }
      }
    }

    CapellaServices.getService().removeElements(elements);
    return port_p;
  }

  /**
   * Perform a dnd of a function port.
   * 
   * @param port_p
   * @param oldContainer_p
   * @param newContainer_p
   */
  public EObject dndABFunctionPort(FunctionPort port_p, NamedElement oldContainer_p, NamedElement newContainer_p) {

    removeUselessPortRealizations(port_p, true, true, false, false);

    // move the port in the new function container
    if (newContainer_p instanceof AbstractFunction) {
      AbstractFunction newFunction = (AbstractFunction) newContainer_p;
      AbstractFunction oldFunction = (AbstractFunction) port_p.eContainer();
      if (port_p instanceof FunctionInputPort) {
        for (ActivityEdge anEdge : ((FunctionInputPort) port_p).getIncoming()) {
          if (anEdge instanceof FunctionalExchange) {
            updateFunctionaChainInvolvementsOfFunctionalExchange((FunctionalExchange) anEdge, oldFunction, newFunction);
          }
        }
        newFunction.getInputs().add((FunctionInputPort) port_p);
      } else {
        for (ActivityEdge anEdge : ((FunctionOutputPort) port_p).getOutgoing()) {
          if (anEdge instanceof FunctionalExchange) {
            updateFunctionaChainInvolvementsOfFunctionalExchange((FunctionalExchange) anEdge, oldFunction, newFunction);
          }
        }
        newFunction.getOutputs().add((FunctionOutputPort) port_p);
      }
    }

    moveFunctionalExchanges(port_p);
    removeComponentExchangeAllocations(getFunctionalExchanges(port_p));

    return port_p;
  }

  /**
   * Perform a dnd of a physical port
   * 
   * @param port_p
   *          the given port
   * @param oldContainer_p
   *          the old view container
   * @param newContainer_p
   *          the new view container
   * @return the EObject
   */
  public EObject dndABPhysicalPort(PhysicalPort port_p, Part oldContainer_p, Part newContainer_p) {

    if (!port_p.eContainer().equals(newContainer_p.getType())) {
      removeUselessExchanges(port_p);
      removeUselessPortRealizations(port_p, true, true, false, false);

      ((Component) newContainer_p.getType()).getOwnedFeatures().add(port_p);
      updateExchanges(port_p, oldContainer_p, newContainer_p);
    }

    return port_p;
  }

  /**
   * Perform a dnd of a component port (standard port or flow port)
   * 
   * @param port_p
   *          the given port
   * @param oldContainer_p
   *          the old view container
   * @param newContainer_p
   *          the new view container
   * @return the EObject
   */
  public EObject dndABComponentPort(ComponentPort port_p, Part oldContainer_p, Part newContainer_p) {

    if (!port_p.eContainer().equals(newContainer_p.getType())) {
      removeUselessExchanges(port_p);
      removeUselessPortRealizations(port_p, true, true, false, false);

      ((Component) newContainer_p.getType()).getOwnedFeatures().add(port_p);
      updateComponentExchanges(port_p, oldContainer_p, newContainer_p);
    }

    return port_p;
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
    if ((container instanceof LogicalComponent) && (component instanceof LogicalComponent)) {
      ((LogicalComponent) container).getOwnedLogicalComponents().add((LogicalComponent) component);

    } else if ((container instanceof PhysicalComponent) && (component instanceof PhysicalComponent)) {
      ((PhysicalComponent) container).getOwnedPhysicalComponents().add((PhysicalComponent) component);
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
    if (ancestor != exchange.eContainer()) {
      if (ancestor instanceof AbstractFunction) {
        ((AbstractFunction) ancestor).getOwnedFunctionalExchanges().add(exchange);
      }
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

    EObject container = ComponentExt.getFirstCommonComponentAncestor(source, target);
    if ((container == null) || !(container instanceof PhysicalComponent)) {
      container = BlockArchitectureExt.getFirstComponent(ComponentExt.getRootBlockArchitecture(exchange));
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
    for (PhysicalLink exchange : PhysicalLinkExt.getAllRelatedPhysicalLinks(port)) {
      if (exchange.getOwnedPhysicalLinkEnds().size() > 0) {
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
  protected void moveComponentExchanges(ComponentPort port_p) {
    for (ComponentExchange exchange : port_p.getComponentExchanges()) {
      moveComponentExchange(exchange);
    }
  }

  protected void moveComponentExchanges(Component component_p) {
    for (ComponentPort port : ComponentExt.getOwnedComponentPort(component_p)) {
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
      if (exchange.getOwnedComponentExchangeEnds().size() > 0) {
        for (ComponentExchangeEnd anEnd : exchange.getOwnedComponentExchangeEnds()) {
          if ((anEnd.getPort() != null) && anEnd.getPort().equals(port) && (anEnd.getPart() != null) && anEnd.getPart().equals(oldPart)) {
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
    List<FunctionalExchange> functionalExchanges = new ArrayList<FunctionalExchange>();

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
  public Collection<? extends AbstractFunction> getTableRootAbstractFunctions(BlockArchitecture architecture_p) {
    return getOwnedAbstractFunctions(getRootFunction(architecture_p));
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends FunctionPkg> getTableRootAbstractFunctionPkgs(BlockArchitecture architecture_p) {
    return getOwnedAbstractFunctionPkgs(getRootFunction(architecture_p));
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends AbstractFunction> getOwnedAbstractFunctions(AbstractFunction function_p) {
    return function_p.getOwnedFunctions();
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends AbstractFunction> getOwnedAbstractFunctions(FunctionPkg functionPkg_p) {
    return FunctionPkgExt.getOwnedFunctions(functionPkg_p);
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends FunctionPkg> getOwnedAbstractFunctionPkgs(FunctionPkg function_p) {
    return FunctionPkgExt.getOwnedFunctionPkgs(function_p);
  }

  /**
   * Returns owned function pkgs
   */
  public Collection<? extends FunctionPkg> getOwnedAbstractFunctionPkgs(AbstractFunction function_p) {
    return FunctionExt.getOwnedFunctionPkgs(function_p);
  }

  /**
   * Returns the root function of current architecture of the given element and create it if not found
   */
  public AbstractFunction getTableRootFunction(EObject element_p) {
    return getRootFunction(element_p);
  }

  /**
   * Returns the root function of current architecture of the given element and create it if not found
   */
  public AbstractFunction getRootFunction(EObject element_p) {
    return BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(element_p));
  }

  /**
   * Gets the functional exchanges related to the port
   * 
   * @param port
   *          the given port
   * @return the functional exchanges
   */
  protected Collection<FunctionalExchange> getFunctionalExchanges(Port port) {
    Collection<FunctionalExchange> exchanges = new HashSet<FunctionalExchange>();

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
   * Returns whether the context_p decorator is from the given node_p eclass and is linked to edges from given edge_p
   * eclass
   * 
   * @param context_p
   * @param diagram_p
   * @param node_p
   * @param edge_p
   * @return
   */
  public boolean isNodeWithoutEdge(EObject context_p, DDiagram diagram_p, EClass node_p, EClass edge_p) {
    if (diagram_p != null) {

      if (context_p instanceof DSemanticDecorator) {
        DSemanticDecorator decorator = (DSemanticDecorator) context_p;
        if (!((decorator.getTarget() != null) && node_p.isInstance(decorator.getTarget()))) {
          return true;
        }

        if (context_p instanceof EdgeTarget) {
          EdgeTarget target = (EdgeTarget) context_p;
          for (DEdge edge : target.getIncomingEdges()) {
            if ((edge.getTarget() != null) && edge_p.isInstance(edge.getTarget())) {
              return true;
            }
          }
          for (DEdge edge : target.getOutgoingEdges()) {
            if ((edge.getTarget() != null) && edge_p.isInstance(edge.getTarget())) {
              return true;
            }
          }
        }
      }
    }

    return false;

  }

  /**
   * Hide Component Port without Interfaces in Diagram
   * 
   * @param context_p
   *          : Function Port
   * @param diagram_p
   *          : DDiagram
   * @return true if PhysicalPort has PhysicalLink displayed in diagram.
   */
  public boolean isComponentPortWithoutInterfaces(EObject context_p, DDiagram diagram_p) {
    return isNodeWithoutEdge(context_p, diagram_p, FaPackage.Literals.COMPONENT_PORT, CsPackage.Literals.INTERFACE);
  }

  /**
   * Hide Physical Port without Exchanges in Diagram
   * 
   * @param context_p
   *          : Function Port
   * @param diagram_p
   *          : DDiagram
   * @return true if PhysicalPort has PhysicalLink displayed in diagram.
   */
  public boolean isPhysicalPortWithoutLinks(EObject context_p, DDiagram diagram_p) {
    return isNodeWithoutEdge(context_p, diagram_p, CsPackage.Literals.PHYSICAL_PORT, CsPackage.Literals.PHYSICAL_LINK);
  }

  /**
   * Hide Component Port without Exchanges in Diagram
   * 
   * @param context_p
   *          : Function Port
   * @param diagram_p
   *          : DDiagram
   * @return true if ComponentPort has ComponentExchange displayed in diagram.
   */
  public boolean isComponentPortWithoutExchanges(EObject context_p, DDiagram diagram_p) {
    return isNodeWithoutEdge(context_p, diagram_p, FaPackage.Literals.COMPONENT_PORT, FaPackage.Literals.COMPONENT_EXCHANGE);
  }

  /**
   * Hide Function Port without Exchanges in Diagram
   * 
   * @param context_p
   *          : Function Port
   * @param diagram_p
   *          : DDiagram
   * @return true if FunctionPort[input/output] has (incoming/outgoing)FunctionExchange displayed in diagram.
   */
  public boolean isFunctionPortWithoutExchanges(EObject context_p, DDiagram diagram_p) {
    return isNodeWithoutEdge(context_p, diagram_p, FaPackage.Literals.FUNCTION_PORT, FaPackage.Literals.FUNCTIONAL_EXCHANGE);
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

    public FunctionContainer(DNodeContainer container_p, FunctionContainer parent_p, DDiagram diagram_p, Map<AbstractFunction, AbstractDNode> functions_p) {
      container = container_p;
      parent = parent_p;
      function = (AbstractFunction) container_p.getTarget();
      incomingExchangeCategories = new HashMap<ExchangeCategory, DNode>();
      outgoingExchangeCategories = new HashMap<ExchangeCategory, DNode>();
      functionPorts = new HashMap<FunctionPort, DNode>();
      categoryNodesToRemove = new HashSet<DNode>();

      for (DNode aNode : container_p.getOwnedBorderedNodes()) {
        if ((aNode.getTarget() != null) && (aNode.getTarget() instanceof FunctionPort)) {
          functionPorts.put((FunctionPort) aNode.getTarget(), aNode);
        }
        if ((aNode.getTarget() != null) && (aNode.getActualMapping().getName().equals(FaServices.getFaServices().getMappingNameInputPinCategory(diagram_p)))) {
          incomingExchangeCategories.put((ExchangeCategory) aNode.getTarget(), aNode);
        }
        if ((aNode.getTarget() != null) && (aNode.getActualMapping().getName().equals(FaServices.getFaServices().getMappingNameOutputPinCategory(diagram_p)))) {
          outgoingExchangeCategories.put((ExchangeCategory) aNode.getTarget(), aNode);
        }
      }

    }

    public void initCategoryNodesToRemove(Map<AbstractFunction, AbstractDNode> functions_p) {
      // init list of exchangeCategory Nodes to Remove
      for (Entry<ExchangeCategory, DNode> me : this.incomingExchangeCategories.entrySet()) {
        boolean toRemove = true;
        // check for each exchange of the category if it has the current
        // container as target function and if the source
        // Function is in the diagram
        for (FunctionalExchange anExchange : me.getKey().getExchanges()) {
          AbstractDNode targetPortContainer = getBestFunctionContainer(anExchange.getTarget(), functions_p);
          AbstractDNode sourcePortContainer = getBestFunctionContainer(anExchange.getSource(), functions_p);
          if ((targetPortContainer != null) && (sourcePortContainer != null) && targetPortContainer.equals(this.container)) {
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
          AbstractDNode targetPortContainer = getBestFunctionContainer(anExchange.getTarget(), functions_p);
          AbstractDNode sourcePortContainer = getBestFunctionContainer(anExchange.getSource(), functions_p);
          if ((targetPortContainer != null) && (sourcePortContainer != null) && sourcePortContainer.equals(this.container)) {
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
      Set<DNode> nodes = new HashSet<DNode>();

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

    public void setIncomingOutgoingExchangeCategories(DDiagram diagram_p) {
      this.incomingExchangeCategories = new HashMap<ExchangeCategory, DNode>();
      this.outgoingExchangeCategories = new HashMap<ExchangeCategory, DNode>();
      for (DNode aNode : this.getContainer().getOwnedBorderedNodes()) {
        if ((aNode.getTarget() != null) && (aNode.getActualMapping().getName().equals(FaServices.getFaServices().getMappingNameInputPinCategory(diagram_p)))) {
          incomingExchangeCategories.put((ExchangeCategory) aNode.getTarget(), aNode);
        }
        if ((aNode.getTarget() != null) && (aNode.getActualMapping().getName().equals(FaServices.getFaServices().getMappingNameOutputPinCategory(diagram_p)))) {
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
  public void reorderFAElements(DDiagram diagram_p) {
    Hashtable<EObject, DDiagramElement> elementsInDiagram = new Hashtable<EObject, DDiagramElement>(); // all
    // displayed
    // elements
    // in
    // the
    // diagram
    Set<DDiagramElement> toBeMoved = new HashSet<DDiagramElement>(); // diagram
    // elements
    // to
    // be
    // moved
    Set<FunctionPort> functionPortsInDiagram = new HashSet<FunctionPort>();
    Map<AbstractFunction, AbstractDNode> allFunctionsInDiagram = new HashMap<AbstractFunction, AbstractDNode>();
    DDiagramContents content = new DDiagramContents(diagram_p);

    // get all displayed functions in the diagram
    for (DDiagramElement aContainer : diagram_p.getContainers()) {
      if ((aContainer != null) && (aContainer.getTarget() != null) && FaServices.getFaServices().isAbstractFunctionVisibleInDFB((AbstractDNode) aContainer, diagram_p)) {
        elementsInDiagram.put(aContainer.getTarget(), aContainer);
        if (aContainer.getTarget() instanceof AbstractFunction) {
          allFunctionsInDiagram.put((AbstractFunction) aContainer.getTarget(), (AbstractDNode) aContainer);
        }
      }
    }
    // get all displayed control nodes and FunctionPorts in the diagram
    for (DNode aNode : diagram_p.getNodes()) {
      if ((aNode != null) && (aNode.getTarget() != null) && (aNode.getTarget() instanceof FunctionPort)) {
        functionPortsInDiagram.add((FunctionPort) aNode.getTarget());
      }
    }

    // The algorithm checks if the Control Nodes and Functions have to be
    // moved
    // it needs two iterations

    // first iteration (to avoid null container)
    // the elements to be moved are temporarily placed in the diagram
    for (DDiagramElement anElement : elementsInDiagram.values()) {
      EObject parent = anElement.getTarget().eContainer();
      // case if the actual container is not available any more :
      // test if the container (A Function) is an ancestor of the current
      // AbstractFunction
      if (anElement.eContainer() instanceof DNodeContainer) {
        EObject actualParentContainer = ((DNodeContainer) anElement.eContainer()).getTarget();
        if (!org.eclipse.emf.ecore.util.EcoreUtil.isAncestor(actualParentContainer, anElement.getTarget())) {
          diagram_p.getOwnedDiagramElements().add(anElement);
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
        if ((null != parentGraphicalElement) && (parentGraphicalElement instanceof DNodeContainer)
            && CapellaServices.getService().isVisibleInDiagram(diagram_p, parentGraphicalElement) && canContainSubContainer(parentGraphicalElement)) {
          DNodeContainer nodeContainer = (DNodeContainer) parentGraphicalElement;
          // if the parent (diagramElement) does not contain the
          // current function (diagramElement)
          // the current function (diagramElement) must be moved
          if (!nodeContainer.getOwnedDiagramElements().contains(anElement)) {
            diagram_p.getOwnedDiagramElements().add(anElement);
            toBeMoved.add(anElement);
          }
          break;
        }
        parent = parent.eContainer();
      }
    }

    // second iteration
    // the elements are correctly moved
    for (DDiagramElement anElement : toBeMoved) {
      // for each parent of the function to be moved, we tests if a
      // diagramElement representing the parent appears in
      // the diagram
      // When a parent is found in the diagram, we moved the function and
      // stop.
      EObject parent = anElement.getTarget().eContainer();
      while ((parent instanceof AbstractFunction) || (parent instanceof FunctionPkg)) {
        DDiagramElement parentGraphicalElement = elementsInDiagram.get(parent);
        if ((null != parentGraphicalElement) && (parentGraphicalElement instanceof DNodeContainer)) {
          DNodeContainer nodeContainer = (DNodeContainer) parentGraphicalElement;
          if (!nodeContainer.getOwnedDiagramElements().contains(anElement)) {
            nodeContainer.getOwnedDiagramElements().add(anElement);
            break;
          }
        }
        parent = parent.eContainer();
      }
    }

    Set<DNodeContainer> ownedVisibleFunctionContainers = new HashSet<DNodeContainer>();
    for (DDiagramElement anElement : diagram_p.getOwnedDiagramElements()) {
      if ((anElement instanceof DNodeContainer) && (anElement.getTarget() instanceof AbstractFunction)
          && (FaServices.getFaServices().isAbstractFunctionVisibleInDFB((DNodeContainer) anElement, diagram_p))) {
        ownedVisibleFunctionContainers.add((DNodeContainer) anElement);
      }
    }
    if (!(((DSemanticDiagram) diagram_p).getTarget() instanceof OperationalActivity)) {
      for (DNodeContainer aContainer : ownedVisibleFunctionContainers) {
        updateBorderedNodes(aContainer, content, null, allFunctionsInDiagram);
      }
    }

  }

  /**
   * This method is necessary because of contextual Data Flow diagrams In contextual diagrams, only the current
   * contextual function can contain subContainers
   * 
   * @param aContainer_p
   *          a container
   * @return true if the container can contain sub containers
   */
  public boolean canContainSubContainer(DDiagramElement aContainer_p) {
    if (!(aContainer_p instanceof DNodeContainer)) {
      return false;
    }
    DSemanticDiagram currentDiagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(aContainer_p);
    if (currentDiagram.getDescription().getName().contains("Contextual")) { //$NON-NLS-1$
      return (aContainer_p.getTarget().equals(currentDiagram.getTarget()));
    }
    return true;
  }

  /**
   * remove/move recursively borderedNodes representing FunctionPorts or ExchangeCategories
   * 
   * @param container_p
   *          current container
   * @param diagram_p
   *          current diagram
   * @param parentContainer_p
   *          parent container
   * @param functions_p
   *          functions in diagram_p
   * @return
   */
  protected FunctionContainer updateBorderedNodes(DNodeContainer container_p, DDiagramContents content_p, FunctionContainer parentContainer_p,
      Map<AbstractFunction, AbstractDNode> functions_p) {
    FunctionContainer currentContainer = new FunctionContainer(container_p, parentContainer_p, content_p.getDDiagram(), functions_p);
    DDiagram diagram = content_p.getDDiagram();

    // function ports
    if (null != currentContainer.getParent()) {
      // for all parent port, if the port should be display in the
      // function, move or create it
      for (Map.Entry<FunctionPort, DNode> me : currentContainer.getParent().getFunctionPorts().entrySet()) {
        if (((me.getValue().eContainer() != null) && (((DDiagramElement) me.getValue().eContainer()).getTarget() != null))) {
          AbstractFunction best = getBestPortFunctionContainer((Pin) me.getKey(), (AbstractFunction) ((DDiagramElement) me.getValue().eContainer()).getTarget(),
              currentContainer.getFunction());
          if ((best != null) && best.equals(currentContainer.getFunction())) {
            if (currentContainer.getFunctionPorts().containsKey(me.getKey())) {
              // delete the bordered node on parent function if it
              // already exists on a child function
              removeNodeAndMoveEdges(me.getValue(), currentContainer.getFunctionPorts().get(me.getKey()), content_p.getDDiagram());
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
    if (parentContainer_p != null) {
      parentContainer_p.setIncomingOutgoingExchangeCategories(content_p.getDDiagram());

      // TODO BUG: In some tricky case where parent category is hidden,
      // categories on children are created but shouldn't
      HashMapSet<ExchangeCategory, AbstractFunction> availableCategories = FaServices.getFaServices().getAvailableCategoriesAndFunctionsToInsertInDataFlowBlank(
          currentContainer.getContainer(), content_p);
      // For all categories of the parentContainer, propagate to children.
      // If not synchronized, create category exchanges in children only
      // if category
      // of parent container need to be removed.
      for (DNode aNode : parentContainer_p.getCategoryNodes()) {
        if (!CapellaServices.getService().isSynchronized(content_p.getDDiagram())) {
          if (!parentContainer_p.getCategoryNodesToRemove().contains(aNode)) {
            continue;
          }
        }
        // avoid creation of category in child if parent node was hidden
        if (!aNode.isVisible()) {
          continue;
        }

        ExchangeCategory currentCategory = (ExchangeCategory) aNode.getTarget();
        if (availableCategories.containsKey(currentCategory)) {
          if (aNode.getActualMapping().getName().equals(FaServices.getFaServices().getMappingNameOutputPinCategory(diagram))) {
            for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(aNode, diagram)) {
              if ((anEdge.getTargetNode() != null) && (anEdge.getTargetNode().eContainer() != null)) {
                DNodeContainer targetFunctionContainer = (DNodeContainer) anEdge.getTargetNode().eContainer();
                if (availableCategories.get(currentCategory).contains(targetFunctionContainer.getTarget())) {
                  FaServices.getFaServices().createViewExchangeCategory(currentCategory, container_p, targetFunctionContainer, diagram);
                  if (!currentContainer.getOutgoingExchangeCategories().containsKey(currentCategory)) {
                    currentContainer.getOutgoingExchangeCategories().put(currentCategory,
                        getBorderedNode(container_p, currentCategory, FaServices.getFaServices().getMappingNameOutputPinCategory(diagram)));
                  }
                }
              }
            }
          }
          if (aNode.getActualMapping().getName().equals(FaServices.getFaServices().getMappingNameInputPinCategory(diagram))) {
            for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(aNode, diagram)) {
              if ((anEdge.getSourceNode() != null) && (anEdge.getSourceNode().eContainer() != null)) {
                DNodeContainer sourceFunctionContainer = (DNodeContainer) anEdge.getSourceNode().eContainer();
                if (availableCategories.get(currentCategory).contains(sourceFunctionContainer.getTarget())) {
                  FaServices.getFaServices().createViewExchangeCategory(currentCategory, sourceFunctionContainer, container_p, diagram);
                  if (!currentContainer.getIncomingExchangeCategories().containsKey(currentCategory)) {
                    currentContainer.getIncomingExchangeCategories().put(currentCategory,
                        getBorderedNode(container_p, currentCategory, FaServices.getFaServices().getMappingNameInputPinCategory(diagram)));
                  }
                }
              }
            }
          }
        }
      }
    }
    currentContainer.initCategoryNodesToRemove(functions_p);

    for (DDiagramElement anElement : container_p.getOwnedDiagramElements()) {
      // recursively update bordered nodes on contained visible
      // NodeContainers
      if ((anElement instanceof DNodeContainer) && (anElement.getTarget() != null) && (anElement.getTarget() instanceof AbstractFunction)
          && FaServices.getFaServices().isAbstractFunctionVisibleInDFB((DNodeContainer) anElement, diagram)) {
        updateBorderedNodes((DNodeContainer) anElement, content_p, currentContainer, functions_p);
      }
      // move up borderedNodes of invisible sub functions
      if ((anElement instanceof DNodeContainer) && (anElement.getTarget() != null) && (anElement.getTarget() instanceof AbstractFunction)
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
   * @param anElement_p
   * @param diagram_p
   * @param currentContainer_p
   * @param functions_p
   */
  public void moveUpBorderedNodes(DNodeContainer container_p, DDiagram diagram_p, FunctionContainer parentContainer_p) {

    Set<DDiagramElement> ownedDiagramElements = new HashSet<DDiagramElement>();
    ownedDiagramElements.addAll(container_p.getOwnedDiagramElements());

    Set<DNode> ownedBorderedNodes = new HashSet<DNode>();
    ownedBorderedNodes.addAll(container_p.getOwnedBorderedNodes());
    // move up controlNodes as Ports and function ports
    for (DNode aBorderedNode : ownedBorderedNodes) {
      if ((aBorderedNode.getTarget() != null) && (aBorderedNode.getTarget() instanceof FunctionPort) && CapellaServices.getService().isVisibleInDiagram(diagram_p, aBorderedNode)
          && !CapellaServices.getService().isSynchronized(diagram_p)) {
        if (parentContainer_p.getFunctionPorts().containsKey(aBorderedNode.getTarget())) {
          // delete the bordered node on current function if it
          // already exists on the parent function
          removeNodeAndMoveEdges(aBorderedNode, parentContainer_p.getFunctionPorts().get(aBorderedNode.getTarget()), diagram_p);
        } else {
          // move the bordered Node on the parent function
          parentContainer_p.getContainer().getOwnedBorderedNodes().add(aBorderedNode);
          parentContainer_p.getFunctionPorts().put((FunctionPort) aBorderedNode.getTarget(), aBorderedNode);
        }
      }
      if ((aBorderedNode.getTarget() != null) && (aBorderedNode.getTarget() instanceof ExchangeCategory)
          && CapellaServices.getService().isVisibleInDiagram(diagram_p, aBorderedNode)) {
        if (aBorderedNode.getActualMapping().getName().equals(FaServices.getFaServices().getMappingNameInputPinCategory(diagram_p))) {
          if (!parentContainer_p.getIncomingExchangeCategories().containsKey(aBorderedNode.getTarget())) {
            // create borderedNode view on parent function
            DNode newNode = FaServices.getFaServices().createViewInputPinCategory((ExchangeCategory) aBorderedNode.getTarget(), parentContainer_p.getContainer(), diagram_p);
            parentContainer_p.getIncomingExchangeCategories().put((ExchangeCategory) aBorderedNode.getTarget(), newNode);
          }
          // delete the bordered node on current function if it
          // already exists on the parent function and move edges
          removeNodeAndMoveEdges(aBorderedNode, parentContainer_p.getIncomingExchangeCategories().get(aBorderedNode.getTarget()), diagram_p);
        }
        if (aBorderedNode.getActualMapping().getName().equals(FaServices.getFaServices().getMappingNameOutputPinCategory(diagram_p))) {
          if (!parentContainer_p.getOutgoingExchangeCategories().containsKey(aBorderedNode.getTarget())) {
            // create borderedNode view on parent function
            DNode newNode = FaServices.getFaServices().createViewOutputPinCategory((ExchangeCategory) aBorderedNode.getTarget(), parentContainer_p.getContainer(), diagram_p);
            parentContainer_p.getOutgoingExchangeCategories().put((ExchangeCategory) aBorderedNode.getTarget(), newNode);
          }
          // delete the bordered node on current function if it
          // already exists on the parent function and move edges
          removeNodeAndMoveEdges(aBorderedNode, parentContainer_p.getOutgoingExchangeCategories().get(aBorderedNode.getTarget()), diagram_p);
        }
      }
    }

  }

  /**
   * @param port_p
   *          a FunctionPort or ControlNode
   * @param function1_p
   * @param function2_p
   * @return the function that must contain graphically the functionPort or controlNode
   */
  protected AbstractFunction getBestPortFunctionContainer(ActivityNode port_p, AbstractFunction function1_p, AbstractFunction function2_p) {
    EObject container = port_p.eContainer();
    while ((container != null) && (container instanceof AbstractFunction)) {
      if (container.equals(function1_p)) {
        return function1_p;
      }
      if (container.equals(function2_p)) {
        return function2_p;
      }
      container = container.eContainer();
    }
    return null;
  }

  /**
   * remove a node and move incoming and outgoing edges on the existing child/parent node
   * 
   * @param toBeRemoved_p
   *          the bordered node to remove
   * @param existingChildNode_p
   *          the existing child/parent borderedNode
   * @param diagram_p
   *          the current diagram
   */
  protected void removeNodeAndMoveEdges(DNode toBeRemoved_p, DNode existingChildNode_p, DDiagram diagram_p) {
    Set<DEdge> incomingExchanges = new HashSet<DEdge>();
    Set<DEdge> outgoingExchanges = new HashSet<DEdge>();
    incomingExchanges.addAll(DiagramServices.getDiagramServices().getIncomingEdges(existingChildNode_p, diagram_p));
    outgoingExchanges.addAll(DiagramServices.getDiagramServices().getOutgoingEdges(existingChildNode_p, diagram_p));

    for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(toBeRemoved_p, diagram_p)) {
      // move edge if necessary
      boolean toMove = true;
      for (DEdge anExistingEdge : incomingExchanges) {
        if ((anExistingEdge.getTarget() != null) && (anExistingEdge.getSourceNode() != null) && (anExistingEdge.getTarget().equals(anEdge.getTarget()))
            && (anExistingEdge.getSourceNode().equals(anEdge.getSourceNode()))) {
          toMove = false;
        }
      }
      if (toMove) {
        anEdge.setTargetNode(existingChildNode_p);
      } else {
        DiagramServices.getDiagramServices().removeEdgeView(anEdge);
      }
    }
    for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(toBeRemoved_p, diagram_p)) {
      // move edge if necessary
      boolean toMove = true;
      for (DEdge anExistingEdge : outgoingExchanges) {
        if ((anExistingEdge.getTarget() != null) && (anExistingEdge.getTargetNode() != null) && (anExistingEdge.getTarget().equals(anEdge.getTarget()))
            && (anExistingEdge.getTargetNode().equals(anEdge.getTargetNode()))) {
          toMove = false;
        }
      }
      if (toMove) {
        anEdge.setSourceNode(existingChildNode_p);
      } else {
        DiagramServices.getDiagramServices().removeEdgeView(anEdge);
      }
    }
    DiagramServices.getDiagramServices().removeNodeView(toBeRemoved_p);
  }

  /**
   * @param port_p
   * @param functions_p
   *          Map of visible containers in the diagram
   * @return the visible container in the diagram that must contain the port port_p
   */
  public AbstractDNode getBestFunctionContainer(ActivityNode port_p, Map<AbstractFunction, AbstractDNode> functions_p) {
    if ((port_p == null) || (port_p.eContainer() == null)) {
      return null;
    }
    EObject functionContainer = port_p.eContainer();
    while ((functionContainer != null) && (functionContainer instanceof AbstractFunction)) {
      if (functions_p.containsKey(functionContainer)) {
        return functions_p.get(functionContainer);
      }
      functionContainer = functionContainer.eContainer();
    }
    return null;
  }

  public DNode getBorderedNode(DNodeContainer container_p, EObject target_p, String mappingName_p) {
    DNode returnedNode = null;
    for (DNode aBorderedNode : container_p.getOwnedBorderedNodes()) {
      if ((aBorderedNode.getTarget() != null) && aBorderedNode.getTarget().equals(target_p) && aBorderedNode.getActualMapping().getName().equals(mappingName_p)) {
        return aBorderedNode;
      }
    }
    return returnedNode;
  }

  public List<AbstractFunction> getShowableParentOfAllocatedFunctions(AbstractFunction parent_p, List<AbstractFunction> showableLeaves_p) {
    return showableLeaves_p;

  }

  public List<AbstractFunction> getShowableAllocatedFunctions(Component component_p) {
    return AbstractFunctionExt.getAllocatedFunctions(component_p);
  }

  public List<AbstractFunction> getShowableAllocatedOperationalActivities(Role role_p) {
    return AbstractFunctionExt.getAllocatedOperationalActivities(role_p);
  }

  public DNodeContainer createViewDeployedPart(EObject target_p, DragAndDropTarget parent_p, DDiagram parentDiagram_p) {
    ContainerMapping mapping = getMappingABDeployedElement(parentDiagram_p);
    return DiagramServices.getDiagramServices().createContainer(mapping, target_p, parent_p, parentDiagram_p);
  }

  public ContainerMapping getMappingABDeployedElement(DDiagram diagram_p) {
    String mappingName = IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME;
    return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
  }

  public boolean isLeaf(EObject function_p) {
    return FunctionExt.isLeaf((AbstractFunction) function_p);
  }

  /**
   * Get all the leaf Functions
   * 
   * @param arch_p
   * @return : List of leaf Functions
   */
  public List<AbstractFunction> getAllLeafAbstractFunctions(BlockArchitecture arch_p) {
    return FunctionExt.getAllLeafAbstractFunctions(arch_p);
  }

  public AbstractFunction getOutgoingAbstractFunction(FunctionalExchange fe_p) {
    return FunctionExt.getOutGoingAbstractFunction(fe_p);
  }

  public EObject insertRemoveAllocatedFunctions(DNodeContainer containerView_p, List<AbstractFunction> selectedFunctions_p) {
    HashMap<AbstractFunction, DNode> visibleFunctions = new HashMap<AbstractFunction, DNode>();

    for (DDiagramElement aElement : containerView_p.getOwnedDiagramElements()) {
      if ((aElement.getTarget() != null) && (aElement.getTarget() instanceof AbstractFunction) && (aElement instanceof DNode)) {
        visibleFunctions.put((AbstractFunction) aElement.getTarget(), (DNode) aElement);
      }
    }
    // delete not selected functions if they are displayed in the container
    // view
    for (Entry<AbstractFunction, DNode> me : visibleFunctions.entrySet()) {
      if (!selectedFunctions_p.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeNodeView(me.getValue());
      } else {
        for (AbstractFunction aParentFunction : FunctionExt.getParentFunctions(me.getKey())) {
          if (selectedFunctions_p.contains(aParentFunction)) {
            DiagramServices.getDiagramServices().removeNodeView(me.getValue());
            break;
          }
        }
      }
    }
    // create view for selected elements if they do not exist
    for (AbstractFunction aSelectedFunction : selectedFunctions_p) {
      if (!visibleFunctions.containsKey(aSelectedFunction)) {
        boolean toAdd = true;
        // test if a parent function is already displayed
        for (AbstractFunction aParentFunction : FunctionExt.getParentFunctions(aSelectedFunction)) {
          if (selectedFunctions_p.contains(aParentFunction)) {
            toAdd = false;
            break;
          }
        }
        if (toAdd) {
          createViewABAbstractFunction(aSelectedFunction, containerView_p, CapellaServices.getService().getDiagramContainer(containerView_p));
        }
      }
    }
    return containerView_p;
  }

  /**
   * Return all the leaf functions from given Block Architecture
   */
  public List<AbstractFunction> getAllLeafFunctions(BlockArchitecture blockArchitecture_p) {
    List<AbstractFunction> allAbstractFunctions = FunctionExt.getAllLeafAbstractFunctions(blockArchitecture_p);
    if (!allAbstractFunctions.isEmpty()) {
      return allAbstractFunctions;
    }

    return new ArrayList<AbstractFunction>(0);
  }

  public List<AbstractFunction> getAllLeafFunctions(ModellingBlock block_p) {
    BlockArchitecture archi = BlockArchitectureExt.getRootBlockArchitecture(block_p);
    return getAllLeafFunctions(archi);
  }

  /**
   * Return all the recursive functions from given Block Architecture
   */
  public List<FunctionalChain> getAllFunctionalChains(BlockArchitecture blockArchitecture_p) {
    List<FunctionalChain> functionalChains = new ArrayList<FunctionalChain>(0);

    // collect all functions
    List<AbstractFunction> allAbstractFunctions = FunctionExt.getAllAbstractFunctions(blockArchitecture_p);
    for (AbstractFunction abstractFunction : allAbstractFunctions) {
      functionalChains.addAll(abstractFunction.getOwnedFunctionalChains());
    }

    // collect all capabilities

    TableCapabilitiesServices cap = new TableCapabilitiesServices();
    Collection<AbstractCapability> allCapabilities = cap.getAllCapabilities(blockArchitecture_p);
    for (AbstractCapability abstractCapability : allCapabilities) {
      functionalChains.addAll(abstractCapability.getOwnedFunctionalChains());
    }

    return functionalChains;
  }

  public List<FunctionalChain> getAllFunctionalChains(ModellingBlock block_p) {
    BlockArchitecture archi = BlockArchitectureExt.getRootBlockArchitecture(block_p);
    return getAllFunctionalChains(archi);
  }

  /**
   * Perform dnd from diagram to diagram of a physical artifacts.
   * 
   * @param node_p
   *          the given physical artifacts
   * @param oldContainer
   *          the given namedElement
   * @param newContainer
   *          the given namedElement
   * @return the EObject
   */
  public EObject dndABPhysicalArtifacts(CapellaElement node_p, NamedElement oldContainer, NamedElement newContainer) {
    // Physical Artifacts == Physical Component, Physical Link and Physical
    // Port

    if (oldContainer.equals(newContainer)) {
      return node_p;
    }

    // Get root architecture, and make sure that its physical architecture
    BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(node_p);
    if (!(arch instanceof PhysicalArchitecture)) {
      return node_p;
    }

    // Collect all physical artifacts
    List<CapellaElement> physicalArtifacts = new ArrayList<CapellaElement>(0);
    List<PhysicalComponent> allPhysicalComponents = PhysicalArchitectureExt.getAllPhysicalComponents((PhysicalArchitecture) arch);
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
      if ((componentType != null) && (componentType instanceof ConfigurationItem)) {
        oldComponent = (ConfigurationItem) componentType;
      }
    } else if (oldContainer instanceof ConfigurationItem) {
      oldComponent = (ConfigurationItem) oldContainer;
    }

    if (newContainer instanceof Part) {
      EObject componentType = CsServices.getService().getComponentType((Part) newContainer);
      if ((componentType != null) && (componentType instanceof ConfigurationItem)) {
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
      List<TraceableElement> newArtifactRealizationsTarget = new ArrayList<TraceableElement>();
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
        if ((null != targetElement) && physicalArtifacts.contains(targetElement) && node_p.equals(targetElement) && !newArtifactRealizationsTarget.contains(targetElement)) {
          artifactRealization.setSourceElement(newComponent);
          newComponent.getOwnedPhysicalArtifactRealizations().add(artifactRealization);
        }
      }
    }

    return node_p;
  }

  /**
   * @used context.odesign returns display name of functional exchange
   * @param exchange_p
   *          the functional exchange
   * @return display name of the functional exchange
   */
  public String getComponentExchangeLabel(ComponentExchange exchange_p, DDiagram diagram_p) {
    if ((exchange_p == null) || isHideComponentExchangesNamesEnable(exchange_p, diagram_p) || isHideCommunicationMeansNamesEnable(exchange_p, diagram_p)) {
      return ICommonConstants.EMPTY_STRING;
    }

    boolean showExchangeItems = false;
    boolean showFunctionalExchangeName = false;
    boolean showExchangeItemsWithOutFE = false;
    // check the activation of the filters
    for (FilterDescription filter : diagram_p.getActivatedFilters()) {
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
      ArrayList<FunctionalExchange> fes = new ArrayList<FunctionalExchange>();
      for (ComponentExchangeFunctionalExchangeAllocation allocation : exchange_p.getOutgoingComponentExchangeFunctionalExchangeAllocations()) {
        if ((allocation.getAllocatedFunctionalExchange() != null) && !fes.contains(allocation.getAllocatedFunctionalExchange())) {
          fes.add(allocation.getAllocatedFunctionalExchange());
        }
      }
      int index = 0;
      if (!showExchangeItemsWithOutFE) {
        for (FunctionalExchange fe : fes) {
          index++;
          getExchangeWithExchangeItemsLabel(fe, false, true, showExchangeItems, showExchangeItemsWithOutFE, result, getRelatedExchangeItems(fe));
          if (index < fes.size()) {
            result.append(", "); //$NON-NLS-1$
          }
        }
      }
      if (fes.size() == 0) {
        List<AbstractExchangeItem> exchangedItems = getRelatedExchangeItems(exchange_p, false);
        getExchangeWithExchangeItemsLabel(exchange_p, false, (!showExchangeItems && !showExchangeItemsWithOutFE) || exchangedItems.isEmpty(), showExchangeItems,
            showExchangeItemsWithOutFE, result, exchangedItems);
      }

    } else {
      // create unique list
      List<AbstractExchangeItem> exchangedItems = new UniqueEList<AbstractExchangeItem>(0);
      // showExchangeItems
      if (showExchangeItems) {
        exchangedItems.addAll(getRelatedExchangeItems(exchange_p, true));
      }
      // showExchangeItemsWithOutFE
      if (showExchangeItemsWithOutFE) {
        exchangedItems.addAll(getRelatedExchangeItems(exchange_p, false));
      }
      getExchangeWithExchangeItemsLabel(exchange_p, false, (!showExchangeItems && !showExchangeItemsWithOutFE), showExchangeItems, showExchangeItemsWithOutFE, result,
          exchangedItems);

    }
    return result.toString();
  }

  /**
   * @used context.odesign returns display name of functional exchange
   * @param exchange_p
   *          the functional exchange
   * @return display name of the functional exchange
   */
  public String getFunctionalExchangeLabel(FunctionalExchange exchange_p, DDiagram diagram_p) {
    if (exchange_p == null) {
      return decorateString(ICommonConstants.EMPTY_STRING, exchange_p);
    }
    if (isHideFunctionalExchangesNamesEnable(exchange_p, diagram_p) || isHideInteractionsNamesEnable(exchange_p, diagram_p)) {
      return Character.toString(ICommonConstants.WHITE_SPACE_CHARACTER);
    }

    boolean showExchangeItems = false;
    boolean showExchangeItemsParameters = false;
    boolean showFunctionalExchanges = false;
    boolean showFEEI = false;
    boolean showFEParams = false;
    boolean showFEEIParams = false;

    // check the activation of the filters
    for (FilterDescription filter : diagram_p.getActivatedFilters()) {
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
      return decorateString(getFEEIMessageName(exchange_p), exchange_p);
    }
    if (showFEEIParams || showFEParams) {
      return decorateString(showFeEiParams(exchange_p, showFEEIParams), exchange_p);
    }

    StringBuilder result = new StringBuilder();

    // exchangeItems or name
    int indice = 0;
    if (showFunctionalExchanges || showExchangeItems || showExchangeItemsParameters) {
      if (showFunctionalExchanges) {
        result.append(getSafeName(exchange_p));
      }
      if (showExchangeItems || showExchangeItemsParameters) {
        List<? extends AbstractExchangeItem> selectEIList = exchange_p.getExchangedItems();
        if (selectEIList.size() != 0) {
          result.append("["); //$NON-NLS-1$
        }
        for (AbstractExchangeItem ei : selectEIList) {
          result.append(InformationServices.getEILabel(ei, showExchangeItemsParameters));
          indice++;
          if (indice < selectEIList.size()) {
            result.append(", "); //$NON-NLS-1$
          }
        }
        if (selectEIList.size() != 0) {
          result.append("]"); //$NON-NLS-1$
        }
      }
    } else {
      result.append(exchange_p.getName());
    }
    return decorateString(result.toString(), exchange_p);
  }

  private String showFeEiParams(FunctionalExchange exchange_p, boolean showEIName) {
    StringBuilder result = new StringBuilder();
    List<? extends AbstractExchangeItem> selectEIList;
    selectEIList = exchange_p.getExchangedItems();

    result.append(getSafeName(exchange_p));

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
      List<ExchangeItemElement> eies = new ArrayList<ExchangeItemElement>();
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

  private String getFEEIMessageName(FunctionalExchange exchange_p) {
    StringBuilder result = new StringBuilder();
    List<? extends AbstractExchangeItem> selectEIList;
    selectEIList = exchange_p.getExchangedItems();
    result.append(exchange_p.getName());
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

  private List<AbstractExchangeItem> getRelatedExchangeItems(ComponentExchange exchange_p, boolean inDepth_p) {
    // exchangeItems
    List<AbstractExchangeItem> exchangedItems = new ArrayList<AbstractExchangeItem>();
    exchangedItems.addAll(exchange_p.getConvoyedInformations());
    if (inDepth_p) {
      for (ComponentExchangeFunctionalExchangeAllocation allocation : exchange_p.getOutgoingComponentExchangeFunctionalExchangeAllocations()) {
        if (allocation.getAllocatedFunctionalExchange() != null) {
          for (AbstractExchangeItem abstractExchangeItem : getRelatedExchangeItems(allocation.getAllocatedFunctionalExchange())) {
            if (!exchangedItems.contains(abstractExchangeItem)) {
              exchangedItems.add(abstractExchangeItem);
            }
          }
        }
      }
    }

    return exchangedItems;
  }

  private List<AbstractExchangeItem> getRelatedExchangeItems(FunctionalExchange exchange_p) {
    // exchangeItems
    List<AbstractExchangeItem> exchangedItems = new ArrayList<AbstractExchangeItem>();

    exchangedItems.addAll(exchange_p.getExchangedItems());
    if (exchangedItems.size() == 0) {
      for (CapellaElement capellaElement : AbstractFunctionExt.getExchangeSourceAndTargetPorts(exchange_p)) {
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

  private void getExchangeWithExchangeItemsLabel(AbstractNamedElement exchange_p, boolean showExchangeItemsParameters, boolean showName, boolean showExchangeItems,
      boolean showExchangeItemsWithOutFE, StringBuilder result, List<AbstractExchangeItem> exchangedItems) {
    int indice = 0;
    if (showName) {
      result.append(exchange_p.getName());
    }

    if (showExchangeItems || showExchangeItemsWithOutFE) {
      if (showName && (exchangedItems.size() > 0)) {
        result.append(" ("); //$NON-NLS-1$
      }
      for (AbstractExchangeItem ei : exchangedItems) {
        result.append(ExchangeItemExt.getEILabel(ei, showExchangeItemsParameters));
        indice++;
        if (indice < exchangedItems.size()) {
          result.append(", "); //$NON-NLS-1$
        }
      }
      if (showName && (exchangedItems.size() > 0)) {
        result.append(")"); //$NON-NLS-1$
      }
    }

  }

  public DDiagramContents getDDiagramContents(DDiagram diagram_p) {
    return new DDiagramContents(diagram_p);
  }

  /**
   * @param diagram_p
   * @param chains_p
   */
  public void showABFunctionalChains(DDiagram diagram_p, Collection<EObject> chains_p) {
    showABFunctionalChains(diagram_p, chains_p, getDDiagramContents(diagram_p));
  }

  /**
   * @param diagram_p
   * @param chains_p
   */
  public void showDFFunctionalChains(DDiagram diagram_p, Collection<EObject> chains_p) {
    showDFFunctionalChains(diagram_p, chains_p, getDDiagramContents(diagram_p));
  }

  /**
   * @param diagram_p
   * @param chains_p
   */
  public void showABFunctionalChains(DDiagram diagram_p, Collection<EObject> chains_p, DDiagramContents context_p) {
    for (EObject object : chains_p) {
      if (context_p.getNode(object) == null) {
        createViewABFunctionalChain(diagram_p, object);
      }
    }
  }

  /**
   * @param diagram_p
   * @param chains_p
   */
  public void showDFFunctionalChains(DDiagram diagram_p, Collection<EObject> chains_p, DDiagramContents context_p) {
    for (EObject object : chains_p) {
      if (context_p.getNode(object) == null) {
        createViewDFFunctionalChain(diagram_p, object);
      }
    }
  }

  /**
   * @param diagram_p
   * @param object_p
   */
  private DNode createViewABFunctionalChain(DDiagram diagram_p, EObject object_p) {
    NodeMapping mapping = getMappingABFunctionalChain(object_p, diagram_p);
    return DiagramServices.getDiagramServices().createNode(mapping, object_p, diagram_p, diagram_p);
  }

  /**
   * @param diagram_p
   * @param object_p
   */
  private DNode createViewDFFunctionalChain(DDiagram diagram_p, EObject object_p) {
    NodeMapping mapping = getMappingDFFunctionalChain(object_p, diagram_p);
    return DiagramServices.getDiagramServices().createNode(mapping, object_p, diagram_p, diagram_p);
  }

  /**
   * @param port_p
   * @param diagram_p
   * @return
   */
  public NodeMapping getMappingABFunctionalChain(EObject port_p, DDiagram diagram_p) {
    String mappingName = null;

    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_OPERATIONAL_PROCESS_END_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
  }

  /**
   * @param port_p
   * @param diagram_p
   * @return
   */
  public NodeMapping getMappingDFFunctionalChain(EObject port_p, DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAIB_OPERATIONAL_PROCESS_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
  }

  /**
   * @param diagram_p
   * @param contextualElements_p
   */
  public void showDFContextualElements(DDiagramContents diagramContent_p, Collection<EObject> contextualElements_p) {
    Collection<AbstractFunction> contextualFunctions = new HashSet<AbstractFunction>();
    Collection<FunctionalExchange> contextualFunctionalExchanges = new HashSet<FunctionalExchange>();
    Collection<EObject> contextualFunctionalChains = new HashSet<EObject>();
    Collection<EObject> contextualModes = new HashSet<EObject>();
    Collection<EObject> contextualScenarios = new HashSet<EObject>();

    for (EObject contextualElement : contextualElements_p) {
      if (contextualElement instanceof AbstractFunction) {
        contextualFunctions.add((AbstractFunction) contextualElement);

        for (AbstractFunction function : FunctionExt.getAllAbstractFunctions((AbstractFunction) contextualElement)) {
          for (FunctionalExchange exchange : FunctionExt.getIncomingExchange(function)) {
            AbstractFunction source = FunctionalExchangeExt.getSourceFunction(exchange);
            AbstractFunction target = FunctionalExchangeExt.getTargetFunction(exchange);
            // Restrict to outside exchanges
            if ((source != null) && (target != null)) {
              if (!(EcoreUtil2.isContainedBy(source, contextualElement) && (EcoreUtil2.isContainedBy(target, contextualElement)))) {
                contextualFunctionalExchanges.add(exchange);
              }
            }
          }
          for (FunctionalExchange exchange : FunctionExt.getOutGoingExchange(function)) {
            AbstractFunction source = FunctionalExchangeExt.getSourceFunction(exchange);
            AbstractFunction target = FunctionalExchangeExt.getTargetFunction(exchange);
            // Restrict to outside exchanges
            if ((source != null) && (target != null)) {
              if (!(EcoreUtil2.isContainedBy(source, contextualElement) && (EcoreUtil2.isContainedBy(target, contextualElement)))) {
                contextualFunctionalExchanges.add(exchange);
              }
            }
          }
        }
      } else if (contextualElement instanceof FunctionalChain) {
        contextualFunctionalChains.add(contextualElement);

        for (FunctionalChainInvolvement involvement : FunctionalChainExt.getInvolvementsOf((FunctionalChain) contextualElement, FaPackage.Literals.ABSTRACT_FUNCTION)) {
          contextualFunctions.add((AbstractFunction) involvement.getInvolved());
        }
        for (FunctionalChainInvolvement involvement : FunctionalChainExt.getInvolvementsOf((FunctionalChain) contextualElement, FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
          contextualFunctionalExchanges.add((FunctionalExchange) involvement.getInvolved());
        }

      } else if (contextualElement instanceof Scenario) {
        contextualScenarios.add(contextualElement);

      } else if (contextualElement instanceof State) {
        contextualModes.add(contextualElement);

      }
    }

    // Display all contextual functions
    for (AbstractFunction function : contextualFunctions) {
      showDFAbstractFunction(function, diagramContent_p.getBestContainer(function), diagramContent_p);
    }

    // Create a customized diagram contents to retrieve brothers of diagram
    // elements instead of best container
    DDiagramContents extendedContent = new DDiagramContents(diagramContent_p) {

      @Override
      public EObject getElement(EObject object_p, EObject context_p) {
        if (context_p instanceof FunctionalExchange) {
          FunctionalExchange exchange = (FunctionalExchange) context_p;
          AbstractFunction sourceExchange = FunctionalExchangeExt.getSourceFunction(exchange);
          AbstractFunction targetExchange = FunctionalExchangeExt.getTargetFunction(exchange);

          AbstractFunction brother = null;
          AbstractFunction brother2 = null;

          if (object_p.equals(sourceExchange)) {
            brother = targetExchange;
            brother2 = sourceExchange;

          } else if (object_p.equals(targetExchange)) {
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
        return object_p;
      }

      @Override
      public Collection<EObject> getParents(EObject object_p, EObject context_p) {
        return super.getParents(object_p, context_p);
      }

      private boolean isVisible(EObject brother_p, EObject brother2_p) {
        EObject parent = brother_p.eContainer();
        if ((parent instanceof AbstractFunction) && (brother2_p instanceof AbstractFunction)) {
          if (FunctionExt.getFirstLevelAbstractFunctions((AbstractFunction) parent).contains(brother2_p)) {
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

    showDFFunctionalChains(diagramContent_p.getDDiagram(), contextualFunctionalChains, diagramContent_p);

    DFServices.getService().showDFScenarios((DSemanticDecorator) diagramContent_p.getDDiagram(), contextualScenarios);
    DFServices.getService().showDFStateModes((DSemanticDecorator) diagramContent_p.getDDiagram(), contextualModes);
  }

  /**
   * is [FUNCTIONKIND = FUNCTION] and not AcotorFunction or ControlNode
   * 
   * @param element_p
   * @return
   */
  public boolean isFunction(AbstractFunction element_p) {
    return FunctionExt.isFunction(element_p);
  }

  /**
   * is [FUNCTIONKIND = FUNCTION] and not AcotorFunction or ControlNode
   * 
   * @param element_p
   * @return
   */
  public boolean isActorFunction(AbstractFunction element_p) {
    return FunctionExt.isActorFunction(element_p);
  }

  public boolean isAllLeavesFunctionActorALlocated(AbstractFunction element_p) {
    int i, j = 0;
    if ((element_p == null) || isLeaf(element_p)) {
      return false;
    }
    List<AbstractFunction> leaves = FunctionExt.getAllLeafAbstractFunctions(element_p);
    i = leaves.size();
    for (AbstractFunction af : leaves) {
      if (FunctionExt.isActorFunction(af)) {
        j++;
      }
    }
    return (i != 0) && (i == j);

  }

  private boolean isDiagramFilterEnable(EObject exchange_p, EObject diagram_p, String filterName_p) {
    if (null != diagram_p) {
      // get Diagram
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(diagram_p);
      Object oDiagram = CsServices.getService().getInterpreterVariable(exchange_p, IInterpreterSiriusVariables.DIAGRAM);
      if ((oDiagram != null) && (oDiagram instanceof DDiagram)) {
        diagram = (DDiagram) oDiagram;
      }
      if (diagram != null) {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        for (FilterDescription filterDescription : activatedFilters) {
          // if given filter is enable return true
          if ((null != filterDescription) && filterDescription.getName().equalsIgnoreCase(filterName_p)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isHideFunctionalExchangesNamesEnable(EObject fe_p, DDiagram diagram_p) {
    return isDiagramFilterEnable(fe_p, diagram_p, IMappingNameConstants.HIDE_FUNCTIONAL_EXCHANGES_NAMES);
  }

  public boolean isHideComponentExchangesNamesEnable(EObject ce_p, EObject view_p) {
    return isDiagramFilterEnable(ce_p, view_p, IMappingNameConstants.HIDE_COMPONENT_EXCHANGES_NAMES);
  }

  public boolean isHideCommunicationMeansNamesEnable(EObject ce_p, EObject view_p) {
    return isDiagramFilterEnable(ce_p, view_p, IMappingNameConstants.HIDE_COMMUNICATION_MEANS_NAMES);
  }

  public boolean isHideInteractionsNamesEnable(EObject fe_p, DDiagram diagram_p) {
    return isDiagramFilterEnable(fe_p, diagram_p, IMappingNameConstants.HIDE_INTERACTIONS_NAMES);
  }

  public boolean isHidePhysicalLinksNamesEnable(EObject pl_p, EObject view_p) {
    return isDiagramFilterEnable(pl_p, view_p, IMappingNameConstants.HIDE_PHYSICAL_LINKS_NAMES);
  }

  public String getExchangeCenterLabel(EObject exchange_p, DDiagram diagram_p) {
    // why white space char
    // The manual refresh of the diagram does not take into account the
    // EmptySting
    String centerLabel = Character.toString(ICommonConstants.WHITE_SPACE_CHARACTER);
    if ((null != exchange_p) && (exchange_p instanceof FunctionalExchange)) {
      FunctionalExchange fe = (FunctionalExchange) exchange_p;
      if (!isHideFunctionalExchangesNamesEnable(exchange_p, diagram_p)) {
        return fe.getName();
      }
    }
    return centerLabel;
  }

  public String getPhysicalLinkCenterLabel(EObject exchange_p, DDiagram diagram_p) {
    // why white space char
    // The manual refresh of the diagram does not take into account the
    // EmptySting
    String centerLabel = Character.toString(ICommonConstants.WHITE_SPACE_CHARACTER);
    if ((null != exchange_p) && (exchange_p instanceof PhysicalLink)) {
      PhysicalLink pl = (PhysicalLink) exchange_p;
      if (!isHidePhysicalLinksNamesEnable(exchange_p, diagram_p)) {
        return pl.getName();
      }
    }
    return centerLabel;
  }

  /**
   * Get the Node mapping for Functional Exchange category pin
   * 
   * @param diagram_p
   * @return
   */
  public NodeMapping getMappingFECategoryOutputPin(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
  }

  public NodeMapping getMappingFECategoryInputPin(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
  }

  /**
   * Get the edge mapping for the Functional Exchange category
   * 
   * @param diagram_p
   * @return
   */
  public EdgeMapping getMappingFECategory(DDiagram diagram_p) {
    return getMappingExchangeCategory(diagram_p);
  }

  /**
   * Do a switch Functional Exchange / Category
   * 
   * @param context_p
   * @param scope
   * @param initialSelection
   * @param selectedElements
   * @return
   */
  public EObject switchFECategories(DSemanticDecorator context_p, Collection<EObject> scope, Collection<EObject> initialSelection, Collection<EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context_p);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    return switchFECategories(content, context_p, selectedElements);
  }

  public EObject switchFECategories(DDiagramContents content_p, DSemanticDecorator context_p, Collection<EObject> selectedElements) {

    FaServices.getFaServices().updateFECategories(content_p);

    switchFEInvisibleCategories(content_p, context_p, selectedElements);

    DDiagram currentDiagram = content_p.getDDiagram();
    Collection<DDiagramElement> sourceViews = new HashSet<DDiagramElement>();
    if (context_p instanceof DDiagramElement) {
      sourceViews.add((DDiagramElement) context_p);
    }
    if (sourceViews.isEmpty()) {
      for (DDiagramElement element : content_p.getDiagramElements(FaServices.getFaServices().getMappingABAbstractFunction(null, currentDiagram))) {
        sourceViews.add(element);
      }
    }

    AbstractShowHide categories = new ShowHideExchangeCategory(content_p);
    DiagramContext context = categories.new DiagramContext();
    if (context_p instanceof DDiagramElement) {
      context.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context_p));
    }

    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        HashMapSet<EObject, Map.Entry<EObject, EObject>> scopeSource = getShowHideSubFECategoriesScope(sourceView);
        for (EObject key : scopeSource.keySet()) {
          if (selectedElements.contains(key)) {
            for (Map.Entry<EObject, EObject> srcTarMap : scopeSource.get(key)) {
              showFECategory(categories, context, (ExchangeCategory) key, srcTarMap.getKey(), srcTarMap.getValue(), true);
            }
          } else {
            for (Map.Entry<EObject, EObject> srcTarMap : scopeSource.get(key)) {
              showFECategory(categories, context, (ExchangeCategory) key, getBestFunctionContainer(srcTarMap.getKey(), content_p),
                  getBestFunctionContainer(srcTarMap.getValue(), content_p), false);
            }
          }
        }
      }
    }

    context = categories.new DiagramContext();
    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        HashMapSet<EObject, Map.Entry<EObject, EObject>> scopeSource = getShowHideSubFECategoriesScope(sourceView);
        for (EObject key : scopeSource.keySet()) {
          if (selectedElements.contains(key)) {
            for (FunctionalExchange exchange : FunctionExt.getAllExchanges((AbstractFunction) sourceViewTarget)) {
              if (exchange.getCategories().contains(key)) {
                categories.hide(exchange, context);
              }
            }
          } else {
            for (FunctionalExchange exchange : FunctionExt.getAllExchanges((AbstractFunction) sourceViewTarget)) {
              if (exchange.getCategories().contains(key)) {
                categories.show(exchange, context);
              }
            }
          }
        }
      }
    }

    FaServices.getFaServices().updateFECategories(content_p);

    content_p.commitDeferredActions();
    return context_p;
  }

  /**
   * Do a Exchange/Category switch for hidden categories
   * 
   * @param content_p
   * @param context_p
   * @param selectedElements
   * @return
   */
  public EObject switchFEInvisibleCategories(DDiagramContents content_p, DSemanticDecorator context_p, Collection<EObject> selectedElements) {

    DDiagram currentDiagram = content_p.getDDiagram();
    Collection<DDiagramElement> invisibleCategoryEdges = new HashSet<DDiagramElement>();
    for (DDiagramElement element : content_p.getDiagramElements(FaServices.getFaServices().getMappingFECategory(currentDiagram))) {
      if (!element.isVisible())
        invisibleCategoryEdges.add(element);
    }

    for (DDiagramElement categoryEdge : invisibleCategoryEdges) {
      EObject categoryObj = categoryEdge.getTarget();
      EObject srcFunc = ((DDiagramElement) ((DEdge) categoryEdge).getSourceNode().eContainer()).getTarget();
      EObject tarFunc = ((DDiagramElement) ((DEdge) categoryEdge).getTargetNode().eContainer()).getTarget();

      if (categoryObj != null && categoryObj instanceof ExchangeCategory) {
        AbstractShowHide invCatSwitch = new ShowHideInvisibleExchangeCategory(content_p);
        DiagramContext context = invCatSwitch.new DiagramContext();
        if (selectedElements.contains(categoryObj)) {
          showFECategory(invCatSwitch, context, (ExchangeCategory) categoryObj, srcFunc, tarFunc, true);
        } else
          showFECategory(invCatSwitch, context, (ExchangeCategory) categoryObj, srcFunc, tarFunc, false);
      }
    }

    content_p.commitDeferredActions();
    return context_p;
  }

  /**
   * 
   * @param abstractFunction
   * @param content_p
   * @return The best container for a function, taking into account hidden functions
   */
  public static EObject getBestFunctionContainer(EObject abstractFunction, DDiagramContents content_p) {
    if (abstractFunction != null && abstractFunction instanceof AbstractFunction) {
      if (!content_p.getDiagramElements(abstractFunction).isEmpty()) {
        boolean bVisible = false;
        for (DDiagramElement element : content_p.getDiagramElements(abstractFunction)) {
          if (element.isVisible())
            bVisible = true;
        }
        // If there is at least one representative view for the function, return the function itself
        if (bVisible)
          return abstractFunction;
      }
      // Else, return its best container
      DragAndDropTarget node = content_p.getBestContainer(abstractFunction);
      if (abstractFunction instanceof AbstractFunction) {
        if (node instanceof DDiagram) {
          return null;
        }
      }
      if (node instanceof DSemanticDecorator && (!(node instanceof DDiagram))) {
        return ((DSemanticDecorator) node).getTarget();
      }
      return abstractFunction.eContainer();
    }
    return null;
  }

  /**
   * Refine the state of categories (e.g. invalid category edges are removed, etc.)
   * 
   * @param context_p
   */
  public void updateFECategories(DDiagramContents context_p) {
    Collection<DEdge> toRemoveEdges = new HashSet<DEdge>();
    Collection<AbstractDNode> toRemoveNodes = new HashSet<AbstractDNode>();
    Collection<AbstractDNode> toHideNodes = new HashSet<AbstractDNode>();

    EdgeMapping edgeMapping = getMappingFECategory(context_p.getDDiagram());
    NodeMapping nodeMapping = getMappingFECategoryOutputPin(context_p.getDDiagram());

    // Retrieve all invalid edges to be removed
    if (edgeMapping != null) {
      for (DDiagramElement element : context_p.getDiagramElements(edgeMapping)) {
        if (!(element instanceof DEdge)) {
          continue;
        }
        DEdge edge = (DEdge) element;

        boolean isValidEdge = isValidFECategoryEdge((ExchangeCategory) element.getTarget(), (DSemanticDecorator) edge.getSourceNode(), (DSemanticDecorator) edge.getTargetNode());
        if (!isValidEdge) {
          toRemoveEdges.add(edge);
        }
      }
    }

    // Retrieve all nodes without incoming/outgoing edges to be removed
    if (nodeMapping != null) {
      for (DDiagramElement element : context_p.getDiagramElements(nodeMapping)) {
        if (!(element instanceof EdgeTarget)) {
          continue;
        }
        Collection<DEdge> edges = new ArrayList<DEdge>();
        edges.addAll(((EdgeTarget) element).getIncomingEdges());
        edges.addAll(((EdgeTarget) element).getOutgoingEdges());

        if (edges.size() == 0) {
          toRemoveNodes.add((AbstractDNode) element);
        } else {
          int nbRemoved = 0;
          for (DEdge edge : edges) {
            if (toRemoveEdges.contains(edge)) {
              nbRemoved++;
            }
          }
          if (nbRemoved == edges.size()) {
            toRemoveNodes.add((AbstractDNode) element);
          }
        }
      }
    }

    // Retrieve all nodes to be hidden or removed
    List<NodeMapping> nodeMappings = FaServices.getFaServices().getMappingABPorts(context_p.getDDiagram());
    if (!nodeMappings.isEmpty()) {
      Iterable<DDiagramElement> diagElements = context_p.getDiagramElements(nodeMappings);
      for (DDiagramElement element : diagElements) {

        if (!(element instanceof EdgeTarget)) {
          continue;
        }
        Collection<DEdge> edges = new ArrayList<DEdge>();
        edges.addAll(((EdgeTarget) element).getIncomingEdges());
        edges.addAll(((EdgeTarget) element).getOutgoingEdges());

        if (edges.size() != 0) {
          int nbRemoved = 0;
          int nbHidden = 0;
          for (DEdge edge : edges) {

            if (!context_p.isVisible(edge)) {
              if (edge.getTarget() != null) {
                EObject target = edge.getTarget();
                if ((target instanceof ComponentExchange) && !(((ComponentExchange) target).getCategories().isEmpty())) {
                  nbHidden++;
                } else if ((target instanceof PhysicalLink) && !(((PhysicalLink) target).getCategories().isEmpty())) {
                  nbHidden++;
                }
              }
            } else if (toRemoveEdges.contains(edge)) {
              nbRemoved++;
            }
          }
          if (nbRemoved == edges.size()) {
            toRemoveNodes.add((AbstractDNode) element);
          } else if ((nbHidden + nbRemoved) == edges.size()) {
            toHideNodes.add((AbstractDNode) element);
          }
        }
      }
    }

    for (DEdge edge : toRemoveEdges) {
      DiagramServices.getDiagramServices().removeEdgeView(edge);
    }

    for (AbstractDNode node : toHideNodes) {
      context_p.deferredHide(node);
    }

    for (AbstractDNode node : toRemoveNodes) {
      DiagramServices.getDiagramServices().removeAbstractDNodeView(node);
    }

  }

  /**
   * Retrieve a map<ExchangeCategory, FunctionalExchange> of available category to display from the given source view
   * 
   * @param context_p
   * @return
   */
  public HashMapSet<EObject, EObject> getShowHideFECategoriesScope(DSemanticDecorator context_p) {
    HashMapSet<EObject, EObject> result = new HashMapSet<EObject, EObject>();
    EObject abstractFunction = context_p.getTarget();
    if (abstractFunction != null && abstractFunction instanceof AbstractFunction) {
      for (FunctionalExchange fe : FunctionExt.getOutGoingExchange((AbstractFunction) abstractFunction))
        for (ExchangeCategory value : fe.getCategories()) {
          result.put(value, FunctionExt.getOutGoingAbstractFunction(fe));
        }

      for (FunctionalExchange fe : FunctionExt.getIncomingExchange((AbstractFunction) abstractFunction))
        for (ExchangeCategory value : fe.getCategories()) {
          result.put(value, FunctionExt.getIncomingAbstractFunction(fe));
        }
    }
    return result;
  }

  /**
   * Retrieve a map of available category to display from the given source view (including those of sub-elements of the
   * source view)
   * 
   * @param context_p
   * @return
   */
  public HashMapSet<EObject, Map.Entry<EObject, EObject>> getShowHideSubFECategoriesScope(DSemanticDecorator context_p) {
    HashMapSet<EObject, Map.Entry<EObject, EObject>> result = new HashMapSet<EObject, Map.Entry<EObject, EObject>>();
    EObject abstractFunction = context_p.getTarget();

    if (abstractFunction != null && abstractFunction instanceof AbstractFunction) {
      for (FunctionalExchange fe : FunctionExt.getAllOutgoingExchanges((AbstractFunction) abstractFunction))
        for (ExchangeCategory value : fe.getCategories()) {
          Map.Entry<EObject, EObject> srcTar = new AbstractMap.SimpleEntry<EObject, EObject>(FunctionExt.getIncomingAbstractFunction(fe),
              FunctionExt.getOutGoingAbstractFunction(fe));
          result.put(value, srcTar);
        }

      for (FunctionalExchange fe : FunctionExt.getAllIncomingExchanges((AbstractFunction) abstractFunction))
        for (ExchangeCategory value : fe.getCategories()) {
          Map.Entry<EObject, EObject> srcTar = new AbstractMap.SimpleEntry<EObject, EObject>(FunctionExt.getIncomingAbstractFunction(fe),
              FunctionExt.getOutGoingAbstractFunction(fe));
          result.put(value, srcTar);
        }
    }
    return result;
  }

  public Collection<EObject> getSwitchFECategoriesScope(DSemanticDecorator context_p) {
    if (context_p instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<EObject>();
      DDiagramContents context = new DDiagramContents((DDiagram) context_p);

      EdgeMapping dfFEMapping = getMappingDFFunctionalExchange(context.getDDiagram());
      EdgeMapping abFEMapping = getMappingABFunctionalExchange(context.getDDiagram());

      for (DDiagramElement element : context.getDiagramElements(dfFEMapping)) {
        if ((element.getTarget() != null) && (element.getTarget() instanceof FunctionalExchange)) {
          values.addAll(((FunctionalExchange) element.getTarget()).getCategories());
        }
      }

      for (DDiagramElement element : context.getDiagramElements(abFEMapping)) {
        if ((element.getTarget() != null) && (element.getTarget() instanceof FunctionalExchange)) {
          values.addAll(((FunctionalExchange) element.getTarget()).getCategories());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> scope = getShowHideFECategoriesScope(context_p);
    return scope.keySet();

  }

  /**
   * Retrieve all Functional Exchange for the related Category
   * 
   * @param element_p
   * @return
   */
  public Collection<FunctionalExchange> getRelatedFunctionalExchanges(EObject element_p) {
    if (element_p instanceof ExchangeCategory) {
      return ((ExchangeCategory) element_p).getExchanges();

    }
    return Collections.emptyList();
  }

  public boolean isValidFECategoryEdge(ExchangeCategory category_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    return true;
  }

  /**
   * Show/Hide a FE category from the source to the target
   * 
   * @param categories_p
   * @param context_p
   * @param key_p
   * @param source_p
   * @param target_p
   * @param b_p
   */
  private void showFECategory(AbstractShowHide categories_p, DiagramContext context_p, ExchangeCategory key_p, EObject source_p, EObject target_p, boolean b_p) {

    context_p.setVariable(ShowHideABComponent.SOURCE_PARTS, Collections.singletonList(source_p));
    context_p.setVariable(ShowHideABComponent.TARGET_PARTS, Collections.singletonList(target_p));

    if (b_p) {
      categories_p.show(key_p, context_p);
    } else {
      categories_p.hide(key_p, context_p);
    }
  }

  /**
   * Show all FE categories in the scope
   * 
   * @param context_p
   * @param scope
   * @param initialSelection
   * @param selectedElements
   * @return
   */
  public EObject showFECategories(DSemanticDecorator context_p, HashMapSet<EObject, EObject> scope, HashMapSet<EObject, EObject> initialSelection,
      final HashMapSet<EObject, EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context_p);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    EObject source = context_p.getTarget();

    AbstractShowHide shService = new ShowHideExchangeCategory(content);
    DiagramContext context = shService.new DiagramContext();
    if (context_p instanceof DDiagramElement) {
      context.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context_p));
    }

    for (EObject key : scope.keySet()) {
      for (EObject value : scope.get(key)) {
        // If the category is not in the the list of selected
        // categories, hide it
        if (!selectedElements.containsKey(key) || !selectedElements.get(key).contains(value)) {
          if (initialSelection.containsKey(key) && initialSelection.get(key).contains(value)) {
            showFECategory(shService, context, (ExchangeCategory) key, source, value, false);
            showFECategory(shService, context, (ExchangeCategory) key, value, source, false);
          }
        }
      }
    }

    for (EObject key : selectedElements.keySet()) {
      for (EObject target : selectedElements.get(key)) {
        showFECategory(shService, context, (ExchangeCategory) key, source, target, true);
        showFECategory(shService, context, (ExchangeCategory) key, target, source, true);
      }

    }

    content.commitDeferredActions();

    return context_p;
  }

  /**
   * Retrieve the initial selection of displayed category for the given source view
   * 
   * @param context_p
   * @return
   */
  public Collection<EObject> getSwitchFECategoriesInitialSelection(DSemanticDecorator context_p) {
    if (context_p instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<EObject>();
      DDiagramContents context = new DDiagramContents((DDiagram) context_p);
      for (DDiagramElement element : context.getDiagramElements(getMappingExchangeCategory(context.getDDiagram()))) {
        if ((element.getTarget() != null) && (element.getTarget() instanceof CapellaElement)) {
          values.add(element.getTarget());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> result = getShowHideFECategoriesInitialSelection(context_p);
    return result.keySet();
  }

  /**
   * Retrieve the initial selection of displayed category for the given source view
   * 
   * @param context_p
   * @return
   */
  public HashMapSet<EObject, EObject> getShowHideFECategoriesInitialSelection(DSemanticDecorator context_p) {
    HashMapSet<EObject, EObject> scope = getShowHideFECategoriesScope(context_p);
    HashMapSet<EObject, EObject> result = new HashMapSet<EObject, EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(context_p);
    EdgeMapping edgeMapping = getMappingExchangeCategory(diagram);
    DDiagramContents context = new DDiagramContents(diagram);

    DSemanticDecorator sourcePartView = CsServices.getService().getRelatedPartView(context_p);

    for (EObject key : scope.keySet()) {
      for (EObject targetPart : scope.get(key)) {
        for (DDiagramElement elementView : context.getDiagramElements(key, edgeMapping)) {
          if (elementView instanceof DEdge) {
            DEdge ve = (DEdge) elementView;

            result.put(key, targetPart);

          }
        }
      }
    }
    return result;
  }

}
