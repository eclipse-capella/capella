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

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * return chain involved in functionalChain (! operational process)
 */
public class PhysicalPathInvolvedPhysicalPath implements IQuery {
  /**
   *  default
   */
  public PhysicalPathInvolvedPhysicalPath() {
    // Does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (isValidInstanceOf(object_p)) {
      PhysicalPath functionalChain = (PhysicalPath) object_p;
      for (PhysicalPathInvolvement involvement : PhysicalPathExt.getInvolvementsOf(functionalChain, CsPackage.Literals.PHYSICAL_PATH)) {
        result.add(involvement.getInvolvedElement());
      }
    }
    return result;
  }

  public boolean isValidInstanceOf(Object element_p) {
    if ((null != element_p) && (element_p instanceof PhysicalPath)) {
      return true;
    }
    return false;
  }
}
