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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_Collection_MinCardinality extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
		BlockArchitecture currentBlockArchitecture = DataPkgExt.getRootBlockArchitecture(element_p);
		SystemEngineering systemEngineering = SystemEngineeringExt.getSystemEngineering(element_p);
		OperationalAnalysis operationalAnalysis = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
		returnValue.addAll(getDataFromLevel(operationalAnalysis, element_p));
		if (!(currentBlockArchitecture instanceof OperationalAnalysis)) {
			SystemAnalysis systemAnalysis = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
			returnValue.addAll(getDataFromLevel(systemAnalysis, element_p));
			if (!(currentBlockArchitecture instanceof SystemAnalysis)) {
				LogicalArchitecture logicalArchitecture = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
				returnValue.addAll(getDataFromLevel(logicalArchitecture, element_p));
				if (!(currentBlockArchitecture instanceof LogicalArchitecture)) {
					PhysicalArchitecture physicalArchitecture = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
					returnValue.addAll(getDataFromLevel(physicalArchitecture, element_p));
					if (!(currentBlockArchitecture instanceof PhysicalArchitecture)) {
						EPBSArchitecture epbsArchitecture = SystemEngineeringExt.getEPBSArchitecture((systemEngineering));
						returnValue.addAll(getDataFromLevel(epbsArchitecture, element_p));
					}
				}
			}
		}
		returnValue.addAll(getUnlevelizedData(element_p));
		returnValue.addAll(getDataFromComponentHierarchy(element_p));
		returnValue.addAll(getDataFromRealizedComponentsHierarchy(element_p));
		returnValue.addAll(getTypesFromComponentHierarchy(element_p));
		returnValue = filterUnNamedElements(returnValue);
		return returnValue;
	}

	/** 
	 * This method purpose is to get the available data related to the given element in the given layer
	 * @param blockArchitecture_p the layer
	 * @param capellaElement_p the capella element
	 * @return the available elements
	 */
	public List<CapellaElement> getDataFromLevel(BlockArchitecture blockArchitecture_p, CapellaElement capellaElement_p) {
		if (null != blockArchitecture_p) {
			DataPkg dataPkg = blockArchitecture_p.getOwnedDataPkg();
			if (null != dataPkg) {
				return getDataFromLevel(dataPkg, capellaElement_p);
			}
		}
		return Collections.emptyList();
	}

	/** 
	 * Returns the available data which do not need to use a level to be found.<br>
	 * The default implementation returns an empty list.<br>
	 * May be overridden when queries need to find data without using layers.
	 * @param capellaElement_p the capella element
	 * @return the available elements
	 */
	public List<CapellaElement> getUnlevelizedData(CapellaElement capellaElement_p) {
		return Collections.emptyList();
	}

	/** 
	 */
	protected List<CapellaElement> getDataFromComponentHierarchy(CapellaElement element_p) {
		List<CapellaElement> allDatas = new ArrayList<CapellaElement>();
		for (Component cpnt : CapellaElementExt.getComponentHierarchy(element_p)) {
			DataPkg dataPkg = cpnt.getOwnedDataPkg();
			if (null != dataPkg) {
				allDatas.addAll(getDataFromLevel(dataPkg, element_p));
			}
		}
		return allDatas;
	}

	/** 
	 */
	protected List<CapellaElement> getDataFromRealizedComponentsHierarchy(CapellaElement element_p) {
		List<CapellaElement> allDatas = new ArrayList<CapellaElement>();
		Component currentCpnt = (element_p instanceof Component) ? (Component) element_p : null;
		if (null == currentCpnt) {
			currentCpnt = (Component) EcoreUtil2.getFirstContainer(element_p, CsPackage.Literals.COMPONENT);
		}
		if (null != currentCpnt) {
			for (Component allocatedCpnt : currentCpnt.getAllocatedComponents()) {
				List<Component> componentHierarchy = CapellaElementExt.getComponentHierarchy(allocatedCpnt);
				componentHierarchy.add(allocatedCpnt);
				for (Component cpnt : componentHierarchy) {
					DataPkg dataPkg = cpnt.getOwnedDataPkg();
					if (null != dataPkg) {
						for (CapellaElement data : getDataFromLevel(dataPkg, element_p)) {
							if (!allDatas.contains(data)) {
								allDatas.add(data);
							}
						}
					}
				}
			}
		}
		return allDatas;
	}

	/** 
	 */
	protected List<CapellaElement> getTypesFromComponentHierarchy(CapellaElement element_p) {
		List<CapellaElement> allDatas = new ArrayList<CapellaElement>();
		for (Component cpnt : CapellaElementExt.getComponentHierarchy(element_p)) {
			DataPkg dataPkg = cpnt.getOwnedDataPkg();
			if (null != dataPkg) {
				allDatas.addAll(DataPkgExt.getAllTypesFromDataPkg(dataPkg));
			}
		}
		allDatas = removeNonPrimitiveClasses(allDatas);
		allDatas = removeNonPrimitiveCollections(allDatas);
		return allDatas;
	}

	/** 
	 * filter unNamed Capella Elements
	 * @param list_p
	 * @return : List<CapellaElement>
	 */
	protected List<CapellaElement> filterUnNamedElements(List<CapellaElement> list_p) {
		List<CapellaElement> result = new ArrayList<CapellaElement>(1);
		for (CapellaElement capellaElement : list_p) {
			if (capellaElement instanceof AbstractNamedElement) {
				String name = ((AbstractNamedElement) capellaElement).getName();
				if ((null != name) && !ICommonConstants.EMPTY_STRING.equals(name)) {
					result.add(capellaElement);
				}
			}
		}
		return result;
	}

	/** 
	 * Removes the non primitives classes from the given list
	 * @param elements_p the list to handle
	 * @return the processed list
	 */
	protected List<CapellaElement> removeNonPrimitiveClasses(List<CapellaElement> elements_p) {
		return removePrimitiveOrNonPrimitiveClasses(elements_p, false);
	}

	/** 
	 * Removes the non primitives Collections from the given list
	 * @param elements_p the list to handle
	 * @return the processed list
	 */
	protected List<CapellaElement> removeNonPrimitiveCollections(List<CapellaElement> elements_p) {
		return removePrimitiveOrNonPrimitiveCollections(elements_p, false);
	}

	/** 
	 * Allows to remove primitive or non primitive classes from a list
	 * @param elements_p the list
	 * @param removePrimitive_p <code>true</code> if you want to remove the primitive classes, <code>false</code> if you want to remove the non primitive classes
	 * @return the processed list
	 */
	protected List<CapellaElement> removePrimitiveOrNonPrimitiveClasses(List<CapellaElement> elements_p, boolean removePrimitive_p) {
		List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
		for (CapellaElement element : elements_p) {
			if (element instanceof Class) {
				Class currentClass = (Class) element;
				if ((!removePrimitive_p && currentClass.isIsPrimitive()) || (removePrimitive_p && !currentClass.isIsPrimitive())) {
					returnValue.add(currentClass);
				}
			} else {
				returnValue.add(element);
			}
		}
		return returnValue;
	}

	/** 
	 * Allows to remove primitive or non primitive Collections from a list
	 * @param elements_p the list
	 * @param removePrimitive_p <code>true</code> if you want to remove the primitive Collections, <code>false</code> if you want to remove the non primitive
	 * Collections
	 * @return the processed list
	 */
	protected List<CapellaElement> removePrimitiveOrNonPrimitiveCollections(List<CapellaElement> elements_p, boolean removePrimitive_p) {
		List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
		for (CapellaElement element : elements_p) {
			if (element instanceof Collection) {
				Collection currentCollection = (Collection) element;
				if ((!removePrimitive_p && currentCollection.isIsPrimitive()) || (removePrimitive_p && !currentCollection.isIsPrimitive())) {
					returnValue.add(currentCollection);
				}
			} else {
				returnValue.add(element);
			}
		}
		return returnValue;
	}

	/** 
	 * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,org.polarsys.capella.core.data.information.datatype.DataType)
	 */
	public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
		List<CapellaElement> returnValue = CapellaElementsHelperForBusinessQueries.getApplicableValuesForCardinalitiesInLevel(dataPkg_p);
		returnValue.addAll(CapellaElementsHelperForBusinessQueries.getApplicablePropertiesForCardinalitiesInLevel(dataPkg_p));
		return returnValue;
	}

}