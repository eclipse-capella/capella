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
 * test on DWF_D_38: A reference relationship association of kind ASSOCIATION can only exist, if and only if the target class has or inherits at least one property being a key.
 * @generated
 */
public class Rule_DWF_D_38 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return InformationPackage.Literals.PROPERTY;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.DWF_D_38";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"26874bd4-eb83-472d-9777-40550e405acb",
				"60587c68-2a8e-4c14-9cf5-b643c06ae0b4",
				"0768b646-9923-49c5-af01-97da08345f43",
				"19f98008-4156-4751-9e4e-0ce49ee5cc32",
				"a81a2c8c-143a-4e96-a05a-483515734bc9",
				"b0a42627-4e24-4c2a-a8c8-597ae3c41163",
				"50bd5c8e-eb28-4583-bf7a-2098bc569dc0",
				"c20ba13e-88be-4aad-814b-1cb4ee7c9094",
				"cdd105e0-9565-431e-bdc1-1351c2e8a691",
				"057cf618-964f-4e2e-87d3-6add49a20706",
				"84983161-5c05-4829-8865-57cb66584716",
				"f5acf7e6-b934-4b51-97c8-ec91a405fff0",
				"dc678226-6071-4ab7-b5cf-8a472571ec2b",
				"bdd61541-a74a-4e29-b532-35b61ceca09e"});
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"26874bd4-eb83-472d-9777-40550e405acb", 1),
						new OracleDefinition(
								"60587c68-2a8e-4c14-9cf5-b643c06ae0b4", 1), 
						new OracleDefinition(
                "0768b646-9923-49c5-af01-97da08345f43", 1)});
	}

}
