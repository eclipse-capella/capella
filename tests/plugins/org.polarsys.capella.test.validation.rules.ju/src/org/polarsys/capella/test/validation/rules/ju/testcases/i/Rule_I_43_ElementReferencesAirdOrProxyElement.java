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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * 
 * Test on I_43 - Element shall not reference to aird element
 *
 */
public class Rule_I_43_ElementReferencesAirdOrProxyElement extends AbstractRulesOnDesignTest {

  public static final String VALID_LINK = "f5f3bd95-42c6-44ee-b4f7-4b03363d320a";
  public static final String LINK_ON_AIRD_ELEMENT = "4baca61d-d6d5-4a61-9754-493b201b74e9";

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   */
  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(VALID_LINK, LINK_ON_AIRD_ELEMENT);
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
    return "org.polarsys.capella.core.re.validation.I_43";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   */
  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition(VALID_LINK, 0), new OracleDefinition(LINK_ON_AIRD_ELEMENT, 1));
  }

}
