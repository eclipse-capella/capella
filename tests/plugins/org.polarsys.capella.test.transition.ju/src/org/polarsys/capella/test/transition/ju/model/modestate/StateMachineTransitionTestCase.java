/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.model.modestate;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * This test case tests the transition of Time Event, Change Event and Constraints on State Machine transition
 *
 */
public class StateMachineTransitionTestCase extends TopDownTransitionTestCase {
  public static String TRANSITION_CONSTRAINT = "a6d5db54-da1d-4d9e-aa07-eb30f94e9b60"; //$NON-NLS-1$
  public static String TIME_EVENT = "54f19924-9dd0-4c4f-9ef7-650a8e524fde"; //$NON-NLS-1$
  public static String CHANGE_EVENT = "a95af136-24e2-421a-9422-f5f7d4d257ff"; //$NON-NLS-1$
  public static String TIME_EVENT_CONSTRAINT = "649fcc41-a430-47aa-90bd-99d0ec607b31"; //$NON-NLS-1$
  public static String CHANGE_EVENT_CONSTRAINT = "688591d4-59ae-492d-8b8b-754f1bd95499"; //$NON-NLS-1$
  public static String STATE_MACHINE = "9b1036c0-4d85-4d0d-be30-293f5e74a2ed"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("StateEventTransition");
  }

  @Override
  public void performTest() throws Exception {
    EObject stateMachine = shouldExist(STATE_MACHINE);
    performStateMachineTransition(Arrays.asList(stateMachine));
    mustBeTransitioned(TRANSITION_CONSTRAINT);
    mustBeTransitioned(TIME_EVENT);
    mustBeTransitioned(CHANGE_EVENT);
    mustBeTransitioned(TIME_EVENT_CONSTRAINT);
    mustBeTransitioned(CHANGE_EVENT_CONSTRAINT);
  }

  public static Test suite() {
    return new StateMachineTransitionTestCase();
  }
}
