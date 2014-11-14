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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.PhysicalPortRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class PhysicalPort_RealizedPhysicalPorts implements IBusinessQuery {

	/**
	 * @param element_p the physical ports
	 * @return list of physical ports
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalPort_RealizedPhysicalPorts_11(PhysicalPort element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<EObject> allPhysicalPorts = new ArrayList<EObject>();

		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
	  for (BlockArchitecture block : arch.getAllocatedArchitectures()) {
	    allPhysicalPorts.addAll(EObjectExt.getAll(block, CsPackage.Literals.PHYSICAL_PORT));
	  }

		if (null != element_p) {
      EList<PhysicalPortRealization> ownedPhysicalPortRealisations = element_p.getOwnedPhysicalPortRealizations();
      for (PhysicalPortRealization ownedPhysicalPortRealisation : ownedPhysicalPortRealisations) {
        TraceableElement targetElement = ownedPhysicalPortRealisation.getTargetElement();
        if (null != targetElement) {
          if (allPhysicalPorts.contains(targetElement)) {
            allPhysicalPorts.remove(targetElement);
          } 
        } 
      }
    }

		for (EObject function : allPhysicalPorts) {
		  availableElements.add((CapellaElement) function);
    }
		
		return availableElements;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof PhysicalPort) {
			availableElements.addAll(getRule_MQRY_PhysicalPort_RealizedPhysicalPorts_11((PhysicalPort) element_p));
		} 
		return availableElements;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (element_p instanceof PhysicalPort) {
      for (PhysicalPortRealization ownedPhysicalPortRealization : ((PhysicalPort) element_p).getOwnedPhysicalPortRealizations()) {
        TraceableElement targetElement = ownedPhysicalPortRealization.getTargetElement();
        if (targetElement instanceof PhysicalPort) {
          currentElements.add((PhysicalPort) targetElement);            
        }
      }
		}

		return currentElements;
	}

  /**
   * {@inheritDoc}
   */
	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CsPackage.Literals.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS);
	}

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getEClass() {
    return CsPackage.Literals.PHYSICAL_PORT;
  }
}
