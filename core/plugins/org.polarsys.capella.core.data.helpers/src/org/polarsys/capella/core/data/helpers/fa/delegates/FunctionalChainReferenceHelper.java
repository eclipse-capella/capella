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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;

public class FunctionalChainReferenceHelper {
	private static FunctionalChainReferenceHelper instance;

	private FunctionalChainReferenceHelper() {
    // do nothing
	}

	public static FunctionalChainReferenceHelper getInstance(){
		if(instance == null)
			instance = new FunctionalChainReferenceHelper();
		return instance;
	}

	public Object doSwitch(FunctionalChainReference element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN_REFERENCE__REFERENCED_FUNCTIONAL_CHAIN)) {
      ret = getReferencedFunctionalChain(element);
    }

		// no helper found... searching in super classes...
    if(null == ret) {
      ret = FunctionalChainInvolvementHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

  protected FunctionalChain getReferencedFunctionalChain(FunctionalChainReference element) {
    InvolvedElement elt = element.getInvolved();
    if (elt instanceof FunctionalChain) {
      return (FunctionalChain) elt;
    }
    return null;
  }
}
