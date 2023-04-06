/*******************************************************************************
 * Copyright (c) 2020, 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.cache;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.cache.Cache;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.sirius.analysis.SequenceDiagramServices;

/**
 * Compute Scenario cache structure for sequence diagram.
 * 
 * @author nlepine
 *
 */
public class ScenarioCache {

  /**
   * Singleton.
   */
  private static ScenarioCache instance;

  /**
   * Generic cache (basically for getCovered).
   */
  private Cache interactionCache = new Cache();

  /**
   * Cache for MessageEnd -> Execution/InstanceRole and InteractionState -> StateFragment. (Cross referencer might be a
   * way to get this information.)
   */
  private Map<InteractionFragment, TimeLapse> interactionFragmentToTimeLapseCache = new ConcurrentHashMap<>();

  /**
   * Cache for ChildExecution -> ParentExecution and StateFragment -> ParentExecution.
   */
  private Map<EObject, Collection<EObject>> semanticCandidatesCache = new ConcurrentHashMap<>();

  /**
   * Cache for InstanceRole -> semantic candidate structure.
   */
  private Map<InstanceRole, List<SemanticCandidateContext>> instanceRoleToSemanticCandidateContextsCache = new ConcurrentHashMap<>();

  /**
   * Cache for InteractionOperand -> OperandContext(CombinedFragment, OperandEnd).
   */
  private Map<InteractionOperand, OperandContext> operandToOperandContextCache = new ConcurrentHashMap<>();

  /**
   * Is InteractionRefreshExtension caches enabled ?
   */
  private boolean isCacheEnabled;

  /**
   * Constructor.
   */
  private ScenarioCache() {
  }

  /**
   * @return ScenarioCache
   */
  public static ScenarioCache getInstance() {
    if (instance == null) {
      instance = new ScenarioCache();
    }
    return instance;
  }

  /**
   * Clear all caches.
   */
  public void clearCaches() {
    interactionCache.clearCache();
    semanticCandidatesCache.clear();
    instanceRoleToSemanticCandidateContextsCache.clear();
    operandToOperandContextCache.clear();
    interactionFragmentToTimeLapseCache.clear();
  }

  /**
   * 
   * @param function
   * @param parameter
   * @return If enabled, return the cached result if any or apply the function to the given parameter and cache the
   *         result before returning it. //
   */
  public <P, R> R getInteractionCache(Function<P, R> function, P parameter) {
    return interactionCache.get(function, parameter);
  }

  /**
   * Get SemanticCandidateContext structure for instanceRole
   * 
   * @param instanceRole
   *          InstanceRole
   * @return List<SemanticCandidateContext>
   */
  public List<SemanticCandidateContext> getInstanceRoleToSemanticCandidateContextCache(InstanceRole instanceRole) {
    return instanceRoleToSemanticCandidateContextsCache.get(instanceRole);
  }

  /**
   * Put SemanticCandidateContext structure for instanceRole in cache.
   * 
   * @param instanceRole
   *          InstanceRole
   * @param structure
   *          List<SemanticCandidateContext>
   */
  public void putInstanceRoleToSemanticCandidateContextsCache(InstanceRole instanceRole,
      List<SemanticCandidateContext> structure) {
    instanceRoleToSemanticCandidateContextsCache.put(instanceRole, structure);
  }

  /**
   * Get OperandContext(CombinedFragment, OperandEnd) for operand
   *
   * @param operand
   *          InteractionOperand
   * @return OperandContext
   */
  public OperandContext getOperandToOperandContextCache(InteractionOperand operand) {
    return operandToOperandContextCache.get(operand);
  }

  /**
   * Get element container.
   * 
   * @param capellaElement
   *          EObject
   * @return Collection<EObject>
   */
  public Collection<EObject> getSemanticCandidatesFromCache(EObject capellaElement) {
    return semanticCandidatesCache.get(capellaElement);
  }

