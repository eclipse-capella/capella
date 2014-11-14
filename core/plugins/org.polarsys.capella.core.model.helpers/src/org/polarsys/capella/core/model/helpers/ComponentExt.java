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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
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
public class ComponentExt {

  /**
   * This method adds an interface implementation.
   * @param component_p the component who will implement the interface
   * @param interf_p the implemented interface
   */
  public static void addImplementedInterface(Component component_p, Interface interf_p) {
    if ((component_p != null) && (interf_p != null)) {
      if (!getImplementedInterfaces(component_p).contains(interf_p)) {
        InterfaceImplementation impl = CsFactory.eINSTANCE.createInterfaceImplementation();
        impl.setImplementedInterface(interf_p);
        component_p.getOwnedInterfaceImplementations().add(impl);
      }
    }
  }

  /**
   * This method adds an provide interface. If the StandardPort doesn't exist, it will be create.
   */
  public static void addProvidedInterface(Component component_p, Interface interf_p) {
    ComponentPort stdPort = null;
    if ((component_p != null) && (interf_p != null)) {
      for (Partition partition : component_p.getOwnedPartitions()) {
        if (partition instanceof ComponentPort) {
          stdPort = (ComponentPort) partition;
          break;
        }
      }
      if (null == stdPort) {
        stdPort = PortExt.createStandardPort();
        component_p.getOwnedFeatures().add(stdPort);
      }
      stdPort.getProvidedInterfaces().add(interf_p);
    }
  }

  /**
   * This method adds an required interface. If the StandardPort doesn't exist, it will be create.
   */
  public static void addRequiredInterface(Component component_p, Interface interf_p) {
    ComponentPort stdPort = null;
    if ((component_p != null) && (interf_p != null)) {
      for (Partition partition : component_p.getOwnedPartitions()) {
        if (partition instanceof ComponentPort) {
          stdPort = (ComponentPort) partition;
          break;
        }
      }
      if (null == stdPort) {
        stdPort = PortExt.createStandardPort();
        component_p.getOwnedFeatures().add(stdPort);
      }
      stdPort.getRequiredInterfaces().add(interf_p);
    }
  }

  /**
   * This method adds an interface usage.
   * @param component_p the component who will use the interface
   * @param interf_p the used interface
   */
  public static void addUsedInterface(Component component_p, Interface interf_p) {
    if ((component_p != null) && (interf_p != null)) {
      if (!getUsedInterfaces(component_p).contains(interf_p)) {
        InterfaceUse use = CsFactory.eINSTANCE.createInterfaceUse();
        use.setUsedInterface(interf_p);
        component_p.getOwnedInterfaceUses().add(use);
      }
    }
  }

  /**
   * This method adds a communication link.
   * @param component_p the component who will use the interface
   * @param communicationLink_p the communication link
   * @param isGenerated_p true if usage is generated, false otherwise
   */
  public static void addCommunicationLink(Component component_p, CommunicationLink communicationLink_p) {
    if ((component_p != null) && (communicationLink_p != null)) {
      if (!getExchangeItems(component_p).contains(communicationLink_p.getExchangeItem())) {
        CommunicationLink lnk = CommunicationFactory.eINSTANCE.createCommunicationLink();
        lnk.setKind(communicationLink_p.getKind());
        lnk.setProtocol(communicationLink_p.getProtocol());
        lnk.setExchangeItem(communicationLink_p.getExchangeItem());
        component_p.getOwnedCommunicationLinks().add(lnk);
      }
    }
  }

