/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.updateconnections.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;

/**
 * This class finds all connections and their ports between two catalog elements. Only ports wich are part of a
 * connection relationship between the two CatalogElements are recorded, all other catalog element ports are
 * discarded.
 */
public class ConnectedCatalogElementsScope implements SparseModelScope.AttachHandler {

  private final Collection<ComponentExchange> componentExchanges = new ArrayList<ComponentExchange>();
  private final Collection<FunctionalExchange> functionalExchanges = new ArrayList<FunctionalExchange>();
  private final Collection<PhysicalLink> physicalLinks = new ArrayList<PhysicalLink>();

  private final Collection<ComponentExchangeFunctionalExchangeAllocation> cefeAllocations = new ArrayList<ComponentExchangeFunctionalExchangeAllocation>();
  private final Collection<ComponentExchangeAllocation> plceAllocations = new ArrayList<ComponentExchangeAllocation>();

  private final Collection<AbstractExchangeItem> exchangeItems = new HashSet<AbstractExchangeItem>();

  private final Collection<Port> leftPorts = new LinkedHashSet<Port>();
  private final Collection<Port> rightPorts = new LinkedHashSet<Port>();
  private final Collection<PhysicalLinkEnd> leftPhysicalLinkEnds = new LinkedHashSet<PhysicalLinkEnd>();
  private final Collection<PhysicalLinkEnd> rightPhysicalLinkEnds = new LinkedHashSet<PhysicalLinkEnd>();
  private final Collection<ComponentExchangeEnd> leftComponentExchangeEnds = new LinkedHashSet<ComponentExchangeEnd>();
  private final Collection<ComponentExchangeEnd> rightComponentExchangeEnds = new LinkedHashSet<ComponentExchangeEnd>();

  private final Collection<Part> leftParts = new LinkedHashSet<Part>();
  private final Collection<Part> rightParts = new LinkedHashSet<Part>();

  // maps connection objects to their connection adapters
  private final Map<Object, Connection> connections = new HashMap<Object, Connection>();

  private final CatalogElement left;
  private final CatalogElement right;

  /**
   * Create a ConnectedCatalogElementsScope of two catalog elements, symbolically named the 'left' and 'right'
   * element. This is to indicate that the order in which the elements are passed is of no importance, both elements
   * are treated equally.
   *
   * @param left
   *          the left CatalogElement
   * @param right
   *          the right CatalogElement
   */
  public ConnectedCatalogElementsScope(CatalogElement left, CatalogElement right) {

    this.left = left;
    this.right = right;

    CatalogElementScope leftScope = new CatalogElementScope(left);
    CatalogElementScope rightScope = new CatalogElementScope(right);

    initFunctionalExchanges(leftScope.getElements(FunctionPort.class), rightScope.getElements(FunctionPort.class));
    initComponentExchanges(leftScope, rightScope);

    initPhysicalLinks(leftScope.getElements(PhysicalPort.class), rightScope.getElements(PhysicalPort.class));

    initAllocations();
    initExchangeItems();

  }

  public Collection<Part> getLeftParts() {
    return Collections.unmodifiableCollection(leftParts);
  }

  public Collection<Part> getRightParts() {
    return Collections.unmodifiableCollection(rightParts);
  }

  public Collection<Port> getLeftPorts() {
    return Collections.unmodifiableCollection(leftPorts);
  }

  public Collection<Port> getRightPorts() {
    return Collections.unmodifiableCollection(rightPorts);
  }

  public Collection<PhysicalLinkEnd> getLeftPhysicalLinkEnds() {
    return Collections.unmodifiableCollection(leftPhysicalLinkEnds);
  }

  public Collection<PhysicalLinkEnd> getRightPhysicalLinkEnds() {
    return Collections.unmodifiableCollection(rightPhysicalLinkEnds);
  }

  public Collection<ComponentExchangeEnd> getLeftComponentExchangeEnds() {
    return Collections.unmodifiableCollection(leftComponentExchangeEnds);
  }

