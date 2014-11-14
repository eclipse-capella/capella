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
package org.polarsys.capella.core.data.helpers.capellacommon;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.ChoicePseudoState;
import org.polarsys.capella.core.data.capellacommon.DeepHistoryPseudoState;
import org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.ForkPseudoState;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacommon.InitialPseudoState;
import org.polarsys.capella.core.data.capellacommon.JoinPseudoState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.ShallowHistoryPseudoState;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.StateEventRealization;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.StateTransitionRealization;
import org.polarsys.capella.core.data.capellacommon.TerminatePseudoState;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.AbstractStateHelper;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.AbstractStateRealizationHelper;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.CapabilityRealizationInvolvedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.CapabilityRealizationInvolvementHelper;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.GenericTraceHelper;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.StateEventHelper;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.StateEventRealizationHelper;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.StateHelper;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.StateTransitionHelper;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.StateTransitionRealizationHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TraceHelper;

public class CapellaCommonHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
		Object ret = null;

		if (object_p instanceof CapabilityRealizationInvolvement) {
			ret = CapabilityRealizationInvolvementHelper.getInstance().doSwitch((CapabilityRealizationInvolvement) object_p, feature_p);
		}
		else if (object_p instanceof CapabilityRealizationInvolvedElement) {
			ret = CapabilityRealizationInvolvedElementHelper.getInstance().doSwitch((CapabilityRealizationInvolvedElement) object_p, feature_p);
		}
		else if (object_p instanceof GenericTrace) {
			ret = GenericTraceHelper.getInstance().doSwitch((GenericTrace) object_p, feature_p);
		}
		else if (object_p instanceof StateMachine) {
			ret = CapellaElementHelper.getInstance().doSwitch((StateMachine) object_p, feature_p);
		}
		else if (object_p instanceof Region) {
			ret = NamedElementHelper.getInstance().doSwitch((Region) object_p, feature_p);
		}
    else if (object_p instanceof ChoicePseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((ChoicePseudoState) object_p, feature_p);
    }
    else if (object_p instanceof ForkPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((ForkPseudoState) object_p, feature_p);
    }
    else if (object_p instanceof InitialPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((InitialPseudoState) object_p, feature_p);
    }
    else if (object_p instanceof JoinPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((JoinPseudoState) object_p, feature_p);
    }
    else if (object_p instanceof TerminatePseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((TerminatePseudoState) object_p, feature_p);
    }
    else if (object_p instanceof EntryPointPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((EntryPointPseudoState) object_p, feature_p);
    }
    else if (object_p instanceof ExitPointPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((ExitPointPseudoState) object_p, feature_p);
    }
    else if (object_p instanceof ShallowHistoryPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((ShallowHistoryPseudoState) object_p, feature_p);
    }
    else if (object_p instanceof DeepHistoryPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((DeepHistoryPseudoState) object_p, feature_p);
    }
    else if (object_p instanceof FinalState) {
      ret = StateHelper.getInstance().doSwitch((FinalState) object_p, feature_p);
    }
    else if (object_p instanceof Mode) {
      ret = StateHelper.getInstance().doSwitch((Mode) object_p, feature_p);
    }
    else if (object_p instanceof State) {
      ret = StateHelper.getInstance().doSwitch((State) object_p, feature_p);
    }
    else if (object_p instanceof StateTransition) {
      ret = StateTransitionHelper.getInstance().doSwitch((StateTransition) object_p, feature_p);
    }
    else if (object_p instanceof StateEvent) {
      ret = StateEventHelper.getInstance().doSwitch((StateEvent) object_p, feature_p);
    }
    else if (object_p instanceof GenericTrace) {
      ret = TraceHelper.getInstance().doSwitch((GenericTrace) object_p, feature_p);
    }
    else if (object_p instanceof AbstractStateRealization) {
      ret = AbstractStateRealizationHelper.getInstance().doSwitch((AbstractStateRealization) object_p, feature_p);
    }
    else if (object_p instanceof StateTransitionRealization) {
      ret = StateTransitionRealizationHelper.getInstance().doSwitch((StateTransitionRealization) object_p, feature_p);
    }
    else if (object_p instanceof StateEventRealization) {
      ret = StateEventRealizationHelper.getInstance().doSwitch((StateEventRealization) object_p, feature_p);
    }

		if(null != ret || feature_p.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
