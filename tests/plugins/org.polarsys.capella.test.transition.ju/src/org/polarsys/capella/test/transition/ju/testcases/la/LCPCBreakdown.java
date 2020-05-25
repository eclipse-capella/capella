/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelLcPc;

/**
 * /** Projection Tests on "LC PC Transition" from Logical Architecture to Physical Architecture
 * <P>
 * 
 * <pre>
 *     These tests are going to be performed under 3 different conditions
 *     Propagate LC with breakdown option without Transit Interface
 *     Propagate LC with breakdown option with Transit Interface
 *     Propagate LC with leaf option
 *       
 *     Model Used: LC-PC-Projection
 *     Model is created with the following elements ...
 *         1.  Create a new "[LCBD] Logical System - Logical Component Breakdown" diagram in Logical System
 *         2.  Create Logical Components LC1, LC2 in the diagram
 *         3.  Create LC11, LC12 under LC1 and LC21 and LC22 under LC2
 *         4.  Create LC111 under LC11
 *         5.  Create LC Pkg under Logical System
 *         6.  Create SubLC1 under LC Pkg and SubLC11 under SubLC1
 *         7.  Create a new "[LAB] Logical System - Logical Architecture Blank" diagram in Logical System
 *         8.  Show all the components; Create Logical Functions LF11 in LC11, LF111 in LC111, Join Function Join1 in LC12
 *         9.  Create Exchange LF111Join1Exchange between LF111 and Join1, and Join1LF11Exchange between Join1 and LF111
 *         10. Create Functional Chain Chain1 with the path LF111, Join1, LF11
 *         11. Create Leaf LC under Logical System
 *         12. Create Logical Actor LA1 and LA2
 *         13. Create Connections C1 between LC1 and LC2, C2 between LC11 and LC12 and C3 between LC21 and LC22 and C4 between LA1 and LA2
 *         14. Create a new "[IDB] Logical System - Interfaces Diagram Blank" under Logical System
 *         15. Create Interfaces Interface1, and Interface2 in the diagram
 *         16. Create Exchange Items "EventEI", "OperationEI" under Interface1 and "FlowEI" and "DataEI" under Interface2
 *         17. Create Interface Implementation link between LC1 and Interface1 and Interface2. Decompose LC1 such that LC11 implements Interface1 and LC12 implements Interface2.
 *         18. Create Interface Use link between LC2 and Interface1 and Interface2. Decompose LC2 such that LC21 uses Interface1 and LC22 uses Interface2.
 *         19. Create a new "[CEI] Logical System - Contextual Component External Interfaces" diagram and add External Interface1 and External Interface2 in the diagram
 *         20. Create "Required Interface Link" between LF11FIP and External Interface1 and "Provided Interface Link" between LF111FOP and External Interface2
 *         21. Save the modeler and diagrams
 *       
 *       
 *       Set the transit interfaces option to false before launching the tests
 * 
 * </pre>
 * 
 * And the tests are documented in {@link #performTest()} method
 */
public class LCPCBreakdown extends TopDownTransitionTestCase {

  // Logical Layer
  private LogicalComponent logicalSystem;
  private LogicalComponent lc1;
  private LogicalComponent lc11;
  private LogicalComponent lc111;
  private LogicalComponent lc12;

  private LogicalComponent lc2;
  private LogicalComponent lc21;
  private LogicalComponent lc22;

  private LogicalComponent leafLC;

  private LogicalComponentPkg lcPkg;
  private LogicalComponent subLC1;
  private LogicalComponent subLC11;
  private LogicalComponent subLC12;

  private ComponentExchange logicalConnection1;
  private ComponentPort lc1OFP;
  private ComponentPort lc2IFP;

  private ComponentExchange logicalConnection2;
  private ComponentPort lc11OFP;
  private ComponentPort lc12IFP;

  private ComponentExchange logicalConnection3;
  private ComponentPort lc21OFP;
  private ComponentPort lc22IFP;

  private ComponentExchange logicalConnection4;

  private Interface logicalInterface1;
  private Interface logicalInterface2;

