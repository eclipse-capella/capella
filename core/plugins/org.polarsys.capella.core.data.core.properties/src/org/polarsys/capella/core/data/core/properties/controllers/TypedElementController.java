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

package org.polarsys.capella.core.data.core.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.ui.properties.controllers.ISimpleSemanticFieldController;

/**
 */
public class TypedElementController implements ISimpleSemanticFieldController {

  /**
   * {@inheritDoc}
   */
  public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> list = new ArrayList<EObject>();

    // query for 'abstractType'
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
    if (query != null) {
      List<EObject> list1 = query.getAvailableElements(semanticElement);

      if (semanticElement.eContainer() instanceof ExchangeItem) {
        // the container is an parameter : every element is acceptable as parameter
        list.addAll(list1);

      } else if ((!(semanticElement instanceof Property)) && (!(semanticElement instanceof UnionProperty))) {
        // if property eContainer is not an Association : add to list only Primitive class and all other elements
        for (EObject capellaElement : list1) {
          if (capellaElement instanceof Class) {
            Class cls = (Class) capellaElement;
            // In the COLLECTIONS SPECIFIC CASE, always add the classes
            // In Complex type SPECIFIC CASE, always add the classes (non-primitive) (and primitives)
            if ((semanticElement instanceof Collection)
             || (semanticElement instanceof ComplexValue/* && !cls.isIsPrimitive()*/)
             || (!(semanticElement instanceof Collection) && !(semanticElement instanceof ComplexValue) && cls.isIsPrimitive()))
            {
              list.add(capellaElement);
            }
          }
          else {
            if (!(capellaElement instanceof Interface)) {
              list.add(capellaElement);
            }
          }
        }
      } else {
        list.addAll(list1);
      }
    }

    return list;
  }

  /**
   * {@inheritDoc}
   */
  public EObject writeOpenValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
    semanticElement.eSet(semanticFeature, value);
    return value;
  }

  /**
   * {@inheritDoc}
   */
  public EObject loadValue(EObject semanticElement, EStructuralFeature semanticFeature) {
    return (EObject) semanticElement.eGet(semanticFeature);
  }
}
