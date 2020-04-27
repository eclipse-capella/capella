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
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractConstraintHelper;
import org.polarsys.capella.core.data.capellacore.Constraint;

public class ConstraintHelper {
	private static ConstraintHelper instance;

	private ConstraintHelper() {
		// Do nothing
	}

	public static ConstraintHelper getInstance() {
		if (instance == null) {
			instance = new ConstraintHelper();
		}
		return instance;
	}

	public Object doSwitch(Constraint element, EStructuralFeature feature) {
		// no helper found... searching in super classes...
		Object ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		
		if(null == ret) {
			ret = AbstractConstraintHelper.getInstance().doSwitch(element, feature);
		}
		return ret;
	}
}
