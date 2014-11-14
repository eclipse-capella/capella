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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 * Query that returns all exchange items in the model from current and upper upper layers
 */
public class ExchangeItemAllocation_AllocatedItem implements IBusinessQuery {
  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(0);

    List<BlockArchitecture> rootAndPreviousBlockArchitectures = BlockArchitectureExt.getRootAndPreviousBlockArchitectures(element_p);
    for (BlockArchitecture blockArchitecture : rootAndPreviousBlockArchitectures) {
      for (EObject obj : EObjectExt.getAll(blockArchitecture, InformationPackage.Literals.EXCHANGE_ITEM)) {
        availableElements.add((CapellaElement) obj);
      }
    }
    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>(1);
    if (element_p instanceof ExchangeItemAllocation) {
      AbstractExchangeItem exchangeItem = ((ExchangeItemAllocation) element_p).getAllocatedItem();
      if (exchangeItem instanceof ExchangeItem) {
        currentElements.add((ExchangeItem) exchangeItem);
      }
    }
    return currentElements;
  }

  /**
   * {@inheritDoc}
   */
  public EClass getEClass() {
    return CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION;
  }

  /**
   * {@inheritDoc}
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM);
  }
}
