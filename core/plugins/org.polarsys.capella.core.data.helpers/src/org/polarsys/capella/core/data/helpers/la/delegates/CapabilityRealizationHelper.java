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

package org.polarsys.capella.core.data.helpers.la.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;

public class CapabilityRealizationHelper {
	private static CapabilityRealizationHelper instance;

	private CapabilityRealizationHelper() {
    // do nothing
	}

	public static CapabilityRealizationHelper getInstance(){
		if(instance == null)
			instance = new CapabilityRealizationHelper();
		return instance;
	}

	public Object doSwitch(CapabilityRealization element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_COMPONENTS)) {
			ret = getInvolvedComponents(element);
		} else if (feature.equals(LaPackage.Literals.CAPABILITY_REALIZATION__REALIZED_CAPABILITIES)) {
      ret = getRealizedCapabilities(element);
    } else if (feature.equals(LaPackage.Literals.CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS)) {
      ret = getRealizedCapabilityRealizations(element);
    } else if (feature.equals(LaPackage.Literals.CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS)) {
      ret = getRealizingCapabilityRealizations(element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AbstractCapabilityHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<CapabilityRealizationInvolvedElement> getInvolvedComponents(CapabilityRealization element) {
    List<CapabilityRealizationInvolvedElement> ret = new ArrayList<>();
    for (CapabilityRealizationInvolvement involvement : element.getOwnedCapabilityRealizationInvolvements()) {
      CapabilityRealizationInvolvedElement involvedCapabilityRealizationInvolvedElement = involvement
          .getInvolvedCapabilityRealizationInvolvedElement();
      if (null != involvedCapabilityRealizationInvolvedElement) {
        ret.add(involvedCapabilityRealizationInvolvedElement);
      }
    }
    return ret;
  }

  protected List<Capability> getRealizedCapabilities(CapabilityRealization element) {
    List <Capability> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof AbstractCapabilityRealization){
        AbstractCapability capability = ((AbstractCapabilityRealization) trace).getRealizedCapability();
        if (capability instanceof Capability) {
          ret.add((Capability) capability);
        }
      }
    }
    return ret;
  }

  protected List<CapabilityRealization> getRealizedCapabilityRealizations(CapabilityRealization element) {
    List <CapabilityRealization> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof AbstractCapabilityRealization){
        AbstractCapability capability = ((AbstractCapabilityRealization) trace).getRealizedCapability();
        if (capability instanceof CapabilityRealization) {
          ret.add((CapabilityRealization) capability);
        }
      }
    }
    return ret;
  }

  protected List<CapabilityRealization> getRealizingCapabilityRealizations(CapabilityRealization element) {
    List <CapabilityRealization> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof AbstractCapabilityRealization){
        AbstractCapability capability = ((AbstractCapabilityRealization) trace).getRealizingCapability();
        if (capability instanceof CapabilityRealization) {
          ret.add((CapabilityRealization) capability);
        }
      }
    }
    return ret;
  }
}
