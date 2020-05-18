/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.oa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.ChoicePseudoState;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.ForkPseudoState;
import org.polarsys.capella.core.data.capellacommon.InitialPseudoState;
import org.polarsys.capella.core.data.capellacommon.JoinPseudoState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.TerminatePseudoState;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelOaSa;

/**
 * Projection Tests on "State Machine Transition" from Operational Analysis to System Analysis
 * <P>
 * This is done with the model "OA-SA-Projection". The model is created as explained below.
 * 
 * <pre>
 *     Model Used: OA-SA-Projection
 *     Model is created with the following elements�
 *           1.  Create a transition between Root Operational Entity with "Realized By System"
 *           2.  Create an Empty State machine "Leaf State Machine" in Root OE
 *           3.  Create a new diagram "[M&S] Default Region - Modes & States" in Root OE State Machine
 *           4.  Create Initial State, Mode1, Mode2, Mode3, Fork, Join, Choice, Terminate and Final in the diagram.
 *           5.  Create Transitions as follows�
 *                   a.  Initial State to Fork
 *                   b.  Fork to Mode1 and Choice
 *                   c.  Mode1 to Join, Choice to Join
 *                   d.  Choice to Mode2; Mode2 to Terminate
 *                   e.  Join to Mode3; Mode3 to Final
 *           6.  Add a Constraint to Mode2
 *           7.  Create a new Diagram [M&S] Mode 2 - Modes & States in Mode2
 *           8.  Add Initial, State1, State2 and Final states in the diagram.
 *           9.  Create Transitions as Initial --> State1 --> State2 --> Final.
 *           10. Save the diagram & model
 * 
 * </pre>
 * 
 * 
 */
public class StateMachineTransition extends TopDownTransitionTestCase {

  // Target Objects
  private Entity _oaRootOE;
  private StateMachine _oaRootOEStateMachine;
  private Region _oaRootOERegion;
  private InitialPseudoState _oaInitialState;
  private Mode _oaMode1;
  private Region _oaMode1Region;
  private State _oaMode1State1;
  private Mode _oaMode2;
  private Region _oaMode2Region;
  private InitialPseudoState _oaMode2Initial;
  private State _oaMode2State1;
  private State _oaMode2State2;
  private FinalState _oaMode2Final;
  private Mode _oaMode3;
  private ForkPseudoState _oaFork;
  private JoinPseudoState _oaJoin;
  private ChoicePseudoState _oaChoice;
  private TerminatePseudoState _oaTerminate;
  private FinalState _oaFinal;
  private StateMachine _oaLeafStateMachine;
  private Region _oaLeafRegion;

  // CTX LAYER
  private SystemComponent _system;
  private StateMachine _ctxRootOEStateMachine;
  private Region _ctxRootOERegion;
  private InitialPseudoState _ctxInitialState;
  private Mode _ctxMode1;
  private Region _ctxMode1Region;
  private State _ctxMode1State1;
  private Mode _ctxMode2;
  private Region _ctxMode2Region;
  private InitialPseudoState _ctxMode2Initial;
  private State _ctxMode2State1;
  private State _ctxMode2State2;
  private FinalState _ctxMode2Final;
  private Mode _ctxMode3;
  private ForkPseudoState _ctxFork;
  private JoinPseudoState _ctxJoin;
  private ChoicePseudoState _ctxChoice;
  private TerminatePseudoState _ctxTerminate;
  private FinalState _ctxFinal;
  private StateMachine _ctxLeafStateMachine;
  private Region _ctxLeafRegion;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {
    // Assign the objects
    _oaRootOE = (Entity) getObject(ModelOaSa.rootOEId);
    _oaRootOEStateMachine = (StateMachine) getObject(ModelOaSa.rootOEStateMachineId);
    _oaRootOERegion = (Region) getObject(ModelOaSa.rootOERegionId);
    _oaInitialState = (InitialPseudoState) getObject(ModelOaSa.initialStateId);
    _oaMode1 = (Mode) getObject(ModelOaSa.mode1Id);
    _oaMode2 = (Mode) getObject(ModelOaSa.mode2Id);
    _oaMode2Region = (Region) getObject(ModelOaSa.mode2RegionId);
    _oaMode2Initial = (InitialPseudoState) getObject(ModelOaSa.mode2InitialId);
    _oaMode2State1 = (State) getObject(ModelOaSa.mode2State1Id);
    _oaMode2State2 = (State) getObject(ModelOaSa.mode2State2Id);
    _oaMode2Final = (FinalState) getObject(ModelOaSa.mode2FinalId);
    _oaMode3 = (Mode) getObject(ModelOaSa.mode3Id);
    _oaFork = (ForkPseudoState) getObject(ModelOaSa.forkId);
    _oaJoin = (JoinPseudoState) getObject(ModelOaSa.joinId);
    _oaChoice = (ChoicePseudoState) getObject(ModelOaSa.choiceId);
    _oaTerminate = (TerminatePseudoState) getObject(ModelOaSa.terminateId);
    _oaFinal = (FinalState) getObject(ModelOaSa.finalId);
    _oaLeafStateMachine = (StateMachine) getObject(ModelOaSa.leafStateMachineId);
    _oaLeafRegion = (Region) getObject(ModelOaSa.leafRegionId);

    _system = (SystemComponent) getObject(ModelOaSa.systemId);

    performTest1();
    performTest2();
    performTest3();
    performTest4();
  }

