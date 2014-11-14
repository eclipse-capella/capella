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

import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.ClassExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public abstract class AbstractClassInheritedClasses implements IBusinessQuery {
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

	/**
	 * All the Classes contained by the Shared Package (and all of its
	 * sub-packages).
	 */
	private List<CapellaElement> getRule_MQRY_Class_Inherited_14(Class currentClass, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering)) {
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				for (Class clazz : GenericPkgExt.getAllClasses(pkg)) {
					if ((!currentClass.isIsPrimitive() && clazz.isIsPrimitive()) || (currentClass.isIsPrimitive() && !clazz.isIsPrimitive()) || (clazz == null) || (clazz.equals(currentClass)))
						continue;
					if (!GeneralizableElementExt.getAllSuperGeneralizableElements(clazz).contains(currentClass)) {
						availableElements.add(clazz);
					}
				}
			}

			if (sharedPkg.getOwnedDataPkg() != null) {
				if (sharedPkg.getOwnedDataPkg() != null) {
					for (Class clazz : DataPkgExt.getAllClasses(sharedPkg.getOwnedDataPkg())) {
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
	 * <p>
	 * Gets all the classes contained by the class package (and all sub
	 * packages) of the current element.
	 * </p>
	 * <p>
	 * Gets all the classes contained by the class package (and all sub
	 * packages) of the current element's parent.
	 * </p>
	 * <p>
	 * Gets all the classes contained by the class package (and all sub
	 * packages) of the current element's parent hierarchy.
	 * </p>
	 * <p>
	 * Gets all the classes contained by the class package (and all sub
	 * packages) of the SharedPkg.
	 * </p>
	 * <p>
	 * Except the current class and the classes in the inheritance hierarchy of
	 * the current class
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAbstractAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		boolean isClassFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
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

		if (element_p instanceof Class) {
			if (!isClassFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Class_Inherited_11((Class) element_p, systemEngineering));
				availableElements.addAll(getRule_MQRY_Class_Inherited_12((Class) element_p, systemEngineering));
				availableElements.addAll(getRule_MQRY_Class_Inherited_13((Class) element_p, systemEngineering));
			}
			availableElements.addAll(getRule_MQRY_Class_Inherited_14((Class) element_p, systemEngineering));
		}

		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element_p);

		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the classes in the inheritance hierarchy of the current class
	 * </p>
	 * <p>
	 * Except the current class
	 * </p>
	 * <p>
	 * Refer MQRY_Class_Inherited_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
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

		if (element_p instanceof Class) {
			Class currentClass = (Class) element_p;
			currentElements.addAll(currentClass.getSuper());
			currentElements = ListExt.removeDuplicates(currentElements);
			currentElements.remove(currentClass);
		}
		return currentElements;
	}


	public List<EReference> getEStructuralFeatures() {
    List<EReference> list = new ArrayList<EReference>(1);
    list.add(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS);
    list.add(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS);
    
    return list;
	}
}