  private LogicalFunction lf11;
  private FunctionInputPort lf11FIP;
  private LogicalFunction lf111;
  private FunctionOutputPort lf111FOP;
  private AbstractFunction logicalJoin1; // join
  private FunctionalExchange lf111Join1Exchange;
  private FunctionalExchange join1LF11Exchange;

  private Interface logicalExternalItf1;
  private Interface logicalExternalItf2;

  // Physical Layer
  private PhysicalComponent physicalSystem;
  private PhysicalComponent pc1;
  private PhysicalComponent pc11;
  private PhysicalComponent pc111;
  private PhysicalComponent pc12;

  private PhysicalComponent pc2;
  private PhysicalComponent pc21;
  private PhysicalComponent pc22;

  private PhysicalComponent leafPC;

  private PhysicalComponentPkg pcPkg;
  private PhysicalComponent subPC1;
  private PhysicalComponent subPC11;
  private PhysicalComponent subPC12;

  private ComponentExchange physicalConnection1;
  private ComponentPort pc1OFP;
  private ComponentPort pc2IFP;

  private ComponentExchange physicalConnection2;
  private ComponentPort pc11OFP;
  private ComponentPort pc12IFP;

  private ComponentExchange physicalConnection3;
  private ComponentPort pc21OFP;
  private ComponentPort pc22IFP;

  private ComponentExchange physicalConnection4;

  private PhysicalFunction pf11;
  private FunctionInputPort pf11FIP;
  private PhysicalFunction pf111;
  private FunctionOutputPort pf111FOP;
  private AbstractFunction physicalJoin1; // Join
  private FunctionalExchange pf111Join1Exchange;
  private FunctionalExchange join1PF11Exchange;

  private InterfacePkg pcInterfacePkg;
  private PhysicalComponentPkg physicalActorPkg;
  private PhysicalFunction rootPF;