  /**
   * Put element -> container in cache.
   * 
   * @param element
   *          EObject
   * @param container
   *          Collection<EObject>
   */
  public void putSemanticCandidatesInCache(EObject element, Collection<EObject> container) {
    semanticCandidatesCache.put(element, container);
  }

  /**
   * @param key
   *          InteractionFragment
   * @return TimeLapse corresponding to key.
   */
  public TimeLapse getTimeLapseFromCache(InteractionFragment key) {
    return interactionFragmentToTimeLapseCache.get(key);
  }

  /**
   * Put end -> TimeLapse in cache.
   * 
   * @param key
   *          InteractionFragment
   * @param value
   *          TimeLapse
   */
  public void putTimeLapseInCache(InteractionFragment key, TimeLapse value) {
    interactionFragmentToTimeLapseCache.put(key, value);
  }

  /**
   * @return endToTimeLapseCache
   */
  public Map<InteractionFragment, TimeLapse> getInteractionFragmentToTimeLapseCache() {
    return interactionFragmentToTimeLapseCache;
  }

  /**
   * @return if refresh cache is enabled
   */
  public boolean isRefreshCacheEnabled() {
    return isCacheEnabled;
  }

  /**
   * Enable refresh cache.
   */
  public void enableRefreshCache() {
    isCacheEnabled = true;
  }

  /**
   * Disable refresh cache.
   */
  public void disableRefreshCache() {
    isCacheEnabled = false;
  }

  /**
   * Compute Scenario structure from InstanceRole if needed and put it in cache.
   * 
   * @param instanceRole
   *          InstanceRole
   * @return List<SemanticCandidateContext>
   */
  public List<SemanticCandidateContext> getSemanticCandidateContexts(InstanceRole instanceRole) {
    List<SemanticCandidateContext> semanticCandidateContexts = getInstanceRoleToSemanticCandidateContextCache(
        instanceRole);
    if (semanticCandidateContexts == null) {
      semanticCandidateContexts = computeInstanceRoleSemanticCandidateContextStructure(instanceRole);
      if (isRefreshCacheEnabled()) {
        putInstanceRoleToSemanticCandidateContextsCache(instanceRole, semanticCandidateContexts);
      }
    }
    return semanticCandidateContexts;
  }

  /**
   * Compute structure for combined fragment and operand if needed and put it in cache.
   *
   * @param operand
   *          InteractionOperand
   */
  public OperandContext getOperandContext(InteractionOperand operand) {
    OperandContext operandContext = getOperandToOperandContextCache(operand);
    if (operandContext == null) {
      Scenario scenario = SequenceDiagramServices.getScenario(operand);
      Map<InteractionOperand, OperandContext> operandToOperandContextStructure = computeOperandToOperandContextStructure(
          scenario);
      operandContext = operandToOperandContextStructure.get(operand);
      if (isRefreshCacheEnabled()) {
        operandToOperandContextCache = operandToOperandContextStructure;
      }
    }
    return operandContext;
  }

  /**
   * Get semantic candidate on element. (InstanceRole -> Executions + StateFragment, Execution -> Execution children +
   * StateFragment)
   * 
   * @param instanceRole
   *          InstanceRole
   * @param element
   *          EObject
   * @return Collection<EObject>
   */
  private Collection<EObject> getSemanticCandidates(InstanceRole instanceRole, EObject element) {
    Collection<EObject> semanticCandidates = getSemanticCandidatesFromCache(element);
    if (semanticCandidates == null) {
      // compute result from SemanticCandidateContext structure
      List<SemanticCandidateContext> semanticCandidateContexts = getSemanticCandidateContexts(instanceRole);

      semanticCandidates = semanticCandidateContexts.stream().filter(scc -> element.equals(scc.getParent()))
          .map(SemanticCandidateContext::getElement).filter(candidate -> candidate != element).distinct()
          .collect(Collectors.toList());
      if (isRefreshCacheEnabled()) {
        putSemanticCandidatesInCache(element, semanticCandidates);
      }
    }
    return semanticCandidates;
  }

