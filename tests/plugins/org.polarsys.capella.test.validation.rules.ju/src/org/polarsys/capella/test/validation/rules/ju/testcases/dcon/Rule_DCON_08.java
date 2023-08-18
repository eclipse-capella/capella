/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dcon;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * 
 * Test on DCON_08 - REC/RPL shall detect no source no target
 *
 */
public class Rule_DCON_08 extends AbstractRulesOnDesignTest {

  public static final String REC1_VALID_LINK = "672f9d39-193b-4556-a4dd-ebd91b75105d";
  public static final String REC1_NO_SOURCE_NO_TARGET_LINK = "2d94d284-b519-4061-b0ce-4ae5a4e95797";
  public static final String REC1_NO_TARGET_LINK = "2dde13a5-aca9-4a47-8637-dafe969a1603";
  public static final String REC1_NO_SOURCE_LINK = "6b688e68-2bbe-4b4d-8380-4fb7e4348bca";

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   */
  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(REC1_VALID_LINK, REC1_NO_SOURCE_NO_TARGET_LINK, REC1_NO_TARGET_LINK, REC1_NO_SOURCE_LINK);
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   */
  @Override
  protected EClass getTargetedEClass() {
    return RePackage.Literals.CATALOG_ELEMENT_LINK;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   */
  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.re.validation.DCON_08";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   */
  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition(REC1_VALID_LINK, 0),
        new OracleDefinition(REC1_NO_SOURCE_NO_TARGET_LINK, 1), new OracleDefinition(REC1_NO_TARGET_LINK, 1),
        new OracleDefinition(REC1_NO_SOURCE_LINK, 1));
  }

}
