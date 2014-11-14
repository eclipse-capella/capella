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
package org.polarsys.capella.core.data.helpers.ctx.services;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;

/**
 * Capability helpers
 */
public class CapabilityExt {

  /**
   * This method adds an involved actor.
   * @param capability_p the capability in which the actor will be involved in
   * @param actor_p the involved actor
   */
  public static void addInvolvedActor(Capability capability_p, Actor actor_p) {
    if ((capability_p != null) && (actor_p != null)) {
      if (!getInvolvedActors(capability_p).contains(actor_p)) {
        Capability capability = (Capability) capability_p.eContainer();
        ActorCapabilityInvolvement involvementLnk = CtxFactory.eINSTANCE.createActorCapabilityInvolvement();

        capability.getOwnedActorCapabilityInvolvements().add(involvementLnk);
        involvementLnk.setInvolver(capability_p);
        involvementLnk.setInvolved(actor_p);
      }
    }
  }

  /**
   * This method adds an involved component.
   * @param capability_p the capability in which the component will be involved in
   * @param component_p the involved component
   */
  public static void addInvolvedComponent(Capability capability_p, Component component_p) {
    if (component_p instanceof Actor) {
      addInvolvedActor(capability_p, (Actor) component_p);
    } else if (component_p instanceof System) {
      addInvolvedSystem(capability_p, (System) component_p);
    }
  }

  /**
   * This method adds an involved system.
   * @param capability_p the capability realization in which the system will be involved in
   * @param system_p the involved system
   */
  public static void addInvolvedSystem(Capability capability_p, System system_p) {
    if ((capability_p != null) && (system_p != null)) {
      if (!getInvolvedSystems(capability_p).contains(system_p)) {
        Capability capability = (Capability) capability_p.eContainer();
        SystemCapabilityInvolvement involvementLnk = CtxFactory.eINSTANCE.createSystemCapabilityInvolvement();

        capability.setOwnedSystemCapabilityInvolvement(involvementLnk);
        involvementLnk.setInvolver(capability_p);
        involvementLnk.setInvolved(system_p);
      }
    }
  }

  /**
   * This method retrieves the involved actors.
   * @param capability_p the capability whose contributing actors will be retrieved
   * @return the contributing actors
   */
  public static List<Actor> getInvolvedActors(Capability capability_p) {
    return getInvolvedActors(capability_p, false);
  }

  /**
   * This method retrieves the involved actors.
   * @param capability_p the capability whose contributing actors will be retrieved
   * @param recurse_p if true, recurse within the inheritance hierarchy
   * @return the contributing actors
   */
  public static List<Actor> getInvolvedActors(Capability capability_p, boolean recurse_p) {
    List<Actor> involvedActors = new ArrayList<Actor>();

    for (ActorCapabilityInvolvement involvement : capability_p.getInvolvedActors()) {
      involvedActors.add(involvement.getActor());
    }

    if (recurse_p) {
      for (AbstractCapability capability : AbstractCapabilityExt.getInheritanceHierarchy(capability_p)) {
        involvedActors.addAll(getInvolvedActors((Capability) capability, false));
      }
    }

    return involvedActors;
  }

  /**
   * This method retrieves the involved components.
   * @param capability_p the capability whose contributing components will be retrieved
   * @return the contributing components
   */
  public static List<Component> getInvolvedComponents(Capability capability_p) {
    return getInvolvedComponents(capability_p, false);
  }

  /**
   * This method retrieves the involved components.
   * @param capability_p the capability whose contributing components will be retrieved
   * @param recurse_p if true, recurse within the inheritance hierarchy
   * @return the contributing components
   */
  public static List<Component> getInvolvedComponents(Capability capability_p, boolean recurse_p) {
    List<Component> involvedComponents = new ArrayList<Component>();

    involvedComponents.addAll(getInvolvedActors(capability_p, recurse_p));
    involvedComponents.addAll(getInvolvedSystems(capability_p, recurse_p));

    return involvedComponents;
  }

  /**
   * This method retrieves the involved systems.
   * @param capability_p the capability whose contributing systems will be retrieved
   * @return the contributing systems
   */
  public static List<System> getInvolvedSystems(Capability capability_p) {
    return getInvolvedSystems(capability_p, false);
  }

