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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.ExchangeSpecification;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * The port extension.
 */
public class PortExt {

  /**
   * Attach with PortAllocation the given port to the given activityNode if wasn't yet linked by a portAllocation
   * @param port_p
   * @param activityNode_p
   */
  public static void attachPort(Port port_p, ActivityNode activityNode_p) {
    if (((activityNode_p instanceof AbstractFunction) || (activityNode_p instanceof FunctionPort)) && !isRelatedTo(port_p, activityNode_p)) {
      PortAllocation portrealS = InformationFactory.eINSTANCE.createPortAllocation();
      portrealS.setSourceElement(port_p);
      portrealS.setTargetElement((TraceableElement) activityNode_p);
      port_p.getOwnedPortAllocations().add(portrealS);
      CapellaElementExt.creationService(portrealS);
    }
  }

  /**
   * Checks if two standard ports could be connected together.
   * @param sourcePort_p The source standard port.
   * @param targetPort_p The target standard port.
   * @return <code>True</code> if the two standard ports could be connected together else <code>false</code>.
   */
  public static boolean canCommunicate(ComponentPort sourcePort_p, ComponentPort targetPort_p) {
    // Check if any provided interface of the source port matches with any
    // required interface of the target port.
    EList<Interface> providedInterfaces = sourcePort_p.getProvidedInterfaces();
    boolean matched = false;
    Iterator<?> iterator = providedInterfaces.iterator();
    while (iterator.hasNext() && !matched) {
      Interface providedInterface = (Interface) iterator.next();
      matched = targetPort_p.getRequiredInterfaces().contains(providedInterface);
    }

    // Check if any required interface of the source port matches with any
    // provided interface of the target port.
    if (!matched) {
      EList<Interface> requiredInterfaces = sourcePort_p.getRequiredInterfaces();
      iterator = requiredInterfaces.iterator();
      while (iterator.hasNext() && !matched) {
        Interface requiredInterface = (Interface) iterator.next();
        matched = targetPort_p.getProvidedInterfaces().contains(requiredInterface);
      }
    }
    return matched;
  }

  /**
   * Create an in flow port
   * @return
   */
  public static ComponentPort createInFlowPort() {
    ComponentPort inP = FaFactory.eINSTANCE.createComponentPort();
    inP.setKind(ComponentPortKind.FLOW);
    inP.setOrientation(OrientationPortKind.IN);
    return inP;
  }

  /**
   * Create an in flow port
   * @param name_p
   * @return
   */
  public static ComponentPort createInFlowPort(String name_p) {
    ComponentPort inP = FaFactory.eINSTANCE.createComponentPort(name_p);
    inP.setKind(ComponentPortKind.FLOW);
    inP.setOrientation(OrientationPortKind.IN);
    return inP;
  }

  /**
   * Create an out flow port
   * @return
   */
  public static ComponentPort createOutFlowPort() {
    ComponentPort inP = FaFactory.eINSTANCE.createComponentPort();
    inP.setKind(ComponentPortKind.FLOW);
    inP.setOrientation(OrientationPortKind.OUT);
    return inP;
  }

  /**
   * Create an out flow port
   * @param name_p
   * @return
   */
  public static ComponentPort createOutFlowPort(String name_p) {
    ComponentPort inP = FaFactory.eINSTANCE.createComponentPort(name_p);
    inP.setKind(ComponentPortKind.FLOW);
    inP.setOrientation(OrientationPortKind.OUT);
    return inP;
  }

  /**
   * @return
   */
  public static ComponentPort createStandardPort() {
    ComponentPort inP = FaFactory.eINSTANCE.createComponentPort();
    inP.setKind(ComponentPortKind.STANDARD);
    return inP;
  }