  private void initSession() {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, false);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__LCPC,
        ITopDownConstants.OPTIONS_TRANSITION__LCPC_BREAKDOWN);
    logicalSystem = getObject(ModelLcPc.logicalSystemId);
    lc1 = getObject(ModelLcPc.lc1Id);
    lc11 = getObject(ModelLcPc.lc11Id);
    lc111 = getObject(ModelLcPc.lc111Id);
    lc12 = getObject(ModelLcPc.lc12Id);
    lc2 = getObject(ModelLcPc.lc2Id);
    lc21 = getObject(ModelLcPc.lc21Id);
    lc22 = getObject(ModelLcPc.lc22Id);
    leafLC = getObject(ModelLcPc.leafLCId);
    lcPkg = getObject(ModelLcPc.lcPkgId);
    subLC1 = getObject(ModelLcPc.subLC1Id);
    subLC11 = getObject(ModelLcPc.subLC11Id);
    logicalConnection1 = getObject(ModelLcPc.connection1Id);
    lc1OFP = getObject(ModelLcPc.lc1OFPId);
    lc2IFP = getObject(ModelLcPc.lc2IFPId);
    logicalConnection2 = getObject(ModelLcPc.connection2Id);
    lc11OFP = getObject(ModelLcPc.lc11OFPId);
    lc12IFP = getObject(ModelLcPc.lc12IFPId);
    logicalConnection3 = getObject(ModelLcPc.connection3Id);
    lc21OFP = getObject(ModelLcPc.lc21OFPId);
    lc22IFP = getObject(ModelLcPc.lc22IFPId);
    logicalConnection4 = getObject(ModelLcPc.connection4Id);
    logicalInterface1 = getObject(ModelLcPc.interface1Id);
    logicalInterface2 = getObject(ModelLcPc.interface2Id);
    lf11 = getObject(ModelLcPc.lf11Id);
    lf11FIP = getObject(ModelLcPc.lf11FIPId);
    lf111 = getObject(ModelLcPc.lf111Id);
    lf111FOP = getObject(ModelLcPc.lf111FOPId);
    logicalJoin1 = getObject(ModelLcPc.join1Id);
    lf111Join1Exchange = getObject(ModelLcPc.lf111Join1ExchangeId);
    join1LF11Exchange = getObject(ModelLcPc.join1LF11ExchangeId);
    logicalExternalItf1 = getObject(ModelLcPc.externalItf1Id);
    logicalExternalItf2 = getObject(ModelLcPc.externalItf2Id);

    physicalSystem = getObject(ModelLcPc.physicalSystemId);
    pcInterfacePkg = getObject(ModelLcPc.pcInterfacePkgId);
    physicalActorPkg = getObject(ModelLcPc.physicalActorPkgId);
    rootPF = getObject(ModelLcPc.rootPFId);
  }

  /**
   * Tests on "LC PC Transition" Projection command:
   * 
   * <pre>
   *         1. Test on one leaf entity (Leaf LC)
   *         2. Test on non leaf entity (LC1)
   *         4. Repetition Test on LC1 without any changes
   *         5. Test on Logical System with changes
   * </pre>
   * 
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  public void performTest() throws Exception {
    initSession();
    leafLCTransitionTest();
    lc1Transition1Test();
    lc1Transition2Test();
    logicalSystemTransitionTest();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  /**
   * Run the projection test "LC to PC Transition" from LogicalSystem
   * 
   * <pre>
   * Expected Result:\
   *             1. All Logical Components LC1, LC11, LC12, LC111, LC2, LC21, LC22, Leaf LC, LC Pkg, SubLC1, SubLC11 are transitioned to Physical Layer\
   *             2.  All Logical Functions allocated LF11, LF111, Join1 and the exchanges are transformed to Physical layer\
   *             3.  The Function Ports are transformed to Physical Layer\
   *             4.  Connections C1, C2, C3 are projected to Physical Layer\
   *             5.  Physical Component LC1 implements Interface1 and Interface2 in Logical Layer\
   *             6.  Physical Component LC2 uses Interface1 and Interface2 in Logical Layer\
   *             7.  Interfaces and exchange items are not transformed to Physical Layer
   * </pre>
   */
  private void leafLCTransitionTest() {
    performLCtoPCTransition(Arrays.asList(logicalSystem));
    assertTrue(NLS.bind(Messages.WrongRealization, physicalSystem.getName(), logicalSystem.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalSystem) == logicalSystem));

    // Check the interfaces are not projected
    List<Interface> pcInterfaces = pcInterfacePkg.getOwnedInterfaces();
    assertTrue(Messages.ErrorMessage, pcInterfaces.isEmpty());
    List<InterfacePkg> subPCInterfacePkgs = pcInterfacePkg.getOwnedInterfacePkgs();
    assertTrue(Messages.ErrorMessage, subPCInterfacePkgs.isEmpty());

    // Test actors are not projected
    List<PhysicalComponent> physicalActors = physicalActorPkg.getOwnedPhysicalComponents();
    assertTrue(Messages.ErrorMessage, physicalActors.size() == 1);

    leafPC = testAndGetProjectedPC(leafLC);
    pc1 = testAndGetProjectedPC(lc1);
    pc11 = testAndGetProjectedPC(lc11);
    pc12 = testAndGetProjectedPC(lc12);
    pc111 = testAndGetProjectedPC(lc111);
    pc2 = testAndGetProjectedPC(lc2);
    pc21 = testAndGetProjectedPC(lc21);
    pc22 = testAndGetProjectedPC(lc22);

    pcPkg = physicalSystem.getOwnedPhysicalComponentPkgs().get(0);
    mustNotBeNull(pcPkg);
    assertTrue(NLS.bind(Messages.WrongRealization, pcPkg.getName(), lcPkg.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pcPkg) == lcPkg));

    subPC1 = testAndGetProjectedPC(subLC1);
    subPC11 = testAndGetProjectedPC(subLC11);
    assertNotNull(leafPC);
    assertNotNull(subPC1);
    assertNotNull(subPC11);

    // Functions
    pf11 = (PhysicalFunction) testAndGetProjectedPF(lf11);

    pf11FIP = (FunctionInputPort) pf11.getInputs().get(0);
    mustNotBeNull(pf11FIP);
    assertTrue(NLS.bind(Messages.WrongRealization, pf11FIP.getName(), lf11FIP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf11FIP) == lf11FIP));

    pf111 = (PhysicalFunction) testAndGetProjectedPF(lf111);
    mustNotBeNull(pf111);
    assertTrue(NLS.bind(Messages.WrongRealization, pf111.getName(), lf111.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf111) == lf111));

    pf111FOP = (FunctionOutputPort) pf111.getOutputs().get(0);
    mustNotBeNull(pf111FOP);
    assertTrue(NLS.bind(Messages.WrongRealization, pf111FOP.getName(), lf111FOP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf111FOP) == lf111FOP));

    physicalJoin1 = testAndGetProjectedPF(logicalJoin1);

    pf111Join1Exchange = (FunctionalExchange) physicalJoin1.getIncoming().get(0);
    mustNotBeNull(pf111Join1Exchange);
    assertTrue(
        NLS.bind(Messages.WrongRealization, pf111Join1Exchange.getName(),
            lf111Join1Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf111Join1Exchange) == lf111Join1Exchange));

    join1PF11Exchange = (FunctionalExchange) physicalJoin1.getOutgoing().get(0);
    mustNotBeNull(join1PF11Exchange);
    assertTrue(
        NLS.bind(Messages.WrongRealization, join1PF11Exchange.getName(), join1LF11Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(join1PF11Exchange) == join1LF11Exchange));

    // Functional Allocation
    assertTrue(NLS.bind(Messages.WrongAllocation, pf11.getName(), pc11.getName()),
        pc11.getAllocatedFunctions().contains(pf11));
    assertTrue(NLS.bind(Messages.WrongAllocation, pf111.getName(), pc111.getName()),
        pc111.getAllocatedFunctions().contains(pf111));
    assertTrue(NLS.bind(Messages.WrongAllocation, physicalJoin1.getName(), pc12.getName()),
        pc12.getAllocatedFunctions().contains(physicalJoin1));

    // Connections
    physicalConnection1 = ProjectionTestUtils.getAllocatingComponentExchange(logicalConnection1);
    mustNotBeNull(physicalConnection1);
    assertTrue(
        NLS.bind(Messages.WrongRealization, physicalConnection1.getName(),
            logicalConnection1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalConnection1) == logicalConnection1));

     pc1OFP = ProjectionTestUtils.getRecentlyAddedComponentPort(pc1);
     mustNotBeNull(pc1OFP);
     assertTrue(NLS.bind(Messages.WrongRealization, pc1OFP.getName(), lc1OFP.getName()),
     (ProjectionTestUtils.getRealizedTargetElement(pc1OFP) == lc1OFP));

     pc2IFP = ProjectionTestUtils.getRecentlyAddedComponentPort(pc2);
     mustNotBeNull(pc2IFP);
     assertTrue(NLS.bind(Messages.WrongRealization, pc2IFP.getName(), lc2IFP.getName()),
     (ProjectionTestUtils.getRealizedTargetElement(pc2IFP) == lc2IFP));

    physicalConnection2 = ProjectionTestUtils.getAllocatingComponentExchange(logicalConnection2);
    mustNotBeNull(physicalConnection2);
    assertTrue(
        NLS.bind(Messages.WrongRealization, physicalConnection2.getName(),
            logicalConnection2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalConnection2) == logicalConnection2));

    pc11OFP = ProjectionTestUtils.getRecentlyAddedComponentPortInFeatures(pc11);
    mustNotBeNull(pc11OFP);
    assertTrue(NLS.bind(Messages.WrongRealization, pc11OFP.getName(), lc11OFP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pc11OFP) == lc11OFP));

    pc12IFP = ProjectionTestUtils.getRecentlyAddedComponentPortInFeatures(pc12);
    mustNotBeNull(pc12IFP);
    assertTrue(NLS.bind(Messages.WrongRealization, pc12IFP.getName(), lc12IFP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pc12IFP) == lc12IFP));

    physicalConnection3 = ProjectionTestUtils.getAllocatingComponentExchange(logicalConnection3);
    mustNotBeNull(physicalConnection3);
    assertTrue(
        NLS.bind(Messages.WrongRealization, physicalConnection3.getName(),
            logicalConnection3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalConnection3) == logicalConnection3));

    pc21OFP = ProjectionTestUtils.getRecentlyAddedComponentPortInFeatures(pc21);
    mustNotBeNull(pc21OFP);
    assertTrue(NLS.bind(Messages.WrongRealization, pc21OFP.getName(), lc21OFP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pc21OFP) == lc21OFP));

    pc22IFP = ProjectionTestUtils.getRecentlyAddedComponentPortInFeatures(pc22);
    mustNotBeNull(pc22IFP);
    assertTrue(NLS.bind(Messages.WrongRealization, pc22IFP.getName(), lc22IFP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pc22IFP) == lc22IFP));

    // Connection 4 shall be null
    physicalConnection4 = ProjectionTestUtils.getAllocatingComponentExchange(logicalConnection4);
    assertNull("Shall be null", physicalConnection4);

    // Interface Impl and Use
    assertTrue(NLS.bind(Messages.ErrorMessage, pc1.getName(), logicalInterface1.getName()),
        pc1.getImplementedInterfaces().contains(logicalInterface1));
    assertTrue(NLS.bind(Messages.ErrorMessage, pc1.getName(), logicalInterface2.getName()),
        pc1.getImplementedInterfaces().contains(logicalInterface2));
    assertTrue(NLS.bind(Messages.ErrorMessage, pc11.getName(), logicalInterface1.getName()),
        pc11.getImplementedInterfaces().contains(logicalInterface1));
    assertTrue(NLS.bind(Messages.ErrorMessage, pc12.getName(), logicalInterface2.getName()),
        pc12.getImplementedInterfaces().contains(logicalInterface2));

    assertTrue(NLS.bind(Messages.ErrorMessage, pc2.getName(), logicalInterface1.getName()),
        pc2.getUsedInterfaces().contains(logicalInterface1));
    assertTrue(NLS.bind(Messages.ErrorMessage, pc2.getName(), logicalInterface2.getName()),
        pc2.getUsedInterfaces().contains(logicalInterface2));
    assertTrue(NLS.bind(Messages.ErrorMessage, pc21.getName(), logicalInterface1.getName()),
        pc21.getUsedInterfaces().contains(logicalInterface1));
    assertTrue(NLS.bind(Messages.ErrorMessage, pc22.getName(), logicalInterface2.getName()),
        pc22.getUsedInterfaces().contains(logicalInterface2));
  }

  private PhysicalComponent testAndGetProjectedPC(LogicalComponent logicalComponent_p) {
    PhysicalComponent physicalComponent = (PhysicalComponent) ProjectionTestUtils
        .getAllocatingComponent(logicalComponent_p);
    mustNotBeNull(physicalComponent);
    assertTrue(
        NLS.bind(Messages.WrongRealization, physicalComponent.getName(), logicalComponent_p.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalComponent) == logicalComponent_p));
    return physicalComponent;
  }

  private AbstractFunction testAndGetProjectedPF(AbstractFunction logicalFunction_p) {
    AbstractFunction physicalFunction = ProjectionTestUtils.getAllocatingFunction(logicalFunction_p);
    mustNotBeNull(physicalFunction);
    assertTrue(
        NLS.bind(Messages.WrongRealization, physicalFunction.getName(), logicalFunction_p.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalFunction) == logicalFunction_p));
    return physicalFunction;
  }

  /**
   * Run the projection test "LC to PC Transition" from LC1 (The transition happens from the Logical Architecture -
   * instead of the selected leaf item)
   * 
   * <pre>
   * Expected Result:\
   *             1. No change occur because the transition is applied on Logical Architecture
   * </pre>
   */
  private void lc1Transition1Test() {
    performLCtoPCTransition(Arrays.asList(lc1));
    assertTrue(NLS.bind(Messages.WrongRealization, physicalSystem.getName(), logicalSystem.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalSystem) == logicalSystem));

    // Check the interfaces are not projected
    List<Interface> pcInterfaces = pcInterfacePkg.getOwnedInterfaces();
    assertTrue(Messages.ErrorMessage, pcInterfaces.isEmpty());
    List<InterfacePkg> subPCInterfacePkgs = pcInterfacePkg.getOwnedInterfacePkgs();
    assertTrue(Messages.ErrorMessage, subPCInterfacePkgs.isEmpty());

    // Test actors are not projected
    List<PhysicalComponent> physicalActors = physicalActorPkg.getOwnedPhysicalComponents();
    assertTrue(Messages.ErrorMessage, physicalActors.size() == 1);

    int projectedPCSize = 3;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPCSize),
        physicalSystem.getOwnedPhysicalComponents().size(), projectedPCSize);

    projectedPCSize = 2;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPCSize),
        pc1.getOwnedPhysicalComponents().size(), projectedPCSize);

    projectedPCSize = 2;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPCSize),
        pc2.getOwnedPhysicalComponents().size(), projectedPCSize);

    int projectedPFSize = 3;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPFSize),
        rootPF.getSubFunctions().size(), projectedPFSize);

    int projectedExchangeSize = 2;
    assertEquals(NLS.bind(Messages.WrongSize, projectedExchangeSize),
        rootPF.getOwnedFunctionalExchanges().size(), projectedExchangeSize);
  }

  /**
   * Run the projection test "LC to PC Transition" from LC1 again (The transition happens from the Logical Architecture
   * - instead of the selected leaf item)
   * 
   * <pre>
   * Expected Result:\
   *             1. No change occur because the transition is applied on Logical Architecture
   * </pre>
   */
  private void lc1Transition2Test() {
    performLCtoPCTransition(Arrays.asList(lc1));
    assertTrue(NLS.bind(Messages.WrongRealization, physicalSystem.getName(), logicalSystem.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalSystem) == logicalSystem));

    // Check the interfaces are not projected
    List<Interface> pcInterfaces = pcInterfacePkg.getOwnedInterfaces();
    assertTrue(Messages.ErrorMessage, pcInterfaces.isEmpty());
    List<InterfacePkg> subPCInterfacePkgs = pcInterfacePkg.getOwnedInterfacePkgs();
    assertTrue(Messages.ErrorMessage, subPCInterfacePkgs.isEmpty());

    // Test actors are not projected
    List<PhysicalComponent> physicalActors = physicalActorPkg.getOwnedPhysicalComponents();
    assertTrue(Messages.ErrorMessage, physicalActors.size() == 1);

    int projectedPCSize = 3;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPCSize),
        physicalSystem.getOwnedPhysicalComponents().size(), projectedPCSize);

    projectedPCSize = 2;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPCSize),
        pc1.getOwnedPhysicalComponents().size(), projectedPCSize);

    projectedPCSize = 2;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPCSize),
        pc2.getOwnedPhysicalComponents().size(), projectedPCSize);

    int projectedPFSize = 3;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPFSize),
        rootPF.getSubFunctions().size(), projectedPFSize);

    int projectedExchangeSize = 2;
    assertEquals(NLS.bind(Messages.WrongSize, projectedExchangeSize),
        rootPF.getOwnedFunctionalExchanges().size(), projectedExchangeSize);
  }

  /**
   * Run the projection test "LC to PC Transition" from Logical System with the changes explained in description \ (The
   * transition happens from the Logical Architecture - instead of the selected leaf item)
   * 
   * <pre>
   *        Make the following changes in the model\
   *                 1.  Remove LF111 (all exchanges associated with this are removed)\
   *                 2.  Add SubLC12 under SubLC1\
   *                 3.  Make LC22 implement External Interface 1 and 2\
   *        Expected Result:\
   *                 1.  PF111 still exists but the functional realization is removed\
   *                 2.  SubPC12 is added under SubPC1 which realizes SubLC12\
   *                 3.  PC22 implements External Interface1 and 2 of Logical layer
   * </pre>
   */
  private void logicalSystemTransitionTest() {
    // Remove LF111 (all exchanges associated with this are removed)
    lf111Join1Exchange.destroy();
    lf111FOP.destroy();
    lf111.destroy();
    // Add SubLC12 under SubLC1
    subLC12 = LaFactory.eINSTANCE.createLogicalComponent("SubLC 12"); //$NON-NLS-1$
    subLC1.getOwnedLogicalComponents().add(subLC12);

    Part subLC12Part = CsFactory.eINSTANCE.createPart("SubLC12:SubLC12"); //$NON-NLS-1$
    subLC1.getOwnedFeatures().add(subLC12Part);
    subLC12Part.setAbstractType(subLC12);
    // Make LC22 implement External Interface 1 and 2
    InterfaceImplementation interImpl1 = CsFactory.eINSTANCE.createInterfaceImplementation();
    interImpl1.setImplementedInterface(logicalExternalItf1);
    lc22.getOwnedInterfaceImplementations().add(interImpl1);

    InterfaceImplementation interImpl2 = CsFactory.eINSTANCE.createInterfaceImplementation();
    interImpl2.setImplementedInterface(logicalExternalItf2);
    lc22.getOwnedInterfaceImplementations().add(interImpl2);

    performLCtoPCTransition(Arrays.asList(logicalSystem));

    assertTrue(NLS.bind(Messages.WrongRealization, physicalSystem.getName(), logicalSystem.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalSystem) == logicalSystem));

    // Check the interfaces are not projected
    List<Interface> pcInterfaces = pcInterfacePkg.getOwnedInterfaces();
    assertTrue(Messages.ErrorMessage, pcInterfaces.isEmpty());
    List<InterfacePkg> subPCInterfacePkgs = pcInterfacePkg.getOwnedInterfacePkgs();
    assertTrue(Messages.ErrorMessage, subPCInterfacePkgs.isEmpty());

    // Test actors are not projected
    List<PhysicalComponent> physicalActors = physicalActorPkg.getOwnedPhysicalComponents();
    assertTrue(Messages.ErrorMessage, physicalActors.size() == 1);

    int projectedPCSize = 3;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPCSize),
        physicalSystem.getOwnedPhysicalComponents().size(), projectedPCSize);

    projectedPCSize = 2;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPCSize),
        pc1.getOwnedPhysicalComponents().size(), projectedPCSize);

    projectedPCSize = 2;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPCSize),
        pc2.getOwnedPhysicalComponents().size(), projectedPCSize);

    int projectedPFSize = 3;
    assertEquals(NLS.bind(Messages.WrongSize, projectedPFSize),
        rootPF.getSubFunctions().size(), projectedPFSize);

    int projectedExchangeSize = 2;
    assertEquals(NLS.bind(Messages.WrongSize, projectedExchangeSize),
        rootPF.getOwnedFunctionalExchanges().size(), projectedExchangeSize);

    // Test PF111
    assertNull(NLS.bind(Messages.NullError, pf111.getName(), pf111.getName()),
        ProjectionTestUtils.getRealizedTargetElement(pf111));
    assertNull(NLS.bind(Messages.NullError, pf111FOP.getName(), pf111FOP.getName()),
        ProjectionTestUtils.getRealizedTargetElement(pf111FOP));
    assertNull(NLS.bind(Messages.NullError, pf111Join1Exchange.getName(), pf111Join1Exchange.getName()),
        ProjectionTestUtils.getRealizedTargetElement(pf111Join1Exchange));

    subPC12 = (PhysicalComponent) ProjectionTestUtils.getAllocatingComponent(subLC12);
    mustNotBeNull(subPC12);
    assertTrue(NLS.bind(Messages.WrongRealization, subPC12.getName(), subLC12.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(subPC12) == subLC12));

    // Interface Impl and Use
    assertTrue(NLS.bind(Messages.ErrorMessage, pc22.getName(), logicalExternalItf1.getName()),
        pc22.getImplementedInterfaces().contains(logicalExternalItf1));
    assertTrue(NLS.bind(Messages.ErrorMessage, pc22.getName(), logicalExternalItf2.getName()),
        pc22.getImplementedInterfaces().contains(logicalExternalItf2));
  }
}
