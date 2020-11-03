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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_PhysicalPort_AllocatedComponentPorts extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (element instanceof PhysicalPort) {
      for (EObject port : getRule_MQRY_Port_AllocatedPorts_11((Port) element)) {
        availableElements.add((CapellaElement) port);
      }
    }
    availableElements.remove(element);
    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    List<EObject> currentElements = new ArrayList<EObject>();
    if (element instanceof PhysicalPort) {
      PhysicalPort elt = (PhysicalPort) element;
      for (AbstractTrace trace : elt.getOutgoingTraces()) {
        if (trace instanceof ComponentPortAllocation) {
          if (((ComponentPortAllocation) trace).getAllocatedPort() != null) {
            currentElements.add(((ComponentPortAllocation) trace).getAllocatedPort());
          }
        }
      }
      currentElements = ListExt.removeDuplicates(currentElements);
      currentElements.remove(elt);
    }
    return currentElements;
  }

  /** 
   */
  protected List<EObject> getRule_MQRY_Port_AllocatedPorts_11(Port element) {
    List<EObject> allPorts = new ArrayList<EObject>();
    EObject ownerObj = element.eContainer();
    if (ownerObj instanceof SystemComponent) {
      allPorts.addAll(((SystemComponent) ownerObj).getContainedComponentPorts());
      
    } else if (ownerObj instanceof LogicalComponent) {
      for (LogicalComponent lc : LogicalComponentExt.getAllSubComponents((LogicalComponent) ownerObj)) {
        allPorts.addAll(lc.getContainedComponentPorts());
      }
    } else if (ownerObj instanceof PhysicalComponent) {
      for (Component deployedCpnt : ComponentExt.getSubUsedAndDeployedComponents((Component) ownerObj)) {
        allPorts.addAll(deployedCpnt.getContainedComponentPorts());
      }
    }
    allPorts = ListExt.removeDuplicates(allPorts);
    return allPorts;
  }

}