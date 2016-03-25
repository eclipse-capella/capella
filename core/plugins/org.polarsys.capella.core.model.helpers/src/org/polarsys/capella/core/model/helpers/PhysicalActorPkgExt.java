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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;

/**
 * LogicalComponentPkg helpers
 * 
 */
public class PhysicalActorPkgExt {
  
  static public PhysicalActorPkg getRootPhysicalActorPkg(PhysicalActorPkg lcPkg) {
    PhysicalActorPkg rootPkg = null;
    if(null != lcPkg) {
      Object container = lcPkg.eContainer();
      if (container instanceof PhysicalArchitecture) {
        return lcPkg;
      }
      if (container instanceof PhysicalActorPkg) {
        rootPkg = getRootPhysicalActorPkg((PhysicalActorPkg) container);
      }
    }
    return rootPkg;
  }

  /**
   * Gets all the LogicalComponents from LogicalComponentPkg
   * 
   * 
   * @param lcPkg the PhysicalActorPkg
   * @return list of LogicalComponents
   */
  static public List<PhysicalActor> getAllPAsFromPAPkg(PhysicalActorPkg lcPkg) {
    List<PhysicalActor> list = new ArrayList<PhysicalActor>();
    if (null != lcPkg) {
      list.addAll(lcPkg.getOwnedPhysicalActors());
      for (PhysicalActorPkg subLCPkg : lcPkg.getOwnedPhysicalActorPkgs()) {
        list.addAll(getAllPAsFromPAPkg(subLCPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces from  PhysicalActorPkg
   * 
   * 
   * @param lcPkg the LogicalComponentPkg
   * @return list of interfaces
   */
  static public List<CapellaElement> getAllInterfacesFromPhysicalActorPkg(PhysicalActorPkg lcPkg) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
 
    List<PhysicalActor> actors = getAllPAsFromPAPkg(lcPkg);
    for (PhysicalActor actor : actors) {
      list.addAll(InterfacePkgExt.getAllInterfaces(actor.getOwnedInterfacePkg()));
    }
    
    return list;
  }
 
}
