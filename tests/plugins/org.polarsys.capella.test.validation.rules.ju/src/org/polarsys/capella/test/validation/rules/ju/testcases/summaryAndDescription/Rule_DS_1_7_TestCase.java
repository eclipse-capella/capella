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

import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * FunctionalChains should have a description.
 */
public class Rule_DS_1_7_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.fa.validation.DS_1-7" });
  }

  public void Rule_DS_1_7_TestCase_OK() {
    final FunctionalChain fc_ok = FaFactory.eINSTANCE.createFunctionalChain();
    fc_ok.setDescription("My Description");

    ok(fc_ok);
  }

  public void Rule_DS_1_7_TestCase_KO() {
    final FunctionalChain fc_ko_1 = FaFactory.eINSTANCE.createFunctionalChain();
    fc_ko_1.setDescription(null);
    ko(fc_ko_1);

    final FunctionalChain fc_ko_2 = FaFactory.eINSTANCE.createFunctionalChain();
    fc_ko_2.setDescription("");
    ko(fc_ko_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_1_7_TestCase_OK();
    Rule_DS_1_7_TestCase_KO();
  }

}
