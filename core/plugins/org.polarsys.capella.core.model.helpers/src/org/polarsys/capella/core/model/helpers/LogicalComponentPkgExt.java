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

import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;


/**
 * LogicalComponentPkg helpers
 * 
 */
public class LogicalComponentPkgExt {
  
  static public LogicalComponentPkg getRootLogicalComponentPkg(LogicalComponentPkg lcPkg_p) {
    LogicalComponentPkg rootPkg = null;
    if(null != lcPkg_p) {
      Object container = lcPkg_p.eContainer();
      if (container instanceof LogicalComponent || container instanceof LogicalArchitecture) {
        return lcPkg_p;
      }
      if (container instanceof LogicalComponentPkg) {
        rootPkg = getRootLogicalComponentPkg((LogicalComponentPkg) container);
      }
    }
    return rootPkg;
  }

  /**
   * Gets all LCDcmps from LCs in a LogicalComponentPkg
   * 
   * 
   * @param lcPkg_p the LogicalComponentPkg
   * @return list of LogicalArchitecture
   */
  static public List<LogicalArchitecture> getAllLogicalArchitectures(LogicalComponentPkg lcPkg_p) {
    List<LogicalArchitecture> list = new ArrayList<LogicalArchitecture>();
    if (null != lcPkg_p) {
      for (LogicalComponent lc : lcPkg_p.getOwnedLogicalComponents()) {
        list.addAll(LogicalComponentExt.getAllLogicalArchitectures( lc));
      }
      for (LogicalComponentPkg subLCPkg : lcPkg_p.getOwnedLogicalComponentPkgs()) {
        list.addAll(getAllLogicalArchitectures(subLCPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the LogicalComponents from LogicalComponentPkg
   * 
   * 
   * @param lcPkg_p the LogicalComponentPkg
   * @return list of LogicalComponents
   */
  static public List<LogicalComponent> getAllLCsFromLCPkg(LogicalComponentPkg lcPkg_p) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>();
    if (null != lcPkg_p) {
      list.addAll(lcPkg_p.getOwnedLogicalComponents());
      for (LogicalComponentPkg subLCPkg : lcPkg_p.getOwnedLogicalComponentPkgs()) {
        list.addAll(getAllLCsFromLCPkg(subLCPkg));
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
  static public List<CapellaElement> getAllInterfacesInLogicalComponentPkg(LogicalComponentPkg lcPkg_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != lcPkg_p) {
      for (LogicalComponent lc : lcPkg_p.getOwnedLogicalComponents()) {
          list.addAll(LogicalComponentExt.getAllInterfacesInLogicalComponent(lc));
      }
      for (LogicalComponentPkg subLCPkg : lcPkg_p.getOwnedLogicalComponentPkgs()) {
        list.addAll(getAllInterfacesInLogicalComponentPkg(subLCPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces used / implemented by lcs in LogicalComponentPkg and sub pkgs.
   * 
   * 
   * @param lcPkg_p
   * @param currentLC_p
   * @param usedFlag_p
   * @return
   */
  static public List<CapellaElement> getAllInterfacesInLogicalComponentPkg(LogicalComponentPkg lcPkg_p, LogicalComponent currentLC_p, boolean usedFlag_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != lcPkg_p) {
      for (LogicalComponent lc : lcPkg_p.getOwnedLogicalComponents()) {
          list.addAll(LogicalComponentExt.getInterfacesFromSameLevelLogicalComponent(  lc, currentLC_p, usedFlag_p));
      }
      for (LogicalComponentPkg subLCPkg : lcPkg_p.getOwnedLogicalComponentPkgs()) {
        list.addAll(getAllInterfacesInLogicalComponentPkg(subLCPkg, currentLC_p, usedFlag_p));
      }
    }
    return list;
  }
}
