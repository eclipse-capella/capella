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
package org.polarsys.capella.test.validation.rules.ju.testcases.dcon;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DCON_02: This rule check if a RPL is up to date compared to its origin REC.
 */
public class Rule_DCON_02 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 */
	protected EClass getTargetedEClass() {
		return RePackage.Literals.CATALOG_ELEMENT;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.re.validation.DCON_02";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "dd886920-7aae-4486-a539-a0cde9030288", "67f00b40-b65f-43a6-a244-84a16a8a085b",
        "39cd1d3a-f734-4cab-9624-95f0a6171773", "ac3e9f1d-3120-43e2-aa5e-0e9d5f74dacb", "1e0213c0-56cb-4cd5-950a-6bc637a898ea"  });
  }

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("dd886920-7aae-4486-a539-a0cde9030288", 1),
        new OracleDefinition("67f00b40-b65f-43a6-a244-84a16a8a085b", 1),
        new OracleDefinition("eb1ad14d-b1fb-4137-9e04-b14a07495d06", 0),
        new OracleDefinition("d8835bb7-da4a-421b-be34-89c3e0591b1b", 0),
        new OracleDefinition("ac3e9f1d-3120-43e2-aa5e-0e9d5f74dacb", 1),
        new OracleDefinition("1e0213c0-56cb-4cd5-950a-6bc637a898ea", 0)

    });
  }
}
