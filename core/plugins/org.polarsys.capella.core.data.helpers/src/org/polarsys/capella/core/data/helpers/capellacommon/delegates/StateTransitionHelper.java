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

	public Object doSwitch(StateTransition element_p, EStructuralFeature feature_p){
		Object ret = null;

		if (feature_p.equals(CapellacommonPackage.Literals.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS)) {
			ret = getRealizedStateTransitions(element_p);
		}
		else if (feature_p.equals(CapellacommonPackage.Literals.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS)) {
      ret = getRealizingStateTransitions(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = RelationshipHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected List<StateTransition> getRealizedStateTransitions(StateTransition element_p) {
	  List<StateTransition> result = new ArrayList<StateTransition>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof StateTransitionRealization) {
        StateTransition transition = ((StateTransitionRealization) trace).getRealizedStateTransition();
        if (null != transition) {
          result.add(transition);
        }
      }
    }
    return result;
	}

  protected List<StateTransition> getRealizingStateTransitions(StateTransition element_p) {
    List<StateTransition> result = new ArrayList<StateTransition>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
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
