/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.pa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.cs.delegates.InterfaceAllocationHelper;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;

public class LogicalInterfaceRealizationHelper {
	private static LogicalInterfaceRealizationHelper instance;

	private LogicalInterfaceRealizationHelper() {
    // do nothing
	}

	public static LogicalInterfaceRealizationHelper getInstance() {
		if (instance == null)
			instance = new LogicalInterfaceRealizationHelper();
		return instance;
	}

	public Object doSwitch(LogicalInterfaceRealization element, EStructuralFeature feature) {
		Object ret = null;
		// no helper found... searching in super classes...
		if(null == ret) {
			ret = InterfaceAllocationHelper.getInstance().doSwitch(element, feature);
		}
		return ret;
	}
}
