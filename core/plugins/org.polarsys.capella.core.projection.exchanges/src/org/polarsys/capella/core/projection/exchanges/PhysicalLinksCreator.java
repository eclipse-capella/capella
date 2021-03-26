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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
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
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
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
   * @param component
   */
  public PhysicalLinksCreator(Component component, Part part) {
    super(component);
    this.part = part;
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
  protected boolean isValidCreation(AbstractEventOperation fe, Component component, Component allocating) {
    return isValidBound(component) && isValidBound(allocating);
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
      if (!isContainedByALogicalNonActorComponent(container)) {
        // Process contained actors
        createPLsFromCEDiffLevels(container);
        createPLsFromCESameLevel(container);
      }
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
    if (!(container instanceof PhysicalComponent && !ComponentExt.isActor(container)
        && ((PhysicalComponent) container).getNature() == PhysicalComponentNature.NODE)) {
      createPhysicalLinksFromCExchanges(container, container);
    }
  }
  
  /**
   * @param sourceContainer
   * @param sourceContained
   */
  protected void createPhysicalLinksFromCExchanges(Component sourceContainer, Component sourceContained) {
    if (isValidBound(sourceContained)) {
      // Process the contained component
      // This reference will allows to handle the processed CEs
      for (ComponentPort port : ComponentExt.getOwnedComponentPort(sourceContained)) {
        // get all the CEs of the port
        for (ComponentExchange componentExchange : port.getComponentExchanges()) {
          processComponentExchange(sourceContainer, sourceContained, port, componentExchange);
        }
      }
    }
  }
  
  /**
   * @param component
   * @return true if the component is contained by a non actor component
   */
  private boolean isContainedByALogicalNonActorComponent(Component component) {
    if (!(component instanceof LogicalComponent))
      return false;
    EObject container = component.eContainer();
    if (container instanceof LogicalComponent) {
      return !ComponentExt.isActor(container) || isContainedByALogicalNonActorComponent((Component) container);
    }
    return false;
  }

  private void processComponentExchange(Component sourceContainer, Component sourceContained, ComponentPort port,
      ComponentExchange componentExchange) {
    if ((componentExchange.getKind() == ComponentExchangeKind.DELEGATION)
        || (componentExchange.getKind() == ComponentExchangeKind.UNSET)
        || doesNodeAlreadyHaveAPhysicalLinkForComponentExchange(sourceContainer, componentExchange)) {
      return;
    }
    Component targetContained = findTheTargetComponent(componentExchange, port);
    if (targetContained != null) {
      if (targetContained instanceof PhysicalComponent) {
        PhysicalComponent targetContainerPC = findDeployingComponent((PhysicalComponent) targetContained);
        if (targetContainerPC != null) {
          doCreatePhysicalLink(componentExchange, sourceContainer, targetContainerPC, port);
        }
      }
      // if the container is the same as contained (if the component doesn't have subcomponents)
      if (sourceContainer.equals(sourceContained)
          && (ComponentExt.isActor(sourceContained) || isSystemOrLogicalSystem(sourceContained))) {
        // if the target is contained by a logical component (non actor), the PL should not be created
        if (!isContainedByALogicalNonActorComponent(targetContained))
          doCreatePhysicalLink(componentExchange, sourceContainer, (Component) targetContained, port);
      } else if (ComponentExt.isActor(targetContained) && targetContained.eContainer() instanceof Component) {
        Component targetContainer = (Component) targetContained.eContainer();
        if (!isContainedByALogicalNonActorComponent(targetContainer))
          doCreatePhysicalLink(componentExchange, sourceContainer, targetContainer, port);
      }
    }
  }
  
  /**
   * Find the target component of the CE
   * @param componentExchange the source component exchange
   * @param port 
   * @return the component if it's a valid target (instanceof Component, validBound, etc), null otherwise
   */
  private Component findTheTargetComponent(ComponentExchange componentExchange, ComponentPort port) {
    // get the opposite port [which could be source or target of the CE]
    InformationsExchanger target = FunctionalExt.getOtherBound(componentExchange, port);
    if (!(target instanceof ComponentPort))
      return null;

    EObject targetContained = target.eContainer();
    // the target is not valid if it's not a Physical, Logical or System Component
    if (!(targetContained instanceof Component && isValidBound((Component) targetContained)))
      return null;

    // for LogicalComponent, the target is not valid if it's not an actor or the logical system
    if (targetContained instanceof LogicalComponent
        && (!ComponentExt.isActor(targetContained) && !isSystemOrLogicalSystem((Component) targetContained)))
      return null;

    return (Component) targetContained;
  }
  
  private boolean isSystemOrLogicalSystem(Component component) {
    if (component instanceof PhysicalComponent || component instanceof Entity)
      return false;
    BlockArchitecture architecture = ComponentExt.getRootBlockArchitecture(component);
    return architecture.getSystem().equals(component);
  }
  
  private PhysicalComponent findDeployingComponent(PhysicalComponent targetContained) {
    for (Part partition : targetContained.getRepresentingParts()) {
      for (DeploymentTarget deploying : getCache(PartExt::getDeployingElements, (Part) partition)) {
        if (deploying instanceof Part) {
          Part deployingPart = (Part) deploying;
          if (deployingPart.getAbstractType() instanceof PhysicalComponent) {
            return (PhysicalComponent) deployingPart.getAbstractType();
          }
        }
      }
    }
    return null;
  }
   
  private void doCreatePhysicalLink(ComponentExchange componentExchange, Component exchangeOutput,
      Component exchangeInput, ComponentPort sourcePort) {
    if (componentExchange.getSource().equals(sourcePort)) {
      doCreatePhysicalLink(componentExchange, exchangeOutput, exchangeInput);
    } else {
      doCreatePhysicalLink(componentExchange, exchangeInput, exchangeOutput);
    }
  }

  /**
   * Create a physical link corresponding to the given component exchange, between the given components
   * @param componentExchange the source component exchange
   * @param exchangeOutput the output component
   * @param exchangeInput the input component
   */
  protected void doCreatePhysicalLink(ComponentExchange componentExchange, Component exchangeOutput, Component exchangeInput) {
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
    
    exchangeInput.getOwnedPhysicalLinks().add(physicalLink);
    exchangeOutput.getOwnedPhysicalLinks().add(physicalLink);
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
  protected boolean doesNodeAlreadyHaveAPhysicalLinkForComponentExchange(Component physicalComponent,
      ComponentExchange componentExchange) {
    boolean result = false;
    // Get the semantic editing domain to access the cross referencer.
    SemanticEditingDomain editingDomain = (SemanticEditingDomain) AdapterFactoryEditingDomain
        .getEditingDomainFor(componentExchange);
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