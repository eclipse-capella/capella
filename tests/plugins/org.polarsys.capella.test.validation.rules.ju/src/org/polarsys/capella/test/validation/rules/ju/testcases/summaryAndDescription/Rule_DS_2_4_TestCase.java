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

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ensure that Interfaces have a summary
 */
public class Rule_DS_2_4_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.cs.validation.DS_2-4" });
  }

  public void Rule_DS_2_4_TestCase_OK() {
    final Interface intf_ok = CsFactory.eINSTANCE.createInterface();
    intf_ok.setSummary("My Summary");

    ok(intf_ok);
  }

  public void Rule_DS_2_4_TestCase_KO() {
    final Interface intf_ko_1 = CsFactory.eINSTANCE.createInterface();
    intf_ko_1.setSummary(null);
    ko(intf_ko_1);

    final Interface intf_ko_2 = CsFactory.eINSTANCE.createInterface();
    intf_ko_2.setSummary("");
    ko(intf_ko_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_2_4_TestCase_OK();
    Rule_DS_2_4_TestCase_KO();
  }

}
