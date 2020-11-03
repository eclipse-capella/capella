/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
 * test on DWF_D_50: This rule checks that for not abstract association role and exchange item element, maximum
 * cardinality value is defined and evals to a value in [1, *[.
 * 
 * @generated
 */
public class Rule_DWF_D_50 extends AbstractRulesOnDesignTest {

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
    return "org.polarsys.capella.core.data.information.validation.DWF_D_50";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] {
        "6692aa14-d5f7-48cc-9d7a-81af7d01dcb7",
        "5719287a-5fa1-4fad-92c1-c54f79636e30",
        "e5d49350-f025-4ca2-91f2-41d132f18267",
        "4579ddd1-a64d-43df-bf43-a387aa5ec5b6",
        "3c3d7212-c793-451d-b78a-2bb8db7ccb4c",
        "1ffced5c-8549-49a7-8ed6-762ae1d78f0f",
        "f77fbc56-c320-4b65-bbae-f06af83f7e7f",
        "d6f98381-c732-40d2-87c3-8834f56bb742",
        "03799f8f-3fc5-4576-bde1-f22042555b66"});
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] {
        new OracleDefinition("5719287a-5fa1-4fad-92c1-c54f79636e30", 1),
        new OracleDefinition("4579ddd1-a64d-43df-bf43-a387aa5ec5b6", 1),
        new OracleDefinition("3c3d7212-c793-451d-b78a-2bb8db7ccb4c", 1),
        new OracleDefinition("1ffced5c-8549-49a7-8ed6-762ae1d78f0f", 1),
        new OracleDefinition("f77fbc56-c320-4b65-bbae-f06af83f7e7f", 1),
        new OracleDefinition("03799f8f-3fc5-4576-bde1-f22042555b66", 1)});
  }

}
