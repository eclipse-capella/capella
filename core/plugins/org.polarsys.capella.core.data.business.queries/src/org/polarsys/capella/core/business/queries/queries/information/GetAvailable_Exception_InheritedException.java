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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.Exception;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.ExceptionExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Exception_InheritedException extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
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
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
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
		if (element instanceof Exception) {
			Exception exception = (Exception) element;
			if (!isElementFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Exception_Inherited_11(exception));
				availableElements.addAll(getRule_MQRY_Exception_Inherited_12(exception));
				availableElements.addAll(getRule_MQRY_Exception_Inherited_13(exception));
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Exception_Inherited_11(Exception currentException) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		DataPkg exceptionPkg = ExceptionExt.getRootOwnerDataPkg(currentException);
		if (null != exceptionPkg) {
			for (Exception exception : exceptionPkg.getOwnedExceptions()) {
				if ((exception == null) || (exception.equals(currentException)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentException).contains(exception)
						&& !GeneralizableElementExt.getAllSuperGeneralizableElements(exception).contains(currentException)) {
					availableElements.add(exception);
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Exception_Inherited_12(Exception currentException) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		DataPkg exceptionPkg = ExceptionExt.getRootOwnerDataPkg(currentException);
		if (null != exceptionPkg) {
			ComponentArchitecture arch = DataPkgExt.getRootComponentArchitecture(exceptionPkg);
			if (null != arch) {
				DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
				if (null != dataPkg) {
					for (Exception exception : DataPkgExt.getAllExceptions(dataPkg)) {
						if ((exception == null) || (exception.equals(currentException)))
							continue;
						if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentException).contains(exception)
								&& !GeneralizableElementExt.getAllSuperGeneralizableElements(exception).contains(currentException)) {
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
							if ((exception == null) || (exception.equals(currentException)))
								continue;
							if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentException).contains(exception)
									&& !GeneralizableElementExt.getAllSuperGeneralizableElements(exception).contains(currentException)) {
								availableElements.add(exception);
							}
						}
					}
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Exception_Inherited_13(Exception currentException) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		DataPkg dataPkg = ExceptionExt.getRootOwnerDataPkg(currentException);
		if (null != dataPkg) {
			for (Exception exception : DataPkgExt.getExceptionsFromParentHierarchy(dataPkg)) {
				if ((exception == null) || (exception.equals(currentException)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentException).contains(exception)
						&& !GeneralizableElementExt.getAllSuperGeneralizableElements(exception).contains(currentException)) {
					availableElements.add(exception);
				}
			}
		}
		return availableElements;
	}

}