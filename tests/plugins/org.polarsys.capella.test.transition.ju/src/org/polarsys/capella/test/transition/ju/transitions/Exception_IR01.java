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
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Merge of instance roles
 * 
 * 
 * test 1 ES2ES
 * 
 * - Create OperationalActivity into Operational Activities named OA1 - Create OperationalActivity into OA1 named OA11 -
 * Create OperationalActivity into OA1 named OA12 - Create OperationalActivity into OA1 named OA13 - Create
 * OperationalActivity into OA1 named OA14 - Create FunctionalExchange into OA1 named FE11 from to - Create
 * FunctionalExchange into OA1 named FE12 from to - Create FunctionalExchange into OA1 named FE13 from to - Rename
 * EntityPkg from Operational Analysis to Operational Entities - Create Entity into Operational Entities named E1 -
 * Create StateMachine into E1 named SM11 - Create Region into SM11 named R111 - Create Part into E1 named part_P11
 * typed by E2 - Create Part into E1 named part_P12 typed by E3 - Create Part into E1 named part_P13 typed by E4 -
 * Create Part into E1 named part_P14 typed by E5 - Create Entity into Operational Entities named E2 - Create
 * ComponentFunctionalAllocation into E2 to OA11 - Create Entity into Operational Entities named E3 - Create
 * ComponentFunctionalAllocation into E3 to OA12 - Create Entity into Operational Entities named E4 - Create
 * ComponentFunctionalAllocation into E4 to OA13 - Create Entity into Operational Entities named E5 - Create
 * ComponentFunctionalAllocation into E5 to OA14 - Rename OperationalCapabilityPkg from Operational Analysis to
 * Operational Capabilities - Create OperationalCapability into Operational Capabilities named OC1 - Create Scenario
 * into OC1 named S11 -> Create InstanceRole IR111 covering part_P13 -> Create InstanceRole IR112 covering part_P14 ->
 * Create InstanceRole IR113 covering part_P12 -> Create InstanceRole IR114 covering part_P11 -> Start ME111 for message
 * FE11 SYNCHRONOUS_CALL from (IR114) -> Message FE11 linked to (FE11) -> End ME112 for message FE11 SYNCHRONOUS_CALL
 * from (IR113) -> Start execution E111 from (ME112) -> Start ME113 for message SM112 CREATE from (IR113) -> Message
 * SM112 linked to () -> End ME114 for message SM112 CREATE from (IR111) -> Start ME115 for message FE11 REPLY from
 * (IR113) -> Message FE11 linked to (FE11) -> End execution E111 for (ME115) -> End ME116 for message FE11 REPLY from
 * (IR114) -> Start ME117 for message FE12 SYNCHRONOUS_CALL from (IR111) -> Message FE12 linked to (FE12) -> End ME118
 * for message FE12 SYNCHRONOUS_CALL from (IR112) -> Start execution E112 from (ME118) -> Start ME119 for message FE12
 * REPLY from (IR112) -> Message FE12 linked to (FE12) -> End execution E112 for (ME119) -> End ME1110 for message FE12
 * REPLY from (IR111) -> Start ME1111 for message FE13 SYNCHRONOUS_CALL from (IR113) -> Message FE13 linked to (FE13) ->
 * End ME1112 for message FE13 SYNCHRONOUS_CALL from (IR111) -> Start execution E113 from (ME1112) -> Start ME1113 for
 * message FE13 REPLY from (IR111) -> Message FE13 linked to (FE13) -> End execution E113 for (ME1113) -> End ME1114 for
 * message FE13 REPLY from (IR113) -> Start ME1115 for message SM118 DELETE from (IR112) -> Message SM118 linked to ()
 * -> End ME1116 for message SM118 DELETE from (IR111) -> Start ME1117 for message SM119 DELETE from (IR112) -> Message
 * SM119 linked to () -> End ME1118 for message SM119 DELETE from (IR113)
 * 
 * 
 * Perform transition of Entity 2 3 into system, Perform transition of Entity 4 5 into an unique actor,
 * 
 * 
 * 
 * Expected Result:\
 *
 * - Only 2 instance roles should be created
 * 
 * 
 * 
 * test 2 FS2FS
 * 
 * 
 * - Create OperationalActivity into OA1 named OA15 - Create OperationalActivity into OA1 named OA16 - Create
 * FunctionalExchange into OA16 named FE161 from OA15 to OA16 - Create OperationalActivity into OA1 named OA17 - Create
 * FunctionalExchange into OA17 named FE171 from OA16 to OA17 - Create OperationalActivity into OA1 named OA18 - Create
 * FunctionalExchange into OA18 named FE181 from OA17 to OA18
 * 
 * 
 * - Create Scenario into OC2 named S21 -> Create InstanceRole IR211 covering OA15 -> Create InstanceRole IR212 covering
 * OA16 -> Create InstanceRole IR213 covering OA17 -> Create InstanceRole IR214 covering OA18 -> Start ME211 for message
 * SM211 SYNCHRONOUS_CALL from (IR211) -> Message SM211 linked to (FE161) -> End ME212 for message SM211
 * SYNCHRONOUS_CALL from (IR212) -> Start execution E211 from (ME212) -> Start ME213 for message SM212 SYNCHRONOUS_CALL
 * from (IR212) -> Message SM212 linked to (FE171) -> End ME214 for message SM212 SYNCHRONOUS_CALL from (IR213) -> Start
 * execution E212 from (ME214) -> Start ME215 for message SM213 SYNCHRONOUS_CALL from (IR213) -> Message SM213 linked to
 * (FE181) -> End ME216 for message SM213 SYNCHRONOUS_CALL from (IR214) -> Start execution E213 from (ME216) -> Start
 * ME217 for message SM214 REPLY from (IR214) -> Message SM214 linked to (FE181) -> End execution E213 for (ME217) ->
 * End ME218 for message SM214 REPLY from (IR213) -> Start ME219 for message SM215 REPLY from (IR213) -> Message SM215
 * linked to (FE171) -> End execution E212 for (ME219) -> End ME2110 for message SM215 REPLY from (IR212) -> Start
 * ME2111 for message SM216 REPLY from (IR212) -> Message SM216 linked to (FE161) -> End execution E211 for (ME2111) ->
 * End ME2112 for message SM216 REPLY from (IR211)
 * 
 * 
 * Perform transition of OA
 * 
 * Move port of FE161t from OA15t to OA16t Delete OA15t Create a sub function into SF17 Move port of FE181t from OA18t
 * to sub function Delete OA18t
 * 
 * 
 * * Expected Result:\
 * 
 * - Only 3 instance roles should be created
 * 
 * 
 * test 3 FS2ES
 *
 * - Create Entity into Operational Entities named E22 - Create ComponentFunctionalAllocation into E22 to OA18 - Create
 * ComponentFunctionalAllocation into E22 to OA17 - Create Entity into Operational Entities named E21 - Create
 * ComponentFunctionalAllocation into E21 to OA16 - Create ComponentFunctionalAllocation into E21 to OA15
 * 
 * 
 * * Expected Result:\
 * 
 * - Only 2 instance roles should be created
 * 
 * 
 */
