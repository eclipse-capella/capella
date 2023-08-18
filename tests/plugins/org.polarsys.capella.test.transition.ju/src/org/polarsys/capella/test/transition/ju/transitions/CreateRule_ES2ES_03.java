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

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Scenario transition CES2CIS
 * 
 * * - Create OperationalActivity into Operational Activities named Root Operational Activity - Create
 * OperationalActivity into Root Operational Activity named OA1 - Create OperationalActivity into Root Operational
 * Activity named OA2 - Create OperationalActivity into Root Operational Activity named OA3 - Create FunctionalExchange
 * into Root Operational Activity named FE1 from to - Create FunctionalExchange into Root Operational Activity named FE2
 * from to - Rename OperationalCapabilityPkg from Operational Analysis to Operational Capabilities - Create
 * OperationalCapability into Operational Capabilities named OC1 - Create Scenario into OC1 named S11 -> Create
 * InstanceRole IR111 covering Entity 2 -> Create InstanceRole IR112 covering OperationalActor 3 -> Create InstanceRole
 * IR113 covering Entity 4 -> Start ME111 for message SM111 CREATE from (IR111) -> Message SM111 linked to () -> End
 * ME112 for message SM111 CREATE from (IR112) -> Start ME113 for message FE1 SYNCHRONOUS_CALL from (IR111) -> Message
 * FE1 linked to (FE1) -> End ME114 for message FE1 SYNCHRONOUS_CALL from (IR113) -> Start execution E111 from (ME114)
 * -> Start ME115 for message FE2 ASYNCHRONOUS_CALL from (IR113) -> Message FE2 linked to (FE2) -> End ME116 for message
 * FE2 ASYNCHRONOUS_CALL from (IR112) -> Start execution E112 from (ME116) -> End execution E112 for (FE2) -> Start
 * ME118 for message FE1 REPLY from (IR113) -> Message FE1 linked to (FE1) -> End execution E111 for (ME118) -> End
 * ME119 for message FE1 REPLY from (IR111) -> Start ME1110 for message SM115 DELETE from (IR112) -> Message SM115
 * linked to () -> End ME1111 for message SM115 DELETE from (IR111) - Rename EntityPkg from Operational Analysis to
 * Operational Entities - Create Entity into Operational Entities named Operational Entity - Create StateMachine into
 * Operational Entity named Operational Entity State Machine - Create Region into Operational Entity State Machine named
 * Default Region - Create Part into Operational Entity named Entity 2 typed by Entity 2 - Create Part into Operational
 * Entity named OperationalActor 3 typed by OperationalActor 3 - Create Part into Operational Entity named Entity 4
 * typed by Entity 4 - Create Entity into Operational Entities named Entity 2 - Create ComponentFunctionalAllocation
 * into Entity 2 to OA1 - Create OperationalActor into Operational Entities named OperationalActor 3 - Create
 * ComponentFunctionalAllocation into OperationalActor 3 to OA3 - Create Entity into Operational Entities named Entity 4
 * - Create ComponentFunctionalAllocation into Entity 4 to OA2
 * 
 * * Expected Result:\
 *
 * OE should be transitioned to SE
 * 
 */
public class CreateRule_ES2ES_03 extends TopDownTransitionTestCase {
  private String id_s11 = "f3c2e751-a8fe-4d58-870f-3e6a3c46980c";
  private String id_ir111 = "f775dae6-98f9-4554-94e2-defdde9bae31";
  private String id_ir112 = "d959e2b9-c97c-44ad-9ff9-fc4ba2003ff7";
  private String id_sm111 = "bfa3fc7c-a6be-4b30-99b8-273cce586735";
  private String id_me111 = "5269c28e-8c7c-48c6-b197-28dc8039b2fa";
  private String id_me112 = "d9589480-fbcc-4853-abf7-e856ad955df3";
  private String id_me113 = "450d3057-b522-4fac-99eb-80ae6c5cd52b";
  private String id_me114 = "91f71e9d-0bbd-4b68-8919-173e329a32e8";
  private String id_me115 = "b6065ffc-57a6-48e7-af4a-a2d8d8cd14a4";
  private String id_me116 = "071de9da-8105-404c-a338-3897a6291f6a";
  private String id_me118 = "643df477-a81c-49de-bb16-21d810f6863f";
  private String id_e111 = "e0748c97-c886-4e26-813f-c82b7dd036d8";
  private String id_e112 = "4e9476d3-eb90-4fa4-9c3d-17d5276612ff";
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
    Scenario sc = (Scenario) mustBeTransitioned(id_s11);
    assertTrue(NLS.bind(Messages.ShouldBeA, "SC1", "DATAFLOW"), sc.getKind() == ScenarioKind.DATA_FLOW);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "sc11t.getOwnedInstanceroles", "2"),
        sc.getOwnedInstanceRoles().size() == 2);
    mustBeTransitioned(id_ir111);
    mustBeTransitioned(id_ir112);

    mustBeTransitioned(id_sm111);
    mustBeTransitioned(id_e111);
    mustBeTransitioned(id_e112);
    mustBeTransitioned(id_me111);
    mustBeTransitioned(id_me112);
    mustBeTransitioned(id_me113);
    mustBeTransitioned(id_me114);
    mustBeTransitioned(id_me115);
    mustBeTransitioned(id_me116);
    mustBeTransitioned(id_me118);
  }

}
