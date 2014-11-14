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
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class PhysicalLink_RealizedPhysicalLinks implements IBusinessQuery {

	/**
	 * @param element_p the physical link
	 * @return list of physical links
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalLink_RealizedPhysicalLinks_11(PhysicalLink element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<EObject> allPhysicalLinks = new ArrayList<EObject>();

		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
	  for (BlockArchitecture block : arch.getAllocatedArchitectures()) {
	    allPhysicalLinks.addAll(EObjectExt.getAll(block, CsPackage.Literals.PHYSICAL_LINK));
	  }

		if (null != element_p) {
      EList<PhysicalLinkRealization> ownedPhysicalLinkRealisations = element_p.getOwnedPhysicalLinkRealizations();
      for (PhysicalLinkRealization ownedPhysicalLinkRealisation : ownedPhysicalLinkRealisations) {
        TraceableElement targetElement = ownedPhysicalLinkRealisation.getTargetElement();
        if (null != targetElement) {
          if (allPhysicalLinks.contains(targetElement)) {
            allPhysicalLinks.remove(targetElement);
          } 
        } 
      }
    }

		for (EObject function : allPhysicalLinks) {
		  availableElements.add((CapellaElement) function);
    }
		
		return availableElements;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof PhysicalLink) {
			availableElements.addAll(getRule_MQRY_PhysicalLink_RealizedPhysicalLinks_11((PhysicalLink) element_p));
		} 
		return availableElements;
	}

	/**
   * {@inheritDoc}
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (element_p instanceof PhysicalLink) {
      for (PhysicalLinkRealization ownedPhysicalLinkRealization : ((PhysicalLink) element_p).getOwnedPhysicalLinkRealizations()) {
        TraceableElement targetElement = ownedPhysicalLinkRealization.getTargetElement();
        if (targetElement instanceof PhysicalLink) {
          currentElements.add((PhysicalLink) targetElement);            
        }
      }
		}

		return currentElements;
	}

  /**
   * {@inheritDoc}
   */
	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CsPackage.Literals.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS);
	}

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getEClass() {
    return CsPackage.Literals.PHYSICAL_LINK;
  }
}
