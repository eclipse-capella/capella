/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPortAllocationExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABAbstractFunction;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABComponent;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABComponentCategory;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABComponentExchange;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABFunctionalExchange;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABPhysicalCategory;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABRole;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * Shared helpers for Architecture Blank diagrams
 */
public class ABServices {

  private static ABServices service = null;

  public static ABServices getService() {
    if (service == null) {
      service = new ABServices();
    }
    return service;
  }

  public boolean isValidDndABFunctionPort(EObject element_p, DSemanticDecorator newContainer_p) {
    return DFServices.getService().isValidDndDFFunctionPort(element_p, newContainer_p);
  }

  public boolean isValidABComponentPort(EObject context_p, DSemanticDecorator containerView_p) {
    // check architecture level
    // if system do not allow inner links
    return !((containerView_p == null) || (containerView_p instanceof DDiagram));
  }

  public boolean isValidABCreationPortAllocation(EObject context_p, DSemanticDecorator sourceView_p) {
    if (sourceView_p == null) {
      return false;
    }
    EObject source = sourceView_p.getTarget();
    return ((source instanceof ComponentPort) || (source instanceof PhysicalPort));
  }

  public boolean isValidABCreationPortAllocation(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {

    if ((sourceView_p == null) || (targetView_p == null)) {
      return false;
    }

    EObject source = sourceView_p.getTarget();
    EObject target = targetView_p.getTarget();

    if (!(((source instanceof PhysicalPort) || (source instanceof ComponentPort)) && (target instanceof FunctionPort))) {
      return false;
    }

    // target port is not owned by source.component
    if (!EcoreUtil.isAncestor(sourceView_p.eContainer(), targetView_p)) {
      return false;
    }

    return true;

  }

  public boolean isValidABCreationComponentPortAllocation(EObject context_p, DSemanticDecorator sourceView_p) {
    if (sourceView_p == null) {
      return false;
    }
    EObject source = sourceView_p.getTarget();
    return (source instanceof PhysicalPort);
  }

  public boolean isValidABCreationComponentPortAllocation(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {

    if ((sourceView_p == null) || (targetView_p == null)) {
      return false;
    }

    EObject source = sourceView_p.getTarget();
    EObject target = targetView_p.getTarget();

    if (!((source instanceof PhysicalPort) && (target instanceof ComponentPort))) {
      return false;
    }

    // target port is not owned by source.component
    EObject parentTarget = targetView_p.eContainer();
    EObject parentSource = sourceView_p.eContainer();

    if ((parentTarget == null) || (parentSource == null)) {
      return false;
    }
    if (!parentSource.equals(parentTarget.eContainer())) {
      return false;
    }

    return true;

  }

  /**
   * Perform a dnd of a component.
   * @param pcMoved the given namedElement
   * @param oldContainer the given namedElement
   * @param newContainer the given namedElement
   * @return pcMoved
   */
  public EObject dndABComponent(NamedElement pcMoved, NamedElement oldContainer_p, NamedElement newContainer_p) {

    EObject oldContainer = oldContainer_p;
    EObject newContainer = newContainer_p;

    if (oldContainer instanceof Part) {
      oldContainer = CsServices.getService().getComponentType((Part) oldContainer_p);
    }
    if (newContainer instanceof Part) {
      newContainer = CsServices.getService().getComponentType((Part) newContainer_p);
    }

    if ((newContainer instanceof Component)) {
      Component newComponent = (Component) newContainer;
      Component component = null;

      // Move part in the new container
      if (pcMoved instanceof Part) {
        newComponent.getOwnedFeatures().add((Part) pcMoved);
        component = (Component) ((Part) pcMoved).getType();
      } else if (pcMoved instanceof Component) {
        for (Part part : ComponentExt.getRepresentingParts((Component) pcMoved)) {
          if (!newComponent.equals(part.eContainer())) {
            newComponent.getOwnedFeatures().add(part);
          }
        }
        component = (Component) pcMoved;
      } else {
        return pcMoved;
      }

      // move component type (avoid move if it is located in its part)
      if (!CsPackage.Literals.PART__OWNED_ABSTRACT_TYPE.equals(component.eContainingFeature())) {
        FaServices.getFaServices().moveComponent(component, newComponent);
      }

      // for all exchanges related to owned childs, move them to the ancestor (copied from odesign specification)
      List<PartitionableElement> listChild = CapellaServices.getService().getAllDescendants(component);
      listChild.add(component);
      for (PartitionableElement child : listChild) {
        if (child instanceof Component) {
          Component componentChild = (Component) child;
          for (ComponentPort port : ComponentExt.getOwnedComponentPort(componentChild)) {
            FaServices.getFaServices().moveComponentExchanges(port);
          }
        }
      }

      // Remove all port outgoing allocations
      for (Port port : ComponentExt.getOwnedComponentPort(component)) {
        FaServices.getFaServices().removeUselessPortRealizations(port, false, true, false, false);
      }

      FaServices.getFaServices().removeUselessExchanges(component);

    }

    return pcMoved;
  }

  public EObject dndABDComponent(NamedElement pcMoved, NamedElement oldContainer_p, NamedElement newContainer_p) {
    return dndABComponent(pcMoved, oldContainer_p, newContainer_p);
  }

  /**
   * Performs semantic operation for a reconnect of source from a component exchange edge
   * @param componentExchange_p
   * @param edge_p
   * @param oldNode_p
   * @param newNode_p
   */
  public EObject reconnectABComponentExchangeSource(EObject componentExchange_p, DSemanticDecorator edge_p, DSemanticDecorator oldNode_p,
      DSemanticDecorator newNode_p) {
    if (edge_p instanceof DEdge) {
      if (componentExchange_p instanceof ComponentExchange) {
        reconnectABComponentExchange((ComponentExchange) componentExchange_p, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE, (DEdge) edge_p,
            oldNode_p, newNode_p);
      }
    }
    return componentExchange_p;
  }

  /**
   * Performs semantic operation for a reconnect of target from a component exchange edge
   * @param componentExchange_p
   * @param edge_p
   * @param oldNode_p
   * @param newNode_p
   */
  public EObject reconnectABComponentExchangeTarget(EObject componentExchange_p, DSemanticDecorator edge_p, DSemanticDecorator oldNode_p,
      DSemanticDecorator newNode_p) {
    if (edge_p instanceof DEdge) {
      if (componentExchange_p instanceof ComponentExchange) {
        reconnectABComponentExchange((ComponentExchange) componentExchange_p, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET, (DEdge) edge_p,
            oldNode_p, newNode_p);
      }
    }
    return componentExchange_p;
  }

  /**
   * Performs semantic operation for a reconnect of a component exchange edge
   * @param componentExchange_p
   * @param edge_p
   * @param oldNode_p
   * @param newNode_p
   */
  public void reconnectABComponentExchange(ComponentExchange componentExchange_p, EReference bound, DEdge edge, DSemanticDecorator oldNode,
      DSemanticDecorator newNode) {
    EObject relatedPart = CsServices.getService().getRelatedPart(newNode);

    if (!(componentExchange_p instanceof CommunicationMean) && CsServices.getService().isMultipartMode(componentExchange_p)
        && (newNode.getTarget() instanceof Port) && (relatedPart instanceof Part)) {
      ComponentExchangeEnd end = null;
      Object boundValue = componentExchange_p.eGet(bound);
      if (boundValue instanceof ComponentExchangeEnd) {
        end = (ComponentExchangeEnd) boundValue;
      }

      if (end == null) {
        end = FaFactory.eINSTANCE.createComponentExchangeEnd();
        componentExchange_p.getOwnedComponentExchangeEnds().add(end);
      }

      end.setPart((Part) relatedPart);
      end.setPort((Port) newNode.getTarget());
      componentExchange_p.eSet(bound, end);

    } else {
      Object boundValue = componentExchange_p.eGet(bound);
      if (boundValue instanceof ComponentExchangeEnd) {
        CapellaServices.getService().removeElement((ComponentExchangeEnd) boundValue);
      }
      componentExchange_p.eSet(bound, newNode.getTarget());
    }

    ComponentExchangeExt.attachToDefaultContainer(componentExchange_p);
  }

  /**
   * Returns whether the source of componentExchange can be replaced from given source_p to given target_p
   * @param exchange_p
   * @param source_p
   * @param target_p
   * @return
   */
  public boolean isValidABReconnectComponentExchangeSource(ComponentExchange exchange_p, EObject source_p, EObject target_p) {
    if (exchange_p instanceof CommunicationMean) {
      return (source_p instanceof Component) && (target_p instanceof Component);
    }

    if (!CsServices.getService().isMultipartMode(exchange_p)) {
      if (target_p instanceof Part) {
        return false;
      }
    }

    if ((source_p instanceof ComponentPort) && (target_p instanceof ComponentPort)) {
      return PortExt.haveSameOrientation((ComponentPort) source_p, (ComponentPort) target_p);
    }
    if ((source_p instanceof ComponentPort) && (target_p instanceof PhysicalPort)) {
      return false;
    }

    return true;
  }

  /**
   * Returns whether the target of componentExchange can be replaced from given source_p to given target_p
   * @param exchange_p
   * @param source_p
   * @param target_p
   * @return
   */
  public boolean isValidABReconnectComponentExchangeTarget(ComponentExchange exchange_p, EObject source_p, EObject target_p) {
    return isValidABReconnectComponentExchangeSource(exchange_p, source_p, target_p);
  }

  /**
   * Performs semantic operation for a reconnect of source from a physical link edge
   * @param physicalLink_p
   * @param edge_p
   * @param oldNode_p
   * @param newNode_p
   */
  public EObject reconnectABPhysicalLinkSource(EObject physicalLink_p, DSemanticDecorator edge_p, DSemanticDecorator oldNode_p, DSemanticDecorator newNode_p) {
    if (edge_p instanceof DEdge) {
      if (physicalLink_p instanceof PhysicalLink) {
        reconnectABPhysicalLink((PhysicalLink) physicalLink_p, (DEdge) edge_p, oldNode_p, newNode_p);
      }
    }
    return physicalLink_p;
  }

  /**
   * Performs semantic operation for a reconnect of target from a physical link edge
   * @param physicalLink_p
   * @param edge_p
   * @param oldNode_p
   * @param newNode_p
   */
  public EObject reconnectABPhysicalLinkTarget(EObject physicalLink_p, DSemanticDecorator edge_p, DSemanticDecorator oldNode_p, DSemanticDecorator newNode_p) {
    if (edge_p instanceof DEdge) {
      if (physicalLink_p instanceof PhysicalLink) {
        reconnectABPhysicalLink((PhysicalLink) physicalLink_p, (DEdge) edge_p, oldNode_p, newNode_p);
      }
    }
    return physicalLink_p;
  }

  /**
   * Performs semantic operation for a reconnect of a physical link edge
   * @param physicalLink_p
   * @param edge_p
   * @param oldNode_p
   * @param newNode_p
   */
  public void reconnectABPhysicalLink(PhysicalLink physicalLink_p, DEdge edge, DSemanticDecorator oldNode, DSemanticDecorator newNode) {

    EObject oldTarget = oldNode.getTarget();
    EObject newTarget = newNode.getTarget();

    PhysicalPort oldPort = null;
    PhysicalPort newPort = null;
    if (oldTarget instanceof PhysicalPort) {
      oldPort = (PhysicalPort) oldTarget;
    }
    if (newTarget instanceof PhysicalPort) {
      newPort = (PhysicalPort) newTarget;
    }

    if (CsServices.getService().isMultipartMode(physicalLink_p) || (physicalLink_p.getOwnedPhysicalLinkEnds().size() > 0)) {
      EObject relatedPart = CsServices.getService().getRelatedPart(newNode);
      PhysicalLinkEnd end = null;
      for (PhysicalLinkEnd end1 : physicalLink_p.getOwnedPhysicalLinkEnds()) {
        if (((oldPort == null) && (end1.getPort() == null)) || ((oldPort != null) && oldPort.equals(end1.getPort()))) {
          end = end1;
          break;
        }
      }
      if (end == null) {
        end = CsFactory.eINSTANCE.createPhysicalLinkEnd();
        physicalLink_p.getOwnedPhysicalLinkEnds().add(end);
        physicalLink_p.getLinkEnds().add(end);
      }
      end.setPart((Part) relatedPart);
      end.setPort((PhysicalPort) newNode.getTarget());

    } else {
      int index = physicalLink_p.getLinkEnds().indexOf(oldPort);
      physicalLink_p.getLinkEnds().add(index, newPort);
      physicalLink_p.getLinkEnds().remove(oldPort);
    }

    org.polarsys.capella.core.model.helpers.PhysicalLinkExt.attachToDefaultContainer(physicalLink_p);
  }

  /**
   * Returns whether the source of physicalLink can be replaced from given source_p to given target_p
   * @param exchange_p
   * @param source_p
   * @param target_p
   * @return
   */
  public boolean isValidABReconnectPhysicalLinkSource(PhysicalLink exchange_p, EObject source_p, EObject target_p) {
    if (!CsServices.getService().isMultipartMode(exchange_p)) {
      if (target_p instanceof Part) {
        return false;
      }
    }
    if ((source_p instanceof ComponentPort) && (target_p instanceof ComponentPort)) {
      return true;
    }
    if ((source_p instanceof PhysicalPort) && (target_p instanceof ComponentPort)) {
      return false;
    }

    return true;
  }

  /**
   * Returns whether the target of physicalLink can be replaced from given source_p to given target_p
   * @param exchange_p
   * @param source_p
   * @param target_p
   * @return
   */
  public boolean isValidABReconnectPhysicalLinkTarget(PhysicalLink exchange_p, EObject source_p, EObject target_p) {
    return isValidABReconnectPhysicalLinkSource(exchange_p, source_p, target_p);
  }

  /**
   * Returns whether part source_p can be moved into target_p
   * @param source_p
   * @param target_p
   * @return
   */
  public boolean isValidDndComponent(Part source_p, Component target_p) {
    for (Part part : ComponentExt.getRepresentingParts(target_p)) {
      Collection<Part> parts = ComponentExt.getPartAncestors(part);
      if (parts.contains(source_p)) {
        return false;
      }
    }

    if (target_p.equals(source_p.getAbstractType())) {
      return false;
    }
    return true;
  }

  /**
   * Returns whether part source_p can be moved into target_p part
   * @param source_p
   * @param target_p
   * @return
   */
  public boolean isValidDndComponent(Part source_p, Part target_p) {
    Collection<Part> parts = ComponentExt.getPartAncestors(target_p);
    if (parts.contains(source_p)) {
      return false;
    }

    if (target_p.getAbstractType().equals(source_p.getAbstractType())) {
      return false;
    }
    return true;
  }

  /**
   * Returns whether component source_p can be moved into target_p component
   * @param source_p
   * @param target_p
   * @return
   */
  public boolean isValidDndComponent(Component source_p, Component target_p) {
    Collection<Component> parts = ComponentExt.getComponentAncestors(target_p);
    if (parts.contains(source_p)) {
      return false;
    }

    if (target_p.equals(source_p)) {
      return false;
    }
    return true;
  }

  /**
   * Returns whether the given part can be drop into the target element view
   */
  public boolean isValidDndABComponent(Part semanticObjectToDrop_p, EObject targetContainerView_p) {

    EObject context = CsServices.getService().getABTarget((DSemanticDecorator) targetContainerView_p);
    if (context instanceof BlockArchitecture) {
      return false;

    } else if (context instanceof Component) {
      return isValidDndComponent(semanticObjectToDrop_p, (Component) context);

    } else if (context instanceof Part) {
      return isValidDndComponent(semanticObjectToDrop_p, (Part) context);

    }

    return false;
  }

  /**
   * Returns whether the given part can be drop into the target element view (ABD=Architecture Breakdown)
   */
  public boolean isValidDndABDComponent(Component semanticObjectToDrop_p, EObject targetContainerView_p) {
    Object target = ((DSemanticDecorator) targetContainerView_p).getTarget();

    if (target instanceof Component) {
      return isValidDndComponent(semanticObjectToDrop_p, (Component) target);
    }
    return false;
  }

  /**
   * Returns whether physical port can be D&D
   * @param source_p
   * @param target_p
   * @return
   */
  public boolean isValidDnDPhysicalPort(EObject context_p, DSemanticDecorator container_p) {
    return PhysicalServices.getService().isValidPhysicalPort(context_p, container_p.getTarget());
  }

  /**
   * Create a functional exchange between both views
   * @param context_p
   * @param sourceView_p
   * @param targetView_p
   * @return
   */
  public EObject createABFunctionalExchange(EObject context_p, AbstractDNode sourceView_p, AbstractDNode targetView_p) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);
    ActivityNode sourceTarget = FaServices.getFaServices().getRelatedActivityNode(sourceView_p);
    ActivityNode targetTarget = FaServices.getFaServices().getRelatedActivityNode(targetView_p);
    FunctionalExchange exchange = FunctionalExchangeExt.createFunctionalExchange(sourceTarget, targetTarget);
    FaServices.getFaServices().createViewFunctionalExchange(exchange, sourceView_p, targetView_p, diagram);
    return exchange;
  }