  /**
   * Get Execution semantic candidates on element.
   * 
   * @param instanceRole
   *          InstanceRole
   * @param element
   *          EObject
   * @return Collection<EObject>
   */
  public Collection<Execution> getExecutionSemanticCandidates(InstanceRole instanceRole, EObject element) {
    return getSemanticCandidates(instanceRole, element).stream().filter(Execution.class::isInstance)
        .map(Execution.class::cast).collect(Collectors.toList());
  }

  /**
   * Get StateFragment semantic candidates on element.
   * 
   * @param element
   *          EObject
   * @param instanceRole
   *          InstanceRole
   * @return Collection<EObject>
   */
  public List<StateFragment> getStateFragmentSemanticCandidates(InstanceRole instanceRole, EObject element) {
    return getSemanticCandidates(instanceRole, element).stream().filter(StateFragment.class::isInstance)
        .map(StateFragment.class::cast).collect(Collectors.toList());
  }

  /**
   * Compute SemanticCandidateContext structure for InstanceRole
   * 
   * @param instanceRole
   *          InstanceRole
   * @return List<SemanticCandidateContext>
   */
  private List<SemanticCandidateContext> computeInstanceRoleSemanticCandidateContextStructure(
      InstanceRole instanceRole) {
    if (instanceRole == null || !(instanceRole.eContainer() instanceof Scenario)) {
      return Collections.emptyList();
    }
    Scenario scenario = SequenceDiagramServices.getScenario(instanceRole);

    // initialize ancestors
    Deque<CapellaElement> ancestors = new ArrayDeque<>();
    ancestors.push(instanceRole);
    List<SemanticCandidateContext> result = new ArrayList<>();

    // Cache missing info from M2 :
    // - cache Execution.start -> Execution and Execution.finish -> Execution when the end is a MessageEnd
    // - cache StateFragment.start -> InteractionState and StateFragment.finish -> InteractionState
    // (ExecutionEnd has a getExecution() method)(ExecutionEnd has a getExecution() method)
    computeTimeLapseStartAndEndCache(scenario);

    // compute Execution/StateFragment and InteractionFragment structure
    Stream<InteractionFragment> interactionFragments = scenario.getOwnedInteractionFragments().stream()
        .filter(frag -> frag instanceof AbstractEnd || frag instanceof InteractionState);
    interactionFragments.forEachOrdered(end -> {
      InstanceRole coveredInstanceRole = getCoveredInstanceRole(end);
      if (coveredInstanceRole != null && coveredInstanceRole.equals(instanceRole)) {
        TimeLapse timeLapse = getTimeLapseFromCache(end);

        // Execution End case
        if (end instanceof ExecutionEnd) {
          visit(ancestors, timeLapse, (ExecutionEnd) end, result);
        }

        // Interaction State case
        if (end instanceof InteractionState) {
          visit(ancestors, timeLapse, (InteractionState) end, result);
        }

        // Message End case
        if (end instanceof MessageEnd) {
          visit(ancestors, timeLapse, (MessageEnd) end, result);
        }

      }
    });
    return result;
  }

