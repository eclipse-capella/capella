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
package org.polarsys.capella.core.business.queries.oa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 */
public class OperationalCapability_InvolvedEntities implements IBusinessQuery {

  /**
   * @param arch_p
   * @return
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch_p, true);
    while (allContents.hasNext()) {
      Object object = allContents.next();
      if (object instanceof Entity) {
        availableElements.add((CapellaElement) object);
      }
    }

    return availableElements;
  }

  /**
   *  same level visibility layer
   *  @param ele_p
   */
  private List<CapellaElement> getRule_MQRY_OperationalCapability_AvailableEntities(OperationalCapability ele_p) {
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

    if (element_p instanceof OperationalCapability) {
      availableElements.addAll(getRule_MQRY_OperationalCapability_AvailableEntities((OperationalCapability) element_p));
    }

    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (element_p instanceof OperationalCapability) {
      OperationalCapability ele = (OperationalCapability) element_p;
      for (EntityOperationalCapabilityInvolvement inv : ele.getOwnedEntityOperationalCapabilityInvolvements()) {
        Entity entity = inv.getEntity();
        if (null != entity) {
          currentElements.add(entity);
        }
      }
    }
    return currentElements;
  }

  /**
   * {@inheritDoc}
   */
  public EClass getEClass() {
    return OaPackage.Literals.OPERATIONAL_CAPABILITY;
  }

  /**
   * {@inheritDoc}
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(OaPackage.Literals.OPERATIONAL_CAPABILITY__OWNED_ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENTS);
  }
}
