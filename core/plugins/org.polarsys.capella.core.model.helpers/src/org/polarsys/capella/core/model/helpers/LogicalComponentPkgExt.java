/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  
  static public LogicalComponentPkg getRootLogicalComponentPkg(LogicalComponentPkg lcPkg) {
    LogicalComponentPkg rootPkg = null;
    if(null != lcPkg) {
      Object container = lcPkg.eContainer();
      if (container instanceof LogicalComponent || container instanceof LogicalArchitecture) {
        return lcPkg;
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
   * @param lcPkg the LogicalComponentPkg
   * @return list of LogicalArchitecture
   */
  static public List<LogicalArchitecture> getAllLogicalArchitectures(LogicalComponentPkg lcPkg) {
    List<LogicalArchitecture> list = new ArrayList<LogicalArchitecture>();
    if (null != lcPkg) {
      for (LogicalComponent lc : lcPkg.getOwnedLogicalComponents()) {
        list.addAll(LogicalComponentExt.getAllLogicalArchitectures( lc));
      }
      for (LogicalComponentPkg subLCPkg : lcPkg.getOwnedLogicalComponentPkgs()) {
        list.addAll(getAllLogicalArchitectures(subLCPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the LogicalComponents from LogicalComponentPkg
   * 
   * 
   * @param lcPkg the LogicalComponentPkg
   * @return list of LogicalComponents
   */
  static public List<LogicalComponent> getAllLCsFromLCPkg(LogicalComponentPkg lcPkg) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>();
    if (null != lcPkg) {
      list.addAll(lcPkg.getOwnedLogicalComponents());
      for (LogicalComponentPkg subLCPkg : lcPkg.getOwnedLogicalComponentPkgs()) {
        list.addAll(getAllLCsFromLCPkg(subLCPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces in LogicalComponentPkg
   * 
   * 
   * @param lcPkg the LogicalComponentPkg
   * @return list of interfaces
   */
  static public List<CapellaElement> getAllInterfacesInLogicalComponentPkg(LogicalComponentPkg lcPkg) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != lcPkg) {
      for (LogicalComponent lc : lcPkg.getOwnedLogicalComponents()) {
          list.addAll(LogicalComponentExt.getAllInterfacesInLogicalComponent(lc));
      }
      for (LogicalComponentPkg subLCPkg : lcPkg.getOwnedLogicalComponentPkgs()) {
        list.addAll(getAllInterfacesInLogicalComponentPkg(subLCPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces used / implemented by lcs in LogicalComponentPkg and sub pkgs.
   * 
   * 
   * @param lcPkg
   * @param currentLC
   * @param usedFlag
   * @return
   */
  static public List<CapellaElement> getAllInterfacesInLogicalComponentPkg(LogicalComponentPkg lcPkg, LogicalComponent currentLC, boolean usedFlag) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != lcPkg) {
      for (LogicalComponent lc : lcPkg.getOwnedLogicalComponents()) {
          list.addAll(LogicalComponentExt.getInterfacesFromSameLevelLogicalComponent(  lc, currentLC, usedFlag));
      }
      for (LogicalComponentPkg subLCPkg : lcPkg.getOwnedLogicalComponentPkgs()) {
        list.addAll(getAllInterfacesInLogicalComponentPkg(subLCPkg, currentLC, usedFlag));
      }
    }
    return list;
  }
}