  /**
   * Compute combined fragments and operands structure for Scenario
   * 
   * @param scenario
   *          Scenario
   * @return Map<InteractionOperand, OperandContext>
   */
  private Map<InteractionOperand, OperandContext> computeOperandToOperandContextStructure(Scenario scenario) {
    // initialize map
    Map<InteractionOperand, OperandContext> operandToOperandContext = new ConcurrentHashMap<>();

    // Compute combined fragment and InteractionOperand structure.
    List<CombinedFragment> combinedFragments = scenario.getOwnedTimeLapses().stream()
        .filter(CombinedFragment.class::isInstance).map(CombinedFragment.class::cast).collect(Collectors.toList());
    combinedFragments.forEach(combinedFragment -> {
      combinedFragment.getReferencedOperands().forEach(op -> {
        operandToOperandContext.put(op, new OperandContext(combinedFragment));
      });
    });

    // Compute InteractionOperand -> InteractionOperand End
    Map<CombinedFragment, LinkedList<InteractionOperand>> combinedFragmentToSortedReferencedOperands = new ConcurrentHashMap<>();
    Stream<InteractionFragment> interactionFragments = scenario.getOwnedInteractionFragments().stream()
        .filter(InteractionOperand.class::isInstance);
    interactionFragments.forEachOrdered(end -> {
      // Interaction Operand case
      visit(operandToOperandContext, (InteractionOperand) end, combinedFragmentToSortedReferencedOperands);
    });

    // Add operand end to operand context
    combinedFragmentToSortedReferencedOperands.values().forEach(sortedOperands -> {
      sortedOperands.forEach(operand -> {
        computeOperandEnds(operandToOperandContext, sortedOperands, operand);
      });
    });

    return operandToOperandContext;
  }

  /**
   * Compute operand end for operands.
   * 
   * @param operandToOperandContext
   *          Map<InteractionOperand, OperandContext>
   * @param sortedOperands
   *          LinkedList<InteractionOperand>
   * @param operand
   *          InteractionOperand
   */
  private void computeOperandEnds(Map<InteractionOperand, OperandContext> operandToOperandContext,
      LinkedList<InteractionOperand> sortedOperands, InteractionOperand operand) {
    OperandContext operandContext = operandToOperandContext.get(operand);
    int currentOperandIndex = sortedOperands.indexOf(operand);
    int nextOperandIndex = currentOperandIndex + 1;
    if (nextOperandIndex < sortedOperands.size()) {
      operandContext.setOperandEnd(sortedOperands.get(nextOperandIndex));
    } else {
      operandContext.setOperandEnd(operandContext.getCombinedFragment().getFinish());
    }
  }

  /**
   * Compute operand context for InteractionOperand.
   * 
   * @param operandToOperandContext
   *          Map<InteractionOperand, OperandContext>
   * @param end
   *          InteractionOperand
   * @param combinedFragmentToSortedReferencedOperands
   *          Map<CombinedFragment, LinkedList<InteractionOperand>>
   */
  private void visit(Map<InteractionOperand, OperandContext> operandToOperandContext, InteractionOperand end,
      Map<CombinedFragment, LinkedList<InteractionOperand>> combinedFragmentToSortedReferencedOperands) {
    OperandContext operandContext = operandToOperandContext.get(end);
    LinkedList<InteractionOperand> sortedOperands = combinedFragmentToSortedReferencedOperands
        .get(operandContext.getCombinedFragment());
    if (sortedOperands == null) {
      sortedOperands = new LinkedList<InteractionOperand>();
    }
    sortedOperands.addLast(end);
    combinedFragmentToSortedReferencedOperands.put(operandContext.getCombinedFragment(), sortedOperands);
  }

