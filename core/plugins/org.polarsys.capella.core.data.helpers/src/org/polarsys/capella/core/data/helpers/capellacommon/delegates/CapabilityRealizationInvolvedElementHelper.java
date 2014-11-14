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

	public Object doSwitch(CapabilityRealizationInvolvedElement element_p, EStructuralFeature feature_p){
		Object ret = null;

		if (feature_p.equals(CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS)) {
			ret = getInvolvingCapabilityRealizationInvolvements(  element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = InvolvedElementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected List <CapabilityRealizationInvolvement> getInvolvingCapabilityRealizationInvolvements(CapabilityRealizationInvolvedElement element_p){
		List<CapabilityRealizationInvolvement> ret = new ArrayList<CapabilityRealizationInvolvement> ();
		for (Involvement involvement : element_p.getInvolvingInvolvements()) {
			if(involvement instanceof CapabilityRealizationInvolvement){
				ret.add((CapabilityRealizationInvolvement) involvement);
			}
		}
		return ret;
	}
}