  public Collection<ComponentExchangeEnd> getRightComponentExchangeEnds() {
    return Collections.unmodifiableCollection(rightComponentExchangeEnds);
  }

  public Collection<FunctionalExchange> getFunctionalExchanges() {
    return Collections.unmodifiableCollection(functionalExchanges);
  }

  public Collection<ComponentExchange> getComponentExchanges() {
    return Collections.unmodifiableCollection(componentExchanges);
  }

  public Collection<PhysicalLink> getPhysicalLinks() {
    return Collections.unmodifiableCollection(physicalLinks);
  }

  public Collection<AbstractExchangeItem> getExchangeItems() {
    return Collections.unmodifiableCollection(exchangeItems);
  }

  /**
   * If the parameter is a connection (here: a component exchange, a functional exchange, or a physical link), return
   * its representation as a connection object. These connection objects are then used as inputs for
   * ConnectionMatchers to compute a match ID.
   * 
   * @param connection
   * @return
   */
  public Connection adapt(Object connection) {
    return connections.get(connection);
  }

  public CatalogElement getLeftCatalogElement() {
    return left;
  }

  public CatalogElement getRightCatalogElement() {
    return right;
  }

  @Override
  public void attachContainment(EObject toAttach, EObject source, Role targetRole, EMapping mapping) {
    if (toAttach instanceof FunctionalExchange) {
      FunctionalExchange sourceFE = (FunctionalExchange) source;
      EObject out = getRoleElement(targetRole, sourceFE.getSourceFunctionOutputPort(), mapping);
      EObject in = getRoleElement(targetRole, sourceFE.getTargetFunctionInputPort(), mapping);
      AbstractFunction container = FunctionalExchangeExt.getDefaultContainer((AbstractFunction) out.eContainer(),
          (AbstractFunction) in.eContainer());
      container.getOwnedFunctionalExchanges().add((FunctionalExchange) toAttach);
    }

    if (toAttach instanceof ComponentExchange) {
      ComponentExchange sourceCE = (ComponentExchange) source;

      CapellaElement sourceCESource = sourceCE.getSourcePart();
      if (sourceCESource == null) {
        sourceCESource = (CapellaElement) sourceCE.getSourcePort();
      }

      CapellaElement sourceCETarget = sourceCE.getTargetPart();
      if (sourceCETarget == null) {
        sourceCETarget = (CapellaElement) sourceCE.getTargetPort();
      }

      CapellaElement targetCESource = (CapellaElement) getRoleElement(targetRole, sourceCESource, mapping);
      if (targetCESource instanceof ComponentPort) {
        targetCESource = (CapellaElement) targetCESource.eContainer();
      }

      CapellaElement targetCETarget = (CapellaElement) getRoleElement(targetRole, sourceCETarget, mapping);
      if (targetCETarget instanceof ComponentPort) {
        targetCETarget = (CapellaElement) targetCESource.eContainer();
      }

      CapellaElement container = ComponentExchangeExt.getDefaultContainer(targetCESource, targetCETarget);
      if (container instanceof ComponentPkg) {
        ((ComponentPkg)container).getOwnedComponentExchanges().add((ComponentExchange) toAttach);
      } else if (container instanceof Component) {
        ((Component)container).getOwnedComponentExchanges().add((ComponentExchange) toAttach);
      }
    }

    if (toAttach instanceof PhysicalLink) {
      PhysicalLink sourcePL = (PhysicalLink) source;
      EObject out = getRoleElement(targetRole, sourcePL.getSourcePhysicalPort(), mapping);
      EObject in = getRoleElement(targetRole, sourcePL.getTargetPhysicalPort(), mapping);
      Namespace container = PhysicalLinkExt.getDefaultContainer((CapellaElement) out.eContainer(),
          (CapellaElement) in.eContainer());
      if (container instanceof Component) {
        ((Component) container).getOwnedPhysicalLinks().add((PhysicalLink) toAttach);
      } else if (container instanceof ComponentPkg) {
        ((ComponentPkg) container).getOwnedPhysicalLinks().add((PhysicalLink) toAttach);
      }
    }

  }

