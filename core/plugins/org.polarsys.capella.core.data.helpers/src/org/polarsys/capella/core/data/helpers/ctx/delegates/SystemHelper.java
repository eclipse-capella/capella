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

package org.polarsys.capella.core.data.helpers.ctx.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemMissionInvolvement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentHelper;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.CapabilityRealizationInvolvedElementHelper;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class SystemHelper {
	private static SystemHelper instance;

	private SystemHelper() {
    // do nothing
	}

	public static SystemHelper getInstance() {
		if (instance == null)
			instance = new SystemHelper();
		return instance;
	}

	public Object doSwitch(System element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CtxPackage.Literals.SYSTEM__PARTICIPATIONS_IN_CAPABILITIES)) {
			ret = getParticipationsInCapabilities(element);
		} else if (feature.equals(CtxPackage.Literals.SYSTEM__PARTICIPATIONS_IN_MISSIONS)) {
			ret = getParticipationsInMissions(element);
		} else if (feature.equals(CtxPackage.Literals.SYSTEM__CONTRIBUTED_CAPABILITIES)) {
			ret = getContributedCapabilities(element);
		} else if (feature.equals(CtxPackage.Literals.SYSTEM__CONTRIBUTED_MISSIONS)) {
			ret = getContributedMissions(element);
		} else if (feature.equals(CtxPackage.Literals.SYSTEM__ALLOCATED_ENTITY_REALIZATIONS)) {
			ret = getAllocatedEntityRealizations(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM__ALLOCATED_SYSTEM_FUNCTIONS)) {
      ret = getAllocatedSystemFunctions(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM__REALIZED_ENTITIES)) {
      ret = getRealizedEntities(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM__REALIZING_LOGICAL_COMPONENTS)) {
      ret = getRealizingLogicalComponents(element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = CapabilityRealizationInvolvedElementHelper.getInstance().doSwitch(element, feature);
		}
		if(null == ret) {
			ret = ComponentHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected List<SystemCapabilityInvolvement> getParticipationsInCapabilities(System element) {
		List<SystemCapabilityInvolvement> ret = new ArrayList<>();
		for (Involvement involvement : element.getInvolvingInvolvements()) {
			if(involvement instanceof SystemCapabilityInvolvement){
				ret.add((SystemCapabilityInvolvement)involvement);
			}
		}
		return ret;
	}

	protected List<SystemMissionInvolvement> getParticipationsInMissions(System element) {
		List<SystemMissionInvolvement> ret = new ArrayList<>();
		for (Involvement involvement : element.getInvolvingInvolvements()) {
			if(involvement instanceof SystemMissionInvolvement){
				ret.add((SystemMissionInvolvement)involvement);
			}
		}
		return ret;
	}

	protected List<Capability> getContributedCapabilities(System element) {
		List<Capability> ret = new ArrayList<>();
		for (SystemCapabilityInvolvement capabilitySupplierLink : element.getParticipationsInCapabilities()) {
			Capability capa = capabilitySupplierLink.getCapability();
			if(null != capa){
				ret.add(capa);
			}
		}
		return ret;
	}

	protected List<Mission> getContributedMissions(System element) {
		List<Mission> ret = new ArrayList<>();
		for (SystemMissionInvolvement missionSupplierLink : element.getParticipationsInMissions()) {
			Mission mission = missionSupplierLink.getMission();
			if(null != mission){
				ret.add(mission);
			}
		}
		return ret;
	}

	protected List<OperationalEntityRealization> getAllocatedEntityRealizations(System element) {
		List<OperationalEntityRealization> ret = new ArrayList<>();
		for (ComponentAllocation componentAllocation : element.getProvisionedComponentAllocations()) {
			if(componentAllocation instanceof OperationalEntityRealization){
				ret.add((OperationalEntityRealization) componentAllocation);	
			}
		}
		return ret;
	}

  protected List<SystemFunction> getAllocatedSystemFunctions(System element) {
    List<SystemFunction> ret = new ArrayList<>();
    for (AbstractFunction function : element.getAllocatedFunctions()) {
      if (function instanceof SystemFunction) {
        ret.add((SystemFunction) function);
      }
    }
    return ret;
  }

  protected List<Entity> getRealizedEntities(System element) {
    List<Entity> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof OperationalEntityRealization) {
        Component cpnt = ((OperationalEntityRealization)trace).getAllocatedComponent();
        if (cpnt instanceof Entity) {
          ret.add((Entity) cpnt);
        }
      }
    }
    return ret;
  }

  protected List<LogicalComponent> getRealizingLogicalComponents(System element) {
    List<LogicalComponent> ret = new ArrayList<>();
    for (Component cpnt : element.getAllocatingComponents()) {
      if (cpnt instanceof LogicalComponent) {
        ret.add((LogicalComponent) cpnt);
      }
    }
    return ret;
  }
}
