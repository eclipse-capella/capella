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
package org.polarsys.capella.core.business.queries.epbs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class ConfigurationItem_ImplementedInterfaces implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

	public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		if (element_p instanceof ConfigurationItem) {
			for (AbstractPhysicalArtifact physicalArtifact : ((ConfigurationItem) element_p).getAllocatedPhysicalArtifacts()) {
			  if (physicalArtifact instanceof PhysicalComponent) {
			    availableElements.addAll(((PhysicalComponent) physicalArtifact).getImplementedInterfaces());
			  }
			}
		} 
		availableElements = ListExt.removeDuplicates(availableElements);

		return availableElements;
	}

	public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof ConfigurationItem) {
			currentElements.addAll(ComponentExt.getImplementedInterfaces((ConfigurationItem) element_p));
		}
		return currentElements;
	}

	public EClass getEClass() {
		return EpbsPackage.Literals.CONFIGURATION_ITEM;
	}

	public List<EReference> getEStructuralFeatures() {
	    List<EReference> list = new ArrayList<EReference>(2);
	    list.add(CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES);
	    list.add(CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
	    return list;
	}

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
	  return RefactorDebugger.callAndTestQuery("GetAvailable_ConfigurationItem_ImplementedInterfaces__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
	  return RefactorDebugger.callAndTestQuery("GetCurrent_ConfigurationItem_ImplementedInterfaces", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
	}
}
