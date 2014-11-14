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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.pa.deployment.ComponentInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.pa.deployment.PortInstance;

public class PortInstanceHelper {
	private static PortInstanceHelper instance;

	private PortInstanceHelper() {
    // do nothing
	}

	public static PortInstanceHelper getInstance() {
		if (instance == null)
			instance = new PortInstanceHelper();
		return instance;
	}

	public Object doSwitch(PortInstance element_p, EStructuralFeature feature_p) {
		Object ret = null;
    
    if (feature_p.equals(DeploymentPackage.Literals.PORT_INSTANCE__COMPONENT)) {
      ret = getComponent(element_p);
    }

		// no helper found... searching in super classes...
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}

  protected ComponentInstance getComponent(PortInstance element_p) {
    EObject owner = element_p.eContainer();
    if (owner instanceof ComponentInstance) {
      return (ComponentInstance) owner;
    }
    return null;
  }
}
