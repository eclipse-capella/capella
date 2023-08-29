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
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_PhysicalLink_AllocatedComponentExchanges extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    return getAvailableElements(capellaElement);
  }

  /**
   * {@inheritDoc}
   */
  public List<Object> getAvailableElements(CapellaElement element) {
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element);
    if (null == arch) {
      return Collections.emptyList();
    }
    if (!(element instanceof PhysicalLink))
      return Collections.emptyList();

    PhysicalLink link = (PhysicalLink) element;
    Component sourceComponent = PhysicalLinkExt.getSourceComponent(link);
    Component targetComponent = PhysicalLinkExt.getTargetComponent(link);
    if (null == sourceComponent || null == targetComponent) {
      return Collections.emptyList();
    }
    Set<ComponentExchange> availableElements = getAvailableExchanges(sourceComponent, targetComponent);
    Iterator<ComponentExchange> it = availableElements.iterator();
    while (it.hasNext()) {
      ComponentExchange connection = it.next();
      EList<AbstractTrace> incomingTraces = connection.getIncomingTraces();
      for (AbstractTrace abstractTrace : incomingTraces) {
        if (abstractTrace instanceof ComponentExchangeAllocation
            && (((ComponentExchangeAllocation) abstractTrace).getComponentExchangeAllocator() != link)) {
          it.remove();
        }
      }
    }
    return List.copyOf(availableElements);
  }

  private Set<ComponentExchange> getAvailableExchanges(Component sourceComponent, Component targetComponent) {
    Set<ComponentExchange> availableElements = new HashSet<>();
    Collection<Component> sourceComponents = ComponentExt.getAllSubUsedComponents(sourceComponent);
    sourceComponents.add(sourceComponent);
    Collection<Component> targetComponents = ComponentExt.getAllSubUsedComponents(targetComponent);
    targetComponents.add(targetComponent);
    List<Component> sourceDeployedElements = getDeployedElements(sourceComponents);
    List<Component> targetDeployedElements = getDeployedElements(targetComponents);

    availableElements.addAll(
        PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(sourceDeployedElements, targetDeployedElements));
    availableElements.addAll(
        PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(targetDeployedElements, sourceDeployedElements));
    return availableElements;
  }

  private List<Component> getDeployedElements(Collection<Component> components) {
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
    return List.copyOf(deployedComponents);
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    List<CapellaElement> currentElements = new ArrayList<>();
    if (element instanceof PhysicalLink) {
      PhysicalLink ele = (PhysicalLink) element;
      EList<ComponentExchangeAllocation> ownedComponentExchangeAllocation = ele.getOwnedComponentExchangeAllocations();
      for (ComponentExchangeAllocation ownedComponentExchangeAll : ownedComponentExchangeAllocation) {
        ComponentExchange componentExchangeAllocated = ownedComponentExchangeAll.getComponentExchangeAllocated();
        if (componentExchangeAllocated != null) {
          currentElements.add(componentExchangeAllocated);
        }
      }
    }
    return currentElements;
  }

}