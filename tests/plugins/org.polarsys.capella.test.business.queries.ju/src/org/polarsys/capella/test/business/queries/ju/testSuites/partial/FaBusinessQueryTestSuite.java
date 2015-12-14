/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.business.queries.ju.testSuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentExchangeCategory_Exchanges;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentExchange_Categories;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentPort_AllocatedPort;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentPort_ProvidedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentPort_RealizedComponentPort;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentPort_RequiredInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ComponentPort_Type;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.Connection_ConnectionFunctionalExchangeAllocations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.Connection_ConnectionRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.Connection_ConvoyedInformation;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.ExchangeCategory_Exchanges;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionInputPort_InComingExchangeItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionInputPort_ProvidedInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionInputPort_RealizedFunctionInputPort;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionInputPort_RequiredInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionOutputPort_OutGoingExchangeItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionOutputPort_ProvidedInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionOutputPort_RealizedFunctionOutputPort;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionOutputPort_RequiredInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalChainInvolvement_ExchangeItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalChain_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalChain_RealizedFunctionalChains;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalExchange_Categories;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalExchange_ExchangeItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.fa.FunctionalExchange_FunctionalExchangeRealizations;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

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
    tests.add(new ComponentExchangeCategory_Exchanges());
    tests.add(new ComponentPort_AllocatedPort());
    tests.add(new ComponentPort_ProvidedInterfaces());
    tests.add(new ComponentPort_RealizedComponentPort());
    tests.add(new ComponentPort_RequiredInterfaces());
    tests.add(new ComponentPort_Type());
    tests.add(new Connection_ConnectionFunctionalExchangeAllocations());
    tests.add(new Connection_ConnectionRealizations());
    tests.add(new Connection_ConvoyedInformation());
    tests.add(new ExchangeCategory_Exchanges());
    tests.add(new FunctionalChain_AvailableInStates());
    tests.add(new FunctionalChain_RealizedFunctionalChains());
    tests.add(new FunctionalChainInvolvement_ExchangeItems());
    tests.add(new FunctionalExchange_Categories());
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

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("sysmodel");
  }

}
