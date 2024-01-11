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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.pa.PaPackage;

/**
 */
public class PhysicalLinkExt extends org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt {

  public static boolean attachTo(PhysicalLink link, CapellaElement container) {
    if ((container != null) && !container.equals(link.eContainer())) {
      if (container instanceof Component) {
        ((Component) container).getOwnedPhysicalLinks().add(link);
      } else if (container instanceof ComponentPkg) {
        ((ComponentPkg) container).getOwnedPhysicalLinks().add(link);
      }
      return true;
    }
    return false;
  }

  /**
   * Move the given physical link to common ancestor
   * @param link
   * @return whether the physical link has been moved
   */
  public static boolean attachToDefaultContainer(PhysicalLink link) {
    return attachTo(link, getDefaultContainer(link));
  }

  /**
   * Return the best container between both given elements
   * @param sourcePart a part or a component
   * @param targetPart a part or a component
   * @return a not null element
   */
  public static Namespace getDefaultContainer(CapellaElement sourcePart, CapellaElement targetPart) {
    EObject container = ComponentExt.getFirstCommonComponentOrPkgAncestor(sourcePart, targetPart);

    if ((container != null) && !(container instanceof Component) && !(container instanceof ComponentPkg)) {
      container = EcoreUtil2.getFirstContainer(container, Arrays.asList(PaPackage.Literals.PHYSICAL_COMPONENT, CsPackage.Literals.COMPONENT_PKG));
    }
    if (!(container instanceof Component) && !(container instanceof ComponentPkg)) {
      container = BlockArchitectureExt.getComponentPkg(ComponentExt.getRootBlockArchitecture(sourcePart), true);
    }
    return (Namespace) container;
  }

  /**
   * The best container is the common ancestor of source/target parts. if no parts, we use common ancestor of components (which can happen in OA or if user has
   * deleted parts)
   * @param link
   * @return a not null element
   */
  public static Namespace getDefaultContainer(PhysicalLink link) {
    CapellaElement source = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceComponent(link);
    Collection<Part> parts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceParts(link);
    if (!parts.isEmpty()) {
      source = parts.iterator().next();
    }

    CapellaElement target = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetComponent(link);
    parts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetParts(link);
    if (!parts.isEmpty()) {
      target = parts.iterator().next();
    }

    return getDefaultContainer(source, target);
  }

  /**
   * returns the best container to store a category for physical links between sourcePart and targetPart.
   * @param sourcePart
   * @param targetPart
   * @return
   */
  public static AbstractFunctionalBlock getDefaultContainerForCategory(CapellaElement sourcePart, CapellaElement targetPart) {
    EObject container = ComponentExt.getFirstCommonComponentOrPkgAncestor(sourcePart, targetPart);
    if (!(container instanceof AbstractFunctionalBlock) && !(container instanceof ComponentPkg)) {
      container = EcoreUtil2.getFirstContainer(container,
          Arrays.asList(FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK, CsPackage.Literals.COMPONENT_PKG));
    }
    if (!(container instanceof AbstractFunctionalBlock) && !(container instanceof ComponentPkg)) {
      container = BlockArchitectureExt.getComponentPkg(ComponentExt.getRootBlockArchitecture(sourcePart), true);
    }
    return (AbstractFunctionalBlock) container;
  }

  /**
   * The best container is the common ancestor of source/target parts. if no parts, we use common ancestor of components (which can happen in OA or if user has
   * deleted parts)
   * @param link
   */
  public static AbstractFunctionalBlock getDefaultContainerForCategory(PhysicalLink link) {
    CapellaElement source = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceComponent(link);
    Collection<Part> parts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceParts(link);
    if (!parts.isEmpty()) {
      source = parts.iterator().next();
    }

    CapellaElement target = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetComponent(link);
    parts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetParts(link);
    if (!parts.isEmpty()) {
      target = parts.iterator().next();
    }

    return getDefaultContainerForCategory(source, target);
  }

  public static boolean isDelegation(PhysicalLink link) {

    Collection<Part> sourceParts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceParts(link);
    Collection<Part> targetParts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetParts(link);

    for (Part part : targetParts) {
      Collection<Part> firstPartAncestors = PartExt.getFirstPartAncestors(part);
      for (Part part2 : firstPartAncestors) {
        if (sourceParts.contains(part2)) {
          return true;
        }
      }
    }

    for (Part part : sourceParts) {
      Collection<Part> firstPartAncestors = PartExt.getFirstPartAncestors(part);
      for (Part part2 : firstPartAncestors) {
        if (targetParts.contains(part2)) {
          return true;
        }
      }
    }

    return false;
  }


