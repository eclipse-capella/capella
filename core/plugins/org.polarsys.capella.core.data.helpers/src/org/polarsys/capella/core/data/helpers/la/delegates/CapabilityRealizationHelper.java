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
package org.polarsys.capella.core.data.helpers.la.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

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

	public Object doSwitch(CapabilityRealization element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_SYSTEM_COMPONENTS)) {
			ret = getInvolvedSystemComponents(element_p);
		} else if (feature_p.equals(LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_ACTORS)) {
			ret = getInvolvedActors(element_p);
		} else if (feature_p.equals(LaPackage.Literals.CAPABILITY_REALIZATION__PARTICIPATING_SYSTEM_COMPONENTS)) {
			ret = getParticitpatingSystemComponents(element_p);
		} else if (feature_p.equals(LaPackage.Literals.CAPABILITY_REALIZATION__PARTICIPATING_ACTORS)) {
			ret = getParticitpatingActors(element_p);
    } else if (feature_p.equals(LaPackage.Literals.CAPABILITY_REALIZATION__REALIZED_CAPABILITIES)) {
      ret = getRealizedCapabilities(element_p);
    } else if (feature_p.equals(LaPackage.Literals.CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS)) {
      ret = getRealizedCapabilityRealizations(element_p);
    } else if (feature_p.equals(LaPackage.Literals.CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS)) {
      ret = getRealizingCapabilityRealizations(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AbstractCapabilityHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected List<SystemComponentCapabilityRealizationInvolvement> getInvolvedSystemComponents(CapabilityRealization element_p) {
		List <SystemComponentCapabilityRealizationInvolvement> ret = new ArrayList<SystemComponentCapabilityRealizationInvolvement>();
		for (Involvement involvement : element_p.getInvolvedInvolvements()) {
			if (involvement instanceof SystemComponentCapabilityRealizationInvolvement) {
				ret.add((SystemComponentCapabilityRealizationInvolvement) involvement);
			}
		}
		return ret;
	}

	protected List<ActorCapabilityRealizationInvolvement> getInvolvedActors(CapabilityRealization element_p) {
		List <ActorCapabilityRealizationInvolvement> ret = new ArrayList<ActorCapabilityRealizationInvolvement>();
		for (Involvement involvement : element_p.getInvolvedInvolvements()) {
			if (involvement instanceof ActorCapabilityRealizationInvolvement) {
				ret.add((ActorCapabilityRealizationInvolvement) involvement);
			}
		}
		return ret;
	}

	protected List<SystemComponent> getParticitpatingSystemComponents(CapabilityRealization element_p) {
		List<SystemComponent> ret = new ArrayList<SystemComponent>();
		for (SystemComponentCapabilityRealizationInvolvement involvement : element_p.getInvolvedSystemComponents()) {
			InvolvedElement comp = involvement.getInvolved();
			if(null != comp && comp instanceof SystemComponent) {
				ret.add((SystemComponent) comp);
			}
		}
		return ret;
	}

	protected List<AbstractActor> getParticitpatingActors(CapabilityRealization element_p) {
		List<AbstractActor> ret = new ArrayList<AbstractActor>();
		for (ActorCapabilityRealizationInvolvement involvement : element_p.getInvolvedActors()) {
			InvolvedElement comp = involvement.getInvolved();
			if(null != comp && comp instanceof AbstractActor) {
				ret.add((AbstractActor) comp);
			}
		}
		return ret;
	}

  protected List<Capability> getRealizedCapabilities(CapabilityRealization element_p) {
    List <Capability> ret = new ArrayList<Capability>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof AbstractCapabilityRealization){
        AbstractCapability capability = ((AbstractCapabilityRealization) trace).getRealizedCapability();
        if (capability instanceof Capability) {
          ret.add((Capability) capability);
        }
      }
    }
    return ret;
  }

  protected List<CapabilityRealization> getRealizedCapabilityRealizations(CapabilityRealization element_p) {
    List <CapabilityRealization> ret = new ArrayList<CapabilityRealization>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof AbstractCapabilityRealization){
        AbstractCapability capability = ((AbstractCapabilityRealization) trace).getRealizedCapability();
        if (capability instanceof CapabilityRealization) {
          ret.add((CapabilityRealization) capability);
        }
      }
    }
    return ret;
  }

  protected List<CapabilityRealization> getRealizingCapabilityRealizations(CapabilityRealization element_p) {
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
