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
package org.polarsys.capella.core.business.queries.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
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
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

/**
 * <p>
 * Gets all the interfaces used by...
 * </p>
 * <p>
 * The owner logical component of the current one.(Refer MQRY_LogicalComponent_UsedInterfaces_11)
 * </p>
 * <p>
 * All the interfaces contained by the interface package (and sub packages) of the current logical component's parent (Refer
 * MQRY_LogicalComponent_UsedInterfaces_12)
 * </p>
 * <p>
 * All the interfaces contained by the interface package(sub packages) of the current LC's parent hierarchy((Refer MQRY_LogicalComponent_UsedInterfaces_13)
 * </p>
 * <p>
 * All the interfaces used/implemented by the same level LCs((Refer MQRY_LogicalComponent_UsedInterfaces_14)
 * </p>
 * <p>
 * Except the interfaces that are already used by the current LC(Refer MQRY_LogicalComponent_UsedInterfaces_1Ex1)
 * </p>
 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
 */
public class GetAvailable_LogicalComponent_UsedInterfaces extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement element = (CapellaElement) input;
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (null == systemEngineering) {
      return (List) availableElements;
    }

    // Update Mode
    if (element instanceof LogicalComponent) {
      LogicalComponent currentLC = (LogicalComponent) element;

      // all the interfaces implemented by owner LC
      availableElements.addAll(getRule_MQRY_LC_UsedInterfaces_11(currentLC));

      // all the interfaces in the interface pkg and sub pkgs of parentLC
      availableElements.addAll(getRule_MQRY_LC_UsedInterfaces_12(currentLC));

      // all the interfaces in the parent hierarchy
      availableElements.addAll(getRule_MQRY_LC_UsedInterfaces_13(currentLC));

      // all the interfaces used/implemented by same level LCs (parent's
      // hierarchy) and in InterfacePkg (sub pkgs) of same level LCs
      availableElements.addAll(getRule_MQRY_LC_UsedInterfaces_14(currentLC));

      // all the interfaces contained by InterfacePkg (and sub pkgs) of
      // current LC
      availableElements.addAll(getRule_MQRY_LC_UsedInterfaces_15(currentLC));

    }

    // removing the duplicate entries in the list
    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements.remove(element);

    return (List) availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces Used by...
   * </p>
   * <p>
   * The owner logical component of the current one.(Refer MQRY_LogicalComponent_UsedInterfaces_11).
   * </p>
   * @param currentLC_p the current Logical Component
   * @param parentLC_p the owner Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_UsedInterfaces_11(LogicalComponent currentLC_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    // current LC
    availableElements.addAll(ComponentExt.getImplementedInterfacesFiltered(currentLC_p, currentLC_p));

    Component parentLC = LogicalComponentExt.getParentContainer(currentLC_p);
    if (parentLC instanceof LogicalComponent) {
      availableElements.addAll(ComponentExt.getUsedInterfacesFiltered(parentLC, currentLC_p));
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces contained by the interface package (and sub packages) of the current logical component's parent (Refer
   * MQRY_LogicalComponent_UsedInterfaces_12).
   * </p>
   * @param currentLC_p the current Logical Component
   * @param parentLC_p the owner Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_UsedInterfaces_12(LogicalComponent currentLC_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Object parent = currentLC_p.eContainer();
    if (null != parent) {
      if (parent instanceof LogicalComponentPkg) {
        LogicalComponentPkg rootLCPkg = LogicalComponentPkgExt.getRootLogicalComponentPkg((LogicalComponentPkg) parent);
        parent = rootLCPkg.eContainer();
      }
      if (parent instanceof LogicalComponent) {
        availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(((LogicalComponent) parent).getOwnedInterfacePkg(), currentLC_p, true));
      } else if (parent instanceof LogicalArchitecture) {
        availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(((LogicalArchitecture) parent).getOwnedInterfacePkg(), currentLC_p, true));
      }
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces contained by the interface package(sub packages) of the current LC's parent hierarchy((Refer
   * MQRY_LogicalComponent_UsedInterfaces_13).
   * </p>
   * @param currentLC_p the current Logical Component
   * @param parentLC_p the parent Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_UsedInterfaces_13(LogicalComponent currentLC_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    // all the interfaces in the parent hierarchy
    for (Interface inter : LogicalComponentExt.getInterfacesFromLCParentHierarchy(currentLC_p)) {
      if (ComponentExt.isUsingInterface(currentLC_p, inter)) {
        continue;
      }
      availableElements.add(inter);
    }
    return availableElements;
  }

  /**
   * <p>
   * All the interfaces used/implemented by the same level LCs((Refer MQRY_LogicalComponent_UsedInterfaces_14).
   * </p>
   * @param currentLC_p the current Logical Component
   * @param parentLC_p the parent Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_UsedInterfaces_14(LogicalComponent currentLC_p) {
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
        }
      } else if (parent instanceof LogicalArchitecture) {
        LogicalArchitecture arch = (LogicalArchitecture) parent;
        for (LogicalComponent lc : LogicalArchitectureExt.getSameLevelComponents(arch)) {
          if ((lc == null) || (lc.equals(currentLC_p))) {
            continue;
          }

        }
      }
    }
    availableElements.removeAll(currentLC_p.getUsedInterfaces());
    return availableElements;
  }

  /**
   * <p>
   * All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Logical Component.((Refer
   * MQRY_LogicalComponent_UsedInterfaces_15).
   * </p>
   * @param currentLC_p the current Logical Component
   * @param parentLogArch_p the parent Logical Architecture
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_UsedInterfaces_15(LogicalComponent currentLC_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (null != currentLC_p) {
    }
    return availableElements;
  }
}
