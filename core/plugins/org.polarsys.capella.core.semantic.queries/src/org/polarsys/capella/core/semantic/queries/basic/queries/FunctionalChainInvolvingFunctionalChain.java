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
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (isValidInstanceOf(object)) {
      FunctionalChain sf = (FunctionalChain) object;
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

  public boolean isValidInstanceOf(Object element) {
    if ((null != element) && (element instanceof FunctionalChain) && !(element instanceof OperationalProcess)) {
      return true;
    }
    return false;
  }
}
