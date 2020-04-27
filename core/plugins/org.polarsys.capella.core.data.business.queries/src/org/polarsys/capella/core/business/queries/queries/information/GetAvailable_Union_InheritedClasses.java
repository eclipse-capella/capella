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
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.ClassExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Union_InheritedClasses extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<EObject> abstractAvailableElements = getAbstractAvailableElements(element);
		if (!abstractAvailableElements.isEmpty()) {
			for (EObject capellaElement : abstractAvailableElements) {
				if (capellaElement instanceof Union) {
					availableElements.add((CapellaElement) capellaElement);
				}
			}
		}
		return availableElements;
	}
	
	public List<EObject> getAbstractAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		boolean isClassFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					isClassFromSharedPkg = true;
					break;
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}

		if (element instanceof Class) {
			if (!isClassFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Class_Inherited_11((Class) element, systemEngineering));
				availableElements.addAll(getRule_MQRY_Class_Inherited_12((Class) element, systemEngineering));
				availableElements.addAll(getRule_MQRY_Class_Inherited_13((Class) element, systemEngineering));
			}
		}

		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element);

		return availableElements;
	}
	
	/**
	 * All the Classes contained by the Class Package (and all of its
	 * sub-packages) of the current Element.
	 */
	private List<CapellaElement> getRule_MQRY_Class_Inherited_11(Class currentClass, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		DataPkg dataPkg = ClassExt.getRootOwnerDataPkg(currentClass);
		if (dataPkg != null) {
			for (Class clazz : DataPkgExt.getAllClasses(dataPkg)) {
		    if ((!currentClass.isIsPrimitive() && clazz.isIsPrimitive()) || (currentClass.isIsPrimitive() && !clazz.isIsPrimitive()) ||(clazz == null) || (clazz.equals(currentClass)))
          continue;
        if (!GeneralizableElementExt.getAllSuperGeneralizableElements(clazz).contains(currentClass)) {
          availableElements.add(clazz);
        }
			}
		}

		return availableElements;
	}

	/**
	 * All the Classes contained by the Class Package (and all of its
	 * sub-packages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 */
	private List<CapellaElement> getRule_MQRY_Class_Inherited_12(Class currentClass, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		DataPkg classPkg = ClassExt.getRootOwnerDataPkg(currentClass);
		if (classPkg != null) {
			BlockArchitecture parentBlockArchitecture = DataPkgExt.getRootBlockArchitecture(classPkg);
			if (parentBlockArchitecture != null) {
				DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(parentBlockArchitecture);
				if (null != dataPkg) {
					for (Class clazz : DataPkgExt.getAllClasses(dataPkg)) {
						if ((!currentClass.isIsPrimitive() && clazz.isIsPrimitive()) || (currentClass.isIsPrimitive() && !clazz.isIsPrimitive()) || (clazz == null) || (clazz.equals(currentClass)))
							continue;
						if (!GeneralizableElementExt.getAllSuperGeneralizableElements(clazz).contains(currentClass)) {
							availableElements.add(clazz);
						}
					}
				}
			}
			Component parentComponent = DataPkgExt.getRootComponent(classPkg);
			if (null != parentComponent) {
				DataPkg dataPkg = parentComponent.getOwnedDataPkg();
				if (null != dataPkg) {
					for (Class clazz : DataPkgExt.getAllClasses(dataPkg)) {
						if ((!currentClass.isIsPrimitive() && clazz.isIsPrimitive()) || (currentClass.isIsPrimitive() && !clazz.isIsPrimitive()) || (clazz == null) || (clazz.equals(currentClass)))
							continue;
						if (!GeneralizableElementExt.getAllSuperGeneralizableElements(clazz).contains(currentClass)) {
							availableElements.add(clazz);
						}
					}
				}
			}
		}
		return availableElements;
	}

	/**
	 * All the Classes contained by the Class Package (and all of its
	 * sub-packages) of the current Element's parents hierarchy.
	 */
	private List<CapellaElement> getRule_MQRY_Class_Inherited_13(Class currentClass, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		DataPkg classPkg = ClassExt.getRootOwnerDataPkg(currentClass);
		if (null != classPkg) {
			for (Class clazz : DataPkgExt.getClassesFromParentHierarchy(classPkg)) {
				if ((!currentClass.isIsPrimitive() && clazz.isIsPrimitive()) || (currentClass.isIsPrimitive() && !clazz.isIsPrimitive()) || (clazz == null) || (clazz.equals(currentClass)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(clazz).contains(currentClass)) {
					availableElements.add(clazz);
				}
			}
		}

		return availableElements;
	}


}