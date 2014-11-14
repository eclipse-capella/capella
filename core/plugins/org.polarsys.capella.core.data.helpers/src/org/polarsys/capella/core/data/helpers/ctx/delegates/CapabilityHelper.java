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

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

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

	public Object doSwitch(Capability element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(CtxPackage.Literals.CAPABILITY__INVOLVED_ACTORS)) {
			ret = getInvolvedActors(element_p);
		} else if (feature_p.equals(CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM)) {
			ret = getInvolvedSystem(element_p);
		} else if (feature_p.equals(CtxPackage.Literals.CAPABILITY__PURPOSE_MISSIONS)) {
			ret = getPurposeMissions(element_p);
		} else if (feature_p.equals(CtxPackage.Literals.CAPABILITY__PARTICIPATING_ACTORS)) {
			ret = getParticipatingActors(element_p);
		} else if (feature_p.equals(CtxPackage.Literals.CAPABILITY__PARTICIPATING_SYSTEM)) {
			ret = getParticipatingSystem(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES)) {
      ret = getRealizedOperationalCapabilities(element_p);
    } else if (feature_p.equals(CtxPackage.Literals.CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS)) {
      ret = getRealizingCapabilityRealizations(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AbstractCapabilityHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected List<ActorCapabilityInvolvement> getInvolvedActors(Capability element_p) {
		List <ActorCapabilityInvolvement> ret = new ArrayList<ActorCapabilityInvolvement>();
		for (Involvement involvement : element_p.getInvolvedInvolvements()) {
			if (involvement instanceof ActorCapabilityInvolvement) {
				ret.add((ActorCapabilityInvolvement) involvement);
			}
		}
		return ret;
	}

	protected SystemCapabilityInvolvement getInvolvedSystem(Capability element_p) {
		return element_p.getOwnedSystemCapabilityInvolvement();
	}

	protected List<Mission> getPurposeMissions(Capability element_p) {
		List<Mission> ret = new ArrayList<Mission>();
		for (CapabilityExploitation exploitation : element_p.getPurposes()) {
			Mission mission = exploitation.getMission();
			if(null != mission){
				ret.add(mission);
			}
		}
		return ret;
	}

	protected System getParticipatingSystem(Capability element_p) {
		SystemCapabilityInvolvement involvement = element_p.getOwnedSystemCapabilityInvolvement();
		if (null != involvement){
			return involvement.getSystem();
		}
		return null;
	}

	protected List<Actor> getParticipatingActors(Capability element_p) {
		List <Actor> ret = new ArrayList<Actor>();
		for (ActorCapabilityInvolvement involvement : element_p.getInvolvedActors()) {
			Actor actor = involvement.getActor();
			if(null != actor){
				ret.add(actor);
			}
		}
		return ret;
	}

  protected List<OperationalCapability> getRealizedOperationalCapabilities(Capability element_p) {
    List <OperationalCapability> ret = new ArrayList<OperationalCapability>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof AbstractCapabilityRealization){
        AbstractCapability capability = ((AbstractCapabilityRealization) trace).getRealizedCapability();
        if (capability instanceof OperationalCapability) {
          ret.add((OperationalCapability) capability);
        }
      }
    }
    return ret;
  }

  protected List<CapabilityRealization> getRealizingCapabilityRealizations(Capability element_p) {
    List <CapabilityRealization> ret = new ArrayList<CapabilityRealization>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
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
