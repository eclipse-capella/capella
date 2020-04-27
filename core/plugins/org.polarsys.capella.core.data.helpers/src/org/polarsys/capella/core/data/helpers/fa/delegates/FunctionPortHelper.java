/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractTypeHelper;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.helpers.information.delegates.PortHelper;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;

public class FunctionPortHelper {
	private static FunctionPortHelper instance;

	private FunctionPortHelper() {
    // do nothing
	}

	public static FunctionPortHelper getInstance() {
		if (instance == null)
			instance = new FunctionPortHelper();
		return instance;
	}

	public Object doSwitch(FunctionPort element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(FaPackage.Literals.FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS)) {
      ret = getAllocatorComponentPorts(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTION_PORT__REALIZED_FUNCTION_PORTS)) {
      ret = getRealizedFunctionPorts(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTION_PORT__REALIZING_FUNCTION_PORTS)) {
      ret = getRealizingFunctionPorts(element);
    }

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = PortHelper.getInstance().doSwitch(element, feature);
		}
    if(null == ret) {
      ret = AbstractTypeHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

  protected List<ComponentPort> getAllocatorComponentPorts(FunctionPort element) {
    List <ComponentPort> ret = new ArrayList<>();
    for (PortAllocation portAllocation : element.getIncomingPortAllocations()) {
      Port port = portAllocation.getAllocatingPort();
      if (port instanceof ComponentPort){
        ret.add((ComponentPort) port);
      }
    }
    return ret;
  }

  protected List<FunctionPort> getRealizedFunctionPorts(FunctionPort element) {
    List <FunctionPort> result = new ArrayList<>();
    for (PortRealization portAllocation : element.getOutgoingPortRealizations()) {
      Port port = portAllocation.getRealizedPort();
      if (port instanceof FunctionPort && !result.contains(port)){
        result.add((FunctionPort) port);
      }
    }
    return result;
  }

  protected List<FunctionPort> getRealizingFunctionPorts(FunctionPort element) {
    List <FunctionPort> result = new ArrayList<>();
    for (PortRealization portAllocation : element.getIncomingPortRealizations()) {
      Port port = portAllocation.getRealizingPort();
      if (port instanceof FunctionPort && !result.contains(port)){
        result.add((FunctionPort) port);
      }
    }
    return result;
  }
}
