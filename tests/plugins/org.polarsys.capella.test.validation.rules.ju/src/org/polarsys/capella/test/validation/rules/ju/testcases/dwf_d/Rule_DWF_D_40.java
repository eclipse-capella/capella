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
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_D_40: This rule ensures that an Association from given layer should not associate with elements of lower Architecture layer.
 * @generated
 */
public class Rule_DWF_D_40 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return InformationPackage.Literals.ASSOCIATION;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.DWF_D_40";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"f8d805eb-4256-4190-81d3-16cee7bbd624",
				"db499d09-05af-46ff-8a6f-28e2086c8e20",
				"64499776-de75-409f-abc9-91e7899fe896",
				"ee4cddd7-be6c-48cc-85ba-bb54be2a773b",
				"19c6e872-bf4d-498f-b0bb-ceab29b767ee",
				"1931684e-cca2-46ef-addb-1fa4abdf912f",
				"d288c252-da46-41f7-9cd8-0f281ad2fc45",
				"86967a3d-2f7e-4a83-9ece-ddbddd7f2887" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"19c6e872-bf4d-498f-b0bb-ceab29b767ee", 1),
						new OracleDefinition(
								"1931684e-cca2-46ef-addb-1fa4abdf912f", 1),
						new OracleDefinition(
								"d288c252-da46-41f7-9cd8-0f281ad2fc45", 1),
						new OracleDefinition(
								"86967a3d-2f7e-4a83-9ece-ddbddd7f2887", 1) });
	}

}
