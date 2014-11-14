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

	public Object doSwitch(ComponentFunctionalAllocation element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION__BLOCK)) {
			ret = getBlock(element_p);
		}
		else if (feature_p.equals(FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION__FUNCTION)) {
			ret = getFunction(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AbstractFunctionAllocationHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected AbstractFunctionalBlock getBlock(ComponentFunctionalAllocation element_p) {
		TraceableElement ret = element_p.getSourceElement();
		if (null != ret && ret instanceof AbstractFunctionalBlock)
			return (AbstractFunctionalBlock) ret;
		return null;
	}

	protected AbstractFunction getFunction(ComponentFunctionalAllocation element_p) {
		TraceableElement ret = element_p.getTargetElement();
		if (null != ret && ret instanceof AbstractFunction)
			return (AbstractFunction) ret;
		return null;
	}
}
