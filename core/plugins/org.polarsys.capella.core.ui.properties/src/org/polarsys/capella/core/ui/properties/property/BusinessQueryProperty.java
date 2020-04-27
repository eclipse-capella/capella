/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.flexibility.properties.property.EStructuralFeatureProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.flexibility.properties.schema.PropertiesSchemaConstants;

/**
 */
public class BusinessQueryProperty extends EStructuralFeatureProperty implements IRestraintProperty {

  Pattern _split = Pattern.compile("\\.");

  /**
   * @param eClass
   * @param reference
   * @param source
   */
  public BusinessQueryProperty() {
  }

  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    return Status.OK_STATUS;
  }

  @Override
  public EClass getRelatedEClass() {
    String value = getParameter(PropertiesSchemaConstants.PropertiesSchema_ESTRUCTURAL_FEATURE_PROPERTY__ECLASS);
    return getRelatedEClass(value, EPackage.Registry.INSTANCE.values());
  }

  @SuppressWarnings("rawtypes")
  @Override
  public EClass getRelatedEClass(String name, Collection pkgs) {
    for (Object pkg : pkgs) {
      if (pkg instanceof EPackage) {
        EPackage ePackage = (EPackage) pkg;
        String prefix = ePackage.getName();

        String[] values = _split.split(name);
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

  @Override
  protected Object getFeatureValue(IPropertyContext context, EObject element, EStructuralFeature reference) {
    EClass clazz = getRelatedEClass();
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(clazz, reference);
    if ((query != null) && (element instanceof CapellaElement)) {
      return new ArrayList<Object>(query.getCurrentElements((CapellaElement) element, false));
    }
    return super.getFeatureValue(context, element, reference);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IRestraintProperty#getChoiceValues(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext)
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Collection<Object> getChoiceValues(IPropertyContext context) {
    ArrayList<EObject> result = new ArrayList<EObject>();
    EClass clazz = getRelatedEClass();
    for (Object object : context.getSourceAsList()) {
      if (object instanceof EObject) {
        EObject element = (EObject) object;
        EStructuralFeature feature = element.eClass().getEStructuralFeature(getRelatedEReference());
        if ((clazz != null) && (feature != null)) {
          IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(clazz, feature);
          if ((query != null) && (element instanceof CapellaElement)) {
            result.addAll(query.getAvailableElements((CapellaElement) element));
          }
        }
      }
    }

    return (Collection) result;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isMany() {
    EClass clazz = getRelatedEClass();
    if (clazz != null) {
      EStructuralFeature feature = clazz.getEStructuralFeature(getRelatedEReference());
      return (feature != null) && feature.isMany();
    }
    return false;
  }
}
