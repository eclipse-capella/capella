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

import org.polarsys.capella.core.data.capellacore.Structure;

public class StructureHelper {
	private static StructureHelper instance;

	private StructureHelper() {
		// Do nothing
	}

	public static StructureHelper getInstance() {
		if (instance == null) {
			instance = new StructureHelper();
		}
		return instance;
	}

	public Object doSwitch(Structure element, EStructuralFeature feature) {
		// no helper found... searching in super classes...
		return NamespaceHelper.getInstance().doSwitch(element, feature);
	}
}
