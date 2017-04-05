/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.statemachine.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.security.auth.login.Configuration;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.MsPackage;

public final class StateMachineUtils {

  /*
   * Prevent instantiation
   */
  private StateMachineUtils() {
  }

  /**
   * Return a {@link Region} of a given {@link AbstractState} or <code>null</code>.
   * @param abstractState the given {@link AbstractState}
   * @return the {@link Region} or <code>null</code>
   */
  public static Region getRegion(AbstractState abstractState) {
    if (abstractState.eContainer() instanceof Region) {
      return (Region) abstractState.eContainer();
    }
    return null;
  }

  /**
   * Returns a list of all, direct and indirect, state machine of this {@link LogicalComponent}.
   * @param logicalComponentRoot the component
   * @return the list of state machines
   */
  public static EList<StateMachine> getAllStateMachines(LogicalComponent logicalComponentRoot) {
    EList<StateMachine> allStateMachines = new BasicEList<StateMachine>();
    allStateMachines.addAll(logicalComponentRoot.getOwnedStateMachines());
    if (!logicalComponentRoot.getSubLogicalComponents().isEmpty()) {
      for (LogicalComponent logicalComponent : logicalComponentRoot.getSubLogicalComponents()) {
        allStateMachines.addAll(getAllStateMachines(logicalComponent));
      }
    }
    return allStateMachines;
  }

  /**
   * Returns a list of all, direct and indirect, state machine of this {@link PhysicalComponent}.
   * @param physicalComponentRoot the component
   * @return the list of state machines
   */
  public static EList<StateMachine> getAllStateMachines(PhysicalComponent physicalComponentRoot) {
    EList<StateMachine> allStateMachines = new BasicEList<StateMachine>();
    allStateMachines.addAll(physicalComponentRoot.getOwnedStateMachines());
    if (!physicalComponentRoot.getSubPhysicalComponents().isEmpty()) {
      for (PhysicalComponent physicalComponent : physicalComponentRoot.getSubPhysicalComponents()) {
        allStateMachines.addAll(getAllStateMachines(physicalComponent));
      }
    }
    return allStateMachines;
  }

  /**
   * Returns a list of all, direct and indirect, {@link State}s of this {@link StateMachine}.
   * @param stateMachine the state machine
   * @return the list of {@link State}s
   */
  public static EList<State> getAllStates(StateMachine stateMachine) {
    EList<State> result = new BasicEList<State>();
    for (Region subRegion : stateMachine.getOwnedRegions()) {
      result.addAll(getAllStates(subRegion));
    }
    return result;
  }

  /**
   * * Returns a list of all, direct and indirect, {@link State}s of this {@link Region}.
   * @param region the region
   * @return the list of {@link State}s
   */
  public static EList<State> getAllStates(Region region) {
    EList<State> result = new BasicEList<State>();
    for (AbstractState abstractState : region.getOwnedStates()) {
      if (abstractState instanceof State) {
        State state = (State) abstractState;
        result.add(state);
        for (Region subRegion : state.getOwnedRegions()) {
          result.addAll(getAllStates(subRegion));
        }
      }
    }
    return result;
  }

  /**
   * Returns a list of all, direct and indirect, {@link Mode}s of this {@link StateMachine}.
   * @param stateMachine the state machine
   * @return the list of {@link Mode}s
   */
  public static EList<Mode> getAllModes(StateMachine stateMachine) {
    EList<Mode> result = new BasicEList<Mode>();
    for (Region subRegion : stateMachine.getOwnedRegions()) {
      result.addAll(getAllModes(subRegion));
    }
    return result;
  }

