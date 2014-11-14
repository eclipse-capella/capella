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
package org.polarsys.capella.core.business.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.LogicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentPkgExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class LogicalComponent_ImplementedInterfaces implements IBusinessQuery, RefactoredBusinessQuery {

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    return RefactorDebugger.callAndTestQuery("GetAvailable_LogicalComponent_ImplementedInterfaces__Lib", element_p, getOldAvailableElements(element_p),//$NON-NLS-1$
        getEClass(), getClass());
  }

  /**
   * <p>
   * Gets all the interfaces implemented by...
   * </p>
   * <p>
   * The owner logical component of the current one.(Refer MQRY_LogicalComponent_UsedInterfaces_11).
   * </p>
   * <p>
   * All the interfaces contained by the interface package (and sub packages) of the current logical component's parent (Refer
   * MQRY_LogicalComponent_UsedInterfaces_12).
   * </p>
   * <p>
   * All the interfaces contained by the interface package(sub packages) of the current LC's parent hierarchy((Refer MQRY_LogicalComponent_UsedInterfaces_13).
   * </p>
   * <p>
   * All the interfaces used/implemented by the same level LCs((Refer MQRY_LogicalComponent_UsedInterfaces_14).
   * </p>
   * <p>
   * Except the interfaces that are already used by the current LC(Refer MQRY_LogicalComponent_UsedInterfaces_1Ex1)
   * </p>
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
   */
  @Override
  public List<CapellaElement> getOldAvailableElements(CapellaElement element) {
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (null == systemEngineering) {
      return availableElements;
    }

    // Update Mode
    if (element instanceof LogicalComponent) {
      LogicalComponent currentLC = (LogicalComponent) element;

      // all the interfaces implemented by owner and current LC
      availableElements.addAll(getRule_MQRY_LC_ImplInterfaces_11(currentLC));

      // all the interfaces in the interface pkg and sub pkgs of parentLC
      availableElements.addAll(getRule_MQRY_LC_ImplInterfaces_12(currentLC));

      // all the interfaces in the parent hierarchy
      availableElements.addAll(getRule_MQRY_LC_ImplInterfaces_13(currentLC));

      // all the interfaces used/implemented by same level LCs (parent's
      // hierarchy) and in InterfacePkg (sub pkgs) of same level LCs
      availableElements.addAll(getRule_MQRY_LC_ImplInterfaces_14(currentLC));

      // all the interfaces contained by InterfacePkg (and sub pkgs) of
      // current LC
      availableElements.addAll(getRule_MQRY_LC_ImplInterfaces_15(currentLC));

      // get allocated components (that is realized component of L-1 level)
      EList<Component> lcs = currentLC.getAllocatedComponents();
      // collect implemented interfaces of current logicalComponent
      for (Component logicalComponent : lcs) {
        availableElements.addAll(logicalComponent.getImplementedInterfaces());
      }

    }

    // removing the duplicate entries in the list
    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements.remove(element);

    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces implemented by...
   * </p>
   * <p>
   * The owner logical component of the current one.(Refer MQRY_LogicalComponent_ImplInterfaces_11).
   * </p>
   * @param currentLC_p the current Logical Component
   * @param parentLC_p the owner Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_ImplInterfaces_11(LogicalComponent currentLC_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    // current LC
    availableElements.addAll(ComponentExt.getImplementedInterfacesFiltered(currentLC_p, currentLC_p));

    // owner LC
    Component parentLC = ComponentExt.getParent(currentLC_p);
    if ((parentLC != null) && (parentLC instanceof LogicalComponent)) {
      availableElements.addAll(ComponentExt.getImplementedInterfacesFiltered(parentLC, currentLC_p));
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces contained by the interface package (and sub packages) of the current logical component's parent (Refer
   * MQRY_LogicalComponent_ImplInterfaces_12).
   * </p>
   * @param currentLC_p the current Logical Component
   * @param parentLC_p the owner Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_ImplInterfaces_12(LogicalComponent currentLC_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Object parent = currentLC_p.eContainer();
    if (null != parent) {
      if (parent instanceof LogicalComponentPkg) {
        LogicalComponentPkg rootLCPkg = LogicalComponentPkgExt.getRootLogicalComponentPkg((LogicalComponentPkg) parent);
        parent = rootLCPkg.eContainer(); // can be LA or LC
      }
      if (parent instanceof LogicalComponent) {
        availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(((LogicalComponent) parent).getOwnedInterfacePkg(), currentLC_p, false));
      } else if (parent instanceof LogicalArchitecture) {
        availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(((LogicalArchitecture) parent).getOwnedInterfacePkg(), currentLC_p, false));
      }
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces contained by the interface package(sub packages) of the current LC's parent hierarchy((Refer
   * MQRY_LogicalComponent_ImplInterfaces_13).
   * </p>
   * @param currentLC_p the current Logical Component
   * @param parentLC_p the parent Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_ImplInterfaces_13(LogicalComponent currentLC_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    // all the interfaces in the parent hierarchy
    for (Interface inter : LogicalComponentExt.getInterfacesFromLCParentHierarchy(currentLC_p)) {
      if (ComponentExt.isImplementingInterface(currentLC_p, inter)) {
        continue;
      }
      availableElements.add(inter);
    }
    return availableElements;
  }

  /**
   * <p>
   * All the interfaces used/implemented by the same level LCs((Refer MQRY_LogicalComponent_ImplInterfaces_14).
   * </p>
   * @param currentLC_p the current Logical Component
   * @param parentLC_p the parent Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_ImplInterfaces_14(LogicalComponent currentLC_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Object parent = currentLC_p.eContainer();
    if (null != parent) {
      if (parent instanceof LogicalComponentPkg) {
        LogicalComponentPkg rootLCPkg = LogicalComponentPkgExt.getRootLogicalComponentPkg((LogicalComponentPkg) parent);
        parent = rootLCPkg.eContainer();
      }
      if (parent instanceof LogicalComponent) {
        LogicalComponent parentLC = (LogicalComponent) parent;
        for (LogicalComponent lc : LogicalComponentExt.getSameLevelComponents(parentLC)) {
          if ((lc == null) || (lc.equals(currentLC_p))) {
            continue;
          }
          availableElements.addAll(LogicalComponentExt.getInterfacesFromLogicalComponent(lc, currentLC_p, false));
        }
      } else if (parent instanceof LogicalArchitecture) {
        LogicalArchitecture arch = (LogicalArchitecture) parent;
        for (LogicalComponent lc : LogicalArchitectureExt.getSameLevelComponents(arch)) {
          if ((lc == null) || (lc.equals(currentLC_p))) {
            continue;
          }
          availableElements.addAll(LogicalComponentExt.getInterfacesFromSameLevelLogicalComponent(lc, currentLC_p, false));
        }
      }
    }
    availableElements.removeAll(currentLC_p.getImplementedInterfaces());
    return availableElements;
  }

  /**
   * <p>
   * All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Logical Component.((Refer
   * MQRY_LogicalComponent_ImplInterfaces_15).
   * </p>
   * @param currentLC_p the current Logical Component
   * @param parentLogArch_p the parent Logical Architecture
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_ImplInterfaces_15(LogicalComponent currentLC_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (null != currentLC_p) {
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces implemented by the current Logical Component
   * </p>
   * <p>
   * Refer MQRY_LogicalComponent_ImplInterfaces_1
   * </p>
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
   *      boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    if (null == systemEngineering) {
      return currentElements;
    }

    // retrieve implemented interfaces of current component
    if (element_p instanceof LogicalComponent) {
      LogicalComponent currentLC = (LogicalComponent) element_p;
      currentElements.addAll(ComponentExt.getImplementedInterfaces(currentLC));

    }

    return currentElements;
  }

  public EClass getEClass() {
    return LaPackage.Literals.LOGICAL_COMPONENT;
  }

  public List<EReference> getEStructuralFeatures() {
    List<EReference> list = new ArrayList<EReference>(1);
    list.add(CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES);
    list.add(CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
    return list;
  }
}
