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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.helpers.information.delegates.PortHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PropertyHelper;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;

public class ComponentPortHelper {
  private static ComponentPortHelper instance;

  private ComponentPortHelper() {
    // do nothing
  }

  public static ComponentPortHelper getInstance() {
    if (instance == null) {
      instance = new ComponentPortHelper();
    }
    return instance;
  }

  public Object doSwitch(ComponentPort element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.COMPONENT_PORT__COMPONENT_EXCHANGES)) {
      ret = getComponentExchanges(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_PORT__ALLOCATED_FUNCTION_PORTS)) {
      ret = getAllocatedFunctionPorts(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_PORT__DELEGATED_COMPONENT_PORTS)) {
      ret = getDelegatedComponentPorts(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_PORT__DELEGATING_COMPONENT_PORTS)) {
      ret = getDelegatingComponentPorts(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_PORT__ALLOCATING_PHYSICAL_PORTS)) {
      ret = getAllocatingPhysicalPorts(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_PORT__REALIZED_COMPONENT_PORTS)) {
      ret = getRealizedComponentPorts(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_PORT__REALIZING_COMPONENT_PORTS)) {
      ret = getRealizingComponentPorts(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = PortHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InformationsExchangerHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = PropertyHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  /**
   * Returns all abstract connections linked to the component port
   */
  protected List<ComponentExchange> getComponentExchanges(ComponentPort element) {
    // linked hash set allows to keep ordering !
    HashSet<ComponentExchange> ret = new LinkedHashSet<>();
    for (AbstractInformationFlow trace : element.getInformationFlows()) {
      if (trace instanceof ComponentExchange) {
        ret.add((ComponentExchange) trace);
      }
    }
    for (ComponentExchangeEnd end : FunctionalExt.getRelatedComponentExchangeEnds(element)) {
      EObject owner = end.eContainer();
      if (owner instanceof ComponentExchange) {
        ret.add((ComponentExchange) owner);
      }
    }
    return new ArrayList<>(ret);
  }

  protected List<FunctionPort> getAllocatedFunctionPorts(ComponentPort element) {
    List <FunctionPort> ret = new ArrayList<>();
    for (PortAllocation portAllocation : element.getOutgoingPortAllocations()) {
      Port port = portAllocation.getAllocatedPort();
      if (port instanceof FunctionPort){
        ret.add((FunctionPort) port);
      }
    }
    return ret;
  }

  protected List<ComponentPort> getDelegatedComponentPorts(ComponentPort element) {
    List <ComponentPort> ret = new ArrayList<>();
    for (AbstractInformationFlow informationFlow : element.getOutgoingInformationFlows()) {
      if (informationFlow instanceof ComponentExchange) {
        if (ComponentExchangeKind.DELEGATION.equals(((ComponentExchange) informationFlow).getKind())) {
          InformationsExchanger informationsExchanger = informationFlow.getTarget();
          if (informationsExchanger instanceof ComponentPort){
            ret.add((ComponentPort) informationsExchanger);
          }
        }
      }
    }
    return ret;
  }

  protected List<ComponentPort> getDelegatingComponentPorts(ComponentPort element) {
    List <ComponentPort> ret = new ArrayList<>();
    for (AbstractInformationFlow informationFlow : element.getIncomingInformationFlows()) {
      if (informationFlow instanceof ComponentExchange) {
        if (ComponentExchangeKind.DELEGATION.equals(((ComponentExchange) informationFlow).getKind())) {
          InformationsExchanger informationsExchanger = informationFlow.getSource();
          if (informationsExchanger instanceof ComponentPort){
            ret.add((ComponentPort) informationsExchanger);
          }
        }
      }
    }
    return ret;
  }

  protected List<PhysicalPort> getAllocatingPhysicalPorts(ComponentPort element) {
    List <PhysicalPort> result = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentPortAllocation) {
        Port port = ((ComponentPortAllocation) trace).getAllocatingPort();
        if (port instanceof PhysicalPort){
          result.add((PhysicalPort) port);
        }
      }
    }
    return result;
  }

  protected List<ComponentPort> getRealizedComponentPorts(ComponentPort element) {
    List <ComponentPort> result = new ArrayList<>();
    for (PortRealization portAllocation : element.getOutgoingPortRealizations()) {
      Port port = portAllocation.getRealizedPort();
      if (port instanceof ComponentPort && !result.contains(port)){
        result.add((ComponentPort) port);
      }
    }
    return result;
  }

  protected List<ComponentPort> getRealizingComponentPorts(ComponentPort element) {
    List <ComponentPort> result = new ArrayList<>();
    for (PortRealization portAllocation : element.getIncomingPortRealizations()) {
      Port port = portAllocation.getRealizingPort();
      if (port instanceof ComponentPort && !result.contains(port)){
        result.add((ComponentPort) port);
      }
    }
    return result;
  }
}
