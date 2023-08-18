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
 * test on TC_DF_06: This rule generates a warning if a bound of FunctionalExchange isn't defined in a function or a sub-function of the function containing the bound which has been realized. A warning can be raised if you have performed a drag and drop of the bound to another function but this function is not a sub-function realizing the function containing the bound which has been realized and there is no traceability link from the current function and the function containing the bound which has been realized. You should add manually a traceability link from the parent function of the realized bound and the function containing the bound which has been realized.
 * @generated
 */
public class Rule_TC_DF_06 extends AbstractRulesOnTransitionTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.fa.validation.TC_DF_06";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"09138dc0-9004-4853-9c82-0b4498b5a964",
				"64fb39be-70c9-43f0-acf3-efb89d39bc55" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition[] { new OracleDefinition(
				"64fb39be-70c9-43f0-acf3-efb89d39bc55", 1) });
	}
}
