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

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

/**
 * Return involved Functions of the current CapabilityRealization
 * 
 *
 */
public class PAAbstractCapabilityInvolvedFunctions  extends AbstractCapabilityInvolvedFunctions {

	/**
	 * 
	 */
	public PAAbstractCapabilityInvolvedFunctions() {
    // do nothing
	}

	/**
	 * 
	 * current.getEnactedFunctions
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
  @Override
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof CapabilityRealization) {
			// make sure that the functional chain is from la level
			BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture((EObject) object);
			if (null != arch && arch instanceof PhysicalArchitecture) {
				List<Object> compute = super.compute(object);
				if(!compute.isEmpty())
					result.addAll(compute);
			}
		}
		return result;
	}
}
