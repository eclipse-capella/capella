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

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ensure that Exchange Items have a description
 */
public class Rule_DS_1_4_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.information.validation.DS_1-4" });
  }

  public void Rule_DS_1_4_TestCase_OK() {
    final ExchangeItem ei_ok = InformationFactory.eINSTANCE.createExchangeItem();
    ei_ok.setDescription("My Description");

    ok(ei_ok);
  }

  public void Rule_DS_1_4_TestCase_KO() {
    final ExchangeItem ei_ko_1 = InformationFactory.eINSTANCE.createExchangeItem();
    ei_ko_1.setDescription(null);
    ko(ei_ko_1);

    final ExchangeItem ei_ko_2 = InformationFactory.eINSTANCE.createExchangeItem();
    ei_ko_2.setDescription("");
    ko(ei_ko_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_1_4_TestCase_OK();
    Rule_DS_1_4_TestCase_KO();
  }

}
