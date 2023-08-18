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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnIntegrityTest;

/**
 * test on I_07: This rule checks that a diagram doesn't contain a naming conflict.
 * @generated
 */
public class Rule_I_07 extends AbstractRulesOnIntegrityTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//new GuiActions().openSession(_airdFile);
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return CapellacorePackage.Literals.CAPELLA_ELEMENT;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.platform.sirius.sirius.validation.I_07";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"05ba5151-e5f4-4eac-b874-c4d0c9a72e3e",
				"eaca9cfa-ce88-4c8e-9a7b-740d3624a615" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition[] { new OracleDefinition(
				"eaca9cfa-ce88-4c8e-9a7b-740d3624a615", 1) });
	}
}
