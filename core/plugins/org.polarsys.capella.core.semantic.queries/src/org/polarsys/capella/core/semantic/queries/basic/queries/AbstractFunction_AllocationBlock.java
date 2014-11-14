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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;

/**
 * Allows to get the allocation blocks of a function.
 * 
 * 
 */
public class AbstractFunction_AllocationBlock {

  public AbstractFunction_AllocationBlock() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> getAllocationBlock(Object object_p) {
    return getAllocationBlocks(object_p);
  }

  /**
   * @param object_p
   * @return
   */
  public List<Object> getAllocationBlocks(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof AbstractFunction) {
      AbstractFunction sf = (AbstractFunction) object_p;
      result.addAll(sf.getAllocationBlocks());
    }
    // On top of what is done before, you have to check the entities where roles are allocated for
    // operational activities:
    if (object_p instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) object_p;
      
      // look for inverse reference : activity allocation
      Collection<Setting> inverseReferences = CapellaElementExt.getInverseReferencesOfEObject(oa);
      for (Setting setting : inverseReferences) {
        EObject eObject = setting.getEObject();
        if (eObject != null) {
          // activity allocation
          if (eObject instanceof ActivityAllocation){
            ActivityAllocation allocaiton = (ActivityAllocation) eObject;
            // get role
            Role role = allocaiton.getRole();
            if (role != null) {
              result.add(role);
            }
          }
        }
      }
    }
    return result;
  }
}
