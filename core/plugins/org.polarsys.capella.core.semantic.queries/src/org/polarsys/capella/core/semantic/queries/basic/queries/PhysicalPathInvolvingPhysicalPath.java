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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * return chain involved in functionalChain (! operational process)
 */
public class PhysicalPathInvolvingPhysicalPath implements IQuery {
  /**
   *  default
   */
  public PhysicalPathInvolvingPhysicalPath() {
    // Does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (isValidInstanceOf(object)) {
      PhysicalPath sf = (PhysicalPath) object;
      Collection<Setting> inverseReferencesOfEObject = CapellaElementExt.getInverseReferencesOfEObject(sf);
      for (Setting setting : inverseReferencesOfEObject) {
        EObject eObject = setting.getEObject();
        if ((null != eObject) && (eObject instanceof PhysicalPathInvolvement)) {
          if (CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT.equals(setting.getEStructuralFeature())) {
            result.add(((PhysicalPathInvolvement) eObject).eContainer());
          }
        }
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
