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

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ensure that ExchangeItem names are different from the containing Interface name.
 */
public class Rule_NC_1_2_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.information.validation.NC_1-2" });
  }

  public void Rule_NC_1_2_TestCase_OK() {
    final ExchangeItem ei_ok = InformationFactory.eINSTANCE.createExchangeItem();
    ei_ok.setName("My EI Name");

    final ExchangeItemAllocation eia = CsFactory.eINSTANCE.createExchangeItemAllocation();
    eia.setAllocatedItem(ei_ok);

    final Interface intf = CsFactory.eINSTANCE.createInterface();
    intf.setName("Another Name for my Interface");
    intf.getOwnedExchangeItemAllocations().add(eia);
    
    ok(eia);
  }

  /**
   * 
   */
  public void Rule_NC_1_2_TestCase_KO() {
    final ExchangeItem ei_ko = InformationFactory.eINSTANCE.createExchangeItem();
    ei_ko.setName("My EI Name");

    final ExchangeItemAllocation eia = CsFactory.eINSTANCE.createExchangeItemAllocation();
    eia.setAllocatedItem(ei_ko);

    final Interface intf = CsFactory.eINSTANCE.createInterface();
    intf.setName(ei_ko.getName());
    intf.getOwnedExchangeItemAllocations().add(eia);

    ko(eia);
  }

  @Override
  public void test() throws Exception {
    Rule_NC_1_2_TestCase_OK();
    Rule_NC_1_2_TestCase_KO();
  }

}
