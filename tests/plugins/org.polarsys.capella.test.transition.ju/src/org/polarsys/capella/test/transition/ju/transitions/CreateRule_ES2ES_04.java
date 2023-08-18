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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Scenario transition CES2CIS
 * 
 * * - Create SystemFunction into System Functions named Root System Function - Create FunctionRealization into Root
 * System Function to Root Operational Activity - Create SystemFunction into Root System Function named SF1 - Create
 * FunctionOutputPort into SF1 named FOP11 - Create SystemFunction into Root System Function named SF2 - Create
 * FunctionInputPort into SF2 named FIP21 - Create FunctionOutputPort into SF2 named FOP21 - Create SystemFunction into
 * Root System Function named SF3 - Create FunctionInputPort into SF3 named FIP31 - Create FunctionOutputPort into SF3
 * named FOP31 - Create SystemFunction into Root System Function named SF4 - Create FunctionInputPort into SF4 named
 * FIP41 - Create FunctionOutputPort into SF4 named FOP41 - Create FunctionOutputPort into SF4 named FOP42 - Create
 * SystemFunction into Root System Function named SF5 - Create FunctionInputPort into SF5 named FIP51 - Create
 * FunctionInputPort into SF5 named FIP52 - Create FunctionInputPort into SF5 named FIP53 - Create FunctionOutputPort
 * into SF5 named FOP51 - Create FunctionalExchange into SF5 named FE51 from FOP51(SF5) to FIP53(SF5) - Create
 * FunctionalExchange into Root System Function named FE1 from FOP11(SF1) to FIP21(SF2) - Create FunctionalExchange into
 * Root System Function named FE2 from FOP21(SF2) to FIP31(SF3) - Create FunctionalExchange into Root System Function
 * named FE3 from FOP31(SF3) to FIP41(SF4) - Create FunctionalExchange into Root System Function named FE4 from
 * FOP41(SF4) to FIP51(SF5) - Create FunctionalExchange into Root System Function named FE5 from FOP42(SF4) to
 * FIP52(SF5) - Rename ActorPkg from System Analysis to Actors - Create TransfoLink into Actors named - Create KeyValue
 * into named - Create Actor into Actors named A1 - Create ComponentFunctionalAllocation into A1 to SF1 - Create Actor
 * into Actors named A2 - Create ComponentFunctionalAllocation into A2 to SF4 - Create Actor into Actors named A3 -
 * Create ComponentFunctionalAllocation into A3 to SF5 - Rename System from System Analysis to System - Create
 * ComponentFunctionalAllocation into System to SF2 - Create ComponentFunctionalAllocation into System to SF3 - Create
 * StateMachine into System named System State Machine - Create Region into System State Machine named Default Region
 * 
 * - Create Capability into Capabilities named C1 - Create Scenario into C1 named S11 -> Create InstanceRole IR111
 * covering System -> Create InstanceRole IR112 covering Actor 3 -> Create InstanceRole IR113 covering Entity 1 ->
 * Create InstanceRole IR114 covering SEntity 1 -> Start ME111 for message SM111 SYNCHRONOUS_CALL from (IR113) ->
 * Message SM111 linked to (FE1) -> End ME112 for message SM111 SYNCHRONOUS_CALL from (IR111) -> Start execution E111
 * from (ME112) -> Start ME113 for message SM112 SYNCHRONOUS_CALL from (IR111) -> Message SM112 linked to (FE2) -> End
 * ME114 for message SM112 SYNCHRONOUS_CALL from (IR111) -> Start execution E112 from (ME114) -> Start ME115 for message
 * SM113 SYNCHRONOUS_CALL from (IR111) -> Message SM113 linked to (FE3) -> End ME116 for message SM113 SYNCHRONOUS_CALL
 * from (IR114) -> Start execution E113 from (ME116) -> Start ME117 for message SM114 SYNCHRONOUS_CALL from (IR114) ->
 * Message SM114 linked to (FE4) -> End ME118 for message SM114 SYNCHRONOUS_CALL from (IR112) -> Start execution E114
 * from (ME118) -> Start ME119 for message SM115 SYNCHRONOUS_CALL from (IR112) -> Message SM115 linked to (FE51) -> End
 * ME1110 for message SM115 SYNCHRONOUS_CALL from (IR112) -> Start execution E115 from (ME1110) -> Start ME1111 for
 * message SM116 REPLY from (IR112) -> Message SM116 linked to (FE51) -> End execution E115 for (ME1111) -> End ME1112
 * for message SM116 REPLY from (IR112) -> Start ME1113 for message SM117 REPLY from (IR112) -> Message SM117 linked to
 * (FE4) -> End execution E114 for (ME1113) -> End ME1114 for message SM117 REPLY from (IR114) -> Start ME1115 for
 * message SM118 REPLY from (IR114) -> Message SM118 linked to (FE3) -> End execution E113 for (ME1115) -> End ME1116
 * for message SM118 REPLY from (IR111) -> Start ME1117 for message SM119 REPLY from (IR111) -> Message SM119 linked to
 * (FE2) -> End execution E112 for (ME1117) -> End ME1118 for message SM119 REPLY from (IR111) -> Start ME1119 for
 * message SM1110 REPLY from (IR111) -> Message SM1110 linked to (FE1) -> End execution E111 for (ME1119) -> End ME1120
 * for message SM1110 REPLY from (IR113) -> Start ME1121 for message SM1111 ASYNCHRONOUS_CALL from (IR113) -> Message
 * SM1111 linked to (FE1) -> End ME1122 for message SM1111 ASYNCHRONOUS_CALL from (IR111) -> Start execution E116 from
 * (ME1122) -> Start ME1123 for message SM1112 ASYNCHRONOUS_CALL from (IR111) -> Message SM1112 linked to (FE3) -> End
 * ME1124 for message SM1112 ASYNCHRONOUS_CALL from (IR114) -> Start execution E117 from (ME1124) -> Start ME1125 for
 * message SM1113 ASYNCHRONOUS_CALL from (IR114) -> Message SM1113 linked to (FE4) -> End ME1126 for message SM1113
 * ASYNCHRONOUS_CALL from (IR112) -> Start execution E118 from (ME1126) -> End execution E118 for (SM1113) -> End
 * execution E117 for (SM1112) -> End execution E116 for (SM1111) - Create ActorCapabilityInvolvement into C1 named -
 * Create ActorCapabilityInvolvement into C1 named - Create ActorCapabilityInvolvement into C1 named - Rename
 * SystemCapabilityInvolvement from C1 to - Create OperationalCapabilityRealization into C1 to OperationalCapability 1
 * 
 * 
 * * Expected Result:\
 *
 * Messages incoming and outgoing of system should not be transitioned since allocation of function is not performed
 * into logical system
 * 
 */
