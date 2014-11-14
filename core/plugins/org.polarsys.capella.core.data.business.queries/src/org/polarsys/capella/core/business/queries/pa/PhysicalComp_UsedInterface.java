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
package org.polarsys.capella.core.business.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class PhysicalComp_UsedInterface implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

	/**
	 * <p>
	 * Gets All the Interfaces contained in the Interface Package (and all of
	 * its sub-packages) of the Physical Architecture layer.
	 * </p>
	 * <p>
	 * Except The interfaces that are already used by the current Physical
	 * Component.
	 * </p>
	 * <p>
	 * Refer MQRY_ PhysicalComponent_UsedInterfaces_1
	 * </p>
	 * 
	 * @param systemEngineering_p
	 *            the {@link System}
	 * @param currentPC_p
	 *            the current {@link PhysicalComponent}
	 * @return list of interfaces
	 */
	private List<CapellaElement> getAllInterfacesInPhysicalArchitecture(SystemEngineering systemEngineering_p, PhysicalComponent currentPC_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

		availableElements.addAll(PhysicalArchitectureExt.getOwnedInterfacesFromPhysicalLayerFiltered(systemEngineering_p, currentPC_p, true));

    for (Component cpnt : CapellaElementExt.getComponentHierarchy(currentPC_p)) {
      InterfacePkg interfacePkg = cpnt.getOwnedInterfacePkg();
      if (null != interfacePkg) {
        availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(interfacePkg, currentPC_p, true));
      }
    }

		return availableElements;
	}

	private List<CapellaElement> getAllInterfacesOfLogicalLayer(SystemEngineering systemEngineering_p, PhysicalComponent currentEle_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		// get la
		LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering_p);
		// get all interfaces from the level
		availableElements.addAll(SystemEngineeringExt.getAllInterfaces(la));
		// remove current impl interfaces by currentEle_p
		availableElements.removeAll(getCurrentElements(currentEle_p, false));
		
		return availableElements;
	}
	
	
	/**
	 * <p>
	 * Gets All the Interfaces contained in the Interface Package (and all of
	 * its sub-packages) of the Physical Architecture layer.
	 * </p>
	 * <p>
	 * Except The interfaces that are already used by the current Physical
	 * Component.
	 * </p>
	 * <p>
	 * Refer MQRY_ PhysicalComponent_UsedInterfaces_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		if (null == systemEngineering) {
			return availableElements;
		}

		if (element_p instanceof PhysicalComponent) {
			PhysicalComponent currentPC = (PhysicalComponent) element_p;

			availableElements.addAll(getAllInterfacesInPhysicalArchitecture(systemEngineering, currentPC));
			
			// get used interfaces from allocated components
			EList<Component> lcs = currentPC.getAllocatedComponents();
			
			// if no allocated components
			// get all the interfaces from logical layer.
			if (lcs.isEmpty()) {
				// add all interfaces of logical layer
				availableElements.addAll(InterfaceExt.filterLCRealizedInterfaces(getAllInterfacesOfLogicalLayer(systemEngineering, currentPC)));
			}else{
				for (Component logicalComponent : lcs) {
					availableElements.addAll(InterfaceExt.filterLCRealizedInterfaces(logicalComponent.getUsedInterfaces()));
					availableElements.addAll(InterfaceExt.filterLCRealizedInterfaces(logicalComponent.getRequiredInterfaces()));
				}
			}
		}
		
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the interfaces that are used by the current Physical Component.
	 * </p>
	 * <p>
	 * Refer MQRY_ PhysicalComponent_UsedInterfaces_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof PhysicalComponent) {
		  PhysicalComponent currentPC = (PhysicalComponent) element_p;
		  // retrieve used interfaces of current physical component
		  currentElements.addAll(ComponentExt.getUsedInterfaces(currentPC));
		}

		return currentElements;
	}

	public EClass getEClass() {
		return PaPackage.Literals.PHYSICAL_COMPONENT;
	}

	public List<EReference> getEStructuralFeatures() {
	  List<EReference> list = new ArrayList<EReference>(1);
	  list.add(CsPackage.Literals.COMPONENT__USED_INTERFACES);
	  list.add(CsPackage.Literals.COMPONENT__OWNED_INTERFACE_USES);
    return list;
	}

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
	  return RefactorDebugger.callAndTestQuery("GetAvailable_PhysicalComp_UsedInterface__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
	  return RefactorDebugger.callAndTestQuery("GetCurrent_PhysicalComp_UsedInterface", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
	}
}
