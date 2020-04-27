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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelCtxLa;

/**
 * Projection Tests on scenario transitions from System Analysis to Logical Layer
 * <P>
 * This is done with the model "CTX-LA-Projection". The model is created as explained below. And the tests are
 * documented in {@link #getTests()} method
 */
public class ScenarioTransition extends TopDownTransitionTestCase {

  // Capability
  private Capability _saMyCapability;

  // Scenario
  private Scenario _saFSMyCapability;
  private Scenario _saESMyCapability;
  private Scenario _saFSMyCapabilityES;
  private Scenario _saESMyCapabilityIS;
  private Scenario _saESMyCapabilityES;
  // Instance roles
  private InstanceRole _saFS_SF_3;
  private InstanceRole _saFS_SF_4;
  private InstanceRole _saES_System;
  private InstanceRole _saES_Actor3;
  private InstanceRole _saESMyCapabilityES_System;
  private InstanceRole _saESMyCapabilityES_Actor3;
  // Sequence Messages
  private SequenceMessage _saFS_SF3SF4Exchange;
  private SequenceMessage _saFS_SF3SF4ExchangeReturn;
  private SequenceMessage _saES_SF2SF11Exchange;
  private SequenceMessage _saES_SF2SF11ExchangeReturn;
  private SequenceMessage _saESMyCapabilityES_SF2SF11Exchange;
  private SequenceMessage _saESMyCapabilityES_SF2SF11ExchangeReturn;

  private CapabilityRealizationPkg _laCapabilitiesPkg;
  // Capability Realization
  private CapabilityRealization _laMyCapability;
  // Scenario
  private Scenario _laFSMyCapability;
  private Scenario _laESMyCapability;
  // Instance roles
  private InstanceRole _laFS_SF_3;
  private InstanceRole _laFS_SF_4;
  private InstanceRole _laES_System;
  private InstanceRole _laES_Actor3;
  // Sequence Messages
  private SequenceMessage _laFS_SF3SF4Exchange;
  private SequenceMessage _laFS_SF3SF4ExchangeReturn;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {
    // Assign the objects
    _saMyCapability = (Capability) getObject(ModelCtxLa.MyCapabilityId);
    _saFSMyCapability = (Scenario) getObject(ModelCtxLa.FSMyCapabilityId);
    _saFS_SF_3 = (InstanceRole) getObject(ModelCtxLa.SF_3Id);
    _saFS_SF_4 = (InstanceRole) getObject(ModelCtxLa.SF_4Id);
    _saFS_SF3SF4Exchange = (SequenceMessage) getObject(ModelCtxLa.SF3SF4ExchangeId);
    _saFS_SF3SF4ExchangeReturn = (SequenceMessage) getObject(ModelCtxLa.SF3SF4ExchangeReturnId);

    _saESMyCapability = (Scenario) getObject(ModelCtxLa.ESMyCapabilityId);
    _saES_System = (InstanceRole) getObject(ModelCtxLa.SystemId);
    _saES_Actor3 = (InstanceRole) getObject(ModelCtxLa.Actor3Id);
    _saES_SF2SF11Exchange = (SequenceMessage) getObject(ModelCtxLa.SF2SF11ExchangeId);
    _saES_SF2SF11ExchangeReturn = (SequenceMessage) getObject(ModelCtxLa.SF2SF11ExchangeReturnId);
    _laCapabilitiesPkg = (CapabilityRealizationPkg) getObject(ModelCtxLa.laCapabilitiesPkgId);

    performTest1();
    performTest2();
    performTest3();
    performTest4();
    performTest5();
  }

  /**
   * Run the projection test "System Functional Scenario to Logical Functional Scenario Initialization" from [FS]
   * MyCapability - Function Scenario
   * 
   * <pre>
   * Expected Result:\
   *               1. [FS] MyCapability - Function Scenario is transitioned to LA Layer
   * </pre>
   */

  public void performTest1() throws Exception {
    performFStoFSTransition(Collections.singletonList(_saFSMyCapability));
    // get the projection of MyCapability in LA layer
    _laMyCapability = _laCapabilitiesPkg.getOwnedCapabilityRealizations().get(0);

    // get the projection of [FS] MyCapability - Function Scenario in LA layer
    _laFSMyCapability = _laMyCapability.getOwnedScenarios().get(0);
    assertNotNull(Messages.NullError, _laFSMyCapability);
    String name = _laFSMyCapability.getName();
    assertTrue(NLS.bind(Messages.RealizationError, name, _saFSMyCapability.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laFSMyCapability) == _saFSMyCapability));

