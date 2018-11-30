/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
   * @param capability1 the capability in which the actor will be involved in
   * @param actor the involved actor
   */
  public static void addInvolvedActor(Capability capability1, Actor actor) {
    if ((capability1 != null) && (actor != null)) {
      if (!getInvolvedActors(capability1).contains(actor)) {
        Capability capability = capability1;
        if(capability1.eContainer() instanceof Capability)
          capability = (Capability) capability1.eContainer();
        ActorCapabilityInvolvement involvementLnk = CtxFactory.eINSTANCE.createActorCapabilityInvolvement();

        capability.getOwnedActorCapabilityInvolvements().add(involvementLnk);
        involvementLnk.setInvolved(actor);
      }
    }
  }

  /**
   * This method adds an involved component.
   * @param capability the capability in which the component will be involved in
   * @param component the involved component
   */
  public static void addInvolvedComponent(Capability capability, Component component) {
    if (component instanceof Actor) {
      addInvolvedActor(capability, (Actor) component);
    } else if (component instanceof System) {
      addInvolvedSystem(capability, (System) component);
    }
  }

  /**
   * This method adds an involved system.
   * @param capability1 the capability realization in which the system will be involved in
   * @param system the involved system
   */
  public static void addInvolvedSystem(Capability capability1, System system) {
    if ((capability1 != null) && (system != null)) {
      if (!getInvolvedSystems(capability1).contains(system)) {
        Capability capability = (Capability) capability1.eContainer();
        SystemCapabilityInvolvement involvementLnk = CtxFactory.eINSTANCE.createSystemCapabilityInvolvement();

        capability.setOwnedSystemCapabilityInvolvement(involvementLnk);
        involvementLnk.setInvolved(system);
      }
    }
  }

  /**
   * This method retrieves the involved actors.
   * @param capability the capability whose contributing actors will be retrieved
   * @return the contributing actors
   */
  public static List<Actor> getInvolvedActors(Capability capability) {
    return getInvolvedActors(capability, false);
  }

  /**
   * This method retrieves the involved actors.
   * @param capability1 the capability whose contributing actors will be retrieved
   * @param recurse if true, recurse within the inheritance hierarchy
   * @return the contributing actors
   */
  public static List<Actor> getInvolvedActors(Capability capability1, boolean recurse) {
    List<Actor> involvedActors = new ArrayList<Actor>();

    for (ActorCapabilityInvolvement involvement : capability1.getInvolvedActors()) {
      involvedActors.add(involvement.getActor());
    }

    if (recurse) {
      for (AbstractCapability capability : AbstractCapabilityExt.getInheritanceHierarchy(capability1)) {
        involvedActors.addAll(getInvolvedActors((Capability) capability, false));
      }
    }

    return involvedActors;
  }

  /**
   * This method retrieves the involved components.
   * @param capability the capability whose contributing components will be retrieved
   * @return the contributing components
   */
  public static List<Component> getInvolvedComponents(Capability capability) {
    return getInvolvedComponents(capability, false);
  }

  /**
   * This method retrieves the involved components.
   * @param capability the capability whose contributing components will be retrieved
   * @param recurse if true, recurse within the inheritance hierarchy
   * @return the contributing components
   */
  public static List<Component> getInvolvedComponents(Capability capability, boolean recurse) {
    List<Component> involvedComponents = new ArrayList<Component>();

    involvedComponents.addAll(getInvolvedActors(capability, recurse));
    involvedComponents.addAll(getInvolvedSystems(capability, recurse));

    return involvedComponents;
  }

  /**
   * This method retrieves the involved systems.
   * @param capability the capability whose contributing systems will be retrieved
   * @return the contributing systems
   */
  public static List<System> getInvolvedSystems(Capability capability) {
    return getInvolvedSystems(capability, false);
  }

  /**
   * This method retrieves the involved systems.
   * @param capability1 the capability whose contributing systems will be retrieved
   * @param recurse if true, recurse within the inheritance hierarchy
   * @return the contributing systems
   */
  public static List<System> getInvolvedSystems(Capability capability1, boolean recurse) {
    List<System> contributingSystems = new ArrayList<System>();

    SystemCapabilityInvolvement contrib = capability1.getOwnedSystemCapabilityInvolvement();
    if (contrib != null) {
      contributingSystems.add(contrib.getSystem());
    }

    if (recurse) {
      for (AbstractCapability capability : AbstractCapabilityExt.getInheritanceHierarchy(capability1)) {
        contributingSystems.addAll(getInvolvedSystems((Capability) capability, false));
      }
    }

    return contributingSystems;
  }

  /**
   * This method retrieves the purpose missions.
   * @param capability the capability whose purpose missions will be retrieved
   * @return the purpose missions
   */
  public static List<Mission> getPurposeMissions(Capability capability) {
    return getPurposeMissions(capability, false);
  }

  /**
   * This method retrieves the purpose missions.
   * @param capability1 the capability whose purpose missions will be retrieved
   * @param recurse if true, recurse within the inheritance hierarchy
   * @return the purpose missions
   */
  public static List<Mission> getPurposeMissions(Capability capability1, boolean recurse) {
    List<Mission> purposeMissions = new ArrayList<Mission>();

    for (CapabilityExploitation exploitation : capability1.getPurposes()) {
      purposeMissions.add(exploitation.getMission());
    }

    for (AbstractCapability capability : AbstractCapabilityExt.getIncludingHierarchy(capability1)) {
      for (CapabilityExploitation exploitation : ((Capability) capability).getPurposes()) {
        purposeMissions.add(exploitation.getMission());
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
   * @param capability the capability
   * @param actor the actor involved
   * @return true if the actor is involved in a capability
   */
  static public boolean hasInvolved(Capability capability, Actor actor) {
    boolean isInvolved = false;

    for (ActorCapabilityInvolvement actorCapabilityInvolvement : capability.getInvolvedActors()) {
      if (actorCapabilityInvolvement.getActor().equals(actor)) {
        isInvolved = true;
        break;
      }
    }

    return isInvolved;
  }

  /**
   * This method removes an involved actor.
   * @param capability1 the capability in which the actor will not be involved in
   * @param actor1 the non involved actor
   */
  public static void removeInvolvedActor(Capability capability1, Actor actor1) {
    Capability capability = (Capability) capability1.eContainer();
    ActorCapabilityInvolvement actorCapabilityInvolvement = null;
    for (ActorCapabilityInvolvement involvement : capability1.getInvolvedActors()) {
      if (involvement.getActor().equals(actor1)) {
        actorCapabilityInvolvement = involvement;
      }
    }

    if (actorCapabilityInvolvement != null) {
      capability.getOwnedActorCapabilityInvolvements().remove(actorCapabilityInvolvement);
      capability1.getInvolvedActors().remove(actorCapabilityInvolvement);
      actorCapabilityInvolvement.destroy();
    }
  }

  /**
   * This method removes an involved component.
   * @param capability the capability in which the component will not be involved in
   * @param component the non involved component
   */
  public static void removeInvolvedComponent(Capability capability, Component component) {
    if (component instanceof Actor) {
      removeInvolvedActor(capability, (Actor) component);
    } else if (component instanceof System) {
      removeInvolvedSystem(capability, (System) component);
    }
  }

  /**
   * This method removes an involved system.
   * @param capability the capability in which the system will not be involved in
   * @param system the non involved system
   */
  public static void removeInvolvedSystem(Capability capability, System system) {
    SystemCapabilityInvolvement involvement = capability.getOwnedSystemCapabilityInvolvement();
    if (involvement.getSystem().equals(system)) {
      capability.setOwnedSystemCapabilityInvolvement(null);
    }
  }
}
