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
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * SystemFunctions should have a description
 */
public class Rule_DS_1_8_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.ctx.validation.DS_1-8" });
  }

  public void Rule_DS_1_8_TestCase_OK() {
    final SystemFunction sf_ok = CtxFactory.eINSTANCE.createSystemFunction();
    sf_ok.setDescription("My Description");

    ok(sf_ok);
  }

  public void Rule_DS_1_8_TestCase_KO() {
    final SystemFunction sf_ko_1 = CtxFactory.eINSTANCE.createSystemFunction();
    sf_ko_1.setDescription(null);
    ko(sf_ko_1);

    final SystemFunction sf_ko_2 = CtxFactory.eINSTANCE.createSystemFunction();
    sf_ko_2.setDescription("");
    ko(sf_ko_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_1_8_TestCase_OK();
    Rule_DS_1_8_TestCase_KO();
  }

}
