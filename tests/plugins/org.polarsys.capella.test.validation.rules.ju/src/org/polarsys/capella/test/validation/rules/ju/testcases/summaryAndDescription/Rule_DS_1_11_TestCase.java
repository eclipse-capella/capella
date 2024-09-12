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
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * ComponentExchanges should have a description
 */
public class Rule_DS_1_11_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.fa.validation.DS_1-11" });
  }

  public void Rule_DS_1_11_TestCase_OK() {
    final ComponentExchange ce_ok = FaFactory.eINSTANCE.createComponentExchange();
    ce_ok.setDescription("My Description");

    final SystemComponent sysComp = CtxFactory.eINSTANCE.createSystemComponent();
    sysComp.getOwnedComponentExchanges().add(ce_ok);

    final SystemComponentPkg sysCompPkg = CtxFactory.eINSTANCE.createSystemComponentPkg();
    sysCompPkg.getOwnedSystemComponents().add(sysComp);

    final SystemAnalysis sa = CtxFactory.eINSTANCE.createSystemAnalysis();
    sa.setOwnedSystemComponentPkg(sysCompPkg);

    ok(ce_ok);
  }

  public void Rule_DS_1_11_TestCase_KO() {
    final ComponentExchange ce_ko_1 = FaFactory.eINSTANCE.createComponentExchange();
    ce_ko_1.setDescription(null);

    final ComponentExchange ce_ko_2 = FaFactory.eINSTANCE.createComponentExchange();
    ce_ko_2.setDescription("");

    final SystemComponent sysComp = CtxFactory.eINSTANCE.createSystemComponent();
    sysComp.getOwnedComponentExchanges().add(ce_ko_1);
    sysComp.getOwnedComponentExchanges().add(ce_ko_2);

    final SystemComponentPkg sysCompPkg = CtxFactory.eINSTANCE.createSystemComponentPkg();
    sysCompPkg.getOwnedSystemComponents().add(sysComp);

    final SystemAnalysis sa = CtxFactory.eINSTANCE.createSystemAnalysis();
    sa.setOwnedSystemComponentPkg(sysCompPkg);

    ko(ce_ko_1);
    ko(ce_ko_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_1_11_TestCase_OK();
    Rule_DS_1_11_TestCase_KO();
  }

}
