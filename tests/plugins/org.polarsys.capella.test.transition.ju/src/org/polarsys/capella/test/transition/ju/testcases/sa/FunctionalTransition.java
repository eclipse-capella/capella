/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.test.transition.ju.CodeHelper;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelCtxLa;

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
 * 
 */
public class FunctionalTransition extends TopDownTransitionTestCase {

  private SystemFunction _rootSF;
  private SystemFunction _sf1;
  private FunctionOutputPort _sf1FOP;
  private SystemFunction _sf11;
  private FunctionOutputPort _sf11FOP;
  private FunctionInputPort _sf11FIP;
  private SystemFunction _sf12;
  private FunctionOutputPort _sf12FOP;
  private FunctionInputPort _sf12FIP;
  private FunctionalExchange _sf11SF12Exchange;
  private SystemFunction _sf2;
  private FunctionOutputPort _sf2FOP;
  private FunctionInputPort _sf2FIP;
  private SystemFunction _af1;
  private FunctionInputPort _af1FIP;
  private SystemFunction _decision1;
  private FunctionalChain _functionalChain1;
  private FunctionalExchange _sf1SF2Exchange;
  private FunctionalExchange _sf2SF11Exchange;
  private FunctionalExchange _sf12AF1Exchange;
  private SystemComponent _a1;
  private SystemComponent _actor1;
  private SystemFunctionPkg _sfPkg1;
  private SystemFunction _sf3;
  private SystemFunction _sf4;
  private FunctionalExchange _sf3SF4Exchange;
  private ExchangeCategory _saec1;

  private LogicalFunction _rootLF;
  private LogicalComponentPkg _laActorPkg;
  private LogicalFunction _lf1;
  private FunctionOutputPort _lf1FOP;
  private LogicalFunction _lf11;
  private FunctionOutputPort _lf11FOP;
  private FunctionInputPort _lf11FIP;
  private LogicalFunction _lf12;
  private FunctionOutputPort _lf12FOP;
  private FunctionInputPort _lf12FIP;
  private FunctionalExchange _lf11LF12Exchange;
  private LogicalFunction _lf2;
  private FunctionOutputPort _lf2FOP;
  private FunctionInputPort _lf2FIP;
  private LogicalFunction _logicalAF1;
  private FunctionInputPort _logicalAF1FIP;
  private LogicalFunction _logicalDecision1;
  private FunctionalChain _logicalFunctionalChain1;
  private FunctionalExchange _lf1LF2Exchange;
  private FunctionalExchange _lf2LF11Exchange;
  private FunctionalExchange _lf12AF1Exchange;
  private LogicalComponent _la1;
  private LogicalComponent _logicalActor1;
  private LogicalFunctionPkg _lfPkg1;
  private LogicalFunction _lf3;
  private LogicalFunction _lf4;
  private FunctionalExchange _lf3LF4Exchange;
  private ExchangeCategory _laec1;

  private SystemFunction _ctxFinal;
  private LogicalFunction _laFinal;

