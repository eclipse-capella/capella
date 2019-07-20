/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers;
import static org.polarsys.capella.core.data.helpers.DataHelpers.FunctionExt;
import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getCache;
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;
import static org.polarsys.capella.core.model.helpers.ModelHelpers.PartExt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.communication.CommunicationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

/**
 * Component helpers
 */
public class ComponentExt implements IComponentExt {

  /**
   * This method adds an interface implementation.
   * 
   * @param component
   *          the component who will implement the interface
   * @param interf
   *          the implemented interface
   */
  @Override
  public void addImplementedInterface(Component component, Interface interf) {
    if ((component != null) && (interf != null)) {
      if (!getImplementedInterfaces(component).contains(interf)) {
        InterfaceImplementation impl = CsFactory.eINSTANCE.createInterfaceImplementation();
        impl.setImplementedInterface(interf);
        component.getOwnedInterfaceImplementations().add(impl);
      }
    }
  }

  /**
   * This method adds an provide interface. If the StandardPort doesn't exist, it will be create.
   */
  @Override
  public void addProvidedInterface(Component component, Interface interf) {
    ComponentPort stdPort = null;
    if ((component != null) && (interf != null)) {
      for (Partition partition : component.getOwnedPartitions()) {
        if (partition instanceof ComponentPort) {
          stdPort = (ComponentPort) partition;
          break;
        }
      }
      if (null == stdPort) {
        stdPort = PortExt.createStandardPort();
        component.getOwnedFeatures().add(stdPort);
      }
      stdPort.getProvidedInterfaces().add(interf);
    }
  }

  /**
   * This method adds an required interface. If the StandardPort doesn't exist, it will be create.
   */
  @Override
  public void addRequiredInterface(Component component, Interface interf) {
    ComponentPort stdPort = null;
    if ((component != null) && (interf != null)) {
      for (Partition partition : component.getOwnedPartitions()) {
        if (partition instanceof ComponentPort) {
          stdPort = (ComponentPort) partition;
          break;
        }
      }
      if (null == stdPort) {
        stdPort = PortExt.createStandardPort();
        component.getOwnedFeatures().add(stdPort);
      }
      stdPort.getRequiredInterfaces().add(interf);
    }
  }

  /**
   * This method adds an interface usage.
   * 
   * @param component
   *          the component who will use the interface
   * @param interf
   *          the used interface
   */
  @Override
  public void addUsedInterface(Component component, Interface interf) {
    if ((component != null) && (interf != null)) {
      if (!getUsedInterfaces(component).contains(interf)) {
        InterfaceUse use = CsFactory.eINSTANCE.createInterfaceUse();
        use.setUsedInterface(interf);
        component.getOwnedInterfaceUses().add(use);
      }
    }
  }

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
  @Override
  public void addCommunicationLink(Component component, CommunicationLink communicationLink) {
    if ((component != null) && (communicationLink != null)) {
      if (!getExchangeItems(component).contains(communicationLink.getExchangeItem())) {
        CommunicationLink lnk = CommunicationFactory.eINSTANCE.createCommunicationLink();
        lnk.setKind(communicationLink.getKind());
        lnk.setProtocol(communicationLink.getProtocol());
        lnk.setExchangeItem(communicationLink.getExchangeItem());
        component.getOwnedCommunicationLinks().add(lnk);
      }
    }
  }

  /**
   * 
   */
  @Override
  public List<AbstractExchangeItem> getExchangeItems(Component component) {
    List<AbstractExchangeItem> result = new ArrayList<AbstractExchangeItem>();
    if (component != null) {
      for (CommunicationLink lnk : component.getOwnedCommunicationLinks()) {
        ExchangeItem exchangeItem = lnk.getExchangeItem();
        if (null != exchangeItem) {
          result.add(exchangeItem);
        }
      }
    }
    return result;
  }

  /**
   * Return all exchange items allocated by all functions port of the given component
   * 
   * @param component
   * @return componentFunctionExchangeItems
   */
  @Override
  public Set<AbstractExchangeItem> getAllocatedFunctionExchangeItems(Component component) {
    Set<AbstractExchangeItem> componentFunctionExchangeItems = new HashSet<AbstractExchangeItem>();
    Set<AbstractFunction> allocatedFunctions = (Set<AbstractFunction>) ComponentExt.getAllocatedFunctions(component);
    for (AbstractFunction abstractFunction : allocatedFunctions) {
      componentFunctionExchangeItems = (Set<AbstractExchangeItem>) AbstractFunctionExt
          .getAllExchangeItems(abstractFunction);
    }
    return componentFunctionExchangeItems;
  }

  /**
   * This method removes an exchange item realization.
   * 
   * @param component
   *          the component who is using the interface
   * @param communicationLink
   *          the communication link
   */
  @Override
  public void removeCommunicationLink(Component component, CommunicationLink communicationLink) {
    CommunicationLink link = null;
    if (null != communicationLink) {
      ListIterator<CommunicationLink> it = component.getOwnedCommunicationLinks().listIterator();
      while (it.hasNext()) {
        CommunicationLink lnk = it.next();
        if (lnk.getExchangeItem().equals(communicationLink.getExchangeItem())
            && lnk.getProtocol().equals(communicationLink.getProtocol())
            && lnk.getKind().equals(communicationLink.getKind())) {
          link = lnk;
        }
      }
    }
    if (link != null) {
      link.destroy();
    }
  }

  /**
   * @param message
   * @param selectedOperation
   * @param portStrategie
   * @param targetOnExchangeItem
   */
  @Override
  public void ensureUseAndImplementsForOperation(SequenceMessage message,
      ExchangeItemAllocation selectedOperation, boolean portStrategie, EObject targetOnExchangeItem) {
    AbstractInstance roleSource = message.getSendingEnd() == null ? null
        : message.getSendingEnd().getCovered().getRepresentedInstance();
    AbstractInstance roleTarget = message.getReceivingEnd() == null ? null
        : message.getReceivingEnd().getCovered().getRepresentedInstance();

    Component src = null;
    Component tgt = null;

    // Get source component (if any).
    if ((roleSource != null) && (roleSource.getAbstractType() instanceof Component)) {
      src = (Component) roleSource.getAbstractType();
    }
    // Get target component (if any).
    if ((roleTarget != null) && (roleTarget.getAbstractType() instanceof Component)) {
      tgt = (Component) roleTarget.getAbstractType();
    }

    // communication pattern : if the message has an EIAllocation which is a READ, there is a logical
    // inversion of use/implements. On departure of the message, it is the component which implements the interface
    // allocating the shared data and not its use.
    if ((src == null) || (tgt == null)) {
      // To be a READ a message must have the following features:
      // - Its target must be an ExchangeItemInstance (on an Execution or directly on the InstanceRole),
      // - And it must be a SYNCHRONOUS CALL.
      if ((roleTarget instanceof ExchangeItemInstance)
          && ((targetOnExchangeItem instanceof Execution) || (targetOnExchangeItem instanceof InstanceRole))
          && (MessageKind.SYNCHRONOUS_CALL == message.getKind())) {
        Component temp = src;
        src = tgt;
        tgt = temp;
      }
    }

    Interface interf = selectedOperation.getAllocatingInterface();

    // computing all interfaces used by the source component
    Collection<Interface> allUsedInterfaces = new HashSet<Interface>();
    for (Component comp : getAllAncestors(src)) {
      allUsedInterfaces.addAll(comp.getUsedInterfaces());
      for (Feature f : comp.getOwnedFeatures()) {
        if (f instanceof ComponentPort) {
          ComponentPort port = (ComponentPort) f;
          allUsedInterfaces.addAll(port.getRequiredInterfaces());
        }
      }
    }

    // computing all interfaces implemented by the target component
    Collection<Interface> allImplementedInterfaces = new HashSet<Interface>();
    for (Component comp : getAllAncestors(tgt)) {
      allImplementedInterfaces.addAll(comp.getImplementedInterfaces());
      for (Feature f : comp.getOwnedFeatures()) {
        if (f instanceof ComponentPort) {
          ComponentPort port = (ComponentPort) f;
          allImplementedInterfaces.addAll(port.getProvidedInterfaces());
        }
      }
    }

    // propagation to interface hierarchy
    allUsedInterfaces.addAll(InterfaceExt.getAllSuperGeneralizableElements(allUsedInterfaces));
    allImplementedInterfaces.addAll(InterfaceExt.getAllSuperGeneralizableElements(allImplementedInterfaces));

    if ((src != null) && !allUsedInterfaces.contains(interf)) {
      if (portStrategie) {
        ComponentPort port = FaFactory.eINSTANCE.createComponentPort("Requires " + interf.getName()); //$NON-NLS-1$
        port.getRequiredInterfaces().add(interf);
        src.getOwnedFeatures().add(port);
      } else {
        // missing usage ling from src
        InterfaceUse usage = CsFactory.eINSTANCE.createInterfaceUse();
        src.getOwnedInterfaceUses().add(usage);
        usage.setUsedInterface(interf);
      }
    }
    if ((tgt != null) && !allImplementedInterfaces.contains(interf)) {
      if (portStrategie) {
        ComponentPort port = FaFactory.eINSTANCE.createComponentPort("Provides " + interf.getName()); //$NON-NLS-1$
        port.getProvidedInterfaces().add(interf);
        tgt.getOwnedFeatures().add(port);
      } else {
        // missing implementation ling from tgt
        InterfaceImplementation impl = CsFactory.eINSTANCE.createInterfaceImplementation();
        tgt.getOwnedInterfaceImplementations().add(impl);
        impl.setImplementedInterface(interf);
      }
    }
  }

  @Override
  public List<Component> getAllAncestors(Component component) {
    List<Component> result = new ArrayList<Component>();
    GeneralizableElement container = component;
    while (container != null) {
      if (container instanceof Component) {
        result.add((Component) container);
      }
      List<GeneralizableElement> parents = container.getSuper();
      if (parents.size() == 0) {
        container = null;
      } else {
        container = parents.get(0);
      }
    }
    return result;
  }

