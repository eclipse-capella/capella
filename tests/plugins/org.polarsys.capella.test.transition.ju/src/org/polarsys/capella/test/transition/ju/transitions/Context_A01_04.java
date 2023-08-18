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
 * - Performing actor transition on SystemAnalisis should transition all elements port/connection/parts/actor
 * 
 * </pre>
 * 
 */
public class Context_A01_04 extends TopDownTransitionTestCase {

  private String id_system_analysis = "a9e45bfb-05bd-4234-97b1-34a6be9bcce1";
  private String id_c1 = "d8acefcf-c2e0-44f7-b820-ccbe85e11037";
  private String id_c2 = "d50dabdc-9406-427c-9286-8db0caa7d4c7";
  private String id_c3 = "167302d5-f0de-4095-ae67-7195e5495b90";
  private String id_c4 = "d8eda6c8-43f5-4927-81f6-91d14667b5da";
  private String id_part_p2 = "32eceb42-f820-44e1-ab15-501651b6361f";
  private String id_part_p3 = "3fa07a39-3326-4f9a-aa68-160a0ac34314";
  private String id_part_p4 = "89a73f5a-164c-483e-89fc-501787722c0b";
  private String id_part_p5 = "555c1306-2867-490e-8ce1-d8ad1de6244f";
  private String id_part_p6 = "179c0498-7cf8-4536-8f65-78535f6329c6";
  private String id_system = "bf4633cc-2a51-427b-ad4b-8b1415c065f1";
  private String id_cp1 = "be587d1e-5f9b-48a8-9914-86441b56a56f";
  private String id_a1 = "3fda8248-2c69-4aaf-a4ff-d4dd78235911";
  private String id_cp11 = "b4eae5cd-ea7e-462e-9442-adab93382ef2";
  private String id_a2 = "025d4cd4-86c3-444d-8c93-04b8e156d8e5";
  private String id_cp21 = "85eea44e-d391-43f8-8b09-d3b9940d0658";
  private String id_cp22 = "aeec0c19-1d64-462e-aaa1-11a98a00ea48";
  private String id_a3 = "b490932c-0745-41e3-87c5-f849c82a1966";
  private String id_cp31 = "8c6636e7-2dd7-42b6-bb98-22c010cfd2bf";
  private String id_cp32 = "0e231094-4aa5-45c3-b8f4-df8ff2e25194";
  private String id_a4 = "28892532-9c72-4d3c-bfd1-8e139e8c9071";
  private String id_cp41 = "62295e91-161f-4233-98d3-1200ad5412df";
  private String id_a5 = "9b21f2dd-7277-498c-9074-15da3e894341";
  private String id_cp51 = "70b135f8-8a9e-4554-abfe-92aa296151c7";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("ActorTransition_01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performActorTransition(Arrays.asList(getObject(id_system_analysis)));
    mustBeTransitioned(id_c1);
    mustBeTransitioned(id_c3);
    mustBeTransitioned(id_c2);
    mustBeTransitioned(id_c4);

    // mustBeTransitioned("PART_P1", _id_part_p1); (i guess its ok)
    mustBeTransitioned(id_part_p2);
    mustBeTransitioned(id_part_p3);
    mustBeTransitioned(id_part_p4);
    mustBeTransitioned(id_part_p5);
    mustBeTransitioned(id_part_p6);

    mustBeTransitioned(id_system);

    mustBeTransitioned(id_cp1);
    mustBeTransitioned(id_a1);
    mustBeTransitioned(id_a2);
    mustBeTransitioned(id_a3);
    mustBeTransitioned(id_a5);
    mustBeTransitioned(id_a4);

    mustBeTransitioned(id_cp11);
    mustBeTransitioned(id_cp21);
    mustBeTransitioned(id_cp22);
    mustBeTransitioned(id_cp31);
    mustBeTransitioned(id_cp32);
    mustBeTransitioned(id_cp41);
    mustBeTransitioned(id_cp51);
  }

}
