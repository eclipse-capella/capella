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
 * test on DCON_04 - RPL/REC_RPL without REC.
 */
public class Rule_DCON_04 extends AbstractRulesOnDesignTest {
  private static final String RPL1 = "d1c58b57-1969-4095-9ace-d58aa24fc5b0";
  private static final String RPL2 = "f7b6eb17-f30a-45e6-ba5a-fdd8bfd0bddd";
  private static final String REC3 = "f75d086a-ef54-4c57-adba-251bcc6af134";
  private static final String RPL3 = "55a68cc7-c89c-4f0f-9e52-09be242710b0";

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
    return "org.polarsys.capella.core.re.validation.DCON_04";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   */
  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { RPL1, RPL2, REC3, RPL3 });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   */
  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition(RPL1, 1), new OracleDefinition(RPL2, 1),
        new OracleDefinition(REC3, 0), new OracleDefinition(RPL3, 0) });
  }
}
