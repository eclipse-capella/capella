/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return all the available Elements of State & Mode
 *
 */
public class AbstractStateAvailableElements implements IQuery {

  public AbstractStateAvailableElements() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof State) {
      // Note that Mode is sub type of State
      State state = (State) object;
      // get the inverse of the current state
      Collection<Setting> inverseReferences = CapellaElementExt.getInverseReferencesOfEObject(state);
      for (Setting setting : inverseReferences) {
        EObject eObject = setting.getEObject();
        if (eObject != null) {
          // add to result only Function, Capability,OperationalCapability  and FunctionalChain
          if (eObject instanceof AbstractFunction 
              || eObject instanceof Capability || eObject instanceof OperationalCapability 
              || eObject instanceof FunctionalChain) { 
            result.add(eObject);
          }
        }
      }
    }
    return result;
  }
}
