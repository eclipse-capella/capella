/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

	public Object doSwitch(PortInstance element, EStructuralFeature feature) {
		Object ret = null;
    
    if (feature.equals(DeploymentPackage.Literals.PORT_INSTANCE__COMPONENT)) {
      ret = getComponent(element);
    }

		// no helper found... searching in super classes...
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

  protected ComponentInstance getComponent(PortInstance element) {
    EObject owner = element.eContainer();
    if (owner instanceof ComponentInstance) {
      return (ComponentInstance) owner;
    }
    return null;
  }
}