  /**
   * Returns available states/modes to insert in given diagram
   * @param containerView_p
   * @return
   */
  public Collection<EObject> getABInsertStateModesScope(DSemanticDecorator containerView_p) {
    HashSet<State> availableStates = new HashSet<State>();
    HashSet<EObject> result = new HashSet<EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView_p);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {
      // Retrieve all available states
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (AbstractFunction function : FunctionPkgExt.getAllAbstractFunctions(BlockArchitectureExt.getFunctionPkg(architecture))) {
        availableStates.addAll(function.getAvailableInStates());
      }

      // Retrains states to states with at least one function not displayed
      for (State state : availableStates) {
        boolean addElement = false;
        for (EObject function : getABInsertStateModesRelatedElements(state, architecture)) {
          if (function instanceof AbstractFunction) {
            if (!content.containsView(function, FaServices.getFaServices().getMappingABAbstractFunction((AbstractFunction) function, diagram))) {
              addElement = true;
              break;
            }
          }
        }
        if (addElement) {
          result.add(state);
        }
      }
    }
    return result;
  }

  /**
   * Display related elements of given states modes in the current diagram
   * @param containerView_p
   * @param elements_p
   */
  public void showABStateModes(DSemanticDecorator containerView_p, Collection<EObject> elements_p) {

    HashSet<EObject> functionsToShow = new HashSet<EObject>();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView_p);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {
      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (EObject object : elements_p) {
        if (object instanceof State) {
          State mode = (State) object;
          functionsToShow.addAll(getABInsertStateModesRelatedElements(mode, sourceArchitecture));
        }
      }

      showABAbstractFunction(functionsToShow, content);
    }
  }

  /**
   * Retrieve related elements for a mode and a given architecture (functions)
   * @param mode_p
   * @param sourceArchitecture_p
   * @return
   */
  public Collection<EObject> getABInsertStateModesRelatedElements(State mode_p, BlockArchitecture sourceArchitecture_p) {
    HashSet<EObject> functionsToShow = new HashSet<EObject>();

    for (EObject element : StateMachineServices.getService().getAllFunctionsActiveInStates(sourceArchitecture_p, mode_p)) {
      BlockArchitecture targetArchitecture = BlockArchitectureExt.getRootBlockArchitecture(element);
      if (sourceArchitecture_p.equals(targetArchitecture)) {
        functionsToShow.add(element);
      }
    }
    return functionsToShow;
  }

  /**
   * Returns whether the tool insert states/modes is available in the given context
   * @param containerView_p
   * @return
   */
  public boolean isValidABInsertStateModes(DSemanticDecorator containerView_p) {
    return containerView_p instanceof DDiagram;
  }

  public Collection<EObject> getABInsertScenariosScope(DSemanticDecorator containerView_p) {
    HashSet<EObject> result = new HashSet<EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView_p);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {

      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      AbstractCapabilityPkg pkg = BlockArchitectureExt.getAbstractCapabilityPkg(sourceArchitecture);
      for (Scenario scenario : AbstractCapabilityPkgExt.getAllScenarios(pkg)) {

        boolean addElement = false;
        for (EObject element : ContextualDiagramHelper.getService().getInsertScenariosRelatedElements(scenario, sourceArchitecture)) {
          if (element instanceof AbstractFunction) {
            if (!content.containsView(element, FaServices.getFaServices().getMappingABAbstractFunction((AbstractFunction) element, diagram))) {
              addElement = true;
              break;
            }
          } else if (element instanceof FunctionalExchange) {
            if (!content.containsView(element, FaServices.getFaServices().getMappingABFunctionalExchange(diagram))) {
              addElement = true;
              break;
            }
          } else if (element instanceof Part) {
            if (!content.containsView(element, FaServices.getFaServices().getMappingABComponent(element, diagram))) {
              addElement = true;
              break;
            }
          } else if (element instanceof Role) {
            if (!content.containsView(element, FaServices.getFaServices().getMappingABRole((Role) element, diagram))) {
              addElement = true;
              break;
            }
          } else if (element instanceof Entity) {
            if (!content.containsView(element, FaServices.getFaServices().getMappingABComponent(element, diagram))) {
              addElement = true;
              break;
            }
          } else if (element instanceof ComponentExchange) {
            if (!content.containsView(element, FaServices.getFaServices().getMappingABConnection(diagram))) {
              addElement = true;
              break;
            }
          }
        }
        if (addElement) {
          if (ScenarioExt.isFunctionalScenario(scenario)) {
            result.add(scenario);
          } else if (ScenarioExt.isDataFlowFunctionalScenario(scenario)) {
            result.add(scenario);
          } else if (ScenarioExt.isDataFlowBehaviouralScenario(scenario)) {
            result.add(scenario);
          } else if (ScenarioExt.isInterfaceScenario(scenario)) {
            result.add(scenario);
          }
        }
      }
    }

    return result;
  }

  /**
   * Display related elements of given states modes in the current diagram
   * @param containerView_p
   * @param elements_p
   */
  public void showABScenarios(DSemanticDecorator containerView_p, Collection<EObject> elements_p) {

    Collection<EObject> partToShow = new HashSet<EObject>();
    Collection<EObject> roleToShow = new HashSet<EObject>();
    Collection<EObject> functionsToShow = new HashSet<EObject>();

    LinkedList<EObject> unallocatedFunctions = new LinkedList<EObject>();
    LinkedList<EObject> unallocatedRoles = new LinkedList<EObject>();
    Collection<EObject> exchangesToShow = new HashSet<EObject>();
    Collection<EObject> componentExchangesToShow = new HashSet<EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView_p);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {

      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (EObject object : elements_p) {
        if (object instanceof Scenario) {
          Scenario scenario = (Scenario) object;

          for (EObject related : ContextualDiagramHelper.getService().getInsertScenariosRelatedElements(scenario, sourceArchitecture)) {

            if (related instanceof AbstractFunction) {
              functionsToShow.add(related);

              boolean isAllocated = !((AbstractFunction) related).getAllocationBlocks().isEmpty();

              // For an OA? if not allocated, we check role allocation
              if (related instanceof OperationalActivity) {
                OperationalActivity oActivity = (OperationalActivity) related;
                if (!isAllocated) {
                  if (!oActivity.getActivityAllocations().isEmpty()) {
                    boolean isRoleAllocated = false;
                    for (ActivityAllocation allocation : oActivity.getActivityAllocations()) {
                      Role role = allocation.getRole();
                      if ((role != null) && !isRoleAllocated) {
                        if (role.getRoleAllocations().isEmpty()) {
                          unallocatedRoles.add(role);
                        }
                      }
                    }
                  } else {
                    unallocatedFunctions.add(related);
                  }
                }

              } else if (!isAllocated) {
                unallocatedFunctions.add(related);
              }

            } else if (related instanceof FunctionalExchange) {
              exchangesToShow.add(related);

            } else if (related instanceof Part) {
              partToShow.add(related);

            } else if (related instanceof Role) {
              roleToShow.add(related);
              if (((Role) related).getRoleAllocations().isEmpty()) {
                unallocatedRoles.add(related);
              }

            } else if (related instanceof Entity) {
              partToShow.add(related);

            } else if (related instanceof ComponentExchange) {
              componentExchangesToShow.add(related);
            }

          }

          if (unallocatedFunctions.size() > 0) {
            unallocatedFunctions.addFirst(scenario);
            Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);
            logger.info(new EmbeddedMessage(NLS.bind(Messages.ABServices_UnallocatedFunctions, EObjectLabelProviderHelper.getText(scenario)),
                IReportManagerDefaultComponents.MODEL, unallocatedFunctions));
          }
          if (unallocatedRoles.size() > 0) {
            unallocatedRoles.addFirst(scenario);
            Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);
            logger.info(new EmbeddedMessage(NLS.bind(Messages.ABServices_UnallocatedRoles, EObjectLabelProviderHelper.getText(scenario)),
                IReportManagerDefaultComponents.MODEL, unallocatedRoles));
          }
        }
      }

      showABComponent(partToShow, content);
      showABRole(roleToShow, content);
      showABAbstractFunction(functionsToShow, content);
      showABFunctionalExchange(exchangesToShow, content);
      showABComponentExchange(componentExchangesToShow, content);
    }

  }

  /**
   * @param roleToShow_p
   * @param content_p
   */
  public EObject showABRole(Collection<EObject> elements_p, DDiagramContents content_p) {
    AbstractShowHide shService = new ShowHideABRole(content_p);

    for (EObject element : elements_p) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content_p.getDDiagram();
  }

  /**
   * Returns whether the tool insert scenario is available in the given context
   * @param containerView_p
   * @return
   */
  public boolean isValidABInsertScenarios(DSemanticDecorator containerView_p) {
    return containerView_p instanceof DDiagram;
  }

  /**
   * Display given abstract functions
   */
  public EObject showABAbstractFunction(Collection<EObject> elements_p, DDiagramContents content_p) {

    AbstractShowHide shService = new ShowHideABAbstractFunction(content_p);

    for (EObject element : elements_p) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content_p.getDDiagram();
  }

  /**
   * Display given component exchanges
   */
  public EObject showABComponentExchange(Collection<EObject> elements_p, DDiagramContents content_p) {

    AbstractShowHide shService = new ShowHideABComponentExchange(content_p);

    for (EObject element : elements_p) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content_p.getDDiagram();
  }

  /**
   * Display given component exchanges
   */
  public EObject showABComponentExchange(Collection<EObject> elements_p, DDiagramContents content_p, DSemanticDecorator currentElementView_p) {

    AbstractShowHide shService = new ShowHideABComponentExchange(content_p);

    for (EObject element : elements_p) {
      DiagramContext context = shService.new DiagramContext();
      if (currentElementView_p instanceof DDiagramElement) {
        context.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(currentElementView_p));
      }
      shService.show(element, context);
    }

    return content_p.getDDiagram();
  }

  /**
   * Display given functional exchanges
   */
  public EObject showABFunctionalExchange(Collection<EObject> elements_p, DDiagramContents content_p) {
    AbstractShowHide shService = new ShowHideABFunctionalExchange(content_p);

    for (EObject element : elements_p) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content_p.getDDiagram();
  }

  /**
   * Display given components
   */
  public EObject showABComponent(Collection<EObject> components_p, DDiagramContents content_p) {

    if ((components_p == null) || components_p.isEmpty()) {
      return content_p.getDDiagram();
    }

    AbstractShowHide shService = new ShowHideABComponent(content_p);

    for (EObject component : components_p) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(component, context);
    }

    return content_p.getDDiagram();
  }

  public boolean isValidABComponentCategoryPort(EObject context_p, DSemanticDecorator containerView_p) {
    return true;
  }

  public boolean isValidABPhysicalCategoryPort(EObject context_p, DSemanticDecorator containerView_p) {
    return true;
  }

  public boolean isValidABPhysicalCategoryEdge(PhysicalLinkCategory category_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {

    if ((category_p == null) || (source_p == null) || (target_p == null)) {
      return false;
    }
    EObject sourcePart = CsServices.getService().getRelatedPart(source_p);
    EObject targetPart = CsServices.getService().getRelatedPart(target_p);
    DSemanticDecorator sourcePartView = CsServices.getService().getRelatedPartView(source_p);
    DSemanticDecorator targetPartView = CsServices.getService().getRelatedPartView(target_p);

    AbstractDNode sourceView = (AbstractDNode) sourcePartView;
    AbstractDNode targetView = (AbstractDNode) targetPartView;
    AbstractDNode sourceCategoryPort = null;
    AbstractDNode targetCategoryPort = null;

    EObject sourceTarget = source_p.getTarget();
    EObject targetTarget = target_p.getTarget();

    if (((sourceTarget instanceof Port) && (targetTarget instanceof PhysicalLinkCategory))
        || ((targetTarget instanceof Port) && (sourceTarget instanceof PhysicalLinkCategory))) {

      // Retrieve both category ports on parts
      for (AbstractDNode node : sourceView.getOwnedBorderedNodes()) {
        if (category_p.equals(node.getTarget())) {
          sourceCategoryPort = node;
          break;
        }
      }

      for (AbstractDNode node : targetView.getOwnedBorderedNodes()) {
        if (category_p.equals(node.getTarget())) {
          targetCategoryPort = node;
          break;
        }
      }

      // If there is already a category edge between both category ports, we remove the edge
      boolean hasEdge = false;
      if ((sourceCategoryPort != null) && (targetCategoryPort != null)) {
        for (DEdge edge : ((EdgeTarget) sourceCategoryPort).getIncomingEdges()) {
          if (category_p.equals(edge.getTarget())) {
            if (((edge.getSourceNode() == sourceCategoryPort) && (edge.getTargetNode() == targetCategoryPort))
                || ((edge.getSourceNode() == targetCategoryPort) && (edge.getTargetNode() == sourceCategoryPort))) {
              hasEdge = true;
              break;
            }
          }
        }
        for (DEdge edge : ((EdgeTarget) sourceCategoryPort).getOutgoingEdges()) {
          if (category_p.equals(edge.getTarget())) {
            if (((edge.getSourceNode() == sourceCategoryPort) && (edge.getTargetNode() == targetCategoryPort))
                || ((edge.getSourceNode() == targetCategoryPort) && (edge.getTargetNode() == sourceCategoryPort))) {
              hasEdge = true;
              break;
            }
          }
        }
      }

      if (hasEdge) {
        return false;
      }

      Port delegationTargetPort = null;
      EObject delegationSourcePart = null;
      if (sourceTarget instanceof Port) {
        delegationTargetPort = (Port) sourceTarget;
        delegationSourcePart = targetPart;
      }
      if (targetTarget instanceof Port) {
        delegationTargetPort = (Port) targetTarget;
        delegationSourcePart = sourcePart;
      }

      boolean isValid = false;

      if ((delegationTargetPort != null) && (delegationSourcePart != null)) {
        // A delegation must exist between both port/parts
        if (delegationTargetPort instanceof PhysicalPort) {
          Collection<PhysicalLink> delegations = PortExt.getDelegationPhysicalLinks((PhysicalPort) delegationTargetPort);
          for (PhysicalLink delegation : delegations) {
            if (delegationTargetPort.equals(PhysicalLinkExt.getTargetPort(delegation))) {
              if (PhysicalLinkExt.getSourceParts(delegation).contains(delegationSourcePart)) {
                isValid = true;
                break;
              }
            } else if (delegationTargetPort.equals(PhysicalLinkExt.getSourcePort(delegation))) {
              if (PhysicalLinkExt.getTargetParts(delegation).contains(delegationSourcePart)) {
                isValid = true;
                break;
              }
            }
          }
        } else if (delegationTargetPort instanceof ComponentPort) {
          // A delegation (without category) must exist between both port/parts
          Collection<ComponentPortAllocation> delegations = PortExt.getIncomingComponentPortAllocations((ComponentPort) delegationTargetPort);
          for (ComponentPortAllocation delegation : delegations) {
            if (delegationTargetPort.equals(ComponentPortAllocationExt.getTargetPort(delegation))) {
              if (ComponentPortAllocationExt.getSourceParts(delegation).contains(delegationSourcePart)) {
                isValid = true;
                break;
              }
            } else if (delegationTargetPort.equals(ComponentPortAllocationExt.getSourcePort(delegation))) {
              if (ComponentPortAllocationExt.getTargetParts(delegation).contains(delegationSourcePart)) {
                isValid = true;
                break;
              }
            }
          }
        }

        if (!isValid) {
          return false;
        }
        isValid = false;

        // A related component exchange must be with the same category
        for (PhysicalLink exchange : getRelatedPhysicalLinks(delegationSourcePart)) {
          if (!org.polarsys.capella.core.model.helpers.PhysicalLinkExt.isDelegation(exchange)) {
            if (exchange.getCategories().contains(category_p)) {
              isValid = true;
              break;
            }
          }
        }

        if (!isValid) {
          return false;
        }
      }

      // Categories of delegations must be a containment
      if (!((sourcePartView.eContainer() == targetPartView) || (targetPartView.eContainer() == sourcePartView))) {
        isValid = false;
      }
      return isValid;
    }

    // retrieve all CE for the related category
    for (PhysicalLink exchange : getRelatedPhysicalLinks(sourcePart)) {

      if (exchange.getCategories().contains(category_p)) {
        Collection<? extends EObject> sourceParts = PhysicalLinkExt.getSourceParts(exchange);
        Collection<? extends EObject> targetParts = PhysicalLinkExt.getTargetParts(exchange);

        // Test if valid for source/target and target/source (category are not oriented)
        if ((sourceParts.contains(sourcePart) && targetParts.contains(targetPart))) {
          if (CsServices.getService().isValidLinkEdge(CsServices.getService().getPhysicalLinkWrapper(exchange), sourcePartView, targetPartView, false)) {
            return true;
          }
        } else if (sourceParts.contains(targetPart) && targetParts.contains(sourcePart)) {
          if (CsServices.getService().isValidLinkEdge(CsServices.getService().getPhysicalLinkWrapper(exchange), targetPartView, sourcePartView, false)) {
            return true;
          }
        }
      }
    }

    return (getRelatedPhysicalLinks(sourcePart).size() == 0);

  }

  public boolean isValidABFunctionalExchangeEdge(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    if (!(context_p instanceof FunctionalExchange) || (sourceView_p == targetView_p)) {
      return false;
    }
    return true;
  }

  public boolean isValidCreationABNodePC(DSemanticDecorator containerView_p) {
    if (containerView_p == null) {
      return false;
    }

    EObject container = containerView_p.getTarget();
    if (container == null) {
      return false;
    }

    EObject type = CsServices.getService().getComponentType(containerView_p);
    if ((type == null) || !(type instanceof AbstractPhysicalComponent)) {
      return false;
    }

    // Deploy Node is allowed on NODE and UNSET, but not on Actor
    AbstractPhysicalComponent pcType = (AbstractPhysicalComponent) type;
    if (PhysicalComponentNature.BEHAVIOR.equals(pcType.getNature())) {
      return false;
    }

    if (pcType instanceof AbstractActor) {
      return false;
    }

    if (((containerView_p instanceof DDiagram) && (CsServices.getService().getABTarget(containerView_p) instanceof BlockArchitecture))) {
      return false;
    }

    return true;
  }

  public boolean isValidCreationABReuseNodePC(DSemanticDecorator containerView_p) {
    return isValidCreationABNodePC(containerView_p);
  }

  public boolean isValidCreationABBehaviorPC(DSemanticDecorator containerView_p) {
    if (containerView_p == null) {
      return false;
    }

    EObject container = containerView_p.getTarget();
    if (container == null) {
      return false;
    }

    EObject type = CsServices.getService().getComponentType(containerView_p);
    if ((type == null) || !(type instanceof AbstractPhysicalComponent)) {
      return false;
    }

    // Deploy Behavior is allowed on BEHAVIOR and UNSET, but not on Actor
    AbstractPhysicalComponent pcType = (AbstractPhysicalComponent) type;
    if (PhysicalComponentNature.NODE.equals(pcType.getNature())) {
      return false;
    }

    if (pcType instanceof AbstractActor) {
      return false;
    }

    if (((containerView_p instanceof DDiagram) && (CsServices.getService().getABTarget(containerView_p) instanceof BlockArchitecture))) {
      return false;
    }

    return true;
  }

  public boolean isValidCreationABReuseBehaviorPC(DSemanticDecorator containerView_p) {
    return isValidCreationABBehaviorPC(containerView_p);
  }

  public boolean isValidCreationABDeployNodePC(DSemanticDecorator containerView_p) {
    if (!(containerView_p instanceof DNodeContainer)) {
      return false;
    }

    EObject type = CsServices.getService().getComponentType(containerView_p);
    if ((type == null) || !(type instanceof AbstractPhysicalComponent)) {
      return false;
    }

    // Deploy Node is allowed only on NODE (not actor UNSET for instance)
    AbstractPhysicalComponent pcType = (AbstractPhysicalComponent) type;
    if (!PhysicalComponentNature.NODE.equals(pcType.getNature())) {
      return false;
    }

    return true;
  }

  public boolean isValidCreationABDeployBehaviorPC(DSemanticDecorator containerView_p) {
    if (!(containerView_p instanceof DNodeContainer)) {
      return false;
    }

    EObject type = CsServices.getService().getComponentType(containerView_p);
    if ((type == null) || !(type instanceof AbstractPhysicalComponent)) {
      return false;
    }

    // Deploy Behavior is allowed on all nature (UNSET too)

    return true;
  }

  public boolean isValidABFunctionalChainInternalLinkEdge(FunctionalChain chain_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    return FunctionalChainServices.getFunctionalChainServices().isValidInternalLinkEdge(chain_p, (EdgeTarget) source_p, (EdgeTarget) target_p);
  }

  public boolean isValidABPhysicalPathInternalLinkEdge(PhysicalPath path_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    if (source_p instanceof EdgeTarget) {
      HashSet<DEdge> edges = new HashSet<DEdge>();
      EdgeTarget source = (EdgeTarget) source_p;
      edges.addAll(DiagramServices.getDiagramServices().getIncomingEdges(source));
      edges.addAll(DiagramServices.getDiagramServices().getOutgoingEdges(source));

      int nbEdges = 0;
      for (DEdge edge : edges) {
        if ((edge != null) && edge.getTarget().equals(path_p)) {
          if (edge.getSourceNode().equals(target_p)) {
            nbEdges++;
          }
          if (edge.getTargetNode().equals(target_p)) {
            nbEdges++;
          }
        }
      }
      return nbEdges <= 1;
    }
    return true;
  }

  public boolean isValidABComponentCategoryEdge(ComponentExchangeCategory category_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {

    if ((category_p == null) || (source_p == null) || (target_p == null)) {
      return false;
    }

    EObject sourcePart = CsServices.getService().getRelatedPart(source_p);
    EObject targetPart = CsServices.getService().getRelatedPart(target_p);
    DSemanticDecorator sourcePartView = CsServices.getService().getRelatedPartView(source_p);
    DSemanticDecorator targetPartView = CsServices.getService().getRelatedPartView(target_p);

    AbstractDNode sourceView = (AbstractDNode) sourcePartView;
    AbstractDNode targetView = (AbstractDNode) targetPartView;
    AbstractDNode sourceCategoryPort = null;
    AbstractDNode targetCategoryPort = null;

    EObject sourceTarget = source_p.getTarget();
    EObject targetTarget = target_p.getTarget();

    if (((sourceTarget instanceof ComponentPort) && (targetTarget instanceof ComponentExchangeCategory))
        || ((targetTarget instanceof ComponentPort) && (sourceTarget instanceof ComponentExchangeCategory))) {

      // Retrieve both category ports on parts
      for (AbstractDNode node : sourceView.getOwnedBorderedNodes()) {
        if (category_p.equals(node.getTarget())) {
          sourceCategoryPort = node;
          break;
        }
      }

      for (AbstractDNode node : targetView.getOwnedBorderedNodes()) {
        if (category_p.equals(node.getTarget())) {
          targetCategoryPort = node;
          break;
        }
      }

      // If there is already a category edge between both category ports, we remove the edge
      boolean hasEdge = false;
      if ((sourceCategoryPort != null) && (targetCategoryPort != null)) {
        for (DEdge edge : ((EdgeTarget) sourceCategoryPort).getIncomingEdges()) {
          if (category_p.equals(edge.getTarget())) {
            if (((edge.getSourceNode() == sourceCategoryPort) && (edge.getTargetNode() == targetCategoryPort))
                || ((edge.getSourceNode() == targetCategoryPort) && (edge.getTargetNode() == sourceCategoryPort))) {
              hasEdge = true;
              break;
            }
          }
        }
        for (DEdge edge : ((EdgeTarget) sourceCategoryPort).getOutgoingEdges()) {
          if (category_p.equals(edge.getTarget())) {
            if (((edge.getSourceNode() == sourceCategoryPort) && (edge.getTargetNode() == targetCategoryPort))
                || ((edge.getSourceNode() == targetCategoryPort) && (edge.getTargetNode() == sourceCategoryPort))) {
              hasEdge = true;
              break;
            }
          }
        }
      }

      if (hasEdge) {
        return false;
      }

      ComponentPort delegationTargetPort = null;
      EObject delegationSourcePart = null;

      if (sourceTarget instanceof ComponentPort) {
        delegationTargetPort = (ComponentPort) sourceTarget;
        delegationSourcePart = targetPart;
      }
      if (targetTarget instanceof ComponentPort) {
        delegationTargetPort = (ComponentPort) targetTarget;
        delegationSourcePart = sourcePart;
      }

      boolean isValid = false;

      if ((delegationTargetPort != null) && (delegationSourcePart != null)) {

        // A delegation (without category) must exist between both port/parts
        Collection<ComponentExchange> delegations = PortExt.getDelegationComponentExchanges(delegationTargetPort);
        for (ComponentExchange delegation : delegations) {
          if (delegationTargetPort.equals(ComponentExchangeExt.getTargetPort(delegation))) {
            if (ComponentExchangeExt.getSourcePartsAndEntities(delegation).contains(delegationSourcePart)) {
              isValid = true;
              break;
            }
          } else if (delegationTargetPort.equals(ComponentExchangeExt.getSourcePort(delegation))) {
            if (ComponentExchangeExt.getTargetPartsAndEntities(delegation).contains(delegationSourcePart)) {
              isValid = true;
              break;
            }
          }
        }

        if (!isValid) {
          return false;
        }
        isValid = false;

        // A related component exchange must be with the same category
        for (ComponentExchange exchange : getRelatedComponentExchanges(delegationSourcePart)) {
          if (!ComponentExchangeExt.isDelegation(exchange)) {
            if (exchange.getCategories().contains(category_p)) {
              isValid = true;
              break;
            }
          }
        }

        if (!isValid) {
          return false;
        }
      }

      // Categories of delegations must be a containment
      if (!((sourcePartView.eContainer() == targetPartView) || (targetPartView.eContainer() == sourcePartView))) {
        isValid = false;
      }
      return isValid;
    }

    // retrieve all CE for the related category
    for (ComponentExchange exchange : getRelatedComponentExchanges(sourcePart)) {

      if (exchange.getCategories().contains(category_p)) {
        Collection<? extends EObject> sourceParts = ComponentExchangeExt.getSourcePartsAndEntities(exchange);
        Collection<? extends EObject> targetParts = ComponentExchangeExt.getTargetPartsAndEntities(exchange);

        // Test if valid for source/target and target/source (category are not oriented)
        if ((sourceParts.contains(sourcePart) && targetParts.contains(targetPart))) {
          if (CsServices.getService().isValidLinkEdge(CsServices.getService().getComponentExchangeWrapper(exchange), sourcePartView, targetPartView, false)) {
            return true;
          }
        } else if (sourceParts.contains(targetPart) && targetParts.contains(sourcePart)) {
          if (CsServices.getService().isValidLinkEdge(CsServices.getService().getComponentExchangeWrapper(exchange), targetPartView, sourcePartView, false)) {
            return true;
          }
        }
      }
    }

    return (getRelatedComponentExchanges(sourcePart).size() == 0);
  }

  public boolean isValidShowHideABPhysicalCategory(EObject context, EObject containerView_p) {
    return isValidShowHideABComponentCategory(context, containerView_p);
  }

  public boolean isValidShowHideABComponentCategory(EObject context, EObject containerView_p) {
    if (containerView_p instanceof AbstractDNode) {
      EObject target = ((AbstractDNode) containerView_p).getTarget();
      if ((target != null) && ((target instanceof Part) || (target instanceof Entity))) {
        return true;
      }
    }
    return false;
  }

  public boolean isValidSwitchABPhysicalCategory(EObject context, EObject containerView_p) {
    return isValidSwitchABComponentCategory(context, containerView_p);
  }

  public boolean isValidSwitchABComponentCategory(EObject context, EObject containerView_p) {
    return containerView_p instanceof DDiagram;
  }

  public EObject showABPhysicalCategories(DSemanticDecorator context_p, HashMapSet<EObject, EObject> scope, HashMapSet<EObject, EObject> initialSelection,
      final HashMapSet<EObject, EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context_p);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    EObject source = context_p.getTarget();

    AbstractShowHide shService = new ShowHideABPhysicalCategory(content);
    DiagramContext context = shService.new DiagramContext();
    if (context_p instanceof DDiagramElement) {
      context.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context_p));
    }

    for (EObject key : scope.keySet()) {
      PhysicalLinkCategory category = (PhysicalLinkCategory) key;

      for (EObject value : scope.get(key)) {
        if (!selectedElements.containsKey(key) || !selectedElements.get(key).contains(value)) {
          if (initialSelection.containsKey(key) && initialSelection.get(key).contains(value)) {

            showABPhysicalCategory(shService, context, (PhysicalLinkCategory) key, source, value, false);

            for (PhysicalLink exchange : getRelatedPhysicalLinks(source)) {
              if (exchange.getCategories().contains(key)) {
                displayABPhysicalCategoryPortDelegation(context, category, exchange, (PhysicalPort) PhysicalLinkExt.getSourcePort(exchange), false, shService);
                displayABPhysicalCategoryPortDelegation(context, category, exchange, (PhysicalPort) PhysicalLinkExt.getTargetPort(exchange), false, shService);
              }
            }

          }
        }
      }
    }

    for (EObject key : selectedElements.keySet()) {
      PhysicalLinkCategory category = (PhysicalLinkCategory) key;

      for (EObject target : selectedElements.get(key)) {
        showABPhysicalCategory(shService, context, (PhysicalLinkCategory) key, source, target, true);
      }

      for (PhysicalLink exchange : getRelatedPhysicalLinks(source)) {
        if (exchange.getCategories().contains(key)) {
          displayABPhysicalCategoryPortDelegation(context, category, exchange, (PhysicalPort) PhysicalLinkExt.getSourcePort(exchange), true, shService);
          displayABPhysicalCategoryPortDelegation(context, category, exchange, (PhysicalPort) PhysicalLinkExt.getTargetPort(exchange), true, shService);
        }
      }
    }

    content.commitDeferredActions();
    return context_p;
  }

  /**
   * Show selected categories and hide unselected categories from the given source
   * @param context_p
   * @param scope
   * @param initialSelection
   * @param selectedElements
   * @return
   */
  public EObject showABComponentCategories(DSemanticDecorator context_p, HashMapSet<EObject, EObject> scope, HashMapSet<EObject, EObject> initialSelection,
      final HashMapSet<EObject, EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context_p);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    EObject source = context_p.getTarget();

    AbstractShowHide shService = new ShowHideABComponentCategory(content);
    DiagramContext context = shService.new DiagramContext();
    if (context_p instanceof DDiagramElement) {
      context.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context_p));
    }

    for (EObject key : scope.keySet()) {
      ComponentExchangeCategory category = (ComponentExchangeCategory) key;

      for (EObject value : scope.get(key)) {
        if (!selectedElements.containsKey(key) || !selectedElements.get(key).contains(value)) {
          if (initialSelection.containsKey(key) && initialSelection.get(key).contains(value)) {
            showABComponentCategory(shService, context, (ComponentExchangeCategory) key, source, value, false);

            for (ComponentExchange exchange : getRelatedComponentExchanges(source)) {
              if (exchange.getCategories().contains(key)) {
                displayABComponentCategoryPortDelegation(context, category, exchange, (ComponentPort) ComponentExchangeExt.getSourcePort(exchange), false,
                    shService);
                displayABComponentCategoryPortDelegation(context, category, exchange, (ComponentPort) ComponentExchangeExt.getTargetPort(exchange), false,
                    shService);
              }
            }

          }
        }
      }
    }

    for (EObject key : selectedElements.keySet()) {
      ComponentExchangeCategory category = (ComponentExchangeCategory) key;

      for (EObject target : selectedElements.get(key)) {
        showABComponentCategory(shService, context, (ComponentExchangeCategory) key, source, target, true);
      }

      for (ComponentExchange exchange : getRelatedComponentExchanges(source)) {
        if (exchange.getCategories().contains(key)) {
          displayABComponentCategoryPortDelegation(context, category, exchange, (ComponentPort) ComponentExchangeExt.getSourcePort(exchange), true, shService);
          displayABComponentCategoryPortDelegation(context, category, exchange, (ComponentPort) ComponentExchangeExt.getTargetPort(exchange), true, shService);
        }
      }
    }

    content.commitDeferredActions();

    return context_p;
  }

  public EObject switchABPhysicalCategories(DSemanticDecorator context_p, Collection<EObject> scope, Collection<EObject> initialSelection,
      Collection<EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context_p);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    return switchABPhysicalCategories(content, context_p, selectedElements, true);
  }

  public EObject switchABPhysicalCategories(DDiagramContents content_p, DSemanticDecorator context_p, Collection<EObject> selectedElements,
      boolean showPhysicalExchanges) {
    ABServices.getService().updateABPhysicalCategories(content_p);

    DDiagram currentDiagram = content_p.getDDiagram();
    Collection<DDiagramElement> sourceViews = new ArrayList<DDiagramElement>();

    // retrieve all part views where to apply the show/hide
    if (context_p instanceof DDiagramElement) {
      sourceViews.add((DDiagramElement) context_p);
    }
    if (sourceViews.isEmpty()) {
      for (DDiagramElement element : content_p.getDiagramElements(FaServices.getFaServices().getMappingABComponent(CsPackage.Literals.ABSTRACT_ACTOR,
          currentDiagram))) {
        sourceViews.add(element);
      }
      for (DDiagramElement element : content_p.getDiagramElements(FaServices.getFaServices()
          .getMappingABComponent(CsPackage.Literals.COMPONENT, currentDiagram))) {
        sourceViews.add(element);
      }
      if (currentDiagram.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
        for (DDiagramElement element : content_p.getDiagramElements(FaServices.getFaServices().getMappingABDeployedElement(currentDiagram))) {
          sourceViews.add(element);
        }
      }
    }

    AbstractShowHide categories = new ShowHideABPhysicalCategory(content_p);
    DiagramContext context = categories.new DiagramContext();
    if (context_p instanceof DDiagramElement) {
      context.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context_p));
    }

    // show or hide categorie links
    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        EObject source = sourceViewTarget;
        HashMapSet<EObject, EObject> scopeSource = getABShowHidePhysicalCategoriesScope(sourceView);
        for (EObject key : scopeSource.keySet()) {

          if (selectedElements.contains(key)) {
            for (EObject target : scopeSource.get(key)) {
              showABPhysicalCategory(categories, context, (PhysicalLinkCategory) key, source, target, true);
            }
          } else {
            for (EObject target : scopeSource.get(key)) {
              showABPhysicalCategory(categories, context, (PhysicalLinkCategory) key, source, target, false);
            }
          }
        }
      }
    }

    // show or hide physical links
    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        EObject source = sourceViewTarget;
        HashMapSet<EObject, EObject> scopeSource = getABShowHidePhysicalCategoriesScope(sourceView);

        // Traverse the categories selected by the user
        for (EObject key : scopeSource.keySet()) {
          PhysicalLinkCategory category = (PhysicalLinkCategory) key;
          if (selectedElements.contains(key)) {
            for (PhysicalLink exchange : getRelatedPhysicalLinks(source)) {
              if (exchange.getCategories().contains(key)) {
                displayABPhysicalCategoryPortDelegation(context, category, exchange, (PhysicalPort) PhysicalLinkExt.getSourcePort(exchange), true, categories);
                displayABPhysicalCategoryPortDelegation(context, category, exchange, (PhysicalPort) PhysicalLinkExt.getTargetPort(exchange), true, categories);
                categories.hide(exchange, context);
              }
            }
          } else if (showPhysicalExchanges) {
            for (PhysicalLink exchange : getRelatedPhysicalLinks(source)) {
              if (exchange.getCategories().contains(key)) {
                displayABPhysicalCategoryPortDelegation(context, category, exchange, (PhysicalPort) PhysicalLinkExt.getSourcePort(exchange), false, categories);
                displayABPhysicalCategoryPortDelegation(context, category, exchange, (PhysicalPort) PhysicalLinkExt.getTargetPort(exchange), false, categories);
                categories.show(exchange, context);
              }
            }
          }
        }
      }
    }

    ABServices.getService().updateABPhysicalCategories(content_p);

    content_p.commitDeferredActions();
    return context_p;
  }

  public EObject switchABComponentCategories(DSemanticDecorator context_p, Collection<EObject> scope, Collection<EObject> initialSelection,
      Collection<EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context_p);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    return switchABComponentCategories(content, context_p, selectedElements);
  }

  /**
   * Show selected categories and hide unselected categories from the given source
   * @param context_p
   * @param scope
   * @param initialSelection
   * @param selectedElements
   * @return
   */
  public EObject switchABComponentCategories(DDiagramContents content_p, DSemanticDecorator context_p, Collection<EObject> selectedElements) {

    ABServices.getService().updateABComponentCategories(content_p);

    DDiagram currentDiagram = content_p.getDDiagram();
    Collection<DDiagramElement> sourceViews = new HashSet<DDiagramElement>();
    if (context_p instanceof DDiagramElement) {
      sourceViews.add((DDiagramElement) context_p);
    }
    if (sourceViews.isEmpty()) {
      for (DDiagramElement element : content_p.getDiagramElements(FaServices.getFaServices().getMappingABComponent(CsPackage.Literals.ABSTRACT_ACTOR,
          currentDiagram))) {
        sourceViews.add(element);
      }
      for (DDiagramElement element : content_p.getDiagramElements(FaServices.getFaServices()
          .getMappingABComponent(CsPackage.Literals.COMPONENT, currentDiagram))) {
        sourceViews.add(element);
      }
      if (currentDiagram.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
        for (DDiagramElement element : content_p.getDiagramElements(FaServices.getFaServices().getMappingABDeployedElement(currentDiagram))) {
          sourceViews.add(element);
        }
      }

    }

    AbstractShowHide categories = new ShowHideABComponentCategory(content_p);
    DiagramContext context = categories.new DiagramContext();
    if (context_p instanceof DDiagramElement) {
      context.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context_p));
    }

    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        EObject source = sourceViewTarget;
        HashMapSet<EObject, EObject> scopeSource = getABShowHideComponentCategoriesScope(sourceView);
        for (EObject key : scopeSource.keySet()) {

          if (selectedElements.contains(key)) {
            for (EObject target : scopeSource.get(key)) {
              showABComponentCategory(categories, context, (ComponentExchangeCategory) key, source, target, true);
            }
          } else {
            for (EObject target : scopeSource.get(key)) {
              showABComponentCategory(categories, context, (ComponentExchangeCategory) key, source, target, false);
            }
          }
        }
      }
    }

    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        EObject source = sourceViewTarget;
        HashMapSet<EObject, EObject> scopeSource = getABShowHideComponentCategoriesScope(sourceView);
        for (EObject key : scopeSource.keySet()) {
          ComponentExchangeCategory category = (ComponentExchangeCategory) key;
          if (selectedElements.contains(key)) {
            for (ComponentExchange exchange : getRelatedComponentExchanges(source)) {
              if (exchange.getCategories().contains(key)) {
                displayABComponentCategoryPortDelegation(context, category, exchange, (ComponentPort) ComponentExchangeExt.getSourcePort(exchange), true,
                    categories);
                displayABComponentCategoryPortDelegation(context, category, exchange, (ComponentPort) ComponentExchangeExt.getTargetPort(exchange), true,
                    categories);
                categories.hide(exchange, context);
              }
            }
          } else {
            for (ComponentExchange exchange : getRelatedComponentExchanges(source)) {
              if (exchange.getCategories().contains(key)) {
                displayABComponentCategoryPortDelegation(context, category, exchange, (ComponentPort) ComponentExchangeExt.getSourcePort(exchange), false,
                    categories);
                displayABComponentCategoryPortDelegation(context, category, exchange, (ComponentPort) ComponentExchangeExt.getTargetPort(exchange), false,
                    categories);
                categories.show(exchange, context);
              }
            }
          }
        }
      }
    }

    ABServices.getService().updateABComponentCategories(content_p);

    content_p.commitDeferredActions();
    return context_p;
  }

  /**
   * @param categories_p
   * @param context_p
   * @param key_p
   * @param source_p
   * @param target_p
   * @param b_p show or hide according to this parameter
   */
  private void showABPhysicalCategory(AbstractShowHide categories_p, DiagramContext context_p, PhysicalLinkCategory key_p, EObject source_p, EObject target_p,
      boolean b_p) {

    context_p.setVariable(ShowHideABComponent.SOURCE_PARTS, Collections.singletonList(source_p));
    context_p.setVariable(ShowHideABComponent.TARGET_PARTS, Collections.singletonList(target_p));

    boolean isSourceAndTargetNoPort = false;
    boolean isSourceAndTargetPort = false;
    for (PhysicalLink exchanges : getRelatedPhysicalLinks(source_p, target_p, key_p)) {
      Port sourcePort = PhysicalLinkExt.getSourcePort(exchanges);
      Port targetPort = PhysicalLinkExt.getTargetPort(exchanges);
      if ((sourcePort == null) && (targetPort == null)) {
        isSourceAndTargetNoPort = true;
      }
      if ((sourcePort != null) && (targetPort != null)) {
        isSourceAndTargetPort = true;
      }
    }

    if (isSourceAndTargetNoPort) {
      context_p.setVariable(ShowHideABPhysicalCategory.NO_TARGET_PORT, Boolean.TRUE);
      context_p.setVariable(ShowHideABPhysicalCategory.NO_SOURCE_PORT, Boolean.TRUE);
      if (b_p) {
        categories_p.show(key_p, context_p);
      } else {
        categories_p.hide(key_p, context_p);
      }

    }
    if (isSourceAndTargetPort) {
      context_p.setVariable(ShowHideABPhysicalCategory.NO_TARGET_PORT, Boolean.FALSE);
      context_p.setVariable(ShowHideABPhysicalCategory.NO_SOURCE_PORT, Boolean.FALSE);
      if (b_p) {
        categories_p.show(key_p, context_p);
      } else {
        categories_p.hide(key_p, context_p);
      }
    }
  }

  /**
   * @param categories_p
   * @param context_p
   * @param key_p
   * @param source_p
   * @param target_p
   */
  private void showABComponentCategory(AbstractShowHide categories_p, DiagramContext context_p, ComponentExchangeCategory key_p, EObject source_p,
      EObject target_p, boolean b_p) {

    context_p.setVariable(ShowHideABComponent.SOURCE_PARTS, Collections.singletonList(source_p));
    context_p.setVariable(ShowHideABComponent.TARGET_PARTS, Collections.singletonList(target_p));

    boolean isSourceAndTargetNoPort = false;
    boolean isSourceAndTargetPort = false;

    for (ComponentExchange exchanges : getRelatedComponentExchanges(source_p, target_p, key_p)) {
      Port sourcePort = ComponentExchangeExt.getSourcePort(exchanges);
      Port targetPort = ComponentExchangeExt.getTargetPort(exchanges);
      if ((sourcePort == null) && (targetPort == null)) {
        isSourceAndTargetNoPort = true;
      }
      if ((sourcePort != null) && (targetPort != null)) {
        isSourceAndTargetPort = true;
      }
    }

    if (isSourceAndTargetNoPort) {
      context_p.setVariable(ShowHideABPhysicalCategory.NO_TARGET_PORT, Boolean.TRUE);
      context_p.setVariable(ShowHideABPhysicalCategory.NO_SOURCE_PORT, Boolean.TRUE);
      if (b_p) {
        categories_p.show(key_p, context_p);
      } else {
        categories_p.hide(key_p, context_p);
      }

    }
    if (isSourceAndTargetPort) {
      context_p.setVariable(ShowHideABPhysicalCategory.NO_TARGET_PORT, Boolean.FALSE);
      context_p.setVariable(ShowHideABPhysicalCategory.NO_SOURCE_PORT, Boolean.FALSE);
      if (b_p) {
        categories_p.show(key_p, context_p);
      } else {
        categories_p.hide(key_p, context_p);
      }
    }
  }

  /**
   * @param exchange_p
   * @param sourcePort_p
   * @param b_p
   * @param categories_p
   */
  protected void displayABPhysicalCategoryPortDelegation(DiagramContext context_p, PhysicalLinkCategory category_p, PhysicalLink exchange_p,
      PhysicalPort sourcePort_p, boolean b_p, AbstractShowHide service_p) {

    if (sourcePort_p == null) {
      return;
    }

    // Display a mix-category/delegation
    for (EObject element : getPhysicalLinkDelegationsForCategory(sourcePort_p, category_p)) {
      if (element instanceof PhysicalLink) {
        PhysicalLink delegation = (PhysicalLink) element;

        if (PhysicalLinkExt.getSourcePort(delegation).equals(sourcePort_p)) {
          context_p.setVariable(ShowHideABComponentCategory.TARGET_PORTS, Collections.singletonList(PhysicalLinkExt.getTargetPort(delegation)));
        }
        if (PhysicalLinkExt.getTargetPort(delegation).equals(sourcePort_p)) {
          context_p.setVariable(ShowHideABComponentCategory.SOURCE_PORTS, Collections.singletonList(PhysicalLinkExt.getSourcePort(delegation)));
        }

        context_p.setVariable(ShowHideABComponent.SOURCE_PARTS, PhysicalLinkExt.getSourceParts(delegation));
        context_p.setVariable(ShowHideABComponent.TARGET_PARTS, PhysicalLinkExt.getTargetParts(delegation));

        if (b_p) {
          service_p.show(category_p, context_p);
          context_p.unsetVariable(ShowHideABPhysicalCategory.SOURCE_PORTS);
          context_p.unsetVariable(ShowHideABPhysicalCategory.TARGET_PORTS);
          service_p.hide(delegation, context_p);

        } else {
          service_p.hide(category_p, context_p);
          context_p.unsetVariable(ShowHideABPhysicalCategory.SOURCE_PORTS);
          context_p.unsetVariable(ShowHideABPhysicalCategory.TARGET_PORTS);
          service_p.show(delegation, context_p);
        }
      } else if (element instanceof ComponentPortAllocation) {
        ComponentPortAllocation delegation = (ComponentPortAllocation) element;

        if (ComponentPortAllocationExt.getSourcePort(delegation).equals(sourcePort_p)) {
          context_p.setVariable(ShowHideABComponentCategory.TARGET_PORTS, Collections.singletonList(ComponentPortAllocationExt.getTargetPort(delegation)));
        }
        if (ComponentPortAllocationExt.getTargetPort(delegation).equals(sourcePort_p)) {
          context_p.setVariable(ShowHideABComponentCategory.SOURCE_PORTS, Collections.singletonList(ComponentPortAllocationExt.getSourcePort(delegation)));
        }

        context_p.setVariable(ShowHideABComponent.SOURCE_PARTS, ComponentPortAllocationExt.getSourceParts(delegation));
        context_p.setVariable(ShowHideABComponent.TARGET_PARTS, ComponentPortAllocationExt.getTargetParts(delegation));

        if (b_p) {
          service_p.show(category_p, context_p);
          context_p.unsetVariable(ShowHideABPhysicalCategory.SOURCE_PORTS);
          context_p.unsetVariable(ShowHideABPhysicalCategory.TARGET_PORTS);
          service_p.hide(delegation, context_p);

        } else {
          service_p.hide(category_p, context_p);
          context_p.unsetVariable(ShowHideABPhysicalCategory.SOURCE_PORTS);
          context_p.unsetVariable(ShowHideABPhysicalCategory.TARGET_PORTS);
          service_p.show(delegation, context_p);
        }
      }
    }
  }

  /**
   * @param exchange_p
   * @param sourcePort_p
   * @param b_p
   * @param categories_p
   */
  protected void displayABComponentCategoryPortDelegation(DiagramContext context_p, ComponentExchangeCategory category_p, ComponentExchange exchange_p,
      ComponentPort sourcePort_p, boolean b_p, AbstractShowHide service_p) {

    if (sourcePort_p == null) {
      return;
    }

    // Display a mix-category/delegation
    for (ComponentExchange delegation : getComponentExchangeDelegationsForCategory(sourcePort_p, category_p)) {

      if (ComponentExchangeExt.getSourcePort(delegation).equals(sourcePort_p)) {
        context_p.setVariable(ShowHideABComponentCategory.TARGET_PORTS, Collections.singletonList(ComponentExchangeExt.getTargetPort(delegation)));
      }
      if (ComponentExchangeExt.getTargetPort(delegation).equals(sourcePort_p)) {
        context_p.setVariable(ShowHideABComponentCategory.SOURCE_PORTS, Collections.singletonList(ComponentExchangeExt.getSourcePort(delegation)));
      }

      context_p.setVariable(ShowHideABComponent.SOURCE_PARTS, ComponentExchangeExt.getSourcePartsAndEntities(delegation));
      context_p.setVariable(ShowHideABComponent.TARGET_PARTS, ComponentExchangeExt.getTargetPartsAndEntities(delegation));

      if (b_p) {
        service_p.show(category_p, context_p);
        context_p.unsetVariable(ShowHideABComponentCategory.SOURCE_PORTS);
        context_p.unsetVariable(ShowHideABComponentCategory.TARGET_PORTS);
        service_p.hide(delegation, context_p);

      } else {
        service_p.hide(category_p, context_p);
        context_p.unsetVariable(ShowHideABComponentCategory.SOURCE_PORTS);
        context_p.unsetVariable(ShowHideABComponentCategory.TARGET_PORTS);
        service_p.show(delegation, context_p);
      }
    }
  }

  /**
   * @param exchange_p
   * @param key_p
   * @return
   */
  protected Collection<EObject> getPhysicalLinkDelegationsForCategory(PhysicalPort port_p, PhysicalLinkCategory category) {
    Collection<EObject> result = new ArrayList<EObject>();

    if (port_p != null) {
      for (PhysicalLink exchange : PortExt.getDelegatedPhysicalLinks(port_p)) {
        if (!exchange.getCategories().contains(category)) {
          result.add(exchange);
        }
      }

      for (ComponentPortAllocation allocation : port_p.getOwnedComponentPortAllocations()) {
        result.add(allocation);
      }

    }
    return result;
  }

  /**
   * @param exchange_p
   * @param key_p
   * @return
   */
  protected Collection<ComponentExchange> getComponentExchangeDelegationsForCategory(ComponentPort port_p, ComponentExchangeCategory category) {
    Collection<ComponentExchange> result = new ArrayList<ComponentExchange>();

    if (port_p != null) {
      for (ComponentExchange exchange : PortExt.getDelegatedComponentExchanges(port_p)) {
        if (!exchange.getCategories().contains(category)) {
          result.add(exchange);
        }
      }
    }
    return result;
  }

  public Collection<PhysicalLink> getRelatedPhysicalLinks(EObject source_p, EObject target_p, PhysicalLinkCategory category_p) {
    Collection<PhysicalLink> result = new ArrayList<PhysicalLink>();

    for (PhysicalLink element : getRelatedPhysicalLinks(source_p)) {
      if (element.getCategories().contains(category_p)) {
        Collection<? extends EObject> sourceParts = PhysicalLinkExt.getSourceParts(element);
        Collection<? extends EObject> targetParts = PhysicalLinkExt.getTargetParts(element);
        // Test if valid for source/target and target/source (category are not oriented)
        if ((sourceParts.contains(source_p) && targetParts.contains(target_p))) {
          result.add(element);

        } else if ((sourceParts.contains(target_p) && targetParts.contains(source_p))) {
          result.add(element);
        }
      }
    }
    return result;
  }

  public Collection<PhysicalLink> getRelatedPhysicalLinks(EObject part_p) {
    if (part_p instanceof Part) {
      return PhysicalLinkExt.getAllRelatedPhysicalLinks((Part) part_p);
    }
    return Collections.emptyList();
  }

  public Collection<ComponentExchange> getRelatedComponentExchanges(EObject sourcePart_p, EObject targetPart_p, ComponentExchangeCategory category_p) {
    Collection<ComponentExchange> result = new ArrayList<ComponentExchange>();

    for (ComponentExchange element : getRelatedComponentExchanges(sourcePart_p)) {
      if (element.getCategories().contains(category_p)) {
        Collection<? extends EObject> sourceParts = ComponentExchangeExt.getSourcePartsAndEntities(element);
        Collection<? extends EObject> targetParts = ComponentExchangeExt.getTargetPartsAndEntities(element);
        // Test if valid for source/target and target/source (category are not oriented)
        if ((sourceParts.contains(sourcePart_p) && targetParts.contains(targetPart_p))) {
          result.add(element);

        } else if ((sourceParts.contains(targetPart_p) && targetParts.contains(sourcePart_p))) {
          result.add(element);
        }
      }
    }
    return result;
  }

  /**
   * Returns related component exchanges linked to the given element
   * @param element_p a part or a component
   * @return
   */
  public Collection<ComponentExchange> getRelatedComponentExchanges(EObject element_p) {
    if (element_p instanceof Part) {
      return ComponentExt.getAllRelatedComponentExchange((Part) element_p, true);

    } else if (element_p instanceof Component) {
      return ComponentExt.getAllRelatedComponentExchange((Component) element_p);
    }
    return Collections.emptyList();
  }

  public HashMapSet<EObject, EObject> getABShowHidePhysicalCategoriesScope(DSemanticDecorator context_p) {
    HashMapSet<EObject, EObject> result = new HashMapSet<EObject, EObject>();
    EObject relatedPart = CsServices.getService().getRelatedPart(context_p);

    if (relatedPart != null) {
      for (PhysicalLink exchange : getRelatedPhysicalLinks(relatedPart)) {
        for (PhysicalLinkCategory value : exchange.getCategories()) {
          Collection<? extends EObject> sourceParts = PhysicalLinkExt.getSourceParts(exchange);
          Collection<? extends EObject> targetParts = PhysicalLinkExt.getTargetParts(exchange);
          if (sourceParts.contains(relatedPart)) {
            for (EObject related : targetParts) {
              result.put(value, related);
            }
          } else if (targetParts.contains(relatedPart)) {
            for (EObject related : sourceParts) {
              result.put(value, related);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Retrieve a map<ExchangeCategory, Collection<Part>> of available category to display from the given source view
   * @param context_p
   * @return
   */
  public HashMapSet<EObject, EObject> getABShowHideComponentCategoriesScope(DSemanticDecorator context_p) {
    HashMapSet<EObject, EObject> result = new HashMapSet<EObject, EObject>();
    EObject relatedPart = CsServices.getService().getRelatedPart(context_p);

    if (relatedPart != null) {
      for (ComponentExchange exchange : getRelatedComponentExchanges(relatedPart)) {
        for (ComponentExchangeCategory value : exchange.getCategories()) {
          Collection<? extends EObject> sourceParts = ComponentExchangeExt.getSourcePartsAndEntities(exchange);
          Collection<? extends EObject> targetParts = ComponentExchangeExt.getTargetPartsAndEntities(exchange);
          if (sourceParts.contains(relatedPart)) {
            for (EObject related : targetParts) {
              result.put(value, related);
            }
          } else if (targetParts.contains(relatedPart)) {
            for (EObject related : sourceParts) {
              result.put(value, related);
            }
          }
        }
      }
    }
    return result;
  }

  public HashMapSet<EObject, EObject> getABShowHidePhysicalCategoriesInitialSelection(DSemanticDecorator context_p) {
    HashMapSet<EObject, EObject> scope = getABShowHidePhysicalCategoriesScope(context_p);
    HashMapSet<EObject, EObject> result = new HashMapSet<EObject, EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(context_p);
    EdgeMapping edgeMapping = getMappingABPhysicalCategory(diagram);
    DDiagramContents context = new DDiagramContents(diagram);

    DSemanticDecorator sourcePartView = CsServices.getService().getRelatedPartView(context_p);

    for (EObject key : scope.keySet()) {
      for (EObject targetPart : scope.get(key)) {
        for (DDiagramElement elementView : context.getDiagramElements(key, edgeMapping)) {
          if (elementView instanceof DEdge) {
            DEdge ve = (DEdge) elementView;
            DSemanticDecorator edgeSourcePartView = CsServices.getService().getRelatedPartView((DSemanticDecorator) ve.getSourceNode());
            DSemanticDecorator edgeTargetPartView = CsServices.getService().getRelatedPartView((DSemanticDecorator) ve.getTargetNode());
            EObject edgeSourcePart = CsServices.getService().getRelatedPart((DSemanticDecorator) ve.getSourceNode());
            EObject edgeTargetPart = CsServices.getService().getRelatedPart((DSemanticDecorator) ve.getTargetNode());

            boolean related = false;
            if (sourcePartView != null) {
              if (sourcePartView.equals(edgeSourcePartView) && targetPart.equals(edgeTargetPart)) {
                related = true;

              } else if (sourcePartView.equals(edgeTargetPartView) && targetPart.equals(edgeSourcePart)) {
                related = true;
              }
            }

            if (related) {
              result.put(key, targetPart);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Retrive the initial selection of displayed category for the given source view
   * @param context_p
   * @return
   */
  public HashMapSet<EObject, EObject> getABShowHideComponentCategoriesInitialSelection(DSemanticDecorator context_p) {
    HashMapSet<EObject, EObject> scope = getABShowHideComponentCategoriesScope(context_p);
    HashMapSet<EObject, EObject> result = new HashMapSet<EObject, EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(context_p);
    EdgeMapping edgeMapping = getMappingABComponentCategory(diagram);
    DDiagramContents context = new DDiagramContents(diagram);

    DSemanticDecorator sourcePartView = CsServices.getService().getRelatedPartView(context_p);

    for (EObject key : scope.keySet()) {
      for (EObject targetPart : scope.get(key)) {
        for (DDiagramElement elementView : context.getDiagramElements(key, edgeMapping)) {
          if (elementView instanceof DEdge) {
            DEdge ve = (DEdge) elementView;
            DSemanticDecorator edgeSourcePartView = CsServices.getService().getRelatedPartView((DSemanticDecorator) ve.getSourceNode());
            DSemanticDecorator edgeTargetPartView = CsServices.getService().getRelatedPartView((DSemanticDecorator) ve.getTargetNode());
            EObject edgeSourcePart = CsServices.getService().getRelatedPart((DSemanticDecorator) ve.getSourceNode());
            EObject edgeTargetPart = CsServices.getService().getRelatedPart((DSemanticDecorator) ve.getTargetNode());

            boolean related = false;
            if (sourcePartView != null) {
              if (sourcePartView.equals(edgeSourcePartView) && targetPart.equals(edgeTargetPart)) {
                related = true;

              } else if (sourcePartView.equals(edgeTargetPartView) && targetPart.equals(edgeSourcePart)) {
                related = true;
              }
            }

            if (related) {
              result.put(key, targetPart);
            }
          }
        }
      }
    }
    return result;
  }

  public Collection<EObject> getABSwitchPhysicalCategoriesScope(DSemanticDecorator context_p) {
    if (context_p instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<EObject>();
      DDiagramContents context = new DDiagramContents((DDiagram) context_p);
      Iterable<DDiagramElement> diagramElements = context.getDiagramElements(FaServices.getFaServices().getMappingABPhysicalLink(context.getDDiagram()));
      for (DDiagramElement element : diagramElements) {
        if ((element.getTarget() != null) && (element.getTarget() instanceof PhysicalLink)) {
          values.addAll(((PhysicalLink) element.getTarget()).getCategories());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> scope = getABShowHidePhysicalCategoriesScope(context_p);
    return scope.keySet();
  }

  /**
   * Retrieve a map<ExchangeCategory, Collection<Part>> of available category to display from the given source view
   * @param context_p
   * @return
   */
  public Collection<EObject> getABSwitchComponentCategoriesScope(DSemanticDecorator context_p) {
    if (context_p instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<EObject>();
      DDiagramContents context = new DDiagramContents((DDiagram) context_p);
      for (DDiagramElement element : context.getDiagramElements(FaServices.getFaServices().getMappingABConnection(context.getDDiagram()))) {
        if ((element.getTarget() != null) && (element.getTarget() instanceof ComponentExchange)) {
          values.addAll(((ComponentExchange) element.getTarget()).getCategories());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> scope = getABShowHideComponentCategoriesScope(context_p);
    return scope.keySet();

  }

  public Collection<EObject> getABSwitchPhysicalCategoriesInitialSelection(DSemanticDecorator context_p) {
    if (context_p instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<EObject>();
      DDiagramContents context = new DDiagramContents((DDiagram) context_p);
      for (DDiagramElement element : context.getDiagramElements(getMappingABPhysicalCategory(context.getDDiagram()))) {
        if ((element.getTarget() != null) && (element.getTarget() instanceof CapellaElement)) {
          values.add(element.getTarget());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> result = getABShowHidePhysicalCategoriesInitialSelection(context_p);
    return result.keySet();
  }

  /**
   * Retrive the initial selection of displayed category for the given source view
   * @param context_p
   * @return
   */
  public Collection<EObject> getABSwitchComponentCategoriesInitialSelection(DSemanticDecorator context_p) {
    if (context_p instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<EObject>();
      DDiagramContents context = new DDiagramContents((DDiagram) context_p);
      for (DDiagramElement element : context.getDiagramElements(getMappingABComponentCategory(context.getDDiagram()))) {
        if ((element.getTarget() != null) && (element.getTarget() instanceof CapellaElement)) {
          values.add(element.getTarget());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> result = getABShowHideComponentCategoriesInitialSelection(context_p);
    return result.keySet();
  }

  /**
   * Retrieve available sources for the given category (since we display ports of category, we return itself)
   * @param context_p
   * @return
   */
  public Collection<EObject> getComponentCategorySources(EObject context_p) {
    return Collections.singletonList(context_p);
  }

  /**
   * Retrieve available targets for the given category (since we display ports of category, we return itself)
   * @param context_p
   * @return
   */
  public Collection<EObject> getComponentCategoryTargets(EObject context_p) {
    return Collections.singletonList(context_p);
  }

  /**
   * Retrieve available sources for the given category (since we display ports of category, we return itself)
   * @param context_p
   * @return
   */
  public Collection<EObject> getPhysicalCategorySources(EObject context_p) {
    return Collections.singletonList(context_p);
  }

  /**
   * Retrieve available targets for the given category (since we display ports of category, we return itself)
   * @param context_p
   * @return
   */
  public Collection<EObject> getPhysicalCategoryTargets(EObject context_p) {
    return Collections.singletonList(context_p);
  }

  public boolean isABComponentCategoryPortIsA(EObject context_p, DSemanticDecorator containerView_p, ComponentPortKind kind_p, OrientationPortKind orientation_p) {
    if ((containerView_p != null) && (containerView_p instanceof AbstractDNode)) {
      EObject sourcePart = CsServices.getService().getRelatedPart(containerView_p);
      // retrieve all CE for the related category
      for (ComponentExchange exchange : getRelatedComponentExchanges(sourcePart)) {
        if (exchange.getCategories().contains(context_p)) {
          Port port;
          if (ComponentExchangeExt.getSourceParts(exchange).contains(sourcePart)) {
            port = ComponentExchangeExt.getSourcePort(exchange);
          } else {
            port = ComponentExchangeExt.getTargetPort(exchange);
          }

          if ((port != null) && (port instanceof ComponentPort)) {
            ComponentPort cp = (ComponentPort) port;
            if (!((cp.getKind() == kind_p) && ((cp.getOrientation() == orientation_p) || (OrientationPortKind.UNSET == orientation_p)))) {
              return false;
            }
          }
        }
      }
      return true;
    }
    return false;
  }

  public boolean isABComponentCategoryPortStandard(EObject context_p, DSemanticDecorator containerView_p) {
    return isABComponentCategoryPortIsA(context_p, containerView_p, ComponentPortKind.STANDARD, OrientationPortKind.UNSET);
  }

  public boolean isABComponentCategoryPortIn(EObject context_p, DSemanticDecorator containerView_p) {
    return isABComponentCategoryPortIsA(context_p, containerView_p, ComponentPortKind.FLOW, OrientationPortKind.IN);
  }

  public boolean isABComponentCategoryPortOut(EObject context_p, DSemanticDecorator containerView_p) {
    return isABComponentCategoryPortIsA(context_p, containerView_p, ComponentPortKind.FLOW, OrientationPortKind.OUT);
  }

  public boolean isABComponentCategoryPortInOut(EObject context_p, DSemanticDecorator containerView_p) {
    return isABComponentCategoryPortIsA(context_p, containerView_p, ComponentPortKind.FLOW, OrientationPortKind.INOUT);
  }

  /**
   * Returns true if an arrow should be displayed onto source of category link
   * @param context_p
   * @return
   */
  public boolean isABComponentCategorySourceOriented(DSemanticDecorator context_p) {
    boolean result = true;
    EObject target = context_p.getTarget();
    if (context_p instanceof DEdge) {
      DEdge edge = (DEdge) context_p;
      DSemanticDecorator sourceNode = (DSemanticDecorator) edge.getSourceNode();
      DSemanticDecorator targetNode = (DSemanticDecorator) edge.getTargetNode();
      EObject sourcePart = CsServices.getService().getRelatedPart(sourceNode);
      EObject targetPart = CsServices.getService().getRelatedPart(targetNode);

      if (target instanceof ComponentExchangeCategory) {
        ComponentExchangeCategory property = (ComponentExchangeCategory) target;

        for (EObject ce : property.getExchanges()) {
          if (ce instanceof ComponentExchange) {
            ComponentExchange exchange = (ComponentExchange) ce;
            Collection<? extends EObject> sourceParts = ComponentExchangeExt.getSourcePartsAndEntities(exchange);
            Collection<? extends EObject> targetParts = ComponentExchangeExt.getTargetPartsAndEntities(exchange);
            Collection<EObject> unionParts = new HashSet<EObject>();
            unionParts.addAll(sourceParts);
            unionParts.addAll(targetParts);

            if (unionParts.contains(sourcePart) && unionParts.contains(targetPart)) {
              if (exchange.isOriented()) {
                if (!(ComponentExchangeExt.getSourcePartsAndEntities(exchange).contains(targetPart) && ComponentExchangeExt.getTargetPartsAndEntities(exchange)
                    .contains(sourcePart))) {
                  result = false;
                  break;
                }
              } else {
                result = false;
                break;
              }
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * Returns true if an arrow should be displayed onto target of category link
   * @param context_p
   * @return
   */
  public boolean isABComponentCategoryTargetOriented(DSemanticDecorator context_p) {
    boolean result = true;
    EObject target = context_p.getTarget();
    if (context_p instanceof DEdge) {
      DEdge edge = (DEdge) context_p;
      DSemanticDecorator sourceNode = (DSemanticDecorator) edge.getSourceNode();
      DSemanticDecorator targetNode = (DSemanticDecorator) edge.getTargetNode();
      EObject sourcePart = CsServices.getService().getRelatedPart(sourceNode);
      EObject targetPart = CsServices.getService().getRelatedPart(targetNode);

      if (target instanceof ComponentExchangeCategory) {
        ComponentExchangeCategory property = (ComponentExchangeCategory) target;

        for (EObject ce : property.getExchanges()) {
          if (ce instanceof ComponentExchange) {
            ComponentExchange exchange = (ComponentExchange) ce;
            Collection<? extends EObject> sourceParts = ComponentExchangeExt.getSourcePartsAndEntities(exchange);
            Collection<? extends EObject> targetParts = ComponentExchangeExt.getTargetPartsAndEntities(exchange);
            Collection<EObject> unionParts = new HashSet<EObject>();
            unionParts.addAll(sourceParts);
            unionParts.addAll(targetParts);

            if (unionParts.contains(sourcePart) && unionParts.contains(targetPart)) {
              if (exchange.isOriented()) {
                if (!(ComponentExchangeExt.getSourcePartsAndEntities(exchange).contains(sourcePart) && ComponentExchangeExt.getTargetPartsAndEntities(exchange)
                    .contains(targetPart))) {
                  result = false;
                  break;
                }
              } else {
                result = false;
                break;
              }
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * @param context_p
   */
  public void updateABComponentCategories(DDiagramContents context_p) {
    Collection<DEdge> toRemoveEdges = new HashSet<DEdge>();
    Collection<AbstractDNode> toRemoveNodes = new HashSet<AbstractDNode>();
    Collection<AbstractDNode> toHideNodes = new HashSet<AbstractDNode>();

    EdgeMapping edgeMapping = getMappingABComponentCategory(context_p.getDDiagram());
    NodeMapping nodeMapping = getMappingABComponentCategoryPin(context_p.getDDiagram());

    // Retrieve all invalid edges to be removed
    if (edgeMapping != null) {
      for (DDiagramElement element : context_p.getDiagramElements(edgeMapping)) {
        if (!(element instanceof DEdge)) {
          continue;
        }
        DEdge edge = (DEdge) element;

        boolean isValidEdge =
            isValidABComponentCategoryEdge((ComponentExchangeCategory) element.getTarget(), (DSemanticDecorator) edge.getSourceNode(),
                (DSemanticDecorator) edge.getTargetNode());
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
   * Remove invalid links and physical categories
   * @param context_p
   */
  public void updateABPhysicalCategories(DDiagramContents context_p) {
    Collection<DEdge> toRemoveEdges = new HashSet<DEdge>();
    Collection<AbstractDNode> toRemoveNodes = new HashSet<AbstractDNode>();
    Collection<AbstractDNode> toHideNodes = new HashSet<AbstractDNode>();

    EdgeMapping edgeMapping = getMappingABPhysicalCategory(context_p.getDDiagram());
    List<NodeMapping> nodeMappings = getMappingABPhysicalCategoryPin(context_p.getDDiagram());

    if (edgeMapping != null) {
      for (DDiagramElement element : context_p.getDiagramElements(edgeMapping)) {
        if (!(element instanceof DEdge)) {
          continue;
        }
        DEdge edge = (DEdge) element;

        boolean isValidEdge =
            isValidABPhysicalCategoryEdge((PhysicalLinkCategory) element.getTarget(), (DSemanticDecorator) edge.getSourceNode(),
                (DSemanticDecorator) edge.getTargetNode());
        if (!isValidEdge) {
          toRemoveEdges.add(edge);
        }
      }
    }

    if (!nodeMappings.isEmpty()) {
      for (DDiagramElement element : context_p.getDiagramElements(nodeMappings)) {
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

    nodeMappings = FaServices.getFaServices().getMappingABPorts(context_p.getDDiagram());
    if (!nodeMappings.isEmpty()) {
      for (DDiagramElement element : context_p.getDiagramElements(nodeMappings)) {
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
   * Retrieve a displayable text for the given category
   * @param property
   * @return
   */
  public String computeComponentCategoryLabel(ComponentExchangeCategory property) {
    return property.getName();
  }

  /**
   * Retrieve a displayable text for the given category
   * @param property
   * @return
   */
  public String computePhysicalCategoryLabel(PhysicalLinkCategory property) {
    return property.getName();
  }

  /**
   * Create a component exchange in an architecture blank diagram. Create port if selected views are not targeting port
   * @param context_p
   * @param sourceViewk
   * @param targetView_p
   * @return
   */
  public EObject createABComponentPortAllocation(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    EObject sourceTarget = sourceView_p.getTarget();
    EObject targetTarget = targetView_p.getTarget();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);

    EdgeTarget nodeSource = null;
    EdgeTarget nodeTarget = null;

    ComponentPortAllocation exchange = null;

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
    PhysicalPort sourcePort = null;
    if (sourceTarget instanceof PhysicalPort) {
      sourcePort = (PhysicalPort) sourceTarget;
      nodeSource = (EdgeTarget) sourceView_p;
    }

    // Create or retrieve targetPort
    ComponentPort targetPort = null;
    if (targetTarget instanceof ComponentPort) {
      targetPort = (ComponentPort) targetTarget;
      nodeTarget = (EdgeTarget) targetView_p;
    }

    // Create component exchange
    exchange = FaFactory.eINSTANCE.createComponentPortAllocation();

    // Set source
    if (CsServices.getService().isMultipartMode((ModelElement) sourceTarget)) {
      ComponentPortAllocationEnd end = FaFactory.eINSTANCE.createComponentPortAllocationEnd();
      end.setPart(sourcePart);
      end.setPort(sourcePort);
      exchange.setSourceElement(end);
      exchange.getOwnedComponentPortAllocationEnds().add(end);
      CapellaServices.getService().creationService(end);
    } else {
      exchange.setSourceElement(sourcePort);
    }

    // Set target
    if (CsServices.getService().isMultipartMode((ModelElement) sourceTarget)) {
      ComponentPortAllocationEnd end = FaFactory.eINSTANCE.createComponentPortAllocationEnd();
      end.setPart(targetPart);
      end.setPort(targetPort);
      exchange.setTargetElement(end);
      exchange.getOwnedComponentPortAllocationEnds().add(end);
      CapellaServices.getService().creationService(end);
    } else {
      exchange.setTargetElement(targetPort);
    }

    // Attach to parent
    ComponentPortAllocationExt.attachToDefaultContainer(exchange);

    CapellaServices.getService().creationService(exchange);
    DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingABComponentPortAllocation(diagram), nodeSource, nodeTarget, exchange);
    return context_p;
  }

  /**
   * Retrieve the edge mapping name for the given diagram
   * @param diagram_p
   * @return
   */
  public EdgeMapping getMappingABComponentCategory(DDiagram diagram_p) {
    String mappingName = null;

    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_COMPONENT_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_COMPONENT_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_COMPONENT_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_COMPONENT_CATEGORY_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  /**
   * Retrieve the edge mapping name for the given diagram
   * @param diagram_p
   * @return
   */
  public EdgeMapping getMappingABPhysicalCategory(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_PHYSICAL_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_PHYSICAL_CATEGORY_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_CATEGORY_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  /**
   * Retrieve the pin view mapping name for the given diagram
   * @param diagram_p
   * @return
   */
  public NodeMapping getMappingABComponentCategoryPin(DDiagram diagram_p) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
  }

  /**
   * Retrieve the pin view mapping names for the given diagram
   * @param diagram_p
   * @return
   */
  public List<NodeMapping> getMappingABPhysicalCategoryPin(DDiagram diagram_p) {
    List<String> mappingNames = new ArrayList<String>();
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.SAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME);
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.LAB_PHYSICAL_CATEGORY_PIN_NAME);
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.PAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME);
    }
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingNames);
  }

  /**
   * Retrieve the pin view mapping name for the given diagram and the semantic element
   * @param diagram_p
   * @return
   */
  public NodeMapping getMappingABPhysicalCategoryPin(DDiagram diagram_p, EObject semantic) {
    String mappingName = null;
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_PHYSICAL_CATEGORY_PIN_NAME;
    }
    if (diagram_p.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram_p, mappingName);
  }

}
