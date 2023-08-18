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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_d;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_D_47: Element shall not inherits from Final element
 * @generated
 */
public class Rule_DWF_D_47 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return CapellacorePackage.Literals.GENERALIZABLE_ELEMENT;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.core.validation.DWF_D_47";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"445cffc1-2031-4901-be88-2758e284b63b",
				"950e89e1-7a8f-4220-a260-e82439e6d94b",
				"bdd8f10f-7260-4367-88e9-4ee4b3128ba6",
				"6d90af53-e66b-4bb6-8163-dfd1b7ad7c7b",
				"b5f52eb0-df0f-48a6-8d8a-6055bca09971",
				"c72e106f-2e78-4d33-a651-b6fd346d4eaf",
				"1fae3b8f-aa1a-49a2-8369-c1bd7b526aab",
				"044d4716-ccf1-463e-83dd-9d5773e7e617",
				"b90092db-17ef-44d9-a4b1-ac0133a50e01",
				"0c499f8c-f9ae-4394-9c08-3b8aa81044cf" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"044d4716-ccf1-463e-83dd-9d5773e7e617", 1),
						new OracleDefinition(
								"b90092db-17ef-44d9-a4b1-ac0133a50e01", 1),
						new OracleDefinition(
								"0c499f8c-f9ae-4394-9c08-3b8aa81044cf", 1) });
	}

}
