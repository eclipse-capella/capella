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
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * 
 * - Rename DataPkg from Logical Architecture to Data
 * - Create Class into Data named Class 1
 * - Create Property into Class 1 named Property 1
 * - Rename LiteralNumericValue from Property 1 to minCard
 * - Rename LiteralNumericValue from Property 1 to maxCard
 * - Create Property into Class 1 named Property 2
 * - Rename LiteralNumericValue from Property 2 to minCard
 * - Rename LiteralNumericValue from Property 2 to maxCard
 * - Create BooleanType into Data named BooleanType 1
 * - Create BooleanType into Data named BooleanType 2_Final
 * 
 * Expected Result:\
 * - Performing Data transition on Data should perform transition of only Boolean1
 * 
 * </pre>
 */
public class Context_DT01_04 extends TopDownTransitionTestCase {

  private String id_data = "0e25b332-ae04-4d61-869e-6244f5418ab4";
  private String id_class_1 = "c4734e7c-2241-48c4-bc7b-cc88a2e1008a";
  private String id_property_1 = "c4591aeb-7c99-4f6c-a881-295bd6c93da9";
  private String id_property_2 = "61b98f65-21b1-46bf-aaea-daafdef5cd32";
  private String id_booleantype_1 = "228786a7-1ea9-4b19-af33-3c48cbf37cc9";
  private String id_booleantype_2_final = "4c33e90e-4add-4a6a-9b5f-9150c1ef3771";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, true);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_I01");
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
  }

  private void step1() {
    performDataTransition(Arrays.asList(getObject(id_data)));
    mustBeTransitioned(id_class_1);
    mustBeTransitioned(id_booleantype_1);
    shouldNotBeTransitioned(id_booleantype_2_final);

    mustBeTransitioned(id_property_1);
    mustBeTransitioned(id_property_2);
  }

}
