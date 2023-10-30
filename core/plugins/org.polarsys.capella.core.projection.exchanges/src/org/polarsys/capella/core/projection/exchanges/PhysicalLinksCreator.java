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

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.Arrays;
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
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
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
import org.polarsys.capella.core.data.ctx.SystemComponent;
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
   * 
   * @param component
   */
  public PhysicalLinksCreator(Component component, Part part) {
    super(component);
    this.part = part;
  }

  /**
   * This implementation creates physical links.
   * 
   * @see org.polarsys.capella.core.projection.commands.utils.DefaultExchangesCreator#createExchanges()
   */
  @Override
  public void createExchanges() {
    if (isValidBound(_component)) {
      boolean physicalLinkCreated = createPhysicalLinksFromCExchanges(_component);
      if (!physicalLinkCreated) {
        String message = "No physical link has been created.";
        EmbeddedMessage eMessage = new EmbeddedMessage(message, logger.getName());
        logger.info(eMessage);
      }
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
   *          This method check all subcomponent and try to create physical links and allocate them to a CE at the
   *          contained level component exchanges If the container hasn't subcomponent, it will try to create a PL and
   *          allocate to a CE at the same level
   */
  protected boolean createPhysicalLinksFromCExchanges(Component container) {
    boolean physicalLinkCreated = false;
    if (container instanceof PhysicalComponent) {
      // Gets the deployments of the node
      EList<AbstractDeploymentLink> deployments = part.getDeploymentLinks();

      for (AbstractDeploymentLink deployment : deployments) {
        if (deployment instanceof PartDeploymentLink) {
          PartDeploymentLink deploymentLink = (PartDeploymentLink) deployment;
          DeployableElement deployedElement = deploymentLink.getDeployedElement();
          if (deployedElement instanceof Part) {
            Type containedPC = ((Part) deployedElement).getType();
            if (containedPC instanceof PhysicalComponent) {
              physicalLinkCreated |= createPhysicalLinksFromCExchanges(container, (PhysicalComponent) containedPC);
            }
          } else if (deployedElement instanceof PhysicalComponent) {
            physicalLinkCreated |= createPhysicalLinksFromCExchanges(container, (PhysicalComponent) deployedElement);
          }
        }
      }
    }
    if (!isContainedByALogicalNonActorComponent(container)) {
      // Process contained actors
      physicalLinkCreated |= createPLsFromCEDiffLevels(container);
      physicalLinkCreated |= createPLsFromCESameLevel(container);
    }
    return physicalLinkCreated;
  }

  /**
   * Create Physical Links from Component Exchanges at different levels
   * 
   * @return
   */
  protected boolean createPLsFromCEDiffLevels(Component container) {
    List<Component> subComponents = ComponentExt.getSubDefinedComponents(container);
    if (!subComponents.isEmpty()) {
      return subComponents.stream().filter(ComponentExt::isActor)
          .map(actor -> createPhysicalLinksFromCExchanges(container, actor)).reduce(Boolean.FALSE, Boolean::logicalOr);
    }
    return false;
  }

  /**
   * Create Physical Links from Component Exchanges at the same level
   */
  protected boolean createPLsFromCESameLevel(Component container) {
    if (ComponentExt.isActor(container) || !isNodeComponent(container)) {
      return createPhysicalLinksFromCExchanges(container, container);
    }
    return false;
  }

  /**
   * @param sourceContainer
   * @param sourceContained
   * 
   * @return boolean if a physicalLink has been created
   */
  protected boolean createPhysicalLinksFromCExchanges(Component sourceContainer, Component sourceContained) {
    boolean physicalLinkCreated = false;
    if (isValidBound(sourceContained)) {
      // Process the contained component
      // This reference will allows to handle the processed CEs
      for (ComponentPort port : ComponentExt.getOwnedComponentPort(sourceContained)) {
        // get all the CEs of the port
        for (ComponentExchange componentExchange : port.getComponentExchanges()) {
          physicalLinkCreated |= processComponentExchange(sourceContainer, sourceContained, port,
              componentExchange) != null;
        }
      }
    }
    return physicalLinkCreated;
  }

  /**
   * @param component
   * @return true if the component is contained by a non actor component
   */
  private boolean isContainedByALogicalNonActorComponent(Component component) {
    EObject container = component.eContainer();
    if (container instanceof LogicalComponent) {
      return !ComponentExt.isActor(container) || isContainedByALogicalNonActorComponent((Component) container);
    }
    return false;
  }

  private PhysicalLink processComponentExchange(Component sourceContainer, Component sourceContained,
      ComponentPort port, ComponentExchange componentExchange) {
    if ((componentExchange.getKind() == ComponentExchangeKind.DELEGATION)
        || (componentExchange.getKind() == ComponentExchangeKind.UNSET)
        || doesNodeAlreadyHasAPhysicalLinkForComponentExchange(sourceContainer, componentExchange)) {
      String message = "Component exchange " + componentExchange.getName()
          + " already has a physical link or the kind is not suitable.";
      EmbeddedMessage eMessage = new EmbeddedMessage(message, logger.getName(), componentExchange);
      logger.error(eMessage);
      return null;
    }

    Component targetContained = findTheTargetComponent(componentExchange, port);
    Component target = computePhysicalLinkBound(targetContained);
    Component source = computePhysicalLinkBound(sourceContained);
    if (!isValidPhysicalLinkBound(source)) {
      String message = "Component " + source.getName() + " cannot host a Physical Link.";
      EmbeddedMessage eMessage = new EmbeddedMessage(message, logger.getName(), source);
      logger.error(eMessage);
      return null;
    } else if (!isValidPhysicalLinkBound(target)) {
      String message = "Component " + target.getName() + " cannot host a Physical Link.";
      EmbeddedMessage eMessage = new EmbeddedMessage(message, logger.getName(), target);
      logger.error(eMessage);
      return null;
    }

    return doCreatePhysicalLink(componentExchange, source, target, port);
  }

  /**
   * verify if given parameter is physical component and it nature is a PC hehaviour
   * 
   * @param component
   * @return a boolean
   */
  private boolean isBehaviourComponent(Component component) {
    return component instanceof PhysicalComponent
        && ((PhysicalComponent) component).getNature() == PhysicalComponentNature.BEHAVIOR;
  }

  /**
   * verify if given parameter is physical component and it nature is a node
   * 
   * @param component
   * @return a boolean
   */
  private boolean isNodeComponent(Component component) {
    return component instanceof PhysicalComponent
        && ((PhysicalComponent) component).getNature() == PhysicalComponentNature.NODE;
  }

  /**
   * Find the target component of the CE
   * 
   * @param componentExchange
   *          the source component exchange
   * @param port
   * @return the component if it's a valid target (instanceof Component, validBound, etc), null otherwise
   */
  private Component findTheTargetComponent(ComponentExchange componentExchange, ComponentPort port) {
    // get the opposite port [which could be source or target of the CE]
    InformationsExchanger target = FunctionalExt.getOtherBound(componentExchange, port);
    if (!(target instanceof ComponentPort))
      return null;
    EObject targetContained = target.eContainer();
    return (Component) targetContained;
  }

  /**
   * Return whether the given component can host the Physical Link
   * 
   * @param componentExchange
   *          the given component
   * @param port
   * @return if the component is a Component not behavior nor owned by a behavior
   */
  private boolean isValidPhysicalLinkBound(Component bound) {
    // get the opposite port [which could be source or target of the CE]

    Component targetContained = bound;
    // the target is not valid if it's not a Physical, Logical or System Component
    if (!(targetContained instanceof Component && isValidBound(targetContained)))
      return false;

    // for LogicalComponent, the target is not valid if it's not an actor or the logical system
    if ((targetContained instanceof LogicalComponent || targetContained instanceof SystemComponent)
        && (!ComponentExt.isActor(targetContained) && !isSystemOrLogicalSystem(targetContained)))
      return false;

    if (isBehaviourComponent(targetContained)) {
      return false;
    }
    if (ComponentExt.isActor(targetContained) && isContainedByALogicalNonActorComponent(targetContained)) {
      return false;
    }
    return true;
  }

  private boolean isSystemOrLogicalSystem(Component component) {
    if (component instanceof PhysicalComponent || component instanceof Entity)
      return false;
    BlockArchitecture architecture = ComponentExt.getRootBlockArchitecture(component);
    return architecture.getSystem().equals(component);
  }

  /**
   * Starting from a component exchange bound (source or target), return the component that will contains the Physical
   * link bound
   */
  private Component computePhysicalLinkBound(Component componentExchangeBound) {
    if (componentExchangeBound == null) {
      return null;
    }
    // Returns the deploying Node Component if its deployed on a Node
    for (Part partition : componentExchangeBound.getRepresentingParts()) {
      for (DeploymentTarget deploying : getCache(PartExt::getDeployingElements, partition)) {
        if (deploying instanceof Part) {
          Part deployingPart = (Part) deploying;
          if (deployingPart.getAbstractType() instanceof PhysicalComponent
              && !isBehaviourComponent((PhysicalComponent) deployingPart.getAbstractType())) {
            return (PhysicalComponent) deployingPart.getAbstractType();
          }
        }
      }
    }
    // If is an Actor, then return it's parent container if the parent is an Actor or an Node
    if (ComponentExt.isActor(componentExchangeBound) && componentExchangeBound.eContainer() instanceof Component
        && !isBehaviourComponent((Component) componentExchangeBound.eContainer())) {
      return (Component) componentExchangeBound.eContainer();
    }
    // Otherwise, we create on the same component
    return componentExchangeBound;
  }

  private PhysicalLink doCreatePhysicalLink(ComponentExchange componentExchange, Component exchangeOutput,
      Component exchangeInput, ComponentPort sourcePort) {
    if (componentExchange.getSource().equals(sourcePort)) {
      return doCreatePhysicalLink(componentExchange, exchangeOutput, exchangeInput);
    }
    return doCreatePhysicalLink(componentExchange, exchangeInput, exchangeOutput);
  }

  /**
   * Create a physical link corresponding to the given component exchange, between the given components
   * 
   * @param componentExchange
   *          the source component exchange
   * @param exchangeOutput
   *          the output component
   * @param exchangeInput
   *          the input component
   */
  protected PhysicalLink doCreatePhysicalLink(ComponentExchange componentExchange, Component exchangeOutput,
      Component exchangeInput) {
    // Precondition:
    if (exchangeOutput == exchangeInput) {
      // Not necessary to create a physical link for exchanges inside the
      // same container.
      return null;
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
    String message = "The Physical link " + physicalLink.getName() + " has been succefully created between the source "
        + exchangeOutput.getLabel() + " and the target " + exchangeInput.getLabel();
    EmbeddedMessage eMessage = new EmbeddedMessage(message, logger.getName(),
        Arrays.asList(physicalLink, exchangeOutput, exchangeInput));
    logger.info(eMessage);
    return physicalLink;
  }

  /**
   * @param informationExchange
   * @param physicalPort
   * @param connection
   */
  private ComponentPortAllocation createComponentPortAllocation(InformationsExchanger informationExchange,
      PhysicalPort physicalPort) {
    ComponentPortAllocation allocation = FaFactory.eINSTANCE.createComponentPortAllocation();
    allocation.setSourceElement(physicalPort);
    allocation.setTargetElement((TraceableElement) informationExchange);
    physicalPort.getOwnedComponentPortAllocations().add(allocation);
    CapellaElementExt.creationService(allocation);
    return allocation;
  }

  /**
   * This method allows to know if the given component exchange has already been allocated to a physical link linked to
   * the given physical component.
   * 
   * @param physicalComponent
   *          the physical component
   * @param componentExchange
   *          the component exchange
   * @return true if its has already been allocated, false otherwise
   */
  protected boolean doesNodeAlreadyHasAPhysicalLinkForComponentExchange(Component physicalComponent,
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