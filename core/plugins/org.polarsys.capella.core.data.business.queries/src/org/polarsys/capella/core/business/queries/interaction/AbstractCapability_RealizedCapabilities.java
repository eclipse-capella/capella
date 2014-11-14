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
package org.polarsys.capella.core.business.queries.interaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 */
public abstract class AbstractCapability_RealizedCapabilities implements IBusinessQuery {

  /**
   * @param arch_p
   * @return
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch_p, true);
    while (allContents.hasNext()) {
      Object object = allContents.next();
      if (object instanceof AbstractCapability) {
        availableElements.add((CapellaElement) object);
      }
    }

    return availableElements;
  }

  /**
   *  same level Visibility Layer
   */
  private List<CapellaElement> getRule_MQRY_AbstractCapability_AvailableCapabilities_11(AbstractCapability ele_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(ele_p);
    if (currentBlockArchitecture != null) {
      for (BlockArchitecture previousBlockArchitecture : BlockArchitectureExt.getPreviousBlockArchitecture(currentBlockArchitecture)) {
        availableElements.addAll(getElementsFromBlockArchitecture(previousBlockArchitecture));
      }
    }

    // remove existing from the availableElements
    for (CapellaElement element : getCurrentElements(ele_p, false)) {
      availableElements.remove(element);
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (element_p instanceof AbstractCapability) {
      availableElements.addAll(getRule_MQRY_AbstractCapability_AvailableCapabilities_11((AbstractCapability) element_p));
    }
    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (element_p instanceof AbstractCapability) {
      AbstractCapability ele = (AbstractCapability) element_p;
      for (AbstractCapabilityRealization realization : ele.getOwnedAbstractCapabilityRealizations()) {
        currentElements.add(realization.getRealizedCapability());
      }
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeature()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS);
  }

}
