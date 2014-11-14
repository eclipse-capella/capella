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
package org.polarsys.capella.core.platform.sirius.ui;

import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Tester which is able to check that a selected object is from 
 * the correct capella metaclass
 * 
 * 
 * Added tester featureIsA to test if a feature value of an eobject is typed by a specific eclass
 */
public class MetaclassPropertyTester extends PropertyTester {

  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
   */
	public boolean test(Object object_p, String propertyName_p, Object[] params_p, Object testedValue_p) {
		if (propertyName_p.equals("classOf")) { //$NON-NLS-1$
			// getting the capella element
			ModelElement element = ModelAdaptation.adaptToCapella(object_p);
			if (element == null) return false;
			String className = (String) testedValue_p;
			if (element.eClass().getName().equals(className)) return true;

			List<EClass> classes = element.eClass().getEAllSuperTypes();
			for (EClass clazz : classes) {
				if (clazz.getName().equals(className)) return true;
			}
			
		} else if (propertyName_p.equals("featureIsA") || propertyName_p.equals("graphicalFeatureIsA")) {//$NON-NLS-1$ //$NON-NLS-2$
      // getting the capella element
      ModelElement element = ModelAdaptation.adaptToCapella(object_p);
      if (element == null) return false;
      if (params_p.length == 0) return false;
      Object feature = params_p[0];
      if (!(feature instanceof String)) return false;

      String featureName = (String) feature;
      String className = (String) testedValue_p;
      EStructuralFeature sfeature = element.eClass().getEStructuralFeature(featureName);
      
      if (feature==null) return false;
      
      Object res = element.eGet(sfeature);
      if (res!=null && res instanceof EObject) {
        EObject result = (EObject)res;
        if (result.eClass().getName().equals(className)) return true;
        
        List<EClass> classes = result.eClass().getEAllSuperTypes();
        for (EClass clazz : classes) {
          if (clazz.getName().equals(className)) return true;
        }
      }
    }
		return false;
	}
}
