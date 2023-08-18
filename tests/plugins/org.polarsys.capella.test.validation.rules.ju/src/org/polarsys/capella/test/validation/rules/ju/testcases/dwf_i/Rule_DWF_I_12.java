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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_I_12: This rule ensures that an ExchangeItemAllocation from given layer should not allocate element of lower Architecture layer.
 * @generated
 */
public class Rule_DWF_I_12 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.cs.validation.DWF_I_12";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"07fa7f87-ef7e-4806-a6e9-b1339d39f47e",
				"a2caba64-473e-4932-b1d3-7e9f697990ce",
				"b87cba44-3573-4fb0-98eb-a955bcfa726e",
				"ad409a58-5ada-4e8a-ba18-085e644ab7e0",
				"4b1b7c99-7088-492a-a5ba-54d1120d896a",
				"2b92bb5f-c73b-427e-ab35-8e551c4f2814",
				"a2477952-02b5-4318-bbbf-f9b7d06651f0",
				"da6e04d8-9680-497f-80fc-defa73d3ea3c" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"4b1b7c99-7088-492a-a5ba-54d1120d896a", 1),
						new OracleDefinition(
								"2b92bb5f-c73b-427e-ab35-8e551c4f2814", 1),
						new OracleDefinition(
								"a2477952-02b5-4318-bbbf-f9b7d06651f0", 1),
						new OracleDefinition(
								"da6e04d8-9680-497f-80fc-defa73d3ea3c", 1) });
	}
}
