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

import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return realized physical artifacts of current CI 
 * 
 */
public abstract class AbstractCIRealizedPhysicalArtifacts implements IQuery {

	public AbstractCIRealizedPhysicalArtifacts() {
		// does nothing
	}

	/**
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof ConfigurationItem) {
		  ConfigurationItem c = (ConfigurationItem) object;
			EList<PhysicalArtifactRealization> realizations = c.getOwnedPhysicalArtifactRealizations();
			for (PhysicalArtifactRealization realization : realizations) {
        TraceableElement element = realization.getTargetElement();
        if (null != element && isValidElement(element)) {
          result.add(element);
        }
      }
		}
		return result;
	}

  /**
   * @param element
   * @return
   */
  public abstract boolean isValidElement(TraceableElement element);
  
}
