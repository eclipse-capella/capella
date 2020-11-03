/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.fa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

/**
 */
public class ComponentExchange_Target implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  @Override
  public EClass getEClass() {
    return FaPackage.Literals.COMPONENT_EXCHANGE;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET);
  }

  @Override
  public List<EObject> getAvailableElements(EObject element) {
    List<EObject> result = new ArrayList<EObject>();
    BlockArchitecture archi = BlockArchitectureExt.getRootBlockArchitecture(element);
    for (Iterator<EObject> it = archi.eAllContents(); it.hasNext();) {
      EObject next = it.next();
      if (next instanceof InformationsExchanger) {
        result.add(next);
      }
    }
    return result;
  }

  @Override
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    ComponentExchange ce = (ComponentExchange) element;
    if (ce.getTarget() != null) {
      return Collections.<EObject> singletonList(ce.getTarget());
    }
    return Collections.emptyList();
  }
}
