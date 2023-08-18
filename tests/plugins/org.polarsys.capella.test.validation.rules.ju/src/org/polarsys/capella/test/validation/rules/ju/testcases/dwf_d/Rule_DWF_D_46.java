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
 * test of DWF_D_46: Association container check
 * @generated
 */
public class Rule_DWF_D_46 extends AbstractRulesOnDesignTest {

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
		return "org.polarsys.capella.core.data.information.validation.DWF_D_46";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"2760a543-a119-48ce-8aaf-acefc7d614b7",
				"c51d230d-2309-4786-813d-185ad734798e",
				"2b94eea5-15ef-49b3-b3c2-87da032530d4",
				"82d95d71-304f-4d47-a6d8-32ec0ccad007",
				"00569abb-d157-46e9-8e46-6708003203e4",
				"877232f6-4642-4270-abc6-960b3d2d7e6b",
				"79bd445f-7d7a-49cb-aaff-1f27dcf3cee7",
				"c595f5dd-b55a-492c-961e-90a960cfbdd2" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"00569abb-d157-46e9-8e46-6708003203e4", 1),
						new OracleDefinition(
								"877232f6-4642-4270-abc6-960b3d2d7e6b", 1),
						new OracleDefinition(
								"79bd445f-7d7a-49cb-aaff-1f27dcf3cee7", 1),
						new OracleDefinition(
								"c595f5dd-b55a-492c-961e-90a960cfbdd2", 1) });
	}

}
