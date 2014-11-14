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
package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ComponentAllocationHelper {
	private static ComponentAllocationHelper instance;

	private ComponentAllocationHelper() {
    // do nothing
	}

	public static ComponentAllocationHelper getInstance() {
		if (instance == null)
			instance = new ComponentAllocationHelper();
		return instance;
	}
	
	public Object doSwitch(ComponentAllocation element_p, EStructuralFeature feature_p) {
		Object ret = null;
		
		if (feature_p.equals(CsPackage.Literals.COMPONENT_ALLOCATION__ALLOCATED_COMPONENT)) {
			ret = getAllocatedComponent(element_p);
		}
		else if (feature_p.equals(CsPackage.Literals.COMPONENT_ALLOCATION__ALLOCATING_COMPONENT)) {
			ret = getAllocatingComponent(element_p);
		} 

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected Component getAllocatedComponent(ComponentAllocation element_p) {
		TraceableElement ret = element_p.getTargetElement();
		if(null != ret && ret instanceof Component)
			return (Component) ret;
		return null;
	}

	protected Component getAllocatingComponent(ComponentAllocation element_p) {
		TraceableElement ret = element_p.getSourceElement();
		if(null != ret && ret instanceof Component)
			return (Component) ret;
		return null;
	}
}
