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

	public Object doSwitch(Type element, EStructuralFeature feature){
		Object ret = null;

		if (feature.equals(CapellacorePackage.Literals.TYPE__TYPED_ELEMENTS)) {
			ret = getTypedElements(element);
		}

		// no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractTypeHelper.getInstance().doSwitch(element, feature);
    }
		if (null == ret) {
			ret = NamespaceHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected List<TypedElement> getTypedElements(Type element){
		List <AbstractTypedElement> absTypedElements = element.getAbstractTypedElements();
		List <TypedElement> ret = new ArrayList<>();

		for (AbstractTypedElement abstractTypedElement : absTypedElements) {
			if(abstractTypedElement instanceof TypedElement){
				ret.add((TypedElement) abstractTypedElement);
			}
		}

		return ret;
	}
}
