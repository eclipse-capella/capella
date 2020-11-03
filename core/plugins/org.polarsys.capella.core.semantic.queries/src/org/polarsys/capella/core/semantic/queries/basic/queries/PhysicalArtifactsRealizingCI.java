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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return realizing CI of current Physical artifacts 
 * 
 */
public  class PhysicalArtifactsRealizingCI implements IQuery {

	public PhysicalArtifactsRealizingCI() {
		// does nothing
	}

	/**
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof PhysicalComponent ||
		    object instanceof PhysicalLink
		    || object instanceof PhysicalPort) {
		  TraceableElement c = (TraceableElement) object;
		  EList<AbstractTrace> incomingTraces = c.getIncomingTraces();
		  for (AbstractTrace abstractTrace : incomingTraces) {
        if (abstractTrace instanceof PhysicalArtifactRealization) {
          TraceableElement sourceElement = abstractTrace.getSourceElement();
          if (null != sourceElement && sourceElement instanceof ConfigurationItem) {
            result.add(sourceElement);
          }
        }
      }
		}
		return result;
	}

  
}
