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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_dc;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_DC_32: This rule ensures that ComponentExchange with out port sourceEnd or target end is not NodePC.
 * @generated
 */
public class Rule_DWF_DC_32 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return FaPackage.Literals.COMPONENT_EXCHANGE;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.fa.validation.DWF_DC_32";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"7dcdc078-6d45-426c-94d8-06eafa9cf637",
				"fd4fa587-8d9a-4c0b-928e-175d654d20e6",
				"fd19a528-cd7d-4cce-a4b2-28ebbf713385",
				"79a31ee9-2bd4-40fb-9709-533a8c38e3e1",
				"68783610-0123-475c-999a-daa56f3d3adf",
				"f547c7fc-0956-4ae0-8d74-fd795a250a52" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"fd4fa587-8d9a-4c0b-928e-175d654d20e6", 1),
						new OracleDefinition(
								"fd19a528-cd7d-4cce-a4b2-28ebbf713385", 1),
						new OracleDefinition(
                "68783610-0123-475c-999a-daa56f3d3adf", 1)});
	}
}
