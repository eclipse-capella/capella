/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvedElementHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentHelper;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;

public class EntityHelper {
	private static EntityHelper instance;

	private EntityHelper() {
	  // do nothing
	}

	public static EntityHelper getInstance() {
		if (instance == null)
			instance = new EntityHelper();
		return instance;
	}

	public Object doSwitch(Entity element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(OaPackage.Literals.ENTITY__SUB_ENTITIES)) {
			ret = getSubEntities(element);
		} else if (feature.equals(OaPackage.Literals.ENTITY__ROLE_ALLOCATIONS)) {
			ret = getRoleAllocations(element);
    } else if (feature.equals(OaPackage.Literals.ENTITY__ALLOCATED_OPERATIONAL_ACTIVITIES)) {
      ret = getAllocatedOperationalActivities(element);
    } else if (feature.equals(OaPackage.Literals.ENTITY__INVOLVING_OPERATIONAL_CAPABILITIES)) {
      ret = getInvolvingOperationalCapabilities(element);
    } else if (feature.equals(OaPackage.Literals.ENTITY__ALLOCATED_ROLES)) {
      ret = getAllocatedRoles(element);
    } else if (feature.equals(OaPackage.Literals.ENTITY__REALIZING_SYSTEM_COMPONENTS)) {
      ret = getRealizingSystemComponents(element);
		}

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = ComponentHelper.getInstance().doSwitch(element, feature);
		}
    if (null == ret) {
      ret = InformationsExchangerHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InvolvedElementHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

	protected List<RoleAllocation> getRoleAllocations(Entity element) {
		List<RoleAllocation> ret = new ArrayList<>();

		for (AbstractTrace abstractTrace : element.getOutgoingTraces()) {
			if (abstractTrace instanceof RoleAllocation) {
				ret.add((RoleAllocation) abstractTrace);
			}
		}
		return ret;
	}

  protected List<Entity> getSubEntities(Entity element) {
    List<Entity> ret = new ArrayList<>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof Part) {
        Type type = ((Part) feature).getType();
        if (type instanceof Entity)
          ret.add((Entity) type);
      }
    }
    return ret;
  }

  protected List<OperationalActivity> getAllocatedOperationalActivities(Entity element) {
    List<OperationalActivity> ret = new ArrayList<>();
    for (AbstractFunction function : element.getAllocatedFunctions()) {
      if (function instanceof OperationalActivity) {
        ret.add((OperationalActivity) function);
      }
    }
    return ret;
  }

  protected List<OperationalCapability> getInvolvingOperationalCapabilities(Entity element) {
    List<OperationalCapability> ret = new ArrayList<>();
    for (Involvement inv : element.getInvolvingInvolvements()) {
      if (inv instanceof EntityOperationalCapabilityInvolvement) {
        OperationalCapability cap = ((EntityOperationalCapabilityInvolvement) inv).getCapability();
        if (null != cap) {
          ret.add(cap);
        }
      }
    }
    return ret;
  }

  protected List<SystemComponent> getRealizingSystemComponents(Entity element) {
    return element.getRealizingComponents().stream().filter(SystemComponent.class::isInstance)
        .map(SystemComponent.class::cast).collect(Collectors.toList());
  }

  protected List<Role> getAllocatedRoles(Entity element) {
    List<Role> ret = new ArrayList<>();
    for (RoleAllocation roleAllocation : element.getRoleAllocations()) {
      Role role = roleAllocation.getRole();
      if (null != role){
        ret.add(role);
      }
    }
    return ret;
  }
}
