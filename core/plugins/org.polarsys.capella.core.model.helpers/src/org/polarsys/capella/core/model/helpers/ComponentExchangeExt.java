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

package org.polarsys.capella.core.model.helpers;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
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
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;

/**
 *
 */
public final class ComponentExchangeExt {

	
	private ComponentExchangeExt() {
	}
	  
  /**
   * Return source port of the connection (mono Part mode)
   * @param connection
   * @return Part
   * @deprecated use the derived reference available on component exchanges
   */
  @Deprecated
  public static Port getSourcePort(ComponentExchange connection) {
    InformationsExchanger source = connection.getSource();
    if (source instanceof ComponentExchangeEnd) {
      return ((ComponentExchangeEnd) source).getPort();
    } else if (source instanceof Port) {
      return (Port) source;
    }

    return null;
  }

/**
   * Return target port of the connection (mono Part mode)
   * @param connection
   * @return
   * @deprecated use the derived reference available on component exchanges
   */
  @Deprecated
  public static Port getTargetPort(ComponentExchange connection) {
    InformationsExchanger target = connection.getTarget();
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
   * @param connection
   * @return
   * @deprecated use the derived reference available on component exchanges
   */
  @Deprecated
  public static Part getSourcePart(ComponentExchange connection) {
    InformationsExchanger source = connection.getSource();
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
  public static Collection<Part> getSourceParts(ComponentExchange connection) {
    Part part = getSourcePart(connection);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component sourceComponent = getSourceComponent(connection);
    if (sourceComponent != null) {
      return getCache(ComponentExt::getRepresentingParts, sourceComponent);
    }
    return Collections.emptyList();
  }

  /**
   * Returns source Parts of the connection If component exchange is related to one part, returns a singleton of related part. If component exchange is related
   * to a component, returns representing parts of the component
   */
  public static Collection<? extends EObject> getSourcePartsAndEntities(ComponentExchange connection) {
    if (connection.getSource() instanceof Entity) {
      return Collections.singletonList(connection.getSource());
    }
    return getSourceParts(connection);
  }

  /**
   * Return target Part of the connection (MultiPart mode) if component exchange is not linked to a part, returns null. (don't returns the first representing
   * partition of connected target component)
   * @param connection
   * @return
   * @deprecated use the derived reference available on component exchanges
   */
  @Deprecated
  public static Part getTargetPart(ComponentExchange connection) {
    InformationsExchanger target = connection.getTarget();
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
  public static Collection<Part> getTargetParts(ComponentExchange connection) {
    Part part = getTargetPart(connection);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component targetComponent = getTargetComponent(connection);
    if (targetComponent != null) {
      return getCache(ComponentExt::getRepresentingParts, targetComponent);
    }
    return Collections.emptyList();
  }

  /**
   * Returns target Parts of the connection If component exchange is related to one part, returns a singleton of related part. If component exchange is related
   * to a component, returns representing parts of the component
   */
  public static Collection<? extends EObject> getTargetPartsAndEntities(ComponentExchange connection) {
    if (connection.getTarget() instanceof Entity) {
      return Collections.singletonList(connection.getTarget());
    }
    return getTargetParts(connection);
  }

  /**
   * Return opposite source port of the connection
   * @param connection
   * @param port
   * @return Port
   */
  public static Port getOppositePort(ComponentExchange connection, Port port) {
    Port sourcePort = getSourcePort(connection);
    if ((null != sourcePort) && sourcePort.equals(port)) {
      return getTargetPort(connection);
    }

    return sourcePort;
  }

  /**
   * Return best source element of the Connection
   * @param connection
   * @return ModelElement
   */
  public static Component getSourceComponent(ComponentExchange connection) {
    InformationsExchanger source = connection.getSource();
    // connection end
    if (source instanceof ComponentExchangeEnd) {
      Part part = ((ComponentExchangeEnd) source).getPart();
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
      AbstractType abstractType = part.getType();
      if ((null != abstractType) && (abstractType instanceof Component)) {
        return (Component) abstractType;
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
        return (Component) source;
    }

    return null;
  }

  /**
   * Return best target element of the Connection
   * @param connection
   * @return ModelElement
   */
  public static Component getTargetComponent(ComponentExchange connection) {
    InformationsExchanger target = connection.getTarget();
    // connection end
    if (target instanceof ComponentExchangeEnd) {
      Part part = ((ComponentExchangeEnd) target).getPart();
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
      AbstractType abstractType = part.getType();
      if ((null != abstractType) && (abstractType instanceof Component)) {
        return (Component) abstractType;
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
        return (Component) target;
    }

    return null;
  }

  /**
   * Returns whether the connection is valid (no null bounds) and linked to an actor
   * @param connection
   * @return
   */
  public static boolean isLinkToAnActor(ComponentExchange connection) {
    Component source = getSourceComponent(connection);
    Component target = getTargetComponent(connection);
    return (source != null) && (target != null) && (source.isActor() || target.isActor());
  }

  /**
   * Returns whether the connection is valid (no null bounds) and not linked to an actor
   * @param connection
   * @return
   */
  public static boolean isNotLinkToAnActor(ComponentExchange connection) {
    Component source = getSourceComponent(connection);
    Component target = getTargetComponent(connection);
    return (source != null) && (target != null) && !source.isActor() && !target.isActor();
  }

  /**
   * Return filtered list of Connection by kinds
   * @param objects
   * @param kinds1
   * @return list of Objects
   */
  public static List<Object> filteredComponentExchangesBykind(List<Object> objects, ComponentExchangeKind[] kinds1) {
    List<Object> results = new ArrayList<Object>();
    List<ComponentExchangeKind> kinds = Arrays.asList(kinds1);
    for (Object object : objects) {
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
   * @param sourcePart
   * @param sourcePort
   * @param targetPart
   * @param targetPort
   * @param kind
   * @return
   */
  public static ComponentExchange createComponentExchange(Part sourcePart, ComponentPort sourcePort, Part targetPart, ComponentPort targetPort,
      ComponentExchangeKind kind, ComponentPortKind sourceKind1, OrientationPortKind sourceOrientation, ComponentPortKind targetKind1,
      OrientationPortKind targetOrientation, boolean isMultipart1) {

    boolean isMultipart = isMultipart1;
    ComponentPort portSource = sourcePort;
    if (portSource == null) {
      Component component = (Component) sourcePart.getAbstractType();
      portSource = FaFactory.eINSTANCE.createComponentPort();
      portSource.setKind(sourceKind1);
      portSource.setOrientation(sourceOrientation);
      component.getOwnedFeatures().add(portSource);
      CapellaElementExt.creationService(portSource);
    }

    ComponentPort portTarget = targetPort;
    if (portTarget == null) {
      Component component = (Component) targetPart.getAbstractType();
      portTarget = FaFactory.eINSTANCE.createComponentPort();
      portTarget.setKind(targetKind1);
      portTarget.setOrientation(targetOrientation);
      component.getOwnedFeatures().add(portTarget);
      CapellaElementExt.creationService(portTarget);
    }

    ComponentExchange componentExchange = FaFactory.eINSTANCE.createComponentExchange();
    componentExchange.setKind(kind);
    attachTo(componentExchange, getDefaultContainer(sourcePart, targetPart));

    if ((!isMultipart) || (kind == ComponentExchangeKind.DELEGATION)) {
      componentExchange.setSource(portSource);
    } else {
      ComponentExchangeEnd end = FaFactory.eINSTANCE.createComponentExchangeEnd();
      end.setPort(portSource);
      end.setPart(sourcePart);
      componentExchange.getOwnedComponentExchangeEnds().add(end);
      componentExchange.setSource(end);
    }

    if (!isMultipart) {
      componentExchange.setTarget(portTarget);
    } else {
      ComponentExchangeEnd end = FaFactory.eINSTANCE.createComponentExchangeEnd();
      end.setPort(portTarget);
      end.setPart(targetPart);
      componentExchange.getOwnedComponentExchangeEnds().add(end);
      componentExchange.setTarget(end);
    }

    CapellaElementExt.creationService(componentExchange);
    return componentExchange;
  }

  /**
   * Attach the given component exchange to the given abstract functional block
   * @param exchange
   * @param container
   * @return
   */
  public static boolean attachTo(ComponentExchange exchange, CapellaElement container) {
    if ((container != null) && !container.equals(exchange.eContainer())) {
      if ((container instanceof Entity) && (exchange instanceof CommunicationMean)) {
        ((Entity) container).getOwnedCommunicationMeans().add((CommunicationMean) exchange);
      } else if (container instanceof ComponentPkg) {
        ((ComponentPkg) container).getOwnedComponentExchanges().add(exchange);
      } else if (container instanceof Component) {
        ((Component) container).getOwnedComponentExchanges().add(exchange);
      }
      return true;
    }
    return false;
  }

  /**
   * Move the given component exchange to common ancestor
   * @param exchange
   * @return whether the component exchange has been moved
   */
  public static boolean attachToDefaultContainer(ComponentExchange exchange) {
    return attachTo(exchange, getDefaultContainer(exchange));
  }

  /**
   * Return the best container between both given elements
   * @param sourcePart a part or a component
   * @param targetPart a part or a component
   * @return a not null element
   */
  public static CapellaElement getDefaultContainer(CapellaElement sourcePart, CapellaElement targetPart) {
    EObject container = ComponentExt.getFirstCommonComponentOrPkgAncestor(sourcePart, targetPart);

    if ((container != null) && !(container instanceof AbstractFunctionalBlock) && !(container instanceof ComponentPkg)) {
      container = EcoreUtil2.getFirstContainer(container, Arrays.asList(FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK, CsPackage.Literals.COMPONENT_PKG));
    }
    if (!(container instanceof AbstractFunctionalBlock) && !(container instanceof ComponentPkg)) {
      container = BlockArchitectureExt.getComponentPkg(ComponentExt.getRootBlockArchitecture(sourcePart), true);
    }
    return (CapellaElement) container;
  }

  /**
   * The best container is the common ancestor of source/target parts. if no parts, we use common ancestor of components (which can happen in OA or if user has
   * deleted parts)
   * @param exchange
   * @return a not null element
   */
  public static CapellaElement getDefaultContainer(ComponentExchange exchange) {
    CapellaElement source = ComponentExchangeExt.getSourceComponent(exchange);
    Collection<Part> parts = ComponentExchangeExt.getSourceParts(exchange);
    if (!parts.isEmpty()) {
      source = parts.iterator().next();
    }

    CapellaElement target = ComponentExchangeExt.getTargetComponent(exchange);
    parts = ComponentExchangeExt.getTargetParts(exchange);
    if (!parts.isEmpty()) {
      target = parts.iterator().next();
    }

    return getDefaultContainer(source, target);
  }

  public static Collection<? extends ComponentExchange> getDelegatedComponentExchanges(ComponentPort port, ComponentExchange delegation) {
    Port cp = null;
    if (delegation.getSourcePort().equals(port)) {
      cp = delegation.getTargetPort();
    } else {
      cp = delegation.getSourcePort();
    }

    List<ComponentExchange> result = new ArrayList<ComponentExchange>();
    if (cp instanceof ComponentPort) {
      // The "pivot" port is available, find the ports that are not from the delegation.
      for (ComponentExchange ce : ((ComponentPort) cp).getComponentExchanges()) {
        if (!ce.equals(delegation)) {
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
   * @param cExchange
   * @param fExchange
   * @return a list of port allocations.
   */
  private static List<PortAllocation> getPortAllocations(ComponentExchange cExchange, FunctionalExchange fExchange) {
    List<PortAllocation> portAllocations = new ArrayList<PortAllocation>();
    // Get all ports allocations associated to the component exchange ports (source and target).
    List<PortAllocation> portAllocationsToCheck = new ArrayList<PortAllocation>();
    Port sourceComponentPort = cExchange.getSourcePort();
    if (null != sourceComponentPort) {
      // Check for null value (this can occur with projectApproach=ReusableComponents).
      portAllocationsToCheck.addAll(sourceComponentPort.getOutgoingPortAllocations());
    }
    Port targetComponentPort = cExchange.getTargetPort();
    if (null != targetComponentPort) {
      portAllocationsToCheck.addAll(targetComponentPort.getOutgoingPortAllocations());
    }
    // Keep only port allocations associated to a port of the given functional exchange.
    for (PortAllocation portAllocation : portAllocationsToCheck) {
      if (portAllocation.getTargetElement().equals(fExchange.getSource()) || portAllocation.getTargetElement().equals(fExchange.getTarget())) {
        portAllocations.add(portAllocation);
      }
    }
    return portAllocations;
  }

  /**
   * Returns whether the given component exchange is a Delegation
   * @param exchange
   * @return
   */
  public static boolean isDelegation(ComponentExchange exchange) {
    return exchange.getKind() == ComponentExchangeKind.DELEGATION;
  }

  /**
   * Return valid FE available for allocation
   * @param element
   * @return
   */
  public static List<CapellaElement> getValidFEAvailableForAllocation(ComponentExchange element) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(1);
    AbstractFunctionalBlock source = null;
    AbstractFunctionalBlock target = null;

    Port sourcePort = ComponentExchangeExt.getSourcePort(element);
    Port targetPort = ComponentExchangeExt.getTargetPort(element);

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

    InformationsExchanger informationsExchangerSource = element.getSource();
    InformationsExchanger informationsExchangerTarget = element.getTarget();

    if ((informationsExchangerSource != null) && (informationsExchangerTarget != null)) {
      source = ComponentExchangeExt.getSourceComponent(element);
      target = ComponentExchangeExt.getTargetComponent(element);

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
   * @param cExchange the ComponentExchange using ports to allocate,
   * @param fExchange the FunctionalExchange using ports to be allocated.
   */
  public static void synchronizePortAllocations(ComponentExchange cExchange, FunctionalExchange fExchange) {

    Port sourceComponentPort = ComponentExchangeExt.getSourcePort(cExchange);
    Port targetComponentPort = ComponentExchangeExt.getTargetPort(cExchange);

    if ((sourceComponentPort != null) && (targetComponentPort != null) && (sourceComponentPort instanceof InformationsExchanger)
        && (targetComponentPort instanceof InformationsExchanger)) {
      Component sourceComponent = ComponentExchangeExt.getSourceComponent(cExchange);
      Component targetComponent = ComponentExchangeExt.getTargetComponent(cExchange);

      AbstractFunction sourceFunction = FunctionalExchangeExt.getSourceFunction(fExchange);
      AbstractFunction targetFunction = FunctionalExchangeExt.getTargetFunction(fExchange);

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
        synchronizeAllocations((InformationsExchanger) sourceComponentPort, fExchange.getSource());
        synchronizeAllocations((InformationsExchanger) targetComponentPort, fExchange.getTarget());
      } else if (!Collections.disjoint(sourceComponentAndSubComponents, targetFunctionAllocatingComponents)
                 && !Collections.disjoint(targetComponentAndSubComponents, sourceFunctionAllocatingComponents)) {
        // Source component "contains" target function and target component "contains" source function.
        // Allocations : Source component port -> target function port + Target component port -> source function port.
        synchronizeAllocations((InformationsExchanger) sourceComponentPort, fExchange.getTarget());
        synchronizeAllocations((InformationsExchanger) targetComponentPort, fExchange.getSource());
      }
    }
  }

  /**
   * Get ports allocations betw
   * @param cExchange
   * @param fExchange
   * @param forceCleaning TODO it seems that this parameter is always set to true (else in the two unsynchronizeAllocations methods which seem not used).
   * @return
   */
  public static List<? extends ModelElement> evaluateImpactsOfUnsynchronizeAllocations(ComponentExchange cExchange, FunctionalExchange fExchange,
      boolean forceCleaning) {
    if (forceCleaning) {
      return getPortAllocations(cExchange, fExchange);
    }

    List<ModelElement> result = new ArrayList<>();
    if (getExchangesFrom(cExchange, fExchange.getSource()).isEmpty()) {
      result.addAll(unsynchronizeAllocations(cExchange.getSource(), fExchange.getSource()));
    }
    if (getExchangesFrom(cExchange, fExchange.getTarget()).isEmpty()) {
      result.addAll(unsynchronizeAllocations(cExchange.getTarget(), fExchange.getTarget()));
    }
    return result;
  }

  /**
   * @param cPort
   * @param fPort
   */
  private static void synchronizeAllocations(InformationsExchanger cPort, ActivityNode fPort) {
    if (cPort instanceof ComponentPort) {
      PortExt.attachPort((ComponentPort) cPort, fPort);
    }
  }

  /**
   * @param cPort
   * @param fPort
   */
  private static List<ModelElement> unsynchronizeAllocations(InformationsExchanger cPort, ActivityNode fPort) {
    List<ModelElement> result = new ArrayList<>();
    PortAllocation allocation = getPortAllocation(cPort, fPort);
    if (null != allocation) {
      result.add(allocation);
    }
    return result;
  }

  /**
   * Retrieves all the functional exchanges related to the given component exchange and the given function port
   * @param cExchange
   * @param fPort
   * @return
   */
  private static List<FunctionalExchange> getExchangesFrom(ComponentExchange cExchange, ActivityNode fPort) {
    List<FunctionalExchange> result = new ArrayList<>();
    if (null != fPort) {
      for (FunctionalExchange exchange : cExchange.getAllocatedFunctionalExchanges()) {
        ActivityNode nodeSource = exchange.getSource();
        ActivityNode nodeTarget = exchange.getTarget();
        if (fPort.equals(nodeSource) || fPort.equals(nodeTarget)) {
          result.add(exchange);
        }
      }
    }

    return result;
  }

  /**
   * Verifies if a port allocation from a component port to a function port already exist
   * @param cPort
   * @param fPort
   */
  private static PortAllocation getPortAllocation(InformationsExchanger cPort, ActivityNode fPort) {
    if (cPort instanceof ComponentPort) {
      for (PortAllocation portAllocation : ((ComponentPort) cPort).getOutgoingPortAllocations()) {
        if (fPort.equals(portAllocation.getTargetElement())) {
          return portAllocation;
        }
      }
    }
    return null;
  }

  /**
   * @param sourcePart
   * @param ce
   */
  public static ComponentPort getAttachingPort(Part sourcePart, ComponentExchange ce) {
    Port componentExcSource = ce.getSourcePort();
    if (componentExcSource instanceof ComponentPort) {
      Component sourceComponent = (Component) componentExcSource.eContainer();
      if (getCache(ComponentExt::getRepresentingParts, sourceComponent).contains(sourcePart)) {
        return (ComponentPort) componentExcSource;
      }

    }
    Port componentExcTarget = ce.getTargetPort();
    if (componentExcTarget instanceof ComponentPort) {
      Component targetComponent = (Component) componentExcTarget.eContainer();
      if (getCache(ComponentExt::getRepresentingParts, targetComponent).contains(sourcePart)) {
        //
      }
      return (ComponentPort) componentExcTarget;
    }
    return null;

  }
  
  /**
   * 
   * @param ce
   * @return the component exchanges that are delegated from the given component exchange
   */
  public static Collection<ComponentExchange> getDelegatedComponentExchanges(ComponentExchange ce) {
    Set<ComponentExchange> delegatedComponentExchanges = new HashSet<>();
    Port sourcePort = ce.getSourcePort();
    Port targetPort = ce.getTargetPort();
    if (sourcePort instanceof ComponentPort && targetPort instanceof ComponentPort) {
      if (!isDelegation(ce))
        delegatedComponentExchanges.addAll(PortExt.getDelegatedComponentExchanges((ComponentPort) sourcePort));
      delegatedComponentExchanges.addAll(PortExt.getDelegatedComponentExchanges((ComponentPort) targetPort));
    }
    return delegatedComponentExchanges;
  }
  
  /**
   * 
   * @param connection The ComponentExchange
   * @return if it's a connection between tow components.
   */
  public static boolean isConnectionBetweenTypes(ComponentExchange connection) {
    return connection.getSourcePart() == null && connection.getTargetPart() == null
        && connection.getSourcePort() != null && connection.getTargetPort() != null;
  }
}
