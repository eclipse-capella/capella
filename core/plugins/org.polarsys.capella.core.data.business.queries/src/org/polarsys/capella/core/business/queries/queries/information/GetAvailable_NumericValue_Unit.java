/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
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
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_NumericValue_Unit extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * <p>
   * Gets all the Units contained by the Value Package (and all of its sub-packages) of the current Element�s parent (can be a Component, a Component
   * Architecture Decomposition package, or a Component Architecture root package).
   * </p>
   * <p>
   * All the Units contained by the Value Package (and all of its sub-packages) of the current Element�s parents hierarchy according to layer visibility and
   * multiple decomposition rules.
   * </p>
   * <p>
   * All the Units contained by the Value Package (and all of its sub-packages) of the Shared Assets Package.
   * </p>
   * <p>
   * Refer MQRY_PhysicalDimension_DefaultUnit_1
   * </p>
   */
  public List<EObject> getAvailableElements(CapellaElement element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    if (systemEngineering == null) {
      return availableElements;
    }
    if (element instanceof NumericValue) {
      NumericValue currentNumericValue = (NumericValue) element;
      availableElements.addAll(getRule_MQRY_NumericValue_Unit_11(currentNumericValue));
      availableElements.addAll(getRule_MQRY_NumericValue_Unit_12(currentNumericValue));
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_NumericValue_Unit_11(NumericValue currentNumericValue) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    EObject container = currentNumericValue.eContainer();
    while (container != null) {
      if (container instanceof Component) {
        DataPkg componentDataPkg = ((Component) container).getOwnedDataPkg();
        if (componentDataPkg != null) {
          for (Unit u : DataPkgExt.getAllUnits((((Component) container).getOwnedDataPkg()))) {
              availableElements.add(u);
          }
        }
      }
      container = container.eContainer();
    }
    BlockArchitecture arch = DataPkgExt.getRootBlockArchitecture(currentNumericValue);
    availableElements.addAll(getElementsFromBlockArchitecture(arch, currentNumericValue.getUnit()));
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_NumericValue_Unit_12(NumericValue currentNumericValue) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    EObject container = currentNumericValue.eContainer();
    if (container instanceof DataPkg) {
      List<DataPkg> dataPkgList = DataPkgExt.getDataPkgsFromParentHierarchy((DataPkg) container);
      for (DataPkg dataPkg : dataPkgList) {
        if (null != dataPkg) {
          for (Unit unit : DataPkgExt.getAllUnits(dataPkg)) {
            availableElements.add(unit);
          }
        }
      }
    }
    availableElements.addAll(getRule_MQRY_NumericValue_Unit_12_1(currentNumericValue));
    return availableElements;
  }

  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, Unit link) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (null != arch) {
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(arch);
      if (null != dataPkg) {
        for (Unit unit : DataPkgExt.getAllUnits(dataPkg)) {
          availableElements.add(unit);
        }
      }
    }
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_NumericValue_Unit_12_1(NumericValue currentNumericValue) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = DataPkgExt.getRootBlockArchitecture(currentNumericValue);
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentNumericValue);
    OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
    if (null != oa) {
      availableElements.addAll(getElementsFromBlockArchitecture(oa, currentNumericValue.getUnit()));
    } else {
      SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
      availableElements.addAll(getElementsFromBlockArchitecture(ca, currentNumericValue.getUnit()));
    }
    if (arch != null) {
      if (((null != oa) && (arch instanceof LogicalArchitecture)) || (arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
        SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
        availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, currentNumericValue.getUnit()));
      }
      if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
        LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
        availableElements.addAll(getElementsFromBlockArchitecture(logArch, currentNumericValue.getUnit()));
      }
      if ((arch instanceof EPBSArchitecture)) {
        PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
        availableElements.addAll(getElementsFromBlockArchitecture(physArch, currentNumericValue.getUnit()));
      }
    }
    return availableElements;
  }

}