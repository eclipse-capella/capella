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

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return inComing and outGoingExchagneItems of ExchagneItems Function Ports
 */
public class ExchangeItemAllocatingOutPutFunctionPorts implements IQuery {

	/**
	 * 
	 */
	public ExchangeItemAllocatingOutPutFunctionPorts() {
    // do nothing
	}

	/** 
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof ExchangeItem) {
      for (EObject obj : EObjectExt.getReferencers((EObject) object, FaPackage.Literals.FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS)) {
        result.add(obj);
      }
    }
    return result;
  }
}
