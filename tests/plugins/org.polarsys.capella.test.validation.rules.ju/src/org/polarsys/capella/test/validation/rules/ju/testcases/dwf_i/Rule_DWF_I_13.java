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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_I_13: This rule ensures that a component doesn't use an interface/ei defined outside its namespace
 * 
 * @generated
 */
public class Rule_DWF_I_13 extends AbstractRulesOnDesignTest {

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return ModellingcorePackage.Literals.MODEL_ELEMENT;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.cs.validation.DWF_I_13";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "f7467d27-5060-43b2-9750-d154fa0cec67", "3597933b-a6fe-4529-9de1-c5e1ffb4aa60",
        "20f380dc-73e0-4fb8-b8da-118bbd602ef9", "1ad6ba6f-9b68-4588-b2ef-ca55a7e3edc9",
        "33ca8369-0332-483c-97e2-11de1927e518", "ab9eba86-a4fd-48f2-bb11-e53c3284f9ad",
        "a975b8b6-590e-40b0-845c-3c75d2e2b281", "7a72dd1d-8f72-4600-bd91-13a2f3e6d927",
        "51c5a0a1-9fb5-438f-8551-bc47d3162536", "15740476-b577-4121-9082-ab3390370249",
        "0b7f74d6-3626-4b73-aeaf-580ac4302d7f", "5a585996-abb1-4396-b10f-bf320011578a",
        "041168ba-40f8-4c49-97bb-01ce0ebb9c3a", "6795b87f-2d1f-4651-b23f-7d3882fd44f7",
        "0f7e34a1-be83-4ffc-916c-70edda8c677b" });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition("a975b8b6-590e-40b0-845c-3c75d2e2b281", 1),
        new OracleDefinition("7a72dd1d-8f72-4600-bd91-13a2f3e6d927", 1),
        new OracleDefinition("51c5a0a1-9fb5-438f-8551-bc47d3162536", 1),
        new OracleDefinition("15740476-b577-4121-9082-ab3390370249", 1),
        new OracleDefinition("0b7f74d6-3626-4b73-aeaf-580ac4302d7f", 1),
        new OracleDefinition("5a585996-abb1-4396-b10f-bf320011578a", 1),
        new OracleDefinition("041168ba-40f8-4c49-97bb-01ce0ebb9c3a", 1),
        new OracleDefinition("6795b87f-2d1f-4651-b23f-7d3882fd44f7", 1),
        new OracleDefinition("0f7e34a1-be83-4ffc-916c-70edda8c677b", 1));
  }
}
