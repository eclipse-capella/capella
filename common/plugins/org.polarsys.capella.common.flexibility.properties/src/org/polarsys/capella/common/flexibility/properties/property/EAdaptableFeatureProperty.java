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

package org.polarsys.capella.common.flexibility.properties.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 * A EStructuralFeature property with adaptable value set.
 * If feature is set on many elements and typed by a String, some variable are defined and replaced when property is set.
 * 
 * - $i variable in string value will be set with index of element in Context.getSource()
 */
public class EAdaptableFeatureProperty extends EStructuralFeatureProperty {

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty#setValue(org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
   */
  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void setValue(IPropertyContext context) {
    EClass clazz = getRelatedEClass();
    int i = 1;

    for (Object object : context.getSourceAsList()) {
      if (object instanceof EObject) {
        EObject element = (EObject) object;
        EStructuralFeature feature = element.eClass().getEStructuralFeature(getRelatedEReference());

        if ((clazz == null) || clazz.isSuperTypeOf(element.eClass())) {
          if (element.eClass().getEAllStructuralFeatures().contains(feature)) {
            if (feature.isMany()) {
              Object value = context.getCurrentValue(this);
              Collection<Object> result = null;

              if (value instanceof Collection) {
                result = (Collection) value;
              } else {
                result = Collections.singleton(value);
              }

              Collection<Object> current = (Collection) element.eGet(feature);

              for (Object res : new ArrayList<Object>(current)) {
                if (!result.contains(res)) {
                  current.remove(res);
                }
              }
              for (Object res : result) {
                if (!current.contains(res)) {
                  current.add(res);
                }
              }
            } else {
              Object result = adaptValue(context.getCurrentValue(this), i++);
              element.eSet(feature, result);
            }
          }
        }
      }
    }
  }

  /**
   * @param currentValue
   * @param i
   * @return
   */
  private Object adaptValue(Object currentValue, int i) {
    if (currentValue instanceof String) {
      String result = (String) currentValue;
      result = result.replace("$i", "" + i);
      return result;
    }
    return currentValue;
  }
}
