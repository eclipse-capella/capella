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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.CodeHelper;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelOaSa;

/**
 * Projection Tests on "Functional Transition" from Operational Analysis to System Analysis
 * <P>
 * This is done with the model "OA-SA-Projection". The model is created as explained below.
 * 
 * <pre>
 *     Model Used: OA-SA-Projection
 *     Model is created with the following elements�
 *         1. Create a new "[OABD] Root Operational Activity - Operational Activity Breakdown" diagram on Root Operational Activity.
 *         2.  Create Operational Activities OA1 and OA2 in the diagram
 *         3.  Create Operational Activities OA11 and OA12 in the diagram
 *         4.  Create "Contained In" link from OA11 and OA12 to OA1 (OA1 has two sub activities, OA11 and OA12)
 *         5.  Allocate OA12 to Actor1 (Actor1 is under root OE pkg - created in previous tests) {@link ActorTransition}
 *         6.  Save the diagram
 * </pre>
 * 
 * 
 */
public class FunctionalTransition extends TopDownTransitionTestCase {

  // Operational Actors
  private Entity _actor1;

  // Operational Activities
  private OperationalActivity _rootOA;
  private OperationalActivity _oa1;
  private OperationalActivity _oa11;
  private OperationalActivity _oa12;
  private OperationalActivity _oa2;
  private OperationalActivity _oa4;
  private OperationalActivity _oa5;
  private OperationalActivityPkg _oapkg1;
  private OperationalActivity _oaFork1;
  private FunctionalExchange _oa1OA2Interaction;
  private FunctionalExchange _oa2OA11Interaction;
  private FunctionalExchange _oa11OA12Interaction;
  private FunctionalExchange _oa4oa5Interaction;
  private OperationalProcess _operationalProcess;
  private ExchangeCategory _oaec1;

  // System Function
  private SystemFunction _rootSF;
  private SystemFunction _sf1;
  private SystemFunction _sf2;
  private SystemFunction _sf11;
  private SystemFunction _sf12;
  private SystemFunction _sf4;
  private SystemFunction _sf5;
  private SystemFunctionPkg _sfpkg1;
  private SystemFunction _sfFork1;
  private FunctionalExchange _sf1SF2Interaction;
  private FunctionalExchange _sf2SF11Interaction;
  private FunctionalExchange _sf11SF12Interaction;
  private FunctionalExchange _sf4sf5Interaction;
  private FunctionalChain _functionalChain;
  private ExchangeCategory _saec1;

