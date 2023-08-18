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
package org.polarsys.capella.test.validation.rules.ju.testcases.tj_pa;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

/**
 * Test on TJ_PA_12 : This rule ensures the realization consistency between Physical Architecture and Logical Architecture.
 */
public class Rule_TJ_PA_12 extends AbstractRulesOnTransitionTest {

  @Override
  protected EClass getTargetedEClass() {
    return PaPackage.Literals.PHYSICAL_ARCHITECTURE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.pa.validation.TJ_PA_12";
  }

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "c2a61ba6-1173-4c7d-ab43-ecea4c9300ab" });
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("c2a61ba6-1173-4c7d-ab43-ecea4c9300ab", 1) });
  }

}
