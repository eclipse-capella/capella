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
package org.polarsys.capella.test.validation.rules.ju.testcases.tj_la;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

/**
 * Test on TJ_LA_07 : This rule ensures the realization consistency between Logical Architecture and System Analysis.
 */
public class Rule_TJ_LA_07 extends AbstractRulesOnTransitionTest {

  @Override
  protected EClass getTargetedEClass() {
    return LaPackage.Literals.LOGICAL_ARCHITECTURE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.la.validation.TJ_LA_07";
  }

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "c2a7f4c9-0866-488e-b1f9-4618971f8cd9" });
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("c2a7f4c9-0866-488e-b1f9-4618971f8cd9", 1) });
  }

}
