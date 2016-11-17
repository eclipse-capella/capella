/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.graphics.RGB;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.SimpleOrientedGraph;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.deployment.DeploymentFactory;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 */
public class PhysicalServices {

  private static PhysicalServices __service = null;

  public static PhysicalServices getService() {
    if (__service == null) {
      __service = new PhysicalServices();
    }
    return __service;
  }

  private static final Integer THICK_EDGE_PHYSICAL_PATH = new Integer(4);
  private static final String INCOMPLETE_PHYSICAL_PATH_LABEL = "incomplete"; //$NON-NLS-1$
  private static final String INVALID_PHYSICAL_PATH_LABEL = "invalid"; //$NON-NLS-1$

  public boolean isMultipleDeploymentAllowed() {
    return CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed();
  }

  /**
   * Returns all semantics of source views for the given path
   */
  public Collection<EObject> getPhysicalPathSources(PhysicalPath path_p) {
    // a link is not oriented, so return both bounds
    HashSet<EObject> result = new HashSet<EObject>();

    for (PhysicalPathInvolvement anInvolvement : PhysicalPathExt.getFlatInvolvementsOf(path_p, CsPackage.Literals.PHYSICAL_LINK)) {
      PhysicalLink currentExchange = (PhysicalLink) anInvolvement.getInvolved();
      if ((currentExchange != null) && !currentExchange.eIsProxy()) {
        EObject source = PhysicalLinkExt.getSourcePort(currentExchange);
        if (result != null) {
          result.add(source);
        }
        EObject target = PhysicalLinkExt.getTargetPort(currentExchange);
        if (result != null) {
          result.add(target);
        }
      }
    }

    return result;
  }

  /**
   * Returns all semantics of target views for the given path
   */
  public Collection<EObject> getPhysicalPathTargets(PhysicalPath path_p) {
    // a link is not oriented, so return both bounds
    return getPhysicalPathSources(path_p);
  }

