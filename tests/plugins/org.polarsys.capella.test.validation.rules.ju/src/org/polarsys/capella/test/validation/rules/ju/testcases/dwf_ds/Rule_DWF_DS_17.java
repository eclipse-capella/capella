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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_ds;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_DS_17:
 * @generated
 */
public class Rule_DWF_DS_17 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return InteractionPackage.Literals.SEQUENCE_MESSAGE;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.interaction.validation.DWF_DS_17";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"31e3a0fb-a5c5-44e8-9530-68a6f240f91c",
				"b885f2a4-3a77-404a-a20c-4fcbcdb9d428",
				"0e28688f-acef-43ae-a890-84ea37f98837",
				"b9d1a6c2-5edd-4516-ba45-1aa988aa5c2c",
				"d7905d17-5c95-4762-a428-589601d00dc1",
				"6bbc326d-b4d9-4ff3-afd0-fead4e3df783",
				"5048080f-d033-48db-92e6-4b2b9908e0a5",
				"529adff2-eecb-461f-8077-c365d1f3413a",
				"a3bb483d-b98e-4cd0-bea1-82cf8a6b5381" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"b9d1a6c2-5edd-4516-ba45-1aa988aa5c2c", 1),
						new OracleDefinition(
								"d7905d17-5c95-4762-a428-589601d00dc1", 1),
						new OracleDefinition(
								"6bbc326d-b4d9-4ff3-afd0-fead4e3df783", 1),
						new OracleDefinition(
								"5048080f-d033-48db-92e6-4b2b9908e0a5", 1),
						new OracleDefinition(
								"529adff2-eecb-461f-8077-c365d1f3413a", 1),
						new OracleDefinition(
								"a3bb483d-b98e-4cd0-bea1-82cf8a6b5381", 1) });
	}
}
