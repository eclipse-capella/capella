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
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class ExchangeItemElement_Type implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

  /**
   * 
   */
  private List<CapellaElement> getRule_MQRY_Parameter_Type_12(ExchangeItemElement currentParameter_p) {
    List<CapellaElement> allAllocatedDatas = new ArrayList<CapellaElement>();
    
    for (Component cpnt : CapellaElementExt.getComponentHierarchy(currentParameter_p)) {
      DataPkg dataPkg = cpnt.getOwnedDataPkg();
      if (null != dataPkg) {
        allAllocatedDatas.addAll(DataPkgExt.getAllTypesFromDataPkg(dataPkg));
      }
    }
    
    return allAllocatedDatas;
  }

	/*
	 * GetsAll the Classes, Signals and DataTypes contained by the Data Package
	 * (and all of its sub-packages) of the current Element's parent (can be a
	 * Component, a Block Architecture Decomposition package, or a Block
	 * Architecture root package).
	 */
	private List<CapellaElement> getRule_MQRY_Parameter_Type_11(ExchangeItemElement currenParameter_p) {
	  List<CapellaElement> allAllocatedDatas = new ArrayList<CapellaElement>();
	  
	  BlockArchitecture block = ComponentExt.getRootBlockArchitecture(currenParameter_p);
	  
	  for (BlockArchitecture current : BlockArchitectureExt.getAllAllocatedArchitectures(block)) {
	    DataPkg pkg = DataPkgExt.getDataPkgOfBlockArchitecture(current);
	    allAllocatedDatas.addAll(DataPkgExt.getAllTypesFromDataPkg(pkg));
	  }
	  
		return allAllocatedDatas;
	}

	/*
	 * All the Classes, Signals and DataTypes contained by the Data Package (and
	 * all of its sub-packages) of the Shared Assets Package.
	 */
	private List<CapellaElement> getRule_MQRY_Parameter_Type_15(ExchangeItemElement currenParameter_p, SystemEngineering systemEngineering_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

		AbstractType type = currenParameter_p.getType();
		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering_p)) {
			DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
			if (dataPkg != null) {
				for (CapellaElement element : DataPkgExt.getAllTypesFromDataPkgForPropsNParams(dataPkg)) {
					if (element.equals(type))
						continue;
					availableElements.add(element);
				}
			}
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				for (CapellaElement element : GenericPkgExt.getAllDataTypes(pkg)) {
					if (element.equals(type))
						continue;
					availableElements.add(element);
				}
				for (CapellaElement element : GenericPkgExt.getAllClasses(pkg)) {
					if (element.equals(type))
						continue;
					availableElements.add(element);
				}
			}
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Get all datatypes available in the given block architecture and its allocated architectures
	 * </p>
	 * <p>
	 * Except The current type itself
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		boolean isParameterFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
			if (sharedPkg != null) {
			  for (ReuseLink link : sharedPkg.getReuseLinks()) {
			    if (SystemEngineeringExt.getSystemEngineering(link) != null) {
			      systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
			      isParameterFromSharedPkg = true;
			      break;
			    }
			  }
			}
			if (systemEngineering == null)
				return availableElements;
		}

		if (element_p instanceof ExchangeItemElement) {
		  ExchangeItemElement parameter = (ExchangeItemElement) element_p;
			
			if (!isParameterFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Parameter_Type_11(parameter));
        availableElements.addAll(getRule_MQRY_Parameter_Type_12(parameter));
			}
			availableElements.addAll(getRule_MQRY_Parameter_Type_15(parameter, systemEngineering));
		}
		
		availableElements = ListExt.removeDuplicates(availableElements);
		
		if (element_p instanceof ExchangeItemElement) {
      ExchangeItemElement parameter = (ExchangeItemElement) element_p;
      availableElements.remove(parameter.getType());
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets the type of current Parameter
	 * </p>
	 * <p>
	 * Refer MQRY_Parameter_Type_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

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

		if (element_p instanceof ExchangeItemElement) {
		  ExchangeItemElement parameter = (ExchangeItemElement) element_p;
			AbstractType type = parameter.getType();
			if (null != type)
				currentElements.add((CapellaElement) type);
		}
		return currentElements;
	}

	public EClass getEClass() {
		return InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT;
	}

	public List<EReference> getEStructuralFeatures() {
    List<EReference> list = new ArrayList<EReference>(2);
    list.add(CapellacorePackage.Literals.TYPED_ELEMENT__TYPE);
    list.add(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
    return list;
	}

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
	  return RefactorDebugger.callAndTestQuery("GetAvailable_ExchangeItemElement_Type__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
	  return RefactorDebugger.callAndTestQuery("GetCurrent_ExchangeItemElement_Type", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
	}
}
