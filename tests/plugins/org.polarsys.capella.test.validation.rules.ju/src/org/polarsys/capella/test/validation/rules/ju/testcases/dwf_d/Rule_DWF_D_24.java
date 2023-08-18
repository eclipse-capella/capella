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
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_D_24: This rule checks that the value of the DataValue(LiteralNumericValue or LiteralStringValue) matches with the data type Regular expression. 1) value of dataValue must be set. 2) if dataValue typed, pattern should not be null.
 * @generated
 */
public class Rule_DWF_D_24 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return DatavaluePackage.Literals.DATA_VALUE;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.datatype.validation.DWF_D_24";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"d80bd394-26ff-4947-999b-4ab6ea68a712",
				"39a0d412-77fa-4fb6-9298-7ee4797b21ea",
				"a42b56fe-6987-46f2-b8c8-27b2f5020c73",
				"48c9e1c1-278c-4dcd-b0bf-4f3ac0829f63",
				"78229197-6b9b-4320-80b1-00564c992f1a",
				"a219eb44-4d40-43df-8789-9383ab29d4c1" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"a42b56fe-6987-46f2-b8c8-27b2f5020c73", 1),
						new OracleDefinition(
								"48c9e1c1-278c-4dcd-b0bf-4f3ac0829f63", 1),
						new OracleDefinition(
								"78229197-6b9b-4320-80b1-00564c992f1a", 1),
						new OracleDefinition(
								"a219eb44-4d40-43df-8789-9383ab29d4c1", 1) });
	}

}
