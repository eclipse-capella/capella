/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.tj_sa;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

/**
 * Test on TJ_SA_0 : This rule ensures the realization consistency between System Analysis and Operational Analysis.
 */
public class Rule_TJ_SA_04 extends AbstractRulesOnTransitionTest {

  @Override
  protected EClass getTargetedEClass() {
    return CtxPackage.Literals.SYSTEM_ANALYSIS;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.ctx.validation.TJ_SA_04";
  }

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "4b52a407-ea27-493f-b259-bb4c1b1708da" });
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("4b52a407-ea27-493f-b259-bb4c1b1708da", 1) });
  }

}
