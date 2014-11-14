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
package org.polarsys.capella.core.data.fa.properties.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.tools.internal.graphical.edit.policies.DeleteHelper;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class ComponentExchange_AllocatedFunctionalExchangesController extends AbstractMultipleSemanticFieldController {
  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.AbstractMultipleSemanticFieldController#getReadOpenValuesQuery(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(),
        FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.AbstractMultipleSemanticFieldController#loadValues(org.polarsys.capella.core.data.capellacore.CapellaElement, org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public List<EObject> loadValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    List<EObject> values = new ArrayList<EObject>();

    Object lst = semanticElement_p.eGet(semanticFeature_p);
    if (lst instanceof Collection<?>) {
      for (Object obj : (Collection<?>) lst) {
        if (obj instanceof ComponentExchangeFunctionalExchangeAllocation) {
          values.add(((ComponentExchangeFunctionalExchangeAllocation) obj).getTargetElement());
        }
      }
    }

    return values;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.AbstractMultipleSemanticFieldController#doAddOperationInWriteOpenValues(org.polarsys.capella.core.data.capellacore.CapellaElement, org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EObject)
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doAddOperationInWriteOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, EObject object_p) {
    ComponentExchangeFunctionalExchangeAllocation link = FaFactory.eINSTANCE.createComponentExchangeFunctionalExchangeAllocation();
    link.setSourceElement(semanticElement_p);
    link.setTargetElement((TraceableElement) object_p);
    ((List<EObject>) semanticElement_p.eGet(semanticFeature_p)).add(link);

    if (CapellaModelPreferencesPlugin.getDefault().isSynchronizationOfComponentPortToFunctionPortAllowed() && (semanticElement_p instanceof ComponentExchange)
        && (object_p instanceof FunctionalExchange)) {
      ComponentExchangeExt.synchronizePortAllocations((ComponentExchange) semanticElement_p, (FunctionalExchange) object_p);
    }
  }

  /**
   * Do the remove operation in {@link #writeOpenValues(CapellaElement, EStructuralFeature, List)}
   * The synchronization of the delegations/allocations is now managed by {@link DeleteHelper} class
   * @param semanticElement_p
   * @param semanticFeature_p
   * @param object_p
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doRemoveOperationInWriteOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, EObject object_p) {
    EObject linkToRemove = null;
    for (EObject obj : (List<EObject>) semanticElement_p.eGet(semanticFeature_p)) {
      if ((obj instanceof ComponentExchangeFunctionalExchangeAllocation)
          && ((ComponentExchangeFunctionalExchangeAllocation) obj).getTargetElement().equals(object_p)) {
        linkToRemove = obj;
      }
    }
    if (linkToRemove != null) {
      super.doRemoveOperationInWriteOpenValues(semanticElement_p, semanticFeature_p, linkToRemove);
    }
  }
}
