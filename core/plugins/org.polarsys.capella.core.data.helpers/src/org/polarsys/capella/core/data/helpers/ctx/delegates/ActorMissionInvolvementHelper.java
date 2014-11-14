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
package org.polarsys.capella.core.data.helpers.ctx.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;

public class ActorMissionInvolvementHelper {
	private static ActorMissionInvolvementHelper instance;

	private ActorMissionInvolvementHelper() {
    // do nothing
	}

	public static ActorMissionInvolvementHelper getInstance() {
		if (instance == null)
			instance = new ActorMissionInvolvementHelper();
		return instance;
	}

	public Object doSwitch(ActorMissionInvolvement element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(CtxPackage.Literals.ACTOR_MISSION_INVOLVEMENT__ACTOR)) {
			ret = getActor(element_p);
		} else if (feature_p.equals(CtxPackage.Literals.ACTOR_MISSION_INVOLVEMENT__MISSION)) {
			ret = getMission(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = InvolvementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected Actor getActor(ActorMissionInvolvement element_p) {
		InvolvedElement elt = element_p.getInvolved();
		if (elt instanceof Actor)
			return (Actor) elt;
		return null;
	}

	protected Mission getMission(ActorMissionInvolvement element_p) {
		InvolverElement elt = element_p.getInvolver();
		if (elt instanceof Mission)
			return (Mission) elt;
		return null;
	}
}
