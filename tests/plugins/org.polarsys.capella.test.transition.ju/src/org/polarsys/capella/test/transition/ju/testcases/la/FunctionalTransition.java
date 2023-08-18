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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.transition.ju.CodeHelper;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelLaPa;

/**
 * Projection Tests on "Functional Transition" from Operational Analysis to System Analysis
 * <P>
 * This is done with the model "CTX-LA-Projection". The model is created as explained below.
 * 
 * <pre>
 *    Model Used: CTX-LA-Projection 
 *       Model is created with the following elements�
 *           1.  Create a new [SFBD] Root System Function - System Function Breakdown diagram on Root System Function.
 *           2.  Create System Functions SF1 and SF2 in the diagram
 *           3.  Create System Functions SF11 and SF12 in the diagram
 *           4.  Create "Contained In" link from SF11 and SF12 to SF1 (SF1 has two sub activities, SF11 and SF12)
 *           5.  Create Actor Function AF1, a new Actor A1 is created in Root Actor Pkg which allocates AF1
 *           6.  Create Decision Function Decision1 under AF1
 *           7.  Allocate SF12 to Actor1
 *           8.  Create Functional Exchange SF1SF2Exchange between SF1 and SF2, SF2SF11Exchange between SF2 and SF11, SF11SF12Exchange between SF11 and SF12, SF12AF1Exchange between SF12 and AF1
 *           9.  Create Functional Chain with the path SF1-->SF2-->SF11-->SF12-->AF1
 *           10. Save the diagram
 * 
 * </pre>
 * 
 * And the tests are documented in {@link #performTest()} method
 */
public class FunctionalTransition extends TopDownTransitionTestCase {

  private LogicalFunction rootLF;
  private LogicalFunction lf1;
  private FunctionOutputPort lf1FOP;
  private LogicalFunction lf11;
  private FunctionOutputPort lf11FOP;
  private FunctionInputPort lf11FIP;
  private LogicalFunction lf12;
  private FunctionOutputPort lf12FOP;
  private FunctionInputPort lf12FIP;
  private FunctionalExchange lf11LF12Exchange;
  private LogicalFunction lf2;
  private FunctionOutputPort lf2FOP;
  private FunctionInputPort lf2FIP;
  private LogicalFunction af1;
  private FunctionInputPort af1FIP;
  private LogicalFunction decision1;
  private FunctionalChain functionalChain1;
  private FunctionalExchange lf1LF2Exchange;
  private FunctionalExchange lf2LF11Exchange;
  private FunctionalExchange lf12AF1Exchange;
  private LogicalComponent a1;
  private LogicalComponent actor1;
  private LogicalFunction lf3;
  private LogicalFunctionPkg lfPkg1;
  private LogicalFunction lf4;
  private LogicalFunction lf5;
  private FunctionalExchange lf4LF5Exchange;
  private ExchangeCategory laec1;

  private PhysicalFunction rootPF;
  private PhysicalComponentPkg paActorPkg;
  private PhysicalFunction pf1;
  private FunctionOutputPort pf1FOP;
  private PhysicalFunction pf11;
  private FunctionOutputPort pf11FOP;
  private FunctionInputPort pf11FIP;
  private PhysicalFunction pf12;
  private FunctionOutputPort pf12FOP;
  private FunctionInputPort pf12FIP;
  private FunctionalExchange pf11PF12Exchange;
  private PhysicalFunction pf2;
  private PhysicalFunction pf3;
  private FunctionOutputPort pf2FOP;
  private FunctionInputPort pf2FIP;
  private PhysicalFunction physicalAF1;
  private FunctionInputPort physicalAF1FIP;
  private PhysicalFunction physicalDecision1;
  private FunctionalChain physicalFunctionalChain1;
  private FunctionalExchange pf1PF2Exchange;
  private FunctionalExchange pf2PF11Exchange;
  private FunctionalExchange pf12AF1Exchange;
  private PhysicalComponent pa1;
  private PhysicalComponent physicalActor1;
  private PhysicalFunctionPkg pfPkg1;
  private PhysicalFunction pf4;
  private PhysicalFunction pf5;
  private FunctionalExchange pf4PF5Exchange;
  private ExchangeCategory paec1;

  private LogicalFunction laFinal;
  private PhysicalFunction paFinal;

