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

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ComponentFunctionalAllocationHelper {
	private static ComponentFunctionalAllocationHelper instance;

	private ComponentFunctionalAllocationHelper() {
    // do nothing
	}

	public static ComponentFunctionalAllocationHelper getInstance() {
		if (instance == null)
			instance = new ComponentFunctionalAllocationHelper();
		return instance;
	}

	public Object doSwitch(ComponentFunctionalAllocation element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION__BLOCK)) {
			ret = getBlock(element);
		}
		else if (feature.equals(FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION__FUNCTION)) {
			ret = getFunction(element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AbstractFunctionAllocationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected AbstractFunctionalBlock getBlock(ComponentFunctionalAllocation element) {
		TraceableElement ret = element.getSourceElement();
		if (ret instanceof AbstractFunctionalBlock)
			return (AbstractFunctionalBlock) ret;
		return null;
	}

	protected AbstractFunction getFunction(ComponentFunctionalAllocation element) {
		TraceableElement ret = element.getTargetElement();
		if (ret instanceof AbstractFunction)
			return (AbstractFunction) ret;
		return null;
	}
}
