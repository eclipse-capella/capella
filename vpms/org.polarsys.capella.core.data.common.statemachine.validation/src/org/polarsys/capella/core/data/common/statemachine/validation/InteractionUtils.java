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
import java.util.List;
import java.util.Stack;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.sirius.analysis.SequenceDiagramServices;

public final class InteractionUtils {

  /*
   * Prevent instantiation
   */
  private InteractionUtils() {
  }

  /**
   * Return the first element ({@link AbstractFunction}/{@link AbstractState}) whose execution is occurring in a chronologically ordered list of a
   * {@link InstanceRole} elements.
   * @param orderedElementsOfInstanceRole the list.
   * @return the first element ({@link AbstractFunction}/{@link AbstractState} ) whose execution is occurring
   */
  public static EObject getFirstElementInExecution(List<EObject> orderedElementsOfInstanceRole) {
    if (!orderedElementsOfInstanceRole.isEmpty() && orderedElementsOfInstanceRole.get(0) instanceof StateFragment) {
      StateFragment stateFragment = (StateFragment) orderedElementsOfInstanceRole.get(0);
      if (stateFragment.getRelatedAbstractFunction() != null) {
        return stateFragment.getRelatedAbstractFunction();
      }
      if (stateFragment.getRelatedAbstractState() != null) {
        return stateFragment.getRelatedAbstractState();
      }
    }
    return null;
  }

  /**
   * Return a chronologically ordered list of all elements ( {@link AbstractFunction}/{@link AbstractState}) whose execution is occurring in a chronologically
   * ordered list of a {@link InstanceRole} elements.
   * @param orderedElementsOfInstanceRole the list.
   * @return the list of elements ({@link AbstractFunction}/ {@link AbstractState} ) whose execution is occurring
   */
  public static List<EObject> getAllElementsInExecution(List<EObject> orderedElementsOfInstanceRole) {
    List<EObject> elementsInExecution = new BasicEList<EObject>();
    for (EObject elementOfInstanceRole : orderedElementsOfInstanceRole) {
      if (elementOfInstanceRole instanceof StateFragment) {
        StateFragment stateFragment = (StateFragment) elementOfInstanceRole;
        if (stateFragment.getRelatedAbstractFunction() != null) {
          elementsInExecution.add(stateFragment.getRelatedAbstractFunction());
        } else if (stateFragment.getRelatedAbstractState() != null) {
          elementsInExecution.add(stateFragment.getRelatedAbstractState());

        } else {
          elementsInExecution.add(null);
        }
      } else if (elementOfInstanceRole instanceof Execution) {
        elementsInExecution.add(elementOfInstanceRole);
      }

    }
    return elementsInExecution;
  }

  /**
   * Return a chronologically ordered list of all elements placed on a given {@link InstanceRole}.
   * @param ir the given {@link InstanceRole}
   * @return a list of all elements.
   */
  public static List<EObject> getOrderedElementsOfInstanceRole(InstanceRole ir) {
    List<EObject> result = new ArrayList<EObject>();

    Scenario scenario = (Scenario) ir.eContainer();
    Stack<TimeLapse> execStack = new Stack<TimeLapse>();

    for (InteractionFragment ifgt : getOrderedInteractionFragments(scenario)) {
      if (ifgt instanceof AbstractEnd) {
        AbstractEnd ae = (AbstractEnd) ifgt;
        if (ae.getCovered() == ir) {
          // if ae starts an exception, the execution is stacked
          // if ae ends an exception, the execution is unstacked
          for (TimeLapse laptime : scenario.getOwnedTimeLapses()) {
            if (laptime instanceof Execution) {
              Execution exec2 = (Execution) laptime;
              if (exec2.getCovered() == ir) {
                if (exec2.getStart() == ae) {
                  if (execStack.isEmpty()) {
                    result.add(exec2);
                    execStack.push(exec2);
                  }
                }
                if (exec2.getFinish() == ae) {
                  execStack.pop();
                }
              }
            }
          }
        }
      } else if (ifgt.getCoveredInstanceRoles().contains(ir)) {
        if ((ifgt instanceof InteractionState) && execStack.isEmpty()) {
          result.add(getStartingExecution(ifgt));
        }

        TimeLapse startingExec = getStartingExecution(ifgt);
        if ((startingExec != null) && (startingExec instanceof Execution)) {
          execStack.push(startingExec);
        }

        TimeLapse endingExec = getEndingExecution(ifgt);
        if ((endingExec != null) && (endingExec instanceof Execution)) {
          execStack.pop();
        }
      }
    }

    return result;
  }

  /**
   * Returns the value of the 'Owned Interaction Fragments' containment reference list of a given {@link Scenario} or an empty list if the value of 'Owned
   * Interaction Fragments' is null.
   * @param scenario the {@link Scenario}
   * @return a list of {@link InteractionFragment}
   */
  public static List<InteractionFragment> getOrderedInteractionFragments(Scenario scenario) {
    if (scenario == null) {
      return new ArrayList<InteractionFragment>();
    }
    return scenario.getOwnedInteractionFragments();
  }

  /**
   * Return the {@link TimeLapse} started by a given {@link InteractionFragment}.
   * @param ifg the {@link InteractionFragment}
   * @return the {@link TimeLapse}
   */
  public static TimeLapse getStartingExecution(InteractionFragment ifg) {
    Scenario s = SequenceDiagramServices.getScenario(ifg);
    for (TimeLapse lap : s.getOwnedTimeLapses()) {
      if (lap.getStart() == ifg) {
        return lap;
      }
    }
    return null;
  }

  /**
   * Return the {@link TimeLapse} finished by a given {@link InteractionFragment}.
   * @param ifg the {@link InteractionFragment}
   * @return the {@link TimeLapse}
   */
  public static TimeLapse getEndingExecution(InteractionFragment ifg) {
    Scenario s = SequenceDiagramServices.getScenario(ifg);
    for (TimeLapse lap : s.getOwnedTimeLapses()) {
      if (lap.getFinish() == ifg) {
        return lap;
      }
    }
    return null;
  }

  /**
   * Verify if {@link InstanceRole} of an architectural part that allocate the function source of a given {@link FunctionalExchange} is present in the
   * {@link Scenario} of a given {@link InstanceRole}
   * @param functionalExchange the {@link FunctionalExchange}.
   * @param instanceRole the given {@link InstanceRole}
   * @return <code>true</code> if {@link InstanceRole} is present in the {@link Scenario}, else <code>false</code>
   */
  public static boolean isLifeLineInInteraction(FunctionalExchange functionalExchange, InstanceRole instanceRole) {
    Scenario scenario = SequenceDiagramServices.getScenario(instanceRole);
    if (functionalExchange.getSourceFunctionOutputPort().eContainer() instanceof AbstractFunction) {
      AbstractFunction functionSource = (AbstractFunction) functionalExchange.getSourceFunctionOutputPort().eContainer();
      for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
        if (!instanceRole.equals(role) && functionSource.getAllocationBlocks().contains(role.getRepresentedInstance().getAbstractType())) {
          return true;
        }
      }
    }
    return false;
  }
}
