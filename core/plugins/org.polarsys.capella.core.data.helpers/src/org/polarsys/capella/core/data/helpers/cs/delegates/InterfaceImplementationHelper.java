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
package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;

public class InterfaceImplementationHelper {
	private static InterfaceImplementationHelper instance;

	private InterfaceImplementationHelper() {
    // do nothing
	}

	public static InterfaceImplementationHelper getInstance() {
		if (instance == null)
			instance = new InterfaceImplementationHelper();
		return instance;
	}
	
	public Object doSwitch(InterfaceImplementation element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(CsPackage.Literals.INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR)) {
			ret = getInterfaceImplementor(element_p);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = RelationshipHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected Component getInterfaceImplementor(InterfaceImplementation element_p) {
		EObject owner = element_p.eContainer();
		if (owner instanceof Component) {
			return (Component) owner;
		}
		return null;
	}
}
