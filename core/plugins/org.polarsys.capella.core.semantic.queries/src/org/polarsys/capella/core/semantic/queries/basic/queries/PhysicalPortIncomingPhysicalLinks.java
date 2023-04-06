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
  public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<>();
		if (object instanceof PhysicalPort) {
		  PhysicalPort currentPort = (PhysicalPort) object;
		  Collection<PhysicalLink> links = getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, currentPort);
		  if (!links.isEmpty()) {
			result.addAll(links);
		  }
		}
		return result;
  }
}