  /**
   * Returns whether the connection is valid (no null bounds) and linked to an actor
   * @param connection
   * @return
   */
  public static boolean isLinkToAnActor(PhysicalLink connection) {
    Component source = getSourceComponent(connection);
    Component target = getTargetComponent(connection);
    return (source != null) && (target != null) && (source.isActor() || target.isActor());
  }

  /**
   * Returns whether the connection is valid (no null bounds) and not linked to an actor
   * @param connection
   * @return
   */
  public static boolean isNotLinkToAnActor(PhysicalLink connection) {
    Component source = getSourceComponent(connection);
    Component target = getTargetComponent(connection);
    return (source != null) && (target != null) && !source.isActor() && !target.isActor();
  }

  /**
   * @param pLink
   * @param cExchange
   */
  public static void synchronizeAllocations(PhysicalLink pLink, ComponentExchange cExchange) {
    Port ceSource = ComponentExchangeExt.getSourcePort(cExchange);
    Port ceTarget = ComponentExchangeExt.getTargetPort(cExchange);
    if ((ceSource instanceof ComponentPort) && (ceTarget instanceof ComponentPort)) {
      synchronizeAllocations(pLink, (ComponentPort) ceSource, (ComponentPort) ceTarget);
    }
  }

  /**
   * @param pLink
   * @param ceSource
   * @param ceTarget
   */
  private static void synchronizeAllocations(PhysicalLink pLink, ComponentPort ceSource, ComponentPort ceTarget) {
    synchronizeAllocations(getPhysicalPortFrom(pLink, ceSource), ceSource);
    synchronizeAllocations(getPhysicalPortFrom(pLink, ceTarget), ceTarget);
  }

  /**
   * @param pPort
   * @param cPort
   */
  protected static void synchronizeAllocations(PhysicalPort pPort, ComponentPort cPort) {
    if ((null == pPort) || (null == cPort)) {
      return;
    }
    ComponentPortAllocation exchange = getComponentPortAllocation(pPort, cPort);
    if (null == exchange) {
      createComponentPortAllocation(pPort, cPort);
    }
  }

  public static List<ModelElement> evaluateImpactsOfUnsynchronizeAllocations(PhysicalLink pLink, ComponentExchange cExchange, boolean forceCleaning) {
    List<ModelElement> result = new ArrayList<>();
    Port ceSource = ComponentExchangeExt.getSourcePort(cExchange);
    Port ceTarget = ComponentExchangeExt.getTargetPort(cExchange);
    if ((ceSource instanceof ComponentPort) && (ceTarget instanceof ComponentPort)) {
      result.addAll(unsynchronizeAllocations(pLink, (ComponentPort) ceSource, (ComponentPort) ceTarget, forceCleaning));
    }
    return result;
  }

  /**
   * @param pLink
   * @param ceSource
   * @param ceTarget
   * @param forceCleaning
   */
  private static List<ModelElement> unsynchronizeAllocations(PhysicalLink pLink, ComponentPort ceSource, ComponentPort ceTarget, boolean forceCleaning) {
    List<ModelElement> result = new ArrayList<>();
    if (forceCleaning || getExchangesFrom(pLink, ceSource).isEmpty()) {
      result.addAll(unsynchronizeAllocations(getPhysicalPortFrom(pLink, ceSource), ceSource));
    }
    if (forceCleaning || getExchangesFrom(pLink, ceTarget).isEmpty()) {
      result.addAll(unsynchronizeAllocations(getPhysicalPortFrom(pLink, ceTarget), ceTarget));
    }
    return result;
  }

  /**
   * @param pPort
   * @param cPort
   */
  protected static List<ModelElement> unsynchronizeAllocations(PhysicalPort pPort, ComponentPort cPort) {
    List<ModelElement> result = new ArrayList<>();
    if ((null != pPort) && (null != cPort)) {
      ComponentPortAllocation allocation = getComponentPortAllocation(pPort, cPort);
      if (null != allocation) {
        result.add(allocation);
      }
    }
    return result;
  }