  /**
   * Compute Semantic candidate Structure for MessageEnd.
   * 
   * @param ancestors
   *          Deque<EObject>
   * @param result
   *          List<SemanticCandidateContext>
   * @param end
   *          MessageEnd
   */
  private void visit(Deque<CapellaElement> ancestors, TimeLapse timeLapse, MessageEnd end,
      List<SemanticCandidateContext> result) {

    // Execution start / SyncCall and ASyncCall
    if (timeLapse instanceof Execution && ((Execution) timeLapse).getStart() == end) {
      result.add(new SemanticCandidateContext(ancestors.peek(), timeLapse, true, ancestors.size() + 1));
      ancestors.push(timeLapse);
    }

    // Message - SemanticCandidateContext to directly know which execution/instanceRole DNode must be source/target of
    // the message's DEdge
    // Handle all cases :
    // - sending end of the main branch (no execution found in interactionFragmentToTimeLapseCache)
    // - receiving end of the main branch (start of execution found in interactionFragmentToTimeLapseCache cache and pushed on the ancestor
    // stack)
    // - sending end of the return branch (end of execution found in interactionFragmentToTimeLapseCache cache, not yet
    // removed from the ancestor cache)
    // - receiving end of the return branch (no execution found in interactionFragmentToTimeLapseCache cache).
    SequenceMessage message = end.getMessage();
    if (message != null) {
      result.add(new SemanticCandidateContext(ancestors.peek(), message, end.equals(message.getSendingEnd()),
          ancestors.size()));
    }

    // Execution with return branch end / SyncCall
    if (timeLapse instanceof Execution && ((Execution) timeLapse).getFinish() == end) {
      ancestors.pop();
      result.add(new SemanticCandidateContext(ancestors.peek(), timeLapse, false, ancestors.size() + 1));
    }
    
    // @formatter:off
    //  The Following diagram would result in
    //
    //     | IR1 |   | IR2 ]   | IR3 ] 
    //        |         |         |
    //        |         |         |
    //        |-------> -         |   e1  m1
    //        |        | |------> -   e2  m2
    //        |        | |       | |
    //        |        | - <-----| |  e3  m3
    //        |        || |      | |
    //        |        || |      | |
    //        |        | - ----->| |      return_m3
    //        |        | |       | |
    //        |<------- -        | |      return_m1
    //        |         |         - 
    //        |         |         | 

    // would result the following structures:

    // For IR1:
    // SemanticCandidateContext(IR1, m1, true, 1)
    // SemanticCandidateContext(IR1, return_m1, false, 1)
    //
    // For IR2:
    // SemanticCandidateContext(IR2, e1, true, 2)
    // SemanticCandidateContext(e1, m1, false, 2)
    // SemanticCandidateContext(e1, m2, true, 2)
    // SemanticCandidateContext(e1, e3, true, 3)
    // SemanticCandidateContext(e3, m3, false, 3)
    // SemanticCandidateContext(e3, return_m3, true, 3)
    // SemanticCandidateContext(e1, e3, false, 3)
    // SemanticCandidateContext(e1, return_m1 true, 2)
    // SemanticCandidateContext(IR2, e1, false, 2)
    //
    // For IR3:
    // SemanticCandidateContext(IR3, e3, true, 2)
    // SemanticCandidateContext(e3, m2, false, 2)
    // SemanticCandidateContext(IR3, e3, false, 2)
    // @formatter:on
  }

  /**
   * Compute SemanticCandidateContext for InteractionState.
   * 
   * @param ancestors
   *          Deque<EObject>
   * @param result
   *          List<SemanticCandidateContext>
   * @param end
   *          InteractionState
   */
  private void visit(Deque<CapellaElement> ancestors, TimeLapse timeLapse, InteractionState end,
      List<SemanticCandidateContext> result) {
    if (timeLapse instanceof StateFragment && ((StateFragment) timeLapse).getStart() == end) {
      result.add(new SemanticCandidateContext(ancestors.peek(), timeLapse, true, ancestors.size() + 1));
    }

    if (timeLapse instanceof StateFragment && ((StateFragment) timeLapse).getFinish() == end) {
      result.add(new SemanticCandidateContext(ancestors.peek(), timeLapse, false, ancestors.size() + 1));
    }
  }

