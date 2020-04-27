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
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (isValidInstanceOf(object)) {
      PhysicalPath functionalChain = (PhysicalPath) object;
      for (PhysicalPathInvolvement involvement : PhysicalPathExt.getInvolvementsOf(functionalChain, CsPackage.Literals.PHYSICAL_PATH)) {
        result.add(involvement.getInvolvedElement());
      }
    }
    return result;
  }

  public boolean isValidInstanceOf(Object element) {
    if ((null != element) && (element instanceof PhysicalPath)) {
      return true;
    }
    return false;
  }
}
