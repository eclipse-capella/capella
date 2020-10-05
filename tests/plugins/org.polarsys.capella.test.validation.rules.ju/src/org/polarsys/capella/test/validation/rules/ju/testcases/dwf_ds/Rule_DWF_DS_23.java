/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
 * test on DWF_DS_23: Check that the interactionUse is referencing a Scenario that exists at the current Architectural Level.
 * @generated
 */
public class Rule_DWF_DS_23 extends AbstractRulesOnDesignTest {
  public static final String INTERACTIONUSE1 = "4f180381-fd81-4e33-a86f-e67d397de77a"; //$NON-NLS-1$
  public static final String INTERACTIONUSE2 = "2c5d735d-8003-4a19-8855-5da13de1b56d"; //$NON-NLS-1$
  public static final String INTERACTIONUSE3 = "edb29508-160e-43c4-bf61-bd476df7c6b2"; //$NON-NLS-1$
  public static final String INTERACTIONUSE4 = "8cbcadf3-7853-4f7d-a829-2ad268b17338"; //$NON-NLS-1$
  public static final String INTERACTIONUSE5 = "a0169aba-e8fb-4f5e-b124-768553fe7427"; //$NON-NLS-1$
  public static final String INTERACTIONUSE6 = "e772a526-17a6-4606-a3bc-2d036afe25a9"; //$NON-NLS-1$
  public static final String INTERACTIONUSE7 = "af3bac69-f24c-4d31-91ce-316b81705748"; //$NON-NLS-1$
  public static final String INTERACTIONUSE8 = "577a52d9-2b63-46d2-8c6e-dca12db3349e"; //$NON-NLS-1$
	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return InteractionPackage.Literals.INTERACTION_USE;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.interaction.validation.DWF_DS_23";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
		    INTERACTIONUSE1, INTERACTIONUSE2, INTERACTIONUSE3, INTERACTIONUSE4, INTERACTIONUSE5, INTERACTIONUSE6, INTERACTIONUSE7, INTERACTIONUSE8 });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
						    INTERACTIONUSE2, 1),
						new OracleDefinition(
						    INTERACTIONUSE4, 1),
						new OracleDefinition(
                INTERACTIONUSE5, 1),
						new OracleDefinition(
                INTERACTIONUSE6, 1),
						new OracleDefinition(
                INTERACTIONUSE7, 1)});
	}
}
