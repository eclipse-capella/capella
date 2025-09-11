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
package org.polarsys.capella.test.validation.rules.ju.testcases.summaryAndDescription;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ensure that Exchange Item Elements have a description
 */
public class Rule_DS_1_3_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.information.validation.DS_1-3" });
  }

  public void Rule_DS_1_3_TestCase_OK() {
    final ExchangeItemElement eie_ok = InformationFactory.eINSTANCE.createExchangeItemElement();
    eie_ok.setDescription("My Description");

    ok(eie_ok);
  }

  public void Rule_DS_1_3_TestCase_KO() {
    final ExchangeItemElement eie_ko_1 = InformationFactory.eINSTANCE.createExchangeItemElement();
    eie_ko_1.setDescription(null);
    ko(eie_ko_1);

    final ExchangeItemElement eie_ko_2 = InformationFactory.eINSTANCE.createExchangeItemElement();
    eie_ko_2.setDescription("");
    ko(eie_ko_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_1_3_TestCase_OK();
    Rule_DS_1_3_TestCase_KO();
  }

}
