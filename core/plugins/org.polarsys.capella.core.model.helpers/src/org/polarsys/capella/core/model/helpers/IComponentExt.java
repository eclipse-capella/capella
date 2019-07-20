/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers;

import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.cache.CacheResult;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LogicalComponent;

public interface IComponentExt {

  /**
   * This method adds an interface implementation.
   * 
   * @param component
   *          the component who will implement the interface
   * @param interf
   *          the implemented interface
   */
  void addImplementedInterface(Component component, Interface interf);

  /**
   * This method adds an provide interface. If the StandardPort doesn't exist, it will be create.
   */
  void addProvidedInterface(Component component, Interface interf);

  /**
   * This method adds an required interface. If the StandardPort doesn't exist, it will be create.
   */
  void addRequiredInterface(Component component, Interface interf);

  /**
   * This method adds an interface usage.
   * 
   * @param component
   *          the component who will use the interface
   * @param interf
   *          the used interface
   */
  void addUsedInterface(Component component, Interface interf);

  /**
   * This method adds a communication link.
   * 
   * @param component
   *          the component who will use the interface
   * @param communicationLink
   *          the communication link
   * @param isGenerated
   *          true if usage is generated, false otherwise
   */
  void addCommunicationLink(Component component, CommunicationLink communicationLink);

  /**
   * 
   */
  @CacheResult
  List<AbstractExchangeItem> getExchangeItems(Component component);

  /**
   * Return all exchange items allocated by all functions port of the given component
   * 
   * @param component
   * @return componentFunctionExchangeItems
   */
  @CacheResult
  Set<AbstractExchangeItem> getAllocatedFunctionExchangeItems(Component component);

  /**
   * This method removes an exchange item realization.
   * 
   * @param component
   *          the component who is using the interface
   * @param communicationLink
   *          the communication link
   */
  void removeCommunicationLink(Component component, CommunicationLink communicationLink);

  /**
   * @param message
   * @param selectedOperation
   * @param portStrategie
   * @param targetOnExchangeItem
   */
  void ensureUseAndImplementsForOperation(SequenceMessage message, ExchangeItemAllocation selectedOperation,
      boolean portStrategie, EObject targetOnExchangeItem);

  @CacheResult
  List<Component> getAllAncestors(Component component);

  /**
   * @param cpnt
   * @return
   */
  @CacheResult
  List<Part> getAllComponentInstances(Component cpnt);

  /**
   * This method retrieves all the implemented interfaces.
   * 
   * @param cpnt
   *          the component whose implemented AND provided (by StandardPorts) interfaces will be retrieved (and all its
   *          inherited components)
   * @return List<Interface> the implemented interfaces
   */
  @CacheResult
  List<Interface> getAllImplementedAndProvidedInterfaces(Component cpnt);

  /**
   * This method retrieves all the implemented interfaces.
   * 
   * @param cpnt
   *          the component whose implemented interfaces will be retrieved (and all its inherited components)
   * @return List<Interface> the implemented interfaces
   */
  @CacheResult
  List<Interface> getAllImplementedInterfaces(Component cpnt);

  /**
   * obtains all relationships ending to cpnt
   * 
   * @param cpnt
   * @return
   */
  @CacheResult
  List<Relationship> getAllIncomingRelationships(Component cpnt);

  /**
   * obtains all relationships starting from cpnt
   * 
   * @param cpnt
   * @return
   */
  @CacheResult
  List<Relationship> getAllOutgoingRelationships(Component cpnt);

  /**
   * This method retrieves all the provided interfaces recursively
   * 
   * @param component
   * @return
   */
  @CacheResult
  List<Interface> getAllProvidedInterfaces(Component cpnt);

  /**
   * Return all the connection related to given Component filter by Connection kind Doesn't include CE linked to ports
   */
  @CacheResult
  Collection<ComponentExchange> getAllRelatedComponentExchange(Part part);

  /**
   * Return all the connection related to given Component filter by Connection kind According to given
   * includePortOfType, returns also CE from ports of the type
   */
  @CacheResult
  Collection<ComponentExchange> getAllRelatedComponentExchange(Part part, boolean includePortOfType);

  /**
   * Return all the connection related to given Component filter by Connection kind
   * 
   * @param component
   * @return
   */
  @CacheResult
  Collection<ComponentExchange> getAllRelatedComponentExchange(Component component);

