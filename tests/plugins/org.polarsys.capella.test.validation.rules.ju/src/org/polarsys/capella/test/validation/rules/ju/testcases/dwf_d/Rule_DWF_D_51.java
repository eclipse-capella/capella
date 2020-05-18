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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_d;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_D_51: This rule checks that for not abstract association role and exchange item element, minimum
 * cardinality value is defined and evals to a natural or zero (i.e. integer belonging to [0, *[).
 * 
 * @generated
 */
public class Rule_DWF_D_51 extends AbstractRulesOnDesignTest {

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return InformationPackage.Literals.CLASS;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.information.validation.DWF_D_51";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] {
        "bc057a6a-5a9d-48c4-a651-5bea50babdd1",
        "363ebe2b-1bd3-45b9-a2a5-63d0db8a6192",
        "88054412-1681-42fe-aeae-4eae0c1d00fa",
        "bc80b012-0b17-4f1a-b792-44e0cc150817",
        "fa1400fb-d78c-451e-81fb-5d7716fc9e54",
        "e44c0907-6754-4ba5-8ea8-91c6b2fc9358",
        "f193c8d3-548c-435c-a935-e2cbe19b4fd0",
        "81bb90c0-079f-412a-b3c5-787ad6daa791",
        "58d56883-89ad-479f-a379-bea10208603b",
        "2c5f3de1-de76-47da-bc1b-afd687c92c67",
        "6931c927-6bbd-431e-b4e8-bb6ccd6b32f2"});
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] {
        new OracleDefinition("363ebe2b-1bd3-45b9-a2a5-63d0db8a6192", 1),
        new OracleDefinition("fa1400fb-d78c-451e-81fb-5d7716fc9e54", 1),
        new OracleDefinition("e44c0907-6754-4ba5-8ea8-91c6b2fc9358", 1),
        new OracleDefinition("f193c8d3-548c-435c-a935-e2cbe19b4fd0", 1),
        new OracleDefinition("58d56883-89ad-479f-a379-bea10208603b", 1)});
  }

}
