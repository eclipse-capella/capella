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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.SimpleOrientedGraph;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.utils.graph.CycleDetectionUtils;
import org.polarsys.capella.common.utils.graph.IDirectedGraph;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

/**
 */
public class FunctionalChainExt {

  static String PLUGIN_ID = "org.polarsys.capella.core.model.helpers"; //$NON-NLS-1$

  public static boolean isFirstFunctionalChainInvolvement(FunctionalChainInvolvement involment) {
    if ((involment.getInvolved() != null) && involment.getPreviousFunctionalChainInvolvements().isEmpty()) {
      return true;
    }
    return false;
  }

  public static boolean isLastFunctionalChainInvolvement(FunctionalChainInvolvement involment) {
    if ((involment.getInvolved() != null) && involment.getNextFunctionalChainInvolvements().isEmpty()) {
      return true;
    }
    return false;
  }

  /**
   * Returns for an involvement linked to FunctionalExchange all ExchangeItem allocated to the involvement which are not
   * allocated to the FunctionalExchange
   * 
   * @param involvement
   * @return
   */
  public static Collection<AbstractExchangeItem> getInvalidExchangeItems(FunctionalChainInvolvementLink involvement) {

    if (involvement.getInvolved() instanceof FunctionalExchange) {
      FunctionalExchange exchange = (FunctionalExchange) involvement.getInvolved();
      Set<AbstractExchangeItem> involvementExchangeItems = new HashSet<>(involvement.getExchangedItems());
      Collection<ExchangeItem> exchangeExchangedItems = exchange.getExchangedItems();

      involvementExchangeItems.removeAll(exchangeExchangedItems);
      return involvementExchangeItems;
    }
    return Collections.emptySet();
  }

  /**
   * retrieves the source functions of a functional chain
   * 
   * @param functionalChain
   *          a functional chain
   * @return source functions of functionalChain
   */
  public static Set<AbstractFunction> getFunctionalChainFirstFunctions(FunctionalChain functionalChain) {
    Set<AbstractFunction> result = new HashSet<>();
    for (FunctionalChainInvolvement inv : functionalChain.getOwnedFunctionalChainInvolvements()) {
      if (isFirstFunctionalChainInvolvement(inv)) {
        if (inv.getInvolved() instanceof AbstractFunction) {
          result.add((AbstractFunction) inv.getInvolved());
        }
      }
    }
    return result;
  }

  /**
   * retrieves the source functions of a functional chain. if functional chain is composite, returns also starting
   * functions of sub functional chains
   * 
   * @param functionalChain
   *          a functional chain
   * @return source functions of functionalChain
   */
  public static Collection<FunctionalChainInvolvement> getFlatFirstFunctionalChainInvolvments(
      FunctionalChain functionalChain) {
    Set<FunctionalChainInvolvement> result = new HashSet<>();

    LinkedList<FunctionalChain> toVisit = new LinkedList<>();
    HashSet<FunctionalChain> visited = new HashSet<>();
    toVisit.add(functionalChain);

    while (!toVisit.isEmpty()) {
      FunctionalChain chain = toVisit.removeFirst();
      if (visited.contains(chain)) {
        continue;
      }
      visited.add(chain);
      for (FunctionalChainInvolvement involvement : chain.getInvolvedFunctionalChainInvolvements()) {
        if (isFirstFunctionalChainInvolvement(involvement)) {
          if (involvement.getInvolved() instanceof FunctionalChain) {
            toVisit.add((FunctionalChain) involvement.getInvolved());
          } else {
            result.add(involvement);
          }
        }
      }
    }
    return result;
  }

  /**
   * retrieves the source functions of a functional chain. if functional chain is composite, returns also starting
   * functions of sub functional chains
   * 
   * @param chain
   *          a functional chain
   * @return source functions of functionalChain
   */
  public static Set<AbstractFunction> getFlatFunctionalChainFirstFunctions(FunctionalChain chain) {
    Set<AbstractFunction> result = new HashSet<>();

    for (FunctionalChainInvolvement inv : getFlatFirstFunctionalChainInvolvments(chain)) {
      if (inv.getInvolved() instanceof AbstractFunction) {
        result.add((AbstractFunction) inv.getInvolved());
      }
    }

    return result;
  }

  /**
   * retrieves the target functions of a functional chain
   * 
   * @param functionalChain
   *          a functional chain
   * @return target functions of functionalChain
   */
  public static Set<AbstractFunction> getFunctionalChainLastFunctions(FunctionalChain functionalChain) {
    Set<AbstractFunction> result = new HashSet<>();
    for (FunctionalChainInvolvement inv : functionalChain.getOwnedFunctionalChainInvolvements()) {
      if (isLastFunctionalChainInvolvement(inv)) {
        if (inv.getInvolved() instanceof AbstractFunction) {
          result.add((AbstractFunction) inv.getInvolved());
        }
      }
    }
    return result;
  }

  /**
   * retrieves the source functions of a functional chain. if functional chain is composite, returns also starting
   * functions of sub functional chains
   * 
   * @param functionalChain_p
   *          a functional chain
   * @return source functions of functionalChain_p
   */
  public static Collection<FunctionalChainInvolvement> getFlatLastFunctionalChainInvolvments(
      FunctionalChain functionalChain1) {
    Set<FunctionalChainInvolvement> result = new HashSet<>();

    LinkedList<FunctionalChain> toVisit = new LinkedList<>();
    HashSet<FunctionalChain> visited = new HashSet<>();
    toVisit.add(functionalChain1);

    while (!toVisit.isEmpty()) {
      FunctionalChain chain = toVisit.removeFirst();
      if (visited.contains(chain)) {
        continue;
      }
      visited.add(chain);
      for (FunctionalChainInvolvement involvement : chain.getInvolvedFunctionalChainInvolvements()) {
        if (isLastFunctionalChainInvolvement(involvement)) {
          if (involvement.getInvolved() instanceof FunctionalChain) {
            toVisit.add((FunctionalChain) involvement.getInvolved());
          } else {
            result.add(involvement);
          }
        }
      }
    }
    return result;
  }

