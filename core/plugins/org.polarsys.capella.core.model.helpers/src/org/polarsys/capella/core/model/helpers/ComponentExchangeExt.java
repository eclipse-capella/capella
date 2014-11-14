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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;

/**
 *
 */
public class ComponentExchangeExt {

  /**
   * Return source port of the connection (mono Part mode)
   * @param connection_p
   * @return Part
   * @deprecated use the derived reference available on component exchanges
   */
  @Deprecated
  public static Port getSourcePort(ComponentExchange connection_p) {
    InformationsExchanger source = connection_p.getSource();
    if (source instanceof ComponentExchangeEnd) {
      return ((ComponentExchangeEnd) source).getPort();
    } else if (source instanceof Port) {
      return (Port) source;
    }

    return null;
  }

  /**
   * Return target port of the connection (mono Part mode)
   * @param connection_p
   * @return
   * @deprecated use the derived reference available on component exchanges
   */
  @Deprecated
  public static Port getTargetPort(ComponentExchange connection_p) {
    InformationsExchanger target = connection_p.getTarget();
    if (target instanceof ComponentExchangeEnd) {
      return ((ComponentExchangeEnd) target).getPort();
    } else if (target instanceof Port) {
      return (Port) target;
    }

    return null;
  }

  /**
   * Returns source Part of the connection (MultiPart mode) if component exchange is not linked to a part, returns null. (don't returns the first representing
   * partition of connected source component)
   * @param connection_p
   * @return
   * @deprecated use the derived reference available on component exchanges
   */
  @Deprecated
  public static Part getSourcePart(ComponentExchange connection_p) {
    InformationsExchanger source = connection_p.getSource();
    if (source instanceof ComponentExchangeEnd) {
      return (Part) ((ComponentExchangeEnd) source).getPart();
    } else if (source instanceof Part) {
      return (Part) source;
    }

    return null;
  }

  /**
   * Returns source Parts of the connection If component exchange is related to one part, returns a singleton of related part. If component exchange is related
   * to a component, returns representing parts of the component
   */
  public static Collection<Part> getSourceParts(ComponentExchange connection_p) {
    Part part = getSourcePart(connection_p);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component sourceComponent = getSourceComponent(connection_p);
    if (sourceComponent != null) {
      return ComponentExt.getRepresentingParts(sourceComponent);
    }
    return Collections.emptyList();
  }

  /**
   * Returns source Parts of the connection If component exchange is related to one part, returns a singleton of related part. If component exchange is related
   * to a component, returns representing parts of the component
   */
  public static Collection<? extends EObject> getSourcePartsAndEntities(ComponentExchange connection_p) {
    if (connection_p.getSource() instanceof Entity) {
      return Collections.singletonList(connection_p.getSource());
    }
    return getSourceParts(connection_p);
  }

  /**
   * Return target Part of the connection (MultiPart mode) if component exchange is not linked to a part, returns null. (don't returns the first representing
   * partition of connected target component)
   * @param connection_p
   * @return
   * @deprecated use the derived reference available on component exchanges
   */
  @Deprecated
  public static Part getTargetPart(ComponentExchange connection_p) {
    InformationsExchanger target = connection_p.getTarget();
    if (target instanceof ComponentExchangeEnd) {
      return (Part) ((ComponentExchangeEnd) target).getPart();
    } else if (target instanceof Part) {
      return (Part) target;
    }
    return null;
  }

  /**
   * Returns target Parts of the connection If component exchange is related to one part, returns a singleton of related part. If component exchange is related
   * to a component, returns representing parts of the component
   */
  public static Collection<Part> getTargetParts(ComponentExchange connection_p) {
    Part part = getTargetPart(connection_p);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component targetComponent = getTargetComponent(connection_p);
    if (targetComponent != null) {
      return ComponentExt.getRepresentingParts(targetComponent);
    }
    return Collections.emptyList();
  }

  /**
   * Returns target Parts of the connection If component exchange is related to one part, returns a singleton of related part. If component exchange is related
   * to a component, returns representing parts of the component
   */
  public static Collection<? extends EObject> getTargetPartsAndEntities(ComponentExchange connection_p) {
    if (connection_p.getTarget() instanceof Entity) {
      return Collections.singletonList(connection_p.getTarget());
    }
    return getTargetParts(connection_p);
  }

