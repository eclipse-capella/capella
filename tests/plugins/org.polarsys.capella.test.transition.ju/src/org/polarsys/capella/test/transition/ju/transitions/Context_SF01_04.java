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
 * - Perform functional transition on SF11
 * - Delete E2t
 * - Move SF1121t SF1122t in LF1
 * - Delete SF112 and owned port
 * 
 * - Perform functional transition on E2
 *
 * Expected Result:\
 * - SF112 should not be created since leaf are transitioned
 * - E2t should not be created since SF112t is not created
 * 
 * </pre>
 * 
 */
public class Context_SF01_04 extends TopDownTransitionTestCase {

  private String id_sf112 = "73865bdb-a58b-4a22-a988-6108b6767dbc";
  private String id_sf112op = "a782e71b-d835-4860-be1a-e1f883dcf0d6";
  private String id_e2 = "9f0ca23e-8160-44fb-9018-d896dbb391ce";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_e2)));
    shouldNotBeTransitioned(id_sf112);
    shouldNotBeTransitioned(id_sf112op);
    shouldNotBeTransitioned(id_e2);
  }
}