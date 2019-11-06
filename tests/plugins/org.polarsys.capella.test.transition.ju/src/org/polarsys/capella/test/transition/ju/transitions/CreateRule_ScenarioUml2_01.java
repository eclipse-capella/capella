/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Scenario transition with uml2 elements - Rename SystemFunctionPkg from System Analysis to System Functions -
 * Create SystemFunction into System Functions named SF1 - Create FunctionRealization into SF1 to Root Operational
 * Activity - Create SystemFunction into SF1 named SF11 - Create FunctionInputPort into SF11 named FIP111 - Create
 * FunctionOutputPort into SF11 named FOP111 - Create FunctionOutputPort into SF11 named FOP112 - Create SystemFunction
 * into SF1 named SF12 - Create FunctionInputPort into SF12 named FIP121 - Create FunctionOutputPort into SF12 named
 * FOP121 - Create SystemFunction into SF1 named SF13 - Create FunctionInputPort into SF13 named FIP131 - Create
 * SystemFunction into SF1 named SF14 - Create FunctionOutputPort into SF14 named FOP141 - Create SystemFunction into
 * SF1 named SF15 - Create FunctionInputPort into SF15 named FIP151 - Create FunctionalExchange into SF1 named FE11 from
 * FOP111(SF11) to FIP121(SF12) -> Linked to ExchangeItem(EI1) - Create FunctionalExchange into SF1 named FE12 from
 * FOP121(SF12) to FIP131(SF13) -> Linked to ExchangeItem(EI2) - Create FunctionalExchange into SF1 named FE13 from
 * FOP141(SF14) to FIP111(SF11) - Create FunctionalExchange into SF1 named FE14 from FOP112(SF11) to FIP151(SF15) -
 * Rename InterfacePkg from System Analysis to Interfaces - Create ExchangeItem into Interfaces named EI1 [EVENT] -
 * Create ExchangeItem into Interfaces named EI2 [OPERATION] - Create ExchangeItem into Interfaces named EI3 [OPERATION]
 * - Create ExchangeItem into Interfaces named EI4 [FLOW] - Create Interface into Interfaces named I1 - Create
 * ExchangeItemAllocation into I1 to EI2 - Create Interface into Interfaces named I2 - Create ExchangeItemAllocation
 * into I2 to EI3 - Create Interface into Interfaces named I3 - Create ExchangeItemAllocation into I3 to EI4 - Create
 * Interface into Interfaces named I4 - Create TransfoLink into I4 named - Create KeyValue into named - Create
 * ExchangeItemAllocation into I4 to EI1 - Create Interface into Interfaces named I5 - Create TransfoLink into I5 named
 * - Create KeyValue into named - Create ExchangeItemAllocation into I5 to EI2 - Create Interface into Interfaces named
 * I6 - Create TransfoLink into I6 named - Create KeyValue into named - Create ExchangeItemAllocation into I6 to EI1 -
 * Create Interface into Interfaces named I7 - Create TransfoLink into I7 named - Create KeyValue into named - Create
 * ExchangeItemAllocation into I7 to EI2 - Rename System from System Analysis to System - Create
 * ComponentFunctionalAllocation into System to SF11 - Create StateMachine into System named SM1 - Create Region into
 * SM1 named R11 - Create ComponentPort into System named CP1 - Create PortRealization into CP1 to FOP111 - Create
 * ComponentPort into System named CP2 - Create PortRealization into CP2 to FOP111 - Create ComponentPort into System
 * named CP3 - Create PortRealization into CP3 to FOP112 - Create ComponentPort into System named CP4 - Create
 * PortRealization into CP4 to FIP111 - Create InterfaceUse into System linked to I1 - Rename ActorPkg from System
 * Analysis to Actors - Create Actor into Actors named A1 - Create ComponentFunctionalAllocation into A1 to SF12 -
 * Create ComponentPort into A1 named CP11 - Create PortRealization into CP11 to FIP121 - Create ComponentPort into A1
 * named CP12 - Create PortRealization into CP12 to FOP121 - Create ComponentPort into A1 named CP13 - Create
 * PortRealization into CP13 to FIP121 - Create ComponentPort into A1 named CP14 - Create PortRealization into CP14 to
 * FOP121 - Create InterfaceUse into A1 linked to I2 - Create InterfaceImplementation into A1 linked to I1 - Create
 * InterfaceImplementation into A1 linked to I3 - Create Actor into Actors named A2 - Create
 * ComponentFunctionalAllocation into A2 to SF13 - Create StateMachine into A2 named SM21 - Create Region into SM21
 * named R211 - Create State into R211 named S2111 - Create Region into S2111 named R21111 - Create State into R211
 * named S2112 - Create Region into S2112 named R21121 - Create Mode into R211 named M2113 - Create Region into M2113
 * named R21131 - Create ComponentPort into A2 named CP21 - Create PortRealization into CP21 to FIP131 - Create
 * ComponentPort into A2 named CP22 - Create PortRealization into CP22 to FIP131 - Create InterfaceUse into A2 linked to
 * I3 - Create InterfaceImplementation into A2 linked to I2 - Create Actor into Actors named A3 - Create
 * ComponentFunctionalAllocation into A3 to SF14 - Create ComponentPort into A3 named CP31 - Create PortRealization into
 * CP31 to FOP141 - Create Actor into Actors named A4 - Create ComponentFunctionalAllocation into A4 to SF15 - Create
 * ComponentPort into A4 named CP41 - Create PortRealization into CP41 to FIP151 - Create Capability into Capabilities
 * named C1 - Create Scenario into C1 named FS_S11 -> Create InstanceRole IR111 covering SF11 -> Create InstanceRole
 * IR112 covering SF12 -> Create InstanceRole IR113 covering SF13 -> Start ME111 for message SM111 SYNCHRONOUS_CALL from
 * (IR111) -> Message SM111 linked to (FE11) -> End ME112 for message SM111 SYNCHRONOUS_CALL from (IR112) -> Start
 * execution E111 from (ME112) -> Start ME113 for message SM112 CREATE from (IR112) -> Message SM112 linked to (FE12) ->
 * End ME114 for message SM112 CREATE from (IR113) -> Start ME115 for message SM113 REPLY from (IR112) -> Message SM113
 * linked to (FE11) -> End execution E111 for (ME115) -> End ME116 for message SM113 REPLY from (IR111) -> Start ME117
 * for message SM114 DELETE from (IR113) -> Message SM114 linked to (FE12) -> End ME118 for message SM114 DELETE from
 * (IR112) - Create Scenario into C1 named ESF_S12 -> Create InstanceRole IR121 covering part_P1 -> Create InstanceRole
 * IR122 covering part_P2 -> Create InstanceRole IR123 covering part_P3 -> Start ME121 for message SM121
 * SYNCHRONOUS_CALL from (IR121) -> Message SM121 linked to (FE11) -> End ME122 for message SM121 SYNCHRONOUS_CALL from
 * (IR122) -> Start execution E121 from (ME122) -> Start ME123 for message SM122 CREATE from (IR122) -> Message SM122
 * linked to (FE12) -> End ME124 for message SM122 CREATE from (IR123) -> Start ME125 for message SM123 REPLY from
 * (IR122) -> Message SM123 linked to (FE11) -> End execution E121 for (ME125) -> End ME126 for message SM123 REPLY from
 * (IR121) -> Start ME127 for message SM124 DELETE from (IR123) -> Message SM124 linked to (FE12) -> End ME128 for
 * message SM124 DELETE from (IR122) - Create Scenario into C1 named ESB_S13 -> Create InstanceRole IR131 covering
 * part_P1 -> Create InstanceRole IR132 covering part_P2 -> Create InstanceRole IR133 covering part_P3 -> Start ME131
 * for message CE1 SYNCHRONOUS_CALL from (IR131) -> Message CE1 linked to (CE1) -> End ME132 for message CE1
 * SYNCHRONOUS_CALL from (IR132) -> Start execution E131 from (ME132) -> Start ME133 for message CE4 CREATE from (IR132)
 * -> Message CE4 linked to (CE4) -> End ME134 for message CE4 CREATE from (IR133) -> Start ME135 for message CE1 REPLY
 * from (IR132) -> Message CE1 linked to (CE1) -> End execution E131 for (ME135) -> End ME136 for message CE1 REPLY from
 * (IR131) -> Start ME137 for message CE4 DELETE from (IR133) -> Message CE4 linked to (CE4) -> End ME138 for message
 * CE4 DELETE from (IR132) - Create ActorCapabilityInvolvement into C1 named - Create ActorCapabilityInvolvement into C1
 * named - Rename SystemCapabilityInvolvement from C1 to * Expected Result:\ All uml2 elements should be transitioned
 * into each transition possible performed FS 2 ESF, should have all uml2 elements transitioned ESF 2 ESB, should have
 * all uml2 elements transitioned ESB 2 EI, should have all uml2 elements transitioned FS 2 FS, should have all uml2
 * elements transitioned, except states ESF 2 ESF, should have all uml2 elements transitioned, except states ESB 2 ESB,
 * should have all uml2 elements transitioned, except states
 */
