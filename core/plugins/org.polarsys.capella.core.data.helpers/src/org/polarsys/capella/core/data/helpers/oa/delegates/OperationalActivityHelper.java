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

  public Object doSwitch(OperationalActivity element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_SWIMLANES)) {
      ret = getOwnedSwimlanes(element_p);
    } else if (feature_p.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_PROCESS)) {
      ret = getOwnedProcess(element_p);
    } else if (feature_p.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__ACTIVITY_ALLOCATIONS)) {
      ret = getActivityAllocations(element_p);
    } else if (feature_p.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__ALLOCATOR_ENTITIES)) {
      ret = getAllocatorEntities(element_p);
    } else if (feature_p.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__REALIZING_SYSTEM_FUNCTIONS)) {
      ret = getRealizingSystemFunctions(element_p);
    } else if (feature_p.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__ALLOCATING_ROLES)) {
      ret = getAllocatingRoles(element_p);
    } else if (feature_p.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__CONTAINED_OPERATIONAL_ACTIVITIES)) {
      ret = getContainedOperationalActivities(element_p);
    } else if (feature_p.equals(OaPackage.Literals.OPERATIONAL_ACTIVITY__CHILDREN_OPERATIONAL_ACTIVITIES)) {
      ret = getChildrenOperationalActivities(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractFunctionHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<ActivityAllocation> getActivityAllocations(OperationalActivity element_p) {
    List<ActivityAllocation> ret = new ArrayList<ActivityAllocation>();

    for (AbstractTrace abstractTrace : element_p.getIncomingTraces()) {
      if (abstractTrace instanceof ActivityAllocation) {
        ret.add((ActivityAllocation) abstractTrace);
      }
    }
    return ret;
  }

  protected List<OperationalProcess> getOwnedProcess(OperationalActivity element_p) {
    List<OperationalProcess> ret = new ArrayList<OperationalProcess>();
    for (FunctionalChain functionalChain : element_p.getOwnedFunctionalChains()) {
      if (functionalChain instanceof OperationalProcess) {
        ret.add((OperationalProcess) functionalChain);
      }
    }
    return ret;
  }

  protected List<OperationalActivity> getContainedOperationalActivities(OperationalActivity element_p) {
    List<OperationalActivity> ret = new ArrayList<OperationalActivity>();
    for (AbstractFunction function : element_p.getOwnedFunctions()) {
      if (function instanceof OperationalActivity) {
        ret.add((OperationalActivity) function);
      }
    }
    return ret;
  }

  protected List<OperationalActivity> getChildrenOperationalActivities(OperationalActivity element_p) {
    List<OperationalActivity> ret = new ArrayList<OperationalActivity>();
    for (AbstractFunction function : element_p.getSubFunctions()) {
      if (function instanceof OperationalActivity) {
        ret.add((OperationalActivity) function);
      }
    }
    return ret;
  }

  protected List<Swimlane> getOwnedSwimlanes(OperationalActivity element_p) {
    // FIXME to be updated according to M� enhancements
    //		List<ActivityGroup> groups = element_p.getOwnedGroups();
    List<Swimlane> ret = new ArrayList<Swimlane>();

    return ret;
  }

  protected List<Entity> getAllocatorEntities(OperationalActivity element_p) {
    List<Entity> ret = new ArrayList<Entity>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Entity) {
          ret.add((Entity) src);
        }
      }
    }
    return ret;
  }

  protected List<SystemFunction> getRealizingSystemFunctions(OperationalActivity element_p) {
    List<SystemFunction> ret = new ArrayList<SystemFunction>();
    for (FunctionRealization functionRealization : element_p.getInFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatingFunction();
      if (abstractFunction instanceof SystemFunction) {
        ret.add((SystemFunction) abstractFunction);
      }
    }
    return ret;
  }

  protected List<Role> getAllocatingRoles(OperationalActivity element_p) {
    List<Role> ret = new ArrayList<Role>();
    for (ActivityAllocation activityAllocation : element_p.getActivityAllocations()) {
      Role role = activityAllocation.getRole();
      if (null != role){
        ret.add(role);
      }
    }
    return ret;
  }
}
