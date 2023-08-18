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
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create SF1, SF11, SF111, SF112, SF1121, SF1122, SF12, SF121, SF122, SF1221, SF1222
 * - Create E1 (SF1222, SF1122)
 * - Create E2 (SF112, SF122)
 * 
 * - Perform functional transition on SF11
 * 
 * Expected Result:\
 * - All items should be transitioned since its the first
 * </pre>
 * 
 */
public class Context_SF01_01 extends TopDownTransitionTestCase {

  private String id_rootsf = "05c7de7b-6e02-4471-b4fc-c84b8dae5472";
  private String id_sf1 = "66202540-92e4-4730-b475-cb93422e19ca";
  private String id_sf11 = "d11f3980-51e8-4f49-8105-61e93af06051";
  private String id_sf111 = "f83da4b8-2713-4180-812a-a19ee45c06b0";
  private String id_sf112 = "c86aa05f-8027-4511-b6dd-8f1895bec852";
  private String id_sf112op = "0585c4de-26c6-4046-bebb-7a846896393a";
  private String id_sf1121 = "513c4200-cc3f-4113-a010-9c84946de625";
  private String id_sf1122 = "38c48a02-1832-495c-84e0-ac058c2f6669";
  private String id_sf1122ip = "2a9b1280-450b-4bd7-a214-a79beb912086";
  private String id_sf12 = "e1a7285f-0d8d-44f7-8fef-8f60fc93a82d";
  private String id_sf121 = "6137da07-5d62-4fed-b428-92e9bbf0c96f";
  private String id_sf122 = "48aa160a-9860-4ea2-b3dc-187d98469e73";
  private String id_sf122ip = "52541227-6e34-48fe-a755-84cbb74b5dd5";
  private String id_sf1221 = "30c94372-412c-4f13-9315-dece7c563317";
  private String id_sf1222 = "4e2b7f2b-ad27-45d1-81df-48a072cc85c0";
  private String id_sf1222op = "54c92c66-23e0-4456-bb50-98d7091d327f";
  private String id_e1 = "d15537c4-ab00-48e4-b2be-befdeceb1cf3";
  private String id_e2 = "508d6ec6-f99f-4dd8-a9e3-87797bea5121";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_sf11)));
    mustBeTransitioned(id_rootsf);
    mustBeTransitioned(id_sf1);
    mustBeTransitioned(id_sf11);
    mustBeTransitioned(id_sf111);
    mustBeTransitioned(id_sf112);

    mustBeTransitioned(id_sf112op);
    mustBeTransitioned(id_sf1121);
    mustBeTransitioned(id_sf1122);
    mustBeTransitioned(id_sf1122ip);
    mustBeTransitioned(id_sf12);

    //// HOP shouldNotBeTransitioned for better contextuality
    shouldNotBeTransitioned(id_sf121);
    shouldNotBeTransitioned(id_sf1221);

    mustBeTransitioned(id_sf122);
    mustBeTransitioned(id_sf122ip);

    mustBeTransitioned(id_sf1222);
    mustBeTransitioned(id_sf1222op);
    mustBeTransitioned(id_e1);
    mustBeTransitioned(id_e2);
  }
}