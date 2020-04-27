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

package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class PortRealizationHelper {
	private static PortRealizationHelper instance;

	private PortRealizationHelper() {
    // do nothing
	}

	public static PortRealizationHelper getInstance() {
		if (instance == null)
			instance = new PortRealizationHelper();
		return instance;
	}

	public Object doSwitch(PortRealization element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InformationPackage.Literals.PORT_REALIZATION__REALIZED_PORT)) {
			ret = getRealizedPort(element);
		}
		else if (feature.equals(InformationPackage.Literals.PORT_REALIZATION__REALIZING_PORT)) {
			ret = getRealizingPort(element);
		} 

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected Port getRealizingPort(PortRealization element) {
		TraceableElement ret = element.getSourceElement();
		if(ret instanceof Port)
			return (Port) ret;
		return null;
	}

	protected Port getRealizedPort(PortRealization element) {
		TraceableElement ret = element.getTargetElement();
		if(ret instanceof Port)
			return (Port) ret;
		return null;
	}
}
