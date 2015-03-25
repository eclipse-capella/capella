/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * test on DWF_DS_18: This rule checks that Sequence Messages in OES or ES scenarios are consistent with their associated Component Exchange or Functional Exchange.
 * @generated
 */
public class Rule_DWF_DS_18 extends AbstractRulesOnDesignTest {

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
		return "org.polarsys.capella.core.data.interaction.validation.DWF_DS_18";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"22842940-cc2a-4e26-8541-59f4e40dd34f",
				"5009483d-6057-4abf-b42f-07727ef7f2a7",
				"7af4920c-e87f-42b4-a942-945b2a377058",
				"ba081e4c-0e93-4957-a181-863ddcedc5b8",
				"9a0dafb6-af13-49e7-b6a2-c3b275c0ed78",
				"68cb4eda-4d4f-4a4b-bb69-3964d0b51501",
				"11eafbd9-aeda-4354-932f-79592cae6f22",
				"30004fd2-52dd-4599-81c7-234209fe83b0" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"9a0dafb6-af13-49e7-b6a2-c3b275c0ed78", 1),
						new OracleDefinition(
								"68cb4eda-4d4f-4a4b-bb69-3964d0b51501", 1),
						new OracleDefinition(
								"11eafbd9-aeda-4354-932f-79592cae6f22", 1),
						new OracleDefinition(
								"30004fd2-52dd-4599-81c7-234209fe83b0", 1) });
	}

	/**
	 * @generated
	 */
	public void testRule_DWF_DS_18() throws Exception {
		test();
	}
}
