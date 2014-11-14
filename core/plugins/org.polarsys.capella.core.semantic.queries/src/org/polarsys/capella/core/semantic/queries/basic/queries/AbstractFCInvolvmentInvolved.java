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

import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return involved element of FunctionalChainInvolvement
 */
abstract public class AbstractFCInvolvmentInvolved implements IQuery {

  /**
   * 
   */
  public AbstractFCInvolvmentInvolved() {
    // do nothing
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
      if (null != involved && isInstaceOf(involved)) {
    	 result.add(involved);
      }
    }
    return result;
  }
  
  public abstract boolean isInstaceOf(EObject involved);
  
}
