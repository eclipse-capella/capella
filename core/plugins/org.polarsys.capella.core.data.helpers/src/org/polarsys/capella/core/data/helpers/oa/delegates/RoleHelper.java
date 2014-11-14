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

	public Object doSwitch(Role element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(OaPackage.Literals.ROLE__ROLE_ALLOCATIONS)) {
			ret = getRoleAllocations(element_p);
		} else if (feature_p.equals(OaPackage.Literals.ROLE__ACTIVITY_ALLOCATIONS)) {
			ret = getActivityAllocations(element_p);
    } else if (feature_p.equals(OaPackage.Literals.ROLE__ALLOCATING_ENTITIES)) {
      ret = getAllocatingEntities(element_p);
    } else if (feature_p.equals(OaPackage.Literals.ROLE__ALLOCATED_OPERATIONAL_ACTIVITIES)) {
      ret = getAllocatedOperationalActivities(element_p);
		}

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = AbstractInstanceHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}

	protected List<RoleAllocation> getRoleAllocations(Role element_p) {
		List<RoleAllocation> ret = new ArrayList<RoleAllocation>();
		for (AbstractTrace abstractTrace : element_p.getIncomingTraces()) {
			if(abstractTrace instanceof RoleAllocation){
				ret.add((RoleAllocation) abstractTrace);
			}
		}
		return ret;
	}

	protected List<ActivityAllocation> getActivityAllocations(Role element_p) {
		List<ActivityAllocation> ret = new ArrayList<ActivityAllocation>();
		for (AbstractTrace abstractTrace : element_p.getOutgoingTraces()) {
			if(abstractTrace instanceof ActivityAllocation){
				ret.add((ActivityAllocation) abstractTrace);
			}
		}
		return ret;
	}

  protected List<Entity> getAllocatingEntities(Role element_p) {
    List<Entity> ret = new ArrayList<Entity>();
    for (RoleAllocation roleAllocation : element_p.getRoleAllocations()) {
      Entity entity = roleAllocation.getEntity();
      if (null != entity){
        ret.add(entity);
      }
    }
    return ret;
  }

  protected List<OperationalActivity> getAllocatedOperationalActivities(Role element_p) {
    List<OperationalActivity> ret = new ArrayList<OperationalActivity>();
    for (ActivityAllocation activityAllocation : element_p.getActivityAllocations()) {
      OperationalActivity activity = activityAllocation.getActivity();
      if (null != activity){
        ret.add(activity);
      }
    }
    return ret;
  }
}
