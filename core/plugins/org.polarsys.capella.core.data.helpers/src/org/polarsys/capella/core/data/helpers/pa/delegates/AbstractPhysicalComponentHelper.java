/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.helpers.cs.delegates.DeploymentTargetHelper;
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

	public Object doSwitch(AbstractPhysicalComponent element, EStructuralFeature feature) {
		Object ret = null;

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = SystemComponentHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = DeployableElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
        ret = DeploymentTargetHelper.getInstance().doSwitch(element, feature);
    }
	return ret;
	}
}