  /**
   * @param context_p
   * @return
   */
  public List<CapellaElement> getAvailableComponentsToDeploy(PhysicalComponent context_p) {
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(PaPackage.Literals.PHYSICAL_COMPONENT,
            CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
    return query.getAvailableElements(context_p);
  }

  public List<CapellaElement> getAvailableComponentsToDeploy(Part context_p) {
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(CsPackage.Literals.PART, CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
    return query.getAvailableElements(context_p);
  }

  public List<CapellaElement> getDeployedComponents(PhysicalComponent context_p) {
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(PaPackage.Literals.PHYSICAL_COMPONENT,
            CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
    return query.getCurrentElements(context_p, false);
  }

  public List<CapellaElement> getDeployedComponents(Part context_p) {
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(CsPackage.Literals.PART, CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
    return query.getCurrentElements(context_p, false);
  }

  public List<Part> getAvailableComponentsToDeploy(Part part_p, boolean behaviour_p) {
    List<Part> returnedList = new ArrayList<Part>();
    for (CapellaElement anElement : getAvailableComponentsToDeploy(part_p)) {
      if (anElement instanceof Part) {
        Part aPart = (Part) anElement;
        if ((behaviour_p && isBehaviour(aPart)) || (!behaviour_p && isNode(aPart))) {
          returnedList.add(aPart);
        }
      }
    }
    return returnedList;
  }

  public List<Part> getDeployedComponents(Part part_p, boolean behaviour_p) {
    List<Part> returnedList = new ArrayList<Part>();
    for (CapellaElement anElement : getDeployedComponents(part_p)) {
      if (anElement instanceof Part) {
        Part aPart = (Part) anElement;
        if ((behaviour_p && isBehaviour(aPart)) || (!behaviour_p && isNode(aPart))) {
          returnedList.add(aPart);
        }
      }
    }
    return returnedList;
  }

  public boolean isValidPPDInvolveComponent(DSemanticDecorator context_p) {
    return context_p instanceof DDiagram;
  }

  public boolean isValidPPDInvolvePart(DSemanticDecorator context_p) {
    return context_p instanceof DDiagram;
  }

  public boolean isValidPPDInvolvePhysicalPath(DSemanticDecorator context_p) {
    return context_p instanceof DDiagram;
  }

  public boolean isValidPPDInvolvePhysicalLink(EObject context_p, PhysicalPathInvolvement source_p, PhysicalPathInvolvement target_p) {
    return isAnEdgeInvolvementAvailableInPPD(context_p, source_p, target_p);
  }

  public boolean isValidPPDInvolvePhysicalLinkAndComponent(DSemanticDecorator context_p) {
    return true;
  }

  public boolean isValidPPDInvolvePhysicalLinkAndPhysicalPath(DSemanticDecorator context_p) {
    return context_p instanceof AbstractDNode;
  }

  public boolean isValidPPDInvolvePhysicalLinkAndPart(DSemanticDecorator context_p) {
    return true;
  }

  public Collection<PhysicalPath> getPPDInvolvePhysicalPathScope(DSemanticDecorator diagram_p) {
    EObject chain = diagram_p.getTarget();
    if (!(chain instanceof PhysicalPath)) {
      return Collections.emptyList();
    }

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(chain);

    // remove all chains owning the current chain
    List<PhysicalPath> chains = PhysicalPathExt.getAllPhysicalPaths(architecture);
    List<PhysicalPath> result = new LinkedList<PhysicalPath>();

    for (PhysicalPath definedChain : chains) {
      boolean toAdd = true;
      if (definedChain.equals(chain)) {
        toAdd = false;
      }
      if (toAdd) {
        for (PhysicalPathInvolvement involvement : PhysicalPathExt.getFlatInvolvementsOf(definedChain, CsPackage.Literals.PHYSICAL_PATH)) {
          if (chain.equals(involvement.getInvolved())) {
            toAdd = false;
            break;
          }
        }
      }
      if (toAdd) {
        result.add(definedChain);
      }
    }
    return result;

  }

  public Collection<Part> getPPDInvolvePartScope(DSemanticDecorator context_p) {
    EObject target = context_p.getTarget();
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
    Collection<Part> parts = new HashSet<Part>();
    for (Part part : PartExt.getAllPartsFromPhysicalArchitecture((PhysicalArchitecture) architecture)) {
      if ((part.getAbstractType() != null) && (part.getAbstractType() instanceof AbstractPhysicalComponent)) {
        if (PhysicalComponentNature.NODE.equals(((AbstractPhysicalComponent) part.getAbstractType()).getNature())) {
          parts.add(part);
        }
      }
    }
    return parts;
  }

  public Collection<Component> getPPDInvolveComponentScope(DSemanticDecorator context_p) {
    EObject target = context_p.getTarget();
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
    Collection<Component> parts = new HashSet<Component>();
    for (Part part : PartExt.getAllPartsFromBlockArch(architecture)) {
      Component component = (Component) part.getAbstractType();
      if ((component != null)) {
        if (!PhysicalComponentExt.isPhysicalComponentRoot(component)) {
          parts.add(component);
        }

      }
    }
    return parts;
  }

  /**
   * @param selectedElement_p
   * @return called by show/hide PhysicalLinks tool (Physical Architecture Blank Diagram)
   */
  public List<PhysicalLink> getDisplayedPhysicalLinks(DNodeContainer selectedElement_p) {
    List<PhysicalLink> returnedList = new ArrayList<PhysicalLink>();
    List<DEdge> incomingOutgoingEdges = new ArrayList<DEdge>();

    for (DNode aNode : selectedElement_p.getOwnedBorderedNodes()) {
      if (aNode.getTarget() instanceof PhysicalPort) {
        incomingOutgoingEdges.addAll(CapellaServices.getService().getIncomingEdges(aNode));
        incomingOutgoingEdges.addAll(CapellaServices.getService().getOutgoingEdges(aNode));
      }
    }
    for (DEdge anEdge : incomingOutgoingEdges) {
      if (anEdge.getTarget() instanceof PhysicalLink) {
        returnedList.add((PhysicalLink) anEdge.getTarget());
      }
    }
    return returnedList;
  }

  /**
   * @param context_p
   * @return called by show/hide PhysicalLinks tool (Physical Architecture Blank Diagram)
   */
  public List<PhysicalLink> getAvailablePhysicalLinksToInsert(DNodeContainer context_p, DDiagram diagram_p) {
    List<PhysicalLink> returnedList = new ArrayList<PhysicalLink>();
    List<PhysicalLink> allPhysicalLinks = new ArrayList<PhysicalLink>();
    List<PhysicalLink> existingExchangesInDiagram = new ArrayList<PhysicalLink>();

    for (DEdge anEdge : CapellaServices.getService().getDiagramContainer(context_p).getEdges()) {
      if (anEdge.getTarget() instanceof PhysicalLink) {
        existingExchangesInDiagram.add((PhysicalLink) anEdge.getTarget());
      }
    }

    Component currentComponent = null;
    EObject target = context_p.getTarget();
    if ((null != target) && (target instanceof Component)) {
      currentComponent = (Component) CsServices.getService().getComponentType((Component) target);
    } else if ((null != target) && (target instanceof Part)) {
      currentComponent = (Component) CsServices.getService().getComponentType((Part) target);
    }

    for (PhysicalPort aPort : ComponentExt.getOwnedPhysicalPort(currentComponent)) {
      allPhysicalLinks.addAll(aPort.getInvolvedLinks());
    }
    for (PhysicalLink aPhysicalLink : allPhysicalLinks) {
      if (!existingExchangesInDiagram.contains(aPhysicalLink)) {
        returnedList.add(aPhysicalLink);
      }
    }
    return returnedList;
  }

  /**
   * @param context_p
   * @return called by show/hide PhysicalLinks tool (Physical Architecture Blank Diagram)
   */
  public List<PhysicalLink> getAvailablePhysicalLinksToInsertOld(DNodeContainer context_p) {
    List<PhysicalLink> returnedList = new ArrayList<PhysicalLink>();
    List<PhysicalLink> allPhysicalLinks = new ArrayList<PhysicalLink>();
    List<PhysicalLink> existingExchangesInDiagram = new ArrayList<PhysicalLink>();

    for (DEdge anEdge : CapellaServices.getService().getDiagramContainer(context_p).getEdges()) {
      if (anEdge.getTarget() instanceof PhysicalLink) {
        existingExchangesInDiagram.add((PhysicalLink) anEdge.getTarget());
      }
    }

    Component currentComponent = null;
    EObject target = context_p.getTarget();
    if ((null != target) && (target instanceof Component)) {
      currentComponent = (Component) CsServices.getService().getComponentType((Component) target);
    } else if ((null != target) && (target instanceof Part)) {
      currentComponent = (Component) CsServices.getService().getComponentType((Part) target);
    }

    for (PhysicalPort aPort : ComponentExt.getOwnedPhysicalPort(currentComponent)) {
      allPhysicalLinks.addAll(aPort.getInvolvedLinks());
    }
    for (PhysicalLink aPhysicalLink : allPhysicalLinks) {
      if (!existingExchangesInDiagram.contains(aPhysicalLink)) {
        returnedList.add(aPhysicalLink);
      }
    }
    return returnedList;
  }

  public List<PhysicalPath> getAllPhysicalPaths(EObject context) {
    return PhysicalPathExt.getAllPhysicalPaths(BlockArchitectureExt.getRootBlockArchitecture(context));
  }

  /**
   * Create a component exchange in an architecture blank diagram. Create port if selected views are not targeting port
   * @param context_p
   * @param sourceViewk
   * @param targetView_p
   * @return
   */
  public EObject createABPhysicalLink(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
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

    EdgeTarget nodeSource = null;
    EdgeTarget nodeTarget = null;

    // Create or retrieve sourcePort
    PhysicalPort sourcePort = null;
    if (sourceTarget instanceof PhysicalPort) {
      sourcePort = (PhysicalPort) sourceTarget;
      nodeSource = (EdgeTarget) sourceView_p;

    } else {
      sourcePort = CsFactory.eINSTANCE.createPhysicalPort();
      ((Component) sourcePart.getType()).getOwnedFeatures().add(sourcePort);
      CapellaServices.getService().creationService(sourcePort);
      nodeSource = CsServices.getService().createViewOrGetPhysicalPort((DNodeContainer) sourceView_p, sourcePort).getKey();
    }

    // Create or retrieve targetPort
    PhysicalPort targetPort = null;
    if (targetTarget instanceof PhysicalPort) {
      targetPort = (PhysicalPort) targetTarget;
      nodeTarget = (EdgeTarget) targetView_p;

    } else {
      targetPort = CsFactory.eINSTANCE.createPhysicalPort();
      ((Component) targetPart.getType()).getOwnedFeatures().add(targetPort);
      CapellaServices.getService().creationService(targetPort);
      nodeTarget = CsServices.getService().createViewOrGetPhysicalPort((DNodeContainer) targetView_p, targetPort).getKey();
    }

    // Create physical link
    PhysicalLink exchange = CsFactory.eINSTANCE.createPhysicalLink();

    // Set source
    if (CsServices.getService().isMultipartMode((ModelElement) sourceTarget)) {
      PhysicalLinkEnd end = CsFactory.eINSTANCE.createPhysicalLinkEnd();
      end.setPart(sourcePart);
      end.setPort(sourcePort);
      exchange.getLinkEnds().add(end);
      exchange.getOwnedPhysicalLinkEnds().add(end);
      CapellaServices.getService().creationService(end);
    } else {
      exchange.getLinkEnds().add(sourcePort);
    }

    // Set target
    if (CsServices.getService().isMultipartMode((ModelElement) sourceTarget)) {
      PhysicalLinkEnd end = CsFactory.eINSTANCE.createPhysicalLinkEnd();
      end.setPart(targetPart);
      end.setPort(targetPort);
      exchange.getLinkEnds().add(end);
      exchange.getOwnedPhysicalLinkEnds().add(end);
      CapellaServices.getService().creationService(end);
    } else {
      exchange.getLinkEnds().add(targetPort);
    }

    // Attach to parent
    org.polarsys.capella.core.model.helpers.PhysicalLinkExt.attachToDefaultContainer(exchange);

    CapellaServices.getService().creationService(exchange);
    DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingABPhysicalLink(diagram), nodeSource, nodeTarget, exchange);
    return context_p;
  }

  public EObject createPhysicalPath(EObject context_p, List<EObject> views_p, EObject source_p) {

    if (!views_p.isEmpty()) {
      List<PhysicalLink> newList = new ArrayList<PhysicalLink>();
      for (EObject aSelectedElement : views_p) {
        if ((aSelectedElement instanceof DEdge) && (((DEdge) aSelectedElement).getTarget() != null)
            && (((DEdge) aSelectedElement).getTarget() instanceof PhysicalLink)) {
          newList.add((PhysicalLink) ((DEdge) aSelectedElement).getTarget());
        }
      }
      Part sourcePath;
      if (canBeInvolvedInPhysicalPath(source_p)) {
        sourcePath = (Part) ((PartitionableElement) source_p).getRepresentingPartitions().get(0);
      } else {
        sourcePath = (Part) source_p;
      }
      EObject target = ((DSemanticDiagram) CapellaServices.getService().getDiagramContainer(views_p.get(0))).getTarget();
      Component container = (Component) target;
      return PhysicalPathExt.createPhysicalPath(container, newList, sourcePath);

    }
    return context_p;
  }

  public boolean canBeInvolvedInPhysicalPath(EObject source_p) {
    return canHavePhysicalPort(source_p);
  }

  public boolean canHavePhysicalPort(EObject source_p) {
    return ((source_p instanceof AbstractPhysicalComponent) || (source_p instanceof AbstractActor) || (source_p instanceof System) || ((source_p instanceof Component) && isLogicalSystemComponent(source_p)));
  }

  private boolean isLogicalSystemComponent(EObject source_p) {
    return (null != source_p.eContainer()) && (source_p.eContainer() instanceof LogicalArchitecture);
  }

  public List<EObject> getAvailableSourcesOfPhysicalPath(EObject context_p, List<EObject> views_p) {
    HashMap<Part, Integer> parts = new HashMap<Part, Integer>();
    List<EObject> result = new ArrayList<EObject>();
    for (EObject aSelectedElement : views_p) {
      if ((aSelectedElement instanceof DEdge) && (((DEdge) aSelectedElement).getTarget() != null)
          && (((DEdge) aSelectedElement).getTarget() instanceof PhysicalLink)) {
        PhysicalLink currentLink = (PhysicalLink) ((DEdge) aSelectedElement).getTarget();
        Part sourcePart = PhysicalLinkExt.getSourcePart(currentLink);
        Part targetPart = PhysicalLinkExt.getTargetPart(currentLink);
        int i = 1;
        if (parts.containsKey(sourcePart)) {
          i = parts.get(sourcePart).intValue() + 1;
        }
        parts.put(sourcePart, Integer.valueOf(i));
        i = 1;
        if (parts.containsKey(targetPart)) {
          i = parts.get(targetPart).intValue() + 1;
        }
        parts.put(targetPart, Integer.valueOf(i));
      }
    }
    for (Entry<Part, Integer> me : parts.entrySet()) {
      if (me.getValue().intValue() == 1) {
        if (CsServices.getService().isMultipartMode(me.getKey())) {
          result.add(me.getKey());
        } else {
          result.add(me.getKey().getType());
        }
      }
    }
    return result;
  }

  public boolean isValidPhysicalPort(EObject context_p, EObject container_p) {
    if (container_p instanceof Part) {
      return canHavePhysicalPort(((Part) container_p).getAbstractType());
    }
    return false;
  }

  public boolean canBeInvolvedInPhysicalLink(EObject context_p) {
    if (context_p instanceof Part) {
      return canHavePhysicalPort(((Part) context_p).getAbstractType());

    }
    if (context_p instanceof PhysicalPort) {
      return true;
    }
    return false;
  }

  public boolean isValidPhysicalPathSelection(EObject context_p, List<EObject> views_p) {
    SimpleOrientedGraph<Part> graph = new SimpleOrientedGraph<Part>();
    if (!views_p.isEmpty()) {
      for (EObject aSelectedElement : views_p) {
        if ((aSelectedElement instanceof DEdge) && (((DEdge) aSelectedElement).getTarget() != null)
            && (((DEdge) aSelectedElement).getTarget() instanceof PhysicalLink)) {
          PhysicalLink aSelectedExchange = (PhysicalLink) ((DEdge) aSelectedElement).getTarget();
          Part sourcePart = PhysicalLinkExt.getSourcePart(aSelectedExchange);
          Part targetPart = PhysicalLinkExt.getTargetPart(aSelectedExchange);
          graph.addNode(sourcePart, targetPart);
        }
      }
      if (!graph.isEmpty()) {
        for (Part aPart : graph.getGraph().keySet()) {
          if (graph.getNotOrientedNeighbours(aPart).size() > 2) {
            // the selection can not contain alternative
            return false;
          }
        }
        return graph.isValid();
      }
      return false;
    }
    return false;
  }

  public EObject showHidePhysicalPaths(EObject context_p, List<PhysicalPath> selectedPaths_p, DDiagram diagram_p) {
    Map<PhysicalPath, DNode> displayedPaths = new HashMap<PhysicalPath, DNode>();
    for (DNode aNode : diagram_p.getNodes()) {
      if ((aNode.getTarget() != null) && (aNode.getTarget() instanceof PhysicalPath)) {
        displayedPaths.put((PhysicalPath) aNode.getTarget(), aNode);
      }
    }
    
    NodeMapping physicalPathMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram_p, MappingConstantsHelper.getMappingPhysicalPath(diagram_p));
    for (PhysicalPath aPath : selectedPaths_p) {
      if (!displayedPaths.containsKey(aPath)) {
        DiagramServices.getDiagramServices().createNode(physicalPathMapping, aPath, diagram_p, diagram_p);
      }
    }
    for (Entry<PhysicalPath, DNode> me : displayedPaths.entrySet()) {
      if (!selectedPaths_p.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeNodeView(me.getValue());
      }
    }
    return context_p;
  }

  public List<PhysicalPath> getAvailablePhysicalPathsToInsert(EObject element_p, DSemanticDiagram diagram_p) {
    Set<EObject> viewTargets = new HashSet<EObject>();
    for (DEdge anEdge : diagram_p.getEdges()) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalLink)) {
        viewTargets.add(anEdge.getTarget());
      }
    }