  /**
   * Return opposite source port of the connection
   * @param connection_p
   * @param port
   * @return Port
   */
  public static Port getOppositePort(ComponentExchange connection_p, Port port) {
    Port sourcePort = getSourcePort(connection_p);
    if ((null != sourcePort) && sourcePort.equals(port)) {
      return getTargetPort(connection_p);
    }

    return sourcePort;
  }

  /**
   * Return best source element of the Connection
   * @param connection_p
   * @return ModelElement
   */
  public static Component getSourceComponent(ComponentExchange connection_p) {
    InformationsExchanger source = connection_p.getSource();
    // connection end
    if (source instanceof ComponentExchangeEnd) {
      Partition part = ((ComponentExchangeEnd) source).getPart();
      if (null != part) {
        AbstractType abstractType = part.getType();
        if ((null != abstractType) && (abstractType instanceof Component)) {
          return (Component) abstractType;
        }
      }
    }
    // part
    else if (source instanceof Part) {
      Part part = (Part) source;
      if (null != part) {
        AbstractType abstractType = part.getType();
        if ((null != abstractType) && (abstractType instanceof Component)) {
          return (Component) abstractType;
        }
      }
    }
    // component port
    else if (source instanceof ComponentPort) {
      EObject eContainer = source.eContainer();
      if ((null != eContainer) && (eContainer instanceof Component)) {
        return (Component) eContainer;
      }
    }// physcialPort (kind delegation connection)
    else if (source instanceof PhysicalPort) {
      EObject eContainer = source.eContainer();
      if ((null != eContainer) && (eContainer instanceof Component)) {
        return (Component) eContainer;
      }
    } else if (source instanceof Component) {
      Component eContainer = (Component) source;
      if ((null != eContainer)) {
        return eContainer;
      }
    }

    return null;
  }

  /**
   * Return best target element of the Connection
   * @param connection_p
   * @return ModelElement
   */
  public static Component getTargetComponent(ComponentExchange connection_p) {
    InformationsExchanger target = connection_p.getTarget();
    // connection end
    if (target instanceof ComponentExchangeEnd) {
      Partition part = ((ComponentExchangeEnd) target).getPart();
      if (null != part) {
        AbstractType abstractType = part.getType();
        if ((null != abstractType) && (abstractType instanceof Component)) {
          return (Component) abstractType;
        }
      }
    }
    // part
    else if (target instanceof Part) {
      Part part = (Part) target;
      if (null != part) {
        AbstractType abstractType = part.getType();
        if ((null != abstractType) && (abstractType instanceof Component)) {
          return (Component) abstractType;
        }
      }
    }
    // component port
    else if (target instanceof ComponentPort) {
      EObject eContainer = target.eContainer();
      if ((null != eContainer) && (eContainer instanceof Component)) {
        return (Component) eContainer;
      }

    }// physcialPort (kind delegation connection)
    else if (target instanceof PhysicalPort) {
      EObject eContainer = target.eContainer();
      if ((null != eContainer) && (eContainer instanceof Component)) {
        return (Component) eContainer;
      }
    } else if (target instanceof Component) {
      Component eContainer = (Component) target;
      if ((null != eContainer)) {
        return eContainer;
      }
    }

    return null;
  }

  /**
   * Returns whether the connection is valid (no null bounds) and linked to an actor
   * @param object_p
   * @return
   */
  public static boolean isLinkToAnActor(ComponentExchange connection_p) {
    Component source = getSourceComponent(connection_p);
    Component target = getTargetComponent(connection_p);
    return (source != null) && (target != null) && ((source instanceof AbstractActor) || (target instanceof AbstractActor));
  }

  /**
   * Returns whether the connection is valid (no null bounds) and not linked to an actor
   * @param object_p
   * @return
   */
  public static boolean isNotLinkToAnActor(ComponentExchange connection_p) {
    Component source = getSourceComponent(connection_p);
    Component target = getTargetComponent(connection_p);
    return (source != null) && (target != null) && !((source instanceof AbstractActor) || (target instanceof AbstractActor));
  }

