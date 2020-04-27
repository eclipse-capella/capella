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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;

public class InterfaceUseHelper {
	private static InterfaceUseHelper instance;

	private InterfaceUseHelper() {
    // do nothing
	}

	public static InterfaceUseHelper getInstance() {
		if (instance == null)
			instance = new InterfaceUseHelper();
		return instance;
	}

	public Object doSwitch(InterfaceUse element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CsPackage.Literals.INTERFACE_USE__INTERFACE_USER)) {
			ret = getInterfaceUser(element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = RelationshipHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected Component getInterfaceUser(InterfaceUse element) {
		EObject owner = element.eContainer();
		if (owner instanceof Component) {
			return (Component) owner;
		}
		return null;
	}
}
