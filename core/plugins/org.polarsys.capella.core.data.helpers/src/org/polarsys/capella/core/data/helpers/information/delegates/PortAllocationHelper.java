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
package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class PortAllocationHelper {
	private static PortAllocationHelper instance;

	private PortAllocationHelper() {
    // do nothing
	}

	public static PortAllocationHelper getInstance() {
		if (instance == null)
			instance = new PortAllocationHelper();
		return instance;
	}

	public Object doSwitch(PortAllocation element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(InformationPackage.Literals.PORT_ALLOCATION__ALLOCATED_PORT)) {
			ret = getAllocatedPort(element_p);
		}
		else if (feature_p.equals(InformationPackage.Literals.PORT_ALLOCATION__ALLOCATING_PORT)) {
			ret = getAllocatingPort(element_p);
		} 

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected Port getAllocatingPort(PortAllocation element_p) {
		TraceableElement ret = element_p.getSourceElement();
		if(null != ret && ret instanceof Port)
			return (Port) ret;
		return null;
	}

	protected Port getAllocatedPort(PortAllocation element_p) {
		TraceableElement ret = element_p.getTargetElement();
		if(null != ret && ret instanceof Port)
			return (Port) ret;
		return null;
	}
}
