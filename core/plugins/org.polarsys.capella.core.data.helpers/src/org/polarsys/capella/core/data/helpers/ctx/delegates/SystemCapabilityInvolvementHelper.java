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

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;

public class SystemCapabilityInvolvementHelper {
	private static SystemCapabilityInvolvementHelper instance;

	private SystemCapabilityInvolvementHelper() {
    // do nothing
	}

	public static SystemCapabilityInvolvementHelper getInstance() {
		if (instance == null)
			instance = new SystemCapabilityInvolvementHelper();
		return instance;
	}

	public Object doSwitch(SystemCapabilityInvolvement element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(CtxPackage.Literals.SYSTEM_CAPABILITY_INVOLVEMENT__SYSTEM)) {
			ret = getSystem(element_p);
		}
		else if (feature_p.equals(CtxPackage.Literals.SYSTEM_CAPABILITY_INVOLVEMENT__CAPABILITY)) {
			ret = getCapability(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = InvolvementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected System getSystem(SystemCapabilityInvolvement element_p) {
		InvolvedElement elt = element_p.getInvolved();
		if (elt instanceof System)
			return (System) elt;
		return null;
	}

	protected Capability getCapability(SystemCapabilityInvolvement element_p) {
		InvolverElement elt = element_p.getInvolver();
		if (elt instanceof Capability)
			return (Capability) elt;
		return null;
	}
}
