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
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;

public class ActorCapabilityInvolvementHelper {
	private static ActorCapabilityInvolvementHelper instance;

	private ActorCapabilityInvolvementHelper() {
    // do nothing
	}

	public static ActorCapabilityInvolvementHelper getInstance() {
		if (instance == null)
			instance = new ActorCapabilityInvolvementHelper();
		return instance;
	}

	public Object doSwitch(ActorCapabilityInvolvement element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(CtxPackage.Literals.ACTOR_CAPABILITY_INVOLVEMENT__ACTOR)) {
			ret = getActor(element_p);
		} else if (feature_p.equals(CtxPackage.Literals.ACTOR_CAPABILITY_INVOLVEMENT__CAPABILITY)) {
			ret = getCapability(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = InvolvementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected Actor getActor(ActorCapabilityInvolvement element_p) {
		InvolvedElement elt = element_p.getInvolved();
		if (elt instanceof Actor)
			return (Actor) elt;
		return null;
	}

	protected Capability getCapability(ActorCapabilityInvolvement element_p) {
		InvolverElement elt = element_p.getInvolver();
		if (elt instanceof Capability)
			return (Capability) elt;
		return null;
	}
}
