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
package org.polarsys.capella.test.validation.rules.ju.testcases.namingConventions;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ensure that ExchangeItemElement names are different from the containing ExchangeItem name.
 */
public class Rule_NC_1_1_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.information.validation.NC_1-1" });
  }
  
  public void Rule_NC_1_1_TestCase_OK() {
    final ExchangeItemElement eie_ok = InformationFactory.eINSTANCE.createExchangeItemElement();
    eie_ok.setName("My EIE Name");
    
    final ExchangeItem ei = InformationFactory.eINSTANCE.createExchangeItem();
    ei.setName("Another Name for my EI");
    ei.getOwnedElements().add(eie_ok);
    
    ok(eie_ok);
  }
  
  /**
   * 
   */
  public void Rule_NC_1_1_TestCase_KO() {
    final ExchangeItemElement eie_ko = InformationFactory.eINSTANCE.createExchangeItemElement();
    eie_ko.setName("My EIE Name");
    
    final ExchangeItem ei = InformationFactory.eINSTANCE.createExchangeItem();
    ei.setName(eie_ko.getName());
    ei.getOwnedElements().add(eie_ko);
    
    ko(eie_ko);
  }

  @Override
  public void test() throws Exception {
    Rule_NC_1_1_TestCase_OK();
    Rule_NC_1_1_TestCase_KO();
  }

}