  private void initSession() {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    rootLF = getObject(ModelLaPa.rootLFId);
    lf1 = getObject(ModelLaPa.lf1Id);
    lf1FOP = getObject(ModelLaPa.lf1FOPId);
    lf11 = getObject(ModelLaPa.lf11Id);
    lf11FOP = getObject(ModelLaPa.lf11FOPId);
    lf11FIP = getObject(ModelLaPa.lf11FIPId);
    lf12 = getObject(ModelLaPa.lf12Id);
    lf12FOP = getObject(ModelLaPa.lf12FOPId);
    lf12FIP = getObject(ModelLaPa.lf12FIPId);
    lf11LF12Exchange = getObject(ModelLaPa.lf11LF12ExchangeId);
    lf2 = getObject(ModelLaPa.lf2Id);
    lf3 = getObject(ModelLaPa.lf3Id);
    lf2FOP = getObject(ModelLaPa.lf2FOPId);
    lf2FIP = getObject(ModelLaPa.lf2FIPId);
    af1 = getObject(ModelLaPa.af1Id);
    af1FIP = getObject(ModelLaPa.af1FIPId);
    decision1 = getObject(ModelLaPa.decision1Id);
    functionalChain1 = getObject(ModelLaPa.functionalChain1Id);
    lf1LF2Exchange = getObject(ModelLaPa.lf1LF2ExchangeId);
    lf2LF11Exchange = getObject(ModelLaPa.lf2LF11ExchangeId);
    lf12AF1Exchange = getObject(ModelLaPa.lf12AF1ExchangeId);
    a1 = getObject(ModelLaPa.a1Id);
    actor1 = getObject(ModelLaPa.actor1Id);
    lfPkg1 = getObject(ModelLaPa.lfPkg1Id);
    lf4 = getObject(ModelLaPa.lf4Id);
    lf5 = getObject(ModelLaPa.lf5Id);
    lf4LF5Exchange = getObject(ModelLaPa.lf4LF5ExchangeId);
    laec1 = getObject(ModelLaPa.ec1Id);

    rootPF = getObject(ModelLaPa.rootPFId);
    paActorPkg = getObject(ModelLaPa.paActorPkgId);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  /**
   * Tests on "Functional Transition" Projection command should be applied:
   * 
   * <pre>
   *         1. Test on one leaf entity (SF2)
   *         2. Test on actor to check the allocated SF is also transitioned (Actor1 & SF12)
   *         3. Test on actor to check the allocated SF is also transitioned (A1 & AF1 & Decision1)
   *         3. Test on non leaf entity (ROOT SF)
   *         4. Repetition Test on Root SF without any changes
   *         5. Test on Root SF with changes
   * </pre>
   * 
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  public void performTest() throws Exception {
    initSession();
    lfPkg1TransitionTest();
    lf3TransitionTest();
    lf2TransitionTest();
    actor1TransitionTest();
    a1TransitionTest();
    rootLFTransition1Test();
    rootLFTransition2Test();
    rootLFChangeTransitionTest();
  }

  /**
   * Run the projection test "Functional Transition" from LFPkg 1
   * 
   * <pre>
   * Expected Result:\
   *             1.  LFPkg 1 Physical Functions Package is added in root physical function of Physical Analysis with realization link to LFPkg 1.
   *             2.  LF4 and LF5 Physical Functions are added in LFPkg 1 Physical Functions Package  
   *             3.  LF4LF5Exchange 1 Physical FunctionalExchange and ExchangeCategory 1 are added in root physical function of Physical Analysis
   * </pre>
   */
  private void lfPkg1TransitionTest() {
    performFunctionalTransition(Arrays.asList(lfPkg1));
    // check LFPkg 1 Physical Functions Package is created
    pfPkg1 = rootPF.getOwnedPhysicalFunctionPkgs().get(0);
    mustNotBeNull(pfPkg1);

    assertTrue(NLS.bind(Messages.RealizationError, pfPkg1.getName(), lfPkg1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pfPkg1) == lfPkg1));

    // check LF4 and LF5 Physical Functions are created
    pf4 = pfPkg1.getOwnedPhysicalFunctions().get(0);
    mustNotBeNull(pf4);
    assertTrue(NLS.bind(Messages.RealizationError, pf4.getName(), lf4.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf4) == lf4));
    FunctionKind actualKindPF4 = pf4.getKind();
    FunctionKind expectedKindPF4 = lf4.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf4.getName(),
        actualKindPF4.getName(), expectedKindPF4.getName()), actualKindPF4.equals(expectedKindPF4));

    pf5 = pfPkg1.getOwnedPhysicalFunctions().get(1);
    mustNotBeNull(pf5);
    assertTrue(NLS.bind(Messages.RealizationError, pf5.getName(), lf5.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf5) == lf5));
    FunctionKind actualKindPF5 = pf5.getKind();
    FunctionKind expectedKindPF5 = lf5.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf5.getName(),
        actualKindPF5.getName(), expectedKindPF5.getName()), actualKindPF5.equals(expectedKindPF5));

    // functional exchange
    pf4PF5Exchange = rootPF.getOwnedFunctionalExchanges().get(0);
    mustNotBeNull(pf4PF5Exchange);
    assertTrue(
        NLS.bind(Messages.WrongRealization, pf4PF5Exchange.getName(),
            lf4LF5Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf4PF5Exchange) == lf4LF5Exchange));

    // exchange category
    paec1 = ((PhysicalFunctionPkg) rootPF.eContainer()).getOwnedCategories().get(0);
    mustNotBeNull(paec1);
    assertTrue(NLS.bind(Messages.WrongRealization, paec1.getName(), laec1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paec1) == laec1));
    assertTrue(NLS.bind(Messages.WrongSize, paec1.getName()),
        paec1.getExchanges().size() == 1);
    assertTrue(NLS.bind(Messages.ErrorMessage, paec1.getName(), pf4PF5Exchange.getName()),
        paec1.getExchanges().contains(pf4PF5Exchange));
  }

  /**
   * Run the projection test "Functional Transition" from LF3
   * 
   * <pre>
   * Expected Result:\
   *             1. PF3 Physical function is added in root Physical function of Physical Architecture with realization link to LF3.\
   *             2.  The size of created Physical functions is 1
   * </pre>
   */
  private void lf3TransitionTest() {
    performFunctionalTransition(Arrays.asList(lf3));
    pf3 = (PhysicalFunction) ProjectionTestUtils.getRecentlyAddedFunction(rootPF);
    // Verify the size of functions created after projection
    assertTrue(NLS.bind(Messages.WrongSize, rootPF.getName(), 1),
        (rootPF.getOwnedFunctions().size() == 1));
    FunctionRealization realization = pf3.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf3.getName(), lf3.getName()),
        realization.getAllocatedFunction() == lf3);
    FunctionKind actualKindPF3 = pf3.getKind();
    FunctionKind expectedKindPF3 = lf3.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf3.getName(),
        actualKindPF3.getName(), expectedKindPF3.getName()), actualKindPF3.equals(expectedKindPF3));
  }

  /**
   * Run the projection test "Functional Transition" from LF2
   * 
   * <pre>
   * Expected Result:\
   *             1. PF2 Physical function is added in root Physical function of Physical Architecture with realization link to LF2.\
   *             2.  PF1 is created in root PF with realization link to LF1\
   *             3.  PF11 is created under PF1 with realization link to LF11\
   *             4.  PF1PF2Exchange and PF2PF11Exchange are projected to Physical Layer\
   *             5.  The size of created Physical functions under root PF is 2
   * </pre>
   */
  private void lf2TransitionTest() {
    performFunctionalTransition(Arrays.asList(lf2));
    pf2 = (PhysicalFunction) ProjectionTestUtils.getAllocatingFunction(lf2);
    // Verify the size of functions created after projection
    assertTrue(NLS.bind(Messages.WrongSize, rootPF.getName(), 3),
        (rootPF.getOwnedFunctions().size() == 3));
    FunctionRealization realization = pf2.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf2.getName(), lf2.getName()),
        realization.getAllocatedFunction() == lf2);
    FunctionKind actualKindPF2 = pf2.getKind();
    FunctionKind expectedKindPF2 = lf2.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf2.getName(),
        actualKindPF2.getName(), expectedKindPF2.getName()), actualKindPF2.equals(expectedKindPF2));

    pf1 = (PhysicalFunction) ProjectionTestUtils.getAllocatingFunction(lf1);
    // Verify the size of functions created after projection
    assertTrue(NLS.bind(Messages.WrongSize, rootPF.getName(), 3),
        (rootPF.getOwnedFunctions().size() == 3));
    realization = pf1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf1.getName(), lf1.getName()),
        realization.getAllocatedFunction() == lf1);
    FunctionKind actualKindPF1 = pf1.getKind();
    FunctionKind expectedKindPF1 = lf1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf1.getName(),
        actualKindPF1.getName(), expectedKindPF1.getName()), actualKindPF1.equals(expectedKindPF1));

    pf11 = (PhysicalFunction) ProjectionTestUtils.getRecentlyAddedFunction(pf1);
    // Verify the size of functions created after projection
    assertTrue(NLS.bind(Messages.WrongSize, pf1.getName(), 1),
        (pf1.getOwnedFunctions().size() == 1));
    realization = pf11.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf11.getName(), lf11.getName()),
        realization.getAllocatedFunction() == lf11);
    FunctionKind actualKindPF11 = pf11.getKind();
    FunctionKind expectedKindPF11 = lf11.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf11.getName(),
        actualKindPF11.getName(), expectedKindPF11.getName()), actualKindPF11.equals(expectedKindPF11));

    // Functional Exchanges
    pf1PF2Exchange = rootPF.getOwnedFunctionalExchanges().get(1);
    assertTrue(
        NLS.bind(Messages.WrongRealization, pf1PF2Exchange.getName(),
            lf1LF2Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf1PF2Exchange) == lf1LF2Exchange));

    pf2PF11Exchange = rootPF.getOwnedFunctionalExchanges().get(2);
    assertTrue(
        NLS.bind(Messages.WrongRealization, pf2PF11Exchange.getName(),
            lf2LF11Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf2PF11Exchange) == lf2LF11Exchange));
  }

  /**
   * Run the projection test "Actor Transition" from Actor1
   * 
   * <pre>
   * Expected Result:\
   *             1. Actor1 is created in PA Layer\
   *             2.  PF12 is also projected towards PA Layer under Root Physical Function
   * </pre>
   */
  private void actor1TransitionTest() {
    performActorTransition(Arrays.asList(actor1));
    physicalActor1 = ProjectionTestUtils.getRecentlyCreatedPhysicalActor(paActorPkg);
    assertTrue(
        NLS.bind(Messages.WrongRealization, physicalActor1.getName(), actor1.getName()),
        (ProjectionTestUtils.getAllocatedComponent(physicalActor1) == actor1));

    pf12 = (PhysicalFunction) ProjectionTestUtils.getRecentlyAddedFunction(pf1);
    // Verify the size of functions created after projection
    assertTrue(NLS.bind(Messages.WrongSize, rootPF.getName(), 4),
        (rootPF.getOwnedFunctions().size() == 4));
    FunctionRealization realization = pf12.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf12.getName(), lf12.getName()),
        realization.getAllocatedFunction() == lf12);
    FunctionKind actualKindPF12 = pf12.getKind();
    FunctionKind expectedKindPF12 = lf12.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf12.getName(),
        actualKindPF12.getName(), expectedKindPF12.getName()), actualKindPF12.equals(expectedKindPF12));

    assertTrue(
        NLS.bind(Messages.ErrorMessage, pf12.getName(), physicalActor1.getName()),
        physicalActor1.getAllocatedFunctions().contains(pf12));
  }

  /**
   * Run the projection test "Actor Transition" from A1
   * 
   * <pre>
   * Expected Result:\
   *             1. A1 is created in PA Layer\
   *             2.  AF1 is also projected towards PA Layer under Root Physical Function\
   *             3.  Decision1 is projected under AF1 in Physical layer
   * </pre>
   */
  private void a1TransitionTest() {
    performActorTransition(Arrays.asList(a1));
    pa1 = (PhysicalComponent) ProjectionTestUtils.getAllocatingComponent(a1);
    assertTrue(NLS.bind(Messages.ErrorMessage, pa1.getName(), paActorPkg.getName()),
        pa1.eContainer() == paActorPkg);
    assertTrue(NLS.bind(Messages.WrongRealization, pa1.getName(), a1.getName()),
        (ProjectionTestUtils.getAllocatedComponent(pa1) == a1));

    physicalAF1 = (PhysicalFunction) ProjectionTestUtils.getRecentlyAddedFunction(rootPF);
    physicalDecision1 = (PhysicalFunction) ProjectionTestUtils.getRecentlyAddedFunction(physicalAF1);
    // Verify the size of functions created after projection
    assertTrue(NLS.bind(Messages.WrongSize, rootPF.getName(), 4),
        (rootPF.getOwnedFunctions().size() == 4));
    FunctionKind actualKind = physicalDecision1.getKind();
    FunctionKind expectedKind = decision1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, physicalDecision1.getName(),
        actualKind.getName(), expectedKind.getName()), actualKind.equals(expectedKind));

    FunctionRealization realization = physicalAF1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, physicalAF1.getName(), af1.getName()),
        realization.getAllocatedFunction() == af1);

    FunctionRealization decisionRealization = physicalDecision1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, physicalDecision1.getName(),
        decision1.getName()), decisionRealization.getAllocatedFunction() == decision1);

    assertTrue(NLS.bind(Messages.ErrorMessage, physicalAF1.getName(), pa1.getName()),
        pa1.getAllocatedFunctions().contains(physicalAF1));
  }

  /**
   * Run the projection test "Functional Transition" from Root LF
   * 
   * <pre>
   * Expected Result:\
   *               1. Physical Function PF1 is added in root Physical function of Physical Architecture.\
   *               2.  Physical Functions PF11 and PF12 are created under PF1 (with corresponding realization links)\
   *               3.  PF12 moves from root PF to PF1\
   *               4.  Functional Exchange PF1PF2Exchange is created between PF1 & PF2\
   *               5.  Functional Exchange PF2PF11Exchange is created between PF2 & PF11\
   *               6.  Functional Exchange PF11PF12Exchange is created between PF11 & PF12\
   *               7.  Functional chain is created with the path PF1-->PF2-->PF11-->PF12-->AF1
   * 
   * </pre>
   */
  private void rootLFTransition1Test() {
    performFunctionalTransition(Arrays.asList(rootLF));
    // Physical Functions SF1 is added in root logical function of System Analysis.
    pf1 = (PhysicalFunction) ProjectionTestUtils.getAllocatingFunction(lf1);
    FunctionRealization realization = pf1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf1.getName(), lf1.getName()),
        realization.getAllocatedFunction() == lf1);
    FunctionKind actualKindPF1 = pf1.getKind();
    FunctionKind expectedKindPF1 = lf1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf1.getName(),
        actualKindPF1.getName(), expectedKindPF1.getName()), actualKindPF1.equals(expectedKindPF1));

    // PhysicalFunctions OA11 and OA12 are created under OA1 (with corresponding realization links)
    pf11 = (PhysicalFunction) pf1.getSubFunctions().get(0);
    FunctionRealization realization11 = pf11.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf11.getName(), lf11.getName()),
        realization11.getAllocatedFunction() == lf11);
    FunctionKind actualKindPF11 = pf11.getKind();
    FunctionKind expectedKindPF11 = lf11.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf11.getName(),
        actualKindPF11.getName(), expectedKindPF11.getName()), actualKindPF11.equals(expectedKindPF11));

    // Physical Functions OA11 and OA12 are created under OA1 (with corresponding realization links)
    pf12 = (PhysicalFunction) pf1.getSubFunctions().get(1);
    FunctionRealization realization12 = pf12.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf12.getName(), pf12.getName()),
        realization12.getAllocatedFunction() == lf12);
    FunctionKind actualKindPF12 = pf12.getKind();
    FunctionKind expectedKindPF12 = lf12.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf12.getName(),
        actualKindPF12.getName(), expectedKindPF12.getName()), actualKindPF12.equals(expectedKindPF12));

    assertTrue(NLS.bind(Messages.ErrorMessage, pf12.getName(), pf1.getName()),
        (pf12.eContainer() == pf1));

    // Allocation still exists
    assertTrue(
        NLS.bind(Messages.ErrorMessage, pf12.getName(), physicalActor1.getName()),
        physicalActor1.getAllocatedFunctions().contains(pf12));

    // Functional Exchanges
    pf1PF2Exchange = rootPF.getOwnedFunctionalExchanges().get(1);
    assertTrue(
        NLS.bind(Messages.WrongRealization, pf1PF2Exchange.getName(),
            lf1LF2Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf1PF2Exchange) == lf1LF2Exchange));

    pf2PF11Exchange = rootPF.getOwnedFunctionalExchanges().get(2);
    assertTrue(
        NLS.bind(Messages.WrongRealization, pf2PF11Exchange.getName(),
            lf2LF11Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf2PF11Exchange) == lf2LF11Exchange));

    pf11PF12Exchange = pf1.getOwnedFunctionalExchanges().get(0);
    assertTrue(
        NLS.bind(Messages.WrongRealization, pf11PF12Exchange.getName(),
            lf11LF12Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf11PF12Exchange) == lf11LF12Exchange));

    pf12AF1Exchange = (FunctionalExchange) CodeHelper.getChildTracingElement(rootPF, lf12AF1Exchange);
    assertTrue(
        NLS.bind(Messages.WrongRealization, pf12AF1Exchange.getName(),
            lf12AF1Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf12AF1Exchange) == lf12AF1Exchange));

    physicalFunctionalChain1 = rootPF.getOwnedFunctionalChains().get(0);
    assertTrue(
        NLS.bind(Messages.WrongRealization, physicalFunctionalChain1.getName(),
            functionalChain1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalFunctionalChain1) == functionalChain1));

    assertTrue(NLS.bind(Messages.ErrorMessage,
        physicalFunctionalChain1.getName(), pf1.getName()),
        physicalFunctionalChain1.getInvolvedFunctions().contains(pf1));
    assertTrue(NLS.bind(Messages.ErrorMessage,
        physicalFunctionalChain1.getName(), pf2.getName()),
        physicalFunctionalChain1.getInvolvedFunctions().contains(pf2));
    assertTrue(NLS.bind(Messages.ErrorMessage,
        physicalFunctionalChain1.getName(), pf11.getName()),
        physicalFunctionalChain1.getInvolvedFunctions().contains(pf11));
    assertTrue(NLS.bind(Messages.ErrorMessage,
        physicalFunctionalChain1.getName(), pf12.getName()),
        physicalFunctionalChain1.getInvolvedFunctions().contains(pf12));
    assertTrue(NLS.bind(Messages.ErrorMessage,
        physicalFunctionalChain1.getName(), physicalAF1.getName()),
        physicalFunctionalChain1.getInvolvedFunctions().contains(physicalAF1));

    // Ports
    pf1FOP = (FunctionOutputPort) pf1.getOutputs().get(0);
    mustNotBeNull(pf1FOP);
    assertTrue(NLS.bind(Messages.RealizationError, pf1FOP.getName(), lf1FOP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf1FOP) == lf1FOP));

    pf11FOP = (FunctionOutputPort) pf11.getOutputs().get(0);
    mustNotBeNull(pf11FOP);
    assertTrue(NLS.bind(Messages.RealizationError, pf11FOP.getName(), lf11FOP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf11FOP) == lf11FOP));

    pf11FIP = (FunctionInputPort) pf11.getInputs().get(0);
    mustNotBeNull(pf11FIP);
    assertTrue(NLS.bind(Messages.RealizationError, pf11FIP.getName(), lf11FIP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf11FIP) == lf11FIP));

    pf2FOP = (FunctionOutputPort) pf2.getOutputs().get(0);
    mustNotBeNull(pf2FOP);
    assertTrue(NLS.bind(Messages.RealizationError, pf2FOP.getName(), lf2FOP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf2FOP) == lf2FOP));

    pf2FIP = (FunctionInputPort) pf2.getInputs().get(0);
    mustNotBeNull(pf2FIP);
    assertTrue(NLS.bind(Messages.RealizationError, pf2FIP.getName(), lf2FIP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf2FIP) == lf2FIP));

    pf12FOP = (FunctionOutputPort) pf12.getOutputs().get(0);
    mustNotBeNull(pf12FOP);
    assertTrue(NLS.bind(Messages.RealizationError, pf12FOP.getName(), lf12FOP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf12FOP) == lf12FOP));

    pf12FIP = (FunctionInputPort) pf12.getInputs().get(0);
    mustNotBeNull(pf12FIP);
    assertTrue(NLS.bind(Messages.RealizationError, pf12FIP.getName(), lf12FIP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(pf12FIP) == lf12FIP));

    physicalAF1FIP = (FunctionInputPort) physicalAF1.getInputs().get(0);
    mustNotBeNull(physicalAF1FIP);
    assertTrue(NLS.bind(Messages.RealizationError, physicalAF1FIP.getName(), af1FIP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(physicalAF1FIP) == af1FIP));
  }

  /**
   * Run the projection test "Functional Transition" from Root LF
   * 
   * <pre>
   * Expected Result:\
   *             1. No new Physical functions are created / projected in Root Physical Functions
   * </pre>
   */
  private void rootLFTransition2Test() {
    performFunctionalTransition(Arrays.asList(rootLF));
    assertTrue(NLS.bind(Messages.WrongSize, rootPF.getName(), 4),
        (rootPF.getOwnedFunctions().size() == 4));
    assertTrue(NLS.bind(Messages.WrongSize, pf1.getName(), 2),
        (pf1.getOwnedFunctions().size() == 2));
  }

  /**
   * Run the projection test "Functional Transition" from Root LF (with the changes in desc)
   * 
   * <pre>
   * Make the following changes in Root System Function\
   *             1. Remove Physical Function PF1 from root PF\
   *             2.  Add a Final Function to PF2 in Logical Layer\
   * Expected Result:\
   *                 1.  Logical Function PF1 is re-projected with its children and appropriate realization links.\
   *                 2.  Physical Function PF2 is created with a child Final Function and realizations are added appropriately.
   * </pre>
   */
  private void rootLFChangeTransitionTest() {
    // Remove System Function SF1 from root SF
    pf1.destroy();
    // Add a Fork Function to OA2 in operational analysis
    laFinal = LaFactory.eINSTANCE.createLogicalFunction("Final1"); //$NON-NLS-1$
    lf2.getOwnedFunctions().add(laFinal);
    performFunctionalTransition(Arrays.asList(rootLF));
    assertTrue(NLS.bind(Messages.WrongSize, rootPF.getName(), 4),
        (rootLF.getOwnedFunctions().size() == 4));
    assertTrue(NLS.bind(Messages.WrongSize, pf1.getName(), 2),
        (pf1.getOwnedFunctions().size() == 2));
    // Physical Functions SF1 is added in root logical function of System Analysis.
    pf1 = (PhysicalFunction) CodeHelper.getChildTracingElement(rootPF, lf1);
    FunctionRealization realization = pf1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf1.getName(), lf1.getName()),
        realization.getAllocatedFunction() == lf1);
    FunctionKind actualKindPF1 = pf1.getKind();
    FunctionKind expectedKindPF1 = lf1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf1.getName(),
        actualKindPF1.getName(), expectedKindPF1.getName()), actualKindPF1.equals(expectedKindPF1));

    // PhysicalFunctions OA11 and OA12 are created under OA1 (with corresponding realization links)
    pf11 = (PhysicalFunction) pf1.getSubFunctions().get(0);
    FunctionRealization realization11 = pf11.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf11.getName(), lf11.getName()),
        realization11.getAllocatedFunction() == lf11);
    FunctionKind actualKindPF11 = pf11.getKind();
    FunctionKind expectedKindPF11 = lf11.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf11.getName(),
        actualKindPF11.getName(), expectedKindPF11.getName()), actualKindPF11.equals(expectedKindPF11));

    // Physical Functions OA11 and OA12 are created under OA1 (with corresponding realization links)
    pf12 = (PhysicalFunction) pf1.getSubFunctions().get(1);
    FunctionRealization realization12 = pf12.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, pf12.getName(), pf12.getName()),
        realization12.getAllocatedFunction() == lf12);
    FunctionKind actualKindPF12 = pf12.getKind();
    FunctionKind expectedKindPF12 = lf12.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, pf12.getName(),
        actualKindPF12.getName(), expectedKindPF12.getName()), actualKindPF12.equals(expectedKindPF12));

    paFinal = (PhysicalFunction) ProjectionTestUtils.getRecentlyAddedFunction(pf2);
    FunctionRealization realizationFork1 = paFinal.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, paFinal.getName(), laFinal.getName()),
        realizationFork1.getAllocatedFunction() == laFinal);
    FunctionKind actualKindFinal = paFinal.getKind();
    FunctionKind expectedKindFinal = laFinal.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, paFinal.getName(),
        actualKindFinal.getName(), expectedKindFinal.getName()), actualKindFinal.equals(expectedKindFinal));

    // Allocation of Ctx Actor is removed still exists
    assertFalse(
        NLS.bind(Messages.ErrorMessage, pf12.getName(), physicalActor1.getName()),
        physicalActor1.getAllocatedFunctions().contains(pf12));
  }
}
