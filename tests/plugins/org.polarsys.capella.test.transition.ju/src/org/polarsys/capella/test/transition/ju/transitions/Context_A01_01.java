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

import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test contextuality of Actor transition
 * 
 * <pre>
 * Model is created with the following elements�
 * - Rename SystemContext from System Analysis to System Context
 * - Create Connection into System Context named C 1 from CP11(A1) to CP21(A2)
 * - Create Connection into System Context named C 2 from CP22(A2) to CP31(A3)
 * - Create Connection into System Context named C 3 from CP32(A3) to CP1(System)
 * - Create Connection into System Context named C 4 from CP51(A5) to CP41(A4)
 * - Create Part into System Context named System typed by System
 * - Create Part into System Context named A1 typed by A1
 * - Create Part into System Context named A2 typed by A2
 * - Create Part into System Context named A3 typed by A3
 * - Create Part into System Context named A4 typed by A4
 * - Create Part into System Context named A5 typed by A5
 * - Rename System from System Analysis to System
 * - Create StateMachine into System named SM1
 * - Create Region into SM1 named R11
 * - Create ComponentPort into System named CP1
 * - Rename ActorPkg from System Analysis to Actors
 * - Create Actor into Actors named A1
 * - Create ComponentPort into A1 named CP11
 * - Create Actor into Actors named A2
 * - Create ComponentPort into A2 named CP21
 * - Create ComponentPort into A2 named CP22
 * - Create Actor into Actors named A3
 * - Create ComponentPort into A3 named CP31
 * - Create ComponentPort into A3 named CP32
 * - Create Actor into Actors named A4
 * - Create ComponentPort into A4 named CP41
 * - Create Actor into Actors named A5
 * - Create ComponentPort into A5 named CP51

 * 
 * Expected Result:\
 * - Performing actor transition on A4 should transition A4 and ports only
 * - Performing actor transition on A5 should transition A5 and ports and C4
 * 
 * </pre>
 * 
 */
public class Context_A01_01 extends TopDownTransitionTestCase {

  private String id_c_3 = "8cb3738a-9a73-4c67-b111-6ee7eab451ac";
  private String id_c_4 = "743c1cd5-8b2f-4834-9ea0-90a7b73ec2aa";
  private String id_part_a5 = "3da9ab30-cab0-4cdc-879a-4bc49dd89e4a";
  private String id_a4 = "eb2319dd-9e61-4eee-a83f-d68bc818af43";
  private String id_cp41 = "84ff856c-6f99-423c-a34e-ae7068e5ae6d";
  private String id_a5 = "ecf54b75-b684-4c37-a06d-55301ac78a1b";
  private String id_cp51 = "4a1afcd7-a0a3-4b02-88ef-b952c6ad1389";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("ActorTransition_01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
  }

  private void step1() {
    performActorTransition(Arrays.asList(getObject(id_a4)));
    mustBeTransitioned(id_a4);
    mustBeTransitioned(id_cp41);
    shouldNotBeTransitioned(id_a5);
    shouldNotBeTransitioned(id_c_4);
  }

  private void step2() {
    performActorTransition(Arrays.asList(getObject(id_a5)));
    mustBeTransitioned(id_a5);
    mustBeTransitioned(id_cp51);
    mustBeTransitioned(id_part_a5);
    mustBeTransitioned(id_c_4);
    shouldNotBeTransitioned(id_c_3);
  }

}
