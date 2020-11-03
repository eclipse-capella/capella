/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.properties.controllers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;

public class FunctionalExchangeCategoriesController extends AbstractMultipleSemanticFieldController {

  @Override
  protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES);
  }
  
  @Override
  public List<EObject> writeOpenValues(EObject semanticElement, EStructuralFeature semanticFeature,
      List<EObject> values) {
    List<EObject> modelCurrentList = readOpenValues(semanticElement, semanticFeature, false);
    for (EObject category : modelCurrentList) {
      if (!values.contains(category)) {
        doRemoveOperationInWriteOpenValues(category, FaPackage.Literals.EXCHANGE_CATEGORY__EXCHANGES, semanticElement);
      }
    }
    for (EObject category : values) {
      if (!modelCurrentList.contains(category)) {
        doAddOperationInWriteOpenValues(category, FaPackage.Literals.EXCHANGE_CATEGORY__EXCHANGES, semanticElement);
      }
    }
    return values;
  }

}
