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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvedElementHelper;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

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

	public Object doSwitch(Entity element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(OaPackage.Literals.ENTITY__SUB_ENTITIES)) {
			ret = getSubEntities(element_p);
		} else if (feature_p.equals(OaPackage.Literals.ENTITY__ROLE_ALLOCATIONS)) {
			ret = getRoleAllocations(element_p);
    } else if (feature_p.equals(OaPackage.Literals.ENTITY__ALLOCATED_OPERATIONAL_ACTIVITIES)) {
      ret = getAllocatedOperationalActivities(element_p);
    } else if (feature_p.equals(OaPackage.Literals.ENTITY__INVOLVING_OPERATIONAL_CAPABILITIES)) {
      ret = getInvolvingOperationalCapabilities(element_p);
    } else if (feature_p.equals(OaPackage.Literals.ENTITY__REALIZING_SYSTEMS)) {
      ret = getRealizingSystems(element_p);
    } else if (feature_p.equals(OaPackage.Literals.ENTITY__ALLOCATED_ROLES)) {
      ret = getAllocatedRoles(element_p);
    } else if (feature_p.equals(OaPackage.Literals.ENTITY__REALIZING_ACTORS)) {
      ret = getRealizingActors(element_p);
		}

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = ComponentHelper.getInstance().doSwitch(element_p, feature_p);
		}
    if (null == ret) {
      ret = InformationsExchangerHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = InvolvedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}

	protected List<RoleAllocation> getRoleAllocations(Entity element_p) {
		List<RoleAllocation> ret = new ArrayList<RoleAllocation>();

		for (AbstractTrace abstractTrace : element_p.getOutgoingTraces()) {
			if (abstractTrace instanceof RoleAllocation) {
				ret.add((RoleAllocation) abstractTrace);
			}
		}
		return ret;
	}

	protected List<Entity> getSubEntities(Entity element_p) {
		List<Entity> ret = new ArrayList<Entity>();

		for (Partition thePartition : element_p.getOwnedPartitions()) {
			Type representedElement = thePartition.getType();
			if (null != representedElement && representedElement instanceof Entity) {
				ret.add((Entity) representedElement);
			}
		}
		return ret;
	}

  protected List<OperationalActivity> getAllocatedOperationalActivities(Entity element_p) {
    List<OperationalActivity> ret = new ArrayList<OperationalActivity>();
    for (AbstractFunction function : element_p.getAllocatedFunctions()) {
      if (function instanceof OperationalActivity) {
        ret.add((OperationalActivity) function);
      }
    }
    return ret;
  }

  protected List<OperationalCapability> getInvolvingOperationalCapabilities(Entity element_p) {
    List<OperationalCapability> ret = new ArrayList<OperationalCapability>();
    for (Involvement inv : element_p.getInvolvingInvolvements()) {
      if (inv instanceof EntityOperationalCapabilityInvolvement) {
        OperationalCapability cap = ((EntityOperationalCapabilityInvolvement) inv).getCapability();
        if (null != cap) {
          ret.add(cap);
        }
      }
    }
    return ret;
  }

  protected List<System> getRealizingSystems(Entity element_p) {
    List<System> ret = new ArrayList<System>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if ((trace instanceof OperationalEntityRealization) || (trace instanceof OperationalActorRealization)) {
        Component cpnt = ((ComponentAllocation)trace).getAllocatingComponent();
        if (cpnt instanceof System) {
          ret.add((System) cpnt);
        }
      }
    }
    return ret;
  }

  protected List<Actor> getRealizingActors(Entity element_p) {
    List<Actor> ret = new ArrayList<Actor>();
    if (!(element_p instanceof OperationalActor)) {
      for (AbstractTrace trace : element_p.getIncomingTraces()) {
        if (trace instanceof OperationalEntityRealization) {
          Component cpnt = ((ComponentAllocation)trace).getAllocatingComponent();
          if (cpnt instanceof Actor) {
            ret.add((Actor) cpnt);
          }
        }
      }
    }
    return ret;
  }

  protected List<Role> getAllocatedRoles(Entity element_p) {
    List<Role> ret = new ArrayList<Role>();
    for (RoleAllocation roleAllocation : element_p.getRoleAllocations()) {
      Role role = roleAllocation.getRole();
      if (null != role){
        ret.add(role);
      }
    }
    return ret;
  }
}
