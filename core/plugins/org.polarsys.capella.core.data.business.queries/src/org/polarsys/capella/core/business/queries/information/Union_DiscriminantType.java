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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.ClassExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.StructureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * @deprecated
 */
@Deprecated
public class Union_DiscriminantType implements IBusinessQuery {
	private List<CapellaElement> getElementsFromComponentArchitecture(ComponentArchitecture arch, Type type) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch != null) {
			DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
			if (null != dataPkg) {
				for (DataType element : DataPkgExt.getAllDataTypes(dataPkg)) {
					if (null != type && type.equals(element))
						continue;
					availableElements.add(element);
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Union_Descriminant_11(Union currentUnion_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		UnionProperty disc = currentUnion_p.getDiscriminant();
		Type type = null;
		if (disc != null)
			type = disc.getType();
		DataPkg classPkg = ClassExt.getRootOwnerDataPkg(currentUnion_p);
		if (classPkg != null) {
			ComponentArchitecture parentComponentArchitecture = DataPkgExt.getRootComponentArchitecture(classPkg);
			availableElements.addAll(getElementsFromComponentArchitecture(parentComponentArchitecture, type));

			Component parentComponent = DataPkgExt.getRootComponent(classPkg);
			if (null != parentComponent) {
				if (parentComponent instanceof LogicalComponent) {
					DataPkg dataPkg = ((LogicalComponent) parentComponent).getOwnedDataPkg();
					if (null != dataPkg) {
						for (DataType element : DataPkgExt.getAllDataTypes(dataPkg)) {
							if (null != type && type.equals(element))
								continue;
							availableElements.add(element);
						}
					}
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Union_Descriminant_12(Union currentUnion_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		UnionProperty disc = currentUnion_p.getDiscriminant();
		Type type = null;
		if (disc != null)
			type = disc.getType();
		DataPkg classPkg = ClassExt.getRootOwnerDataPkg(currentUnion_p);
		if (classPkg != null) {
			for (DataPkg dataPkg : StructureExt.getDataPkgsFromParentHierarchy(classPkg)) {
				if (null != dataPkg) {
					for (DataType element : DataPkgExt.getAllDataTypes(dataPkg)) {
						if (null != type && type.equals(element))
							continue;
						availableElements.add(element);
					}
				}
			}
		}
		availableElements.addAll(getRule_MQRY_Union_Descriminant_12_1(currentUnion_p));
		return availableElements;
	}

	/*
	 * Layer visibility
	 */
	private List<CapellaElement> getRule_MQRY_Union_Descriminant_12_1(Union currentUnion_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		UnionProperty disc = currentUnion_p.getDiscriminant();
		Type type = null;
		if (disc != null)
			type = disc.getType();
		DataPkg classPkg = ClassExt.getRootOwnerDataPkg(currentUnion_p);
		if (classPkg != null) {
			SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(classPkg);
			SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
			availableElements.addAll(getElementsFromComponentArchitecture(ca, type));

			ComponentArchitecture arch = DataPkgExt.getRootComponentArchitecture(classPkg);
			if (null != arch) {
				if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
					LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
					availableElements.addAll(getElementsFromComponentArchitecture(logArch, type));
				}
				if ((arch instanceof EPBSArchitecture)) {
					PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
					availableElements.addAll(getElementsFromComponentArchitecture(physArch, type));
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Union_Descriminant_13(Union currentUnion_p, SystemEngineering systemEngineering_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		UnionProperty disc = currentUnion_p.getDiscriminant();
		Type type = null;
		if (disc != null)
			type = disc.getType();
		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering_p)) {
			DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
			if (dataPkg != null) {
				for (DataType element : DataPkgExt.getAllDataTypes(dataPkg)) {
					if (null != type && type.equals(element))
						continue;
					availableElements.add(element);
				}
			}
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				for (CapellaElement element : GenericPkgExt.getAllDataTypes(pkg)) {
					if (null != type && type.equals(element))
						continue;
					availableElements.add(element);
				}
			}
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Refer MQRY_Union_Discriminant_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
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

		if (element_p instanceof Union) {
			Union union = (Union) element_p;
			if (!isElementFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Union_Descriminant_11(union));
				availableElements.addAll(getRule_MQRY_Union_Descriminant_12(union));
			}
			availableElements.addAll(getRule_MQRY_Union_Descriminant_13(union, systemEngineering));

		}

		availableElements = ListExt.removeDuplicates(availableElements);

		return availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (element_p instanceof Union) {
			Union currentUnion = (Union) element_p;
			UnionProperty disc = currentUnion.getDiscriminant();
			if (disc != null) {
				currentElements.add(disc.getType());
			}
		}

		return currentElements;
	}

	public EClass getEClass() {
		return InformationPackage.Literals.UNION;
	}

	public List<EReference> getEStructuralFeatures() {
	  return null;
	}
}
