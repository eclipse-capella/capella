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
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test of DWF_D_44: Enumeration Literals Domain Values Constraints
 * @generated
 */
public class Rule_DWF_D_44 extends AbstractRulesOnDesignTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return DatatypePackage.Literals.ENUMERATION;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.DWF_D_44";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"e36f5324-27bb-4d51-adac-bd4370d1d62b",
				"48755c99-6e46-4a1e-bccd-ca5f1863c8d2",
				"a6a0f682-6d40-4741-a04e-577e9865e728",
				"3b84dc6c-f6e8-4779-85a8-f69ba60f39ba",
				"748ffe94-cd5d-4d3f-a2e0-857e5bb78b49",
				"72fc0a0c-ad00-4ba2-a11f-83fc0da68929",
				"12034014-1d5a-456d-b284-2fd1df146212",
				"b54d9456-3dc0-4015-a356-dddb7818e852",
				"bd1c8649-c613-4caa-87b4-7e597603045c",
				"5bc7eb7e-6eda-46ea-b388-0eaf1130fa48",
				"85015764-7e43-4315-8299-ee9640212dd3",
				"aabfcbce-2641-4622-bbba-3e72f9f4f768",
				"8a28f72e-97a8-40a4-8e61-4c97e0283dbb",
				"cf77d15e-7c15-4640-b6bb-f1bd78171f1b",
				"6223987b-1982-41a6-bc25-35e0db85d467",
				"d6a06948-003e-4fd8-b636-1804cce33541",
				"203c603d-3106-4962-9b6c-8ea116ad0b9c",
				"35a2da86-1bca-4d55-bb7f-fe0822c16864",
				"14305881-a3ec-4b07-8e0e-0c320a989e49",
				"a7ccfa96-0bcb-4998-9666-2072eaff3ea7",
				"5db5c625-a718-4582-9048-5d9b66484434" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"aabfcbce-2641-4622-bbba-3e72f9f4f768", 1),
						new OracleDefinition(
								"8a28f72e-97a8-40a4-8e61-4c97e0283dbb", 1),
						new OracleDefinition(
								"cf77d15e-7c15-4640-b6bb-f1bd78171f1b", 1),
						new OracleDefinition(
								"6223987b-1982-41a6-bc25-35e0db85d467", 1),
						new OracleDefinition(
								"d6a06948-003e-4fd8-b636-1804cce33541", 1),
						new OracleDefinition(
								"203c603d-3106-4962-9b6c-8ea116ad0b9c", 1),
						new OracleDefinition(
								"35a2da86-1bca-4d55-bb7f-fe0822c16864", 1),
						new OracleDefinition(
								"14305881-a3ec-4b07-8e0e-0c320a989e49", 1),
						new OracleDefinition(
								"a7ccfa96-0bcb-4998-9666-2072eaff3ea7", 1),
						new OracleDefinition(
								"5db5c625-a718-4582-9048-5d9b66484434", 1) });
	}

}
