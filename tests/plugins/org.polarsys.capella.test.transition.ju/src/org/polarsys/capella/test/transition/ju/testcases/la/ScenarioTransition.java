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
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelLaPa;

/**
 * Projection Tests on scenario transitions from Logical Analysis to Physical Layer
 * <P>
 * This is done with the model "LA-PA-Projection". The model is created as explained below. And the tests are documented
 * in {@link #performTest()} method
 */
public class ScenarioTransition extends TopDownTransitionTestCase {

  // CapabilityRealization
  private CapabilityRealization laMyCapabilityRealization;

  // Scenario
  private Scenario laFSMyCapabilityRealization;
  private Scenario laESMyCapabilityRealization;
  private Scenario laFSMyCapabilityRealizationES;
  private Scenario laESMyCapabilityRealizationIS;
  private Scenario laESMyCapabilityRealizationES;
  // Instance roles
  private InstanceRole laFS_LF_5;
  private InstanceRole laFS_LF_4;
  private InstanceRole laES_LogicalActor1;
  private InstanceRole laES_LA1;
  private InstanceRole laESMyCapabilityRealizationES_LogicalActor1;
  private InstanceRole laESMyCapabilityRealizationES_LA1;
  // Sequence Messages
  private SequenceMessage laFS_LF4LF5Exchange;
  private SequenceMessage laFS_LF4LF5ExchangeReturn;
  private SequenceMessage laES_LF12LF1Exchange;
  private SequenceMessage laES_LF12LF1ExchangeReturn;
  private SequenceMessage laESMyCapabilityRealizationES_LF12LF1Exchange;
  private SequenceMessage laESMyCapabilityRealizationES_LF12LF1ExchangeReturn;

  private CapabilityRealizationPkg paCapabilitiesPkg;
  // CapabilityRealization Realization
  private CapabilityRealization paMyCapabilityRealization;
  // Scenario
  private Scenario paFSMyCapabilityRealization;
  private Scenario paESMyCapabilityRealization;
  // Instance roles
  private InstanceRole paFS_LF_5;
  private InstanceRole paFS_LF_4;
  private InstanceRole paES_LogicalActor1;
  private InstanceRole paES_LA1;
  // Sequence Messages
  private SequenceMessage paFS_LF4LF5Exchange;
  private SequenceMessage paFS_LF4LF5ExchangeReturn;

  private void initSession() {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    laMyCapabilityRealization = getObject(ModelLaPa.MyCapabilityRealizationId);
    laFSMyCapabilityRealization = getObject(ModelLaPa.FSMyCapabilityRealizationId);
    laFS_LF_5 = getObject(ModelLaPa.LF_5Id);
    laFS_LF_4 = getObject(ModelLaPa.LF_4Id);
    laFS_LF4LF5Exchange = getObject(ModelLaPa.LF4LF5ExchangeId);
    laFS_LF4LF5ExchangeReturn = getObject(ModelLaPa.LF4LF5ExchangeReturnId);

    laESMyCapabilityRealization = getObject(ModelLaPa.ESMyCapabilityRealizationId);
    laES_LogicalActor1 = getObject(ModelLaPa.LogicalActor1Id);
    laES_LA1 = getObject(ModelLaPa.LA1Id);
    laES_LF12LF1Exchange = getObject(ModelLaPa.LF12LF1ExchangeId);
    laES_LF12LF1ExchangeReturn = getObject(ModelLaPa.LF12LF1ExchangeReturnId);
    paCapabilitiesPkg = getObject(ModelLaPa.paCapabilitiesPkgId);
  }

  /**
   * Tests on scenario transition Projection command should be applied:
   * 
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  public void performTest() throws Exception {
    initSession();
    LAFStoPAFSInitializationTest();
    LAEStoPAESInitializationTest();
    FStoESInitializationTest();
    EStoISInitializationTest();
    ESFtoESBInitializationTest();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  /**
   * Run the projection test "Logical Functional Scenario to Physical Functional Scenario Initialization" from [FS]
   * MyCapabilityRealization - Function Scenario
   * 
   * <pre>
   * Expected Result:\
   *               1. [FS] MyCapabilityRealization - Function Scenario is transitioned to PA Layer
   * </pre>
   */
  private void LAFStoPAFSInitializationTest() {
    performFStoFSTransition(Arrays.asList(laFSMyCapabilityRealization));
    // get the projection of MyCapabilityRealization in LA layer
    paMyCapabilityRealization = paCapabilitiesPkg.getOwnedCapabilityRealizations().get(0);

    // get the projection of [FS] MyCapabilityRealization - Function Scenario in PA layer
    paFSMyCapabilityRealization = paMyCapabilityRealization.getOwnedScenarios().get(0);
    assertNotNull(Messages.NullError, paFSMyCapabilityRealization);
    String name = paFSMyCapabilityRealization.getName();
    assertTrue(NLS.bind(Messages.RealizationError, name, laFSMyCapabilityRealization.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFSMyCapabilityRealization) == laFSMyCapabilityRealization));

