/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnIntegrityTest;

public class Rule_I_36 extends AbstractRulesOnIntegrityTest {

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return CapellacorePackage.Literals.CONSTRAINT;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.core.validation.I_36";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "f4a96328-5b2b-48b7-86b7-b636f7ce6d54", // pre/post condition of Capability
        "366eef0b-37ab-4037-a618-7c14d1f38685", // pre/post condition of Scenario
        "a4bcfd4f-5b59-4164-b141-02e20fa25dbc", // exchange context of SequenceMessage
        "6865fef6-fa31-4a6b-836f-0061929df538", // guard of InteractionOperand
        "9ca7eda0-d5c5-4b7f-a823-83987284722a", // expression of TimeEvent
        "9f5fd4d4-1273-4c5d-bbeb-e7c4e4d5b12e", // expression of ChangeEvent
        "2303f3f0-d1db-457e-af6a-f21e8a25da80", // guard of Transition
        "93134332-0e6c-43c2-a9ae-01fe8aa2ddcb", // Constraint OK, contraining an element
        "e57caeaa-086a-4c3b-8149-c42bd1b21a27" });

  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { 
        new OracleDefinition("f4a96328-5b2b-48b7-86b7-b636f7ce6d54", 1), // pre/post
        new OracleDefinition("366eef0b-37ab-4037-a618-7c14d1f38685", 1), // pre/post condition of Scenario
        new OracleDefinition("a4bcfd4f-5b59-4164-b141-02e20fa25dbc", 1), // exchange context of SequenceMessage
        new OracleDefinition("6865fef6-fa31-4a6b-836f-0061929df538", 1), // guard of InteractionOperand
        new OracleDefinition("9ca7eda0-d5c5-4b7f-a823-83987284722a", 1), // expression of TimeEvent
        new OracleDefinition("9f5fd4d4-1273-4c5d-bbeb-e7c4e4d5b12e", 1), // expression of ChangeEvent
        new OracleDefinition("2303f3f0-d1db-457e-af6a-f21e8a25da80", 1), // guard of Transition
        new OracleDefinition("93134332-0e6c-43c2-a9ae-01fe8aa2ddcb", 0), // Constraint OK, contraining an element
        new OracleDefinition("e57caeaa-086a-4c3b-8149-c42bd1b21a27", 1) }); // Constraint KO, not contraining an element
  }
}
