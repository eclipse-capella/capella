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

	public Object doSwitch(FunctionalChainReference element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN_REFERENCE__REFERENCED_FUNCTIONAL_CHAIN)) {
      ret = getReferencedFunctionalChain(element_p);
    }

		// no helper found... searching in super classes...
    if(null == ret) {
      ret = FunctionalChainInvolvementHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}

  protected FunctionalChain getReferencedFunctionalChain(FunctionalChainReference element_p) {
    InvolvedElement elt = element_p.getInvolved();
    if (elt instanceof FunctionalChain) {
      return (FunctionalChain) elt;
    }
    return null;
  }
}
