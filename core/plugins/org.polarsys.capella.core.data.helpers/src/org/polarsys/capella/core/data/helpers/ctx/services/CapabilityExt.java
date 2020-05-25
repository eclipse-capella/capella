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

package org.polarsys.capella.core.data.helpers.ctx.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;

/**
 * Capability helpers
 */
public class CapabilityExt {

  /**
   * This method adds an involved SystemComponent
   * 
   * @param capability
   *          the capability in which the SystemComponent will be involved in
   * @param systemComponent
   *          the involved SystemComponent
   */
  public static void addInvolvedSystemComponent(Capability capability, SystemComponent systemComponent) {
    if (capability != null && systemComponent != null) {
      if (!getInvolvedSystemComponents(capability).contains(systemComponent)) {
        CapabilityInvolvement involvementLnk = CtxFactory.eINSTANCE.createCapabilityInvolvement();
        capability.getOwnedCapabilityInvolvements().add(involvementLnk);
        involvementLnk.setInvolved(systemComponent);
      }
    }
  }

  /**
   * This method retrieves the involved SystemComponents.
   * 
   * @param capability
   *          the capability whose contributing SystemComponents will be retrieved
   * @return the contributing SystemComponents
   */
  public static List<SystemComponent> getInvolvedSystemComponents(Capability capability) {
    return getInvolvedSystemComponents(capability, false);
  }

  /**
   * This method retrieves the involved actors.
   * 
   * @param capability
   *          the capability whose contributing actors will be retrieved
   * @param recurse
   *          if true, recurse within the inheritance hierarchy
   * @return the contributing actors
   */
  public static List<SystemComponent> getInvolvedSystemComponents(Capability capability, boolean recurse) {
    List<SystemComponent> involvedSystemComponents = capability.getInvolvedSystemComponents();
    if (recurse) {
      for (AbstractCapability superCapability : AbstractCapabilityExt.getInheritanceHierarchy(capability)) {
        involvedSystemComponents.addAll(getInvolvedSystemComponents((Capability) superCapability, false));
      }
    }
    return involvedSystemComponents;
  }

  /**
   * This method retrieves the purpose missions.
   * 
   * @param capability
   *          the capability whose purpose missions will be retrieved
   * @return the purpose missions
   */
  public static List<Mission> getPurposeMissions(Capability capability) {
    return getPurposeMissions(capability, false);
  }

  /**
   * This method retrieves the purpose missions.
   * 
   * @param capability1
   *          the capability whose purpose missions will be retrieved
   * @param recurse
   *          if true, recurse within the inheritance hierarchy
   * @return the purpose missions
   */
  public static List<Mission> getPurposeMissions(Capability capability1, boolean recurse) {
    List<Mission> purposeMissions = new ArrayList<>();

    for (CapabilityExploitation exploitation : capability1.getPurposes()) {
      Mission mission = exploitation.getMission();
      if (mission != null) {
        purposeMissions.add(mission);
      }
    }

    for (AbstractCapability capability : AbstractCapabilityExt.getIncludingHierarchy(capability1)) {
      for (CapabilityExploitation exploitation : ((Capability) capability).getPurposes()) {
        Mission mission = exploitation.getMission();
        if (mission != null) {
          purposeMissions.add(mission);
        }
      }
    }

    if (recurse) {
      for (AbstractCapability capability : AbstractCapabilityExt.getInheritanceHierarchy(capability1)) {
        purposeMissions.addAll(getPurposeMissions((Capability) capability, false));
      }
    }

    return purposeMissions;
  }

  /**
   * This method checks whether an actor is involved in a capability
   * 
   * @param capability
   *          the capability
   * @param actor
   *          the actor involved
   * @return true if the actor is involved in a capability
   */
  public static boolean hasInvolved(Capability capability, SystemComponent systemComponent) {
    return capability.getInvolvedSystemComponents().contains(systemComponent);
  }

  /**
   * This method removes an involved component.
   * 
   * @param capability
   *          the capability in which the component will not be involved in
   * @param component
   *          the non involved component
   */
  public static void removeInvolvedSystemComponent(Capability capability, SystemComponent component) {
    List<CapabilityInvolvement> capabilityInvolvementsToRemove = capability.getOwnedCapabilityInvolvements().stream()
        .filter(involvement -> involvement.getSystemComponent() == component).collect(Collectors.toList());
    for (CapabilityInvolvement involvement : capabilityInvolvementsToRemove) {
      involvement.destroy();
    }
  }
}
