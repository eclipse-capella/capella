/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return allocated component exchanges of current physical link
 * 
 */
public class PhysicalLinksRealizedConnection implements IQuery {
  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    if (object instanceof PhysicalLink) {
    	PhysicalLink link = (PhysicalLink) object;
    	EList<AbstractTrace> outgoingTraces = link.getOutgoingTraces();
    	for (AbstractTrace abstractTrace : outgoingTraces) {
			if (abstractTrace instanceof ComponentExchangeAllocation) {
				TraceableElement element = abstractTrace.getTargetElement();
				if(null != element)
				result.add(element);
			}
		}
    }
    return result;
  }
}
