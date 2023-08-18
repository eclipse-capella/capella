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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_I_08: This rule checks that a CommunicationLink kind is only used with a specific CommunicatinLink protocol.
 * @generated
 */
public class Rule_DWF_I_08 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#setUp()
	 * @generated
	 */
	@Override
  protected void setUp() throws Exception {
		super.setUp();
		PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
    preferenceHelper.setBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, true);
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#tearDown()
	 * @generated
	 */
	@Override
  protected void tearDown() throws Exception {
		super.tearDown();
		PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
    preferenceHelper.setBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, false);
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
  protected EClass getTargetedEClass() {
		return FaPackage.Literals.COMPONENT_PORT;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
  protected String getRuleID() {
		return "org.polarsys.capella.core.data.fa.validation.DWF_I_08";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
  protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"020f1ae5-ecb0-4c5a-954a-2bc2406adf75",
				"a80f5ef0-92bb-4854-bdd1-74089d8c9b54",
				"a093de07-6596-4475-be01-116b8d6a63a8",
				"746557a0-c331-443b-8874-3aacfdf93c34",
				"fe8b9e16-40c0-46b2-9c21-7c8e9a27b359",
				"e9406dab-051c-4ab3-8ab7-a15282728586",
				"c950e2db-f260-410d-8722-ccc17fcadb1a",
				"95e6e646-14e4-4768-9a78-ce50b44afe5b" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
  protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"c950e2db-f260-410d-8722-ccc17fcadb1a", 1),
						new OracleDefinition(
								"95e6e646-14e4-4768-9a78-ce50b44afe5b", 1) });
	}
}
