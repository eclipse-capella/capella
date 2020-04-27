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

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 */
public class AbstractFunction_realizingFunctions implements IQuery {

  /**
   * 
   */
  public AbstractFunction_realizingFunctions() {
    // do nothing
  }

  /**
   * current.inFunctionRealisations.allocatingFunction
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof AbstractFunction) {
      AbstractFunction af = (AbstractFunction) object;
      for (FunctionRealization functionRealisation : af.getInFunctionRealizations()) {
        AbstractFunction allocatingFunction = functionRealisation.getAllocatingFunction();
        if (allocatingFunction != null) {
          result.add(allocatingFunction);
        }
      }
    }
    return result;
  }

}
