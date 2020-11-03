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
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;


/**
 * Return realized functional chains of current functional chains
 * 
 * 
 */
public abstract class AbsFunctionalChainRealizedFunctionalChains implements IQuery {

	/**
	 * 
	 */
	public AbsFunctionalChainRealizedFunctionalChains() {
		// do nothing
	}

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof FunctionalChain) {
      FunctionalChain chain = (FunctionalChain) object;
      // collect outgoing traces
      EList<AbstractTrace> traces = chain.getOutgoingTraces();
      for (AbstractTrace trace : traces) {
        // filter functionalChainRealization
        if (trace instanceof FunctionalChainRealization) {
          // get target element
          TraceableElement element = trace.getTargetElement();
          if (element != null && isValidInstanceOf(element)) {
            result.add(element);
          }
        }
      }

    }
    return result;
  }

	/**
	 * filter the valid instance type
	 * @param element
	 * @return
	 */
	abstract public boolean isValidInstanceOf(EObject element);
}
