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
package org.polarsys.capella.core.projection.exchanges;

import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getCache;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;

/**
 * This class is the <code>IExchangesCreator</code> implementation specific to node physical components.<br>
 * This implementation creates physical links.
 */
public class PhysicalLinksCreator extends DefaultExchangesCreator {
  
  private Part part = null;
  
  /**
   * Constructor
   * @param component_p
   */
  public PhysicalLinksCreator(Component component_p, Part part_p) {
    super(component_p);
    this.part = part_p;
  }

  /**
   * This implementation creates physical links.
   * @see org.polarsys.capella.core.projection.commands.utils.DefaultExchangesCreator#createExchanges()
   */
  @Override
  public void createExchanges() {
    if (isValidBound(_component)) {
      createPhysicalLinksFromCExchanges(_component);
    }
  }

  /**
   * @see org.polarsys.capella.core.projection.exchanges.DefaultExchangesCreator#isValidCreation(org.polarsys.capella.core.data.fa.FunctionalExchange,
   *      org.polarsys.capella.core.data.cs.Component, org.polarsys.capella.core.data.cs.Component)
   */
  @Override
  protected boolean isValidCreation(AbstractEventOperation fe_p, Component component_p, Component allocating_p) {
    return isValidBound(component_p) && isValidBound(allocating_p);
  }
  
  /**
   * @param container
   * 
   * This method check all subcomponent and try to create physical links and allocate them to a CE at the
   * contained level component exchanges If the container hasn't subcomponent, it will try to create a PL and
   * allocate to a CE at the same level
   */
  protected void createPhysicalLinksFromCExchanges(Component container) {
    if (container != null) {
      if(container instanceof PhysicalComponent) {
        // Gets the deployments of the node
        EList<AbstractDeploymentLink> deployments = part.getDeploymentLinks();

        for (AbstractDeploymentLink deployment : deployments) {
          if (deployment instanceof PartDeploymentLink) {
            PartDeploymentLink deploymentLink = (PartDeploymentLink) deployment;
            DeployableElement deployedElement = deploymentLink.getDeployedElement();
            if (deployedElement instanceof Part) {
              Type containedPC = ((Part) deployedElement).getType();
              if (containedPC instanceof PhysicalComponent) {
                createPhysicalLinksFromCExchanges(container, (PhysicalComponent)containedPC);
              }
            } else if (deployedElement instanceof PhysicalComponent) {
              createPhysicalLinksFromCExchanges(container, (PhysicalComponent) deployedElement);
            }
          }
        }
      }
      // Process contained actors
      createPLsFromCEDiffLevels(container);
      createPLsFromCESameLevel(container);
      
    }
  }

  /**
   * Create Physical Links from Component Exchanges at different levels
   */
  protected void createPLsFromCEDiffLevels(Component container) {
    List<Component> subComponents = ComponentExt.getSubDefinedComponents(container);
    if (!subComponents.isEmpty()) {
      subComponents.stream().filter(c -> ComponentExt.isActor(c)).forEach(actor -> {
        createPhysicalLinksFromCExchanges(container, actor);
      });
    }
  }
  
  /**
   * Create Physical Links from Component Exchanges at the same level
   */
  protected void createPLsFromCESameLevel(Component container) {
    if (ComponentExt.isActor(container)) {
      createPhysicalLinksFromCExchanges(container, container);
    }
  }
  