  /**
   * Run the projection test "State Machine Transition" from Leaf State Machine
   * 
   * <pre>
   * Expected Result:\
   *               1. Leaf State Machine & its region are created in Ctx Layer under System\
   *               2. State Realization Link is created between the state machines
   * </pre>
   */

  public void performTest1() throws Exception {
    performRealizedBySystemTransition(Collections.singletonList((EObject) _oaRootOE));

    performStateMachineTransition(Collections.singletonList(_oaLeafStateMachine));
    _ctxLeafStateMachine = ProjectionTestUtils.getRecentlyAddedStateMachine(_system);
    mustNotBeNull(_ctxLeafStateMachine);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxLeafStateMachine.getName(), _oaLeafStateMachine.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxLeafStateMachine) == _oaLeafStateMachine));

    _ctxLeafRegion = _ctxLeafStateMachine.getOwnedRegions().get(0);
    mustNotBeNull(_ctxLeafRegion);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxLeafRegion.getName(), _oaLeafRegion.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxLeafRegion) == _oaLeafRegion));
  }

  /**
   * Run the projection test "State Machine Transition" from Root OE State Machine
   * 
   * <pre>
   * Expected Result:\
   *               1. Initial State, Mode1, Mode2, Mode3, Fork, Join, Choice, Terminate \
   *                    and Final states and modes are projected to Ctx Layer\
   *               2.  Transitions are also created between the elements\
   *               3.  Add Initial, State1, State2 and Final states are projected inside Mode2 with transitions
   * </pre>
   */

  public void performTest2() throws Exception {
    performStateMachineTransition(Collections.singletonList(_oaRootOEStateMachine));
    _ctxRootOEStateMachine = ProjectionTestUtils.getRecentlyAddedStateMachine(_system);
    mustNotBeNull(_ctxRootOEStateMachine);

    assertTrue(NLS.bind(Messages.RealizationError, _ctxRootOEStateMachine.getName(), _oaRootOEStateMachine.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxRootOEStateMachine) == _oaRootOEStateMachine));

    _ctxRootOERegion = _ctxRootOEStateMachine.getOwnedRegions().get(0);
    mustNotBeNull(_ctxRootOERegion);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxRootOERegion.getName(), _oaRootOERegion.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxRootOERegion) == _oaRootOERegion));

    _ctxInitialState = (InitialPseudoState) _ctxRootOERegion.getOwnedStates().get(0);
    mustNotBeNull(_ctxInitialState);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxInitialState.getName(), _oaInitialState.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxInitialState) == _oaInitialState));

    _ctxMode1 = (Mode) _ctxRootOERegion.getOwnedStates().get(1);
    mustNotBeNull(_ctxMode1);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode1.getName(), _oaMode1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode1) == _oaMode1));

    _oaMode1Region = _oaMode1.getOwnedRegions().get(0);
    _ctxMode1Region = _ctxMode1.getOwnedRegions().get(0);
    mustNotBeNull(_ctxMode1Region);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode1Region.getName(), _oaMode1Region.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode1Region) == _oaMode1Region));

    _ctxMode2 = (Mode) _ctxRootOERegion.getOwnedStates().get(2);
    mustNotBeNull(_ctxMode2);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode2.getName(), _oaMode2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode2) == _oaMode2));

    // ========================================================================================================
    // Mode 2 Sub Elements START
    // ========================================================================================================
    _ctxMode2Region = _ctxMode2.getOwnedRegions().get(0);
    mustNotBeNull(_ctxMode2Region);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode2Region.getName(), _oaMode2Region.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode2Region) == _oaMode2Region));

    _ctxMode2Initial = (InitialPseudoState) _ctxMode2Region.getOwnedStates().get(0);
    mustNotBeNull(_ctxMode2Initial);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode2Initial.getName(), _oaMode2Initial.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode2Initial) == _oaMode2Initial));

    _ctxMode2State1 = (State) _ctxMode2Region.getOwnedStates().get(1);
    mustNotBeNull(_ctxMode2State1);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode2State1.getName(), _oaMode2State1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode2State1) == _oaMode2State1));

    _ctxMode2State2 = (State) _ctxMode2Region.getOwnedStates().get(2);
    mustNotBeNull(_ctxMode2State2);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode2State2.getName(), _oaMode2State2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode2State2) == _oaMode2State2));

    _ctxMode2Final = (FinalState) _ctxMode2Region.getOwnedStates().get(3);
    mustNotBeNull(_ctxMode2Final);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode2Final.getName(), _oaMode2Final.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode2Final) == _oaMode2Final));
    // ========================================================================================================
    // Mode 2 Sub Elements END
    // ========================================================================================================

    _ctxMode3 = (Mode) _ctxRootOERegion.getOwnedStates().get(3);
    mustNotBeNull(_ctxMode3);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode3.getName(), _oaMode3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode3) == _oaMode3));

    _ctxFork = (ForkPseudoState) _ctxRootOERegion.getOwnedStates().get(4);
    mustNotBeNull(_ctxFork);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxFork.getName(), _oaFork.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxFork) == _oaFork));

    _ctxJoin = (JoinPseudoState) _ctxRootOERegion.getOwnedStates().get(5);
    mustNotBeNull(_ctxJoin);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxJoin.getName(), _oaJoin.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxJoin) == _oaJoin));

    _ctxChoice = (ChoicePseudoState) _ctxRootOERegion.getOwnedStates().get(6);
    mustNotBeNull(_ctxChoice);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxChoice.getName(), _oaChoice.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxChoice) == _oaChoice));

    _ctxTerminate = (TerminatePseudoState) _ctxRootOERegion.getOwnedStates().get(7);
    mustNotBeNull(_ctxTerminate);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxTerminate.getName(), _oaTerminate.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxTerminate) == _oaTerminate));

    _ctxFinal = (FinalState) _ctxRootOERegion.getOwnedStates().get(8);
    mustNotBeNull(_ctxFinal);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxFinal.getName(), _oaFinal.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxFinal) == _oaFinal));
  }

  /**
   * Run the projection test "State Machine Transition" from Root OE State Machine
   * 
   * <pre>
   * Expected Result:\
   *               1. No change happen from previous result
   * </pre>
   */

  public void performTest3() throws Exception {
    performStateMachineTransition(Collections.singletonList(_oaRootOEStateMachine));
    assertEquals(Messages.WrongAllocation, _system.getOwnedStateMachines().size(), 3);
    assertEquals(Messages.WrongAllocation, _ctxRootOERegion.getOwnedStates().size(),
        9);
    assertEquals(Messages.WrongAllocation, _ctxMode2Region.getOwnedStates().size(), 4);
  }

  /**
   * Run the projection test "State Machine Transition" from Root OE State Machine with the changes
   * 
   * <pre>
   * Make the following changes:\
   *               1.  Remove Mode3 from Root OE\
   *               2.  Remove State2 from Mode2\
   *               3.  Add State1 to Mode1\
   * Expected Result:\
   *               1.  Mode3 still exist in Ctx Layer but the realization link doesn't exist anymore\
   *               2.  State2 still exist in Ctx Layer without the realization link\
   *               3.  State1 is added to Mode1 with the projection
   * </pre>
   */

  public void performTest4() throws Exception {

    // Remove Mode3 from Root OE
    // Remove State2 from Mode2
    // Add State1 to Mode1
    getExecutionManager(_oaRootOE).execute(new AbstractReadWriteCommand() {

      public void run() {
        _oaMode3.destroy();
        _oaMode2State2.destroy();
        _oaMode1State1 = CapellacommonFactory.eINSTANCE.createState("State1"); //$NON-NLS-1$
        _oaMode1Region.getOwnedStates().add(_oaMode1State1);
      }
    });

    performStateMachineTransition(Collections.singletonList(_oaRootOEStateMachine));
    mustNotBeNull(_ctxMode2State2);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode2State2.getName(), _oaMode2State2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode2State2) == null));

    mustNotBeNull(_ctxMode3);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode3.getName(), _oaMode3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode3) == null));

    _ctxMode1Region = _ctxMode1.getOwnedRegions().get(0);
    mustNotBeNull(_ctxMode1Region);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode1Region.getName(), _oaMode1Region.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode1Region) == _oaMode1Region));

    _ctxMode1State1 = (State) _ctxMode1Region.getOwnedStates().get(0);
    mustNotBeNull(_ctxMode1State1);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxMode1State1.getName(), _oaMode1State1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxMode1State1) == _oaMode1State1));
  }
}
