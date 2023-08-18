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
 * Expected Result:\
 * - Performing LCPC transition on LogicalSystem shoudl trnasitioned C1, the allocated FE and convey ExchangeItem
 * 
 * </pre>
 */
public class Context_EI01_06 extends TopDownTransitionTestCase {

  private String id_exchange_1 = "19d4c2a5-fde8-40c2-9c83-de7162bc839d";
  private String id_exchangeitem_uu = "4ed2e508-efe2-4102-99ab-953cb3eb2aaa";
  private String id_logical_system = "dbd014e2-3f07-4f8a-ac30-7726124ff1ca";
  private String id_c_1 = "b4b6afc7-6156-48e5-870b-22abdb03760f";
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
    performLCtoPCTransition(Arrays.asList(getObject(id_logical_system)));
    mustBeTransitioned(id_c_1);
    mustBeTransitioned(id_exchange_1);
    mustBeTransitioned(id_exchangeitem_uu);
  }

}