  // find the element for a given role in a given mapping, where the element for the opposite role is known
  private EObject getRoleElement(Role role, EObject element, EMapping mapping) {
    return mapping.getMatchFor(element, role.opposite()).get(role);
  }

  private void initExchangeItems() {
    // add exchange items
    for (FunctionalExchange fe : getFunctionalExchanges()) {
      exchangeItems.addAll(fe.getExchangedItems());
    }
    for (ComponentExchange ce : getComponentExchanges()) {
      exchangeItems.addAll(ce.getConvoyedInformations());
    }
  }

  private void initAllocations() {
    // add component exchange allocations where CE and FE are in scope
    for (FunctionalExchange fe : getFunctionalExchanges()) {
      for (ComponentExchangeFunctionalExchangeAllocation cefe : EObjectExt
          .<ComponentExchangeFunctionalExchangeAllocation> getReferencers(fe,
              ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT)) {
        if (componentExchanges.contains(cefe.getSourceElement())) {
          cefeAllocations.add(cefe);
        }
      }
    }

    for (ComponentExchange ce : getComponentExchanges()) {
      for (ComponentExchangeAllocation plce : EObjectExt.<ComponentExchangeAllocation> getReferencers(ce,
          ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT)) {
        if (physicalLinks.contains(plce.getSourceElement())) {
          plceAllocations.add(plce);
        }
      }
    }
  }

  private void initComponentExchanges(CatalogElementScope leftScope, CatalogElementScope rightScope) {

    // this finds all component exchanges that are connected to any port/part in the left scope
    Collection<ComponentExchange> allExchanges = new ArrayList<ComponentExchange>();

    for (ComponentPort leftPort : leftScope.getElements(ComponentPort.class)) {
      for (ComponentExchange ex : leftPort.getComponentExchanges()) {
        allExchanges.add(ex);
      }
    }

    // Component Exchanges without ports
    for (Part leftPart : leftScope.getElements(Part.class)) {
      for (AbstractInformationFlow aif : leftPart.getInformationFlows()) {
        if (aif instanceof ComponentExchange) {
          ComponentExchange ce = (ComponentExchange) aif;
          allExchanges.add(ce);
        }
      }
    }

    // now find the component exchanges that are also connected to any port/part in the right scope
    Map<ComponentExchange, Connection> ceConnections = new HashMap<ComponentExchange, Connection>();
    for (ComponentExchange ce : allExchanges) {
      if (rightScope.getElements().contains(ce.getTargetPart())
          || rightScope.getElements().contains(ce.getTargetPort())) {
        ceConnections.put(ce, new Connection(ce, ce.getSource(), ce.getTarget()));
      } else if (rightScope.getElements().contains(ce.getSourcePart())
          || rightScope.getElements().contains(ce.getSourcePort())) {
        ceConnections.put(ce, new Connection(ce, ce.getTarget(), ce.getSource()));
      }
    }

    for (Map.Entry<ComponentExchange, Connection> entry : ceConnections.entrySet()) {
      Connection connection = entry.getValue();
      if (connection.getSource() instanceof ComponentPort) {
        leftPorts.add((ComponentPort) connection.getSource());
      } else if (connection.getSource() instanceof ComponentExchangeEnd) {
        Part x = ((ComponentExchangeEnd) connection.getSource()).getPart();
        if (x != null) {
          leftComponentExchangeEnds.add((ComponentExchangeEnd) connection.getSource());
          leftPorts.add(((ComponentExchangeEnd) connection.getSource()).getPort());
          leftParts.add((Part) x);
        } else {
          continue;
        }
      } else if (connection.getSource() instanceof Part) {
        leftParts.add((Part) connection.getSource());
      }
      if (connection.getTarget() instanceof ComponentPort) {
        rightPorts.add((ComponentPort) connection.getTarget());
      } else if (connection.getTarget() instanceof ComponentExchangeEnd) {
        Part x = ((ComponentExchangeEnd) connection.getTarget()).getPart();
        if (x != null) {
          rightPorts.add(((ComponentExchangeEnd) connection.getTarget()).getPort());
          rightComponentExchangeEnds.add(((ComponentExchangeEnd) connection.getTarget()));
          rightParts.add((Part) x);
        } else {
          continue;
        }
      } else if (connection.getTarget() instanceof Part) {
        rightParts.add((Part) connection.getTarget());
      }

      connections.put(entry.getKey(), entry.getValue());
      componentExchanges.add((ComponentExchange) entry.getKey());
    }
  }

