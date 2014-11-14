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
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * LogicalArchitecture helpers
 * 
 */
public class LogicalArchitectureExt {

  /**
   * Gets all logical architectures and decompositions
   * 
   * 
   * @param logArch_p the LogicalArchitecture
   * @return list of LogicalArchitecture
   */
  static public List<LogicalArchitecture> getAllLogicalArchitectures(LogicalArchitecture logArch_p) {
    List<LogicalArchitecture> list = new ArrayList<LogicalArchitecture>();
    if (null != logArch_p) {
      list.addAll(LogicalComponentExt.getAllLogicalArchitectures(logArch_p.getOwnedLogicalComponent()));
    }
    return list;
  }

  /**
   * Gets all LogicalComponents from LogicalArchitecture
   * 
   * 
   * @param logArch_p the LogicalArchitecture
   * @return list of LogicalComponents
   */
  static public List<LogicalComponent> getAllLCsFromLogicalArchitectureLayer(LogicalArchitecture logArch_p) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>();
    if (null != logArch_p) {
      LogicalComponent ownedLogicalComponent = logArch_p.getOwnedLogicalComponent();
      if (null != ownedLogicalComponent) {
        list.add(ownedLogicalComponent);
      }

      list.addAll(getAllLCsFromLC(ownedLogicalComponent));
      list.addAll(LogicalComponentPkgExt.getAllLCsFromLCPkg(logArch_p.getOwnedLogicalComponentPkg()));
    }
    return list;
  }

  /**
   * Gets all LogicalComponents from a LogicalComponent
   * 
   * 
   * @param lc_p the LogicalComponent
   * @return list of LogicalComponent
   */
  static public List<LogicalComponent> getAllLCsFromLC(LogicalComponent lc_p) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>();
    if (null != lc_p) {
      //Assumed that the lc is not decomposed (single level and multi level)
      if ((lc_p.getSubLogicalComponents().size() == 0) && (lc_p.getOwnedLogicalArchitectures().size() == 0)) {
        list.add(lc_p);
      }
      for (LogicalComponentPkg lcPkg : lc_p.getOwnedLogicalComponentPkgs()) {
        list.addAll(LogicalComponentPkgExt.getAllLCsFromLCPkg(lcPkg));
      }
      for (LogicalArchitecture logArch : lc_p.getOwnedLogicalArchitectures()) {
        list.addAll(getAllLCsFromLogicalArchitectureLayer(logArch));
      }
      for (LogicalComponent subLC : lc_p.getSubLogicalComponents()) {
        list.addAll(getAllLCsFromLC(subLC));
      }
    }
    return list;
  }

  
  /**
   * Gets all LogicalComponents from a LogicalComponent
   * 
   * 
   * @param lc_p the LogicalComponent
   * @return list of LogicalComponent
   */
  static public List<LogicalComponent> getAllLCFromLC(LogicalComponent lc_p) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>();
    if (null != lc_p) {
      //Assumed that the lc is not decomposed (single level and multi level)
      if ((lc_p.getSubLogicalComponents().size() == 0) && (lc_p.getOwnedLogicalArchitectures().size() == 0)) {
        list.add(lc_p);
      }
      for (LogicalComponentPkg lcPkg : lc_p.getOwnedLogicalComponentPkgs()) {
        list.addAll(LogicalComponentPkgExt.getAllLCsFromLCPkg(lcPkg));
      }
      for (LogicalArchitecture logArch : lc_p.getOwnedLogicalArchitectures()) {
        list.addAll(getAllLCsFromLogicalArchitectureLayer(logArch));
      }
      for (LogicalComponent subLC : lc_p.getSubLogicalComponents()) {
    	  list.add(subLC);
        list.addAll(getAllLCsFromLC(subLC));
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces in LogicalArchitecture
   * 
   *
   * @param logArch_p the LogicalArchitecture
   * @return list of interfaces
   */
  static public List<CapellaElement> getAllInterfacesInLogicalArchitecture(LogicalArchitecture logArch_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logArch_p) {
      list.addAll(InterfacePkgExt.getAllInterfaces(logArch_p.getOwnedInterfacePkg()));
      list.addAll(LogicalComponentExt.getAllInterfacesInLogicalComponent(logArch_p.getOwnedLogicalComponent()));
      list.addAll(LogicalComponentPkgExt.getAllInterfacesInLogicalComponentPkg(logArch_p.getOwnedLogicalComponentPkg()));
    }
    return list;
  }

  /**
   * Gets all the interfaces used by LCs in logical Architecture Gets all the interfaces in InterfacePkg (and sub pkgs) of LCs in logical Architecture
   * Gets all the interfaces used by LCs in LCPkg (and sub LC pkgs) of logical architecture Gets all the interfaces in InterfacePkg (and sub pkgs) of LCs in LCPkg (and sub LC pkgs) of logical architecture
   * 
   * 
   * @param logicalArchitecture_p the logical architecture
   * @return list of Interfaces
   */
  static public List<CapellaElement> getAllInterfacesInLogicalArchitecture(LogicalArchitecture logicalArchitecture_p, LogicalComponent currentLC_p,
      boolean usedFlag_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logicalArchitecture_p) {
      list.addAll(InterfacePkgExt.getAllInterfacesFiltered(logicalArchitecture_p.getOwnedInterfacePkg(), currentLC_p, usedFlag_p));
      list.addAll(LogicalComponentExt.getInterfacesFromSameLevelLogicalComponent(logicalArchitecture_p.getOwnedLogicalComponent(), currentLC_p, usedFlag_p));
      list.addAll(LogicalComponentPkgExt.getAllInterfacesInLogicalComponentPkg(logicalArchitecture_p.getOwnedLogicalComponentPkg(), currentLC_p, usedFlag_p));
    }
    return list;
  }

  /**
   * This method retrieves all the logical components from the model.
   *
   * @param currentElement_p
   * @return List<LogicalComponent>
   */
  public static List<LogicalComponent> getAllLogicalComponents(LogicalArchitecture currentElement_p) {
    Set<EObject> pcSet = EObjectExt.getAll(currentElement_p, LaPackage.Literals.LOGICAL_COMPONENT);
    List<LogicalComponent> lcList = new ArrayList<LogicalComponent>();
    for (EObject obj : pcSet) {
      lcList.add((LogicalComponent) obj);
    }
    return lcList;
  }

  public static List<LogicalComponent> getSameLevelComponents(LogicalArchitecture currentElement_p) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>(1);
    if (null != currentElement_p) {
      list.add(currentElement_p.getOwnedLogicalComponent());
      for (LogicalComponent alc : LogicalComponentPkgExt.getAllLCsFromLCPkg(currentElement_p.getOwnedLogicalComponentPkg())) {
        list.add(alc);
      }
    }
    return list;
  }

  /**
   * This method retrieves all the logical components from the LogicalComponentPkg.
   *
   * @param currentElement_p
   * @return List<LogicalComponent>
   */
  public static List<LogicalComponent> getAllLogicalComponents(LogicalComponentPkg currentElement_p) {
    Set<EObject> pcSet = EObjectExt.getAll(currentElement_p, LaPackage.Literals.LOGICAL_COMPONENT);
    List<LogicalComponent> lcList = new ArrayList<LogicalComponent>();
    for (EObject obj : pcSet) {
      lcList.add((LogicalComponent) obj);
    }
    return lcList;
  }

  static public List<AbstractFunction> getAllFunctions(BlockArchitecture arch_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != arch_p) {
      FunctionPkg functionPkg = arch_p.getOwnedFunctionPkg();
      if ((functionPkg != null) && (functionPkg instanceof LogicalFunctionPkg)) {
        list = getAllFunctionsFromFunctionPkg((LogicalFunctionPkg) functionPkg);
      }

    }
    return list;
  }

  static public List<AbstractFunction> getAllAbstractFunctions(BlockArchitecture arch_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != arch_p) {
      FunctionPkg functionPkg = arch_p.getOwnedFunctionPkg();
      if ((functionPkg != null) && (functionPkg instanceof LogicalFunctionPkg)) {
        list = getAllAbstractFunctionsFromFunctionPkg((LogicalFunctionPkg) functionPkg);
      }

    }
    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromFunctionPkg(LogicalFunctionPkg sysFunPkg_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != sysFunPkg_p) {
      EList<LogicalFunction> ownedFunctions = sysFunPkg_p.getOwnedLogicalFunctions();
      // owned function of FunctionPkg
      list.addAll(ownedFunctions);
      // owned function of Function
      for (AbstractFunction function : ownedFunctions) {
        list.addAll(getAllFunctionsFromFunction(function));
      }
      // owned function of (subPkg of sysFunPkg_p) SystemFunctionPkg
      for (LogicalFunctionPkg ownedFunctionPkg : sysFunPkg_p.getOwnedLogicalFunctionPkgs()) {
        list.addAll(getAllFunctionsFromFunctionPkg(ownedFunctionPkg));
      }
    }
    return list;
  }

  static public List<AbstractFunction> getAllAbstractFunctionsFromFunctionPkg(LogicalFunctionPkg sysFunPkg_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);

    if (null != sysFunPkg_p) {
      EList<LogicalFunction> ownedFunctions = sysFunPkg_p.getOwnedLogicalFunctions();
      // owned function of FunctionPkg
      list.addAll(ownedFunctions);
      // owned function of Function
      for (AbstractFunction function : ownedFunctions) {
        list.addAll(getAllAbstractFunctionsFromFunction(function));
      }
      // owned function of (subPkg of sysFunPkg_p) SystemFunctionPkg
      for (LogicalFunctionPkg ownedFunctionPkg : sysFunPkg_p.getOwnedLogicalFunctionPkgs()) {
        list.addAll(getAllAbstractFunctionsFromFunctionPkg(ownedFunctionPkg));
      }
    }

    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromFunction(AbstractFunction fun_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != fun_p) {
      EList<AbstractFunction> ownedSystemFunctions = fun_p.getOwnedFunctions();
      for (AbstractFunction abstractFunction : ownedSystemFunctions) {
        list.add(abstractFunction);
      }
      // owned function of function
      for (AbstractFunction function : ownedSystemFunctions) {
        list.addAll(getAllFunctionsFromFunction(function));
      }
    }
    return list;
  }

  static public List<AbstractFunction> getAllAbstractFunctionsFromFunction(AbstractFunction fun_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != fun_p) {
      EList<AbstractFunction> ownedSystemFunctions = fun_p.getOwnedFunctions();
      for (AbstractFunction abstractFunction : ownedSystemFunctions) {
        list.add(abstractFunction);
      }
      // owned function of function
      for (AbstractFunction function : ownedSystemFunctions) {
        list.addAll(getAllAbstractFunctionsFromFunction(function));
      }
    }
    return list;
  }
}
