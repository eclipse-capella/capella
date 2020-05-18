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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;

public class ComponentExchangeEndHelper {
	private static ComponentExchangeEndHelper instance;

	private ComponentExchangeEndHelper() {
      // Do nothing
	}

	public static ComponentExchangeEndHelper getInstance(){
		if(instance == null) {
			instance = new ComponentExchangeEndHelper();
		}
		return instance;
	}

	public Object doSwitch(ComponentExchangeEnd element, EStructuralFeature feature) {

		// no helper found... searching in super classes...
		Object ret = InformationsExchangerHelper.getInstance().doSwitch(element, feature);
		
		if (null == ret) {
			ret = CapellaElementHelper.getInstance().doSwitch(element, feature);
		}
		return ret;
	}
}
