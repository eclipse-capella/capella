/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.la;

import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
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
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelLaPa;

/**
 * Projection Tests on "State Machine Transition" from Logical Architecture to Physical Architecture
 * <P>
 * This is done with the model "LA-PA-Projection". The model is created as explained below.
 * 
 * <pre>
 *     Model Used: LA-PA-Projection
 *     Model is created with the following elements�
 *           1.  Ensure a realization link between Logical System and Physical System
 *           2.  Create an Empty State machine "Leaf State Machine" in Logical System
 *           3.  Create a new diagram "[M&S] Default Region - Modes & States" in Logical System State Machine
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
 * And the tests are documented in {@link #performTest()} method
 */
public class StateMachineTransition extends TopDownTransitionTestCase {

  // Target Objects
  @SuppressWarnings("unused")
  private LogicalComponent laSystem;
  private StateMachine laSystemStateMachine;
  private Region laSystemRegion;
  private InitialPseudoState laInitialState;
  private Mode laMode1;
  private Region laMode1Region;
  private State laMode1State1;
  private Mode laMode2;
  private Region laMode2Region;
  private InitialPseudoState laMode2Initial;
  private State laMode2State1;
  private State laMode2State2;
  private FinalState laMode2Final;
  private Mode laMode3;
  private ForkPseudoState laFork;
  private JoinPseudoState laJoin;
  private ChoicePseudoState laChoice;
  private TerminatePseudoState laTerminate;
  private FinalState laFinal;
  private StateMachine laLeafStateMachine;
  private Region laLeafRegion;

  // Physical LAYER
  private PhysicalComponent physicalSystem;
  private StateMachine physicalSystemStateMachine;
  private Region physicalSystemRegion;
  private InitialPseudoState paInitialState;
  private Mode paMode1;
  private Region paMode1Region;
  private State paMode1State1;
  private Mode paMode2;
  private Region paMode2Region;
  private InitialPseudoState paMode2Initial;
  private State paMode2State1;
  private State paMode2State2;
  private FinalState paMode2Final;
  private Mode paMode3;
  private ForkPseudoState paFork;
  private JoinPseudoState paJoin;
  private ChoicePseudoState paChoice;
  private TerminatePseudoState paTerminate;
  private FinalState paFinal;
  private StateMachine paLeafStateMachine;
  private Region paLeafRegion;

  private void initSession() {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    laSystem = getObject(ModelLaPa.logicalSystemId);
    laSystemStateMachine = getObject(ModelLaPa.logicalSystemStateMachineId);
    laSystemRegion = getObject(ModelLaPa.logicalSystemRegionId);
    laInitialState = getObject(ModelLaPa.initialStateId);
    laMode1 = getObject(ModelLaPa.mode1Id);
    laMode2 = getObject(ModelLaPa.mode2Id);
    laMode2Region = getObject(ModelLaPa.mode2RegionId);
    laMode2Initial = getObject(ModelLaPa.mode2InitialId);
    laMode2State1 = getObject(ModelLaPa.mode2State1Id);
    laMode2State2 = getObject(ModelLaPa.mode2State2Id);
    laMode2Final = getObject(ModelLaPa.mode2FinalId);
    laMode3 = getObject(ModelLaPa.mode3Id);
    laFork = getObject(ModelLaPa.forkId);
    laJoin = getObject(ModelLaPa.joinId);
    laChoice = getObject(ModelLaPa.choiceId);
    laTerminate = getObject(ModelLaPa.terminateId);
    laFinal = getObject(ModelLaPa.finalId);
    laLeafStateMachine = getObject(ModelLaPa.leafStateMachineId);
    laLeafRegion = getObject(ModelLaPa.leafRegionId);

    physicalSystem = getObject(ModelLaPa.physicalSystemId);
  }

  /**
   * Tests on "State Machine Transition" Projection command should be applied:
   * 
   * <pre>
   *         1. Test on one leaf entity (Leaf State Machine)
   *         2. Test on non leaf entity (Logical System State Machine)
   *         4. Repetition Test on Logical System State Machine without any changes
   *         5. Test on Logical System State Machine with changes
   * </pre>
   * 
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  public void performTest() throws Exception {
    initSession();
    leafSMProjectionTest();
    logicalSystemSMProjection1Test();
    logicalSMProjection2Test();
    logicalSMProjection3Test();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  /**
   * Run the projection test "State Machine Transition" from Leaf State Machine
   * 
   * <pre>
   * Expected Result:\
   *               1. Leaf State Machine & its region are created in Physical Layer under Physical System\
   *               2. State Realization Link is created between the state machines
   * </pre>
   */
  private void leafSMProjectionTest() {
    performStateMachineTransition(Arrays.asList(laLeafStateMachine));
    paLeafStateMachine = ProjectionTestUtils.getRecentlyAddedStateMachine(physicalSystem);
    mustNotBeNull(paLeafStateMachine);
    assertTrue(NLS.bind(Messages.RealizationError, paLeafStateMachine.getName(), laLeafStateMachine.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paLeafStateMachine) == laLeafStateMachine));

