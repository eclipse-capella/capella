/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;


public class TypedElementHelper {
	private static TypedElementHelper instance;
	
	private TypedElementHelper() {
	  //
	}
	
	public static TypedElementHelper getInstance(){
		if(instance == null)
			instance = new TypedElementHelper();
		return instance;
	}

	public Object doSwitch(TypedElement element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CapellacorePackage.Literals.TYPED_ELEMENT__TYPE)) {
			ret = getType( element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected Type getType(TypedElement element){
		AbstractType absType = element.getAbstractType();
		if(absType instanceof Type) {
			return (Type)absType;
		}
		return null;
	}
}
