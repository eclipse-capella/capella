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

import org.eclipse.emf.ecore.EObject;
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
public class Context_LCPC01_03 extends TopDownTransitionTestCase {

  private String id_lc111__lc111 = "dc8c2747-d326-4e03-9907-b718a338e97c";
  private String id_lc111 = "1e08df96-18de-43e9-bded-fd07b152b36f";

  private String id_lc1__lc1 = "f8e2568a-cab3-4c20-b768-6a6d0d0e0185";
  private String id_lc1 = "c306f609-0ca0-477e-85e7-1555e26a3f94";
  private String id_lc11__lc11 = "3a821661-5fe2-472f-994b-83e484b0446c";
  private String id_lc11 = "9868f19b-e122-463f-8bc3-86fe5142daed";
  private String id_logical_system = "aa1404d1-73b0-46f2-b9e3-120a7ddbf60e";

  private String id_lc2__lc2 = "0b472bf6-c9b0-487e-8f16-33046bdeef53";
  private String id_lc2 = "b36fa3f9-9198-4e51-b599-f2d4e28306ff";
  private String id_lc211__lc211 = "1a8b06ee-d98a-4a8d-9a12-540c6f1983c6";
  private String id_lcp21 = "af8d5bed-0532-4914-8dfd-813d35b350a5";
  private String id_lc211 = "f93f683f-5c15-4be2-9150-e319102bed0f";
  private String id_lc2111__lc2111 = "1b67bc55-9c76-4e1e-8dcc-9263519ffa24";
  private String id_lc2111 = "0d3fa663-0d58-408f-9ed6-d4b28ab18cf8";
  private String id_lcp1 = "8e791ecd-82fe-42e9-87cd-ba5e2d112c7d";
  private String id_lcp11 = "cd62dbc0-0ce1-4bd6-a0fa-a73836b63441";
  private String id_lcp111 = "6e9e0e95-f73c-40a7-ae65-69e6b4fe6bbf";
  private String id_lc1111 = "6a3b8ad0-64d1-4fd5-9325-c4d55637a939";
  private String id_lc111111__lc111111 = "8bdd187b-2d7a-4af5-8339-45fb0ecc7501";
  private String id_lcp11111 = "64c5d662-8ff0-48f1-b0d3-c805bdec7131";
  private String id_lc111111 = "490b99d8-1a5e-4f27-838e-abd273327153";
  private String id_lc1111111__lc1111111 = "2da146d8-ba2a-413d-8113-1ab7c52b7943";
  private String id_lc1111111 = "b14d1663-92b8-49cc-b050-3733c6ef50c1";
  private String id_lc1111__lc1111 = "4f691d17-6971-4ead-86b4-d741f0004e49";

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
    step2();
    step3();
  }

  private void step1() {
    performLCtoPCTransition(Arrays.asList(getObject(id_lc111)));
    EObject ls = mustBeTransitioned(id_logical_system);
    mustBeTransitionedDirecltyContainedBy(id_lc111, ls);
    mustBeTransitionedDirecltyContainedBy(id_lc111__lc111, ls);

    shouldNotBeTransitioned(id_lc1);
    shouldNotBeTransitioned(id_lc1__lc1);
    shouldNotBeTransitioned(id_lc11__lc11);
    shouldNotBeTransitioned(id_lc11);
  }

  private void step2() {
    performLCtoPCTransition(Arrays.asList(getObject(id_lc2111)));
    EObject ls = mustBeTransitioned(id_logical_system);
    EObject lcp21 = mustBeTransitioned(id_lcp21);

    mustBeTransitionedDirecltyContainedBy(id_lc2111, lcp21);
    mustBeTransitionedDirecltyContainedBy(id_lc2111__lc2111, ls);

    shouldNotBeTransitioned(id_lc2__lc2);
    shouldNotBeTransitioned(id_lc2);
    shouldNotBeTransitioned(id_lc211__lc211);
    shouldNotBeTransitioned(id_lc211);
  }

  private void step3() {
    performLCtoPCTransition(Arrays.asList(getObject(id_lc1111111)));
    EObject ls = mustBeTransitioned(id_logical_system);
    EObject lcp1 = mustBeTransitionedDirecltyContainedBy(id_lcp1, ls);
    EObject lcp11 = mustBeTransitionedDirecltyContainedBy(id_lcp11, lcp1);
    EObject lcp111 = mustBeTransitionedDirecltyContainedBy(id_lcp111, lcp11);
    EObject lcp11111 = mustBeTransitionedDirecltyContainedBy(id_lcp11111, lcp111);

    mustBeTransitionedDirecltyContainedBy(id_lc1111111, lcp11111);
    mustBeTransitionedDirecltyContainedBy(id_lc1111111__lc1111111, ls);

    shouldNotBeTransitioned(id_lc1111__lc1111);
    shouldNotBeTransitioned(id_lc1111);
    shouldNotBeTransitioned(id_lc111111__lc111111);
    shouldNotBeTransitioned(id_lc111111);
  }
}