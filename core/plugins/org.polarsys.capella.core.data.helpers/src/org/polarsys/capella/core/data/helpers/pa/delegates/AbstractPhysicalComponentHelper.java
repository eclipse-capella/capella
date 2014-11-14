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
package org.polarsys.capella.core.data.helpers.pa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.cs.delegates.DeployableElementHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.SystemComponentHelper;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;

public class AbstractPhysicalComponentHelper {
	private static AbstractPhysicalComponentHelper instance;

	private AbstractPhysicalComponentHelper() {
    // do nothing
	}

	public static AbstractPhysicalComponentHelper getInstance() {
		if (instance == null)
			instance = new AbstractPhysicalComponentHelper();
		return instance;
	}

	public Object doSwitch(AbstractPhysicalComponent element_p, EStructuralFeature feature_p) {
		Object ret = null;

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = SystemComponentHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = DeployableElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
		return ret;
	}
}
