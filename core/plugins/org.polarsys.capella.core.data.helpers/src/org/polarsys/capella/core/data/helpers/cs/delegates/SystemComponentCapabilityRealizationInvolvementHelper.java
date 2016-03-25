/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.CapabilityRealizationInvolvementHelper;

public class SystemComponentCapabilityRealizationInvolvementHelper {
	private static SystemComponentCapabilityRealizationInvolvementHelper instance;

	private SystemComponentCapabilityRealizationInvolvementHelper() {
	  // do nothing
	}

	public static SystemComponentCapabilityRealizationInvolvementHelper getInstance() {
		if (instance == null)
			instance = new SystemComponentCapabilityRealizationInvolvementHelper();
		return instance;
	}

	public Object doSwitch(SystemComponentCapabilityRealizationInvolvement element, EStructuralFeature feature) {
		Object ret = null;

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = CapabilityRealizationInvolvementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}
}
