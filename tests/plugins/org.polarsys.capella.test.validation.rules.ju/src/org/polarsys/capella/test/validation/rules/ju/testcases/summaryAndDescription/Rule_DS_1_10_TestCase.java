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
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Actors should have a description
 */
public class Rule_DS_1_10_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.cs.validation.DS_1-10" });
  }

  public void Rule_DS_1_10_TestCase_OK() {
    final SystemComponent actor_ok = CtxFactory.eINSTANCE.createSystemComponent();
    actor_ok.setActor(true);
    actor_ok.setDescription("My Description");

    final SystemComponentPkg sysCompPkg = CtxFactory.eINSTANCE.createSystemComponentPkg();
    sysCompPkg.getOwnedSystemComponents().add(actor_ok);

    final SystemAnalysis sa = CtxFactory.eINSTANCE.createSystemAnalysis();
    sa.setOwnedSystemComponentPkg(sysCompPkg);

    ok(actor_ok);
  }

  public void Rule_DS_1_10_TestCase_KO() {
    final SystemComponent actor_ko_1 = CtxFactory.eINSTANCE.createSystemComponent();
    actor_ko_1.setActor(true);
    actor_ko_1.setDescription(null);

    final SystemComponent actor_ko_2 = CtxFactory.eINSTANCE.createSystemComponent();
    actor_ko_2.setActor(true);
    actor_ko_2.setDescription("");

    final SystemComponentPkg sysCompPkg = CtxFactory.eINSTANCE.createSystemComponentPkg();
    sysCompPkg.getOwnedSystemComponents().add(actor_ko_1);
    sysCompPkg.getOwnedSystemComponents().add(actor_ko_2);

    final SystemAnalysis sa = CtxFactory.eINSTANCE.createSystemAnalysis();
    sa.setOwnedSystemComponentPkg(sysCompPkg);

    ko(actor_ko_1);
    ko(actor_ko_2);
  }

  @Override
  public void test() throws Exception {
    Rule_DS_1_10_TestCase_OK();
    Rule_DS_1_10_TestCase_KO();
  }

}
