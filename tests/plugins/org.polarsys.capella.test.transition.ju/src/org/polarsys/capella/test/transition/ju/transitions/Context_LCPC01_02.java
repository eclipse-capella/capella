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

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
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
public class Context_LCPC01_02 extends TopDownTransitionTestCase {

  private String id_logical_system = "312d0638-7d63-41a9-87a5-1779c07e0d73";
  private String id_lsc1 = "192a0525-898b-4e0f-af30-a238551894f3";
  private String id_lsc2 = "5e33cb26-925f-4e8f-8cbc-2dba8ba7d8c3";
  private String id_lsc3 = "56f49268-6655-41c6-846f-671c4107f175";
  private String id_lc1 = "3384c2d2-65f9-43ca-8498-d4de225a11f2";
  private String id_lc11 = "49863b3b-1c9d-468d-9f43-94dbb9b544aa";
  private String id_lc2 = "d3376d16-efd2-46d0-8662-e212f0760649";
  private String id_lc3 = "b0ecdfb0-4dce-4978-81d6-5e30dad4805e";
  private String id_lc31 = "b22507c9-ffb6-4bc8-b11f-4adec3e86b09";
  private String id_lc4 = "8bf15561-61b5-4e6c-b3a9-4762170c031e";
  private String id_lc41 = "dc560dae-0676-4c46-8cda-0440882e9a19";
  private String id_c1 = "81bd16fb-4aa1-48dc-b41e-d368531133c7";
  private String id_c2 = "7ba0b15e-618a-4ad1-b042-1a4203e0ca40";
  private String id_c3 = "908d4905-6a65-432e-9de8-56d3be9c292b";
  private String id_c4 = "ec11cb39-efc9-4556-853f-5539b18eb4aa";
  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__LCPC, ITopDownConstants.OPTIONS_TRANSITION__LCPC_LEAF);
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

    shouldNotBeTransitioned(id_c1);
    shouldNotBeTransitioned(id_c2);
    shouldNotBeTransitioned(id_c3);
    mustBeTransitioned(id_c4);

    mustBeTransitioned(id_lsc1);
    shouldNotBeTransitioned(id_lsc2);
    mustBeTransitioned(id_lsc3);

    mustBeTransitionedTo(id_lc1, PaPackage.Literals.PHYSICAL_COMPONENT_PKG);
    mustBeTransitionedTo(id_lc3, PaPackage.Literals.PHYSICAL_COMPONENT_PKG);
    mustBeTransitionedTo(id_lc4, PaPackage.Literals.PHYSICAL_COMPONENT_PKG);

    mustBeTransitionedTo(id_lc41, CsPackage.Literals.COMPONENT);
    mustBeTransitionedTo(id_lc31, CsPackage.Literals.COMPONENT);
    mustBeTransitionedTo(id_lc11, CsPackage.Literals.COMPONENT);
    mustBeTransitionedTo(id_lc2, CsPackage.Literals.COMPONENT);
  }
}