    // check its Scenario Kind
    ScenarioKind actualKind = _saFSMyCapability.getKind();
    ScenarioKind expectedKind = ScenarioKind.FUNCTIONAL;
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualKind.getName(),
        expectedKind.getName()), actualKind.equals(expectedKind));

    // check the number of Instance Roles is the same
    int actualIRNumber = _laFSMyCapability.getOwnedInstanceRoles().size();
    int expectedIRNumber = _saFSMyCapability.getOwnedInstanceRoles().size();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualIRNumber,
        expectedIRNumber), actualIRNumber == expectedIRNumber);

    // check the number of Interaction Fragments is the same
    int actualIFNumber = _laFSMyCapability.getOwnedInteractionFragments().size();
    int expectedIFNumber = _saFSMyCapability.getOwnedInteractionFragments().size();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualIFNumber,
        expectedIFNumber), actualIFNumber == expectedIFNumber);

    // check the number of Sequence Messages is the same
    int actualMsgNumber = _laFSMyCapability.getOwnedMessages().size();
    int expectedMsgNumber = _saFSMyCapability.getOwnedMessages().size();
    assertTrue(
        MessageFormat.format(Messages.ErrorMessage, name, actualMsgNumber, expectedMsgNumber),
        actualMsgNumber == expectedMsgNumber);

    // get SF 3 Instance Role
    _laFS_SF_3 = _laFSMyCapability.getOwnedInstanceRoles().get(1);
    assertNotNull(Messages.NullError, _laFS_SF_3);
    assertTrue(NLS.bind(Messages.RealizationError, _laFS_SF_3.getName(), _saFS_SF_3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laFS_SF_3) == _saFS_SF_3));

    // get SF 4 Instance Role
    _laFS_SF_4 = _laFSMyCapability.getOwnedInstanceRoles().get(0);
    assertNotNull(Messages.NullError, _laFS_SF_4);
    assertTrue(NLS.bind(Messages.RealizationError, _laFS_SF_4.getName(), _saFS_SF_4.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laFS_SF_4) == _saFS_SF_4));

    // get SF3SF4Exchange Sequence Message
    _laFS_SF3SF4Exchange = _laFSMyCapability.getOwnedMessages().get(0);
    assertNotNull(Messages.NullError, _laFS_SF3SF4Exchange);
    assertTrue(NLS.bind(Messages.RealizationError, _laFS_SF3SF4Exchange.getName(), _saFS_SF3SF4Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laFS_SF3SF4Exchange) == _saFS_SF3SF4Exchange));

    // get SF3SF4Exchange Sequence Message (Return branch)
    _laFS_SF3SF4ExchangeReturn = _laFSMyCapability.getOwnedMessages().get(1);
    assertNotNull(Messages.NullError, _laFS_SF3SF4ExchangeReturn);
    assertTrue(
        NLS.bind(Messages.RealizationError, _laFS_SF3SF4ExchangeReturn.getName(), _saFS_SF3SF4ExchangeReturn.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laFS_SF3SF4ExchangeReturn) == _saFS_SF3SF4ExchangeReturn));

  }

  /**
   * Run the projection test "System Exchange Scenario to Logical Exchange Scenario Initialization" from [ES]
   * MyCapability - Exchange Scenario
   * 
   * <pre>
   * Expected Result:\
   *               1. [ES] MyCapability - Exchange Scenario is transitioned to LA Layer
   * </pre>
   */

  public void performTest2() throws Exception {
    performEStoESTransition(Collections.singletonList(_saESMyCapability));
    // get [ES] MyCapability - Exchange Scenario in LA layer
    _laESMyCapability = _laMyCapability.getOwnedScenarios().get(1);
    assertNotNull(Messages.NullError, _laESMyCapability);

    String name = _laESMyCapability.getName();
    assertTrue(NLS.bind(Messages.RealizationError, name, _saESMyCapability.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laESMyCapability) == _saESMyCapability));

    // check its Scenario Kind
    ScenarioKind actualKind = _saESMyCapability.getKind();
    ScenarioKind expectedKind = ScenarioKind.DATA_FLOW;
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualKind.getName(),
        expectedKind.getName()), actualKind.equals(expectedKind));

    // check the number of Instance Roles is the same
    int actualIRNumber = _laESMyCapability.getOwnedInstanceRoles().size();
    int expectedIRNumber = _saESMyCapability.getOwnedInstanceRoles().size();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualIRNumber,
        expectedIRNumber), actualIRNumber == expectedIRNumber);

    // get System instance role
    _laES_System = _laESMyCapability.getOwnedInstanceRoles().get(0);
    assertNotNull(Messages.NullError, _laES_System);
    assertTrue(NLS.bind(Messages.RealizationError, _laES_System.getName(), _saES_System.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laES_System) == _saES_System));

    // get Actor 3 instance role
    _laES_Actor3 = _laESMyCapability.getOwnedInstanceRoles().get(1);
    assertNotNull(Messages.NullError, _laES_Actor3);
    assertTrue(NLS.bind(Messages.RealizationError, _laES_Actor3.getName(), _saES_Actor3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laES_Actor3) == _saES_Actor3));
  }

  /**
   * Run the projection test "Functional Scenario to Exchange Scenario Initialization" from [FS] MyCapability - Function
   * Scenario
   * 
   * <pre>
   * Expected Result:\
   *               1. [FS] MyCapability - Function Scenario (ES) is created in SA Layer
   * </pre>
   */

  public void performTest3() throws Exception {
    performFStoESTransition(Collections.singletonList(_saFSMyCapability));
    // get [FS] MyCapability - Functional Scenario (ES)
    _saFSMyCapabilityES = _saMyCapability.getOwnedScenarios().get(2);
    assertNotNull(Messages.NullError, _saFSMyCapabilityES);

    String name = _saFSMyCapabilityES.getName();
    assertTrue(NLS.bind(Messages.RealizationError, name, _saFSMyCapability.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saFSMyCapabilityES) == _saFSMyCapability));

    // check that the Scenario Kind has changed to DATA_FLOW
    ScenarioKind actualKind = _saFSMyCapabilityES.getKind();
    ScenarioKind expectedKind = ScenarioKind.DATA_FLOW;
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualKind.getName(),
        expectedKind.getName()), actualKind.equals(expectedKind));
  }

  /**
   * Run the projection test "Exchange Scenario to Interface Scenario Initialization" from [ES] MyCapability - Exchange
   * Scenario
   * 
   * <pre>
   * Expected Result:\
   *               1. [ES] MyCapability - Exchange Scenario (IS) is created in SA Layer
   * </pre>
   */

  public void performTest4() throws Exception {
    performEStoISTransition(Collections.singletonList(_saESMyCapability));
    // get [ES] MyCapability - Exchange Scenario (IS)
    _saESMyCapabilityIS = _saMyCapability.getOwnedScenarios().get(3);
    assertNotNull(Messages.NullError, _saESMyCapabilityIS);

    String name = _saESMyCapabilityIS.getName();
    assertTrue(NLS.bind(Messages.RealizationError, name, _saESMyCapability.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saESMyCapabilityIS) == _saESMyCapability));

    // check that the Scenario Kind has changed to INTERFACE
    ScenarioKind actualKind = _saESMyCapabilityIS.getKind();
    ScenarioKind expectedKind = ScenarioKind.INTERFACE;
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualKind.getName(),
        expectedKind.getName()), actualKind.equals(expectedKind));
  }

  /**
   * Run the projection test "Exchange Scenario (functional) to Exchange Scenario (behavioral) Initialization" from [ES]
   * MyCapability - Exchange Scenario
   * 
   * <pre>
   * Expected Result:\
   *               1. [ES] MyCapability - Function Scenario (ES) is created in SA Layer
   * </pre>
   */

  public void performTest5() throws Exception {
    performESFtoESBTransition(Collections.singletonList(_saESMyCapability));
    // get [ES] MyCapability - Exchange Scenario (ES)
    _saESMyCapabilityES = _saMyCapability.getOwnedScenarios().get(4);
    assertNotNull(Messages.NullError, _saESMyCapabilityES);

    String name = _saESMyCapabilityES.getName();
    assertTrue(NLS.bind(Messages.RealizationError, name, _saESMyCapability.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saESMyCapabilityES) == _saESMyCapability));

    // check its Scenario Kind is the same
    ScenarioKind actualKind = _saESMyCapabilityES.getKind();
    ScenarioKind expectedKind = ScenarioKind.DATA_FLOW;
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualKind.getName(),
        expectedKind.getName()), actualKind.equals(expectedKind));

    // check the number of Instance Roles is the same
    int actualIRNumber = _saESMyCapabilityES.getOwnedInstanceRoles().size();
    int expectedIRNumber = _saESMyCapability.getOwnedInstanceRoles().size();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualIRNumber,
        expectedIRNumber), actualIRNumber == expectedIRNumber);

    // check the number of Interaction fragments is the same
    int actualIFNumber = _saESMyCapabilityES.getOwnedInteractionFragments().size();
    int expectedIFNumber = _saESMyCapability.getOwnedInteractionFragments().size();
    assertTrue(MessageFormat.format(Messages.ErrorMessage, name, actualIFNumber,
        expectedIFNumber), actualIFNumber == expectedIFNumber);

    // check that the number of Sequence Messages is the same
    int actualMsgNumber = _saESMyCapabilityES.getOwnedMessages().size();
    int expectedMsgNumber = _saESMyCapability.getOwnedMessages().size();
    assertTrue(
        MessageFormat.format(Messages.ErrorMessage, name, actualMsgNumber, expectedMsgNumber),
        actualMsgNumber == expectedMsgNumber);

    // get System Instance Role
    _saESMyCapabilityES_System = _saESMyCapabilityES.getOwnedInstanceRoles().get(0);
    assertNotNull(Messages.NullError, _saESMyCapabilityES_System);
    assertTrue(NLS.bind(Messages.RealizationError, _saESMyCapabilityES_System.getName(), _saES_System.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saESMyCapabilityES_System) == _saES_System));

    // get Actor 3 Instance Role
    _saESMyCapabilityES_Actor3 = _saESMyCapabilityES.getOwnedInstanceRoles().get(1);
    assertNotNull(Messages.NullError, _saESMyCapabilityES_Actor3);
    assertTrue(NLS.bind(Messages.RealizationError, _saESMyCapabilityES_Actor3.getName(), _saES_Actor3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saESMyCapabilityES_Actor3) == _saES_Actor3));

    // get SF2SF4&&Exchange Sequence Message
    _saESMyCapabilityES_SF2SF11Exchange = _saESMyCapabilityES.getOwnedMessages().get(0);
    assertNotNull(Messages.NullError, _saESMyCapabilityES_SF2SF11Exchange);
    assertTrue(
        NLS.bind(Messages.RealizationError, _saESMyCapabilityES_SF2SF11Exchange.getName(),
            _saES_SF2SF11Exchange.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_saESMyCapabilityES_SF2SF11Exchange) == _saES_SF2SF11Exchange));
    assertTrue(
        MessageFormat.format(Messages.ErrorMessage,
            _saESMyCapabilityES_SF2SF11Exchange.getName()),
        _saESMyCapabilityES_SF2SF11Exchange.getInvokedOperation() instanceof ComponentExchange);

    // get SF2SF4&&Exchange Sequence Message (Return Branch)
    _saESMyCapabilityES_SF2SF11ExchangeReturn = _saESMyCapabilityES.getOwnedMessages().get(1);
    assertNotNull(Messages.NullError, _saESMyCapabilityES_SF2SF11ExchangeReturn);
    assertTrue(
        NLS.bind(Messages.RealizationError, _saESMyCapabilityES_SF2SF11ExchangeReturn.getName(),
            _saES_SF2SF11ExchangeReturn.getName()),
        (ProjectionTestUtils
            .getRealizedTargetElement(_saESMyCapabilityES_SF2SF11ExchangeReturn) == _saES_SF2SF11ExchangeReturn));
    assertTrue(
        MessageFormat.format(Messages.ErrorMessage,
            _saESMyCapabilityES_SF2SF11ExchangeReturn.getName()),
        _saESMyCapabilityES_SF2SF11ExchangeReturn.getInvokedOperation() instanceof ComponentExchange);
  }

}
