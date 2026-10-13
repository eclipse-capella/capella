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

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ensure that Data Types have a summary
 */
public class Rule_DS_2_2_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.information.validation.DS_2-2" });
  }

  public void Rule_DS_2_2_TestCase_OK() {
    final Class class_ok = InformationFactory.eINSTANCE.createClass();
    class_ok.setSummary("My Summary");

    ok(class_ok);
  }

  public void Rule_DS_2_2_TestCase_KO() {
    final Class class_ko_1 = InformationFactory.eINSTANCE.createClass();
    class_ko_1.setSummary(null);
    ko(class_ko_1);

    final Class class_ko_2 = InformationFactory.eINSTANCE.createClass();
    class_ko_2.setSummary("");
    ko(class_ko_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_2_2_TestCase_OK();
    Rule_DS_2_2_TestCase_KO();
  }

}