  /**
   * Return filtered list of Connection by kinds
   * @param Objects_p
   * @param kinds_p
   * @return list of Objects
   */
  public static List<Object> filteredComponentExchangesBykind(List<Object> Objects_p, ComponentExchangeKind[] kinds_p) {
    List<Object> results = new ArrayList<Object>();
    List<ComponentExchangeKind> kinds = Arrays.asList(kinds_p);
    for (Object object : Objects_p) {
      if (object instanceof ComponentExchange) {
        ComponentExchangeKind currentKind = ((ComponentExchange) object).getKind();
        if (kinds.contains(currentKind)) {
          results.add(object);
        }
      }
    }
    return results;
  }

  /**
   * Create a componentExchange from both parts. create ports if unset or use given ports.
   * @param sourcePart_p
   * @param sourcePort_p
   * @param targetPart_p
   * @param targetPort_p
   * @param kind_p
   * @return
   */
  public static ComponentExchange createComponentExchange(Part sourcePart_p, ComponentPort sourcePort_p, Part targetPart_p, ComponentPort targetPort_p,
      ComponentExchangeKind kind_p, ComponentPortKind sourceKind_p, OrientationPortKind sourceOrientation_p, ComponentPortKind targetKind_p,
      OrientationPortKind targetOrientation_p, boolean isMultipart_p) {

    boolean isMultipart = isMultipart_p;
    ComponentPort portSource = sourcePort_p;
    if (portSource == null) {
      Component component = (Component) sourcePart_p.getAbstractType();
      portSource = FaFactory.eINSTANCE.createComponentPort();
      portSource.setKind(sourceKind_p);
      portSource.setOrientation(sourceOrientation_p);
      component.getOwnedFeatures().add(portSource);
      CapellaElementExt.creationService(portSource);
    }

    ComponentPort portTarget = targetPort_p;
    if (portTarget == null) {
      Component component = (Component) targetPart_p.getAbstractType();
      portTarget = FaFactory.eINSTANCE.createComponentPort();
      portTarget.setKind(targetKind_p);
      portTarget.setOrientation(targetOrientation_p);
      component.getOwnedFeatures().add(portTarget);
      CapellaElementExt.creationService(portTarget);
    }

    ComponentExchange componentExchange = FaFactory.eINSTANCE.createComponentExchange();
    componentExchange.setKind(kind_p);
    attachTo(componentExchange, getDefaultContainer(sourcePart_p, targetPart_p));

    if ((!isMultipart) || (kind_p == ComponentExchangeKind.DELEGATION)) {
      componentExchange.setSource(portSource);
    } else {
      ComponentExchangeEnd end = FaFactory.eINSTANCE.createComponentExchangeEnd();
      end.setPort(portSource);
      end.setPart(sourcePart_p);
      componentExchange.getOwnedComponentExchangeEnds().add(end);
      componentExchange.setSource(end);
    }

    if (!isMultipart) {
      componentExchange.setTarget(portTarget);
    } else {
      ComponentExchangeEnd end = FaFactory.eINSTANCE.createComponentExchangeEnd();
      end.setPort(portTarget);
      end.setPart(targetPart_p);
      componentExchange.getOwnedComponentExchangeEnds().add(end);
      componentExchange.setTarget(end);
    }

    CapellaElementExt.creationService(componentExchange);
    return componentExchange;
  }

  /**
   * Attach the given component exchange to the given abstract functional block
   * @param exchange_p
   * @param container_p
   * @return
   */
  public static boolean attachTo(ComponentExchange exchange_p, AbstractFunctionalBlock container_p) {
    if ((container_p != null) && !container_p.equals(exchange_p.eContainer())) {
      if ((container_p instanceof Entity) && (exchange_p instanceof CommunicationMean)) {
        ((Entity) container_p).getOwnedCommunicationMeans().add((CommunicationMean) exchange_p);
      } else {
        (container_p).getOwnedComponentExchanges().add(exchange_p);
      }
      return true;
    }
    return false;
  }

