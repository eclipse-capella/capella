/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class FunctionRealizationsController extends AbstractMultipleSemanticFieldController {
  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.AbstractMultipleSemanticFieldController#getReadOpenValuesQuery(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.AbstractMultipleSemanticFieldController#loadValues(org.polarsys.capella.core.data.capellacore.CapellaElement, org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public List<EObject> loadValues(CapellaElement semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> values = new ArrayList<EObject>();

    Object lst = semanticElement.eGet(semanticFeature);
    if (lst instanceof Collection<?>) {
      for (Object obj : (Collection<?>) lst) {
        if (obj instanceof FunctionRealization) {
          values.add(((FunctionRealization) obj).getAllocatedFunction());
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
  protected void doAddOperationInWriteOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature, EObject object) {
    FunctionRealization link = FaFactory.eINSTANCE.createFunctionRealization();
    link.setSourceElement(semanticElement);
    link.setTargetElement((TraceableElement) object);
    ((List<EObject>) semanticElement.eGet(semanticFeature)).add(link);
  }

  /**
   * Do the remove operation in {@link #writeOpenValues(CapellaElement, EStructuralFeature, List)}
   * @param semanticElement
   * @param semanticFeature
   * @param object
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doRemoveOperationInWriteOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature, EObject object) {
    EObject linkToRemove = null;
    for (EObject obj : (List<EObject>) semanticElement.eGet(semanticFeature)) {
      if ((obj instanceof FunctionRealization)
        && ((FunctionRealization) obj).getAllocatedFunction().equals(object))
      {
        linkToRemove = obj;
      }
    }
    if (linkToRemove != null)
      super.doRemoveOperationInWriteOpenValues(semanticElement, semanticFeature, linkToRemove);
  }
}
