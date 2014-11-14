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
package org.polarsys.capella.core.business.queries.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class InterfaceUse_UsedInterface implements IBusinessQuery {

  /**
   * All the Interface contained by the Root Interface Package (and all of its subPackages).
   * And also of the current Element's Parent (can be a Component, a BlockArchitecture Decomposition Package
   * , or a BlockArchitecture root package) 
   * Exclude all the current Element's 'Implemented Interfaces'
   * @param currentInterfaceUse
   * @param systemEngineering
   * @return List of Interfaces
   */
	private List<CapellaElement> getRule_MQRY_InterfaceUse_UsedInterface_11(InterfaceUse currentInterfaceUse, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		Interface usedInterface = currentInterfaceUse.getUsedInterface();
		
		BlockArchitecture rootBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(currentInterfaceUse);  
		InterfacePkg interfacePkg = rootBlockArchitecture.getOwnedInterfacePkg();
		
		if (interfacePkg != null) {
			for (Interface inter : InterfacePkgExt.getAllInterfaces(interfacePkg)) {
				if (inter != null){
				  if (null != usedInterface) {
            if (inter.equals(usedInterface)) {
              availableElements.add(inter);
            }
          }else{
            availableElements.add(inter);
          }
				}
			}
		}
		
		EObject container = currentInterfaceUse.eContainer();
		if (container != null && container instanceof Component) {
	    Component parentComponent = (Component) container;
	    if (parentComponent != null) {
	      for (Interface inter : InterfacePkgExt.getAllInterfaces(parentComponent.getOwnedInterfacePkg())) {
	        if (inter != null){
	          if (null != usedInterface) {
	            if (inter.equals(usedInterface)) {
	              availableElements.add(inter);
	            }
	          }else{
	            availableElements.add(inter);
	          }
	        }
	      }
	    }      
    }

    
		return availableElements;
	}


	/**
	 * <p>
	 * Gets all the Interfaces contained by the Interface Package (and all of
	 * its subPackages) of the current Element's parent (can be a 
	 * Component, a Block Architecture root package or the SystemEngineering
	 * package).
	 * </p>
	 * <p>
	 * Gets all the Interfaces contained by the Interface Package (and all of
	 * its subPackages) of the current Element's parents hierarchy.
	 * </p>
	 * <p>
	 * Except the current Interface itself and interfaces in the inheritance
	 * hierarchy of the current Interface
	 * </p>
	 * <p>
	 * Refer MQRY_Interface_Inherited_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (element_p instanceof InterfaceUse) {
		  InterfaceUse implementedInterface = (InterfaceUse) element_p;
				availableElements.addAll(getRule_MQRY_InterfaceUse_UsedInterface_11(implementedInterface, systemEngineering));
		}

		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element_p);

		return availableElements;
	}

	/**
	 * <p>
	 * Gets usedInterface
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof InterfaceUse) {
		  InterfaceUse ele = (InterfaceUse) element_p;
		  currentElements.add(ele.getUsedInterface());
		  currentElements = ListExt.removeDuplicates(currentElements);
			currentElements.remove(ele);
		}
		return currentElements;
	}

	public EClass getEClass() {
		return CsPackage.Literals.INTERFACE_USE;
	}

	public List<EReference> getEStructuralFeatures() {
	  return Collections.singletonList(CsPackage.Literals.INTERFACE_USE__USED_INTERFACE);
	}
}
