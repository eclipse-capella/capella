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

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 */
public class AbstractFunction_realizedFunctions implements IQuery {

  /**
   * 
   */
  public AbstractFunction_realizedFunctions() {
    // do nothing
  }

  /**
   * current.outFunctionRealisations.allocatedFunction
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof AbstractFunction) {
      AbstractFunction af = (AbstractFunction) object_p;
      for (FunctionRealization functionRealisation : af.getOutFunctionRealizations()) {
        AbstractFunction allocatedFunction = functionRealisation.getAllocatedFunction();
        if (null != allocatedFunction)
          result.add(allocatedFunction);
      }
    }
    return result;
  }

}
