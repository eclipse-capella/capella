/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_DC_34: This rule ensures that a Physical Component is not of nature UNSET.
 * @generated
 */
public class Rule_DWF_DC_34 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return PaPackage.Literals.PHYSICAL_COMPONENT;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.pa.validation.DWF_DC_34";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"08e0e107-41a8-4242-a2d8-abee39d94149",			// Physical System
				"d29fd7fa-2748-4dd7-b8b6-7f0aab34eaf6",			// PC 1 Node
				"267f3d41-85cb-441a-8bd9-7277fcbecac2",			// PC 2 Behavior
				"94d40c85-11d3-420a-a603-85955c8fb3b0" });		// PC 3 Unset
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition[] { 
				new OracleDefinition("08e0e107-41a8-4242-a2d8-abee39d94149", 1),	// Physical System has a child with 1 error
				new OracleDefinition("94d40c85-11d3-420a-a603-85955c8fb3b0", 1) });	// PC 3 Unset
	}
}
