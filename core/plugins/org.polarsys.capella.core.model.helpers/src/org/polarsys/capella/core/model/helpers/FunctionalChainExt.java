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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
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
   * Returns for an involvement linked to FunctionalExchange all ExchangeItem allocated to the involvement which are not allocated to the FunctionalExchange
   * @param involvement
   * @return
   */
  public static Collection<AbstractExchangeItem> getInvalidExchangeItems(FunctionalChainInvolvement involvement) {
    // Get ExchangeItems from SequenceMessage.
    List<ExchangeItem> exchangedItemsFromInvolvement = involvement.getExchangedItems();

    if ((involvement.getInvolved() != null) && (involvement.getInvolved() instanceof FunctionalExchange)) {

      // Get ExchangeItems from invoked operation of given SequenceMessage.
      Collection<ExchangeItem> exchangeItemsFromInvokedOperation = ((FunctionalExchange) involvement.getInvolved()).getExchangedItems();
      // Collect invalid ExchangeItems (ExchangeItems referenced by the SequenceMessage but not associated with the invoked operation).
      List<AbstractExchangeItem> invalidExchangeItems = new ArrayList<AbstractExchangeItem>(exchangedItemsFromInvolvement);
      invalidExchangeItems.removeAll(exchangeItemsFromInvokedOperation);
      return invalidExchangeItems;
    }
    return Collections.emptyList();
  }

  /**
   * retrieves the source functions of a functional chain
   * @param functionalChain a functional chain
   * @return source functions of functionalChain
   */
  public static Set<AbstractFunction> getFunctionalChainFirstFunctions(FunctionalChain functionalChain) {
    Set<AbstractFunction> result = new HashSet<AbstractFunction>();
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
   * retrieves the source functions of a functional chain. if functional chain is composite, returns also starting functions of sub functional chains
   * @param functionalChain a functional chain
   * @return source functions of functionalChain
   */
  public static Collection<FunctionalChainInvolvement> getFlatFirstFunctionalChainInvolvments(FunctionalChain functionalChain) {
    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();

    LinkedList<FunctionalChain> toVisit = new LinkedList<FunctionalChain>();
    HashSet<FunctionalChain> visited = new HashSet<FunctionalChain>();
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
   * retrieves the source functions of a functional chain. if functional chain is composite, returns also starting functions of sub functional chains
   * @param chain a functional chain
   * @return source functions of functionalChain
   */
  public static Set<AbstractFunction> getFlatFunctionalChainFirstFunctions(FunctionalChain chain) {
    Set<AbstractFunction> result = new HashSet<AbstractFunction>();

    for (FunctionalChainInvolvement inv : getFlatFirstFunctionalChainInvolvments(chain)) {
      if (inv.getInvolved() instanceof AbstractFunction) {
        result.add((AbstractFunction) inv.getInvolved());
      }
    }

    return result;
  }

  /**
   * retrieves the target functions of a functional chain
   * @param functionalChain a functional chain
   * @return target functions of functionalChain
   */
  public static Set<AbstractFunction> getFunctionalChainLastFunctions(FunctionalChain functionalChain) {
    Set<AbstractFunction> result = new HashSet<AbstractFunction>();
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
   * retrieves the source functions of a functional chain. if functional chain is composite, returns also starting functions of sub functional chains
   * @param functionalChain_p a functional chain
   * @return source functions of functionalChain_p
   */
  public static Collection<FunctionalChainInvolvement> getFlatLastFunctionalChainInvolvments(FunctionalChain functionalChain1) {
    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();

    LinkedList<FunctionalChain> toVisit = new LinkedList<FunctionalChain>();
    HashSet<FunctionalChain> visited = new HashSet<FunctionalChain>();
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
   * retrieves the target functions of a functional chain if functional chain is composite, returns also ending functions of sub functional chains
   * @param functionalChain_p a functional chain
   * @return target functions of functionalChain_p
   */
  public static Set<AbstractFunction> getFlatFunctionalChainLastFunctions(FunctionalChain chain) {
    Set<AbstractFunction> result = new HashSet<AbstractFunction>();

    for (FunctionalChainInvolvement inv : getFlatLastFunctionalChainInvolvments(chain)) {
      if (inv.getInvolved() instanceof AbstractFunction) {
        result.add((AbstractFunction) inv.getInvolved());
      }
    }
    return result;
  }

  public static Set<FunctionalChainInvolvement> getPreviousExchangeInvolvements(FunctionalChainInvolvement involvement) {
    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();
    LinkedList<FunctionalChainInvolvement> toVisit = new LinkedList<FunctionalChainInvolvement>();
    HashSet<FunctionalChainInvolvement> visited = new HashSet<FunctionalChainInvolvement>();
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
   * Returns previous exchanges involved before this involvement. if its a functional chain reference, returns last exchanges info this functional chain
   * @param involvement
   * @return the previous involvements that involve a Functional Exchange
   */
  public static Set<FunctionalChainInvolvement> getFlatPreviousExchangeInvolvements(FunctionalChainInvolvement involvement) {
    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();

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
    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();
    LinkedList<FunctionalChainInvolvement> toVisit = new LinkedList<FunctionalChainInvolvement>();
    HashSet<FunctionalChainInvolvement> visited = new HashSet<FunctionalChainInvolvement>();
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
  public static Set<FunctionalChainInvolvement> getFlatNextExchangeInvolvements(FunctionalChainInvolvement involvement) {
    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();

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
    List<FunctionalChainInvolvement> ret = new ArrayList<FunctionalChainInvolvement>();
    for (FunctionalChainInvolvement inv : element.getOwnedFunctionalChainInvolvements()) {
      if (isFirstFunctionalChainInvolvement(inv)) {
        ret.add(inv);
      }
    }
    return ret;
  }

  public static List<FunctionalChainInvolvement> getLastFunctionalChainInvolvements(FunctionalChain element) {
    List<FunctionalChainInvolvement> ret = new ArrayList<FunctionalChainInvolvement>();
    for (FunctionalChainInvolvement inv : element.getOwnedFunctionalChainInvolvements()) {
      if (isLastFunctionalChainInvolvement(inv)) {
        ret.add(inv);
      }
    }
    return ret;
  }

  public static Set<FunctionalChainInvolvement> getFlatPreviousFunctionalChainInvolvements(FunctionalChainInvolvement involvement) {

    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();

    for (FunctionalChainInvolvement in : involvement.getPreviousFunctionalChainInvolvements()) {
      if (in.getInvolved() instanceof FunctionalChain) {
        result.addAll(getFlatLastFunctionalChainInvolvments((FunctionalChain) in.getInvolved()));
      } else {
        result.add(in);
      }
    }

    return result;

  }

  public static Set<FunctionalChainInvolvement> getFlatNextFunctionalChainInvolvements(FunctionalChainInvolvement involvement) {

    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();

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
   * Highly related with org.polarsys.capella.core.data.fa.validation.functionalChain.MDCHK_FunctionalChain_Involvements_1
   * @param fc
   * @return true if the functional chain is valid, false otherwise
   */
  public static boolean isFunctionalChainValid(FunctionalChain fc) {
    // Well formed
    if (!isFunctionalChainWellFormed(fc)) {
      return false;
    }
    // At least one chain involvement
    List<FunctionalChainInvolvement> sources = fc.getFirstFunctionalChainInvolvements();
    if (sources.isEmpty()) {
      return false;
    }
    // Check for cycles
    boolean cycleFound = containsACycle(fc);
    if (cycleFound) {
      return false;
    }
    return true;
  }

  /**
   * @param fc
   * @return true if the functional chain is well-formed, false otherwise
   */
  public static boolean isFunctionalChainWellFormed(FunctionalChain fc) {
    SimpleOrientedGraph<AbstractFunction> graph = new SimpleOrientedGraph<AbstractFunction>();
    if (fc.getOwnedFunctionalChainInvolvements().isEmpty()) {
      return false;
    }
    for (FunctionalChainInvolvement inv : getFlatInvolvements(fc)) {
      if (!isFunctionalChainInvolvementValid(inv)) {
        return false;
      }
      if (inv.getInvolved() instanceof FunctionalExchange) {
        FunctionalExchange currentExchange = (FunctionalExchange) inv.getInvolved();
        graph.addNode(FunctionExt.getIncomingAbstractFunction(currentExchange), FunctionExt.getOutGoingAbstractFunction(currentExchange));
      }
    }
    if (graph.isEmpty()) {
      return false;
    }
    if (!graph.isAConnectedGraph()) {
      return false;
    }
    return true;
  }

  public static Collection<FunctionalExchange> getFlatIncomingExchanges(FunctionalChainInvolvement element) {

    Collection<AbstractFunction> targetFunctions = new HashSet<AbstractFunction>();
    Collection<FunctionalExchange> targetExchanges = new HashSet<FunctionalExchange>();

    InvolvedElement involvedElement = element.getInvolved();
    if (involvedElement instanceof FunctionalExchange) {
      targetExchanges.add((FunctionalExchange) involvedElement);

    } else if (involvedElement instanceof AbstractFunction) {
      targetFunctions.add((AbstractFunction) involvedElement);

    } else if (involvedElement instanceof FunctionalChain) {
      targetFunctions.addAll(FunctionalChainExt.getFlatFunctionalChainFirstFunctions((FunctionalChain) involvedElement));
    }

    for (AbstractFunction function : targetFunctions) {
      targetExchanges.addAll(FunctionExt.getIncomingExchange(function));
    }
    return targetExchanges;
  }

  public static Collection<FunctionalExchange> getFlatOutgoingExchanges(FunctionalChainInvolvement element) {

    Collection<AbstractFunction> sourceFunctions = new HashSet<AbstractFunction>();
    Collection<FunctionalExchange> sourceExchanges = new HashSet<FunctionalExchange>();

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

  public static Collection<FunctionalExchange> getFlatCommonFunctionalExchanges(FunctionalChainInvolvement source, FunctionalChainInvolvement target) {
    Collection<FunctionalExchange> sourceExchanges = getFlatOutgoingExchanges(source);
    Collection<FunctionalExchange> targetExchanges = getFlatIncomingExchanges(target);
    sourceExchanges.retainAll(targetExchanges);
    return sourceExchanges;
  }

  public static Collection<FunctionalExchange> getFlatIncomingExchanges(FunctionalChain element) {
    Collection<AbstractFunction> targetFunctions = new HashSet<AbstractFunction>();
    Collection<FunctionalExchange> targetExchanges = new HashSet<FunctionalExchange>();

    targetFunctions.addAll(FunctionalChainExt.getFlatFunctionalChainFirstFunctions(element));

    for (AbstractFunction function : targetFunctions) {
      targetExchanges.addAll(FunctionExt.getIncomingExchange(function));
    }
    return targetExchanges;
  }

  /**
   * @param inv
   * @return true if the functional chain involvement is valid, false otherwise
   */
  public static IStatus isFunctionalChainInvolvementValidWithStatus(FunctionalChainInvolvement inv) {
    IStatus status = Status.OK_STATUS;

    boolean isOperational = CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(inv);
    String functionalChain = isOperational ? Messages.FunctionalChainExt_OperationalProcess : Messages.FunctionalChainExt_FunctionalChain;
    String function = isOperational ? Messages.FunctionalChainExt_OperationalActivity : Messages.FunctionalChainExt_Function;
    String exchange = isOperational ? Messages.FunctionalChainExt_Interaction : Messages.FunctionalChainExt_FunctionalExchange;
    String aFunction = (isOperational ? Messages.FunctionalChainExt_an : Messages.FunctionalChainExt_a) + function;
    String aExchange = (isOperational ? Messages.FunctionalChainExt_an : Messages.FunctionalChainExt_a) + exchange;
    String aFunctionalChain = (isOperational ? Messages.FunctionalChainExt_an : Messages.FunctionalChainExt_a) + functionalChain;

    // Check null and containment
    if (inv.getInvolved() == null) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.Involvement_InvolvedNull);
    }
    if (inv.getInvolver() == null) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.FunctionalChainExt_InvolverNull);
    }
    if (!(inv.eContainer().equals(inv.getInvolver()))) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.FunctionalChainExt_InvolverNotContainer);
    }

    // Check correct involved element
    if (inv instanceof FunctionalChainReference) {
      if (!(inv.getInvolved() instanceof FunctionalChain)) {
        return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_InvolvedElementNot, aFunctionalChain));
      }
    } else {
      if (!((inv.getInvolved() instanceof AbstractFunction) || (inv.getInvolved() instanceof FunctionalExchange))) {
        return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_InvolvedElementNotAndNot, aFunction, aExchange));
      }
    }

    // Check next involvement count
    if (inv.getInvolved() instanceof FunctionalExchange) {
      if (inv.getNextFunctionalChainInvolvements().size() > 1) {
	    return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_InvolvedElementWithMultipleNext, aExchange));
	  }
	  if (inv.getPreviousFunctionalChainInvolvements().size() > 1) {
	    return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_InvolvedElementWithMultiplePrevious, aExchange));
	  }
	}
    if (((inv.getInvolved() instanceof AbstractFunction) && inv.getNextFunctionalChainInvolvements().isEmpty() && inv
        .getPreviousFunctionalChainInvolvements().isEmpty())) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_InvolvementAlone, aFunction));
    }

    for (FunctionalChainInvolvement aNext : inv.getNextFunctionalChainInvolvements()) {

      // A function should be linked to a functional exchange
      if (inv.getInvolved() instanceof AbstractFunction) {
        if ((aNext.getInvolved() == null) || !(aNext.getInvolved() instanceof FunctionalExchange)) {
          return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_IsButNextIsNotA, aFunction, aExchange));
        }
        AbstractFunction currentFunction = (AbstractFunction) inv.getInvolved();
        if (!currentFunction.equals(FunctionExt.getIncomingAbstractFunction((FunctionalExchange) aNext.getInvolved()))) {
          return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_IsNotRelatedToSourceNext, aFunction, exchange));
        }

      }
      // A functional exchange should be an incoming exchange of next involvement
      else if (inv.getInvolved() instanceof FunctionalExchange) {
        if ((aNext.getInvolved() == null) || !((aNext.getInvolved() instanceof AbstractFunction) || (aNext.getInvolved() instanceof FunctionalChain))) {
          return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_NextIsNotOrNot, new Object[] { aExchange, aFunction,
                                                                                                                         aFunctionalChain }));
        }

        if (getFlatCommonFunctionalExchanges(inv, aNext).isEmpty()) {
          if (aNext.getInvolved() instanceof FunctionalChain) {
            return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_IsNotRelatedToTargetNextFunctionalChain,
                new Object[] { aExchange, function, functionalChain }));
          }
          return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_IsNotRelatedToTargetNext, new Object[] { aExchange, function,
                                                                                                                                   function }));
        }
      }
      // A functional chain should be between both involvement
      else if (inv.getInvolved() instanceof FunctionalChain) {
        if (getFlatCommonFunctionalExchanges(inv, aNext).isEmpty()) {
          if (aNext.getInvolved() instanceof FunctionalChain) {
            return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_IsNotRelatedToTargetNextFunctionalChain,
                new Object[] { aFunctionalChain, function, functionalChain }));
          }
          return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.FunctionalChainExt_IsNotRelatedToOutgoingNext, new Object[] { aFunctionalChain,
                                                                                                                                     exchange, exchange }));
        }
      }

    }
    return status;
  }

  /**
   * @param inv
   * @return true if the functional chain involvement is valid, false otherwise
   */
  public static boolean isFunctionalChainInvolvementValid(FunctionalChainInvolvement inv) {
    return isFunctionalChainInvolvementValidWithStatus(inv).isOK();
  }

  /**
   * detect a cycle in a FunctionalChain
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
  public static FunctionalChain createFunctionalChain(AbstractFunctionalChainContainer container, Collection<EObject> involvedElements) {
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
    HashMap<FunctionalExchange, FunctionalChainInvolvement> involvedExchanges = new HashMap<FunctionalExchange, FunctionalChainInvolvement>();
    HashMap<AbstractFunction, FunctionalChainInvolvement> involvedFunctions = new HashMap<AbstractFunction, FunctionalChainInvolvement>();

    for (EObject elt : involvedElements) {
      if (((elt instanceof AbstractFunction) && !(involvedFunctions.containsKey(elt)))
          || ((elt instanceof FunctionalExchange) && !(involvedExchanges.containsKey(elt)))) {

        FunctionalChainInvolvement newInv = createInvolvement(newFC, (InvolvedElement) elt);
        if (elt instanceof FunctionalExchange) {
          involvedExchanges.put((FunctionalExchange) elt, newInv);
          AbstractFunction targetFunction = FunctionExt.getOutGoingAbstractFunction((FunctionalExchange) elt);
          if (!involvedFunctions.containsKey(targetFunction)) {
            FunctionalChainInvolvement newInvFunction = createInvolvement(newFC, targetFunction);
            involvedFunctions.put(targetFunction, newInvFunction);
          }
          AbstractFunction sourceFunction = FunctionExt.getIncomingAbstractFunction((FunctionalExchange) elt);
          if (!involvedFunctions.containsKey(sourceFunction)) {
            FunctionalChainInvolvement newInvFunction = createInvolvement(newFC, sourceFunction);
            involvedFunctions.put(sourceFunction, newInvFunction);
          }
        }
        if (elt instanceof AbstractFunction) {
          involvedFunctions.put((AbstractFunction) elt, newInv);
        }
      }
    }

    for (Entry<FunctionalExchange, FunctionalChainInvolvement> me : involvedExchanges.entrySet()) {
      AbstractFunction targetFunction = FunctionExt.getOutGoingAbstractFunction(me.getKey());
      if (involvedFunctions.containsKey(targetFunction)) {
        me.getValue().getNextFunctionalChainInvolvements().add(involvedFunctions.get(targetFunction));
      }

      AbstractFunction sourceFunction = FunctionExt.getIncomingAbstractFunction(me.getKey());
      if (involvedFunctions.containsKey(sourceFunction)) {
        involvedFunctions.get(sourceFunction).getNextFunctionalChainInvolvements().add(me.getValue());
      }
    }

    return newFC;
  }

  public static FunctionalChainAbstractCapabilityInvolvement createFunctionalChainAbstractCapabilityInvolvement(AbstractCapability capability,
      FunctionalChain target) {
    for (FunctionalChainAbstractCapabilityInvolvement inv : capability.getOwnedFunctionalChainAbstractCapabilityInvolvements()) {
      if (inv.getInvolved().equals(target)) {
        return inv;
      }
    }
    FunctionalChainAbstractCapabilityInvolvement newInv = InteractionFactory.eINSTANCE.createFunctionalChainAbstractCapabilityInvolvement();
    newInv.setInvolved(target);
    capability.getOwnedFunctionalChainAbstractCapabilityInvolvements().add(newInv);
    return newInv;
  }

  /**
   * @param fc the functional chain which contains the new involvement
   * @param involved the involved element
   * @return a new FunctionalChainInvolvement initialized with the given arguments
   */
  public static FunctionalChainInvolvement createInvolvement(FunctionalChain fc, InvolvedElement involved) {
    FunctionalChainInvolvement newInv = FaFactory.eINSTANCE.createFunctionalChainInvolvement();
    fc.getOwnedFunctionalChainInvolvements().add(newInv);
    newInv.setInvolved(involved);
    return newInv;
  }

  /**
   * Returns all functional exchanges defined in the given architecture
   */
  public static List<FunctionalChain> getAllFunctionalChains(BlockArchitecture architecture) {
    EList<FunctionalChain> functionalChains = new BasicEList<FunctionalChain>();

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
    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();
    for (FunctionalChainInvolvement anInvolvement : fc.getOwnedFunctionalChainInvolvements()) {
      if (involved.equals(anInvolvement.getInvolved())) {
        result.add(anInvolvement);
      }
    }
    return result;
  }

  /**
   * Returns all involvement related to chain inclosed all involvements of sub functional chains, and involvement of functional chains
   * @param functionalChain1
   * @return
   */
  public static Collection<FunctionalChainInvolvement> getFlatInvolvements(FunctionalChain functionalChain1) {
    Collection<FunctionalChainInvolvement> involvments = new ArrayList<FunctionalChainInvolvement>();
    LinkedList<FunctionalChain> toVisit = new LinkedList<FunctionalChain>();
    HashSet<FunctionalChain> visited = new HashSet<FunctionalChain>();
    toVisit.add(functionalChain1);

    while (!toVisit.isEmpty()) {
      FunctionalChain chain = toVisit.removeFirst();
      if (visited.contains(chain)) {
        continue;
      }
      visited.add(chain);
      for (FunctionalChainInvolvement involvement : chain.getInvolvedFunctionalChainInvolvements()) {
        if ((involvement.getInvolved() != null) && (involvement.getInvolved() instanceof FunctionalChain)) {
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
    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();
    for (FunctionalChainInvolvement anInvolvement : fc.getOwnedFunctionalChainInvolvements()) {
      if ((anInvolvement.getInvolved() != null) && involvedClass.isInstance(anInvolvement.getInvolved())) {
        result.add(anInvolvement);
      }
    }
    return result;
  }

  public static Set<FunctionalExchange> getFunctionalExchanges(FunctionalChain fc) {
    Set<FunctionalExchange> result = new HashSet<FunctionalExchange>();
    for (FunctionalChainInvolvement involvement : getInvolvementsOf(fc, FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
      if (involvement.getInvolved() != null) {
        result.add((FunctionalExchange) involvement.getInvolved());
      }
    }
    return result;
  }

  public static Set<FunctionalExchange> getFlatFunctionalExchanges(FunctionalChain fc) {
    Set<FunctionalExchange> result = new HashSet<FunctionalExchange>();

    for (FunctionalChainInvolvement involvement : getFlatInvolvementsOf(fc, FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
      if (involvement.getInvolved() != null) {
        result.add((FunctionalExchange) involvement.getInvolved());
      }
    }
    return result;
  }

  /**
   * @param key_p
   * @return
   */
  public static Set<FunctionalChainInvolvement> getFlatInvolvementsOf(FunctionalChain fc, EClass involvedClass) {
    Set<FunctionalChainInvolvement> result = new HashSet<FunctionalChainInvolvement>();
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
    Set<EObject> result = new HashSet<EObject>();
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
    List<Component> result = new ArrayList<Component>();

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
     * Directed links of a source FunctionalChainInvolvements are obtained from NextFunctionalChainInvolvements method {@inheritDoc}
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

}
