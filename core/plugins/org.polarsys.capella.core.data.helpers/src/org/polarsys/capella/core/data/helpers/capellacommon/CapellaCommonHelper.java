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

	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;

		if (object instanceof CapabilityRealizationInvolvement) {
			ret = CapabilityRealizationInvolvementHelper.getInstance().doSwitch((CapabilityRealizationInvolvement) object, feature);
		}
		else if (object instanceof CapabilityRealizationInvolvedElement) {
			ret = CapabilityRealizationInvolvedElementHelper.getInstance().doSwitch((CapabilityRealizationInvolvedElement) object, feature);
		}
		else if (object instanceof GenericTrace) {
			ret = GenericTraceHelper.getInstance().doSwitch((GenericTrace) object, feature);
		}
		else if (object instanceof StateMachine) {
			ret = CapellaElementHelper.getInstance().doSwitch((StateMachine) object, feature);
		}
		else if (object instanceof Region) {
			ret = NamedElementHelper.getInstance().doSwitch((Region) object, feature);
		}
    else if (object instanceof ChoicePseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((ChoicePseudoState) object, feature);
    }
    else if (object instanceof ForkPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((ForkPseudoState) object, feature);
    }
    else if (object instanceof InitialPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((InitialPseudoState) object, feature);
    }
    else if (object instanceof JoinPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((JoinPseudoState) object, feature);
    }
    else if (object instanceof TerminatePseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((TerminatePseudoState) object, feature);
    }
    else if (object instanceof EntryPointPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((EntryPointPseudoState) object, feature);
    }
    else if (object instanceof ExitPointPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((ExitPointPseudoState) object, feature);
    }
    else if (object instanceof ShallowHistoryPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((ShallowHistoryPseudoState) object, feature);
    }
    else if (object instanceof DeepHistoryPseudoState) {
      ret = AbstractStateHelper.getInstance().doSwitch((DeepHistoryPseudoState) object, feature);
    }
    else if (object instanceof FinalState) {
      ret = StateHelper.getInstance().doSwitch((FinalState) object, feature);
    }
    else if (object instanceof Mode) {
      ret = StateHelper.getInstance().doSwitch((Mode) object, feature);
    }
    else if (object instanceof State) {
      ret = StateHelper.getInstance().doSwitch((State) object, feature);
    }
    else if (object instanceof StateTransition) {
      ret = StateTransitionHelper.getInstance().doSwitch((StateTransition) object, feature);
    }
    else if (object instanceof StateEvent) {
      ret = StateEventHelper.getInstance().doSwitch((StateEvent) object, feature);
    }
    else if (object instanceof GenericTrace) {
      ret = TraceHelper.getInstance().doSwitch((GenericTrace) object, feature);
    }
    else if (object instanceof AbstractStateRealization) {
      ret = AbstractStateRealizationHelper.getInstance().doSwitch((AbstractStateRealization) object, feature);
    }
    else if (object instanceof StateTransitionRealization) {
      ret = StateTransitionRealizationHelper.getInstance().doSwitch((StateTransitionRealization) object, feature);
    }
    else if (object instanceof StateEventRealization) {
      ret = StateEventRealizationHelper.getInstance().doSwitch((StateEventRealization) object, feature);
    }

		if(null != ret || feature.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