  /**
   * Return all the connection related to given Component filter by Connection kind
   * 
   * @param component
   * @return
   */
  @CacheResult
  Collection<ComponentExchange> getAllRelatedComponentExchange(Component component, boolean addRelatedOfParts);

  /**
   * Return all the connection related to given Component filter by Connection kind
   * 
   * @param component
   * @return
   */
  @CacheResult
  Collection<ComponentExchange> getAllRelatedConnectionByKind(Component component, ComponentExchangeKind kind);

  /**
   * This method retrieves all the required interfaces recursively
   * 
   * @param component
   * @return
   */
  @CacheResult
  List<Interface> getAllRequiredInterfaces(Component cpnt);

  /**
   * get all the States and Modes from current Component
   * 
   * @param ele
   * @param comp
   * @return
   */
  @CacheResult
  List<CapellaElement> getAllStatesAndModesFromComponent(Component comp);

  @CacheResult
  Collection<Part> getAllSubUsedParts(Component component, boolean useDeploymentLinks);

  @CacheResult
  Collection<Part> getAllSubUsedParts(Part part, boolean useDeploymentLinks);

  /**
   * Returns sub components of the component. (include also the given component)
   */
  @CacheResult
  Collection<Component> getAllSubUsedComponents(Component component);

  /**
   * This method retrieves all the used interfaces.
   * 
   * @param cpnt
   *          the component whose used AND required (by StandardPorts) interfaces will be retrieved (and all its
   *          inherited components)
   * @return List<Interface> the used and required interfaces
   */
  @CacheResult
  List<Interface> getAllUsedAndRequiredInterfaces(Component cpnt);

  /**
   * This method retrieves all the used interfaces.
   * 
   * @param cpnt
   *          the component whose used interfaces will be retrieved (and all its inherited components)
   * @return List<Interface> the used interfaces
   */
  @CacheResult
  List<Interface> getAllUsedInterfaces(Component cpnt);

  /**
   * Returns all components which are available by namespace.
   */
  @CacheResult
  Collection<Component> getAvailableComponentsByNamespace(EObject component);

  /**
   * Returns parents and brothers of parents components of parts of the given object
   */
  @CacheResult
  Collection<EObject> getAvailableComponentsByNamespaceOfParts(Deque<EObject> ownerParts);

  /**
   * Returns parents and brothers of parents components of parts of the given object
   */
  @CacheResult
  Collection<EObject> getAvailableComponentsByNamespaceOfParts(Part part);

  /**
   * This method is deprecated: cycles can occur in multi part mode.
   * 
   * @param c1
   * @param c2
   * @return
   */
  @CacheResult
  Component getCommonComponentAncestor(Component c1, Component c2);

  /**
   * Gets all the components contained in a component
   * 
   * @param component
   *          the parent component
   * @return list of components
   */
  @CacheResult
  List<Component> getComponentsFromComponent(Component component);

  /**
   * Return the Part list involved in decomposition for the Component given in parameter
   */
  @CacheResult
  List<Part> getDecompositionPartInvolved(Component lc);

  /**
   * @return the list of Component direct common parent for componentA and componentB.
   * @return null if not have a direct common parent.
   */
  @CacheResult
  List<Component> getDirectCommonParent(Component componentA, Component componentB);

  /**
   * @return component owning the the part given in parameter
   */
  Component getDirectParent(Partition part);

  /**
   * @return component owning the component parts given in parameter
   */
  @CacheResult
  List<Component> getDirectParents(Component component);

  /**
   * Retrieve the first common component ancestor for two elements with eClass = Port part or component return the
   * common component ancestor between source and target if source is target, return the c1 unlike
   * getCommonComponentAncestor
   */
  @CacheResult
  EObject getFirstCommonComponentAncestor(EObject source, EObject target, boolean useDeployementLinks);

  @CacheResult
  EObject getFirstCommonComponentAncestor(EObject source, EObject target);

  /**
   * Get the most common ancestor of all given components. Returns <code>null</code> if none can be found.
   * 
   * @param components
   * @return
   */
  Component getFirstCommonComponentAncestor(List<Component> components);

  /**
   * This method retrieves the implemented AND provided (by Ports) interfaces
   * 
   * @param component
   *          the component whose used and provided interfaces will be retrieved
   * @return List<Interface> the used interfaces
   */
  List<Interface> getImplementedAndProvidedInterfaces(Component component);

