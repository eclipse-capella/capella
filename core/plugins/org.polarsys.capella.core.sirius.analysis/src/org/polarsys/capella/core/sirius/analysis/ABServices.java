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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
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
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.model.helpers.ComponentPortAllocationExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABComponent;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABComponentCategory;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABComponentExchange;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABComponentPortAllocation;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABPhysicalCategory;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABPhysicalLink;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABPortAllocation;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideABRole;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideFunction;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideFunctionalExchange;
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

  public boolean isValidDndABFunctionPort(EObject element, DSemanticDecorator newContainer) {
    return DFServices.getService().isValidDndDFFunctionPort(element, newContainer);
  }

  public boolean isValidABComponentPort(EObject context, DSemanticDecorator containerView) {
    // check architecture level
    // if system do not allow inner links
    return !((containerView == null) || (containerView instanceof DDiagram));
  }

  public boolean isValidABCreationPortAllocation(EObject context, DSemanticDecorator sourceView) {
    if (sourceView == null) {
      return false;
    }
    EObject source = sourceView.getTarget();
    return ((source instanceof ComponentPort) || (source instanceof PhysicalPort));
  }

  public boolean isValidABCreationPortAllocation(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    if ((sourceView == null) || (targetView == null)) {
      return false;
    }

    EObject source = sourceView.getTarget();
    EObject target = targetView.getTarget();

    if (!(((source instanceof PhysicalPort) || (source instanceof ComponentPort))
        && (target instanceof FunctionPort))) {
      return false;
    }

    // target port is not owned by source.component
    if (!EcoreUtil.isAncestor(sourceView.eContainer(), targetView)) {
      return false;
    }

    return true;

  }

  public boolean isValidABCreationComponentPortAllocation(EObject context, DSemanticDecorator sourceView) {
    if (sourceView == null) {
      return false;
    }
    EObject source = sourceView.getTarget();
    return (source instanceof PhysicalPort);
  }

  public boolean isValidABCreationComponentPortAllocation(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    if ((sourceView == null) || (targetView == null)) {
      return false;
    }

    EObject source = sourceView.getTarget();
    EObject target = targetView.getTarget();

    if (!((source instanceof PhysicalPort) && (target instanceof ComponentPort))) {
      return false;
    }

    // target port is not owned by source.component
    EObject parentTarget = targetView.eContainer();
    EObject parentSource = sourceView.eContainer();

    if ((parentTarget == null) || (parentSource == null)) {
      return false;
    }

    return true;

  }

  /**
   * Perform a dnd of a component.
   * 
   * @param pcMoved
   *          the given namedElement
   * @param oldContainer
   *          the given namedElement
   * @param newContainer
   *          the given namedElement
   * @return pcMoved
   */
  public EObject dndABComponent(NamedElement pcMoved, NamedElement oldContainer, NamedElement newContainer) {
    return _dndABComponent(pcMoved, newContainer, newContainer);
  }

  /**
   * Perform a dnd of a component.
   * 
   * @param pcMoved
   *          the given namedElement
   * @param targetContainerView
   *          the new target
   * @return pcMoved
   */
  public EObject dndABComponent(NamedElement pcMoved, DSemanticDecorator targetContainerView) {

    EObject newOwner = null;
    Component component = null;

    if (pcMoved instanceof Part) {
      component = (Component) ((Part) pcMoved).getType();
    } else if (pcMoved instanceof Component) {
      component = (Component) pcMoved;
    }

    if (component != null && component.isActor()) {
      newOwner = CsServices.getService().getABActorTarget(targetContainerView);
    } else {
      newOwner = CsServices.getService().getABTarget(targetContainerView);
    }

    return _dndABComponent(pcMoved, newOwner, (NamedElement) targetContainerView.getTarget());
  }

  private EObject _dndABComponent(NamedElement pcMoved, EObject newOwner, NamedElement newContainer) {
    if (newOwner instanceof Part) {
      newOwner = CsServices.getService().getComponentType((Part) newContainer);
    }

    Component component = null;
    if (newOwner instanceof ComponentPkg) {
      ComponentPkg newComponentPkg = (ComponentPkg) newOwner;
      component = null;

      // Move part in the new container
      if (pcMoved instanceof Part) {
        newComponentPkg.getOwnedParts().add((Part) pcMoved);
        component = (Component) ((Part) pcMoved).getType();
      } else if (pcMoved instanceof Component) {
        for (Part part : getCache(ComponentExt::getRepresentingParts, (Component) pcMoved)) {
          if (!newComponentPkg.equals(part.eContainer())) {
            newComponentPkg.getOwnedParts().add(part);
          }
        }
        component = (Component) pcMoved;
      } else {
        return pcMoved;
      }
      // move component type (avoid move if it is located in its part)
      if (!CsPackage.Literals.PART__OWNED_ABSTRACT_TYPE.equals(component.eContainingFeature())) {
        FaServices.getFaServices().moveComponent(component, newComponentPkg);
      }

    } else if (newOwner instanceof Component) {
      Component newComponent = (Component) newOwner;
      component = null;

      // Move part in the new container
      if (pcMoved instanceof Part) {
        newComponent.getOwnedFeatures().add((Part) pcMoved);
        component = (Component) ((Part) pcMoved).getType();
      } else if (pcMoved instanceof Component) {
        for (Part part : getCache(ComponentExt::getRepresentingParts, (Component) pcMoved)) {
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
    }
    if (component != null) {
      // for all exchanges related to owned childs, move them to the ancestor (copied from odesign specification)
      List<Component> listChild = CapellaServices.getService().getAllDescendants(component);
      listChild.add(component);
      for (Component child : listChild) {
        if (child instanceof Component) {
          Component componentChild = child;
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

  public EObject dndCBComponent(NamedElement pcMoved, NamedElement oldContainer, NamedElement newContainer) {
    return dndABComponent(pcMoved, oldContainer, newContainer);
  }

  public List<Component> getCBComponentSemanticCandidates(EObject container) {
    if (container instanceof ComponentPkg) {
      return ComponentPkgExt.getAllSubDefinedComponents((ComponentPkg) container);
    } else if (container instanceof Component) {
      Component containerComponent = (Component) container;
      List<Component> allSubDefinedComponents = ComponentExt.getAllSubDefinedComponents(containerComponent);

      BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(containerComponent);
      Component systemComponent = rootBlockArchitecture.getSystem();
      if (containerComponent != systemComponent) {
        allSubDefinedComponents.add(containerComponent);
      }

      return allSubDefinedComponents;
    }
    return Collections.emptyList();
  }

  public boolean isValidCreationCBNodePC(DSemanticDecorator decorator) {
    return ABServices.getService().isValidCreationABNodePC(decorator);
  }

  public boolean isValidCreationCBBehaviorPC(DSemanticDecorator decorator) {
    return ABServices.getService().isValidCreationABBehaviorPC(decorator);
  }

  public boolean isValidCreationCBComponent(DSemanticDecorator decorator) {
    return ABServices.getService().isValidCreationABComponent(decorator);
  }

  public boolean isValidCreationCBActor(DSemanticDecorator decorator) {
    return ABServices.getService().isValidCreationABActor(decorator);
  }

  public List<Component> getCBLinkSemanticCandidates(Component component) {
    List<Component> returnedList = new ArrayList<>();
    if (CsServices.getService().isMultipartMode(component)) {
      return returnedList;
    }
    for (Component cpnt : ComponentExt.getDirectParents(component)) {
      if (!returnedList.contains(cpnt)) {
        returnedList.add(cpnt);
      }
    }
    return returnedList;
  }

  public List<EObject> getCBMultipartLinkSemanticCandidates(ModelElement element) {
    List<EObject> result = new ArrayList<>();
    if (!(element instanceof Component) || !CsServices.getService().isMultipartMode(element)) {
      return result;
    }
    for (Component aComponent : CsServices.getService().getSubDefinedByUsedComponents((Component) element)) {
      for (Part part : ComponentExt.getSubParts(aComponent)) {
        if (!result.contains(part)) {
          result.add(part);
        }
      }
    }

    return result;
  }

  /**
   * Performs semantic operation for a reconnect of source from a component exchange edge
   * 
   * @param componentExchange
   * @param edge
   * @param oldNode
   * @param newNode
   */
  public EObject reconnectABComponentExchangeSource(EObject componentExchange, DSemanticDecorator edge,
      DSemanticDecorator oldNode, DSemanticDecorator newNode) {
    if (edge instanceof DEdge) {
      if (componentExchange instanceof ComponentExchange) {
        reconnectABComponentExchange((ComponentExchange) componentExchange,
            ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE, (DEdge) edge, oldNode, newNode);
      }
    }
    return componentExchange;
  }

  /**
   * Performs semantic operation for a reconnect of target from a component exchange edge
   * 
   * @param componentExchange
   * @param edge
   * @param oldNode
   * @param newNode
   */
  public EObject reconnectABComponentExchangeTarget(EObject componentExchange, DSemanticDecorator edge,
      DSemanticDecorator oldNode, DSemanticDecorator newNode) {
    if (edge instanceof DEdge) {
      if (componentExchange instanceof ComponentExchange) {
        reconnectABComponentExchange((ComponentExchange) componentExchange,
            ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET, (DEdge) edge, oldNode, newNode);
      }
    }
    return componentExchange;
  }

  /**
   * Performs semantic operation for a reconnect of a component exchange edge
   * 
   * @param componentExchange
   * @param edge
   * @param oldNode
   * @param newNode
   */
  public void reconnectABComponentExchange(ComponentExchange componentExchange, EReference bound, DEdge edge,
      DSemanticDecorator oldNode, DSemanticDecorator newNode) {
    EObject relatedPart = CsServices.getService().getRelatedPart(newNode);

    if (!(componentExchange instanceof CommunicationMean) && CsServices.getService().isMultipartMode(componentExchange)
        && (newNode.getTarget() instanceof Port) && (relatedPart instanceof Part)) {
      ComponentExchangeEnd end = null;
      Object boundValue = componentExchange.eGet(bound);
      if (boundValue instanceof ComponentExchangeEnd) {
        end = (ComponentExchangeEnd) boundValue;
      }

      if (end == null) {
        end = FaFactory.eINSTANCE.createComponentExchangeEnd();
        componentExchange.getOwnedComponentExchangeEnds().add(end);
      }

      end.setPart((Part) relatedPart);
      end.setPort((Port) newNode.getTarget());
      componentExchange.eSet(bound, end);

    } else {
      Object boundValue = componentExchange.eGet(bound);
      if (boundValue instanceof ComponentExchangeEnd) {
        CapellaServices.getService().removeElement((ComponentExchangeEnd) boundValue);
      }
      componentExchange.eSet(bound, newNode.getTarget());
    }

    ComponentExchangeExt.attachToDefaultContainer(componentExchange);
  }

  /**
   * Returns whether the source of componentExchange can be replaced from given source to given target
   * 
   * @param exchange
   * @param source
   * @param target
   * @return
   */
  public boolean isValidABReconnectComponentExchangeSource(ComponentExchange exchange, EObject source, EObject target) {
    if (exchange instanceof CommunicationMean) {
      return (source instanceof Component) && (target instanceof Component);
    }

    if (!CsServices.getService().isMultipartMode(exchange)) {
      if (target instanceof Part) {
        return false;
      }
    }

    if ((source instanceof ComponentPort) && (target instanceof ComponentPort)) {
      return PortExt.haveSameOrientation((ComponentPort) source, (ComponentPort) target);
    }
    if ((source instanceof ComponentPort) && (target instanceof PhysicalPort)) {
      return false;
    }

    return true;
  }

  /**
   * Returns whether the target of componentExchange can be replaced from given source to given target
   * 
   * @param exchange
   * @param source
   * @param target
   * @return
   */
  public boolean isValidABReconnectComponentExchangeTarget(ComponentExchange exchange, EObject source, EObject target) {
    return isValidABReconnectComponentExchangeSource(exchange, source, target);
  }

  /**
   * Performs semantic operation for a reconnect of source from a physical link edge
   * 
   * @param physicalLink
   * @param edge
   * @param oldNode
   * @param newNode
   */
  public EObject reconnectABPhysicalLinkSource(EObject physicalLink, DSemanticDecorator edge,
      DSemanticDecorator oldNode, DSemanticDecorator newNode) {
    if (edge instanceof DEdge) {
      if (physicalLink instanceof PhysicalLink) {
        reconnectABPhysicalLink((PhysicalLink) physicalLink, (DEdge) edge, oldNode, newNode);
      }
    }
    return physicalLink;
  }

  /**
   * Performs semantic operation for a reconnect of target from a physical link edge
   * 
   * @param physicalLink
   * @param edge
   * @param oldNode
   * @param newNode
   */
  public EObject reconnectABPhysicalLinkTarget(EObject physicalLink, DSemanticDecorator edge,
      DSemanticDecorator oldNode, DSemanticDecorator newNode) {
    if (edge instanceof DEdge) {
      if (physicalLink instanceof PhysicalLink) {
        reconnectABPhysicalLink((PhysicalLink) physicalLink, (DEdge) edge, oldNode, newNode);
      }
    }
    return physicalLink;
  }

  /**
   * Performs semantic operation for a reconnect of a physical link edge
   * 
   * @param physicalLink
   * @param edge
   * @param oldNode
   * @param newNode
   */
  public void reconnectABPhysicalLink(PhysicalLink physicalLink, DEdge edge, DSemanticDecorator oldNode,
      DSemanticDecorator newNode) {

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

    if (CsServices.getService().isMultipartMode(physicalLink) || (physicalLink.getOwnedPhysicalLinkEnds().size() > 0)) {
      EObject relatedPart = CsServices.getService().getRelatedPart(newNode);
      PhysicalLinkEnd end = null;
      for (PhysicalLinkEnd end1 : physicalLink.getOwnedPhysicalLinkEnds()) {
        if (((oldPort == null) && (end1.getPort() == null)) || ((oldPort != null) && oldPort.equals(end1.getPort()))) {
          end = end1;
          break;
        }
      }
      if (end == null) {
        end = CsFactory.eINSTANCE.createPhysicalLinkEnd();
        physicalLink.getOwnedPhysicalLinkEnds().add(end);
        physicalLink.getLinkEnds().add(end);
      }
      end.setPart((Part) relatedPart);
      end.setPort((PhysicalPort) newNode.getTarget());

    } else {
      int index = physicalLink.getLinkEnds().indexOf(oldPort);
      physicalLink.getLinkEnds().add(index, newPort);
      physicalLink.getLinkEnds().remove(oldPort);
    }

    org.polarsys.capella.core.model.helpers.PhysicalLinkExt.attachToDefaultContainer(physicalLink);
  }

  /**
   * Returns whether the source of physicalLink can be replaced from given source to given target
   * 
   * @param exchange
   * @param source
   * @param target
   * @return
   */
  public boolean isValidABReconnectPhysicalLinkSource(PhysicalLink exchange, EObject source, EObject target) {
    if (!CsServices.getService().isMultipartMode(exchange)) {
      if (target instanceof Part) {
        return false;
      }
    }
    if ((source instanceof ComponentPort) && (target instanceof ComponentPort)) {
      return true;
    }
    if ((source instanceof PhysicalPort) && (target instanceof ComponentPort)) {
      return false;
    }

    return true;
  }

  /**
   * Returns whether the target of physicalLink can be replaced from given source to given target
   * 
   * @param exchange
   * @param source
   * @param target
   * @return
   */
  public boolean isValidABReconnectPhysicalLinkTarget(PhysicalLink exchange, EObject source, EObject target) {
    return isValidABReconnectPhysicalLinkSource(exchange, source, target);
  }

  /**
   * Returns whether part source can be moved into target
   * 
   * @param source
   * @param target
   * @return
   */
  public boolean isValidDndComponent(Part source, Component target) {
    return PartExt.canMoveInto(source, target);
  }

  /**
   * Returns whether part source can be moved into target part
   * 
   * @param source
   * @param target
   * @return
   */
  public boolean isValidDndComponent(Part source, Part target) {
    Collection<Part> parts = ComponentExt.getPartAncestors(target);
    if (parts.contains(source)) {
      return false;
    }
    if (!ComponentExt.canMoveInto((Component) source.getAbstractType(), (Component) target.getAbstractType(),
        PartExt.isDeployed(source))) {
      return false;
    }
    return true;
  }

  /**
   * Returns whether part source can be moved into target component package
   * 
   * @param sourcePart
   * @param targetPkg
   * @return whether part source can be moved into target component package
   */
  public boolean isValidDndComponent(Part sourcePart, ComponentPkg targetPkg) {
    return PartExt.canMoveInto(sourcePart, targetPkg);
  }

  /**
   * Returns whether component source can be moved into target component package
   * 
   * @param sourceComponent
   * @param targetPkg
   * @return whether component source can be moved into target component package
   */
  public boolean isValidDndComponent(Component sourceComponent, ComponentPkg targetPkg) {
    return ComponentExt.canMoveInto(sourceComponent, targetPkg);
  }

  /**
   * Returns whether component source can be moved into target component
   * 
   * @param source
   * @param target
   * @return
   */
  public boolean isValidDndComponent(Component source, Component target) {
    return ComponentExt.canMoveInto(source, target);
  }

  /**
   * Returns whether the given component can be dropped into the target element view
   */
  public boolean isValidDndABComponent(Component semanticObjectToDrop, EObject targetContainerView) {

    List<Part> representingParts = semanticObjectToDrop.getRepresentingParts();

    if (representingParts.isEmpty()) {
      return false;
    }

    Part part = representingParts.get(0);
    return isValidDndABComponent(part, targetContainerView);
  }

  /**
   * Returns whether the given part can be dropped into the target element view
   */
  public boolean isValidDndABComponent(Part semanticObjectToDrop, EObject targetContainerView) {
    EObject context = null;

    Component component = (Component) semanticObjectToDrop.getAbstractType();
    if (component.isActor()) {
      context = CsServices.getService().getABActorTarget((DSemanticDecorator) targetContainerView);
      return isValidCreationABActor(context);
    }

    context = CsServices.getService().getABTarget((DSemanticDecorator) targetContainerView);
    if (context instanceof BlockArchitecture) {
      return false;

    } else if (context instanceof Component) {
      return isValidDndComponent(semanticObjectToDrop, (Component) context);
    } else if (context instanceof Part) {
      return isValidDndComponent(semanticObjectToDrop, (Part) context);
    } else if (context instanceof ComponentPkg) {
      return isValidDndComponent(semanticObjectToDrop, (ComponentPkg) context);
    }

    return false;
  }

  /**
   * Returns whether the given part can be drop into the target element view (ABD=Architecture Breakdown)
   */
  public boolean isValidDndCBComponent(Component semanticObjectToDrop, EObject targetContainerView) {
    Object target = ((DSemanticDecorator) targetContainerView).getTarget();
    if (target instanceof Component) {
      return isValidDndComponent(semanticObjectToDrop, (Component) target);
    }
    return false;
  }

  /**
   * Returns whether physical port can be D&D
   * 
   * @param source
   * @param target
   * @return
   */
  public boolean isValidDnDPhysicalPort(EObject context, DSemanticDecorator container) {
    return PhysicalServices.getService().isValidPhysicalPort(context, container.getTarget());
  }

  /**
   * Create a functional exchange between both views
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createABFunctionalExchange(EObject context, AbstractDNode sourceView, AbstractDNode targetView) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
    ActivityNode sourceTarget = FaServices.getFaServices().getRelatedActivityNode(sourceView);
    ActivityNode targetTarget = FaServices.getFaServices().getRelatedActivityNode(targetView);
    FunctionalExchange exchange = FunctionalExchangeExt.createFunctionalExchange(sourceTarget, targetTarget);
    FaServices.getFaServices().createViewFunctionalExchange(exchange, sourceView, targetView, diagram);
    CsServices.getService().setInterpreterVariable(context, "result", exchange);
    return exchange;
  }

  /**
   * Returns available states/modes to insert in given diagram
   * 
   * @param containerView
   * @return
   */
  public Collection<EObject> getABInsertStateModesScope(DSemanticDecorator containerView) {
    HashSet<State> availableStates = new HashSet<State>();
    HashSet<EObject> result = new HashSet<EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {
      // Retrieve all available states
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (AbstractFunction function : FunctionPkgExt
          .getAllAbstractFunctions(BlockArchitectureExt.getFunctionPkg(architecture))) {
        availableStates.addAll(function.getAvailableInStates());
      }

      // Retrains states to states with at least one function not displayed
      for (State state : availableStates) {
        boolean addElement = false;
        for (EObject function : getABInsertStateModesRelatedElements(state, architecture)) {
          if (function instanceof AbstractFunction) {
            if (!content.containsView(function,
                FaServices.getFaServices().getMappingABAbstractFunction((AbstractFunction) function, diagram))) {
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
   * 
   * @param containerView
   * @param elements
   */
  public void showABStateModes(DSemanticDecorator containerView, Collection<EObject> elements) {

    HashSet<EObject> functionsToShow = new HashSet<EObject>();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {
      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (EObject object : elements) {
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
   * 
   * @param mode
   * @param sourceArchitecture
   * @return
   */
  public Collection<EObject> getABInsertStateModesRelatedElements(State mode, BlockArchitecture sourceArchitecture) {
    HashSet<EObject> functionsToShow = new HashSet<EObject>();

    for (EObject element : StateMachineServices.getService().getAllFunctionsActiveInStates(sourceArchitecture, mode)) {
      BlockArchitecture targetArchitecture = BlockArchitectureExt.getRootBlockArchitecture(element);
      if (sourceArchitecture.equals(targetArchitecture)) {
        functionsToShow.add(element);
      }
    }
    return functionsToShow;
  }

  /**
   * Returns whether the tool insert states/modes is available in the given context
   * 
   * @param containerView
   * @return
   */
  public boolean isValidABInsertStateModes(DSemanticDecorator containerView) {
    return containerView instanceof DDiagram;
  }

  public Collection<EObject> getABInsertScenariosScope(DSemanticDecorator containerView) {
    HashSet<EObject> result = new HashSet<EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {

      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      AbstractCapabilityPkg pkg = BlockArchitectureExt.getAbstractCapabilityPkg(sourceArchitecture);
      for (Scenario scenario : AbstractCapabilityPkgExt.getAllScenarios(pkg)) {

        boolean addElement = false;
        for (EObject element : ContextualDiagramHelper.getService().getInsertScenariosRelatedElements(scenario,
            sourceArchitecture)) {
          if (element instanceof AbstractFunction) {
            if (!content.containsView(element,
                content.getMapping(MappingConstantsHelper.getMappingABAbstractFunction(diagram)))) {
              addElement = true;
              break;
            }
          } else if (element instanceof FunctionalExchange) {
            if (!content.containsView(element,
                content.getMapping(MappingConstantsHelper.getMappingABFunctionalExchange(diagram)))) {
              addElement = true;
              break;
            }
          } else if (element instanceof Part) {
            if (!content.containsView(element,
                content.getMapping(MappingConstantsHelper.getMappingABComponent(element, diagram)))) {
              addElement = true;
              break;
            }
          } else if (element instanceof Role) {
            if (!content.containsView(element, content.getMapping(MappingConstantsHelper.getMappingABRole(diagram)))) {
              addElement = true;
              break;
            }
          } else if (element instanceof Entity) {
            if (!content.containsView(element,
                content.getMapping(MappingConstantsHelper.getMappingABComponent(element, diagram)))) {
              addElement = true;
              break;
            }
          } else if (element instanceof ComponentExchange) {
            if (!content.containsView(element,
                content.getMapping(MappingConstantsHelper.getMappingABConnection(diagram)))) {
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
   * 
   * @param containerView
   * @param elements
   */
  public void showABScenarios(DSemanticDecorator containerView, Collection<EObject> elements) {

    Collection<EObject> partToShow = new HashSet<EObject>();
    Collection<EObject> roleToShow = new HashSet<EObject>();
    Collection<EObject> functionsToShow = new HashSet<EObject>();

    LinkedList<EObject> unallocatedFunctions = new LinkedList<EObject>();
    LinkedList<EObject> unallocatedRoles = new LinkedList<EObject>();
    Collection<EObject> exchangesToShow = new HashSet<EObject>();
    Collection<EObject> componentExchangesToShow = new HashSet<EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {

      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (EObject object : elements) {
        if (object instanceof Scenario) {
          Scenario scenario = (Scenario) object;

          for (EObject related : ContextualDiagramHelper.getService().getInsertScenariosRelatedElements(scenario,
              sourceArchitecture)) {

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
            logger.info(
                new EmbeddedMessage(NLS.bind(Messages.ABServices_UnallocatedFunctions, EObjectExt.getText(scenario)),
                    IReportManagerDefaultComponents.MODEL, unallocatedFunctions));
          }
          if (unallocatedRoles.size() > 0) {
            unallocatedRoles.addFirst(scenario);
            Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);
            logger
                .info(new EmbeddedMessage(NLS.bind(Messages.ABServices_UnallocatedRoles, EObjectExt.getText(scenario)),
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
   * @param roleToShow
   * @param content
   */
  public EObject showABRole(Collection<EObject> elements, DDiagramContents content) {
    AbstractShowHide shService = new ShowHideABRole(content);

    for (EObject element : elements) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content.getDDiagram();
  }

  /**
   * Returns whether the tool insert scenario is available in the given context
   * 
   * @param containerView
   * @return
   */
  public boolean isValidABInsertScenarios(DSemanticDecorator containerView) {
    return containerView instanceof DDiagram;
  }

  public boolean isValidInsertAppliedPV(DSemanticDecorator containerView) {
    return containerView instanceof EdgeTarget;
  }

  /**
   * Display given abstract functions
   */
  public EObject showABAbstractFunction(Collection<EObject> elements, DDiagramContents content) {

    AbstractShowHide shService = new ShowHideFunction(content);

    for (EObject element : elements) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content.getDDiagram();
  }

  /**
   * Display given component exchanges
   */
  public EObject showABComponentExchange(Collection<EObject> elements, DDiagramContents content) {

    AbstractShowHide shService = new ShowHideABComponentExchange(content);

    for (EObject element : elements) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content.getDDiagram();
  }

  /**
   * Display given physical links
   */
  public EObject showABPhysicalLink(Collection<EObject> elements, DDiagramContents content) {

    AbstractShowHide shService = new ShowHideABPhysicalLink(content);

    for (EObject element : elements) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content.getDDiagram();
  }

  /**
   * Display given component exchanges
   */
  public EObject showABComponentExchange(Collection<EObject> elements, DDiagramContents content,
      DSemanticDecorator currentElementView) {

    AbstractShowHide shService = new ShowHideABComponentExchange(content);

    for (EObject element : elements) {
      DiagramContext context = shService.new DiagramContext();
      if (currentElementView instanceof DDiagramElement) {
        context.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS,
            Collections.singletonList(currentElementView));
      }
      shService.show(element, context);
    }

    return content.getDDiagram();
  }

  /**
   * Display given functional exchanges
   */
  public EObject showABFunctionalExchange(Collection<EObject> elements, DDiagramContents content) {
    AbstractShowHide shService = new ShowHideFunctionalExchange(content);

    for (EObject element : elements) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content.getDDiagram();
  }

  /**
   * Display given components
   */
  public EObject showABComponent(Collection<EObject> components, DDiagramContents content) {

    if ((components == null) || components.isEmpty()) {
      return content.getDDiagram();
    }

    AbstractShowHide shService = new ShowHideABComponent(content);

    for (EObject component : components) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(component, context);
    }

    return content.getDDiagram();
  }

  public boolean isValidABComponentCategoryPort(EObject context, DSemanticDecorator containerView) {
    return true;
  }

  public boolean isValidABPhysicalCategoryPort(EObject context, DSemanticDecorator containerView) {
    return true;
  }

  public boolean doesCategoryExistBetweenParts(EObject category, AbstractDNode sourcePartView,
      AbstractDNode targetPartView) {
    AbstractDNode sourceCategoryPort = null;
    AbstractDNode targetCategoryPort = null;
    // Retrieve both category ports on parts
    for (AbstractDNode node : sourcePartView.getOwnedBorderedNodes()) {
      if (category.equals(node.getTarget())) {
        sourceCategoryPort = node;
        break;
      }
    }
    for (AbstractDNode node : targetPartView.getOwnedBorderedNodes()) {
      if (category.equals(node.getTarget())) {
        targetCategoryPort = node;
        break;
      }
    }
    // Check if there's a category between category ports
    if ((sourceCategoryPort != null) && (targetCategoryPort != null)) {
      for (DEdge edge : DiagramServices.getDiagramServices().getAllEdges((EdgeTarget) sourceCategoryPort)) {
        if (category.equals(edge.getTarget())) {
          if (((edge.getSourceNode() == sourceCategoryPort) && (edge.getTargetNode() == targetCategoryPort))
              || ((edge.getSourceNode() == targetCategoryPort) && (edge.getTargetNode() == sourceCategoryPort))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * 
   * @param delegationTargetPort
   * @param delegationSourcePart
   * @return whether a port delegation exists between a port and a part
   */
  public boolean doesDelegationExist(Port delegationTargetPort, Part delegationSourcePart) {
    if ((delegationTargetPort != null) && (delegationSourcePart != null)) {
      if (delegationTargetPort instanceof PhysicalPort) {
        return doesDelegationExistForPhysicalPort((PhysicalPort) delegationTargetPort, delegationSourcePart);
      } else if (delegationTargetPort instanceof ComponentPort) {
        return doesPortAllocationExist((ComponentPort) delegationTargetPort, delegationSourcePart)
            || doesDelegationExistForComponentPort(delegationTargetPort, delegationSourcePart);
      }
    }
    return false;
  }

  private boolean doesDelegationExistForComponentPort(Port delegationTargetPort, Part delegationSourcePart) {
    Collection<ComponentExchange> delegationCEs = PortExt
        .getDelegationComponentExchanges((ComponentPort) delegationTargetPort);
    for (ComponentExchange delegation : delegationCEs) {
      if (delegationTargetPort.equals(ComponentExchangeExt.getTargetPort(delegation))) {
        if (ComponentExchangeExt.getSourcePartsAndEntities(delegation).contains(delegationSourcePart)) {
          return true;
        }
      } else if (delegationTargetPort.equals(ComponentExchangeExt.getSourcePort(delegation))) {
        if (ComponentExchangeExt.getTargetPartsAndEntities(delegation).contains(delegationSourcePart)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean doesPortAllocationExist(ComponentPort delegationTargetPort, Part delegationSourcePart) {
    Collection<ComponentPortAllocation> portAllocations = PortExt
        .getIncomingComponentPortAllocations(delegationTargetPort);
    for (ComponentPortAllocation delegation : portAllocations) {
      if (delegationTargetPort.equals(ComponentPortAllocationExt.getTargetPort(delegation))) {
        if (ComponentPortAllocationExt.getSourceParts(delegation).contains(delegationSourcePart)) {
          return true;
        }
      } else if (delegationTargetPort.equals(ComponentPortAllocationExt.getSourcePort(delegation))) {
        if (ComponentPortAllocationExt.getTargetParts(delegation).contains(delegationSourcePart)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean doesDelegationExistForPhysicalPort(PhysicalPort delegationTargetPort, Part delegationSourcePart) {
    Collection<PhysicalLink> delegations = PortExt.getDelegationPhysicalLinks(delegationTargetPort);
    for (PhysicalLink delegation : delegations) {
      if (delegationTargetPort.equals(PhysicalLinkExt.getTargetPort(delegation))) {
        if (PhysicalLinkExt.getSourceParts(delegation).contains(delegationSourcePart)) {
          return true;
        }
      } else if (delegationTargetPort.equals(PhysicalLinkExt.getSourcePort(delegation))) {
        if (PhysicalLinkExt.getTargetParts(delegation).contains(delegationSourcePart)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean isValidABPhysicalCategoryEdge(PhysicalLinkCategory category, DSemanticDecorator source,
      DSemanticDecorator target) {
    if ((category == null) || (source == null) || (target == null)) {
      return false;
    }
    EObject sourcePart = CsServices.getService().getRelatedPart(source);
    EObject targetPart = CsServices.getService().getRelatedPart(target);
    DSemanticDecorator sourcePartView = CsServices.getService().getRelatedPartView(source);
    DSemanticDecorator targetPartView = CsServices.getService().getRelatedPartView(target);

    AbstractDNode sourceView = (AbstractDNode) sourcePartView;
    AbstractDNode targetView = (AbstractDNode) targetPartView;

    EObject sourceTarget = source.getTarget();
    EObject targetTarget = target.getTarget();

    // Category with delegation
    if (((sourceTarget instanceof Port) && (targetTarget instanceof PhysicalLinkCategory))
        || ((targetTarget instanceof Port) && (sourceTarget instanceof PhysicalLinkCategory))) {
      // Case 1: If there's a real category, category with delegation should not be displayed
      if (doesCategoryExistBetweenParts(category, sourceView, targetView)) {
        return false;
      }
      // Case 2: If there's no delegation, no category edge
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
      if (delegationTargetPort instanceof Port && delegationSourcePart instanceof Part
          && !doesDelegationExist(delegationTargetPort, (Part) delegationSourcePart)) {
        return false;
      }

      // Verify further that a non-delegation physical link must exist for the same category
      boolean hasNonDelegationPL = false;
      for (PhysicalLink physicalLink : getCache(this::getRelatedPhysicalLinks, delegationSourcePart)) {
        if (!CsServices.getService().getAllDelegatesPhysicalLink(delegationSourcePart).contains(physicalLink)
            && physicalLink.getCategories().contains(category)) {
          hasNonDelegationPL = true;
        }
      }
      if (!hasNonDelegationPL)
        return false;

      // Case3: Part containing category port must be the container of part containing delegation port
      if (!((sourcePartView.eContainer() == targetPartView) || (targetPartView.eContainer() == sourcePartView))) {
        return false;
      }
      return true;
    }

    // retrieve all CE for the related category
    for (PhysicalLink exchange : getRelatedPhysicalLinks(sourcePart)) {
      if (exchange.getCategories().contains(category)) {
        Collection<? extends EObject> sourceParts = PhysicalLinkExt.getSourceParts(exchange);
        Collection<? extends EObject> targetParts = PhysicalLinkExt.getTargetParts(exchange);

        // Test if valid for source/target and target/source (category are not oriented)
        if ((sourceParts.contains(sourcePart) && targetParts.contains(targetPart))) {
          if (CsServices.getService().isValidLinkEdge(CsServices.getService().getPhysicalLinkWrapper(exchange),
              sourcePartView, targetPartView, false)) {
            return true;
          }
        } else if (sourceParts.contains(targetPart) && targetParts.contains(sourcePart)) {
          if (CsServices.getService().isValidLinkEdge(CsServices.getService().getPhysicalLinkWrapper(exchange),
              targetPartView, sourcePartView, false)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  public boolean isValidABFunctionalExchangeEdge(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {

    boolean isValid = true;

    isValid &= (context instanceof FunctionalExchange);
    isValid &= (sourceView != targetView);
    isValid &= !CapellaServices.getService().areInternalEdgePorts(sourceView, targetView);
    isValid &= !FaServices.getFaServices().isACategoryDisplayed(context, sourceView, targetView);

    return isValid;
  }

  public boolean isValidABInteractionEdge(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {
    if (!(context instanceof FunctionalExchange) || (sourceView == targetView)) {
      return false;
    }
    return true;
  }

  public boolean isValidCreationABNodePC(DSemanticDecorator containerView) {
    if (containerView == null || containerView.getTarget() == null) {
      return false;
    }

    EObject target = CsServices.getService().getABTarget(containerView);
    if (!isValidCreationABComponent(target)) {
      return false;
    }

    EObject type = CsServices.getService().getComponentType(containerView);
    // Deploy Node is allowed on NODE and UNSET, but not on Actor
    if (type instanceof PhysicalComponent) {
      PhysicalComponent pcType = (PhysicalComponent) type;
      if (PhysicalComponentNature.BEHAVIOR.equals(pcType.getNature())) {
        return false;
      }
    }

    return true;
  }

  public boolean isValidCreationABReuseNodePC(DSemanticDecorator containerView) {
    return isValidCreationABNodePC(containerView);
  }

  /**
   * Can we create a component to a graphical element?
   */
  public boolean isValidCreationABComponent(EObject element) {
    if (element instanceof DSemanticDecorator) {
      return ComponentExt.canCreateABComponent(((DSemanticDecorator) element).getTarget());
    }
    return ComponentExt.canCreateABComponent(element);
  }

  /**
   * Can we create an actor to a graphical element?
   */
  public boolean isValidCreationABActor(EObject element) {
    if (element instanceof DSemanticDecorator) {
      return ComponentExt.canCreateABActor(((DSemanticDecorator) element).getTarget());
    }
    return ComponentExt.canCreateABActor(element);
  }

  public boolean isValidCreationABBehaviorPC(DSemanticDecorator containerView) {
    if (containerView == null || containerView.getTarget() == null) {
      return false;
    }

    EObject target = CsServices.getService().getABTarget(containerView);
    if (!isValidCreationABComponent(target)) {
      return false;
    }

    EObject type = CsServices.getService().getComponentType(containerView);
    if (type instanceof PhysicalComponent) {
      PhysicalComponent pcType = (PhysicalComponent) type;
      if (PhysicalComponentNature.NODE.equals(pcType.getNature())) {
        return false;
      }
    }

    return true;
  }

  public boolean isValidCreationABReuseBehaviorPC(DSemanticDecorator containerView) {
    return isValidCreationABBehaviorPC(containerView);
  }

  public boolean isValidCreationABDeployNodePC(DSemanticDecorator containerView) {
    if (!(containerView instanceof DNodeContainer)) {
      return false;
    }

    EObject target = getABDeployTarget(containerView);
    if (!(target instanceof PhysicalComponent)) {
      return false;
    }

    // Deploy Node is allowed only on NODE or UNSET too
    EObject type = CsServices.getService().getComponentType(containerView);
    PhysicalComponent pcType = (PhysicalComponent) type;
    if (PhysicalComponentNature.BEHAVIOR.equals(pcType.getNature())) {
      return false;
    }
    return true;
  }

  public boolean isValidCreationABDeployBehaviorPC(DSemanticDecorator containerView) {
    if (!(containerView instanceof DNodeContainer)) {
      return false;
    }

    EObject target = getABDeployTarget(containerView);
    if (!(target instanceof PhysicalComponent)) {
      return false;
    }

    // Deploy Behavior is allowed on all nature (UNSET too)
    return true;
  }

  public boolean isValidABFunctionalChainInternalLinkEdge(FunctionalChain chain, DSemanticDecorator source,
      DSemanticDecorator target) {
    return FunctionalChainServices.getFunctionalChainServices().isValidInternalLinkEdge(chain, (EdgeTarget) source,
        (EdgeTarget) target);
  }

  public boolean isValidABPhysicalPathInternalLinkEdge(PhysicalPath path, DSemanticDecorator source,
      DSemanticDecorator target) {
    return PhysicalServices.getService().isValidInternalLinkEdge(path, (EdgeTarget) source, (EdgeTarget) target);
  }

  public boolean isValidFunctionalExchangeCategoryEdge(ExchangeCategory category, DSemanticDecorator source,
      DSemanticDecorator target) {

    if ((category == null) || (source == null) || (target == null)) {
      return false;
    }

    DSemanticDecorator sourcePartView = CsServices.getService().getRelatedFunctionView(source);
    DSemanticDecorator targetPartView = CsServices.getService().getRelatedFunctionView(target);

    AbstractFunction sourceTarget = (AbstractFunction) sourcePartView.getTarget();
    AbstractFunction targetTarget = (AbstractFunction) targetPartView.getTarget();

    return isSourceTargetCategoryFunction(sourceTarget, targetTarget, category);
  }

  public boolean isSourceTargetCategoryFunction(AbstractFunction source, AbstractFunction target,
      ExchangeCategory category) {
    Collection<EObject> cSource = new ArrayList<>(category.getExchanges());

    List<FunctionalExchange> allSourceExchanges = new ArrayList<>();
    for (AbstractFunction currentFunction : getCache(FunctionExt::getAllAbstractFunctions, source)) {
      allSourceExchanges.addAll(FunctionExt.getOutGoingExchange(currentFunction));
      allSourceExchanges.addAll(FunctionExt.getOutGoingExchange(currentFunction));
    }

    List<FunctionalExchange> allTargetExchanges = new ArrayList<>();
    for (AbstractFunction currentFunction : getCache(FunctionExt::getAllAbstractFunctions, target)) {
      allTargetExchanges.addAll(FunctionExt.getIncomingExchange(currentFunction));
      allTargetExchanges.addAll(FunctionExt.getIncomingExchange(currentFunction));
    }

    allSourceExchanges.retainAll(allTargetExchanges);
    cSource.retainAll(allSourceExchanges);
    return !cSource.isEmpty();

  }

  public boolean isSourceCategoryFunction(AbstractFunction source, ExchangeCategory category) {
    Collection<EObject> cSource = new ArrayList<>(category.getExchanges());

    List<FunctionalExchange> allSourceExchanges = new ArrayList<>();
    for (AbstractFunction currentFunction : getCache(FunctionExt::getAllAbstractFunctions, source)) {
      allSourceExchanges.addAll(FunctionExt.getOutGoingExchange(currentFunction));
      allSourceExchanges.addAll(FunctionExt.getOutGoingExchange(currentFunction));
    }

    cSource.retainAll(allSourceExchanges);
    return !cSource.isEmpty();
  }

  public boolean isTargetCategoryFunction(AbstractFunction target, ExchangeCategory category) {
    Collection<EObject> cSource = new ArrayList<>(category.getExchanges());

    List<FunctionalExchange> allSourceExchanges = new ArrayList<>();
    for (AbstractFunction currentFunction : getCache(FunctionExt::getAllAbstractFunctions, target)) {
      allSourceExchanges.addAll(FunctionExt.getIncomingExchange(currentFunction));
      allSourceExchanges.addAll(FunctionExt.getIncomingExchange(currentFunction));
    }

    cSource.retainAll(allSourceExchanges);
    return !cSource.isEmpty();

  }

  public boolean isComponentCategoryWithoutExchange(ComponentExchangeCategory category, Part source, Part target) {
    Collection<ComponentExchange> exchanges = new ArrayList<>(category.getExchanges());
    Collection<ComponentExchange> sourceRelatedComponentExchanges = getCache(
        ComponentExt::getAllRelatedComponentExchange, source);
    Collection<ComponentExchange> targetRelatedComponentExchanges = getCache(
        ComponentExt::getAllRelatedComponentExchange, target);
    sourceRelatedComponentExchanges.retainAll(targetRelatedComponentExchanges);
    exchanges.retainAll(sourceRelatedComponentExchanges);
    return !exchanges.isEmpty();
  }

  public boolean isValidABComponentCategoryEdge(ComponentExchangeCategory category, DSemanticDecorator source,
      DSemanticDecorator target) {
    if ((category == null) || (source == null) || (target == null)) {
      return false;
    }
    EObject sourcePart = CsServices.getService().getRelatedPart(source);
    EObject targetPart = CsServices.getService().getRelatedPart(target);
    DSemanticDecorator sourcePartView = CsServices.getService().getRelatedPartView(source);
    DSemanticDecorator targetPartView = CsServices.getService().getRelatedPartView(target);

    AbstractDNode sourceView = (AbstractDNode) sourcePartView;
    AbstractDNode targetView = (AbstractDNode) targetPartView;

    EObject sourceTarget = source.getTarget();
    EObject targetTarget = target.getTarget();

    // Category with delegation
    if (((sourceTarget instanceof ComponentPort) && (targetTarget instanceof ComponentExchangeCategory))
        || ((targetTarget instanceof ComponentPort) && (sourceTarget instanceof ComponentExchangeCategory))) {

      // Case 1: If there's a real category, category with delegation should not be displayed
      if (doesCategoryExistBetweenParts(category, sourceView, targetView)) {
        return false;
      }

      // Case 2: If there's no delegation, no category edge
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
      if (delegationTargetPort instanceof Port && delegationSourcePart instanceof Part
          && !doesDelegationExist((Port) delegationTargetPort, (Part) delegationSourcePart)) {
        return false;
      }
      // Verify further that a non-delegation component exchange must exist for the same category
      boolean hasNonDelegationCE = false;
      for (ComponentExchange exchange : getCache(ABServices::getRelatedComponentExchanges2, delegationSourcePart)) {
        if (!ComponentExchangeExt.isDelegation(exchange) && exchange.getCategories().contains(category)) {
          hasNonDelegationCE = true;
        }
      }
      if (!hasNonDelegationCE)
        return false;

      // Case3: Part containing category port must be the container of part containing delegation port
      if (!((sourcePartView.eContainer() == targetPartView) || (targetPartView.eContainer() == sourcePartView))) {
        return false;
      }
      return true;
    }

    // retrieve all CE for the related category
    for (ComponentExchange exchange : getRelatedComponentExchanges(sourcePart)) {
      if (exchange.getCategories().contains(category)) {
        Collection<? extends EObject> sourceParts = ComponentExchangeExt.getSourcePartsAndEntities(exchange);
        Collection<? extends EObject> targetParts = ComponentExchangeExt.getTargetPartsAndEntities(exchange);

        // Test if valid for source/target and target/source (category are not oriented)
        if ((sourceParts.contains(sourcePart) && targetParts.contains(targetPart))) {
          if (CsServices.getService().isValidLinkEdge(CsServices.getService().getComponentExchangeWrapper(exchange),
              sourcePartView, targetPartView, false)) {
            return true;
          }
        } else if (sourceParts.contains(targetPart) && targetParts.contains(sourcePart)) {
          if (CsServices.getService().isValidLinkEdge(CsServices.getService().getComponentExchangeWrapper(exchange),
              targetPartView, sourcePartView, false)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isValidShowHideABPhysicalCategory(EObject context, EObject containerView) {
    return isValidShowHideABComponentCategory(context, containerView);
  }

  public boolean isValidShowHideABComponentCategory(EObject context, EObject containerView) {
    if (containerView instanceof AbstractDNode) {
      EObject target = ((AbstractDNode) containerView).getTarget();
      if ((target != null) && ((target instanceof Part) || (target instanceof Entity))) {
        return true;
      }
    }
    return false;
  }

  public boolean isValidSwitchABPhysicalCategory(EObject context, EObject containerView) {
    return isValidSwitchABComponentCategory(context, containerView);
  }

  public boolean isValidSwitchABComponentCategory(EObject context, EObject containerView) {
    return containerView instanceof DDiagram;
  }

  public EObject showABPhysicalCategories(DSemanticDecorator context, HashMapSet<EObject, EObject> scope,
      HashMapSet<EObject, EObject> initialSelection, final HashMapSet<EObject, EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    EObject source = context.getTarget();

    AbstractShowHide shService = new ShowHideABPhysicalCategory(content);
    DiagramContext ctx = shService.new DiagramContext();
    if (context instanceof DDiagramElement) {
      ctx.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context));
    }

    for (EObject key : scope.keySet()) {
      PhysicalLinkCategory category = (PhysicalLinkCategory) key;

      for (EObject value : scope.get(key)) {
        if (!selectedElements.containsKey(key) || !selectedElements.get(key).contains(value)) {
          if (initialSelection.containsKey(key) && initialSelection.get(key).contains(value)) {

            showABPhysicalCategory(shService, ctx, (PhysicalLinkCategory) key, source, value, false);

            for (PhysicalLink exchange : getRelatedPhysicalLinks(source)) {
              if (exchange.getCategories().contains(key)) {
                displayABPhysicalCategoryPortDelegation(ctx, category, exchange,
                    (PhysicalPort) PhysicalLinkExt.getSourcePort(exchange), false, shService);
                displayABPhysicalCategoryPortDelegation(ctx, category, exchange,
                    (PhysicalPort) PhysicalLinkExt.getTargetPort(exchange), false, shService);
              }
            }

          }
        }
      }
    }

    for (EObject key : selectedElements.keySet()) {
      PhysicalLinkCategory category = (PhysicalLinkCategory) key;

      for (EObject target : selectedElements.get(key)) {
        showABPhysicalCategory(shService, ctx, (PhysicalLinkCategory) key, source, target, true);
      }

      for (PhysicalLink exchange : getRelatedPhysicalLinks(source)) {
        if (exchange.getCategories().contains(key)) {
          displayABPhysicalCategoryPortDelegation(ctx, category, exchange,
              (PhysicalPort) PhysicalLinkExt.getSourcePort(exchange), true, shService);
          displayABPhysicalCategoryPortDelegation(ctx, category, exchange,
              (PhysicalPort) PhysicalLinkExt.getTargetPort(exchange), true, shService);
        }
      }
    }

    content.commitDeferredActions();
    return context;
  }

  /**
   * Show selected categories and hide unselected categories from the given source
   * 
   * @param context
   * @param scope
   * @param initialSelection
   * @param selectedElements
   * @return
   */
  public EObject showABComponentCategories(DSemanticDecorator context, HashMapSet<EObject, EObject> scope,
      HashMapSet<EObject, EObject> initialSelection, final HashMapSet<EObject, EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    EObject source = context.getTarget();

    AbstractShowHide shService = new ShowHideABComponentCategory(content);
    DiagramContext ctx = shService.new DiagramContext();
    if (context instanceof DDiagramElement) {
      ctx.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context));
    }

    for (EObject key : scope.keySet()) {
      ComponentExchangeCategory category = (ComponentExchangeCategory) key;

      for (EObject value : scope.get(key)) {
        if (!selectedElements.containsKey(key) || !selectedElements.get(key).contains(value)) {
          if (initialSelection.containsKey(key) && initialSelection.get(key).contains(value)) {
            showABComponentCategory(shService, ctx, (ComponentExchangeCategory) key, source, value, false);

            for (ComponentExchange exchange : getCache(ABServices::getRelatedComponentExchanges2, source)) {
              if (exchange.getCategories().contains(key)) {
                displayABComponentCategoryPortDelegation(ctx, category, exchange,
                    (ComponentPort) ComponentExchangeExt.getSourcePort(exchange), false, shService);
                displayABComponentCategoryPortDelegation(ctx, category, exchange,
                    (ComponentPort) ComponentExchangeExt.getTargetPort(exchange), false, shService);
              }
            }

          }
        }
      }
    }

    for (EObject key : selectedElements.keySet()) {
      ComponentExchangeCategory category = (ComponentExchangeCategory) key;

      for (EObject target : selectedElements.get(key)) {
        showABComponentCategory(shService, ctx, (ComponentExchangeCategory) key, source, target, true);
      }

      for (ComponentExchange exchange : getRelatedComponentExchanges(source)) {
        if (exchange.getCategories().contains(key)) {
          displayABComponentCategoryPortDelegation(ctx, category, exchange,
              (ComponentPort) ComponentExchangeExt.getSourcePort(exchange), true, shService);
          displayABComponentCategoryPortDelegation(ctx, category, exchange,
              (ComponentPort) ComponentExchangeExt.getTargetPort(exchange), true, shService);
        }
      }
    }

    content.commitDeferredActions();

    return context;
  }

  public EObject switchABPhysicalCategories(DSemanticDecorator context, Collection<EObject> scope,
      Collection<EObject> initialSelection, Collection<EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    return switchABPhysicalCategories(content, context, selectedElements, true);
  }

  /**
   * Do a switch Physical Link / Category
   * 
   * @param content
   * @param context
   * @param selectedElements
   *          in tool, the categories chosen by the user, in refresh, the displayed categories
   * @param showHiddenPhysicalLinks
   * @return
   */
  public EObject switchABPhysicalCategories(DDiagramContents content, DSemanticDecorator context,
      Collection<EObject> selectedElements, boolean showHiddenPhysicalLinks) {
    ABServices.getService().updateABPhysicalCategories(content);

    // retrieve all part views where to apply the show/hide
    Collection<DDiagramElement> sourceViews = computeRelatedSourceViewsToPhysicalLinks(content, context);

    AbstractShowHide physicalCategoryShowHideService = new ShowHideABPhysicalCategory(content);
    DiagramContext ctx = physicalCategoryShowHideService.new DiagramContext();
    if (context instanceof DDiagramElement) {
      ctx.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context));
    }

    // Compute only once relatedPhysicalLinks and abShowHidePhysicalCategoriesScope of a source view
    Map<DDiagramElement, Collection<PhysicalLink>> sourceViewToRelatedPhysicalLinksMap = new HashMap<>();
    Map<DDiagramElement, HashMapSet<EObject, EObject>> abShowHidePhysicalCategoriesScopeMap = new HashMap<>();
    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        Collection<PhysicalLink> relatedPhysicalLinks = getRelatedPhysicalLinks(sourceViewTarget);

        sourceViewToRelatedPhysicalLinksMap.put(sourceView, relatedPhysicalLinks);
        abShowHidePhysicalCategoriesScopeMap.put(sourceView,
            getABShowHidePhysicalCategoriesScope(sourceView, relatedPhysicalLinks));
      }
    }

    // 1. SHOW OR HIDE PHYSICAL CATEGORIES
    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        EObject source = sourceViewTarget;
        HashMapSet<EObject, EObject> scopeSource = abShowHidePhysicalCategoriesScopeMap.get(sourceView);
        for (EObject key : scopeSource.keySet()) {
          PhysicalLinkCategory physicalLinkCategory = (PhysicalLinkCategory) key;
          for (EObject target : scopeSource.get(key)) {
            if (selectedElements.contains(key)) {
              showABPhysicalCategory(physicalCategoryShowHideService, ctx, physicalLinkCategory, source, target, true);
            } else {
              showABPhysicalCategory(physicalCategoryShowHideService, ctx, physicalLinkCategory, source, target, false);
            }
          }
        }
      }
    }

    // 2. SHOW OR HIDE PHYSICAL LINKS
    // In tool (showHiddenPhysicalLinks==true), user may have removed some categories, so he wants to display hidden
    // physical links associated to them.
    // In refresh (showHiddenPhysicalLinks==false), categories haven't been changed by the user, so he doesn't want to
    // display hidden physical links,
    // he just want to hide new physical links associated to displayed categories.
    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        HashMapSet<EObject, EObject> scopeSource = abShowHidePhysicalCategoriesScopeMap.get(sourceView);

        // Traverse the categories selected by the user
        for (EObject key : scopeSource.keySet()) {
          PhysicalLinkCategory category = (PhysicalLinkCategory) key;
          for (PhysicalLink physicalLink : sourceViewToRelatedPhysicalLinksMap.get(sourceView)) {
            if (physicalLink.getCategories().contains(key)) {
              PhysicalPort sourcePort = (PhysicalPort) PhysicalLinkExt.getSourcePort(physicalLink);
              PhysicalPort targetPort = (PhysicalPort) PhysicalLinkExt.getTargetPort(physicalLink);
              if (selectedElements.contains(key)) {
                displayABPhysicalCategoryPortDelegation(ctx, category, physicalLink, sourcePort, true,
                    physicalCategoryShowHideService);
                displayABPhysicalCategoryPortDelegation(ctx, category, physicalLink, targetPort, true,
                    physicalCategoryShowHideService);
                // Hide the physical link
                physicalCategoryShowHideService.hide(physicalLink, ctx);
              } else if (showHiddenPhysicalLinks) {
                displayABPhysicalCategoryPortDelegation(ctx, category, physicalLink, sourcePort, false,
                    physicalCategoryShowHideService);
                displayABPhysicalCategoryPortDelegation(ctx, category, physicalLink, targetPort, false,
                    physicalCategoryShowHideService);

                // Show the physical link
                physicalCategoryShowHideService.show(physicalLink, ctx);
              }
            }
          }
        }
      }
    }

    // 3.
    ABServices.getService().updateABPhysicalCategories(content);
    content.commitDeferredActions();

    return context;
  }

  private Collection<DDiagramElement> computeRelatedSourceViewsToPhysicalLinks(DDiagramContents content,
      DSemanticDecorator context) {
    DDiagram currentDiagram = content.getDDiagram();
    Collection<DDiagramElement> sourceViews = new ArrayList<>();
    if (context instanceof DDiagramElement) {
      sourceViews.add((DDiagramElement) context);
    }
    if (sourceViews.isEmpty()) {
      for (String mappingName : MappingConstantsHelper.getMappingsABComponent(currentDiagram)) {
        DiagramElementMapping mapping = content.getMapping(mappingName);
        for (DDiagramElement element : content.getDiagramElements(mapping)) {
          sourceViews.add(element);
        }
        if (currentDiagram.getDescription().getName()
            .equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
          mapping = content.getMapping(MappingConstantsHelper.getMappingABDeployedElement(currentDiagram));
          for (DDiagramElement element : content.getDiagramElements(mapping)) {
            sourceViews.add(element);
          }
        }
      }
    }
    return sourceViews;
  }

  public EObject switchABComponentCategories(DSemanticDecorator context, Collection<EObject> scope,
      Collection<EObject> initialSelection, Collection<EObject> selectedElements) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context);
    DDiagramContents content = new DDiagramContents(currentDiagram);
    return switchABComponentCategories(content, context, selectedElements, true);
  }

  @Deprecated
  public EObject switchABComponentCategories(DDiagramContents content, DSemanticDecorator context,
      Collection<EObject> selectedElements) {
    return switchABComponentCategories(content, context, selectedElements, true);
  }

  /**
   * Show selected categories and hide unselected categories from the given source
   * 
   * @param context
   * @param scope
   * @param initialSelection
   * @param selectedElements
   *          in tool, the categories chosen by the user, in refresh, the displayed categories
   * @return
   */
  public EObject switchABComponentCategories(DDiagramContents content, DSemanticDecorator context,
      Collection<EObject> selectedElements, boolean showHiddenExchanges) {

    ABServices.getService().updateABComponentCategories(content);

    DDiagram currentDiagram = content.getDDiagram();
    Collection<DDiagramElement> sourceViews = new HashSet<>();
    if (context instanceof DDiagramElement) {
      sourceViews.add((DDiagramElement) context);
    }
    if (sourceViews.isEmpty()) {
      for (String mappingName : MappingConstantsHelper.getMappingsABComponent(currentDiagram)) {
        DiagramElementMapping mapping = content.getMapping(mappingName);
        for (DDiagramElement element : content.getDiagramElements(mapping)) {
          sourceViews.add(element);
        }
        if (currentDiagram.getDescription().getName()
            .equalsIgnoreCase(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
          mapping = content.getMapping(MappingConstantsHelper.getMappingABDeployedElement(currentDiagram));
          for (DDiagramElement element : content.getDiagramElements(mapping)) {
            sourceViews.add(element);
          }
        }
      }

    }

    AbstractShowHide categories = new ShowHideABComponentCategory(content);
    DiagramContext ctx = categories.new DiagramContext();
    if (context instanceof DDiagramElement) {
      ctx.setVariable(ShowHideABComponentExchange.SOURCE_PART_VIEWS, Collections.singletonList(context));
    }

    // Compute only once relatedComponentExchanges and abShowHideComponentCategoriesScope of a source view
    Map<EObject, Collection<ComponentExchange>> relatedComponentExchangesMap = new HashMap<EObject, Collection<ComponentExchange>>();
    Map<DDiagramElement, HashMapSet<EObject, EObject>> abShowHideComponentCategoriesScopeMap = new HashMap<DDiagramElement, HashMapSet<EObject, EObject>>();
    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        EObject source = sourceViewTarget;
        Collection<ComponentExchange> relatedComponentExchanges = getCache(ABServices::getRelatedComponentExchanges2,
            source);

        relatedComponentExchangesMap.put(source, relatedComponentExchanges);
        abShowHideComponentCategoriesScopeMap.put(sourceView,
            getABShowHideComponentCategoriesScope(sourceView, relatedComponentExchanges));
      }
    }

    // Display the categories between parts if they are part of selectedElements, or hide them
    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        EObject source = sourceViewTarget;
        HashMapSet<EObject, EObject> scopeSource = abShowHideComponentCategoriesScopeMap.get(sourceView);
        for (EObject key : scopeSource.keySet()) {

          if (selectedElements.contains(key)) {
            for (EObject target : scopeSource.get(key)) {
              showABComponentCategory(categories, ctx, (ComponentExchangeCategory) key, source, target, true);
            }
          } else {
            for (EObject target : scopeSource.get(key)) {
              showABComponentCategory(categories, ctx, (ComponentExchangeCategory) key, source, target, false);
            }
          }
        }
      }
    }

    // In tool (showHiddenExchanges==true), user may have removed some categories, so he wants to display hidden
    // exchanges associated to them.
    // In refresh (showHiddenExchanges==false), categories haven't been changed by the user, so he doesn't want to
    // display hidden exchanges,
    // he just want to hide new exchanges associated to displayed categories.
    for (DDiagramElement sourceView : sourceViews) {
      EObject sourceViewTarget = sourceView.getTarget();
      if (sourceViewTarget != null) {
        EObject source = sourceViewTarget;
        HashMapSet<EObject, EObject> scopeSource = abShowHideComponentCategoriesScopeMap.get(sourceView);
        for (EObject key : scopeSource.keySet()) {
          ComponentExchangeCategory category = (ComponentExchangeCategory) key;
          if (selectedElements.contains(key)) {
            for (ComponentExchange exchange : relatedComponentExchangesMap.get(source)) {
              if (exchange.getCategories().contains(key)) {
                displayABComponentCategoryPortDelegation(ctx, category, exchange,
                    (ComponentPort) ComponentExchangeExt.getSourcePort(exchange), true, categories);
                displayABComponentCategoryPortDelegation(ctx, category, exchange,
                    (ComponentPort) ComponentExchangeExt.getTargetPort(exchange), true, categories);
                categories.hide(exchange, ctx);
              }
            }
          } else if (showHiddenExchanges) {
            for (ComponentExchange exchange : relatedComponentExchangesMap.get(source)) {
              if (exchange.getCategories().contains(key)) {
                displayABComponentCategoryPortDelegation(ctx, category, exchange,
                    (ComponentPort) ComponentExchangeExt.getSourcePort(exchange), false, categories);
                displayABComponentCategoryPortDelegation(ctx, category, exchange,
                    (ComponentPort) ComponentExchangeExt.getTargetPort(exchange), false, categories);
                categories.show(exchange, ctx);
              }
            }
          }
        }
      }
    }

    ABServices.getService().updateABComponentCategories(content);

    content.commitDeferredActions();
    return context;
  }

  /**
   * @param categories
   * @param context
   * @param key
   * @param source
   * @param target
   * @param b
   *          show or hide according to this parameter
   */
  private void showABPhysicalCategory(AbstractShowHide categories, DiagramContext context, PhysicalLinkCategory key,
      EObject source, EObject target, boolean b) {

    context.setVariable(ShowHideABComponent.SOURCE_PARTS, Collections.singletonList(source));
    context.setVariable(ShowHideABComponent.TARGET_PARTS, Collections.singletonList(target));

    boolean isSourceAndTargetNoPort = false;
    boolean isSourceAndTargetPort = false;
    for (PhysicalLink exchanges : getRelatedPhysicalLinks(source, target, key)) {
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
      context.setVariable(ShowHideABPhysicalCategory.NO_TARGET_PORT, Boolean.TRUE);
      context.setVariable(ShowHideABPhysicalCategory.NO_SOURCE_PORT, Boolean.TRUE);
      if (b) {
        categories.show(key, context);
      } else {
        categories.hide(key, context);
      }

    }
    if (isSourceAndTargetPort) {
      context.setVariable(ShowHideABPhysicalCategory.NO_TARGET_PORT, Boolean.FALSE);
      context.setVariable(ShowHideABPhysicalCategory.NO_SOURCE_PORT, Boolean.FALSE);
      if (b) {
        categories.show(key, context);
      } else {
        categories.hide(key, context);
      }
    }
  }

  /**
   * @param categories
   * @param context
   * @param key
   * @param source
   * @param target
   */
  private void showABComponentCategory(AbstractShowHide categories, DiagramContext context,
      ComponentExchangeCategory key, EObject source, EObject target, boolean b) {

    context.setVariable(ShowHideABComponent.SOURCE_PARTS, Collections.singletonList(source));
    context.setVariable(ShowHideABComponent.TARGET_PARTS, Collections.singletonList(target));

    boolean isSourceAndTargetNoPort = false;
    boolean isSourceAndTargetPort = false;

    for (ComponentExchange exchanges : getRelatedComponentExchanges(source, target, key)) {
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
      context.setVariable(ShowHideABPhysicalCategory.NO_TARGET_PORT, Boolean.TRUE);
      context.setVariable(ShowHideABPhysicalCategory.NO_SOURCE_PORT, Boolean.TRUE);
      if (b) {
        categories.show(key, context);
      } else {
        categories.hide(key, context);
      }

    }
    if (isSourceAndTargetPort) {
      context.setVariable(ShowHideABPhysicalCategory.NO_TARGET_PORT, Boolean.FALSE);
      context.setVariable(ShowHideABPhysicalCategory.NO_SOURCE_PORT, Boolean.FALSE);
      if (b) {
        categories.show(key, context);
      } else {
        categories.hide(key, context);
      }
    }
  }

  /**
   * @param exchange
   * @param sourcePort
   * @param b
   * @param categories
   */
  protected void displayABPhysicalCategoryPortDelegation(DiagramContext context, PhysicalLinkCategory category,
      PhysicalLink exchange, PhysicalPort sourcePort, boolean b, AbstractShowHide service) {

    if (sourcePort == null) {
      return;
    }
    // Display a mix-category/delegation ONLY at the first level of delegation
    if (!org.polarsys.capella.core.model.helpers.PhysicalLinkExt.isDelegation(exchange)) {
      for (EObject element : getPhysicalLinkDelegationsForCategory(sourcePort, category)) {
        if (element instanceof PhysicalLink) {
          PhysicalLink delegation = (PhysicalLink) element;

          if (PhysicalLinkExt.getSourcePort(delegation).equals(sourcePort)) {
            context.setVariable(ShowHideABComponentCategory.TARGET_PORTS,
                Collections.singletonList(PhysicalLinkExt.getTargetPort(delegation)));
          }
          if (PhysicalLinkExt.getTargetPort(delegation).equals(sourcePort)) {
            context.setVariable(ShowHideABComponentCategory.SOURCE_PORTS,
                Collections.singletonList(PhysicalLinkExt.getSourcePort(delegation)));
          }

          context.setVariable(ShowHideABComponent.SOURCE_PARTS, PhysicalLinkExt.getSourceParts(delegation));
          context.setVariable(ShowHideABComponent.TARGET_PARTS, PhysicalLinkExt.getTargetParts(delegation));

          if (b) {
            service.show(category, context);
            context.unsetVariable(ShowHideABPhysicalCategory.SOURCE_PORTS);
            context.unsetVariable(ShowHideABPhysicalCategory.TARGET_PORTS);
            service.hide(delegation, context);

          } else {
            service.hide(category, context);
            context.unsetVariable(ShowHideABPhysicalCategory.SOURCE_PORTS);
            context.unsetVariable(ShowHideABPhysicalCategory.TARGET_PORTS);
            service.show(delegation, context);
          }
        } else if (element instanceof ComponentPortAllocation) {
          ComponentPortAllocation delegation = (ComponentPortAllocation) element;

          if (ComponentPortAllocationExt.getSourcePort(delegation).equals(sourcePort)) {
            context.setVariable(ShowHideABComponentCategory.TARGET_PORTS,
                Collections.singletonList(ComponentPortAllocationExt.getTargetPort(delegation)));
          }
          if (ComponentPortAllocationExt.getTargetPort(delegation).equals(sourcePort)) {
            context.setVariable(ShowHideABComponentCategory.SOURCE_PORTS,
                Collections.singletonList(ComponentPortAllocationExt.getSourcePort(delegation)));
          }

          context.setVariable(ShowHideABComponent.SOURCE_PARTS, ComponentPortAllocationExt.getSourceParts(delegation));
          context.setVariable(ShowHideABComponent.TARGET_PARTS, ComponentPortAllocationExt.getTargetParts(delegation));

          if (b) {
            service.show(category, context);
            context.unsetVariable(ShowHideABPhysicalCategory.SOURCE_PORTS);
            context.unsetVariable(ShowHideABPhysicalCategory.TARGET_PORTS);
            service.hide(delegation, context);

          } else {
            service.hide(category, context);
            context.unsetVariable(ShowHideABPhysicalCategory.SOURCE_PORTS);
            context.unsetVariable(ShowHideABPhysicalCategory.TARGET_PORTS);
            service.show(delegation, context);
          }
        }
      }
    }
  }

  /**
   * @param exchange
   * @param sourcePort
   * @param b
   * @param categories
   */
  protected void displayABComponentCategoryPortDelegation(DiagramContext context, ComponentExchangeCategory category,
      ComponentExchange exchange, ComponentPort sourcePort, boolean b, AbstractShowHide service) {

    if (sourcePort == null) {
      return;
    }

    // Display a mix-category/delegation ONLY at the first level of delegation
    if (!ComponentExchangeExt.isDelegation(exchange)) {
      for (ComponentExchange delegation : getComponentExchangeDelegationsForCategory(sourcePort, category)) {

        if (ComponentExchangeExt.getSourcePort(delegation).equals(sourcePort)) {
          context.setVariable(ShowHideABComponentCategory.TARGET_PORTS,
              Collections.singletonList(ComponentExchangeExt.getTargetPort(delegation)));
        }
        if (ComponentExchangeExt.getTargetPort(delegation).equals(sourcePort)) {
          context.setVariable(ShowHideABComponentCategory.SOURCE_PORTS,
              Collections.singletonList(ComponentExchangeExt.getSourcePort(delegation)));
        }

        context.setVariable(ShowHideABComponent.SOURCE_PARTS,
            ComponentExchangeExt.getSourcePartsAndEntities(delegation));
        context.setVariable(ShowHideABComponent.TARGET_PARTS,
            ComponentExchangeExt.getTargetPartsAndEntities(delegation));

        if (b) {
          service.show(category, context);
          context.unsetVariable(ShowHideABComponentCategory.SOURCE_PORTS);
          context.unsetVariable(ShowHideABComponentCategory.TARGET_PORTS);
          service.hide(delegation, context);

        } else {
          service.hide(category, context);
          context.unsetVariable(ShowHideABComponentCategory.SOURCE_PORTS);
          context.unsetVariable(ShowHideABComponentCategory.TARGET_PORTS);
          service.show(delegation, context);
        }
      }
    }
  }

  /**
   * @param exchange
   * @param key
   * @return
   */
  protected Collection<EObject> getPhysicalLinkDelegationsForCategory(PhysicalPort port,
      PhysicalLinkCategory category) {
    Collection<EObject> result = new ArrayList<>();

    if (port != null) {
      for (PhysicalLink exchange : PortExt.getDelegatedPhysicalLinks(port)) {
        if (!exchange.getCategories().contains(category)) {
          result.add(exchange);
        }
      }

      for (ComponentPortAllocation allocation : port.getOwnedComponentPortAllocations()) {
        result.add(allocation);
      }

    }
    return result;
  }

  /**
   * @param exchange
   * @param key
   * @return
   */
  protected Collection<ComponentExchange> getComponentExchangeDelegationsForCategory(ComponentPort port,
      ComponentExchangeCategory category) {
    Collection<ComponentExchange> result = new ArrayList<>();

    if (port != null) {
      for (ComponentExchange exchange : PortExt.getDelegatedComponentExchanges(port)) {
        if (!exchange.getCategories().contains(category)) {
          result.add(exchange);
        }
      }
    }
    return result;
  }

  public Collection<PhysicalLink> getRelatedPhysicalLinks(EObject source, EObject target,
      PhysicalLinkCategory category) {
    Collection<PhysicalLink> result = new ArrayList<>();

    for (PhysicalLink element : getRelatedPhysicalLinks(source)) {
      if (element.getCategories().contains(category)) {
        Collection<? extends EObject> sourceParts = PhysicalLinkExt.getSourceParts(element);
        Collection<? extends EObject> targetParts = PhysicalLinkExt.getTargetParts(element);
        // Test if valid for source/target and target/source (category are not oriented)
        if ((sourceParts.contains(source) && targetParts.contains(target))) {
          result.add(element);

        } else if ((sourceParts.contains(target) && targetParts.contains(source))) {
          result.add(element);
        }
      }
    }
    return result;
  }

  public Collection<PhysicalLink> getRelatedPhysicalLinks(EObject part) {
    if (part instanceof Part) {
      return getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, (Part) part);
    }
    return Collections.emptyList();
  }

  public Collection<ComponentExchange> getRelatedComponentExchanges(EObject sourcePart, EObject targetPart,
      ComponentExchangeCategory category) {
    Collection<ComponentExchange> result = new ArrayList<>();

    for (ComponentExchange element : getCache(ABServices::getRelatedComponentExchanges2, sourcePart)) {
      if (element.getCategories().contains(category)) {
        Collection<? extends EObject> sourceParts = ComponentExchangeExt.getSourcePartsAndEntities(element);
        Collection<? extends EObject> targetParts = ComponentExchangeExt.getTargetPartsAndEntities(element);
        // Test if valid for source/target and target/source (category are not oriented)
        if ((sourceParts.contains(sourcePart) && targetParts.contains(targetPart))) {
          result.add(element);

        } else if ((sourceParts.contains(targetPart) && targetParts.contains(sourcePart))) {
          result.add(element);
        }
      }
    }
    return result;
  }

  /**
   * Returns related component exchanges linked to the given element
   * 
   * @param element
   *          a part or a component
   * @return
   */
  public Collection<ComponentExchange> getRelatedComponentExchanges(EObject element) {
    if (element instanceof Part) {
      return ComponentExt.getAllRelatedComponentExchange((Part) element, true);

    } else if (element instanceof Component) {
      return getCache(ComponentExt::getAllRelatedComponentExchange, (Component) element);
    }
    return Collections.emptyList();
  }

  public Collection<PhysicalLink> getRelatedPhysicalLink(EObject element) {
    if (element instanceof Part) {
      return getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, (Part) element);

    } else if (element instanceof Component) {
      return getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, (Component) element);
    }
    return Collections.emptyList();
  }

  public HashMapSet<EObject, EObject> getABShowHidePhysicalCategoriesScope(DSemanticDecorator context,
      Collection<PhysicalLink> relatedPhysicalLinks) {
    HashMapSet<EObject, EObject> result = new HashMapSet<>();
    EObject relatedPart = CsServices.getService().getRelatedPart(context);

    if (relatedPart != null) {
      for (PhysicalLink physicalLink : relatedPhysicalLinks) {
        for (PhysicalLinkCategory physicalLinkCategory : physicalLink.getCategories()) {
          Collection<? extends EObject> sourceParts = PhysicalLinkExt.getSourceParts(physicalLink);
          Collection<? extends EObject> targetParts = PhysicalLinkExt.getTargetParts(physicalLink);
          if (sourceParts.contains(relatedPart)) {
            for (EObject targetPart : targetParts) {
              result.put(physicalLinkCategory, targetPart);
            }
          } else if (targetParts.contains(relatedPart)) {
            for (EObject sourcePart : sourceParts) {
              result.put(physicalLinkCategory, sourcePart);
            }
          }
        }
      }
    }
    return result;
  }

  public HashMapSet<EObject, EObject> getABShowHidePhysicalCategoriesScope(DSemanticDecorator context) {
    EObject relatedPart = CsServices.getService().getRelatedPart(context);
    if (relatedPart != null) {
      Collection<PhysicalLink> relatedPhysicalLinks = getRelatedPhysicalLinks(relatedPart);
      return getABShowHidePhysicalCategoriesScope(context, relatedPhysicalLinks);
    } else {
      return new HashMapSet<EObject, EObject>();
    }
  }

  /**
   * Retrieve a map<ExchangeCategory, Collection<Part>> of available category to display from the given source view
   * 
   * @param context
   * @return
   */
  public HashMapSet<EObject, EObject> getABShowHideComponentCategoriesScope(DSemanticDecorator context,
      Collection<ComponentExchange> relatedComponentExchanges) {
    HashMapSet<EObject, EObject> result = new HashMapSet<>();
    EObject relatedPart = CsServices.getService().getRelatedPart(context);

    if (relatedPart != null) {
      for (ComponentExchange exchange : relatedComponentExchanges) {
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

  /**
   * Retrieve a map<ExchangeCategory, Collection<Part>> of available category to display from the given source view
   * 
   * @param context
   * @return
   */
  @Deprecated
  public HashMapSet<EObject, EObject> getABShowHideComponentCategoriesScope(DSemanticDecorator context) {
    EObject relatedPart = CsServices.getService().getRelatedPart(context);
    if (relatedPart != null) {
      Collection<ComponentExchange> relatedComponentExchanges = getCache(ABServices::getRelatedComponentExchanges2,
          relatedPart);
      return getABShowHideComponentCategoriesScope(context, relatedComponentExchanges);
    }
    return new HashMapSet<>();
  }

  public HashMapSet<EObject, EObject> getABShowHidePhysicalCategoriesInitialSelection(DSemanticDecorator context) {
    HashMapSet<EObject, EObject> scope = getABShowHidePhysicalCategoriesScope(context);
    HashMapSet<EObject, EObject> result = new HashMapSet<>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(context);
    DDiagramContents ctx = new DDiagramContents(diagram);
    DiagramElementMapping edgeMapping = ctx
        .getMapping(MappingConstantsHelper.getMappingABPhysicalCategory(ctx.getDDiagram()));
    DSemanticDecorator sourcePartView = CsServices.getService().getRelatedPartView(context);

    for (EObject key : scope.keySet()) {
      for (EObject targetPart : scope.get(key)) {
        for (DDiagramElement elementView : ctx.getDiagramElements(key, edgeMapping)) {
          if (elementView instanceof DEdge) {
            DEdge ve = (DEdge) elementView;
            DSemanticDecorator edgeSourcePartView = CsServices.getService()
                .getRelatedPartView((DSemanticDecorator) ve.getSourceNode());
            DSemanticDecorator edgeTargetPartView = CsServices.getService()
                .getRelatedPartView((DSemanticDecorator) ve.getTargetNode());
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
   * 
   * @param context
   * @return
   */
  public HashMapSet<EObject, EObject> getABShowHideComponentCategoriesInitialSelection(DSemanticDecorator context) {
    HashMapSet<EObject, EObject> scope = getABShowHideComponentCategoriesScope(context);
    HashMapSet<EObject, EObject> result = new HashMapSet<>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(context);
    DDiagramContents ctx = new DDiagramContents(diagram);
    DiagramElementMapping edgeMapping = ctx
        .getMapping(MappingConstantsHelper.getMappingABComponentCategory(ctx.getDDiagram()));
    DSemanticDecorator sourcePartView = CsServices.getService().getRelatedPartView(context);

    for (EObject key : scope.keySet()) {
      for (EObject targetPart : scope.get(key)) {
        for (DDiagramElement elementView : ctx.getDiagramElements(key, edgeMapping)) {
          if (elementView instanceof DEdge) {
            DEdge ve = (DEdge) elementView;
            DSemanticDecorator edgeSourcePartView = CsServices.getService()
                .getRelatedPartView((DSemanticDecorator) ve.getSourceNode());
            DSemanticDecorator edgeTargetPartView = CsServices.getService()
                .getRelatedPartView((DSemanticDecorator) ve.getTargetNode());
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

  public Collection<EObject> getABSwitchPhysicalCategoriesScope(DSemanticDecorator context) {
    if (context instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<>();
      DDiagramContents ctx = new DDiagramContents((DDiagram) context);
      Iterable<DDiagramElement> diagramElements = ctx
          .getDiagramElements(ctx.getMapping(MappingConstantsHelper.getMappingABPhysicalLink(ctx.getDDiagram())));
      for (DDiagramElement element : diagramElements) {
        if (element.getTarget() instanceof PhysicalLink) {
          values.addAll(((PhysicalLink) element.getTarget()).getCategories());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> scope = getABShowHidePhysicalCategoriesScope(context);
    return scope.keySet();
  }

  /**
   * Retrieve a map<ExchangeCategory, Collection<Part>> of available category to display from the given source view
   * 
   * @param context
   * @return
   */
  public Collection<EObject> getABSwitchComponentCategoriesScope(DSemanticDecorator context) {
    if (context instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<>();
      DDiagramContents ctx = new DDiagramContents((DDiagram) context);
      for (DDiagramElement element : ctx
          .getDiagramElements(ctx.getMapping(MappingConstantsHelper.getMappingABConnection(ctx.getDDiagram())))) {
        if (element.getTarget() instanceof ComponentExchange) {
          values.addAll(((ComponentExchange) element.getTarget()).getCategories());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> scope = getABShowHideComponentCategoriesScope(context);
    return scope.keySet();

  }

  public Collection<EObject> getABSwitchPhysicalCategoriesInitialSelection(DSemanticDecorator context) {
    if (context instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<>();
      DDiagramContents ctx = new DDiagramContents((DDiagram) context);
      DiagramElementMapping edgeMapping = ctx
          .getMapping(MappingConstantsHelper.getMappingABPhysicalCategory(ctx.getDDiagram()));

      for (DDiagramElement element : ctx.getDiagramElements(edgeMapping)) {
        if (element.getTarget() instanceof CapellaElement) {
          values.add(element.getTarget());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> result = getABShowHidePhysicalCategoriesInitialSelection(context);
    return result.keySet();
  }

  /**
   * Retrieve the initial selection of displayed category for the given source view
   * 
   * @param context
   * @return
   */
  public Collection<EObject> getABSwitchComponentCategoriesInitialSelection(DSemanticDecorator context) {
    if (context instanceof DDiagram) {
      HashSet<EObject> values = new HashSet<>();
      DDiagramContents ctx = new DDiagramContents((DDiagram) context);
      DiagramElementMapping edgeMapping = ctx
          .getMapping(MappingConstantsHelper.getMappingABComponentCategory(ctx.getDDiagram()));
      for (DDiagramElement element : ctx.getDiagramElements(edgeMapping)) {
        if (element.getTarget() instanceof CapellaElement) {
          values.add(element.getTarget());
        }
      }
      return values;
    }
    HashMapSet<EObject, EObject> result = getABShowHideComponentCategoriesInitialSelection(context);
    return result.keySet();
  }

  /**
   * Retrieve available sources for the given category (since we display ports of category, we return itself)
   * 
   * @param context
   * @return
   */
  public Collection<EObject> getComponentCategorySourcesWithoutCache(EObject context) {
    Collection<EObject> result = new HashSet<>();
    result.add(context);
    for (ComponentExchange e : ((ComponentExchangeCategory) context).getExchanges()) {
      result.add(e.getSource());
      for (ComponentExchange d : getComponentExchangeDelegationsForCategory(
          (ComponentPort) ComponentExchangeExt.getSourcePort(e), ((ComponentExchangeCategory) context))) {
        result.add(d.getSource());
        result.add(d.getTarget());
      }
      for (ComponentExchange d : getComponentExchangeDelegationsForCategory(
          (ComponentPort) ComponentExchangeExt.getTargetPort(e), ((ComponentExchangeCategory) context))) {
        result.add(d.getSource());
        result.add(d.getTarget());
      }
    }
    return result;
  }

  public Collection<EObject> getComponentCategorySources(EObject context) {
    return getCache(this::getComponentCategorySourcesWithoutCache, context);
  }

  /**
   * Retrieve available targets for the given category (since we display ports of category, we return itself)
   * 
   * @param context
   * @return
   */
  public Collection<EObject> getComponentCategoryTargetsWithoutCache(EObject context) {
    return getComponentCategorySourcesWithoutCache(context);
  }

  public Collection<EObject> getComponentCategoryTargets(EObject context) {
    return getCache(this::getComponentCategoryTargetsWithoutCache, context);
  }

  /**
   * Retrieve available sources for the given category (since we display ports of category, we return itself)
   * 
   * @param context
   * @return
   */
  public Collection<EObject> getPhysicalCategorySourcesWithoutCache(EObject context) {
    Collection<EObject> result = new HashSet<>();
    result.add(context);
    for (PhysicalLink e : ((PhysicalLinkCategory) context).getLinks()) {
      result.add(e.getSourcePhysicalPort());
      result.add(e.getTargetPhysicalPort());
      for (EObject d : getPhysicalLinkDelegationsForCategory(e.getSourcePhysicalPort(),
          ((PhysicalLinkCategory) context))) {
        if (d instanceof PhysicalLink) {
          result.addAll(((PhysicalLink) d).getLinkEnds());
        } else if (d instanceof ComponentPortAllocation) {
          result.add(((ComponentPortAllocation) d).getAllocatedPort());
        }
      }
      for (EObject d : getPhysicalLinkDelegationsForCategory(e.getTargetPhysicalPort(),
          ((PhysicalLinkCategory) context))) {
        if (d instanceof PhysicalLink) {
          result.addAll(((PhysicalLink) d).getLinkEnds());
        } else if (d instanceof ComponentPortAllocation) {
          result.add(((ComponentPortAllocation) d).getAllocatedPort());
        }
      }
    }
    return result;
  }

  public Collection<EObject> getPhysicalCategorySources(EObject context) {
    return getCache(this::getPhysicalCategorySourcesWithoutCache, context);
  }

  /**
   * Retrieve available targets for the given category (since we display ports of category, we return itself)
   * 
   * @param context
   * @return
   */
  public Collection<EObject> getPhysicalCategoryTargetsWithoutCache(EObject context) {
    return getPhysicalCategorySourcesWithoutCache(context);
  }

  public Collection<EObject> getPhysicalCategoryTargets(EObject context) {
    return getCache(this::getPhysicalCategoryTargetsWithoutCache, context);
  }

  public boolean isABComponentCategoryPortIsA(EObject context, DSemanticDecorator containerView, ComponentPortKind kind,
      OrientationPortKind orientation) {
    if (containerView instanceof AbstractDNode) {
      EObject sourcePart = CsServices.getService().getRelatedPart(containerView);
      // retrieve all CE for the related category
      for (ComponentExchange exchange : getCache(ABServices::getRelatedComponentExchanges2, sourcePart)) {
        if (exchange.getCategories().contains(context)) {
          Port port;
          if (ComponentExchangeExt.getSourceParts(exchange).contains(sourcePart)) {
            port = ComponentExchangeExt.getSourcePort(exchange);
          } else {
            port = ComponentExchangeExt.getTargetPort(exchange);
          }

          if (port instanceof ComponentPort) {
            ComponentPort cp = (ComponentPort) port;
            if (!((cp.getKind() == kind)
                && ((cp.getOrientation() == orientation) || (OrientationPortKind.UNSET == orientation)))) {
              return false;
            }
          }
        }
      }
      return true;
    }
    return false;
  }

  public boolean isABComponentCategoryPortStandard(EObject context, DSemanticDecorator containerView) {
    return isABComponentCategoryPortIsA(context, containerView, ComponentPortKind.STANDARD, OrientationPortKind.UNSET);
  }

  public boolean isABComponentCategoryPortIn(EObject context, DSemanticDecorator containerView) {
    return isABComponentCategoryPortIsA(context, containerView, ComponentPortKind.FLOW, OrientationPortKind.IN);
  }

  public boolean isABComponentCategoryPortOut(EObject context, DSemanticDecorator containerView) {
    return isABComponentCategoryPortIsA(context, containerView, ComponentPortKind.FLOW, OrientationPortKind.OUT);
  }

  public boolean isABComponentCategoryPortInOut(EObject context, DSemanticDecorator containerView) {
    return isABComponentCategoryPortIsA(context, containerView, ComponentPortKind.FLOW, OrientationPortKind.INOUT);
  }

  /**
   * Returns true if an arrow should be displayed onto source of category link
   * 
   * @param context
   * @return
   */
  public boolean isABComponentCategorySourceOriented(DSemanticDecorator context) {
    boolean result = true;
    EObject target = context.getTarget();
    if (context instanceof DEdge) {
      DEdge edge = (DEdge) context;
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
            Collection<EObject> unionParts = new HashSet<>();
            unionParts.addAll(sourceParts);
            unionParts.addAll(targetParts);

            if (unionParts.contains(sourcePart) && unionParts.contains(targetPart)) {
              if (exchange.isOriented()) {
                if (!(ComponentExchangeExt.getSourcePartsAndEntities(exchange).contains(targetPart)
                    && ComponentExchangeExt.getTargetPartsAndEntities(exchange).contains(sourcePart))) {
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
   * 
   * @param context
   * @return
   */
  public boolean isABComponentCategoryTargetOriented(DSemanticDecorator context) {
    boolean result = true;
    EObject target = context.getTarget();
    if (context instanceof DEdge) {
      DEdge edge = (DEdge) context;
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
            Collection<EObject> unionParts = new HashSet<>();
            unionParts.addAll(sourceParts);
            unionParts.addAll(targetParts);

            if (unionParts.contains(sourcePart) && unionParts.contains(targetPart)) {
              if (exchange.isOriented()) {
                if (!(ComponentExchangeExt.getSourcePartsAndEntities(exchange).contains(sourcePart)
                    && ComponentExchangeExt.getTargetPartsAndEntities(exchange).contains(targetPart))) {
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
   * @param context
   */
  public void updateABComponentCategories(DDiagramContents context) {
    Collection<DEdge> toRemoveEdges = new HashSet<>();
    Collection<AbstractDNode> toRemoveNodes = new HashSet<>();
    Collection<AbstractDNode> toHideNodes = new HashSet<>();

    DiagramElementMapping edgeMapping = context
        .getMapping(MappingConstantsHelper.getMappingABComponentCategory(context.getDDiagram()));
    DiagramElementMapping nodeMapping = context
        .getMapping(MappingConstantsHelper.getMappingABComponentCategoryPin(context.getDDiagram()));

    // Retrieve all invalid edges to be removed
    if (edgeMapping != null) {
      for (DDiagramElement element : getCache(context::getDiagramElements, edgeMapping)) {
        if (!(element instanceof DEdge)) {
          continue;
        }
        DEdge edge = (DEdge) element;

        boolean isValidEdge = isValidABComponentCategoryEdge((ComponentExchangeCategory) element.getTarget(),
            (DSemanticDecorator) edge.getSourceNode(), (DSemanticDecorator) edge.getTargetNode());
        if (!isValidEdge) {
          toRemoveEdges.add(edge);
        }
      }
    }

    // Retrieve all nodes without incoming/outgoing edges to be removed
    if (nodeMapping != null) {
      for (DDiagramElement element : getCache(context::getDiagramElements, nodeMapping)) {
        if (!(element instanceof EdgeTarget)) {
          continue;
        }
        Collection<DEdge> edges = new ArrayList<>();
        edges.addAll(((EdgeTarget) element).getIncomingEdges());
        edges.addAll(((EdgeTarget) element).getOutgoingEdges());

        if (edges.isEmpty()) {
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
    Collection<DiagramElementMapping> nodeMappings = context
        .getMappings(MappingConstantsHelper.getMappingABPorts(context.getDDiagram()));
    if (!nodeMappings.isEmpty()) {
      Iterable<DDiagramElement> diagElements = getCache(context::getDiagramElements, nodeMappings);
      for (DDiagramElement element : diagElements) {

        if (!(element instanceof EdgeTarget)) {
          continue;
        }
        Collection<DEdge> edges = new ArrayList<>();
        edges.addAll(((EdgeTarget) element).getIncomingEdges());
        edges.addAll(((EdgeTarget) element).getOutgoingEdges());

        if (!edges.isEmpty()) {
          int nbRemoved = 0;
          int nbHidden = 0;
          for (DEdge edge : edges) {

            if (!context.isVisible(edge)) {
              if (edge.getTarget() != null) {
                EObject target = edge.getTarget();
                if ((target instanceof ComponentExchange)
                    && !(((ComponentExchange) target).getCategories().isEmpty())) {
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
      context.deferredHide(node);
    }

    for (AbstractDNode node : toRemoveNodes) {
      DiagramServices.getDiagramServices().removeAbstractDNodeView(node);
    }

  }

  /**
   * @param context
   */
  public void updateABFunctionalCategories(DDiagramContents context) {
    DiagramElementMapping inputPinNodeMapping = context
        .getMapping(MappingConstantsHelper.getMappingFunctionalExchangeCategoryInputPin(context.getDDiagram()));
    DiagramElementMapping outputPinNodeMapping = context
        .getMapping(MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(context.getDDiagram()));
    // Retrieve all nodes without incoming/outgoing edges to be removed
    if (inputPinNodeMapping instanceof AbstractNodeMapping)
      DiagramServices.getDiagramServices().removeNodeWithoutEdges(context, (AbstractNodeMapping) inputPinNodeMapping);
    if (outputPinNodeMapping instanceof AbstractNodeMapping)
      DiagramServices.getDiagramServices().removeNodeWithoutEdges(context, (AbstractNodeMapping) outputPinNodeMapping);
  }

  /**
   * Remove invalid links and physical categories
   * 
   * @param context
   */
  public void updateABPhysicalCategories(DDiagramContents context) {
    Collection<DEdge> toRemoveEdges = new HashSet<>();
    Collection<AbstractDNode> toRemoveNodes = new HashSet<>();
    Collection<AbstractDNode> toHideNodes = new HashSet<>();

    DiagramElementMapping edgeMapping = context
        .getMapping(MappingConstantsHelper.getMappingABPhysicalCategory(context.getDDiagram()));
    Collection<DiagramElementMapping> nodeMappings = context
        .getMappings(MappingConstantsHelper.getMappingABPhysicalCategoryPin(context.getDDiagram()));

    if (edgeMapping != null) {
      for (DDiagramElement element : context.getDiagramElements(edgeMapping)) {
        if (!(element instanceof DEdge)) {
          continue;
        }
        DEdge edge = (DEdge) element;

        boolean isValidEdge = isValidABPhysicalCategoryEdge((PhysicalLinkCategory) element.getTarget(),
            (DSemanticDecorator) edge.getSourceNode(), (DSemanticDecorator) edge.getTargetNode());
        if (!isValidEdge) {
          toRemoveEdges.add(edge);
        }
      }
    }

    for (DiagramElementMapping nodeMapping : nodeMappings) {
      for (DDiagramElement element : getCache(context::getDiagramElements, nodeMapping)) {
        if (!(element instanceof EdgeTarget)) {
          continue;
        }
        Collection<DEdge> edges = new ArrayList<>();
        edges.addAll(((EdgeTarget) element).getIncomingEdges());
        edges.addAll(((EdgeTarget) element).getOutgoingEdges());

        if (edges.isEmpty()) {
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

    nodeMappings = context.getMappings(MappingConstantsHelper.getMappingABPorts(context.getDDiagram()));
    for (DiagramElementMapping nodeMapping : nodeMappings) {
      for (DDiagramElement element : getCache(context::getDiagramElements, nodeMapping)) {
        if (!(element instanceof EdgeTarget)) {
          continue;
        }
        Collection<DEdge> edges = new ArrayList<>();
        edges.addAll(((EdgeTarget) element).getIncomingEdges());
        edges.addAll(((EdgeTarget) element).getOutgoingEdges());

        if (!edges.isEmpty()) {
          int nbRemoved = 0;
          int nbHidden = 0;
          for (DEdge edge : edges) {
            if (!context.isVisible(edge)) {
              if (edge.getTarget() != null) {
                EObject target = edge.getTarget();
                if ((target instanceof ComponentExchange)
                    && !(((ComponentExchange) target).getCategories().isEmpty())) {
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
      context.deferredHide(node);
    }

    for (AbstractDNode node : toRemoveNodes) {
      DiagramServices.getDiagramServices().removeAbstractDNodeView(node);
    }

  }

  /**
   * Retrieve a displayable text for the given category
   * 
   * @param property
   * @return
   */
  public String computeComponentCategoryLabel(ComponentExchangeCategory property) {
    return property.getName();
  }

  /**
   * Retrieve a displayable text for the given category
   * 
   * @param property
   * @return
   */
  public String computePhysicalCategoryLabel(PhysicalLinkCategory property) {
    return property.getName();
  }

  /**
   * Create a component exchange in an architecture blank diagram. Create port if selected views are not targeting port
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createABComponentPortAllocation(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {
    EObject sourceTarget = sourceView.getTarget();
    EObject targetTarget = targetView.getTarget();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);

    EdgeTarget nodeSource = null;
    EdgeTarget nodeTarget = null;

    ComponentPortAllocation exchange = null;

    InformationsExchanger sourceRelatedPart = CsServices.getService().getRelatedPart(sourceView);
    InformationsExchanger targetRelatedPart = CsServices.getService().getRelatedPart(targetView);

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
      nodeSource = (EdgeTarget) sourceView;
    }

    // Create or retrieve targetPort
    ComponentPort targetPort = null;
    if (targetTarget instanceof ComponentPort) {
      targetPort = (ComponentPort) targetTarget;
      nodeTarget = (EdgeTarget) targetView;
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
    DiagramServices.getDiagramServices().createEdge(
        FaServices.getFaServices().getMappingABComponentPortAllocation(diagram), nodeSource, nodeTarget, exchange);
    return context;
  }

  /**
   * Create a port allocation in an architecture blank diagram. Create port if selected views are not targeting port
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createABPortAllocation(EObject context, DSemanticDecorator sourceView, DSemanticDecorator targetView) {
    EObject sourceTarget = sourceView.getTarget();
    EObject targetTarget = targetView.getTarget();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);

    EdgeTarget nodeSource = null;
    EdgeTarget nodeTarget = null;

    PortAllocation exchange = null;

    // Create or retrieve sourcePort
    ComponentPort sourcePort = null;
    if (sourceTarget instanceof ComponentPort) {
      sourcePort = (ComponentPort) sourceTarget;
      nodeSource = (EdgeTarget) sourceView;
    }

    // Create or retrieve targetPort
    FunctionPort targetPort = null;
    if (targetTarget instanceof FunctionPort) {
      targetPort = (FunctionPort) targetTarget;
      nodeTarget = (EdgeTarget) targetView;
    }

    exchange = InformationFactory.eINSTANCE.createPortAllocation();

    exchange.setSourceElement(sourcePort);
    exchange.setTargetElement(targetPort);

    // Attach to parent
    Port container = sourcePort;
    if ((container != null) && !container.equals(exchange.eContainer())) {
      (container).getOwnedPortAllocations().add(exchange);
    }

    CapellaServices.getService().creationService(exchange);
    DiagramServices.getDiagramServices().createEdge(FaServices.getFaServices().getMappingABPortAllocation(diagram),
        nodeSource, nodeTarget, exchange);
    return context;
  }

  /**
   * Retrieve the edge mapping name for the given diagram
   * 
   * @param diagram
   * @return
   */
  @Deprecated
  public EdgeMapping getMappingABComponentCategory(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABComponentCategory(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  /**
   * Retrieve the edge mapping name for the given diagram
   * 
   * @param diagram
   * @return
   */
  @Deprecated
  public EdgeMapping getMappingABPhysicalCategory(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABPhysicalCategory(diagram);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  /**
   * Retrieve the pin view mapping name for the given diagram
   * 
   * @param diagram
   * @return
   */
  @Deprecated
  public NodeMapping getMappingABComponentCategoryPin(DDiagram diagram) {
    String mappingName = MappingConstantsHelper.getMappingABComponentCategoryPin(diagram);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
  }

  /**
   * Retrieve the pin view mapping names for the given diagram
   * 
   * @param diagram
   * @return
   */
  @Deprecated
  public List<NodeMapping> getMappingABPhysicalCategoryPin(DDiagram diagram) {
    List<String> mappingNames = MappingConstantsHelper.getMappingABPhysicalCategoryPin(diagram);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingNames);
  }

  /**
   * Retrieve the pin view mapping name for the given diagram and the semantic element
   * 
   * @param diagram
   * @return
   */
  @Deprecated
  public NodeMapping getMappingABPhysicalCategoryPin(DDiagram diagram, EObject semantic) {
    String mappingName = MappingConstantsHelper.getMappingABPhysicalCategoryPin(diagram, semantic);
    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
  }

  public Object getABFunctionalExchangeSemanticCandidates(DDiagram diagram) {
    return DFServices.getService().getDFFunctionalExchangeSemanticCandidates(diagram);
  }

  public Object getABPhysicalLinkSemanticCandidates(DDiagram diagram) {
    Collection<PhysicalLink> result = new ArrayList<>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllNodeContainers(diagram)) {
      if (dNode instanceof AbstractDNode) {
        EObject target = dNode.getTarget();
        if (target instanceof Part) {
          result.addAll(getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, (Part) target));
        } else if (target instanceof Component) {
          result.addAll(getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, (Component) target));
        }
      }
    }

    return result;
  }

  public Object getABComponentExchangeSemanticCandidates(DDiagram diagram) {
    Collection<ComponentExchange> result = new ArrayList<>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllNodeContainers(diagram)) {
      EObject target = dNode.getTarget();
      if (target instanceof Part) {
        result.addAll(ComponentExt.getAllRelatedComponentExchange((Part) target, true));
      } else if (target instanceof Component) {
        result.addAll(getCache(ComponentExt::getAllRelatedComponentExchange, (Component) target));
      }
    }

    return result;
  }

  public Object getABPortAllocationSemanticCandidates(DDiagram diagram) {
    Collection<EObject> result = new ArrayList<>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, true)) {
      EObject target = dNode.getTarget();
      if (target instanceof Port) {
        result.addAll(((Port) target).getOutgoingPortAllocations());
      }
    }

    return result;
  }

  public Object getABComponentPortAllocationSemanticCandidates(DDiagram diagram) {
    Collection<ComponentPortAllocation> result = new ArrayList<>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, true)) {
      EObject target = dNode.getTarget();
      if (target instanceof PhysicalPort) {
        for (AbstractTrace trace : ((PhysicalPort) target).getOutgoingTraces()) {
          if (trace instanceof ComponentPortAllocation) {
            result.add((ComponentPortAllocation) trace);
          }
        }
      }
    }

    return result;
  }

  public EObject getABDeployTarget(DSemanticDecorator decorator) {
    EObject component = CsServices.getService().getComponentType(decorator);
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(component);
    return architecture.getSystem();
  }

  /**
   * Display given component port allocations
   */
  public EObject showABComponentPortAllocations(Collection<EObject> elements, DDiagramContents content) {

    AbstractShowHide shService = new ShowHideABComponentPortAllocation(content);

    for (EObject element : elements) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content.getDDiagram();
  }

  /**
   * Hide given component port allocations
   */
  public EObject hideABComponentPortAllocations(Collection<EObject> elements, DDiagramContents content) {

    AbstractShowHide shService = new ShowHideABComponentPortAllocation(content);

    for (EObject element : elements) {
      DiagramContext context = shService.new DiagramContext();
      shService.hide(element, context);
    }

    return content.getDDiagram();
  }

  /**
   * Display given component port allocations
   */
  public EObject showABPortAllocations(Collection<EObject> elements, DDiagramContents content) {
    AbstractShowHide shService = new ShowHideABPortAllocation(content);

    for (EObject element : elements) {
      DiagramContext context = shService.new DiagramContext();
      shService.show(element, context);
    }

    return content.getDDiagram();
  }

  /**
   * Hide given component port allocations
   */
  public EObject hideABPortAllocations(Collection<EObject> elements, DDiagramContents content) {

    AbstractShowHide shService = new ShowHideABPortAllocation(content);

    for (EObject element : elements) {
      DiagramContext context = shService.new DiagramContext();
      shService.hide(element, context);
    }

    return content.getDDiagram();
  }

  public static Collection<ComponentExchange> getRelatedComponentExchanges2(EObject element) {
    return getService().getRelatedComponentExchanges(element);
  }

  public static Collection<EObject> getComponentExchangeCategories(DDiagram diagram) {
    Collection<EObject> categories = new HashSet<>();
    DDiagramContents context = new DDiagramContents(diagram);
    DiagramElementMapping edgeMapping = context
        .getMapping(MappingConstantsHelper.getMappingABComponentCategory(context.getDDiagram()));
    if (edgeMapping != null) {
      for (DDiagramElement element : context.getDiagramElements(edgeMapping)) {
        if (element.getTarget() instanceof ComponentExchangeCategory) {
          categories.add(element.getTarget());
        }
      }
    }
    return categories;
  }

  public static Collection<ExchangeCategory> getExchangeCategories(DDiagram diagram) {
    Collection<ExchangeCategory> categories = new HashSet<>();
    DDiagramContents context = new DDiagramContents(diagram);
    DiagramElementMapping edgeMapping = context
        .getMapping(MappingConstantsHelper.getMappingFunctionalExchangeCategory(diagram));
    if (edgeMapping != null) {
      for (DDiagramElement element : context.getDiagramElements(edgeMapping)) {
        EObject target = element.getTarget();
        if (target instanceof ExchangeCategory) {
          categories.add((ExchangeCategory) target);
        }
      }
    }
    return categories;
  }

  public static Collection<EObject> getPhysicalLinkCategory(DDiagram diagram) {
    Collection<EObject> categories = new HashSet<>();
    DDiagramContents context = new DDiagramContents(diagram);
    DiagramElementMapping edgeMapping = context
        .getMapping(MappingConstantsHelper.getMappingABPhysicalCategory(context.getDDiagram()));
    if (edgeMapping != null) {
      for (DDiagramElement element : context.getDiagramElements(edgeMapping)) {
        if (element.getTarget() instanceof PhysicalLinkCategory) {
          categories.add(element.getTarget());
        }
      }
    }
    return categories;
  }

}
