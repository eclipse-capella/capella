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

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * return chain involved in functionalChain (! operational process)
 */
public class FunctionalChainInvolvingFunctionalChain implements IQuery {
  /**
   *  default
   */
  public FunctionalChainInvolvingFunctionalChain() {
    // Does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (isValidInstanceOf(object_p)) {
      FunctionalChain sf = (FunctionalChain) object_p;
      Collection<Setting> inverseReferencesOfEObject = CapellaElementExt.getInverseReferencesOfEObject(sf);
      for (Setting setting : inverseReferencesOfEObject) {
        EObject eObject = setting.getEObject();
        if ((null != eObject) && (eObject instanceof FunctionalChainInvolvement)) {
          if (CapellacorePackage.Literals.INVOLVEMENT__INVOLVED.equals(setting.getEStructuralFeature())) {
            result.add(((FunctionalChainInvolvement) eObject).getInvolver());
          }
        }
      }

    }
    return result;
  }

  public boolean isValidInstanceOf(Object element_p) {
    if ((null != element_p) && (element_p instanceof FunctionalChain) && !(element_p instanceof OperationalProcess)) {
      return true;
    }
    return false;
  }
}
