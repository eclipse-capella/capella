/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.epbs.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.epbs.EPBSContext;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentHelper;

public class EPBSContextHelper {
	private static EPBSContextHelper instance;

	private EPBSContextHelper() {
    // do nothing
	}

	public static EPBSContextHelper getInstance() {
		if (instance == null) {
			instance = new EPBSContextHelper();
		}
		return instance;
	}

	public Object doSwitch(EPBSContext element, EStructuralFeature feature) {
		// no helper found... searching in super classes...
		return ComponentHelper.getInstance().doSwitch(element, feature);
	}
}
