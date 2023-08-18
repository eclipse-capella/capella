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
 * Transition of exchange item is enabled
 * 
 * Expected Result:\
 * - I1 (and sub) and exchange items should be transitioned
 * 
 * </pre>
 */
public class Context_EI01_02 extends TopDownTransitionTestCase {

  private String id_ei2 = "be99d0e4-3a44-4571-94d1-37489ee77913";
  private String id_i1 = "42367f79-bf2f-401d-b7c0-fe39dab0be01";
  private String id_ei3 = "e25388f9-2d18-464a-8dcc-c3f67f29c8c6";
  private String id_ei4 = "9f0f1393-342b-4dcc-bf15-3e34693d6fe3";
  private String id_ei1 = "87a872e2-eeb5-45c1-ae66-90341576ab60";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, true);
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
    mustBeTransitioned(id_ei2);
    mustBeTransitioned(id_i1);
    mustBeTransitioned(id_ei3);
    mustBeTransitioned(id_ei4);
    mustBeTransitioned(id_ei1);
  }

}
