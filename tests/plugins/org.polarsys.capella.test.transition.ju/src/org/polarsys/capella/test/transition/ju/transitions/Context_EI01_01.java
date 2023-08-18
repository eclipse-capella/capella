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
 * Perform transition on I1
 * 
 * Transition of exchange item is disabled
 * 
 * Expected Result:\
 * - I1 (and sub) should be the only element transitioned
 * 
 * </pre>
 */
public class Context_EI01_01 extends TopDownTransitionTestCase {

  private String id_ei2 = "1d21e9c7-398a-4924-a078-5e2edff3f226";
  private String id_i1 = "860c4a33-7e9e-46d4-addc-20756d56d420";
  private String id_ei3 = "342720d0-76a4-4354-9421-46ff279f4b47";
  private String id_ei4 = "f3d421dd-569d-4eac-9ca0-2c7858f6cea9";

  private String id_ei1 = "7e807c59-34fb-4894-b7ee-26f96d9bdf20";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, false);
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
    performInterfaceTransition(Arrays.asList(getObject(id_i1)));
    mustBeTransitioned(id_i1);
    shouldNotBeTransitioned(id_ei2);
    shouldNotBeTransitioned(id_ei3);
    shouldNotBeTransitioned(id_ei1);
    shouldNotBeTransitioned(id_ei4);
  }

}
