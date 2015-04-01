/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * test on DWF_I_03: This rule ensures that an ExchangeItemElement direction can be set only if ExchangeItem is of kind Operation.
 * @generated
 */
public class Rule_DWF_I_03 extends AbstractRulesOnDesignTest {

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
		return "org.polarsys.capella.core.data.information.validation.DWF_I_03";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"cf264a06-6749-40ec-aa69-398e90ac30d7",
				"40d0891e-c393-47b2-b87b-9f72b4f24598",
				"09d1da5d-c8c2-4ed2-b9f7-51fa65c3d8cb",
				"5bb5aae0-3b6f-438f-acda-36c84f345b5e",
				"e034326a-7cb6-4eb6-91ec-29171b6ad5e4" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"40d0891e-c393-47b2-b87b-9f72b4f24598", 1),
						new OracleDefinition(
								"09d1da5d-c8c2-4ed2-b9f7-51fa65c3d8cb", 1),
						new OracleDefinition(
								"5bb5aae0-3b6f-438f-acda-36c84f345b5e", 1),
						new OracleDefinition(
								"e034326a-7cb6-4eb6-91ec-29171b6ad5e4", 1) });
	}
}