  /**
   * 
   */
  public static List<AbstractExchangeItem> getExchangeItems(Component component_p) {
    List<AbstractExchangeItem> result = new ArrayList<AbstractExchangeItem>();
    if (component_p != null) {
      for (CommunicationLink lnk : component_p.getOwnedCommunicationLinks()) {
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
   * @param component
   * @return componentFunctionExchangeItems
   */
  public static Set<AbstractExchangeItem> getAllocatedFunctionExchangeItems(Component component) {
    Set<AbstractExchangeItem> componentFunctionExchangeItems = new HashSet<AbstractExchangeItem>();
    Set<AbstractFunction> allocatedFunctions = (Set<AbstractFunction>) ComponentExt.getAllocatedFunctions(component);
    for (AbstractFunction abstractFunction : allocatedFunctions) {
      componentFunctionExchangeItems = (Set<AbstractExchangeItem>) AbstractFunctionExt.getAllExchangeItems(abstractFunction);
    }
    return componentFunctionExchangeItems;
  }

  /**
   * This method removes an exchange item realization.
   * @param component_p the component who is using the interface
   * @param communicationLink_p the communication link
   */
  public static void removeCommunicationLink(Component component_p, CommunicationLink communicationLink_p) {
    CommunicationLink link = null;
    if (null != communicationLink_p) {
      ListIterator<CommunicationLink> it = component_p.getOwnedCommunicationLinks().listIterator();
      while (it.hasNext()) {
        CommunicationLink lnk = it.next();
        if (lnk.getExchangeItem().equals(communicationLink_p.getExchangeItem()) && lnk.getProtocol().equals(communicationLink_p.getProtocol())
            && lnk.getKind().equals(communicationLink_p.getKind())) {
          link = lnk;
        }
      }
    }
    if (link != null) {
      link.destroy();
    }
  }

  /**
   * @param message_p
   * @param selectedOperation_p
   * @param portStrategie_p
   * @param targetOnExchangeItem_p
   */
  public static void ensureUseAndImplementsForOperation(SequenceMessage message_p, ExchangeItemAllocation selectedOperation_p, boolean portStrategie_p,
      EObject targetOnExchangeItem_p) {
    AbstractInstance roleSource = message_p.getSendingEnd() == null ? null : message_p.getSendingEnd().getCovered().getRepresentedInstance();
    AbstractInstance roleTarget = message_p.getReceivingEnd() == null ? null : message_p.getReceivingEnd().getCovered().getRepresentedInstance();

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
    // inversion of use/implements. On departure of the message, it is the component which implements the interface allocating the shared data and not its use.
    if ((src == null) || (tgt == null)) {
      // To be a READ a message must have the following features:
      // - Its target must be an ExchangeItemInstance (on an Execution or directly on the InstanceRole),
      // - And it must be a SYNCHRONOUS CALL.
      if ((roleTarget instanceof ExchangeItemInstance) && ((targetOnExchangeItem_p instanceof Execution) || (targetOnExchangeItem_p instanceof InstanceRole))
          && (MessageKind.SYNCHRONOUS_CALL == message_p.getKind())) {
        Component temp = src;
        src = tgt;
        tgt = temp;
      }
    }

    Interface interf = selectedOperation_p.getAllocatingInterface();

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
      if (portStrategie_p) {
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
      if (portStrategie_p) {
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

  public static List<Component> getAllAncestors(Component component_p) {
    List<Component> result = new ArrayList<Component>();
    GeneralizableElement container = component_p;
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
   * @param cpnt_p
   * @return
   */
  static public List<Part> getAllComponentInstances(Component cpnt_p) {
    List<Part> instList = new ArrayList<Part>();

    for (EObject obj : EObjectExt.getAll(cpnt_p, CsPackage.Literals.PART)) {
      instList.add((Part) obj);
    }

    return instList;
  }

  /**
   * This method retrieves all the implemented interfaces.
   * @param cpnt_p the component whose implemented AND provided (by StandardPorts) interfaces will be retrieved (and all its inherited components)
   * @return List<Interface> the implemented interfaces
   */
  public static List<Interface> getAllImplementedAndProvidedInterfaces(Component cpnt_p) {
    List<Interface> implementedAndProvidedItf = new ArrayList<Interface>();

    if (cpnt_p != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt_p);
      superComponents.add(cpnt_p);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          Component cpnt = (Component) element;
          for (InterfaceImplementation impl : cpnt.getImplementedInterfaceLinks()) {
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
          for (Interface abstractItf : cpnt.getProvidedInterfaces()) {
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
   * @param cpnt_p the component whose implemented interfaces will be retrieved (and all its inherited components)
   * @return List<Interface> the implemented interfaces
   */
  public static List<Interface> getAllImplementedInterfaces(Component cpnt_p) {
    List<Interface> implementedInterfaces = new ArrayList<Interface>();

    if (cpnt_p != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt_p);
      superComponents.add(cpnt_p);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          Component cpnt = (Component) element;
          for (InterfaceImplementation impl : cpnt.getImplementedInterfaceLinks()) {
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
   * obtains all relationships ending to cpnt_p
   * @param cpnt_p
   * @return
   */
  static public List<Relationship> getAllIncomingRelationships(Component cpnt_p) {
    List<Relationship> ret = new ArrayList<Relationship>();

    return ret;

  }

  /**
   * obtains all relationships starting from cpnt_p
   * @param cpnt_p
   * @return
   */
  static public List<Relationship> getAllOutgoingRelationships(Component cpnt_p) {
    List<Relationship> ret = new ArrayList<Relationship>();

    ret.addAll(cpnt_p.getUsedInterfaceLinks());
    ret.addAll(cpnt_p.getImplementedInterfaceLinks());

    return ret;
  }

  /**
   * This method retrieves all the provided interfaces recursively
   * @param component_p
   * @return
   */
  public static List<Interface> getAllProvidedInterfaces(Component cpnt_p) {
    List<Interface> requiredItf = new ArrayList<Interface>();

    if (cpnt_p != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt_p);
      superComponents.add(cpnt_p);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          Component cpnt = (Component) element;
          for (Interface abstractItf : cpnt.getProvidedInterfaces()) {
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
  public static Collection<ComponentExchange> getAllRelatedComponentExchange(Part part_p) {
    return getAllRelatedComponentExchange(part_p, false);
  }

  /**
   * Return all the connection related to given Component filter by Connection kind According to given includePortOfType_p, returns also CE from ports of the
   * type
   */
  public static Collection<ComponentExchange> getAllRelatedComponentExchange(Part part_p, boolean includePortOfType_p) {

    List<ComponentExchange> result = new ArrayList<ComponentExchange>();

    if ((part_p.getAbstractType() != null) && (part_p.getAbstractType() instanceof Component)) {

      EList<AbstractInformationFlow> informationFlows = part_p.getInformationFlows();
      for (AbstractInformationFlow abstractInformationFlow : informationFlows) {
        if (abstractInformationFlow instanceof ComponentExchange) {
          result.add((ComponentExchange) abstractInformationFlow);
        }
      }
      for (ComponentExchangeEnd end : FunctionalExt.getRelatedComponentExchangeEnds(part_p)) {
        EObject owner = end.eContainer();
        if (owner instanceof ComponentExchange) {
          result.add((ComponentExchange) owner);
        }
      }

      if (includePortOfType_p) {
        List<ComponentPort> ownedComponentPort = getOwnedComponentPort((Component) part_p.getAbstractType());
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
   * @param component_p
   * @return
   */
  public static Collection<ComponentExchange> getAllRelatedComponentExchange(Component component_p) {
    return getAllRelatedComponentExchange(component_p, true);
  }

  /**
   * Return all the connection related to given Component filter by Connection kind
   * @param component_p
   * @return
   */
  public static Collection<ComponentExchange> getAllRelatedComponentExchange(Component component_p, boolean addRelatedOfParts) {
    List<ComponentExchange> result = new ArrayList<ComponentExchange>();

    List<ComponentPort> ownedComponentPort = getOwnedComponentPort(component_p);
    // retrieve connections from owned component Ports
    for (ComponentPort componentPort : ownedComponentPort) {
      result.addAll(componentPort.getComponentExchanges());
    }

    // returns also communication means
    if (component_p instanceof Entity) {
      for (AbstractInformationFlow flow : ((Entity) component_p).getInformationFlows()) {
        if (flow instanceof CommunicationMean) {
          result.add((CommunicationMean) flow);
        }
      }
    }

    if (addRelatedOfParts) {
      // retrieve connection from typed elements (today only Part are valid)
      EList<AbstractTypedElement> abstractTypedElements = component_p.getAbstractTypedElements();
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
   * @param component_p
   * @return
   */
  public static Collection<ComponentExchange> getAllRelatedConnectionByKind(Component component_p, ComponentExchangeKind kind_p) {
    List<ComponentExchange> result = new ArrayList<ComponentExchange>();

    if (null == component_p) {
      return null;
    }

    // filter connection by kind
    Collection<ComponentExchange> allRelatedConnection = getAllRelatedComponentExchange(component_p);
    result.addAll(allRelatedConnection);
    if (null != kind_p) {
      for (ComponentExchange connection : allRelatedConnection) {
        if (connection.getKind() != kind_p) {
          result.remove(connection);
        }
      }
    }

    return result;

  }

  /**
   * This method retrieves all the required interfaces recursively
   * @param component_p
   * @return
   */
  public static List<Interface> getAllRequiredInterfaces(Component cpnt_p) {
    List<Interface> providedItf = new ArrayList<Interface>();

    if (cpnt_p != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt_p);
      superComponents.add(cpnt_p);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          Component cpnt = (Component) element;
          for (Interface abstractItf : cpnt.getRequiredInterfaces()) {
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
   * @param ele_p
   * @param comp_p
   * @return
   */
  public static List<CapellaElement> getAllStatesAndModesFromComponent(Component comp_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(1);

    // collect all the modes form Component -> StateMachin
    if (comp_p != null) {
      EList<StateMachine> ownedStateMachines = comp_p.getOwnedStateMachines();
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

  public static Collection<Part> getAllSubUsedParts(Component component_p, boolean useDeploymentLinks_p) {
    Collection<Part> result = new HashSet<Part>();

    for (Partition partition : component_p.getRepresentingPartitions()) {
      if (partition instanceof Part) {
        result.add((Part) partition);
      }
    }

    return getAllSubUsedParts(result, useDeploymentLinks_p);
  }

  public static Collection<Part> getAllSubUsedParts(Part part_p, boolean useDeploymentLinks_p) {
    return getAllSubUsedParts(Collections.singletonList(part_p), useDeploymentLinks_p);
  }

  private static Collection<Part> getAllSubUsedParts(Collection<Part> parts, boolean useDeploymentLinks_p) {
    Collection<Part> comps = new HashSet<Part>();
    Collection<Part> visited = new ArrayList<Part>();

    LinkedList<Part> subs = new LinkedList<Part>();
    List<Part> initial = new LinkedList<Part>();
    List<Part> internal = new ArrayList<Part>();

    initial.addAll(parts);
    subs.addAll(parts);

    while (subs.size() > 0) {
      Part sub = subs.removeFirst();
      if (!visited.contains(sub)) {
        visited.add(sub);

        if ((sub.getAbstractType() != null) && (sub.getAbstractType() instanceof Component)) {
          internal = ComponentExt.getSubParts((Component) sub.getAbstractType(), false);
          comps.addAll(internal);
          subs.addAll(internal);
        }

        if (useDeploymentLinks_p) {
          internal = PartExt.getDeployedParts(sub);
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
  public static Collection<Component> getAllSubUsedComponents(Component component) {
    // Collection<Component> rsult = PartExt.getComponentsOfParts(getAllSubUsedParts(component, false));

    Collection<Component> comps = new HashSet<Component>();
    Collection<Component> visited = new ArrayList<Component>();

    LinkedList<Component> subs = new LinkedList<Component>();
    List<Component> internal = new ArrayList<Component>();

    subs.add(component);
    while (subs.size() > 0) {
      Component sub = subs.removeFirst();
      if (!visited.contains(sub)) {
        visited.add(sub);
        internal = ComponentExt.getSubUsedComponents(sub);
        comps.addAll(internal);
        subs.addAll(internal);
      }
    }

    return comps;
  }

  /**
   * This method retrieves all the used interfaces.
   * @param cpnt_p the component whose used AND required (by StandardPorts) interfaces will be retrieved (and all its inherited components)
   * @return List<Interface> the used and required interfaces
   */
  public static List<Interface> getAllUsedAndRequiredInterfaces(Component cpnt_p) {
    List<Interface> usedAndRequiredItf = new ArrayList<Interface>();

    if (cpnt_p != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt_p);
      superComponents.add(cpnt_p);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          Component cpnt = (Component) element;
          for (InterfaceUse use : cpnt.getUsedInterfaceLinks()) {
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
          for (Interface abstractItf : cpnt.getRequiredInterfaces()) {
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
   * @param cpnt_p the component whose used interfaces will be retrieved (and all its inherited components)
   * @return List<Interface> the used interfaces
   */
  public static List<Interface> getAllUsedInterfaces(Component cpnt_p) {
    List<Interface> usedInterfaces = new ArrayList<Interface>();

    if (cpnt_p != null) {
      List<GeneralizableElement> superComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(cpnt_p);
      superComponents.add(cpnt_p);

      for (GeneralizableElement element : superComponents) {
        if (element instanceof Component) {
          Component cpnt = (Component) element;
          for (InterfaceUse use : cpnt.getUsedInterfaceLinks()) {
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
  static public Collection<Component> getAvailableComponentsByNamespace(EObject component) {
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
  static public Collection<EObject> getAvailableComponentsByNamespaceOfParts(LinkedList<EObject> ownerParts) {
    Collection<EObject> components = new java.util.HashSet<EObject>();

    // Access from all hierarchy of components and blockarchitectures, all sub components and actors
    while (ownerParts.size() > 0) {
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
  static public Collection<EObject> getAvailableComponentsByNamespaceOfParts(Part part) {
    LinkedList<EObject> ownerParts = new LinkedList<EObject>();
    ownerParts.add(part);
    return getAvailableComponentsByNamespaceOfParts(ownerParts);
  }

  public static Component getCommonComponentAncestor(Component c1, Component c2) {
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
   * @param component_p the parent component
   * @return list of components
   */
  static public List<Component> getComponentsFromComponent(Component component_p) {
    List<Component> list = new ArrayList<Component>();
    for (Object obj : component_p.eContents()) {
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
  public static List<Part> getDecompositionPartInvolved(Component lc_p) {
    List<Part> decompPartInvolved = new ArrayList<Part>();
    for (Partition partition : lc_p.getOwnedPartitions()) {
      AbstractType currentLc = partition.getAbstractType();
      if ((currentLc instanceof Component) && !decompPartInvolved.contains(partition)) {
        decompPartInvolved.add((Part) partition);
      }
    }
    return decompPartInvolved;
  }

  /**
   * @return the list of Component direct common parent for componentA_p and componentB_p.
   * @return null if not have a direct common parent.
   */
  public static List<Component> getDirectCommonParent(Component componentA_p, Component componentB_p) {
    List<Component> lstDirCommonParents = new ArrayList<Component>();
    List<Component> parentsComponents_A = ComponentExt.getDirectParents(componentA_p);
    for (Component parentB : ComponentExt.getDirectParents(componentB_p)) {
      if (parentsComponents_A.contains(parentB)) {
        lstDirCommonParents.add(parentB);
      }
    }
    return lstDirCommonParents;
  }

  /**
   * @return component owning the the part given in parameter
   */
  public static Component getDirectParent(Partition part_p) {
    return (Component) EcoreUtil2.getFirstContainer(part_p, CsPackage.Literals.COMPONENT);
  }

  /**
   * @return component owning the component parts given in parameter
   */
  public static List<Component> getDirectParents(Component component_p) {
    List<Component> parentsComponents = new ArrayList<Component>();

    for (Partition partition : component_p.getRepresentingPartitions()) {
      Component partitionanableElt = (Component) EcoreUtil2.getFirstContainer(partition, CsPackage.Literals.COMPONENT);
      parentsComponents.add(partitionanableElt);
    }
    return parentsComponents;
  }

  /**
   * Retrieve the first common component ancestor for two elements with eClass = Port part or component return the common component ancestor between source_p
   * and target_p if source_p is target_p, return the c1 unlike getCommonComponentAncestor
   */
  public static EObject getFirstCommonComponentAncestor(EObject source_p, EObject target_p, boolean useDeployementLinks_p) {
    if ((source_p == null) || (target_p == null)) {
      return null;
    }

    EObject source = source_p;
    EObject target = target_p;
    EObject result = null;

    LinkedList<EObject> sourceList = new LinkedList<EObject>();
    Collection<EObject> sourceListVisited = new HashSet<EObject>();

    ArrayList<EObject> targetList = new ArrayList<EObject>();
    Collection<EObject> targetListVisited = new HashSet<EObject>();

    // Retrieve ordonned list of containers for the target
    int i = 0;
    targetList.add(target);
    while (i < targetList.size()) {
      EObject targetA = targetList.get(i);
      targetListVisited.add(targetA);
      i++;

      for (EObject related : getRelatedElementsForCommonAncestor(targetA, useDeployementLinks_p)) {
        if ((related != null) && !targetListVisited.contains(related)) {
          targetList.add(related);
        }
      }
    }

    // Find for containers of source, if one is equals to one targetContainers
    sourceList.add(source);
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

      for (EObject related : getRelatedElementsForCommonAncestor(sourceContainer, useDeployementLinks_p)) {
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

  public static EObject getFirstCommonComponentAncestor(EObject source_p, EObject target_p) {
    return getFirstCommonComponentAncestor(source_p, target_p, false);
  }

  /**
   * This method retrieves the implemented AND provided (by Ports) interfaces
   * @param component_p the component whose used and provided interfaces will be retrieved
   * @return List<Interface> the used interfaces
   */
  public static List<Interface> getImplementedAndProvidedInterfaces(Component component_p) {
    List<Interface> implementedAndProvidedItf;

    implementedAndProvidedItf = getImplementedInterfaces(component_p);
    for (Interface itfProvided : component_p.getProvidedInterfaces()) {
      if (!implementedAndProvidedItf.contains(itfProvided)) {
        implementedAndProvidedItf.add(itfProvided);
      }
    }
    return implementedAndProvidedItf;
  }

  /**
   * This method retrieves the implemented interfaces.
   * @param component_p the component whose implemented interfaces will be retrieved
   * @return List<Interface> the implemented interfaces
   */
  public static List<Interface> getImplementedInterfaces(Component component_p) {
    List<Interface> implementedInterfaces = new ArrayList<Interface>();

    if (component_p != null) {
      List<InterfaceImplementation> implementationSet = component_p.getImplementedInterfaceLinks();
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
   * Gets all the interfaces implemented by the component <code>currentComp_p</code>, except those already implemented by <code>component_p</code>.
   * @param currentComp_p the component from which all the implemented interfaces have to be listed
   * @param component_p the component to be checked
   * @return list of all implemented interfaces
   */
  static public List<Interface> getImplementedInterfacesFiltered(Component currentComp_p, Component component_p) {
    List<Interface> interfaceList = new ArrayList<Interface>();
    boolean isCheckRequired = !component_p.equals(currentComp_p);

    for (InterfaceImplementation interfaceImpl : currentComp_p.getImplementedInterfaceLinks()) {
      Interface inter = interfaceImpl.getImplementedInterface();
      if ((inter != null)) {
        if (isCheckRequired) {
          if (isImplementingInterface(component_p, inter)) {
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
  public static DataPkg getDataPkg(Component component_p, boolean create_p) {
    if ((component_p.getOwnedInterfacePkg() == null) && create_p) {
      // to externalize when constants in skeleton will be into helpers.
      DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name);
      component_p.setOwnedDataPkg(pkg);
    }
    return component_p.getOwnedDataPkg();
  }

  /**
   * Retrieve the interface pkg from the given component (create an interface pkg if null)
   */
  public static DataPkg getDataPkg(Component component_p) {
    return getDataPkg(component_p, true);
  }

  /**
   * Retrieve the interface pkg from the given component (create an interface pkg if null)
   */
  public static InterfacePkg getInterfacePkg(Component component_p, boolean create_p) {
    if ((component_p.getOwnedInterfacePkg() == null) && create_p) {
      // to externalize when constants in skeleton will be into helpers.
      InterfacePkg pkg = CsFactory.eINSTANCE.createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name);
      component_p.setOwnedInterfacePkg(pkg);
    }
    return component_p.getOwnedInterfacePkg();
  }

  /**
   * Retrieve the interface pkg from the given component (create an interface pkg if null)
   */
  public static InterfacePkg getInterfacePkg(Component component_p) {
    return getInterfacePkg(component_p, true);
  }

  /**
   * This method gets all the interfaces in Parent's hierarchy of current LC
   * @param component_p the Logical Component
   * @return List of interfaces
   */
  public static List<Interface> getInterfacesFromComponentParentHierarchy(Component component_p) {
    List<Interface> list = new ArrayList<Interface>();
    // Add interfaces from parent Logical Architecture
    BlockArchitecture arch = ComponentExt.getRootBlockArchitecture(component_p);
    if (null != arch) {
      list.addAll(InterfacePkgExt.getAllInterfaces(arch.getOwnedInterfacePkg()));
      // get recursively all the interfaces from the parent hierarchy
      list.addAll(InterfacePkgExt.getOwnedInterfacesFromBlockArchitectureParent(arch));
    }
    for (Component ancestor : ComponentExt.getComponentAncestors(component_p)) {
      list.addAll(InterfacePkgExt.getAllInterfaces(ancestor.getOwnedInterfacePkg()));
    }
    return list;
  }

  /**
   * Gets the owned ports.
   * @param component_p the given component
   * @return the owned port
   */
  public static List<ComponentPort> getOwnedComponentPort(Component component_p) {
    List<ComponentPort> returnedList = new ArrayList<ComponentPort>();
    for (Feature aFeature : component_p.getOwnedFeatures()) {
      if (aFeature instanceof ComponentPort) {
        returnedList.add((ComponentPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Gets the owned flow ports.
   * @param component_p the given component
   * @return the owned flow port
   */
  public static List<ComponentPort> getOwnedFlowPort(Component component_p) {
    List<ComponentPort> returnedList = new ArrayList<ComponentPort>();
    for (Feature aFeature : component_p.getOwnedFeatures()) {
      if (PortExt.isFlowPort(aFeature)) {
        returnedList.add((ComponentPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Gets the owned in flow ports.
   * @param component_p the given component
   * @return the owned in flow port
   */
  public static List<ComponentPort> getOwnedInFlowPort(Component component_p) {
    List<ComponentPort> returnedList = new ArrayList<ComponentPort>();
    for (Feature aFeature : component_p.getOwnedFeatures()) {
      if (PortExt.isIn((ComponentPort) aFeature) && PortExt.isFlowPort(aFeature)) {
        returnedList.add((ComponentPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Gets the owned out flow ports.
   * @param component_p the given component
   * @return the owned out flow port
   */
  public static List<ComponentPort> getOwnedOutFlowPort(Component component_p) {
    List<ComponentPort> returnedList = new ArrayList<ComponentPort>();
    for (Feature aFeature : component_p.getOwnedFeatures()) {
      if ((aFeature instanceof ComponentPort) && PortExt.isOut((ComponentPort) aFeature) && PortExt.isFlowPort(aFeature)) {
        returnedList.add((ComponentPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Gets the owned physical ports.
   * @param component_p the given component
   * @return the owned physical port
   */
  public static List<PhysicalPort> getOwnedPhysicalPort(Component component_p) {
    List<PhysicalPort> returnedList = new ArrayList<PhysicalPort>();
    for (Feature aFeature : component_p.getOwnedFeatures()) {
      if (aFeature instanceof PhysicalPort) {
        returnedList.add((PhysicalPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Check if the compnent has physical ports.
   * @param component_p the given component
   */
  public static boolean hasPhysicalPort(Component component_p) {
    return !ComponentExt.getOwnedPhysicalPort(component_p).isEmpty();
  }

  /**
   * Gets the owned ports.
   * @param component_p the given component
   * @return the owned port
   */
  public static List<Port> getOwnedPort(Component component_p) {
    List<Port> returnedList = new ArrayList<Port>();
    for (Feature aFeature : component_p.getOwnedFeatures()) {
      if (aFeature instanceof Port) {
        returnedList.add((Port) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * @param modelElement_p
   * @return
   */
  public static Collection<ComponentExchange> getAllOwnedComponentExchanges(Component modelElement_p) {
    List<ComponentExchange> instList = new ArrayList<ComponentExchange>();
    for (EObject obj : EObjectExt.getAll(modelElement_p, FaPackage.Literals.COMPONENT_EXCHANGE)) {
      instList.add((ComponentExchange) obj);
    }
    return instList;
  }

  /**
   * Gets the owned standard ports.
   * @param component_p the given component
   * @return the owned standard port
   */
  public static List<ComponentPort> getOwnedStandardPort(Component component_p) {
    List<ComponentPort> returnedList = new ArrayList<ComponentPort>();
    for (Feature aFeature : component_p.getOwnedFeatures()) {
      if (PortExt.isStandardPort(aFeature)) {
        returnedList.add((ComponentPort) aFeature);
      }
    }
    return returnedList;
  }

  /**
   * Get the Parent Component respecting the breakdown structure logic
   * @param component_p : component whose parent we are looking for
   * @return parent component doesn't work for multipart
   */
  @Deprecated
  public static Component getParent(Component component_p) {
    Component parent = null;
    for (Partition aPartition : component_p.getRepresentingPartitions()) {
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
  public static Collection<Component> getComponentAncestors(Component component_p) {
    Collection<Component> result = new HashSet<Component>();
    for (Partition partition : component_p.getRepresentingPartitions()) {
      if (partition instanceof Part) {
        for (Part componentAncestor : ComponentExt.getPartAncestors((Part) partition)) {
          if ((componentAncestor.getAbstractType() != null) && (componentAncestor.getAbstractType() instanceof Component)) {
            result.add((Component) componentAncestor.getAbstractType());
          }
        }
      }
    }
    return result;
  }

  public static Collection<Part> getPartAncestors(Part child) {
    return getPartAncestors(child, false);
  }

  /**
   * Returns recursively all parts which contains the given part. All representing partition of containers and all parts deploying the given part
   */
  public static Collection<Part> getPartAncestors(Part child, boolean addGeneralizationOfParents) {
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
              List<GeneralizableElement> elements = GeneralizableElementExt.getAllSuperGeneralizableElements((GeneralizableElement) parent);
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

  private static List<EObject> getRelatedElementsForCommonAncestor(EObject eobject_p, boolean useDeployementLinks_p) {

    List<EObject> result = new LinkedList<EObject>();

    if (eobject_p instanceof Component) {
      for (Partition part : ((Component) eobject_p).getRepresentingPartitions()) {
        result.add(part);
      }

    } else if (eobject_p instanceof Port) {
      result.add(EcoreUtil2.getFirstContainer(eobject_p, CsPackage.Literals.COMPONENT));

    } else if (eobject_p instanceof Part) {
      List<DeploymentTarget> elements = PartExt.getDeployingElements((Part) eobject_p);
      if (useDeployementLinks_p && (elements.size() > 0)) {
        for (EObject e : elements) {
          result.add(e);
        }
      } else {
        result.add(EcoreUtil2.getFirstContainer(eobject_p, CsPackage.Literals.COMPONENT));
      }

    }

    return result;

  }

  /**
   * Returns functional exchanges which are related to allocated functions of the given component
   */
  public static Collection<FunctionalExchange> getRelatedFunctionalExchanges(Component component_p) {
    Collection<FunctionalExchange> functionalExchanges = new HashSet<FunctionalExchange>();

    for (Component component : getAllSubUsedComponents(component_p)) {
      for (AbstractFunction af : component.getAllocatedFunctions()) {
        functionalExchanges.addAll(FunctionExt.getIncomingExchange(af));
        functionalExchanges.addAll(FunctionExt.getOutGoingExchange(af));

      }
    }
    return functionalExchanges;
  }

  /**
   * Returns all allocated functions of the given component
   * @param component_p
   */
  public static Collection<AbstractFunction> getAllocatedFunctions(Component component_p) {
    Set<AbstractFunction> functionalExchanges = new HashSet<AbstractFunction>();
    EList<AbstractFunction> allocatedFunctions = component_p.getAllocatedFunctions();
    for (AbstractFunction abstractFunction2 : allocatedFunctions) {
      AbstractFunction abstractFunction = abstractFunction2;
      functionalExchanges.add(abstractFunction);

    }
    return functionalExchanges;

  }

  /**
   * Returns related interfaces of components (implements, uses, provides, requires).
   */
  public static Collection<Interface> getRelatedInterfaces(Component component) {
    Collection<Interface> interfaces = new ArrayList<Interface>();

    if (component != null) {
      interfaces.addAll(getImplementedAndProvidedInterfaces(component));
      interfaces.addAll(getUsedAndRequiredInterfaces(component));
    }

    return interfaces;
  }

  /**
   * Return the related interfaces of component from References(use, implement, required and provided links)
   * @param component_p
   * @return
   */
  @SuppressWarnings("unchecked")
  public static List<Interface> getRelatedInterfacesFromReference(Component cpnt_p, EReference reference_p) {
    List<Interface> providedItf = new ArrayList<Interface>();
    if (cpnt_p != null) {
      EList<Interface> list = (EList<Interface>) cpnt_p.eGet(reference_p);
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
   * @param component_p
   * @return
   */
  public static List<Interface> getRequiredInterfaces(Component cpnt_p) {
    List<Interface> requiredItf = new ArrayList<Interface>();
    if (cpnt_p != null) {
      for (Interface itf : cpnt_p.getRequiredInterfaces()) {
        if (!requiredItf.contains(itf)) {
          requiredItf.add(itf);
        }
      }
    }
    return requiredItf;
  }

  /**
   * @param modelElement_p : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  public static BlockArchitecture getRootBlockArchitecture(ModelElement modelElement_p) {
    return BlockArchitectureExt.getRootBlockArchitecture(modelElement_p);
  }

  static public Component getRootComponent(Component component_p) {
    Component component = null;
    if (null != component_p) {
      Object container = component_p.eContainer();
      if (container instanceof Component) {
        component = (Component) container;
      } else if (container instanceof Structure) {
        component = StructureExt.getRootComponent((Structure) container);
      }
    }
    return component;
  }

  /**
   * Gets the root component architecture from the current component
   * @param component_p the current component
   * @return the root component architecture
   */
  static public ComponentArchitecture getRootComponentArchitecture(Component component_p) {
    ComponentArchitecture compArch = null;
    if (null != component_p) {
      Object container = component_p.eContainer();

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
  static public List<? extends Component> getSubDefinedActors(BlockArchitecture architecture) {
    List<Component> elements = new ArrayList<Component>();

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
  static public List<Component> getSubDefinedComponents(Component object) {
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
  static private void getSubDefinedComponents(ConfigurationItemPkg compoPkg, Collection<Component> components) {
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
  static private void getSubDefinedComponents(LogicalComponentPkg compoPkg, Collection<Component> components) {
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
  static public List<Component> getSubDefinedComponents(BlockArchitecture object) {
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
  static private void getSubDefinedComponents(EntityPkg entityPkg, Collection<Component> components) {
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
  static private void getSubDefinedComponents(PhysicalComponentPkg compoPkg, Collection<Component> components) {
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
  public static List<Component> getSubUsedComponents(Component component) {
    return PartExt.getComponentsOfParts(getSubParts(component, false));
  }

  public static List<Component> getSubUsedComponents(Component component, boolean useDeploymentLinks_p) {
    return PartExt.getComponentsOfParts(getSubParts(component, useDeploymentLinks_p));
  }

  /**
   * Returns sub part used by the component (have a part)
   */
  public static List<Part> getSubParts(PartitionableElement component_p) {
    List<Part> result = new ArrayList<Part>();
    for (Partition partition : component_p.getOwnedPartitions()) {
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
  public static List<Part> getSubParts(Component component_p, boolean useDeploymentLinks_p) {
    List<Part> result = new ArrayList<Part>();

    if (useDeploymentLinks_p) {
      for (Partition partition : component_p.getRepresentingPartitions()) {
        if (partition instanceof Part) {
          result.addAll(PartExt.getDeployedParts((Part) partition));
        }
      }
    }
    result.addAll(getSubParts(component_p));
    return result;
  }

  /**
   * This method retrieves the inherited components.
   * @param cpnt_p The component source.
   * @return The list of inherited components.
   */
  public static List<Component> getSuperComponents(Component cpnt_p) {
    List<Component> superComponents = new ArrayList<Component>();

    for (GeneralizableElement elt : cpnt_p.getSuper()) {
      if (elt instanceof Component) {
        superComponents.add((Component) elt);
      }
    }

    return superComponents;
  }

  /**
   * Returns representing parts of given component.
   * @param component_p
   * @return
   */
  public static Collection<Part> getRepresentingParts(Component component_p) {
    ArrayList<Part> result = new ArrayList<Part>();
    if (component_p != null) {
      for (Partition partition : component_p.getRepresentingPartitions()) {
        if (partition instanceof Part) {
          result.add((Part) partition);
        }
      }
    }
    return result;
  }

  /**
   * This method retrieves the used interfaces AND required (by Ports)
   * @param component_p the component whose used and required interfaces will be retrieved
   * @return List<Interface> the used interfaces
   */
  public static List<Interface> getUsedAndRequiredInterfaces(Component component_p) {
    List<Interface> usedAndRequiredItf;
    usedAndRequiredItf = getUsedInterfaces(component_p);
    for (Interface itfRequired : component_p.getRequiredInterfaces()) {
      if (!usedAndRequiredItf.contains(itfRequired)) {
        usedAndRequiredItf.add(itfRequired);
      }
    }
    return usedAndRequiredItf;
  }

  /**
   * This method retrieves the used interfaces.
   * @param component_p the component whose used interfaces will be retrieved
   * @return List<Interface> the used interfaces
   */
  public static List<Interface> getUsedInterfaces(Component component_p) {
    List<Interface> usedInterfaces = new ArrayList<Interface>();

    if (component_p != null) {
      List<InterfaceUse> useSet = component_p.getUsedInterfaceLinks();
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
   * Gets all the interfaces used by the component <code>currentComp_p</code>, except those already used by <code>component_p</code>.
   * @param currentComp_p the component from which all the used interfaces have to be listed
   * @param component_p the component to be checked
   * @return list of all used interfaces
   */
  static public List<Interface> getUsedInterfacesFiltered(Component currentComp_p, Component component_p) {
    List<Interface> interfaceList = new ArrayList<Interface>();
    boolean isCheckRequired = !component_p.equals(currentComp_p);

    for (InterfaceUse interfaceUse : currentComp_p.getUsedInterfaceLinks()) {
      Interface inter = interfaceUse.getUsedInterface();
      if (inter != null) {
        if (isCheckRequired) {
          if (isUsingInterface(component_p, inter)) {
            continue;
          }
        }
        interfaceList.add(inter);
      }
    }
    return interfaceList;
  }

  /**
   * @return True if componentA_p and componentB_p have a direct common parent
   */
  public static boolean haveDirectCommonParent(Component componentA_p, Component componentB_p) {
    List<Component> parentsComponents_A = ComponentExt.getDirectParents(componentA_p);
    for (Component parentB : ComponentExt.getDirectParents(componentB_p)) {
      if (parentsComponents_A.contains(parentB)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether component used or require the exchange item by related interfaces
   */
  public static boolean isAnImplementedOrProvidedExchangeItem(Component component_p, AbstractExchangeItem exchangeItem_p) {
    // Enlarged search to StandardPorts
    List<Interface> itfLst = ComponentExt.getAllImplementedAndProvidedInterfaces(component_p);

    if (itfLst != null) {
      for (Interface itf : itfLst) {
        if (itf.getExchangeItems().contains(exchangeItem_p)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns whether component used or require the exchange item by related interfaces
   */
  public static boolean isAnUsedOrRequiredExchangeItem(Component component_p, AbstractExchangeItem exchangeItem_p) {
    // Enlarged search to StandardPorts
    List<Interface> itfLst = ComponentExt.getAllUsedAndRequiredInterfaces(component_p);

    if (itfLst != null) {
      for (Interface itf : itfLst) {
        if (itf.getExchangeItems().contains(exchangeItem_p)) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean isComponentAncestor(Component ancestor, Component child) {
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
  public static boolean isComponentRoot(EObject element_p) {
    if (!(element_p instanceof Component)) {
      return false;
    }

    // Last Component hierarchy detection
    return (!EcoreUtil2.isContainedBy(element_p, CsPackage.Literals.COMPONENT));
  }

  /**
   * Retrieve part ancestors, deploying elements and owner
   * @param currentPart_p
   * @param parents_p
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static Collection<Part> getBestPartAncestors(Part currentPart_p) {
    LinkedList<Part> parents_p = new LinkedList<Part>();
    Collection e = PartExt.getDeployingElements(currentPart_p);
    parents_p.addAll(e);
    if (e.size() == 0) {
      EObject parent = ComponentExt.getDirectParent(currentPart_p);
      if ((parent != null) && (parent instanceof Component)) {
        parents_p.addAll((Collection) ((Component) parent).getRepresentingPartitions());
      }
    }
    return parents_p;
  }

  public static Collection<EObject> filterNodes(Collection<EObject> elements_p) {
    Iterator<EObject> itElement = elements_p.iterator();
    while (itElement.hasNext()) {
      EObject element = itElement.next();
      if (element instanceof Part) {
        AbstractType type = ((Part) element).getAbstractType();
        if ((type instanceof PhysicalComponent) && (((PhysicalComponent) type).getNature() == PhysicalComponentNature.NODE)) {
          itElement.remove();
        }
      }
    }
    return elements_p;
  }

  /**
   * Retrieve whether both given part can be considered as "brother-linked" It should be defined in the same component or be an actor/in the root system
   * @param source_p
   * @param target_p
   * @return
   */
  public static boolean isBrothers(Part source_p, Part target_p) {

    Collection<EObject> deployingSource = new HashSet<EObject>();
    deployingSource.addAll(PartExt.getDeployingElements(source_p));

    if (deployingSource.size() == 0) {
      EObject sourceContainer = EcoreUtil2.getFirstContainer(source_p, CsPackage.Literals.COMPONENT);
      if ((sourceContainer != null) && (sourceContainer instanceof Component)) {
        EObject sourceContainer2 = EcoreUtil2.getFirstContainer(sourceContainer, CsPackage.Literals.COMPONENT);
        if ((sourceContainer2 != null) && (sourceContainer2 instanceof Component)) {
          deployingSource.addAll(((PartitionableElement) sourceContainer).getRepresentingPartitions());
        }
      }
    }

    Collection<EObject> deployingTarget = new HashSet<EObject>();
    deployingTarget.addAll(PartExt.getDeployingElements(target_p));

    if (deployingTarget.size() == 0) {
      EObject targetContainer = EcoreUtil2.getFirstContainer(target_p, CsPackage.Literals.COMPONENT);
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
  public static boolean isComposite(Component component_p) {
    for (Partition partition : component_p.getOwnedPartitions()) {
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
   * @param component_p the component
   * @param interface_p the interface implemented
   * @return true if the component is implementing the interface
   */
  static public boolean isImplementingInterface(Component component_p, Interface interface_p) {
    if (component_p != null) {
      for (InterfaceImplementation interfaceImpl : component_p.getImplementedInterfaceLinks()) {
        if ((null != interfaceImpl.getImplementedInterface()) && interfaceImpl.getImplementedInterface().equals(interface_p)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks w whether one of the component and all its super type is implementing the interface
   * @param component_p
   * @param interface_p
   * @return true if the component is implementing the interface
   */
  static public boolean isImplementingInterfaceWithAllSuperType(Component component_p, Interface interface_p) {
    List<GeneralizableElement> superGeneralizableElements = GeneralizableElementExt.getAllSuperGeneralizableElements(component_p);
    superGeneralizableElements.add(component_p);
    for (CapellaElement generalizableElement : superGeneralizableElements) {
      if (generalizableElement instanceof Component) {
        if (generalizableElement != null) {
          for (InterfaceImplementation interfaceImpl : ((Component) generalizableElement).getImplementedInterfaceLinks()) {
            if ((null != interfaceImpl.getImplementedInterface()) && interfaceImpl.getImplementedInterface().equals(interface_p)) {
              return true;
            }
          }
        }
      }
    }

    return false;
  }

  /**
   * This method evaluates if a Component is a leaf or not.
   * @param component_p the Component evaluated
   * @return 'true' if leaf, 'false' otherwise
   */
  public static boolean isLeaf(Component component_p) {
    return !isComposite(component_p);
  }

  /**
   * Check if both the element are of same type as LogicalComponent or PhysicalComponent and also check if there eContainer is same Type
   * @param me1 CapellaElement
   * @param me2 CapellaElement
   * @return true if both are LogicalComonent or PhysicalComponent - and both eContainer is Same Type of Element
   */
  public static boolean isSameTypeAndNotRootElement(EObject me1, EObject me2) {
    if ((me1 == null) || (me2 == null)) {
      return false;
    }

    if ((me1 instanceof LogicalComponent) && (me2 instanceof LogicalComponent)) {
      if (!((((LogicalComponent) me1).eContainer() instanceof LogicalComponent) || !(((LogicalComponent) me2).eContainer() instanceof LogicalComponent))) {
        return false;
      }
      return true;
    } else if ((me1 instanceof PhysicalComponent) && (me2 instanceof PhysicalComponent)) {
      if (!((((PhysicalComponent) me1).eContainer() instanceof PhysicalComponent) || !(((PhysicalComponent) me2).eContainer() instanceof PhysicalComponent))) {
        return false;
      }
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the component is using the interface
   * @param component_p the component
   * @param interface_p the interface used
   * @return true if the component is using the interface
   */
  static public boolean isUsingInterface(Component component_p, Interface interface_p) {
    if (component_p != null) {
      for (InterfaceUse interfaceUse : component_p.getUsedInterfaceLinks()) {
        if ((null != interfaceUse.getUsedInterface()) && interfaceUse.getUsedInterface().equals(interface_p)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks whether one of the component and all its super type is using the interface
   * @param component_p
   * @param interface_p
   * @return true if the component is using the interface
   */
  static public boolean isUsingInterfaceWithAllSuperType(Component component_p, Interface interface_p) {
    List<GeneralizableElement> superGeneralizableElements = GeneralizableElementExt.getAllSuperGeneralizableElements(component_p);
    superGeneralizableElements.add(component_p);
    for (CapellaElement generalizableElement : superGeneralizableElements) {
      if (generalizableElement instanceof Component) {
        if (generalizableElement != null) {
          for (InterfaceUse interfaceUse : ((Component) generalizableElement).getUsedInterfaceLinks()) {
            if ((null != interfaceUse.getUsedInterface()) && interfaceUse.getUsedInterface().equals(interface_p)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  private static Collection<EObject> createComponentExchangeAndDelegations(LinkedList<Part> sourceResults, ComponentPort sourcePort_p,
      LinkedList<Part> targetResults, ComponentPort targetPort_p) {

    Collection<EObject> results = new HashSet<EObject>();

    // If top-level parts are brother-linked, create delegations from lowest to top-level parts of both side
    // create a componentExchange from both top-level ports.
    boolean isMultipart = false;
    Part finalSourcePart = null;
    Part finalTargetPart = null;
    ComponentPort sourcePort = sourcePort_p;
    ComponentPort targetPort = targetPort_p;

    ComponentPortKind sourceKind = ComponentPortKind.FLOW;
    ComponentPortKind targetKind = ComponentPortKind.FLOW;

    OrientationPortKind sourceOrientation = OrientationPortKind.OUT;
    OrientationPortKind targetOrientation = OrientationPortKind.IN;
    if (sourcePort != null) {
      sourceKind = sourcePort.getKind();
      sourceOrientation = sourcePort.getOrientation();
    }
    if (targetPort != null) {
      targetKind = targetPort.getKind();
      targetOrientation = targetPort.getOrientation();
    }

    if (sourceResults.size() > 0) {
      // Create a delegation from sourcePort to the top-level part previously computed, and change sourcePort to the top-level created port
      finalSourcePart = sourceResults.removeFirst();
      while (sourceResults.size() > 0) {
        Part source = sourceResults.removeFirst();
        ComponentExchange delegation =
            ComponentExchangeExt.createComponentExchange(source, null, finalSourcePart, sourcePort, ComponentExchangeKind.DELEGATION, sourceKind,
                sourceOrientation, sourceKind, sourceOrientation, isMultipart);
        results.add(delegation);
        sourcePort = (ComponentPort) ComponentExchangeExt.getSourcePort(delegation);
        finalSourcePart = source;
      }
    }

    if (targetResults.size() > 0) {
      // Create a delegation from targetPort to the top-level part previously computed, and change targetPort to the top-level created port
      finalTargetPart = targetResults.removeFirst();
      while (targetResults.size() > 0) {
        Part target = targetResults.removeFirst();
        ComponentExchange delegation =
            ComponentExchangeExt.createComponentExchange(target, null, finalTargetPart, targetPort, ComponentExchangeKind.DELEGATION, targetKind,
                targetOrientation, targetKind, targetOrientation, isMultipart);
        results.add(delegation);
        targetPort = (ComponentPort) ComponentExchangeExt.getSourcePort(delegation);
        finalTargetPart = target;
      }
    }

    // Create a componentExchange between both top-level computed port
    if ((finalSourcePart != null) && (finalTargetPart != null)) {
      ComponentExchange exchange =
          ComponentExchangeExt.createComponentExchange(finalSourcePart, sourcePort, finalTargetPart, targetPort, ComponentExchangeKind.FLOW, sourceKind,
              sourceOrientation, targetKind, targetOrientation, isMultipart);
      results.add(exchange);
    }

    return results;
  }

  /**
   * Create for two part a componentExchange between brother-linked parts and delegations from givens parts/ports to both brother-linked parts.
   * @param sourcePart
   * @param sourcePort_p
   * @param targetPart
   * @param targetPort_p
   * @return
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static Collection<EObject> createComponentExchangeThroughDelegations(Part sourcePart, ComponentPort sourcePort_p, Part targetPart,
      ComponentPort targetPort_p) {

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
          results.addAll(createComponentExchangeAndDelegations(new LinkedList(sourceResults), sourcePort_p, new LinkedList(targetResults), targetPort_p));
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
   * @param component_p the component who is implementing the interface
   * @param interf_p the implemented interface
   */
  public static void removeImplementedInterface(Component component_p, Interface interf_p) {
    InterfaceImplementation realizationLink = null;
    ListIterator<InterfaceImplementation> it = component_p.getImplementedInterfaceLinks().listIterator();
    while (it.hasNext()) {
      InterfaceImplementation lnk = it.next();
      if (lnk.getImplementedInterface().equals(interf_p)) {
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
  public static void removeProvidedInterface(Component component_p, Interface interf_p) {
    ComponentPort stdPort = null;
    for (Partition partition : component_p.getOwnedPartitions()) {
      if ((partition instanceof ComponentPort) && ((ComponentPort) partition).getProvidedInterfaces().contains(interf_p)) {
        stdPort = (ComponentPort) partition;
        break;
      }
    }
    stdPort.getProvidedInterfaces().remove(interf_p);
  }

  /**
   * This method remove an require interface.
   */
  public static void removeRequiredInterface(Component component_p, Interface interf_p) {
    ComponentPort stdPort = null;
    for (Partition partition : component_p.getOwnedPartitions()) {
      if ((partition instanceof ComponentPort) && ((ComponentPort) partition).getRequiredInterfaces().contains(interf_p)) {
        stdPort = (ComponentPort) partition;
        break;
      }
    }
    stdPort.getRequiredInterfaces().remove(interf_p);
  }

  /**
   * This method removes an interface usage.
   * @param component_p the component who is using the interface
   * @param interf_p the used interface
   */
  public static void removeUsedInterface(Component component_p, Interface interf_p) {
    InterfaceUse useLink = null;
    ListIterator<InterfaceUse> it = component_p.getUsedInterfaceLinks().listIterator();
    while (it.hasNext()) {
      InterfaceUse lnk = it.next();
      if (lnk.getUsedInterface().equals(interf_p)) {
        useLink = lnk;
      }
    }
    if (useLink != null) {
      useLink.destroy();
    }
  }

  public static List<PartitionableElement> getAllPartitionableElementAncestors(PartitionableElement current) {
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
   * @param exchange_p
   * @param sourcePart_p
   * @param targetPart_p
   * @param sourcePort_p
   * @param targetPort_p
   * @return
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static boolean isComponentExchangeThroughDelegationsExists(Part sourcePart_p, Part targetPart_p, Port sourcePort_p, Port targetPort_p) {

    LinkedList<Part> sourceResults = new LinkedList<Part>();
    LinkedList<Part> targetResults = new LinkedList<Part>();

    boolean isBrothers = false;
    boolean canBeBrotherified = false;
    LinkedList<Part> sources = new LinkedList<Part>();
    sources.add(sourcePart_p);

    // for all parents which are brother-linked, check if there is a linked component exchange to given ports.
    while (sources.size() > 0) {
      Part source = sources.removeFirst();
      sourceResults.add(source);

      isBrothers = false;
      LinkedList<Part> targets = new LinkedList<Part>();
      targets.addFirst(targetPart_p);
      targetResults.clear();
      while ((targets.size() > 0)) {
        Part target = targets.removeFirst();
        targetResults.add(target);

        isBrothers = ComponentExt.isBrothers(source, target);

        if (isBrothers) {
          canBeBrotherified = true;
          if (areLinked(source, target, sourcePort_p, targetPort_p)) {
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
  private static boolean areLinked(Part source_p, Part target_p, Port sourcePort, Port targetPort) {
    Component sourceComponent = (Component) source_p.getAbstractType();
    Component targetComponent = (Component) target_p.getAbstractType();

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

  /*
   * Return true if given interface is providing interface of given componentPort
   */
  static public boolean isProvidingInterface(ComponentPort componentPort_p, Interface interface_p) {
    if ((componentPort_p != null) && (null != interface_p)) {
      for (Interface inter : componentPort_p.getProvidedInterfaces()) {
        if ((null != inter) && inter.equals(interface_p)) {
          return true;
        }
      }
    }
    return false;
  }

  /*
   * Return true if given interface is requiring interface of given componentPort
   */
  static public boolean isRequiringInterface(ComponentPort componentPort_p, Interface interface_p) {
    if ((componentPort_p != null) && (null != interface_p)) {
      for (Interface inter : componentPort_p.getRequiredInterfaces()) {
        if ((null != inter) && inter.equals(interface_p)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * From a component, returns its entire hierarchy as displayed in a blank architecture, with sub-component, as well as deployed them for the components of the physical level
   * @param component_p the root component
   * @return
   */
  public static List<Component> getAllSubUsedAndDeployedComponents(Component component_p) {
    List<Component> result = new ArrayList<Component>();
    result.add(component_p);

    // sub component :
    for (Component component : getSubUsedComponents(component_p)) {
      result.addAll(getAllSubUsedAndDeployedComponents(component));
    }

    // components deployed via parts
    if (component_p instanceof PhysicalComponent) {
      PhysicalComponent pc = (PhysicalComponent) component_p;
      for (PhysicalComponent deployedPC : pc.getDeployedPhysicalComponents()) {
        result.addAll(getAllSubUsedAndDeployedComponents(deployedPC));
      }
    }
    return result;
  }

}