  /**
   * Compute SemanticCandidateContext for ExecutionEnd.
   * 
   * @param ancestors
   *          Deque<EObject>
   * @param result
   *          List<SemanticCandidateContext>
   * @param end
   *          ExecutionEnd
   */
  private void visit(Deque<CapellaElement> ancestors, TimeLapse timeLapse, ExecutionEnd end,
      List<SemanticCandidateContext> result) {
    // Should not happen in current implementation of Capella Sequence diagrams
    if (timeLapse != null && timeLapse.getStart() == end) {
      result.add(new SemanticCandidateContext(ancestors.peek(), timeLapse, true, ancestors.size() + 1));
      ancestors.push(timeLapse);
    }

    // Execution without return branch end / ASyncCall
    if (timeLapse != null && timeLapse.getFinish() == end) {
      ancestors.pop();
      result.add(new SemanticCandidateContext(ancestors.peek(), timeLapse, false, ancestors.size() + 1));
    }
  }

  /**
   * Return covered instance role.
   * 
   * @param end
   *          InteractionFragment
   * @return covered instance role.
   */
  private InstanceRole getCoveredInstanceRole(InteractionFragment end) {
    InstanceRole covered = null;
    if (end instanceof AbstractEnd) {
      covered = getInteractionCache(AbstractEnd::getCovered, (AbstractEnd) end);
    } else if (end instanceof InteractionState) {
      covered = getInteractionCache(InteractionState::getCovered, (InteractionState) end);
    }
    return covered;
  }

  /**
   * Compute cache for TimeLapse start and finish ends.
   * 
   * @param scenario
   *          Scenario
   */
  private void computeTimeLapseStartAndEndCache(Scenario scenario) {
    // cache Execution.start -> Execution and Execution.finish -> Execution
    // scan timelapse only one time
    if (getInteractionFragmentToTimeLapseCache().isEmpty()) {
      Stream<TimeLapse> timeLapses = scenario.getOwnedTimeLapses().stream()
          .filter(tl -> !(tl instanceof AbstractFragment));
      timeLapses.forEach(e -> {
        // Keep Interaction to TimeLapse info in cache
        // M2 allows to retrieve an Execution from an ExecutionEnd (execution.getFinish() when there is no
        // return branch : ASyncCall)
        // but not an Execution from a MessagEnd (SyncCall and ASyncCall) or a StateFragment from an
        // InteractionState.
        putTimeLapseInCache(e.getStart(), e);
        putTimeLapseInCache(e.getFinish(), e);
      });
    }
  }

  /**
   * Helper class to keep track of who "contains" who depending on the vsm semantic candidate expression.
   * 
   * @author pcdavid
   */
  public static final class SemanticCandidateContext {
    private final EObject parent;

    private final boolean start;

    private final EObject element;

    private final int level;

    public SemanticCandidateContext(EObject parent, EObject element, boolean start, int level) {
      this.parent = parent;
      this.element = element;
      this.level = level;
      this.start = start;
    }

    public boolean isStart() {
      return start;
    }

    public EObject getParent() {
      return parent;
    }

    public EObject getElement() {
      return element;
    }

    public int getLevel() {
      return level;
    }

    @Override
    public String toString() {
      return String.format("%02d\t%s\t%s", getLevel(), element, parent);
    }
  }

  public static final class OperandContext {

    private InteractionOperand operand;

    private CombinedFragment combinedFragment;

    private InteractionFragment operandEnd;

    public OperandContext(CombinedFragment combinedFragment) {
      this.combinedFragment = combinedFragment;
      this.operandEnd = null;
      this.operand = null;
    }

    public OperandContext(InteractionOperand operand, CombinedFragment combinedFragment,
        InteractionFragment operandEnd) {
      this.operand = operand;
      this.combinedFragment = combinedFragment;
      this.operandEnd = operandEnd;
    }

    public InteractionOperand getOperand() {
      return operand;
    }

    public CombinedFragment getCombinedFragment() {
      return combinedFragment;
    }

    public InteractionFragment getOperandEnd() {
      return operandEnd;
    }

    public void setOperandEnd(InteractionFragment operandEnd) {
      this.operandEnd = operandEnd;
    }

    @Override
    public String toString() {
      return String.format("%02d\t%s\t%s", combinedFragment, operandEnd);
    }

  }

}
