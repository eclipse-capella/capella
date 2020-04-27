/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;

public class PhysicalLinkCategoryExt {
  /**
   * 
   * @param category
   * @return physical links that are covered by the given category
   */
  public static Collection<PhysicalLink> getCoveredPhysicalLinks(PhysicalLinkCategory category) {
    Set<PhysicalLink> coveredLinks = new HashSet<>();
    coveredLinks.addAll(category.getLinks());
    // Delegated physical links are also considered to be covered by the category
    for (PhysicalLink link : category.getLinks()) {
      coveredLinks.addAll(PhysicalLinkExt.getDelegatedPhysicalLinks(link));
    }
    return coveredLinks;
  }
  
  /**
   * 
   * @param category
   * @return Component Port Allocations that are covered by the given category
   */
  public static Collection<ComponentPortAllocation> getCoveredComponentPortAllocations(PhysicalLinkCategory category) {
    Set<ComponentPortAllocation> cpa = new HashSet<>();
    EList<PhysicalLink> links = category.getLinks();
    for (PhysicalLink pl : links) {
      PhysicalPort sourcePhysicalPort = pl.getSourcePhysicalPort();
      cpa.addAll(sourcePhysicalPort.getOwnedComponentPortAllocations());
      PhysicalPort targetPhysicalPort = pl.getTargetPhysicalPort();
      cpa.addAll(targetPhysicalPort.getOwnedComponentPortAllocations());
    }
    return cpa;
  }
}
