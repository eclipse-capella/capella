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

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemMissionInvolvement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;

public class SystemMissionInvolvementHelper {
	private static SystemMissionInvolvementHelper instance;

	private SystemMissionInvolvementHelper() {
    // do nothing
	}

	public static SystemMissionInvolvementHelper getInstance() {
		if (instance == null)
			instance = new SystemMissionInvolvementHelper();
		return instance;
	}

	public Object doSwitch(SystemMissionInvolvement element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(CtxPackage.Literals.SYSTEM_MISSION_INVOLVEMENT__SYSTEM)) {
			ret = getSystem(element_p);
		}
		else if (feature_p.equals(CtxPackage.Literals.SYSTEM_MISSION_INVOLVEMENT__MISSION)) {
			ret = getMission(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = InvolvementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected System getSystem(SystemMissionInvolvement element_p) {
		InvolvedElement elt = element_p.getInvolved();
		if (elt instanceof System)
			return (System) elt;
		return null;
	}

	protected Mission getMission(SystemMissionInvolvement element_p) {
		InvolverElement elt = element_p.getInvolver();
		if (elt instanceof Mission)
			return (Mission) elt;
		return null;
	}
}
