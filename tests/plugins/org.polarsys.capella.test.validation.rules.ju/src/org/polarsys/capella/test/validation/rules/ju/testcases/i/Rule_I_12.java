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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnIntegrityTest;

/**
 * test on I_12: This test checks the realization consistency between functions.
 * @generated
 */
public class Rule_I_12 extends AbstractRulesOnIntegrityTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return FaPackage.Literals.ABSTRACT_FUNCTION;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.fa.validation.I_12";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"7a506cb5-f8ac-4786-aaa2-58502aca14b5",
				"93ffdb60-a88d-433c-a56d-3417f2402283",
				"b02c9379-528a-45db-9fec-5a3fb1982b43",
				"80a27dd5-d928-485b-95f8-0ca078ae5877",
				"d659579e-bb45-4568-84e3-2801f19bbfa4",
				"49abf6aa-fad7-4d0d-aab0-686d902c59b6" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"80a27dd5-d928-485b-95f8-0ca078ae5877", 1),
						new OracleDefinition(
								"d659579e-bb45-4568-84e3-2801f19bbfa4", 1),
						new OracleDefinition(
								"49abf6aa-fad7-4d0d-aab0-686d902c59b6", 1) });
	}
}
