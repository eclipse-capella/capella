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

import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 *  
 * - Rename LogicalComponent from Logical Architecture to Logical System
 * - Create Connection into Logical System named LSC1 from CP111(LC11) to CP311(LC31)
 * - Create Connection into Logical System named LSC2 from CP12(LC1) to CP32(LC3)
 * - Create Connection into Logical System named LSC3 from CP112(LC11) to CP21(LC2)
 * - Create StateMachine into Logical System named SM1
 * - Create Region into SM1 named R11
 * - Create Part into Logical System named part_P1 typed by LC1
 * - Create Part into Logical System named part_P2 typed by LC2
 * - Create Part into Logical System named part_P3 typed by LC3
 * - Create Part into Logical System named part_P4 typed by LC4
 * - Create LogicalComponent into Logical System named LC1
 * - Create Part into LC1 named part_P11 typed by LC11
 * - Create ComponentPort into LC1 named CP12
 * - Create LogicalComponent into LC1 named LC11
 * - Create ComponentPort into LC11 named CP111
 * - Create ComponentPort into LC11 named CP112
 * - Create LogicalComponent into Logical System named LC2
 * - Create ComponentPort into LC2 named CP21
 * - Create LogicalComponent into Logical System named LC3
 * - Create Part into LC3 named part_P31 typed by LC31
 * - Create ComponentPort into LC3 named CP32
 * - Create LogicalComponent into LC3 named LC31
 * - Create ComponentPort into LC31 named CP311
 * - Create LogicalComponent into Logical System named LC4
 * - Create ComponentPort into LC4 named CP41
 * - Create Part into LC4 named part_P42 typed by LC41
 * - Create ComponentPort into LC4 named CP43
 * - Create LogicalComponent into LC4 named LC41
 * - Create ComponentPort into LC41 named CP411
 * - Create ComponentPort into LC41 named CP412
 * - Create SystemRealization into Logical System to System
 * - Rename LogicalActorPkg from Logical Architecture to Actors
 * - Create LogicalActor into Actors named LA1
 * - Create ComponentPort into LA1 named ACP11
 * - Create LogicalActor into Actors named LA2
 * - Create ComponentPort into LA2 named ACP21
 * - Create LogicalActor into Actors named LA3
 * - Create ComponentPort into LA3 named ACP31
 * - Create ComponentPort into LA3 named ACP32
 * - Rename LogicalContext from Logical Architecture to Logical Context
 * - Create Connection into Logical Context named C1 from ACP11(LA1) to CP41(LC4)
 * - Create Connection into Logical Context named C2 from ACP21(LA2) to CP411(LC41)
 * - Create Connection into Logical Context named C3 from CP43(LC4) to ACP31(LA3)
 * - Create Connection into Logical Context named C4 from CP412(LC41) to ACP32(LA3)
 * - Create Part into Logical Context named part_LS typed by Logical System
 * - Create Part into Logical Context named part_LA1 typed by LA1
 * - Create Part into Logical Context named part_LA2 typed by LA2
 * - Create Part into Logical Context named part_LA3 typed by LA3
 * 
 * Perform transition of A3
 * 
 * Expected Result:\
 * Perform transition of LS
 * - All sub component should be transitioned, 
 * - All LSC* shouldbe transitioned
 * - Only C3-C4 should be trnasitoned
 * 
 * </pre>
 */
public class Context_LCPC01_01 extends TopDownTransitionTestCase {

  private String id_c1 = "42bd911f-e7a1-42ab-8076-6b33eb89db8b";
  private String id_c2 = "03b03303-15ae-4014-8769-faa8e1fd0b82";
  private String id_c3 = "dbd1c901-1d0a-4518-be94-23ed1951dc32";
  private String id_c4 = "dcda4d29-99f2-438a-99f7-05f9e1788f2d";
  private String id_logical_system = "6ea9874d-9141-41f9-88fe-375d2e92a663";
  private String id_lsc1 = "dd0e5cce-8006-4248-b3c6-615c605e98e6";
  private String id_lsc2 = "d4beb47a-3f78-472c-a558-7fee776fa9e5";
  private String id_lsc3 = "3de0ff76-c36b-44f9-b25a-96b48600e468";
  private String id_lc1 = "c4b8ee31-2530-4583-8d3d-0f1398a48e89";
  private String id_lc11 = "d0e57033-ac1c-4ad3-a92f-d88fb30e8d46";
  private String id_lc2 = "9624e8fe-d94d-433e-bc12-a89c5ea54c64";
  private String id_lc3 = "0db20e72-943f-4283-888c-6b5422d9ea26";
  private String id_lc31 = "9935a6fd-5569-4bcb-b4e3-f065ad8b0eca";
  private String id_lc4 = "77c5e381-3fda-46f8-9fee-28980ff63fd9";
  private String id_lc41 = "d651c8ce-59a7-4297-a835-e1a5270d35e7";
  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__LCPC,
        ITopDownConstants.OPTIONS_TRANSITION__LCPC_BREAKDOWN);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("LCPCTransition_01");
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
  }

  private void step1() {
    performLCtoPCTransition(Arrays.asList(getObject(id_logical_system)));
    mustBeTransitioned(id_lc1);
    mustBeTransitioned(id_lc11);
    mustBeTransitioned(id_lc2);
    mustBeTransitioned(id_lc3);
    mustBeTransitioned(id_lc4);
    mustBeTransitioned(id_lc31);
    mustBeTransitioned(id_lc41);

    mustBeTransitioned(id_lsc3);
    mustBeTransitioned(id_lsc2);
    mustBeTransitioned(id_lsc1);

    shouldNotBeTransitioned(id_c1);
    shouldNotBeTransitioned(id_c2);

    mustBeTransitioned(id_c3);
    mustBeTransitioned(id_c4);
  }
}