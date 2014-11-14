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

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractTypeHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;

public class TypeHelper {
	private static TypeHelper instance;

	private TypeHelper() {
	  //
	}

	public static TypeHelper getInstance(){
		if(instance == null)
			instance = new TypeHelper();
		return instance;
	}

	public Object doSwitch(Type element_p, EStructuralFeature feature_p){
		Object ret = null;

		if (feature_p.equals(CapellacorePackage.Literals.TYPE__TYPED_ELEMENTS)) {
			ret = getTypedElements(element_p);
		}

		// no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractTypeHelper.getInstance().doSwitch(element_p, feature_p);
    }
		if (null == ret) {
			ret = NamespaceHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected List<TypedElement> getTypedElements(Type element_p){
		List <AbstractTypedElement> absTypedElements = element_p.getAbstractTypedElements();
		List <TypedElement> ret = new ArrayList<TypedElement>();

		for (AbstractTypedElement abstractTypedElement : absTypedElements) {
			if(abstractTypedElement instanceof TypedElement){
				ret.add((TypedElement) abstractTypedElement);
			}
		}

		return ret;
	}
}
