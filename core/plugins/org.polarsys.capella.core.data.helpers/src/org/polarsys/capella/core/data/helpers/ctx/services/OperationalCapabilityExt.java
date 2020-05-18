/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.ctx.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * Capability helpers
 */
public class OperationalCapabilityExt {
  /**
   * This method adds an involved entity.
   * 
   * @param capability
   *          the capability in which the entity will be involved in
   * @param entity
   *          the involved entity
   */
  public static void addInvolvedEntity(OperationalCapability capability, Entity entity) {
    if ((capability != null) && (entity != null)) {
      if (!getInvolvedEntities(capability).contains(entity)) {
        OperationalCapability opCapability = capability;
        if (capability.eContainer() instanceof OperationalCapability)
          opCapability = (OperationalCapability) capability.eContainer();
        EntityOperationalCapabilityInvolvement involvementLnk = OaFactory.eINSTANCE
            .createEntityOperationalCapabilityInvolvement();

        opCapability.getOwnedEntityOperationalCapabilityInvolvements().add(involvementLnk);
        involvementLnk.setInvolved(entity);
      }
    }
  }

  /**
   * This method retrieves the involved entities.
   * 
   * @param capability
   *          the capability whose contributing entities will be retrieved
   * @return the contributing entities
   */
  public static List<Entity> getInvolvedEntities(OperationalCapability capability) {
    return getInvolvedEntities(capability, false);
  }

  /**
   * This method retrieves the involved entities.
   * 
   * @param capability
   *          the capability whose contributing entities will be retrieved
   * @param recurse
   *          if true, recurse within the inheritance hierarchy
   * @return the contributing entities
   */
  public static List<Entity> getInvolvedEntities(OperationalCapability capability, boolean recurse) {
    List<Entity> involvedEntities = new ArrayList<>();

    EList<Entity> entities = capability.getInvolvedEntities();
    if (!entities.isEmpty())
      involvedEntities.addAll(entities);

    if (recurse) {
      for (AbstractCapability abstractCapability : AbstractCapabilityExt.getInheritanceHierarchy(capability)) {
        involvedEntities.addAll(getInvolvedEntities((OperationalCapability) abstractCapability, false));
      }
    }

    return involvedEntities;
  }
}
