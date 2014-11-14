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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;

/**
 * LogicalComponentPkg helpers
 * 
 */
public class LogicalActorPkgExt {
  
  static public LogicalActorPkg getRootLogicalActorPkg(LogicalActorPkg lcPkg_p) {
    LogicalActorPkg rootPkg = null;
    if(null != lcPkg_p) {
      Object container = lcPkg_p.eContainer();
      if (container instanceof LogicalArchitecture) {
        return lcPkg_p;
      }
      if (container instanceof LogicalActorPkg) {
        rootPkg = getRootLogicalActorPkg((LogicalActorPkg) container);
      }
    }
    return rootPkg;
  }

  /**
   * Gets all the LogicalComponents from LogicalComponentPkg
   * 
   * @param lcPkg_p the LogicalComponentPkg
   * @return list of LogicalComponents 
   * @link use ActorPkgExt.getAllActors() instead 
   */
  @Deprecated
  static public List<LogicalActor> getAllLAsFromLAPkg(LogicalActorPkg lcPkg_p) {
    List<LogicalActor> list = new ArrayList<LogicalActor>();
    if (null != lcPkg_p) {
      list.addAll(lcPkg_p.getOwnedLogicalActors());
      for (LogicalActorPkg subLCPkg : lcPkg_p.getOwnedLogicalActorPkgs()) {
        list.addAll(getAllLAsFromLAPkg(subLCPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces in LogicalComponentPkg
   * 
   * 
   * @param lcPkg_p the LogicalComponentPkg
   * @return list of interfaces
   */
  static public List<CapellaElement> getAllInterfacesFromLogicalActorPkg(LogicalActorPkg lcPkg_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
 
    List<LogicalActor> allLAsFromLAPkg = getAllLAsFromLAPkg(lcPkg_p);
    for (LogicalActor logicalActor : allLAsFromLAPkg) {
      list.addAll(InterfacePkgExt.getAllInterfaces(logicalActor.getOwnedInterfacePkg()));
    }
    
    return list;
  }
 
}