  /**
   * Move the given component exchange to common ancestor
   * @param exchange_p
   * @return whether the component exchange has been moved
   */
  public static boolean attachToDefaultContainer(ComponentExchange exchange_p) {
    return attachTo(exchange_p, getDefaultContainer(exchange_p));
  }

  /**
   * Return the best container between both given elements
   * @param sourcePart_p a part or a component
   * @param targetPart_p a part or a component
   * @return a not null element
   */
  public static AbstractFunctionalBlock getDefaultContainer(CapellaElement sourcePart_p, CapellaElement targetPart_p) {
    EObject container = ComponentExt.getFirstCommonComponentAncestor(sourcePart_p, targetPart_p);
    if ((container != null) && !(container instanceof AbstractFunctionalBlock)) {
      container = EcoreUtil2.getFirstContainer(container, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK);
    }
    if ((container == null) || !(container instanceof AbstractFunctionalBlock)) {
      container = BlockArchitectureExt.getContext(ComponentExt.getRootBlockArchitecture(sourcePart_p));
    }
    return (AbstractFunctionalBlock) container;
  }

  /**
   * The best container is the common ancestor of source/target parts. if no parts, we use common ancestor of components (which can happen in OA or if user has
   * deleted parts)
   * @param exchange_p
   * @return a not null element
   */
  public static AbstractFunctionalBlock getDefaultContainer(ComponentExchange exchange_p) {
    CapellaElement source = ComponentExchangeExt.getSourceComponent(exchange_p);
    Collection<Part> parts = ComponentExchangeExt.getSourceParts(exchange_p);
    if (!parts.isEmpty()) {
      source = parts.iterator().next();
    }

    CapellaElement target = ComponentExchangeExt.getTargetComponent(exchange_p);
    parts = ComponentExchangeExt.getTargetParts(exchange_p);
    if (!parts.isEmpty()) {
      target = parts.iterator().next();
    }

    return getDefaultContainer(source, target);
  }

  /**
   * The best container for storing a category for the given exchange
   * @param exchange_p
   * @return
   */
  public static AbstractFunctionalBlock getDefaultContainerForCategory(ComponentExchange exchange_p) {
    CapellaElement source = ComponentExchangeExt.getSourceComponent(exchange_p);
    Collection<Part> parts = ComponentExchangeExt.getSourceParts(exchange_p);
    if (!parts.isEmpty()) {
      source = parts.iterator().next();
    }

    CapellaElement target = ComponentExchangeExt.getTargetComponent(exchange_p);
    parts = ComponentExchangeExt.getTargetParts(exchange_p);
    if (!parts.isEmpty()) {
      target = parts.iterator().next();
    }

    return getDefaultContainerForCategory(source, target);
  }

  /**
   * Return the best container for storing a category for an exchange between both given elements
   * @param sourcePart_p
   * @param targetPart_p
   * @return
   */
  public static AbstractFunctionalBlock getDefaultContainerForCategory(CapellaElement sourcePart_p, CapellaElement targetPart_p) {
    EObject container = ComponentExt.getFirstCommonComponentAncestor(sourcePart_p, targetPart_p);
    if ((container != null) && !(container instanceof AbstractFunctionalBlock)) {
      container =
          EcoreUtil2.getFirstContainer(container,
              Arrays.asList(FaPackage.eINSTANCE.getAbstractFunctionalBlock(), FaPackage.eINSTANCE.getAbstractFunctionalStructure()));
    }
    if ((container == null) || !(container instanceof AbstractFunctionalBlock)) {
      container = BlockArchitectureExt.getContext(ComponentExt.getRootBlockArchitecture(sourcePart_p));
    }
    return (AbstractFunctionalBlock) container;
  }

