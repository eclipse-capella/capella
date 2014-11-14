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
package org.polarsys.capella.core.data.core.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.ISimpleSemanticFieldController;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class TypedElementController implements ISimpleSemanticFieldController {

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#readOpenValues()
   */
  public List<EObject> readOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    List<EObject> list = new ArrayList<EObject>();

    // query for 'abstractType'
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
    if (query != null) {
      List<CapellaElement> list1 = query.getAvailableElements(semanticElement_p);

      if (semanticElement_p.eContainer() instanceof ExchangeItem) {
        // the container is an parameter : every element is acceptable as parameter
        list.addAll(list1);

      } else if ((!(semanticElement_p instanceof Property)) && (!(semanticElement_p instanceof UnionProperty))) {
        // if property eContainer is not an Association : add to list only Primitive class and all other elements
        for (CapellaElement capellaElement : list1) {
          if (capellaElement instanceof Class) {
            Class cls = (Class) capellaElement;
            // In the COLLECTIONS SPECIFIC CASE, always add the classes
            // In Complex type SPECIFIC CASE, always add the classes (non-primitive) (and primitives)
            if ((semanticElement_p instanceof Collection)
             || (semanticElement_p instanceof ComplexValue/* && !cls.isIsPrimitive()*/)
             || (!(semanticElement_p instanceof Collection) && !(semanticElement_p instanceof ComplexValue) && cls.isIsPrimitive()))
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
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#writeOpenValue(org.eclipse.emf.ecore.EObject)
   */
  public EObject writeOpenValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p, EObject value) {
    semanticElement_p.eSet(semanticFeature_p, value);
    return value;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#loadData(org.polarsys.capella.core.data.information.datatype.BooleanType,
   *      org.eclipse.emf.ecore.EReference)
   */
  public EObject loadValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    return (EObject) semanticElement_p.eGet(semanticFeature_p);
  }
}
