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
 * - Create ExchangeItem into Interfaces named ExchangeItem uu
 * - Rename LogicalComponent from Logical Architecture to Logical System
 * - Create Connection into Logical System named C 1 from ComponentPort 1(LC 1) to ComponentPort 1(LC 2)
 * - Create ConnectionFunctionalExchangeAllocation into C 1 to Exchange 1
 * - Create StateMachine into Logical System named Logical System State Machine
 * - Create Region into Logical System State Machine named Default Region
 * - Create Part into Logical System named LC 1 typed by LC 1
 * - Create Part into Logical System named LC 2 typed by LC 2
 * - Create LogicalComponent into Logical System named LC 1
 * - Create ComponentFunctionalAllocation into LC 1 to LogicalFunction 1
 * - Rename LogicalFunctionPkg from Logical Architecture to Logical Functions
 * - Create LogicalFunction into Logical Functions named Root Logical Function
 * - Create FunctionRealization into Root Logical Function to Root System Function
 * - Create LogicalFunction into Root Logical Function named LogicalFunction 1
 * - Create FunctionalExchange into Root Logical Function named Exchange 1 from FunctionOutputPort 1(LogicalFunction 1) to FunctionInputPort 1(LogicalFunction 2)
 *    -> Linked to ExchangeItem(ExchangeItem uu)
 * 
 * 
 * Expected Result:\
 * - Performing LCPC transition on LogicalSystem shoudl trnasitioned C1, the allocated FE and not convey ExchangeItem
 * 
 * </pre>
 */
public class Context_EI01_07 extends TopDownTransitionTestCase {

  private String id_exchange_1 = "f1051de8-fa6b-4d21-8962-e48061117907";
  private String id_exchangeitem_uu = "f78d0f80-e2a0-43ae-96e4-4008ed26b318";
  private String id_logical_system = "1f29267d-44f9-4c92-bac6-3cd913521b48";
  private String id_c_1 = "53a53976-6689-4a35-9c34-f83a8714d4aa";
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
    performLCtoPCTransition(Arrays.asList(getObject(id_logical_system)));
    mustBeTransitioned(id_c_1);
    mustBeTransitioned(id_exchange_1);
    shouldNotBeTransitioned(id_exchangeitem_uu);
  }

}
