/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.StateTransitionRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class StateTransitionHelper {
	private static StateTransitionHelper instance;

	private StateTransitionHelper() {
	  //
	}

	public static StateTransitionHelper getInstance(){
		if(instance == null)
			instance = new StateTransitionHelper();
		return instance;
	}

	public Object doSwitch(StateTransition element, EStructuralFeature feature){
		Object ret = null;

		if (feature.equals(CapellacommonPackage.Literals.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS)) {
			ret = getRealizedStateTransitions(element);
		}
		else if (feature.equals(CapellacommonPackage.Literals.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS)) {
      ret = getRealizingStateTransitions(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = RelationshipHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected List<StateTransition> getRealizedStateTransitions(StateTransition element) {
	  List<StateTransition> result = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof StateTransitionRealization) {
        StateTransition transition = ((StateTransitionRealization) trace).getRealizedStateTransition();
        if (null != transition) {
          result.add(transition);
        }
      }
    }
    return result;
	}

  protected List<StateTransition> getRealizingStateTransitions(StateTransition element) {
    List<StateTransition> result = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof StateTransitionRealization) {
        StateTransition transition = ((StateTransitionRealization) trace).getRealizingStateTransition();
        if (null != transition) {
          result.add(transition);
        }
      }
    }
    return result;
  }
}
