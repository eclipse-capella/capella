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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_df;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_DF_12: A duplicate, a gather, a route, a select or a split function must be a leaf function.
 * @generated
 */
public class Rule_DWF_DF_12 extends AbstractRulesOnDesignTest {

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
		return "org.polarsys.capella.core.data.fa.validation.DWF_DF_12";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"90914fec-6a02-471b-9efe-a4c9cc8ab3db",
				"443f0b8a-e25d-4ae8-ab9a-ca03e350731f",
				"b8bbf663-64fb-4f51-816b-9e9b45849966",
				"c29a545d-13d4-469d-b8bb-7b5663bfa293",
				"00bd9fc3-4485-46bd-8adf-a19f01290693",
				"8debb471-8334-4bfb-a4d5-4b3a03ccd749",
				"275154bf-c3c5-453b-9e37-34abadf72f04",
				"de773ee5-5cb8-482d-a8e2-17b7eba5fe47",
				"8d64d021-c4c5-4a79-87fb-a7beaa16a9eb",
				"d237ef3d-e826-42a3-8814-e5d766bf1e3c" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"8debb471-8334-4bfb-a4d5-4b3a03ccd749", 1),
						new OracleDefinition(
								"275154bf-c3c5-453b-9e37-34abadf72f04", 1),
						new OracleDefinition(
								"de773ee5-5cb8-482d-a8e2-17b7eba5fe47", 1),
						new OracleDefinition(
								"8d64d021-c4c5-4a79-87fb-a7beaa16a9eb", 1),
						new OracleDefinition(
								"d237ef3d-e826-42a3-8814-e5d766bf1e3c", 1) });
	}
}
