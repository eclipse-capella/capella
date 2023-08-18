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
  * - Rename OperationalActivityPkg from Operational Analysis to Operational Activities
 * - Create OperationalActivity into Operational Activities named OA1
 * - Create OperationalProcess into OA1 named OA1_OP11
 * - Create FunctionalChainInvolvement into OA1_OP11 to FE11
 * - Create FunctionalChainInvolvement into OA1_OP11 to OA12
 * - Create FunctionalChainInvolvement into OA1_OP11 to OA11
 * - Create FunctionalChainInvolvement into OA1_OP11 to FE12
 * - Create FunctionalChainInvolvement into OA1_OP11 to OA13
 * - Create OperationalActivity into OA1 named OA11
 * - Create OperationalActivity into OA1 named OA12
 * - Create OperationalActivity into OA1 named OA13
 * - Create FunctionalExchange into OA1 named FE11 from  to 
 * - Create FunctionalExchange into OA1 named FE12 from  to 
 * - Rename OperationalCapabilityPkg from Operational Analysis to Operational Capabilities
 * - Create OperationalCapability into Operational Capabilities named OC1
 * - Create OperationalProcess into OC1 named OC1_OP11
 * - Create FunctionalChainInvolvement into OC1_OP11 to FE11
 * - Create FunctionalChainInvolvement into OC1_OP11 to OA12
 * - Create FunctionalChainInvolvement into OC1_OP11 to OA11
 * - Create FunctionalChainInvolvement into OC1_OP11 to FE12
 * - Create FunctionalChainInvolvement into OC1_OP11 to OA13
 * - Rename SystemFunctionPkg from System Analysis to System Functions
 * - Create SystemFunction into System Functions named SF1
 * - Create FunctionalChain into SF1 named SF1_FC11
 * - Create FunctionalChainInvolvement into SF1_FC11 to FE11
 * - Create FunctionalChainInvolvement into SF1_FC11 to SF12
 * - Create FunctionalChainInvolvement into SF1_FC11 to SF11
 * - Create FunctionalChainInvolvement into SF1_FC11 to FE12
 * - Create FunctionalChainInvolvement into SF1_FC11 to SF13
 * - Create FunctionRealization into SF1 to OA1
 * - Create SystemFunction into SF1 named SF11
 * - Create FunctionOutputPort into SF11 named FOP111
 * - Create SystemFunction into SF1 named SF12
 * - Create FunctionInputPort into SF12 named FIP121
 * - Create FunctionOutputPort into SF12 named FOP121
 * - Create SystemFunction into SF1 named SF13
 * - Create FunctionInputPort into SF13 named FIP131
 * - Create FunctionalExchange into SF1 named FE11 from FOP111(SF11) to FIP121(SF12)
 * - Create FunctionalExchange into SF1 named FE12 from FOP121(SF12) to FIP131(SF13)
 * - Rename CapabilityPkg from System Analysis to Capabilities
 * - Create Capability into Capabilities named C1
 * - Create FunctionalChain into C1 named C1_FC11
 * - Create FunctionalChainInvolvement into C1_FC11 to FE11
 * - Create FunctionalChainInvolvement into C1_FC11 to SF12
 * - Create FunctionalChainInvolvement into C1_FC11 to SF11
 * - Create FunctionalChainInvolvement into C1_FC11 to FE12
 * - Create FunctionalChainInvolvement into C1_FC11 to SF13
 * - Rename SystemCapabilityInvolvement from C1 to 
 * - Rename LogicalFunctionPkg from Logical Architecture to Logical Functions
 * - Create TransfoLink into Logical Functions named 
 * - Create KeyValue into  named 
 * - Create LogicalFunction into Logical Functions named LF1
 * - Create FunctionalChain into LF1 named LF1_FC11
 * - Create FunctionalChainInvolvement into LF1_FC11 to FE11
 * - Create FunctionalChainInvolvement into LF1_FC11 to LF12
 * - Create FunctionalChainInvolvement into LF1_FC11 to LF11
 * - Create FunctionalChainInvolvement into LF1_FC11 to FE12
 * - Create FunctionalChainInvolvement into LF1_FC11 to LF13
 * - Create FunctionRealization into LF1 to SF1
 * - Create LogicalFunction into LF1 named LF11
 * - Create FunctionOutputPort into LF11 named FOP111
 * - Create LogicalFunction into LF1 named LF12
 * - Create FunctionInputPort into LF12 named FIP121
 * - Create FunctionOutputPort into LF12 named FOP121
 * - Create LogicalFunction into LF1 named LF13
 * - Create FunctionInputPort into LF13 named FIP131
 * - Create FunctionalExchange into LF1 named FE11 from FOP111(LF11) to FIP121(LF12)
 * - Create FunctionalExchange into LF1 named FE12 from FOP121(LF12) to FIP131(LF13)
 * - Rename CapabilityRealizationPkg from Logical Architecture to Capabilities
 * - Create CapabilityRealization into Capabilities named CR1
 * - Create FunctionalChain into CR1 named CR1_FC11
 * - Create FunctionalChainInvolvement into CR1_FC11 to FE11
 * - Create FunctionalChainInvolvement into CR1_FC11 to LF12
 * - Create FunctionalChainInvolvement into CR1_FC11 to LF11
 * - Create FunctionalChainInvolvement into CR1_FC11 to FE12
 * - Create FunctionalChainInvolvement into CR1_FC11 to LF13



 * - Perform functional transition on all functional chains
 * 
 * Expected Result:\
 * - all functional chain should be ok
 * </pre>
 * 
 */
public class Context_FC01 extends TopDownTransitionTestCase {

  private String id_oa1_op11 = "8a2f5b36-34fd-4ea1-b4a2-dbfae2aff736";
  private String id_oc1_op11 = "02bc012c-5dc7-45d6-9f10-b29b2c0b8e1a";
  private String id_sf1_fc11 = "4003dc4e-fe97-4506-ba99-f1e7cc1f1e8d";
  private String id_c1_fc11 = "191d2b75-d4a6-4104-b59a-f1385ba2aa33";
  private String id_lf1_fc11 = "ea5df204-7539-4b87-91ae-fea0fe9816a9";
  private String id_cr1_fc11 = "30f174f9-f6c5-47ce-9aa6-77216ffb6f25";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
    step4();
    step5();
    step6();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_c1_fc11)));
    mustBeTransitioned(id_c1_fc11);
  }

  private void step2() {
    performFunctionalTransition(Arrays.asList(getObject(id_oa1_op11)));
    mustBeTransitioned(id_oa1_op11);
  }

  private void step3() {
    performFunctionalTransition(Arrays.asList(getObject(id_oc1_op11)));
    mustBeTransitioned(id_oc1_op11);
  }

  private void step4() {
    performFunctionalTransition(Arrays.asList(getObject(id_sf1_fc11)));
    mustBeTransitioned(id_sf1_fc11);
  }

  private void step5() {
    performFunctionalTransition(Arrays.asList(getObject(id_lf1_fc11)));
    mustBeTransitioned(id_lf1_fc11);
  }

  private void step6() {
    performFunctionalTransition(Arrays.asList(getObject(id_cr1_fc11)));
    mustBeTransitioned(id_cr1_fc11);
  }
}