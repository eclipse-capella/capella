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
 * 
 * - Rename InterfacePkg from System Analysis to Interfaces
 * - Create ExchangeItem into Interfaces named EI2
 * - Create Interface into Interfaces named I1
 * - Create ExchangeItemAllocation into I1 to EI1 named ExchangeItemAllocation
 * - Create ExchangeItemAllocation into I1 to EI2 named ExchangeItemAllocation
 * - Create ExchangeItemAllocation into I1 to EI3 named ExchangeItemAllocation
 * - Create ExchangeItemAllocation into I1 to EI4 named ExchangeItemAllocation
 * - Create InterfacePkg into Interfaces named IP1
 * - Create ExchangeItem into IP1 named EI3
 * - Rename DataPkg from System Analysis to Data
 * - Create ExchangeItem into Data named EI4
 * - Create DataPkg into Data named DP1
 * - Create ExchangeItem into DP1 named EI1
 * 
 * Perform transition on EI2
 * 
 * Expected Result:\
 * - IP33 should be the only element transitioned
 * 
 * </pre>
 * 
 */
public class Context_EI01_03 extends TopDownTransitionTestCase {

  private String id_ei2 = "996a2e4b-8180-4c87-8502-19d83ec1b015";
  private String id_i1 = "9e9abdcc-4e1f-407d-b59e-c3a3dad292a7";
  private String id_ip1 = "012d91b2-c6be-45e3-9f41-09cd9180cbc3";
  private void initSession() {

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
    performExchangeItemTransition(Arrays.asList(getObject(id_ei2)));
    mustBeTransitioned(id_ei2);
    shouldNotBeTransitioned(id_i1);
    shouldNotBeTransitioned(id_ip1);
  }

}
