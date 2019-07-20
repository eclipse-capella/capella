/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.common.helpers.query.IQuery;

import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getCache;

/**
 * Return connected Physical Links of current Physical Port
 *
 */
public abstract class AbstractPhysicalPortPhysicalLinks implements IQuery {

	/**
	 * 
	 */
	public AbstractPhysicalPortPhysicalLinks() {
    // do nothing
	}

	/** 
	 *  
	 * current.flowSource.ownerElement
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<>();
		if (object instanceof PhysicalPort) {
		  PhysicalPort currentPort = (PhysicalPort) object;
		  Collection<PhysicalLink> links = PhysicalLinkExt.getAllRelatedPhysicalLinks(currentPort);
		  for (PhysicalLink physicalLink : links) {
        Port port = getSourcePortFromLink(physicalLink);
        if (null != port && port.equals(currentPort)) {
          result.add(physicalLink);
        }
      }
		}
		return result;
	}
	
	public abstract Port getSourcePortFromLink(PhysicalLink link);
}