  /**
   * Run the projection test "Functional Transition" from SFPkg 1
   * 
   * <pre>
   * Expected Result:\
   *             1.  SFPkg 1 Logical Functions Package is added in root logical function of Logical Analysis with realization link to SFPkg 1.
   *             2.  SF3 and SF4 Logical Functions are added in SFPkg 1 Logical Functions Package  
   *             3.  SF3SF4Exchange 1 Logical FunctionalExchange and ExchangeCategory 1 are added in root logical function of Logical Analysis
   * </pre>
   */

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {
    // Assign the objects
    _rootSF = (SystemFunction) getObject(ModelCtxLa.rootSFId);
    _sf1 = (SystemFunction) getObject(ModelCtxLa.sf1Id);
    _sf1FOP = (FunctionOutputPort) getObject(ModelCtxLa.sf1FOPId);
    _sf11 = (SystemFunction) getObject(ModelCtxLa.sf11Id);
    _sf11FOP = (FunctionOutputPort) getObject(ModelCtxLa.sf11FOPId);
    _sf11FIP = (FunctionInputPort) getObject(ModelCtxLa.sf11FIPId);
    _sf12 = (SystemFunction) getObject(ModelCtxLa.sf12Id);
    _sf12FOP = (FunctionOutputPort) getObject(ModelCtxLa.sf12FOPId);
    _sf12FIP = (FunctionInputPort) getObject(ModelCtxLa.sf12FIPId);
    _sf11SF12Exchange = (FunctionalExchange) getObject(ModelCtxLa.sf11SF12ExchangeId);
    _sf2 = (SystemFunction) getObject(ModelCtxLa.sf2Id);
    _sf2FOP = (FunctionOutputPort) getObject(ModelCtxLa.sf2FOPId);
    _sf2FIP = (FunctionInputPort) getObject(ModelCtxLa.sf2FIPId);
    _af1 = (SystemFunction) getObject(ModelCtxLa.af1Id);
    _af1FIP = (FunctionInputPort) getObject(ModelCtxLa.af1FIPId);
    _decision1 = (SystemFunction) getObject(ModelCtxLa.decision1Id);
    _functionalChain1 = (FunctionalChain) getObject(ModelCtxLa.functionalChain1Id);
    _sf1SF2Exchange = (FunctionalExchange) getObject(ModelCtxLa.sf1SF2ExchangeId);
    _sf2SF11Exchange = (FunctionalExchange) getObject(ModelCtxLa.sf2SF11ExchangeId);
    _sf12AF1Exchange = (FunctionalExchange) getObject(ModelCtxLa.sf12AF1ExchangeId);
    _a1 = (SystemComponent) getObject(ModelCtxLa.a1Id);
    _actor1 = (SystemComponent) getObject(ModelCtxLa.actor1Id);
    _sfPkg1 = (SystemFunctionPkg) getObject(ModelCtxLa.sfPkg1Id);
    _sf3 = (SystemFunction) getObject(ModelCtxLa.sf3Id);
    _sf4 = (SystemFunction) getObject(ModelCtxLa.sf4Id);
    _sf3SF4Exchange = (FunctionalExchange) getObject(ModelCtxLa.sf3SF4ExchangeId);
    _saec1 = (ExchangeCategory) getObject(ModelCtxLa.ec1Id);

    _rootLF = (LogicalFunction) getObject(ModelCtxLa.rootLFId);
    _laActorPkg = (LogicalComponentPkg) getObject(ModelCtxLa.laActorPkgId);

    performTest1();
    performTest2();
    performTest3();
    performTest4();
    performTest5();
    performTest6();
    performTest7();

  }

  public void performTest1() throws Exception {
    performFunctionalTransition(Collections.singletonList(_sfPkg1));

    // check SFPkg 1 Logical Functions Package is created
    _lfPkg1 = _rootLF.getOwnedLogicalFunctionPkgs().get(0);
    mustNotBeNull(_lfPkg1);

    assertTrue(NLS.bind(Messages.RealizationError, _lfPkg1.getName(), _sfPkg1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lfPkg1) == _sfPkg1));

