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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * Return allocated component exchanges (! kind DELEGATION) of given Physical Path
 */
public class PhysicalPath_AllocatedComponentExchanges implements IBusinessQuery {

  /**
   * @param element_p
   * @return
   */
  private List<CapellaElement> getRule_MQRY_PhysicalPath_AllocatedComponentExchanges_12(PhysicalPath element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    List<Component> terminalPCs = new ArrayList<Component>();

    for (PhysicalPathInvolvement first : PhysicalPathExt.getFlatFirstPhysicalPathInvolvments(element_p)) {
      AbstractPathInvolvedElement involvedElt = first.getInvolvedElement();
      if (involvedElt instanceof Part) {
        AbstractType pc = ((Part) involvedElt).getAbstractType();
        if ((pc instanceof Component) && !terminalPCs.contains(pc)) {
          terminalPCs.add((Component) pc);
        }
      }
    }
    for (PhysicalPathInvolvement last : PhysicalPathExt.getFlatLastPhysicalPathInvolvments(element_p)) {
      AbstractPathInvolvedElement involvedElt = last.getInvolvedElement();
      if (involvedElt instanceof Part) {
        AbstractType pc = ((Part) involvedElt).getAbstractType();
        if ((pc instanceof Component) && !terminalPCs.contains(pc)) {
          terminalPCs.add((Component) pc);
        }
      }
    }

    for (Component firstPC : terminalPCs) {
      for (Component lastPC : terminalPCs) {
        if (!firstPC.equals(lastPC)) {
          for (CapellaElement exchange : PhysicalLink_AllocatedComponentExchanges.getAvailableExchanges(firstPC, lastPC)) {
            if (!availableElements.contains(exchange)) {
              availableElements.add(exchange);
            }
          }
        }
      }
    }

    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (element_p instanceof PhysicalPath) {
      availableElements.addAll(getRule_MQRY_PhysicalPath_AllocatedComponentExchanges_12((PhysicalPath) element_p));
    }
    availableElements = ListExt.removeDuplicates(availableElements);

    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof PhysicalPath) {
      PhysicalPath ele = (PhysicalPath) element_p;
      List<ComponentExchange> allocatedComponentExchanges = ele.getAllocatedComponentExchanges();
      if (null != allocatedComponentExchanges) {
        currentElements.addAll(allocatedComponentExchanges);
      }
    }

    return currentElements;
  }

  /**
   * {@inheritDoc}
   */
  public EClass getEClass() {
    return CsPackage.Literals.PHYSICAL_PATH;
  }

  /**
   * {@inheritDoc}
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS);
  }
}
