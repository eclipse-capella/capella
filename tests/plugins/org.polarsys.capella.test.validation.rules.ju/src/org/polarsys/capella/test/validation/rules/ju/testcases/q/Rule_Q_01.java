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
 * test on Q_01: Functions, Components, Actors, Functional Exchanges and Component Exchanges must have a summary.
 * @generated
 */
public class Rule_Q_01 extends AbstractRulesOnQualityTest {

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
		return "org.polarsys.capella.core.data.core.validation.Q_01";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"0541ca5b-f2a7-4660-b9c9-aaa195c2a099",
				"961be8ee-305b-49a0-ad3f-9b2459898ac6",
				"cb6d41f7-e1bc-4ae8-be34-6c32d183d9ed",
				"b0666052-3766-4c5a-91d5-3f3fee3ccd1c",
				"f2ee15c5-a76d-437e-a697-d93c3fc9ecd2",
				"db97e533-7b70-42f0-aa3d-c0078a0aa0fe",
				"32b1ce1e-1568-4b1a-9b5f-b5fef26f7fac",
				"6504146f-34a2-46e8-bb46-43a684925b4b",
				"db3fe999-ed10-4342-83d3-85d3f9304385",
				"d559c246-8315-41a1-9011-86abc30f4849" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"db97e533-7b70-42f0-aa3d-c0078a0aa0fe", 1),
						new OracleDefinition(
								"32b1ce1e-1568-4b1a-9b5f-b5fef26f7fac", 1),
						new OracleDefinition(
								"6504146f-34a2-46e8-bb46-43a684925b4b", 1),
						new OracleDefinition(
								"db3fe999-ed10-4342-83d3-85d3f9304385", 1),
						new OracleDefinition(
								"d559c246-8315-41a1-9011-86abc30f4849", 1) });
	}
}
