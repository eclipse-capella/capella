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
package org.polarsys.capella.core.data.information.datatype.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.helpers.information.services.DataTypeExt;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;

/**
 */
public class DataTypeController extends AbstractMultipleSemanticFieldController {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> loadValues(EObject semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> result = new ArrayList<EObject>();
    List<EObject> values = super.loadValues(semanticElement, semanticFeature);
    if (null != values) {
      for (Object value : values) {
        result.add(((InformationRealization) value).getTargetElement());
      }
    }
    return result;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(),
      DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doAddOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    DataTypeExt.addRealizedInformation((DataType) semanticElement, (CapellaElement) object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doRemoveOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    DataTypeExt.removeRealizedInformation((DataType) semanticElement, (CapellaElement) object);
  }
}