public class CreateRule_ES2ES_04 extends TopDownTransitionTestCase {
  private String id_s11 = "c16d657c-beee-4876-9a7e-0b9c94d590ad";
  private String id_ir111 = "a985c586-9050-45a2-8606-679f4dabc5df";
  private String id_ir112 = "ccc94343-eefd-4efe-a6bb-65b4d6746bfd";
  private String id_ir113 = "72f03527-5e94-45ba-824f-2190436dde8b";
  private String id_ir114 = "968b96d1-1124-4675-9806-61646d5bde0c";
  private String id_sm111 = "4c0b6d7c-eba8-45a2-9304-15174651a3fe";
  private String id_sm112 = "07e4aaea-d7e5-426a-9404-1ef97dbddf7d";
  private String id_sm113 = "17fe735b-2698-4a1c-b6b9-94d9279ff947";
  private String id_sm114 = "b3400322-c8b2-46b7-88a0-2a734b8a679c";
  private String id_sm115 = "e76e2052-a7a4-4e0c-bc72-f5b39e87dccd";
  private String id_sm116 = "7f54887c-ff09-4512-9037-06c2ef48e47a";
  private String id_sm117 = "eaffec13-5a65-4daf-99db-082b60b798b7";
  private String id_sm118 = "df4b796b-09cf-4af4-898a-2c17bef31fd9";
  private String id_sm119 = "c186f3d5-c01f-465d-ac26-de5b34574c21";
  private String id_sm1110 = "028bc24e-6254-4b7d-9f09-3ff935ae68ed";
  private String id_e111 = "ffd5e702-f7fa-4087-997f-2fa1994a7cc1";
  private String id_e112 = "a3c4270c-0a53-416b-a759-4de1fba7d850";
  private String id_e113 = "469e43ec-e807-4fcf-91ac-ca3b5fd1a991";
  private String id_e114 = "5d262de9-3fc9-4158-a59c-4cb2652377a4";
  private String id_e115 = "f17937c1-76ab-42f4-8c62-c9bb7c80d894";
  private String id_e116 = "460f2c09-f9d3-459f-b2ec-b1c12e320b34";
  private String id_e117 = "794105eb-db29-4984-871e-1b294b2e43ec";
  private String id_e118 = "e27f08bf-5de2-46eb-be1c-8cbc11087d07";
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performEStoESTransition(Arrays.asList(getObject(id_s11)));
    mustBeTransitioned(id_s11);
    mustBeTransitioned(id_ir111);
    mustBeTransitioned(id_ir112);
    mustBeTransitioned(id_ir113);
    mustBeTransitioned(id_ir114);

