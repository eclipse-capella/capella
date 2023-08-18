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
package org.polarsys.capella.test.transition.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.transition.ju.transitions.*;

import junit.framework.Test;

public class TransitionsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new TransitionsTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new ActorTransitionWithGeneralizationTest());
    tests.add(new SequenceMessageAndExchangeItems());
    
    tests.add(new Context_A01_01());
    tests.add(new Context_A01_02());
    tests.add(new Context_A01_03());
    tests.add(new Context_A01_04());
    tests.add(new Context_DT01_01());
    tests.add(new Context_DT01_02());
    tests.add(new Context_DT01_03());
    tests.add(new Context_DT01_04());
    tests.add(new Context_DT01_05());
    tests.add(new Context_EI01_01());
    tests.add(new Context_EI01_02());
    tests.add(new Context_EI01_03());
    tests.add(new Context_EI01_04());
    tests.add(new Context_EI01_05());
    tests.add(new Context_EI01_06());
    tests.add(new Context_EI01_07());
    tests.add(new Context_EI01_08());
    tests.add(new Context_FC01());
    tests.add(new Context_I01_01());
    tests.add(new Context_I01_02());
    tests.add(new Context_LCPC01_01());
    tests.add(new Context_LCPC01_02());
    tests.add(new Context_LCPC01_03());
    tests.add(new Context_SF01_01());
    tests.add(new Context_SF01_02());
    tests.add(new Context_SF01_04());
    tests.add(new Context_SF01_06());
    tests.add(new Context_SF01_07());
    tests.add(new Context_SF01_08());
    tests.add(new Context_SM01_01());
    tests.add(new Context_SM01_02());
    tests.add(new Context_SM01_03());
    tests.add(new Context_CEPL());
    tests.add(new Context_Part());
    tests.add(new CreateRule_ES2ES_01());
    tests.add(new CreateRule_ES2ES_02());
    tests.add(new CreateRule_ES2ES_03());
    tests.add(new CreateRule_ES2ES_04());
    tests.add(new CreateRule_ES2ES_SystemAllocation());
    tests.add(new CreateRule_ES2ES_MoveFunction());
    tests.add(new CreateRule_ES2ES_MoveFunctionPort());
    tests.add(new CreateRule_ES2IS_01());
    tests.add(new CreateRule_ES2IS_02());
    tests.add(new CreateRule_ESF2ESB_01());
    tests.add(new CreateRule_FS2ES_01());
    tests.add(new CreateRule_FS2ES_02());
    tests.add(new CreateRule_FS2FS_01());
    tests.add(new CreateRule_FS2FS_Involvement());
    tests.add(new CreateRule_RA01_01());
    tests.add(new CreateRule_RA01_02());
    tests.add(new CreateRule_RA01_03());
    tests.add(new CreateRule_RA01_04());
    tests.add(new CreateRule_RA01_05());
    tests.add(new CreateRule_ScenarioUml2_01());
    tests.add(new CreateRule_StateFragments());
    //tests.add(new Exception_CFE01_01()); //This test is a wrong-positive revealing issue 2685
    tests.add(new Exception_FCI01_01());
    tests.add(new Exception_FCI01_02());
    tests.add(new Exception_FCI01_03());
    tests.add(new Exception_FE01_01());
    tests.add(new Exception_IP01_01());
    tests.add(new Exception_IR01());
    tests.add(new Exception_ME01G_01());
    tests.add(new Exception_OAI01_01());
    tests.add(new Exception_SF01_01());
    tests.add(new Exception_SF01_02());
    tests.add(new Exception_SF01_03());
    tests.add(new Exception_SF01_04());
    tests.add(new FunctionalChainInvolvmentsCapabilities());
    tests.add(new FunctionalExchangeAttachment());
    tests.add(new GenerateInterface01_01());
    tests.add(new LCPCTransition_02());
    tests.add(new LaInnerLC());
    tests.add(new ManyToOne());
    tests.add(new OneToMany());
    tests.add(new PropertyValuesParameter_01());
    tests.add(new PropertyValuesParameter_02());
    tests.add(new PropertyValuesParameter_03());
    tests.add(new PropertyValuesParameter_04());
    tests.add(new PropertyValuesParameter_05());
    tests.add(new PropertyValuesParameter_06());
    tests.add(new PropertyValuesParameter_07());
    tests.add(new PropertyValuesParameter_08());
    tests.add(new PropertyValuesParameter_09());
    tests.add(new PropertyValuesParameter_10());
    tests.add(new UpdateRule_FC01_01());
    tests.add(new UpdateRule_MEG01EI_01());
    tests.add(new UpdateRule_MEG01EI_02());
    tests.add(new UpdateRule_MEG01EI_03());
    tests.add(new UpdateRule_MEG01I_01());
    tests.add(new UpdateRule_MEG01TYPE_01());
    tests.add(new UpdateRule_NE01_01());
    tests.add(new UpdateRule_ST01_01());
    tests.add(new SystemTransition());
    tests.add(new CreateRule_IS2IS_MultiInstanceRoles());
    tests.add(new CreateRule_IS2IS_ItfEI_01());
    tests.add(new CreateRule_IS2IS_ItfEI_02());
    tests.add(new CreateRule_IS2IS_ItfEI_03());
    tests.add(new CreateRule_IS2IS_ItfEI_04());
    tests.add(new CreateRule_IS2IS_StateFragment_01());
    tests.add(new CreateRule_IS2IS_StateFragment_02());
    tests.add(new CreateRule_IS2IS_EPBS_01());
    tests.add(new CreateRule_IS2IS_EPBS_02());
    tests.add(new CreateRule_IS2IS_EPBS_03());
    tests.add(new CreateRule_IS2IS_EPBS_04());
    tests.add(new TwiceAllocation());
    tests.add(new NonLeafAllocation());
    tests.add(new ReferencedScenarios_01());
    tests.add(new ReferencedScenarios_02());
    tests.add(new ReferencedScenarios_03());
    tests.add(new ReferencedScenarios_04());
    tests.add(new ReferencedScenarios_05());
    
    return tests;
  }

}
