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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.StateTransitionRealization;

public class StateTransitionRealizationHelper {
	private static StateTransitionRealizationHelper instance;

	private StateTransitionRealizationHelper() {
	  //
	}

	public static StateTransitionRealizationHelper getInstance(){
		if(instance == null)
			instance = new StateTransitionRealizationHelper();
		return instance;
	}

	public Object doSwitch(StateTransitionRealization element_p, EStructuralFeature feature_p){
		Object ret = null;

		if (feature_p.equals(CapellacommonPackage.Literals.STATE_TRANSITION_REALIZATION__REALIZED_STATE_TRANSITION)) {
			ret = getRealizedStateTransition(element_p);
		}
		else if (feature_p.equals(CapellacommonPackage.Literals.STATE_TRANSITION_REALIZATION__REALIZING_STATE_TRANSITION)) {
      ret = getRealizingStateTransition(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected StateTransition getRealizedStateTransition(StateTransitionRealization element_p) {
    if (element_p.getTargetElement() instanceof StateTransition) {
      return (StateTransition) element_p.getTargetElement();
    }
    return null;
	}

  protected StateTransition getRealizingStateTransition(StateTransitionRealization element_p) {
    if (element_p.getSourceElement() instanceof StateTransition) {
      return (StateTransition) element_p.getSourceElement();
    }
    return null;
  }
}
