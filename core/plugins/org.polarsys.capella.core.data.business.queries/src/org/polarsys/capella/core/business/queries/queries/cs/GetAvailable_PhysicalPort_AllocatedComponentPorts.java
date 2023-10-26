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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.PortExt;

public class GetAvailable_PhysicalPort_AllocatedComponentPorts extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    return getAvailableElements(capellaElement);
  }

  /**
   * {@inheritDoc}
   */
  public List<Object> getAvailableElements(CapellaElement element) {
    if (element instanceof PhysicalPort) {
      return List.copyOf(getAvailablePorts((PhysicalPort) element));
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    if (!(element instanceof PhysicalPort))
      return Collections.emptyList();
    Set<Port> currentElements = new HashSet<>();
    PhysicalPort elt = (PhysicalPort) element;
    for (AbstractTrace trace : elt.getOutgoingTraces()) {
      if (trace instanceof ComponentPortAllocation portAllocation) {
        if (portAllocation.getAllocatedPort() != null) {
          currentElements.add(portAllocation.getAllocatedPort());
        }
      }
    }
    currentElements.remove(elt);
    return List.copyOf(currentElements);
  }

  /** 
   */
  private Set<Port> getAvailablePorts(PhysicalPort sourcePort) {

    List<PhysicalPort> targetPorts = getTheOthersPhysicalPort(sourcePort);
    List<Component> sourceDeployedComponents = getDeployedElements(sourcePort);
    List<Component> targetDeployedComponents = targetPorts.stream().flatMap(pp -> getDeployedElements(pp).stream())
        .toList();
    Set<ComponentExchange> availableElements = new HashSet<>();
    availableElements.addAll(PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(sourceDeployedComponents,
        targetDeployedComponents));
    availableElements.addAll(PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(targetDeployedComponents,
        sourceDeployedComponents));
    Set<Port> list = new HashSet<>();
    for (ComponentExchange ce : availableElements) {
      Component source = ComponentExchangeExt.getSourceComponent(ce);
      if (sourceDeployedComponents.contains(source))
        list.add(ce.getSourcePort());
      else
        list.add(ce.getTargetPort());
    }
    return list;
  }

  private List<PhysicalPort> getTheOthersPhysicalPort(PhysicalPort port) {
    PhysicalPort pp = port;
    List<PhysicalPort> result = new ArrayList<>();
    for (PhysicalLink pLink : port.getInvolvedLinks()) {
      for (AbstractPhysicalLinkEnd linkEnd : pLink.getLinkEnds()) {
        if (linkEnd instanceof PhysicalPort) {
          pp = (PhysicalPort) linkEnd;
        } else if (linkEnd instanceof PhysicalLinkEnd) {
          pp = ((PhysicalLinkEnd) linkEnd).getPort();
        }
        if (!pp.equals(port))
          result.add(pp);
      }
    }
    return result;
  }

  private List<Component> getDeployedElements(PhysicalPort port) {
    Component container = PortExt.getRelatedComponent(port);
    if (container == null)
      return Collections.emptyList();
    Collection<Component> sourceComponents = ComponentExt.getAllSubUsedComponents(container);
    sourceComponents.add(container);
    List<Component> deployedComponents = new ArrayList<>();
    for (Component component : sourceComponents) {
      if (component instanceof SystemComponent) {
        deployedComponents.add(component);
      } else if (component instanceof LogicalComponent) {
        deployedComponents.addAll(LogicalComponentExt.getAllSubComponents((LogicalComponent) component));
      } else if (component instanceof PhysicalComponent) {
        deployedComponents.add(component);
        for (AbstractTypedElement abstractTypedElement : component.getAbstractTypedElements()) {
          if (abstractTypedElement instanceof Part typedPart) {
            Stream
                .concat(PartExt.getSubUsedParts(typedPart).stream(),
                    PartExt.getAllDeployableElements(typedPart).stream())
                .filter(Part.class::isInstance).map(p -> PartExt.getComponentOfPart((Part) p))
                .filter(Objects::nonNull)
                .forEachOrdered(deployedComponents::add);
          }
        }
      }
    }
    return deployedComponents;
  }
}