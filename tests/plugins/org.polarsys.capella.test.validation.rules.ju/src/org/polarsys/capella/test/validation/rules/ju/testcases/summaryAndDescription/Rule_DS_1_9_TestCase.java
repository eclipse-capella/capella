/*******************************************************************************
 * Copyright (c) 2024, 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.summaryAndDescription;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * FunctionalExchanges should have a description
 */
public class Rule_DS_1_9_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.fa.validation.DS_1-9" });
  }

  public void Rule_DS_1_9_TestCase_OK() {
    final FunctionalExchange fe_ok = FaFactory.eINSTANCE.createFunctionalExchange();
    fe_ok.setDescription("My Description");
    
    final SystemFunction sf = CtxFactory.eINSTANCE.createSystemFunction();
    sf.getOwnedFunctionalExchanges().add(fe_ok);
    
    final SystemFunctionPkg fpkg = CtxFactory.eINSTANCE.createSystemFunctionPkg();
    fpkg.getOwnedSystemFunctions().add(sf);
    
    final SystemAnalysis sa = CtxFactory.eINSTANCE.createSystemAnalysis();
    sa.setOwnedFunctionPkg(fpkg);

    ok(fe_ok);
  }

  public void Rule_DS_1_9_TestCase_KO() {
    final FunctionalExchange fe_ko_1 = FaFactory.eINSTANCE.createFunctionalExchange();
    fe_ko_1.setDescription(null);
    
    final FunctionalExchange fe_ko_2 = FaFactory.eINSTANCE.createFunctionalExchange();
    fe_ko_2.setDescription("");
    
    final SystemFunction sf = CtxFactory.eINSTANCE.createSystemFunction();
    sf.getOwnedFunctionalExchanges().add(fe_ko_1);
    sf.getOwnedFunctionalExchanges().add(fe_ko_2);
    
    final SystemFunctionPkg fpkg = CtxFactory.eINSTANCE.createSystemFunctionPkg();
    fpkg.getOwnedSystemFunctions().add(sf);
    
    final SystemAnalysis sa = CtxFactory.eINSTANCE.createSystemAnalysis();
    sa.setOwnedFunctionPkg(fpkg);
    
    ko(fe_ko_1);
    ko(fe_ko_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_1_9_TestCase_OK();
    Rule_DS_1_9_TestCase_KO();
  }

}
