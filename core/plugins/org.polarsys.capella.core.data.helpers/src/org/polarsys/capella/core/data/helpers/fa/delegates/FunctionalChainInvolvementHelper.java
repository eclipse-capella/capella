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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;

public class FunctionalChainInvolvementHelper {
	private static FunctionalChainInvolvementHelper instance;

	private FunctionalChainInvolvementHelper() {
    // do nothing
	}

	public static FunctionalChainInvolvementHelper getInstance(){
		if(instance == null)
			instance = new FunctionalChainInvolvementHelper();
		return instance;
	}

	public Object doSwitch(FunctionalChainInvolvement element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS)) {
      ret = getPreviousFunctionalChainInvolvements(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT)) {
      ret = getInvolvedElement(element_p);
    }

		// no helper found... searching in super classes...
    if(null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}

	protected List<FunctionalChainInvolvement> getPreviousFunctionalChainInvolvements(FunctionalChainInvolvement element_p) {
    List<FunctionalChainInvolvement> ret = new ArrayList<FunctionalChainInvolvement>();
    EObject owner = element_p.eContainer();
    if (owner instanceof FunctionalChain) {
      for (EObject anInverseReference : EObjectExt.getReferencers(element_p, FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS)) {
        if ((anInverseReference instanceof FunctionalChainInvolvement) && ((FunctionalChain) owner).getOwnedFunctionalChainInvolvements().contains(anInverseReference)) {
          ret.add((FunctionalChainInvolvement) anInverseReference);
        }
      }
    }
    return ret;
  }

  protected InvolvedElement getInvolvedElement(FunctionalChainInvolvement element_p) {
    return element_p.getInvolved();
  }
}
