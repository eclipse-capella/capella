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
package org.polarsys.capella.core.business.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public abstract class NumericValue_Unit implements IBusinessQuery {

  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, Unit link) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (null != arch) {
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(arch);
      if (null != dataPkg) {
        for (Unit unit : DataPkgExt.getAllUnits(dataPkg)) {
          if (unit.equals(link))
            continue;
          availableElements.add(unit);
        }
      }
    }
    return availableElements;
  }

  /*
   * Gets all the Units contained by the Value Package (and all of its sub-packages) of the current Element’s parent (can be a Component, a Component
   * Architecture Decomposition package, or a Component Architecture root package).
   */
  protected List<CapellaElement> getRule_MQRY_NumericValue_Unit_11(NumericValue currentNumericValue_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    EObject container = currentNumericValue_p.eContainer();
    while (container != null) {
      if (container instanceof Component) {
        DataPkg componentDataPkg = ((Component) container).getOwnedDataPkg();
        if (componentDataPkg != null) {
          for (Unit u : DataPkgExt.getAllUnits((((Component) container).getOwnedDataPkg()))) {
            if (!u.equals(currentNumericValue_p.getUnit())) {
              availableElements.add(u);
            }
          }
        }
      }
      container = container.eContainer();
    }

    BlockArchitecture arch = DataPkgExt.getRootBlockArchitecture(currentNumericValue_p);
    availableElements.addAll(getElementsFromBlockArchitecture(arch, currentNumericValue_p.getUnit()));

    return availableElements;
  }

  /*
   * Gets all the Units contained by the Value Package (and all of its sub-packages) of the current Element’s parents hierarchy according to layer visibility
   * and multiple decomposition rules.
   */
  protected List<CapellaElement> getRule_MQRY_NumericValue_Unit_12(NumericValue currentNumericValue_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    EObject container = currentNumericValue_p.eContainer();
    if (container instanceof DataPkg) {
      List<DataPkg> dataPkgList = DataPkgExt.getDataPkgsFromParentHierarchy((DataPkg) container);

      for (DataPkg dataPkg : dataPkgList) {
        if (null != dataPkg) {
          for (Unit unit : DataPkgExt.getAllUnits(dataPkg)) {
            if (unit.equals(currentNumericValue_p.getUnit()))
              continue;
            availableElements.add(unit);
          }
        }
      }
    }

    availableElements.addAll(getRule_MQRY_NumericValue_Unit_12_1(currentNumericValue_p));
    return availableElements;
  }

  /*
   * layer visibility
   */
  private List<CapellaElement> getRule_MQRY_NumericValue_Unit_12_1(NumericValue currentNumericValue_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    BlockArchitecture arch = DataPkgExt.getRootBlockArchitecture(currentNumericValue_p);

    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentNumericValue_p);
    OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
    if (null != oa) {
      availableElements.addAll(getElementsFromBlockArchitecture(oa, currentNumericValue_p.getUnit()));
    } else {
      SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
      availableElements.addAll(getElementsFromBlockArchitecture(ca, currentNumericValue_p.getUnit()));
    }

    if (arch != null) {
      if (null != oa && (arch instanceof LogicalArchitecture) || (arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
        SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
        availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, currentNumericValue_p.getUnit()));
      }
      if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
        LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
        availableElements.addAll(getElementsFromBlockArchitecture(logArch, currentNumericValue_p.getUnit()));
      }
      if ((arch instanceof EPBSArchitecture)) {
        PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
        availableElements.addAll(getElementsFromBlockArchitecture(physArch, currentNumericValue_p.getUnit()));
      }
    }

    return availableElements;
  }

  /*
   * All the Units contained by the Value Package (and all of its sub-packages) of the Shared Assets Package.
   */
  protected List<CapellaElement> getRule_MQRY_NumericValue_Unit_13(NumericValue currentNumericValue_p, SystemEngineering systemEngineering_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering_p)) {
      DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
      if (null != dataPkg) {
        for (Unit unit : DataPkgExt.getAllUnits(dataPkg)) {
          if (unit.equals(currentNumericValue_p.getUnit()))
            continue;
          availableElements.add(unit);
        }
      }
      // for (GenericPkg pkg : sharedPkg.getGenericPkgs()) {
      GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
      if (pkg != null) {
        for (Unit unit : GenericPkgExt.getAllUnits(pkg)) {
          if (unit.equals(currentNumericValue_p.getUnit()))
            continue;
          availableElements.add(unit);
        }
      }
      // }
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the Units contained by the Value Package (and all of its sub-packages) of the current Element’s parent (can be a Component, a Component
   * Architecture Decomposition package, or a Component Architecture root package).
   * </p>
   * <p>
   * All the Units contained by the Value Package (and all of its sub-packages) of the current Element’s parents hierarchy according to layer visibility and
   * multiple decomposition rules.
   * </p>
   * <p>
   * All the Units contained by the Value Package (and all of its sub-packages) of the Shared Assets Package.
   * </p>
   * <p>
   * Except the current value itself
   * </p>
   * <p>
   * Refer MQRY_PhysicalDimension_DefaultUnit_1
   * </p>
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

    boolean isElementFromSharedPkg = false;
    if (null == systemEngineering) {
      SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
      for (ReuseLink link : sharedPkg.getReuseLinks()) {
        if (SystemEngineeringExt.getSystemEngineering(link) != null) {
          systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
          isElementFromSharedPkg = true;
          break;
        }
      }
      if (systemEngineering == null)
        return availableElements;
    }

    if (element_p instanceof NumericValue) {
      NumericValue currentNumericValue = (NumericValue) element_p;
      if (!isElementFromSharedPkg) {
        availableElements.addAll(getRule_MQRY_NumericValue_Unit_11(currentNumericValue));
        availableElements.addAll(getRule_MQRY_NumericValue_Unit_12(currentNumericValue));
      }
      availableElements.addAll(getRule_MQRY_NumericValue_Unit_13(currentNumericValue, systemEngineering));
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

    if (null == systemEngineering) {
      SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
      for (ReuseLink link : sharedPkg.getReuseLinks()) {
        if (SystemEngineeringExt.getSystemEngineering(link) != null) {
          systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
          break;
        }
      }
      if (systemEngineering == null)
        return currentElements;
    }

    if (element_p instanceof NumericValue) {
      NumericValue currentLiteralNumericValue = (NumericValue) element_p;
      Unit link = currentLiteralNumericValue.getUnit();
      if (null != link) {
        currentElements.add(link);
      }
    }
    return currentElements;
  }
}
