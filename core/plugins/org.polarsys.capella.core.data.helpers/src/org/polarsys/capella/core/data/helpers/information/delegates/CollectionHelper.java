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
package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.ClassifierHelper;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.capellacore.Feature;

public class CollectionHelper {
	private static CollectionHelper instance;

	private CollectionHelper() {
    // do nothing
	}

	public static CollectionHelper getInstance(){
		if(instance == null)
			instance = new CollectionHelper();
		return instance;
	}

	public Object doSwitch(Collection element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(InformationPackage.Literals.COLLECTION__CONTAINED_OPERATIONS)) {
			ret = getContainedOperations(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = ClassifierHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected List <Operation> getContainedOperations(Collection element_p){
		List <Operation> ret = new ArrayList <Operation>();
		for (Feature feature : element_p.getOwnedFeatures()) {
			if(feature instanceof Operation) {
				ret.add((Operation) feature);
			}
		}
		return ret;
	}
}