  private void initFunctionalExchanges(Collection<FunctionPort> left, Collection<FunctionPort> right) {
    for (FunctionPort p : left) {
      if (p instanceof FunctionInputPort) {
        for (FunctionalExchange fe : ((FunctionInputPort) p).getIncomingFunctionalExchanges()) {
          if (right.contains(fe.getSourceFunctionOutputPort())) {
            functionalExchanges.add(fe);
            leftPorts.add(p);
            rightPorts.add(fe.getSourceFunctionOutputPort());
            connections.put(fe, new Connection(fe, p, fe.getSourceFunctionOutputPort()));
          }
        }
      }
      if (p instanceof FunctionOutputPort) {
        for (FunctionalExchange fe : ((FunctionOutputPort) p).getOutgoingFunctionalExchanges()) {
          if (right.contains(fe.getTargetFunctionInputPort())) {
            functionalExchanges.add(fe);
            leftPorts.add(p);
            rightPorts.add(fe.getTargetFunctionInputPort());
            connections.put(fe, new Connection(fe, p, fe.getTargetFunctionInputPort()));
          }
        }
      }
    }
  }

  private void initPhysicalLinks(Collection<PhysicalPort> left, Collection<PhysicalPort> right) {
    for (PhysicalPort p : left) {
      for (PhysicalLink pl : p.getInvolvedLinks()) {
        Connection connection = null;
        EObject source = PhysicalLinkExt.getSource(pl);
        EObject target = PhysicalLinkExt.getTarget(pl);
        if (p == pl.getSourcePhysicalPort() && right.contains(pl.getTargetPhysicalPort())) {
          connection = new Connection(pl, source, target);
        } else if (p == pl.getTargetPhysicalPort() && right.contains(pl.getSourcePhysicalPort())) {
          connection = new Connection(pl, target, source);
        }
        if (connection != null) {
          physicalLinks.add((PhysicalLink) connection.getConnection());
          connections.put(pl, connection);
          if (connection.getSource() instanceof Port) {
            leftPorts.add((Port) connection.getSource());
          } else if (connection.getSource() instanceof PhysicalLinkEnd) {
            leftPhysicalLinkEnds.add((PhysicalLinkEnd) connection.getSource());
            leftPorts.add(((PhysicalLinkEnd) connection.getSource()).getPort());
            leftParts.add(((PhysicalLinkEnd) connection.getSource()).getPart());
          }
          if (connection.getTarget() instanceof Port) {
            rightPorts.add((Port) connection.getTarget());
          } else if (connection.getTarget() instanceof PhysicalLinkEnd) {
            rightPhysicalLinkEnds.add((PhysicalLinkEnd) connection.getTarget());
            rightPorts.add(((PhysicalLinkEnd) connection.getTarget()).getPort());
            rightParts.add(((PhysicalLinkEnd) connection.getTarget()).getPart());
          }
        }
      }
    }
  }

