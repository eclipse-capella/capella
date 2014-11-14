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
package org.polarsys.capella.core.ui.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public abstract class AbstractSimpleSemanticFieldController extends AbstractSemanticFieldController implements ISimpleSemanticFieldController {

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.ISimpleSemanticFieldController#loadValue(org.polarsys.capella.core.data.capellacore.CapellaElement, org.eclipse.emf.ecore.EStructuralFeature)
   */
  public EObject loadValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    return (EObject) semanticElement_p.eGet(semanticFeature_p);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#readOpenValues()
   */
  public List<EObject> readOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    List<EObject> list = new ArrayList<EObject>();
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), semanticFeature_p);
    if (null != query) {
      List<CapellaElement> availableElements = query.getAvailableElements(semanticElement_p);
      list.addAll(availableElements);
    }
    return list;
  }
}
