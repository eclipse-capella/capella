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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_I_01: Checks whether the ExchangeItemElements of an ExchangeItem are consistent with applied exchange mechanism: - Elements of an ExchangeItem with exchange mechanism OPERATION must be of kind PARAMETER - For all other exchange mechanisms, elements must be of kind TYPE
 * @generated
 */
public class Rule_DWF_I_01 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return InformationPackage.Literals.EXCHANGE_ITEM;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.DWF_I_01";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"ffd170ae-b485-4dde-919b-af46428d8775",
				"b613d6ff-4ff9-44c6-a2f3-e49757fd0ed1",
				"6c98b672-4dc5-4ae6-b84b-b86e53736b4c",
				"549ec04c-367f-45b8-84e5-523418060023",
				"86293f4f-b35e-4f37-ac10-39a13a3344a7",
				"5169e272-82b3-4e78-93d8-6771ef54f0aa",
				"33c53f08-3b0c-4bef-a2c8-86a84e329c4e",
				"08689991-c6d6-473f-aa26-38dfe22f2bec",
				"81f1d393-7453-46a0-9192-b48c58f48eab",
				"950d1fa5-5949-4e5f-b7a1-ae0cf91e37d1" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"5169e272-82b3-4e78-93d8-6771ef54f0aa", 1),
						new OracleDefinition(
								"33c53f08-3b0c-4bef-a2c8-86a84e329c4e", 1),
						new OracleDefinition(
								"08689991-c6d6-473f-aa26-38dfe22f2bec", 1),
						new OracleDefinition(
								"81f1d393-7453-46a0-9192-b48c58f48eab", 1),
						new OracleDefinition(
								"950d1fa5-5949-4e5f-b7a1-ae0cf91e37d1", 1) });
	}
}
