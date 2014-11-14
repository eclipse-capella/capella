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
package org.polarsys.capella.core.business.queries.la;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 */
public class CapabilityRealization_ComponentCapabilityRealization implements IBusinessQuery {

  /**
   * @param arch_p
   * @return
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch_p, true);
    while (allContents.hasNext()) {
      Object object = allContents.next();
      if ((object instanceof SystemComponent)
       && !(object instanceof PhysicalActor))
      {
        availableElements.add((CapellaElement) object);
      }
    }

    return availableElements;
  }

  /**
   *  same level visibility layer
   *  @param ele_p
   */
  private List<CapellaElement> getRule_MQRY_CapabilityRealization_AvailableActors(CapabilityRealization ele_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(ele_p);
    if (currentBlockArchitecture != null) {
      availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture));
    }

    // remove existing from the availableElements
    for (CapellaElement element : getCurrentElements(ele_p, false)) {
      availableElements.remove(element);
    }

    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (element_p instanceof CapabilityRealization) {
      availableElements.addAll(getRule_MQRY_CapabilityRealization_AvailableActors((CapabilityRealization) element_p));
    }

    return availableElements;
	}

  /**
   * {@inheritDoc}
   */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof CapabilityRealization) {
		  CapabilityRealization capabilityRealization = (CapabilityRealization) element_p;
      for (SystemComponentCapabilityRealizationInvolvement cpntReal : capabilityRealization.getOwnedSystemComponentCapabilityRealizations()) {
        InvolvedElement involved = cpntReal.getInvolved();
        if (null != involved) {
          currentElements.add(involved);
        }
      }
		}
		return currentElements;
	}

  /**
   * {@inheritDoc}
   */
	public EClass getEClass() {
		return LaPackage.Literals.CAPABILITY_REALIZATION;
	}

  /**
   * {@inheritDoc}
   */
	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(LaPackage.Literals.CAPABILITY_REALIZATION__OWNED_SYSTEM_COMPONENT_CAPABILITY_REALIZATIONS);
	}
}
