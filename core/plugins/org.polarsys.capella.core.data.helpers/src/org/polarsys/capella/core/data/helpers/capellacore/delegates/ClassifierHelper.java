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
package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

public class ClassifierHelper {
	private static ClassifierHelper instance;

	private ClassifierHelper() {
	  // do nothing
	}

	public static ClassifierHelper getInstance() {
		if (instance == null)
			instance = new ClassifierHelper();
		return instance;
	}

	public Object doSwitch(Classifier element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(CapellacorePackage.Literals.CLASSIFIER__CONTAINED_PROPERTIES)) {
      ret = getContainedProperties(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = GeneralizableElementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	/**
	 * @param element_p the owner {@link Classifier}
	 * @return returns ONLY the instances of {@link Property} meta-class and NOT one of its sub-types
	 */
  protected List <Property> getContainedProperties(Classifier element_p){
    List <Property> ret = new ArrayList <Property>();
    for (Feature feature : element_p.getOwnedFeatures()) {
      if(feature.eClass().equals(InformationPackage.Literals.PROPERTY)) {
        ret.add((Property) feature);
      }
    }
    return ret;
  }
}
