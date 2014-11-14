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
package org.polarsys.capella.core.business.queries.fa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * Return all the functional chains of allocated architecture of current architecture
 * 
 */
public class FunctionalChain_RealizedFunctionalChains implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    if (null != rootBlockArchitecture) {
      EList<BlockArchitecture> allocatedArchitectures = rootBlockArchitecture.getAllocatedArchitectures();
      for (BlockArchitecture blockArchitecture : allocatedArchitectures) {
        if (null != blockArchitecture) {
          List<FunctionalChain> allFunctionalChains = FunctionalChainExt.getAllFunctionalChains(blockArchitecture);
          if (!allFunctionalChains.isEmpty()) {
            availableElements.addAll(allFunctionalChains);
          }
        }
      }
    }

    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements.remove(element_p);

    return availableElements;
  }

  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof FunctionalChain) {
      FunctionalChain ele = (FunctionalChain) element_p;
      for (FunctionalChainRealization realization : ele.getOwnedFunctionalChainRealizations()) {
        currentElements.add((CapellaElement) realization.getTargetElement());
      }
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return FaPackage.Literals.FUNCTIONAL_CHAIN;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS);
  }

}
