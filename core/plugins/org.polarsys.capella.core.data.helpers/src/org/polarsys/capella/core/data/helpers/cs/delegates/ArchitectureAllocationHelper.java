/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

	public Object doSwitch(ArchitectureAllocation element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE)) {
			ret = getAllocatedArchitecture(element);
		}
		else if (feature.equals(CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE)) {
			ret = getAllocatingArchitecture(element);
		} 

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected BlockArchitecture getAllocatedArchitecture(ArchitectureAllocation element) {
		TraceableElement ret = element.getTargetElement();
		if(ret instanceof BlockArchitecture)
			return (BlockArchitecture) ret;
		return null;
	}

	protected BlockArchitecture getAllocatingArchitecture(ArchitectureAllocation element) {
		TraceableElement ret = element.getSourceElement();
		if(ret instanceof BlockArchitecture)
			return (BlockArchitecture) ret;
		return null;
	}
}
