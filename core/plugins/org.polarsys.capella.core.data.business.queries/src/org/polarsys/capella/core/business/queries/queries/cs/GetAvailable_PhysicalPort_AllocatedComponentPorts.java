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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
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
      if (trace instanceof ComponentPortAllocation) {
        ComponentPortAllocation portAllocation = (ComponentPortAllocation) trace;
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
  private Set<ComponentPort> getAvailablePorts(PhysicalPort port) {
    Component container = PortExt.getRelatedComponent(port);
    if (container == null)
      return Collections.emptySet();
    Collection<Component> components = ComponentExt.getAllSubUsedComponents(container);
    components.add(container);

    Set<Component> deployedComponents = new HashSet<>();
    for (Component component : components) {
      if (component instanceof SystemComponent) {
        deployedComponents.add(component);
      } else if (component instanceof LogicalComponent) {
        deployedComponents.addAll(LogicalComponentExt.getAllSubComponents((LogicalComponent) component));
      } else if (component instanceof PhysicalComponent) {
        deployedComponents.add(component);
        for (AbstractTypedElement abstractTypedElement : component.getAbstractTypedElements()) {
          if (abstractTypedElement instanceof Part) {
            Part typedPart = (Part) abstractTypedElement;
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

    return deployedComponents.stream().flatMap(c -> c.getContainedComponentPorts().stream())
        .collect(Collectors.toSet());
  }

}