  public static Collection<? extends ComponentExchange> getDelegatedComponentExchanges(ComponentPort port, ComponentExchange delegation_p) {
    Port cp = null;
    if (delegation_p.getSourcePort().equals(port)) {
      cp = delegation_p.getTargetPort();
    } else {
      cp = delegation_p.getSourcePort();
    }

    List<ComponentExchange> result = new ArrayList<ComponentExchange>();
    if (cp instanceof ComponentPort) {
      // The "pivot" port is available, find the ports that are not from the delegation.
      for (ComponentExchange ce : ((ComponentPort) cp).getComponentExchanges()) {
        if (ce != delegation_p) {
          if (ce.getKind().equals(ComponentExchangeKind.DELEGATION)) {
            result.addAll(getDelegatedComponentExchanges((ComponentPort) cp, ce));
          } else {
            result.add(ce);
          }
        }
      }
    }
    return result;
  }

  /**
   * Get every port allocations between ports of given exchanges.
   * @param cExchange_p
   * @param fExchange_p
   * @return a list of port allocations.
   */
  private static List<PortAllocation> getPortAllocations(ComponentExchange cExchange_p, FunctionalExchange fExchange_p) {
    List<PortAllocation> portAllocations = new ArrayList<PortAllocation>();
    // Get all ports allocations associated to the component exchange ports (source and target).
    List<PortAllocation> portAllocationsToCheck = new ArrayList<PortAllocation>();
    Port sourceComponentPort = cExchange_p.getSourcePort();
    if (null != sourceComponentPort) {
      // Check for null value (this can occur with projectApproach=ReusableComponents).
      portAllocationsToCheck.addAll(sourceComponentPort.getOutgoingPortAllocations());
    }
    Port targetComponentPort = cExchange_p.getTargetPort();
    if (null != targetComponentPort) {
      portAllocationsToCheck.addAll(targetComponentPort.getOutgoingPortAllocations());
    }
    // Keep only port allocations associated to a port of the given functional exchange.
    for (PortAllocation portAllocation : portAllocationsToCheck) {
      if (portAllocation.getTargetElement().equals(fExchange_p.getSource()) || portAllocation.getTargetElement().equals(fExchange_p.getTarget())) {
        portAllocations.add(portAllocation);
      }
    }
    return portAllocations;
  }

  /**
   * Returns whether the given component exchange is a Delegation
   * @param exchange_p
   * @return
   */
  public static boolean isDelegation(ComponentExchange exchange_p) {
    return exchange_p.getKind() == ComponentExchangeKind.DELEGATION;
  }

  /**
   * Return valid FE available for allocation
   * @param element_p
   * @return
   */
  public static List<CapellaElement> getValidFEAvailableForAllocation(ComponentExchange element_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(1);
    // Source{Component}
    AbstractFunctionalBlock source = null;
    // Target{Component}
    AbstractFunctionalBlock target = null;

    Port sourcePort = ComponentExchangeExt.getSourcePort(element_p);
    Port targetPort = ComponentExchangeExt.getTargetPort(element_p);

    // toSource: target port should not be IN, source port should not be OUT
    // toTarget: source port should not be IN, target port should not be OUT
    // If both source/target are without port, we allow only toTarget

    boolean compTargetWithSource =
        (targetPort == null) || ((targetPort instanceof ComponentPort) && (!(((ComponentPort) targetPort).getOrientation() == OrientationPortKind.IN)));

    boolean compSourceWithTarget =
        ((sourcePort == null) || ((sourcePort instanceof ComponentPort) && (!(((ComponentPort) sourcePort).getOrientation() == OrientationPortKind.IN))));

    boolean toSource =
        compTargetWithSource && (((sourcePort instanceof ComponentPort) && (!(((ComponentPort) sourcePort).getOrientation() == OrientationPortKind.OUT))));

    boolean toTarget =
        compSourceWithTarget
            && ((targetPort == null) || ((targetPort instanceof ComponentPort) && (!(((ComponentPort) targetPort).getOrientation() == OrientationPortKind.OUT))));

    InformationsExchanger informationsExchangerSource = element_p.getSource();
    InformationsExchanger informationsExchangerTarget = element_p.getTarget();

    if ((informationsExchangerSource != null) && (informationsExchangerTarget != null)) {
      source = ComponentExchangeExt.getSourceComponent(element_p);
      target = ComponentExchangeExt.getTargetComponent(element_p);

      if (toTarget) {
        // add all available functional Exchanges from source to target
        result.addAll(AbstractFunctionExt.getAllAllocatedFunctionalExchangeFiltered(source, target));
      }

      if (toSource) {
        // add all available functional Exchanges from target to source
        result.addAll(AbstractFunctionExt.getAllAllocatedFunctionalExchangeFiltered(target, source));
      }
    }
    return result;
  }

