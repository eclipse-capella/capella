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
package org.polarsys.capella.core.data.helpers.ctx.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.cs.delegates.AbstractActorHelper;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class ActorHelper {
	private static ActorHelper instance;

	private ActorHelper() {
    // do nothing
	}

	public static ActorHelper getInstance() {
		if (instance == null)
			instance = new ActorHelper();
		return instance;
	}

	public Object doSwitch(Actor element_p, EStructuralFeature feature_p) {
		Object ret = null;
		
		if (feature_p.equals(CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_CAPABILITIES)) {
			ret = getParticipationsInCapabilities(element_p);
		} else if (feature_p.equals(CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS)) {
			ret = getParticipationsInCapabilityRealizations(element_p);
		} else if (feature_p.equals(CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_MISSIONS)) {
			ret = getParticipationsInMissions(element_p);
		} else if (feature_p.equals(CtxPackage.Literals.ACTOR__CONTRIBUTED_MISSIONS)) {
			ret = getContributedMissions(element_p);
		} else if (feature_p.equals(CtxPackage.Literals.ACTOR__CONTRIBUTED_CAPABILITIES)) {
			ret = getContributedCapabilities(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.ACTOR__ALLOCATED_SYSTEM_FUNCTIONS)) {
      ret = getAllocatedSystemFunctions(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.ACTOR__REALIZING_LOGICAL_ACTORS)) {
      ret = getRealizingLogicalActors(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.ACTOR__REALIZED_OPERATIONAL_ACTORS)) {
      ret = getRealizedOperationalActors(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.ACTOR__REALIZED_ENTITIES)) {
      ret = getRealizedEntities(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AbstractActorHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected List<ActorCapabilityInvolvement> getParticipationsInCapabilities(Actor element_p) {
		List<ActorCapabilityInvolvement> ret = new ArrayList<ActorCapabilityInvolvement>();
		for (Involvement involvement : element_p.getInvolvingInvolvements()) {
			if(involvement instanceof ActorCapabilityInvolvement){
				ret.add((ActorCapabilityInvolvement)involvement);
			}
		}
		return ret;
	}

	protected List<ActorCapabilityRealizationInvolvement> getParticipationsInCapabilityRealizations(Actor element_p) {
		List<ActorCapabilityRealizationInvolvement> ret = new ArrayList<ActorCapabilityRealizationInvolvement>();
		for (CapabilityRealizationInvolvement involvement : element_p.getInvolvingCapabilityRealizationInvolvements()) {
			if(involvement instanceof ActorCapabilityRealizationInvolvement){
				ret.add((ActorCapabilityRealizationInvolvement)involvement);
			}
		}
		return ret;
	}

	protected List<ActorMissionInvolvement> getParticipationsInMissions(Actor element_p) {
		List<ActorMissionInvolvement> ret = new ArrayList<ActorMissionInvolvement>();
		for (Involvement involvement : element_p.getInvolvingInvolvements()) {
			if(involvement instanceof ActorMissionInvolvement){
				ret.add((ActorMissionInvolvement)involvement);
			}
		}
		return ret;
	}

	protected List<Capability> getContributedCapabilities(Actor element_p) {
		List<Capability> ret = new ArrayList<Capability>();
		for (ActorCapabilityInvolvement involvement : element_p.getParticipationsInCapabilities()) {
			Capability capa = involvement.getCapability();
			if(null != capa){
				ret.add(capa);
			}
		}
		return ret;
	}

	protected List<Mission> getContributedMissions(Actor element_p) {
		List<Mission> ret = new ArrayList<Mission>();
		for (ActorMissionInvolvement involvement : element_p.getParticipationsInMissions()) {
			Mission mission = involvement.getMission();
			if(null != mission){
				ret.add(mission);
			}
		}
		return ret;
	}

  protected List<SystemFunction> getAllocatedSystemFunctions(Actor element_p) {
    List<SystemFunction> ret = new ArrayList<SystemFunction>();
    for (AbstractFunction function : element_p.getAllocatedFunctions()) {
      if (function instanceof SystemFunction) {
        ret.add((SystemFunction) function);
      }
    }
    return ret;
  }

  protected List<LogicalActor> getRealizingLogicalActors(Actor element_p) {
    List<LogicalActor> ret = new ArrayList<LogicalActor>();
    for (Component cpnt : element_p.getAllocatingComponents()) {
      if (cpnt instanceof LogicalActor) {
        ret.add((LogicalActor) cpnt);
      }
    }
    return ret;
  }

  protected List<OperationalActor> getRealizedOperationalActors(Actor element_p) {
    List<OperationalActor> ret = new ArrayList<OperationalActor>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof OperationalActorRealization) {
        Component cpnt = ((OperationalActorRealization)trace).getAllocatedComponent();
        if (cpnt instanceof OperationalActor) {
          ret.add((OperationalActor) cpnt);
        }
      }
    }
    return ret;
  }

  protected List<Entity> getRealizedEntities(Actor element_p) {
    List<Entity> ret = new ArrayList<Entity>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof OperationalEntityRealization) {
        Component cpnt = ((OperationalEntityRealization)trace).getAllocatedComponent();
        if ((cpnt instanceof Entity) && !(cpnt instanceof OperationalActor)) {
          ret.add((Entity) cpnt);
        }
      }
    }
    return ret;
  }
}
