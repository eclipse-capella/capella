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
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class PhysicalPath_RealizedPhysicalPaths implements IBusinessQuery {

	/**
	 * @param element_p the physical path
	 * @return list of physical paths
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalPath_RealizedPhysicalPaths_11(PhysicalPath element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<EObject> allPhysicalPaths = new ArrayList<EObject>();

		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
	  for (BlockArchitecture block : arch.getAllocatedArchitectures()) {
	    allPhysicalPaths.addAll(EObjectExt.getAll(block, CsPackage.Literals.PHYSICAL_PATH));
	  }

		if (null != element_p) {
      EList<PhysicalPathRealization> ownedPhysicalPathRealisations = element_p.getOwnedPhysicalPathRealizations();
      for (PhysicalPathRealization ownedPhysicalPathRealisation : ownedPhysicalPathRealisations) {
        TraceableElement targetElement = ownedPhysicalPathRealisation.getTargetElement();
        if (null != targetElement) {
          if (allPhysicalPaths.contains(targetElement)) {
            allPhysicalPaths.remove(targetElement);
          } 
        } 
      }
    }

		for (EObject function : allPhysicalPaths) {
		  availableElements.add((CapellaElement) function);
    }
		
		return availableElements;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof PhysicalPath) {
			availableElements.addAll(getRule_MQRY_PhysicalPath_RealizedPhysicalPaths_11((PhysicalPath) element_p));
		} 
		return availableElements;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (element_p instanceof PhysicalPath) {
      for (PhysicalPathRealization ownedPhysicalPathRealization : ((PhysicalPath) element_p).getOwnedPhysicalPathRealizations()) {
        TraceableElement targetElement = ownedPhysicalPathRealization.getTargetElement();
        if (targetElement instanceof PhysicalPath) {
          currentElements.add((PhysicalPath) targetElement);            
        }
      }
		}

		return currentElements;
	}

  /**
   * {@inheritDoc}
   */
	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_REALIZATIONS);
	}

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getEClass() {
    return CsPackage.Literals.PHYSICAL_PATH;
  }
}