  /**
   * This method retrieves the involved systems.
   * @param capability_p the capability whose contributing systems will be retrieved
   * @param recurse_p if true, recurse within the inheritance hierarchy
   * @return the contributing systems
   */
  public static List<System> getInvolvedSystems(Capability capability_p, boolean recurse_p) {
    List<System> contributingSystems = new ArrayList<System>();

    SystemCapabilityInvolvement contrib = capability_p.getOwnedSystemCapabilityInvolvement();
    if (contrib != null) {
      contributingSystems.add(contrib.getSystem());
    }

    if (recurse_p) {
      for (AbstractCapability capability : AbstractCapabilityExt.getInheritanceHierarchy(capability_p)) {
        contributingSystems.addAll(getInvolvedSystems((Capability) capability, false));
      }
    }

    return contributingSystems;
  }

  /**
   * This method retrieves the purpose missions.
   * @param capability_p the capability whose purpose missions will be retrieved
   * @return the purpose missions
   */
  public static List<Mission> getPurposeMissions(Capability capability_p) {
    return getPurposeMissions(capability_p, false);
  }

  /**
   * This method retrieves the purpose missions.
   * @param capability_p the capability whose purpose missions will be retrieved
   * @param recurse_p if true, recurse within the inheritance hierarchy
   * @return the purpose missions
   */
  public static List<Mission> getPurposeMissions(Capability capability_p, boolean recurse_p) {
    List<Mission> purposeMissions = new ArrayList<Mission>();

    for (CapabilityExploitation exploitation : capability_p.getPurposes()) {
      purposeMissions.add(exploitation.getMission());
    }

    for (AbstractCapability capability : AbstractCapabilityExt.getIncludingHierarchy(capability_p)) {
      for (CapabilityExploitation exploitation : ((Capability) capability).getPurposes()) {
        purposeMissions.add(exploitation.getMission());
      }
    }

    if (recurse_p) {
      for (AbstractCapability capability : AbstractCapabilityExt.getInheritanceHierarchy(capability_p)) {
        purposeMissions.addAll(getPurposeMissions((Capability) capability, false));
      }
    }

    return purposeMissions;
  }

  /**
   * This method checks whether an actor is involved in a capability
   * @param capability_p the capability
   * @param actor_p the actor involved
   * @return true if the actor is involved in a capability
   */
  static public boolean hasInvolved(Capability capability_p, Actor actor_p) {
    boolean isInvolved = false;

    for (ActorCapabilityInvolvement actorCapabilityInvolvement : capability_p.getInvolvedActors()) {
      if (actorCapabilityInvolvement.getActor().equals(actor_p)) {
        isInvolved = true;
        break;
      }
    }

    return isInvolved;
  }

  /**
   * This method removes an involved actor.
   * @param capability_p the capability in which the actor will not be involved in
   * @param actor_p the non involved actor
   */
  public static void removeInvolvedActor(Capability capability_p, Actor actor_p) {
    Capability capability = (Capability) capability_p.eContainer();
    ActorCapabilityInvolvement actorCapabilityInvolvement = null;
    for (ActorCapabilityInvolvement involvement : capability_p.getInvolvedActors()) {
      if (involvement.getActor().equals(actor_p)) {
        actorCapabilityInvolvement = involvement;
      }
    }

    if (actorCapabilityInvolvement != null) {
      capability.getOwnedActorCapabilityInvolvements().remove(actorCapabilityInvolvement);
      capability_p.getInvolvedActors().remove(actorCapabilityInvolvement);
      actorCapabilityInvolvement.destroy();
    }
  }

  /**
   * This method removes an involved component.
   * @param capability_p the capability in which the component will not be involved in
   * @param component_p the non involved component
   */
  public static void removeInvolvedComponent(Capability capability_p, Component component_p) {
    if (component_p instanceof Actor) {
      removeInvolvedActor(capability_p, (Actor) component_p);
    } else if (component_p instanceof System) {
      removeInvolvedSystem(capability_p, (System) component_p);
    }
  }

  /**
   * This method removes an involved system.
   * @param capability_p the capability in which the system will not be involved in
   * @param system_p the non involved system
   */
  public static void removeInvolvedSystem(Capability capability_p, System system_p) {
    SystemCapabilityInvolvement involvement = capability_p.getOwnedSystemCapabilityInvolvement();
    if (involvement.getSystem().equals(system_p)) {
      capability_p.setOwnedSystemCapabilityInvolvement(null);
    }
  }
}
