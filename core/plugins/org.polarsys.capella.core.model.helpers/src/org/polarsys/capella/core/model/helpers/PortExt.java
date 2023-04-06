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

package org.polarsys.capella.core.model.helpers;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.SystemComponent;
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

/**
 * The port extension.
 */
public class PortExt {

  /**
   * Attach with PortAllocation the given port to the given activityNode if wasn't yet linked by a portAllocation
   * @param port
   * @param activityNode
   */
  public static void attachPort(Port port, ActivityNode activityNode) {
    if (((activityNode instanceof AbstractFunction) || (activityNode instanceof FunctionPort)) && !isRelatedTo(port, activityNode)) {
      PortAllocation portrealS = InformationFactory.eINSTANCE.createPortAllocation();
      portrealS.setSourceElement(port);
      portrealS.setTargetElement((TraceableElement) activityNode);
      port.getOwnedPortAllocations().add(portrealS);
      CapellaElementExt.creationService(portrealS);
    }
  }

  /**
   * Checks if two standard ports could be connected together.
   * @param sourcePort The source standard port.
   * @param targetPort The target standard port.
   * @return <code>True</code> if the two standard ports could be connected together else <code>false</code>.
   */
  public static boolean canCommunicate(ComponentPort sourcePort, ComponentPort targetPort) {
    // Check if any provided interface of the source port matches with any
    // required interface of the target port.
    EList<Interface> providedInterfaces = sourcePort.getProvidedInterfaces();
    boolean matched = false;
    Iterator<?> iterator = providedInterfaces.iterator();
    while (iterator.hasNext() && !matched) {
      Interface providedInterface = (Interface) iterator.next();
      matched = targetPort.getRequiredInterfaces().contains(providedInterface);
    }

    // Check if any required interface of the source port matches with any
    // provided interface of the target port.
    if (!matched) {
      EList<Interface> requiredInterfaces = sourcePort.getRequiredInterfaces();
      iterator = requiredInterfaces.iterator();
      while (iterator.hasNext() && !matched) {
        Interface requiredInterface = (Interface) iterator.next();
        matched = targetPort.getProvidedInterfaces().contains(requiredInterface);
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
   * @param name
   * @return
   */
  public static ComponentPort createInFlowPort(String name) {
    ComponentPort inP = FaFactory.eINSTANCE.createComponentPort(name);
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
   * @param name
   * @return
   */
  public static ComponentPort createOutFlowPort(String name) {
    ComponentPort inP = FaFactory.eINSTANCE.createComponentPort(name);
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

  public static final Collection<ComponentPort> getAllLinkedDelegatedComponentPorts(ComponentPort componentPort1) {
    LinkedList<ComponentPort> ports = new LinkedList<ComponentPort>();
    Collection<ComponentPort> visited = new ArrayList<ComponentPort>();

    ports.add(componentPort1);
    while (ports.size() > 0) {
      ComponentPort port = ports.removeFirst();
      if (!visited.contains(port)) {
        visited.add(port);
        ports.addAll(getDelegatingComponentPorts(port));
      }
    }

    visited.remove(componentPort1);
    ports.add(componentPort1);
    while (ports.size() > 0) {
      ComponentPort port = ports.removeFirst();
      if (!visited.contains(port)) {
        visited.add(port);
        ports.addAll(getDelegatedComponentPorts(port));
      }
    }

    return visited;
  }

  public static final Collection<ComponentPort> getAllDelegatedComponentPorts(ComponentPort componentPort1) {
    LinkedList<ComponentPort> ports = new LinkedList<ComponentPort>();
    Collection<ComponentPort> visited = new HashSet<ComponentPort>();

    ports.add(componentPort1);
    while (ports.size() > 0) {
      ComponentPort port = ports.removeFirst();
      if (!visited.contains(port)) {
        visited.add(port);
        ports.addAll(getDelegatedComponentPorts(port));
      }
    }

    visited.remove(componentPort1);
    return visited;
  }

  public static final Collection<ComponentPort> getAllDelegatingComponentPorts(ComponentPort componentPort1) {
    LinkedList<ComponentPort> ports = new LinkedList<ComponentPort>();
    Collection<ComponentPort> visited = new HashSet<ComponentPort>();

    ports.add(componentPort1);
    while (ports.size() > 0) {
      ComponentPort port = ports.removeFirst();
      if (!visited.contains(port)) {
        visited.add(port);
        ports.addAll(getDelegatingComponentPorts(port));
      }
    }

    visited.remove(componentPort1);
    return visited;
  }

  public static final Collection<ComponentPort> getDelegatingComponentPorts(ComponentPort port) {
    List<ComponentPort> ports = new ArrayList<ComponentPort>(1);
    for (ComponentExchange flow : port.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        if (port.equals(ComponentExchangeExt.getTargetPort(flow))) {
          Port opposite = ComponentExchangeExt.getOppositePort(flow, port);
          if (opposite instanceof ComponentPort) {
            ports.add((ComponentPort) opposite);
          }
        }
      }
    }
    return ports;
  }

  public static final Collection<ComponentExchange> getDelegatingComponentExchanges(ComponentPort port) {
    List<ComponentExchange> exchanges = new ArrayList<ComponentExchange>(1);
    for (ComponentExchange flow : port.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        if (port.equals(ComponentExchangeExt.getTargetPort(flow))) {
          exchanges.add(flow);
        }
      }
    }
    return exchanges;
  }

  public static final Collection<ComponentPort> getDelegatedComponentPorts(ComponentPort port) {
    List<ComponentPort> ports = new ArrayList<ComponentPort>(1);
    for (ComponentExchange flow : port.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        if (port.equals(ComponentExchangeExt.getSourcePort(flow))) {
          Port opposite = ComponentExchangeExt.getOppositePort(flow, port);
          if (opposite instanceof ComponentPort) {
            ports.add((ComponentPort) opposite);
          }
        }
      }
    }
    return ports;
  }

  public static final Collection<ComponentExchange> getDelegatedComponentExchanges(ComponentPort port) {
    List<ComponentExchange> exchanges = new ArrayList<ComponentExchange>(1);
    for (ComponentExchange flow : port.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        if (port.equals(ComponentExchangeExt.getSourcePort(flow))) {
          exchanges.add(flow);
        }
      }
    }
    return exchanges;
  }

  public static final Collection<ComponentPort> getLinkedDelegatedComponentPorts(ComponentPort port) {
    List<ComponentPort> ports = new ArrayList<ComponentPort>(1);
    for (ComponentExchange flow : port.getComponentExchanges()) {
      if (flow.getKind() == ComponentExchangeKind.DELEGATION) {
        Port opposite = ComponentExchangeExt.getOppositePort(flow, port);
        if (opposite instanceof ComponentPort) {
          ports.add((ComponentPort) opposite);
        }
      }
    }
    return ports;
  }

  /**
   * This method retrieves the provided interfaces.
   * @param port the port whose provided interfaces will be retrieved
   * @return List<Interface> the provided interfaces
   */
  public static List<Interface> getProvidedInterfaces(ComponentPort port) {
    List<Interface> providedInterfaces = new ArrayList<Interface>();

    if (port != null) {
      SystemComponent portType = (SystemComponent) port.getType();
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
   * Retrieve the owning component of the given port
   * @param aFeature_p
   * @return
   */
  public static Component getRelatedComponent(ComponentPort port) {
    if (port.eContainer() instanceof Component) {
      return (Component) port.eContainer();
    }
    return null;
  }

  /**
   * Retrieve the owning component of the given port
   * @param aFeature_p
   * @return
   */
  public static Component getRelatedComponent(PhysicalPort port) {
    if (port.eContainer() instanceof Component) {
      return (Component) port.eContainer();
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
   * @param port the port whose required interfaces will be retrieved
   * @return List<Interface> the required interfaces
   */
  public static List<Interface> getRequiredInterfaces(ComponentPort port) {
    List<Interface> requiredInterfaces = new ArrayList<Interface>();

    if (port != null) {
      SystemComponent portType = (SystemComponent) port.getType();
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
   * @param componentPort
   * @param functionPort
   * @return <ul>
   *         <li><code>Boolean.TRUE</code> if the related Component (or one if its subcomponents) is allocating the related Function,</li>
   *         <li><code>Boolean.FALSE</code> if not,</li>
   *         <li><code>null</code> if given ComponentPort isn't contained by a Component or if given function port is not or is not contained by an
   *         AbstractFunction.</li>
   *         </ul>
   */
  public static Boolean isRelatedComponentAllocatingRelatedFunction(ComponentPort componentPort, ActivityNode functionPort) {
    // Get the Component containing the given port.
    Component component = PortExt.getRelatedComponent(componentPort);
    if (null == component) {
      return null;
    }
    // Get the AbstractFunction containing the given port.
    AbstractFunction abstractFunction = FunctionExt.getRelatedFunction(functionPort);
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
   * @param aFeature
   * @return
   */
  public static boolean isFlowPort(EObject aFeature) {
    return (aFeature instanceof ComponentPort) && (((ComponentPort) aFeature).getKind() == ComponentPortKind.FLOW);
  }

  /**
   * @param aFeature
   * @return
   */
  public static boolean isIn(ComponentPort aFeature) {
    return (aFeature.getOrientation() == OrientationPortKind.IN) || (aFeature.getOrientation() == OrientationPortKind.INOUT);
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
   * Checks if port is an (strictly) in flow port.
   * @param port the given componentPort
   * @return true, if is in flow port
   */
  public static boolean isInStrictFlowPort(EObject port) {
    return isFlowPort(port) && isInStrict((ComponentPort) port);
  }

  /**
   * @param cp
   * @return
   */
  public static boolean isInStrict(ComponentPort cp) {
    return OrientationPortKind.IN.equals(cp.getOrientation());
  }

  /**
   * @param cp
   * @return
   */
  public static boolean isOutStrict(ComponentPort cp) {
    return OrientationPortKind.OUT.equals(cp.getOrientation());
  }
  
  /**
   * @param cp
   * @return
   */
  public static boolean isInoutStrict(ComponentPort cp) {
    return OrientationPortKind.INOUT.equals(cp.getOrientation());
  }

  public static boolean isNotCompatibleWith(ComponentPort comparedPort, ComponentPort reference) {
    if ((comparedPort == null) || (reference == null)) {
      return true;
    }

    SystemComponent comparedType = (SystemComponent) comparedPort.getType();
    SystemComponent referenceType = (SystemComponent) reference.getType();

    if ((comparedType == null) || (referenceType == null)) {
      return true;
    }

    List<Interface> comparedProvItf = comparedPort.getProvidedInterfaces();
    List<Interface> comparedReqItf = comparedPort.getRequiredInterfaces();

    List<Interface> referenceProvItf = reference.getProvidedInterfaces();
    List<Interface> referenceReqItf = reference.getRequiredInterfaces();

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
   * @param aFeature
   * @return
   */
  public static boolean isOut(ComponentPort aFeature) {
    return (aFeature.getOrientation() == OrientationPortKind.OUT) || (aFeature.getOrientation() == OrientationPortKind.INOUT);
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
   * Checks if port is an (strictly) out flow port.
   * @param port the given componentPort
   * @return true, if is out flow port
   */
  public static boolean isOutStrictFlowPort(EObject port) {
    return isFlowPort(port) && isOutStrict((ComponentPort) port);
  }
  
  /**
   * Checks if port is an (strictly) inout flow port.
   * @param port the given componentPort
   * @return true, if is in flow port
   */
  public static boolean isInoutStrictFlowPort(EObject port) {
    return isFlowPort(port) && isInoutStrict((ComponentPort) port);
  }

  /**
   * Returns whether the component port is related to the function port and has a connection which allocate the functional exchange
   */
  public static boolean isRelatedTo(Port cport, ActivityNode fport) {
    if ((cport == null) || (fport == null)) {
      return false;
    }
    for (AbstractTrace trace : cport.getIncomingTraces()) {
      if ((trace.getSourceElement() != null) && trace.getSourceElement().equals(fport)) {
        return true;
      }
      if ((trace.getTargetElement() != null) && trace.getTargetElement().equals(fport)) {
        return true;
      }
    }

    for (AbstractTrace trace : cport.getOutgoingTraces()) {
      if ((trace.getSourceElement() != null) && trace.getSourceElement().equals(fport)) {
        return true;
      }
      if ((trace.getTargetElement() != null) && trace.getTargetElement().equals(fport)) {
        return true;
      }
    }

    return false;
  }

  /**
   * @param eObject_p
   * @return
   */
  public static boolean isStandardPort(Object aFeature) {
    return (aFeature instanceof ComponentPort) && (((ComponentPort) aFeature).getKind() == ComponentPortKind.STANDARD);
  }

  /**
   * Returns whether the port sourcePrevious is transitioned to sourceCurrent or if sourcePrevious is transitioned to one of parents ports of
   * sourceCurrent (by delegation)
   */
  public static boolean isTransitionedTo(ComponentPort sourcePrevious, ComponentPort sourceCurrent) {
    if (RefinementLinkExt.isLinkedTo(sourceCurrent, sourcePrevious)) {
      return true;
    }

    for (ComponentPort port : retrieveParentDelegating(sourceCurrent)) {
      if (RefinementLinkExt.isLinkedTo(port, sourcePrevious)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether the port sourcePrevious is transitioned to sourceCurrent or if sourcePrevious is transitioned to one of parents ports of
   * sourceCurrent (by delegation)
   */
  public static boolean isTransitionedTo(FunctionPort sourcePrevious, FunctionPort sourceCurrent) {
    return RefinementLinkExt.isLinkedTo(sourceCurrent, sourcePrevious);
  }

  public static Collection<ComponentPort> retrieveParentDelegating(ComponentPort sourceCurrent) {
    LinkedList<ComponentPort> ports = new LinkedList<ComponentPort>();
    HashSet<ComponentPort> visited = new HashSet<ComponentPort>();
    ports.add(sourceCurrent);

    while (ports.size() > 0) {
      ComponentPort port = ports.removeFirst();
      visited.add(port);

      for (ComponentPort cp : PortExt.getLinkedDelegatedComponentPorts(sourceCurrent)) {
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
  public static boolean transitionedPortIsValid(ActivityNode eObj, TraceableElement container) {
    if (null != container) {
      for (AbstractTrace traceFunction : container.getIncomingTraces()) {
        if (traceFunction.getSourceElement() instanceof AbstractFunction) {
          AbstractFunction targetFunction = (AbstractFunction) traceFunction.getSourceElement();
          if (EcoreUtil2.isOrIsContainedBy(eObj, targetFunction)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * @param eObj
   * @return
   */
  public static boolean transitionedPortIsValid(ComponentPort eObj) {
    for (AbstractTrace trace : eObj.getOutgoingTraces()) {
      if (trace.getTargetElement() instanceof ComponentPort) {
        ComponentPort exc = (ComponentPort) trace.getTargetElement();
        Component container = (Component) exc.eContainer();
        if (transitionedPortIsValid(eObj, container)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns whether the port is defined in a transitioned function or sub function of the given container
   */
  public static boolean transitionedPortIsValid(ComponentPort eObj, TraceableElement container) {
    if (null != container) {
      for (AbstractTrace traceFunction : container.getIncomingTraces()) {
        if (traceFunction.getSourceElement() instanceof Component) {
          Component targetFunction = (Component) traceFunction.getSourceElement();
          if ((eObj.eContainer() == targetFunction) || ComponentExt.isComponentAncestor((Component) eObj.eContainer(), targetFunction)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * @param eObj
   * @return
   */
  public static boolean transitionedPortIsValid(FunctionPort eObj) {
    for (AbstractTrace trace : eObj.getOutgoingTraces()) {
      if (trace.getTargetElement() instanceof FunctionPort) {
        FunctionPort exc = (FunctionPort) trace.getTargetElement();
        AbstractFunction container = (AbstractFunction) exc.eContainer();
        if ((eObj instanceof ActivityNode) && transitionedPortIsValid((ActivityNode) eObj, container)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns whether the link can be considered as a delegation
   * @param port
   * @return
   */
  public static Collection<PhysicalLink> getDelegatedPhysicalLinks(PhysicalPort port) {
    Collection<PhysicalLink> links = new HashSet<>();

    Component component = PortExt.getRelatedComponent(port);
    Collection<Part> parts = getCache(ComponentExt::getRepresentingParts, component);

    for (PhysicalLink link : port.getInvolvedLinks()) {
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
   * @param port
   * @return
   */
  public static Collection<PhysicalLink> getDelegatingPhysicalLinks(PhysicalPort port) {
    Collection<PhysicalLink> links = new HashSet<>();

    Component component = PortExt.getRelatedComponent(port);
    Collection<Part> parts = getCache(ComponentExt::getRepresentingParts, component);

    for (PhysicalLink link : port.getInvolvedLinks()) {
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
  public static Collection<PhysicalLink> getDelegationPhysicalLinks(PhysicalPort port) {
    Collection<PhysicalLink> links = new ArrayList<PhysicalLink>();
    links.addAll(getDelegatedPhysicalLinks(port));
    links.addAll(getDelegatingPhysicalLinks(port));
    return links;
  }
}
