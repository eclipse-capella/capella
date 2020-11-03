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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.AbstractFunctionAllocation;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;

public class AbstractFunctionAllocationHelper {
	private static AbstractFunctionAllocationHelper instance;

	private AbstractFunctionAllocationHelper() {
    // Do nothing
	}

	public static AbstractFunctionAllocationHelper getInstance() {
		if (instance == null) {
			instance = new AbstractFunctionAllocationHelper();
		}
		return instance;
	}
	
	public Object doSwitch(AbstractFunctionAllocation element, EStructuralFeature feature) {
		// no helper found... searching in super classes...
		return AllocationHelper.getInstance().doSwitch(element, feature);
	}
}
