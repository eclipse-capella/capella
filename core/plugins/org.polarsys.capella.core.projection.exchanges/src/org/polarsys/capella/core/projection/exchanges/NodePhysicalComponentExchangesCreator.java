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
package org.polarsys.capella.core.projection.exchanges;

import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getCache;

import java.util.Collection;

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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;
import org.polarsys.capella.core.model.helpers.PortExt;

/**
 * This class is the <code>IExchangesCreator</code> implementation specific to node physical components.<br>
 * This implementation creates physical links.
 */
public class NodePhysicalComponentExchangesCreator extends DefaultExchangesCreator {

  private Part part = null;

  /**
   * Constructor
   * @param component
   */
  public NodePhysicalComponentExchangesCreator(Component component, Part part) {
    super(component);
    if (null != part) {
      this.part = part;
    }
  }

  /**
   * This implementation creates physical links.
   * @see org.polarsys.capella.core.projection.commands.utils.DefaultExchangesCreator#createExchanges()
   */
  @Override
  public void createExchanges() {
    if (_component instanceof PhysicalComponent) {
      // Casts the component as a physical component
      PhysicalComponent node = (PhysicalComponent) _component;
      createExchangesForPhysicalComponents(node);
      // Creates the exchanges for directly allocated functions
      super.createExchanges();
    }
  }

  /**
   * Returns whether a component which will be a bound of a created exchange is valid or not
   * @param component
   * @return
   */
  @Override
  protected boolean isValidBound(Component component) {
    if (component instanceof PhysicalComponent) {
      PhysicalComponent cpnt = (PhysicalComponent) component;
      PhysicalComponentNature nature = cpnt.getNature();
      if ((cpnt instanceof PhysicalComponent) && (nature == PhysicalComponentNature.NODE)) {
        return true;

      }
    }
    return false;
  }

  /**
   * @see org.polarsys.capella.core.projection.exchanges.DefaultExchangesCreator#isValidCreation(org.polarsys.capella.core.data.fa.FunctionalExchange, org.polarsys.capella.core.data.cs.Component, org.polarsys.capella.core.data.cs.Component)
   */
  @Override
  protected boolean isValidCreation(AbstractEventOperation fe, Component component, Component allocating) {
    return isValidBound(component) && isValidBound(allocating);
  }

  /**
   * Creates the exchanges related to exchanges between deployed physical components.
   * @param containerPC the node physical component from which the search of deployed physical component will be done
   */
  protected void createExchangesForPhysicalComponents(PhysicalComponent containerPC) {
    // Gets the deployments of the node
    EList<AbstractDeploymentLink> deployments = part.getDeploymentLinks();

    for (AbstractDeploymentLink deployment : deployments) {
      if (deployment instanceof PartDeploymentLink) {
        PartDeploymentLink deploymentLink = (PartDeploymentLink) deployment;
        DeployableElement deployedElement = deploymentLink.getDeployedElement();
        if (deployedElement instanceof Part) {
          Part part = (Part) deployedElement;
          Type containedPC = part.getType();
          if (null != containedPC) {
            createExchangesForPhysicalComponents(containerPC, containedPC);
          }
        } else if (deployedElement instanceof PhysicalComponent) {
          createExchangesForPhysicalComponents(containerPC, (PhysicalComponent) deployedElement);
        }
      }
    }
    // Process contained Physical Actors
    containerPC.getOwnedPhysicalComponents().stream().filter(c -> ComponentExt.isActor(c)).forEach(actor -> {
      createExchangesForPhysicalComponents(containerPC, actor);
    });
  }

