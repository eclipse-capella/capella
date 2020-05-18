/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_dc;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * This rule checks that only a Root Component exists at System/Logical/Physical level
 */
public class Rule_DWF_DC_38 extends AbstractRulesOnDesignTest {
  public static final String LA_ARCHITECTURE = "2533beae-1966-4952-a476-424224988605";

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  @Override
  protected EClass getTargetedEClass() {
    return LaPackage.Literals.LOGICAL_ARCHITECTURE;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.cs.validation.DWF_DC_38";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { LA_ARCHITECTURE });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition(LA_ARCHITECTURE, 1) });
  }
}
