/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.fa;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

@Deprecated
public class GetAvailable_ComponentPort_MinCardinality extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
//		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
//		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
//		boolean isPropertyFromSharedPkg = false;
//		if (null == systemEngineering) {
//			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
//			for (ReuseLink link : sharedPkg.getReuseLinks()) {
//				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
//					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
//					isPropertyFromSharedPkg = true;
//					break;
//				}
//			}
//			if (systemEngineering == null)
//				return availableElements;
//		}
//		if (element instanceof ComponentPort) {
//			ComponentPort property = (ComponentPort) element;
//			if (!isPropertyFromSharedPkg) {
//				availableElements.addAll(getRule_MQRY_StandardPort_MinCard_11(property));
//				availableElements.addAll(getRule_MQRY_StandardPort_MinCard_12(property));
//			}
//			availableElements.addAll(getRule_MQRY_StandardPort_MinCard_13(property, systemEngineering));
//		}
//		availableElements = ListExt.removeDuplicates(availableElements);
//		return availableElements;
		throw new UnsupportedOperationException();
	}

//	/** 
//	 * All the NumericValues contained by the Data Package (and all of its
//	 * subpackages) of the current Element's parent (can be a Component, a
//	 * Component Architecture Decomposition package, or a Component Architecture
//	 * root package).
//	 */
//	private List<CapellaElement> getRule_MQRY_StandardPort_MinCard_11(ComponentPort currentProperty) {
//		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
//		PhysicalComponent classifier = (PhysicalComponent) currentProperty.eContainer();
//		NumericValue minCard = currentProperty.getOwnedMinCard();
//		ComponentArchitecture arch = PhysicalComponentExt.getOwningPhysicalArchitecture(classifier);
//		if (null != arch) {
//			DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
//			if (null != dataPkg) {
//				for (DataValue value : DataPkgExt.getAllDataValues(dataPkg)) {
//					if (value instanceof NumericValue) {
//						if (value.equals(minCard))
//							continue;
//						availableElements.add(value);
//					}
//				}
//			}
//		}
//		return availableElements;
//	}
//
//	/** 
//	 * All the NumericValues contained by the Data Packages (and all of its
//	 * subpackages) of the current Element's parents hierarchy according to
//	 * layer visibility and multiple decomposition rules.
//	 */
//	private List<CapellaElement> getRule_MQRY_StandardPort_MinCard_12(ComponentPort currentProperty) {
//		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
//		PhysicalComponent classifier = (PhysicalComponent) currentProperty.eContainer();
//		NumericValue minCard = currentProperty.getOwnedMinCard();
//		List<DataPkg> dataPkgList = ClassifierExt.getDataPkgsFromParentHierarchy(classifier);
//		for (DataPkg dataPkg : dataPkgList) {
//			if (null != dataPkg) {
//				for (DataValue value : DataPkgExt.getAllDataValues(dataPkg)) {
//					if (value instanceof NumericValue) {
//						if (value.equals(minCard))
//							continue;
//						availableElements.add(value);
//					}
//				}
//			}
//		}
//		return availableElements;
//	}
//
//	/** 
//	 * All the NumericValues contained by the Data Package (and all of its
//	 * subpackages) of the Shared Assets Package.
//	 */
//	private List<CapellaElement> getRule_MQRY_StandardPort_MinCard_13(ComponentPort currentProperty, SystemEngineering systemEngineering) {
//		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
//		NumericValue minCard = currentProperty.getOwnedMinCard();
//		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering)) {
//			DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
//			if (null != dataPkg) {
//				for (DataValue value : DataPkgExt.getAllDataValues(dataPkg)) {
//					if (value instanceof NumericValue) {
//						if (value.equals(minCard))
//							continue;
//						availableElements.add(value);
//					}
//				}
//			}
//		}
//		return availableElements;
//	}

}