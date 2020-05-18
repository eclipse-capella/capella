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

package org.polarsys.capella.core.data.helpers.capellacommon.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvedElementHelper;
import org.polarsys.capella.core.data.la.CapabilityRealization;


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

		if (feature.equals(CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS)) {
			ret = getCapabilityRealizationInvolvements(element);
		} else if (feature.equals(CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS)) {
      ret = getInvolvingCapabilityRealizations(element);
    }

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = InvolvedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}
	
	protected List<CapabilityRealizationInvolvement> getCapabilityRealizationInvolvements(CapabilityRealizationInvolvedElement element){
		List<CapabilityRealizationInvolvement> ret = new ArrayList<> ();
		for (Involvement involvement : element.getInvolvingInvolvements()) {
			if(involvement instanceof CapabilityRealizationInvolvement){
				ret.add((CapabilityRealizationInvolvement) involvement);
			}
		}
		return ret;
	}
	
  protected List<CapabilityRealization> getInvolvingCapabilityRealizations(CapabilityRealizationInvolvedElement element){
    List<CapabilityRealization> ret = new ArrayList<> ();
    for (CapabilityRealizationInvolvement involvement : element.getCapabilityRealizationInvolvements()) {
      InvolverElement involver = involvement.getInvolver();
      if (involver instanceof CapabilityRealization)
        ret.add((CapabilityRealization) involver);
    }
    return ret;
  }
}
