/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

	public Object doSwitch(InterfaceAllocator element,EStructuralFeature feature) {
		Object ret = null;
		
		if (feature.equals(CsPackage.Literals.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES)) {
			ret = getAllocatedInterfaces(element);
		} else	
		if (feature.equals(CsPackage.Literals.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS)) {
			ret = getProvisionedInterfaceAllocations(element);
		} 

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}
	
	protected List<Interface> getAllocatedInterfaces(InterfaceAllocator element) {
		List<InterfaceAllocation> allocs = element.getProvisionedInterfaceAllocations();
		List <Interface> ret = new ArrayList<>();
		
		for (InterfaceAllocation alloc : allocs) {
			Interface allocated = alloc.getAllocatedInterface();
			
			if (null != allocated){
				ret.add(allocated);
			}
		}
		
		return ret;
	}
	
	protected List<InterfaceAllocation> getProvisionedInterfaceAllocations(InterfaceAllocator element) {
		List<AbstractTrace> traces = element.getOutgoingTraces();
		List <InterfaceAllocation> ret = new ArrayList<>();
		
		for (AbstractTrace trace : traces) {
						
			if (trace instanceof InterfaceAllocation){
				ret.add((InterfaceAllocation) trace);
			}
		}
		
		return ret;
	}
}
