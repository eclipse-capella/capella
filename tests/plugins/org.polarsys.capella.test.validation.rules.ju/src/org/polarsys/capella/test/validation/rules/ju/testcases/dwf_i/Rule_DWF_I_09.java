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
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_I_09: This rule checks that a CommunicationLink kind is only used with a specific CommunicatinLink protocol.
 * @generated
 */
public class Rule_DWF_I_09 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return CommunicationPackage.Literals.COMMUNICATION_LINK;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.communication.validation.DWF_I_09";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"610772eb-81af-4477-9b9e-b075d014841c",
				"66728cf7-d173-4a23-a5be-dc695aa258be",
				"adae3993-eb8f-4729-936e-5ac068a39596",
				"ee729103-f6f0-42b1-9b9b-2224604efae6",
				"946eeb54-fbb4-43e8-91e0-0f8052941f48",
				"53787303-f857-4979-a6e3-4ad4b674c713",
				"2c9bbf94-b34d-49dd-b91e-648b555b8f62",
				"3fae3873-139c-412e-a482-65b9789a66d1",
				"d919afbc-4712-4fe4-84bb-c2ac731c203f",
				"3b71c6e3-ab45-4223-b449-7e630c05f280",
				"32e16d2a-0b04-4a43-b012-17c0e95361c1",
				"c0bb771e-46a3-4412-b9e1-2939707bb4f8",
				"83ca7005-ea9e-44f5-be6b-dbcb1b116bae",
				"2ce4fe36-6f2d-40bc-8dd6-464a7c49681f",
				"fed3dc36-c853-4e7e-8f29-c4ae6197202e" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"53787303-f857-4979-a6e3-4ad4b674c713", 1),
						new OracleDefinition(
								"2c9bbf94-b34d-49dd-b91e-648b555b8f62", 1),
						new OracleDefinition(
								"3fae3873-139c-412e-a482-65b9789a66d1", 1),
						new OracleDefinition(
								"d919afbc-4712-4fe4-84bb-c2ac731c203f", 1),
						new OracleDefinition(
								"3b71c6e3-ab45-4223-b449-7e630c05f280", 1),
						new OracleDefinition(
								"32e16d2a-0b04-4a43-b012-17c0e95361c1", 1),
						new OracleDefinition(
								"c0bb771e-46a3-4412-b9e1-2939707bb4f8", 1),
						new OracleDefinition(
								"83ca7005-ea9e-44f5-be6b-dbcb1b116bae", 1),
						new OracleDefinition(
								"2ce4fe36-6f2d-40bc-8dd6-464a7c49681f", 1),
						new OracleDefinition(
								"fed3dc36-c853-4e7e-8f29-c4ae6197202e", 1) });
	}
}