    // check SF3 and SF4 Logical Functions are created
    _lf3 = _lfPkg1.getOwnedLogicalFunctions().get(0);
    mustNotBeNull(_lf3);
    assertTrue(NLS.bind(Messages.RealizationError, _lf3.getName(), _sf3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf3) == _sf3));
    FunctionKind actualKindLF3 = _lf3.getKind();
    FunctionKind expectedKindLF3 = _sf3.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf3.getName(),
        actualKindLF3.getName(), expectedKindLF3.getName()), actualKindLF3.equals(expectedKindLF3));

    _lf4 = _lfPkg1.getOwnedLogicalFunctions().get(1);
    mustNotBeNull(_lf4);
    assertTrue(NLS.bind(Messages.RealizationError, _lf4.getName(), _sf4.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf4) == _sf4));
    FunctionKind actualKindLF4 = _lf4.getKind();
    FunctionKind expectedKindLF4 = _sf4.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf4.getName(),
        actualKindLF4.getName(), expectedKindLF4.getName()), actualKindLF4.equals(expectedKindLF4));

    // functional exchange
    _lf3LF4Exchange = _rootLF.getOwnedFunctionalExchanges().get(1);
    mustNotBeNull(_lf3LF4Exchange);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _lf3LF4Exchange.getName(),
            _sf3SF4Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf3LF4Exchange) == _sf3SF4Exchange));

    // exchange category
    _laec1 = ((LogicalFunctionPkg) _rootLF.eContainer()).getOwnedCategories().get(0);
    mustNotBeNull(_laec1);
    assertTrue(NLS.bind(Messages.WrongRealization, _laec1.getName(), _saec1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laec1) == _saec1));
    assertTrue(NLS.bind(Messages.WrongSize, _laec1.getName()),
        _laec1.getExchanges().size() == 1);
    assertTrue(
        NLS.bind(Messages.ErrorMessage, _laec1.getName(), _lf3LF4Exchange.getName()),
        _laec1.getExchanges().contains(_lf3LF4Exchange));
  }

  /**
   * Run the projection test "Functional Transition" from SF2
   * 
   * <pre>
   * Expected Result:\
   *             1. SF2 logical function is added in root logical function of Logical Architecture with realization link to SF2.\
   *             2. The size of created logical functions is 1
   * </pre>
   */

  public void performTest2() throws Exception {
    performFunctionalTransition(Collections.singletonList(_sf2));

    // Verify the size of functions created after projection
    assertEquals(NLS.bind(Messages.WrongSize, _rootLF.getName(), 2), 2,
        _rootLF.getOwnedFunctions().size());

    _lf2 = (LogicalFunction) ProjectionTestUtils.getAllocatingFunction(_sf2);
    mustNotBeNull(_lf2);
    FunctionRealization realization = _lf2.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _lf2.getName(), _sf2.getName()),
        realization.getAllocatedFunction() == _sf2);
    FunctionKind actualKindLF2 = _lf2.getKind();
    FunctionKind expectedKindLF2 = _sf2.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf2.getName(),
        actualKindLF2.getName(), expectedKindLF2.getName()), actualKindLF2.equals(expectedKindLF2));

    _lf1 = (LogicalFunction) ProjectionTestUtils.getAllocatingFunction(_sf1);
    mustNotBeNull(_lf1);
    FunctionRealization realization1 = _lf1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _lf1.getName(), _sf1.getName()),
        realization1.getAllocatedFunction() == _sf1);
    FunctionKind actualKindLF1 = _lf1.getKind();
    FunctionKind expectedKindLF1 = _sf1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf1.getName(),
        actualKindLF1.getName(), expectedKindLF1.getName()), actualKindLF1.equals(expectedKindLF1));
  }

  /**
   * Run the projection test "Actor Transition" from Actor1
   * 
   * <pre>
   * Expected Result:\
   *             1. Actor1 is created in LA Layer\
   *             2.  LF12 is also projected towards LA Layer under Root Logical Function
   * </pre>
   */

  public void performTest3() throws Exception {
    performActorTransition(Collections.singletonList(_actor1));

    _logicalActor1 = ProjectionTestUtils.getRecentlyCreatedLogicalActor(_laActorPkg);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _logicalActor1.getName(), _actor1.getName()),
        (ProjectionTestUtils.getAllocatedComponent(_logicalActor1) == _actor1));

    _lf12 = (LogicalFunction) ProjectionTestUtils.getRecentlyAddedFunction(_lf1);
    // Verify the size of functions created after projection
    assertEquals(NLS.bind(Messages.WrongSize, _rootLF.getName(), 3), 3,
        _rootLF.getOwnedFunctions().size());
    FunctionRealization realization = _lf12.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _lf12.getName(), _sf12.getName()),
        realization.getAllocatedFunction() == _sf12);

    FunctionKind actualKindLF12 = _lf12.getKind();
    FunctionKind expectedKindLF12 = _sf12.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf12.getName(),
        actualKindLF12.getName(), expectedKindLF12.getName()), actualKindLF12.equals(expectedKindLF12));

    assertTrue(
        NLS.bind(Messages.ErrorMessage, _lf12.getName(), _logicalActor1.getName()),
        _logicalActor1.getAllocatedFunctions().contains(_lf12));
  }

  /**
   * Run the projection test "Actor Transition" from A1
   * 
   * <pre>
   * Expected Result:\
   *             1. A1 is created in LA Layer\
   *             2.  AF1 is also projected towards LA Layer under Root Logical Function\
   *             3.  Decision1 is projected under AF1 in logical layer
   * </pre>
   */

  public void performTest4() throws Exception {
    performActorTransition(Collections.singletonList(_a1));

    _la1 = ProjectionTestUtils.getRecentlyCreatedLogicalActor(_laActorPkg);
    assertTrue(NLS.bind(Messages.WrongRealization, _la1.getName(), _a1.getName()),
        (ProjectionTestUtils.getAllocatedComponent(_la1) == _a1));

    _logicalAF1 = (LogicalFunction) ProjectionTestUtils.getRecentlyAddedFunction(_rootLF);
    _logicalDecision1 = (LogicalFunction) ProjectionTestUtils.getRecentlyAddedFunction(_logicalAF1);
    // Verify the size of functions created after projection
    assertTrue(NLS.bind(Messages.WrongSize, _rootLF.getName(), 3),
        (_rootLF.getOwnedFunctions().size() == 3));
    FunctionKind actualKind = _logicalDecision1.getKind();
    FunctionKind expectedKind = _decision1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _logicalDecision1.getName(),
        actualKind.getName(), expectedKind.getName()), actualKind.equals(expectedKind));

    FunctionRealization realization = _logicalAF1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _logicalAF1.getName(), _af1.getName()),
        realization.getAllocatedFunction() == _af1);

    FunctionRealization decisionRealization = _logicalDecision1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _logicalDecision1.getName(),
        _decision1.getName()), decisionRealization.getAllocatedFunction() == _decision1);

    assertTrue(NLS.bind(Messages.ErrorMessage, _logicalAF1.getName(), _la1.getName()),
        _la1.getAllocatedFunctions().contains(_logicalAF1));
  }

  /**
   * Run the projection test "Functional Transition" from Root SF
   * 
   * <pre>
   * Expected Result:\
   *               1. Logical Function LF1 is added in root logical function of Logical Architecture.\
   *               2.  Logical Functions LF11 and LF12 are created under LF1 (with corresponding realization links)\
   *               3.  LF12 moves from root LF to LF1\
   *               4.  Functional Exchange LF1LF2Exchange is created between LF1 & LF2\
   *               5.  Functional Exchange LF2LF11Exchange is created between LF2 & LF11\
   *               6.  Functional Exchange LF11LF12Exchange is created between LF11 & LF12\
   *               7.  Functional chain is created with the path LF1-->LF2-->LF11-->LF12-->AF1
   * 
   * </pre>
   */

  public void performTest5() throws Exception {
    performFunctionalTransition(Collections.singletonList(_rootSF));
    // return Collections.singletonList((EObject) _rootSF);

    // performFunctionalTransition(Collections.singletonList();

    // Logical Functions SF1 is added in root logical function of System Analysis.
    _lf1 = (LogicalFunction) ProjectionTestUtils.getAllocatingFunction(_sf1);
    FunctionRealization realization = _lf1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _lf1.getName(), _sf1.getName()),
        realization.getAllocatedFunction() == _sf1);
    FunctionKind actualKindLF1 = _lf1.getKind();
    FunctionKind expectedKindLF1 = _sf1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf1.getName(),
        actualKindLF1.getName(), expectedKindLF1.getName()), actualKindLF1.equals(expectedKindLF1));

    // LogicalFunctions OA11 and OA12 are created under OA1 (with corresponding realization links)
    _lf11 = (LogicalFunction) _lf1.getSubFunctions().get(0);
    FunctionRealization realization11 = _lf11.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _lf11.getName(), _sf11.getName()),
        realization11.getAllocatedFunction() == _sf11);
    FunctionKind actualKindLF11 = _lf11.getKind();
    FunctionKind expectedKindLF11 = _sf11.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf11.getName(),
        actualKindLF11.getName(), expectedKindLF11.getName()), actualKindLF11.equals(expectedKindLF11));

    // Logical Functions OA11 and OA12 are created under OA1 (with corresponding realization links)
    _lf12 = (LogicalFunction) _lf1.getSubFunctions().get(1);
    FunctionRealization realization12 = _lf12.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _lf12.getName(), _lf12.getName()),
        realization12.getAllocatedFunction() == _sf12);

    FunctionKind actualKindLF12 = _lf12.getKind();
    FunctionKind expectedKindLF12 = _sf12.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf12.getName(),
        actualKindLF12.getName(), expectedKindLF12.getName()), actualKindLF12.equals(expectedKindLF12));

    assertTrue(NLS.bind(Messages.ErrorMessage, _lf12.getName(), _lf1.getName()),
        (_lf12.eContainer() == _lf1));

    // Allocation still exists
    assertTrue(
        NLS.bind(Messages.ErrorMessage, _lf12.getName(), _logicalActor1.getName()),
        _logicalActor1.getAllocatedFunctions().contains(_lf12));

    // Functional Exchanges
    _lf1LF2Exchange = _rootLF.getOwnedFunctionalExchanges().get(2);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _lf1LF2Exchange.getName(),
            _sf1SF2Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf1LF2Exchange) == _sf1SF2Exchange));

    _lf2LF11Exchange = _rootLF.getOwnedFunctionalExchanges().get(3);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _lf2LF11Exchange.getName(),
            _sf2SF11Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf2LF11Exchange) == _sf2SF11Exchange));

    _lf11LF12Exchange = _lf1.getOwnedFunctionalExchanges().get(0);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _lf11LF12Exchange.getName(),
            _sf11SF12Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf11LF12Exchange) == _sf11SF12Exchange));

    _lf12AF1Exchange = (FunctionalExchange) CodeHelper.getChildTracingElement(_rootLF, _sf12AF1Exchange);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _lf12AF1Exchange.getName(),
            _sf12AF1Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf12AF1Exchange) == _sf12AF1Exchange));

    _logicalFunctionalChain1 = (FunctionalChain) CodeHelper.getChildTracingElement(_rootLF, _functionalChain1);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _logicalFunctionalChain1.getName(),
            _functionalChain1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_logicalFunctionalChain1) == _functionalChain1));

    assertTrue(NLS.bind(Messages.ErrorMessage,
        _logicalFunctionalChain1.getName(), _lf1.getName()),
        _logicalFunctionalChain1.getInvolvedFunctions().contains(_lf1));
    assertTrue(NLS.bind(Messages.ErrorMessage,
        _logicalFunctionalChain1.getName(), _lf2.getName()),
        _logicalFunctionalChain1.getInvolvedFunctions().contains(_lf2));
    assertTrue(NLS.bind(Messages.ErrorMessage,
        _logicalFunctionalChain1.getName(), _lf11.getName()),
        _logicalFunctionalChain1.getInvolvedFunctions().contains(_lf11));
    assertTrue(NLS.bind(Messages.ErrorMessage,
        _logicalFunctionalChain1.getName(), _lf12.getName()),
        _logicalFunctionalChain1.getInvolvedFunctions().contains(_lf12));
    assertTrue(NLS.bind(Messages.ErrorMessage,
        _logicalFunctionalChain1.getName(), _logicalAF1.getName()),
        _logicalFunctionalChain1.getInvolvedFunctions().contains(_logicalAF1));

    // Ports
    _lf1FOP = (FunctionOutputPort) _lf1.getOutputs().get(1);
    mustNotBeNull(_lf1FOP);
    assertTrue(NLS.bind(Messages.RealizationError, _lf1FOP.getName(), _sf1FOP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf1FOP) == _sf1FOP));

    _lf11FOP = (FunctionOutputPort) _lf11.getOutputs().get(0);
    mustNotBeNull(_lf11FOP);
    assertTrue(NLS.bind(Messages.RealizationError, _lf11FOP.getName(), _sf11FOP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf11FOP) == _sf11FOP));

    _lf11FIP = (FunctionInputPort) _lf11.getInputs().get(0);
    mustNotBeNull(_lf11FIP);
    assertTrue(NLS.bind(Messages.RealizationError, _lf11FIP.getName(), _sf11FIP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf11FIP) == _sf11FIP));

    _lf2FOP = (FunctionOutputPort) _lf2.getOutputs().get(0);
    mustNotBeNull(_lf2FOP);
    assertTrue(NLS.bind(Messages.RealizationError, _lf2FOP.getName(), _sf2FOP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf2FOP) == _sf2FOP));

    _lf2FIP = (FunctionInputPort) _lf2.getInputs().get(0);
    mustNotBeNull(_lf2FIP);
    assertTrue(NLS.bind(Messages.RealizationError, _lf2FIP.getName(), _sf2FIP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf2FIP) == _sf2FIP));

    _lf12FOP = (FunctionOutputPort) _lf12.getOutputs().get(0);
    mustNotBeNull(_lf12FOP);
    assertTrue(NLS.bind(Messages.RealizationError, _lf12FOP.getName(), _sf12FOP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf12FOP) == _sf12FOP));

    _lf12FIP = (FunctionInputPort) _lf12.getInputs().get(0);
    mustNotBeNull(_lf12FIP);
    assertTrue(NLS.bind(Messages.RealizationError, _lf12FIP.getName(), _sf12FIP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_lf12FIP) == _sf12FIP));

    _logicalAF1FIP = (FunctionInputPort) _logicalAF1.getInputs().get(0);
    mustNotBeNull(_logicalAF1FIP);
    assertTrue(NLS.bind(Messages.RealizationError, _logicalAF1FIP.getName(), _af1FIP.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_logicalAF1FIP) == _af1FIP));
  }

  /**
   * Run the projection test "Functional Transition" from Root SF
   * 
   * <pre>
   * Expected Result:\
   *             1. No new logical functions are created / projected in Root logical Functions
   * </pre>
   */

  public void performTest6() throws Exception {
    performFunctionalTransition(Collections.singletonList(_rootSF));

    assertTrue(NLS.bind(Messages.WrongSize, _rootLF.getName(), 3),
        (_rootLF.getOwnedFunctions().size() == 3));
    assertTrue(NLS.bind(Messages.WrongSize, _lf1.getName(), 2),
        (_lf1.getOwnedFunctions().size() == 2));
  }

  /**
   * Run the projection test "Functional Transition" from Root SF (with the changes in desc)
   * 
   * <pre>
   * Make the following changes in Root System Function\
   *             1. Remove Logical Function SF1 from root LF\
   *             2.  Add a Final Function to SF2 in Ctx Layer\
   * Expected Result:\
   *             1. System Function LF1 is re-projected with its children and appropriate realization links.\
   *             2.  Logical Function LF2 is created with a child Final Function and realizations are added appropriately.
   * </pre>
   */

  public void performTest7() throws Exception {
    // Remove System Function SF1 from root SF

    // Add a Fork Function to OA2 in operational analysis
    ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionUtil.getEditingDomain(_rootLF))
        .execute(new AbstractReadWriteCommand() {
          public void run() {
            _lf1.destroy();
            _ctxFinal = CtxFactory.eINSTANCE.createSystemFunction("Final1"); //$NON-NLS-1$
            _sf2.getOwnedFunctions().add(_ctxFinal);
          }
        });

    performFunctionalTransition(Collections.singletonList(_rootSF));

    assertTrue(NLS.bind(Messages.WrongSize, _rootLF.getName(), 3),
        (_rootSF.getOwnedFunctions().size() == 3));
    assertTrue(NLS.bind(Messages.WrongSize, _sf1.getName(), 2),
        (_sf1.getOwnedFunctions().size() == 2));
    // Logical Functions SF1 is added in root logical function of System Analysis.
    _lf1 = (LogicalFunction) CodeHelper.getChildTracingElement(_rootLF, _sf1);
    FunctionRealization realization = _lf1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _lf1.getName(), _sf1.getName()),
        realization.getAllocatedFunction() == _sf1);
    FunctionKind actualKindLF1 = _lf1.getKind();
    FunctionKind expectedKindLF1 = _sf1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf1.getName(),
        actualKindLF1.getName(), expectedKindLF1.getName()), actualKindLF1.equals(expectedKindLF1));

    // LogicalFunctions OA11 and OA12 are created under OA1 (with corresponding realization links)
    _lf11 = (LogicalFunction) _lf1.getSubFunctions().get(0);
    FunctionRealization realization11 = _lf11.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _lf11.getName(), _sf11.getName()),
        realization11.getAllocatedFunction() == _sf11);
    FunctionKind actualKindLF11 = _lf11.getKind();
    FunctionKind expectedKindLF11 = _sf11.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf11.getName(),
        actualKindLF11.getName(), expectedKindLF11.getName()), actualKindLF11.equals(expectedKindLF11));

    // Logical Functions OA11 and OA12 are created under OA1 (with corresponding realization links)
    _lf12 = (LogicalFunction) _lf1.getSubFunctions().get(1);
    FunctionRealization realization12 = _lf12.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _lf12.getName(), _lf12.getName()),
        realization12.getAllocatedFunction() == _sf12);
    FunctionKind actualKindLF12 = _lf12.getKind();
    FunctionKind expectedKindLF12 = _sf12.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _lf12.getName(),
        actualKindLF12.getName(), expectedKindLF12.getName()), actualKindLF12.equals(expectedKindLF12));

    _laFinal = (LogicalFunction) ProjectionTestUtils.getRecentlyAddedFunction(_lf2);
    FunctionRealization realizationFork1 = _laFinal.getOutFunctionRealizations().get(0);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _laFinal.getName(), _ctxFinal.getName()),
        realizationFork1.getAllocatedFunction() == _ctxFinal);
    FunctionKind actualKindFinal = _laFinal.getKind();
    FunctionKind expectedKindFinal = _ctxFinal.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _laFinal.getName(),
        actualKindFinal.getName(), expectedKindFinal.getName()), actualKindFinal.equals(expectedKindFinal));

    // Allocation of Ctx Actor is removed still exists
    assertFalse(
        NLS.bind(Messages.ErrorMessage, _lf12.getName(), _logicalActor1.getName()),
        _logicalActor1.getAllocatedFunctions().contains(_lf12));

  }

}
