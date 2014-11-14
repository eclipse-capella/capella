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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.helpers.information.delegates.PartitionHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PortHelper;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;

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

  public Object doSwitch(ComponentPort element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(FaPackage.Literals.COMPONENT_PORT__COMPONENT_EXCHANGES)) {
      ret = getComponentExchanges(element_p);
    } else if (feature_p.equals(FaPackage.Literals.COMPONENT_PORT__ALLOCATED_FUNCTION_PORTS)) {
      ret = getAllocatedFunctionPorts(element_p);
    } else if (feature_p.equals(FaPackage.Literals.COMPONENT_PORT__DELEGATED_COMPONENT_PORTS)) {
      ret = getDelegatedComponentPorts(element_p);
    } else if (feature_p.equals(FaPackage.Literals.COMPONENT_PORT__DELEGATING_COMPONENT_PORTS)) {
      ret = getDelegatingComponentPorts(element_p);
    } else if (feature_p.equals(FaPackage.Literals.COMPONENT_PORT__ALLOCATING_PHYSICAL_PORTS)) {
      ret = getAllocatingPhysicalPorts(element_p);
    } else if (feature_p.equals(FaPackage.Literals.COMPONENT_PORT__REALIZED_COMPONENT_PORTS)) {
      ret = getRealizedComponentPorts(element_p);
    } else if (feature_p.equals(FaPackage.Literals.COMPONENT_PORT__REALIZING_COMPONENT_PORTS)) {
      ret = getRealizingComponentPorts(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = PortHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = InformationsExchangerHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = PartitionHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  /**
   * Returns all abstract connections linked to the component port
   */
  protected List<ComponentExchange> getComponentExchanges(ComponentPort element_p) {
    // linked hash set allows to keep ordering !
    HashSet<ComponentExchange> ret = new LinkedHashSet<ComponentExchange>();
    for (AbstractInformationFlow trace : element_p.getInformationFlows()) {
      if (trace instanceof ComponentExchange) {
        ret.add((ComponentExchange) trace);
      }
    }
    for (ComponentExchangeEnd end : FunctionalExt.getRelatedComponentExchangeEnds(element_p)) {
      EObject owner = end.eContainer();
      if (owner instanceof ComponentExchange) {
        ret.add((ComponentExchange) owner);
      }
    }
    return new ArrayList<ComponentExchange>(ret);
  }

  protected List<FunctionPort> getAllocatedFunctionPorts(ComponentPort element_p) {
    List <FunctionPort> ret = new ArrayList<FunctionPort>();
    for (PortAllocation portAllocation : element_p.getOutgoingPortAllocations()) {
      Port port = portAllocation.getAllocatedPort();
      if (port instanceof FunctionPort){
        ret.add((FunctionPort) port);
      }
    }
    return ret;
  }

  protected List<ComponentPort> getDelegatedComponentPorts(ComponentPort element_p) {
    List <ComponentPort> ret = new ArrayList<ComponentPort>();
    for (AbstractInformationFlow informationFlow : element_p.getOutgoingInformationFlows()) {
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

  protected List<ComponentPort> getDelegatingComponentPorts(ComponentPort element_p) {
    List <ComponentPort> ret = new ArrayList<ComponentPort>();
    for (AbstractInformationFlow informationFlow : element_p.getIncomingInformationFlows()) {
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

  protected List<PhysicalPort> getAllocatingPhysicalPorts(ComponentPort element_p) {
    List <PhysicalPort> result = new ArrayList<PhysicalPort>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof ComponentPortAllocation) {
        Port port = ((ComponentPortAllocation) trace).getAllocatingPort();
        if (port instanceof PhysicalPort){
          result.add((PhysicalPort) port);
        }
      }
    }
    return result;
  }

  protected List<ComponentPort> getRealizedComponentPorts(ComponentPort element_p) {
    List <ComponentPort> result = new ArrayList<ComponentPort>();
    for (PortRealization portAllocation : element_p.getOutgoingPortRealizations()) {
      Port port = portAllocation.getRealizedPort();
      if (port instanceof ComponentPort && !result.contains(port)){
        result.add((ComponentPort) port);
      }
    }
    return result;
  }

  protected List<ComponentPort> getRealizingComponentPorts(ComponentPort element_p) {
    List <ComponentPort> result = new ArrayList<ComponentPort>();
    for (PortRealization portAllocation : element_p.getIncomingPortRealizations()) {
      Port port = portAllocation.getRealizingPort();
      if (port instanceof ComponentPort && !result.contains(port)){
        result.add((ComponentPort) port);
      }
    }
    return result;
  }
}
