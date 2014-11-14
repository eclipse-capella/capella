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

	public Object doSwitch(TypedElement element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(CapellacorePackage.Literals.TYPED_ELEMENT__TYPE)) {
			ret = getType( element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected Type getType(TypedElement element_p){
		AbstractType absType = element_p.getAbstractType();
		if(absType instanceof Type) {
			return (Type)absType;
		}
		return null;
	}
}
