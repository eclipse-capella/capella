/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.flexibility.properties.schema.IEStructuralFeatureProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.PropertiesSchemaConstants;

/**
 * Describes a property associated to an element
 */
public class EStructuralFeatureProperty extends AbstractProperty implements IEditableProperty, IEStructuralFeatureProperty {

  private Pattern split = Pattern.compile("\\.");

  public String getRelatedEReference() {
	  return getParameter(PropertiesSchemaConstants.PropertiesSchema_ESTRUCTURAL_FEATURE_PROPERTY__EFEATURE);
  }

  public EClass getRelatedEClass() {
    String value = getParameter(PropertiesSchemaConstants.PropertiesSchema_ESTRUCTURAL_FEATURE_PROPERTY__ECLASS);
    return getRelatedEClass(value, EPackage.Registry.INSTANCE.values());
  }

  public EClass getRelatedEClass(String name, Collection pkgs) {
    for (Object pkg : pkgs) {
      if (pkg instanceof EPackage) {
        EPackage ePackage = (EPackage) pkg;
        String prefix = ePackage.getName();

        String[] values = split.split(name);
        String prefix2 = values[values.length - 2];
        String lastName = values[values.length - 1];

        if ((prefix != null) && prefix2.equals(prefix)) {
          EClassifier clazze = ePackage.getEClassifier(lastName);
          if ((clazze != null) && (clazze instanceof EClass)) {
            return (EClass) clazze;
          }
          EClass parent = getRelatedEClass(name, ePackage.getESubpackages());
          if (parent != null) {
            return parent;
          }
        }
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#getType()
   */
  public Object getType() {
    return EObject.class;
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#getValue(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext)
   */
  public Object getValue(IPropertyContext context) {
    Collection<Object> values = new LinkedHashSet<Object>();

    EClass clazz = getRelatedEClass();

    for (Object object : context.getSourceAsList()) {
      if (object instanceof EObject) {
        EObject element = (EObject) object;
        EStructuralFeature feature = element.eClass().getEStructuralFeature(getRelatedEReference());
        if (feature != null && (clazz == null || clazz.isSuperTypeOf(element.eClass()))) {
            Object result = getFeatureValue(context, element, feature);
            	if (result != null) {
            		values.add(result);
            	}
        	}
    	  }
    }
    if (values.size() == 1) {
      return values.iterator().next();
    }
    return values;
  }

  /**
   * @param element
   * @param feature
   * @return
   */
  protected Object getFeatureValue(IPropertyContext context, EObject element, EStructuralFeature feature) {
    if (feature.isMany()) {
      return new ArrayList<Object>((EList) element.eGet(feature));
    }
    return element.eGet(feature);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty#setValue(org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void setValue(IPropertyContext context) {
    EClass clazz = getRelatedEClass();

    for (Object object : context.getSourceAsList()) {
      if (object instanceof EObject) {
        EObject element = (EObject) object;
        EStructuralFeature feature = element.eClass().getEStructuralFeature(getRelatedEReference());

        // We should check EClass!!!
        if ((clazz == null) || clazz.isSuperTypeOf(element.eClass())) {
          if (element.eClass().getEAllStructuralFeatures().contains(feature)) {
            if (feature.isMany()) {
              Collection<EObject> result = (Collection) context.getCurrentValue(this);
              Collection<EObject> current = (Collection) element.eGet(feature);

              for (EObject res : new ArrayList<EObject>(current)) {
                if (!result.contains(res)) {
                  current.remove(res);
                }
              }
              for (EObject res : result) {
                if (!current.contains(res)) {
                  current.add(res);
                }
              }
            } else {
              Object result = context.getCurrentValue(this);
              element.eSet(feature, result);
            }
          }
        }
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#toType(java.lang.Object,
   *      org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext)
   */
  public Object toType(Object value, IPropertyContext context) {
    return value;
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#validate(java.lang.Object,
   *      org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext)
   */
  public IStatus validate(Object newValue, IPropertyContext context) {
    return Status.OK_STATUS;
  }
}
