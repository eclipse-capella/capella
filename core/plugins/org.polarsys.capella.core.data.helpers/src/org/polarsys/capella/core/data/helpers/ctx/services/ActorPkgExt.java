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

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;

/**
 * ActorPkg helpers
 * 
 */
public class ActorPkgExt {

	/**
	 * Gets all the actors recursively from the ActorPkg
	 * 
	 * 
	 * @param actorPkg_p
	 *            the ActorPkg
	 * @return list of Actors
	 */
	static public List<Actor> getAllActors(ActorPkg actorPkg_p) {
		List<Actor> list = new ArrayList<Actor>();
		if (null != actorPkg_p) {
			list.addAll(actorPkg_p.getOwnedActors());
			for (ActorPkg subActorPkg : actorPkg_p.getOwnedActorPkgs()) {
				list.addAll(getAllActors(subActorPkg));
			}
		}
		return list;
	}
	
	 static public List<LogicalActor> getAllActors(LogicalActorPkg actorPkg_p) {
	    List<LogicalActor> list = new ArrayList<LogicalActor>();
	    if (null != actorPkg_p) {
	      list.addAll(actorPkg_p.getOwnedLogicalActors());
	      for (LogicalActorPkg subActorPkg : actorPkg_p.getOwnedLogicalActorPkgs()) {
	        list.addAll(getAllActors(subActorPkg));
	      }
	    }
	    return list;
	  }
	 
   static public List<PhysicalActor> getAllActors(PhysicalActorPkg actorPkg_p) {
     List<PhysicalActor> list = new ArrayList<PhysicalActor>();
     if (null != actorPkg_p) {
       list.addAll(actorPkg_p.getOwnedPhysicalActors());
       for (PhysicalActorPkg subActorPkg : actorPkg_p.getOwnedPhysicalActorPkgs()) {
         list.addAll(getAllActors(subActorPkg));
       }
     }
     return list;
   }
   
   /**
    * This method retrieves the inherited actors.
    * 
    * @param actor_p
    *            The actor source.
    * @return The list of inherited actors.
    */
   public static List<AbstractActor> getSuperActors(AbstractActor actor_p) {
     List<AbstractActor> superActors = new ArrayList<AbstractActor>();

     for (Generalization actorGeneralization : actor_p.getSuperGeneralizations()) {
       GeneralizableElement superActor = actorGeneralization.getSuper();
       if (superActor != null && superActor instanceof AbstractActor) {
         superActors.add((AbstractActor) superActor);
       }
     }

     return superActors;
   }

}
