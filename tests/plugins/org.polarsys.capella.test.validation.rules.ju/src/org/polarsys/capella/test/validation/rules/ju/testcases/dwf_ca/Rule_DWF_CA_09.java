/*******************************************************************************
 * Copyright (c) 2019, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_ca;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_CA_09: This rule checks that if the current capability has related operational processes or functionals
 * chains that are not involved with the capability.
 * 
 * @generated
 */
public class Rule_DWF_CA_09 extends AbstractRulesOnDesignTest {

  public static final String SYSTEM_CAPABILITY_1 = "4c4eb558-8251-44a9-9f06-9bbd20e3317e";
  public static final String SYSTEM_CAPABILITY_2 = "641da578-f417-4dc4-a675-94fb924d2050";
  public static final String SYSTEM_CAPABILITY_3 = "5beef70b-8798-46b6-97d4-9217bd29ba62";
  public static final String SYSTEM_CAPABILITY_4 = "86ff71a4-dc57-4584-a2d6-414d1f2a94b9";

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.interaction.validation.DWF_CA_09";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    // data to validate
    return Arrays.asList(new String[] { SYSTEM_CAPABILITY_1, SYSTEM_CAPABILITY_2, SYSTEM_CAPABILITY_3, SYSTEM_CAPABILITY_4 });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition(SYSTEM_CAPABILITY_1, 1),
        new OracleDefinition(SYSTEM_CAPABILITY_2, 0),
        new OracleDefinition(SYSTEM_CAPABILITY_3, 1),
        new OracleDefinition(SYSTEM_CAPABILITY_4, 1),});
  }

}