  /**
   * * Returns a list of all, direct and indirect, {@link Mode}s of this {@link Region}.
   * @param region the region
   * @return the list of {@link Mode}s
   */
  public static EList<Mode> getAllModes(Region region) {
    EList<Mode> result = new BasicEList<Mode>();
    for (AbstractState abstractState : region.getOwnedStates()) {
      if (abstractState instanceof Mode) {
        Mode state = (Mode) abstractState;
        result.add(state);
        for (Region subRegion : state.getOwnedRegions()) {
          result.addAll(getAllModes(subRegion));
        }
      }
    }
    return result;
  }

  /**
   * 
   */
  /**
   * Return a list of the higher {@link State}s of a given {@link State}.
   * @param state the given {@link State}
   * @return the list of the higher {@link State}s
   */
  public static EList<State> getHigherStates(State state) {
    EList<State> result = new BasicEList<State>();
    if (state.eContainer().eContainer() instanceof State) {
      result.add((State) state.eContainer().eContainer());
      result.addAll(getHigherStates((State) state.eContainer().eContainer()));
    }
    return result;
  }

  /**
   * Return the {@link StateMachine} of a given {@link State}.
   * @param state the given {@link State}
   * @return the {@link StateMachine}
   */
  public static StateMachine getStateMachine(State state) {
    if (state.eContainer().eContainer() instanceof StateMachine) {
      return (StateMachine) state.eContainer().eContainer();
    } else if (state.eContainer().eContainer() instanceof State) {
      return getStateMachine((State) state.eContainer().eContainer());
    }
    return null;
  }

  /**
   * Return all {@link StateMachine}s container of one or more {@link State}. of a given list.
   * @param states a given list of {@link State}s
   * @return a list of {@link StateMachine}
   */
  public static EList<StateMachine> getAllStateMachines(EList<State> states) {
    EList<StateMachine> stateMachines = new BasicEList<StateMachine>();

    for (State state : states) {
      StateMachine stateMachine = getStateMachine(state);
      if (!stateMachines.contains(stateMachine)) {
        stateMachines.add(stateMachine);
      }
    }
    return stateMachines;
  }

  /**
   * Return the {@link Component} container a given {@link StateMachine}.
   * @param stateMachines a given list of {@link StateMachine}s
   * @return the {@link Component}
   */
  public static Component getComponent(StateMachine stateMachine) {
    if (stateMachine.eContainer() instanceof Component) {
      return (Component) stateMachine.eContainer();
    }
    return null;
  }

  /**
   * Return all {@link SystemComponent}s container of one or more {@link StateMachine}.
   * @param stateMachines a given list of {@link StateMachine}s
   * @return a list of {@link SystemComponent}
   */
  public static EList<SystemComponent> getSystemComponents(EList<StateMachine> stateMachines) {
    EList<SystemComponent> systemComponents = new BasicEList<SystemComponent>();
    for (StateMachine stateMachine : stateMachines) {
      if (stateMachine.eContainer() instanceof SystemComponent && !systemComponents.contains(stateMachine.eContainer())) {
        systemComponents.add((SystemComponent) stateMachine.eContainer());
      }
    }
    return systemComponents;
  }

