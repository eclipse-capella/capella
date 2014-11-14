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
package org.polarsys.capella.core.data.helpers.fa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;

public class ComponentExchangeEndHelper {
	private static ComponentExchangeEndHelper instance;

	private ComponentExchangeEndHelper() {
    // do nothing
	}

	public static ComponentExchangeEndHelper getInstance(){
		if(instance == null)
			instance = new ComponentExchangeEndHelper();
		return instance;
	}

	public Object doSwitch(ComponentExchangeEnd element_p,EStructuralFeature feature_p) {
		Object ret = null;

		// no helper found... searching in super classes...
    if (null == ret) {
      ret = InformationsExchangerHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}
	
}