  /**
   * This method retrieves the implemented interfaces.
   * 
   * @param component
   *          the component whose implemented interfaces will be retrieved
   * @return List<Interface> the implemented interfaces
   */
  @CacheResult
  List<Interface> getImplementedInterfaces(Component component);

  /**
   * Gets all the interfaces implemented by the component <code>currentComp</code>, except those already implemented by
   * <code>component</code>.
   * 
   * @param currentComp
   *          the component from which all the implemented interfaces have to be listed
   * @param component
   *          the component to be checked
   * @return list of all implemented interfaces
   */
  @CacheResult
  List<Interface> getImplementedInterfacesFiltered(Component currentComp, Component component);

  /**
   * Retrieve the Data pkg from the given component (create an interface pkg if null)
   */
  DataPkg getDataPkg(Component component, boolean create);

  /**
   * Retrieve the interface pkg from the given component (create an interface pkg if null)
   */
  DataPkg getDataPkg(Component component);

  /**
   * Retrieve the interface pkg from the given component (create an interface pkg if null)
   */
  InterfacePkg getInterfacePkg(Component component, boolean create);

  /**
   * Retrieve the interface pkg from the given component (create an interface pkg if null)
   */
  InterfacePkg getInterfacePkg(Component component);

  /**
   * This method gets all the interfaces in Parent's hierarchy of current LC
   * 
   * @param component
   *          the Logical Component
   * @return List of interfaces
   */
  @CacheResult
  List<Interface> getInterfacesFromComponentParentHierarchy(Component component);

  /**
   * Gets the owned ports.
   * 
   * @param component
   *          the given component
   * @return the owned port
   */
  @CacheResult
  List<ComponentPort> getOwnedComponentPort(Component component);

  /**
   * Gets the owned flow ports.
   * 
   * @param component
   *          the given component
   * @return the owned flow port
   */
  @CacheResult
  List<ComponentPort> getOwnedFlowPort(Component component);

  /**
   * Gets the owned in flow ports.
   * 
   * @param component
   *          the given component
   * @return the owned in flow port
   */
  @CacheResult
  List<ComponentPort> getOwnedInFlowPort(Component component);

  /**
   * Gets the owned out flow ports.
   * 
   * @param component
   *          the given component
   * @return the owned out flow port
   */
  @CacheResult
  List<ComponentPort> getOwnedOutFlowPort(Component component);

  /**
   * Gets the owned physical ports.
   * 
   * @param component
   *          the given component
   * @return the owned physical port
   */
  @CacheResult
  List<PhysicalPort> getOwnedPhysicalPort(Component component);

  /**
   * Check if the compnent has physical ports.
   * 
   * @param component
   *          the given component
   */
  @CacheResult
  boolean hasPhysicalPort(Component component);

  /**
   * Gets the owned ports.
   * 
   * @param component
   *          the given component
   * @return the owned port
   */
  @CacheResult
  List<Port> getOwnedPort(Component component);

  /**
   * @param modelElement
   * @return
   */
  @CacheResult
  Collection<ComponentExchange> getAllOwnedComponentExchanges(Component modelElement);

  /**
   * Gets the owned standard ports.
   * 
   * @param component
   *          the given component
   * @return the owned standard port
   */
  List<ComponentPort> getOwnedStandardPort(Component component);

  /**
   * Get the Parent Component respecting the breakdown structure logic
   * 
   * @param component
   *          : component whose parent we are looking for
   * @return parent component doesn't work for multipart
   */
  @CacheResult
  Component getParent(Component component);

  /**
   * Returns recursively all components which contains the given component.
   */
  @CacheResult
  Collection<Component> getComponentAncestors(Component component);

  @CacheResult
  Collection<Part> getPartAncestors(Part child);

  /**
   * Returns recursively all parts which contains the given part. All representing partition of containers and all parts
   * deploying the given part
   */
  @CacheResult
  Collection<Part> getPartAncestors(Part child, boolean addGeneralizationOfParents);

  /**
   * Returns functional exchanges which are related to allocated functions of the given component
   */
  @CacheResult
  Collection<FunctionalExchange> getRelatedFunctionalExchanges(Component component);

  /**
   * Returns all allocated functions of the given component
   * 
   * @param component
   */
  @CacheResult
  Collection<AbstractFunction> getAllocatedFunctions(Component component);

  /**
   * Returns related interfaces of components (implements, uses, provides, requires).
   */
  @CacheResult
  Collection<Interface> getRelatedInterfaces(Component component);

