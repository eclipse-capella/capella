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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class InterfaceAllocatorHelper {
	private static InterfaceAllocatorHelper instance;

	private InterfaceAllocatorHelper() {
    // do nothing
	}

	public static InterfaceAllocatorHelper getInstance() {
		if (instance == null)
			instance = new InterfaceAllocatorHelper();
		return instance;
	}

	public Object doSwitch(InterfaceAllocator element_p,EStructuralFeature feature_p) {
		Object ret = null;
		
		if (feature_p.equals(CsPackage.Literals.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES)) {
			ret = getAllocatedInterfaces(element_p);
		} else	
		if (feature_p.equals(CsPackage.Literals.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS)) {
			ret = getProvisionedInterfaceAllocations(element_p);
		} 

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}
	
	protected List<Interface> getAllocatedInterfaces(InterfaceAllocator element_p) {
		List<InterfaceAllocation> allocs = element_p.getProvisionedInterfaceAllocations();
		List <Interface> ret = new ArrayList<Interface>();
		
		for (InterfaceAllocation alloc : allocs) {
			Interface allocated = alloc.getAllocatedInterface();
			
			if (null != allocated){
				ret.add(allocated);
			}
		}
		
		return ret;
	}
	
	protected List<InterfaceAllocation> getProvisionedInterfaceAllocations(InterfaceAllocator element_p) {
		List<AbstractTrace> traces = element_p.getOutgoingTraces();
		List <InterfaceAllocation> ret = new ArrayList<InterfaceAllocation>();
		
		for (AbstractTrace trace : traces) {
						
			if (trace instanceof InterfaceAllocation){
				ret.add((InterfaceAllocation) trace);
			}
		}
		
		return ret;
	}


}
