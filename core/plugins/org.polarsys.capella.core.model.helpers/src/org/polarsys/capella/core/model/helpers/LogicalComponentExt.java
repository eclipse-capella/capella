/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * LogicalComponent helpers
 */
public class LogicalComponentExt {

  /**
   * Gets all the interfaces in LogicalComponent
   * @param logicalComponent1 the LogicalComponent
   * @return list of interfaces
   */
  static public List<CapellaElement> getAllInterfacesInLogicalComponent(LogicalComponent logicalComponent1) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logicalComponent1) {
      list.addAll(InterfacePkgExt.getAllInterfacesFiltered(logicalComponent1.getOwnedInterfacePkg(), null, false));
      for (LogicalComponent lc : logicalComponent1.getSubLogicalComponents()) {
        list.addAll(getAllInterfacesInLogicalComponent(lc));
      }
      for (LogicalComponentPkg lcPkg : logicalComponent1.getOwnedLogicalComponentPkgs()) {
        list.addAll(LogicalComponentPkgExt.getAllInterfacesInLogicalComponentPkg(lcPkg));
      }
      for (LogicalArchitecture logArch : logicalComponent1.getOwnedLogicalArchitectures()) {
        list.addAll(BlockArchitectureExt.getAllInterfaces(logArch));
      }
    }
    return list;
  }

  /**
   * Gets all LCDcmps
   * @param logicalComponent1 the logical component
   * @return list of logical architectures
   */
  static public List<LogicalArchitecture> getAllLogicalArchitectures(LogicalComponent logicalComponent1) {
    List<LogicalArchitecture> list = new ArrayList<LogicalArchitecture>();
    if (null != logicalComponent1) {
      list.addAll(logicalComponent1.getOwnedLogicalArchitectures());
      for (LogicalComponentPkg lcPkg : logicalComponent1.getOwnedLogicalComponentPkgs()) {
        list.addAll(LogicalComponentPkgExt.getAllLogicalArchitectures(lcPkg));
      }
      for (LogicalComponent lc : logicalComponent1.getSubLogicalComponents()) {
        list.addAll(getAllLogicalArchitectures(lc));
      }
    }
    return list;
  }

  /**
   * This method gets all the CapabilityRealizations in Parent's hierarchy of current LC
   * @param component the Logical Component
   * @return List of CapabilityRealizations
   */
  static public List<CapabilityRealization> getCapabilityRealizationUseCaseFromLCParentHierarchy(LogicalComponent component) {
    List<CapabilityRealization> list = new ArrayList<CapabilityRealization>();
    Component parentComponent = getParentContainer(component);

    // Add CapabilityRealizationUseCases from parent Logical Architecture
    BlockArchitecture arch = ComponentExt.getRootBlockArchitecture(component);
    if (null != arch) {
      list.addAll(CapellaElementExt.getAllCapabilityRealizationInvolvedWith(arch));
    }

    if (parentComponent instanceof LogicalComponent) {
      LogicalComponent parentLC = (LogicalComponent) parentComponent;
      list.addAll(CapellaElementExt.getAllCapabilityRealizationsFromAbstractCapabilityPkg(parentLC.getOwnedAbstractCapabilityPkg()));
      list.addAll(getCapabilityRealizationUseCaseFromLCParentHierarchy(parentLC));
    }
    return list;
  }

  /**
   * Return the LogicalComponent list involved in decomposition for the LogicalComponent given in parameter
   */
  public static List<LogicalComponent> getDecompositionLogicalComponentInvolved(LogicalComponent lc) {
    List<LogicalComponent> decompLcInvolved = new ArrayList<LogicalComponent>();
    for (Part part : lc.getContainedParts()) {
      AbstractType currentLc = part.getAbstractType();
      if ((currentLc instanceof LogicalComponent) && !decompLcInvolved.contains(currentLc)) {
        decompLcInvolved.add((LogicalComponent) currentLc);
      }
    }
    return decompLcInvolved;
  }

  /**
   * This method retrieves the implementor physical component.
   * @param component the component whose implementor physical component will be retrieved
   * @return the implementor physical component
   */
  public static PhysicalComponent getImplementor(LogicalComponent component, PhysicalArchitecture physicalArchitecture) {
    PhysicalComponent implementorPC = null;
    for (PhysicalComponent pc : getImplementors(component)) {
      if (EcoreUtil2.isContainedBy(pc, physicalArchitecture)) {
        implementorPC = pc;
      }
    }
    return implementorPC;
  }

  /**
   * This method retrieves all physical components which implement the specified logical component.
   * @param component The source logical component.
   * @return The physical components list.
   */
  public static List<PhysicalComponent> getImplementors(LogicalComponent component) {
    // The result list.
    List<PhysicalComponent> implementorsList = new ArrayList<PhysicalComponent>();

    EList<?> eImplementors = component.getRealizingComponents();
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
   * @param component the Logical Component
   * @return List of interfaces
   */
  static public List<Interface> getInterfacesFromLCParentHierarchy(LogicalComponent component) {
    List<Interface> list = new ArrayList<Interface>();
    // Add interfaces from parent Logical Architecture
    BlockArchitecture arch = ComponentExt.getRootBlockArchitecture(component);
    if (null != arch) {
      list.addAll(InterfacePkgExt.getAllInterfaces(arch.getOwnedInterfacePkg()));
      // get recursively all the interfaces from the parent hierarchy
      list.addAll(InterfacePkgExt.getOwnedInterfacesFromBlockArchitectureParent(arch));
    }

    Component parentComponent = getParentContainer(component);

    if ((parentComponent != null) && (parentComponent instanceof LogicalComponent)) {
      LogicalComponent parentLC = (LogicalComponent) parentComponent;
      list.addAll(InterfacePkgExt.getAllInterfaces(parentLC.getOwnedInterfacePkg()));
      list.addAll(getInterfacesFromLCParentHierarchy(parentLC));
    }

    return list;
  }

  /**
   * Gets all the interfaces used/implemented by LCs in Logical Component Pkg and its sub LCPkgs of the logicalComponent. Also it gets all the interfaces in
   * the interfacePkgs and subInterfacePkgs of the LCs.
   * @param logicalComponent the Logical Component
   * @param currentLC the current Logical Component
   * @param usedFlag flag for checking used / implemented interface
   * @return list of interfaces.
   */
  static public List<CapellaElement> getInterfacesFromLogicalComponent(LogicalComponent logicalComponent, LogicalComponent currentLC, boolean usedFlag) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logicalComponent) {
      int lcLevel = LogicalComponentExt.getRecursiveLogicalComponentLevel(logicalComponent);
      int currentLCLevel = LogicalComponentExt.getRecursiveLogicalComponentLevel(currentLC);
      if (lcLevel == currentLCLevel) { // check for same level
        if (!logicalComponent.equals(currentLC)) {
          if (usedFlag) {
            list.addAll(ComponentExt.getUsedInterfacesFiltered(logicalComponent, ((currentLC != null) ? currentLC : logicalComponent)));
          } else {
            list.addAll(ComponentExt.getImplementedInterfacesFiltered(logicalComponent, ((currentLC != null) ? currentLC : logicalComponent)));
          }
        }
        list.addAll(InterfacePkgExt.getAllInterfacesFiltered(logicalComponent.getOwnedInterfacePkg(), currentLC, usedFlag));
      }
      for (LogicalComponentPkg logicalComponentPkg : logicalComponent.getOwnedLogicalComponentPkgs()) {
        list.addAll(LogicalComponentPkgExt.getAllInterfacesInLogicalComponentPkg(logicalComponentPkg, currentLC, usedFlag));
      }
      for (LogicalComponent lc : logicalComponent.getSubLogicalComponents()) {
        list.addAll(getInterfacesFromLogicalComponent(lc, currentLC, usedFlag));
      }
      for (LogicalArchitecture logArch : logicalComponent.getOwnedLogicalArchitectures()) {
        list.addAll(BlockArchitectureExt.getAllInterfaces(logArch));
      }
    }
    return list;
  }

  /**
   * Gets the interfaces used/implemented by the same level LCs
   * @param logicalComponent
   * @param currentLC
   * @return
   */
  static public List<CapellaElement> getInterfacesFromSameLevelLogicalComponent(LogicalComponent logicalComponent, LogicalComponent currentLC) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logicalComponent) {
      int lcLevel = getRecursiveLogicalComponentLevel(logicalComponent);
      int currentLCLevel = getRecursiveLogicalComponentLevel(currentLC);
      if (lcLevel == currentLCLevel) { // check for same level
        if (!logicalComponent.equals(currentLC)) {
          list.addAll(logicalComponent.getUsedInterfaces());
          list.addAll(logicalComponent.getImplementedInterfaces());

          // Notes in the same layer, for instance currentLC shall
          // not propose any Interface, even if it is badly
          // used/implemented by logicalComponent.
          List<Interface> innerInterfaces = InterfacePkgExt.getAllInterfacesFiltered(logicalComponent.getOwnedInterfacePkg(), null, true);
          list.removeAll(innerInterfaces);
        }
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces used/implemented by the same level Logical Component. Also it gets all the interfaces in the interfacePkgs and subInterfacePkgs of
   * the LCs.
   * @param logicalComponent the Logical Component
   * @param currentLC the current Logical Component
   * @param usedFlag flag for checking used / implemented interface
   * @return list of interfaces.
   */
  static public List<CapellaElement> getInterfacesFromSameLevelLogicalComponent(LogicalComponent logicalComponent, LogicalComponent currentLC,
      boolean usedFlag) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != logicalComponent) {
      int lcLevel = getRecursiveLogicalComponentLevel(logicalComponent);
      int currentLCLevel = getRecursiveLogicalComponentLevel(currentLC);
      if (lcLevel == currentLCLevel) { // check for same level
        if (!logicalComponent.equals(currentLC)) {
          if (usedFlag) {
            list.addAll(ComponentExt.getUsedInterfacesFiltered(logicalComponent, ((currentLC != null) ? currentLC : logicalComponent)));
          } else {
            list.addAll(ComponentExt.getImplementedInterfacesFiltered(logicalComponent, ((currentLC != null) ? currentLC : logicalComponent)));
          }
        }
        list.addAll(InterfacePkgExt.getAllInterfacesFiltered(logicalComponent.getOwnedInterfacePkg(), currentLC, usedFlag));
      }
    }
    return list;
  }

  /**
   * Retrieves the Logical component container of Scenario given in parameter. (For Scenario under the LogicalArchitecture, return Root LogicalComponent
   * @return
   */
  public static LogicalComponent getLogicalComponentContainerFromScenario(Scenario scenario) {
    LogicalComponent containerLc = null;

    containerLc = (LogicalComponent) EcoreUtil2.getFirstContainer(scenario, LaPackage.Literals.LOGICAL_COMPONENT);
    if (containerLc == null) {
      // Case : Scenario contained by LogicalArchitecture (not under a Logical Component)
      // Retrieve the Root Logical Component (from System)
      LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(scenario, LaPackage.Literals.LOGICAL_ARCHITECTURE);
      containerLc = (LogicalComponent) la.getSystem();
    }

    return containerLc;
  }

  private static LogicalComponent getParentComponent(LogicalComponent component) {
    LogicalComponent parentComponent = null;
    for (Part part : component.getRepresentingParts()) {
      EObject ownerClassifier = part.eContainer();
      if (ownerClassifier instanceof LogicalComponent) {
        parentComponent = (LogicalComponent) ownerClassifier;
      }
    }
    return parentComponent;
  }

  /**
   * @param component the current Logical Component
   * @return returns the Component containing the given Logical Component (can be either a Logical Component, or the System)
   */
  public static Component getParentContainer(LogicalComponent component) {
    return getRecursiveParentContainer(component);
  }

  /**
   * This method calculates a component level.<br/>
   * It will go up through the component's hierarchy by following the "Partition" link until the Root Component is found.
   */
  private static int getRecursiveLogicalComponentLevel(LogicalComponent component) {
    int level = 0;
    LogicalComponent parentComponent = null;
    LogicalComponent currentComponent = component;

    while (getParentComponent(currentComponent) != null) {
      parentComponent = getParentComponent(currentComponent);
      currentComponent = parentComponent;
      level++;
    }
    return level;
  }

  /**
   * @param component
   * @return
   */
  private static Component getRecursiveParentContainer(LogicalComponent component) {
    Component cpnt = null;
    EObject container = component.eContainer();
    if (container instanceof LogicalComponentPkg) {
      cpnt = getRecursiveParentContainer((LogicalComponentPkg) container);
    } else if (container instanceof LogicalComponent) {
      cpnt = (LogicalComponent) container;
    }

    return cpnt;
  }

  /**
   * @param componentPkg
   * @return
   */
  private static Component getRecursiveParentContainer(LogicalComponentPkg componentPkg) {
    Component cpnt = null;
    EObject container = componentPkg.eContainer();
    if (container instanceof LogicalComponentPkg) {
      cpnt = getRecursiveParentContainer((LogicalComponentPkg) container);
    } else if (container instanceof LogicalComponent) {
      cpnt = (LogicalComponent) container;
    }
    return cpnt;
  }

  /**
   * @return RootLogicalComponent from the LogicalComponent given in parameter
   */
  public static LogicalComponent getRootLogicalComponentFromCurrentLC(EObject elt) {
    if ((elt == null) || ComponentExt.isComponentRoot(elt)) {
      return (LogicalComponent) elt;
    }

    return getRootLogicalComponentFromCurrentLC(elt.eContainer());
  }

  public static List<LogicalComponent> getSameLevelComponents(LogicalComponent currentElement) {
    List<LogicalComponent> list = new ArrayList<LogicalComponent>(1);
    if (null != currentElement) {
      for (LogicalComponent alc : currentElement.getSubLogicalComponents()) {
        list.add(alc);
      }
      for (LogicalComponentPkg lcPkg : currentElement.getOwnedLogicalComponentPkgs()) {
        for (LogicalComponent alc : LogicalComponentPkgExt.getAllLCsFromLCPkg(lcPkg)) {
          list.add(alc);
        }
      }
    }
    return list;
  }

  /**
   * This method retrieves recursively the children of a given logical component.
   * @param component the component whose children will be retrieved
   * @return List<LogicalComponent>
   */
  public static List<LogicalComponent> getAllSubComponents(LogicalComponent component) {
    List<LogicalComponent> result = new ArrayList<LogicalComponent>();

    for (LogicalComponent lc : component.getSubLogicalComponents()) {
      result.addAll(getAllSubComponents(lc));
    }
    result.add(component);

    return result;
  }
}
