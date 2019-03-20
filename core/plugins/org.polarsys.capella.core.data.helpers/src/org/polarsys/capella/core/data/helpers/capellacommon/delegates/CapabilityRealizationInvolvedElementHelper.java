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

package org.polarsys.capella.core.data.helpers.capellacommon.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvedElementHelper;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.Involvement;


public class CapabilityRealizationInvolvedElementHelper {
	private static CapabilityRealizationInvolvedElementHelper instance;

	private CapabilityRealizationInvolvedElementHelper() {
	  //
	}

	public static CapabilityRealizationInvolvedElementHelper getInstance(){
		if(instance == null)
			instance = new CapabilityRealizationInvolvedElementHelper();
		return instance;
	}

	public Object doSwitch(CapabilityRealizationInvolvedElement element, EStructuralFeature feature){
		Object ret = null;

		if (feature.equals(CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS)) {
			ret = getInvolvingCapabilityRealizationInvolvements(  element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = InvolvedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected List <CapabilityRealizationInvolvement> getInvolvingCapabilityRealizationInvolvements(CapabilityRealizationInvolvedElement element){
		List<CapabilityRealizationInvolvement> ret = new ArrayList<> ();
		for (Involvement involvement : element.getInvolvingInvolvements()) {
			if(involvement instanceof CapabilityRealizationInvolvement){
				ret.add((CapabilityRealizationInvolvement) involvement);
			}
		}
		return ret;
	}
}
