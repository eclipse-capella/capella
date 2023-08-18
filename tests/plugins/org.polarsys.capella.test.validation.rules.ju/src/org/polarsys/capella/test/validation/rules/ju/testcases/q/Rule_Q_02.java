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
package org.polarsys.capella.test.validation.rules.ju.testcases.q;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnQualityTest;

/**
 * test on Q_02: Functions, Components, Actors, Functional Exchanges and Component Exchanges must have a description.
 * @generated
 */
public class Rule_Q_02 extends AbstractRulesOnQualityTest {

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
		return "org.polarsys.capella.core.data.core.validation.Q_02";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"9e6ec893-7595-4102-8be5-8d70233ca9f2",
				"f7972dd2-f9f7-459e-a444-002c6ea436a0",
				"94d41eb5-a8fb-4093-95e7-b5b37db78b7f",
				"b86b8d62-f459-4f2e-a4ff-79275e526abd",
				"2fdd0e47-123a-4bf4-8079-e2606fe3ea3e",
				"5d6a919b-8e02-4b2d-b24a-ff7eb2b3f4d8",
				"9c8fc23f-a573-490d-817d-7e66db0992b7",
				"0f2cf92b-72d8-4267-b199-dd9a1cba37c4",
				"813482a0-9ae4-456a-be6f-123055bccddf",
				"986f9dab-a224-40b0-b83c-ab5fdd8291b3" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"5d6a919b-8e02-4b2d-b24a-ff7eb2b3f4d8", 1),
						new OracleDefinition(
								"9c8fc23f-a573-490d-817d-7e66db0992b7", 1),
						new OracleDefinition(
								"0f2cf92b-72d8-4267-b199-dd9a1cba37c4", 1),
						new OracleDefinition(
								"813482a0-9ae4-456a-be6f-123055bccddf", 1),
						new OracleDefinition(
								"986f9dab-a224-40b0-b83c-ab5fdd8291b3", 1) });
	}
}
