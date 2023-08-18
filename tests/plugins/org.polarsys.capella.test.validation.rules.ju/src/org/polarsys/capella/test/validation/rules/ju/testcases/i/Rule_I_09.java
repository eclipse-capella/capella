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
 * test on I_09: This rule ensures that an element doesn't contain a naming conflict. This rule only applies to exchanges (Component Exchanges, Physical Links and Functional Exchanges) which have the same source (Component / Function) and the same target (Component / Function).
 * @generated
 */
public class Rule_I_09 extends AbstractRulesOnIntegrityTest {

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
		return "org.polarsys.capella.core.data.core.validation.I_09";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"4ea997ff-3e48-45fd-8b4d-81730b314d44",
				"ff52d7cd-cc12-4921-983f-ca724f248656",
				"a5a84398-893b-4910-b215-ce0d0a3007c2",
				"bd768329-baf0-4ea0-90b4-463437d9f21a",
				"3887413a-929a-4c5e-8213-e22299fd8807",
				"4925eca1-181e-4987-a535-6a8c86e5597d" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"bd768329-baf0-4ea0-90b4-463437d9f21a", 1),
						new OracleDefinition(
								"3887413a-929a-4c5e-8213-e22299fd8807", 1),
						new OracleDefinition(
								"4925eca1-181e-4987-a535-6a8c86e5597d", 1) });
	}
}
