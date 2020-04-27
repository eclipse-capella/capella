/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.capellacore.NamedElement;

public class NamedElementHelper {
	private static NamedElementHelper instance;

	private NamedElementHelper() {
	  // do nothing
	}

	public static NamedElementHelper getInstance() {
		if (instance == null) {
			instance = new NamedElementHelper();
		}
		return instance;
	}

	public Object doSwitch(NamedElement element, EStructuralFeature feature) {
		// no helper found... searching in super classes...
		return CapellaElementHelper.getInstance().doSwitch(element, feature);
	}
}
