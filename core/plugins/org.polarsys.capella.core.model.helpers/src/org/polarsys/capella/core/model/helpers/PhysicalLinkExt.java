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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
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

  public static boolean attachTo(PhysicalLink link_p, Component container_p) {
    if ((container_p != null) && !container_p.equals(link_p.eContainer())) {
      (container_p).getOwnedPhysicalLinks().add(link_p);
      return true;
    }
    return false;
  }

  /**
   * Move the given physical link to common ancestor
   * @param exchange_p
   * @return whether the physical link has been moved
   */
  public static boolean attachToDefaultContainer(PhysicalLink link_p) {
    return attachTo(link_p, getDefaultContainer(link_p));
  }

  /**
   * Return the best container between both given elements
   * @param sourcePart_p a part or a component
   * @param targetPart_p a part or a component
   * @return a not null element
   */
  public static Component getDefaultContainer(CapellaElement sourcePart_p, CapellaElement targetPart_p) {
    EObject container = ComponentExt.getFirstCommonComponentAncestor(sourcePart_p, targetPart_p);
    if ((container != null) && !(container instanceof Component)) {
      container = EcoreUtil2.getFirstContainer(container, PaPackage.Literals.PHYSICAL_COMPONENT);
    }
    if ((container == null) || !(container instanceof Component)) {
      container = BlockArchitectureExt.getFirstComponent(ComponentExt.getRootBlockArchitecture(sourcePart_p));
    }
    return (Component) container;
  }

  /**
   * The best container is the common ancestor of source/target parts. if no parts, we use common ancestor of components (which can happen in OA or if user has
   * deleted parts)
   * @param exchange_p
   * @return a not null element
   */
  public static Component getDefaultContainer(PhysicalLink link_p) {
    CapellaElement source = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceComponent(link_p);
    Collection<Part> parts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceParts(link_p);
    if (!parts.isEmpty()) {
      source = parts.iterator().next();
    }

    CapellaElement target = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetComponent(link_p);
    parts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetParts(link_p);
    if (!parts.isEmpty()) {
      target = parts.iterator().next();
    }

    return getDefaultContainer(source, target);
  }

  /**
   * returns the best container to store a category for physical links between sourcePart_p and targetPart_p.
   * @param sourcePart_p
   * @param targetPart_p
   * @return
   */
  public static AbstractFunctionalBlock getDefaultContainerForCategory(CapellaElement sourcePart_p, CapellaElement targetPart_p) {
    EObject container = ComponentExt.getFirstCommonComponentAncestor(sourcePart_p, targetPart_p);
    if ((container != null) && !(container instanceof AbstractFunctionalBlock)) {
      container = EcoreUtil2.getFirstContainer(container, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK);
    }
    if ((container == null) || !(container instanceof AbstractFunctionalBlock)) {
      container = BlockArchitectureExt.getFirstComponent(ComponentExt.getRootBlockArchitecture(sourcePart_p));
    }
    return (AbstractFunctionalBlock) container;
  }

  /**
   * The best container is the common ancestor of source/target parts. if no parts, we use common ancestor of components (which can happen in OA or if user has
   * deleted parts)
   * @param link_p
   */
  public static AbstractFunctionalBlock getDefaultContainerForCategory(PhysicalLink link_p) {
    CapellaElement source = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceComponent(link_p);
    Collection<Part> parts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceParts(link_p);
    if (!parts.isEmpty()) {
      source = parts.iterator().next();
    }

    CapellaElement target = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetComponent(link_p);
    parts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetParts(link_p);
    if (!parts.isEmpty()) {
      target = parts.iterator().next();
    }

    return getDefaultContainerForCategory(source, target);
  }

  public static boolean isDelegation(PhysicalLink link_p) {

    Collection<Part> sourceParts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceParts(link_p);
    Collection<Part> targetParts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetParts(link_p);

    boolean flag = false;
    for (Part part : targetParts) {
      Collection<Part> firstPartAncestors = PartExt.getFirstPartAncestors(part);
      for (Part part2 : firstPartAncestors) {
        if (sourceParts.contains(part2)) {
          return true;
        }
      }
    }

    if (!flag) {
      for (Part part : sourceParts) {
        Collection<Part> firstPartAncestors = PartExt.getFirstPartAncestors(part);
        for (Part part2 : firstPartAncestors) {
          if (targetParts.contains(part2)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * @param pLink_p
   * @param cExchange_p
   */
  public static void synchronizeAllocations(PhysicalLink pLink_p, ComponentExchange cExchange_p) {
    Port ceSource = ComponentExchangeExt.getSourcePort(cExchange_p);
    Port ceTarget = ComponentExchangeExt.getTargetPort(cExchange_p);
    if ((ceSource instanceof ComponentPort) && (ceTarget instanceof ComponentPort)) {
      synchronizeAllocations(pLink_p, (ComponentPort) ceSource, (ComponentPort) ceTarget);
    }
  }

  /**
   * @param pLink_p
   * @param ceSource_p
   * @param ceTarget_p
   */
  private static void synchronizeAllocations(PhysicalLink pLink_p, ComponentPort ceSource_p, ComponentPort ceTarget_p) {
    synchronizeAllocations(getPhysicalPortFrom(pLink_p, ceSource_p), ceSource_p);
    synchronizeAllocations(getPhysicalPortFrom(pLink_p, ceTarget_p), ceTarget_p);
  }

  /**
   * @param pPort_p
   * @param cPort_p
   */
  protected static void synchronizeAllocations(PhysicalPort pPort_p, ComponentPort cPort_p) {
    if ((null == pPort_p) || (null == cPort_p)) {
      return;
    }
    ComponentPortAllocation exchange = getComponentPortAllocation(pPort_p, cPort_p);
    if (null == exchange) {
      createComponentPortAllocation(pPort_p, cPort_p);
    }
  }

  public static List<ModelElement> evaluateImpactsOfUnsynchronizeAllocations(PhysicalLink pLink_p, ComponentExchange cExchange_p, boolean forceCleaning_p) {
    List<ModelElement> result = new ArrayList<ModelElement>();
    Port ceSource = ComponentExchangeExt.getSourcePort(cExchange_p);
    Port ceTarget = ComponentExchangeExt.getTargetPort(cExchange_p);
    if ((ceSource instanceof ComponentPort) && (ceTarget instanceof ComponentPort)) {
      result.addAll(unsynchronizeAllocations(pLink_p, (ComponentPort) ceSource, (ComponentPort) ceTarget, forceCleaning_p));
    }
    return result;
  }

  /**
   * @param pLink_p
   * @param ceSource_p
   * @param ceTarget_p
   * @param forceCleaning_p
   */
  private static List<ModelElement> unsynchronizeAllocations(PhysicalLink pLink_p, ComponentPort ceSource_p, ComponentPort ceTarget_p, boolean forceCleaning_p) {
    List<ModelElement> result = new ArrayList<ModelElement>();
    if (forceCleaning_p || getExchangesFrom(pLink_p, ceSource_p).isEmpty()) {
      result.addAll(unsynchronizeAllocations(getPhysicalPortFrom(pLink_p, ceSource_p), ceSource_p));
    }
    if (forceCleaning_p || getExchangesFrom(pLink_p, ceTarget_p).isEmpty()) {
      result.addAll(unsynchronizeAllocations(getPhysicalPortFrom(pLink_p, ceTarget_p), ceTarget_p));
    }
    return result;
  }

  /**
   * @param pPort_p
   * @param cPort_p
   */
  protected static List<ModelElement> unsynchronizeAllocations(PhysicalPort pPort_p, ComponentPort cPort_p) {
    List<ModelElement> result = new ArrayList<ModelElement>();
    if ((null != pPort_p) && (null != cPort_p)) {
      ComponentPortAllocation allocation = getComponentPortAllocation(pPort_p, cPort_p);
      if (null != allocation) {
        result.add(allocation);
      }
    }
    return result;
  }

  /**
   * Verifies if a component port allocation from a physical port to a component port already exist
   * @param pPort_p
   * @param cPort_p
   */
  private static ComponentPortAllocation getComponentPortAllocation(PhysicalPort pPort_p, ComponentPort cPort_p) {
    for (AbstractTrace trace : pPort_p.getOutgoingTraces()) {
      if (trace instanceof ComponentPortAllocation) {
        if (cPort_p.equals(trace.getTargetElement())) {
          return (ComponentPortAllocation) trace;
        }
      }
    }
    return null;
  }

  /**
   * Retrieves all the component exchanges related to the given physical link / physical path and the given component port
   * @param cpntAllocator_p
   * @param cPort_p
   * @return
   */
  protected static List<ComponentExchange> getExchangesFrom(ComponentExchangeAllocator cpntAllocator_p, InformationsExchanger cPort_p) {
    List<ComponentExchange> result = new ArrayList<ComponentExchange>();
    if (null != cPort_p) {
      for (ComponentExchange exchange : cpntAllocator_p.getAllocatedComponentExchanges()) {
        InformationsExchanger ceSource = exchange.getSource();
        InformationsExchanger ceTarget = exchange.getTarget();
        if (cPort_p.equals(ceSource) || cPort_p.equals(ceTarget)) {
          result.add(exchange);
        }
      }
    }

    return result;
  }

  /**
   * Retrieves the physical port related to the given physical link and the given component port
   * @param pLink_p
   * @param cPort_p
   * @return
   */
  protected static PhysicalPort getPhysicalPortFrom(PhysicalLink pLink_p, InformationsExchanger cPort_p) {
    EObject cPortOwner = cPort_p.eContainer();
    if (cPortOwner instanceof AbstractType) {
      for (AbstractTypedElement elt : ((AbstractType) cPortOwner).getAbstractTypedElements()) {
        if (elt instanceof DeployableElement) {
          for (AbstractDeploymentLink lnk : ((DeployableElement) elt).getDeployingLinks()) {
            DeploymentTarget tgt = lnk.getLocation();
            if (tgt instanceof AbstractTypedElement) {
              AbstractType type = ((AbstractTypedElement) tgt).getAbstractType();
              if (type instanceof Component) {
                for (Feature feature : ((Component) type).getOwnedFeatures()) {
                  if (feature instanceof PhysicalPort) {
                    for (AbstractPhysicalLinkEnd linkEnd : pLink_p.getLinkEnds()) {
                      if ((linkEnd instanceof PhysicalPort) && linkEnd.equals(feature)) {
                        return (PhysicalPort) feature;
                      } else if (linkEnd instanceof PhysicalLinkEnd) {
                        PhysicalPort pp = ((PhysicalLinkEnd) linkEnd).getPort();
                        if (feature.equals(pp)) {
                          return (PhysicalPort) feature;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return null;
  }

  /**
   * Creates a component port allocation from a physical port to a component port
   * @param pPort_p
   * @param cPort_p
   */
  private static void createComponentPortAllocation(PhysicalPort pPort_p, ComponentPort cPort_p) {
    ComponentPortAllocation allocation = FaFactory.eINSTANCE.createComponentPortAllocation();
    allocation.setSourceElement(pPort_p);
    allocation.setTargetElement(cPort_p);
    pPort_p.getOwnedComponentPortAllocations().add(allocation);
    CapellaElementExt.creationService(allocation);
  }

}
