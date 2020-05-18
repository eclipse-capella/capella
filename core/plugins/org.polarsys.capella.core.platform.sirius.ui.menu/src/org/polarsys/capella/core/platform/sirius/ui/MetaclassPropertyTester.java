/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui;

import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Tester which is able to check that a selected object is from the correct Capella meta-class
 * 
 * 
 * Added tester featureIsA to test if a feature value of an EObject is typed by a specific EClass
 */
public class MetaclassPropertyTester extends PropertyTester {

  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[],
   *      java.lang.Object)
   */
  public boolean test(Object object, String propertyName, Object[] params, Object testedValue) {
    if (propertyName.equals("classOf")) { //$NON-NLS-1$
      // Getting the Capella element
      ModelElement element = ModelAdaptation.adaptToCapella(object);
      if (element == null)
        return false;
      String className = (String) testedValue;
      if (element.eClass().getName().equals(className))
        return true;

      List<EClass> classes = element.eClass().getEAllSuperTypes();
      for (EClass clazz : classes) {
        if (clazz.getName().equals(className))
          return true;
      }

    } else if (propertyName.equals("featureIsA") || propertyName.equals("graphicalFeatureIsA")) {//$NON-NLS-1$ //$NON-NLS-2$
      // Getting the Capella element
      ModelElement element = ModelAdaptation.adaptToCapella(object);
      if (element == null)
        return false;
      if (params.length == 0)
        return false;
      Object feature = params[0];
      if (!(feature instanceof String))
        return false;

      String featureName = (String) feature;
      String className = (String) testedValue;
      EStructuralFeature sfeature = element.eClass().getEStructuralFeature(featureName);

      Object res = element.eGet(sfeature);
      if (res instanceof EObject) {
        EObject result = (EObject) res;
        if (result.eClass().getName().equals(className))
          return true;

        List<EClass> classes = result.eClass().getEAllSuperTypes();
        for (EClass clazz : classes) {
          if (clazz.getName().equals(className))
            return true;
        }
      }
    }
    return false;
  }
}
