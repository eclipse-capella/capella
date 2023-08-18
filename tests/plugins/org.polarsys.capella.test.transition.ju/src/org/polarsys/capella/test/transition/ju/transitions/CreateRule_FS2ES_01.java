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
public class CreateRule_FS2ES_01 extends TopDownTransitionTestCase {
  private String id_s1 = "346a24cf-3cf2-4807-8911-a360fbac8f6a";
  private String id_ir11 = "36b6dfe0-fc58-46cf-958c-828cd91e779b";
  private String id_ir12 = "7165c63f-28b4-463b-bdb0-9b19c5ff5081";
  private String id_ir13 = "7d3d547d-c2f9-4f1e-b3e1-966a012141fd";
  private String id_sm11 = "61d0073e-c157-4e32-8b23-5de1e9f2f59f";
  private String id_sm12 = "4be503c0-2ca7-4492-9069-02a205c64859";
  private String id_sm13 = "8ac0a9bf-f2d8-444f-9425-55555be3cbd9";
  private String id_sm14 = "c5d06e20-7379-49fb-881f-f0758e8732a9";
  private String id_sm15 = "7cbd9382-249b-498b-9f3e-3fed7348598d";
  private String id_s2 = "0452d389-aec1-42d9-820d-e974c10d5f59";
  private String id_ir21 = "b8fb8135-2561-46d6-a23d-b825f652c1f3";
  private String id_ir22 = "924d318e-6b70-4e71-92c8-1399be169eaf";
  private String id_sm21 = "adca4928-f02b-4e4a-9e45-5063fe3e4f3b";
  private String id_sm22 = "1edbb319-e452-4503-810e-ac9c5be854db";
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
  }

  private void step1() {
    performFStoESTransition(Arrays.asList(getObject(id_s1)));
    mustBeTransitioned(id_s1);
    mustBeTransitioned(id_ir11);
    mustBeTransitioned(id_ir12);
    mustBeTransitioned(id_ir13);

    mustBeTransitioned(id_sm11);
    mustBeTransitioned(id_sm12);
    mustBeTransitioned(id_sm13);
    mustBeTransitioned(id_sm14);
    mustBeTransitioned(id_sm15);
  }

  private void step2() {
    performFStoESTransition(Arrays.asList(getObject(id_s2)));
    mustBeTransitioned(id_s2);
    mustBeTransitioned(id_ir21);
    mustBeTransitioned(id_ir22);

    mustBeTransitioned(id_sm21);
    mustBeTransitioned(id_sm22);
  }

}
