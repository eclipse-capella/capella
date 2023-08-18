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
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

/**
 * Test on TC_DC_12 : This rule ensures that Root Logical Component always realizes a Root System Component.
 */
public class Rule_TC_DC_12 extends AbstractRulesOnTransitionTest {

  @Override
  protected EClass getTargetedEClass() {
    return LaPackage.Literals.LOGICAL_COMPONENT;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.la.validation.TC_DC_12";
  }

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "8256d54b-ba8f-46df-89d8-7fe0d4a582e9" });
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("8256d54b-ba8f-46df-89d8-7fe0d4a582e9", 1) });
  }

}
