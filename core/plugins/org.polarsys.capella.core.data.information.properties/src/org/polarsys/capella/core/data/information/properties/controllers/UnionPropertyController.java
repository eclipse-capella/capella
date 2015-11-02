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
package org.polarsys.capella.core.data.information.properties.controllers;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class UnionPropertyController extends AbstractMultipleSemanticFieldController implements IMultipleEditableSemanticFieldController {

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.IMultipleEditableSemanticFieldController#addValue(org.polarsys.capella.core.data.capellacore.CapellaElement,
   *      org.eclipse.emf.ecore.EStructuralFeature)
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<EObject> addValue(CapellaElement semanticElement, EStructuralFeature semanticFeature) {
    if (semanticElement instanceof UnionProperty) {
      Union union = (Union) semanticElement.eContainer();
      if (union != null) {
        UnionProperty prop = union.getDiscriminant();
        if (prop != null) {
          AbstractType type = prop.getAbstractType();
          if (type != null) {
            DataValue newValue = null;
            if (type instanceof StringType) {
              newValue = DatavalueFactory.eINSTANCE.createLiteralStringValue();
            } else if (type instanceof BooleanType) {
              newValue = DatavalueFactory.eINSTANCE.createLiteralBooleanValue();
            } else if (type instanceof Enumeration) {
              newValue = DatavalueFactory.eINSTANCE.createEnumerationLiteral();
            } else if (type instanceof NumericType) {
              newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
            } else if (type instanceof PhysicalQuantity) {
              newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
            }

            if (newValue != null) {
              ((Collection) union.eGet(InformationPackage.Literals.CLASS__OWNED_DATA_VALUES)).add(newValue);
              newValue.eSet(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, EcoreUtil2
                  .getUniqueName(newValue, union, InformationPackage.Literals.CLASS__OWNED_DATA_VALUES,
                      ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, newValue.eClass().getName()));
              newValue.eSet(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, type);

              if (editValueWizard(newValue)) {
                ((Collection) semanticElement.eGet(semanticFeature)).add(newValue);
                return (List<EObject>) semanticElement.eGet(semanticFeature);
              }
              throw new EditableSemanticFieldException();
            }
          }
        }
      }
    }

    return null;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.AbstractMultipleSemanticFieldController#getReadOpenValuesQuery(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), InformationPackage.Literals.UNION_PROPERTY__QUALIFIER);
  }
}
