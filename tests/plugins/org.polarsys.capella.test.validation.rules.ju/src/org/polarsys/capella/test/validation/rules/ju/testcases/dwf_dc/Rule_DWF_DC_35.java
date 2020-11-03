/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_DC_35: Check that the function is available in all the Mode/State allocated to a functional chain in
 * which the function is involved.
 * 
 * @generated
 */
public class Rule_DWF_DC_35 extends AbstractRulesOnDesignTest {
  public static final String OPERATIONAL_ACTIVITY_1 = "7a109f7e-7274-4d61-9c0c-207926d6c13b";
  public static final String OPERATIONAL_ACTIVITY_2 = "f48c9a52-899c-464a-95ef-96a59e10ff5b";
  public static final String OPERATIONAL_ACTIVITY_3 = "b4d2cf43-b03e-47b1-9f76-d0b388783ee9";
  public static final String OPERATIONAL_ACTIVITY_4_NOK = "d5a07e76-98bc-4199-a6dd-65b4224196a9";
  public static final String OPERATIONAL_ACTIVITY_5 = "c7548570-0c93-4af6-8bb6-981f29a1b905";
  public static final String OPERATIONAL_ACTIVITY_6 = "f4f346b9-b1c4-490a-91fa-b67865f0bd25";

  public static final String SYSTEM_FUNCTION_1 = "cb51a680-cb41-48fd-8114-1c84ca14b886";
  public static final String SYSTEM_FUNCTION_2 = "fb9df7b4-562e-481d-b08c-8c9d0d7f7850";
  public static final String SYSTEM_FUNCTION_3 = "2e52e8b1-338f-43ef-a909-8e96c3c6eaac";
  public static final String SYSTEM_FUNCTION_4_NOK = "3ded208d-68c6-4ad1-9b99-ba13dbc9d9c2";
  public static final String SYSTEM_FUNCTION_5 = "ca1b277b-5802-4718-a41d-238ed3c8ee5d";
  public static final String SYSTEM_FUNCTION_6 = "7760242c-2e83-43e0-8f64-2e8116010252";

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.ABSTRACT_FUNCTION;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.DWF_DC_35";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { OPERATIONAL_ACTIVITY_1, OPERATIONAL_ACTIVITY_2, OPERATIONAL_ACTIVITY_3,
        OPERATIONAL_ACTIVITY_4_NOK, OPERATIONAL_ACTIVITY_5, OPERATIONAL_ACTIVITY_6, SYSTEM_FUNCTION_1,
        SYSTEM_FUNCTION_2, SYSTEM_FUNCTION_3, SYSTEM_FUNCTION_4_NOK, SYSTEM_FUNCTION_5, SYSTEM_FUNCTION_6 });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition(OPERATIONAL_ACTIVITY_4_NOK, 1),
        new OracleDefinition(SYSTEM_FUNCTION_4_NOK, 1) });
  }
}
