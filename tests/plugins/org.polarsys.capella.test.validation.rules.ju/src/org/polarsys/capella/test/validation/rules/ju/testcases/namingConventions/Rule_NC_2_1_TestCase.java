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
package org.polarsys.capella.test.validation.rules.ju.testcases.namingConventions;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ensure that Exchange Item Element names are different from their Target Type name.
 */
public class Rule_NC_2_1_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.information.validation.NC_2-1" });
  }

  public void Rule_NC_2_1_TestCase_OK() {
    final ExchangeItemElement eie_ok = InformationFactory.eINSTANCE.createExchangeItemElement();
    eie_ok.setName("My EIE name");

    final Class targetClass = InformationFactory.eINSTANCE.createClass();
    targetClass.setName("Another Name for target Class");

    eie_ok.setAbstractType(targetClass);

    ok(eie_ok);
  }

  /**
   * 
   */
  public void Rule_NC_2_1_TestCase_KO() {
    final ExchangeItemElement eie_ko = InformationFactory.eINSTANCE.createExchangeItemElement();
    eie_ko.setName("My EIE name");

    final Class targetClass = InformationFactory.eINSTANCE.createClass();
    targetClass.setName(eie_ko.getName());

    eie_ko.setAbstractType(targetClass);

    ko(eie_ko);
  }

  @Override
  public void test() throws Exception {
    Rule_NC_2_1_TestCase_OK();
    Rule_NC_2_1_TestCase_KO();
  }

}
