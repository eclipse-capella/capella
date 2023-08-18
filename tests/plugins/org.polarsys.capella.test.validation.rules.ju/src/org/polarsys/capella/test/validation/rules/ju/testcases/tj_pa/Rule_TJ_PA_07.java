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
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

/**
 * test on TJ_PA_07: This rule checks that a Physical Component implements / uses / provides / requires an interface of its own level. This rule is only evaluated if the transition preference"Transit Interfaces handled by Components from context/logical layers to physical architecture" is enabled.
 * @generated
 */
public class Rule_TJ_PA_07 extends AbstractRulesOnTransitionTest {

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
		return PaPackage.Literals.PHYSICAL_COMPONENT;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.pa.validation.TJ_PA_07";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"13f3ca3f-90bd-4083-b5aa-a7186e9779f3",
				"6a97bb38-ba91-4572-a3f6-03ac6d1b1a39",
				"de76c92b-adf6-4d88-80a6-c50a325392c4",
				"66a4763d-3f75-4ebf-9772-af7a873b6b81",
				"1c4a7101-c020-44e3-ad73-27c2a1faaecd",
				"409f2aae-bd77-4977-acf6-f95247ff3dd8",
				"103e611f-25b7-464b-8686-fdfc256ce103",
				"884fdecb-d118-4f59-8619-38b7bc7c983a" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"1c4a7101-c020-44e3-ad73-27c2a1faaecd", 1),
						new OracleDefinition(
								"409f2aae-bd77-4977-acf6-f95247ff3dd8", 1),
						new OracleDefinition(
								"103e611f-25b7-464b-8686-fdfc256ce103", 1),
						new OracleDefinition(
								"884fdecb-d118-4f59-8619-38b7bc7c983a", 1) });
	}
}
