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

package org.polarsys.capella.core.data.helpers.fa.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ExchangeLink;
import org.polarsys.capella.core.data.fa.ExchangeSpecification;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionSpecification;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;

/**
 * Helper for functional analysis aspects.
 * 
 */
public class FunctionalExt {

  /**
   * Returns the parent function (first level) for a given function. The
   * parent function is null for all root functions.
   * 
   * @param FunctionSpecification
   *            function
   * @return Function the parent (may be null).
   */
  public static final FunctionSpecification getParent(FunctionSpecification function) {
    FunctionSpecification parent = null;
    if (function != null) {
      if (function.eContainer() instanceof FunctionSpecification) {
        parent = (FunctionSpecification) function.eContainer();
      }
    }
    return parent;
  }

  /**
   * Returns the root function (main ancestor) for a given function. It is the
   * function itself for root functions.
   * 
   * @param FunctionSpecification
   *            function
   * @return Function the parent (may be the given function itself).
   */
  public static final FunctionSpecification getRoot(FunctionSpecification function) {
    FunctionSpecification root = function;
    while ((root != null) && (root.eContainer() instanceof FunctionSpecification)) {
      root = (FunctionSpecification) function.eContainer();
    }
    return root;
  }

  public static List<Port> getRelatedPorts(ComponentExchange componentExchange) {
    List<Port> ports = new ArrayList<>();
    //Source
    if (componentExchange.getSource() instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd) componentExchange.getSource();
      if (end.getPort() != null) {
        ports.add(end.getPort());
      }
    } else if (componentExchange.getSource() instanceof Port) {
      ports.add((Port) componentExchange.getSource());
    }
    //Target
    if (componentExchange.getTarget() instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd) componentExchange.getTarget();
      if (end.getPort() != null) {
        ports.add(end.getPort());
      }
    } else if (componentExchange.getTarget() instanceof Port) {
      ports.add((Port) componentExchange.getTarget());
    }
    return ports;
  }

  public static List<Part> getRelatedParts(ComponentExchange componentExchange) {
    List<Part> parts = new ArrayList<>();
    //Source
    if (componentExchange.getSource() instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd) componentExchange.getSource();
      if (end.getPart() instanceof Part) {
        parts.add((Part) end.getPart());
      }
    } else if (componentExchange.getSource() instanceof Part) {
      parts.add((Part) componentExchange.getSource());
    }
    //Target
    if (componentExchange.getTarget() instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd) componentExchange.getTarget();
      if (end.getPart() instanceof Part) {
        parts.add((Part) end.getPart());
      }
    } else if (componentExchange.getTarget() instanceof Part) {
      parts.add((Part) componentExchange.getTarget());
    }
    return parts;
  }

  /**
   * Returns whether a FunctionalLink is empty. Rules : </BR>
   * <UL>
   * <LI>FunctionalLink has no sources and destination functions.</LI>
   * <LI>FunctionalLink has no referenced exchanges.</LI>
   * </UL>
   * Result :</BR> False is default answer (if given function or
   * functionalLink is null).
   * 
   * @return as specified above.
   */
  public static final boolean isEmpty(ExchangeLink functionalLink) {
    if ((functionalLink.getExchanges() != null) && !(functionalLink.getExchanges().isEmpty())) {
      return false;
    }
    if ((functionalLink.getSources() != null) && !functionalLink.getSources().isEmpty()) {
      return false;
    }
    if ((functionalLink.getDestinations() != null) && !functionalLink.getDestinations().isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Returns whether a Exchange is empty. Rules : </BR>
   * <UL>
   * <LI>Exchange has no sources and destination functions.</LI>
   * </UL>
   * Result :</BR> False is default answer (if given function or
   * functionalLink is null).
   * 
   * @return as specified above.
   */
  public static final boolean isEmpty(ExchangeSpecification exchange) {
    return (exchange.getSource() == null) || (exchange.getTarget() == null);
  }

  /**
   * Returns whether a FunctionalLink has coherent sources/destinations and
   * exchanges and container. Rules : </BR>
   * <UL>
   * <LI>FunctionalLink isEmpty {@link #isEmpty(FunctionalLink)}</LI>
   * <LI>OR</LI>
   * <LI>When not null, FunctionalLink, exchanges, sources and destinations
   * containers are identical</LI>
   * </UL>
   * Result :</BR> False is default answer (if given function or
   * functionalLink is null).
   * 
   * @return as specified above.
   */
  public static final boolean isLevelCoherent(ExchangeLink functionalLink) {
    if (isEmpty(functionalLink)) {
      return true;
    }
    Set<EObject> temp = new HashSet<>();
    temp.add(functionalLink);
    temp.addAll(functionalLink.getExchanges());
    temp.addAll(functionalLink.getDestinations());
    temp.addAll(functionalLink.getSources());
    EObject previous = functionalLink;
    for (EObject eObj : temp) {
      EObject currCont = eObj.eContainer();
      if (currCont != null) {
        if (currCont != previous) {
          return false;
        }
        previous = currCont;
      }
    }
    return true;
  }

  /**
   * Returns all functional exchanges defined in the given architecture
   */
  public static EList<FunctionalExchange> getAllFunctionalExchanges(BlockArchitecture architecture) {
    EList<FunctionalExchange> functionExchanges = new BasicEList<>();
    List<AbstractFunction> subFunctions = FunctionExt.getAllAbstractFunctions(architecture);
    for (AbstractFunction abstractFunction : subFunctions) {
      functionExchanges.addAll(abstractFunction.getOwnedFunctionalExchanges());
    }

    return functionExchanges;
  }

  /**
   * @param port
   * @return
   */
  public static Collection<FunctionalExchange> getFunctionalExchanges(ComponentPort port) {
    Collection<FunctionalExchange> exchanges = new HashSet<>();
    for (ComponentExchange componentExchange : port.getComponentExchanges()) {
      exchanges.addAll(componentExchange.getAllocatedFunctionalExchanges());
    }
    return exchanges;
  }

  /**
   * @param exchange
   * @param source
   * @return
   */
  public static InformationsExchanger getOtherBound(ExchangeSpecification exchange, InformationsExchanger source) {
    if (exchange.getSource() == source) {
      return exchange.getTarget();
    }
    return exchange.getSource();
  }

  public static Collection<ComponentExchangeEnd> getRelatedComponentExchangeEnds(Part element) {
    Collection<ComponentExchangeEnd> result = new ArrayList<>();
    List<EReference> refs = new ArrayList<>();
    refs.add(FaPackage.Literals.COMPONENT_EXCHANGE_END__PART);

    for (Object objectRef : EObjectExt.getReferencers(element, refs)) {
      if (!result.contains(objectRef)) {
        result.add((ComponentExchangeEnd) objectRef);
      }
    }
    return result;
  }

  public static Collection<ComponentExchangeEnd> getRelatedComponentExchangeEnds(Port element) {
    Collection<ComponentExchangeEnd> result = new ArrayList<>();
    List<EReference> refs = new ArrayList<>();
    refs.add(FaPackage.Literals.COMPONENT_EXCHANGE_END__PORT);

    for (Object objectRef : EObjectExt.getReferencers(element, refs)) {
      if (!result.contains(objectRef)) {
        result.add((ComponentExchangeEnd) objectRef);
      }
    }

    return result;
  }

}
