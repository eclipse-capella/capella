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
package org.polarsys.capella.test.validation.rules.ju.testcases.tj_epbs;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

/**
 * Test on TJ_EPBS_04 : This rule ensures the realization consistency between EPBS Architecture and Physical Architecture.
 */
public class Rule_TJ_EPBS_04 extends AbstractRulesOnTransitionTest {

  @Override
  protected EClass getTargetedEClass() {
    return EpbsPackage.Literals.EPBS_ARCHITECTURE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.epbs.validation.TJ_EPBS_04";
  }

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "6e4dac02-f611-4ca8-b7e3-462968a98e16" });
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("6e4dac02-f611-4ca8-b7e3-462968a98e16", 1) });
  }

}
