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
package org.polarsys.capella.core.data.helpers.fa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

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

	public Object doSwitch(FunctionPort element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(FaPackage.Literals.FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS)) {
      ret = getAllocatorComponentPorts(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTION_PORT__REALIZED_FUNCTION_PORTS)) {
      ret = getRealizedFunctionPorts(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTION_PORT__REALIZING_FUNCTION_PORTS)) {
      ret = getRealizingFunctionPorts(element_p);
    }

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = PortHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

  protected List<ComponentPort> getAllocatorComponentPorts(FunctionPort element_p) {
    List <ComponentPort> ret = new ArrayList<ComponentPort>();
    for (PortAllocation portAllocation : element_p.getIncomingPortAllocations()) {
      Port port = portAllocation.getAllocatingPort();
      if (port instanceof ComponentPort){
        ret.add((ComponentPort) port);
      }
    }
    return ret;
  }

  protected List<FunctionPort> getRealizedFunctionPorts(FunctionPort element_p) {
    List <FunctionPort> result = new ArrayList<FunctionPort>();
    for (PortRealization portAllocation : element_p.getOutgoingPortRealizations()) {
      Port port = portAllocation.getRealizedPort();
      if (port instanceof FunctionPort && !result.contains(port)){
        result.add((FunctionPort) port);
      }
    }
    return result;
  }

  protected List<FunctionPort> getRealizingFunctionPorts(FunctionPort element_p) {
    List <FunctionPort> result = new ArrayList<FunctionPort>();
    for (PortRealization portAllocation : element_p.getIncomingPortRealizations()) {
      Port port = portAllocation.getRealizingPort();
      if (port instanceof FunctionPort && !result.contains(port)){
        result.add((FunctionPort) port);
      }
    }
    return result;
  }
}
