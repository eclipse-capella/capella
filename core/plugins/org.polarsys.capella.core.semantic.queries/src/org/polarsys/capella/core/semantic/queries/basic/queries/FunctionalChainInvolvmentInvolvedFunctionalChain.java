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

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 */
public class FunctionalChainInvolvmentInvolvedFunctionalChain implements IQuery {
  /**
   *  default
   */
  public FunctionalChainInvolvmentInvolvedFunctionalChain() {
    // Does nothing
  }

  /**
   * current.getEnactedFunctions
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>(1);
    if (object_p instanceof FunctionalChainInvolvement) {
      FunctionalChainInvolvement chain = (FunctionalChainInvolvement) object_p;
      InvolvedElement involved = chain.getInvolved();
      if ((null != involved) && isValidInstanceOf(chain)) {
        result.add(involved);
      }
    }
    return result;
  }

  public boolean isValidInstanceOf(Object element_p) {
    if ((null != element_p) && (element_p instanceof FunctionalChainInvolvement)) {
      FunctionalChainInvolvement involvment = (FunctionalChainInvolvement) element_p;
      if ((involvment.getInvolved() instanceof FunctionalChain)) {
        if ((involvment.getInvolver() instanceof FunctionalChain)) {
          if (!(involvment.getInvolver() instanceof OperationalProcess)) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
