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
 * test on DWF_DS_19: This rule checks that Sequence Messages in OAS or FS scenarios are consistent with their associated Functional Exchange.
 * @generated
 */
public class Rule_DWF_DS_19 extends AbstractRulesOnDesignTest {

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
		return "org.polarsys.capella.core.data.interaction.validation.DWF_DS_19";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"4e63b920-e4b0-418a-8170-70be8fdeff76",
				"b27c49eb-79c6-4fdf-a331-a08f5d720b0e",
				"30f29f1d-9e00-457e-8472-da628d4636bb",
				"a0d25269-c355-4970-ae21-da2453b809f7",
				"9829a946-391d-4c19-a3ae-2cf089e498a9",
				"6bfef9ce-ad9c-46bf-8a95-8799af8148a2" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"30f29f1d-9e00-457e-8472-da628d4636bb", 1),
						new OracleDefinition(
								"a0d25269-c355-4970-ae21-da2453b809f7", 1),
						new OracleDefinition(
								"9829a946-391d-4c19-a3ae-2cf089e498a9", 1),
						new OracleDefinition(
								"6bfef9ce-ad9c-46bf-8a95-8799af8148a2", 1) });
	}
}