public class CreateRule_ScenarioUml2_01 extends TopDownTransitionTestCase {
  private String id_c1 = "6b0ca281-94e3-4184-b5f7-d2b07e465f57";

  private String id_fs_s11 = "3d98dd46-4cf7-404e-917a-d53090742bee";
  private String id_ir111 = "a5e9b33e-687c-4491-8bfc-4722b130dcb4";
  private String id_ir112 = "e576caa2-5b83-42fb-be29-da359072614a";
  private String id_ir113 = "044fec11-d2dd-4a93-841d-27d3d71bf71f";
  private String id_fe119 = "a649a5af-727e-4465-83b4-f7994c8a3b15";
  private String id_fe1110 = "21db8b9b-a6a5-46f4-abe2-ea0e68a584ae";
  private String id_is1111 = "f7c9107b-dd87-48cd-990a-2e1e36c0181e";
  private String id_fe1112 = "821f7d3e-d8ed-4dab-98d5-6d39239ba3b2";
  private String id_io1113 = "ead86d44-6ac4-4da1-b8df-0ffed07d09a7";
  private String id_fe1114 = "1c9a7328-fa81-4de7-8c20-578ceeab078c";
  private String id_fe1115 = "57c75ff9-4ed6-4378-be01-6184556ccd6f";
  private String id_io1116 = "8e6ca45d-d0a7-46d4-bbc7-799fcf21a236";
  private String id_is1117 = "c9266be3-8791-4a7b-913a-69a2ece15587";
  private String id_io1118 = "f227e459-1eac-42cc-9f4d-3c25acbddb29";
  private String id_is1119 = "8ce1d455-6c57-4923-82e3-ec2a93d6aa73";
  private String id_fe1120 = "bf0ce55e-d97d-426a-a31a-b3513774d38b";
  private String id_iu112 = "a49a59b3-b38b-4827-9bd3-43760c460065";
  private String id_cf113 = "72dc1209-147a-4989-9c76-9e36a88b84e5";
  private String id_cf114 = "da5f6982-ae30-4f47-826a-d15df189e898";
  private String id_esf_s12 = "53627943-ea14-42bd-ad0f-2ad05ba95baa";
  private String id_fe129 = "65a9acb2-a1ea-435a-a14d-082336387221";
  private String id_fe1210 = "cfaa157b-8530-4fb1-97bc-b88c6fe91111";
  private String id_is1211 = "558ff761-69fa-4e29-aeaf-32bc9a358786";
  private String id_fe1212 = "f77e810a-a770-4e53-a93d-49ac5f5f3dff";
  private String id_io1213 = "7c79bc7d-2fa2-4f3a-9f3e-950bb9ca6435";
  private String id_fe1214 = "4289235a-54cd-43b1-a7c6-35f34ddd5e10";
  private String id_fe1215 = "6255f420-1aae-450b-a9ec-64292be6319d";
  private String id_io1216 = "73882279-c4fa-47e9-a409-d5920feac107";
  private String id_is1217 = "ad513d15-d169-4268-8292-a73d6a9aad73";
  private String id_io1218 = "36e9ebe9-4283-452d-9b4d-bec9ac835b24";
  private String id_is1219 = "507cda14-0501-435b-86ee-59ce92379a5d";
  private String id_fe1220 = "671ba4b6-0ac7-46bd-a373-21738e3b42a6";
  private String id_iu122 = "885ea985-5ce6-4057-af1c-d7273279a20a";
  private String id_cf123 = "3294af80-230e-4323-89c1-a863c78aa38a";
  private String id_cf124 = "b5905c45-2ade-44e1-a66b-ec93114238a9";
  private String id_esb_s13 = "22575e4c-a06c-426f-b600-157258bf2dcf";
  private String id_fe139 = "46e7e853-1174-4885-a544-306c3437e144";
  private String id_fe1310 = "2bfec0bc-19ed-4094-a6fe-9f6b0d495403";
  private String id_is1311 = "ffe3a9a3-fb6a-46d1-b70c-44720e96e5be";
  private String id_fe1312 = "04e7f753-1558-4760-a1d2-b13fb0f42ad7";
  private String id_io1313 = "00a29fc7-114e-4085-82a8-03bc9e1b4897";
  private String id_fe1314 = "59969108-a570-47e5-ba86-7349cf91495d";
  private String id_fe1315 = "2b2b5d02-b1c4-459b-9154-ed1ac28fe0b9";
  private String id_io1316 = "f6ca66ee-e133-44ba-a43c-531e95693e05";
  private String id_is1317 = "0e80e401-d914-4794-89fb-0ae2628cf618";
  private String id_io1318 = "b0ab6a1b-fe3a-4008-b088-d640bd8638a3";
  private String id_is1319 = "96c16648-74ee-4933-b9ed-6fdb8a052b6b";
  private String id_fe1320 = "9b1b63c3-9992-4908-afdd-4b2e93d174e2";
  private String id_iu132 = "eaf8b3f0-9749-47d4-8b49-19fd796a6379";
  private String id_cf133 = "9e12a9e7-7cd5-4e1f-a2ab-6744184e0e6f";
  private String id_cf134 = "5fbb356a-1ffe-4fd3-9b79-6b64cd24b8e3";
  private String id_capabilities = "c6092f71-d8db-4546-9588-20196d140ff4";
  private Scenario logScenC01;
  private Scenario logScenEsfS12;
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
    step4();
    step5();
    step6();
  }

  private void step1() {
    performFStoESTransition(Arrays.asList(getObject(id_fs_s11)));
    // Retrieve a scenario in the same capability
    logScenC01 = (Scenario) mustBeTransitioned(id_fs_s11, getObject(id_c1));
    mustBeTransitioned(id_ir111, logScenC01);
    mustBeTransitioned(id_ir112, logScenC01);
    mustBeTransitioned(id_ir113, logScenC01);

    mustBeTransitionedAndReference(id_fe119, logScenC01);
    mustBeTransitionedAndReference(id_fe1110, logScenC01);
    mustBeTransitionedAndReference(id_is1111, logScenC01);
    mustBeTransitionedAndReference(getStateFragment(getObject(id_is1111)), logScenC01);
    mustBeTransitionedAndReference(getSecondState(getObject(id_is1111)), logScenC01);
    mustBeTransitionedAndReference(id_fe1112, logScenC01);
    mustBeTransitionedAndReference(id_io1113, logScenC01);
    mustBeTransitionedAndReference(id_fe1114, logScenC01);
    mustBeTransitionedAndReference(id_fe1115, logScenC01);
    mustBeTransitionedAndReference(id_io1116, logScenC01);
    mustBeTransitionedAndReference(id_is1117, logScenC01);
    mustBeTransitionedAndReference(getStateFragment(getObject(id_is1117)), logScenC01);
    mustBeTransitionedAndReference(getSecondState(getObject(id_is1117)), logScenC01);
    mustBeTransitionedAndReference(id_io1118, logScenC01);
    mustBeTransitionedAndReference(id_is1119, logScenC01);
    mustBeTransitionedAndReference(getStateFragment(getObject(id_is1119)), logScenC01);
    mustBeTransitionedAndReference(getSecondState(getObject(id_is1119)), logScenC01);
    mustBeTransitionedAndReference(id_fe1120, logScenC01);
    mustBeTransitioned(id_iu112, logScenC01);
    mustBeTransitioned(id_cf113, logScenC01);
    mustBeTransitioned(id_cf114, logScenC01);
  }

  private void step2() {
    performESFtoESBTransition(Arrays.asList(getObject(id_esf_s12)));
    // Retrieve a scenario in the same capability
    logScenEsfS12 = (Scenario) mustBeTransitioned(id_esf_s12, getObject(id_c1));

    mustBeTransitionedAndReference(id_fe129, logScenEsfS12);
    mustBeTransitionedAndReference(id_fe1210, logScenEsfS12);
    mustBeTransitionedAndReference(id_is1211, logScenEsfS12);
    mustBeTransitionedAndReference(getStateFragment(getObject(id_is1211)), logScenEsfS12);
    mustBeTransitionedAndReference(getSecondState(getObject(id_is1211)), logScenEsfS12);
    mustBeTransitionedAndReference(id_fe1212, logScenEsfS12);
    mustBeTransitionedAndReference(id_io1213, logScenEsfS12);
    mustBeTransitionedAndReference(id_fe1214, logScenEsfS12);
    mustBeTransitionedAndReference(id_fe1215, logScenEsfS12);
    mustBeTransitionedAndReference(id_io1216, logScenEsfS12);
    mustBeTransitionedAndReference(id_is1217, logScenEsfS12);
    mustBeTransitionedAndReference(getStateFragment(getObject(id_is1217)), logScenEsfS12);
    mustBeTransitionedAndReference(getSecondState(getObject(id_is1217)), logScenEsfS12);
    mustBeTransitionedAndReference(id_io1218, logScenEsfS12);
    mustBeTransitionedAndReference(id_is1219, logScenEsfS12);
    mustBeTransitionedAndReference(getStateFragment(getObject(id_is1219)), logScenEsfS12);
    mustBeTransitionedAndReference(getSecondState(getObject(id_is1219)), logScenEsfS12);
    mustBeTransitionedAndReference(id_fe1220, logScenEsfS12);
    mustBeTransitioned(id_iu122, logScenEsfS12);
    mustBeTransitioned(id_cf123, logScenEsfS12);
    mustBeTransitioned(id_cf124, logScenEsfS12);
  }

  private void step3() {
    performEStoISTransition(Arrays.asList(getObject(id_esb_s13)));
    // Retrieve a scenario in the same capability
    EObject container = mustBeTransitioned(id_esb_s13, getObject(id_c1));

    mustBeTransitionedAndReference(id_fe139, container);
    mustBeTransitionedAndReference(id_fe1310, container);
    mustBeTransitionedAndReference(id_is1311, container);
    mustBeTransitionedAndReference(getStateFragment(getObject(id_is1311)), container);
    mustBeTransitionedAndReference(getSecondState(getObject(id_is1311)), container);
    mustBeTransitionedAndReference(id_fe1312, container);
    mustBeTransitionedAndReference(id_io1313, container);
    mustBeTransitionedAndReference(id_fe1314, container);
    //mustBeTransitionedAndReference(id_fe1315, container);
    mustBeTransitionedAndReference(id_io1316, container);
    mustBeTransitionedAndReference(id_is1317, container);
    mustBeTransitionedAndReference(getStateFragment(getObject(id_is1317)), container);
    mustBeTransitionedAndReference(getSecondState(getObject(id_is1317)), container);
    mustBeTransitionedAndReference(id_io1318, container);
    mustBeTransitionedAndReference(id_is1319, container);
    mustBeTransitionedAndReference(getStateFragment(getObject(id_is1319)), container);
    mustBeTransitionedAndReference(getSecondState(getObject(id_is1319)), container);
    mustBeTransitionedAndReference(id_fe1320, container);
    mustBeTransitioned(id_iu132, container);
    mustBeTransitioned(id_cf133, container);
    mustBeTransitioned(id_cf134, container);
  }

  private void step4() {
    performFStoFSTransition(Arrays.asList(getObject(id_fs_s11)));
    // Retrieve scenario into the logical capability
    EObject container = mustBeTransitionedAndReference(id_fs_s11, getObject(id_capabilities));
    assertEquals(logScenC01.getId(), ((Scenario) container).getId());
    mustBeTransitionedAndReference(id_ir111, logScenC01);
    mustBeTransitionedAndReference(id_ir112, logScenC01);
    mustBeTransitionedAndReference(id_ir113, logScenC01);
    mustBeTransitionedAndReference(id_fe119, logScenC01);
    mustBeTransitionedAndReference(id_fe1110, logScenC01);

    // shouldNotBeTransitionedTo("IS1111", id_is1111, container);
    // shouldNotBeTransitionedTo("SF1111", getStateFragment(getObject(id_is1111)), container);
    // shouldNotBeTransitionedTo("SF1111", getSecondState(getObject(id_is1111)), container);
    mustBeTransitionedAndReference(id_fe1112, container);
    mustBeTransitionedAndReference(id_io1113, container);
    mustBeTransitionedAndReference(id_fe1114, container);
    mustBeTransitionedAndReference(id_fe1115, container);
    mustBeTransitionedAndReference(id_io1116, container);
    // shouldNotBeTransitionedTo("IS1117", id_is1117, container);
    // shouldNotBeTransitionedTo("SF1117", getStateFragment(getObject(id_is1117)), container);
    // shouldNotBeTransitionedTo("SF1117", getSecondState(getObject(id_is1117)), container);
    mustBeTransitionedAndReference(id_io1118, container);
    // shouldNotBeTransitionedTo("IS1119", id_is1119, container);
    // shouldNotBeTransitionedTo("SF1119", getStateFragment(getObject(id_is1119)), container);
    // shouldNotBeTransitionedTo("SF1119", getSecondState(getObject(id_is1119)), container);
    mustBeTransitionedAndReference(id_fe1120, container);
    mustBeTransitionedAndReference(id_iu112, container);
    mustBeTransitionedAndReference(id_cf113, container);
    mustBeTransitionedAndReference(id_cf114, container);
  }

  private void step5() {
    performEStoESTransition(Arrays.asList(getObject(id_esf_s12)));
    // Retrieve scenario into the logical capability
    EObject container = mustBeTransitionedAndReference(id_esf_s12, getObject(id_capabilities));
    assertEquals(logScenEsfS12.getId(), ((Scenario) container).getId());
    mustBeTransitionedAndReference(id_fe129, logScenEsfS12);
    mustBeTransitionedAndReference(id_fe1210, logScenEsfS12);

    // shouldNotBeTransitionedTo("IS1211", id_is1211, container);
    // shouldNotBeTransitionedTo("SF1211", getStateFragment(getObject(id_is1211)), container);
    // shouldNotBeTransitionedTo("SF1211", getSecondState(getObject(id_is1211)), container);
    mustBeTransitionedAndReference(id_fe1212, container);
    mustBeTransitionedAndReference(id_io1213, container);
    mustBeTransitionedAndReference(id_fe1214, container);
    mustBeTransitionedAndReference(id_fe1215, container);
    mustBeTransitionedAndReference(id_io1216, container);
    // shouldNotBeTransitionedTo("IS1217", id_is1217, container);
    // shouldNotBeTransitionedTo("SF1217", getStateFragment(getObject(id_is1217)), container);
    // shouldNotBeTransitionedTo("SF1217", getSecondState(getObject(id_is1217)), container);
    mustBeTransitionedAndReference(id_io1218, container);
    // shouldNotBeTransitionedTo("IS1219", id_is1219, container);
    // shouldNotBeTransitionedTo("SF1219", getStateFragment(getObject(id_is1219)), container);
    // shouldNotBeTransitionedTo("SF1219", getSecondState(getObject(id_is1219)), container);
    mustBeTransitionedAndReference(id_fe1220, container);
    mustBeTransitionedAndReference(id_iu122, container);
    mustBeTransitionedAndReference(id_cf123, container);
    mustBeTransitionedAndReference(id_cf124, container);
  }

  private void step6() {
    performEStoESTransition(Arrays.asList(getObject(id_esb_s13)));
    // Retrieve scenario into the logical capability
    EObject container = mustBeTransitionedAndReference(id_esb_s13, getObject(id_capabilities));

    mustBeTransitionedAndReference(id_fe139, container);
    mustBeTransitionedAndReference(id_fe1310, container);
    // shouldNotBeTransitionedTo("IS1311", id_is1311, container);
    // shouldNotBeTransitionedTo("SF1311", getStateFragment(getObject(id_is1311)), container);
    // shouldNotBeTransitionedTo("SF1311", getSecondState(getObject(id_is1311)), container);
    mustBeTransitionedAndReference(id_fe1312, container);
    mustBeTransitionedAndReference(id_io1313, container);
    mustBeTransitionedAndReference(id_fe1314, container);
    mustBeTransitionedAndReference(id_fe1315, container);
    mustBeTransitionedAndReference(id_io1316, container);
    // shouldNotBeTransitionedTo(id_is1317, container);
    // shouldNotBeTransitionedTo(getStateFragment(getObject(id_is1317)), container);
    // shouldNotBeTransitionedTo(getSecondState(getObject(id_is1317)), container);
    mustBeTransitionedAndReference(id_io1318, container);
    // shouldNotBeTransitionedTo("IS1319", id_is1319, container);
    // shouldNotBeTransitionedTo("SF1319", getStateFragment(getObject(id_is1319)), container);
    // shouldNotBeTransitionedTo("SF1319", getSecondState(getObject(id_is1319)), container);
    mustBeTransitionedAndReference(id_fe1320, container);
    mustBeTransitionedAndReference(id_iu132, container);
    mustBeTransitionedAndReference(id_cf133, container);
    mustBeTransitionedAndReference(id_cf134, container);
  }

  private String getSecondState(EObject _is1319_p) {
    for (EObject ref : EObjectExt.getReferencers(_is1319_p, InteractionPackage.Literals.TIME_LAPSE__START)) {
      if (ref instanceof StateFragment) {
        if (((StateFragment) ref).getFinish() != null) {
          return ((StateFragment) ref).getFinish().getId();
        }
      }
    }

    for (EObject ref : EObjectExt.getReferencers(_is1319_p, InteractionPackage.Literals.TIME_LAPSE__FINISH)) {
      if (ref instanceof StateFragment) {
        if (((StateFragment) ref).getStart() != null) {
          return ((StateFragment) ref).getStart().getId();
        }
      }
    }

    return null;
  }

  private String getStateFragment(EObject _is1319_p) {
    List<EObject> refs = EObjectExt.getReferencers(_is1319_p, InteractionPackage.Literals.TIME_LAPSE__START);
    refs.addAll(EObjectExt.getReferencers(_is1319_p, InteractionPackage.Literals.TIME_LAPSE__FINISH));
    for (EObject ref : refs) {
      if (ref instanceof StateFragment) {
        return ((StateFragment) ref).getId();
      }
    }
    return null;
  }

}
