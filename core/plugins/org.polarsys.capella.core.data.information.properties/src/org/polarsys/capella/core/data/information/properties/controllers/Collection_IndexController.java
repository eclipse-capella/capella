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
package org.polarsys.capella.core.data.information.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;

/**
 */
public class Collection_IndexController extends AbstractMultipleSemanticFieldController {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), InformationPackage.Literals.COLLECTION__INDEX);
  }
  
  /**
   * {@inheritDoc}
   * Override this method to modify model when the order of indexes change
   */
  @Override
  public List<EObject> writeOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, List<EObject> values) {
    List<EObject> result = new ArrayList<>();
    if (null != values) {
      List<EObject> modelCurrentList = readOpenValues(semanticElement, semanticFeature, false);
      for (EObject currentModelObject : modelCurrentList) {
        doRemoveOperationInWriteOpenValues(semanticElement, semanticFeature, currentModelObject);
      }
      for (EObject currentObject : values) {
        doAddOperationInWriteOpenValues(semanticElement, semanticFeature, currentObject);
        result.add(currentObject);
      }
    }
    return result;
  }
}
