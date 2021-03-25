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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_dc;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

public class Rule_DWF_DC_44 extends AbstractRulesOnDesignTest {
  
  public static final String PC_1_2N = "091aa694-d1b8-408b-ac10-9a9fb9295fe2"; //$NON-NLS-1$
  public static final String PC_1_3N = "e88c090c-3578-47b7-9a0a-ed74b57b98d4"; //$NON-NLS-1$
  public static final String PC_1_4N = "8c85401c-e937-4279-8b4d-cbf91e6a1bf8"; //$NON-NLS-1$
  public static final String PC_1_5N = "8d292707-629d-4f61-9ff5-c35e54abbbc3"; //$NON-NLS-1$
  public static final String PC_1_2B = "a8ae81c8-9a7c-4c72-9e80-978054b8112d"; //$NON-NLS-1$
  public static final String PC_1_4B = "fe4a4aca-e0bf-4778-a962-ecfff3615918"; //$NON-NLS-1$
  public static final String PC_1_3B = "55982c67-fee7-4d7c-9326-a1d553173748"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  @Override
  protected EClass getTargetedEClass() {
    return PaPackage.Literals.PHYSICAL_COMPONENT;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.pa.validation.DWF_DC_44";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(PC_1_2N, PC_1_3N, PC_1_4N, PC_1_5N, PC_1_2B, PC_1_4B, PC_1_3B);
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition(PC_1_2N, 1), new OracleDefinition(PC_1_3N, 1),
        new OracleDefinition(PC_1_4N, 0), new OracleDefinition(PC_1_5N, 1),
        new OracleDefinition(PC_1_2B, 1), new OracleDefinition(PC_1_4B, 1),
        new OracleDefinition(PC_1_3B, 0));
  }
}
