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

import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ensure that Components have a summary
 */
public class Rule_DS_2_1_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.cs.validation.DS_2-1" });
  }

  public void Rule_DS_2_1_TestCase_OK() {
    final SystemComponent comp = CtxFactory.eINSTANCE.createSystemComponent();
    comp.setSummary("My Summary");

    ok(comp);
  }

  public void Rule_DS_2_1_TestCase_KO() {
    final SystemComponent comp_1 = CtxFactory.eINSTANCE.createSystemComponent();
    comp_1.setSummary(null);
    ko(comp_1);

    final SystemComponent comp_2 = CtxFactory.eINSTANCE.createSystemComponent();
    comp_2.setSummary("");
    ko(comp_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_2_1_TestCase_OK();
    Rule_DS_2_1_TestCase_KO();
  }

}
