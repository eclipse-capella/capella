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
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.Configuration;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.common.statemachine.validation.StateMachineUtils;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.vp.ms.CSConfiguration;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public final class FunctionalAnalysisUtils {

  /*
   * Prevent instantiation
   */
  private FunctionalAnalysisUtils() {
  }

  /**
   * Return a list of involved {@link Component}s in a given {@link FunctionalChain}.
   * @param functionalChain the given {@link FunctionalChain}
   * @return the list of the involved {@link Component}s.
   */
  public static EList<Component> getInvolvedComponents(FunctionalChain functionalChain) {
    EList<Component> involvedComponents = new BasicEList<Component>();
    for (AbstractFunction abstractFunction : functionalChain.getInvolvedFunctions()) {
      if (!abstractFunction.getAllocationBlocks().isEmpty() && abstractFunction.getAllocationBlocks().get(0) instanceof Component) {
        if (!involvedComponents.contains(abstractFunction.getAllocationBlocks().get(0))) {
          involvedComponents.add((Component) abstractFunction.getAllocationBlocks().get(0));
        }
      }
    }
    return involvedComponents;
  }

  /**
   * Return a list of involved {@link Component}s in a given {@link FunctionalChain}.
   * @param component the given {@link FunctionalChain}
   * @return the list of the involved {@link Component}s.
   */
  public static EList<FunctionalChain> getFunctionalChainInvolvers(Component component) {
    EList<FunctionalChain> fcInvolvers = new BasicEList<FunctionalChain>();
    for (AbstractFunction abstractFunction : component.getAllocatedFunctions()) {
      for (FunctionalChain fc : abstractFunction.getInvolvingFunctionalChains()) {
        if (!fcInvolvers.contains(fc)) {
          fcInvolvers.add(fc);
        }
      }
    }
    return fcInvolvers;
  }

  /**
   * Returns a list of all, direct and indirect, {@link State} of all direct {@link StateMachine}s of a given {@link Component}.
   * @param component given {@link Component}
   * @return list of all, direct and indirect, {@link State}s.
   */
  public static EList<State> getAllStates(Component component) {
    EList<State> allStates = new BasicEList<State>();
    for (StateMachine stateMachine : component.getOwnedStateMachines()) {
      allStates.addAll(StateMachineUtils.getAllStates(stateMachine));
    }
    return allStates;
  }

  /**
   * Returns a list of common {@link AbstractFunction}s between a given {@link Component} and a given {@link FunctionalChain}.
   * @param component given {@link Component}
   * @return list of common {@link AbstractFunction}s.
   */
  public static EList<AbstractFunction> getCommonFunctions(Component component, FunctionalChain functionalChain) {

    EList<AbstractFunction> commonFunctions = new BasicEList<AbstractFunction>(component.getAllocatedFunctions());
    EList<AbstractFunction> allocatedFunctionsToComponent = new BasicEList<AbstractFunction>(component.getAllocatedFunctions());
    allocatedFunctionsToComponent.removeAll(functionalChain.getInvolvedFunctions());
    commonFunctions.removeAll(allocatedFunctionsToComponent);

    return commonFunctions;
  }

  /**
   * Find the combinations of {@link State}, {@link Component}, {@link Configuration} and {@link AbstractFunction} where the {@link FunctionalChain} is not
   * assured.
   * @param fc the {@link FunctionalChain}
   * @param state the {@link State}
   * @param component the {@link Component}
   * @param commonFunctions the common functions between the {@link Component} and the {@link FunctionalChain}
   * @return All combinations where the {@link FunctionalChain} is not assured
   */
  public static List<FunctionalChainAnalysisResult> analyzeFC_State(FunctionalChain fc, State state, Component component,
      EList<AbstractFunction> commonFunctions) {
    List<FunctionalChainAnalysisResult> fcAnalysisResults = new ArrayList<FunctionalChainAnalysisResult>();
    if (!StateMachineUtils.getConfigurations(state).isEmpty()) {
      for (CSConfiguration configuration : StateMachineUtils.getConfigurations(state)) {
        EList<AbstractFunction> missingFunctions = new BasicEList<AbstractFunction>(commonFunctions);
        missingFunctions.removeAll(configuration.getFunctions());
        if (!configuration.getFunctions().containsAll(commonFunctions)) {
          FunctionalChainAnalysisResult fcAnalysisResult = new FunctionalChainAnalysisResult(fc, state, component, configuration, missingFunctions);
          fcAnalysisResults.add(fcAnalysisResult);
        }
      }
    } else if (!Collections2.filter(component.getOwnedExtensions(), Predicates.instanceOf(CSConfiguration.class)).isEmpty()) {
      FunctionalChainAnalysisResult fcAnalysisResult = new FunctionalChainAnalysisResult(fc, state, component, null, null);
      fcAnalysisResults.add(fcAnalysisResult);
    }
    return fcAnalysisResults;
  }

}