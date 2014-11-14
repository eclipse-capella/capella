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
    List<Port> ports = new ArrayList<Port>();
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
    List<Part> parts = new ArrayList<Part>();
    //Source
    if (componentExchange.getSource() instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd) componentExchange.getSource();
      if ((end.getPart() != null) && (end.getPart() instanceof Part)) {
        parts.add((Part) end.getPart());
      }
    } else if (componentExchange.getSource() instanceof Part) {
      parts.add((Part) componentExchange.getSource());
    }
    //Target
    if (componentExchange.getTarget() instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd) componentExchange.getTarget();
      if ((end.getPart() != null) && (end.getPart() instanceof Part)) {
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
  public static final boolean isEmpty(ExchangeLink functionalLink_p) {
    if ((functionalLink_p.getExchanges() != null) && !(functionalLink_p.getExchanges().isEmpty())) {
      return false;
    }
    if ((functionalLink_p.getSources() != null) && !functionalLink_p.getSources().isEmpty()) {
      return false;
    }
    if ((functionalLink_p.getDestinations() != null) && !functionalLink_p.getDestinations().isEmpty()) {
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
  public static final boolean isEmpty(ExchangeSpecification exchange_p) {
    return (exchange_p.getSource() == null) || (exchange_p.getTarget() == null);
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
  public static final boolean isLevelCoherent(ExchangeLink functionalLink_p) {
    if (isEmpty(functionalLink_p)) {
      return true;
    }
    Set<EObject> temp = new HashSet<EObject>();
    temp.add(functionalLink_p);
    temp.addAll(functionalLink_p.getExchanges());
    temp.addAll(functionalLink_p.getDestinations());
    temp.addAll(functionalLink_p.getSources());
    EObject previous = functionalLink_p;
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
    EList<FunctionalExchange> functionExchanges = new BasicEList<FunctionalExchange>();
    List<AbstractFunction> subFunctions = FunctionExt.getAllAbstractFunctions(architecture);
    for (AbstractFunction abstractFunction : subFunctions) {
      functionExchanges.addAll(abstractFunction.getOwnedFunctionalExchanges());
    }

    return functionExchanges;
  }

  /**
   * @param port_p
   * @return
   */
  public static Collection<FunctionalExchange> getFunctionalExchanges(ComponentPort port_p) {
    Collection<FunctionalExchange> exchanges = new HashSet<FunctionalExchange>();
    for (ComponentExchange componentExchange : port_p.getComponentExchanges()) {
      exchanges.addAll(componentExchange.getAllocatedFunctionalExchanges());
    }
    return exchanges;
  }

  /**
   * @param port_p
   * @return
   */
  public static InformationsExchanger getOtherBound(ExchangeSpecification exchange_p, InformationsExchanger source) {
    if (exchange_p.getSource() == source) {
      return exchange_p.getTarget();
    }
    return exchange_p.getSource();
  }

  public static Collection<ComponentExchangeEnd> getRelatedComponentExchangeEnds(Part element_p) {
    Collection<ComponentExchangeEnd> result = new ArrayList<ComponentExchangeEnd>();
    List<EReference> refs = new ArrayList<EReference>();
    refs.add(FaPackage.Literals.COMPONENT_EXCHANGE_END__PART);

    for (Object objectRef : EObjectExt.getReferencers(element_p, refs)) {
      if (!result.contains(objectRef)) {
        result.add((ComponentExchangeEnd) objectRef);
      }
    }
    return result;
  }

  public static Collection<ComponentExchangeEnd> getRelatedComponentExchangeEnds(Port element_p) {
    Collection<ComponentExchangeEnd> result = new ArrayList<ComponentExchangeEnd>();
    List<EReference> refs = new ArrayList<EReference>();
    refs.add(FaPackage.Literals.COMPONENT_EXCHANGE_END__PORT);

    for (Object objectRef : EObjectExt.getReferencers(element_p, refs)) {
      if (!result.contains(objectRef)) {
        result.add((ComponentExchangeEnd) objectRef);
      }
    }

    return result;
  }

}
