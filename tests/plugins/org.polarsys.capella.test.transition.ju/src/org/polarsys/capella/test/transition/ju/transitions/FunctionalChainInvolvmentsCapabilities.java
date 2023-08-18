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
 * Capability Transition was not propagating function/functionalChain involvements
 */
public class FunctionalChainInvolvmentsCapabilities extends TopDownTransitionTestCase {
  private String id_sf1 = "c797db70-4549-426b-9042-f7420ac78193";
  private String id_sf2 = "eef0ec0f-58f6-4f6e-8667-b433ed5ba097";
  private String id_sf3 = "9ec4d465-9a48-466b-a783-e8d4c96f377e";
  private String id_c1 = "2bbc37c2-bb3a-48b3-af41-c2eeea4f6bd9";
  private String id_fc11 = "31921741-8abf-4b1d-bdac-fb3740f05570";
  private String id_inv_to_fc11 = "34efcb66-8947-4b6e-9b7d-a1cd7a55ac41";
  private String id_inv_to_sf1 = "db7ab81b-ac5e-4380-b5dc-0b85ec5a9af5";
  private String id_inv_to_sf2 = "4c84623b-e4fa-465c-bf0a-f8c21c33a2b8";
  private String id_inv_to_sf3 = "dfb28059-7a79-4559-9018-a2540c0034c8";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_I01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
    step4();
  }

  /**
   * Capability transition without any function in realizing architecture should not propagate function
   */
  private void step1() {
    performCapabilityTransition(Arrays.asList(getObject(id_c1)));
    mustBeTransitioned(id_c1);

    shouldNotBeTransitioned(id_fc11);
    shouldNotBeTransitioned(id_inv_to_fc11);

    shouldNotBeTransitioned(id_sf1);
    shouldNotBeTransitioned(id_inv_to_sf1);

    shouldNotBeTransitioned(id_sf2);
    shouldNotBeTransitioned(id_inv_to_sf2);

    shouldNotBeTransitioned(id_sf3);
    shouldNotBeTransitioned(id_inv_to_sf3);
  }

  /**
   * Functional Transition should not propagate involvements, even if capa is in realizing architecture
   */
  private void step2() {
    performFunctionalTransition(Arrays.asList(getObject(id_sf1)));
    mustBeTransitioned(id_sf1);
    shouldNotBeTransitioned(id_inv_to_sf1);
  }

  /**
   * Capability transition should propagate involvement of realized functions
   */
  private void step3() {
    performCapabilityTransition(Arrays.asList(getObject(id_c1)));
    mustBeTransitioned(id_c1);

    mustBeTransitioned(id_sf1);
    mustBeTransitioned(id_inv_to_sf1);

    shouldNotBeTransitioned(id_sf3);
    shouldNotBeTransitioned(id_inv_to_sf3);

    shouldNotBeTransitioned(id_fc11);
    shouldNotBeTransitioned(id_inv_to_fc11);
  }

  /**
   * Functional Transition on capability should propagate all involvements and functions - functional chains
   */
  private void step4() {
    performFunctionalTransition(Arrays.asList(getObject(id_c1)));
    mustBeTransitioned(id_c1);
    mustBeTransitioned(id_fc11);
    mustBeTransitioned(id_inv_to_fc11);
    mustBeTransitioned(id_inv_to_sf1);
    mustBeTransitioned(id_inv_to_sf2);
    mustBeTransitioned(id_inv_to_sf3);
    mustBeTransitioned(id_sf1);
    mustBeTransitioned(id_sf2);
    mustBeTransitioned(id_sf3);
  }

}
