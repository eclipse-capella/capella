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
package org.polarsys.capella.core.projection.exchanges;

import java.util.ListIterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * This class gives some utility methods to help with automated entities exchanges creation.
 * 
 * 
 */
public class EntitiesExchangesHelper {
  /**
   * Gets the entities implementing the given role, if there are any.
   * @param role_p the role
   * @return <code>Entity</code>'s instances
   */
  public static EList<Entity> getImplementingEntities(Role role_p) {
    EList<Entity> entities = new BasicEList<Entity>();
    for (AbstractTrace trace : role_p.getIncomingTraces()) {
      if (trace instanceof RoleAllocation && trace.getSourceElement() instanceof Entity) {
        entities.add((Entity) trace.getSourceElement());
      }
    }
    return entities;
  }

  /**
   * Gets the role implementing the given function if there is any
   * @param function_p
   * @return a <code>Role</code> instance
   */
  public static Role getImplementingRole(AbstractFunction function_p) {
    EList<AbstractTrace> incomingTraces = function_p.getIncomingTraces();
    ListIterator<AbstractTrace> listIterator = incomingTraces.listIterator();
    while (listIterator.hasNext()) {
      AbstractTrace trace = listIterator.next();
      if (trace instanceof ActivityAllocation) {
        Role implementingRole = (Role) trace.getSourceElement();
        return implementingRole;
      }
    }
    return null;
  }

  /**
   * The helper allowing to get the <code>Role</code>feature "ownedActivityAllocations" do not work yet.<br>
   * So this helper is designed to allow to get this feature in the meantime.
   * @param role_p the role
   * @return the feature value
   */
  @SuppressWarnings("unchecked")
  public static EList<ActivityAllocation> getActivityAllocations(Role role_p) {
    return (EList<ActivityAllocation>) role_p.eGet(OaPackage.Literals.ROLE__OWNED_ACTIVITY_ALLOCATIONS);
  }

  /**
   * The helper allowing to get the <code>Entity</code>feature "ownedRoleAllocations" do not work yet.<br>
   * So this helper is designed to allow to get this feature in the meantime.
   * @param entity_p the entity
   * @return the feature value
   */
  @SuppressWarnings("unchecked")
  public static EList<RoleAllocation> getRoleAllocations(Entity entity_p) {
    return (EList<RoleAllocation>) entity_p.eGet(OaPackage.Literals.ENTITY__OWNED_ROLE_ALLOCATIONS);
  }
}
