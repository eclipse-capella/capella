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
package org.polarsys.capella.core.data.helpers.capellacommon.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.State;

public class StateHelper {
	private static StateHelper instance;

	private StateHelper() {
	  //
	}

	public static StateHelper getInstance(){
		if(instance == null)
			instance = new StateHelper();
		return instance;
	}

	public Object doSwitch(State element_p, EStructuralFeature feature_p){
		Object ret = null;

		if (feature_p.equals(CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_FUNCTIONS)) {
			ret = getAvailableAbstractFunctions(element_p);
		}
		else if (feature_p.equals(CapellacommonPackage.Literals.STATE__AVAILABLE_FUNCTIONAL_CHAINS)) {
      ret = getAvailableFunctionalChains(element_p);
    }
    else if (feature_p.equals(CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_CAPABILITIES)) {
      ret = getAvailableAbstractCapabilities(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = AbstractStateHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected List<AbstractFunction> getAvailableAbstractFunctions(State element_p) {
	  List<AbstractFunction> result = new ArrayList<AbstractFunction>();
	  for (EObject referencer : EObjectExt.getReferencers(element_p, FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES)) {
	    if (referencer instanceof AbstractFunction) {
	      result.add((AbstractFunction) referencer);
	    }
	  }
    return result;
	}

  protected List<FunctionalChain> getAvailableFunctionalChains(State element_p) {
    List<FunctionalChain> result = new ArrayList<FunctionalChain>();
    for (EObject referencer : EObjectExt.getReferencers(element_p, FaPackage.Literals.FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES)) {
      if (referencer instanceof FunctionalChain) {
        result.add((FunctionalChain) referencer);
      }
    }
    return result;
  }

  protected List<AbstractCapability> getAvailableAbstractCapabilities(State element_p) {
    List<AbstractCapability> result = new ArrayList<AbstractCapability>();
    for (EObject referencer : EObjectExt.getReferencers(element_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES)) {
      if (referencer instanceof AbstractCapability) {
        result.add((AbstractCapability) referencer);
      }
    }
    return result;
  }
}
