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
package org.polarsys.capella.test.validation.rules.ju.testcases.tc_dc;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

/**
 * Test on TC_DC_13 : This rule ensures that Root Physical Component always realizes a Root Logical Component.
 */
public class Rule_TC_DC_13 extends AbstractRulesOnTransitionTest {

  @Override
  protected EClass getTargetedEClass() {
    return PaPackage.Literals.PHYSICAL_COMPONENT;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.pa.validation.TC_DC_13";
  }

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "33a3a42f-df1a-4055-bf49-3236bd838890" });
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("33a3a42f-df1a-4055-bf49-3236bd838890", 1) });
  }

}
