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
 * - Rename InterfacePkg from Logical Architecture to Interfaces
 * - Create ExchangeItem into Interfaces named EI2
 * - Create ExchangeItem into Interfaces named EI2-Final
 * - Create Interface into Interfaces named Interface 1
 * - Create ExchangeItemAllocation into Interface 1 to EI4-Final named ExchangeItemAllocation
 * - Create ExchangeItemAllocation into Interface 1 to EI2-Final named ExchangeItemAllocation
 * - Create ExchangeItemAllocation into Interface 1 to EI2 named ExchangeItemAllocation
 * - Create ExchangeItemAllocation into Interface 1 to EI4 named ExchangeItemAllocation
 * - Rename DataPkg from Logical Architecture to Data
 * - Create ExchangeItem into Data named EI4
 * - Create ExchangeItem into Data named EI4-Final
 * - Rename LogicalActorPkg from Logical Architecture to Actors
 * - Create LogicalActor into Actors named LogicalActor 1
 * 
 * Expected Result:\
 * - Performing Interface transition of I1 should transition only ExchangeItem non final
 * 
 * </pre>
 */
public class Context_EI01_08 extends TopDownTransitionTestCase {

  private String id_ei2 = "de66ad33-37df-437f-a6ef-1a795d347b66";
  private String id_ei2_final = "709ede9e-55f4-4896-993b-6ecd427ecf04";
  private String id_interface_1 = "cabcc5f5-2281-4b34-83ae-ee9161ede590";
  private String id_ei4 = "f2d8c04a-0441-41ee-a799-34c0186ecd19";
  private String id_ei4_final = "28ac992f-7063-4c7a-b59f-1bea0788dfed";
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
    performInterfaceTransition(Arrays.asList(getObject(id_interface_1)));
    mustBeTransitioned(id_ei2);
    shouldNotBeTransitioned(id_ei2_final);

    mustBeTransitioned(id_interface_1);

    mustBeTransitioned(id_ei4);
    shouldNotBeTransitioned(id_ei4_final);
  }

}