  /**
   * @param sourceContainer
   * @param sourceContained
   */
  protected void createPhysicalLinksFromCExchanges(Component sourceContainer, Component sourceContained) {
    if (isValidBound(sourceContainer) && isValidBound(sourceContained)) {
      // Process the contained component
      // This reference will allows to handle the processed connections
      for (ComponentPort port : ComponentExt.getOwnedComponentPort(sourceContained)) {
        // get all the connection of the port
        for (ComponentExchange connection : port.getComponentExchanges()) {
          // filter delegation and unSet type of connection
          if ((connection.getKind() != ComponentExchangeKind.DELEGATION)
              && (connection.getKind() != ComponentExchangeKind.UNSET)) {
            // check if physicalLink creation is necessary
            if (!doesNodeAlreadyHaveAPhysicalLinkForComponentExchange(sourceContainer, connection)) {
              // get the opposite port [which could be
              // source or target of the Connection]
              InformationsExchanger target = FunctionalExt.getOtherBound(connection, port);
              if (target instanceof ComponentPort) {
                // get the container of the target port
                EObject targetContained = target.eContainer();
                // find the target Node

                if (targetContained instanceof Component && isValidBound((Component) targetContained)) {
                  if (targetContained instanceof PhysicalComponent) {
                    // For all parts, find the deploying component
                    for (Part partition : ((PhysicalComponent) targetContained).getRepresentingParts()) {
                      for (DeploymentTarget deploying : getCache(PartExt::getDeployingElements, (Part) partition)) {
                        if (deploying instanceof Part) {
                          Part deployingPart = (Part) deploying;
                          if (deployingPart.getAbstractType() instanceof PhysicalComponent) {
                            PhysicalComponent targetContainerPC = (PhysicalComponent) deployingPart.getAbstractType();
                            doCreatePhysicalLink(connection, sourceContainer, targetContainerPC);
                          }
                        }
                      }
                    }
                  }
                  // if the container is the same as contained (if the component doesn't have subcomponents)
                  if (sourceContainer.equals(sourceContained) && ComponentExt.isActor(sourceContained)) {
                    doCreatePhysicalLink(connection, sourceContainer, (Component) targetContained, port);
                  } else if (ComponentExt.isActor(targetContained)
                      && targetContained.eContainer() instanceof Component) {
                    Component targetContainer = (Component) targetContained.eContainer();
                    doCreatePhysicalLink(connection, sourceContainer, targetContainer);
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  
  private void doCreatePhysicalLink(ComponentExchange componentExchange_p, Component exchangeOutput_p,
      Component exchangeInput_p, ComponentPort sourcePort_p) {
    if (componentExchange_p.getSource().equals(sourcePort_p)) {
      doCreatePhysicalLink(componentExchange_p, exchangeOutput_p, exchangeInput_p);
    } else {
      doCreatePhysicalLink(componentExchange_p, exchangeInput_p, exchangeOutput_p);
    }
  }

  /**
   * Create a physical link corresponding to the given component exchange, between the given components
   * @param componentExchange_p the source component exchange
   * @param exchangeOutput_p the output component
   * @param exchangeInput_p the input component
   */
  protected void doCreatePhysicalLink(ComponentExchange componentExchange_p, Component exchangeOutput_p, Component exchangeInput_p) {
    // Precondition:
    if (exchangeOutput_p == exchangeInput_p) {
      // Not necessary to create a physical link for exchanges inside the
      // same container.
      return;
    }
    PhysicalLink physicalLink = CsFactory.eINSTANCE.createPhysicalLink(componentExchange_p.getLabel());
    PhysicalPort outP = CsFactory.eINSTANCE.createPhysicalPort(componentExchange_p.getSource().getLabel());
    PhysicalPort inP = CsFactory.eINSTANCE.createPhysicalPort(componentExchange_p.getTarget().getLabel());
    physicalLink.getLinkEnds().add(outP);
    physicalLink.getLinkEnds().add(inP);

    exchangeInput_p.getOwnedFeatures().add(inP);
    exchangeOutput_p.getOwnedFeatures().add(outP);
    CapellaElementExt.creationService(inP);
    CapellaElementExt.creationService(outP);

    PhysicalLinkExt.attachToDefaultContainer(physicalLink);
    CapellaElementExt.creationService(physicalLink);

    // Creates the exchange allocation
    ComponentExchangeAllocation cea = FaFactory.eINSTANCE.createComponentExchangeAllocation();
    cea.setSourceElement(physicalLink);
    cea.setTargetElement(componentExchange_p);
    physicalLink.getOwnedComponentExchangeAllocations().add(cea);
    CapellaElementExt.creationService(cea);

    // source side delegation
    InformationsExchanger target = componentExchange_p.getTarget();
    createComponentPortAllocation(target, inP);

    // target side Delegation
    InformationsExchanger source = componentExchange_p.getSource();
    createComponentPortAllocation(source, outP);

  }

  /**
   * @param informationExchange_p
   * @param physicalPort_p
   * @param connection_p
   */
  private ComponentPortAllocation createComponentPortAllocation(InformationsExchanger informationExchange_p, PhysicalPort physicalPort_p) {
    ComponentPortAllocation allocation = FaFactory.eINSTANCE.createComponentPortAllocation();
    allocation.setSourceElement(physicalPort_p);
    allocation.setTargetElement((TraceableElement) informationExchange_p);
    physicalPort_p.getOwnedComponentPortAllocations().add(allocation);
    CapellaElementExt.creationService(allocation);
    return allocation;
  }

  /**
   * This method allows to know if the given component exchange has already been allocated to a physical link linked to the given physical component.
   * @param physicalComponent_p the physical component
   * @param componentExchange_p the component exchange
   * @return true if its has already been allocated, false otherwise
   */
  protected boolean doesNodeAlreadyHaveAPhysicalLinkForComponentExchange(Component physicalComponent_p,
      ComponentExchange componentExchange_p) {
    boolean result = false;
    // Get the semantic editing domain to access the cross referencer.
    SemanticEditingDomain editingDomain = (SemanticEditingDomain) AdapterFactoryEditingDomain
        .getEditingDomainFor(componentExchange_p);
    // Get the cross referencer.
    ECrossReferenceAdapter crossReferencer = editingDomain.getCrossReferencer();
    // Search inverses relations on given component exchange.
    Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(componentExchange_p, true);
    for (Setting setting : inverseReferences) {
      // Search for a relation targeting the ComponentExchangeAllocation metaclass.
      if (setting.getEObject() instanceof ComponentExchangeAllocation) {
        result = true;
        break;
      }
    }
    return result;
  }
}