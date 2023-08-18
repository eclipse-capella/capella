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
 * test on DWF_I_10: This rule checks that a CommunicationLink protocol are only used with a specific CommunicatinLink kind.
 * @generated
 */
public class Rule_DWF_I_10 extends AbstractRulesOnDesignTest {

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
		return "org.polarsys.capella.core.data.information.communication.validation.DWF_I_10";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"a24ca191-c596-4c00-b00a-01ffc50dc16e",
				"d8349ed0-f712-413b-9051-227e5aad8550",
				"37cb8e2a-77fa-41d2-9589-32ee8f851825",
				"45b23bd0-0501-4df6-b9f4-6bbf65936707",
				"1526643b-6a84-46cf-a10c-63c2044bdfbb",
				"bd00fb5c-1978-429f-b1bb-99a5c62c169e" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"45b23bd0-0501-4df6-b9f4-6bbf65936707", 1),
						new OracleDefinition(
								"1526643b-6a84-46cf-a10c-63c2044bdfbb", 1),
						new OracleDefinition(
								"bd00fb5c-1978-429f-b1bb-99a5c62c169e", 1) });
	}
}
