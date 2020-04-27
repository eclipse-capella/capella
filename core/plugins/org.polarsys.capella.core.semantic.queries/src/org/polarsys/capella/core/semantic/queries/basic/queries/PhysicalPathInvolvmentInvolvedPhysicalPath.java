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
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(1);
    if (object instanceof PhysicalPathInvolvement) {
      PhysicalPathInvolvement chain = (PhysicalPathInvolvement) object;
      AbstractPathInvolvedElement involved = chain.getInvolvedElement();
      if ((null != involved) && isValidInstanceOf(chain)) {
        result.add(involved);
      }
    }
    return result;
  }

  public boolean isValidInstanceOf(Object element) {
    if ((null != element) && (element instanceof PhysicalPathInvolvement)) {
      PhysicalPathInvolvement involvment = (PhysicalPathInvolvement) element;
      if ((involvment.getInvolvedElement() instanceof PhysicalPath)) {
        return true;
      }
    }
    return false;
  }
}