  /**
   * retrieves the target functions of a functional chain if functional chain is composite, returns also ending
   * functions of sub functional chains
   * 
   * @param functionalChain_p
   *          a functional chain
   * @return target functions of functionalChain_p
   */
  public static Set<AbstractFunction> getFlatFunctionalChainLastFunctions(FunctionalChain chain) {
    Set<AbstractFunction> result = new HashSet<>();

    for (FunctionalChainInvolvement inv : getFlatLastFunctionalChainInvolvments(chain)) {
      if (inv.getInvolved() instanceof AbstractFunction) {
        result.add((AbstractFunction) inv.getInvolved());
      }
    }
    return result;
  }

  public static Set<FunctionalChainInvolvement> getPreviousExchangeInvolvements(
      FunctionalChainInvolvement involvement) {
    Set<FunctionalChainInvolvement> result = new HashSet<>();
    LinkedList<FunctionalChainInvolvement> toVisit = new LinkedList<>();
    HashSet<FunctionalChainInvolvement> visited = new HashSet<>();
    toVisit.add(involvement);

    while (!toVisit.isEmpty()) {
      FunctionalChainInvolvement involvment = toVisit.removeFirst();
      if (visited.contains(involvment)) {
        continue;
      }
      visited.add(involvment);

      for (FunctionalChainInvolvement aPreviousInv : involvment.getPreviousFunctionalChainInvolvements()) {
        if (aPreviousInv.getInvolved() != null) {
          if (aPreviousInv.getInvolved() instanceof FunctionalExchange) {
            result.add(aPreviousInv);
          } else {
            toVisit.add(aPreviousInv);
          }
        }
      }
    }
    return result;
  }

