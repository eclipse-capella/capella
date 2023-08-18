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
package org.polarsys.capella.test.validation.rules.ju.testcases.tc_df;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

/**
 * test on TC_DF_09: The rule ensure that Funtion of kind ROUTE and SELECT can only have Condition set.
 * @generated
 */
public class Rule_TC_DF_09 extends AbstractRulesOnTransitionTest {

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
		return "org.polarsys.capella.core.data.fa.validation.TC_DF_09";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"9d80003f-740d-43b8-8b60-d65b0a5494f5",
				"10b715b4-6e5e-4f7f-bddc-2e5c9dd3fbe6",
				"738a24fa-c491-488e-8a87-4f865dd0e3cc",
				"35e67699-91a2-4da5-b3c1-9173694d4035",
				"78081497-fc24-4622-80ff-309e69b75a36",
				"79fb5e9a-71c4-4835-b24e-58dd91c7cad8" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"738a24fa-c491-488e-8a87-4f865dd0e3cc", 1),
						new OracleDefinition(
								"35e67699-91a2-4da5-b3c1-9173694d4035", 1),
						new OracleDefinition(
								"78081497-fc24-4622-80ff-309e69b75a36", 1),
						new OracleDefinition(
								"79fb5e9a-71c4-4835-b24e-58dd91c7cad8", 1) });
	}
}
