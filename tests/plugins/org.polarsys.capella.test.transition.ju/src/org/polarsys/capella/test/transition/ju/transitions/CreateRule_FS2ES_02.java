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
 * Test Scenario transition FS2CES
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create Capability into Capabilities named Capability 1
 * - Create Scenario into Capability 1 named S1
 *    -> Create InstanceRole IR11 covering SF11
 *    -> Create InstanceRole IR12 covering SF12
 *    -> Create InstanceRole IR13 covering SF13
 *    -> Create InstanceRole IR14 covering SF14
 *    -> Start ME11 for message SM13 ASYNCHRONOUS_CALL from (IR14)
 *    -> Message SM13 linked to (FE13)
 *    -> End ME12 for message SM13 ASYNCHRONOUS_CALL from (IR11)
 *    -> Start execution E13 from (ME12)
 *    -> Start ME13 for message SM11 SYNCHRONOUS_CALL from (IR11)
 *    -> Message SM11 linked to (FE11)
 *    -> End ME14 for message SM11 SYNCHRONOUS_CALL from (IR12)
 *    -> Start execution E11 from (ME14)
 *    -> Start ME15 for message SM12 SYNCHRONOUS_CALL from (IR12)
 *    -> Message SM12 linked to (FE12)
 *    -> End ME16 for message SM12 SYNCHRONOUS_CALL from (IR13)
 *    -> Start execution E12 from (ME16)
 *    -> Start ME17 for message SM14 REPLY from (IR13)
 *    -> Message SM14 linked to (FE12)
 *    -> End execution E12 for (ME17)
 *    -> End ME18 for message SM14 REPLY from (IR12)
 *    -> Start ME19 for message SM15 REPLY from (IR12)
 *    -> Message SM15 linked to (FE11)
 *    -> End execution E11 for (ME19)
 *    -> End ME110 for message SM15 REPLY from (IR11)
 *    -> End execution E13 for (SM13)
 * - Create Scenario into Capability 1 named S2
 *    -> Create InstanceRole IR21 covering SF11
 *    -> Create InstanceRole IR22 covering SF15
 *    -> Start ME21 for message SM21 SYNCHRONOUS_CALL from (IR21)
 *    -> Message SM21 linked to (FE14)
 *    -> End ME22 for message SM21 SYNCHRONOUS_CALL from (IR22)
 *    -> Start execution E21 from (ME22)
 *    -> Start ME23 for message SM22 REPLY from (IR22)
 *    -> Message SM22 linked to (FE14)
 *    -> End execution E21 for (ME23)
 *    -> End ME24 for message SM22 REPLY from (IR21)
 * - Rename SystemCapabilityInvolvement from Capability 1 to 



 * 
 * Expected Result:\
 * - Performing fs>ces transition on S1 should transition correctly the scenario
 * - Performing fs>ces transition on S2 should transition correctly the scenario, using the default resolver, so the first part of actor A4 should be selected
 * 
 * </pre>
 * 
 */
public class CreateRule_FS2ES_02 extends TopDownTransitionTestCase {
  private String id_s21 = "37d68b09-e144-4bfa-96e2-4c9c4c526c91";
  private String id_ir211 = "bf0b7e8f-d351-4a7b-9503-92a85a69c9d2";
  private String id_ir212 = "e30a7f5b-2d05-423b-be8f-134e76aeeffb";
  private String id_s22 = "cb112288-5211-4d9d-857d-0ddaeecccffe";
  private String id_ir221 = "604fba92-8a38-4689-b141-9de55247615c";
  private String id_ir222 = "e26ba902-640e-423d-be12-d7e5e01c1851";
  private String id_s23 = "3e3e5cd8-013b-4c11-b55c-3004d47551ff";
  private String id_ir231 = "0c1c242f-a6cd-4846-914a-38f92359e910";
  private String id_ir232 = "c0cccd2d-a55d-44ce-9b60-d04b0bb52c45";
  private String id_s24 = "f16588c5-8a24-443e-ad17-adaaf54f5688";
  private String id_ir241 = "69ff3c7c-3cf8-4fd3-badd-b1a7e663319f";
  private String id_ir242 = "9ad5135a-068e-45fb-abe9-47c5c4010257";
  private String id_s11 = "70b9cdf1-2702-40cc-9cfd-308066c00374";
  private String id_ir111 = "bc9a69ca-54c7-4b4c-9f0b-8c05b4ef4e72";
  private String id_ir112 = "329ae908-7ea4-4a8e-a5a8-d59880946fe5";
  private String id_s12 = "9df549be-e746-45ad-9b6a-b23121f51215";
  private String id_ir121 = "5f1d093a-88f0-4867-8a35-191ab3c7649e";
  private String id_ir122 = "a2936876-34d6-4a0b-ae7d-66cffa62f48d";
  private String id_s25 = "bc7043a4-c62d-4417-b55b-f210d99f41b6";
  private String id_ir251 = "f9258731-1d0c-41e9-8873-14b9bad994ba";
  private String id_ir252 = "40058463-7511-4742-a5cc-cae66895fe67";

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
    step7();
  }

  private void step1() {
    performFStoESTransition(Arrays.asList(getObject(id_s11)));
    mustBeTransitioned(id_s11);
    mustBeTransitioned(id_ir111);
    mustBeTransitioned(id_ir112);
  }

  private void step2() {
    performFStoESTransition(Arrays.asList(getObject(id_s12)));
    shouldNotBeTransitioned(id_s12);
    shouldNotBeTransitioned(id_ir121);
    shouldNotBeTransitioned(id_ir122);
  }

  private void step3() {
    performFStoESTransition(Arrays.asList(getObject(id_s21)));
    mustBeTransitioned(id_s21);
    mustBeTransitioned(id_ir211);
    mustBeTransitioned(id_ir212);
  }

  private void step4() {
    performFStoESTransition(Arrays.asList(getObject(id_s22)));
    shouldNotBeTransitioned(id_s22);
    shouldNotBeTransitioned(id_ir221);
    shouldNotBeTransitioned(id_ir222);
  }

  private void step5() {
    performFStoESTransition(Arrays.asList(getObject(id_s23)));
    mustBeTransitioned(id_s23);
    mustBeTransitioned(id_ir231);
    mustBeTransitioned(id_ir232);
  }

  private void step6() {
    performFStoESTransition(Arrays.asList(getObject(id_s24)));
    mustBeTransitioned(id_s24);
    mustBeTransitioned(id_ir241);
    mustBeTransitioned(id_ir242);
  }

  private void step7() {
    performFStoESTransition(Arrays.asList(getObject(id_s25)));
    shouldNotBeTransitioned(id_s25);
    shouldNotBeTransitioned(id_ir251);
    shouldNotBeTransitioned(id_ir252);
  }

}