  /**
   * Return the related interfaces of component from References(use, implement, required and provided links)
   * 
   * @param component
   * @return
   */
  List<Interface> getRelatedInterfacesFromReference(Component cpnt, EReference reference);

  /**
   * This method retrieves all the required interfaces.
   * 
   * @param component
   * @return
   */
  @CacheResult
  List<Interface> getRequiredInterfaces(Component cpnt);

  /**
   * @param modelElement
   *          : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  @CacheResult
  BlockArchitecture getRootBlockArchitecture(ModelElement modelElement);

  @CacheResult
  Component getRootComponent(Component component);

  /**
   * Gets the root component architecture from the current component
   * 
   * @param component
   *          the current component
   * @return the root component architecture
   */
  @CacheResult
  ComponentArchitecture getRootComponentArchitecture(Component component);

  /**
   * Gets the sub defined actors.
   */
  @CacheResult
  List<? extends Component> getSubDefinedActors(BlockArchitecture architecture);

  /**
   * Returns components defined into the component.
   */
  @CacheResult
  List<Component> getSubDefinedComponents(Component object);

  /**
   * Returns components defined into the architecture.
   */
  @CacheResult
  List<Component> getSubDefinedComponents(BlockArchitecture object);

  /**
   * Returns sub components of the component which are used (have a part)
   */
  @CacheResult
  List<Component> getSubUsedComponents(Component component);

  @CacheResult
  List<Component> getSubUsedAndDeployedComponents(Component component);

  @CacheResult
  List<Component> getSubUsedComponents(Component component, boolean useDeploymentLinks);

  /**
   * Returns sub part used by the component (have a part)
   */
  @CacheResult
  List<Part> getSubParts(PartitionableElement component);

  /**
   * Returns sub part used by the component (have a part).
   */
  @CacheResult
  List<Part> getSubParts(Component component, boolean useDeploymentLinks);

  /**
   * This method retrieves the inherited components.
   * 
   * @param cpnt
   *          The component source.
   * @return The list of inherited components.
   */
  @CacheResult
  List<Component> getSuperComponents(Component cpnt);

  /**
   * Returns representing parts of given component.
   * 
   * @param component
   * @return
   */
  @CacheResult
  Collection<Part> getRepresentingParts(Component component);

  /**
   * This method retrieves the used interfaces AND required (by Ports)
   * 
   * @param component
   *          the component whose used and required interfaces will be retrieved
   * @return List<Interface> the used interfaces
   */
  @CacheResult
  List<Interface> getUsedAndRequiredInterfaces(Component component);

  /**
   * This method retrieves the used interfaces.
   * 
   * @param component
   *          the component whose used interfaces will be retrieved
   * @return List<Interface> the used interfaces
   */
  @CacheResult
  List<Interface> getUsedInterfaces(Component component);

  /**
   * Gets all the interfaces used by the component <code>currentComp</code>, except those already used by
   * <code>component</code>.
   * 
   * @param currentComp
   *          the component from which all the used interfaces have to be listed
   * @param component
   *          the component to be checked
   * @return list of all used interfaces
   */
  @CacheResult
  List<Interface> getUsedInterfacesFiltered(Component currentComp, Component component);

  /**
   * @return True if componentA and componentB have a direct common parent
   */
  boolean haveDirectCommonParent(Component componentA, Component componentB);

  /**
   * Returns whether component used or require the exchange item by related interfaces
   */
  boolean isAnImplementedOrProvidedExchangeItem(Component component, AbstractExchangeItem exchangeItem);

  /**
   * Returns whether component used or require the exchange item by related interfaces
   */
  boolean isAnUsedOrRequiredExchangeItem(Component component, AbstractExchangeItem exchangeItem);

  @CacheResult
  boolean isComponentAncestor(Component ancestor, Component child);

  /*
   * Return true if the the Component given in parameter is the ComponentRoot
   */
  @CacheResult
  boolean isComponentRoot(EObject element);

  /**
   * Retrieve part ancestors, deploying elements and owner
   * 
   * @param currentPart
   * @param parents
   */
  @CacheResult
  Collection<Part> getBestPartAncestors(Part currentPart);

  @CacheResult
  Collection<EObject> filterNodes(Collection<EObject> elements);

  /**
   * Retrieve whether both given part can be considered as "brother-linked" It should be defined in the same component
   * or be an actor/in the root system
   * 
   * @param source
   * @param target
   * @return
   */
  @CacheResult

