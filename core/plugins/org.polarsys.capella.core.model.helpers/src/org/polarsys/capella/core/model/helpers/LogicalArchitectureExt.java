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
   * @param logArch
   *          the LogicalArchitecture
   * @return list of LogicalArchitecture
   */
  static public List<LogicalArchitecture> getAllLogicalArchitectures(LogicalArchitecture logArch) {
    List<LogicalArchitecture> list = new ArrayList<LogicalArchitecture>();
    if (null != logArch) {
      list.addAll(LogicalComponentExt.getAllLogicalArchitectures(logArch.getOwnedLogicalComponent()));
    }
    return list;
  }

  /**
   * Gets all LogicalComponents from LogicalArchitecture
   * 
   * 
   * @param logArch
   *          the LogicalArchitecture
   * @return list of LogicalComponents
   */
  static public List<LogicalComponent> getAllLCsFromLogicalArchitectureLayer(LogicalArchitecture logArch) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>();
    if (null != logArch) {
      LogicalComponent ownedLogicalComponent = logArch.getOwnedLogicalComponent();
      if (null != ownedLogicalComponent) {
        list.add(ownedLogicalComponent);
      }

      list.addAll(getAllLCsFromLC(ownedLogicalComponent));
      list.addAll(LogicalComponentPkgExt.getAllLCsFromLCPkg(logArch.getOwnedLogicalComponentPkg()));
    }
    return list;
  }

  /**
   * Gets all LogicalComponents from a LogicalComponent
   * 
   * 
   * @param lc
   *          the LogicalComponent
   * @return list of LogicalComponent
   */
  static public List<LogicalComponent> getAllLCsFromLC(LogicalComponent lc) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>();
    if (null != lc) {
      // Assumed that the lc is not decomposed (single level and multi level)
      if ((lc.getSubLogicalComponents().size() == 0) && (lc.getOwnedLogicalArchitectures().size() == 0)) {
        list.add(lc);
      }
      for (LogicalComponentPkg lcPkg : lc.getOwnedLogicalComponentPkgs()) {
        list.addAll(LogicalComponentPkgExt.getAllLCsFromLCPkg(lcPkg));
      }
      for (LogicalArchitecture logArch : lc.getOwnedLogicalArchitectures()) {
        list.addAll(getAllLCsFromLogicalArchitectureLayer(logArch));
      }
      for (LogicalComponent subLC : lc.getSubLogicalComponents()) {
        if (subLC != lc) {
          list.add(lc);
          list.addAll(getAllLCsFromLC(subLC));
        }
      }
    }
    return list;
  }

  /**
   * Gets all LogicalComponents from a LogicalComponent
   * 
   * 
   * @param lc
   *          the LogicalComponent
   * @return list of LogicalComponent
   */
  static public List<LogicalComponent> getAllLCFromLC(LogicalComponent lc) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>();
    if (null != lc) {
      // Assumed that the lc is not decomposed (single level and multi level)
      if ((lc.getSubLogicalComponents().size() == 0) && (lc.getOwnedLogicalArchitectures().size() == 0)) {
        list.add(lc);
      }
      for (LogicalComponentPkg lcPkg : lc.getOwnedLogicalComponentPkgs()) {
        list.addAll(LogicalComponentPkgExt.getAllLCsFromLCPkg(lcPkg));
      }
      for (LogicalArchitecture logArch : lc.getOwnedLogicalArchitectures()) {
        list.addAll(getAllLCsFromLogicalArchitectureLayer(logArch));
      }
      for (LogicalComponent subLC : lc.getSubLogicalComponents()) {
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
   * @param logArch
   *          the LogicalArchitecture
   * @return list of interfaces
   */
  static public List<CapellaElement> getAllInterfacesInLogicalArchitecture(LogicalArchitecture logArch) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logArch) {
      list.addAll(InterfacePkgExt.getAllInterfaces(logArch.getOwnedInterfacePkg()));
      list.addAll(LogicalComponentExt.getAllInterfacesInLogicalComponent(logArch.getOwnedLogicalComponent()));
      list.addAll(LogicalComponentPkgExt.getAllInterfacesInLogicalComponentPkg(logArch.getOwnedLogicalComponentPkg()));
    }
    return list;
  }

  /**
   * Gets all the interfaces used by LCs in logical Architecture Gets all the interfaces in InterfacePkg (and sub pkgs)
   * of LCs in logical Architecture Gets all the interfaces used by LCs in LCPkg (and sub LC pkgs) of logical
   * architecture Gets all the interfaces in InterfacePkg (and sub pkgs) of LCs in LCPkg (and sub LC pkgs) of logical
   * architecture
   * 
   * 
   * @param logicalArchitecture
   *          the logical architecture
   * @return list of Interfaces
   */
  static public List<CapellaElement> getAllInterfacesInLogicalArchitecture(LogicalArchitecture logicalArchitecture,
      LogicalComponent currentLC, boolean usedFlag) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logicalArchitecture) {
      list.addAll(InterfacePkgExt.getAllInterfacesFiltered(logicalArchitecture.getOwnedInterfacePkg(), currentLC,
          usedFlag));
      list.addAll(LogicalComponentExt.getInterfacesFromSameLevelLogicalComponent(
          logicalArchitecture.getOwnedLogicalComponent(), currentLC, usedFlag));
      list.addAll(LogicalComponentPkgExt.getAllInterfacesInLogicalComponentPkg(
          logicalArchitecture.getOwnedLogicalComponentPkg(), currentLC, usedFlag));
    }
    return list;
  }

  /**
   * This method retrieves all the logical components from the model.
   *
   * @param currentElement
   * @return List<LogicalComponent>
   */
  public static List<LogicalComponent> getAllLogicalComponents(LogicalArchitecture currentElement) {
    Set<EObject> pcSet = EObjectExt.getAll(currentElement, LaPackage.Literals.LOGICAL_COMPONENT);
    List<LogicalComponent> lcList = new ArrayList<LogicalComponent>();
    for (EObject obj : pcSet) {
      lcList.add((LogicalComponent) obj);
    }
    return lcList;
  }

  public static List<LogicalComponent> getSameLevelComponents(LogicalArchitecture currentElement) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>(1);
    if (null != currentElement) {
      list.add(currentElement.getOwnedLogicalComponent());
      for (LogicalComponent alc : LogicalComponentPkgExt.getAllLCsFromLCPkg(currentElement
          .getOwnedLogicalComponentPkg())) {
        list.add(alc);
      }
    }
    return list;
  }

  /**
   * This method retrieves all the logical components from the LogicalComponentPkg.
   *
   * @param currentElement
   * @return List<LogicalComponent>
   */
  public static List<LogicalComponent> getAllLogicalComponents(LogicalComponentPkg currentElement) {
    Set<EObject> pcSet = EObjectExt.getAll(currentElement, LaPackage.Literals.LOGICAL_COMPONENT);
    List<LogicalComponent> lcList = new ArrayList<LogicalComponent>();
    for (EObject obj : pcSet) {
      lcList.add((LogicalComponent) obj);
    }
    return lcList;
  }

  static public List<AbstractFunction> getAllFunctions(BlockArchitecture arch) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != arch) {
      FunctionPkg functionPkg = arch.getOwnedFunctionPkg();
      if ((functionPkg != null) && (functionPkg instanceof LogicalFunctionPkg)) {
        list = getAllFunctionsFromFunctionPkg((LogicalFunctionPkg) functionPkg);
      }

    }
    return list;
  }

  static public List<AbstractFunction> getAllAbstractFunctions(BlockArchitecture arch) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != arch) {
      FunctionPkg functionPkg = arch.getOwnedFunctionPkg();
      if ((functionPkg != null) && (functionPkg instanceof LogicalFunctionPkg)) {
        list = getAllAbstractFunctionsFromFunctionPkg((LogicalFunctionPkg) functionPkg);
      }

    }
    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromFunctionPkg(LogicalFunctionPkg sysFunPkg) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != sysFunPkg) {
      EList<LogicalFunction> ownedFunctions = sysFunPkg.getOwnedLogicalFunctions();
      // owned function of FunctionPkg
      list.addAll(ownedFunctions);
      // owned function of Function
      for (AbstractFunction function : ownedFunctions) {
        list.addAll(getAllFunctionsFromFunction(function));
      }
      // owned function of (subPkg of sysFunPkg_p) SystemFunctionPkg
      for (LogicalFunctionPkg ownedFunctionPkg : sysFunPkg.getOwnedLogicalFunctionPkgs()) {
        list.addAll(getAllFunctionsFromFunctionPkg(ownedFunctionPkg));
      }
    }
    return list;
  }

  static public List<AbstractFunction> getAllAbstractFunctionsFromFunctionPkg(LogicalFunctionPkg sysFunPkg) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);

    if (null != sysFunPkg) {
      EList<LogicalFunction> ownedFunctions = sysFunPkg.getOwnedLogicalFunctions();
      // owned function of FunctionPkg
      list.addAll(ownedFunctions);
      // owned function of Function
      for (AbstractFunction function : ownedFunctions) {
        list.addAll(getAllAbstractFunctionsFromFunction(function));
      }
      // owned function of (subPkg of sysFunPkg_p) SystemFunctionPkg
      for (LogicalFunctionPkg ownedFunctionPkg : sysFunPkg.getOwnedLogicalFunctionPkgs()) {
        list.addAll(getAllAbstractFunctionsFromFunctionPkg(ownedFunctionPkg));
      }
    }

    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromFunction(AbstractFunction fun) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != fun) {
      EList<AbstractFunction> ownedSystemFunctions = fun.getOwnedFunctions();
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

  static public List<AbstractFunction> getAllAbstractFunctionsFromFunction(AbstractFunction fun) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != fun) {
      EList<AbstractFunction> ownedSystemFunctions = fun.getOwnedFunctions();
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
