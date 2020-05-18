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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvedElementHelper;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;

public class SystemComponentHelper {
  private static SystemComponentHelper instance;

  private SystemComponentHelper() {
    // do nothing
  }

  public static SystemComponentHelper getInstance() {
    if (instance == null)
      instance = new SystemComponentHelper();
    return instance;
  }

  public Object doSwitch(SystemComponent element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CtxPackage.Literals.SYSTEM_COMPONENT__CAPABILITY_INVOLVEMENTS)) {
      ret = getCapabilityInvolvements(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_COMPONENT__INVOLVING_CAPABILITIES)) {
      ret = getInvolvingCapabilities(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_COMPONENT__MISSION_INVOLVEMENTS)) {
      ret = getMissionInvolvements(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_COMPONENT__INVOLVING_MISSIONS)) {
      ret = getInvolvingMissions(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_COMPONENT__REALIZED_ENTITIES)) {
      ret = getRealizedEntities(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_COMPONENT__REALIZING_LOGICAL_COMPONENTS)) {
      ret = getRealizingLogicalComponents(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_COMPONENT__ALLOCATED_SYSTEM_FUNCTIONS)) {
      ret = getAllocatedSystemFunctions(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = ComponentHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InvolvedElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<CapabilityInvolvement> getCapabilityInvolvements(SystemComponent element) {
    List<CapabilityInvolvement> ret = new ArrayList<>();
    for (Involvement involvement : element.getInvolvingInvolvements()) {
      if (involvement instanceof CapabilityInvolvement) {
        ret.add((CapabilityInvolvement) involvement);
      }
    }
    return ret;
  }

  protected List<Capability> getInvolvingCapabilities(SystemComponent element) {
    List<Capability> ret = new ArrayList<>();
    for (CapabilityInvolvement involvement : element.getCapabilityInvolvements()) {
      InvolverElement involver = involvement.getInvolver();
      if (involver instanceof Capability)
        ret.add((Capability) involver);
    }
    return ret;
  }

  protected List<MissionInvolvement> getMissionInvolvements(SystemComponent element) {
    List<MissionInvolvement> ret = new ArrayList<>();
    for (Involvement involvement : element.getInvolvingInvolvements()) {
      if (involvement instanceof MissionInvolvement) {
        ret.add((MissionInvolvement) involvement);
      }
    }
    return ret;
  }

  protected List<Mission> getInvolvingMissions(SystemComponent element) {
    List<Mission> ret = new ArrayList<>();
    for (MissionInvolvement involvement : element.getMissionInvolvements()) {
      InvolverElement involver = involvement.getInvolver();
      if (involver instanceof Mission)
        ret.add((Mission) involver);
    }
    return ret;
  }

  protected List<Entity> getRealizedEntities(SystemComponent element) {
    return element.getRealizedComponents().stream().filter(Entity.class::isInstance).map(Entity.class::cast)
        .collect(Collectors.toList());
  }
  
  protected List<LogicalComponent> getRealizingLogicalComponents(SystemComponent element) {
    return element.getRealizingComponents().stream().filter(LogicalComponent.class::isInstance)
        .map(LogicalComponent.class::cast).collect(Collectors.toList());
  }
  
  protected List<SystemFunction> getAllocatedSystemFunctions(SystemComponent element) {
    List<SystemFunction> ret = new ArrayList<>();
    for (AbstractFunction function : element.getAllocatedFunctions()) {
      if (function instanceof SystemFunction) {
        ret.add((SystemFunction) function);
      }
    }
    return ret;
  }
}