  /**
   * Given Functional Exchange (FE) has been allocated to given Component Exchange (CE) -> allocate Component Ports to Function Ports.<br>
   * There are two possibilities to allocate ports:
   * <ol>
   * <li>CE Source Port -> FE Source Port (FOP) and CE Target Port -> FE Target Port (FIP),</li>
   * <li>CE Source Port -> FE Target Port (FIP) and CE Target Port -> FE Source Port (FOP).</li>
   * </ol>
   * Actually a functional port is allocated to the component port of the component "containing" the function (as displayed in diagrams SAB, LAB or PAB),
   * input/output feature of ports are not taken into account.<br>
   * So :
   * <ol>
   * <li>If the source component "contains" the source function and the target component "contains" the target function, the allocation 1 is done,</li>
   * <li>If the source component "contains" the target function and the target component "contains" the source function, the allocation 2 is done.</li>
   * </ol>
   * If both allocations are realizable, the first one is done.
   * @param cExchange_p the ComponentExchange using ports to allocate,
   * @param fExchange_p the FunctionalExchange using ports to be allocated.
   */
  public static void synchronizePortAllocations(ComponentExchange cExchange_p, FunctionalExchange fExchange_p) {

    Port sourceComponentPort = ComponentExchangeExt.getSourcePort(cExchange_p);
    Port targetComponentPort = ComponentExchangeExt.getTargetPort(cExchange_p);

    if ((sourceComponentPort != null) && (targetComponentPort != null) && (sourceComponentPort instanceof InformationsExchanger)
        && (targetComponentPort instanceof InformationsExchanger)) {
      Component sourceComponent = ComponentExchangeExt.getSourceComponent(cExchange_p);
      Component targetComponent = ComponentExchangeExt.getTargetComponent(cExchange_p);

      AbstractFunction sourceFunction = FunctionalExchangeExt.getSourceFunction(fExchange_p);
      AbstractFunction targetFunction = FunctionalExchangeExt.getTargetFunction(fExchange_p);

      // Get all nested components (as displayed in diagrams) of the source component .
      List<Component> sourceComponentAndSubComponents = ComponentExt.getAllSubUsedAndDeployedComponents(sourceComponent);
      // Get all components allocating the source function.
      List<AbstractFunctionalBlock> sourceFunctionAllocatingComponents = sourceFunction.getAllocationBlocks();

      // Get all nested components (as displayed in diagrams) of the target component.
      List<Component> targetComponentAndSubComponents = ComponentExt.getAllSubUsedAndDeployedComponents(targetComponent);
      // Get all components allocating the target function.
      List<AbstractFunctionalBlock> targetFunctionAllocatingComponents = targetFunction.getAllocationBlocks();

      if (!Collections.disjoint(sourceComponentAndSubComponents, sourceFunctionAllocatingComponents)
          && !Collections.disjoint(targetComponentAndSubComponents, targetFunctionAllocatingComponents)) {
        // Source component "contains" source function and target component "contains" target function :
        // Allocations : Source component port -> source function port + Target component port -> target function port.
        synchronizeAllocations((InformationsExchanger) sourceComponentPort, fExchange_p.getSource());
        synchronizeAllocations((InformationsExchanger) targetComponentPort, fExchange_p.getTarget());
      } else if (!Collections.disjoint(sourceComponentAndSubComponents, targetFunctionAllocatingComponents)
                 && !Collections.disjoint(targetComponentAndSubComponents, sourceFunctionAllocatingComponents)) {
        // Source component "contains" target function and target component "contains" source function.
        // Allocations : Source component port -> target function port + Target component port -> source function port.
        synchronizeAllocations((InformationsExchanger) sourceComponentPort, fExchange_p.getTarget());
        synchronizeAllocations((InformationsExchanger) targetComponentPort, fExchange_p.getSource());
      }
    }
  }

