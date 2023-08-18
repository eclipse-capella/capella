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
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_D_43: this rule check that for an Exchange Item with none Operation Exchange Mechanisme, the inner Exchange Item Element shall NOT have parameter direction set and in case of an Exchange Item Operation Exchange Mechanisme, all inner Exchanges Items Elements shall have parameter a direction set
 * @generated
 */
public class Rule_DWF_D_43 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.DWF_D_43";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"30bbf4f4-7a70-4bfe-b741-0eef8ea9f1f6",
				"ef9d4df3-38ba-44ad-a08a-37f9a99a2b6e",
				"1cecb2d3-6e20-4055-a5b7-481219d51290",
				"3d009001-7e07-4c7a-be71-fd1e5bbe05af" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"1cecb2d3-6e20-4055-a5b7-481219d51290", 1),
						new OracleDefinition(
								"3d009001-7e07-4c7a-be71-fd1e5bbe05af", 1) });
	}

}