  /**
   * Returns previous exchanges involved before this involvement. if its a functional chain reference, returns last
   * exchanges info this functional chain
   * 
   * @param involvement
   * @return the previous involvements that involve a Functional Exchange
   */
  public static Set<FunctionalChainInvolvement> getFlatPreviousExchangeInvolvements(
      FunctionalChainInvolvement involvement) {
    Set<FunctionalChainInvolvement> result = new HashSet<>();

    for (FunctionalChainInvolvement aPreviousInv : getFlatPreviousFunctionalChainInvolvements(involvement)) {
      if (aPreviousInv.getInvolved() != null) {
        if (aPreviousInv.getInvolved() instanceof FunctionalExchange) {
          result.add(aPreviousInv);
        } else {
          for (FunctionalChainInvolvement aaanv : getFlatPreviousFunctionalChainInvolvements(aPreviousInv)) {
            if (aaanv.getInvolved() instanceof FunctionalExchange) {
              result.add(aaanv);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * @param involvement
   * @return the next involvements that involve a Functional Exchange
   */
  public static Set<FunctionalChainInvolvement> getNextExchangeInvolvements(FunctionalChainInvolvement involvement) {
    Set<FunctionalChainInvolvement> result = new HashSet<>();
    LinkedList<FunctionalChainInvolvement> toVisit = new LinkedList<>();
    HashSet<FunctionalChainInvolvement> visited = new HashSet<>();
    toVisit.add(involvement);

    while (!toVisit.isEmpty()) {
      FunctionalChainInvolvement involvment = toVisit.removeFirst();
      if (visited.contains(involvment)) {
        continue;
      }
      visited.add(involvment);

      for (FunctionalChainInvolvement aPreviousInv : involvment.getNextFunctionalChainInvolvements()) {
        if (aPreviousInv.getInvolved() != null) {
          if (aPreviousInv.getInvolved() instanceof FunctionalExchange) {
            result.add(aPreviousInv);
          } else {
            toVisit.add(aPreviousInv);
          }
        }
      }
    }

    return result;
  }

  /**
   * @param involvement
   * @return the next involvements that involve a Functional Exchange
   */
  public static Set<FunctionalChainInvolvement> getFlatNextExchangeInvolvements(
      FunctionalChainInvolvement involvement) {
    Set<FunctionalChainInvolvement> result = new HashSet<>();

    for (FunctionalChainInvolvement aPreviousInv : getFlatNextFunctionalChainInvolvements(involvement)) {
      if (aPreviousInv.getInvolved() != null) {
        if (aPreviousInv.getInvolved() instanceof FunctionalExchange) {
          result.add(aPreviousInv);
        } else {
          for (FunctionalChainInvolvement aaanv : getFlatNextFunctionalChainInvolvements(aPreviousInv)) {
            if (aaanv.getInvolved() instanceof FunctionalExchange) {
              result.add(aaanv);
            }
          }
        }
      }
    }
    return result;
  }

  public static List<FunctionalChainInvolvement> getFirstFunctionalChainInvolvements(FunctionalChain element) {
    List<FunctionalChainInvolvement> ret = new ArrayList<>();
    for (FunctionalChainInvolvement inv : element.getOwnedFunctionalChainInvolvements()) {
      if (isFirstFunctionalChainInvolvement(inv)) {
        ret.add(inv);
      }
    }
    return ret;
  }

  public static List<FunctionalChainInvolvement> getLastFunctionalChainInvolvements(FunctionalChain element) {
    List<FunctionalChainInvolvement> ret = new ArrayList<>();
    for (FunctionalChainInvolvement inv : element.getOwnedFunctionalChainInvolvements()) {
      if (isLastFunctionalChainInvolvement(inv)) {
        ret.add(inv);
      }
    }
    return ret;
  }

  public static Set<FunctionalChainInvolvement> getFlatPreviousFunctionalChainInvolvements(
      FunctionalChainInvolvement involvement) {

    Set<FunctionalChainInvolvement> result = new HashSet<>();

    for (FunctionalChainInvolvement in : involvement.getPreviousFunctionalChainInvolvements()) {
      if (in.getInvolved() instanceof FunctionalChain) {
        result.addAll(getFlatLastFunctionalChainInvolvments((FunctionalChain) in.getInvolved()));
      } else {
        result.add(in);
      }
    }

    return result;

  }

  public static Set<FunctionalChainInvolvement> getFlatNextFunctionalChainInvolvements(
      FunctionalChainInvolvement involvement) {

    Set<FunctionalChainInvolvement> result = new HashSet<>();

    for (FunctionalChainInvolvement in : involvement.getNextFunctionalChainInvolvements()) {
      if (in.getInvolved() instanceof FunctionalChain) {
        result.addAll(getFlatFirstFunctionalChainInvolvments((FunctionalChain) in.getInvolved()));
      } else {
        result.add(in);
      }
    }

    return result;
  }

  /**
   * 
   * @param fcr
   * @return a list of all first FCIFunctions and its hierarchical context included in a FunctionalChainReference
   */
  public static List<List<FunctionalChainInvolvement>> getFirstFlatHierachicalContexts(FunctionalChainReference fcr) {
    List<FunctionalChainReference> hierarchicalContext = new ArrayList<>();
    hierarchicalContext.add(fcr);
    List<List<FunctionalChainInvolvement>> allHierarchicalContexts = getFlatHierachicalContexts(fcr,
        hierarchicalContext);
    return allHierarchicalContexts.stream()
        .filter(fciCollection -> isFirstFunctionalChainInvolvement(fciCollection.get(0))).collect(Collectors.toList());
  }

  /**
   * 
   * @param fcr
   * @return a list of all last FCIFunctions and its hierarchical context included in a FunctionalChainReference
   */
  public static List<List<FunctionalChainInvolvement>> getLastFlatHierachicalContexts(FunctionalChainReference fcr) {
    List<FunctionalChainReference> hierarchicalContext = new ArrayList<>();
    hierarchicalContext.add(fcr);
    List<List<FunctionalChainInvolvement>> allHierarchicalContexts = getFlatHierachicalContexts(fcr,
        hierarchicalContext);
    return allHierarchicalContexts.stream()
        .filter(fciCollection -> isLastFunctionalChainInvolvement(fciCollection.get(0))).collect(Collectors.toList());
  }

  /**
   * 
   * @param fcr
   * @param currentContext
   * @return a list of all FCIFunctions and its hierarchical context included in a FunctionalChainReference
   */
  public static List<List<FunctionalChainInvolvement>> getFlatHierachicalContexts(FunctionalChainReference fcr,
      List<FunctionalChainReference> currentContext) {
    List<List<FunctionalChainInvolvement>> allHierarchicalContexts = new ArrayList<>();
    InvolvedElement involved = fcr.getInvolved();
    if (involved instanceof FunctionalChain) {
      FunctionalChain functionalChain = (FunctionalChain) involved;
      for (FunctionalChainInvolvement involvement : functionalChain.getInvolvedFunctionalChainInvolvements()) {
        if (involvement instanceof FunctionalChainInvolvementFunction) {
          List<FunctionalChainInvolvement> fciAndHierarchicalContext = new ArrayList<>();
          fciAndHierarchicalContext.add(involvement);
          fciAndHierarchicalContext.addAll(currentContext);
          allHierarchicalContexts.add(fciAndHierarchicalContext);
        } else if (involvement instanceof FunctionalChainReference) {
          List<FunctionalChainReference> childContext = new ArrayList<>();
          childContext.add((FunctionalChainReference) involvement);
          childContext.addAll(currentContext);
          allHierarchicalContexts
              .addAll(getFlatHierachicalContexts((FunctionalChainReference) involvement, childContext));
        }
      }
    }
    return allHierarchicalContexts;
  }

  /**
   * Highly related with
   * org.polarsys.capella.core.data.fa.validation.functionalChain.MDCHK_FunctionalChain_Involvements_1
   * 
   * @param fc
   * @return true if the functional chain is valid, false otherwise
   */
  public static boolean isFunctionalChainValid(FunctionalChain chain) {
    return isFunctionalChainWellFormed(chain) && !containsACycle(chain);
  }

  /**
   * @param chain
   * @return true if the functional chain is well-formed, false otherwise
   */
  public static boolean isFunctionalChainWellFormed(FunctionalChain chain) {
    SimpleOrientedGraph<AbstractFunction> graph = new SimpleOrientedGraph<>();

    if (chain.getOwnedFunctionalChainInvolvements().isEmpty()) {
      return false;
    }

    for (FunctionalChainInvolvement involvement : getFlatInvolvements(chain)) {
      if (!isFunctionalChainInvolvementValid(involvement)) {
        return false;
      }

      if (involvement instanceof FunctionalChainInvolvementLink
          && involvement.getInvolved() instanceof FunctionalExchange) {
        FunctionalExchange currentExchange = (FunctionalExchange) involvement.getInvolved();
        graph.addNode(FunctionExt.getIncomingAbstractFunction(currentExchange),
            FunctionExt.getOutGoingAbstractFunction(currentExchange));
      }
    }
    if (graph.isEmpty()) {
      return false;
    }

    return graph.isAConnectedGraph();
  }

  public static Collection<FunctionalExchange> getFlatIncomingExchanges(FunctionalChainInvolvement element) {

    Collection<AbstractFunction> targetFunctions = new HashSet<>();
    Collection<FunctionalExchange> targetExchanges = new HashSet<>();

    InvolvedElement involvedElement = element.getInvolved();
    if (involvedElement instanceof FunctionalExchange) {
      targetExchanges.add((FunctionalExchange) involvedElement);

    } else if (involvedElement instanceof AbstractFunction) {
      targetFunctions.add((AbstractFunction) involvedElement);

    } else if (involvedElement instanceof FunctionalChain) {
      targetFunctions
          .addAll(FunctionalChainExt.getFlatFunctionalChainFirstFunctions((FunctionalChain) involvedElement));
    }

    for (AbstractFunction function : targetFunctions) {
      targetExchanges.addAll(FunctionExt.getIncomingExchange(function));
    }
    return targetExchanges;
  }

  public static Collection<FunctionalExchange> getFlatOutgoingExchanges(FunctionalChainInvolvement element) {

    Collection<AbstractFunction> sourceFunctions = new HashSet<>();
    Collection<FunctionalExchange> sourceExchanges = new HashSet<>();

    InvolvedElement involvedElement = element.getInvolved();

    if (involvedElement instanceof FunctionalExchange) {
      sourceExchanges.add((FunctionalExchange) involvedElement);

    } else if (involvedElement instanceof AbstractFunction) {
      sourceFunctions.add((AbstractFunction) involvedElement);

    } else if (involvedElement instanceof FunctionalChain) {
      sourceFunctions.addAll(FunctionalChainExt.getFlatFunctionalChainLastFunctions((FunctionalChain) involvedElement));
    }

    for (AbstractFunction function : sourceFunctions) {
      sourceExchanges.addAll(FunctionExt.getOutGoingExchange(function));
    }

    return sourceExchanges;
  }

  public static Collection<FunctionalExchange> getFlatCommonFunctionalExchanges(FunctionalChainInvolvement source,
      FunctionalChainInvolvement target) {
    Collection<FunctionalExchange> sourceExchanges = getFlatOutgoingExchanges(source);
    Collection<FunctionalExchange> targetExchanges = getFlatIncomingExchanges(target);
    sourceExchanges.retainAll(targetExchanges);
    return sourceExchanges;
  }

  public static Collection<FunctionalExchange> getFlatIncomingExchanges(FunctionalChain element) {
    Collection<AbstractFunction> targetFunctions = new HashSet<>();
    Collection<FunctionalExchange> targetExchanges = new HashSet<>();

    targetFunctions.addAll(FunctionalChainExt.getFlatFunctionalChainFirstFunctions(element));

    for (AbstractFunction function : targetFunctions) {
      targetExchanges.addAll(FunctionExt.getIncomingExchange(function));
    }
    return targetExchanges;
  }

  /**
   * 
   * @param involvement
   * @return
   */
  public static IStatus getFunctionalChainInvolvementValidityStatus(FunctionalChainInvolvement involvement) {
    InvolvedElement involved = involvement.getInvolved();
    if (involved == null) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.Involvement_InvolvedNull);
    }

    InvolverElement involver = involvement.getInvolver();
    if (involver == null) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.FunctionalChainExt_InvolverNull);
    }

    if (!(involvement.eContainer().equals(involver))) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.FunctionalChainExt_InvolverNotContainer);
    }

    if (involvement instanceof FunctionalChainReference) {
      return getFunctionalChainReferenceValidity((FunctionalChainReference) involvement);

    } else if (involvement instanceof FunctionalChainInvolvementLink) {
      return getFunctionalChainInvolvementLinkValidity((FunctionalChainInvolvementLink) involvement);

    } else if (involvement instanceof FunctionalChainInvolvementFunction) {
      return getFunctionalChainInvolvementFunctionValidity((FunctionalChainInvolvementFunction) involvement);
    }

    return Status.OK_STATUS;
  }

