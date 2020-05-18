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
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.ClassExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.StructureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Union_DiscriminantType extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Refer MQRY_Union_Discriminant_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		List<EObject> availableElements = new ArrayList<EObject>();
		boolean isElementFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
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
		if (element instanceof Union) {
			Union union = (Union) element;
			if (!isElementFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Union_Descriminant_11(union));
				availableElements.addAll(getRule_MQRY_Union_Descriminant_12(union));
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Union_Descriminant_11(Union currentUnion) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		UnionProperty disc = currentUnion.getDiscriminant();
		Type type = null;
		if (disc != null)
			type = disc.getType();
		DataPkg classPkg = ClassExt.getRootOwnerDataPkg(currentUnion);
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

	private List<CapellaElement> getRule_MQRY_Union_Descriminant_12(Union currentUnion) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		UnionProperty disc = currentUnion.getDiscriminant();
		Type type = null;
		if (disc != null)
			type = disc.getType();
		DataPkg classPkg = ClassExt.getRootOwnerDataPkg(currentUnion);
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
		availableElements.addAll(getRule_MQRY_Union_Descriminant_12_1(currentUnion));
		return availableElements;
	}

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

	private List<CapellaElement> getRule_MQRY_Union_Descriminant_12_1(Union currentUnion) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		UnionProperty disc = currentUnion.getDiscriminant();
		Type type = null;
		if (disc != null)
			type = disc.getType();
		DataPkg classPkg = ClassExt.getRootOwnerDataPkg(currentUnion);
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

}