  /**
   * @param sourceContainerPC
   * @param sourceContainedPC
   */
  private void createExchangesForPhysicalComponents(PhysicalComponent sourceContainerPC, Type sourceContainedPC) {
    if (sourceContainedPC instanceof PhysicalComponent) {
      PhysicalComponent containedPhysicalComponent = (PhysicalComponent) sourceContainedPC;
      // This reference will allows to handle the processed connections
      for (ComponentPort port : ComponentExt.getOwnedComponentPort(containedPhysicalComponent)) {
        // Get all the connection of the port
        for (ComponentExchange connection : port.getComponentExchanges()) {
          // filter delegation and unSet type of connection
          if ((connection.getKind() != ComponentExchangeKind.DELEGATION)
              && (connection.getKind() != ComponentExchangeKind.UNSET)) {
            // check if physicalLink creation is necessary
            if (!doesNodeAlreadyHaveAPhysicalLinkForComponentExchange(sourceContainerPC, connection)) {
              // get the opposite port [which could be
              // source or target of the Connection]
              InformationsExchanger target = FunctionalExt.getOtherBound(connection, port);
              if (target instanceof ComponentPort) {
                // get the container of the target port
                EObject targetContainedPC = target.eContainer();
                // find the target Node
                // [PhysicalComponent]
                if (targetContainedPC instanceof PhysicalComponent) {
                  // For all parts, find the deploying component
                  for (Part partition : ((PhysicalComponent) targetContainedPC).getRepresentingParts()) {
                    for (DeploymentTarget deploying : getCache(PartExt::getDeployingElements, (Part) partition)) {
                      if (deploying instanceof Part) {
                        Part deployingPart = (Part) deploying;
                        if (deployingPart.getAbstractType() instanceof PhysicalComponent) {
                          PhysicalComponent targetContainerPC = (PhysicalComponent) deployingPart.getAbstractType();
                          createPhysicalLink(connection, sourceContainerPC, targetContainerPC, port);
                        }
                      }
                    }
                  }
                  // Create the exchange if container is an Actor contained in a PhyscialComponent
                  if (ComponentExt.isActor(targetContainedPC)
                      && targetContainedPC.eContainer() instanceof PhysicalComponent) {
                    PhysicalComponent targetContainerPC = (PhysicalComponent) targetContainedPC.eContainer();
                    createPhysicalLink(connection, sourceContainerPC, targetContainerPC, port);
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  private void createPhysicalLink(ComponentExchange connection, PhysicalComponent sourceContainerPC,
      PhysicalComponent targetContainerPC, ComponentPort sourcePort) {
    // Create an exchange if there is no connection created
    // TODO In multipart, create physical links with part related, not type
    if (isValidCreation(connection, sourceContainerPC, targetContainerPC)
        && !doesNodeAlreadyHaveAPhysicalLinkForComponentExchange(sourceContainerPC, connection)) {
      if (connection.getSource().equals(sourcePort)) {
        doCreateExchange(connection, sourceContainerPC, targetContainerPC);
      } else {
        doCreateExchange(connection, targetContainerPC, sourceContainerPC);
      }
    }
  }
  
  /**
   * This implementation creates physical links related to the given functional exchange.
   * @see org.polarsys.capella.core.projection.commands.utils.DefaultExchangesCreator#doCreatePhysicalLink(org.polarsys.capella.core.data.fa.FunctionalExchange,
   *      org.polarsys.capella.core.data.cs.Component, org.polarsys.capella.core.data.cs.Component)
   */
  @Override
  protected void doCreateExchange(FunctionalExchange functionalExchange, Component exchangeOutput, Component exchangeInput) {
    PhysicalLink physicalLink = CsFactory.eINSTANCE.createPhysicalLink(functionalExchange.getLabel());
    PhysicalPort outP = CsFactory.eINSTANCE.createPhysicalPort(functionalExchange.getSource().getName());
    PhysicalPort inP = CsFactory.eINSTANCE.createPhysicalPort(functionalExchange.getTarget().getName());
    physicalLink.getLinkEnds().add(outP);
    physicalLink.getLinkEnds().add(inP);
    exchangeInput.getOwnedFeatures().add(inP);
    exchangeOutput.getOwnedFeatures().add(outP);
    PhysicalLinkExt.attachToDefaultContainer(physicalLink);

    // Creates the ports allocation
    PortExt.attachPort(outP, functionalExchange.getSource());
    PortExt.attachPort(inP, functionalExchange.getTarget());
  }

  /**
   * Create a physical link corresponding to the given component exchange, between the given components
   * @param componentExchange the source component exchange
   * @param exchangeOutput the output component
   * @param exchangeInput the input component
   */
  protected void doCreateExchange(ComponentExchange componentExchange, Component exchangeOutput, Component exchangeInput) {
    // Precondition:
    if (exchangeOutput == exchangeInput) {
      // Not necessary to create a physical link for exchanges inside the
      // same container.
      return;
    }
    PhysicalLink physicalLink = CsFactory.eINSTANCE.createPhysicalLink(componentExchange.getLabel());
    PhysicalPort outP = CsFactory.eINSTANCE.createPhysicalPort(componentExchange.getSource().getLabel());
    PhysicalPort inP = CsFactory.eINSTANCE.createPhysicalPort(componentExchange.getTarget().getLabel());
    physicalLink.getLinkEnds().add(outP);
    physicalLink.getLinkEnds().add(inP);

    exchangeInput.getOwnedFeatures().add(inP);
    exchangeOutput.getOwnedFeatures().add(outP);
    CapellaElementExt.creationService(inP);
    CapellaElementExt.creationService(outP);

    PhysicalLinkExt.attachToDefaultContainer(physicalLink);
    CapellaElementExt.creationService(physicalLink);

    // Creates the exchange allocation
    ComponentExchangeAllocation cea = FaFactory.eINSTANCE.createComponentExchangeAllocation();
    cea.setSourceElement(physicalLink);
    cea.setTargetElement(componentExchange);
    physicalLink.getOwnedComponentExchangeAllocations().add(cea);
    CapellaElementExt.creationService(cea);

    // source side delegation
    InformationsExchanger target = componentExchange.getTarget();
    createComponentPortAllocation(target, inP);

    // target side Delegation
    InformationsExchanger source = componentExchange.getSource();
    createComponentPortAllocation(source, outP);

  }

  /**
   * @param informationExchange
   * @param physicalPort
   * @param connection
   */
  private ComponentPortAllocation createComponentPortAllocation(InformationsExchanger informationExchange, PhysicalPort physicalPort) {
    ComponentPortAllocation allocation = FaFactory.eINSTANCE.createComponentPortAllocation();
    allocation.setSourceElement(physicalPort);
    allocation.setTargetElement((TraceableElement) informationExchange);
    physicalPort.getOwnedComponentPortAllocations().add(allocation);
    CapellaElementExt.creationService(allocation);
    return allocation;
  }

  /**
   * This method allows to know if the given component exchange has already been allocated to a physical link linked to the given physical component.
   * @param physicalComponent the physical component
   * @param componentExchange the component exchange
   * @return true if its has already been allocated, false otherwise
   */
  protected boolean doesNodeAlreadyHaveAPhysicalLinkForComponentExchange(PhysicalComponent physicalComponent, ComponentExchange componentExchange) {
    boolean result = false;
    // Get the semantic editing domain to access the cross referencer.
    SemanticEditingDomain editingDomain = (SemanticEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(componentExchange);
    // Get the cross referencer.
    ECrossReferenceAdapter crossReferencer = editingDomain.getCrossReferencer();
    // Search inverses relations on given component exchange.
    Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(componentExchange, true);
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