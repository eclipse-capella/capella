/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * return chain involved in functionalChain (! operational process)
 */
public class FunctionalChainInvolvedFunctionalChain implements IQuery {
  /**
   *  default
   */
  public FunctionalChainInvolvedFunctionalChain() {
    // Does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (isValidInstanceOf(object)) {
      FunctionalChain functionalChain = (FunctionalChain) object;
      for (FunctionalChainInvolvement involvement : FunctionalChainExt.getInvolvementsOf(functionalChain, FaPackage.Literals.FUNCTIONAL_CHAIN)) {
        result.add(involvement.getInvolved());
      }
    }
    return result;
  }

  public boolean isValidInstanceOf(Object element) {
    if ((null != element) && (element instanceof FunctionalChain) && !(element instanceof OperationalProcess)) {
      return true;
    }
    return false;
  }
}
