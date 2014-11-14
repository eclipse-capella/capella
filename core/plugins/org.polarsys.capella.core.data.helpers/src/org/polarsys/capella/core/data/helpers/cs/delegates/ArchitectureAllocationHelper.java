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

import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ArchitectureAllocationHelper {
	private static ArchitectureAllocationHelper instance;

	private ArchitectureAllocationHelper() {
    // do nothing
	}

	public static ArchitectureAllocationHelper getInstance() {
		if (instance == null)
			instance = new ArchitectureAllocationHelper();
		return instance;
	}

	public Object doSwitch(ArchitectureAllocation element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE)) {
			ret = getAllocatedArchitecture(element_p);
		}
		else if (feature_p.equals(CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE)) {
			ret = getAllocatingArchitecture(element_p);
		} 

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected BlockArchitecture getAllocatedArchitecture(ArchitectureAllocation element_p) {
		TraceableElement ret = element_p.getTargetElement();
		if(null != ret && ret instanceof BlockArchitecture)
			return (BlockArchitecture) ret;
		return null;
	}

	protected BlockArchitecture getAllocatingArchitecture(ArchitectureAllocation element_p) {
		TraceableElement ret = element_p.getSourceElement();
		if(null != ret && ret instanceof BlockArchitecture)
			return (BlockArchitecture) ret;
		return null;
	}
}