    List<PhysicalPath> result = new ArrayList<PhysicalPath>();
    for (PhysicalPath aPath : getAllPhysicalPaths(diagram_p.getTarget())) {
      Collection<PhysicalLink> involvedLinks = PhysicalPathExt.getFlatPhysicalLinks(aPath);
      if (!result.contains(aPath)) {
        for (EObject link : involvedLinks) {
          if (viewTargets.contains(link)) {
            result.add(aPath);
            break;
          }
        }
      }
    }
    return result;
  }

  public PhysicalPort getRelatedPort(AbstractPhysicalLinkEnd end) {
    return PhysicalLinkExt.getRelatedPort(end);
  }

  public List<PhysicalPath> getDisplayedPhysicalPaths(DDiagram diagram_p) {
    List<PhysicalPath> returnedList = new ArrayList<PhysicalPath>();
    for (DNode aNode : diagram_p.getNodes()) {
      if ((aNode.getTarget() != null) && (aNode.getTarget() instanceof PhysicalPath)) {
        returnedList.add((PhysicalPath) aNode.getTarget());
      }
    }
    return returnedList;
  }

  public EObject reconnectPhysicalLink(PhysicalLink link_p, AbstractPhysicalLinkEnd source_p, AbstractPhysicalLinkEnd target_p) {
    int index = link_p.getLinkEnds().indexOf(source_p);
    link_p.getLinkEnds().add(index, target_p);
    link_p.getLinkEnds().remove(source_p);
    return link_p;
  }

  /**
   * Manage allocation of PC deployment
   * @param view_p
   * @param selectedElements_p
   * @param isBehaviour_p
   * @return
   */
  public EObject manageComponentDeployments(DNodeContainer view_p, List<DeployableElement> selectedElements_p, boolean isBehaviour_p) {
    Part currentPart = (Part) view_p.getTarget();
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(view_p);
    Map<Part, DNodeContainer> displayedDeployments = new HashMap<Part, DNodeContainer>();
    ContainerMapping deploymentMapping = getDeploymentMapping(currentDiagram);

    for (DDiagramElement aDiagramElement : view_p.getOwnedDiagramElements()) {
      if (aDiagramElement.getMapping().equals(deploymentMapping)) {
        displayedDeployments.put((Part) aDiagramElement.getTarget(), (DNodeContainer) aDiagramElement);
      }
    }
    List<AbstractDeploymentLink> linksToRemove = new ArrayList<AbstractDeploymentLink>(1);
    for (AbstractDeploymentLink aLink : currentPart.getOwnedDeploymentLinks()) {
      if (!selectedElements_p.contains(aLink.getDeployedElement())) {
        if (aLink.getDeployedElement() instanceof Part) {
          Part aDeployedPart = (Part) aLink.getDeployedElement();
          if ((isBehaviour_p && isBehaviour(aDeployedPart)) || (!isBehaviour_p && isNode(aDeployedPart))) {
            if (displayedDeployments.containsKey(aDeployedPart)) {
              // remove the view
              DiagramServices.getDiagramServices().removeContainerView(displayedDeployments.get(aDeployedPart));
            }
            // collect the deployed links to remove
            linksToRemove.add(aLink);
          }
        }
      }
    }
    
    CapellaServices.getService().removeElements(linksToRemove);

    // create the view
    List<Part> deployedComponents = getDeployedComponents(currentPart, isBehaviour_p);
    for (DeployableElement aSelectedElement : selectedElements_p) {
      if (!deployedComponents.contains(aSelectedElement)) {
        PartDeploymentLink newLink = DeploymentFactory.eINSTANCE.createPartDeploymentLink();
        currentPart.getOwnedDeploymentLinks().add(newLink);
        newLink.setLocation(currentPart);
        newLink.setDeployedElement(aSelectedElement);
        DiagramServices.getDiagramServices().createContainer(deploymentMapping, aSelectedElement, view_p, currentDiagram);
      }
    }
    return view_p;
  }

  public ContainerMapping getDeploymentMapping(DDiagram diagram_p) {
    return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, MappingConstantsHelper.getMappingABDeployedElement(diagram_p));
  }

  public boolean isBehaviour(Part part_p) {
    EObject componentType = CsServices.getService().getComponentType(part_p);
    PhysicalComponent comp = null;
    if (componentType instanceof PhysicalComponent) {
      comp = (PhysicalComponent) componentType;
      return PhysicalComponentNature.BEHAVIOR.equals(comp.getNature());
    }
    return false;
  }

  public boolean isNode(Part part_p) {
    EObject componentType = CsServices.getService().getComponentType(part_p);
    PhysicalComponent comp = null;
    if (componentType instanceof PhysicalComponent) {
      comp = (PhysicalComponent) componentType;
      return PhysicalComponentNature.NODE.equals(comp.getNature());
    }
    return false;
  }

  public boolean isPhysicalActor(Part part_p) {
    EObject componentType = CsServices.getService().getComponentType(part_p);
    if (componentType instanceof PhysicalActor) {
      return true;
    }
    return false;
  }

  public void updatePhysicalPathStyles(DDiagram diagram_p) {
    HashMap<PhysicalPath, DNode> displayedPaths = new HashMap<PhysicalPath, DNode>(); // displayed physical paths
    HashMap<PhysicalLink, DEdge> displayedPhysicalLinks = new HashMap<PhysicalLink, DEdge>(); // displayed physical links
    HashMap<PhysicalPath, Set<DEdge>> displayedIL = new HashMap<PhysicalPath, Set<DEdge>>(); // displayed Internal Links
    HashMap<DEdge, Set<PhysicalPath>> coloredLinks = new HashMap<DEdge, Set<PhysicalPath>>(); // colored physical Links
    Set<DEdge> updatedInternalLinks = new HashSet<DEdge>();
    boolean hasResetNode = false;

    // find displayed physical paths
    for (DNode aNode : diagram_p.getNodes()) {
      if ((aNode.getTarget() != null) && (aNode.getTarget() instanceof PhysicalPath)) {
        displayedPaths.put((PhysicalPath) aNode.getTarget(), aNode);
      }
    }

    // find displayed Functional Exchanges and Internal Links
    for (DEdge anEdge : diagram_p.getEdges()) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalLink)) {
        displayedPhysicalLinks.put((PhysicalLink) anEdge.getTarget(), anEdge);
      }
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalPath)) {
        if (!displayedIL.containsKey(anEdge.getTarget())) {
          Set<DEdge> newSet = new HashSet<DEdge>();
          newSet.add(anEdge);
          displayedIL.put((PhysicalPath) anEdge.getTarget(), newSet);
        } else {
          displayedIL.get(anEdge.getTarget()).add(anEdge);
        }
      }
    }

    // find physical links that must be colored
    for (Entry<PhysicalPath, DNode> me : displayedPaths.entrySet()) {
      for (PhysicalLink link : PhysicalPathExt.getFlatPhysicalLinks(me.getKey())) {
        if (displayedPhysicalLinks.containsKey(link)) {
          DEdge linkEdge = displayedPhysicalLinks.get(link);
          if (!coloredLinks.containsKey(linkEdge)) {
            Set<PhysicalPath> newSet = new HashSet<PhysicalPath>();
            newSet.add(me.getKey());
            coloredLinks.put(linkEdge, newSet);
          } else {
            coloredLinks.get(linkEdge).add(me.getKey());
          }
        }
      }
    }

    // remove internal Links if the physical path is not displayed
    for (Entry<PhysicalPath, Set<DEdge>> me : displayedIL.entrySet()) {
      if (!displayedPaths.containsKey(me.getKey())) {
        for (DEdge anEdge : me.getValue()) {
          diagram_p.getOwnedDiagramElements().remove(anEdge);
          hasResetNode = true;
        }
      }
    }

    for (Entry<PhysicalPath, DNode> me : displayedPaths.entrySet()) {

      updatePhysicalPathNodeColor(me.getValue(), displayedPaths.values());
      RGBValues color = ShapeUtil.getNodeColorStyle(me.getValue());

      // customize internal links
      Set<DEdge> internalLinks = new HashSet<DEdge>();
      internalLinks = updatePhysicalPathInternalLinks(me.getKey(), displayedPhysicalLinks, displayedIL, color);
      updatedInternalLinks.addAll(internalLinks);

      // customize physical paths
      for (PhysicalLink link : PhysicalPathExt.getFlatPhysicalLinks(me.getKey())) {
        if (displayedPhysicalLinks.containsKey(link)) {
          DEdge currentEdge = displayedPhysicalLinks.get(link);
          if (coloredLinks.get(currentEdge).size() == 1) {
            customizePhysicalLinkEdgeStyle(currentEdge, color);
          } else {
            customizePhysicalLinkEdgeStyle(currentEdge, ShapeUtil.getBlackColor());
          }
        }
      }
      me.getValue().refresh();
    }

    // destroy old internal links
    for (Set<DEdge> anInternalLinkSet : displayedIL.values()) {
      for (DEdge anInternalLink : anInternalLinkSet) {
        if (!updatedInternalLinks.contains(anInternalLink)) {
          DiagramServices.getDiagramServices().removeEdgeView(anInternalLink);
          hasResetNode = true;
        }
      }
    }

    // reset physical links with no physical path
    if ((displayedPaths.size() == 0) || hasResetNode) {
      for (DEdge aFE : displayedPhysicalLinks.values()) {
        if (!coloredLinks.containsKey(aFE)) {
          resetPhysicalLinkStyle(aFE);
        }
      }
    }

  }

  /**
   * @param currentSourceNode_p
   * @return
   */
  private boolean isValidNodeForInternalLink(EdgeTarget currentNode_p) {
    if (null == currentNode_p) {
      return false;
    }
    if (!(currentNode_p instanceof DNode)) {
      return false;
    }
    if (!DiagramServices.getDiagramServices().isABorderedNode((DNode) currentNode_p)) {
      return false;
    }
    return true;
  }

  /**
   * @param fc_p
   * @return
   */
  protected Collection<PhysicalLink> getFlatPreviousPhysicalLinks(PhysicalPathInvolvement fci_p) {
    Collection<PhysicalLink> result = new HashSet<PhysicalLink>();

    for (PhysicalPathInvolvement involvment : PhysicalPathExt.getFlatPreviousLinkInvolvements(fci_p)) {
      if ((involvment.getInvolved() != null) && (involvment.getInvolved() instanceof PhysicalLink)) {
        result.add((PhysicalLink) involvment.getInvolved());
      }
    }

    return result;
  }

  /**
   * @param fc_p
   * @return
   */
  protected Collection<PhysicalLink> getFlatNextPhysicalLinks(PhysicalPathInvolvement fci_p) {
    Collection<PhysicalLink> result = new HashSet<PhysicalLink>();

    for (PhysicalPathInvolvement involvment : PhysicalPathExt.getFlatNextExchangeInvolvements(fci_p)) {
      if ((involvment.getInvolved() != null) && (involvment.getInvolved() instanceof PhysicalLink)) {
        result.add((PhysicalLink) involvment.getInvolved());
      }
    }

    return result;
  }

  protected Set<DEdge> updatePhysicalPathInternalLinks(PhysicalPath path_p, HashMap<PhysicalLink, DEdge> displayedPhysicalLinks_p,
      HashMap<PhysicalPath, Set<DEdge>> displayedIL_p, RGBValues color_p) {
    Set<DEdge> internalLinks = new HashSet<DEdge>();

    HashMapSet<AbstractDNode, DNode> mapsPorts = new HashMapSet<AbstractDNode, DNode>() {

      /**
       * {@inheritDoc}
       */
      @Override
      protected Collection<DNode> createInternalSet() {
        return new TreeSet<DNode>(new Comparator<DNode>() {
          public int compare(DNode o1_p, DNode o2_p) {
            if ((o1_p == null) && (o2_p == null)) {
              return 0;

            } else if ((o1_p == null)) {
              return 1;

            } else if ((o2_p == null)) {
              return -1;
            }

            if ((o1_p.getTarget() == null) && (o2_p.getTarget() == null)) {
              return 0;

            } else if (((o1_p.getTarget() == null) || !(o1_p.getTarget() instanceof AbstractNamedElement))) {
              return 2;

            } else if (((o2_p.getTarget() == null) || !(o2_p.getTarget() instanceof AbstractNamedElement))) {
              return -2;
            }

            String name1 = ((AbstractNamedElement) o1_p.getTarget()).getName();
            String name2 = ((AbstractNamedElement) o2_p.getTarget()).getName();

            int cmp = name1.compareTo(name2);
            if (cmp == 0) {
              name1 = EcoreUtil.getID(o1_p.getTarget());
              name2 = EcoreUtil.getID(o2_p.getTarget());
              cmp = name1.compareTo(name2);
            }
            return cmp;
          }
        });
      }

    };

    // iterate over involved physical links. retrieve for each NodeContainer all ports related to the path
    for (PhysicalPathInvolvement anInvolvement : PhysicalPathExt.getFlatInvolvementsOf(path_p, CsPackage.Literals.PHYSICAL_LINK)) {
      PhysicalLink currentExchange = (PhysicalLink) anInvolvement.getInvolved();
      if (!displayedPhysicalLinks_p.containsKey(currentExchange)) {
        continue;
      }

      DEdge currentEdge = displayedPhysicalLinks_p.get(currentExchange);
      if ((currentEdge == null) || !isValidNodeForInternalLink(currentEdge.getSourceNode()) || !isValidNodeForInternalLink(currentEdge.getTargetNode())) {
        continue;
      }

      DNode currentSourceNode = (DNode) currentEdge.getSourceNode();
      DNode currentTargetNode = (DNode) currentEdge.getTargetNode();

      mapsPorts.put((AbstractDNode) currentSourceNode.eContainer(), currentSourceNode);
      mapsPorts.put((AbstractDNode) currentTargetNode.eContainer(), currentTargetNode);
    }

    // For each container, for all couple<port-port>, create a link
    for (AbstractDNode node : mapsPorts.keySet()) {
      Collection<DNode> nodes = mapsPorts.get(node);
      for (DNode sourcePort : nodes) {
        for (DNode targetPort : nodes) {
          if (!sourcePort.equals(targetPort)) {

            DEdge link = retrieveInternalLink(sourcePort, targetPort, path_p, color_p);
            if (link != null) {
              internalLinks.add(link);
            }
          }
        }
      }
    }

    return internalLinks;
  }

  /**
   * Create or return an internal link between both nodes.
   */
  protected DEdge retrieveInternalLink(DNode sourceNode_p, DNode targetNode_p, PhysicalPath fc_p, RGBValues color_p) {
    if (sourceNode_p == targetNode_p) {
      return null;
    }

    DEdge internalLink = null;
    for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(sourceNode_p)) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalPath) && (anEdge.getTarget().equals(fc_p))) {

        if ((anEdge.getSourceNode() != null) && (anEdge.getTargetNode() != null)) {
          if (anEdge.getSourceNode().equals(sourceNode_p) && anEdge.getTargetNode().equals(targetNode_p)) {
            internalLink = anEdge;
            break;
          } else if (anEdge.getSourceNode().equals(targetNode_p) && anEdge.getTargetNode().equals(sourceNode_p)) {
            internalLink = anEdge;
            break;
          }
        }
      }
    }

    for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(sourceNode_p)) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalPath) && (anEdge.getTarget().equals(fc_p))) {

        if ((anEdge.getSourceNode() != null) && (anEdge.getTargetNode() != null)) {
          if (anEdge.getSourceNode().equals(sourceNode_p) && anEdge.getTargetNode().equals(targetNode_p)) {
            internalLink = anEdge;
            break;
          } else if (anEdge.getSourceNode().equals(targetNode_p) && anEdge.getTargetNode().equals(sourceNode_p)) {
            internalLink = anEdge;
            break;
          }
        }
      }
    }

    if (internalLink == null) {
      internalLink = createInternalLink(sourceNode_p, targetNode_p, fc_p, color_p);
    }
    if (internalLink != null) {
      customizeInternalLinksEdgeStyle(internalLink, color_p);
    }
    return internalLink;
  }

  /**
   * @param sourceNode_p
   * @param targetNode_p
   * @param color_p
   * @return
   */
  public DEdge createInternalLink(DNode sourceNode_p, DNode targetNode_p, PhysicalPath fc_p, RGBValues color_p) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceNode_p);
    EdgeMapping mapping = getPhysicalPathInternLinkEdgeMapping(diagram);
    DEdge newEdge = DiagramServices.getDiagramServices().findDEdgeElement(diagram, sourceNode_p, targetNode_p, fc_p, mapping);
    if (newEdge == null) {
      newEdge = DiagramServices.getDiagramServices().createEdge(mapping, sourceNode_p, targetNode_p, fc_p);
    }
    return newEdge;
  }

  /**
   * @param newEdge_p
   * @param color_p
   */
  public void customizeInternalLinksEdgeStyle(DEdge edge_p, RGBValues color) {
    RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
    ShapeUtil.setEdgeColorStyle(edge_p, rgbColor);
    ShapeUtil.setEdgeThickStyle(edge_p, THICK_EDGE_PHYSICAL_PATH);

  }

  /**
   * @param newEdge_p
   * @param color_p
   */
  public void customizePhysicalLinkEdgeStyle(DEdge edge_p, RGBValues color) {

    // get current style size of an edge
    EdgeStyle edgeStyle = edge_p.getOwnedStyle();
    Integer currentSize = edgeStyle.getSize();

    // get default style size of an edge
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getEdgeMapping(edge_p);
    if (mapping != null) {
      EdgeStyleDescription desc =
          (EdgeStyleDescription) getMappingHelper(edge_p).getBestStyleDescription(mapping, edge_p.getTarget(), edge_p, edge_p.eContainer(),
              CapellaServices.getService().getDiagramContainer(edge_p));
      if (null != desc) {
        // assuming it is an integer value
        String defaultSize = desc.getSizeComputationExpression();
        if ((null != defaultSize) && (null != currentSize)
            && (currentSize.equals(Integer.valueOf(defaultSize)) || (currentSize.equals(THICK_EDGE_PHYSICAL_PATH)))) {
          // apply change
          RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
          ShapeUtil.setEdgeColorStyle(edge_p, rgbColor);
          ShapeUtil.setEdgeThickStyle(edge_p, THICK_EDGE_PHYSICAL_PATH);
        }
      }
    }

  }

  public boolean isCompletePhysicalPath(PhysicalPath path_p, DDiagram diagram_p) {
    Set<PhysicalLink> visibleLinks = new HashSet<PhysicalLink>();
    for (DEdge anEdge : diagram_p.getEdges()) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalLink)) {
        visibleLinks.add((PhysicalLink) anEdge.getTarget());
      }
    }
    for (PhysicalLink link : PhysicalPathExt.getFlatPhysicalLinks(path_p)) {
      if (!(visibleLinks.contains(link))) {
        return false;
      }
    }
    return true;
  }

  public String getPhysicalPathLabel(PhysicalPath path_p, DDiagram diagram_p) {
    String label = path_p.getName();

    boolean isComplete = isCompletePhysicalPath(path_p, diagram_p);
    boolean isValid = PhysicalPathExt.isPhysicalPathValid(path_p);
    if (!isComplete || !isValid) {
      label = label + " ("; //$NON-NLS-1$
    }
    if (!isComplete) {
      label = label + INCOMPLETE_PHYSICAL_PATH_LABEL;
    }
    if (!isComplete && !isValid) {
      label = label + ", "; //$NON-NLS-1$
    }
    if (!isValid) {
      label = label + INVALID_PHYSICAL_PATH_LABEL;
    }
    if (!isComplete || !isValid) {
      label = label + ")"; //$NON-NLS-1$
    }
    return label;
  }

  public void updatePhysicalPathNodeColor(DNode pathNode_p, Collection<DNode> visiblePhysicalPaths_p) {
    RGBValues color = ShapeUtil.getNodeColorStyle(pathNode_p);
    LinkedList<RGB> colorList = new LinkedList<RGB>();

    RGB blue = new RGB(24, 114, 248);
    RGB yellow = new RGB(249, 252, 103);
    RGB purple = new RGB(160, 32, 240);
    RGB gray = new RGB(136, 136, 136);
    RGB orange = new RGB(255, 165, 0);
    RGB green = new RGB(34, 139, 34);
    RGB brown = new RGB(165, 42, 42);

    colorList.addLast(blue);
    colorList.addLast(brown);
    colorList.addLast(orange);
    colorList.addLast(green);
    colorList.addLast(purple);
    colorList.addLast(yellow);
    colorList.addLast(gray);

    boolean changeColor = false;

    if (ShapeUtil.isSameColor(color, gray)) {
      changeColor = true;
    }
    for (DNode aFc : visiblePhysicalPaths_p) {
      if (!aFc.equals(pathNode_p)) {
        RGBValues nodeColor = ShapeUtil.getNodeColorStyle(aFc);
        if (ShapeUtil.isSameColor(nodeColor, color)) {
          changeColor = true;
        }
        ShapeUtil.removeColorFromList(nodeColor, colorList);
      }
    }
    if (!changeColor) {
      return;
    }
    if (!colorList.isEmpty()) {
      ShapeUtil.setColorStyle(pathNode_p, colorList.get(0));
    }
  }

  /**
   * @param aFE_p
   */
  public void resetPhysicalLinkStyle(DEdge aEdge_p) {
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getEdgeMapping(aEdge_p);
    if (mapping != null) {
      // get default style size of an edge
      EdgeStyleDescription desc =
          (EdgeStyleDescription) getMappingHelper(aEdge_p).getBestStyleDescription(mapping, aEdge_p.getTarget(), aEdge_p, aEdge_p.eContainer(),
              CapellaServices.getService().getDiagramContainer(aEdge_p));
      String defaultStyleSize = desc.getSizeComputationExpression();
      // get current style size of an edge
      EdgeStyle edgeStyle = aEdge_p.getOwnedStyle();
      Integer currentSize = edgeStyle.getSize();

      if ((null != currentSize) && (null != defaultStyleSize)) {
        // apply style & color : if currentSize is equal to default size + if current size is equal to default size of Functional Chain
        if (currentSize.equals(THICK_EDGE_PHYSICAL_PATH) || currentSize.equals(defaultStyleSize)) {
          if (ShapeUtil.resetEdgeThickStyle(aEdge_p, new Integer(defaultStyleSize))) {
            ShapeUtil.resetEdgeColorStyle(aEdge_p, ShapeUtil.getDefaultColor(aEdge_p, desc, desc.getStrokeColor()));
          }
        }
      }
    }
  }

  public EdgeMapping getPhysicalPathInternLinkEdgeMapping(DDiagram diagram_p) {
    String mappingName = MappingConstantsHelper.getMappingPhysicalPathInternLink(diagram_p);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  public List<PhysicalPathInvolvement> getPreviousPhysicalPathInvolvements(PhysicalPathInvolvement involvement_p) {
    return involvement_p.getPreviousInvolvements();
  }

  public List<CapellaElement> getAllComponentsFromPhysicalArchitecture(PhysicalArchitecture arch_p) {
    List<CapellaElement> returnedList = new ArrayList<CapellaElement>();
    BlockArchitectureExt.getAllComponentsFromPA(arch_p, returnedList);
    return returnedList;
  }

  public boolean isAnEdgeInvolvementAvailableInPPD(final EObject context_p, final PhysicalPathInvolvement source_p, final PhysicalPathInvolvement target_p) {
    Collection<PhysicalLink> commonExchanges = getPPDCommonPhysicalLinks(source_p, target_p);
    boolean result = !commonExchanges.isEmpty();
    return (result && !findInvolvementInNext(target_p, source_p, new HashSet<PhysicalPathInvolvement>()));
  }

  public boolean findInvolvementInNext(final PhysicalPathInvolvement currentInvolvement_p, final PhysicalPathInvolvement involvementToFind_p,
      final Set<PhysicalPathInvolvement> visitedInvolvements_p) {
    if (visitedInvolvements_p.contains(currentInvolvement_p)) {
      // to avoid infinite loop (cycle)
      return false;
    }
    if (currentInvolvement_p.equals(involvementToFind_p)) {
      return true;
    }
    Set<PhysicalPathInvolvement> visitedInvolvements = new HashSet<PhysicalPathInvolvement>(visitedInvolvements_p);
    visitedInvolvements.add(currentInvolvement_p);
    for (PhysicalPathInvolvement aNext : currentInvolvement_p.getNextInvolvements()) {
      if (findInvolvementInNext(aNext, involvementToFind_p, visitedInvolvements)) {
        return true;
      }
    }
    return false;
  }

  public List<PhysicalLink> getAvailablePhysicaLinks(PhysicalPath path_p, Part source, Part target) {
    List<PhysicalLink> returnedPhysicalLinks = new ArrayList<PhysicalLink>();
    Collection<PhysicalLink> incoming = PhysicalLinkExt.getAllRelatedPhysicalLinks(target);
    Collection<PhysicalLink> outgoing = PhysicalLinkExt.getAllRelatedPhysicalLinks(source);
    List<AbstractPathInvolvedElement> involvedElements = PhysicalPathExt.getInvolvedElements(path_p);
    for (PhysicalLink aPhysicalLink : incoming) {
      if (outgoing.contains(aPhysicalLink) && !involvedElements.contains(aPhysicalLink)) {
        returnedPhysicalLinks.add(aPhysicalLink);
      }
    }
    return returnedPhysicalLinks;
  }

  public List<PhysicalLink> getPPDInvolvePhysicalLinkAndComponentScope(DNode node_p) {
    List<PhysicalLink> returnedList = new ArrayList<PhysicalLink>();
    List<PhysicalLink> existingInvolvedFE = getOutgoingIncomingEdgePhysicalLinks(node_p);

    PhysicalPathInvolvement selectedInvolvement = (PhysicalPathInvolvement) node_p.getTarget();
    if (selectedInvolvement.getInvolved() instanceof PhysicalPath) {
      existingInvolvedFE.addAll(PhysicalPathExt.getFlatPhysicalLinks((PhysicalPath) selectedInvolvement.getInvolved()));
    }

    for (PhysicalLink aFE : PhysicalPathExt.getFlatOutgoingIncomingLinks(selectedInvolvement)) {
      if (!existingInvolvedFE.contains(aFE)) {
        returnedList.add(aFE);
      }
    }
    return returnedList;
  }

  public List<PhysicalLink> getPPDInvolvePhysicalLinkAndPartScope(DNode node_p) {
    List<PhysicalLink> returnedList = new ArrayList<PhysicalLink>();
    List<PhysicalLink> existingInvolvedFE = getOutgoingIncomingEdgePhysicalLinks(node_p);

    PhysicalPathInvolvement selectedInvolvement = (PhysicalPathInvolvement) node_p.getTarget();
    if (selectedInvolvement.getInvolved() instanceof PhysicalPath) {
      existingInvolvedFE.addAll(PhysicalPathExt.getFlatPhysicalLinks((PhysicalPath) selectedInvolvement.getInvolved()));
    }

    for (PhysicalLink aFE : PhysicalPathExt.getFlatOutgoingIncomingLinks(selectedInvolvement)) {
      if (!existingInvolvedFE.contains(aFE)) {
        returnedList.add(aFE);
      }
    }

    return returnedList;
  }

  public List<PhysicalLink> getAvailablePhysicalLinkToInsertInPPD(DNode node_p) {
    List<PhysicalLink> returnedList = new ArrayList<PhysicalLink>();
    List<PhysicalLink> existingInvolvedLinks = new ArrayList<PhysicalLink>();
    for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(node_p)) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalPathInvolvement)) {
        PhysicalPathInvolvement currentInv = (PhysicalPathInvolvement) anEdge.getTarget();
        if ((currentInv.getInvolvedElement() != null) && (currentInv.getInvolvedElement() instanceof PhysicalLink)) {
          existingInvolvedLinks.add((PhysicalLink) currentInv.getInvolvedElement());
        }
      }
    }
    PhysicalPathInvolvement selectedInvolvement = (PhysicalPathInvolvement) node_p.getTarget();
    List<AbstractPathInvolvedElement> involvedElements = PhysicalPathExt.getInvolvedElements((PhysicalPath) selectedInvolvement.eContainer());
    for (PhysicalLink aLink : PhysicalLinkExt.getAllRelatedPhysicalLinks((Part) selectedInvolvement.getInvolvedElement())) {
      if (!existingInvolvedLinks.contains(aLink) && !involvedElements.contains(aLink)) {
        returnedList.add(aLink);
      }
    }
    return returnedList;
  }

  public Part getOppositePart(PhysicalLink link_p, EObject involvedElement_p) {
    Part sourcePart = PhysicalLinkExt.getSourcePart(link_p);
    if (involvedElement_p.equals(sourcePart)) {
      return PhysicalLinkExt.getTargetPart(link_p);
    }
    if (involvedElement_p instanceof PhysicalPath) {
      for (PhysicalPathInvolvement involvment : PhysicalPathExt.getFlatLastPhysicalPathInvolvments((PhysicalPath) involvedElement_p)) {
        if (sourcePart.equals(involvment.getInvolvedElement())) {
          return PhysicalLinkExt.getTargetPart(link_p);
        }
      }
    }

    return sourcePart;
  }

  /**
   * Don't use it, to remove after 2.3
   * @param inv_p
   * @return true if the involvement is the source or the target of the physical path
   */
  @Deprecated
  public boolean isPhysicalPathEnd(PhysicalPathInvolvement inv_p) {
    return false;
  }

  private MappingHelper getMappingHelper(DSemanticDecorator semanticDecorator_p) {
    return new MappingHelper(SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticDecorator_p.getTarget()));
  }

  public Collection<PhysicalLink> getPPDInvolvePhysicalLinkScope(EObject context_p, EObject source_p, EObject target_p) {
    PhysicalPathInvolvement sourceInvolvment = (PhysicalPathInvolvement) source_p;
    PhysicalPathInvolvement targetInvolvment = (PhysicalPathInvolvement) target_p;

    if ((sourceInvolvment.getInvolved() != null) && sourceInvolvment.getInvolved().equals(targetInvolvment.getInvolved())) {
      return Collections.emptyList();
    }
    Collection<PhysicalLink> commonExchanges = getPPDCommonPhysicalLinks(sourceInvolvment, targetInvolvment);

    // We remove any already involved PhysicalLink in source/target PhysicalPath
    if (sourceInvolvment.getInvolved() instanceof PhysicalPath) {
      commonExchanges.removeAll(PhysicalPathExt.getFlatPhysicalLinks(((PhysicalPathReference) sourceInvolvment).getReferencedPhysicalPath()));
    }
    if (targetInvolvment.getInvolved() instanceof PhysicalPath) {
      commonExchanges.removeAll(PhysicalPathExt.getFlatPhysicalLinks(((PhysicalPathReference) targetInvolvment).getReferencedPhysicalPath()));
    }

    return commonExchanges;
  }

  /**
   * @param source_p
   * @param target_p
   * @return
   */
  private Collection<PhysicalLink> getPPDCommonPhysicalLinks(PhysicalPathInvolvement source_p, PhysicalPathInvolvement target_p) {
    return PhysicalPathExt.getFlatCommonPhysicalLinks(source_p, target_p);
  }

  /**
   * @param context_p
   * @return
   */
  public HashMapSet<PhysicalLink, PhysicalPath> getPPDInvolvePhysicalLinkAndPhysicalPathScope(DNode node_p) {
    HashMapSet<PhysicalLink, PhysicalPath> set = new HashMapSet<PhysicalLink, PhysicalPath>();
    if ((node_p == null) || (node_p.getTarget() == null) || node_p.getTarget().eIsProxy()) {
      return set;
    }
    EObject target = node_p.getTarget();
    if (!(target instanceof PhysicalPathInvolvement)) {
      return set;
    }

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node_p);
    if (!(diagram instanceof DSemanticDecorator)) {
      return set;
    }

    List<PhysicalLink> existingInvolvedFE = getOutgoingIncomingEdgePhysicalLinks(node_p);

    PhysicalPathInvolvement involvement = (PhysicalPathInvolvement) target;
    Collection<PhysicalLink> outgoing = PhysicalPathExt.getFlatOutgoingIncomingLinks(involvement);

    Collection<Part> sourceParts = new HashSet<Part>();

    if (involvement.getInvolved() instanceof PhysicalPath) {
      PhysicalPath path = (PhysicalPath) involvement.getInvolved();
      outgoing.removeAll(PhysicalPathExt.getFlatPhysicalLinks(path));
      sourceParts.addAll(PhysicalPathExt.getFlatPhysicalPathFirstParts(path));
      sourceParts.addAll(PhysicalPathExt.getFlatPhysicalPathLastParts(path));

    } else if (involvement.getInvolved() instanceof Part) {
      sourceParts.add((Part) involvement.getInvolved());
    }

    Collection<PhysicalPath> paths = getPPDInvolvePhysicalPathScope((DSemanticDecorator) diagram);

    for (PhysicalPath path : paths) {

      if (path.equals(involvement.getInvolved())) {
        continue;
      }

      Collection<PhysicalLink> incoming = PhysicalPathExt.getFlatOutgoingIncomingLinks(path);
      incoming.retainAll(outgoing);
      incoming.removeAll(PhysicalPathExt.getFlatPhysicalLinks(path));

      Collection<Part> targetParts = new HashSet<Part>();
      targetParts.addAll(PhysicalPathExt.getFlatPhysicalPathFirstParts(path));
      targetParts.addAll(PhysicalPathExt.getFlatPhysicalPathLastParts(path));

      for (PhysicalLink exchange : incoming) {

        if (!existingInvolvedFE.contains(exchange)) {

          Collection<Part> parts = new HashSet<Part>();
          parts.addAll(PhysicalLinkExt.getSourceParts(exchange));
          parts.addAll(PhysicalLinkExt.getTargetParts(exchange));

          parts.removeAll(sourceParts);
          parts.retainAll(targetParts);

          if (!parts.isEmpty()) {
            set.put(exchange, path);
          }
        }
      }
    }

    return set;
  }

  /**
   * @param node_p
   * @return
   */
  private List<PhysicalLink> getOutgoingIncomingEdgePhysicalLinks(DNode node_p) {
    List<PhysicalLink> existingInvolvedFE = new ArrayList<PhysicalLink>();
    for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(node_p)) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalPathInvolvement)) {
        PhysicalPathInvolvement currentInv = (PhysicalPathInvolvement) anEdge.getTarget();
        if ((currentInv.getInvolvedElement() != null) && (currentInv.getInvolvedElement() instanceof PhysicalLink)) {
          existingInvolvedFE.add((PhysicalLink) currentInv.getInvolvedElement());
        }
      }
    }
    for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(node_p)) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof PhysicalPathInvolvement)) {
        PhysicalPathInvolvement currentInv = (PhysicalPathInvolvement) anEdge.getTarget();
        if ((currentInv.getInvolvedElement() != null) && (currentInv.getInvolvedElement() instanceof PhysicalLink)) {
          existingInvolvedFE.add((PhysicalLink) currentInv.getInvolvedElement());
        }
      }
    }

    return existingInvolvedFE;
  }

  /**
   * @param context_p
   * @return
   */
  public HashMapSet<PhysicalLink, PhysicalPath> getInvolvePhysicalLinkAndPhysicalPathInitialSelection(AbstractDNode context_p) {
    return new HashMapSet<PhysicalLink, PhysicalPath>();
  }

  /**
   * @param context_p
   * @param hashMapSet_p
   * @param hashMapSet2_p
   * @param hashMapSet3_p
   */
  public void involvedPPDPhysicalLinkPhysicalPath(AbstractDNode context_p, HashMapSet<PhysicalLink, PhysicalPath> scope_p,
      HashMapSet<PhysicalLink, PhysicalPath> initialSelection_p, HashMapSet<PhysicalLink, PhysicalPath> selection_p) {
    if ((context_p == null)) {
      return;
    }
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(context_p);
    if ((diagram == null) || !(diagram instanceof DSemanticDecorator)) {
      return;
    }
    PhysicalPath sourceFC = (PhysicalPath) ((DSemanticDecorator) diagram).getTarget();
    EObject target = context_p.getTarget();
    if ((target == null) || target.eIsProxy() || !(target instanceof PhysicalPathInvolvement)) {
      return;
    }

    PhysicalPathInvolvement iSource = (PhysicalPathInvolvement) ((DSemanticDecorator) context_p).getTarget();

    for (PhysicalLink link : selection_p.keySet()) {
      for (PhysicalPath chain : selection_p.get(link)) {
        PhysicalPathInvolvement iExchange = CsFactory.eINSTANCE.createPhysicalPathInvolvement();
        iExchange.setInvolved(link);
        iExchange.setInvolver(sourceFC);
        sourceFC.getOwnedPhysicalPathInvolvements().add(iExchange);
        iSource.getNextInvolvements().add(iExchange);

        PhysicalPathInvolvement iChain = CsFactory.eINSTANCE.createPhysicalPathReference();
        iChain.setInvolved(chain);
        iChain.setInvolver(sourceFC);
        sourceFC.getOwnedPhysicalPathInvolvements().add(iChain);
        iExchange.getNextInvolvements().add(iChain);

        AbstractNodeMapping nodeMapping =
            DiagramServices.getDiagramServices().getAbstractNodeMapping(diagram, IMappingNameConstants.PPD_PHYSICAL_PATH__MAPPING_NAME);
        if (nodeMapping == null) {
          return;
        }
        AbstractDNode node = DiagramServices.getDiagramServices().createAbstractDNode(nodeMapping, iChain, diagram, diagram);
        if (node == null) {
          return;
        }
        EdgeMapping edgeMapping = DiagramServices.getDiagramServices().getEdgeMapping(diagram, IMappingNameConstants.PPD_PHYSICAL_LINK__MAPPING_NAME);
        if (edgeMapping == null) {
          return;
        }
        DiagramServices.getDiagramServices().createEdge(edgeMapping, (EdgeTarget) context_p, (EdgeTarget) node, iExchange);
      }
    }
  }

}
