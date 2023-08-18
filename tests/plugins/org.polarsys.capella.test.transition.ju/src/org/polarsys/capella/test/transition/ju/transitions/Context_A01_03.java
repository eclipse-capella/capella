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
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 *  * - Rename SystemContext from System Analysis to System Context
 * - Create Connection into System Context named C1 from CP3(System) to CP52(A5)
 * - Create Connection into System Context named C2 from CP53(A5) to CP21(A2)
 * - Create Connection into System Context named C3 from CP4(System) to CP11(A1)
 * - Create Connection into System Context named C4 from CP5(System) to CP33(A3)
 * - Create Part into System Context named part_P1 typed by A1
 * - Create Part into System Context named part_P2 typed by A2
 * - Create Part into System Context named part_P3 typed by A3
 * - Create Part into System Context named part_P4 typed by A4
 * - Create Part into System Context named part_P5 typed by A5
 * - Create Part into System Context named part_System typed by System
 * - Rename System from System Analysis to System
 * - Create StateMachine into System named SM1
 * - Create Region into SM1 named R11
 * - Create ComponentPort into System named CP1
 * - Create ComponentPort into System named CP2
 * - Create ComponentPort into System named CP3
 * - Create ComponentPort into System named CP4
 * - Create ComponentPort into System named CP5
 * - Rename ActorPkg from System Analysis to Actors
 * - Create Actor into Actors named A1
 * - Create ComponentPort into A1 named CP11
 * - Create Actor into Actors named A2
 * - Create ComponentPort into A2 named CP21
 * - Create ComponentPort into A2 named ComponentPort 1
 * - Create Actor into Actors named A3
 * - Create ComponentPort into A3 named CP31
 * - Create ComponentPort into A3 named CP32
 * - Create ComponentPort into A3 named CP33
 * - Create Actor into Actors named A4
 * - Create Actor into Actors named A5
 * - Create ComponentPort into A5 named CP51
 * - Create ComponentPort into A5 named CP52
 * - Create ComponentPort into A5 named CP53

 * A3 and A5(without port) are transitioned, not A1
 * 
 * Expected Result:\
 * - Performing actor transition on System should transition C1, C4 but not C3
 * A1 A2 A4, C2 should not be transitioend
 * CP51, CP53 should not , CP52 should
 * 
 * </pre>
 * 
 */
public class Context_A01_03 extends TopDownTransitionTestCase {

  private String id_c1 = "7af84abf-9ba5-4ae6-82aa-a58b5f616016";
  private String id_c2 = "673b2dfa-f4e1-42dc-ab3a-e43ab7e2d438";
  private String id_c3 = "452e0559-c0cc-4250-b685-83edfe424e79";
  private String id_c4 = "e2227d0b-ee7a-4f3a-ad97-3621422d5259";
  private String id_part_p1 = "6f49844c-f209-46dd-9cfb-e04615416dbc";
  private String id_part_p3 = "2115e697-d142-4094-a406-34b9036c0695";
  private String id_part_p5 = "efd8075c-36b8-4c3a-b248-6acecea98622";
  private String id_system = "ccc84099-16b7-44bc-ad11-5e9655ac4df3";
  private String id_cp1 = "d6fddf33-a7a4-4608-9ffa-8649b61c2953";
  private String id_cp2 = "d9265047-bd06-40f4-b1ef-6730319f5712";
  private String id_cp3 = "33b8ee79-31cd-401f-9792-a6b634b2d9aa";
  private String id_cp4 = "7b1fa2db-a8af-4ee6-bbcc-49d67306239b";
  private String id_cp5 = "4e8ce424-6fc6-4948-adce-818119a89a1d";
  private String id_a1 = "ded56b24-5513-47ff-a5b0-e58d10aa44e4";
  private String id_a3 = "27c79f49-b8f8-4f56-a78a-56aa8fba551a";
  private String id_a5 = "861bf2aa-8017-4309-b654-35c938d8ada6";
  private String id_cp51 = "b7d3d3a6-38b5-43de-82f2-5e7e3b5e3595";
  private String id_cp52 = "11582cd1-04a0-4a16-93f0-8b174890b4b0";
  private String id_cp53 = "c7cc9244-a7ca-47f4-8377-75550b11cbf5";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("ActorTransition_01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performActorTransition(Arrays.asList(getObject(id_system)));
    mustBeTransitioned(id_c1);
    shouldNotBeTransitioned(id_c3);
    shouldNotBeTransitioned(id_c2);
    mustBeTransitioned(id_c4);
    mustBeTransitioned(id_cp1);
    mustBeTransitioned(id_cp2);
    mustBeTransitioned(id_cp3);
    mustBeTransitioned(id_cp4);
    mustBeTransitioned(id_cp5);
    mustBeTransitioned(id_a5);
    shouldNotBeTransitioned(id_cp51);
    shouldNotBeTransitioned(id_cp53);

    mustBeTransitioned(id_cp52);
    shouldNotBeTransitioned(id_a1);
    mustBeTransitioned(id_a3);

    mustBeTransitioned(id_part_p5);
    mustBeTransitioned(id_part_p3);
    shouldNotBeTransitioned(id_part_p1);
  }
}
