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
	
	public Object doSwitch(ComponentAllocation element, EStructuralFeature feature) {
		Object ret = null;
		
		if (feature.equals(CsPackage.Literals.COMPONENT_ALLOCATION__ALLOCATED_COMPONENT)) {
			ret = getAllocatedComponent(element);
		}
		else if (feature.equals(CsPackage.Literals.COMPONENT_ALLOCATION__ALLOCATING_COMPONENT)) {
			ret = getAllocatingComponent(element);
		} 

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected Component getAllocatedComponent(ComponentAllocation element) {
		TraceableElement ret = element.getTargetElement();
		if(null != ret && ret instanceof Component)
			return (Component) ret;
		return null;
	}

	protected Component getAllocatingComponent(ComponentAllocation element) {
		TraceableElement ret = element.getSourceElement();
		if(null != ret && ret instanceof Component)
			return (Component) ret;
		return null;
	}
}