  public static final List<ComponentExchange> getAssemblyComponentExchanges(ComponentPort port) {
    List<ComponentExchange> componentExchanges = new ArrayList<ComponentExchange>(1);
    for (ComponentExchange flow : port.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.ASSEMBLY) {
        componentExchanges.add(flow);
      }
    }
    return componentExchanges;
  }

  /**
   * Returns the list of connected ComponentPort for a given ComponentPort. Rules :<br>
   * Connected ComponentPort are : </BR> Port connected to the given port directly Return :<br>
   * The list does not contain the supplied flowPort, is not null but may be empty.
   * @param FlowPort flowPort
   * @return List<Function> the list of siblings (may be empty).
   */
  public static final Collection<ComponentPort> getConnectedComponentPortsWithoutDelegation(ComponentPort port) {
    Collection<ComponentPort> connected = new HashSet<ComponentPort>(1);

    for (ComponentExchange ex : port.getComponentExchanges()) {
      if ((ex.getKind() != ComponentExchangeKind.DELEGATION)) {
        Port sourcePort = ComponentExchangeExt.getSourcePort(ex);
        Port targetPort = ComponentExchangeExt.getTargetPort(ex);
        if ((sourcePort != null) && (sourcePort instanceof ComponentPort)) {
          connected.add((ComponentPort) sourcePort);
        }
        if ((targetPort != null) && (targetPort instanceof ComponentPort)) {
          connected.add((ComponentPort) targetPort);
        }
      }
    }

    connected.remove(port);
    return connected;
  }

  /**
   * Returns the list of connected ComponentPort for a given ComponentPort. Rules :<br>
   * Connected ComponentPort are : </BR> Port connected to the given port directly Return :<br>
   * The list does not contain the supplied flowPort, is not null but may be empty.
   * @param FlowPort flowPort
   * @return List<Function> the list of siblings (may be empty).
   */
  public static final Collection<ComponentPort> getConnectedComponentPorts(ComponentPort port) {
    Collection<ComponentPort> connected = new HashSet<ComponentPort>(1);

    for (ComponentExchange ex : port.getComponentExchanges()) {
      Port sourcePort = ComponentExchangeExt.getSourcePort(ex);
      Port targetPort = ComponentExchangeExt.getTargetPort(ex);

      if ((sourcePort != null) && (sourcePort instanceof ComponentPort)) {
        connected.add((ComponentPort) sourcePort);
      }
      if ((targetPort != null) && (targetPort instanceof ComponentPort)) {
        connected.add((ComponentPort) targetPort);
      }
    }

    connected.remove(port);
    return connected;
  }

  public static final List<ComponentPortAllocation> getIncomingComponentPortAllocations(ComponentPort port) {
    List<ComponentPortAllocation> componentExchanges = new ArrayList<ComponentPortAllocation>(1);
    for (AbstractTrace flow : port.getIncomingTraces()) {
      if (flow instanceof ComponentPortAllocation) {
        componentExchanges.add((ComponentPortAllocation) flow);
      }
    }
    return componentExchanges;
  }

  public static final List<ComponentExchange> getDelegationComponentExchanges(ComponentPort port) {
    List<ComponentExchange> componentExchanges = new ArrayList<ComponentExchange>(1);
    for (ComponentExchange flow : port.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        componentExchanges.add(flow);
      }
    }
    return componentExchanges;
  }

  public static final List<ComponentExchange> getFlowComponentExchanges(ComponentPort port) {
    List<ComponentExchange> componentExchanges = new ArrayList<ComponentExchange>(1);
    for (ComponentExchange flow : port.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.FLOW) {
        componentExchanges.add(flow);
      }
    }
    return componentExchanges;
  }

  public static final Collection<ComponentPort> getAllLinkedDelegatedComponentPorts(ComponentPort port_p) {
    LinkedList<ComponentPort> ports = new LinkedList<ComponentPort>();
    Collection<ComponentPort> visited = new ArrayList<ComponentPort>();

    ports.add(port_p);
    while (ports.size() > 0) {
      ComponentPort port = ports.removeFirst();
      if (!visited.contains(port)) {
        visited.add(port);
        ports.addAll(getDelegatingComponentPorts(port));
      }
    }

    visited.remove(port_p);
    ports.add(port_p);
    while (ports.size() > 0) {
      ComponentPort port = ports.removeFirst();
      if (!visited.contains(port)) {
        visited.add(port);
        ports.addAll(getDelegatedComponentPorts(port));
      }
    }

    return visited;
  }

  public static final Collection<ComponentPort> getAllDelegatedComponentPorts(ComponentPort port_p) {
    LinkedList<ComponentPort> ports = new LinkedList<ComponentPort>();
    Collection<ComponentPort> visited = new HashSet<ComponentPort>();

    ports.add(port_p);
    while (ports.size() > 0) {
      ComponentPort port = ports.removeFirst();
      if (!visited.contains(port)) {
        visited.add(port);
        ports.addAll(getDelegatedComponentPorts(port));
      }
    }

    visited.remove(port_p);
    return visited;
  }

  public static final Collection<ComponentPort> getAllDelegatingComponentPorts(ComponentPort port_p) {
    LinkedList<ComponentPort> ports = new LinkedList<ComponentPort>();
    Collection<ComponentPort> visited = new HashSet<ComponentPort>();

    ports.add(port_p);
    while (ports.size() > 0) {
      ComponentPort port = ports.removeFirst();
      if (!visited.contains(port)) {
        visited.add(port);
        ports.addAll(getDelegatingComponentPorts(port));
      }
    }

    visited.remove(port_p);
    return visited;
  }

  public static final Collection<ComponentPort> getDelegatingComponentPorts(ComponentPort port_p) {
    List<ComponentPort> ports = new ArrayList<ComponentPort>(1);
    for (ComponentExchange flow : port_p.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        if (port_p.equals(ComponentExchangeExt.getTargetPort(flow))) {
          Port opposite = ComponentExchangeExt.getOppositePort(flow, port_p);
          if (opposite instanceof ComponentPort) {
            ports.add((ComponentPort) opposite);
          }
        }
      }
    }
    return ports;
  }

  public static final Collection<ComponentExchange> getDelegatingComponentExchanges(ComponentPort port_p) {
    List<ComponentExchange> exchanges = new ArrayList<ComponentExchange>(1);
    for (ComponentExchange flow : port_p.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        if (port_p.equals(ComponentExchangeExt.getTargetPort(flow))) {
          exchanges.add(flow);
        }
      }
    }
    return exchanges;
  }

  public static final Collection<ComponentPort> getDelegatedComponentPorts(ComponentPort port_p) {
    List<ComponentPort> ports = new ArrayList<ComponentPort>(1);
    for (ComponentExchange flow : port_p.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        if (port_p.equals(ComponentExchangeExt.getSourcePort(flow))) {
          Port opposite = ComponentExchangeExt.getOppositePort(flow, port_p);
          if (opposite instanceof ComponentPort) {
            ports.add((ComponentPort) opposite);
          }
        }
      }
    }
    return ports;
  }

  public static final Collection<ComponentExchange> getDelegatedComponentExchanges(ComponentPort port_p) {
    List<ComponentExchange> exchanges = new ArrayList<ComponentExchange>(1);
    for (ComponentExchange flow : port_p.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        if (port_p.equals(ComponentExchangeExt.getSourcePort(flow))) {
          exchanges.add(flow);
        }
      }
    }
    return exchanges;
  }

  public static final Collection<ComponentPort> getLinkedDelegatedComponentPorts(ComponentPort port_p) {
    List<ComponentPort> ports = new ArrayList<ComponentPort>(1);
    for (ComponentExchange flow : port_p.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        Port opposite = ComponentExchangeExt.getOppositePort(flow, port_p);
        if (opposite instanceof ComponentPort) {
          ports.add((ComponentPort) opposite);
        }
      }
    }
    return ports;
  }

  /**
   * This method retrieves the provided interfaces.
   * @param port_p the port whose provided interfaces will be retrieved
   * @return List<Interface> the provided interfaces
   */
  public static List<Interface> getProvidedInterfaces(ComponentPort port_p) {
    List<Interface> providedInterfaces = new ArrayList<Interface>();

    if (port_p != null) {
      SystemComponent portType = (SystemComponent) port_p.getType();
      if (portType != null) {
        if (portType instanceof PhysicalComponent) {
          PhysicalComponent pc = (PhysicalComponent) portType;
          providedInterfaces.addAll(PhysicalComponentExt.getProvidedInterfaces(pc));
        } else {
          List<Interface> providedSet = portType.getImplementedInterfaces();
          for (Interface itf : providedSet) {
            providedInterfaces.add(itf);
          }
        }
      }
    }

    return providedInterfaces;
  }

  /**
   * Retrieve the owning component of the given port_p
   * @param aFeature_p
   * @return
   */
  public static Component getRelatedComponent(ComponentPort port_p) {
    if (port_p.eContainer() instanceof Component) {
      return (Component) port_p.eContainer();
    }
    return null;
  }

  /**
   * Retrieve the owning component of the given port_p
   * @param aFeature_p
   * @return
   */
  public static Component getRelatedComponent(PhysicalPort port_p) {
    if (port_p.eContainer() instanceof Component) {
      return (Component) port_p.eContainer();
    }
    return null;
  }

  /**
   * Returns the list of current non delegation exchanges for a given flowPort.</BR> Rules :<br>
   * <UL>
   * <LI>must be instances of Exchange.</LI>
   * <LI>flowPort must be at one end of those PortCommunications.</LI>
   * </UL>
   * Return :<br>
   * The list is not null but may be empty.
   */
  public static final List<ExchangeSpecification> getRelatedExchangeSpecifications(ComponentPort port) {
    List<ExchangeSpecification> exchanges = new ArrayList<ExchangeSpecification>(1);
    for (AbstractInformationFlow flow : port.getInformationFlows()) {
      if (flow instanceof ExchangeSpecification) {
        ExchangeSpecification exchange = (ExchangeSpecification) flow;
        exchanges.add(exchange);
      }
    }

    return exchanges;
  }

  /**
   * This method retrieves the required interfaces.
   * @param port_p the port whose required interfaces will be retrieved
   * @return List<Interface> the required interfaces
   */
  public static List<Interface> getRequiredInterfaces(ComponentPort port_p) {
    List<Interface> requiredInterfaces = new ArrayList<Interface>();

    if (port_p != null) {
      SystemComponent portType = (SystemComponent) port_p.getType();
      if (portType != null) {
        if (portType instanceof PhysicalComponent) {
          PhysicalComponent pc = (PhysicalComponent) portType;
          requiredInterfaces.addAll(PhysicalComponentExt.getRequiredInterfaces(pc));
        } else {
          List<Interface> requiredSet = portType.getUsedInterfaces();
          for (Interface itf : requiredSet) {
            requiredInterfaces.add(itf);
          }
        }
      }
    }

    return requiredInterfaces;
  }

  /**
   * Returns whether the given ComponentPorts have complementary orientations.</BR> Rules :</BR>
   * <UL>
   * <LI>an IN has complementary orientation with INOUT Or OUT.</LI>
   * <LI>an OUT has complementary orientation with INOUT Or IN.</LI>
   * </UL>
   * Return :</BR> Defaults to true.
   * @return boolean as specified above.
   */
  public static final boolean haveComplementaryOrientation(ComponentPort first, ComponentPort second) {
    if ((first == null) || (second == null)) {
      // trivial protection
      return false;
    }
    if (first.getOrientation() == OrientationPortKind.IN) {
      return second.getOrientation() != OrientationPortKind.IN;
    }
    if (first.getOrientation() == OrientationPortKind.OUT) {
      return second.getOrientation() != OrientationPortKind.OUT;
    }
    // default case
    return true;
  }

  /**
   * Returns whether the given ComponentPorts have same orientation.</BR> Rules :</BR>
   * <UL>
   * <LI>an IN has same orientation with IN Or INOUT.</LI>
   * <LI>an OUT has same orientation with OUT Or INOUT.</LI>
   * </UL>
   * Return :</BR> Defaults to true.
   * @return boolean as specified above.
   */
  public static final boolean haveSameOrientation(ComponentPort first, ComponentPort second) {
    if ((first == null) || (second == null)) {
      // trivial protection
      return false;
    }
    if (first.getOrientation() == OrientationPortKind.IN) {
      return second.getOrientation() != OrientationPortKind.OUT;
    }
    if (first.getOrientation() == OrientationPortKind.OUT) {
      return second.getOrientation() != OrientationPortKind.IN;
    }
    // default case
    return true;
  }

  /**
   * Check if the Component related to a ComponentPort (or one of his subcomponents) is allocating the Function related to a FunctionPort.<br>
   * This method can be used to know if a PortAllocation can be created between the given ports.
   * @param componentPort_p
   * @param functionPort_p
   * @return <ul>
   *         <li><code>Boolean.TRUE</code> if the related Component (or one if its subcomponents) is allocating the related Function,</li>
   *         <li><code>Boolean.FALSE</code> if not,</li>
   *         <li><code>null</code> if given ComponentPort isn't contained by a Component or if given function port is not or is not contained by an
   *         AbstractFunction.</li>
   *         </ul>
   */
  public static Boolean isRelatedComponentAllocatingRelatedFunction(ComponentPort componentPort_p, ActivityNode functionPort_p) {
    // Get the Component containing the given port.
    Component component = PortExt.getRelatedComponent(componentPort_p);
    if (null == component) {
      return null;
    }
    // Get the AbstractFunction containing the given port.
    AbstractFunction abstractFunction = FunctionExt.getRelatedFunction(functionPort_p);
    if (null == abstractFunction) {
      return null;
    }
    // Get the component and all its nested components.
    List<Component> componentsHierarchy = ComponentExt.getAllSubUsedAndDeployedComponents(component);
    // Get all components allocating the function.
    List<AbstractFunctionalBlock> allocatingComponents = abstractFunction.getAllocationBlocks();
    // Is the Function allocated by a Component which is the same as/nested in the parent Component ?
    if (Collections.disjoint(componentsHierarchy, allocatingComponents)) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }

  /**
   * @param aFeature_p
   * @return
   */
  public static boolean isFlowPort(EObject aFeature_p) {
    return (aFeature_p instanceof ComponentPort) && (((ComponentPort) aFeature_p).getKind() == ComponentPortKind.FLOW);
  }

  /**
   * @param aFeature_p
   * @return
   */
  public static boolean isIn(ComponentPort aFeature_p) {
    return (aFeature_p.getOrientation() == OrientationPortKind.IN) || (aFeature_p.getOrientation() == OrientationPortKind.INOUT);
  }

  /**
   * Checks if port is an in flow port.
   * @param port the given componentPort
   * @return true, if is in flow port
   */
  public static boolean isInFlowPort(EObject port) {
    return isFlowPort(port) && isIn((ComponentPort) port);
  }

  /**
   * @param cp_p
   * @return
   */
  public static boolean isInStrict(ComponentPort cp_p) {
    return OrientationPortKind.IN.equals(cp_p.getOrientation());
  }

  /**
   * @param cp_p
   * @return
   */
  public static boolean isOutStrict(ComponentPort cp_p) {
    return OrientationPortKind.OUT.equals(cp_p.getOrientation());
  }

  public static boolean isNotCompatibleWith(ComponentPort comparedPort_p, ComponentPort reference_p) {
    if ((comparedPort_p == null) || (reference_p == null)) {
      return true;
    }

    SystemComponent comparedType = (SystemComponent) comparedPort_p.getType();
    SystemComponent referenceType = (SystemComponent) reference_p.getType();

    if ((comparedType == null) || (referenceType == null)) {
      return true;
    }

    List<Interface> comparedProvItf = comparedPort_p.getProvidedInterfaces();
    List<Interface> comparedReqItf = comparedPort_p.getRequiredInterfaces();

    List<Interface> referenceProvItf = reference_p.getProvidedInterfaces();
    List<Interface> referenceReqItf = reference_p.getRequiredInterfaces();

    for (Interface refItf : referenceReqItf) {
      if (comparedProvItf.contains(refItf)) {
        return false;
      }
    }
    for (Interface refItf : referenceProvItf) {
      if (comparedReqItf.contains(refItf)) {
        return false;
      }
    }

    return true;
  }

  /**
   * @param aFeature_p
   * @return
   */
  public static boolean isOut(ComponentPort aFeature_p) {
    return (aFeature_p.getOrientation() == OrientationPortKind.OUT) || (aFeature_p.getOrientation() == OrientationPortKind.INOUT);
  }

  /**
   * Checks if port is an out flow port.
   * @param port the given componentPort
   * @return true, if is out flow port
   */
  public static boolean isOutFlowPort(EObject port) {
    return isFlowPort(port) && isOut((ComponentPort) port);
  }

  /**
   * Returns whether the component port is related to the function port and has a connection which allocate the functional exchange
   */
  public static boolean isRelatedTo(Port cport_p, ActivityNode fport_p) {
    if ((cport_p == null) || (fport_p == null)) {
      return false;
    }
    for (AbstractTrace trace : cport_p.getIncomingTraces()) {
      if ((trace.getSourceElement() != null) && trace.getSourceElement().equals(fport_p)) {
        return true;
      }
      if ((trace.getTargetElement() != null) && trace.getTargetElement().equals(fport_p)) {
        return true;
      }
    }

    for (AbstractTrace trace : cport_p.getOutgoingTraces()) {
      if ((trace.getSourceElement() != null) && trace.getSourceElement().equals(fport_p)) {
        return true;
      }
      if ((trace.getTargetElement() != null) && trace.getTargetElement().equals(fport_p)) {
        return true;
      }
    }

    return false;
  }

  /**
   * @param eObject_p
   * @return
   */
  public static boolean isStandardPort(Object aFeature_p) {
    return (aFeature_p instanceof ComponentPort) && (((ComponentPort) aFeature_p).getKind() == ComponentPortKind.STANDARD);
  }

  /**
   * Returns whether the port sourcePrevious_p is transitioned to sourceCurrent_p or if sourcePrevious_p is transitioned to one of parents ports of
   * sourceCurrent_p (by delegation)
   */
  public static boolean isTransitionedTo(ComponentPort sourcePrevious_p, ComponentPort sourceCurrent_p) {
    if (RefinementLinkExt.isLinkedTo(sourceCurrent_p, sourcePrevious_p)) {
      return true;
    }

    for (ComponentPort port : retrieveParentDelegating(sourceCurrent_p)) {
      if (RefinementLinkExt.isLinkedTo(port, sourcePrevious_p)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether the port sourcePrevious_p is transitioned to sourceCurrent_p or if sourcePrevious_p is transitioned to one of parents ports of
   * sourceCurrent_p (by delegation)
   */
  public static boolean isTransitionedTo(FunctionPort sourcePrevious_p, FunctionPort sourceCurrent_p) {
    return RefinementLinkExt.isLinkedTo(sourceCurrent_p, sourcePrevious_p);
  }

  public static Collection<ComponentPort> retrieveParentDelegating(ComponentPort sourceCurrent_p) {
    LinkedList<ComponentPort> ports = new LinkedList<ComponentPort>();
    HashSet<ComponentPort> visited = new HashSet<ComponentPort>();
    ports.add(sourceCurrent_p);

    while (ports.size() > 0) {
      ComponentPort port = ports.removeFirst();
      visited.add(port);

      for (ComponentPort cp : PortExt.getLinkedDelegatedComponentPorts(sourceCurrent_p)) {
        if (!visited.contains(cp)) {
          ports.addLast(cp);
        }
      }
    }

    return visited;
  }

  /**
   * Returns whether the activity node is defined in a transitioned function or sub function of the given container (works for port and others activity nodes)
   */
  public static boolean transitionedPortIsValid(ActivityNode eObj_p, TraceableElement container) {
    if (null != container) {
      for (AbstractTrace traceFunction : container.getIncomingTraces()) {
        if (traceFunction.getSourceElement() instanceof AbstractFunction) {
          AbstractFunction targetFunction = (AbstractFunction) traceFunction.getSourceElement();
          if (EcoreUtil2.isOrIsContainedBy(eObj_p, targetFunction)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * @param eObj_p
   * @return
   */
  public static boolean transitionedPortIsValid(ComponentPort eObj_p) {
    for (AbstractTrace trace : eObj_p.getOutgoingTraces()) {
      if (trace.getTargetElement() instanceof ComponentPort) {
        ComponentPort exc = (ComponentPort) trace.getTargetElement();
        Component container = (Component) exc.eContainer();
        if (transitionedPortIsValid(eObj_p, container)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns whether the port is defined in a transitioned function or sub function of the given container
   */
  public static boolean transitionedPortIsValid(ComponentPort eObj_p, TraceableElement container) {
    if (null != container) {
      for (AbstractTrace traceFunction : container.getIncomingTraces()) {
        if (traceFunction.getSourceElement() instanceof Component) {
          Component targetFunction = (Component) traceFunction.getSourceElement();
          if ((eObj_p.eContainer() == targetFunction) || ComponentExt.isComponentAncestor((Component) eObj_p.eContainer(), targetFunction)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * @param eObj_p
   * @return
   */
  public static boolean transitionedPortIsValid(FunctionPort eObj_p) {
    for (AbstractTrace trace : eObj_p.getOutgoingTraces()) {
      if (trace.getTargetElement() instanceof FunctionPort) {
        FunctionPort exc = (FunctionPort) trace.getTargetElement();
        AbstractFunction container = (AbstractFunction) exc.eContainer();
        if ((eObj_p instanceof ActivityNode) && transitionedPortIsValid((ActivityNode) eObj_p, container)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns whether the link can be considered as a delegation
   * @param delegationTargetPort_p
   * @return
   */
  public static Collection<PhysicalLink> getDelegatedPhysicalLinks(PhysicalPort port_p) {
    Collection<PhysicalLink> links = new HashSet<PhysicalLink>();

    Component component = PortExt.getRelatedComponent(port_p);
    Collection<Part> parts = ComponentExt.getRepresentingParts(component);

    for (PhysicalLink link : port_p.getInvolvedLinks()) {
      Collection<Part> sourceParts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceParts(link);
      Collection<Part> targetParts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetParts(link);

      boolean isSource = false;
      boolean isTarget = false;
      for (Part owningPart : parts) {
        if (sourceParts.contains(owningPart)) {
          isSource = true;
        } else if (targetParts.contains(owningPart)) {
          isTarget = true;
        }
      }

      if (isSource && !isTarget) {
        for (Part part : targetParts) {
          Collection<Part> firstPartAncestors = PartExt.getFirstPartAncestors(part);
          for (Part part2 : firstPartAncestors) {
            if (sourceParts.contains(part2)) {
              links.add(link);
              break;
            }
          }
        }

      } else if (isTarget && !isSource) {
        for (Part part : sourceParts) {
          Collection<Part> firstPartAncestors = PartExt.getFirstPartAncestors(part);
          for (Part part2 : firstPartAncestors) {
            if (targetParts.contains(part2)) {
              links.add(link);
              break;
            }
          }
        }
      }

    }
    return links;
  }

  /**
   * Returns whether the link can be considered as a delegation
   * @param delegationTargetPort_p
   * @return
   */
  public static Collection<PhysicalLink> getDelegatingPhysicalLinks(PhysicalPort port_p) {
    Collection<PhysicalLink> links = new HashSet<PhysicalLink>();

    Component component = PortExt.getRelatedComponent(port_p);
    Collection<Part> parts = ComponentExt.getRepresentingParts(component);

    for (PhysicalLink link : port_p.getInvolvedLinks()) {
      Collection<Part> sourceParts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceParts(link);
      Collection<Part> targetParts = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetParts(link);

      boolean isSource = false;
      boolean isTarget = false;
      for (Part owningPart : parts) {
        if (sourceParts.contains(owningPart)) {
          isSource = true;
        } else if (targetParts.contains(owningPart)) {
          isTarget = true;
        }
      }

      if (isSource && !isTarget) {
        for (Part part : sourceParts) {
          Collection<Part> firstPartAncestors = PartExt.getFirstPartAncestors(part);
          for (Part part2 : firstPartAncestors) {
            if (targetParts.contains(part2)) {
              links.add(link);
              break;
            }
          }
        }

      } else if (isTarget && !isSource) {
        for (Part part : targetParts) {
          Collection<Part> firstPartAncestors = PartExt.getFirstPartAncestors(part);
          for (Part part2 : firstPartAncestors) {
            if (sourceParts.contains(part2)) {
              links.add(link);
              break;
            }
          }
        }
      }

    }
    return links;
  }

  /**
   * Returns whether the link can be considered as a delegation
   * @param delegationTargetPort_p
   * @return
   */
  public static Collection<PhysicalLink> getDelegationPhysicalLinks(PhysicalPort port_p) {
    Collection<PhysicalLink> links = new ArrayList<PhysicalLink>();
    links.addAll(getDelegatedPhysicalLinks(port_p));
    links.addAll(getDelegatingPhysicalLinks(port_p));
    return links;
  }
}
