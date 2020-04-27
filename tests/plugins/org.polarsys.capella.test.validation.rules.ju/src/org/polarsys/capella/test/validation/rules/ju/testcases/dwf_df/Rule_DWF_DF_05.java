/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_df;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_DF_05: This check ensures that all the functions involved in FunctionalChain are also active in modes and
 * states as FunctionChain
 * 
 * @generated
 */
public class Rule_DWF_DF_05 extends AbstractRulesOnDesignTest {
  public static final String OK1_OPERATIONAL_PROCESS = "5d74b068-1b06-43be-855b-50629ba1df2c";
  public static final String OK2_OPERATIONAL_PROCESS = "1770da0c-9943-45cd-a0b5-9e756146e6fc";
  public static final String OK3_OPERATIONAL_PROCESS = "fdc00357-1c13-4ebd-9bf6-fecd0b87485e";
  public static final String NOK1_OPERATIONAL_PROCESS = "949176bd-bc18-447b-8753-5d5d91c3ed4a";
  public static final String NOK2_OPERATIONAL_PROCESS = "64124654-d87c-451b-8859-6faf8d82e7ca";

  public static final String OK1_FUNCTIONAL_CHAIN = "d84f31d0-482b-4d69-9769-a0e30ccd7e55";
  public static final String OK2_FUNCTIONAL_CHAIN = "94543312-5e65-483f-b829-84bd19dbb263";
  public static final String OK3_FUNCTIONAL_CHAIN = "a7c5a5c6-f6c9-431a-bc4d-d306fd66cc16";
  public static final String NOK1_FUNCTIONAL_CHAIN = "c045a58a-8bce-44f6-80c0-d224735ec064";
  public static final String NOK2_FUNCTIONAL_CHAIN = "99d0a967-17fb-4401-8eb1-f944e9b7d796";

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTIONAL_CHAIN;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.DWF_DF_05";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { OK1_FUNCTIONAL_CHAIN, OK2_FUNCTIONAL_CHAIN, OK3_FUNCTIONAL_CHAIN,
        NOK1_FUNCTIONAL_CHAIN, NOK2_FUNCTIONAL_CHAIN, OK1_OPERATIONAL_PROCESS, OK2_OPERATIONAL_PROCESS,
        OK3_OPERATIONAL_PROCESS, NOK1_OPERATIONAL_PROCESS, NOK2_OPERATIONAL_PROCESS });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition(NOK1_FUNCTIONAL_CHAIN, 1),
        new OracleDefinition(NOK2_FUNCTIONAL_CHAIN, 1), new OracleDefinition(NOK1_OPERATIONAL_PROCESS, 1),
        new OracleDefinition(NOK2_OPERATIONAL_PROCESS, 1) });
  }
}
