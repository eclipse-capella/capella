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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class SystemComponent_referencingComponent implements IQuery {

  /**
   * 
   */
  public SystemComponent_referencingComponent() {
    // do nothing
  }

  /**
   * 
   * representingPartitions.ownerElement
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof SystemComponent) {
      SystemComponent lc = (SystemComponent) object_p;
      EList<Partition> parts = lc.getRepresentingPartitions();
      for (Partition part : parts) {
        EObject res = EcoreUtil2.getFirstContainer(part, LaPackage.Literals.LOGICAL_COMPONENT);
        if ((res != null) && (res instanceof LogicalComponent)) {
          result.add(res);
        }
      }
    }
    return result;
  }
}
