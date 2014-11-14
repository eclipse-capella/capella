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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
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
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
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

/**
 */
public class PhysicalQuantity_Unit implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

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
	 * Gets all the Units contained by the Value Package (and all of its
	 * sub-packages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalDimension_DefaultUnit_11(PhysicalQuantity currentPhysicalDimension_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		EObject container =  currentPhysicalDimension_p.eContainer();
		BlockArchitecture arch = DataPkgExt.getRootBlockArchitecture(currentPhysicalDimension_p);
		availableElements.addAll(getElementsFromBlockArchitecture(arch, currentPhysicalDimension_p.getUnit()));
if (container instanceof DataPkg) {
  Component comp = DataPkgExt.getRootComponent((DataPkg) container);
  if (null != comp) {
      DataPkg dataPkg = comp.getOwnedDataPkg();
      if (null != dataPkg) {
        for (Unit unit : DataPkgExt.getAllUnits(dataPkg)) {
          if (unit.equals(currentPhysicalDimension_p.getUnit()))
            continue;
          availableElements.add(unit);
        }
      }
  }
}

		return availableElements;
	}

	/*
	 * Gets all the Units contained by the Value Package (and all of its
	 * sub-packages) of the current Element's parents hierarchy according to
	 * layer visibility and multiple decomposition rules.
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalDimension_DefaultUnit_12(PhysicalQuantity currentPhysicalDimension_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		EObject container =   currentPhysicalDimension_p.eContainer();
		if (container instanceof DataPkg) {
	    List<DataPkg> dataPkgList = DataPkgExt.getDataPkgsFromParentHierarchy((DataPkg) container);

	    for (DataPkg dataPkg : dataPkgList) {
	      if (null != dataPkg) {
          for (Unit unit : DataPkgExt.getAllUnits(dataPkg)) {
            if (unit.equals(currentPhysicalDimension_p.getUnit()))
              continue;
            availableElements.add(unit);
          }
        }
	    }
    }

		availableElements.addAll(getRule_MQRY_PhysicalDimension_DefaultUnit_12_1(currentPhysicalDimension_p));
		return availableElements;
	}

	/*
	 * layre visibility
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalDimension_DefaultUnit_12_1(PhysicalQuantity currentPhysicalDimension_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		
		BlockArchitecture arch = DataPkgExt.getRootBlockArchitecture(currentPhysicalDimension_p);
		
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentPhysicalDimension_p);
		OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
		if (null != oa) {
			availableElements.addAll(getElementsFromBlockArchitecture(oa, currentPhysicalDimension_p.getUnit()));
		}else{
			SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
			availableElements.addAll(getElementsFromBlockArchitecture(ca, currentPhysicalDimension_p.getUnit()));
		}
		
		if (arch != null) {
			if (null != oa && (arch instanceof LogicalArchitecture) || (arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
				availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, currentPhysicalDimension_p.getUnit()));
			}
			if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
				availableElements.addAll(getElementsFromBlockArchitecture(logArch, currentPhysicalDimension_p.getUnit()));
			}
			if ((arch instanceof EPBSArchitecture)) {
				PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
				availableElements.addAll(getElementsFromBlockArchitecture(physArch, currentPhysicalDimension_p.getUnit()));
			}
		}
		
		return availableElements;
	}

	/*
	 * All the Units contained by the Value Package (and all of its
	 * sub-packages) of the Shared Assets Package.
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalDimension_DefaultUnit_13(PhysicalQuantity currentPhysicalDimension_p, SystemEngineering systemEngineering_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering_p)) {
			DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
			if (null != dataPkg) {
				for (Unit unit : DataPkgExt.getAllUnits(dataPkg)) {
					if (unit.equals(currentPhysicalDimension_p.getUnit()))
						continue;
					availableElements.add(unit);
				}
			}
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				for (Unit unit : GenericPkgExt.getAllUnits(pkg)) {
					if (unit.equals(currentPhysicalDimension_p.getUnit()))
						continue;
					availableElements.add(unit);
				}
			}
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the Units contained by the Value Package (and all of its
	 * sub-packages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 * </p>
	 * <p>
	 * All the Units contained by the Value Package (and all of its
	 * sub-packages) of the current Element's parents hierarchy according to
	 * layer visibility and multiple decomposition rules.
	 * </p>
	 * <p>
	 * All the Units contained by the Value Package (and all of its
	 * sub-packages) of the Shared Assets Package.
	 * </p>
	 * <p>
	 * Except the current value itself
	 * </p>
	 * <p>
	 * Refer MQRY_PhysicalDimension_DefaultUnit_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.common.ui.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
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

		if (element_p instanceof PhysicalQuantity) {
		  PhysicalQuantity currentPhysicalDimension = (PhysicalQuantity) element_p;
			if (!isElementFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_PhysicalDimension_DefaultUnit_11(currentPhysicalDimension));
				availableElements.addAll(getRule_MQRY_PhysicalDimension_DefaultUnit_12(currentPhysicalDimension));
			}
			availableElements.addAll(getRule_MQRY_PhysicalDimension_DefaultUnit_13(currentPhysicalDimension, systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.common.ui.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
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

		if (element_p instanceof PhysicalQuantity) {
		  PhysicalQuantity currentPhysicalDimension = (PhysicalQuantity) element_p;
			Unit link = currentPhysicalDimension.getUnit();
			if (null != link) {
				currentElements.add(link);
			}
		}
		return currentElements;
	}

	public EClass getEClass() {
		return DatatypePackage.Literals.PHYSICAL_QUANTITY;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(DatatypePackage.Literals.PHYSICAL_QUANTITY__UNIT);
	}

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
	  return RefactorDebugger.callAndTestQuery("GetAvailable_PhysicalQuantity_Unit__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
	  return RefactorDebugger.callAndTestQuery("GetCurrent_PhysicalQuantity_Unit", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
	}
}
