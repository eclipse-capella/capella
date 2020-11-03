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

package org.polarsys.capella.core.data.helpers.pa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.cs.delegates.DeployableElementHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.DeploymentTargetHelper;
import org.polarsys.capella.core.data.pa.deployment.AbstractPhysicalInstance;
import org.polarsys.capella.core.data.pa.deployment.ComponentInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.pa.deployment.PortInstance;

public class ComponentInstanceHelper {
	private static ComponentInstanceHelper instance;

	private ComponentInstanceHelper() {
    // do nothing
	}

	public static ComponentInstanceHelper getInstance() {
		if (instance == null)
			instance = new ComponentInstanceHelper();
		return instance;
	}

	public Object doSwitch(ComponentInstance element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(DeploymentPackage.Literals.COMPONENT_INSTANCE__PORT_INSTANCES)) {
      ret = getPortInstances(element);
    }

	// No helper found... searching in super classes...
    if (null == ret) {
      ret = DeployableElementHelper.getInstance().doSwitch(element, feature);
    }
    
    if (null == ret) {
        ret = DeploymentTargetHelper.getInstance().doSwitch(element, feature);
    }

	return ret;
	}

  protected List<PortInstance> getPortInstances(ComponentInstance element) {
    List<PortInstance> portInstances = new ArrayList<>();
    for (AbstractPhysicalInstance abstractPhysicalInstance : element.getOwnedAbstractPhysicalInstances()) {
      if (abstractPhysicalInstance instanceof PortInstance) {
        portInstances.add((PortInstance) abstractPhysicalInstance);
      }
    }
    return portInstances;
  }
}
