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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_uc;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_UC_02: This rule checks that OperationalCapability/Capability/CapabilityRealization element is realized by below layer.
 * @generated
 */
public class Rule_DWF_UC_02 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return InteractionPackage.Literals.ABSTRACT_CAPABILITY;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.interaction.validation.DWF_UC_02";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"90c70ede-c7b7-418f-83db-8bee26811ae5",
				"d7a81976-8520-40f7-aad8-43b0b933846a",
				"2b0ffc9c-af22-4077-87c7-675f8ae86f08",
				"af889162-a3f2-45f2-bc0c-1951e76c7f35",
				"3b4d4d8d-cf17-4f21-afac-2f79f91e61c5",
				"4e058d4b-d0e3-4610-9a61-cea0b2089bc9" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"af889162-a3f2-45f2-bc0c-1951e76c7f35", 1),
						new OracleDefinition(
								"3b4d4d8d-cf17-4f21-afac-2f79f91e61c5", 1),
						new OracleDefinition(
								"4e058d4b-d0e3-4610-9a61-cea0b2089bc9", 1) });
	}
}
