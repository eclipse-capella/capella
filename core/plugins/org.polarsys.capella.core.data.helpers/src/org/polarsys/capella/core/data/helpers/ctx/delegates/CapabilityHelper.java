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

package org.polarsys.capella.core.data.helpers.ctx.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.oa.OperationalCapability;

public class CapabilityHelper {
	private static CapabilityHelper instance;

	private CapabilityHelper() {
    // do nothing
	}

	public static CapabilityHelper getInstance() {
		if (instance == null)
			instance = new CapabilityHelper();
		return instance;
	}

	public Object doSwitch(Capability element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CtxPackage.Literals.CAPABILITY__PURPOSE_MISSIONS)) {
			ret = getPurposeMissions(element);
		} else if (feature.equals(CtxPackage.Literals.CAPABILITY__PURPOSES)) {
      ret = getPurposes(element);
    } else if (feature.equals(CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM_COMPONENTS)) {
			ret = getInvolvedSystemComponents(element);
		} else if (feature.equals(CtxPackage.Literals.CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES)) {
      ret = getRealizedOperationalCapabilities(element);
    } else if (feature.equals(CtxPackage.Literals.CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS)) {
      ret = getRealizingCapabilityRealizations(element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AbstractCapabilityHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected List<CapabilityExploitation> getPurposes(Capability element) {
	  return EObjectExt.getReferencers(element, CtxPackage.Literals.CAPABILITY_EXPLOITATION__CAPABILITY);
	}

	protected List<Mission> getPurposeMissions(Capability element) {
		List<Mission> ret = new ArrayList<>();
		for (CapabilityExploitation exploitation : element.getPurposes()) {
			Mission mission = exploitation.getMission();
			if(null != mission){
				ret.add(mission);
			}
		}
		return ret;
	}

  protected List<SystemComponent> getInvolvedSystemComponents(Capability element) {
    List<SystemComponent> ret = new ArrayList<>();
    for (CapabilityInvolvement involvement : element.getOwnedCapabilityInvolvements()) {
      SystemComponent systemComponent = involvement.getSystemComponent();
      if (null != systemComponent) {
        ret.add(systemComponent);
      }
    }
    return ret;
  }

  protected List<OperationalCapability> getRealizedOperationalCapabilities(Capability element) {
    List <OperationalCapability> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof AbstractCapabilityRealization){
        AbstractCapability capability = ((AbstractCapabilityRealization) trace).getRealizedCapability();
        if (capability instanceof OperationalCapability) {
          ret.add((OperationalCapability) capability);
        }
      }
    }
    return ret;
  }

  protected List<CapabilityRealization> getRealizingCapabilityRealizations(Capability element) {
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
