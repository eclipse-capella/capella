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

package org.polarsys.capella.core.data.helpers.capellacommon.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;


public class CapabilityRealizationInvolvementHelper {
	private static CapabilityRealizationInvolvementHelper instance;

	private CapabilityRealizationInvolvementHelper() {
	  //
	}

	public static CapabilityRealizationInvolvementHelper getInstance(){
		if(instance == null)
			instance = new CapabilityRealizationInvolvementHelper();
		return instance;
	}

	public Object doSwitch(CapabilityRealizationInvolvement element, EStructuralFeature feature){
		Object ret = null;

		if (feature.equals(CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED_CAPABILITY_REALIZATION_INVOLVED_ELEMENT)) {
			ret = getInvolvedCapabilityRealizationInvolvedElement(  element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = InvolvementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected CapabilityRealizationInvolvedElement getInvolvedCapabilityRealizationInvolvedElement(CapabilityRealizationInvolvement element){
		InvolvedElement involved= element.getInvolved();
		if(involved instanceof CapabilityRealizationInvolvedElement){
			return (CapabilityRealizationInvolvedElement) involved;
		}
		return null;
	}
}
