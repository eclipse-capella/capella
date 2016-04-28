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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class BlockArchitectureHelper {
private static BlockArchitectureHelper instance;
	
	private BlockArchitectureHelper() {
    // do nothing
	}
	
	public static BlockArchitectureHelper getInstance(){
		if(instance == null)
			instance = new BlockArchitectureHelper();
		return instance;
	}
	
	public Object doSwitch(BlockArchitecture element, EStructuralFeature feature) {
		
		Object ret = null;
		
		if (feature.equals(CsPackage.Literals.BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES)) {
			ret = getAllocatedArchitectures(element);
		}
		else if (feature.equals(CsPackage.Literals.BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES)) {
			ret = getAllocatingArchitectures(element);
		}
		else if (feature.equals(CsPackage.Literals.BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS)) {
			ret = getProvisionedArchitectureAllocations(element);
		}
		else if (feature.equals(CsPackage.Literals.BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS)) {
			ret = getProvisioningArchitectureAllocations(element);
		} 

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = StructureHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}
	
	protected List<ArchitectureAllocation> getProvisionedArchitectureAllocations(BlockArchitecture element) {
		List<AbstractTrace> traces = element.getOutgoingTraces();
		List <ArchitectureAllocation> ret = new ArrayList<ArchitectureAllocation>();
		
		for (AbstractTrace trace : traces) {
						
			if (trace instanceof ArchitectureAllocation){
				ret.add((ArchitectureAllocation) trace);
			}
		}
		
		return ret;
	}
	
	protected List<ArchitectureAllocation> getProvisioningArchitectureAllocations(BlockArchitecture element) {
		List<AbstractTrace> traces = element.getIncomingTraces();
		List <ArchitectureAllocation> ret = new ArrayList<ArchitectureAllocation>();
		
		for (AbstractTrace trace : traces) {
						
			if (trace instanceof ArchitectureAllocation){
				ret.add((ArchitectureAllocation) trace);
			}
		}
		
		return ret;
	}
	
	protected List<BlockArchitecture> getAllocatedArchitectures(BlockArchitecture element){
		List <ArchitectureAllocation> allocs = element.getProvisionedArchitectureAllocations();
		List <BlockArchitecture> ret = new ArrayList<BlockArchitecture>();

		for (ArchitectureAllocation architectureAllocation : allocs) {
			ret.add(architectureAllocation.getAllocatedArchitecture());
		}
		
		return ret;
	}

	protected List <BlockArchitecture> getAllocatingArchitectures(BlockArchitecture element) {
		List <ArchitectureAllocation> allocs = element.getProvisioningArchitectureAllocations();
		List <BlockArchitecture> ret = new ArrayList<BlockArchitecture>();

		for (ArchitectureAllocation architectureAllocation : allocs) {
			ret.add(architectureAllocation.getAllocatingArchitecture());
		}
		
		return ret;
	}
}