  boolean isBrothers(Part source, Part target);

  /**
   * This method evaluates if a Component is a composite or not.
   */
  boolean isComposite(Component component);

  /**
   * This method checks whether the component is implementing the interface
   * 
   * @param component
   *          the component
   * @param itf
   *          the interface implemented
   * @return true if the component is implementing the interface
   */
  @CacheResult

  boolean isImplementingInterface(Component component, Interface itf);

  /**
   * This method checks w whether one of the component and all its super type is implementing the interface
   * 
   * @param component
   * @param itf
   * @return true if the component is implementing the interface
   */
  @CacheResult

  boolean isImplementingInterfaceWithAllSuperType(Component component, Interface itf);

  /**
   * This method evaluates if a Component is a leaf or not.
   * 
   * @param component
   *          the Component evaluated
   * @return 'true' if leaf, 'false' otherwise
   */
  @CacheResult

  boolean isLeaf(Component component);

  /**
   * Check if both the element are of same type as LogicalComponent or PhysicalComponent and also check if there
   * eContainer is same Type
   * 
   * @param me1
   *          CapellaElement
   * @param me2
   *          CapellaElement
   * @return true if both are LogicalComonent or PhysicalComponent - and both eContainer is Same Type of Element
   */
  boolean isSameTypeAndNotRootElement(EObject me1, EObject me2);

  /**
   * This method checks whether the component is using the interface
   * 
   * @param component
   *          the component
   * @param itf
   *          the interface used
   * @return true if the component is using the interface
   */
  boolean isUsingInterface(Component component, Interface itf);

  /**
   * This method checks whether one of the component and all its super type is using the interface
   * 
   * @param component
   * @param itf
   * @return true if the component is using the interface
   */
  boolean isUsingInterfaceWithAllSuperType(Component component, Interface itf);

  /**
   * Create for two part a componentExchange between brother-linked parts and delegations from givens parts/ports to
   * both brother-linked parts.
   * 
   * @param sourcePart
   * @param sourcePort
   * @param targetPart
   * @param targetPort
   * @return
   */
  Collection<EObject> createComponentExchangeThroughDelegations(Part sourcePart, ComponentPort sourcePort,
      Part targetPart, ComponentPort targetPort);

  /**
   * This method removes an interface implementation.
   * 
   * @param component
   *          the component who is implementing the interface
   * @param interf
   *          the implemented interface
   */
  void removeImplementedInterface(Component component, Interface interf);

  /**
   * This method remove an provide interface.
   */
  void removeProvidedInterface(Component component, Interface interf);

  /**
   * This method remove an require interface.
   */
  void removeRequiredInterface(Component component, Interface interf);

  /**
   * This method removes an interface usage.
   * 
   * @param component
   *          the component who is using the interface
   * @param interf
   *          the used interface
   */
  void removeUsedInterface(Component component, Interface interf);

  List<PartitionableElement> getAllPartitionableElementAncestors(PartitionableElement current);

  /**
   * Returns whether both ports are also linked by a componentExchange in brothers parts.
   * 
   * @param exchange
   * @param sourcePart
   * @param targetPart
   * @param sourcePort
   * @param targetPort
   * @return
   */
  boolean isComponentExchangeThroughDelegationsExists(Part sourcePart, Part targetPart, Port sourcePort,
      Port targetPort);

  /**
   * Return true if given interface is providing interface of given componentPort
   */
  @CacheResult

  boolean isProvidingInterface(ComponentPort componentPort, Interface interf);

  /**
   * Return true if given interface is requiring interface of given componentPort
   */
  @CacheResult
  boolean isRequiringInterface(ComponentPort componentPort, Interface interf);

  /**
   * From a component, returns its entire hierarchy as displayed in a blank architecture, with sub-component, as well as
   * deployed them for the components of the physical level
   * 
   * @param component
   *          the root component
   * @return
   */
  @CacheResult
  List<Component> getAllSubUsedAndDeployedComponents(Component component);

  /**
   * Removes duplicate in an EList of interfaces.
   * 
   * @param list
   * @return
   */
  @CacheResult
  Collection<Interface> removeDuplicate(EList<Interface> list);

  IStatus isRequiredorUsedItfDelegated(IValidationContext ctx, LogicalComponent lcomp, Interface itf);

  IStatus isProvidedorImplementedItfDelegated(IValidationContext ctx, Component lcomp, Interface itf);

}