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
package org.polarsys.capella.test.transition.ju.testcases.sa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelCtxLa;

/**
 * Projection Tests on "State Machine Transition" from System Analysis to Logical Layer
 * <P>
 * This is done with the model "CTX-LA-Projection". The model is created as explained below.
 * 
 * <pre>
 *     Model Used: CTX-LA-Projection
 *     Model is created with the following elements�
 *           1.  2.	Ensure a realization link between Ctx System and Logical System
 *           2.  Create an Empty State machine "Leaf State Machine" in Ctx System
 *           3.  Create a new diagram "[M&S] Default Region - Modes & States" in Ctx System State Machine
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
  @SuppressWarnings("unused")
  private SystemComponent _ctxSystem;
  private StateMachine _ctxSystemStateMachine;
  private Region _ctxSystemRegion;
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

  // Logical LAYER
  private LogicalComponent _logicalSystem;
  private StateMachine _logicalSystemStateMachine;
  private Region _logicalSystemRegion;
  private InitialPseudoState _laInitialState;
  private Mode _laMode1;
  private Region _laMode1Region;
  private State _laMode1State1;
  private Mode _laMode2;
  private Region _laMode2Region;
  private InitialPseudoState _laMode2Initial;
  private State _laMode2State1;
  private State _laMode2State2;
  private FinalState _laMode2Final;
  private Mode _laMode3;
  private ForkPseudoState _laFork;
  private JoinPseudoState _laJoin;
  private ChoicePseudoState _laChoice;
  private TerminatePseudoState _laTerminate;
  private FinalState _laFinal;
  private StateMachine _laLeafStateMachine;
  private Region _laLeafRegion;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {
    // Assign the objects
    _ctxSystem = (SystemComponent) getObject(ModelCtxLa.ctxSystemId);
    _ctxSystemStateMachine = (StateMachine) getObject(ModelCtxLa.ctxSystemStateMachineId);
    _ctxSystemRegion = (Region) getObject(ModelCtxLa.ctxSystemRegionId);
    _ctxInitialState = (InitialPseudoState) getObject(ModelCtxLa.initialStateId);
    _ctxMode1 = (Mode) getObject(ModelCtxLa.mode1Id);
    _ctxMode2 = (Mode) getObject(ModelCtxLa.mode2Id);
    _ctxMode2Region = (Region) getObject(ModelCtxLa.mode2RegionId);
    _ctxMode2Initial = (InitialPseudoState) getObject(ModelCtxLa.mode2InitialId);
    _ctxMode2State1 = (State) getObject(ModelCtxLa.mode2State1Id);
    _ctxMode2State2 = (State) getObject(ModelCtxLa.mode2State2Id);
    _ctxMode2Final = (FinalState) getObject(ModelCtxLa.mode2FinalId);
    _ctxMode3 = (Mode) getObject(ModelCtxLa.mode3Id);
    _ctxFork = (ForkPseudoState) getObject(ModelCtxLa.forkId);
    _ctxJoin = (JoinPseudoState) getObject(ModelCtxLa.joinId);
    _ctxChoice = (ChoicePseudoState) getObject(ModelCtxLa.choiceId);
    _ctxTerminate = (TerminatePseudoState) getObject(ModelCtxLa.terminateId);
    _ctxFinal = (FinalState) getObject(ModelCtxLa.finalId);
    _ctxLeafStateMachine = (StateMachine) getObject(ModelCtxLa.leafStateMachineId);
    _ctxLeafRegion = (Region) getObject(ModelCtxLa.leafRegionId);

    _logicalSystem = (LogicalComponent) getObject(ModelCtxLa.logicalSystemId);

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
   *               1. Leaf State Machine & its region are created in Logical Layer under Logical System\
   *               2. State Realization Link is created between the state machines
   * </pre>
   */

  public void performTest1() throws Exception {
    performStateMachineTransition(Collections.singletonList(_ctxLeafStateMachine));
    _laLeafStateMachine = ProjectionTestUtils.getRecentlyAddedStateMachine(_logicalSystem);
    mustNotBeNull(_laLeafStateMachine);
    assertTrue(NLS.bind(Messages.RealizationError, _laLeafStateMachine.getName(), _ctxLeafStateMachine.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laLeafStateMachine) == _ctxLeafStateMachine));

    _laLeafRegion = _laLeafStateMachine.getOwnedRegions().get(0);
    mustNotBeNull(_laLeafRegion);
    assertTrue(NLS.bind(Messages.RealizationError, _laLeafRegion.getName(), _ctxLeafRegion.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laLeafRegion) == _ctxLeafRegion));
  }

  /**
   * Run the projection test "State Machine Transition" from Ctx System State Machine
   * 
   * <pre>
   * Expected Result:\
   *               1. Initial State, Mode1, Mode2, Mode3, Fork, Join, Choice, Terminate \
   *                    and Final states and modes are projected to Logical Layer\
   *               2.  Transitions are also created between the elements\
   *               3.  Add Initial, State1, State2 and Final states are projected inside Mode2 with transitions
   * </pre>
   */

  public void performTest2() throws Exception {
    performStateMachineTransition(Collections.singletonList(_ctxSystemStateMachine));
    _logicalSystemStateMachine = ProjectionTestUtils.getRecentlyAddedStateMachine(_logicalSystem);
    mustNotBeNull(_logicalSystemStateMachine);

    assertTrue(
        NLS.bind(Messages.RealizationError, _logicalSystemStateMachine.getName(), _ctxSystemStateMachine.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_logicalSystemStateMachine) == _ctxSystemStateMachine));

    _logicalSystemRegion = _logicalSystemStateMachine.getOwnedRegions().get(0);
    mustNotBeNull(_logicalSystemRegion);
    assertTrue(NLS.bind(Messages.RealizationError, _logicalSystemRegion.getName(), _ctxSystemRegion.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_logicalSystemRegion) == _ctxSystemRegion));

    _laInitialState = (InitialPseudoState) _logicalSystemRegion.getOwnedStates().get(0);
    mustNotBeNull(_laInitialState);
    assertTrue(NLS.bind(Messages.RealizationError, _laInitialState.getName(), _ctxInitialState.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laInitialState) == _ctxInitialState));

    _laMode1 = (Mode) _logicalSystemRegion.getOwnedStates().get(1);
    mustNotBeNull(_laMode1);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode1.getName(), _ctxMode1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode1) == _ctxMode1));

    _ctxMode1Region = _ctxMode1.getOwnedRegions().get(0);
    _laMode1Region = _laMode1.getOwnedRegions().get(0);
    mustNotBeNull(_laMode1Region);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode1Region.getName(), _ctxMode1Region.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode1Region) == _ctxMode1Region));

    _laMode2 = (Mode) _logicalSystemRegion.getOwnedStates().get(2);
    mustNotBeNull(_laMode2);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode2.getName(), _ctxMode2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode2) == _ctxMode2));

    // ========================================================================================================
    // Mode 2 Sub Elements START
    // ========================================================================================================
    _laMode2Region = _laMode2.getOwnedRegions().get(0);
    mustNotBeNull(_laMode2Region);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode2Region.getName(), _ctxMode2Region.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode2Region) == _ctxMode2Region));

    _laMode2Initial = (InitialPseudoState) _laMode2Region.getOwnedStates().get(0);
    mustNotBeNull(_laMode2Initial);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode2Initial.getName(), _ctxMode2Initial.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode2Initial) == _ctxMode2Initial));

    _laMode2State1 = (State) _laMode2Region.getOwnedStates().get(1);
    mustNotBeNull(_laMode2State1);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode2State1.getName(), _ctxMode2State1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode2State1) == _ctxMode2State1));

    _laMode2State2 = (State) _laMode2Region.getOwnedStates().get(2);
    mustNotBeNull(_laMode2State2);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode2State2.getName(), _ctxMode2State2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode2State2) == _ctxMode2State2));

    _laMode2Final = (FinalState) _laMode2Region.getOwnedStates().get(3);
    mustNotBeNull(_laMode2Final);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode2Final.getName(), _ctxMode2Final.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode2Final) == _ctxMode2Final));
    // ========================================================================================================
    // Mode 2 Sub Elements END
    // ========================================================================================================

    _laMode3 = (Mode) _logicalSystemRegion.getOwnedStates().get(3);
    mustNotBeNull(_laMode3);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode3.getName(), _ctxMode3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode3) == _ctxMode3));

    _laFork = (ForkPseudoState) _logicalSystemRegion.getOwnedStates().get(4);
    mustNotBeNull(_laFork);
    assertTrue(NLS.bind(Messages.RealizationError, _laFork.getName(), _ctxFork.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laFork) == _ctxFork));

    _laJoin = (JoinPseudoState) _logicalSystemRegion.getOwnedStates().get(5);
    mustNotBeNull(_laJoin);
    assertTrue(NLS.bind(Messages.RealizationError, _laJoin.getName(), _ctxJoin.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laJoin) == _ctxJoin));

    _laChoice = (ChoicePseudoState) _logicalSystemRegion.getOwnedStates().get(6);
    mustNotBeNull(_laChoice);
    assertTrue(NLS.bind(Messages.RealizationError, _laChoice.getName(), _ctxChoice.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laChoice) == _ctxChoice));

    _laTerminate = (TerminatePseudoState) _logicalSystemRegion.getOwnedStates().get(7);
    mustNotBeNull(_laTerminate);
    assertTrue(NLS.bind(Messages.RealizationError, _laTerminate.getName(), _ctxTerminate.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laTerminate) == _ctxTerminate));

    _laFinal = (FinalState) _logicalSystemRegion.getOwnedStates().get(8);
    mustNotBeNull(_laFinal);
    assertTrue(NLS.bind(Messages.RealizationError, _laFinal.getName(), _ctxFinal.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laFinal) == _ctxFinal));
  }

  /**
   * Run the projection test "State Machine Transition" from Ctx System State Machine
   * 
   * <pre>
   * Expected Result:\
   *               1. No change happen from previous result
   * </pre>
   */

  public void performTest3() throws Exception {
    performStateMachineTransition(Collections.singletonList(_ctxSystemStateMachine));
    assertEquals(Messages.WrongSize,
        _logicalSystem.getOwnedStateMachines().size(), 3);
    assertEquals(Messages.WrongSize,
        _logicalSystemRegion.getOwnedStates().size(), 9);
    assertEquals(Messages.WrongSize, _laMode2Region.getOwnedStates().size(), 4);
  }

  /**
   * Run the projection test "State Machine Transition" from Ctx System State Machine with the changes
   * 
   * <pre>
   * Make the following changes:\
   *               1.  Remove Mode3 from Ctx System\
   *               2.  Remove State2 from Mode2\
   *               3.  Add State1 to Mode1\
   * Expected Result:\
   *               1.  Mode3 still exist in Logical Layer but the realization link doesn't exist anymore\
   *               2.  State2 still exist in Logical Layer without the realization link\
   *               3.  State1 is added to Mode1 with the projection
   * </pre>
   */

  public void performTest4() throws Exception {

    // Remove Mode3 from Ctx System
    // Remove State2 from Mode2
    // Add State1 to Mode1
    getExecutionManager(_ctxSystemStateMachine).execute(new AbstractReadWriteCommand() {

      public void run() {
        _ctxMode3.destroy();
        _ctxMode2State2.destroy();
        _ctxMode1State1 = CapellacommonFactory.eINSTANCE.createState("State1"); //$NON-NLS-1$
        _ctxMode1Region.getOwnedStates().add(_ctxMode1State1);
      }
    });

    performStateMachineTransition(Collections.singletonList(_ctxSystemStateMachine));
    mustNotBeNull(_laMode2State2);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode2State2.getName(), _ctxMode2State2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode2State2) == null));

    mustNotBeNull(_laMode3);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode3.getName(), _ctxMode3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode3) == null));

    _laMode1Region = _laMode1.getOwnedRegions().get(0);
    mustNotBeNull(_laMode1Region);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode1Region.getName(), _ctxMode1Region.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode1Region) == _ctxMode1Region));

    _laMode1State1 = (State) _laMode1Region.getOwnedStates().get(0);
    mustNotBeNull(_laMode1State1);
    assertTrue(NLS.bind(Messages.RealizationError, _laMode1State1.getName(), _ctxMode1State1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laMode1State1) == _ctxMode1State1));
  }

}
