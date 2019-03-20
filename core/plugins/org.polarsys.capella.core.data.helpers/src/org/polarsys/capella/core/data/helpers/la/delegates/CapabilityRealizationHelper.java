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

	public Object doSwitch(CapabilityRealization element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_SYSTEM_COMPONENTS)) {
			ret = getInvolvedSystemComponents(element);
		} else if (feature.equals(LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_ACTORS)) {
			ret = getInvolvedActors(element);
		} else if (feature.equals(LaPackage.Literals.CAPABILITY_REALIZATION__PARTICIPATING_SYSTEM_COMPONENTS)) {
			ret = getParticitpatingSystemComponents(element);
		} else if (feature.equals(LaPackage.Literals.CAPABILITY_REALIZATION__PARTICIPATING_ACTORS)) {
			ret = getParticitpatingActors(element);
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

	protected List<SystemComponentCapabilityRealizationInvolvement> getInvolvedSystemComponents(CapabilityRealization element) {
		List <SystemComponentCapabilityRealizationInvolvement> ret = new ArrayList<>();
		for (Involvement involvement : element.getInvolvedInvolvements()) {
			if (involvement instanceof SystemComponentCapabilityRealizationInvolvement) {
				ret.add((SystemComponentCapabilityRealizationInvolvement) involvement);
			}
		}
		return ret;
	}

	protected List<ActorCapabilityRealizationInvolvement> getInvolvedActors(CapabilityRealization element) {
		List <ActorCapabilityRealizationInvolvement> ret = new ArrayList<>();
		for (Involvement involvement : element.getInvolvedInvolvements()) {
			if (involvement instanceof ActorCapabilityRealizationInvolvement) {
				ret.add((ActorCapabilityRealizationInvolvement) involvement);
			}
		}
		return ret;
	}

	protected List<SystemComponent> getParticitpatingSystemComponents(CapabilityRealization element) {
		List<SystemComponent> ret = new ArrayList<>();
		for (SystemComponentCapabilityRealizationInvolvement involvement : element.getInvolvedSystemComponents()) {
			InvolvedElement comp = involvement.getInvolved();
			if(comp instanceof SystemComponent) {
				ret.add((SystemComponent) comp);
			}
		}
		return ret;
	}

	protected List<AbstractActor> getParticitpatingActors(CapabilityRealization element) {
		List<AbstractActor> ret = new ArrayList<>();
		for (ActorCapabilityRealizationInvolvement involvement : element.getInvolvedActors()) {
			InvolvedElement comp = involvement.getInvolved();
			if(comp instanceof AbstractActor) {
				ret.add((AbstractActor) comp);
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
