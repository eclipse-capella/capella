/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
  public List<Object> getAllocationBlock(Object object) {
    return getAllocationBlocks(object);
  }

  /**
   * @param object
   * @return
   */
  public List<Object> getAllocationBlocks(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof AbstractFunction) {
      AbstractFunction sf = (AbstractFunction) object;
      result.addAll(sf.getAllocationBlocks());
    }
    // On top of what is done before, you have to check the entities where roles are allocated for
    // operational activities:
    if (object instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) object;
      
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
