/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;

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

	public Object doSwitch(Classifier element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(CapellacorePackage.Literals.CLASSIFIER__CONTAINED_PROPERTIES)) {
      ret = getContainedProperties(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = GeneralizableElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	/**
	 * @param element the owner {@link Classifier}
	 * @return returns ONLY the instances of {@link Property} meta-class and NOT one of its sub-types
	 */
  protected List <Property> getContainedProperties(Classifier element){
    List <Property> ret = new ArrayList <>();
    for (Feature feature : element.getOwnedFeatures()) {
      if(feature.eClass().equals(InformationPackage.Literals.PROPERTY)) {
        ret.add((Property) feature);
      }
    }
    return ret;
  }
}
