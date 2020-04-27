/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase;

import junit.framework.Test;

public class Rule_DCON_01 extends ValidationRulePartialTestCase {

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   */
  protected EClass getTargetedEClass() {
    return ModellingcorePackage.Literals.MODEL_ELEMENT;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   */
  protected String getRuleID() {
    return "org.polarsys.capella.patterns.DCON_01";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "53663c63-1dc5-43e2-a130-11f6dec4868f" });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("53663c63-1dc5-43e2-a130-11f6dec4868f", 1) });
  }

  @Override
  protected String getRequiredTestModel() {
    return "DCON_01";
  }

  public static Test suite() {
    return new Rule_DCON_01();
  }
}
