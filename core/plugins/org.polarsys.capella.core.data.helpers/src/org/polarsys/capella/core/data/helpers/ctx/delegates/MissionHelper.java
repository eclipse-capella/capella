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
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolverElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;

public class MissionHelper {
	private static MissionHelper instance;

	private MissionHelper() {
    // do nothing
	}

	public static MissionHelper getInstance() {
		if (instance == null)
			instance = new MissionHelper();
		return instance;
	}

	public Object doSwitch(Mission element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CtxPackage.Literals.MISSION__INVOLVED_SYSTEM_COMPONENTS)) {
			ret = getInvolvedSystemComponents(element);
		}
		else if (feature.equals(CtxPackage.Literals.MISSION__EXPLOITED_CAPABILITIES)) {
			ret = getExploitedCapabilities(element);
		}

		// no helper found... searching in super classes...
    if(null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element, feature);
    }
		if(null == ret) {
			ret = InvolverElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<SystemComponent> getInvolvedSystemComponents(Mission element) {
    List<SystemComponent> ret = new ArrayList<>();
    for (MissionInvolvement involvement : element.getOwnedMissionInvolvements()) {
      SystemComponent systemComponent = involvement.getSystemComponent();
      if (null != systemComponent) {
        ret.add(systemComponent);
      }
    }
    return ret;
  }
  
	protected List<Capability> getExploitedCapabilities(Mission element) {
		List<CapabilityExploitation> exploitations = element.getOwnedCapabilityExploitations();
		List<Capability> ret = new ArrayList<>();
		for (CapabilityExploitation exploitation : exploitations) {
			Capability capa = exploitation.getCapability();
			if(null != capa){
				ret.add(capa);
			}
		}
		return ret;
	}
}
