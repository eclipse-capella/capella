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

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Allows to get the target entity of a communication mean
 * 
 * 
 */
public class CommunicationMean_Target implements IQuery {

  public CommunicationMean_Target() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof CommunicationMean) {
      EObject eObject = (EObject) object;
      Object obj = eObject.eGet(ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET);
      if (null != obj) {
        result.add(obj);
      }
    }
    return result;
  }
}
