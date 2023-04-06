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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * Return outgoing or incoming physical links of current physical component and physical actor
 */
public abstract class AbsAbstractPhysicalComponentPhysicalLink implements IQuery {

  /** 
   * 
   */
  public AbsAbstractPhysicalComponentPhysicalLink() {
    // do nothing
  }

  /**
   * current.componentPorts.outgoingFlows
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<>();
    if (object instanceof PhysicalComponent) {
      PhysicalComponent absPhyComp = (PhysicalComponent) object;
      Collection<PhysicalLink> allRelatedPhysicalLinks = getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, absPhyComp);
      for (PhysicalLink physicalLink : allRelatedPhysicalLinks) {
        result.add(physicalLink);
      }
    }
    return result;
  }

  /**
   * Get source or target of the physical link
   * @param physicalLink
   * @return
   */
  @Deprecated
  abstract public EObject getRequiredComponent(PhysicalLink physicalLink);

}
