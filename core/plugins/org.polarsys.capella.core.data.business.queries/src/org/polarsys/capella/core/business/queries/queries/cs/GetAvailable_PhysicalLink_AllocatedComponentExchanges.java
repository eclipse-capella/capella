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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_PhysicalLink_AllocatedComponentExchanges extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> getAvailableElements(CapellaElement element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element);
    if (null == arch) {
      return availableElements;
    }
    if (element instanceof PhysicalLink) {
      PhysicalLink link = (PhysicalLink) element;
      Component sourceComponent = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt
          .getSourceComponent(link);
      Component targetComponent = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt
          .getTargetComponent(link);
      if (null != sourceComponent && null != targetComponent) {
        availableElements.addAll(getAvailableExchanges(sourceComponent, targetComponent));
      }
      List<EObject> allReadyAllocatedConnection = new ArrayList<EObject>();
      for (EObject capellaElement : availableElements) {
        if (capellaElement instanceof ComponentExchange) {
          ComponentExchange connection = (ComponentExchange) capellaElement;
          EList<AbstractTrace> incomingTraces = connection.getIncomingTraces();
          for (AbstractTrace abstractTrace : incomingTraces) {
            if (abstractTrace instanceof ComponentExchangeAllocation
                && (((ComponentExchangeAllocation) abstractTrace).getComponentExchangeAllocator() != element)) {
              allReadyAllocatedConnection.add(capellaElement);
            }
          }
        }
      }
      availableElements.removeAll(allReadyAllocatedConnection);
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

  public static List<CapellaElement> getAvailableExchanges(Component sourceComponent, Component targetComponent) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    List<Component> sourceDeployedElements = new ArrayList<Component>(1);
    List<Component> targetDeployedElements = new ArrayList<Component>(1);
    Collection<Component> sourceComponents = ComponentExt.getAllSubUsedComponents(sourceComponent);
    sourceComponents.add(sourceComponent);
    Collection<Component> targetComponents = ComponentExt.getAllSubUsedComponents(targetComponent);
    targetComponents.add(targetComponent);
    
    for (Component component : sourceComponents) {
      if (component instanceof SystemComponent) {
        sourceDeployedElements.add(component);
      } else if (component instanceof LogicalComponent) {
        sourceDeployedElements.addAll(LogicalComponentExt.getAllSubComponents((LogicalComponent) component));
      } else if (component instanceof PhysicalComponent) {
        for (AbstractTypedElement abstractTypedElement : component.getAbstractTypedElements()) {
          if (abstractTypedElement instanceof Part) {
            for (DeployableElement deployableElement : PartExt.getAllDeployableElements((Part) abstractTypedElement)) {
              if (deployableElement instanceof Part) {
                AbstractType abstractType = ((Part) deployableElement).getAbstractType();
                if (abstractType instanceof Component) {
                  sourceDeployedElements.add((Component) abstractType);
                }
              }
            }
          }
        }
      }
    }
    for (Component component : targetComponents) {
      if (component instanceof SystemComponent) {
        targetDeployedElements.add(component);
      } else if (component instanceof LogicalComponent) {
        targetDeployedElements.addAll(LogicalComponentExt.getAllSubComponents((LogicalComponent) component));
      } else if (component instanceof PhysicalComponent) {
        for (AbstractTypedElement abstractTypedElement : component.getAbstractTypedElements()) {
          if (abstractTypedElement instanceof Part) {
            for (DeployableElement deployableElement : PartExt.getAllDeployableElements((Part) abstractTypedElement)) {
              if (deployableElement instanceof Part) {
                AbstractType abstractType = ((Part) deployableElement).getAbstractType();
                if (abstractType instanceof Component) {
                  targetDeployedElements.add((Component) abstractType);
                }
              }
            }
          }
        }
      }
    }
    availableElements.addAll(
        PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(sourceDeployedElements, targetDeployedElements));
    availableElements.addAll(
        PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(targetDeployedElements, sourceDeployedElements));
    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
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