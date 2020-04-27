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

import org.polarsys.capella.core.data.helpers.information.delegates.AbstractInstanceHelper;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class RoleHelper {
	private static RoleHelper instance;

	private RoleHelper() {
    // do nothing
	}

	public static RoleHelper getInstance(){
		if(instance == null)
			instance = new RoleHelper();
		return instance;
	}

	public Object doSwitch(Role element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(OaPackage.Literals.ROLE__ROLE_ALLOCATIONS)) {
			ret = getRoleAllocations(element);
		} else if (feature.equals(OaPackage.Literals.ROLE__ACTIVITY_ALLOCATIONS)) {
			ret = getActivityAllocations(element);
    } else if (feature.equals(OaPackage.Literals.ROLE__ALLOCATING_ENTITIES)) {
      ret = getAllocatingEntities(element);
    } else if (feature.equals(OaPackage.Literals.ROLE__ALLOCATED_OPERATIONAL_ACTIVITIES)) {
      ret = getAllocatedOperationalActivities(element);
		}

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = AbstractInstanceHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

	protected List<RoleAllocation> getRoleAllocations(Role element) {
		List<RoleAllocation> ret = new ArrayList<>();
		for (AbstractTrace abstractTrace : element.getIncomingTraces()) {
			if(abstractTrace instanceof RoleAllocation){
				ret.add((RoleAllocation) abstractTrace);
			}
		}
		return ret;
	}

	protected List<ActivityAllocation> getActivityAllocations(Role element) {
		List<ActivityAllocation> ret = new ArrayList<>();
		for (AbstractTrace abstractTrace : element.getOutgoingTraces()) {
			if(abstractTrace instanceof ActivityAllocation){
				ret.add((ActivityAllocation) abstractTrace);
			}
		}
		return ret;
	}

  protected List<Entity> getAllocatingEntities(Role element) {
    List<Entity> ret = new ArrayList<>();
    for (RoleAllocation roleAllocation : element.getRoleAllocations()) {
      Entity entity = roleAllocation.getEntity();
      if (null != entity){
        ret.add(entity);
      }
    }
    return ret;
  }

  protected List<OperationalActivity> getAllocatedOperationalActivities(Role element) {
    List<OperationalActivity> ret = new ArrayList<>();
    for (ActivityAllocation activityAllocation : element.getActivityAllocations()) {
      OperationalActivity activity = activityAllocation.getActivity();
      if (null != activity){
        ret.add(activity);
      }
    }
    return ret;
  }
}