    paLeafRegion = paLeafStateMachine.getOwnedRegions().get(0);
    mustNotBeNull(paLeafRegion);
    assertTrue(NLS.bind(Messages.RealizationError, paLeafRegion.getName(), laLeafRegion.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paLeafRegion) == laLeafRegion));
  }

  /**
   * Run the projection test "State Machine Transition" from Logical System State Machine
   * 
   * <pre>
   * Expected Result:\
   *                   1. Initial State, Mode1, Mode2, Mode3, Fork, Join, Choice, Terminate \
   *                     and Final states and modes are projected to Physical Layer\
   *                   2.  Transitions are also created between the elements\
   *                   3.  Add Initial, State1, State2 and Final states are projected inside Mode2 with transitions
   * </pre>
   */
  private void logicalSystemSMProjection1Test() {
    performStateMachineTransition(Arrays.asList(laSystemStateMachine));
    physicalSystemStateMachine = ProjectionTestUtils.getRecentlyAddedStateMachine(physicalSystem);
    mustNotBeNull(physicalSystemStateMachine);

    assertTrue(
        NLS.bind(Messages.RealizationError, physicalSystemStateMachine.getName(), laSystemStateMachine.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalSystemStateMachine) == laSystemStateMachine));

    physicalSystemRegion = physicalSystemStateMachine.getOwnedRegions().get(0);
    mustNotBeNull(physicalSystemRegion);
    assertTrue(NLS.bind(Messages.RealizationError, physicalSystemRegion.getName(), laSystemRegion.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalSystemRegion) == laSystemRegion));

    paInitialState = (InitialPseudoState) physicalSystemRegion.getOwnedStates().get(0);
    mustNotBeNull(paInitialState);
    assertTrue(NLS.bind(Messages.RealizationError, paInitialState.getName(), laInitialState.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paInitialState) == laInitialState));

    paMode1 = (Mode) physicalSystemRegion.getOwnedStates().get(1);
    mustNotBeNull(paMode1);
    assertTrue(NLS.bind(Messages.RealizationError, paMode1.getName(), laMode1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode1) == laMode1));

    laMode1Region = laMode1.getOwnedRegions().get(0);
    paMode1Region = paMode1.getOwnedRegions().get(0);
    mustNotBeNull(paMode1Region);
    assertTrue(NLS.bind(Messages.RealizationError, paMode1Region.getName(), laMode1Region.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode1Region) == laMode1Region));

    paMode2 = (Mode) physicalSystemRegion.getOwnedStates().get(2);
    mustNotBeNull(paMode2);
    assertTrue(NLS.bind(Messages.RealizationError, paMode2.getName(), laMode2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode2) == laMode2));

    // ========================================================================================================
    // Mode 2 Sub Elements START
    // ========================================================================================================
    paMode2Region = paMode2.getOwnedRegions().get(0);
    mustNotBeNull(paMode2Region);
    assertTrue(NLS.bind(Messages.RealizationError, paMode2Region.getName(), laMode2Region.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode2Region) == laMode2Region));

    paMode2Initial = (InitialPseudoState) paMode2Region.getOwnedStates().get(0);
    mustNotBeNull(paMode2Initial);
    assertTrue(NLS.bind(Messages.RealizationError, paMode2Initial.getName(), laMode2Initial.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode2Initial) == laMode2Initial));

    paMode2State1 = (State) paMode2Region.getOwnedStates().get(1);
    mustNotBeNull(paMode2State1);
    assertTrue(NLS.bind(Messages.RealizationError, paMode2State1.getName(), laMode2State1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode2State1) == laMode2State1));

    paMode2State2 = (State) paMode2Region.getOwnedStates().get(2);
    mustNotBeNull(paMode2State2);
    assertTrue(NLS.bind(Messages.RealizationError, paMode2State2.getName(), laMode2State2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode2State2) == laMode2State2));

    paMode2Final = (FinalState) paMode2Region.getOwnedStates().get(3);
    mustNotBeNull(paMode2Final);
    assertTrue(NLS.bind(Messages.RealizationError, paMode2Final.getName(), laMode2Final.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode2Final) == laMode2Final));
    // ========================================================================================================
    // Mode 2 Sub Elements END
    // ========================================================================================================

    paMode3 = (Mode) physicalSystemRegion.getOwnedStates().get(3);
    mustNotBeNull(paMode3);
    assertTrue(NLS.bind(Messages.RealizationError, paMode3.getName(), laMode3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode3) == laMode3));

    paFork = (ForkPseudoState) physicalSystemRegion.getOwnedStates().get(4);
    mustNotBeNull(paFork);
    assertTrue(NLS.bind(Messages.RealizationError, paFork.getName(), laFork.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFork) == laFork));

    paJoin = (JoinPseudoState) physicalSystemRegion.getOwnedStates().get(5);
    mustNotBeNull(paJoin);
    assertTrue(NLS.bind(Messages.RealizationError, paJoin.getName(), laJoin.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paJoin) == laJoin));

    paChoice = (ChoicePseudoState) physicalSystemRegion.getOwnedStates().get(6);
    mustNotBeNull(paChoice);
    assertTrue(NLS.bind(Messages.RealizationError, paChoice.getName(), laChoice.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paChoice) == laChoice));

    paTerminate = (TerminatePseudoState) physicalSystemRegion.getOwnedStates().get(7);
    mustNotBeNull(paTerminate);
    assertTrue(NLS.bind(Messages.RealizationError, paTerminate.getName(), laTerminate.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paTerminate) == laTerminate));

    paFinal = (FinalState) physicalSystemRegion.getOwnedStates().get(8);
    mustNotBeNull(paFinal);
    assertTrue(NLS.bind(Messages.RealizationError, paFinal.getName(), laFinal.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFinal) == laFinal));
  }

  /**
   * Run the projection test "State Machine Transition" from logical System State Machine
   * 
   * <pre>
   * Expected Result:\
   *               1. No change happen from previous result
   * </pre>
   */
  private void logicalSMProjection2Test() {
    performStateMachineTransition(Arrays.asList(laSystemStateMachine));
    assertEquals(Messages.WrongAllocation,
        physicalSystem.getOwnedStateMachines().size(), 3);
    assertEquals(Messages.WrongAllocation,
        physicalSystemRegion.getOwnedStates().size(), 9);
    assertEquals(Messages.WrongAllocation, paMode2Region.getOwnedStates().size(), 4);
  }

  /**
   * Run the projection test "State Machine Transition" from Logical System State Machine with the changes
   * 
   * <pre>
   * Make the following changes:\
   *                   1. Remove Mode3 from Logical System\
   *                   2.  Remove State2 from Mode2\
   *                   3.  Add State1 to Mode1\
   * Expected Result:\
   *                   1.  Mode3 still exist in Physical Layer but the realization link doesn't exist anymore\
   *                   2.  State2 still exist in Physical Layer without the realization link\
   *                   3.  State1 is added to Mode1 with the projection
   * </pre>
   */
  private void logicalSMProjection3Test() {
    // Remove Mode3 from Ctx System
    laMode3.destroy();
    // Remove State2 from Mode2
    laMode2State2.destroy();
    // Add State1 to Mode1
    laMode1State1 = CapellacommonFactory.eINSTANCE.createState("State1"); //$NON-NLS-1$
    laMode1Region.getOwnedStates().add(laMode1State1);

    performStateMachineTransition(Arrays.asList(laSystemStateMachine));

    mustNotBeNull(paMode2State2);
    assertTrue(NLS.bind(Messages.RealizationError, paMode2State2.getName(), laMode2State2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode2State2) == null));

    mustNotBeNull(paMode3);
    assertTrue(NLS.bind(Messages.RealizationError, paMode3.getName(), laMode3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode3) == null));

    paMode1Region = paMode1.getOwnedRegions().get(0);
    mustNotBeNull(paMode1Region);
    assertTrue(NLS.bind(Messages.RealizationError, paMode1Region.getName(), laMode1Region.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode1Region) == laMode1Region));

    paMode1State1 = (State) paMode1Region.getOwnedStates().get(0);
    mustNotBeNull(paMode1State1);
    assertTrue(NLS.bind(Messages.RealizationError, paMode1State1.getName(), laMode1State1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paMode1State1) == laMode1State1));
  }

}
