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
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

/**
 * Return involved operational activities of the current operational process
 * 
 *
 */
public class PAFunctionalChainInvolvedFunctions  extends FunctionalChain_enactedFunctions {

	/**
	 * 
	 */
	public PAFunctionalChainInvolvedFunctions() {
    // do nothing
	}

	/**
	 * 
	 * current.getEnactedFunctions
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
  @Override
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof FunctionalChain && !(object_p instanceof OperationalProcess)) {
			// make sure that the functional chain is from pa level
			BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture((EObject) object_p);
			if (null != arch && arch instanceof PhysicalArchitecture) {
				List<Object> compute = super.compute(object_p);
				if(!compute.isEmpty())
					result.addAll(compute);
			}
		}
		return result;
	}
}
