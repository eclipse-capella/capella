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
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;
import org.polarsys.capella.core.model.preferences.helpers.PreferencesHelper;

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
   * Action to perform allocation of all the targetElements(list of functions) to sourceElement(component or role(oa
   * layer))
   * 
   * @param targetElements
   *          list of functions
   * @param sourceElement
   *          a component
   */
  public void allocatingFunctionsToComponent(List<EObject> targetElements, EObject sourceElement) {
    if (sourceElement != null) {
      if (sourceElement instanceof Component) {
        Component comp = (Component) sourceElement;
        for (EObject modelElement : targetElements) {
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
      else if (sourceElement instanceof Role) {
        Role role = (Role) sourceElement;
        for (EObject modelElement : targetElements) {
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
   * Action to perform allocation of all the targetElements(list of exchangeItems) to sourceElements(Interfaces)
   * 
   * @param selectedCapellaElement
   * @param selectedElements
   */
  public void allocatingExchangeItemsToInterfaces(List<EObject> targetElements, List<EObject> sourceElements) {
    if (!sourceElements.isEmpty() && !targetElements.isEmpty()) {
      for (EObject source : sourceElements) {
        if (source instanceof Interface) {
          // interface
          Interface sourceElement = (Interface) source;
          for (EObject target : targetElements) {
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
   * 
   * @param targetElements
   * @param sourceElements
   */
  public void allocatingPCPartsToPCPart(List<EObject> targetElements, EObject sourceElement) {
    if (sourceElement != null) {
      if (sourceElement instanceof Part) {
        Part sourcePart = (Part) sourceElement;
        for (EObject modelElement : targetElements) {
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
   * 
   * @param selectedCapellaElement
   * @param object
   */
  public void allocatingFEsToComponentExchanges(List<EObject> targetElements, EObject sourceElement) {
    if (sourceElement instanceof ComponentExchange) {
      ComponentExchange componentExchange = (ComponentExchange) sourceElement;
      boolean shouldSyncronize = PreferencesHelper
          .isSynchronizationOfComponentPortToFunctionPortAllowed();

      for (EObject object : targetElements) {
        if (object instanceof FunctionalExchange) {
          FunctionalExchange functionalExchange = (FunctionalExchange) object;
          ComponentExchangeFunctionalExchangeAllocation allocation = FaFactory.eINSTANCE
              .createComponentExchangeFunctionalExchangeAllocation();

          allocation.setSourceElement(componentExchange);
          allocation.setTargetElement(functionalExchange);
          componentExchange.getOwnedComponentExchangeFunctionalExchangeAllocations().add(allocation);

          if (shouldSyncronize) {
            ComponentExchangeExt.synchronizePortAllocations(componentExchange, functionalExchange);
          }
        }
      }
    }
  }

  /**
   * Action to perform allocation of component Exchanges.
   * 
   * @param selectedCapellaElement
   * @param object
   */
  public void allocatingCEsToPhysicalLinks(List<EObject> targetElements, EObject sourceElement) {
    if (sourceElement instanceof PhysicalLink) {
      PhysicalLink physicalLink = (PhysicalLink) sourceElement;
      boolean shouldSyncronize = PreferencesHelper
          .isSynchronizationOfPhysicalPortToComponentPortOnPhysicalLinkAllowed();

      for (EObject object : targetElements) {
        if (object instanceof ComponentExchange) {
          ComponentExchange componentExchange = (ComponentExchange) object;
          ComponentExchangeAllocation allocation = FaFactory.eINSTANCE.createComponentExchangeAllocation();

          allocation.setSourceElement(physicalLink);
          allocation.setTargetElement(componentExchange);
          physicalLink.getOwnedComponentExchangeAllocations().add(allocation);

          if (shouldSyncronize) {
            PhysicalLinkExt.synchronizeAllocations(physicalLink, componentExchange);
          }
        }
      }
    }
  }
}