  /**
   * Verifies if a component port allocation from a physical port to a component port already exist
   * @param pPort
   * @param cPort
   */
  private static ComponentPortAllocation getComponentPortAllocation(PhysicalPort pPort, ComponentPort cPort) {
    for (AbstractTrace trace : pPort.getOutgoingTraces()) {
      if (trace instanceof ComponentPortAllocation && cPort.equals(trace.getTargetElement())) {
        return (ComponentPortAllocation) trace;
      }
    }
    return null;
  }

  /**
   * Retrieves all the component exchanges related to the given physical link / physical path and the given component port
   * @param cpntAllocator
   * @param cPort
   * @return
   */
  protected static List<ComponentExchange> getExchangesFrom(ComponentExchangeAllocator cpntAllocator, InformationsExchanger cPort) {
    List<ComponentExchange> result = new ArrayList<>();
    if (null != cPort) {
      for (ComponentExchange exchange : cpntAllocator.getAllocatedComponentExchanges()) {
        InformationsExchanger ceSource = exchange.getSource();
        InformationsExchanger ceTarget = exchange.getTarget();
        if (cPort.equals(ceSource) || cPort.equals(ceTarget)) {
          result.add(exchange);
        }
      }
    }

    return result;
  }

  /**
   * Retrieves the physical port related to the given physical link and the given component port
   * @param pLink
   * @param cPort
   * @return
   */
  protected static PhysicalPort getPhysicalPortFrom(PhysicalLink pLink, InformationsExchanger cPort) {
    EObject cPortOwner = cPort.eContainer();
    for (AbstractPhysicalLinkEnd linkEnd : pLink.getLinkEnds()) {
      PhysicalPort pp = null;
      if (linkEnd instanceof PhysicalPort) {
        pp = (PhysicalPort) linkEnd;
      } else if (linkEnd instanceof PhysicalLinkEnd) {
        pp = ((PhysicalLinkEnd) linkEnd).getPort();
      }
      if (pp != null) {
        EObject ppOwner = pp.eContainer();
        if (ppOwner instanceof Component) {
          List<Component> allSubUsedAndDeployedComponents = ComponentExt
              .getAllSubUsedAndDeployedComponents((Component) ppOwner);
          allSubUsedAndDeployedComponents.add((Component) ppOwner);
          if (allSubUsedAndDeployedComponents.contains(cPortOwner)) {
            return pp;
          }
        }
      }
    }
    return null;
  }

  /**
   * Creates a component port allocation from a physical port to a component port
   * @param pPort
   * @param cPort
   */
  private static void createComponentPortAllocation(PhysicalPort pPort, ComponentPort cPort) {
    ComponentPortAllocation allocation = FaFactory.eINSTANCE.createComponentPortAllocation();
    allocation.setSourceElement(pPort);
    allocation.setTargetElement(cPort);
    pPort.getOwnedComponentPortAllocations().add(allocation);
    CapellaElementExt.creationService(allocation);
  }

  /**
   * 
   * @param pl
   * @return the physical links that are delegated from the given physical link (on both ports)
   */
  public static Collection<PhysicalLink> getDelegatedPhysicalLinks(PhysicalLink pl) {
    Set<PhysicalLink> delegatedPhysicalLinks = new HashSet<>();
    PhysicalPort sourcePhysicalPort = pl.getSourcePhysicalPort();
    PhysicalPort targetPhysicalPort = pl.getTargetPhysicalPort();
    if (isDelegation(pl)) {
      if (PortExt.getDelegatingPhysicalLinks(sourcePhysicalPort).contains(pl))
        delegatedPhysicalLinks.addAll(PortExt.getDelegatedPhysicalLinks(sourcePhysicalPort));
      if (PortExt.getDelegatingPhysicalLinks(targetPhysicalPort).contains(pl))
        delegatedPhysicalLinks.addAll(PortExt.getDelegatedPhysicalLinks(targetPhysicalPort));
    } else {
      delegatedPhysicalLinks.addAll(PortExt.getDelegatedPhysicalLinks(sourcePhysicalPort));
      delegatedPhysicalLinks.addAll(PortExt.getDelegatedPhysicalLinks(targetPhysicalPort));
    }
    return delegatedPhysicalLinks;
  }
}
