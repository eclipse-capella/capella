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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_d;

import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_D_35: This rule ensures that EnumerationLiteral and EnumerationReference are Typed.
 * @generated
 */
public class Rule_DWF_D_35 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return DatavaluePackage.Literals.ABSTRACT_ENUMERATION_VALUE;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.DWF_D_35";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"1521c7b7-25ca-4ded-bec5-3e5ba4b3597d",
				"a5d48b79-3077-47a6-baae-e8d301fd0fa2",
				"01d6e841-d111-4349-8f02-92db8f8187cd",
				"ec4e1750-6730-489e-be78-1290b27add32" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"01d6e841-d111-4349-8f02-92db8f8187cd", 1),
						new OracleDefinition(
								"ec4e1750-6730-489e-be78-1290b27add32", 1) });
	}

	/**
	 * @generated
	 */
	public void testRule_DWF_D_35() throws Exception {
		test();
	}
}
