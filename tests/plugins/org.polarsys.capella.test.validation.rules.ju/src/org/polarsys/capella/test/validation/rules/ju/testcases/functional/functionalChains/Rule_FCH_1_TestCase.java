/*******************************************************************************
 * Copyright (c) 2024 Thales LAS France SAS.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.functional.functionalChains;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * FunctionalChain should involve only leaf functions.
 */
public class Rule_FCH_1_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.fa.validation.FCH_1" });
  }

  public void Rule_FCH_1_TestCase_OK() {
    final FunctionalChain fc = FaFactory.eINSTANCE.createFunctionalChain();

    final FunctionalChainInvolvementFunction fcif = FaFactory.eINSTANCE.createFunctionalChainInvolvementFunction();
    fc.getOwnedFunctionalChainInvolvements().add(fcif);
    
    final SystemFunction sysFunction = CtxFactory.eINSTANCE.createSystemFunction();
    fcif.setInvolved(sysFunction);

    ok(fc);
  }

  /**
   * 
   */
  public void Rule_FCH_1_TestCase_KO() {
    final FunctionalChain fc = FaFactory.eINSTANCE.createFunctionalChain();

    final FunctionalChainInvolvementFunction fcif = FaFactory.eINSTANCE.createFunctionalChainInvolvementFunction();
    fc.getOwnedFunctionalChainInvolvements().add(fcif);

    final SystemFunction sysFunction = CtxFactory.eINSTANCE.createSystemFunction();
    fcif.setInvolved(sysFunction);

    // Make sysFunction an non-leaf function, by assigning it a sub-function
    final SystemFunction subSysFunction = CtxFactory.eINSTANCE.createSystemFunction();
    sysFunction.getOwnedFunctions().add(subSysFunction);

    ko(fc);
  }

  @Override
  public void test() throws Exception {
    Rule_FCH_1_TestCase_OK();
    Rule_FCH_1_TestCase_KO();
  }

}
