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

import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.cs.delegates.AbstractActorHelper;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.pa.PhysicalActor;

public class LogicalActorHelper {
  private static LogicalActorHelper instance;

  private LogicalActorHelper() {
    // do nothing
  }

  public static LogicalActorHelper getInstance() {
    if (instance == null)
      instance = new LogicalActorHelper();
    return instance;
  }

  public Object doSwitch(LogicalActor element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(LaPackage.Literals.LOGICAL_ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS)) {
      ret = getParticipationInCapabilityRealizations(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_ACTOR__SYSTEM_ACTOR_REALIZATIONS)) {
      ret = getSystemActorRealizations(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_ACTOR__ALLOCATED_LOGICAL_FUNCTIONS)) {
      ret = getAllocatedLogicalFunctions(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_ACTOR__REALIZING_PHYSICAL_ACTORS)) {
      ret = getRealizingPhysicalActors(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_ACTOR__REALIZED_SYSTEM_ACTORS)) {
      ret = getRealizedSystemActors(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractActorHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<ActorCapabilityRealizationInvolvement> getParticipationInCapabilityRealizations(LogicalActor element) {
    List<ActorCapabilityRealizationInvolvement> ret = new ArrayList<>();
    for (CapabilityRealizationInvolvement involvement : element.getInvolvingCapabilityRealizationInvolvements()) {
      if (involvement instanceof ActorCapabilityRealizationInvolvement) {
        ret.add((ActorCapabilityRealizationInvolvement) involvement);
      }
    }
    return ret;
  }

  protected List<SystemActorRealization> getSystemActorRealizations(LogicalActor element) {
    List<SystemActorRealization> ret = new ArrayList<>();
    for (ComponentAllocation componentAllocation : element.getProvisionedComponentAllocations()) {
      if (componentAllocation instanceof SystemActorRealization) {
        ret.add((SystemActorRealization) componentAllocation);
      }
    }
    return ret;
  }

  protected List<LogicalFunction> getAllocatedLogicalFunctions(LogicalActor element) {
    List<LogicalFunction> ret = new ArrayList<>();
    for (AbstractFunction function : element.getAllocatedFunctions()) {
      if (function instanceof LogicalFunction) {
        ret.add((LogicalFunction) function);
      }
    }
    return ret;
  }

  protected List<PhysicalActor> getRealizingPhysicalActors(LogicalActor element) {
    List<PhysicalActor> ret = new ArrayList<>();
    for (Component cpnt : element.getAllocatingComponents()) {
      if (cpnt instanceof PhysicalActor) {
        ret.add((PhysicalActor) cpnt);
      }
    }
    return ret;
  }

  protected List<Actor> getRealizedSystemActors(LogicalActor element) {
    List<Actor> ret = new ArrayList<>();
    for (Component cpnt : element.getAllocatedComponents()) {
      if (cpnt instanceof Actor) {
        ret.add((Actor) cpnt);
      }
    }
    return ret;
  }
}
