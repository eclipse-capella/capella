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
package org.polarsys.capella.core.business.queries.information;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;

/**
 * This is the query for Value Part referenced Property
 */
public class ValuePart_ReferencedProperty implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();

    if (element_p instanceof ValuePart) {
      ValuePart elt = (ValuePart) element_p;
      if (elt.eContainer() instanceof ComplexValue) {
        AbstractType type = ((ComplexValue) elt.eContainer()).getAbstractType();
        if (type instanceof GeneralizableElement) {
          List<GeneralizableElement> superTypes = new ArrayList<GeneralizableElement>();
          superTypes.addAll(((GeneralizableElement) type).getSuper());
          superTypes.add((GeneralizableElement) type);
          for (GeneralizableElement superType : superTypes) {
            if (superType instanceof Classifier) {
              for (Feature feature : ((Classifier) superType).getOwnedFeatures()) {
                if (feature instanceof Property) {
                  returnValue.add(feature);
                }
              }
            }
          }
        }
      }
    }

    return returnValue;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof ValuePart) {
      ValuePart elt = (ValuePart) element_p;
      Property property = elt.getReferencedProperty();
      if (property != null) {
        currentElements.add(property);
      }
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return DatavaluePackage.Literals.VALUE_PART;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(DatavaluePackage.Literals.VALUE_PART__REFERENCED_PROPERTY);
  }
}
