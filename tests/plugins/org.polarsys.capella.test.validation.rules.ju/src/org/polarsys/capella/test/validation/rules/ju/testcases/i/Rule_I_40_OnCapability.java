/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnIntegrityTest;

/**
 * test on I_26: Checks that there are no equivalent Involvement elements. Two Involvement elements are equivalent if
 * they have identical types and identical source and target elements.
 * 
 * @generated
 */
public class Rule_I_40_OnCapability extends AbstractRulesOnIntegrityTest {

  private final static String OPERATIONAL_CAPABILITY_1 = "d38df0ca-5af1-4396-8e51-e003645e7407";
  private final static String OPERATIONAL_CAPABILITY_2 = "390d29fd-d10f-464d-bc23-598f623a5d20";
  private final static String CAPABILITY_REALIZATION_1 = "14914d40-66e2-4729-8955-c8abb0903bea";
  private final static String CAPABILITY_REALIZATION_2 = "25585ce0-f36e-413b-a056-fb4cd43ddf30";

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return CtxPackage.Literals.CAPABILITY;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.core.validation.I_40";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { OPERATIONAL_CAPABILITY_1, OPERATIONAL_CAPABILITY_2, CAPABILITY_REALIZATION_1,
        CAPABILITY_REALIZATION_2 });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition(OPERATIONAL_CAPABILITY_1, 1),
        new OracleDefinition(CAPABILITY_REALIZATION_1, 1), new OracleDefinition(OPERATIONAL_CAPABILITY_2, 0),
        new OracleDefinition(CAPABILITY_REALIZATION_2, 0) });
  }
}
