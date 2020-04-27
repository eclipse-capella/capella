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

	public Object doSwitch(StateTransitionRealization element, EStructuralFeature feature){
		Object ret = null;

		if (feature.equals(CapellacommonPackage.Literals.STATE_TRANSITION_REALIZATION__REALIZED_STATE_TRANSITION)) {
			ret = getRealizedStateTransition(element);
		}
		else if (feature.equals(CapellacommonPackage.Literals.STATE_TRANSITION_REALIZATION__REALIZING_STATE_TRANSITION)) {
      ret = getRealizingStateTransition(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected StateTransition getRealizedStateTransition(StateTransitionRealization element) {
    if (element.getTargetElement() instanceof StateTransition) {
      return (StateTransition) element.getTargetElement();
    }
    return null;
	}

  protected StateTransition getRealizingStateTransition(StateTransitionRealization element) {
    if (element.getSourceElement() instanceof StateTransition) {
      return (StateTransition) element.getSourceElement();
    }
    return null;
  }
}
