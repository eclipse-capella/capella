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
 * test on DWF_DS_10: This rule checks the consistency between Scenario kind and Level: On OA level, only INTERACTION Scenarios are allowed, On SYS/LC/PC levels, only DATA_FLOW, INTERFACE and FUNCTIONAL Scenarii are allowed, On EPBS level, only INTERFACE Scenarios are allowed. Whatever the level, UNSET Scenarios raise an error. NB: When migrating the model, Unset scenarii become Interface scenarii. Change them manually
 * @generated
 */
public class Rule_DWF_DS_10 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return InteractionPackage.Literals.SCENARIO;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.interaction.validation.DWF_DS_10";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"4d643a00-e32d-4a20-ab1e-fb13bfd7b451",
				"41f108cb-8815-4a9e-9a08-efe58b2a4625",
				"f1c8b222-7a73-4258-9344-2a4ba3127f11",
				"1c23564f-6ddf-4848-afaa-587db2e8a012",
				"3a52e990-7c2b-4df3-98db-a8c8b0cee533",
				"f2c04951-2789-4a33-9cc1-39c909fb03d8",
				"b8fd4308-8fb2-4425-ac90-aee007d37f25",
				"9f6ad702-6a4c-4f70-9f2d-0d99491e9912",
				"64127175-3930-47f3-96df-342fc4fb9590",
				"1afc4f7f-9635-4263-b43f-213ec44fad39",
				"afb1ec22-58c2-4edb-b9a8-0aa4637d85dd",
				"c2b2ef3b-cc0f-4e49-afae-197ad309fa5a",
				"724cb6dc-ed4a-4979-a308-fa20ede0f8e0",
				"85aae672-86a5-4a3e-b905-ac46a9ba2a08",
				"3f9a12cc-dcff-4483-896d-6985b2e88d54",
				"5031bad6-08be-4685-81c4-d3ec28095735",
				"edb1ddf4-1b17-43ca-a4b3-9631a4992fc1",
				"8438bad9-2030-4951-a36a-1ce1a9f4a16d",
				"00f7bbc4-8ed4-426d-bbb9-a2bbf80af5be",
				"573adee6-ef60-46bf-82ae-44d272199e7c",
				"0eb851e5-6dd8-40de-a5c5-cedb1d735d24",
				"56ee8348-0d5a-4eaf-99f8-29a1f3567f93",
				"506049e4-7369-4f59-a71e-365739d11b3b",
				"4e2eb0f3-96ce-4cd9-8ddc-b839834d006d",
				"302aeb39-fc4a-4fa3-95a6-8cdadfbd2c1d" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"c2b2ef3b-cc0f-4e49-afae-197ad309fa5a", 1),
						new OracleDefinition(
								"724cb6dc-ed4a-4979-a308-fa20ede0f8e0", 1),
						new OracleDefinition(
								"85aae672-86a5-4a3e-b905-ac46a9ba2a08", 1),
						new OracleDefinition(
								"3f9a12cc-dcff-4483-896d-6985b2e88d54", 1),
						new OracleDefinition(
								"5031bad6-08be-4685-81c4-d3ec28095735", 1),
						new OracleDefinition(
								"edb1ddf4-1b17-43ca-a4b3-9631a4992fc1", 1),
						new OracleDefinition(
								"8438bad9-2030-4951-a36a-1ce1a9f4a16d", 1),
						new OracleDefinition(
								"00f7bbc4-8ed4-426d-bbb9-a2bbf80af5be", 1),
						new OracleDefinition(
								"573adee6-ef60-46bf-82ae-44d272199e7c", 1),
						new OracleDefinition(
								"0eb851e5-6dd8-40de-a5c5-cedb1d735d24", 1),
						new OracleDefinition(
								"56ee8348-0d5a-4eaf-99f8-29a1f3567f93", 1),
						new OracleDefinition(
								"506049e4-7369-4f59-a71e-365739d11b3b", 1),
						new OracleDefinition(
								"4e2eb0f3-96ce-4cd9-8ddc-b839834d006d", 1),
						new OracleDefinition(
								"302aeb39-fc4a-4fa3-95a6-8cdadfbd2c1d", 1) });
	}
}
