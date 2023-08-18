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
 * - Perform functional transition on SF11
 * - Delete SF121t, SF1221t
 * 
 * - Perform functional transition on SF11
 * 
 * Expected Result:\
 * - SF121, SF1221 should not be transitioned
 * </pre>
 * 
 */
public class Context_SF01_02 extends TopDownTransitionTestCase {

  private String id_sf11 = "6d77ea4d-6fb2-471f-a6fb-5710c1c597d3";
  private String id_sf121 = "4054b1d3-80d7-47ac-bffd-ca5081621ebe";
  private String id_sf1221 = "d5e2357b-cf1e-4fc6-9498-86584dce5178";
  private String id_e1 = "0111b3a0-3bc9-407e-b232-0fdc7bb35d0a";

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
    shouldNotBeTransitioned(id_sf121);
    shouldNotBeTransitioned(id_sf1221);
    mustBeTransitioned(id_e1);
  }
}