  /**
   * @param cpnt
   * @return
   */
  @Override
  public List<Part> getAllComponentInstances(Component cpnt) {
    List<Part> instList = new ArrayList<Part>();

    for (EObject obj : EObjectExt.getAll(cpnt, CsPackage.Literals.PART)) {
      instList.add((Part) obj);
    }

    return instList;
  }

  /**
   * This method retrieves all the implemented interfaces.
   * 
   * @param cpnt
   *          the component whose implemented AND provided (by StandardPorts) interfaces will be retrieved (and all its
   *          inherited components)
   * @return List<Interface> the implemented interfaces
   */
  @Override
  public List<Interface> getAllImplementedAndProvidedInterfaces(Component cpnt) {
    List<Interface> implementedAndProvidedItf = new ArrayList<Interface>();

    if (cpnt != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt);
      superComponents.add(cpnt);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          for (InterfaceImplementation impl : ((Component) element).getImplementedInterfaceLinks()) {
            Interface itf = impl.getImplementedInterface();
            if ((itf != null) && !implementedAndProvidedItf.contains(itf)) {
              implementedAndProvidedItf.add(itf);
              for (GeneralizableElement elt : GeneralizableElementExt.getAllSuperGeneralizableElements(itf)) {
                if (!implementedAndProvidedItf.contains(elt)) {
                  implementedAndProvidedItf.add((Interface) elt);
                }
              }
            }
          }
          for (Interface abstractItf : ((Component) element).getProvidedInterfaces()) {
            if (!implementedAndProvidedItf.contains(abstractItf)) {
              implementedAndProvidedItf.add(abstractItf);
              for (GeneralizableElement elt : GeneralizableElementExt.getAllSuperGeneralizableElements(abstractItf)) {
                if (!implementedAndProvidedItf.contains(elt)) {
                  implementedAndProvidedItf.add((Interface) elt);
                }
              }
            }
          }
        }
      }
    }
    return implementedAndProvidedItf;
  }

  /**
   * This method retrieves all the implemented interfaces.
   * 
   * @param cpnt
   *          the component whose implemented interfaces will be retrieved (and all its inherited components)
   * @return List<Interface> the implemented interfaces
   */
  @Override
  public List<Interface> getAllImplementedInterfaces(Component cpnt) {
    List<Interface> implementedInterfaces = new ArrayList<Interface>();

    if (cpnt != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt);
      superComponents.add(cpnt);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          for (InterfaceImplementation impl : ((Component) element).getImplementedInterfaceLinks()) {
            Interface itf = impl.getImplementedInterface();
            if ((itf != null) && !implementedInterfaces.contains(itf)) {
              implementedInterfaces.add(itf);
            }
          }
        }
      }
    }
    return implementedInterfaces;
  }

  /**
   * obtains all relationships ending to cpnt
   * 
   * @param cpnt
   * @return
   */
  @Override
  public List<Relationship> getAllIncomingRelationships(Component cpnt) {
    List<Relationship> ret = new ArrayList<Relationship>();

    return ret;

  }

  /**
   * obtains all relationships starting from cpnt
   * 
   * @param cpnt
   * @return
   */
  @Override
  public List<Relationship> getAllOutgoingRelationships(Component cpnt) {
    List<Relationship> ret = new ArrayList<Relationship>();

    ret.addAll(cpnt.getUsedInterfaceLinks());
    ret.addAll(cpnt.getImplementedInterfaceLinks());

    return ret;
  }

  /**
   * This method retrieves all the provided interfaces recursively
   * 
   * @param component
   * @return
   */
  @Override
  public List<Interface> getAllProvidedInterfaces(Component cpnt) {
    List<Interface> requiredItf = new ArrayList<Interface>();

    if (cpnt != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt);
      superComponents.add(cpnt);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          for (Interface abstractItf : ((Component) element).getProvidedInterfaces()) {
            if (!requiredItf.contains(abstractItf)) {
              requiredItf.add(abstractItf);
            }
          }
        }
      }
    }
    return requiredItf;
  }

  /**
   * Return all the connection related to given Component filter by Connection kind Doesn't include CE linked to ports
   */
  @Override
  public Collection<ComponentExchange> getAllRelatedComponentExchange(Part part) {
    return getAllRelatedComponentExchange(part, false);
  }

  /**
   * Return all the connection related to given Component filter by Connection kind According to given
   * includePortOfType, returns also CE from ports of the type
   */
  @Override
  public Collection<ComponentExchange> getAllRelatedComponentExchange(Part part, boolean includePortOfType) {

    List<ComponentExchange> result = new ArrayList<>();

    if (part.getAbstractType() instanceof Component) {

      EList<AbstractInformationFlow> informationFlows = part.getInformationFlows();
      for (AbstractInformationFlow abstractInformationFlow : informationFlows) {
        if (abstractInformationFlow instanceof ComponentExchange) {
          result.add((ComponentExchange) abstractInformationFlow);
        }
      }
      for (ComponentExchangeEnd end : getCache(FunctionalExt::getRelatedComponentExchangeEnds, part)) {
        EObject owner = end.eContainer();
        if (owner instanceof ComponentExchange) {
          result.add((ComponentExchange) owner);
        }
      }

      if (includePortOfType) {
        List<ComponentPort> ownedComponentPort = getOwnedComponentPort((Component) part.getAbstractType());
        // retrieve connections from owned component Ports
        for (ComponentPort componentPort : ownedComponentPort) {
          result.addAll(componentPort.getComponentExchanges());
        }
      }
    }

    return result;

  }

  /**
   * Return all the connection related to given Component filter by Connection kind
   * 
   * @param component
   * @return
   */
  @Override
  public Collection<ComponentExchange> getAllRelatedComponentExchange(Component component) {
    return getAllRelatedComponentExchange(component, true);
  }

  /**
   * Return all the connection related to given Component filter by Connection kind
   * 
   * @param component
   * @return
   */
  @Override
  public Collection<ComponentExchange> getAllRelatedComponentExchange(Component component,
      boolean addRelatedOfParts) {
    List<ComponentExchange> result = new ArrayList<ComponentExchange>();

    List<ComponentPort> ownedComponentPort = getOwnedComponentPort(component);
    // retrieve connections from owned component Ports
    for (ComponentPort componentPort : ownedComponentPort) {
      result.addAll(componentPort.getComponentExchanges());
    }

    // returns also communication means
    if (component instanceof Entity) {
      for (AbstractInformationFlow flow : ((Entity) component).getInformationFlows()) {
        if (flow instanceof CommunicationMean) {
          result.add((CommunicationMean) flow);
        }
      }
    }

    if (addRelatedOfParts) {
      // retrieve connection from typed elements (today only Part are valid)
      EList<AbstractTypedElement> abstractTypedElements = component.getAbstractTypedElements();
      for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
        if (abstractTypedElement instanceof Part) {
          Part part = (Part) abstractTypedElement;
          result.addAll(getAllRelatedComponentExchange(part, false));
        }
      }
    }

    return result;
  }

  /**
   * Return all the connection related to given Component filter by Connection kind
   * 
   * @param component
   * @return
   */
  @Override
  public Collection<ComponentExchange> getAllRelatedConnectionByKind(Component component,
      ComponentExchangeKind kind) {
    List<ComponentExchange> result = new ArrayList<>();

    if (null == component) {
      return null;
    }

    // filter connection by kind
    Collection<ComponentExchange> allRelatedConnection = getCache(ComponentExt::getAllRelatedComponentExchange,
        component);
    result.addAll(allRelatedConnection);
    if (null != kind) {
      for (ComponentExchange connection : allRelatedConnection) {
        if (connection.getKind() != kind) {
          result.remove(connection);
        }
      }
    }

    return result;

  }

  /**
   * This method retrieves all the required interfaces recursively
   * 
   * @param component
   * @return
   */
  @Override
  public List<Interface> getAllRequiredInterfaces(Component cpnt) {
    List<Interface> providedItf = new ArrayList<Interface>();

    if (cpnt != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt);
      superComponents.add(cpnt);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          for (Interface abstractItf : ((Component) element).getRequiredInterfaces()) {
            if (!providedItf.contains(abstractItf)) {
              providedItf.add(abstractItf);
            }
          }
        }
      }

    }
    return providedItf;
  }

  /**
   * get all the States and Modes from current Component
   * 
   * @param ele
   * @param comp
   * @return
   */
  @Override
  public List<CapellaElement> getAllStatesAndModesFromComponent(Component comp) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(1);

    // collect all the modes form Component -> StateMachin
    if (comp != null) {
      EList<StateMachine> ownedStateMachines = comp.getOwnedStateMachines();
      for (StateMachine stateMachine : ownedStateMachines) {
        TreeIterator<Object> allContents = EcoreUtil.getAllContents(stateMachine, false);
        while (allContents.hasNext()) {
          Object object = allContents.next();
          if (object instanceof State) {
            result.add((CapellaElement) object);
          }
        }
      }
    }

    return result;
  }

  @Override
  public Collection<Part> getAllSubUsedParts(Component component, boolean useDeploymentLinks) {
    Collection<Part> result = new HashSet<Part>();

    for (Partition partition : component.getRepresentingPartitions()) {
      if (partition instanceof Part) {
        result.add((Part) partition);
      }
    }

    return getAllSubUsedParts(result, useDeploymentLinks);
  }

  @Override
  public Collection<Part> getAllSubUsedParts(Part part, boolean useDeploymentLinks) {
    return getAllSubUsedParts(Collections.singletonList(part), useDeploymentLinks);
  }

  private Collection<Part> getAllSubUsedParts(Collection<Part> parts, boolean useDeploymentLinks) {
    Collection<Part> comps = new HashSet<Part>();
    Collection<Part> visited = new ArrayList<Part>();

    LinkedList<Part> subs = new LinkedList<Part>();
    List<Part> initial = new LinkedList<Part>();

    initial.addAll(parts);
    subs.addAll(parts);

    while (subs.size() > 0) {
      Part sub = subs.removeFirst();
      if (!visited.contains(sub)) {
        visited.add(sub);

        if ((sub.getAbstractType() != null) && (sub.getAbstractType() instanceof Component)) {
          List<Part> internal = ComponentExt.getSubParts((Component) sub.getAbstractType(), false);
          comps.addAll(internal);
          subs.addAll(internal);
        }

        if (useDeploymentLinks) {
          List<Part> internal = PartExt.getDeployedParts(sub);
          comps.addAll(internal);
          subs.addAll(internal);
        }

      }
    }
    return comps;
  }

  /**
   * Returns sub components of the component. (include also the given component)
   */
  @Override
  public Collection<Component> getAllSubUsedComponents(Component component) {
    // Collection<Component> rsult = PartExt.getComponentsOfParts(getAllSubUsedParts(component, false));

    Collection<Component> comps = new HashSet<Component>();
    Collection<Component> visited = new ArrayList<Component>();

    LinkedList<Component> subs = new LinkedList<Component>();

    subs.add(component);
    while (subs.size() > 0) {
      Component sub = subs.removeFirst();
      if (!visited.contains(sub)) {
        visited.add(sub);
        List<Component> internal = ComponentExt.getSubUsedComponents(sub);
        comps.addAll(internal);
        subs.addAll(internal);
      }
    }

    return comps;
  }

  /**
   * This method retrieves all the used interfaces.
   * 
   * @param cpnt
   *          the component whose used AND required (by StandardPorts) interfaces will be retrieved (and all its
   *          inherited components)
   * @return List<Interface> the used and required interfaces
   */
  @Override
  public List<Interface> getAllUsedAndRequiredInterfaces(Component cpnt) {
    List<Interface> usedAndRequiredItf = new ArrayList<Interface>();

    if (cpnt != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt);
      superComponents.add(cpnt);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          for (InterfaceUse use : ((Component) element).getUsedInterfaceLinks()) {
            Interface itf = use.getUsedInterface();
            if ((itf != null) && !usedAndRequiredItf.contains(itf)) {
              usedAndRequiredItf.add(itf);
              for (GeneralizableElement elt : GeneralizableElementExt.getAllSuperGeneralizableElements(itf)) {
                if (!usedAndRequiredItf.contains(elt)) {
                  usedAndRequiredItf.add((Interface) elt);
                }
              }
            }
          }
          for (Interface abstractItf : ((Component) element).getRequiredInterfaces()) {
            if (!usedAndRequiredItf.contains(abstractItf)) {
              usedAndRequiredItf.add(abstractItf);
              for (GeneralizableElement elt : GeneralizableElementExt.getAllSuperGeneralizableElements(abstractItf)) {
                if (!usedAndRequiredItf.contains(elt)) {
                  usedAndRequiredItf.add((Interface) elt);
                }
              }
            }
          }
        }
      }

    }
    return usedAndRequiredItf;
  }

  /**
   * This method retrieves all the used interfaces.
   * 
   * @param cpnt
   *          the component whose used interfaces will be retrieved (and all its inherited components)
   * @return List<Interface> the used interfaces
   */
  @Override
  public List<Interface> getAllUsedInterfaces(Component cpnt) {
    List<Interface> usedInterfaces = new ArrayList<Interface>();

    if (cpnt != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt);
      superComponents.add(cpnt);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          for (InterfaceUse use : ((Component) element).getUsedInterfaceLinks()) {
            Interface itf = use.getUsedInterface();
            if ((itf != null) && !usedInterfaces.contains(itf)) {
              usedInterfaces.add(itf);
            }
          }
        }
      }
    }
    return usedInterfaces;
  }

  /**
   * Returns all components which are available by namespace.
   */
  @Override
  public Collection<Component> getAvailableComponentsByNamespace(EObject component) {
    Collection<Component> components = new java.util.HashSet<Component>();

    EObject rootComponent = component;

    // Add components accessible by namespace
    while (rootComponent != null) {
      if (rootComponent instanceof BlockArchitecture) {
        components.addAll(getSubDefinedComponents((BlockArchitecture) rootComponent));
      } else if (rootComponent instanceof Component) {
        components.addAll(getSubDefinedComponents((Component) rootComponent));
      }
      rootComponent = rootComponent.eContainer();
    }

    return components;
  }

  /**
   * Returns parents and brothers of parents components of parts of the given object
   */
  @Override
  public Collection<EObject> getAvailableComponentsByNamespaceOfParts(Deque<EObject> ownerParts) {
    Collection<EObject> components = new HashSet<>();

    // Access from all hierarchy of components and blockarchitectures, all sub components and actors
    while (!ownerParts.isEmpty()) {
      EObject owner = ownerParts.removeFirst();
      EObject parent = owner;
      if ((parent != null) && !components.contains(parent)) {

        if (parent instanceof BlockArchitecture) {
          BlockArchitecture parentArchitecture = (BlockArchitecture) parent;
          components.addAll(getSubDefinedComponents(parentArchitecture));
          components.addAll(getSubDefinedActors(parentArchitecture));

        } else if (parent instanceof Component) {
          Component parentComponent = (Component) parent;
          components.add(parentComponent);
          components.addAll(getSubDefinedComponents(parentComponent));
        }

        ownerParts.addLast(parent.eContainer());
      }
    }

    return components;
  }

  /**
   * Returns parents and brothers of parents components of parts of the given object
   */
  @Override
  public Collection<EObject> getAvailableComponentsByNamespaceOfParts(Part part) {
    LinkedList<EObject> ownerParts = new LinkedList<EObject>();
    ownerParts.add(part);
    return getAvailableComponentsByNamespaceOfParts(ownerParts);
  }

  /**
   * This method is deprecated: cycles can occur in multi part mode.
   * 
   * @param c1
   * @param c2
   * @return
   */
  @Override
  @Deprecated
  public Component getCommonComponentAncestor(Component c1, Component c2) {
    Component ancestor = c1;
    if (isComponentAncestor(c2, ancestor)) {
      return c2;
    }
    while (!isComponentAncestor(ancestor, c2)) {
      if (ancestor.getAbstractTypedElements().get(0) instanceof Part) {
        Part ancestorPart = (Part) ancestor.getAbstractTypedElements().get(0);
        EObject parentAncestor = getDirectParent(ancestorPart);
        if (parentAncestor instanceof Component) {
          ancestor = (Component) parentAncestor;
        } else {
          break;
        }
      } else {
        break;
      }
    }
    return ancestor;
  }

  /**
   * Gets all the components contained in a component
   * 
   * @param component
   *          the parent component
   * @return list of components
   */
  @Override
  public List<Component> getComponentsFromComponent(Component component) {
    List<Component> list = new ArrayList<Component>();
    for (Object obj : component.eContents()) {
      if (obj instanceof Component) {
        list.add((Component) obj);
      } else if (obj instanceof Structure) {
        for (Object content : ((Structure) obj).eContents()) {
          if (content instanceof Component) {
            list.add((Component) content);
            list.addAll(getComponentsFromComponent((Component) content));
          }
        }
      }
    }
    return list;
  }

  /**
   * Return the Part list involved in decomposition for the Component given in parameter
   */
  @Override
  public List<Part> getDecompositionPartInvolved(Component lc) {
    List<Part> decompPartInvolved = new ArrayList<Part>();
    for (Partition partition : lc.getOwnedPartitions()) {
      AbstractType currentLc = partition.getAbstractType();
      if ((currentLc instanceof Component) && !decompPartInvolved.contains(partition)) {
        decompPartInvolved.add((Part) partition);
      }
    }
    return decompPartInvolved;
  }

  /**
   * @return the list of Component direct common parent for componentA and componentB.
   * @return null if not have a direct common parent.
   */
  @Override
  public List<Component> getDirectCommonParent(Component componentA, Component componentB) {
    List<Component> lstDirCommonParents = new ArrayList<Component>();
    List<Component> parentsComponents_A = ComponentExt.getDirectParents(componentA);
    for (Component parentB : ComponentExt.getDirectParents(componentB)) {
      if (parentsComponents_A.contains(parentB)) {
        lstDirCommonParents.add(parentB);
      }
    }
    return lstDirCommonParents;
  }

  /**
   * @return component owning the the part given in parameter
   */
  @Override
  public Component getDirectParent(Partition part) {
    return (Component) EcoreUtil2.getFirstContainer(part, CsPackage.Literals.COMPONENT);
  }

  /**
   * @return component owning the component parts given in parameter
   */
  @Override
  public List<Component> getDirectParents(Component component) {
    List<Component> parentsComponents = new ArrayList<Component>();

    for (Partition partition : component.getRepresentingPartitions()) {
      Component partitionanableElt = (Component) EcoreUtil2.getFirstContainer(partition, CsPackage.Literals.COMPONENT);
      parentsComponents.add(partitionanableElt);
    }
    return parentsComponents;
  }

  /**
   * Retrieve the first common component ancestor for two elements with eClass = Port part or component return the
   * common component ancestor between source and target if source is target, return the c1 unlike
   * getCommonComponentAncestor
   */
  @Override
  public EObject getFirstCommonComponentAncestor(EObject source, EObject target, boolean useDeployementLinks) {
    if ((source == null) || (target == null)) {
      return null;
    }

    EObject src = source;
    EObject tgt = target;
    EObject result = null;

    LinkedList<EObject> sourceList = new LinkedList<EObject>();
    Collection<EObject> sourceListVisited = new HashSet<EObject>();

    ArrayList<EObject> targetList = new ArrayList<EObject>();
    Collection<EObject> targetListVisited = new HashSet<EObject>();

    // Retrieve ordonned list of containers for the target
    int i = 0;
    targetList.add(tgt);
    while (i < targetList.size()) {
      EObject targetA = targetList.get(i);
      targetListVisited.add(targetA);
      i++;

      for (EObject related : getRelatedElementsForCommonAncestor(targetA, useDeployementLinks)) {
        if ((related != null) && !targetListVisited.contains(related)) {
          targetList.add(related);
        }
      }
    }

    // Find for containers of source, if one is equals to one targetContainers
    sourceList.add(src);
    while (sourceList.size() > 0) {
      EObject sourceContainer = sourceList.removeFirst();
      if (sourceContainer == null) {
        continue;
      }
      sourceListVisited.add(sourceContainer);

      for (EObject targetContainer : targetList) {
        // If the sourceContainer is the targetContainer
        if (sourceContainer == targetContainer) {
          result = sourceContainer;
          break;
        }
      }

      if (result != null) {
        break;
      }

      for (EObject related : getRelatedElementsForCommonAncestor(sourceContainer, useDeployementLinks)) {
        if (!sourceListVisited.contains(related)) {
          sourceList.add(related);
        }
      }
    }

    if (result == null) {
      return null;
    }
    if (result instanceof Port) {
      result = result.eContainer();
    }
    if (result instanceof Part) {
      result = ((Part) result).getAbstractType();
    }
    return result;
  }

  @Override
  public EObject getFirstCommonComponentAncestor(EObject source, EObject target) {
    return getFirstCommonComponentAncestor(source, target, false);
  }

  /**
   * Get the most common ancestor of all given components. Returns <code>null</code> if none can be found.
   * 
   * @param components
   * @return
   */
  @Override
  public Component getFirstCommonComponentAncestor(List<Component> components) {
    if (components == null || components.isEmpty()) {
      return null;
    }
    Component commonAncestor = components.get(0);
    for (int i = 1; i < components.size(); ++i) {
      commonAncestor = (Component) getFirstCommonComponentAncestor(commonAncestor, components.get(i), true);
      if (commonAncestor == null) {
        return null;
      }
    }
    return commonAncestor;
  }

  /**
   * This method retrieves the implemented AND provided (by Ports) interfaces
   * 
   * @param component
   *          the component whose used and provided interfaces will be retrieved
   * @return List<Interface> the used interfaces
   */
  @Override
  public List<Interface> getImplementedAndProvidedInterfaces(Component component) {
    List<Interface> implementedAndProvidedItf;

    implementedAndProvidedItf = getImplementedInterfaces(component);
    for (Interface itfProvided : component.getProvidedInterfaces()) {
      if (!implementedAndProvidedItf.contains(itfProvided)) {
        implementedAndProvidedItf.add(itfProvided);
      }
    }
    return implementedAndProvidedItf;
  }

  /**
   * This method retrieves the implemented interfaces.
   * 
   * @param component
   *          the component whose implemented interfaces will be retrieved
   * @return List<Interface> the implemented interfaces
   */
  @Override
  public List<Interface> getImplementedInterfaces(Component component) {
    List<Interface> implementedInterfaces = new ArrayList<Interface>();

    if (component != null) {
      List<InterfaceImplementation> implementationSet = component.getImplementedInterfaceLinks();
      for (InterfaceImplementation impl : implementationSet) {
        Interface itf = impl.getImplementedInterface();
        if (itf != null) {
          implementedInterfaces.add(itf);
        }
      }
    }

    return implementedInterfaces;
  }

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
  @Override
  public List<Interface> getImplementedInterfacesFiltered(Component currentComp, Component component) {
    List<Interface> interfaceList = new ArrayList<Interface>();
    boolean isCheckRequired = !component.equals(currentComp);

    for (InterfaceImplementation interfaceImpl : currentComp.getImplementedInterfaceLinks()) {
      Interface inter = interfaceImpl.getImplementedInterface();
      if ((inter != null)) {
        if (isCheckRequired) {
          if (isImplementingInterface(component, inter)) {
            continue;
          }
        }
        interfaceList.add(inter);
      }
    }
    return interfaceList;
  }

  /**
   * Retrieve the Data pkg from the given component (create an interface pkg if null)
   */
  @Override
  public DataPkg getDataPkg(Component component, boolean create) {
    if ((component.getOwnedInterfacePkg() == null) && create) {
      // to externalize when constants in skeleton will be into helpers.
      DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name);
      component.setOwnedDataPkg(pkg);
    }
    return component.getOwnedDataPkg();
  }

  /**
   * Retrieve the interface pkg from the given component (create an interface pkg if null)
   */
  @Override
  public DataPkg getDataPkg(Component component) {
    return getDataPkg(component, true);
  }

  /**
   * Retrieve the interface pkg from the given component (create an interface pkg if null)
   */
  @Override
  public InterfacePkg getInterfacePkg(Component component, boolean create) {
    if ((component.getOwnedInterfacePkg() == null) && create) {
      // to externalize when constants in skeleton will be into helpers.
      InterfacePkg pkg = CsFactory.eINSTANCE.createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name);
      component.setOwnedInterfacePkg(pkg);
    }
    return component.getOwnedInterfacePkg();
  }

  /**
   * Retrieve the interface pkg from the given component (create an interface pkg if null)
   */
  @Override
  public InterfacePkg getInterfacePkg(Component component) {
    return getInterfacePkg(component, true);
  }

  /**
   * This method gets all the interfaces in Parent's hierarchy of current LC
   * 
   * @param component
   *          the Logical Component
   * @return List of interfaces
   */
  @Override
  public List<Interface> getInterfacesFromComponentParentHierarchy(Component component) {
    List<Interface> list = new ArrayList<Interface>();
    // Add interfaces from parent Logical Architecture
    BlockArchitecture arch = ComponentExt.getRootBlockArchitecture(component);
    if (null != arch) {
      list.addAll(InterfacePkgExt.getAllInterfaces(arch.getOwnedInterfacePkg()));
      // get recursively all the interfaces from the parent hierarchy
      list.addAll(InterfacePkgExt.getOwnedInterfacesFromBlockArchitectureParent(arch));
    }
    for (Component ancestor : ComponentExt.getComponentAncestors(component)) {
      list.addAll(InterfacePkgExt.getAllInterfaces(ancestor.getOwnedInterfacePkg()));
    }
    return list;
  }

  /**
   * Gets the owned ports.
   * 
   * @param component
   *          the given component
   * @return the owned port
   */
  @Override
  public List<ComponentPort> getOwnedComponentPort(Component component) {
    List<ComponentPort> returnedList = new ArrayList<ComponentPort>();
    for (Feature aFeature : component.getOwnedFeatures()) {
      if (aFeature instanceof ComponentPort) {
        returnedList.add((ComponentPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Gets the owned flow ports.
   * 
   * @param component
   *          the given component
   * @return the owned flow port
   */
  @Override
  public List<ComponentPort> getOwnedFlowPort(Component component) {
    List<ComponentPort> returnedList = new ArrayList<>();
    for (Feature aFeature : component.getOwnedFeatures()) {
      if (PortExt.isFlowPort(aFeature)) {
        returnedList.add((ComponentPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Gets the owned in flow ports.
   * 
   * @param component
   *          the given component
   * @return the owned in flow port
   */
  @Override
  public List<ComponentPort> getOwnedInFlowPort(Component component) {
    List<ComponentPort> returnedList = new ArrayList<>();
    for (Feature aFeature : component.getOwnedFeatures()) {
      if (PortExt.isIn((ComponentPort) aFeature) && PortExt.isFlowPort(aFeature)) {
        returnedList.add((ComponentPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Gets the owned out flow ports.
   * 
   * @param component
   *          the given component
   * @return the owned out flow port
   */
  @Override
  public List<ComponentPort> getOwnedOutFlowPort(Component component) {
    List<ComponentPort> returnedList = new ArrayList<>();
    for (Feature aFeature : component.getOwnedFeatures()) {
      if ((aFeature instanceof ComponentPort) && PortExt.isOut((ComponentPort) aFeature)
          && PortExt.isFlowPort(aFeature)) {
        returnedList.add((ComponentPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Gets the owned physical ports.
   * 
   * @param component
   *          the given component
   * @return the owned physical port
   */
  @Override
  public List<PhysicalPort> getOwnedPhysicalPort(Component component) {
    List<PhysicalPort> returnedList = new ArrayList<>();
    for (Feature aFeature : component.getOwnedFeatures()) {
      if (aFeature instanceof PhysicalPort) {
        returnedList.add((PhysicalPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Check if the compnent has physical ports.
   * 
   * @param component
   *          the given component
   */
  @Override
  public boolean hasPhysicalPort(Component component) {
    return !ComponentExt.getOwnedPhysicalPort(component).isEmpty();
  }

  /**
   * Gets the owned ports.
   * 
   * @param component
   *          the given component
   * @return the owned port
   */
  @Override
  public List<Port> getOwnedPort(Component component) {
    List<Port> returnedList = new ArrayList<>();
    for (Feature aFeature : component.getOwnedFeatures()) {
      if (aFeature instanceof Port) {
        returnedList.add((Port) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * @param modelElement
   * @return
   */
  @Override
  public Collection<ComponentExchange> getAllOwnedComponentExchanges(Component modelElement) {
    List<ComponentExchange> instList = new ArrayList<>();
    for (EObject obj : EObjectExt.getAll(modelElement, FaPackage.Literals.COMPONENT_EXCHANGE)) {
      instList.add((ComponentExchange) obj);
    }
    return instList;
  }

  /**
   * Gets the owned standard ports.
   * 
   * @param component
   *          the given component
   * @return the owned standard port
   */
  @Override
  public List<ComponentPort> getOwnedStandardPort(Component component) {
    List<ComponentPort> returnedList = new ArrayList<>();
    for (Feature aFeature : component.getOwnedFeatures()) {
      if (PortExt.isStandardPort(aFeature)) {
        returnedList.add((ComponentPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Get the Parent Component respecting the breakdown structure logic
   * 
   * @param component
   *          : component whose parent we are looking for
   * @return parent component doesn't work for multipart
   */
  @Override
  @Deprecated
  public Component getParent(Component component) {
    Component parent = null;
    for (Partition aPartition : component.getRepresentingPartitions()) {
      if (aPartition instanceof Part) {
        PartitionableElement ownerElement = getDirectParent(aPartition);
        if ((null == ownerElement) || !(ownerElement instanceof Component)) {
          return parent;
        }
        return (Component) ownerElement;
      }
    }
    return parent;
  }

  /**
   * Returns recursively all components which contains the given component.
   */
  @Override
  public Collection<Component> getComponentAncestors(Component component) {
    Collection<Component> result = new HashSet<Component>();
    for (Partition partition : component.getRepresentingPartitions()) {
      if (partition instanceof Part) {
        for (Part componentAncestor : ComponentExt.getPartAncestors((Part) partition)) {
          if (componentAncestor.getAbstractType() instanceof Component) {
            result.add((Component) componentAncestor.getAbstractType());
          }
        }
      }
    }
    return result;
  }

  @Override
  public Collection<Part> getPartAncestors(Part child) {
    return getPartAncestors(child, false);
  }

  /**
   * Returns recursively all parts which contains the given part. All representing partition of containers and all parts
   * deploying the given part
   */
  @Override
  public Collection<Part> getPartAncestors(Part child, boolean addGeneralizationOfParents) {
    Collection<Part> result = new ArrayList<Part>();
    HashSet<Part> visited = new HashSet<Part>();
    LinkedList<Partition> toVisit = new LinkedList<Partition>();

    if (child != null) {
      toVisit.add(child);

      while (toVisit.size() > 0) {
        Partition visit = toVisit.removeFirst();

        if (visit instanceof Part) {
          Part pvisit = (Part) visit;

          EObject parent = getDirectParent(pvisit);
          if ((parent != null) && (parent instanceof PartitionableElement)) {
            List<EObject> container = new ArrayList<EObject>();
            container.add(parent);

            // Retrieve also generalization of
            if (addGeneralizationOfParents && (parent instanceof GeneralizableElement)) {
              List<GeneralizableElement> elements = GeneralizableElementExt
                  .getAllSuperGeneralizableElements((GeneralizableElement) parent);
              container.addAll(elements);
            }

            for (EObject element : container) {
              if (element instanceof PartitionableElement) {
                for (Partition partition : ((PartitionableElement) element).getRepresentingPartitions()) {
                  if (partition instanceof Part) {
                    Part part = (Part) partition;
                    if (!visited.contains(part)) {
                      toVisit.addLast(part);
                      result.add(part);
                      visited.add(part);
                    }
                  }
                }
              }
            }

            for (DeploymentTarget target : PartExt.getDeployingElements(pvisit)) {
              if (target instanceof Part) {
                Part part = (Part) target;
                if (!visited.contains(part)) {
                  toVisit.addLast(part);
                  result.add(part);
                  visited.add(part);
                }
              }
            }
          }
        }
      }

    }

    return result;
  }

  private List<EObject> getRelatedElementsForCommonAncestor(EObject eobject, boolean useDeployementLinks) {

    List<EObject> result = new LinkedList<>();

    if (eobject instanceof Component) {
      for (Partition part : ((Component) eobject).getRepresentingPartitions()) {
        result.add(part);
      }

    } else if (eobject instanceof Port) {
      result.add(EcoreUtil2.getFirstContainer(eobject, CsPackage.Literals.COMPONENT));

    } else if (eobject instanceof Part) {
      List<DeploymentTarget> elements = PartExt.getDeployingElements((Part) eobject);
      if (useDeployementLinks && !elements.isEmpty()) {
        for (EObject e : elements) {
          result.add(e);
        }
      } else {
        result.add(EcoreUtil2.getFirstContainer(eobject, CsPackage.Literals.COMPONENT));
      }

    }

    return result;

  }

  /**
   * Returns functional exchanges which are related to allocated functions of the given component
   */
  @Override
  public Collection<FunctionalExchange> getRelatedFunctionalExchanges(Component component) {
    Collection<FunctionalExchange> functionalExchanges = new HashSet<>();

    for (Component cpnt : getAllSubUsedComponents(component)) {
      for (AbstractFunction af : cpnt.getAllocatedFunctions()) {
        functionalExchanges.addAll(FunctionExt.getIncomingExchange(af));
        functionalExchanges.addAll(FunctionExt.getOutGoingExchange(af));

      }
    }
    return functionalExchanges;
  }

  /**
   * Returns all allocated functions of the given component
   * 
   * @param component
   */
  @Override
  public Collection<AbstractFunction> getAllocatedFunctions(Component component) {
    Set<AbstractFunction> functionalExchanges = new HashSet<>();
    EList<AbstractFunction> allocatedFunctions = component.getAllocatedFunctions();
    for (AbstractFunction abstractFunction2 : allocatedFunctions) {
      AbstractFunction abstractFunction = abstractFunction2;
      functionalExchanges.add(abstractFunction);

    }
    return functionalExchanges;

  }

  /**
   * Returns related interfaces of components (implements, uses, provides, requires).
   */
  @Override
  public Collection<Interface> getRelatedInterfaces(Component component) {
    Collection<Interface> interfaces = new ArrayList<>();

    if (component != null) {
      interfaces.addAll(getImplementedAndProvidedInterfaces(component));
      interfaces.addAll(getUsedAndRequiredInterfaces(component));
    }

    return interfaces;
  }

  /**
   * Return the related interfaces of component from References(use, implement, required and provided links)
   * 
   * @param component
   * @return
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<Interface> getRelatedInterfacesFromReference(Component cpnt, EReference reference) {
    List<Interface> providedItf = new ArrayList<Interface>();
    if (cpnt != null) {
      EList<Interface> list = (EList<Interface>) cpnt.eGet(reference);
      for (Interface itf : list) {
        if (!providedItf.contains(itf)) {
          providedItf.add(itf);
        }
      }
    }
    return providedItf;
  }

  /**
   * This method retrieves all the required interfaces.
   * 
   * @param component
   * @return
   */
  @Override
  public List<Interface> getRequiredInterfaces(Component cpnt) {
    List<Interface> requiredItf = new ArrayList<Interface>();
    if (cpnt != null) {
      for (Interface itf : cpnt.getRequiredInterfaces()) {
        if (!requiredItf.contains(itf)) {
          requiredItf.add(itf);
        }
      }
    }
    return requiredItf;
  }

  /**
   * @param modelElement
   *          : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  @Override
  public BlockArchitecture getRootBlockArchitecture(ModelElement modelElement) {
    return BlockArchitectureExt.getRootBlockArchitecture(modelElement);
  }

  @Override
  public Component getRootComponent(Component component) {
    Component rootCpnt = null;
    if (null != component) {
      Object container = component.eContainer();
      if (container instanceof Component) {
        rootCpnt = (Component) container;
      } else if (container instanceof Structure) {
        rootCpnt = StructureExt.getRootComponent((Structure) container);
      }
    }
    return rootCpnt;
  }

  /**
   * Gets the root component architecture from the current component
   * 
   * @param component
   *          the current component
   * @return the root component architecture
   */
  @Override
  public ComponentArchitecture getRootComponentArchitecture(Component component) {
    ComponentArchitecture compArch = null;
    if (null != component) {
      Object container = component.eContainer();

      if (container instanceof Component) {
        compArch = getRootComponentArchitecture((Component) container);
      } else if (container instanceof ComponentArchitecture) {
        compArch = (ComponentArchitecture) container;
      } else if (container instanceof Structure) {
        compArch = StructureExt.getRootComponentArchitecture((Structure) container);
      }
    }
    return compArch;
  }

  /**
   * Gets the sub defined actors.
   */
  @Override
  public List<? extends Component> getSubDefinedActors(BlockArchitecture architecture) {
    List<Component> elements = new ArrayList<>();

    Component component = BlockArchitectureExt.getContext(architecture, false);
    if (component != null) {
      for (Component compo : getSubDefinedComponents(component)) {
        if (compo instanceof AbstractActor) {
          elements.add(compo);
        }
      }
    }

    if (architecture instanceof SystemAnalysis) {
      elements.addAll(ActorPkgExt.getAllActors(((SystemAnalysis) architecture).getOwnedActorPkg()));

    } else if (architecture instanceof LogicalArchitecture) {
      elements.addAll(ActorPkgExt.getAllActors(((LogicalArchitecture) architecture).getOwnedLogicalActorPkg()));

    } else if (architecture instanceof PhysicalArchitecture) {
      elements.addAll(ActorPkgExt.getAllActors(((PhysicalArchitecture) architecture).getOwnedPhysicalActorPkg()));

    }

    return elements;

  }

  /**
   * Returns components defined into the component.
   */
  @Override
  public List<Component> getSubDefinedComponents(Component object) {
    List<Component> elements = new ArrayList<Component>();

    if (object instanceof LogicalComponent) {
      elements.addAll(((LogicalComponent) object).getOwnedLogicalComponents());
      for (LogicalComponentPkg pkg : ((LogicalComponent) object).getOwnedLogicalComponentPkgs()) {
        getSubDefinedComponents(pkg, elements);
      }

    } else if (object instanceof PhysicalComponent) {
      elements.addAll(((PhysicalComponent) object).getOwnedPhysicalComponents());
      for (PhysicalComponentPkg pkg : ((PhysicalComponent) object).getOwnedPhysicalComponentPkgs()) {
        getSubDefinedComponents(pkg, elements);
      }
    } else if (object instanceof ConfigurationItem) {
      elements.addAll(((ConfigurationItem) object).getOwnedConfigurationItems());
      for (ConfigurationItemPkg pkg : ((ConfigurationItem) object).getOwnedConfigurationItemPkgs()) {
        getSubDefinedComponents(pkg, elements);
      }
    }

    for (Part part : getSubParts(object)) {
      if ((part.getOwnedAbstractType() != null) && (part.getOwnedAbstractType() instanceof Component)) {
        elements.add((Component) part.getOwnedAbstractType());
        elements.addAll(getSubDefinedComponents((Component) part.getOwnedAbstractType()));
      }
    }
    return elements;
  }

  /**
   * Fill the collection with components defined into the physical component package.
   */
  private void getSubDefinedComponents(ConfigurationItemPkg compoPkg, Collection<Component> components) {
    if (compoPkg == null) {
      return;
    }
    components.addAll(compoPkg.getOwnedConfigurationItems());
    for (ConfigurationItemPkg pkg : compoPkg.getOwnedConfigurationItemPkgs()) {
      getSubDefinedComponents(pkg, components);
    }
  }

  /**
   * Fill the collection with components defined into the logical component package.
   */
  private void getSubDefinedComponents(LogicalComponentPkg compoPkg, Collection<Component> components) {
    if (compoPkg == null) {
      return;
    }
    components.addAll(compoPkg.getOwnedLogicalComponents());
    for (LogicalComponentPkg pkg : compoPkg.getOwnedLogicalComponentPkgs()) {
      getSubDefinedComponents(pkg, components);
    }
  }

  /**
   * Returns components defined into the architecture.
   */
  @Override
  public List<Component> getSubDefinedComponents(BlockArchitecture object) {
    List<Component> elements = new ArrayList<Component>();
    Component component = BlockArchitectureExt.getContext(object, false);
    if (component != null) {
      elements.addAll(getSubDefinedComponents(component));
    }

    if (object instanceof OperationalAnalysis) {
      getSubDefinedComponents(((OperationalAnalysis) object).getOwnedEntityPkg(), elements);
    }
    if (object instanceof SystemAnalysis) {
      elements.add(((SystemAnalysis) object).getOwnedSystem());

    } else if (object instanceof LogicalArchitecture) {
      elements.add(((LogicalArchitecture) object).getOwnedLogicalComponent());
      getSubDefinedComponents(((LogicalArchitecture) object).getOwnedLogicalComponentPkg(), elements);

    } else if (object instanceof PhysicalArchitecture) {
      elements.add(((PhysicalArchitecture) object).getOwnedPhysicalComponent());
      getSubDefinedComponents(((PhysicalArchitecture) object).getOwnedPhysicalComponentPkg(), elements);

    } else if (object instanceof EPBSArchitecture) {
      elements.add(((EPBSArchitecture) object).getOwnedConfigurationItem());
      getSubDefinedComponents(((EPBSArchitecture) object).getOwnedConfigurationItemPkg(), elements);
    }

    return elements;
  }

  /**
   * Fill the collection with components defined into the physical component package.
   */
  private void getSubDefinedComponents(EntityPkg entityPkg, Collection<Component> components) {
    if (entityPkg == null) {
      return;
    }
    components.addAll(entityPkg.getOwnedEntities());
    for (EntityPkg pkg : entityPkg.getOwnedEntityPkgs()) {
      getSubDefinedComponents(pkg, components);
    }
  }

  /**
   * Fill the collection with components defined into the physical component package.
   */
  private void getSubDefinedComponents(PhysicalComponentPkg compoPkg, Collection<Component> components) {
    if (compoPkg == null) {
      return;
    }
    components.addAll(compoPkg.getOwnedComponents());
    for (PhysicalComponentPkg pkg : compoPkg.getOwnedPhysicalComponentPkgs()) {
      getSubDefinedComponents(pkg, components);
    }
  }

  /**
   * Returns sub components of the component which are used (have a part)
   */
  @Override
  public List<Component> getSubUsedComponents(Component component) {
    return PartExt.getComponentsOfParts(getSubParts(component, false));
  }

  @Override
  public List<Component> getSubUsedAndDeployedComponents(Component component) {
    return PartExt.getComponentsOfParts(getSubParts(component, true));
  }

  @Override
  public List<Component> getSubUsedComponents(Component component, boolean useDeploymentLinks) {
    return PartExt.getComponentsOfParts(getSubParts(component, useDeploymentLinks));
  }

  /**
   * Returns sub part used by the component (have a part)
   */
  @Override
  public List<Part> getSubParts(PartitionableElement component) {
    List<Part> result = new ArrayList<Part>();
    for (Partition partition : component.getOwnedPartitions()) {
      if (partition instanceof Part) {
        result.add((Part) partition);
      }
    }
    // Add also part owned by component pks when available
    return result;
  }

  /**
   * Returns sub part used by the component (have a part).
   */
  @Override
  public List<Part> getSubParts(Component component, boolean useDeploymentLinks) {
    List<Part> result = new ArrayList<Part>();

    if (useDeploymentLinks) {
      for (Partition partition : component.getRepresentingPartitions()) {
        if (partition instanceof Part) {
          result.addAll(PartExt.getDeployedParts((Part) partition));
        }
      }
    }
    result.addAll(getSubParts(component));
    return result;
  }

  /**
   * This method retrieves the inherited components.
   * 
   * @param cpnt
   *          The component source.
   * @return The list of inherited components.
   */
  @Override
  public List<Component> getSuperComponents(Component cpnt) {
    List<Component> superComponents = new ArrayList<Component>();

    for (GeneralizableElement elt : cpnt.getSuper()) {
      if (elt instanceof Component) {
        superComponents.add((Component) elt);
      }
    }

    return superComponents;
  }

  /**
   * Returns representing parts of given component.
   * 
   * @param component
   * @return
   */
  @Override
  public Collection<Part> getRepresentingParts(Component component) {
    ArrayList<Part> result = new ArrayList<Part>();
    if (component != null) {
      for (Partition partition : component.getRepresentingPartitions()) {
        if (partition instanceof Part) {
          result.add((Part) partition);
        }
      }
    }
    return result;
  }

  /**
   * This method retrieves the used interfaces AND required (by Ports)
   * 
   * @param component
   *          the component whose used and required interfaces will be retrieved
   * @return List<Interface> the used interfaces
   */
  @Override
  public List<Interface> getUsedAndRequiredInterfaces(Component component) {
    List<Interface> usedAndRequiredItf;
    usedAndRequiredItf = getUsedInterfaces(component);
    for (Interface itfRequired : component.getRequiredInterfaces()) {
      if (!usedAndRequiredItf.contains(itfRequired)) {
        usedAndRequiredItf.add(itfRequired);
      }
    }
    return usedAndRequiredItf;
  }

  /**
   * This method retrieves the used interfaces.
   * 
   * @param component
   *          the component whose used interfaces will be retrieved
   * @return List<Interface> the used interfaces
   */
  @Override
  public List<Interface> getUsedInterfaces(Component component) {
    List<Interface> usedInterfaces = new ArrayList<Interface>();

    if (component != null) {
      List<InterfaceUse> useSet = component.getUsedInterfaceLinks();
      for (InterfaceUse use : useSet) {
        Interface itf = use.getUsedInterface();
        if (itf != null) {
          usedInterfaces.add(itf);
        }
      }
    }
    return usedInterfaces;
  }

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
  @Override
  public List<Interface> getUsedInterfacesFiltered(Component currentComp, Component component) {
    List<Interface> interfaceList = new ArrayList<Interface>();
    boolean isCheckRequired = !component.equals(currentComp);

    for (InterfaceUse interfaceUse : currentComp.getUsedInterfaceLinks()) {
      Interface inter = interfaceUse.getUsedInterface();
      if (inter != null) {
        if (isCheckRequired) {
          if (isUsingInterface(component, inter)) {
            continue;
          }
        }
        interfaceList.add(inter);
      }
    }
    return interfaceList;
  }

  /**
   * @return True if componentA and componentB have a direct common parent
   */
  @Override
  public boolean haveDirectCommonParent(Component componentA, Component componentB) {
    List<Component> parentsComponents_A = ComponentExt.getDirectParents(componentA);
    for (Component parentB : ComponentExt.getDirectParents(componentB)) {
      if (parentsComponents_A.contains(parentB)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether component used or require the exchange item by related interfaces
   */
  @Override
  public boolean isAnImplementedOrProvidedExchangeItem(Component component, AbstractExchangeItem exchangeItem) {
    // Enlarged search to StandardPorts
    List<Interface> itfLst = ComponentExt.getAllImplementedAndProvidedInterfaces(component);

    if (itfLst != null) {
      for (Interface itf : itfLst) {
        if (itf.getExchangeItems().contains(exchangeItem)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns whether component used or require the exchange item by related interfaces
   */
  @Override
  public boolean isAnUsedOrRequiredExchangeItem(Component component, AbstractExchangeItem exchangeItem) {
    // Enlarged search to StandardPorts
    List<Interface> itfLst = ComponentExt.getAllUsedAndRequiredInterfaces(component);

    if (itfLst != null) {
      for (Interface itf : itfLst) {
        if (itf.getExchangeItems().contains(exchangeItem)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean isComponentAncestor(Component ancestor, Component child) {
    boolean result = false;
    if (ancestor instanceof Entity) {
      if (((Entity) ancestor).getSubEntities().isEmpty()) {
        result = false;
      } else if (((Entity) ancestor).getSubEntities().contains(child)) {
        result = true;
      } else {
        for (Entity ent : ((Entity) ancestor).getSubEntities()) {
          if (isComponentAncestor(ent, child)) {
            result = true;
          }
        }
      }

    }

    else if (ancestor instanceof LogicalComponent) {
      if (((LogicalComponent) ancestor).getSubLogicalComponents().isEmpty()) {
        result = false;
      } else if (((LogicalComponent) ancestor).getSubLogicalComponents().contains(child)) {
        result = true;
      } else {
        for (LogicalComponent ent : ((LogicalComponent) ancestor).getSubLogicalComponents()) {
          if (isComponentAncestor(ent, child)) {
            result = true;
          }
        }
      }

    } else if (ancestor instanceof PhysicalComponent) {
      if (((PhysicalComponent) ancestor).getSubPhysicalComponents().isEmpty()) {
        result = false;
      } else if (((PhysicalComponent) ancestor).getSubPhysicalComponents().contains(child)) {
        result = true;
      } else {
        for (PhysicalComponent ent : ((PhysicalComponent) ancestor).getSubPhysicalComponents()) {
          if (isComponentAncestor(ent, child)) {
            result = true;
          }
        }
      }

    }

    else if (ancestor instanceof ComponentContext) {
      ComponentContext cContext = (ComponentContext) ancestor;
      for (Partition partition : ComponentExt.getSubParts(cContext)) {
        if (partition.getAbstractType() instanceof Component) {
          Component current = (Component) partition.getAbstractType();
          if ((current == child) || isComponentAncestor(current, child)) {
            result = true;
            break;
          }
        }
      }
    }

    return result;
  }

  /*
   * Return true if the the Component given in parameter is the ComponentRoot
   */
  @Override
  public boolean isComponentRoot(EObject element) {
    if (!(element instanceof Component)) {
      return false;
    }

    // Last Component hierarchy detection
    return (!EcoreUtil2.isContainedBy(element, CsPackage.Literals.COMPONENT));
  }

  /**
   * Retrieve part ancestors, deploying elements and owner
   * 
   * @param currentPart
   * @param parents
   */
  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Collection<Part> getBestPartAncestors(Part currentPart) {
    LinkedList<Part> parents = new LinkedList<>();
    Collection e = PartExt.getDeployingElements(currentPart);
    parents.addAll(e);
    if (e.isEmpty()) {
      EObject parent = ComponentExt.getDirectParent(currentPart);
      if (parent instanceof Component) {
        parents.addAll((Collection) ((Component) parent).getRepresentingPartitions());
      }
    }
    return parents;
  }

  @Override
  public Collection<EObject> filterNodes(Collection<EObject> elements) {
    Iterator<EObject> itElement = elements.iterator();
    while (itElement.hasNext()) {
      EObject element = itElement.next();
      if (element instanceof Part) {
        AbstractType type = ((Part) element).getAbstractType();
        if ((type instanceof PhysicalComponent)
            && (((PhysicalComponent) type).getNature() == PhysicalComponentNature.NODE)) {
          itElement.remove();
        }
      }
    }
    return elements;
  }

  /**
   * Retrieve whether both given part can be considered as "brother-linked" It should be defined in the same component
   * or be an actor/in the root system
   * 
   * @param source
   * @param target
   * @return
   */
  @Override
  public boolean isBrothers(Part source, Part target) {

    Collection<EObject> deployingSource = new HashSet<EObject>();
    deployingSource.addAll(PartExt.getDeployingElements(source));

    if (deployingSource.size() == 0) {
      EObject sourceContainer = EcoreUtil2.getFirstContainer(source, CsPackage.Literals.COMPONENT);
      if ((sourceContainer != null) && (sourceContainer instanceof Component)) {
        EObject sourceContainer2 = EcoreUtil2.getFirstContainer(sourceContainer, CsPackage.Literals.COMPONENT);
        if ((sourceContainer2 != null) && (sourceContainer2 instanceof Component)) {
          deployingSource.addAll(((PartitionableElement) sourceContainer).getRepresentingPartitions());
        }
      }
    }

    Collection<EObject> deployingTarget = new HashSet<EObject>();
    deployingTarget.addAll(PartExt.getDeployingElements(target));

    if (deployingTarget.size() == 0) {
      EObject targetContainer = EcoreUtil2.getFirstContainer(target, CsPackage.Literals.COMPONENT);
      if ((targetContainer != null) && (targetContainer instanceof Component)) {
        EObject targetContainer2 = EcoreUtil2.getFirstContainer(targetContainer, CsPackage.Literals.COMPONENT);
        if ((targetContainer2 != null) && (targetContainer2 instanceof Component)) {
          deployingTarget.addAll(((PartitionableElement) targetContainer).getRepresentingPartitions());
        }
      }
    }

    if ((deployingSource.size() == 0) && (deployingTarget.size() == 0)) {
      return true;
    }

    deployingSource.retainAll(deployingTarget);
    return deployingSource.size() > 0;
  }

  /**
   * This method evaluates if a Component is a composite or not.
   */
  @Override
  public boolean isComposite(Component component) {
    for (Partition partition : component.getOwnedPartitions()) {
      if (partition instanceof Part) {
        if ((partition.getType() == null) || (partition.getType() instanceof Component)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks whether the component is implementing the interface
   * 
   * @param component
   *          the component
   * @param itf
   *          the interface implemented
   * @return true if the component is implementing the interface
   */
  @Override
  public boolean isImplementingInterface(Component component, Interface itf) {
    if (component != null) {
      for (InterfaceImplementation interfaceImpl : component.getImplementedInterfaceLinks()) {
        if ((null != interfaceImpl.getImplementedInterface()) && interfaceImpl.getImplementedInterface().equals(itf)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks w whether one of the component and all its super type is implementing the interface
   * 
   * @param component
   * @param itf
   * @return true if the component is implementing the interface
   */
  @Override
  public boolean isImplementingInterfaceWithAllSuperType(Component component, Interface itf) {
    List<GeneralizableElement> superGeneralizableElements = GeneralizableElementExt
        .getAllSuperGeneralizableElements(component);
    superGeneralizableElements.add(component);
    for (CapellaElement generalizableElement : superGeneralizableElements) {
      if (generalizableElement instanceof Component) {
        for (InterfaceImplementation interfaceImpl : ((Component) generalizableElement)
            .getImplementedInterfaceLinks()) {
          if ((null != interfaceImpl.getImplementedInterface())
              && interfaceImpl.getImplementedInterface().equals(itf)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * This method evaluates if a Component is a leaf or not.
   * 
   * @param component
   *          the Component evaluated
   * @return 'true' if leaf, 'false' otherwise
   */
  @Override
  public boolean isLeaf(Component component) {
    return !isComposite(component);
  }

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
  @Override
  public boolean isSameTypeAndNotRootElement(EObject me1, EObject me2) {
    if ((me1 == null) || (me2 == null)) {
      return false;
    }

    if ((me1 instanceof LogicalComponent) && (me2 instanceof LogicalComponent)) {
      if (!((((LogicalComponent) me1).eContainer() instanceof LogicalComponent)
          || !(((LogicalComponent) me2).eContainer() instanceof LogicalComponent))) {
        return false;
      }
      return true;
    } else if ((me1 instanceof PhysicalComponent) && (me2 instanceof PhysicalComponent)) {
      if (!((((PhysicalComponent) me1).eContainer() instanceof PhysicalComponent)
          || !(((PhysicalComponent) me2).eContainer() instanceof PhysicalComponent))) {
        return false;
      }
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the component is using the interface
   * 
   * @param component
   *          the component
   * @param itf
   *          the interface used
   * @return true if the component is using the interface
   */
  @Override
  public boolean isUsingInterface(Component component, Interface itf) {
    if (component != null) {
      for (InterfaceUse interfaceUse : component.getUsedInterfaceLinks()) {
        if ((null != interfaceUse.getUsedInterface()) && interfaceUse.getUsedInterface().equals(itf)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks whether one of the component and all its super type is using the interface
   * 
   * @param component
   * @param itf
   * @return true if the component is using the interface
   */
  @Override
  public boolean isUsingInterfaceWithAllSuperType(Component component, Interface itf) {
    List<GeneralizableElement> superGeneralizableElements = GeneralizableElementExt
        .getAllSuperGeneralizableElements(component);
    superGeneralizableElements.add(component);
    for (CapellaElement generalizableElement : superGeneralizableElements) {
      if (generalizableElement instanceof Component) {
        for (InterfaceUse interfaceUse : ((Component) generalizableElement).getUsedInterfaceLinks()) {
          if ((null != interfaceUse.getUsedInterface()) && interfaceUse.getUsedInterface().equals(itf)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private Collection<EObject> createComponentExchangeAndDelegations(LinkedList<Part> sourceResults,
      ComponentPort sourcePort, Deque<Part> targetResults, ComponentPort targetPort) {

    Collection<EObject> results = new HashSet<EObject>();

    // If top-level parts are brother-linked, create delegations from lowest to top-level parts of both side
    // create a componentExchange from both top-level ports.
    boolean isMultipart = false;
    Part finalSourcePart = null;
    Part finalTargetPart = null;
    ComponentPort srcPort = sourcePort;
    ComponentPort tgtPort = targetPort;

    ComponentPortKind sourceKind = ComponentPortKind.FLOW;
    ComponentPortKind targetKind = ComponentPortKind.FLOW;

    OrientationPortKind sourceOrientation = OrientationPortKind.OUT;
    OrientationPortKind targetOrientation = OrientationPortKind.IN;
    if (srcPort != null) {
      sourceKind = srcPort.getKind();
      sourceOrientation = srcPort.getOrientation();
    }
    if (tgtPort != null) {
      targetKind = tgtPort.getKind();
      targetOrientation = tgtPort.getOrientation();
    }

    if (sourceResults.size() > 0) {
      // Create a delegation from sourcePort to the top-level part previously computed, and change sourcePort to the
      // top-level created port
      finalSourcePart = sourceResults.removeFirst();
      while (sourceResults.size() > 0) {
        Part source = sourceResults.removeFirst();
        ComponentExchange delegation = ComponentExchangeExt.createComponentExchange(source, null, finalSourcePart,
            srcPort, ComponentExchangeKind.DELEGATION, sourceKind, sourceOrientation, sourceKind, sourceOrientation,
            isMultipart);
        results.add(delegation);
        srcPort = (ComponentPort) ComponentExchangeExt.getSourcePort(delegation);
        finalSourcePart = source;
      }
    }

    if (targetResults.size() > 0) {
      // Create a delegation from targetPort to the top-level part previously computed, and change targetPort to the
      // top-level created port
      finalTargetPart = targetResults.removeFirst();
      while (targetResults.size() > 0) {
        Part target = targetResults.removeFirst();
        ComponentExchange delegation = ComponentExchangeExt.createComponentExchange(target, null, finalTargetPart,
            tgtPort, ComponentExchangeKind.DELEGATION, targetKind, targetOrientation, targetKind, targetOrientation,
            isMultipart);
        results.add(delegation);
        tgtPort = (ComponentPort) ComponentExchangeExt.getSourcePort(delegation);
        finalTargetPart = target;
      }
    }

    // Create a componentExchange between both top-level computed port
    if ((finalSourcePart != null) && (finalTargetPart != null)) {
      ComponentExchange exchange = ComponentExchangeExt.createComponentExchange(finalSourcePart, srcPort,
          finalTargetPart, tgtPort, ComponentExchangeKind.FLOW, sourceKind, sourceOrientation, targetKind,
          targetOrientation, isMultipart);
      results.add(exchange);
    }

    return results;
  }

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
  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public Collection<EObject> createComponentExchangeThroughDelegations(Part sourcePart, ComponentPort sourcePort,
      Part targetPart, ComponentPort targetPort) {

    Collection<EObject> results = new HashSet<EObject>();

    LinkedList<Part> sourceResults = new LinkedList<Part>();
    LinkedList<Part> targetResults = new LinkedList<Part>();

    boolean isBrothers = false;

    LinkedList<Part> sources = new LinkedList<Part>();
    sources.add(sourcePart);

    // compute both top-level parts which are brother-linked.
    while (sources.size() > 0) {
      Part source = sources.removeFirst();
      sourceResults.add(source);

      isBrothers = false;
      LinkedList<Part> targets = new LinkedList<Part>();
      targets.addFirst(targetPart);
      targetResults.clear();
      while ((targets.size() > 0)) {
        Part target = targets.removeFirst();
        targetResults.add(target);

        isBrothers = ComponentExt.isBrothers(source, target);

        if (isBrothers) {
          results.addAll(createComponentExchangeAndDelegations(new LinkedList(sourceResults), sourcePort,
              new LinkedList(targetResults), targetPort));
          isBrothers = true;
          targetResults.remove(target);
        } else {
          targets.addAll(ComponentExt.getBestPartAncestors(target));
          ComponentExt.filterNodes((Collection) targets);
        }
      }

      if (isBrothers) {
        sourceResults.remove(source);
      }

      if (!isBrothers) {
        sources.addAll(ComponentExt.getBestPartAncestors(source));
        ComponentExt.filterNodes((Collection) sources);
      }

    }

    return results;
  }

  /**
   * This method removes an interface implementation.
   * 
   * @param component
   *          the component who is implementing the interface
   * @param interf
   *          the implemented interface
   */
  @Override
  public void removeImplementedInterface(Component component, Interface interf) {
    InterfaceImplementation realizationLink = null;
    ListIterator<InterfaceImplementation> it = component.getImplementedInterfaceLinks().listIterator();
    while (it.hasNext()) {
      InterfaceImplementation lnk = it.next();
      if (lnk.getImplementedInterface().equals(interf)) {
        realizationLink = lnk;
      }
    }
    if (realizationLink != null) {
      realizationLink.destroy();
    }
  }

  /**
   * This method remove an provide interface.
   */
  @Override
  public void removeProvidedInterface(Component component, Interface interf) {
    ComponentPort stdPort = null;
    for (Partition partition : component.getOwnedPartitions()) {
      if ((partition instanceof ComponentPort)
          && ((ComponentPort) partition).getProvidedInterfaces().contains(interf)) {
        stdPort = (ComponentPort) partition;
        stdPort.getProvidedInterfaces().remove(interf);
        break;
      }
    }
  }

  /**
   * This method remove an require interface.
   */
  @Override
  public void removeRequiredInterface(Component component, Interface interf) {
    ComponentPort stdPort = null;
    for (Partition partition : component.getOwnedPartitions()) {
      if ((partition instanceof ComponentPort)
          && ((ComponentPort) partition).getRequiredInterfaces().contains(interf)) {
        stdPort = (ComponentPort) partition;
        stdPort.getRequiredInterfaces().remove(interf);
        break;
      }
    }
  }

  /**
   * This method removes an interface usage.
   * 
   * @param component
   *          the component who is using the interface
   * @param interf
   *          the used interface
   */
  @Override
  public void removeUsedInterface(Component component, Interface interf) {
    InterfaceUse useLink = null;
    ListIterator<InterfaceUse> it = component.getUsedInterfaceLinks().listIterator();
    while (it.hasNext()) {
      InterfaceUse lnk = it.next();
      if (lnk.getUsedInterface().equals(interf)) {
        useLink = lnk;
      }
    }
    if (useLink != null) {
      useLink.destroy();
    }
  }

  @Override
  public List<PartitionableElement> getAllPartitionableElementAncestors(PartitionableElement current) {
    List<PartitionableElement> result = new ArrayList<PartitionableElement>();
    List<Partition> representingPartitions = current.getRepresentingPartitions();
    List<PartitionableElement> ancestors = new ArrayList<PartitionableElement>();
    for (Partition partition : representingPartitions) {
      Component parent = getDirectParent(partition);
      if (parent != null) {
        ancestors.add(parent);
      }
    }
    result.addAll(ancestors);

    for (PartitionableElement partitionableElement : ancestors) {
      result.addAll(getAllPartitionableElementAncestors(partitionableElement));
    }

    return result;
  }

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
  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public boolean isComponentExchangeThroughDelegationsExists(Part sourcePart, Part targetPart, Port sourcePort,
      Port targetPort) {

    LinkedList<Part> sourceResults = new LinkedList<Part>();
    LinkedList<Part> targetResults = new LinkedList<Part>();

    boolean isBrothers = false;
    boolean canBeBrotherified = false;
    LinkedList<Part> sources = new LinkedList<Part>();
    sources.add(sourcePart);

    // for all parents which are brother-linked, check if there is a linked component exchange to given ports.
    while (sources.size() > 0) {
      Part source = sources.removeFirst();
      sourceResults.add(source);

      isBrothers = false;
      LinkedList<Part> targets = new LinkedList<Part>();
      targets.addFirst(targetPart);
      targetResults.clear();
      while ((targets.size() > 0)) {
        Part target = targets.removeFirst();
        targetResults.add(target);

        isBrothers = ComponentExt.isBrothers(source, target);

        if (isBrothers) {
          canBeBrotherified = true;
          if (areLinked(source, target, sourcePort, targetPort)) {
            return true;
          }
          isBrothers = true;
          targetResults.remove(target);
        }
        targets.addAll(ComponentExt.getBestPartAncestors(target));
        ComponentExt.filterNodes((Collection) targets);
      }

      if (isBrothers) {
        sourceResults.remove(source);
      }
      sources.addAll(ComponentExt.getBestPartAncestors(source));
      ComponentExt.filterNodes((Collection) sources);
    }
    return !canBeBrotherified;
  }

  /**
   * Returns whether given port have a component exchange between them between the given parts
   */
  private boolean areLinked(Part source, Part target, Port sourcePort, Port targetPort) {
    Component sourceComponent = (Component) source.getAbstractType();
    Component targetComponent = (Component) target.getAbstractType();

    List<ComponentPort> ports = new ArrayList<ComponentPort>();
    for (ComponentPort port : ComponentExt.getOwnedComponentPort(targetComponent)) {
      if ((port == targetPort) || PortExt.getAllDelegatedComponentPorts(port).contains(targetPort)) {
        ports.add(port);
      }
    }

    for (ComponentPort port : ComponentExt.getOwnedComponentPort(sourceComponent)) {
      if ((port == sourcePort) || PortExt.getAllDelegatedComponentPorts(port).contains(sourcePort)) {
        Collection<ComponentPort> targets = PortExt.getConnectedComponentPortsWithoutDelegation(port);
        targets.retainAll(ports);
        if (targets.size() > 0) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Return true if given interface is providing interface of given componentPort
   */
  @Override
  public boolean isProvidingInterface(ComponentPort componentPort, Interface interf) {
    if ((componentPort != null) && (null != interf)) {
      for (Interface inter : componentPort.getProvidedInterfaces()) {
        if ((null != inter) && inter.equals(interf)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Return true if given interface is requiring interface of given componentPort
   */
  @Override
  public boolean isRequiringInterface(ComponentPort componentPort, Interface interf) {
    if ((componentPort != null) && (null != interf)) {
      for (Interface inter : componentPort.getRequiredInterfaces()) {
        if ((null != inter) && inter.equals(interf)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * From a component, returns its entire hierarchy as displayed in a blank architecture, with sub-component, as well as
   * deployed them for the components of the physical level
   * 
   * @param component
   *          the root component
   * @return
   */
  @Override
  public List<Component> getAllSubUsedAndDeployedComponents(Component component) {
    List<Component> result = new ArrayList<Component>();
    result.add(component);

    // sub component :
    for (Component cpnt : getSubUsedComponents(component)) {
      result.addAll(getAllSubUsedAndDeployedComponents(cpnt));
    }

    // components deployed via parts
    if (component instanceof PhysicalComponent) {
      PhysicalComponent pc = (PhysicalComponent) component;
      for (PhysicalComponent deployedPC : pc.getDeployedPhysicalComponents()) {
        result.addAll(getAllSubUsedAndDeployedComponents(deployedPC));
      }
    }
    return result;
  }

  /**
   * Removes duplicate in an EList of interfaces.
   * 
   * @param list
   * @return
   */
  @Override
  public Collection<Interface> removeDuplicate(EList<Interface> list) {
    Collection<Interface> newList = new ArrayList<Interface>();
    for (Interface itf : list) {
      if (!newList.contains(itf)) {
        newList.add(itf);
      }
    }
    return newList;
  }

  @Override
  public IStatus isRequiredorUsedItfDelegated(IValidationContext ctx, LogicalComponent lcomp, Interface itf) {
    boolean found = false;

    if (!ComponentExt.isComposite(lcomp)) {
      return ctx.createSuccessStatus();
    }

    EList<Partition> ownedPartitions = lcomp.getOwnedPartitions();
    Iterator<Partition> iterator = ownedPartitions.iterator();
    while (iterator.hasNext()) {
      Partition next = iterator.next();
      if (next instanceof Part) {
        Part part = (Part) next;
        AbstractType abstractType = part.getAbstractType();
        if (abstractType instanceof LogicalComponent) {
          LogicalComponent lson = (LogicalComponent) abstractType;
          if (lson.getRequiredInterfaces().contains(itf) || lson.getUsedInterfaces().contains(itf)) {
            found = true;
          }
        }
      }
    }
    if (!found) {
      return ctx.createFailureStatus(new Object[] { lcomp.getName(), itf.getName() });
    }
    return ctx.createSuccessStatus();

  }

  @Override
  public IStatus isProvidedorImplementedItfDelegated(IValidationContext ctx, Component lcomp, Interface itf) {
    boolean found = false;

    if (!ComponentExt.isComposite(lcomp)) {
      return ctx.createSuccessStatus();
    }

    EList<Partition> ownedPartitions = lcomp.getOwnedPartitions();
    Iterator<Partition> iterator = ownedPartitions.iterator();
    while (iterator.hasNext()) {
      Partition next = iterator.next();
      if (next instanceof Part) {
        Part part = (Part) next;
        AbstractType abstractType = part.getAbstractType();
        if (abstractType instanceof Component) {
          Component lson = (Component) abstractType;
          if (lson.getProvidedInterfaces().contains(itf) || lson.getImplementedInterfaces().contains(itf)) {
            found = true;
          }
        }
      }
    }
    if (!found) {
      return ctx.createFailureStatus(new Object[] { lcomp.getName(), itf.getName() });
    }
    return ctx.createSuccessStatus();
  }

}
