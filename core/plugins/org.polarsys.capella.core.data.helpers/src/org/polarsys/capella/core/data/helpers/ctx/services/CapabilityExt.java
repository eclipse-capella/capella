/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;

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
    if (recurse) {
      List<SystemComponent> involvedSystemComponents = new ArrayList<>(capability.getInvolvedSystemComponents());
      for (AbstractCapability superCapability : AbstractCapabilityExt.getInheritanceHierarchy(capability)) {
        involvedSystemComponents.addAll(getInvolvedSystemComponents((Capability) superCapability, false));
      }
      return involvedSystemComponents;
    }
    return capability.getInvolvedSystemComponents();
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

  /**
   * Returns all related scenarios for a given capability, including those on recursive levels. This function is
   * recursive.
   * 
   * @param capability
   *          the capability.
   * @return all the related scenarios for a capability, including those on recursive levels.
   */
  public static Set<Scenario> getAllRelatedScenarios(AbstractCapability capability) {
    EList<Scenario> ownedScenarios = capability.getOwnedScenarios();
    Set<Scenario> externalReferencedScenarios = new LinkedHashSet<Scenario>();
    for (Scenario ownedScenario : ownedScenarios) {
      recursiveGetExternalReferencedScenarios(ownedScenario, externalReferencedScenarios);
    }
    externalReferencedScenarios.removeAll(ownedScenarios);
    return externalReferencedScenarios;
  }

  /**
   * This is a recursive version that returns all related functional chains for a functional chain, including those on
   * recursive levels.
   * 
   * @param fc
   *          the functional chain.
   * @param gatheredFunctionalChains
   *          the functional chain accumulator.
   */
  private static void recursiveGetExternalReferencedScenarios(Scenario aScenario,
      Set<Scenario> alreadyGatheredScenarios) {
    EList<Scenario> referencedScenarios = aScenario.getReferencedScenarios();
    List<InteractionUse> uses = aScenario.getOwnedTimeLapses().stream().filter(InteractionUse.class::isInstance)
        .map(InteractionUse.class::cast).collect(Collectors.toList());
    List<Scenario> usedScenarios = uses.stream().map(interactionUse -> interactionUse.getReferencedScenario())
        .collect(Collectors.toList());

    Set<Scenario> allReferencedScenarios = new HashSet<Scenario>(referencedScenarios);
    allReferencedScenarios.addAll(usedScenarios);

    for (Scenario aReferencedScenario : allReferencedScenarios) {
      if (!alreadyGatheredScenarios.contains(aReferencedScenario)) {
        alreadyGatheredScenarios.add(aReferencedScenario);
        recursiveGetExternalReferencedScenarios(aReferencedScenario, alreadyGatheredScenarios);
      }
    }
  }
}
