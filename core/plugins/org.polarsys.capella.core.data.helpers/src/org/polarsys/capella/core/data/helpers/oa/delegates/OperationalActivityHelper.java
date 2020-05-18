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

package org.polarsys.capella.core.data.helpers.oa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionHelper;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.Swimlane;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class OperationalActivityHelper {
  private static OperationalActivityHelper instance;

  private OperationalActivityHelper() {
    // do nothing
  }

  public static OperationalActivityHelper getInstance() {
    if (instance == null) {
      instance = new OperationalActivityHelper();
    }
    return instance;
  }

  public Object doSwitch(OperationalActivity element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_SWIMLANES)) {
      ret = getOwnedSwimlanes(element);
    } else if (feature.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_PROCESS)) {
      ret = getOwnedProcess(element);
    } else if (feature.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__ACTIVITY_ALLOCATIONS)) {
      ret = getActivityAllocations(element);
    } else if (feature.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__ALLOCATOR_ENTITIES)) {
      ret = getAllocatorEntities(element);
    } else if (feature.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__REALIZING_SYSTEM_FUNCTIONS)) {
      ret = getRealizingSystemFunctions(element);
    } else if (feature.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__ALLOCATING_ROLES)) {
      ret = getAllocatingRoles(element);
    } else if (feature.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__CONTAINED_OPERATIONAL_ACTIVITIES)) {
      ret = getContainedOperationalActivities(element);
    } else if (feature.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__CHILDREN_OPERATIONAL_ACTIVITIES)) {
      ret = getChildrenOperationalActivities(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractFunctionHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<ActivityAllocation> getActivityAllocations(OperationalActivity element) {
    List<ActivityAllocation> ret = new ArrayList<>();

    for (AbstractTrace abstractTrace : element.getIncomingTraces()) {
      if (abstractTrace instanceof ActivityAllocation) {
        ret.add((ActivityAllocation) abstractTrace);
      }
    }
    return ret;
  }

  protected List<OperationalProcess> getOwnedProcess(OperationalActivity element) {
    List<OperationalProcess> ret = new ArrayList<>();
    for (FunctionalChain functionalChain : element.getOwnedFunctionalChains()) {
      if (functionalChain instanceof OperationalProcess) {
        ret.add((OperationalProcess) functionalChain);
      }
    }
    return ret;
  }

  protected List<OperationalActivity> getContainedOperationalActivities(OperationalActivity element) {
    List<OperationalActivity> ret = new ArrayList<>();
    for (AbstractFunction function : element.getOwnedFunctions()) {
      if (function instanceof OperationalActivity) {
        ret.add((OperationalActivity) function);
      }
    }
    return ret;
  }

  protected List<OperationalActivity> getChildrenOperationalActivities(OperationalActivity element) {
    List<OperationalActivity> ret = new ArrayList<>();
    for (AbstractFunction function : element.getSubFunctions()) {
      if (function instanceof OperationalActivity) {
        ret.add((OperationalActivity) function);
      }
    }
    return ret;
  }

  protected List<Swimlane> getOwnedSwimlanes(OperationalActivity element) {
    // FIXME to be updated according to M� enhancements
    //		List<ActivityGroup> groups = element.getOwnedGroups();
    List<Swimlane> ret = new ArrayList<Swimlane>();

    return ret;
  }

  protected List<Entity> getAllocatorEntities(OperationalActivity element) {
    List<Entity> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Entity) {
          ret.add((Entity) src);
        }
      }
    }
    return ret;
  }

  protected List<SystemFunction> getRealizingSystemFunctions(OperationalActivity element) {
    List<SystemFunction> ret = new ArrayList<SystemFunction>();
    for (FunctionRealization functionRealization : element.getInFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatingFunction();
      if (abstractFunction instanceof SystemFunction) {
        ret.add((SystemFunction) abstractFunction);
      }
    }
    return ret;
  }

  protected List<Role> getAllocatingRoles(OperationalActivity element) {
    List<Role> ret = new ArrayList<Role>();
    for (ActivityAllocation activityAllocation : element.getActivityAllocations()) {
      Role role = activityAllocation.getRole();
      if (null != role){
        ret.add(role);
      }
    }
    return ret;
  }
}
