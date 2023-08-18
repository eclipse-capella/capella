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
package org.polarsys.capella.test.validation.rules.ju.testcases.tj_pa;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

/**
 * test on TJ_PA_11: This rule checks that a Logical Interface is realized by at least one Physical Interface.
 * @generated
 */
public class Rule_TJ_PA_11 extends AbstractRulesOnTransitionTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
	  PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
	  preferenceHelper.setBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, true);
		super.setUp();
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return CsPackage.Literals.INTERFACE;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.cs.validation.TJ_PA_11";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"799d37e8-6b75-4e81-86d6-4f83651840e6",
				"d8318b5a-6222-4ed0-8fc8-dc0ff1d75572" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition[] { new OracleDefinition(
				"d8318b5a-6222-4ed0-8fc8-dc0ff1d75572", 1) });
	}
}
