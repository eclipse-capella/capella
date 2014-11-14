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
package org.polarsys.capella.core.business.queries.cs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;

/**
 *
 */
public class PhysicalLink_AllocatedComponentExchanges implements IBusinessQuery {

  
  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
    
    if (null == arch) {
      return availableElements;
    }

    if (element_p instanceof PhysicalLink) {
      // physical link
      PhysicalLink link = (PhysicalLink) element_p;
      // source component{PhysicalComponent & Actor} of physical link
      Component sourceComponent = PhysicalLinkExt.getSourceComponent(link);
      // target component{PhysicalComponent & Actor} of physical link
      Component targetComponent = PhysicalLinkExt.getTargetComponent(link);
      
      if (null  != sourceComponent && null != targetComponent) {
        availableElements.addAll(getAvailableExchanges(sourceComponent, targetComponent));
      }

      // remove already allocated connection
      List<CapellaElement> allReadyAllocatedConnection = new ArrayList<CapellaElement>();
      for ( CapellaElement capellaElement: availableElements) {
        if (capellaElement instanceof ComponentExchange) {
          ComponentExchange connection = (ComponentExchange) capellaElement;
          EList<AbstractTrace> incomingTraces = connection.getIncomingTraces();
          for (AbstractTrace abstractTrace : incomingTraces) {
            if (abstractTrace instanceof ComponentExchangeAllocation) {
              allReadyAllocatedConnection.add(capellaElement);
            }
          }
        }
      }
      availableElements.removeAll(allReadyAllocatedConnection);
      
      // remove connection related to element_p
      List<CapellaElement> currentElements = getCurrentElements(element_p, false);
      for (CapellaElement capellaElement : currentElements) {
        availableElements.remove(capellaElement);
      }
      
    }
    
    // remove duplicates 
    availableElements = ListExt.removeDuplicates(availableElements);
    
    return availableElements;
  }

  public static List<CapellaElement> getAvailableExchanges(Component sourceComponent, Component targetComponent) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    // collect deployed component in any of the source part
    List<Component> sourceDeployedElements =  new ArrayList<Component>(1);
    // collect deployed component in any of the target part
    List<Component> targetDeployedElements =  new ArrayList<Component>(1);

    // all the descendants of the @sourceComponent
    Collection<Component> sourceComponents = ComponentExt.getAllSubUsedComponents(sourceComponent);
    sourceComponents.add(sourceComponent);

    // all the descendants of the @targetComponent
    Collection<Component> targetComponents = ComponentExt.getAllSubUsedComponents(targetComponent);
    targetComponents.add(targetComponent);

    for (Component component : sourceComponents) {
      if (component instanceof System) {
        sourceDeployedElements.add(component);
      } else if (component instanceof LogicalComponent) {
        sourceDeployedElements.addAll(LogicalComponentExt.getAllSubComponents((LogicalComponent) component));
      } else if (component instanceof PhysicalComponent || component instanceof PhysicalActor) {
        // get deployed Components of source
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
      // if type is an actor add to list
      if (component instanceof AbstractActor){
        sourceDeployedElements.add(component);
      }
    }  

    for (Component component : targetComponents) {
      if (component instanceof System) {
        targetDeployedElements.add(component);
      } else if (component instanceof LogicalComponent) {
        targetDeployedElements.addAll(LogicalComponentExt.getAllSubComponents((LogicalComponent) component));
      } else if (component instanceof PhysicalComponent || component instanceof PhysicalActor) {
        // get deployed Components of target
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
      // if type is an actor add to list
      if (component instanceof AbstractActor) {
        targetDeployedElements.add(component);
      }
    }

    // find the connection between sourceList and targetList of component
    availableElements.addAll(PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(sourceDeployedElements, targetDeployedElements));
    availableElements.addAll(PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(targetDeployedElements, sourceDeployedElements ));

    return availableElements;
  }

  /**
   * {@inheritDoc}
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    
    if (element_p instanceof PhysicalLink) {
      PhysicalLink ele = (PhysicalLink) element_p;
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

  /**
   * {@inheritDoc}
   */
  public EClass getEClass() {
    return CsPackage.Literals.PHYSICAL_LINK;
  }

  /**
   * {@inheritDoc}
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS);
  }
}