  /**
   * Updates both arguments so that - for each port in the rec scope, the corresponding rpl port is added to the rpl
   * scope and vice versa. - exchange items in the rpl scope are added to the rec scope and vice versa
   */
  public static void complement(ConnectedCatalogElementsScope recScope, ConnectedCatalogElementsScope rplScope) {

    // for each port in the rec scope, add the corresponding port in the rpl scope
    for (Port recPort : recScope.getLeftPorts()) {
      for (CatalogElementLink rplLink : rplScope.left.getOwnedLinks()) {
        if (rplLink.getOrigin().getTarget() == recPort) {
          rplScope.leftPorts.add((Port) rplLink.getTarget());
        }

      }
    }

    for (Port recPort : recScope.getRightPorts()) {
      for (CatalogElementLink rplLink : rplScope.right.getOwnedLinks()) {
        if (rplLink.getOrigin().getTarget() == recPort) {
          rplScope.rightPorts.add((Port) rplLink.getTarget());
        }
      }
    }

    // for each port in the rpl scope, add the corresponding port in the rec scope
    for (Port port : rplScope.getLeftPorts()) {
      for (CatalogElementLink rplLink : rplScope.left.getOwnedLinks()) {
        if (rplLink.getTarget() == port) {
          recScope.leftPorts.add((Port) rplLink.getOrigin().getTarget());
        }
      }
    }

    for (Port port : rplScope.getRightPorts()) {
      for (CatalogElementLink rplLink : rplScope.right.getOwnedLinks()) {
        if (rplLink.getTarget() == port) {
          recScope.rightPorts.add((Port) rplLink.getOrigin().getTarget());
        }
      }
    }

    // for each part in the rplscope add the corresponding part in the rec scope
    for (Part part : rplScope.getRightParts()) {
      for (CatalogElementLink rplLink : rplScope.right.getOwnedLinks()) {
        if (rplLink.getTarget() == part) {
          recScope.rightParts.add((Part) rplLink.getOrigin().getTarget());
        }
      }
    }

    for (Part part : rplScope.getLeftParts()) {
      for (CatalogElementLink rplLink : rplScope.left.getOwnedLinks()) {
        if (rplLink.getTarget() == part) {
          recScope.leftParts.add((Part) rplLink.getOrigin().getTarget());
        }
      }
    }

    // for each part in the rec scope, add the corresponding part in the rpl scope
    for (Part recPart : recScope.getLeftParts()) {
      for (CatalogElementLink rplLink : rplScope.left.getOwnedLinks()) {
        if (rplLink.getOrigin().getTarget() == recPart) {
          rplScope.leftParts.add((Part) rplLink.getTarget());
        }
      }
    }

    for (Part recPart : recScope.getRightParts()) {
      for (CatalogElementLink rplLink : rplScope.right.getOwnedLinks()) {
        if (rplLink.getOrigin().getTarget() == recPart) {
          rplScope.rightParts.add((Part) rplLink.getTarget());
        }
      }
    }

    // add all exchange items in the rpl scope to those in the rec scope, and vice versa
    recScope.exchangeItems.addAll(rplScope.getExchangeItems());
    rplScope.exchangeItems.addAll(recScope.getExchangeItems());

  }

  public SparseModelScope asDiffmergeScope() {

    Collection<EObject> elements = new HashSet<EObject>();
    elements.addAll(getComponentExchanges());
    elements.addAll(getFunctionalExchanges());
    elements.addAll(getPhysicalLinks());
    elements.addAll(getLeftPorts());
    elements.addAll(getRightPorts());
    elements.addAll(getLeftPhysicalLinkEnds());
    elements.addAll(getRightPhysicalLinkEnds());
    elements.addAll(getRightComponentExchangeEnds());
    elements.addAll(getLeftComponentExchangeEnds());
    elements.addAll(getLeftParts());
    elements.addAll(getRightParts());

    elements.addAll(cefeAllocations);
    elements.addAll(plceAllocations);
    elements.addAll(exchangeItems);

    SparseModelScope result = new SparseModelScope(elements);
    result.getExternalReferenceElements().addAll(getLeftPorts());
    result.getExternalReferenceElements().addAll(getRightPorts());
    result.getExternalReferenceElements().addAll(getLeftParts());
    result.getExternalReferenceElements().addAll(getRightParts());
    result.getExternalReferenceElements().addAll(exchangeItems);

    result.setAttachHandler(this);
    return result;
  }
}