public class Exception_IR01 extends TopDownTransitionTestCase {
  private String id_s11 = "d6d077e0-acc3-4e93-90c3-13237402aed3";
  private String id_ir111 = "8b554280-ec64-499c-a741-d0fef460e80c";
  private String id_ir112 = "9a654581-879e-48c5-adef-db94e0fecb2d";
  private String id_ir113 = "9f480327-8684-444f-b056-a385e03cc3ec";
  private String id_ir114 = "02d65bf5-109a-4dfc-88de-20c866b57bf0";
  private String id_me111 = "7697a982-a095-432d-a8fa-d1bfa9aea7c5";
  private String id_me112 = "46167834-11a2-4566-bf17-16a2935aaee6";
  private String id_me113 = "5267d2a3-2363-405e-8c10-974787afbcc6";
  private String id_me114 = "3a5d3bc7-6a8f-4a11-950b-ccccdc33a439";
  private String id_me115 = "cd1e6a10-55b5-45b1-9e0e-4723af9cc97b";
  private String id_me116 = "9a20ba54-6fe6-4547-a323-01ffd2563b2e";
  private String id_me118 = "7b351133-4e6c-45f7-a3a9-e58eb868cb15";
  private String id_e111 = "fa64cd98-85be-40f8-a4b7-e8101d7f5cd5";
  private String id_e112 = "ca12b6d1-2609-4541-834a-bf0ec69e61bb";
  private String id_s21 = "3533e9fe-a93c-4b95-8849-4aeebd919100";
  private String id_ir211 = "69cb912f-20b7-40f2-b989-31fcf35623be";
  private String id_ir212 = "8b7a4fda-ebea-43a5-91b7-603e8a1f3d20";
  private String id_ir213 = "e9654567-64cc-4449-8a0d-735caed13947";
  private String id_ir214 = "507e3301-aaf7-4927-aff9-df064a069d9c";
  private String id_me211 = "98230cb4-386b-4bda-bf32-d4e65a759442";
  private String id_me212 = "3800f1cd-1c5c-49c1-bcb9-4a3abc20146d";
  private String id_me213 = "ec9b0574-4350-4529-9ade-6f6c93712903";
  private String id_me214 = "4a6b3825-f811-4412-baef-8434bbd7ffb2";
  private String id_me215 = "b3afca05-9525-4cfa-a0c4-971e6377fd00";
  private String id_me216 = "25afde3f-6666-4c0e-b1c0-623835b4a4ae";
  private String id_me218 = "e5e4a5cf-e53c-499e-97d9-74d811c4fe48";
  private String id_e211 = "18d8ad09-3ab8-4e8a-a302-be770d69e8ae";
  private String id_e212 = "65d3a6d8-3e29-4c82-8619-9cc9a5330532";
  private String id_s22 = "69f289c3-1476-474d-abac-7b739fe590b6";
  private String id_ir221 = "8370e567-6ba1-45b6-98ad-ee16575df902";
  private String id_ir222 = "c36ec89d-8e56-40bc-bcce-13b45723885c";
  private String id_ir223 = "748852e7-880f-415e-bab1-f5355704b431";
  private String id_ir224 = "f9a69662-092d-4a79-9b57-0395566ea6e9";
  private String id_me221 = "aba16f1a-67b7-4346-b24e-69a0bdb241c6";
  private String id_me222 = "1e6f5d47-9b8f-4a1b-b0f6-7890842a5c87";
  private String id_me223 = "3713261f-52d6-4b5e-abbe-70c94bc90ff1";
  private String id_me224 = "408ce27b-86d0-4c0e-bedb-a6376961d351";
  private String id_me225 = "d1e56f11-9667-4696-b5c9-eeaabea1fa63";
  private String id_me226 = "241e503a-f4fb-4911-a052-79370dd8ce48";
  private String id_me228 = "de063ded-9cfa-498a-b4a6-37695d5c0d3b";
  private String id_e221 = "4c139207-59f8-4afe-b19a-286462391402";
  private String id_e222 = "0e98b2d8-b902-4853-b045-628412801c35";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
  }

  /**
   * Only 2 instance roles should be created
   */
  private void step1() {
    performEStoESTransition(Arrays.asList(getObject(id_s11)));
    Scenario source = (Scenario) getObject(id_s11);
    Scenario container = (Scenario) mustBeTransitioned(id_s11);

    InstanceRole role1 = (InstanceRole) mustBeTransitionedTo(id_ir111, InteractionPackage.Literals.INSTANCE_ROLE,
        container);
    InstanceRole role2 = (InstanceRole) mustBeTransitionedTo(id_ir112, InteractionPackage.Literals.INSTANCE_ROLE,
        container);
    InstanceRole role3 = (InstanceRole) mustBeTransitionedTo(id_ir113, InteractionPackage.Literals.INSTANCE_ROLE,
        container);
    InstanceRole role4 = (InstanceRole) mustBeTransitionedTo(id_ir114, InteractionPackage.Literals.INSTANCE_ROLE,
        container);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "sc11.getOwnedInstanceroles", "4"), //$NON-NLS-2$
        source.getOwnedInstanceRoles().size() == 4);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "sc11t.getOwnedInstanceroles", "2"), //$NON-NLS-2$
        container.getOwnedInstanceRoles().size() == 2);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "IR111t", "IR112t"), role1 == role2); //$NON-NLS-2$
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "IR113t", "IR114t"), role3 == role4); //$NON-NLS-2$

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

  /**
   * Only 3 instance roles should be created
   */
  private void step2() {
    performFStoFSTransition(Arrays.asList(getObject(id_s21)));
    Scenario source = (Scenario) getObject(id_s21);
    Scenario container = (Scenario) mustBeTransitioned(id_s21);

    InstanceRole role1 = (InstanceRole) mustBeTransitionedTo(id_ir211, InteractionPackage.Literals.INSTANCE_ROLE,
        container);
    InstanceRole role2 = (InstanceRole) mustBeTransitionedTo(id_ir212, InteractionPackage.Literals.INSTANCE_ROLE,
        container);
    mustBeTransitionedTo(id_ir213, InteractionPackage.Literals.INSTANCE_ROLE, container);
    mustBeTransitionedTo(id_ir214, InteractionPackage.Literals.INSTANCE_ROLE, container);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "sc11.getOwnedInstanceroles", "4"), //$NON-NLS-2$
        source.getOwnedInstanceRoles().size() == 4);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "sc11t.getOwnedInstanceroles", "3"), //$NON-NLS-2$
        container.getOwnedInstanceRoles().size() == 3);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "IR111t", "IR112t"), role1 == role2); //$NON-NLS-2$

    mustBeTransitioned(id_e211);
    mustBeTransitioned(id_e212);
    mustBeTransitioned(id_me211);
    mustBeTransitioned(id_me212);
    mustBeTransitioned(id_me213);
    mustBeTransitioned(id_me214);
    mustBeTransitioned(id_me215);
    mustBeTransitioned(id_me216);
    mustBeTransitioned(id_me218);
  }

  /**
   * Only 2 instance roles should be created
   */
  private void step3() {
    performFStoESTransition(Arrays.asList(getObject(id_s22)));
    Scenario source = getObject(id_s22);
    Scenario container = (Scenario) mustBeTransitioned(id_s22);

    InstanceRole role1 = (InstanceRole) mustBeTransitionedTo(id_ir221, InteractionPackage.Literals.INSTANCE_ROLE,
        container);
    InstanceRole role2 = (InstanceRole) mustBeTransitionedTo(id_ir222, InteractionPackage.Literals.INSTANCE_ROLE,
        container);
    InstanceRole role3 = (InstanceRole) mustBeTransitionedTo(id_ir223, InteractionPackage.Literals.INSTANCE_ROLE,
        container);
    InstanceRole role4 = (InstanceRole) mustBeTransitionedTo(id_ir224, InteractionPackage.Literals.INSTANCE_ROLE,
        container);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "sc11.getOwnedInstanceroles", "4"), //$NON-NLS-2$
        source.getOwnedInstanceRoles().size() == 4);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "sc11t.getOwnedInstanceroles", "2"), //$NON-NLS-2$
        container.getOwnedInstanceRoles().size() == 2);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "IR111t", "IR112t"), role1 == role2); //$NON-NLS-2$
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "IR113t", "IR114t"), role3 == role4); //$NON-NLS-2$

    mustBeTransitioned(id_e221);
    mustBeTransitioned(id_e222);
    mustBeTransitioned(id_me221);
    mustBeTransitioned(id_me222);
    mustBeTransitioned(id_me223);
    mustBeTransitioned(id_me224);
    mustBeTransitioned(id_me225);
    mustBeTransitioned(id_me226);
    mustBeTransitioned(id_me228);
  }

}
