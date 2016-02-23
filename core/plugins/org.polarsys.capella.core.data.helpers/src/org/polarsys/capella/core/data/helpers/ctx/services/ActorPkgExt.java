/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;

/**
 * ActorPkg helpers
 * 
 */
public class ActorPkgExt {

  /**
   * @deprecated Use getAllActors(Structure) instead
   */
  @Deprecated
  static public List<Actor> getAllActors(ActorPkg actorPkg) {
    return (List) getAllActors((Structure) actorPkg);
  }

  /**
   * Gets all the actors recursively from the ActorPkg
   */
  static public List<AbstractActor> getAllActors(Structure actorPkg) {
    List<AbstractActor> list = new ArrayList<AbstractActor>();
    if (null != actorPkg) {
      if (actorPkg instanceof ActorPkg) {
        ActorPkg pkg = (ActorPkg) actorPkg;
        list.addAll(pkg.getOwnedActors());
        for (ActorPkg subActorPkg : pkg.getOwnedActorPkgs()) {
          list.addAll(getAllActors(subActorPkg));
        }
      } else if (actorPkg instanceof LogicalActorPkg) {
        LogicalActorPkg pkg = (LogicalActorPkg) actorPkg;
        list.addAll(pkg.getOwnedLogicalActors());
        for (LogicalActorPkg subActorPkg : pkg.getOwnedLogicalActorPkgs()) {
          list.addAll(getAllActors(subActorPkg));
        }
      } else if (actorPkg instanceof PhysicalActorPkg) {
        PhysicalActorPkg pkg = (PhysicalActorPkg) actorPkg;
        list.addAll(pkg.getOwnedPhysicalActors());
        for (PhysicalActorPkg subActorPkg : pkg.getOwnedPhysicalActorPkgs()) {
          list.addAll(getAllActors(subActorPkg));
        }
      }

    }
    return list;
  }

  /**
   * @deprecated Use getAllActors(Structure) instead
   */
  @Deprecated
  static public List<LogicalActor> getAllActors(LogicalActorPkg actorPkg) {
    return (List) getAllActors((Structure) actorPkg);
  }

  /**
   * @deprecated Use getAllActors(Structure) instead
   */
  @Deprecated
  static public List<PhysicalActor> getAllActors(PhysicalActorPkg actorPkg) {
    return (List) getAllActors((Structure) actorPkg);
  }

  /**
   * This method retrieves the inherited actors.
   * 
   * @param actor
   *          The actor source.
   * @return The list of inherited actors.
   */
  public static List<AbstractActor> getSuperActors(AbstractActor actor) {
    List<AbstractActor> superActors = new ArrayList<AbstractActor>();

    for (Generalization actorGeneralization : actor.getSuperGeneralizations()) {
      GeneralizableElement superActor = actorGeneralization.getSuper();
      if ((superActor != null) && (superActor instanceof AbstractActor)) {
        superActors.add((AbstractActor) superActor);
      }
    }

    return superActors;
  }

}
