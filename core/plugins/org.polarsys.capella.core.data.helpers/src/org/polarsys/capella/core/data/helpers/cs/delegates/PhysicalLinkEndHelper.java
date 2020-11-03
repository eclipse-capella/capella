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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;

public class PhysicalLinkEndHelper {
	private static PhysicalLinkEndHelper instance;

	private PhysicalLinkEndHelper() {
    // do nothing
	}

	public static PhysicalLinkEndHelper getInstance() {
		if (instance == null) {
			instance = new PhysicalLinkEndHelper();
		}
		return instance;
	}

	public Object doSwitch(PhysicalLinkEnd element, EStructuralFeature feature) {
		// no helper found... searching in super classes...
		return AbstractPhysicalLinkEndHelper.getInstance().doSwitch(element, feature);
	}
}
