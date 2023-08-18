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
 * Perform transition on IP1
 * 
 * Expected Result:\
 * - IP1 should be the only element transitioned and subs
 * 
 * </pre>
 * 
 */
public class Context_I01_01 extends TopDownTransitionTestCase {

  private String id_ei2 = "e8962332-b75c-4b7b-9c13-91f0c949f283"; //$NON-NLS-1$
  private String id_i1 = "76d5a3da-5881-4f11-afea-e56d5f368376"; //$NON-NLS-1$
  private String id_ip1 = "8f6781a0-c3fc-4d87-a23a-7c7b5026b5dc"; //$NON-NLS-1$
  private String id_ei3 = "ecacdea6-4c1d-4be7-93fa-6286663ac2dd"; //$NON-NLS-1$
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_I01");
  }

  @Override
  public void performTest() throws Exception {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, true);
    step1();
  }

  private void step1() {
    performInterfaceTransition(getObjects(id_ip1));
    mustBeTransitioned(id_ip1);
    mustBeTransitioned(id_ei3);

    shouldNotBeTransitioned(id_i1);
    shouldNotBeTransitioned(id_ei2);

  }
}