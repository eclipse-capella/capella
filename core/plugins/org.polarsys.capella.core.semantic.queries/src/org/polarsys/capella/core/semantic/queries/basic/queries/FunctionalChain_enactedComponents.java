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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class FunctionalChain_enactedComponents implements IQuery {

	/**
	 * 
	 */
	public FunctionalChain_enactedComponents() {
    // do nothing
	}

	/** 
	 *  
	 * current.getEnactedAbstractFunctionalBlock(select Component)
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof FunctionalChain) {
			FunctionalChain fc = (FunctionalChain) object;
			EList<AbstractFunctionalBlock> enactedFunctionalBlocks = fc.getEnactedFunctionalBlocks();
			for (AbstractFunctionalBlock abstractFunctionalBlock : enactedFunctionalBlocks) {
			  if (abstractFunctionalBlock instanceof Component) {
	        result.add(abstractFunctionalBlock);
	      }        
      }
		}
		return result;
	}
}
