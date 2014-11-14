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

import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 */
public class PhysicalPathInvolvmentInvolvedPhysicalPath implements IQuery {
  /**
   *  default
   */
  public PhysicalPathInvolvmentInvolvedPhysicalPath() {
    // Does nothing
  }

  /**
   * current.getEnactedFunctions
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>(1);
    if (object_p instanceof PhysicalPathInvolvement) {
      PhysicalPathInvolvement chain = (PhysicalPathInvolvement) object_p;
      AbstractPathInvolvedElement involved = chain.getInvolvedElement();
      if ((null != involved) && isValidInstanceOf(chain)) {
        result.add(involved);
      }
    }
    return result;
  }

  public boolean isValidInstanceOf(Object element_p) {
    if ((null != element_p) && (element_p instanceof PhysicalPathInvolvement)) {
      PhysicalPathInvolvement involvment = (PhysicalPathInvolvement) element_p;
      if ((involvment.getInvolvedElement() instanceof PhysicalPath)) {
        return true;
      }
    }
    return false;
  }
}
