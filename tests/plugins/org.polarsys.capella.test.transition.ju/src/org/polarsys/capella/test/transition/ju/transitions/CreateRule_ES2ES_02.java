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
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
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
public class CreateRule_ES2ES_02 extends TopDownTransitionTestCase {
  private String id_s11 = "bceb58b0-3e42-41cd-8819-0e0b6bbbe908";
  private String id_sm111 = "5742a1f3-879d-483f-9c7f-6c418aeea7ab";

  private String id_smfe1 = "a46e54e0-8231-4d30-9883-27c8819edea4";
  private String id_smfe2 = "10564b7e-3087-415e-bf41-c2c74f2ac46e";
  private String id_smfe3 = "559e785f-28e6-4280-93ca-3a32674d0d21";
  private String id_me111 = "18939e89-1ca5-4451-9f33-3b4aa41e5e7a";
  private String id_me112 = "edb71bbf-1e5f-4e9c-aa75-e8041c2daa4e";
  private String id_me113 = "e496c96b-7038-4fba-95b2-3ac4d6f5edb5";
  private String id_me114 = "3adfc81e-6aa0-4ba2-9250-5c6f81a7a0a4";
  private String id_me115 = "2c1d5b53-ed58-464d-a6a1-b6beed97b53d";
  private String id_me116 = "46729b0a-9584-4211-bf30-5b1fd272a172";
  private String id_me118 = "9ef323ef-e765-44b7-84b8-01a1e4bb1c15";
  private String id_me119 = "0d86c309-2a27-4f9a-8061-974c2a8b0293";
  private String id_me1110 = "200d3a0c-370e-47de-9651-6ea9209017ab";
  private String id_me1111 = "f4ad10d9-7e71-4c9e-84a3-b36d8ab2b5b0";
  private String id_e111 = "65ebb17e-469e-468f-87bf-9a90da720f1d";
  private String id_e112 = "41c5464d-c38a-4e06-975c-8032cf52c7dc";
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, true);
  }
  
  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
  }

  private void step1() {
    performEStoESTransition(Arrays.asList(getObject(id_s11)));
    Scenario sc = (Scenario) mustBeTransitioned(id_s11);
    assertTrue(NLS.bind("Element ''{0}'' should be a ''{1}''", "SC1", "DATAFLOW"),
        sc.getKind() == ScenarioKind.DATA_FLOW);

    mustBeTransitioned(id_sm111);
    mustBeTransitioned(id_smfe1);
    mustBeTransitioned(id_smfe2);
    mustBeTransitioned(id_smfe3);
    mustBeTransitioned(id_e111);
    mustBeTransitioned(id_e112);
    mustBeTransitioned(id_me111);
    mustBeTransitioned(id_me112);
    mustBeTransitioned(id_me113);
    mustBeTransitioned(id_me114);
    mustBeTransitioned(id_me115);
    mustBeTransitioned(id_me116);
    mustBeTransitioned(id_me118);
    mustBeTransitioned(id_me119);
    mustBeTransitioned(id_me1110);
    mustBeTransitioned(id_me1111);
  }

}
