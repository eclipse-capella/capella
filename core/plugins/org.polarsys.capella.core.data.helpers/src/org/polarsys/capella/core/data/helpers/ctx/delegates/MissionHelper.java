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
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemMissionInvolvement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolverElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.capellacore.Involvement;

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

		if (feature.equals(CtxPackage.Literals.MISSION__INVOLVED_ACTORS)) {
			ret = getInvolvedActors(element);
		}
		else if (feature.equals(CtxPackage.Literals.MISSION__INVOLVED_SYSTEM)) {
			ret = getInvolvedSystem(element);
		}
		else if (feature.equals(CtxPackage.Literals.MISSION__PARTICIPATING_ACTORS)) {
			ret = getParticipatingActors(element);
		}
		else if (feature.equals(CtxPackage.Literals.MISSION__PARTICIPATING_SYSTEM)) {
			ret = getParticipatingSystem(element);
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

	protected List<ActorMissionInvolvement> getInvolvedActors(Mission element) {
		List<Involvement> involvements = element.getInvolvedInvolvements();
		List <ActorMissionInvolvement> ret = new ArrayList<>();
		for (Involvement involvement : involvements) {
			if (involvement instanceof ActorMissionInvolvement) {
				ret.add((ActorMissionInvolvement) involvement);
			}
		}
		return ret;
	}

	protected SystemMissionInvolvement getInvolvedSystem(Mission element) {
		return element.getOwnedSystemMissionInvolvement();
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

	protected System getParticipatingSystem(Mission element) {
		SystemMissionInvolvement involvement = element.getOwnedSystemMissionInvolvement();
		if (null != involvement){
			return involvement.getSystem();
		}
		return null;
	}

	protected List<Actor> getParticipatingActors(Mission element) {
		List<ActorMissionInvolvement> involvements = element.getInvolvedActors();
		List <Actor> ret = new ArrayList<>();
		for (ActorMissionInvolvement involvement : involvements) {
			Actor actor = involvement.getActor();
			if(null != actor){
				ret.add(actor);
			}
		}
		return ret;
	}
}
