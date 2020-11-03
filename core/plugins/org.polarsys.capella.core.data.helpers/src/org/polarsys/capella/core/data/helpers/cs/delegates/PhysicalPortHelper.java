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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.PhysicalPortRealization;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.helpers.information.delegates.PortHelper;
import org.polarsys.capella.core.data.information.Port;

public class PhysicalPortHelper {
	private static PhysicalPortHelper instance;

	private PhysicalPortHelper() {
    // do nothing
	}

	public static PhysicalPortHelper getInstance() {
		if (instance == null)
			instance = new PhysicalPortHelper();
		return instance;
	}

	public Object doSwitch(PhysicalPort element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CsPackage.Literals.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS)) {
      ret = getAllocatedComponentPorts(element);
    } else if (feature.equals(CsPackage.Literals.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS)) {
      ret = getRealizedPhysicalPorts(element);
    } else if (feature.equals(CsPackage.Literals.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS)) {
      ret = getRealizingPhysicalPorts(element);
    }

		// no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractPhysicalArtifactHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractPhysicalLinkEndHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InformationsExchangerHelper.getInstance().doSwitch(element, feature);
    }
		if (null == ret) {
			ret = PortHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<ComponentPort> getAllocatedComponentPorts(PhysicalPort element) {
    List <ComponentPort> result = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ComponentPortAllocation) {
        Port port = ((ComponentPortAllocation) trace).getAllocatedPort();
        if (port instanceof ComponentPort) {
          result.add((ComponentPort) port);
        }
      }
    }
    return result;
  }

  protected List<PhysicalPort> getRealizedPhysicalPorts(PhysicalPort element) {
    List<PhysicalPort> ports = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof PhysicalPortRealization) {
        TraceableElement port = ((PhysicalPortRealization) trace).getTargetElement();
        if (port instanceof PhysicalPort) {
          ports.add((PhysicalPort) port);
        }
      }
    }
    return ports;
  }

  protected List<PhysicalPort> getRealizingPhysicalPorts(PhysicalPort element) {
    List<PhysicalPort> ports = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof PhysicalPortRealization) {
        TraceableElement port = ((PhysicalPortRealization) trace).getSourceElement();
        if (port instanceof PhysicalPort) {
          ports.add((PhysicalPort) port);
        }
      }
    }
    return ports;
  }
}
