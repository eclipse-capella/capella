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
  public static final String INTERACTIONUSE1 = "afd22474-4ed2-415f-8291-7320bdf06e13"; //$NON-NLS-1$
  public static final String INTERACTIONUSE2 = "159aa1e3-db08-4b77-af79-e78056a0741c"; //$NON-NLS-1$
  public static final String INTERACTIONUSE3 = "22f6d356-c750-4968-b7b3-a92f013b9d7c"; //$NON-NLS-1$
  public static final String INTERACTIONUSE4 = "0214909e-b008-4b46-a371-6a4dcff2d215"; //$NON-NLS-1$
  public static final String INTERACTIONUSE5 = "2cefd903-fbaf-464a-9e20-278c50a9c322"; //$NON-NLS-1$
  public static final String INTERACTIONUSE6 = "47230179-beb9-43c0-b889-f6c701a34145"; //$NON-NLS-1$
  public static final String INTERACTIONUSE7 = "daeedd48-b2cc-42ce-a430-5a42eac305db"; //$NON-NLS-1$
  public static final String INTERACTIONUSE8 = "439ff812-4739-4d08-878b-94e64806cc4c"; //$NON-NLS-1$
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
						    INTERACTIONUSE3, 1),
						new OracleDefinition(
                INTERACTIONUSE4, 1),
						new OracleDefinition(
                INTERACTIONUSE6, 1),
						new OracleDefinition(
                INTERACTIONUSE8, 1)});
	}
}
