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
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.Exception;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.ExceptionExt;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class Exception_InheritedException implements IBusinessQuery {

	/*
	 * All the Exceptions contained by the Exception Package (and all of its
	 * sub-packages) of the current Element.
	 */
	private List<CapellaElement> getRule_MQRY_Exception_Inherited_11(Exception currentException_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		DataPkg exceptionPkg = ExceptionExt.getRootOwnerDataPkg(currentException_p);
		if (null != exceptionPkg) {
			for (Exception exception : exceptionPkg.getOwnedExceptions()) {
				if ((exception == null) || (exception.equals(currentException_p)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentException_p).contains(exception)
						&& !GeneralizableElementExt.getAllSuperGeneralizableElements(exception).contains(currentException_p)) {
					availableElements.add(exception);
				}
			}
		}
		return availableElements;
	}

	/*
	 * All the Exceptions contained by the Exception Package (and all of its
	 * sub-packages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 */
	private List<CapellaElement> getRule_MQRY_Exception_Inherited_12(Exception currentException_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		DataPkg exceptionPkg = ExceptionExt.getRootOwnerDataPkg(currentException_p);
		if (null != exceptionPkg) {
			ComponentArchitecture arch = DataPkgExt.getRootComponentArchitecture(exceptionPkg);
			if (null != arch) {
				DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
				if (null != dataPkg) {
					for (Exception exception : DataPkgExt.getAllExceptions(dataPkg)) {
						if ((exception == null) || (exception.equals(currentException_p)))
							continue;
						if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentException_p).contains(exception)
								&& !GeneralizableElementExt.getAllSuperGeneralizableElements(exception).contains(currentException_p)) {
							availableElements.add(exception);
						}
					}
				}
			}

			Component comp = DataPkgExt.getRootComponent(exceptionPkg);
			if (null != comp) {
				if (comp instanceof LogicalComponent) {
					DataPkg dataPkg = ((LogicalComponent) comp).getOwnedDataPkg();
					if (null != dataPkg) {
						for (Exception exception : DataPkgExt.getAllExceptions(dataPkg)) {
							if ((exception == null) || (exception.equals(currentException_p)))
								continue;
							if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentException_p).contains(exception)
									&& !GeneralizableElementExt.getAllSuperGeneralizableElements(exception).contains(currentException_p)) {
								availableElements.add(exception);
							}
						}
					}
				}
			}
		}
		return availableElements;
	}

	/*
	 * All the Exceptions contained by the Exception Package (and all of its
	 * sub-packages) of the current Element's parents hierarchy.
	 */
	private List<CapellaElement> getRule_MQRY_Exception_Inherited_13(Exception currentException_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		DataPkg dataPkg = ExceptionExt.getRootOwnerDataPkg(currentException_p);
		if (null != dataPkg) {
			for (Exception exception : DataPkgExt.getExceptionsFromParentHierarchy(dataPkg)) {
				if ((exception == null) || (exception.equals(currentException_p)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentException_p).contains(exception)
						&& !GeneralizableElementExt.getAllSuperGeneralizableElements(exception).contains(currentException_p)) {
					availableElements.add(exception);
				}
			}
		}
		return availableElements;
	}

	/*
	 * All the Exceptions contained by the Shared package (and all of its
	 * sub-packages).
	 */
	private List<CapellaElement> getRule_MQRY_Exception_Inherited_14(Exception currentException_p, SystemEngineering systemEngineering_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering_p)) {
			DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
			if (null != dataPkg) {
				for (Exception exception : DataPkgExt.getAllExceptions(dataPkg)) {
					if ((exception == null) || (exception.equals(currentException_p)))
						continue;
					if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentException_p).contains(exception)
							&& !GeneralizableElementExt.getAllSuperGeneralizableElements(exception).contains(currentException_p)) {
						availableElements.add(exception);
					}
				}
			}

			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				for (Exception exception : GenericPkgExt.getAllExceptions(pkg)) {
					if ((exception == null) || (exception.equals(currentException_p)))
						continue;
					if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentException_p).contains(exception)
							&& !GeneralizableElementExt.getAllSuperGeneralizableElements(exception).contains(currentException_p)) {
						availableElements.add(exception);
					}
				}
			}
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets...
	 * </p>
	 * <p>
	 * All the Exceptions contained by the Exception Package (and all of its
	 * sub-packages) of the current Element.
	 * </p>
	 * <p>
	 * All the Exceptions contained by the Exception Package (and all of its
	 * sub-packages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 * </p>
	 * <p>
	 * All the Exceptions contained by the Exception Package (and all of its
	 * sub-packages) of the current Element's parents hierarchy.
	 * </p>
	 * <p>
	 * All the Exceptions contained by the Shared package (and all of its
	 * sub-packages).
	 * </p>
	 * <p>
	 * Except The current Exception itself and The Exceptions in the inheritance
	 * hierarchy of the current Exception
	 * </p>
	 * <p>
	 * See rule MQRY_Exception_Inherited_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
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

		if (element_p instanceof Exception) {
			Exception exception = (Exception) element_p;
			if (!isElementFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Exception_Inherited_11(exception));
				availableElements.addAll(getRule_MQRY_Exception_Inherited_12(exception));
				availableElements.addAll(getRule_MQRY_Exception_Inherited_13(exception));
			}
			availableElements.addAll(getRule_MQRY_Exception_Inherited_14(exception, systemEngineering));
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

		if (element_p instanceof Exception) {
			Exception exception = (Exception) element_p;
			currentElements.addAll(exception.getSuper());
			currentElements = ListExt.removeDuplicates(currentElements);
			currentElements.remove(exception);
		}
		return currentElements;
	}

	public EClass getEClass() {
		return CommunicationPackage.Literals.EXCEPTION;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS);
	}
}
