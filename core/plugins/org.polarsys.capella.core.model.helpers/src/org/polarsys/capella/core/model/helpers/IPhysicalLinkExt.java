/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers;

import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;

public interface IPhysicalLinkExt {

  boolean attachTo(PhysicalLink link, Component container);

  /**
   * Move the given physical link to common ancestor
   * @param link
   * @return whether the physical link has been moved
   */
  boolean attachToDefaultContainer(PhysicalLink link);

  /**
   * Return the best container between both given elements
   * @param sourcePart a part or a component
   * @param targetPart a part or a component
   * @return a not null element
   */
  Component getDefaultContainer(CapellaElement sourcePart, CapellaElement targetPart);

  /**
   * The best container is the common ancestor of source/target parts. if no parts, we use common ancestor of components (which can happen in OA or if user has
   * deleted parts)
   * @param link
   * @return a not null element
   */
  Component getDefaultContainer(PhysicalLink link);

  /**
   * returns the best container to store a category for physical links between sourcePart and targetPart.
   * @param sourcePart
   * @param targetPart
   * @return
   */
  AbstractFunctionalBlock getDefaultContainerForCategory(CapellaElement sourcePart, CapellaElement targetPart);

  /**
   * The best container is the common ancestor of source/target parts. if no parts, we use common ancestor of components (which can happen in OA or if user has
   * deleted parts)
   * @param link
   */
  AbstractFunctionalBlock getDefaultContainerForCategory(PhysicalLink link);

  boolean isDelegation(PhysicalLink link);

  /**
   * @param pLink
   * @param cExchange
   */
  void synchronizeAllocations(PhysicalLink pLink, ComponentExchange cExchange);

  /**
   * @param pLink
   * @param ceSource
   * @param ceTarget
   */
  void synchronizeAllocations(PhysicalLink pLink, ComponentPort ceSource, ComponentPort ceTarget);

  /**
   * @param pPort
   * @param cPort
   */
  void synchronizeAllocations(PhysicalPort pPort, ComponentPort cPort);

  List<ModelElement> evaluateImpactsOfUnsynchronizeAllocations(PhysicalLink pLink, ComponentExchange cExchange,
      boolean forceCleaning);

  /**
   * @param pLink
   * @param ceSource
   * @param ceTarget
   * @param forceCleaning
   */
  List<ModelElement> unsynchronizeAllocations(PhysicalLink pLink, ComponentPort ceSource, ComponentPort ceTarget,
      boolean forceCleaning);

  /**
   * @param pPort
   * @param cPort
   */
  List<ModelElement> unsynchronizeAllocations(PhysicalPort pPort, ComponentPort cPort);

  /**
   * Verifies if a component port allocation from a physical port to a component port already exist
   * @param pPort
   * @param cPort
   */
  ComponentPortAllocation getComponentPortAllocation(PhysicalPort pPort, ComponentPort cPort);

  /**
   * Retrieves all the component exchanges related to the given physical link / physical path and the given component port
   * @param cpntAllocator
   * @param cPort
   * @return
   */
  List<ComponentExchange> getExchangesFrom(ComponentExchangeAllocator cpntAllocator, InformationsExchanger cPort);

  /**
   * Retrieves the physical port related to the given physical link and the given component port
   * @param pLink
   * @param cPort
   * @return
   */
  PhysicalPort getPhysicalPortFrom(PhysicalLink pLink, InformationsExchanger cPort);

  /**
   * Creates a component port allocation from a physical port to a component port
   * @param pPort
   * @param cPort
   */
  void createComponentPortAllocation(PhysicalPort pPort, ComponentPort cPort);

  /**
   * 
   * @param pl
   * @return the physical links that are delegated from the given physical link (on both ports)
   */
  Collection<PhysicalLink> getDelegatedPhysicalLinks(PhysicalLink pl);

}