  private SystemComponentPkg _rootActorPkg;
  private SystemComponent _ctxActor1;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, true);
    // Assign the objects
    _rootActorPkg = (SystemComponentPkg) getObject(ModelOaSa.rootActorPkgId);
    _actor1 = (Entity) getObject(ModelOaSa.actor1Id);

    _rootOA = (OperationalActivity) getObject(ModelOaSa.rootOAId);
    _oa1 = (OperationalActivity) getObject(ModelOaSa.oa1Id);
    _oa2 = (OperationalActivity) getObject(ModelOaSa.oa2Id);
    _oa11 = (OperationalActivity) getObject(ModelOaSa.oa11Id);
    _oa12 = (OperationalActivity) getObject(ModelOaSa.oa12Id);
    _oa4 = (OperationalActivity) getObject(ModelOaSa.oa4Id);
    _oa5 = (OperationalActivity) getObject(ModelOaSa.oa5Id);
    _oapkg1 = (OperationalActivityPkg) getObject(ModelOaSa.oapkg1Id);

    _rootSF = (SystemFunction) getObject(ModelOaSa.rootSFId);
    _oa1OA2Interaction = (FunctionalExchange) getObject(ModelOaSa.oa1OA2InteractionId);
    _oa2OA11Interaction = (FunctionalExchange) getObject(ModelOaSa.oa2OA11InteractionId);
    _oa11OA12Interaction = (FunctionalExchange) getObject(ModelOaSa.oa11OA12InteractionId);
    _oa4oa5Interaction = (FunctionalExchange) getObject(ModelOaSa.oa4oa5InteractionId);
    _operationalProcess = (OperationalProcess) getObject(ModelOaSa.operationalProcessId);
    _oaec1 = (ExchangeCategory) getObject(ModelOaSa.ec1Id);

    performTest1();
    performTest2();
    performTest3();
    performTest4();
    performTest5();
    performTest6();

  }

  /**
   * Run the projection test "Functional Transition" from OAPkg 1
   * 
   * <pre>
   * Expected Result:\
   *             1.  OAPkg 1 System Functions Package is added in root system function of System Analysis with realization link to OAPkg 1.
   *             2.  OA4 and OA5 System Functions are added in OAPkg 1 System Functions Package  
   *             3.  OA4OA5Interaction 1 FunctionalExchange and ExchangeCategory 1 are added in root system function of System Analysis
   * </pre>
   */

  public void performTest1() throws Exception {
    performFunctionalTransition(Collections.singletonList(_oapkg1));
    // check OAPkg 1 System Functions Package is created
    _sfpkg1 = _rootSF.getOwnedSystemFunctionPkgs().get(0);
    mustNotBeNull(_sfpkg1);

    assertTrue(NLS.bind(Messages.ShouldRealize, _sfpkg1.getName(), _oapkg1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_sfpkg1) == _oapkg1));

    // check OA4 and OA5 System Functions are created
    _sf4 = _sfpkg1.getOwnedSystemFunctions().get(0);
    mustNotBeNull(_sf4);
    assertTrue(NLS.bind(Messages.ShouldRealize, _sf4.getName(), _oa4.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_sf4) == _oa4));
    FunctionKind actualKindSF4 = _sf4.getKind();
    FunctionKind expectedKindSF4 = _oa4.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sf4.getName(), actualKindSF4.getName(),
        expectedKindSF4.getName()), actualKindSF4.equals(expectedKindSF4));

    _sf5 = _sfpkg1.getOwnedSystemFunctions().get(1);
    mustNotBeNull(_sf5);
    assertTrue(NLS.bind(Messages.ShouldRealize, _sf5.getName(), _oa5.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_sf5) == _oa5));
    FunctionKind actualKindSF5 = _sf5.getKind();
    FunctionKind expectedKindSF5 = _oa5.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sf5.getName(), actualKindSF5.getName(),
        expectedKindSF5.getName()), actualKindSF5.equals(expectedKindSF5));

    // functional exchange
    _sf4sf5Interaction = _rootSF.getOwnedFunctionalExchanges().get(0);
    mustNotBeNull(_sf4sf5Interaction);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _sf4sf5Interaction.getName(),
            _oa4oa5Interaction.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_sf4sf5Interaction) == _oa4oa5Interaction));

    // exchange category
    _saec1 = ((SystemFunctionPkg) _rootSF.eContainer()).getOwnedCategories().get(0);
    mustNotBeNull(_saec1);
    assertTrue(NLS.bind(Messages.WrongRealization, _saec1.getName(), _oaec1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saec1) == _oaec1));
    assertTrue(NLS.bind(Messages.ErrorMessage, _saec1.getName()),
        _saec1.getExchanges().size() == 1);
    assertTrue(NLS.bind(Messages.ErrorMessage, _saec1.getName(), _sf4sf5Interaction.getName()),
        _saec1.getExchanges().contains(_sf4sf5Interaction));
  }

  /**
   * Run the projection test "Functional Transition" from OA2
   * 
   * <pre>
   * Expected Result:\
   *             1.  OA2 system function is added in root system function of System Analysis with realization link to OA2.\
   *             2.  The size of created system functions is 2
   * </pre>
   */

  public void performTest2() throws Exception {
    performFunctionalTransition(Collections.singletonList(_oa2));
    _sf2 = (SystemFunction) ProjectionTestUtils.getAllocatingFunction(_oa2);
    // Verify the size of functions created after projection
    assertTrue(NLS.bind(Messages.ErrorMessage, _rootSF.getName(), 2),
        (_rootSF.getOwnedFunctions().size() == 2));
    FunctionRealization realization = _sf2.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _sf2.getName(), _oa2.getName()),
        realization.getAllocatedFunction() == _oa2);
    FunctionKind actualKindSF2 = _sf2.getKind();
    FunctionKind expectedKindSF2 = _oa2.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sf2.getName(), actualKindSF2.getName(),
        expectedKindSF2.getName()), actualKindSF2.equals(expectedKindSF2));
  }

  /**
   * Run the projection test "Create System Actor" from Actor1
   * 
   * <pre>
   * Expected Result:\
   *             1.  Actor1 is created in Ctx Layer\
   *             2.  OA12 is also projected towards Ctx Layer under Root System Function
   * </pre>
   */

  public void performTest3() throws Exception {
    performOE2ActorTransition(Collections.singletonList(_actor1));
    _ctxActor1 = (SystemComponent) CodeHelper.getChildTracingElement(_rootActorPkg, _actor1);
    assertTrue(NLS.bind(Messages.WrongRealization, _ctxActor1.getName(), _actor1.getName()),
        (ProjectionTestUtils.getRealizedEntity(_ctxActor1) == _actor1));

    _sf12 = (SystemFunction) ProjectionTestUtils.getAllocatingFunction(_oa12);
    // Verify the size of functions created after projection
    assertTrue(NLS.bind(Messages.ErrorMessage, _rootSF.getName(), 2),
        (_rootSF.getOwnedFunctions().size() == 2));
    FunctionRealization realization = _sf12.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _sf12.getName(), _oa12.getName()),
        realization.getAllocatedFunction() == _oa12);
    FunctionKind actualKindSF12 = _sf12.getKind();
    FunctionKind expectedKindSF12 = _oa12.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sf12.getName(),
        actualKindSF12.getName(), expectedKindSF12.getName()), actualKindSF12.equals(expectedKindSF12));

    assertTrue(NLS.bind(Messages.ErrorMessage, _sf12.getName(), _ctxActor1.getName()),
        _ctxActor1.getAllocatedFunctions().contains(_sf12));
  }

  /**
   * Run the projection test "Functional Transition" from OA1
   * 
   * <pre>
   * Expected Result:\
   *             1. System Functions OA1 is added in root system function of System Analysis.\
   *             2. System Functions OA11 and OA12 are created under OA1 (with corresponding realization links)\
   *             3. OA12 moves from root SF to OA1
   *             4. Functional Exchange OA1OA2Interaction is created between SF1 & SF2
   *             5. Functional Exchange OA2OA11Interaction is created between SF2 & SF11
   *             6. Functional Exchange OA11OA12Interaction is created between SF11 & SF12
   *             7. Functional chain is created with the path OA1-->OA2-->OA11-->OA12
   * 
   * </pre>
   */

  public void performTest4() throws Exception {
    performFunctionalTransition(Collections.singletonList(_rootOA));
    // System Functions OA1 is added in root system function of System Analysis.
    _sf1 = (SystemFunction) ProjectionTestUtils.getAllocatingFunction(_oa1);
    FunctionRealization realization = _sf1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _sf1.getName(), _oa1.getName()),
        realization.getAllocatedFunction() == _oa1);
    FunctionKind actualKindSF1 = _sf1.getKind();
    FunctionKind expectedKindSF1 = _oa1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sf1.getName(), actualKindSF1.getName(),
        expectedKindSF1.getName()), actualKindSF1.equals(expectedKindSF1));

    // System Functions OA11 and OA12 are created under OA1 (with corresponding realization links)
    _sf11 = (SystemFunction) ProjectionTestUtils.getAllocatingFunction(_oa11);
    FunctionRealization realization11 = _sf11.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _sf11.getName(), _oa11.getName()),
        realization11.getAllocatedFunction() == _oa11);
    FunctionKind actualKindSF11 = _sf11.getKind();
    FunctionKind expectedKindSF11 = _oa11.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sf11.getName(),
        actualKindSF11.getName(), expectedKindSF11.getName()), actualKindSF11.equals(expectedKindSF11));

    // System Functions OA11 and OA12 are created under OA1 (with corresponding realization links)
    _sf12 = (SystemFunction) ProjectionTestUtils.getAllocatingFunction(_oa12);
    FunctionRealization realization12 = _sf12.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _sf12.getName(), _oa12.getName()),
        realization12.getAllocatedFunction() == _oa12);
    FunctionKind actualKindSF12 = _sf12.getKind();
    FunctionKind expectedKindSF12 = _oa12.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sf12.getName(),
        actualKindSF12.getName(), expectedKindSF12.getName()), actualKindSF12.equals(expectedKindSF12));

    assertTrue(NLS.bind(Messages.ErrorMessage, _sf12.getName(), _sf1.getName()),
        (_sf12.eContainer() == _sf1));

    // Allocation still exists
    assertTrue(NLS.bind(Messages.ErrorMessage, _sf12.getName(), _ctxActor1.getName()),
        _ctxActor1.getAllocatedFunctions().contains(_sf12));

    // Functional Exchanges
    _sf1SF2Interaction = _rootSF.getOwnedFunctionalExchanges().get(1);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _sf1SF2Interaction.getName(),
            _oa1OA2Interaction.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_sf1SF2Interaction) == _oa1OA2Interaction));

    _sf2SF11Interaction = _rootSF.getOwnedFunctionalExchanges().get(2);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _sf2SF11Interaction.getName(),
            _oa2OA11Interaction.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_sf2SF11Interaction) == _oa2OA11Interaction));

    _sf11SF12Interaction = _sf1.getOwnedFunctionalExchanges().get(0);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _sf11SF12Interaction.getName(),
            _oa11OA12Interaction.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_sf11SF12Interaction) == _oa11OA12Interaction));

    _functionalChain = _rootSF.getOwnedFunctionalChains().get(0);
    assertTrue(
        NLS.bind(Messages.WrongRealization, _functionalChain.getName(),
            _operationalProcess.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_functionalChain) == _operationalProcess));

    assertTrue(
        NLS.bind(Messages.ErrorMessage, _functionalChain.getName(), _sf1.getName()),
        _functionalChain.getInvolvedFunctions().contains(_sf1));
    assertTrue(
        NLS.bind(Messages.ErrorMessage, _functionalChain.getName(), _sf2.getName()),
        _functionalChain.getInvolvedFunctions().contains(_sf2));
    assertTrue(
        NLS.bind(Messages.ErrorMessage, _functionalChain.getName(), _sf11.getName()),
        _functionalChain.getInvolvedFunctions().contains(_sf11));
    assertTrue(
        NLS.bind(Messages.ErrorMessage, _functionalChain.getName(), _sf12.getName()),
        _functionalChain.getInvolvedFunctions().contains(_sf12));
  }

  /**
   * Run the projection test "Functional Transition" from Root OA
   * 
   * <pre>
   * Expected Result:\
   *             1. No new system functions are created / projected in Root System Functions
   * </pre>
   */

  public void performTest5() throws Exception {
    performFunctionalTransition(Collections.singletonList(_rootOA));
    assertTrue(NLS.bind(Messages.ErrorMessage, _rootSF.getName(), 3),
        (_rootSF.getOwnedFunctions().size() == 3));
    assertTrue(NLS.bind(Messages.ErrorMessage, _sf1.getName(), 2),
        (_sf1.getOwnedFunctions().size() == 2));
  }

  /**
   * Run the projection test "Functional Transition" from Root OA (with the changes in desc)
   * 
   * <pre>
   * Make the following changes in Root System Function\
   *             1.  Remove System Function SF1 from root SF\
   *             2.  Add a Fork Function to OA2 in operational analysis\
   * Expected Result:\
   *             1. System Function OA1 is re-projected with its children and appropriate realization links.\
   *             2.  System Function OA2 is created with a child Fork Function and realizations are added appropriately.
   * </pre>
   */

  public void performTest6() throws Exception {
    // Remove System Function SF1 from root SF
    // Add a Fork Function to OA2 in operational analysis
    getExecutionManager(_rootOA).execute(new AbstractReadWriteCommand() {
      public void run() {
        _sf1.destroy();
        _oaFork1 = OaFactory.eINSTANCE.createOperationalActivity("Fork1"); //$NON-NLS-1$
        _oaFork1.setKind(FunctionKind.DUPLICATE);
        _oa2.getOwnedFunctions().add(_oaFork1);
      }
    });

    performFunctionalTransition(Collections.singletonList(_rootOA));
    assertTrue(NLS.bind(Messages.ErrorMessage, _rootSF.getName(), 3),
        (_rootSF.getOwnedFunctions().size() == 3));
    assertTrue(NLS.bind(Messages.ErrorMessage, _sf1.getName(), 2),
        (_sf1.getOwnedFunctions().size() == 2));
    // System Functions OA1 is added in root system function of System Analysis.
    _sf1 = (SystemFunction) CodeHelper.getChildTracingElement(_rootSF, _oa1);
    FunctionRealization realization = _sf1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _sf1.getName(), _oa1.getName()),
        realization.getAllocatedFunction() == _oa1);
    FunctionKind actualKindSF1 = _sf1.getKind();
    FunctionKind expectedKindSF1 = _oa1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sf1.getName(), actualKindSF1.getName(),
        expectedKindSF1.getName()), actualKindSF1.equals(expectedKindSF1));

    // System Functions OA11 and OA12 are created under OA1 (with corresponding realization links)
    _sf11 = (SystemFunction) _sf1.getSubFunctions().get(0);
    FunctionRealization realization11 = _sf11.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _sf11.getName(), _oa11.getName()),
        realization11.getAllocatedFunction() == _oa11);
    FunctionKind actualKindSF11 = _sf11.getKind();
    FunctionKind expectedKindSF11 = _oa11.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sf11.getName(),
        actualKindSF11.getName(), expectedKindSF11.getName()), actualKindSF11.equals(expectedKindSF11));

    // System Functions OA11 and OA12 are created under OA1 (with corresponding realization links)
    _sf12 = (SystemFunction) _sf1.getSubFunctions().get(1);
    FunctionRealization realization12 = _sf12.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _sf12.getName(), _oa12.getName()),
        realization12.getAllocatedFunction() == _oa12);
    FunctionKind actualKindSF12 = _sf12.getKind();
    FunctionKind expectedKindSF12 = _oa12.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sf12.getName(),
        actualKindSF12.getName(), expectedKindSF12.getName()), actualKindSF12.equals(expectedKindSF12));

    _sfFork1 = (SystemFunction) ProjectionTestUtils.getRecentlyAddedFunction(_sf2);
    FunctionRealization realizationFork1 = _sfFork1.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(Messages.WrongRealization, _sfFork1.getName(), _oaFork1.getName()),
        realizationFork1.getAllocatedFunction() == _oaFork1);
    FunctionKind actualKindFork1 = _sfFork1.getKind();
    FunctionKind expectedKindFork1 = _oaFork1.getKind();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, _sfFork1.getName(),
        actualKindFork1.getName(), expectedKindFork1.getName()), actualKindFork1.equals(expectedKindFork1));

    // Allocation of Ctx Actor is removed still exists
    assertFalse(NLS.bind(Messages.ErrorMessage, _sf12.getName(), _ctxActor1.getName()),
        _ctxActor1.getAllocatedFunctions().contains(_sf12));

  }
}
