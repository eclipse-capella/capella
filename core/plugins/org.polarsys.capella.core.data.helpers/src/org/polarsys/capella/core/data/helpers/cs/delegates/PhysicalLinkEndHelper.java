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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;

public class PhysicalLinkEndHelper {
	private static PhysicalLinkEndHelper instance;

	private PhysicalLinkEndHelper() {
    // do nothing
	}

	public static PhysicalLinkEndHelper getInstance() {
		if (instance == null)
			instance = new PhysicalLinkEndHelper();
		return instance;
	}

	public Object doSwitch(PhysicalLinkEnd element_p, EStructuralFeature feature_p) {
		Object ret = null;

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = AbstractPhysicalLinkEndHelper.getInstance().doSwitch(element_p, feature_p);
		}
		return ret;
	}
}