    // check its Scenario Kind
    ScenarioKind actualKind = laFSMyCapabilityRealization.getKind();
    ScenarioKind expectedKind = ScenarioKind.FUNCTIONAL;
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualKind.getName(),
        expectedKind.getName()), actualKind.equals(expectedKind));

    // check the number of Instance Roles is the same
    int actualIRNumber = paFSMyCapabilityRealization.getOwnedInstanceRoles().size();
    int expectedIRNumber = laFSMyCapabilityRealization.getOwnedInstanceRoles().size();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualIRNumber,
        expectedIRNumber), actualIRNumber == expectedIRNumber);

    // check the number of Interaction Fragments is the same
    int actualIFNumber = paFSMyCapabilityRealization.getOwnedInteractionFragments().size();
    int expectedIFNumber = laFSMyCapabilityRealization.getOwnedInteractionFragments().size();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualIFNumber,
        expectedIFNumber), actualIFNumber == expectedIFNumber);

    // check the number of Sequence Messages is the same
    int actualMsgNumber = paFSMyCapabilityRealization.getOwnedMessages().size();
    int expectedMsgNumber = laFSMyCapabilityRealization.getOwnedMessages().size();
    assertTrue(
        MessageFormat.format(Messages.ErrorMessage, name, actualMsgNumber, expectedMsgNumber),
        actualMsgNumber == expectedMsgNumber);

    // get SF 3 Instance Role
    paFS_LF_5 = paFSMyCapabilityRealization.getOwnedInstanceRoles().get(0);
    assertNotNull(Messages.NullError, paFS_LF_5);
    assertTrue(NLS.bind(Messages.RealizationError, paFS_LF_5.getName(), laFS_LF_5.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFS_LF_5) == laFS_LF_5));

    // get SF 4 Instance Role
    paFS_LF_4 = paFSMyCapabilityRealization.getOwnedInstanceRoles().get(1);
    assertNotNull(Messages.NullError, paFS_LF_4);
    assertTrue(NLS.bind(Messages.RealizationError, paFS_LF_4.getName(), laFS_LF_4.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFS_LF_4) == laFS_LF_4));

    // get LF12LF1Exchange Sequence Message
    paFS_LF4LF5Exchange = paFSMyCapabilityRealization.getOwnedMessages().get(0);
    assertNotNull(Messages.NullError, paFS_LF4LF5Exchange);
    assertTrue(NLS.bind(Messages.RealizationError, paFS_LF4LF5Exchange.getName(), laFS_LF4LF5Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFS_LF4LF5Exchange) == laFS_LF4LF5Exchange));

    // get LF12LF1Exchange Sequence Message (Return branch)
    paFS_LF4LF5ExchangeReturn = paFSMyCapabilityRealization.getOwnedMessages().get(1);
    assertNotNull(Messages.NullError, paFS_LF4LF5ExchangeReturn);
    assertTrue(
        NLS.bind(Messages.RealizationError, paFS_LF4LF5ExchangeReturn.getName(), laFS_LF4LF5ExchangeReturn.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFS_LF4LF5ExchangeReturn) == laFS_LF4LF5ExchangeReturn));
  }

  /**
   * Run the projection test "Logical Exchange Scenario to Physical Exchange Scenario Initialization" from [ES]
   * MyCapabilityRealization - Exchange Scenario
   * 
   * <pre>
   * Expected Result:\
   *               1. [ES] MyCapabilityRealization - Exchange Scenario is transitioned to PA Layer
   * </pre>
   */
  private void LAEStoPAESInitializationTest() {
    performEStoESTransition(Arrays.asList(laESMyCapabilityRealization));
    // get [ES] MyCapabilityRealization - Exchange Scenario in PA layer
    paESMyCapabilityRealization = paMyCapabilityRealization.getOwnedScenarios().get(1);
    assertNotNull(Messages.NullError, paESMyCapabilityRealization);

    String name = paESMyCapabilityRealization.getName();
    assertTrue(NLS.bind(Messages.RealizationError, name, laESMyCapabilityRealization.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paESMyCapabilityRealization) == laESMyCapabilityRealization));

    // check its Scenario Kind
    ScenarioKind actualKind = laESMyCapabilityRealization.getKind();
    ScenarioKind expectedKind = ScenarioKind.DATA_FLOW;
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualKind.getName(),
        expectedKind.getName()), actualKind.equals(expectedKind));

    // check the number of Instance Roles is the same
    int actualIRNumber = paESMyCapabilityRealization.getOwnedInstanceRoles().size();
    int expectedIRNumber = laESMyCapabilityRealization.getOwnedInstanceRoles().size();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualIRNumber,
        expectedIRNumber), actualIRNumber == expectedIRNumber);

    // get LogicalActor1 instance role
    paES_LogicalActor1 = paESMyCapabilityRealization.getOwnedInstanceRoles().get(0);
    assertNotNull(Messages.NullError, paES_LogicalActor1);
    assertTrue(NLS.bind(Messages.RealizationError, paES_LogicalActor1.getName(), laES_LogicalActor1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paES_LogicalActor1) == laES_LogicalActor1));

    // get Actor 3 instance role
    paES_LA1 = paESMyCapabilityRealization.getOwnedInstanceRoles().get(1);
    assertNotNull(Messages.NullError, paES_LA1);
    assertTrue(NLS.bind(Messages.RealizationError, paES_LA1.getName(), laES_LA1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paES_LA1) == laES_LA1));
  }

  /**
   * Run the projection test "Functional Scenario to Exchange Scenario Initialization" from [FS] MyCapabilityRealization
   * - Function Scenario
   * 
   * <pre>
   * Expected Result:\
   *               1. [FS] MyCapabilityRealization - Function Scenario (ES) is created in LA Layer
   * </pre>
   */
  private void FStoESInitializationTest() {
    performFStoESTransition(Arrays.asList(laFSMyCapabilityRealization));
    // get [FS] MyCapabilityRealization - Functional Scenario (ES)
    laFSMyCapabilityRealizationES = laMyCapabilityRealization.getOwnedScenarios().get(2);
    assertNotNull(Messages.NullError, laFSMyCapabilityRealizationES);

    String name = laFSMyCapabilityRealizationES.getName();
    assertTrue(NLS.bind(Messages.RealizationError, name, laFSMyCapabilityRealization.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(laFSMyCapabilityRealizationES) == laFSMyCapabilityRealization));

    // check that the Scenario Kind has changed to DATA_FLOW
    ScenarioKind actualKind = laFSMyCapabilityRealizationES.getKind();
    ScenarioKind expectedKind = ScenarioKind.DATA_FLOW;
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualKind.getName(),
        expectedKind.getName()), actualKind.equals(expectedKind));
  }

  /**
   * Run the projection test "Exchange Scenario to Interface Scenario Initialization" from [ES] MyCapabilityRealization
   * - Exchange Scenario
   * 
   * <pre>
   * Expected Result:\
   *               1. [ES] MyCapabilityRealization - Exchange Scenario (IS) is created in LA Layer
   * </pre>
   */
  private void EStoISInitializationTest() {
    performEStoISTransition(Arrays.asList(laESMyCapabilityRealization));
    // get [ES] MyCapabilityRealization - Exchange Scenario (IS)
    laESMyCapabilityRealizationIS = laMyCapabilityRealization.getOwnedScenarios().get(3);
    assertNotNull(Messages.NullError, laESMyCapabilityRealizationIS);

    String name = laESMyCapabilityRealizationIS.getName();
    assertTrue(NLS.bind(Messages.RealizationError, name, laESMyCapabilityRealization.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(laESMyCapabilityRealizationIS) == laESMyCapabilityRealization));

    // check that the Scenario Kind has changed to INTERFACE
    ScenarioKind actualKind = laESMyCapabilityRealizationIS.getKind();
    ScenarioKind expectedKind = ScenarioKind.INTERFACE;
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualKind.getName(),
        expectedKind.getName()), actualKind.equals(expectedKind));
  }

  /**
   * Run the projection test "Exchange Scenario (functional) to Exchange Scenario (behavioral) Initialization" from [ES]
   * MyCapabilityRealization - Exchange Scenario
   * 
   * <pre>
   * Expected Result:\
   *               1. [ES] MyCapabilityRealization - Function Scenario (ES) is created in LA Layer
   * </pre>
   */
  private void ESFtoESBInitializationTest() {
    performESFtoESBTransition(Arrays.asList(laESMyCapabilityRealization));
    // get [ES] MyCapabilityRealization - Exchange Scenario (ES)
    laESMyCapabilityRealizationES = laMyCapabilityRealization.getOwnedScenarios().get(4);
    assertNotNull(Messages.NullError, laESMyCapabilityRealizationES);

    String name = laESMyCapabilityRealizationES.getName();
    assertTrue(NLS.bind(Messages.RealizationError, name, laESMyCapabilityRealization.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(laESMyCapabilityRealizationES) == laESMyCapabilityRealization));

    // check its Scenario Kind is the same
    ScenarioKind actualKind = laESMyCapabilityRealizationES.getKind();
    ScenarioKind expectedKind = ScenarioKind.DATA_FLOW;
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualKind.getName(),
        expectedKind.getName()), actualKind.equals(expectedKind));

    // check the number of Instance Roles is the same
    int actualIRNumber = laESMyCapabilityRealizationES.getOwnedInstanceRoles().size();
    int expectedIRNumber = laESMyCapabilityRealization.getOwnedInstanceRoles().size();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualIRNumber,
        expectedIRNumber), actualIRNumber == expectedIRNumber);

    // check the number of Interaction fragments is the same
    int actualIFNumber = laESMyCapabilityRealizationES.getOwnedInteractionFragments().size();
    int expectedIFNumber = laESMyCapabilityRealization.getOwnedInteractionFragments().size();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualIFNumber,
        expectedIFNumber), actualIFNumber == expectedIFNumber);

    // check that the number of Sequence Messages is the same
    int actualMsgNumber = laESMyCapabilityRealizationES.getOwnedMessages().size();
    int expectedMsgNumber = laESMyCapabilityRealization.getOwnedMessages().size();
    assertTrue(
        MessageFormat.format(Messages.ErrorMessage, name, actualMsgNumber, expectedMsgNumber),
        actualMsgNumber == expectedMsgNumber);

    // get LogicalActor1 Instance Role
    laESMyCapabilityRealizationES_LogicalActor1 = laESMyCapabilityRealizationES.getOwnedInstanceRoles().get(0);
    assertNotNull(Messages.NullError, laESMyCapabilityRealizationES_LogicalActor1);
    assertTrue(
        NLS.bind(Messages.RealizationError, laESMyCapabilityRealizationES_LogicalActor1.getName(),
            laES_LogicalActor1.getName()),
        (ProjectionTestUtils
            .getRealizedTargetElement(laESMyCapabilityRealizationES_LogicalActor1) == laES_LogicalActor1));

    // get Actor 3 Instance Role
    laESMyCapabilityRealizationES_LA1 = laESMyCapabilityRealizationES.getOwnedInstanceRoles().get(1);
    assertNotNull(Messages.NullError, laESMyCapabilityRealizationES_LA1);
    assertTrue(NLS.bind(Messages.RealizationError, laESMyCapabilityRealizationES_LA1.getName(), laES_LA1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(laESMyCapabilityRealizationES_LA1) == laES_LA1));

    // get SF2SF4&&Exchange Sequence Message
    laESMyCapabilityRealizationES_LF12LF1Exchange = laESMyCapabilityRealizationES.getOwnedMessages().get(0);
    assertNotNull(Messages.NullError, laESMyCapabilityRealizationES_LF12LF1Exchange);
    assertTrue(
        NLS.bind(Messages.RealizationError, laESMyCapabilityRealizationES_LF12LF1Exchange.getName(),
            laES_LF12LF1Exchange.getName()),
        (ProjectionTestUtils
            .getRealizedTargetElement(laESMyCapabilityRealizationES_LF12LF1Exchange) == laES_LF12LF1Exchange));
    assertTrue(
        MessageFormat.format(Messages.ErrorMessage,
            laESMyCapabilityRealizationES_LF12LF1Exchange.getName()),
        laESMyCapabilityRealizationES_LF12LF1Exchange.getInvokedOperation() instanceof ComponentExchange);

    // get SF2SF4&&Exchange Sequence Message (Return Branch)
    laESMyCapabilityRealizationES_LF12LF1ExchangeReturn = laESMyCapabilityRealizationES.getOwnedMessages().get(1);
    assertNotNull(Messages.NullError, laESMyCapabilityRealizationES_LF12LF1ExchangeReturn);
    assertTrue(
        NLS.bind(Messages.RealizationError, laESMyCapabilityRealizationES_LF12LF1ExchangeReturn.getName(),
            laES_LF12LF1ExchangeReturn.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(
            laESMyCapabilityRealizationES_LF12LF1ExchangeReturn) == laES_LF12LF1ExchangeReturn));
    assertTrue(
        MessageFormat.format(Messages.ErrorMessage,
            laESMyCapabilityRealizationES_LF12LF1ExchangeReturn.getName()),
        laESMyCapabilityRealizationES_LF12LF1ExchangeReturn.getInvokedOperation() instanceof ComponentExchange);
  }
}
