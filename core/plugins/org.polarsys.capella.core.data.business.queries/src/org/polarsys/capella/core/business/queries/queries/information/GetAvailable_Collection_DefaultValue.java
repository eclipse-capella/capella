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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_Collection_DefaultValue extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> returnValue = new ArrayList<EObject>();
		BlockArchitecture currentBlockArchitecture = DataPkgExt.getRootBlockArchitecture(element);
		SystemEngineering systemEngineering = SystemEngineeringExt.getSystemEngineering(element);
		OperationalAnalysis operationalAnalysis = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
		returnValue.addAll(getDataFromLevel(operationalAnalysis, element));
		if (!(currentBlockArchitecture instanceof OperationalAnalysis)) {
			SystemAnalysis systemAnalysis = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
			returnValue.addAll(getDataFromLevel(systemAnalysis, element));
			if (!(currentBlockArchitecture instanceof SystemAnalysis)) {
				LogicalArchitecture logicalArchitecture = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
				returnValue.addAll(getDataFromLevel(logicalArchitecture, element));
				if (!(currentBlockArchitecture instanceof LogicalArchitecture)) {
					PhysicalArchitecture physicalArchitecture = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
					returnValue.addAll(getDataFromLevel(physicalArchitecture, element));
					if (!(currentBlockArchitecture instanceof PhysicalArchitecture)) {
						EPBSArchitecture epbsArchitecture = SystemEngineeringExt.getEPBSArchitecture((systemEngineering));
						returnValue.addAll(getDataFromLevel(epbsArchitecture, element));
					}
				}
			}
		}
		returnValue.addAll(getUnlevelizedData(element));
		returnValue.addAll(getDataFromComponentHierarchy(element));
		returnValue.addAll(getDataFromRealizedComponentsHierarchy(element));
		returnValue.addAll(getTypesFromComponentHierarchy(element));
		returnValue = filterUnNamedElements(returnValue);
		return returnValue;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
	 */
	public List<EReference> getEStructuralFeatures() {
		return Collections.singletonList(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE);
	}

	/** 
	 * This method purpose is to get the available data related to the given element in the given layer
	 * @param blockArchitecture the layer
	 * @param capellaElement the capella element
	 * @return the available elements
	 */
	public List<CapellaElement> getDataFromLevel(BlockArchitecture blockArchitecture, CapellaElement capellaElement) {
		if (null != blockArchitecture) {
			DataPkg dataPkg = blockArchitecture.getOwnedDataPkg();
			if (null != dataPkg) {
				return getDataFromLevel(dataPkg, capellaElement);
			}
		}
		return Collections.emptyList();
	}

	/** 
	 */
	protected List<CapellaElement> getDataFromComponentHierarchy(CapellaElement element) {
		List<CapellaElement> allDatas = new ArrayList<CapellaElement>();
		for (Component cpnt : CapellaElementExt.getComponentHierarchy(element)) {
			DataPkg dataPkg = cpnt.getOwnedDataPkg();
			if (null != dataPkg) {
				allDatas.addAll(getDataFromLevel(dataPkg, element));
			}
		}
		return allDatas;
	}

	/** 
	 */
	protected List<CapellaElement> getDataFromRealizedComponentsHierarchy(CapellaElement element) {
		List<CapellaElement> allDatas = new ArrayList<CapellaElement>();
		Component currentCpnt = (element instanceof Component) ? (Component) element : null;
		if (null == currentCpnt) {
			currentCpnt = (Component) EcoreUtil2.getFirstContainer(element, CsPackage.Literals.COMPONENT);
		}
		if (null != currentCpnt) {
			for (Component allocatedCpnt : currentCpnt.getRealizedComponents()) {
				List<Component> componentHierarchy = CapellaElementExt.getComponentHierarchy(allocatedCpnt);
				componentHierarchy.add(allocatedCpnt);
				for (Component cpnt : componentHierarchy) {
					DataPkg dataPkg = cpnt.getOwnedDataPkg();
					if (null != dataPkg) {
						for (CapellaElement data : getDataFromLevel(dataPkg, element)) {
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
	protected List<EObject> getTypesFromComponentHierarchy(CapellaElement element) {
		List<EObject> allDatas = new ArrayList<EObject>();
		for (Component cpnt : CapellaElementExt.getComponentHierarchy(element)) {
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
	 * @param list
	 * @return : List<CapellaElement>
	 */
	protected List<EObject> filterUnNamedElements(List<EObject> list) {
		List<EObject> result = new ArrayList<EObject>(1);
		for (EObject capellaElement : list) {
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
	 * @param elements the list to handle
	 * @return the processed list
	 */
	protected List<EObject> removeNonPrimitiveClasses(List<EObject> elements) {
		return removePrimitiveOrNonPrimitiveClasses(elements, false);
	}

	/** 
	 * Removes the non primitives Collections from the given list
	 * @param elements the list to handle
	 * @return the processed list
	 */
	protected List<EObject> removeNonPrimitiveCollections(List<EObject> elements) {
		return removePrimitiveOrNonPrimitiveCollections(elements, false);
	}

	/** 
	 * Allows to remove primitive or non primitive classes from a list
	 * @param elements the list
	 * @param removePrimitive <code>true</code> if you want to remove the primitive classes, <code>false</code> if you want to remove the non primitive classes
	 * @return the processed list
	 */
	protected List<EObject> removePrimitiveOrNonPrimitiveClasses(List<EObject> elements, boolean removePrimitive) {
		List<EObject> returnValue = new ArrayList<EObject>();
		for (EObject element : elements) {
			if (element instanceof Class) {
				Class currentClass = (Class) element;
				if ((!removePrimitive && currentClass.isIsPrimitive()) || (removePrimitive && !currentClass.isIsPrimitive())) {
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
	 * @param elements the list
	 * @param removePrimitive <code>true</code> if you want to remove the primitive Collections, <code>false</code> if you want to remove the non primitive
	 * Collections
	 * @return the processed list
	 */
	protected List<EObject> removePrimitiveOrNonPrimitiveCollections(List<EObject> elements, boolean removePrimitive) {
		List<EObject> returnValue = new ArrayList<EObject>();
		for (EObject element : elements) {
			if (element instanceof Collection) {
				Collection currentCollection = (Collection) element;
				if ((!removePrimitive && currentCollection.isIsPrimitive()) || (removePrimitive && !currentCollection.isIsPrimitive())) {
					returnValue.add(currentCollection);
				}
			} else {
				returnValue.add(element);
			}
		}
		return returnValue;
	}

	public List<CapellaElement> getUnlevelizedData(CapellaElement capellaElement) {
		List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
		if (capellaElement instanceof Property) {
			AbstractType type = ((Property) capellaElement).getAbstractType();
			if (type != null) {
				if (type instanceof Enumeration) {
					List<GeneralizableElement> rootSupertypes = GeneralizableElementExt.getRootSupertypes((Enumeration) type);
					rootSupertypes.add((GeneralizableElement) type);
					for (CapellaElement melElem : rootSupertypes) {
						if (melElem instanceof Enumeration) {
							Enumeration rootBooleanType = (Enumeration) melElem;
							returnValue.addAll(rootBooleanType.getOwnedLiterals());
						}
					}
				} else if (type instanceof BooleanType) {
					List<GeneralizableElement> rootSupertypes = GeneralizableElementExt.getRootSupertypes((BooleanType) type);
					rootSupertypes.add((GeneralizableElement) type);
					for (CapellaElement melElem : rootSupertypes) {
						if (melElem instanceof BooleanType) {
							BooleanType rootBooleanType = (BooleanType) melElem;
							returnValue.addAll(rootBooleanType.getOwnedLiterals());
						}
					}
				}
			}
		}
		return returnValue;
	}

	/** 
	 * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getDataFromLevel(DataPkg dataPkg, CapellaElement capellaElement) {
		List<CapellaElement> dataFromLevel = super_getDataFromLevel(dataPkg, capellaElement);
		if (capellaElement instanceof MultiplicityElement) {
			dataFromLevel.addAll(CapellaElementsHelperForBusinessQueries.getApplicableValuesForMultEleConsideringSuperGenElements(dataPkg,
					(MultiplicityElement) capellaElement, getEStructuralFeatures()));
		}
		return dataFromLevel;
	}
	
  public List<CapellaElement> super_getDataFromLevel(DataPkg dataPkg, CapellaElement capellaElement) {
    if (capellaElement instanceof MultiplicityElement) {

      List<CapellaElement> returnValue =
          CapellaElementsHelperForBusinessQueries.getStandardApplicableValuesForMultiplicityElementInLevel(dataPkg, (MultiplicityElement) capellaElement,
              getEStructuralFeatures());
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesWithTypeOf(dataPkg, capellaElement));
      return returnValue;
    }
    return Collections.emptyList();
  }


}