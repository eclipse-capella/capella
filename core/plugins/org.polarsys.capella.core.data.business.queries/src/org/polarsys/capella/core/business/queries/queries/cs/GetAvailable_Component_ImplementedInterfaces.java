/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Component_ImplementedInterfaces extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets all the interfaces implemented by...
	 * </p>
	 * <p>
	 * The owner logical component of the current one.(Refer
	 * MQRY_LogicalComponent_UsedInterfaces_11).
	 * </p>
	 * <p>
	 * All the interfaces contained by the interface package (and sub packages)
	 * of the current logical component's parent (Refer
	 * MQRY_LogicalComponent_UsedInterfaces_12).
	 * </p>
	 * <p>
	 * All the interfaces contained by the interface package(sub packages) of
	 * the current LC's parent hierarchy((Refer
	 * MQRY_LogicalComponent_UsedInterfaces_13).
	 * </p>
	 * <p>
	 * All the interfaces used/implemented by the same level LCs((Refer
	 * MQRY_LogicalComponent_UsedInterfaces_14).
	 * </p>
	 * <p>
	 * Except the interfaces that are already used by the current LC(Refer
	 * MQRY_LogicalComponent_UsedInterfaces_1Ex1)
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		List<EObject> availableElements = new ArrayList<EObject>();
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof Component) {
			Component currentLC = (Component) element;
			availableElements.addAll(getRule_MQRY_LC_ImplInterfaces_11(currentLC));
			availableElements.addAll(getRule_MQRY_LC_ImplInterfaces_12(currentLC));
			availableElements.addAll(getRule_MQRY_LC_ImplInterfaces_13(currentLC));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element);
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets all the interfaces implemented by...
	 * </p>
	 * <p>
	 * The owner logical component of the current one.(Refer
	 * MQRY_LogicalComponent_ImplInterfaces_11).
	 * </p>
	 * @param currentLCthe current Logical Component
	 * @param parentLCthe owner Logical Component
	 * @return list of interfaces
	 */
	private List<CapellaElement> getRule_MQRY_LC_ImplInterfaces_11(Component currentLC) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (Component component : ComponentExt.getDirectParents(currentLC)) {
			availableElements.addAll(ComponentExt.getImplementedInterfacesFiltered(component, currentLC));
		}
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets all the interfaces contained by the interface package (and sub
	 * packages) of the current logical component's parent (Refer
	 * MQRY_LogicalComponent_ImplInterfaces_12).
	 * </p>
	 * @param currentLCthe current Logical Component
	 * @param parentLCthe owner Logical Component
	 * @return list of interfaces
	 */
	private List<CapellaElement> getRule_MQRY_LC_ImplInterfaces_12(Component currentLC) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		Object parent = currentLC.eContainer();
		if (null != parent) {
			if (parent instanceof Component) {
				availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(((Component) parent).getOwnedInterfacePkg(), currentLC, false));
			} else if (parent instanceof BlockArchitecture) {
				availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(((BlockArchitecture) parent).getOwnedInterfacePkg(), currentLC, false));
			}
		}
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets all the interfaces contained by the interface package(sub packages)
	 * of the current LC's parent hierarchy((Refer
	 * MQRY_LogicalComponent_ImplInterfaces_13).
	 * </p>
	 * @param currentLCthe current Logical Component
	 * @param parentLCthe parent Logical Component
	 * @return list of interfaces
	 */
	private List<CapellaElement> getRule_MQRY_LC_ImplInterfaces_13(Component currentLC) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (Interface inter : ComponentExt.getInterfacesFromComponentParentHierarchy(currentLC)) {
			if (ComponentExt.isImplementingInterface(currentLC, inter)) {
				continue;
			}
			availableElements.add(inter);
		}
		return availableElements;
	}

}