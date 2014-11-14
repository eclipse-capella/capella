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
package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractConstraintHelper;
import org.polarsys.capella.core.data.capellacore.Constraint;

public class ConstraintHelper {
	private static ConstraintHelper instance;

	private ConstraintHelper() {//
	}

	public static ConstraintHelper getInstance() {
		if (instance == null)
			instance = new ConstraintHelper();
		return instance;
	}

	public Object doSwitch(Constraint element_p, EStructuralFeature feature_p) {
		Object ret = null;

		// no helper found... searching in super classes...
		if (null == ret) {
		  ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
		}
		if(null == ret) {
			ret = AbstractConstraintHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}
}
