/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_d;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on Rule_DWF_D_58: In monopart mode: This rule ensures that ConstrainedElements value is not Part/PartDeploymentLink.
 * @generated
 */
public class Rule_DWF_D_58 extends AbstractRulesOnDesignTest {

  public static final String CONSTRAINTS_1 = "119a7092-8982-4e39-b395-70624a6de7fd";
  public static final String CONSTRAINTS_2 = "3a5ea6eb-51c6-4e75-a04c-1aac37d9950f";
  public static final String CONSTRAINTS_3 = "ff310d8a-5a91-424e-809d-d551213e0043";
  public static final String CONSTRAINTS_4 = "aed567e3-0576-4fa8-9e3b-ebfdfbe1bb9a";
  public static final String CONSTRAINTS_5 = "849ce5ef-857f-469c-84af-446bb3a8707c";
  public static final String CONSTRAINTS_6 = "ba48ed69-5e0f-4d27-a6e4-a0c680e75ba5";
  public static final String CONSTRAINTS_7 = "97157fbb-17da-4c0e-a700-4d300f11ed2d";
  public static final String CONSTRAINTS_8 = "6aacb92c-406e-4d20-86e5-c4c3c6ed5b1f";
  public static final String CONSTRAINTS_9 = "28837852-6600-4baf-9c27-29b708c7fdf0";
  public static final String CONSTRAINTS_10 = "8c2b0c6e-7747-4a84-80e1-b0bf8ee220ca";
  public static final String CONSTRAINTS_11 = "19ebe9cb-25df-4cdd-be93-7e835d202082";
  public static final String CONSTRAINTS_12 = "a34283ee-7b62-48be-b0da-3ff1d5de812b";
  public static final String CONSTRAINTS_13 = "81575950-f249-4932-a245-8ec4ca99a34a";
  
  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return CapellacorePackage.Literals.CONSTRAINT;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.core.validation.DWF_D_58";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] {
        CONSTRAINTS_1,
        CONSTRAINTS_2, 
        CONSTRAINTS_3,
        CONSTRAINTS_4,
        CONSTRAINTS_5,
        CONSTRAINTS_6,
        CONSTRAINTS_7,
        CONSTRAINTS_8,
        CONSTRAINTS_9,
        CONSTRAINTS_10,
        CONSTRAINTS_11,
        CONSTRAINTS_12,
        CONSTRAINTS_13});
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { 
        new OracleDefinition(CONSTRAINTS_1, 1),
        new OracleDefinition(CONSTRAINTS_2, 1),
        new OracleDefinition(CONSTRAINTS_5, 1),
        new OracleDefinition(CONSTRAINTS_6, 1),
        new OracleDefinition(CONSTRAINTS_10, 1),
        new OracleDefinition(CONSTRAINTS_12, 1)
    });
  }

}
