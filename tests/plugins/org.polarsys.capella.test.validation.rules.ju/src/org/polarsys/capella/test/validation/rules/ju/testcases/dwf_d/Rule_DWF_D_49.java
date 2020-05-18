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
 * test on DWF_D_49: This rule checks that when a property or an association role is part of key, value of maximum
 * cardinality must be equal to one.
 * 
 * @generated
 */
public class Rule_DWF_D_49 extends AbstractRulesOnDesignTest {

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
    return "org.polarsys.capella.core.data.information.validation.DWF_D_49";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] {
        "6280241b-bb13-443a-bbbb-8a40137768c3",
        "0999b8b3-d249-4b1a-8005-e6273dbfa700",
        "dabef3c4-f512-41eb-b140-3b07451a6ff0",
        "2471fe7d-aa57-4ffa-9adb-05e5c3bcb028",
        "01b33a54-f753-4d42-8ed7-7995e4ed6ea4",
        "b4a80d5b-9b1f-4758-906c-4d4c53bd4f39"});
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] {
        new OracleDefinition("0999b8b3-d249-4b1a-8005-e6273dbfa700", 1),
        new OracleDefinition("2471fe7d-aa57-4ffa-9adb-05e5c3bcb028", 1),
        new OracleDefinition("01b33a54-f753-4d42-8ed7-7995e4ed6ea4", 1)});
  }

}
