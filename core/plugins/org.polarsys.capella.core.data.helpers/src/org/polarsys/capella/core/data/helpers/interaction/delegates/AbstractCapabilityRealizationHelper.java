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

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class AbstractCapabilityRealizationHelper {
	private static AbstractCapabilityRealizationHelper instance;

	private AbstractCapabilityRealizationHelper() {
    // do nothing
	}

	public static AbstractCapabilityRealizationHelper getInstance() {
		if (instance == null)
			instance = new AbstractCapabilityRealizationHelper();
		return instance;
	}

	public Object doSwitch(AbstractCapabilityRealization element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION__REALIZED_CAPABILITY)) {
			ret = getRealizedCapability(element);
		}
		else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION__REALIZING_CAPABILITY)) {
			ret = getRealizingCapability(element);
		} 

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected AbstractCapability getRealizedCapability(AbstractCapabilityRealization element) {
		TraceableElement ret = element.getTargetElement();
		if(ret instanceof AbstractCapability)
			return (AbstractCapability) ret;
		return null;
	}

	protected AbstractCapability getRealizingCapability(AbstractCapabilityRealization element) {
		TraceableElement ret = element.getSourceElement();
		if(ret instanceof AbstractCapability)
			return (AbstractCapability) ret;
		return null;
	}
}
