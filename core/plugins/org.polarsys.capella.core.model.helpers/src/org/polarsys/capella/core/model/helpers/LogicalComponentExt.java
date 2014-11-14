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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * LogicalComponent helpers
 */
public class LogicalComponentExt {

  /**
   * Gets all the interfaces in LogicalComponent
   * @param lc_p the LogicalComponent
   * @return list of interfaces
   */
  static public List<CapellaElement> getAllInterfacesInLogicalComponent(LogicalComponent lc_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != lc_p) {
      list.addAll(InterfacePkgExt.getAllInterfacesFiltered(lc_p.getOwnedInterfacePkg(), null, false));
      for (LogicalComponent lc : lc_p.getSubLogicalComponents()) {
        list.addAll(getAllInterfacesInLogicalComponent(lc));
      }
      for (LogicalComponentPkg lcPkg : lc_p.getOwnedLogicalComponentPkgs()) {
        list.addAll(LogicalComponentPkgExt.getAllInterfacesInLogicalComponentPkg(lcPkg));
      }
      for (LogicalArchitecture logArch : lc_p.getOwnedLogicalArchitectures()) {
        list.addAll(LogicalArchitectureExt.getAllInterfacesInLogicalArchitecture(logArch));
      }
    }
    return list;
  }

  /**
   * Gets all LCDcmps
   * @param lc_p the logical component
   * @return list of logical architectures
   */
  static public List<LogicalArchitecture> getAllLogicalArchitectures(LogicalComponent lc_p) {
    List<LogicalArchitecture> list = new ArrayList<LogicalArchitecture>();
    if (null != lc_p) {
      list.addAll(lc_p.getOwnedLogicalArchitectures());
      for (LogicalComponentPkg lcPkg : lc_p.getOwnedLogicalComponentPkgs()) {
        list.addAll(LogicalComponentPkgExt.getAllLogicalArchitectures(lcPkg));
      }
      for (LogicalComponent lc : lc_p.getSubLogicalComponents()) {
        list.addAll(getAllLogicalArchitectures(lc));
      }
    }
    return list;
  }

  /**
   * This method gets all the CapabilityRealizations in Parent's hierarchy of current LC
   * @param component_p the Logical Component
   * @return List of CapabilityRealizations
   */
  static public List<CapabilityRealization> getCapabilityRealizationUseCaseFromLCParentHierarchy(LogicalComponent component_p) {
    List<CapabilityRealization> list = new ArrayList<CapabilityRealization>();
    Component parentComponent = getParentContainer(component_p);

    // Add CapabilityRealizationUseCases from parent Logical Architecture
    BlockArchitecture arch = ComponentExt.getRootBlockArchitecture(component_p);
    if (null != arch) {
      list.addAll(CapellaElementExt.getAllCapabilityRealizationInvolvedWith(arch));
    }

    if (parentComponent instanceof LogicalComponent) {
      LogicalComponent parentLC = (LogicalComponent) parentComponent;
      list.addAll(CapellaElementExt.getAllCapabilityRealizationsFromAbstractCapabilityPkg(parentLC.getOwnedAbstractCapabilityPkg()));
      list.addAll(getCapabilityRealizationUseCaseFromLCParentHierarchy(parentLC));
    } else if (parentComponent instanceof System) {
      SystemAnalysis ca = (SystemAnalysis) ((System) parentComponent).eContainer();
      if ((null != ca) && !ca.equals(arch)) {
        list.addAll(CapellaElementExt.getAllCapabilityRealizationsFromAbstractCapabilityPkg(ca.getOwnedAbstractCapabilityPkg()));
      }
    }
    return list;
  }

  /**
   * Return the LogicalComponent list involved in decomposition for the LogicalComponent given in parameter
   */
  public static List<LogicalComponent> getDecompositionLogicalComponentInvolved(LogicalComponent lc_p) {
    List<LogicalComponent> decompLcInvolved = new ArrayList<LogicalComponent>();
    for (Partition partition : lc_p.getOwnedPartitions()) {
      AbstractType currentLc = partition.getAbstractType();
      if ((currentLc instanceof LogicalComponent) && !decompLcInvolved.contains(currentLc)) {
        decompLcInvolved.add((LogicalComponent) currentLc);
      }
    }
    return decompLcInvolved;
  }

  /**
   * This method retrieves the implementor physical component.
   * @param component_p the component whose implementor physical component will be retrieved
   * @return the implementor physical component
   */
  public static PhysicalComponent getImplementor(LogicalComponent component_p, PhysicalArchitecture physicalArchitecture_p) {
    PhysicalComponent implementorPC = null;
    for (PhysicalComponent pc : getImplementors(component_p)) {
      if (EcoreUtil2.isContainedBy(pc, physicalArchitecture_p)) {
        implementorPC = pc;
      }
    }
    return implementorPC;
  }

  /**
   * This method retrieves all physical components which implement the specified logical component.
   * @param component_p The source logical component.
   * @return The physical components list.
   */
  public static List<PhysicalComponent> getImplementors(LogicalComponent component_p) {
    // The result list.
    List<PhysicalComponent> implementorsList = new ArrayList<PhysicalComponent>();

    EList<?> eImplementors = component_p.getAllocatingComponents();
    for (Object object : eImplementors) {
      PhysicalComponent implementorPC = (PhysicalComponent) object;
      if (null != implementorPC) {
        implementorsList.add(implementorPC);
      }
    }
    return implementorsList;
  }

  /**
   * This method gets all the interfaces in Parent's hierarchy of current LC
   * @param component_p the Logical Component
   * @return List of interfaces
   */
  static public List<Interface> getInterfacesFromLCParentHierarchy(LogicalComponent component_p) {
    List<Interface> list = new ArrayList<Interface>();
    // Add interfaces from parent Logical Architecture
    BlockArchitecture arch = ComponentExt.getRootBlockArchitecture(component_p);
    if (null != arch) {
      list.addAll(InterfacePkgExt.getAllInterfaces(arch.getOwnedInterfacePkg()));
      // get recursively all the interfaces from the parent hierarchy
      list.addAll(InterfacePkgExt.getOwnedInterfacesFromBlockArchitectureParent(arch));
    }

    Component parentComponent = getParentContainer(component_p);

    if ((parentComponent != null) && (parentComponent instanceof LogicalComponent)) {
      LogicalComponent parentLC = (LogicalComponent) parentComponent;
      list.addAll(InterfacePkgExt.getAllInterfaces(parentLC.getOwnedInterfacePkg()));
      list.addAll(getInterfacesFromLCParentHierarchy(parentLC));
    } else if (parentComponent instanceof System) {
      SystemAnalysis ca = (SystemAnalysis) ((System) parentComponent).eContainer();
      if ((null != ca) && !ca.equals(arch)) {
        list.addAll(InterfacePkgExt.getAllInterfaces(ca.getOwnedInterfacePkg()));
      }
    }

    return list;
  }

  /**
   * Gets all the interfaces used/implemented by LCs in Logical Component Pkg and its sub LCPkgs of the logicalComponent_p. Also it gets all the interfaces in
   * the interfacePkgs and subInterfacePkgs of the LCs.
   * @param logicalComponent_p the Logical Component
   * @param currentLC_p the current Logical Component
   * @param usedFlag_p flag for checking used / implemented interface
   * @return list of interfaces.
   */
  static public List<CapellaElement> getInterfacesFromLogicalComponent(LogicalComponent logicalComponent_p, LogicalComponent currentLC_p, boolean usedFlag_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logicalComponent_p) {
      int lcLevel = LogicalComponentExt.getRecursiveLogicalComponentLevel(logicalComponent_p);
      int currentLCLevel = LogicalComponentExt.getRecursiveLogicalComponentLevel(currentLC_p);
      if (lcLevel == currentLCLevel) { // check for same level
        if (!logicalComponent_p.equals(currentLC_p)) {
          if (usedFlag_p) {
            list.addAll(ComponentExt.getUsedInterfacesFiltered(logicalComponent_p, ((currentLC_p != null) ? currentLC_p : logicalComponent_p)));
          } else {
            list.addAll(ComponentExt.getImplementedInterfacesFiltered(logicalComponent_p, ((currentLC_p != null) ? currentLC_p : logicalComponent_p)));
          }
        }
        list.addAll(InterfacePkgExt.getAllInterfacesFiltered(logicalComponent_p.getOwnedInterfacePkg(), currentLC_p, usedFlag_p));
      }
      for (LogicalComponentPkg logicalComponentPkg : logicalComponent_p.getOwnedLogicalComponentPkgs()) {
        list.addAll(LogicalComponentPkgExt.getAllInterfacesInLogicalComponentPkg(logicalComponentPkg, currentLC_p, usedFlag_p));
      }
      for (LogicalComponent lc : logicalComponent_p.getSubLogicalComponents()) {
        list.addAll(getInterfacesFromLogicalComponent(lc, currentLC_p, usedFlag_p));
      }
      for (LogicalArchitecture logArch : logicalComponent_p.getOwnedLogicalArchitectures()) {
        list.addAll(LogicalArchitectureExt.getAllInterfacesInLogicalArchitecture(logArch, currentLC_p, usedFlag_p));
      }
    }
    return list;
  }

  /**
   * Gets the interfaces used/implemented by the same level LCs
   * @param logicalComponent_p
   * @param currentLC_p
   * @return
   */
  static public List<CapellaElement> getInterfacesFromSameLevelLogicalComponent(LogicalComponent logicalComponent_p, LogicalComponent currentLC_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logicalComponent_p) {
      int lcLevel = getRecursiveLogicalComponentLevel(logicalComponent_p);
      int currentLCLevel = getRecursiveLogicalComponentLevel(currentLC_p);
      if (lcLevel == currentLCLevel) { // check for same level
        if (!logicalComponent_p.equals(currentLC_p)) {
          list.addAll(logicalComponent_p.getUsedInterfaces());
          list.addAll(logicalComponent_p.getImplementedInterfaces());

          // Notes in the same layer, for instance currentLC_p shall
          // not propose any Interface, even if it is badly
          // used/implemented by logicalComponent_p.
          List<Interface> innerInterfaces = InterfacePkgExt.getAllInterfacesFiltered(logicalComponent_p.getOwnedInterfacePkg(), null, true);
          list.removeAll(innerInterfaces);
        }
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces used/implemented by the same level Logical Component. Also it gets all the interfaces in the interfacePkgs and subInterfacePkgs of
   * the LCs.
   * @param logicalComponent_p the Logical Component
   * @param currentLC_p the current Logical Component
   * @param usedFlag_p flag for checking used / implemented interface
   * @return list of interfaces.
   */
  static public List<CapellaElement> getInterfacesFromSameLevelLogicalComponent(LogicalComponent logicalComponent_p, LogicalComponent currentLC_p,
      boolean usedFlag_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logicalComponent_p) {
      int lcLevel = getRecursiveLogicalComponentLevel(logicalComponent_p);
      int currentLCLevel = getRecursiveLogicalComponentLevel(currentLC_p);
      if (lcLevel == currentLCLevel) { // check for same level
        if (!logicalComponent_p.equals(currentLC_p)) {
          if (usedFlag_p) {
            list.addAll(ComponentExt.getUsedInterfacesFiltered(logicalComponent_p, ((currentLC_p != null) ? currentLC_p : logicalComponent_p)));
          } else {
            list.addAll(ComponentExt.getImplementedInterfacesFiltered(logicalComponent_p, ((currentLC_p != null) ? currentLC_p : logicalComponent_p)));
          }
        }
        list.addAll(InterfacePkgExt.getAllInterfacesFiltered(logicalComponent_p.getOwnedInterfacePkg(), currentLC_p, usedFlag_p));
      }
    }
    return list;
  }

  /**
   * Retrieves the Logical component container of Scenario given in parameter. (For Scenario under the LogicalArchitecture, return Root LogicalComponent
   * @return
   */
  public static LogicalComponent getLogicalComponentContainerFromScenario(Scenario scenario_p) {
    LogicalComponent containerLc = null;

    containerLc = (LogicalComponent) EcoreUtil2.getFirstContainer(scenario_p, LaPackage.Literals.LOGICAL_COMPONENT);
    if (containerLc == null) {
      // Case : Scenario contained by LogicalArchitecture (not under a Logical Component)
      // Retrieve the Root Logical Component (from System)
      LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(scenario_p, LaPackage.Literals.LOGICAL_ARCHITECTURE);
      containerLc = SystemEngineeringExt.getRootLogicalComponent(la);
    }

    return containerLc;
  }

  private static LogicalComponent getParentComponent(LogicalComponent component_p) {
    LogicalComponent parentComponent = null;
    for (Partition partition : component_p.getRepresentingPartitions()) {
      Classifier ownerClassifier = (Classifier) partition.eContainer();
      if (ownerClassifier instanceof LogicalComponent) {
        parentComponent = (LogicalComponent) ownerClassifier;
      }
    }
    return parentComponent;
  }

  /**
   * @param component_p the current Logical Component
   * @return returns the Component containing the given Logical Component (can be either a Logical Component, or the System)
   */
  public static Component getParentContainer(LogicalComponent component_p) {
    return getRecursiveParentContainer(component_p);
  }

  /**
   * This method calculates a component level.<br/>
   * It will go up through the component's hierarchy by following the "Partition" link until the Root Component is found.
   */
  private static int getRecursiveLogicalComponentLevel(LogicalComponent component_p) {
    int level = 0;
    LogicalComponent parentComponent = null;
    LogicalComponent currentComponent = component_p;

    while (getParentComponent(currentComponent) != null) {
      parentComponent = getParentComponent(currentComponent);
      currentComponent = parentComponent;
      level++;
    }
    return level;
  }

  /**
   * @param component_p
   * @return
   */
  private static Component getRecursiveParentContainer(LogicalArchitecture component_p) {
    Component cpnt = null;
    EObject container = component_p.eContainer();

    if (container instanceof SystemEngineering) {
      cpnt = SystemEngineeringExt.getSystem((SystemEngineering) container);
    } else if (container instanceof LogicalComponent) {
      // added here if LogicalComponent is decomposed into LCDcmps, which is a LogicalArchitecture
      cpnt = (LogicalComponent) container;
    } else if (container instanceof LogicalArchitecturePkg) {
      cpnt = getRecursiveParentContainer((LogicalArchitecturePkg) container);
    }

    return cpnt;
  }

  /**
   * @param component_p
   * @return
   */
  private static Component getRecursiveParentContainer(LogicalArchitecturePkg component_p) {
    Component cpnt = null;
    EObject container = component_p.eContainer();

    if (container instanceof SystemEngineering) {
      cpnt = SystemEngineeringExt.getSystem((SystemEngineering) container);
    }

    return cpnt;
  }

  /**
   * @param component_p
   * @return
   */
  private static Component getRecursiveParentContainer(LogicalComponent component_p) {
    Component cpnt = null;
    EObject container = component_p.eContainer();

    if (container instanceof LogicalArchitecture) {
      cpnt = getRecursiveParentContainer((LogicalArchitecture) container);
    } else if (container instanceof LogicalComponentPkg) {
      cpnt = getRecursiveParentContainer((LogicalComponentPkg) container);
    } else if (container instanceof LogicalComponent) {
      cpnt = (LogicalComponent) container;
    }

    return cpnt;
  }

  /**
   * @param componentPkg_p
   * @return
   */
  private static Component getRecursiveParentContainer(LogicalComponentPkg componentPkg_p) {
    Component cpnt = null;
    EObject container = componentPkg_p.eContainer();

    if (container instanceof LogicalArchitecture) {
      cpnt = getRecursiveParentContainer((LogicalArchitecture) container);
    } else if (container instanceof LogicalComponentPkg) {
      cpnt = getRecursiveParentContainer((LogicalComponentPkg) container);
    } else if (container instanceof LogicalComponent) {
      cpnt = (LogicalComponent) container;
    }

    return cpnt;
  }

  /**
   * @return RootLogicalComponent from the LogicalComponent given in parameter
   */
  public static LogicalComponent getRootLogicalComponentFromCurrentLC(EObject elt_p) {
    if ((elt_p == null) || ComponentExt.isComponentRoot(elt_p)) {
      return (LogicalComponent) elt_p;
    }

    return getRootLogicalComponentFromCurrentLC(elt_p.eContainer());
  }

  public static List<LogicalComponent> getSameLevelComponents(LogicalComponent currentElement_p) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>(1);
    if (null != currentElement_p) {
      for (LogicalComponent alc : currentElement_p.getSubLogicalComponents()) {
        list.add(alc);
      }
      for (LogicalComponentPkg lcPkg : currentElement_p.getOwnedLogicalComponentPkgs()) {
        for (LogicalComponent alc : LogicalComponentPkgExt.getAllLCsFromLCPkg(lcPkg)) {
          list.add(alc);
        }
      }
    }
    return list;
  }

  /**
   * This method retrieves recursively the children of a given logical component.
   * @param component_p the component whose children will be retrieved
   * @return List<LogicalComponent>
   */
  public static List<LogicalComponent> getAllSubComponents(LogicalComponent component_p) {
    List<LogicalComponent> result = new ArrayList<LogicalComponent>();

    for (LogicalComponent lc : component_p.getSubLogicalComponents()) {
      result.addAll(getAllSubComponents(lc));
    }
    result.add(component_p);

    return result;
  }
}
