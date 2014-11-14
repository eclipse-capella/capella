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

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return incoming and outgoing Physical Links of current Physical Port
 *
 */
public class PhysicalPortIncomingPhysicalLinks  implements IQuery {

	/**
	 * 
	 */
	public PhysicalPortIncomingPhysicalLinks() {
    // do nothing
	}

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractPhysicalPortPhysicalLinks#getSourcePortFromLink(org.polarsys.capella.core.data.pa.PhysicalLink)
   */
  public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof PhysicalPort) {
		  PhysicalPort currentPort = (PhysicalPort) object_p;
		  Collection<PhysicalLink> links = PhysicalLinkExt.getAllRelatedPhysicalLinks(currentPort);
		  if (!links.isEmpty()) {
			result.addAll(links);
		  }
		}
		return result;
  }
}