    // Messages incoming from system and outgoing should not be transitioend
    shouldNotBeTransitioned(id_sm111);
    msgShouldNotBeTransitioned(id_sm112);
    msgShouldNotBeTransitioned(id_sm113);
    msgMustBeTransitioned(id_sm114);
    msgMustBeTransitioned(id_sm115);

    // Reply messages incoming from system and outgoing should not be transitioend
    msgShouldNotBeTransitioned(id_sm1110);
    msgShouldNotBeTransitioned(id_sm119);
    msgShouldNotBeTransitioned(id_sm118);
    msgMustBeTransitioned(id_sm117);
    msgMustBeTransitioned(id_sm116);

    // Messages incoming from system and outgoing should not be transitioend
    executionShouldNotBeTransitioned(id_e111);
    executionShouldNotBeTransitioned(id_e112);
    executionShouldNotBeTransitioned(id_e113);
    executionMustBeTransitioned(id_e114);
    executionMustBeTransitioned(id_e115);
    executionShouldNotBeTransitioned(id_e116);
    executionShouldNotBeTransitioned(id_e117);
    executionMustBeTransitioned(id_e118);
  }

  protected void msgShouldNotBeTransitioned(String id) {
    SequenceMessage msge = getObject(id);
    shouldNotBeTransitioned(id);

    if (msge.getReceivingEnd() != null) {
      shouldNotBeTransitioned(msge.getReceivingEnd().getId());
    }
    if (msge.getSendingEnd() != null) {
      shouldNotBeTransitioned(msge.getSendingEnd().getId());
    }
  }

  protected void msgMustBeTransitioned(String id) {
    SequenceMessage msge = getObject(id);
    mustBeTransitioned(id);

    if (msge.getReceivingEnd() != null) {
      mustBeTransitioned(msge.getReceivingEnd().getId());
      if (msge.getReceivingEnd().getEvent() != null) {
        mustBeTransitioned(msge.getReceivingEnd().getEvent().getId());
      }
    }
    if (msge.getSendingEnd() != null) {
      mustBeTransitioned(msge.getSendingEnd().getId());
      if (msge.getSendingEnd().getEvent() != null) {
        mustBeTransitioned(msge.getSendingEnd().getEvent().getId());
      }
    }
  }

  protected void executionShouldNotBeTransitioned(String id) {
    Execution msge = getObject(id);
    shouldNotBeTransitioned(id);
    if (msge.getStart() != null) {
      shouldNotBeTransitioned(msge.getStart().getId());
    }
    if (msge.getFinish() != null) {
      shouldNotBeTransitioned(msge.getFinish().getId());
    }
  }

  protected void executionMustBeTransitioned(String id) {
    Execution msge = getObject(id);
    mustBeTransitioned(id);
    if (msge.getStart() != null) {
      mustBeTransitioned(msge.getStart().getId());
    }
    if (msge.getFinish() != null) {
      mustBeTransitioned(msge.getFinish().getId());
    }
  }

}
