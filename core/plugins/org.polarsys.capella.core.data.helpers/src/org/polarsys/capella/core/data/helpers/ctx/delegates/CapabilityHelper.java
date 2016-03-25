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

	public Object doSwitch(Capability element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CtxPackage.Literals.CAPABILITY__INVOLVED_ACTORS)) {
			ret = getInvolvedActors(element);
		} else if (feature.equals(CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM)) {
			ret = getInvolvedSystem(element);
		} else if (feature.equals(CtxPackage.Literals.CAPABILITY__PURPOSE_MISSIONS)) {
			ret = getPurposeMissions(element);
		} else if (feature.equals(CtxPackage.Literals.CAPABILITY__PARTICIPATING_ACTORS)) {
			ret = getParticipatingActors(element);
		} else if (feature.equals(CtxPackage.Literals.CAPABILITY__PARTICIPATING_SYSTEM)) {
			ret = getParticipatingSystem(element);
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

	protected List<ActorCapabilityInvolvement> getInvolvedActors(Capability element) {
		List <ActorCapabilityInvolvement> ret = new ArrayList<ActorCapabilityInvolvement>();
		for (Involvement involvement : element.getInvolvedInvolvements()) {
			if (involvement instanceof ActorCapabilityInvolvement) {
				ret.add((ActorCapabilityInvolvement) involvement);
			}
		}
		return ret;
	}

	protected SystemCapabilityInvolvement getInvolvedSystem(Capability element) {
		return element.getOwnedSystemCapabilityInvolvement();
	}

	protected List<Mission> getPurposeMissions(Capability element) {
		List<Mission> ret = new ArrayList<Mission>();
		for (CapabilityExploitation exploitation : element.getPurposes()) {
			Mission mission = exploitation.getMission();
			if(null != mission){
				ret.add(mission);
			}
		}
		return ret;
	}

	protected System getParticipatingSystem(Capability element) {
		SystemCapabilityInvolvement involvement = element.getOwnedSystemCapabilityInvolvement();
		if (null != involvement){
			return involvement.getSystem();
		}
		return null;
	}

	protected List<Actor> getParticipatingActors(Capability element) {
		List <Actor> ret = new ArrayList<Actor>();
		for (ActorCapabilityInvolvement involvement : element.getInvolvedActors()) {
			Actor actor = involvement.getActor();
			if(null != actor){
				ret.add(actor);
			}
		}
		return ret;
	}

  protected List<OperationalCapability> getRealizedOperationalCapabilities(Capability element) {
    List <OperationalCapability> ret = new ArrayList<OperationalCapability>();
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
    List <CapabilityRealization> ret = new ArrayList<CapabilityRealization>();
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
