/*******************************************************************************
 * Copyright (c) 2006, 2021 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.model.business.internal.operations.DDiagramSpecOperations;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.RGB;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.SimpleOrientedGraph;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
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
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.deployment.DeploymentFactory;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInternalLinksGraph;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInternalLinksGraph.InternalLinkEdge;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInvolvementGraph;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInvolvementGraph.InvolvementEdge;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.sirius.analysis.cache.DEdgeIconCache;
import org.polarsys.capella.core.sirius.analysis.cache.PhysicalPathCache;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.preferences.DiagramProcessChainPathPreferencePage;
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

  private static final Integer THICK_EDGE_PHYSICAL_PATH = Integer.valueOf(4);
  private static final String INCOMPLETE_PHYSICAL_PATH_LABEL = "incomplete"; //$NON-NLS-1$
  private static final String INVALID_PHYSICAL_PATH_LABEL = "invalid"; //$NON-NLS-1$

  public boolean isMultipleDeploymentAllowed() {
    return CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed();
  }

  /**
   * Returns all semantics of source views for the given path
   */
  public Collection<EObject> getPhysicalPathSources(PhysicalPath path) {
    // a link is not oriented, so return both bounds
    Set<EObject> result = new HashSet<>();

    for (PhysicalPathInvolvement anInvolvement : PhysicalPathExt.getFlatInvolvementsOf(path,
        CsPackage.Literals.PHYSICAL_LINK)) {
      PhysicalLink currentExchange = (PhysicalLink) anInvolvement.getInvolved();
      if ((currentExchange != null) && !currentExchange.eIsProxy()) {
        EObject source = PhysicalLinkExt.getSourcePort(currentExchange);
        result.add(source);
        EObject target = PhysicalLinkExt.getTargetPort(currentExchange);
        result.add(target);
      }
    }

    return result;
  }

  /**
   * Returns all semantics of target views for the given path
   */
  public Collection<EObject> getPhysicalPathTargets(PhysicalPath path) {
    // a link is not oriented, so return both bounds
    return getPhysicalPathSources(path);
  }

  /**
   * @param context
   * @return
   */
  public List<EObject> getAvailableComponentsToDeploy(PhysicalComponent context) {
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(PaPackage.Literals.PHYSICAL_COMPONENT,
        CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
    return query.getAvailableElements(context);
  }

  public List<EObject> getAvailableComponentsToDeploy(Part context) {
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(CsPackage.Literals.PART,
        CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
    List<EObject> availableElements = query.getAvailableElements(context);
    return availableElements;
  }

  public List<EObject> getDeployedComponents(EObject context) {
    if (context instanceof PhysicalComponent) {
      IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(
          PaPackage.Literals.PHYSICAL_COMPONENT, CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
      return query.getCurrentElements(context, false);

    } else if (context instanceof Part) {
      IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(CsPackage.Literals.PART,
          CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
      return query.getCurrentElements(context, false);
    }
    return Collections.emptyList();
  }

  public List<Part> getAvailableComponentsToDeploy(Part part, boolean behaviour) {
    List<Part> returnedList = new ArrayList<>();
    for (EObject anElement : getAvailableComponentsToDeploy(part)) {
      if (anElement instanceof Part) {
        Part aPart = (Part) anElement;
        if ((behaviour && isBehaviour(aPart)) || (!behaviour && isNode(aPart))) {
          returnedList.add(aPart);
        }
      }
    }
    return returnedList;
  }

  public List<Part> getDeployedComponents(Part part, boolean behaviour) {
    List<Part> returnedList = new ArrayList<>();
    for (EObject anElement : getDeployedComponents(part)) {
      if (anElement instanceof Part) {
        Part aPart = (Part) anElement;
        if ((behaviour && isBehaviour(aPart)) || (!behaviour && isNode(aPart))) {
          returnedList.add(aPart);
        }
      }
    }
    return returnedList;
  }

  public boolean isValidPPDInvolveComponent(DSemanticDecorator context) {
    return context instanceof DDiagram;
  }

  public boolean isValidPPDInvolvePart(DSemanticDecorator context) {
    return context instanceof DDiagram;
  }

  public boolean isValidPPDInvolvePhysicalPath(DSemanticDecorator context) {
    return context instanceof DDiagram;
  }

  public boolean isValidPPDInvolvePhysicalLink(EObject context, PhysicalPathInvolvement source,
      PhysicalPathInvolvement target) {
    return isAnEdgeInvolvementAvailableInPPD(context, source, target);
  }

  public boolean isValidPPDInvolvePhysicalLinkAndComponent(DSemanticDecorator context) {
    return true;
  }

  public boolean isValidPPDInvolvePhysicalLinkAndPhysicalPath(DSemanticDecorator context) {
    return context instanceof AbstractDNode;
  }

  public boolean isValidPPDInvolvePhysicalLinkAndPart(DSemanticDecorator context) {
    return true;
  }

  public Collection<PhysicalPath> getPPDInvolvePhysicalPathScope(DSemanticDecorator diagram) {
    EObject chain = diagram.getTarget();
    if (!(chain instanceof PhysicalPath)) {
      return Collections.emptyList();
    }

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(chain);

    // remove all chains owning the current chain
    List<PhysicalPath> chains = PhysicalPathExt.getAllPhysicalPaths(architecture);
    List<PhysicalPath> result = new LinkedList<>();

    for (PhysicalPath definedChain : chains) {
      boolean toAdd = true;
      if (definedChain.equals(chain)) {
        toAdd = false;
      }
      if (toAdd) {
        for (PhysicalPathInvolvement involvement : PhysicalPathExt.getFlatInvolvementsOf(definedChain,
            CsPackage.Literals.PHYSICAL_PATH)) {
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

  public Collection<Part> getPPDInvolvePartScope(DSemanticDecorator context) {
    EObject target = context.getTarget();
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
    Collection<Part> parts = new HashSet<>();
    for (Part part : PartExt.getAllPartsFromPhysicalArchitecture((PhysicalArchitecture) architecture)) {
      if (part.getAbstractType() instanceof PhysicalComponent) {
        if (PhysicalComponentNature.NODE.equals(((PhysicalComponent) part.getAbstractType()).getNature())) {
          parts.add(part);
        }
      }
    }
    return parts;
  }

  public Collection<Component> getPPDInvolveComponentScope(DSemanticDecorator context) {
    EObject target = context.getTarget();
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
    Collection<Component> parts = new HashSet<>();
    for (Part part : PartExt.getAllPartsFromBlockArch(architecture)) {
      Component component = (Component) part.getAbstractType();
      if ((component != null) && !PhysicalComponentExt.isPhysicalComponentRoot(component)) {
        parts.add(component);
      }
    }
    return parts;
  }

  /**
   * @param selectedElement
   * @return called by show/hide PhysicalLinks tool (Physical Architecture Blank Diagram)
   */
  public List<PhysicalLink> getDisplayedPhysicalLinks(DNodeContainer selectedElement) {
    List<PhysicalLink> returnedList = new ArrayList<>();
    List<DEdge> incomingOutgoingEdges = new ArrayList<>();

    for (DNode aNode : selectedElement.getOwnedBorderedNodes()) {
      if (aNode.getTarget() instanceof PhysicalPort) {
        incomingOutgoingEdges.addAll(CapellaServices.getService().getIncomingEdges(aNode));
        incomingOutgoingEdges.addAll(CapellaServices.getService().getOutgoingEdges(aNode));
      }
    }
    for (DEdge anEdge : incomingOutgoingEdges) {
      String edgeMappingName = anEdge.getMapping().getName();
      if (anEdge.getTarget() instanceof PhysicalLink
          && !(edgeMappingName.equals(IMappingNameConstants.PAB_COMPUTED_PHYSICAL_LINK)
              || edgeMappingName.equals(IMappingNameConstants.LAB_COMPUTED_PHYSICAL_LINK))) {
        returnedList.add((PhysicalLink) anEdge.getTarget());
      }
    }
    return returnedList;
  }

  /**
   * @param context
   * @return called by show/hide PhysicalLinks tool (Physical Architecture Blank Diagram)
   */
  public List<PhysicalLink> getAvailablePhysicalLinksToInsert(DNodeContainer context, DDiagram diagram) {
    List<PhysicalLink> returnedList = new ArrayList<>();
    List<PhysicalLink> allPhysicalLinks = new ArrayList<>();
    List<PhysicalLink> existingExchangesInDiagram = new ArrayList<>();

    for (DEdge anEdge : CapellaServices.getService().getDiagramContainer(context).getEdges()) {
      if (anEdge.getTarget() instanceof PhysicalLink) {
        existingExchangesInDiagram.add((PhysicalLink) anEdge.getTarget());
      }
    }

    Component currentComponent = null;
    EObject target = context.getTarget();
    if (target instanceof Component) {
      currentComponent = (Component) CsServices.getService().getComponentType((Component) target);
    } else if (target instanceof Part) {
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
   * @param context
   * @return called by show/hide PhysicalLinks tool (Physical Architecture Blank Diagram)
   */
  public List<PhysicalLink> getAvailablePhysicalLinksToInsertOld(DNodeContainer context) {
    List<PhysicalLink> returnedList = new ArrayList<>();
    List<PhysicalLink> allPhysicalLinks = new ArrayList<>();
    List<PhysicalLink> existingExchangesInDiagram = new ArrayList<>();

    for (DEdge anEdge : CapellaServices.getService().getDiagramContainer(context).getEdges()) {
      if (anEdge.getTarget() instanceof PhysicalLink) {
        existingExchangesInDiagram.add((PhysicalLink) anEdge.getTarget());
      }
    }

    Component currentComponent = null;
    EObject target = context.getTarget();
    if (target instanceof Component) {
      currentComponent = (Component) CsServices.getService().getComponentType((Component) target);
    } else if (target instanceof Part) {
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
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createABPhysicalLink(EObject context, DSemanticDecorator sourceView, DSemanticDecorator targetView) {
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

    EdgeTarget nodeSource = null;
    EdgeTarget nodeTarget = null;

    // Create or retrieve sourcePort
    PhysicalPort sourcePort = null;
    if (sourceTarget instanceof PhysicalPort) {
      sourcePort = (PhysicalPort) sourceTarget;
      nodeSource = (EdgeTarget) sourceView;

    } else if (sourcePart != null) {
      sourcePort = CsFactory.eINSTANCE.createPhysicalPort();
      ((Component) sourcePart.getType()).getOwnedFeatures().add(sourcePort);
      CapellaServices.getService().creationService(sourcePort);
      nodeSource = CsServices.getService().createViewOrGetPhysicalPort((DNodeContainer) sourceView, sourcePort)
          .getKey();
    }

    // Create or retrieve targetPort
    PhysicalPort targetPort = null;
    if (targetTarget instanceof PhysicalPort) {
      targetPort = (PhysicalPort) targetTarget;
      nodeTarget = (EdgeTarget) targetView;

    } else if (targetPart != null) {
      targetPort = CsFactory.eINSTANCE.createPhysicalPort();
      ((Component) targetPart.getType()).getOwnedFeatures().add(targetPort);
      CapellaServices.getService().creationService(targetPort);
      nodeTarget = CsServices.getService().createViewOrGetPhysicalPort((DNodeContainer) targetView, targetPort)
          .getKey();
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
    DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingABPhysicalLink(diagram),
        nodeSource, nodeTarget, exchange);
    CsServices.getService().setInterpreterVariable(context, "result", exchange);
    return context;
  }

  public EObject createPhysicalPath(EObject context, List<EObject> views, EObject source) {

    if (!views.isEmpty() && source != null) {
      List<PhysicalLink> newList = new ArrayList<>();
      for (EObject aSelectedElement : views) {
        if ((aSelectedElement instanceof DEdge) && (((DEdge) aSelectedElement).getTarget() != null)
            && (((DEdge) aSelectedElement).getTarget() instanceof PhysicalLink)) {
          newList.add((PhysicalLink) ((DEdge) aSelectedElement).getTarget());
        }
      }
      Part sourcePath;
      if (canBeInvolvedInPhysicalPath(source)) {
        sourcePath = ((Component) source).getRepresentingParts().get(0);
      } else {
        sourcePath = (Part) source;
      }

      EObject ancestor = EcoreUtil2.getCommonAncestor(newList);
      Component container = CapellaServices.getService().getComponentContainer(ancestor);
      if (container == null) {
        EObject target = ((DSemanticDiagram) CapellaServices.getService().getDiagramContainer(views.get(0)))
            .getTarget();
        container = CapellaServices.getService().getComponentContainer(target);
      }
      return PhysicalPathExt.createPhysicalPath(container, newList, sourcePath);

    }
    return null;
  }

  public boolean canBeInvolvedInPhysicalPath(EObject source) {
    return canHavePhysicalPort(source);
  }

  public boolean canHavePhysicalPort(EObject source) {

    if (source instanceof LogicalComponent) {
      LogicalComponent container = (LogicalComponent) ComponentExt.getParentRootComponent(source);
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(source);
      LogicalComponent system = (LogicalComponent) architecture.getSystem();

      return source.equals(system) || (container != null && !container.equals(system));
    }
    return (((source instanceof SystemComponent) || (source instanceof PhysicalComponent)));
  }

  /**
   * 
   * @param context
   * @param views
   * @return The available source for the creation of PhysicalPath by applying one time invoked Part algorithm.
   */
  public List<EObject> getAvailableSourcesOfPhysicalPath(EObject context, List<EObject> views) {
    HashMap<Part, Integer> parts = new HashMap<>();
    List<EObject> result = new ArrayList<>();
    for (EObject aSelectedElement : views) {
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

  public EObject getPhysicalPathSource(EObject context, List<EObject> views) {
    return getAvailableSourcesOfPhysicalPath(context, views).get(0);
  }

  public boolean isValidPhysicalPort(EObject context, EObject container) {
    if (container instanceof Part) {
      return canHavePhysicalPort(((Part) container).getAbstractType());
    }
    return false;
  }

  public boolean canBeInvolvedInPhysicalLink(EObject context) {
    if (context instanceof Part) {
      return canHavePhysicalPort(((Part) context).getAbstractType());

    }
    return context instanceof PhysicalPort;
  }
  
  public boolean isValidCreationPhysicalLink(EObject source, EObject target) {
    if (source instanceof PhysicalPort && source.equals(target)) {
      return false;
    }
    return canBeInvolvedInPhysicalLink(source) && canBeInvolvedInPhysicalLink(target);
  }

  public boolean isValidPhysicalPathSelection(EObject context, List<EObject> views) {
    SimpleOrientedGraph<Part> graph = new SimpleOrientedGraph<>();
    if (!views.isEmpty()) {
      for (EObject aSelectedElement : views) {
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
        return graph.isValid() && !getAvailableSourcesOfPhysicalPath(context, views).isEmpty();
      }
      return false;
    }
    return false;
  }

  public EObject showHidePhysicalPaths(EObject context, List<PhysicalPath> selectedPaths, DDiagram diagram) {
    Map<PhysicalPath, DNode> displayedPaths = new HashMap<>();
    for (DNode aNode : diagram.getNodes()) {
      if (aNode.getTarget() instanceof PhysicalPath) {
        displayedPaths.put((PhysicalPath) aNode.getTarget(), aNode);
      }
    }

    NodeMapping physicalPathMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        MappingConstantsHelper.getMappingPhysicalPath(diagram));
    for (PhysicalPath aPath : selectedPaths) {
      if (!displayedPaths.containsKey(aPath)) {
        DiagramServices.getDiagramServices().createNode(physicalPathMapping, aPath, diagram, diagram);
      }
    }
    for (Entry<PhysicalPath, DNode> me : displayedPaths.entrySet()) {
      if (!selectedPaths.contains(me.getKey())) {
        DiagramServices.getDiagramServices().removeNodeView(me.getValue());
      }
    }
    return context;
  }

  public List<PhysicalPath> getAvailablePhysicalPathsToInsert(EObject element, DSemanticDiagram diagram) {
    Set<EObject> viewTargets = new HashSet<>();
    for (DEdge anEdge : diagram.getEdges()) {
      if (anEdge.getTarget() instanceof PhysicalLink) {
        viewTargets.add(anEdge.getTarget());
      }
    }

    List<PhysicalPath> result = new ArrayList<>();
    for (PhysicalPath aPath : getAllPhysicalPaths(diagram.getTarget())) {
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

  public List<PhysicalPath> getDisplayedPhysicalPaths(DDiagram diagram) {
    List<PhysicalPath> returnedList = new ArrayList<>();
    for (DNode aNode : diagram.getNodes()) {
      if (aNode.getTarget() instanceof PhysicalPath) {
        returnedList.add((PhysicalPath) aNode.getTarget());
      }
    }
    return returnedList;
  }

  public EObject reconnectPhysicalLink(PhysicalLink link, AbstractPhysicalLinkEnd source,
      AbstractPhysicalLinkEnd target) {
    int index = link.getLinkEnds().indexOf(source);
    link.getLinkEnds().add(index, target);
    link.getLinkEnds().remove(source);
    return link;
  }

  /**
   * Manage allocation of PC deployment
   * 
   * @param view
   * @param selectedElements
   * @param isBehaviour
   * @return
   */
  public EObject manageComponentDeployments(DNodeContainer view, List<DeployableElement> selectedElements,
      boolean isBehaviour) {
    Part currentPart = (Part) view.getTarget();
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(view);
    Map<Part, DNodeContainer> displayedDeployments = new HashMap<>();
    ContainerMapping deploymentMapping = getDeploymentMapping(currentDiagram);

    for (DDiagramElement aDiagramElement : view.getOwnedDiagramElements()) {
      if (aDiagramElement.getMapping().equals(deploymentMapping)) {
        displayedDeployments.put((Part) aDiagramElement.getTarget(), (DNodeContainer) aDiagramElement);
      }
    }
    List<AbstractDeploymentLink> linksToRemove = new ArrayList<>(1);
    for (AbstractDeploymentLink aLink : currentPart.getOwnedDeploymentLinks()) {
      if (!selectedElements.contains(aLink.getDeployedElement())) {
        if (aLink.getDeployedElement() instanceof Part) {
          Part aDeployedPart = (Part) aLink.getDeployedElement();
          if ((isBehaviour && isBehaviour(aDeployedPart)) || (!isBehaviour && isNode(aDeployedPart))) {
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
    List<Part> deployedComponents = getDeployedComponents(currentPart, isBehaviour);
    for (DeployableElement aSelectedElement : selectedElements) {
      if (!deployedComponents.contains(aSelectedElement)) {
        PartDeploymentLink newLink = DeploymentFactory.eINSTANCE.createPartDeploymentLink();
        currentPart.getOwnedDeploymentLinks().add(newLink);
        newLink.setLocation(currentPart);
        newLink.setDeployedElement(aSelectedElement);
        DiagramServices.getDiagramServices().createContainer(deploymentMapping, aSelectedElement, view, currentDiagram);
      }
    }
    return view;
  }

  public ContainerMapping getDeploymentMapping(DDiagram diagram) {
    return DiagramServices.getDiagramServices().getContainerMapping(diagram,
        MappingConstantsHelper.getMappingABDeployedElement(diagram));
  }

  public boolean isBehaviour(Part part) {
    EObject componentType = CsServices.getService().getComponentType(part);
    PhysicalComponent comp = null;
    if (componentType instanceof PhysicalComponent) {
      comp = (PhysicalComponent) componentType;
      return PhysicalComponentNature.BEHAVIOR.equals(comp.getNature());
    }
    return false;
  }

  public boolean isNode(Part part) {
    EObject componentType = CsServices.getService().getComponentType(part);
    PhysicalComponent comp = null;
    if (componentType instanceof PhysicalComponent) {
      comp = (PhysicalComponent) componentType;
      return PhysicalComponentNature.NODE.equals(comp.getNature());
    }
    return false;
  }

  public boolean isPhysicalActor(Part part) {
    EObject componentType = CsServices.getService().getComponentType(part);
    return ComponentExt.isActor(componentType);
  }

  public void updateInternalPhysicalPaths(DDiagram diagram) {
    // Find displayed physical paths
    HashMap<PhysicalPath, DNode> displayedPaths = computePhysicalPathToNodeMap(diagram);

    // Find displayed Internal Links
    HashMap<PhysicalPath, Set<DEdge>> physicalPathToVisibleInternalEdges = computePhysicalPathToEdgesMap(diagram);

    // Remove internal Links if the physical path is not displayed
    for (Entry<PhysicalPath, Set<DEdge>> entry : physicalPathToVisibleInternalEdges.entrySet()) {
      PhysicalPath physicalPath = entry.getKey();
      if (!displayedPaths.containsKey(physicalPath)) {
        Set<DEdge> physicalPathEdges = entry.getValue();
        for (DEdge edge : physicalPathEdges) {
          edge.getSemanticElements().remove(physicalPath);
          if (edge.getSemanticElements().isEmpty()) {
            diagram.getOwnedDiagramElements().remove(edge);
          }
        }
      }
    }

    // Remove INVALID internal link edge
    for (Entry<PhysicalPath, Set<DEdge>> entry : physicalPathToVisibleInternalEdges.entrySet()) {
      Set<DEdge> edges = entry.getValue();
      for (DEdge edge : edges) {
        if (!isValidInternalLinkEdge(entry.getKey(), edge.getSourceNode(), edge.getTargetNode()) && entry.getKey().equals(edge.getTarget())) {
          edge.getSemanticElements().remove(entry.getKey());
          if (edge.getSemanticElements().isEmpty()) {
            DiagramServices.getDiagramServices().removeEdgeView(edge); // We remove the edge view here, on FC this is done in precondition of the edge
          }
        }
      }
    }

  }

  public Collection<EObject> getPPInternalLinkSemanticElements(DEdge view) {
    Collection<PhysicalPath> displayedPaths = getDisplayedPhysicalPaths(view.getParentDiagram());
    return view.getSemanticElements().stream().filter(x -> displayedPaths.contains(x)).collect(Collectors.toList());
  }
  
  public void updatePhysicalPathStyles(DDiagram diagram) {
    // Find displayed physical paths
    HashMap<PhysicalPath, DNode> displayedPaths = computePhysicalPathToNodeMap(diagram);
    HashMapSet<DSemanticDecorator, RGBValues> viewColors = new HashMapSet<>();
    // Find displayed Physical Links
    HashMap<PhysicalLink, DEdge> displayedPhysicalLinks = computePhysicalLinkToEdgeMap(diagram);
    // Find physical links that must be colored
    HashMap<DEdge, Set<PhysicalPath>> coloredLinks = computeEdgeToPhysicalPathsMap(displayedPaths,
        displayedPhysicalLinks);
    ArrayList<DEdge> displayedLinks = new ArrayList<>();
    
    for (Entry<PhysicalPath, DNode> entry : displayedPaths.entrySet()) {
      DNode node = entry.getValue();
      PhysicalPath path = entry.getKey();
      updatePhysicalPathNodeColor(node, displayedPaths.values());
      RGBValues color = ShapeUtil.getNodeColorStyle(node);
      PhysicalPathInvolvementGraph graph = PhysicalPathCache.getInstance().getInvolvementGraph(path);

      // Add color for related physical links
      for (InvolvementEdge edge : graph.getEdges().values()) {
        PhysicalLink involvedPL = graph.getInvolvedPhysicalLink(edge);
        if (involvedPL != null) {
          for (DSemanticDecorator view : DiagramServices.getDiagramServices().getDiagramElements(diagram, involvedPL)) {
            viewColors.put(view, color);
            if (view instanceof DEdge) {
              Set<PhysicalPath> paths = coloredLinks.computeIfAbsent((DEdge) view, k -> new HashSet<>());
              paths.add(path);
            }
          }
        }
      }
      
      // Create and highlight internal links
      PhysicalPathInternalLinksGraph linksGraph = PhysicalPathCache.getInstance().getInternalLinksGraph(graph);
      for (InternalLinkEdge edge : linksGraph.getEdges().values()) {
        PhysicalPort source = edge.getSource().getSemantic();
        PhysicalPort target = edge.getTarget().getSemantic();
        // In multi-part mode, there can be many nodes in a diagram for the same physical port
        Collection<DSemanticDecorator> sourceNodes = DiagramServices.getDiagramServices().getDiagramElements(diagram, source);
        Collection<DSemanticDecorator> targetNodes = DiagramServices.getDiagramServices().getDiagramElements(diagram, target);
        for (DSemanticDecorator sourceNode : sourceNodes) {
          for (DSemanticDecorator targetNode : targetNodes) {
            DEdge link = createInternalLink((DNode) sourceNode, (DNode) targetNode, path);
            if (link != null) {
              displayedLinks.add(link);
            }
          }
        }
      }
    }
    
    for (DEdge link : displayedLinks) {
      ArrayList<RGBValues> pathColors = new ArrayList<>();
      for (EObject pp : link.getSemanticElements()) {
        if (displayedPaths.containsKey(pp)) {
          RGBValues color = ShapeUtil.getNodeColorStyle(displayedPaths.get(pp));
          pathColors.add(color);
        }
      }
      customizeInternalLinksEdgeStyle(link, pathColors.size() == 1 ? pathColors.get(0) : ShapeUtil.getBlackColor());
    }
    
    customizePhysicalLinkEdgeLabels(coloredLinks, displayedPaths);

    // Update all physical links
    for (DEdge view : diagram.getEdges()) {
      EObject target = view.getTarget();
      if (target instanceof PhysicalLink) {
        if (viewColors.containsKey(view)) {
          customizePhysicalLinkEdgeStyle(view, ShapeUtil.getColor(viewColors.get(view)));
        } else {
          resetPhysicalLinkStyle(view);
          resetPhysicalLinkEdgeLabels(view);
        }
      }
    }
  }

  public void resetPhysicalLinkEdgeLabels(DEdge aPL) {
    DEdgeIconCache.getInstance().removeIcon(aPL);
    DEdgeIconCache.getInstance().setLabel(aPL, ICommonConstants.EMPTY_STRING);
    DiagramServices.getDiagramServices().refreshBeginEndLabels(aPL);
    CapellaServices.getService().refreshElement(aPL);
  }

  /**
   * Add pie icons on the begin and the end labels of a Physical Link
   * 
   * @param coloredLinks
   * @param displayedPaths
   */
  public void customizePhysicalLinkEdgeLabels(Map<DEdge, Set<PhysicalPath>> coloredLinks,
      Map<PhysicalPath, DNode> displayedPaths) {
    for (Map.Entry<DEdge, Set<PhysicalPath>> entry : coloredLinks.entrySet()) {
      DEdge edge = entry.getKey();
      Set<PhysicalPath> paths = entry.getValue();
      List<RGBValues> pathColors = paths.stream().map(displayedPaths::get).map(ShapeUtil::getNodeColorStyle)
          .collect(Collectors.toList());
      if (pathColors.size() > 1) {
        DEdgeIconCache.getInstance().setIcon(edge, pathColors);
        DEdgeIconCache.getInstance().setLabel(edge, DiagramServices.getDiagramServices()
            .getOverlappedLabels(paths.stream().map(AbstractNamedElement::getName).collect(Collectors.toList())));
      } else {
        DEdgeIconCache.getInstance().removeIcon(edge);
        DEdgeIconCache.getInstance().setLabel(edge, ICommonConstants.EMPTY_STRING);
      }
      DiagramServices.getDiagramServices().refreshBeginEndLabels(edge);
      CapellaServices.getService().refreshElement(edge);
    }
  }

  private HashMap<DEdge, Set<PhysicalPath>> computeEdgeToPhysicalPathsMap(HashMap<PhysicalPath, DNode> displayedPaths,
      HashMap<PhysicalLink, DEdge> displayedPhysicalLinks) {
    // Colored Physical Links
    HashMap<DEdge, Set<PhysicalPath>> coloredLinks = new HashMap<>();
    for (Entry<PhysicalPath, DNode> me : displayedPaths.entrySet()) {
      for (PhysicalLink link : PhysicalPathExt.getFlatPhysicalLinks(me.getKey())) {
        if (displayedPhysicalLinks.containsKey(link)) {
          DEdge linkEdge = displayedPhysicalLinks.get(link);
          if (!coloredLinks.containsKey(linkEdge)) {
            Set<PhysicalPath> newSet = new HashSet<>();
            newSet.add(me.getKey());
            coloredLinks.put(linkEdge, newSet);
          } else {
            coloredLinks.get(linkEdge).add(me.getKey());
          }
        }
      }
    }
    return coloredLinks;
  }

  /**
   * Returns the displayed internal links per PhysicalPath
   */
  private HashMap<PhysicalPath, Set<DEdge>> computePhysicalPathToEdgesMap(DDiagram diagram) {
    HashMap<PhysicalPath, Set<DEdge>> displayedIL = new HashMap<>();
    for (DEdge anEdge : DDiagramSpecOperations.getEdgesFromMapping(diagram, getPhysicalPathInternLinkEdgeMapping(diagram))) {
      for (EObject semantic : anEdge.getSemanticElements()) {
        if (semantic instanceof PhysicalPath) {
            displayedIL.computeIfAbsent((PhysicalPath) semantic, x -> new HashSet<>()).add(anEdge);
        }
      }
    }
    
    return displayedIL;
  }

  private HashMap<PhysicalLink, DEdge> computePhysicalLinkToEdgeMap(DDiagram diagram) {
    // Displayed Physical Links
    HashMap<PhysicalLink, DEdge> displayedPhysicalLinks = new HashMap<>();
    for (DEdge anEdge : diagram.getEdges()) {
      if (anEdge.getTarget() instanceof PhysicalLink) {
        displayedPhysicalLinks.put((PhysicalLink) anEdge.getTarget(), anEdge);
      }
    }
    return displayedPhysicalLinks;
  }

  private HashMap<PhysicalPath, DNode> computePhysicalPathToNodeMap(DDiagram diagram) {
    // Displayed Physical Paths
    HashMap<PhysicalPath, DNode> displayedPaths = new HashMap<>();
    for (DDiagramElement aNode : diagram.getOwnedDiagramElements()) {
      if ((aNode instanceof DNode) && (aNode.getTarget() instanceof PhysicalPath)) {
        displayedPaths.put((PhysicalPath) aNode.getTarget(), (DNode) aNode);
      }
    }
    return displayedPaths;
  }

  /**
   * @param physicalPathInvolvement
   * @return
   */
  protected Collection<PhysicalLink> getFlatPreviousPhysicalLinks(PhysicalPathInvolvement physicalPathInvolvement) {
    Collection<PhysicalLink> result = new HashSet<>();

    for (PhysicalPathInvolvement involvment : PhysicalPathExt
        .getFlatPreviousLinkInvolvements(physicalPathInvolvement)) {
      if (involvment.getInvolved() instanceof PhysicalLink) {
        result.add((PhysicalLink) involvment.getInvolved());
      }
    }

    return result;
  }

  /**
   * @param physicalPathInvolvement
   * @return
   */
  protected Collection<PhysicalLink> getFlatNextPhysicalLinks(PhysicalPathInvolvement physicalPathInvolvement) {
    Collection<PhysicalLink> result = new HashSet<>();

    for (PhysicalPathInvolvement involvment : PhysicalPathExt
        .getFlatNextExchangeInvolvements(physicalPathInvolvement)) {
      if (involvment.getInvolved() instanceof PhysicalLink) {
        result.add((PhysicalLink) involvment.getInvolved());
      }
    }

    return result;
  }

  public boolean isValidInternalLinkEdge(PhysicalPath path, EdgeTarget currentSourceNode, EdgeTarget currentTargetNode) {
    if (currentSourceNode == null) {
      return false;
    }
    if (currentTargetNode == null) {
      return false;
    }

    List<DEdge> edgesRelatedToSource = getRelatedEdges(currentSourceNode);
    if (!hasAtLeastOneVisibleEdgeNotPhysicalPath(edgesRelatedToSource)) {
      return false;
    }

    List<DEdge> edgesRelatedToTarget = getRelatedEdges(currentTargetNode);
    if (!hasAtLeastOneVisibleEdgeNotPhysicalPath(edgesRelatedToTarget)) {
      return false;
    }

    EObject sourceParent = currentSourceNode.eContainer();
    EObject targetParent = currentTargetNode.eContainer();
    // Internal links are valid on same parent
    if (sourceParent == null || targetParent == null || !sourceParent.equals(targetParent)) {
      return false;
    }
    
    DDiagram diagram = ((DDiagramElement) currentSourceNode).getParentDiagram();
    DEdge anotherEdge = DiagramServices.getDiagramServices().findInternalLinkEdge(diagram, currentSourceNode,
        currentTargetNode, getPhysicalPathInternLinkEdgeMapping(diagram));
    boolean hasAnotherEdge = anotherEdge != null && !(path.equals(anotherEdge.getTarget()));
    return !hasAnotherEdge;
  }

  private boolean hasAtLeastOneVisibleEdgeNotPhysicalPath(List<DEdge> edgesRelatedToSource) {
    boolean sourceHasAtLeastOneVisibleEdgeNotPhysicalPath = false;
    for (DEdge dEdge : edgesRelatedToSource) {
      if (dEdge.getTarget() instanceof PhysicalPath) {
        continue;
      }
      if (!DiagramServices.getDiagramServices().isHidden(dEdge)) {
        sourceHasAtLeastOneVisibleEdgeNotPhysicalPath = true;
        break;
      }
    }
    return sourceHasAtLeastOneVisibleEdgeNotPhysicalPath;
  }

  private List<DEdge> getRelatedEdges(EdgeTarget currentSourceNode) {
    List<DEdge> edgesRelatedToSource = new ArrayList<DEdge>();
    for (DEdge dEdge : currentSourceNode.getIncomingEdges()) {
      edgesRelatedToSource.add(dEdge);
    }
    for (DEdge dEdge : currentSourceNode.getOutgoingEdges()) {
      edgesRelatedToSource.add(dEdge);
    }
    return edgesRelatedToSource;
  }

  /**
   * Create or return an internal link between both nodes.
   */
  public DEdge createInternalLink(DNode sourceNode, DNode targetNode, PhysicalPath physicalPath) {
    if (sourceNode == targetNode) {
      return null;
    }
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceNode);
    EdgeMapping mapping = getPhysicalPathInternLinkEdgeMapping(diagram);
    DEdge newEdge = DiagramServices.getDiagramServices().findInternalLinkEdge(diagram, sourceNode, targetNode, mapping);
    if (newEdge == null && isValidInternalLinkEdge(physicalPath, sourceNode, targetNode)) {
      newEdge = DiagramServices.getDiagramServices().createEdge(mapping, sourceNode, targetNode, physicalPath);
    }
    if (newEdge != null && !newEdge.getSemanticElements().contains(physicalPath)) {
      newEdge.getSemanticElements().add(physicalPath);
    }
    return newEdge;
  }

  /**
   * @param newEdge
   * @param color
   */
  public void customizeInternalLinksEdgeStyle(DEdge edge, RGBValues color) {
    RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
    ShapeUtil.setEdgeColorStyle(edge, rgbColor);
    ShapeUtil.setEdgeThickStyle(edge, THICK_EDGE_PHYSICAL_PATH);

  }

  /**
   * @param newEdge
   * @param color
   */
  public void customizePhysicalLinkEdgeStyle(DEdge edge, RGBValues color) {

    // get current style size of an edge
    EdgeStyle edgeStyle = edge.getOwnedStyle();
    Integer currentSize = edgeStyle.getSize();

    // get default style size of an edge
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getEdgeMapping(edge);
    if (mapping != null) {
      EdgeStyleDescription desc = (EdgeStyleDescription) getMappingHelper(edge).getBestStyleDescription(mapping,
          edge.getTarget(), edge, edge.eContainer(), CapellaServices.getService().getDiagramContainer(edge));
      if (null != desc) {
        // assuming it is an integer value
        String defaultSize = desc.getSizeComputationExpression();
        if ((null != defaultSize) && (null != currentSize)
            && (currentSize.equals(Integer.valueOf(defaultSize)) || (currentSize.equals(THICK_EDGE_PHYSICAL_PATH)))) {
          // apply change
          RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
          ShapeUtil.setEdgeColorStyle(edge, rgbColor);
          ShapeUtil.setEdgeThickStyle(edge, THICK_EDGE_PHYSICAL_PATH);
        }
      }
    }

  }

  public boolean isCompletePhysicalPath(PhysicalPath path, DDiagram diagram) {
    Collection<PhysicalLink> physicalLinksOnThePath = PhysicalPathExt.getFlatPhysicalLinks(path);

    int numberOfVisibleRelatedPLEdges = 0;

    for (DEdge anEdge : diagram.getEdges()) {
      if (anEdge.isVisible()) {
        EObject edgeTarget = anEdge.getTarget();
        if (edgeTarget instanceof PhysicalLink && physicalLinksOnThePath.contains(edgeTarget)) {
          numberOfVisibleRelatedPLEdges++;
        }
      }
    }

    return numberOfVisibleRelatedPLEdges == physicalLinksOnThePath.size();
  }

  public String getPhysicalPathLabel(PhysicalPath path, DDiagram diagram) {
    String label = EObjectExt.getText(path);

    boolean isComplete = isCompletePhysicalPath(path, diagram);
    IProject project = PreferencesHelper.getProject(path);
    boolean displayIncompleteLabel = !isComplete && ScopedCapellaPreferencesStore
        .getBoolean(DiagramProcessChainPathPreferencePage.NAME_PREF_DISPLAY_INCOMPLETE_IN_PHYSICAL_PATH_LABEL, project);
    boolean isValid = PhysicalPathExt.isPhysicalPathValid(path);
    boolean displayInvalidLabel = !isValid && ScopedCapellaPreferencesStore
        .getBoolean(DiagramProcessChainPathPreferencePage.NAME_PREF_DISPLAY_INVALID_IN_PHYSICAL_PATH_LABEL, project);
    if (displayIncompleteLabel || displayInvalidLabel) {
      label = label + " ("; //$NON-NLS-1$
    }
    if (displayIncompleteLabel) {
      label = label + INCOMPLETE_PHYSICAL_PATH_LABEL;
    }
    if (displayIncompleteLabel && displayInvalidLabel) {
      label = label + ", "; //$NON-NLS-1$
    }
    if (displayInvalidLabel) {
      label = label + INVALID_PHYSICAL_PATH_LABEL;
    }
    if (displayIncompleteLabel || displayInvalidLabel) {
      label = label + ")"; //$NON-NLS-1$
    }
    return label;
  }

  public void updatePhysicalPathNodeColor(DNode pathNode, Collection<DNode> visiblePhysicalPaths) {

    RGBValues color = ShapeUtil.getNodeColorStyle(pathNode);
    ColorManager colorManager = ColorManager.getInstance();

    boolean changeColor = false;
    List<RGB> colorList = colorManager.getColorList();

    if (ShapeUtil.isSameColor(color, colorManager.getGrayColor())) {
      changeColor = true;
    }
    for (DNode aPhysicalPath : visiblePhysicalPaths) {
      if (!aPhysicalPath.equals(pathNode)) {
        RGBValues nodeColor = ShapeUtil.getNodeColorStyle(aPhysicalPath);
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
      ShapeUtil.setColorStyle(pathNode, colorList.get(0));
    }
  }

  /**
   * @param aFE
   */
  public void resetPhysicalLinkStyle(DEdge aEdge) {
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getEdgeMapping(aEdge);
    if (mapping != null) {
      // get default style size of an edge
      EdgeStyleDescription desc = (EdgeStyleDescription) getMappingHelper(aEdge).getBestStyleDescription(mapping,
          aEdge.getTarget(), aEdge, aEdge.eContainer(), CapellaServices.getService().getDiagramContainer(aEdge));
      String defaultStyleSize = desc.getSizeComputationExpression();
      // get current style size of an edge
      EdgeStyle edgeStyle = aEdge.getOwnedStyle();
      Integer currentSize = edgeStyle.getSize();

      if ((null != currentSize) && (null != defaultStyleSize)) {
        // apply style & color : if currentSize is equal to default size
        // + if current size is equal to default size of
        // Functional Chain
        if (currentSize.equals(THICK_EDGE_PHYSICAL_PATH) || currentSize.equals(defaultStyleSize)) {
          if (ShapeUtil.resetEdgeThickStyle(aEdge, Integer.valueOf(defaultStyleSize))) {
            ShapeUtil.resetEdgeColorStyle(aEdge, ShapeUtil.getDefaultColor(aEdge, desc, desc.getStrokeColor()));
          }
        }
      }
    }
  }

  public EdgeMapping getPhysicalPathInternLinkEdgeMapping(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingPhysicalPathInternLink(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  public List<PhysicalPathInvolvement> getPreviousPhysicalPathInvolvements(PhysicalPathInvolvement involvement) {
    return involvement.getPreviousInvolvements();
  }

  public Collection<? extends CapellaElement> getAllComponentsFromPhysicalArchitecture(PhysicalArchitecture arch) {
    return BlockArchitectureExt.getAllComponents(arch);
  }

  public boolean isAnEdgeInvolvementAvailableInPPD(final EObject context, final PhysicalPathInvolvement source,
      final PhysicalPathInvolvement target) {
    Collection<PhysicalLink> commonExchanges = getPPDCommonPhysicalLinks(source, target);
    boolean result = !commonExchanges.isEmpty();
    return (result && !findInvolvementInNext(target, source, new HashSet<PhysicalPathInvolvement>()));
  }

  public boolean findInvolvementInNext(final PhysicalPathInvolvement currentInvolvement,
      final PhysicalPathInvolvement involvementToFind, final Set<PhysicalPathInvolvement> visitedInvolvements) {
    if (visitedInvolvements.contains(currentInvolvement)) {
      // to avoid infinite loop (cycle)
      return false;
    }
    if (currentInvolvement.equals(involvementToFind)) {
      return true;
    }
    Set<PhysicalPathInvolvement> involvements = new HashSet<>(visitedInvolvements);
    involvements.add(currentInvolvement);
    for (PhysicalPathInvolvement aNext : currentInvolvement.getNextInvolvements()) {
      if (findInvolvementInNext(aNext, involvementToFind, involvements)) {
        return true;
      }
    }
    return false;
  }

  public List<PhysicalLink> getAvailablePhysicaLinks(PhysicalPath path, Part source, Part target) {
    List<PhysicalLink> returnedPhysicalLinks = new ArrayList<>();
    Collection<PhysicalLink> incoming = getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, target);
    Collection<PhysicalLink> outgoing = getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, source);
    List<AbstractPathInvolvedElement> involvedElements = PhysicalPathExt.getInvolvedElements(path);
    for (PhysicalLink aPhysicalLink : incoming) {
      if (outgoing.contains(aPhysicalLink) && !involvedElements.contains(aPhysicalLink)) {
        returnedPhysicalLinks.add(aPhysicalLink);
      }
    }
    return returnedPhysicalLinks;
  }

  public List<PhysicalLink> getPPDInvolvePhysicalLinkAndComponentScope(DNode node) {
    List<PhysicalLink> returnedList = new ArrayList<>();
    List<PhysicalLink> existingInvolvedFE = getOutgoingIncomingEdgePhysicalLinks(node);

    PhysicalPathInvolvement selectedInvolvement = (PhysicalPathInvolvement) node.getTarget();
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

  public List<PhysicalLink> getPPDInvolvePhysicalLinkAndPartScope(DNode node) {
    List<PhysicalLink> returnedList = new ArrayList<>();
    List<PhysicalLink> existingInvolvedFE = getOutgoingIncomingEdgePhysicalLinks(node);

    PhysicalPathInvolvement selectedInvolvement = (PhysicalPathInvolvement) node.getTarget();
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

  public List<PhysicalLink> getAvailablePhysicalLinkToInsertInPPD(DNode node) {
    List<PhysicalLink> returnedList = new ArrayList<>();
    List<PhysicalLink> existingInvolvedLinks = new ArrayList<>();
    for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(node)) {
      if (anEdge.getTarget() instanceof PhysicalPathInvolvement) {
        PhysicalPathInvolvement currentInv = (PhysicalPathInvolvement) anEdge.getTarget();
        if (currentInv.getInvolvedElement() instanceof PhysicalLink) {
          existingInvolvedLinks.add((PhysicalLink) currentInv.getInvolvedElement());
        }
      }
    }
    PhysicalPathInvolvement selectedInvolvement = (PhysicalPathInvolvement) node.getTarget();
    List<AbstractPathInvolvedElement> involvedElements = PhysicalPathExt
        .getInvolvedElements((PhysicalPath) selectedInvolvement.eContainer());
    for (PhysicalLink aLink : getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks,
        (Part) selectedInvolvement.getInvolvedElement())) {
      if (!existingInvolvedLinks.contains(aLink) && !involvedElements.contains(aLink)) {
        returnedList.add(aLink);
      }
    }
    return returnedList;
  }

  public Part getOppositePart(PhysicalLink link, EObject involvedElement) {
    Part sourcePart = PhysicalLinkExt.getSourcePart(link);
    if (involvedElement.equals(sourcePart)) {
      return PhysicalLinkExt.getTargetPart(link);
    }
    if (involvedElement instanceof PhysicalPath) {
      for (PhysicalPathInvolvement involvment : PhysicalPathExt
          .getFlatLastPhysicalPathInvolvments((PhysicalPath) involvedElement)) {
        if (sourcePart.equals(involvment.getInvolvedElement())) {
          return PhysicalLinkExt.getTargetPart(link);
        }
      }
    }

    return sourcePart;
  }

  /**
   * Don't use it, to remove after 2.3
   * 
   * @param inv
   * @return true if the involvement is the source or the target of the physical path
   */
  @Deprecated
  public boolean isPhysicalPathEnd(PhysicalPathInvolvement inv) {
    return false;
  }

  private MappingWithInterpreterHelper getMappingHelper(DSemanticDecorator semanticDecorator) {
    return new MappingWithInterpreterHelper(
        SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticDecorator.getTarget()));
  }

  public Collection<PhysicalLink> getPPDInvolvePhysicalLinkScope(EObject context, EObject source, EObject target) {
    PhysicalPathInvolvement sourceInvolvment = (PhysicalPathInvolvement) source;
    PhysicalPathInvolvement targetInvolvment = (PhysicalPathInvolvement) target;

    if ((sourceInvolvment.getInvolved() != null)
        && sourceInvolvment.getInvolved().equals(targetInvolvment.getInvolved())) {
      return Collections.emptyList();
    }
    Collection<PhysicalLink> commonExchanges = getPPDCommonPhysicalLinks(sourceInvolvment, targetInvolvment);

    // We remove any already involved PhysicalLink in source/target
    // PhysicalPath
    if (sourceInvolvment.getInvolved() instanceof PhysicalPath) {
      commonExchanges.removeAll(
          PhysicalPathExt.getFlatPhysicalLinks(((PhysicalPathReference) sourceInvolvment).getReferencedPhysicalPath()));
    }
    if (targetInvolvment.getInvolved() instanceof PhysicalPath) {
      commonExchanges.removeAll(
          PhysicalPathExt.getFlatPhysicalLinks(((PhysicalPathReference) targetInvolvment).getReferencedPhysicalPath()));
    }

    return commonExchanges;
  }

  /**
   * @param source
   * @param target
   * @return
   */
  private Collection<PhysicalLink> getPPDCommonPhysicalLinks(PhysicalPathInvolvement source,
      PhysicalPathInvolvement target) {
    return PhysicalPathExt.getFlatCommonPhysicalLinks(source, target);
  }

  /**
   * @param context
   * @return
   */
  public HashMapSet<PhysicalLink, PhysicalPath> getPPDInvolvePhysicalLinkAndPhysicalPathScope(DNode node) {
    HashMapSet<PhysicalLink, PhysicalPath> set = new HashMapSet<>();
    if ((node == null) || (node.getTarget() == null) || node.getTarget().eIsProxy()) {
      return set;
    }
    EObject target = node.getTarget();
    if (!(target instanceof PhysicalPathInvolvement)) {
      return set;
    }

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node);
    if (!(diagram instanceof DSemanticDecorator)) {
      return set;
    }

    List<PhysicalLink> existingInvolvedFE = getOutgoingIncomingEdgePhysicalLinks(node);

    PhysicalPathInvolvement involvement = (PhysicalPathInvolvement) target;
    Collection<PhysicalLink> outgoing = PhysicalPathExt.getFlatOutgoingIncomingLinks(involvement);

    Collection<Part> sourceParts = new HashSet<>();

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

      Collection<Part> targetParts = new LinkedHashSet<>();
      targetParts.addAll(PhysicalPathExt.getFlatPhysicalPathFirstParts(path));
      targetParts.addAll(PhysicalPathExt.getFlatPhysicalPathLastParts(path));

      for (PhysicalLink exchange : incoming) {

        if (!existingInvolvedFE.contains(exchange)) {

          Collection<Part> parts = new HashSet<>();
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
   * @param node
   * @return
   */
  private List<PhysicalLink> getOutgoingIncomingEdgePhysicalLinks(DNode node) {
    List<PhysicalLink> existingInvolvedFE = new ArrayList<>();
    for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(node)) {
      if (anEdge.getTarget() instanceof PhysicalPathInvolvement) {
        PhysicalPathInvolvement currentInv = (PhysicalPathInvolvement) anEdge.getTarget();
        if (currentInv.getInvolvedElement() instanceof PhysicalLink) {
          existingInvolvedFE.add((PhysicalLink) currentInv.getInvolvedElement());
        }
      }
    }
    for (DEdge anEdge : DiagramServices.getDiagramServices().getIncomingEdges(node)) {
      if (anEdge.getTarget() instanceof PhysicalPathInvolvement) {
        PhysicalPathInvolvement currentInv = (PhysicalPathInvolvement) anEdge.getTarget();
        if (currentInv.getInvolvedElement() instanceof PhysicalLink) {
          existingInvolvedFE.add((PhysicalLink) currentInv.getInvolvedElement());
        }
      }
    }

    return existingInvolvedFE;
  }

  /**
   * @param context
   * @return
   */
  public HashMapSet<PhysicalLink, PhysicalPath> getInvolvePhysicalLinkAndPhysicalPathInitialSelection(
      AbstractDNode context) {
    return new HashMapSet<>();
  }

  /**
   * @param context
   * @param scope
   * @param initialSelection
   * @param selection
   */
  public void involvedPPDPhysicalLinkPhysicalPath(AbstractDNode context, HashMapSet<PhysicalLink, PhysicalPath> scope,
      HashMapSet<PhysicalLink, PhysicalPath> initialSelection, HashMapSet<PhysicalLink, PhysicalPath> selection) {
    if ((context == null)) {
      return;
    }
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(context);
    if (!(diagram instanceof DSemanticDecorator)) {
      return;
    }
    PhysicalPath sourceFC = (PhysicalPath) ((DSemanticDecorator) diagram).getTarget();
    EObject target = context.getTarget();
    if ((target == null) || target.eIsProxy() || !(target instanceof PhysicalPathInvolvement)) {
      return;
    }

    PhysicalPathInvolvement iSource = (PhysicalPathInvolvement) context.getTarget();

    for (PhysicalLink link : selection.keySet()) {
      for (PhysicalPath chain : selection.get(link)) {
        PhysicalPathInvolvement iExchange = CsFactory.eINSTANCE.createPhysicalPathInvolvement();
        iExchange.setInvolved(link);
        sourceFC.getOwnedPhysicalPathInvolvements().add(iExchange);
        iSource.getNextInvolvements().add(iExchange);

        PhysicalPathInvolvement iChain = CsFactory.eINSTANCE.createPhysicalPathReference();
        iChain.setInvolved(chain);
        sourceFC.getOwnedPhysicalPathInvolvements().add(iChain);
        iExchange.getNextInvolvements().add(iChain);

        AbstractNodeMapping nodeMapping = DiagramServices.getDiagramServices().getAbstractNodeMapping(diagram,
            IMappingNameConstants.PPD_PHYSICAL_PATH__MAPPING_NAME);
        if (nodeMapping == null) {
          return;
        }
        AbstractDNode node = DiagramServices.getDiagramServices().createAbstractDNode(nodeMapping, iChain, diagram,
            diagram);
        if (node == null) {
          return;
        }
        EdgeMapping edgeMapping = DiagramServices.getDiagramServices().getEdgeMapping(diagram,
            IMappingNameConstants.PPD_PHYSICAL_LINK__MAPPING_NAME);
        if (edgeMapping == null) {
          return;
        }
        DiagramServices.getDiagramServices().createEdge(edgeMapping, (EdgeTarget) context, (EdgeTarget) node,
            iExchange);
      }
    }
  }

  public PhysicalComponent createPhysicalComponent(EObject container) {

    PhysicalComponent component = PaFactory.eINSTANCE.createPhysicalComponent();
    if (component != null) {
      if (container instanceof PhysicalComponent) {
        PhysicalComponent componentContainer = (PhysicalComponent) container;
        componentContainer.getOwnedPhysicalComponents().add(component);
      }
      CapellaServices.getService().creationService(component);
    }

    return component;
  }

  public List<ComponentPortAllocation> getDisplayedComponentPortAllocations(DNodeContainer selectedElement) {
    List<ComponentPortAllocation> result = new ArrayList<>();
    List<ComponentPortAllocation> allAllocations = getAllComponentPortAllocationAvailable(selectedElement);

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(selectedElement);
    for (ComponentPortAllocation portAllocation : allAllocations) {
      if (DiagramServices.getDiagramServices().getDiagramElement(diagram, portAllocation) != null)
        result.add(portAllocation);
    }
    return result;
  }

  /**
   * @param context
   * @return called by show/hide Component Port Allocations tool (Physical Architecture Blank Diagram)
   */
  public List<ComponentPortAllocation> getAllComponentPortAllocationAvailable(DNodeContainer selectedElement) {

    List<ComponentPortAllocation> result = new ArrayList<>();

    for (DNode dNode : selectedElement.getNodes()) {

      if (dNode.getTarget() instanceof PhysicalPort) {
        EObject target = dNode.getTarget();
        for (AbstractTrace trace : ((PhysicalPort) target).getOutgoingTraces()) {
          if (trace instanceof ComponentPortAllocation) {
            result.add((ComponentPortAllocation) trace);
          }
        }
      } else if (dNode.getTarget() instanceof ComponentPort) {
        EObject target = dNode.getTarget();
        for (AbstractTrace trace : ((ComponentPort) target).getIncomingTraces()) {
          if (trace instanceof ComponentPortAllocation) {
            result.add((ComponentPortAllocation) trace);
          }
        }
      }
    }
    return result;
  }

  /**
   * @param context
   * @return called by show/hide Component Port Allocations tool (Physical Architecture Blank Diagram)
   */
  public List<ComponentPortAllocation> getAvailableComponentPortAllocationToInsert(DNodeContainer selectedElement,
      DDiagram diagram) {
    List<ComponentPortAllocation> allAllocations = getAllComponentPortAllocationAvailable(selectedElement);
    List<ComponentPortAllocation> existingAllocations = getDisplayedComponentPortAllocations(selectedElement);

    allAllocations.removeAll(existingAllocations);
    return allAllocations;
  }

  public PhysicalComponentNature getComponentNature(DSemanticDecorator decorator) {

    EObject target = decorator.getTarget();

    if (target instanceof PhysicalComponentPkg) {
      Component parentComponent = ComponentPkgExt.getParentComponent((ComponentPkg) target);
      if (parentComponent instanceof PhysicalComponent) {
        return ((PhysicalComponent) parentComponent).getNature();
      }

    } else if (target instanceof PhysicalComponent) {
      return ((PhysicalComponent) target).getNature();
    } else if (target instanceof Part) {
      Component component = PartExt.getComponentOfPart((Part) target);
      if (component instanceof PhysicalComponent) {
        return ((PhysicalComponent) component).getNature();
      }

    }

    return PhysicalComponentNature.UNSET;

  }

}
