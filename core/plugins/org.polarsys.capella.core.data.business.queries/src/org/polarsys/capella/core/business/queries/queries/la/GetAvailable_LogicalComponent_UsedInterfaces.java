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
package org.polarsys.capella.core.business.queries.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.LogicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentPkgExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

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
   * @param currentLC the current Logical Component
   * @param parentLC the owner Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_UsedInterfaces_11(LogicalComponent currentLC) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    Component parentLC = LogicalComponentExt.getParentContainer(currentLC);
    if (parentLC instanceof LogicalComponent) {
      availableElements.addAll(ComponentExt.getUsedInterfaces(parentLC));
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces contained by the interface package (and sub packages) of the current logical component's parent (Refer
   * MQRY_LogicalComponent_UsedInterfaces_12).
   * </p>
   * @param currentLC the current Logical Component
   * @param parentLC the owner Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_UsedInterfaces_12(LogicalComponent currentLC) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Object parent = currentLC.eContainer();
    if (null != parent) {
      if (parent instanceof LogicalComponentPkg) {
        LogicalComponentPkg rootLCPkg = LogicalComponentPkgExt.getRootLogicalComponentPkg((LogicalComponentPkg) parent);
        parent = rootLCPkg.eContainer();
      }
      if (parent instanceof LogicalComponent) {
        availableElements.addAll(InterfacePkgExt.getAllInterfaces(((LogicalComponent) parent).getOwnedInterfacePkg()));
      } else if (parent instanceof LogicalArchitecture) {
        availableElements.addAll(InterfacePkgExt.getAllInterfaces(((LogicalArchitecture) parent).getOwnedInterfacePkg()));
      }
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces contained by the interface package(sub packages) of the current LC's parent hierarchy((Refer
   * MQRY_LogicalComponent_UsedInterfaces_13).
   * </p>
   * @param currentLC the current Logical Component
   * @param parentLC the parent Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_UsedInterfaces_13(LogicalComponent currentLC) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    // all the interfaces in the parent hierarchy
    for (Interface inter : LogicalComponentExt.getInterfacesFromLCParentHierarchy(currentLC)) {
      availableElements.add(inter);
    }
    return availableElements;
  }

  /**
   * <p>
   * All the interfaces used/implemented by the same level LCs((Refer MQRY_LogicalComponent_UsedInterfaces_14).
   * </p>
   * @param currentLC the current Logical Component
   * @param parentLC the parent Logical Component
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_UsedInterfaces_14(LogicalComponent currentLC) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Object parent = currentLC.eContainer();
    if (null != parent) {
      if (parent instanceof LogicalComponentPkg) {
        LogicalComponentPkg rootLCPkg = LogicalComponentPkgExt.getRootLogicalComponentPkg((LogicalComponentPkg) parent);
        parent = rootLCPkg.eContainer();
      }
      if (parent instanceof LogicalComponent) {
        LogicalComponent parentLC = (LogicalComponent) parent;
        for (LogicalComponent lc : LogicalComponentExt.getSameLevelComponents(parentLC)) {
          if ((lc == null) || (lc.equals(currentLC))) {
            continue;
          }
        }
      } else if (parent instanceof LogicalArchitecture) {
        LogicalArchitecture arch = (LogicalArchitecture) parent;
        for (LogicalComponent lc : LogicalArchitectureExt.getSameLevelComponents(arch)) {
          if ((lc == null) || (lc.equals(currentLC))) {
            continue;
          }

        }
      }
    }
    return availableElements;
  }

  /**
   * <p>
   * All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Logical Component.((Refer
   * MQRY_LogicalComponent_UsedInterfaces_15).
   * </p>
   * @param currentLC the current Logical Component
   * @param parentLogArch the parent Logical Architecture
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_LC_UsedInterfaces_15(LogicalComponent currentLC) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (null != currentLC) {
    }
    return availableElements;
  }
}