  /**
   * Get ports allocations betw
   * @param cExchange_p
   * @param fExchange_p
   * @param forceCleaning_p TODO it seems that this parameter is always set to true (else in the two unsynchronizeAllocations methods which seem not used).
   * @return
   */
  public static List<? extends ModelElement> evaluateImpactsOfUnsynchronizeAllocations(ComponentExchange cExchange_p, FunctionalExchange fExchange_p,
      boolean forceCleaning_p) {
    if (forceCleaning_p) {
      return getPortAllocations(cExchange_p, fExchange_p);
    }

    List<ModelElement> result = new ArrayList<ModelElement>();
    if (getExchangesFrom(cExchange_p, fExchange_p.getSource()).isEmpty()) {
      result.addAll(unsynchronizeAllocations(cExchange_p.getSource(), fExchange_p.getSource()));
    }
    if (getExchangesFrom(cExchange_p, fExchange_p.getTarget()).isEmpty()) {
      result.addAll(unsynchronizeAllocations(cExchange_p.getTarget(), fExchange_p.getTarget()));
    }
    return result;
  }

  /**
   * @param cPort_p
   * @param fPort_p
   */
  private static void synchronizeAllocations(InformationsExchanger cPort_p, ActivityNode fPort_p) {
    if (cPort_p instanceof ComponentPort) {
      PortExt.attachPort((ComponentPort) cPort_p, fPort_p);
    }
  }

  /**
   * @param cPort_p
   * @param fPort_p
   */
  private static List<ModelElement> unsynchronizeAllocations(InformationsExchanger cPort_p, ActivityNode fPort_p) {
    List<ModelElement> result = new ArrayList<ModelElement>();
    PortAllocation allocation = getPortAllocation(cPort_p, fPort_p);
    if (null != allocation) {
      result.add(allocation);
    }
    return result;
  }

  /**
   * Retrieves all the functional exchanges related to the given component exchange and the given function port
   * @param cExchange_p
   * @param fPort_p
   * @return
   */
  private static List<FunctionalExchange> getExchangesFrom(ComponentExchange cExchange_p, ActivityNode fPort_p) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    if (null != fPort_p) {
      for (FunctionalExchange exchange : cExchange_p.getAllocatedFunctionalExchanges()) {
        ActivityNode nodeSource = exchange.getSource();
        ActivityNode nodeTarget = exchange.getTarget();
        if (fPort_p.equals(nodeSource) || fPort_p.equals(nodeTarget)) {
          result.add(exchange);
        }
      }
    }

    return result;
  }

  /**
   * Verifies if a port allocation from a component port to a function port already exist
   * @param cPort_p
   * @param fPort_p
   */
  private static PortAllocation getPortAllocation(InformationsExchanger cPort_p, ActivityNode fPort_p) {
    if (cPort_p instanceof ComponentPort) {
      for (PortAllocation portAllocation : ((ComponentPort) cPort_p).getOutgoingPortAllocations()) {
        if (fPort_p.equals(portAllocation.getTargetElement())) {
          return portAllocation;
        }
      }
    }
    return null;
  }

  /**
   * @param sourcePart_p
   * @param ce_p
   */
  public static ComponentPort getAttachingPort(Part sourcePart_p, ComponentExchange ce_p) {
    Port componentExcSource = ce_p.getSourcePort();
    if (componentExcSource instanceof ComponentPort) {
      Component sourceComponent = (Component) componentExcSource.eContainer();
      if (ComponentExt.getRepresentingParts(sourceComponent).contains(sourcePart_p)) {
        return (ComponentPort) componentExcSource;
      }

    }
    Port componentExcTarget = ce_p.getTargetPort();
    if (componentExcTarget instanceof ComponentPort) {
      Component targetComponent = (Component) componentExcTarget.eContainer();
      if (ComponentExt.getRepresentingParts(targetComponent).contains(sourcePart_p)) {
        //
      }
      return (ComponentPort) componentExcTarget;
    }
    return null;

  }
}
