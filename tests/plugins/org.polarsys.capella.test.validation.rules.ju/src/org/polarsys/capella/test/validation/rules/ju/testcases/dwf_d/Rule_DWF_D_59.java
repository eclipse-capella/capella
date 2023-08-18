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
 * test on Rule_DWF_D_59: 
 *  In monopart mode: This rule ensures that a constraint is not stored under and have ConstrainedElements values as a Part/PartDeploymentLink.
    Exception : In EPBS Layer constraint's can be stored under Part

    In multipart mode : A constraint should not be stored under PartDeploymentlink
    Exception : If first ConstraintElements value is container PartDeploymentlink or empty ConstraintElements value
 * @generated
 */
public class Rule_DWF_D_59 extends AbstractRulesOnDesignTest {

  public static final String CONSTRAINTS_1 = "5be7fc6c-4465-43ee-a425-9d6939455613";
  public static final String CONSTRAINTS_2 = "93af5b04-e33e-456e-8cd8-3ebe5cf7f54b";
  public static final String CONSTRAINTS_3 = "c07e4429-d824-4b6f-87cb-a28cfc72d15b";
  public static final String CONSTRAINTS_4 = "8e7b18ca-7e33-48d7-97cb-7be900ea707e";
  public static final String CONSTRAINTS_5 = "65f66f7b-9278-4733-902b-bb503a2d0c2f";
  public static final String CONSTRAINTS_6 = "acd06c3a-9cea-4b34-9fbb-0db22df77605";
  public static final String CONSTRAINTS_7 = "20121f3c-e075-4946-bee7-fc3ced6c656e";
  public static final String CONSTRAINTS_8 = "c949a40e-44cb-4377-9f24-3bd85282826c";
  public static final String CONSTRAINTS_9 = "13aba3bb-24a7-4392-9e55-cd05e8d388cd";
  public static final String CONSTRAINTS_10 = "7de4969a-e1bc-4c0a-afb1-4e89200f32c8";
  public static final String CONSTRAINTS_11 = "fe301422-b071-4136-883a-5a3c201e3713";
  public static final String CONSTRAINTS_12 = "6ade0c33-e0bb-4668-8ab5-0de62fb79f4e";
  public static final String CONSTRAINTS_13 = "ad9a3d98-635e-4b49-9943-fc09854965d4";
  
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
    return "org.polarsys.capella.core.data.core.validation.DWF_D_59";
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
        new OracleDefinition(CONSTRAINTS_3, 1),
        new OracleDefinition(CONSTRAINTS_4, 1),
        new OracleDefinition(CONSTRAINTS_7, 1),
        new OracleDefinition(CONSTRAINTS_8, 1)
     });
  }

}
