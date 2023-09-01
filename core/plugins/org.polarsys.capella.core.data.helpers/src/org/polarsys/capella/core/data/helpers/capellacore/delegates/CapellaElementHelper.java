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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.TraceableElementHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class CapellaElementHelper {
	private static CapellaElementHelper instance;

	private CapellaElementHelper() {
	  // do nothing
	}

	public static CapellaElementHelper getInstance() {
		if (instance == null)
			instance = new CapellaElementHelper();
		return instance;
	}

	public Object doSwitch(CapellaElement element, EStructuralFeature feature) {
    Object ret = null;

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = TraceableElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
	}
}
