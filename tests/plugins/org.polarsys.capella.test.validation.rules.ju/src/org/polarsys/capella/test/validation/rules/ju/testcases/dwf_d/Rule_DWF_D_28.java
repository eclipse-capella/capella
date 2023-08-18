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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_d;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_D_28: This rule ensures that DataValue Reference referenced value is named.
 * @generated
 */
public class Rule_DWF_D_28 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return DatavaluePackage.Literals.DATA_VALUE;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.DWF_D_28";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"bd6cdd93-46bf-4196-a1f8-5ec875e515d3",
				"354296da-be0c-415a-b476-ac0e84731721",
				"337b2964-de3b-4028-88a2-33b0b1bc16a0",
				"67c1571b-01a5-42b1-8108-e2a7d39a5f6a",
				"bc79995e-45d5-45f6-863a-a54e4ccb0003",
				"ddd17795-f914-4629-8968-b56d94a82dae",
				"45d5d856-5d5a-4309-90ea-37c903aa103b",
				"26546641-cf6d-4398-8b88-94e437b3e140",
				"69f771e9-2696-4f87-8970-e4a589106082",
				"52ba1411-039e-4919-897d-2398b58548b5" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"ddd17795-f914-4629-8968-b56d94a82dae", 1),
						new OracleDefinition(
								"45d5d856-5d5a-4309-90ea-37c903aa103b", 1),
						new OracleDefinition(
								"26546641-cf6d-4398-8b88-94e437b3e140", 1),
						new OracleDefinition(
								"69f771e9-2696-4f87-8970-e4a589106082", 1),
						new OracleDefinition(
								"52ba1411-039e-4919-897d-2398b58548b5", 1) });
	}

}
