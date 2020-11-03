/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.business.queries.ju.testSuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentExchangeCategory_Exchanges;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentExchange_Categories;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentExchange_Source;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentExchange_Target;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentPort_AllocatedPort;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentPort_ProvidedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentPort_RealizedComponentPort;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentPort_RequiredInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentPort_Type;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentExchange_AllocatedFunctionalExchanges;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentExchange_RealizedComponentExchanges;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentExchange_ConvoyedInformations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ExchangeCategory_Exchanges;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionInputPort_InComingExchangeItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionInputPort_ProvidedInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionInputPort_RealizedFunctionInputPort;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionInputPort_RequiredInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionOutputPort_OutGoingExchangeItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionOutputPort_ProvidedInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionOutputPort_RealizedFunctionOutputPort;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionOutputPort_RequiredInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalChainInvolvementLink_ExchangeItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalChain_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalChain_RealizedFunctionalChains;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalExchange_Categories;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalExchange_ExchangeItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalExchange_FunctionalExchangeRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalExchange_Source;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalExchange_Target;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.SequenceLink_Links;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 * @author Erwan Brottier
 */
public class FaBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new FaBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ComponentExchange_Categories());
    tests.add(new ComponentExchange_Source());
    tests.add(new ComponentExchange_Target());
    tests.add(new ComponentExchangeCategory_Exchanges());
    tests.add(new ComponentPort_AllocatedPort());
    tests.add(new ComponentPort_ProvidedInterfaces());
    tests.add(new ComponentPort_RealizedComponentPort());
    tests.add(new ComponentPort_RequiredInterfaces());
    tests.add(new ComponentPort_Type());
    tests.add(new ComponentExchange_AllocatedFunctionalExchanges());
    tests.add(new ComponentExchange_RealizedComponentExchanges());
    tests.add(new ComponentExchange_ConvoyedInformations());
    tests.add(new ExchangeCategory_Exchanges());
    tests.add(new FunctionalChain_AvailableInStates());
    tests.add(new FunctionalChain_RealizedFunctionalChains());
    tests.add(new FunctionalChainInvolvementLink_ExchangeItems());
    tests.add(new SequenceLink_Links());
    tests.add(new FunctionalExchange_Categories());
    tests.add(new FunctionalExchange_Source());
    tests.add(new FunctionalExchange_Target());
    tests.add(new FunctionalExchange_ExchangeItems());
    tests.add(new FunctionalExchange_FunctionalExchangeRealizations());
    tests.add(new FunctionInputPort_InComingExchangeItems());
    tests.add(new FunctionInputPort_ProvidedInterface());
    tests.add(new FunctionInputPort_RealizedFunctionInputPort());
    tests.add(new FunctionInputPort_RequiredInterface());
    tests.add(new FunctionOutputPort_OutGoingExchangeItems());
    tests.add(new FunctionOutputPort_ProvidedInterface());
    tests.add(new FunctionOutputPort_RealizedFunctionOutputPort());
    tests.add(new FunctionOutputPort_RequiredInterface());    
    return tests;
  }
}
