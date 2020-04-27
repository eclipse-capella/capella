/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.interaction;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.InteractionPackage;


public class AbstractCapabilityGeneralization_Super implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEClass()
   */
  @Override
  public EClass getEClass() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEStructuralFeatures()
   */
  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER);
  }

  @Override
  public List<EObject> getAvailableElements(EObject element) {
    AbstractCapabilityGeneralization acg = (AbstractCapabilityGeneralization) element;
    AbstractCapability sub = acg.getSub();
    if (sub != null) {
      IBusinessQuery delegate = BusinessQueriesProvider.getInstance().getContribution(sub.eClass(), InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUPER);
      if (delegate != null) {
        return delegate.getAvailableElements(sub);
      }
    }
    return Collections.emptyList();
  }

  @Override
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    return Collections.<EObject>singletonList(((AbstractCapabilityGeneralization) element).getSuper());
  }

}
