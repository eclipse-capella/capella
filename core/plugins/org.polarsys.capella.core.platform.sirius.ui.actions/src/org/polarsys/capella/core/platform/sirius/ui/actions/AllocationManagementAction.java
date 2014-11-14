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
package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.pa.deployment.DeploymentFactory;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

/**
 */
public class AllocationManagementAction {
  /**
   * 
   */
  public AllocationManagementAction() {
    // do nothing
  }

  /**
   * 
   */
  public static AllocationManagementAction getInstance() {
    return new AllocationManagementAction();
  }

  /**
   * Action to perform allocation of all the targetElements_p(list of functions) to sourceElement_p(component or role(oa layer))
   * @param targetElements_p list of functions
   * @param sourceElement_p a component
   */
  public void allocatingFunctionsToComponent(List<EObject> targetElements_p, EObject sourceElement_p) {
    if (sourceElement_p != null) {
      if (sourceElement_p instanceof Component) {
        Component comp = (Component) sourceElement_p;
        for (EObject modelElement : targetElements_p) {
          if (modelElement instanceof AbstractFunction) {
            AbstractFunction function = (AbstractFunction) modelElement;
            // continue only is current function is not allocated by any other component
            if (function.getAllocationBlocks().isEmpty()) {
              // create ComponentFunctionalAllocation
              ComponentFunctionalAllocation alloction = FaFactory.eINSTANCE.createComponentFunctionalAllocation();
              // add ComponentFunctionalAllocation to Component
              comp.getOwnedFunctionalAllocation().add(alloction);
              // set source
              alloction.setSourceElement(comp);
              // set target
              alloction.setTargetElement(function);
            }
          }
        }
      }
      // functions can also be allocated by roles
      else if (sourceElement_p instanceof Role) {
        Role role = (Role) sourceElement_p;
        for (EObject modelElement : targetElements_p) {
          if (modelElement instanceof AbstractFunction) {
            AbstractFunction function = (AbstractFunction) modelElement;
            // continue only is current function is not allocated by any other component
            if (function.getAllocationBlocks().isEmpty()) {
              // create ComponentFunctionalAllocation
              ActivityAllocation newAllocation = OaFactory.eINSTANCE.createActivityAllocation();
              // add ComponentFunctionalAllocation to Component
              role.getOwnedActivityAllocations().add(newAllocation);
              // set source
              newAllocation.setSourceElement(role);
              // set target
              newAllocation.setTargetElement(function);
            }
          }
        }
      }
    }
  }

  /**
   * Action to perform allocation of all the targetElements_p(list of exchangeItems) to sourceElements_p(Interfaces)
   * @param selectedCapellaElement_p
   * @param selectedElements_p
   */
  public void allocatingExchangeItemsToInterfaces(List<EObject> targetElements_p, List<EObject> sourceElements_p) {
    if (!sourceElements_p.isEmpty() && !targetElements_p.isEmpty()) {
      for (EObject source : sourceElements_p) {
        if (source instanceof Interface) {
          // interface
          Interface sourceElement = (Interface) source;
          for (EObject target : targetElements_p) {
            if (target instanceof ExchangeItem) {
              // exchange item
              ExchangeItem targetElement = (ExchangeItem) target;
              // exchange item allocation of current interface
              EList<ExchangeItemAllocation> allocations = sourceElement.getOwnedExchangeItemAllocations();
              // new exchange item allocation
              ExchangeItemAllocation newAllocation = CsFactory.eINSTANCE.createExchangeItemAllocation();
              // add new allocation to interface
              allocations.add(newAllocation);
              // set its allocated item as current exchangeItem
              newAllocation.setAllocatedItem(targetElement);
            }
          }
        }
      }
    }
  }

  /**
   * Action to perform deployment of physical component
   * @param targetElements_p
   * @param sourceElements_p
   */
  public void allocatingPCPartsToPCPart(List<EObject> targetElements_p, EObject sourceElement_p) {
    if (sourceElement_p != null) {
      if (sourceElement_p instanceof Part) {
        Part sourcePart = (Part) sourceElement_p;
        for (EObject modelElement : targetElements_p) {
          if (modelElement instanceof Part) {
            Part targetPart = (Part) modelElement;
            // continue only is current function is not allocated by any other component
            if (targetPart.getDeployingLinks().isEmpty()) {
              if (PhysicalComponentExt.isDeploymentPossible(sourcePart, targetPart)) {
                // create ComponentFunctionalAllocation
                PartDeploymentLink deployment = DeploymentFactory.eINSTANCE.createPartDeploymentLink();
                // add ComponentFunctionalAllocation to Component
                sourcePart.getOwnedDeploymentLinks().add(deployment);
                // set DeployedElement
                deployment.setDeployedElement(targetPart);
                // set Location
                deployment.setLocation(sourcePart);
              }
            }
          }
        }
      }
    }
  }

  /**
   * Action to perform allocation of functional exchanges.
   * @param selectedCapellaElement_p
   * @param object_p
   */
  public void allocatingFEsToComponentExchanges(List<EObject> targetElements_p, EObject sourceElement_p) {
    if (sourceElement_p != null) {
      if (sourceElement_p instanceof ComponentExchange) {
        ComponentExchange compExc = (ComponentExchange) sourceElement_p;
        for (EObject object : targetElements_p) {
          if (object instanceof FunctionalExchange) {
            FunctionalExchange functExc = (FunctionalExchange) object;
            ComponentExchangeFunctionalExchangeAllocation allocation = FaFactory.eINSTANCE.createComponentExchangeFunctionalExchangeAllocation();
            // add allocation to component exchange
            compExc.getOwnedComponentExchangeFunctionalExchangeAllocations().add(allocation);
            // set source
            allocation.setSourceElement(compExc);
            // set target
            allocation.setTargetElement(functExc);
            // Create ports allocations if requested.
            if (CapellaModelPreferencesPlugin.getDefault().isSynchronizationOfComponentPortToFunctionPortAllowed()) {
              ComponentExchangeExt.synchronizePortAllocations(compExc, functExc);
            }
          }
        }
      }
    }
  }

  /**
   * Action to perform allocation of component Exchanges.
   * @param selectedCapellaElement_p
   * @param object_p
   */
  public void allocatingCEsToPhysicalLinks(List<EObject> targetElements_p, EObject sourceElement_p) {
    if (sourceElement_p != null) {
      if (sourceElement_p instanceof PhysicalLink) {
        PhysicalLink physicalLink = (PhysicalLink) sourceElement_p;
        for (EObject object : targetElements_p) {
          if (object instanceof ComponentExchange) {
            ComponentExchangeAllocation allocation = FaFactory.eINSTANCE.createComponentExchangeAllocation();
            // add allocation to component exchange
            physicalLink.getOwnedComponentExchangeAllocations().add(allocation);
            // set source
            allocation.setSourceElement(physicalLink);
            // set target
            allocation.setTargetElement((ComponentExchange) object);
          }
        }
      }
    }
  }
}
