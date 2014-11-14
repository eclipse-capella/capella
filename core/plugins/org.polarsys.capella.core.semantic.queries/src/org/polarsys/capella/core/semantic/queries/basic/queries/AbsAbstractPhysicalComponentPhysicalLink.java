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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.common.helpers.query.IQuery;

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
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof AbstractPhysicalComponent) {
      AbstractPhysicalComponent absPhyComp = (AbstractPhysicalComponent) object_p;
      Collection<PhysicalLink> allRelatedPhysicalLinks = PhysicalLinkExt.getAllRelatedPhysicalLinks(absPhyComp);
      for (PhysicalLink physicalLink : allRelatedPhysicalLinks) {
        result.add(physicalLink);
      }
    }
    return result;
  }

  /**
   * Get source or target of the physical link
   * @param physicalLink_p
   * @return
   */
  @Deprecated
  abstract public EObject getRequiredComponent(PhysicalLink physicalLink_p);

}
