/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
 * test on DCON_06 - REC_RPL without RPL.
 */
public class Rule_DCON_06 extends AbstractRulesOnDesignTest {
  public static final String REC1 = "e0896a8d-8547-4bdb-ab85-e901ff77baf6";
  public static final String REC2 = "a65c077c-b690-45f4-9d57-6790ac43e387";
  public static final String RPL2_REC2 = "90c5c79d-9c74-4d86-a219-2de521e7eb5d";

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   */
  @Override
  protected EClass getTargetedEClass() {
    return RePackage.Literals.CATALOG_ELEMENT;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   */
  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.re.validation.DCON_06";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   */
  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { REC1, REC2, RPL2_REC2 });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   */
  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition(REC1, 0), new OracleDefinition(REC2, 0),
        new OracleDefinition(RPL2_REC2, 1) });
  }
}