  /**
   * Test if a {@link AbstractFunction} is available in one or more M/S of the {@link StateMachine}
   * @param function the {@link AbstractFunction}
   * @param stateMachine the {@link StateMachine}
   * @return true if the {@link AbstractFunction} is available in one or M/S, else false.
   */
  public static boolean isAvailableIn(AbstractFunction function, StateMachine stateMachine) {
    EList<State> stateMachineElements = StateMachineUtils.getAllStates(stateMachine);
    EList<State> availableInStates = new BasicEList<State>();// function.getAvailableInStates();
    for (State state : availableInStates) {
      if (stateMachineElements.contains(state)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Compute the inclusion level of a given {@link State}.
   * @param state the {@link State}
   * @return the level.
   */
  public static int getLevel(State state) {
    int level = 1;
    if (StateMachineUtils.isContainedInState(state)) {
      return level + getLevel((State) state.eContainer().eContainer());
    }
    return level;
  }

  /**
   * Test if a given {@link State} is included in another {@link State} or not.
   * @param state the given {@link State}
   * @return <code>True</code> if the given {@link State} is included in a {@link State}, else <code>False</code>.
   */
  public static boolean isContainedInState(State state) {
    if (state.eContainer().eContainer() instanceof State) {
      return true;
    }
    return false;
  }

  /**
   * Return a list of all {@link StateTransition} link a given two {@link AbstractState}s
   * @param state1 {@link AbstractState} one
   * @param state2 {@link AbstractState} two
   * @return list of {@link StateTransition}
   */
  public static EList<StateTransition> getTransitionBetweenStates(AbstractState state1, AbstractState state2) {
    EList<StateTransition> transitions = new BasicEList<StateTransition>();
    for (StateTransition incomingTransition : state1.getIncoming()) {
      if (incomingTransition.getSource().equals(state2)) {
        transitions.add(incomingTransition);
      }
    }
    for (StateTransition outgoingTransition : state1.getOutgoing()) {
      if (outgoingTransition.getTarget().equals(state2)) {
        transitions.add(outgoingTransition);
      }
    }
    return transitions;
  }

  /**
   * Verify if a path exists from {@link AbstractState} 1 to {@link AbstractState} 2.
   * @param state1 the {@link AbstractState} 1
   * @param state2 the {@link AbstractState} 2
   * @return <code>true</code> if path exist, else <code>false</code>
   */
  public static boolean isPathFromState1ToState2(AbstractState state1, AbstractState state2) {
    StateMachineUtils stateMachineUtils = new StateMachineUtils();
    TargetStates targetStates = stateMachineUtils.new TargetStates();
    if (targetStates.getAllTargetStates(state1).contains(state2)) {
      return true;
    }
    return false;
  }

  public class TargetStates {

    private Set<AbstractState> targetStates = new HashSet<AbstractState>();

    /**
     * Return a list of all reachable {@link AbstractState}s from a given {@link AbstractState}.
     * @param state the given {@link AbstractState}
     * @return list of all reachable {@link AbstractState}s
     */
    public Set<AbstractState> getAllTargetStates(AbstractState state) {
      targetStates.clear();
      return getAllTargetStatesOperation(state);
    }

    /**
     * Return a list of all reachable {@link AbstractState}s from a given {@link AbstractState}.
     * @param state the given {@link AbstractState}
     * @return list of all reachable {@link AbstractState}s
     */
    private Set<AbstractState> getAllTargetStatesOperation(AbstractState state) {
      Set<AbstractState> directTargetStates = new HashSet<AbstractState>();

      for (StateTransition outgoingTrasition : state.getOutgoing()) {
        if (!targetStates.contains(outgoingTrasition.getTarget())) {
          directTargetStates.add(outgoingTrasition.getTarget());
        }
      }
      targetStates.addAll(directTargetStates);
      for (AbstractState abstractState : directTargetStates) {
        targetStates.addAll(getAllTargetStatesOperation(abstractState));
      }
      return targetStates;
    }
  }

  public class Paths {

    private Set<StateTransition> outgoingStateTransitions = new HashSet<StateTransition>();
    private Set<StateTransition> incomingStateTransitions = new HashSet<StateTransition>();

    /**
     * Return a list of all reachable {@link StateTransition}s from a given {@link AbstractState}.
     * @param state the given {@link AbstractState}
     * @return list of all reachable {@link StateTransition}s
     */
    public Set<StateTransition> getAllOutgoingStateTransitions(AbstractState state) {
      outgoingStateTransitions.clear();
      return getAllOutgoingStateTransitionsOperation(state);
    }

    /**
     * Return a list of all reachable {@link StateTransition}s from a given {@link AbstractState}.
     * @param state the given {@link AbstractState}
     * @return list of all reachable {@link StateTransition}s
     */
    private Set<StateTransition> getAllOutgoingStateTransitionsOperation(AbstractState state) {

      Set<StateTransition> directOutgoingStatesTransition = new HashSet<StateTransition>();

      for (StateTransition outgoingTrasition : state.getOutgoing()) {
        if (!outgoingStateTransitions.contains(outgoingTrasition)) {
          directOutgoingStatesTransition.add(outgoingTrasition);
        }
      }

      outgoingStateTransitions.addAll(directOutgoingStatesTransition);
      for (StateTransition outgoingTrasition : directOutgoingStatesTransition) {
        outgoingStateTransitions.addAll(getAllOutgoingStateTransitionsOperation(outgoingTrasition.getTarget()));
      }
      return outgoingStateTransitions;
    }

    /**
     * Return a list of all {@link StateTransition}s from which a given {@link AbstractState} is reachable.
     * @param state the given {@link AbstractState}
     * @return list of {@link StateTransition}s
     */
    public Set<StateTransition> getAllIncomingStateTransitions(AbstractState state) {
      incomingStateTransitions.clear();
      return getAllIncomingStateTransitionsOperation(state);
    }

    /**
     * Return a list of all {@link StateTransition}s from which a given {@link AbstractState} is reachable.
     * @param state the given {@link AbstractState}
     * @return list of {@link StateTransition}s
     */
    private Set<StateTransition> getAllIncomingStateTransitionsOperation(AbstractState state) {

      Set<StateTransition> directIncomingStatesTransition = new HashSet<StateTransition>();

      for (StateTransition incomingTrasition : state.getIncoming()) {
        if (!incomingStateTransitions.contains(incomingTrasition)) {
          directIncomingStatesTransition.add(incomingTrasition);
        }
      }

      incomingStateTransitions.addAll(directIncomingStatesTransition);
      for (StateTransition incomingTrasition : directIncomingStatesTransition) {
        incomingStateTransitions.addAll(getAllIncomingStateTransitionsOperation(incomingTrasition.getSource()));
      }
      return incomingStateTransitions;
    }

    /**
     * Return a list of all {@link StateTransition}s involved in all paths between two given given {@link AbstractState}s.
     * @param beginningState the beginning {@link AbstractState} of the path.
     * @param endingState the ending {@link AbstractState} of the path.
     * @return list of all involved {@link StateTransition}s in all paths
     */
    public Set<StateTransition> getStateTransitionsInPaths(AbstractState beginningState, AbstractState endingState) {
      Set<StateTransition> stateTransitions = new HashSet<StateTransition>();

      stateTransitions.addAll(getAllOutgoingStateTransitions(beginningState));
      stateTransitions.retainAll(getAllIncomingStateTransitions(endingState));

      return stateTransitions;
    }
  }

  /**
   * Returns a list of common {@link Configuration}s between a given {@link Mode}s .
   * @param modes the {@link Mode}s
   * @return a list of common {@link Configuration}s
   */
  public static EList<CSConfiguration> getCommonConfigurations(EList<Mode> modes) {
    EList<CSConfiguration> configurations = new BasicEList<CSConfiguration>();
    if (!modes.isEmpty()) {
      configurations = new BasicEList<CSConfiguration>(getConfigurations(modes.get(0)));
      for (Mode mode : modes) {
        configurations.retainAll(getConfigurations(mode));
      }
    }
    return configurations;
  }

  
  public static Collection<CSConfiguration> getConfigurations(AbstractState state){
    SemanticEditingDomain domain = (SemanticEditingDomain) TransactionUtil.getEditingDomain(state);
    if (domain == null){
      return Collections.emptyList();
    }

    Collection<CSConfiguration> result = new ArrayList<CSConfiguration>();
    for (Setting setting : domain.getCrossReferencer().getInverseReferences(state, MsPackage.Literals.CS_CONFIGURATION__SUPPORTED_MODES, true)){
      result.add((CSConfiguration) setting.getEObject());
    }
    return result;
  }
  
}