  private static IStatus getFunctionalChainReferenceValidity(FunctionalChainReference reference) {
    boolean isOperationalLevel = CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(reference);
    String functionalChainType = isOperationalLevel ? Messages.FunctionalChainExt_OperationalProcess
        : Messages.FunctionalChainExt_FunctionalChain;
    String functionalChainLabel = (isOperationalLevel ? Messages.FunctionalChainExt_an : Messages.FunctionalChainExt_a)
        + functionalChainType;

    InvolvedElement involved = reference.getInvolved();
    if (!(involved instanceof FunctionalChain)) {
      return new Status(IStatus.ERROR, PLUGIN_ID,
          NLS.bind(Messages.FunctionalChainExt_InvolvedElementNot, functionalChainLabel));
    }

    return Status.OK_STATUS;
  }

  private static IStatus getFunctionalChainInvolvementLinkValidity(FunctionalChainInvolvementLink link) {
    boolean isOperationalLevel = CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(link);
    String functionType = isOperationalLevel ? Messages.FunctionalChainExt_OperationalActivity
        : Messages.FunctionalChainExt_Function;
    String exchangeType = isOperationalLevel ? Messages.FunctionalChainExt_Interaction
        : Messages.FunctionalChainExt_FunctionalExchange;
    String functionLabel = (isOperationalLevel ? Messages.FunctionalChainExt_an : Messages.FunctionalChainExt_a)
        + functionType;
    String exchangeLabel = (isOperationalLevel ? Messages.FunctionalChainExt_an : Messages.FunctionalChainExt_a)
        + exchangeType;

    InvolvedElement involved = link.getInvolved();
    if (!(involved instanceof FunctionalExchange || involved instanceof AbstractFunction)) {
      return new Status(IStatus.ERROR, PLUGIN_ID,
          NLS.bind(Messages.FunctionalChainExt_InvolvedElementNotAndNot, exchangeLabel, functionLabel));
    }

    // involvement link should have a source and target
    if (link.getSource() == null) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.FunctionalChainInvLink_SourceNull);
    }
    if (link.getTarget() == null) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.FunctionalChainInvLink_TargetNull);
    }

    for (FunctionalChainInvolvement nextInvolvement : link.getNextFunctionalChainInvolvements()) {
      // involvement link should have a function as next
      if (!(nextInvolvement instanceof FunctionalChainInvolvementFunction)) {
        return new Status(IStatus.ERROR, PLUGIN_ID,
            NLS.bind(Messages.FunctionalChainExt_IsButNextIsNotA, exchangeLabel, functionLabel));
      }

      FunctionalChainInvolvementFunction nextInvolvementFunction = (FunctionalChainInvolvementFunction) nextInvolvement;
      InvolvedElement nextInvolved = nextInvolvementFunction.getInvolved();

      // involved functional exchange should have the nextFunction as outgoing function
      if (involved instanceof FunctionalExchange
          && nextInvolved != FunctionExt.getOutGoingAbstractFunction((FunctionalExchange) involved)) {
        return new Status(IStatus.ERROR, PLUGIN_ID,
            NLS.bind(Messages.FunctionalChainExt_IsNotRelatedToSourceNext, exchangeLabel, functionType));
      }
      // involved function should be the same as the nextFunction
      else if (involved instanceof AbstractFunction && involved != nextInvolved) {
        return new Status(IStatus.ERROR, PLUGIN_ID,
            NLS.bind(Messages.FunctionalChainExt_IsNotRelatedToSourceNext, functionLabel, functionType));
      }
    }

    return Status.OK_STATUS;
  }

  private static IStatus getFunctionalChainInvolvementFunctionValidity(
      FunctionalChainInvolvementFunction involvementFunction) {
    boolean isOperationalLevel = CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(involvementFunction);
    String functionType = isOperationalLevel ? Messages.FunctionalChainExt_OperationalActivity
        : Messages.FunctionalChainExt_Function;
    String exchangeType = isOperationalLevel ? Messages.FunctionalChainExt_Interaction
        : Messages.FunctionalChainExt_FunctionalExchange;
    String functionLabel = (isOperationalLevel ? Messages.FunctionalChainExt_an : Messages.FunctionalChainExt_a)
        + functionType;
    String exchangeLabel = (isOperationalLevel ? Messages.FunctionalChainExt_an : Messages.FunctionalChainExt_a)
        + exchangeType;

    InvolvedElement involved = involvementFunction.getInvolved();
    if (!(involved instanceof AbstractFunction)) {
      return new Status(IStatus.ERROR, PLUGIN_ID,
          NLS.bind(Messages.FunctionalChainExt_InvolvedElementNot, functionLabel));
    }

    // involvement function should have at least one incoming or outgoing link
    if (involvementFunction.getIncomingInvolvementLinks().isEmpty()
        && involvementFunction.getOutgoingInvolvementLinks().isEmpty()) {
      return new Status(IStatus.ERROR, PLUGIN_ID,
          NLS.bind(Messages.FunctionalChainExt_InvolvementAlone, functionLabel));
    }

    for (FunctionalChainInvolvement nextInvolvement : involvementFunction.getNextFunctionalChainInvolvements()) {
      // involvement function should have a involvement link as next
      if (!(nextInvolvement instanceof FunctionalChainInvolvementLink)) {
        return new Status(IStatus.ERROR, PLUGIN_ID,
            NLS.bind(Messages.FunctionalChainExt_IsButNextIsNotA, functionLabel, exchangeLabel));
      }

      FunctionalChainInvolvementLink nextLink = (FunctionalChainInvolvementLink) nextInvolvement;

      // the incoming function of the next exchange should be the involved function
      if (nextLink.getInvolved() instanceof FunctionalExchange) {
        FunctionalExchange nextExchange = (FunctionalExchange) nextLink.getInvolved();
        if (involved != FunctionExt.getIncomingAbstractFunction(nextExchange)) {
          return new Status(IStatus.ERROR, PLUGIN_ID,
              NLS.bind(Messages.FunctionalChainExt_IsNotRelatedToSourceNext, functionLabel, exchangeType));
        }
      }
      // the next function should be the same as the involved function
      else if (nextLink.getInvolved() instanceof AbstractFunction && involved != nextLink.getInvolved()) {
        return new Status(IStatus.ERROR, PLUGIN_ID,
            NLS.bind(Messages.FunctionalChainExt_IsNotRelatedToSourceNext, functionLabel, exchangeType));
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * @param inv
   * @return true if the functional chain involvement is valid, false otherwise
   */
  public static boolean isFunctionalChainInvolvementValid(FunctionalChainInvolvement inv) {
    return getFunctionalChainInvolvementValidityStatus(inv).isOK();
  }

  /**
   * detect a cycle in a FunctionalChain
   * 
   * @param involvement
   * @param visitedInvolvements_p
   * @return
   */
  public static boolean containsACycle(FunctionalChainInvolvement involvement) {
    // We delegate the calculation to the containsACycle for functional chains
    if (involvement.getInvolved() instanceof FunctionalChain) {
      FunctionalChain fc = (FunctionalChain) involvement.getInvolved();
      return containsACycle(fc);
    }
    return false;
  }

  /**
   * Contains a cycle
   * 
   * @param functionalChain
   * @return if there is cycle or not
   */
  public static boolean containsACycle(FunctionalChain functionalChain) {
    // Get the graph representation
    FunctionalChainDirectedGraph graph = getFunctionalChainDirectedGraph(functionalChain);
    if (graph != null) {
      // Compute detection algorithm
      boolean cycleFound = CycleDetectionUtils.containsCycles(graph);
      if (cycleFound) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param container
   * @param involvedElements
   * @return a new FunctionalChain initialized with the given involved elements
   */
  public static FunctionalChain createFunctionalChain(AbstractFunctionalChainContainer container,
      Collection<EObject> involvedElements) {
    FunctionalChain newFC;
    if (BlockArchitectureExt.getRootBlockArchitecture(container) instanceof OperationalAnalysis) {
      newFC = OaFactory.eINSTANCE.createOperationalProcess();
    } else {
      newFC = FaFactory.eINSTANCE.createFunctionalChain();
    }
    container.getOwnedFunctionalChains().add(newFC);
    if (container instanceof AbstractCapability) {
      createFunctionalChainAbstractCapabilityInvolvement((AbstractCapability) container, newFC);
    }
    EditingDomain editingDomain = TransactionHelper.getEditingDomain(newFC);
    StrictCompoundCommand command = CreationHelper.getAdditionnalCommand(editingDomain, newFC);
    if (command.canExecute()) {
      command.execute();
    }
    HashMap<FunctionalExchange, FunctionalChainInvolvementLink> involvedExchanges = new HashMap<>();
    HashMap<AbstractFunction, FunctionalChainInvolvementFunction> involvedFunctions = new HashMap<>();

    for (EObject elt : involvedElements) {
      if (((elt instanceof AbstractFunction) && !(involvedFunctions.containsKey(elt)))
          || ((elt instanceof FunctionalExchange) && !(involvedExchanges.containsKey(elt)))) {
        if (elt instanceof FunctionalExchange) {
          FunctionalChainInvolvementLink newInvLink = createInvolvementLink(newFC, (InvolvedElement) elt);
          involvedExchanges.put((FunctionalExchange) elt, newInvLink);
          AbstractFunction targetFunction = FunctionExt.getOutGoingAbstractFunction((FunctionalExchange) elt);
          if (!involvedFunctions.containsKey(targetFunction)) {
            FunctionalChainInvolvementFunction newInvFunction = createInvolvementFunction(newFC, targetFunction);
            involvedFunctions.put(targetFunction, newInvFunction);
          }
          AbstractFunction sourceFunction = FunctionExt.getIncomingAbstractFunction((FunctionalExchange) elt);
          if (!involvedFunctions.containsKey(sourceFunction)) {
            FunctionalChainInvolvementFunction newInvFunction = createInvolvementFunction(newFC, sourceFunction);
            involvedFunctions.put(sourceFunction, newInvFunction);
          }
        }
        if (elt instanceof AbstractFunction) {
          FunctionalChainInvolvementFunction newInv = createInvolvementFunction(newFC, (InvolvedElement) elt);
          involvedFunctions.put((AbstractFunction) elt, newInv);
        }
      }
    }

    for (Entry<FunctionalExchange, FunctionalChainInvolvementLink> me : involvedExchanges.entrySet()) {
      AbstractFunction targetFunction = FunctionExt.getOutGoingAbstractFunction(me.getKey());
      if (involvedFunctions.containsKey(targetFunction)) {
        me.getValue().setTarget(involvedFunctions.get(targetFunction));
      }

      AbstractFunction sourceFunction = FunctionExt.getIncomingAbstractFunction(me.getKey());
      if (involvedFunctions.containsKey(sourceFunction)) {
        me.getValue().setSource(involvedFunctions.get(sourceFunction));
      }
    }

    return newFC;
  }

  public static FunctionalChainAbstractCapabilityInvolvement createFunctionalChainAbstractCapabilityInvolvement(
      AbstractCapability capability, FunctionalChain target) {
    for (FunctionalChainAbstractCapabilityInvolvement inv : capability
        .getOwnedFunctionalChainAbstractCapabilityInvolvements()) {
      if (inv.getInvolved().equals(target)) {
        return inv;
      }
    }
    FunctionalChainAbstractCapabilityInvolvement newInv = InteractionFactory.eINSTANCE
        .createFunctionalChainAbstractCapabilityInvolvement();
    newInv.setInvolved(target);
    capability.getOwnedFunctionalChainAbstractCapabilityInvolvements().add(newInv);
    return newInv;
  }

  /**
   * @param fc
   *          the functional chain which contains the new involvement
   * @param involved
   *          the involved element
   * @return a new FunctionalChainInvolvementFunction initialized with the given arguments
   */
  public static FunctionalChainInvolvementFunction createInvolvementFunction(FunctionalChain fc,
      InvolvedElement involved) {
    FunctionalChainInvolvementFunction newInv = FaFactory.eINSTANCE.createFunctionalChainInvolvementFunction();
    fc.getOwnedFunctionalChainInvolvements().add(newInv);
    newInv.setInvolved(involved);
    return newInv;
  }

  /**
   * @param fc
   *          the functional chain which contains the new involvement
   * @param involved
   *          the involved element
   * @return a new FunctionalChainInvolvementLink initialized with the given arguments
   */
  public static FunctionalChainInvolvementLink createInvolvementLink(FunctionalChain fc, InvolvedElement involved) {
    FunctionalChainInvolvementLink newInv = FaFactory.eINSTANCE.createFunctionalChainInvolvementLink();
    fc.getOwnedFunctionalChainInvolvements().add(newInv);
    newInv.setInvolved(involved);
    return newInv;
  }

  /**
   * Returns all functional exchanges defined in the given architecture
   */
  public static List<FunctionalChain> getAllFunctionalChains(BlockArchitecture architecture) {
    EList<FunctionalChain> functionalChains = new BasicEList<>();

    AbstractCapabilityPkg pkg = architecture.getOwnedAbstractCapabilityPkg();
    if (pkg != null) {
      for (AbstractCapability aCapability : AbstractCapabilityPkgExt.getAllAbstractCapabilities(pkg)) {
        functionalChains.addAll(aCapability.getOwnedFunctionalChains());
      }
    }

    for (AbstractFunction abstractFunction : FunctionExt.getAllAbstractFunctions(architecture)) {
      functionalChains.addAll(abstractFunction.getOwnedFunctionalChains());
    }

    return functionalChains;
  }

  /**
   * @param fc
   * @param involved
   * @return all the involvements of the functional chain that involves the given element
   */
  public static Set<FunctionalChainInvolvement> getInvolvementsOf(FunctionalChain fc, InvolvedElement involved) {
    Set<FunctionalChainInvolvement> result = new HashSet<>();
    for (FunctionalChainInvolvement anInvolvement : fc.getOwnedFunctionalChainInvolvements()) {
      if (involved.equals(anInvolvement.getInvolved())) {
        result.add(anInvolvement);
      }
    }
    return result;
  }

  /**
   * Returns all involvement related to chain inclosed all involvements of sub functional chains, and involvement of
   * functional chains
   * 
   * @param functionalChain1
   * @return
   */
  public static Collection<FunctionalChainInvolvement> getFlatInvolvements(FunctionalChain functionalChain1) {
    Collection<FunctionalChainInvolvement> involvments = new ArrayList<>();
    LinkedList<FunctionalChain> toVisit = new LinkedList<>();
    HashSet<FunctionalChain> visited = new HashSet<>();
    toVisit.add(functionalChain1);

    while (!toVisit.isEmpty()) {
      FunctionalChain chain = toVisit.removeFirst();
      if (visited.contains(chain)) {
        continue;
      }
      visited.add(chain);
      for (FunctionalChainInvolvement involvement : chain.getInvolvedFunctionalChainInvolvements()) {
        if (involvement.getInvolved() instanceof FunctionalChain) {
          toVisit.add((FunctionalChain) involvement.getInvolved());
        }
        involvments.add(involvement);
      }
    }
    return involvments;
  }

  /**
   * @param fc
   * @param involved_p
   * @return all the involvements of the functional chain that involves the given element
   */
  public static Set<FunctionalChainInvolvement> getInvolvementsOf(FunctionalChain fc, EClass involvedClass) {
    Set<FunctionalChainInvolvement> result = new HashSet<>();
    for (FunctionalChainInvolvement anInvolvement : fc.getOwnedFunctionalChainInvolvements()) {
      if ((anInvolvement.getInvolved() != null) && involvedClass.isInstance(anInvolvement.getInvolved())) {
        result.add(anInvolvement);
      }
    }
    return result;
  }

  public static Set<FunctionalExchange> getFunctionalExchanges(FunctionalChain fc) {
    Set<FunctionalExchange> result = new HashSet<>();
    for (FunctionalChainInvolvement involvement : getInvolvementsOf(fc, FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
      if (involvement.getInvolved() != null) {
        result.add((FunctionalExchange) involvement.getInvolved());
      }
    }
    return result;
  }

  public static Set<FunctionalExchange> getFlatFunctionalExchanges(FunctionalChain fc) {
    Set<FunctionalExchange> result = new HashSet<>();

    for (FunctionalChainInvolvement involvement : getFlatInvolvementsOf(fc, FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
      if (involvement.getInvolved() != null) {
        result.add((FunctionalExchange) involvement.getInvolved());
      }
    }
    return result;
  }

  public static Set<AbstractFunction> getFlatFunctions(FunctionalChain fc) {
    Set<AbstractFunction> result = new LinkedHashSet<>();

    for (FunctionalChainInvolvement involvement : getFlatInvolvementsOf(fc, FaPackage.Literals.ABSTRACT_FUNCTION)) {
      if (involvement.getInvolved() != null) {
        result.add((AbstractFunction) involvement.getInvolved());
      }
    }
    return result;
  }

  /**
   * @param key_p
   * @return
   */
  public static Set<FunctionalChainInvolvement> getFlatInvolvementsOf(FunctionalChain fc, EClass involvedClass) {
    Set<FunctionalChainInvolvement> result = new LinkedHashSet<>();
    for (FunctionalChainInvolvement involvement : FunctionalChainExt.getFlatInvolvements(fc)) {
      if ((involvement.getInvolved() != null) && involvedClass.isInstance(involvement.getInvolved())) {
        result.add(involvement);
      }
    }
    return result;
  }

  /**
   * @param key_p
   * @return
   */
  public static Set<EObject> getFlatInvolvedElements(FunctionalChain fc, EClass involvedClass) {
    Set<EObject> result = new HashSet<>();
    for (FunctionalChainInvolvement involvement : FunctionalChainExt.getFlatInvolvements(fc)) {
      if ((involvement.getInvolved() != null) && involvedClass.isInstance(involvement.getInvolved())) {
        result.add(involvement.getInvolved());
      }
    }
    return result;
  }

  /**
   * @param functionalChain
   * @return
   */
  public static FunctionalChainDirectedGraph getFunctionalChainDirectedGraph(FunctionalChain functionalChain) {
    return new FunctionalChainExt().new FunctionalChainDirectedGraph(functionalChain);
  }

  /**
   * @param functionalChain
   * @return list of components which have allocated functions involved in the given functional chain
   */
  public static List<Component> getComponents(FunctionalChain functionalChain) {
    List<Component> result = new ArrayList<>();

    EList<InvolvedElement> involvedElements = functionalChain.getInvolvedElements();
    for (InvolvedElement involvedElement : involvedElements) {
      if (involvedElement instanceof AbstractFunction) {
        List<Component> componentsFunc = AbstractFunctionExt
            .getMotherAllFunctionAllocation((AbstractFunction) involvedElement);
        if (!componentsFunc.isEmpty()) {
          result.addAll(componentsFunc);
        }
      }
    }

    return result;
  }

  /**
   * The directed graph behind a FunctionalChain. Normally to be used for cycle detection
   */
  @SuppressWarnings(value = { "all" })
  public class FunctionalChainDirectedGraph implements IDirectedGraph<Object> {

    FunctionalChain chain;

    public FunctionalChainDirectedGraph(FunctionalChain functionalChain) {
      chain = functionalChain;
    }

    /**
     * Directed links of a source FunctionalChainInvolvements are obtained from NextFunctionalChainInvolvements method
     * {@inheritDoc}
     */
    public Iterator<Object> getSucessors(Object source) {
      if (source instanceof FunctionalChainInvolvement) {
        FunctionalChainInvolvement involvement = (FunctionalChainInvolvement) source;
        List sucessors = involvement.getNextFunctionalChainInvolvements();
        if (sucessors != null) {
          return sucessors.iterator();
        }
      }
      return new ArrayList().iterator();
    }

    /**
     * Nodes are the FunctionalChainInvolvements {@inheritDoc}
     */
    public Iterator<Object> getNodes() {
      List functionalChainInvolvements = chain.getInvolvedFunctionalChainInvolvements();
      if (functionalChainInvolvements != null) {
        return functionalChainInvolvements.iterator();
      }
      return new ArrayList().iterator();
    }

  }

  /**
   * Create a new Sequence Link from a Functional Chain Involvement Link:
   * 
   * - New SL has the same source and target as FCIL - New SL has the same sourceReferenceHierarchy and
   * targetReferenceHierarchy as FCIL - New SL has the same Functional Chain container as FCIL - New SL contains FCIL in
   * its "links"
   * 
   * @param fciLink
   * @return new sequence link
   */
  public static SequenceLink createSequenceLink(FunctionalChainInvolvementLink fciLink) {

    FunctionalChainInvolvementFunction fcifSource = fciLink.getSource();
    FunctionalChainInvolvementFunction fcifTarget = fciLink.getTarget();

    FunctionalChain functionalChain = (FunctionalChain) fciLink.eContainer();

    SequenceLink newSeqLink = FaFactory.eINSTANCE.createSequenceLink();
    newSeqLink.setSource(fcifSource);
    newSeqLink.setTarget(fcifTarget);

    functionalChain.getOwnedSequenceLinks().add(newSeqLink);

    // Reuse the ref hierarchy of the selected FCILink
    newSeqLink.getTargetReferenceHierarchy().addAll(fciLink.getTargetReferenceHierarchy());
    newSeqLink.getSourceReferenceHierarchy().addAll(fciLink.getSourceReferenceHierarchy());

    newSeqLink.getLinks().add(fciLink);

    return newSeqLink;
  }

  public static Collection<ControlNode> getFlatControlNodes(FunctionalChain functionalChain) {
    List<ControlNode> ownedSequenceNodes = new ArrayList<>();
    ownedSequenceNodes.addAll(functionalChain.getOwnedSequenceNodes());
    functionalChain.getOwnedFunctionalChainInvolvements().stream().filter(FunctionalChainReference.class::isInstance)
        .map(FunctionalChainReference.class::cast).map(FunctionalChainReference::getInvolved)
        .map(FunctionalChain.class::cast).distinct().forEach(fc -> ownedSequenceNodes.addAll(getFlatControlNodes(fc)));
    return ownedSequenceNodes;
  }
  
  public static Collection<SequenceLink> getFlatSequenceLinks(FunctionalChain functionalChain) {
    List<SequenceLink> ownedSequenceLinks = new ArrayList<>();
    ownedSequenceLinks.addAll(functionalChain.getOwnedSequenceLinks());
    functionalChain.getOwnedFunctionalChainInvolvements().stream().filter(FunctionalChainReference.class::isInstance)
        .map(FunctionalChainReference.class::cast).map(FunctionalChainReference::getInvolved)
        .map(FunctionalChain.class::cast).distinct().forEach(fc -> ownedSequenceLinks.addAll(getFlatSequenceLinks(fc)));
    return ownedSequenceLinks;
  }
}
