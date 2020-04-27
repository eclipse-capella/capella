/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_ca;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_CA_08: This rule checks that if the current Capability has actors that are not involved but are present
 * in at least one operational process or functional chain or scenario related to the capability.
 * 
 * @generated
 */
public class Rule_DWF_CA_08 extends AbstractRulesOnDesignTest {
  public static final String OPERATIONAL_CAPABILITY_1 = "831081af-11ba-43dd-89e0-7fa2aa2edfe5";
  public static final String OPERATIONAL_CAPABILITY_2 = "c58d4273-da3a-441f-abd5-0e1ed9418219";
  public static final String OPERATIONAL_CAPABILITY_3 = "e1cd5069-cd7b-42ba-9b08-e0c616fa166c";

  public static final String SYSTEM_CAPABILITY_1 = "83fece9f-c799-4c84-88e7-753df849d6b7";
  public static final String SYSTEM_CAPABILITY_2 = "46fa1352-22cd-42e7-b3d2-f44a2a65d07e";
  public static final String SYSTEM_CAPABILITY_3 = "94205721-7325-4fb9-9498-b488be90b1c3";
  public static final String SYSTEM_CAPABILITY_4 = "17900ead-952a-4b37-a64f-37b793a0d295";
  public static final String SYSTEM_CAPABILITY_5 = "f6827b38-3b86-4fd0-b327-77cce4730bf1";

  public static final String PHYSICAL_CAPABILITY_1 = "34d774ba-50b4-4d1a-8d14-19c406d3951a";
  public static final String PHYSICAL_CAPABILITY_2 = "efdd561d-9e00-4817-9497-1b434297f721";
  public static final String PHYSICAL_CAPABILITY_3 = "48c42fe8-2676-45cc-9e02-4ed8ff655741";
  public static final String PHYSICAL_CAPABILITY_4 = "99ad2c4e-8bef-45da-82a5-9dd19dda60bf";

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.interaction.validation.DWF_CA_08";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    // data to validate
    return Arrays.asList(new String[] { OPERATIONAL_CAPABILITY_1, OPERATIONAL_CAPABILITY_2, OPERATIONAL_CAPABILITY_3,
        SYSTEM_CAPABILITY_1, SYSTEM_CAPABILITY_2, SYSTEM_CAPABILITY_3, SYSTEM_CAPABILITY_4, SYSTEM_CAPABILITY_5,
        PHYSICAL_CAPABILITY_1, PHYSICAL_CAPABILITY_2, PHYSICAL_CAPABILITY_3, PHYSICAL_CAPABILITY_4 });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition(OPERATIONAL_CAPABILITY_1, 1),
        new OracleDefinition(OPERATIONAL_CAPABILITY_2, 1), new OracleDefinition(OPERATIONAL_CAPABILITY_3, 1),
        new OracleDefinition(SYSTEM_CAPABILITY_1, 0), new OracleDefinition(SYSTEM_CAPABILITY_2, 1),
        new OracleDefinition(SYSTEM_CAPABILITY_3, 1), new OracleDefinition(SYSTEM_CAPABILITY_4, 0),
        new OracleDefinition(SYSTEM_CAPABILITY_5, 1), new OracleDefinition(PHYSICAL_CAPABILITY_1, 1),
        new OracleDefinition(PHYSICAL_CAPABILITY_2, 1), new OracleDefinition(PHYSICAL_CAPABILITY_3, 1),
        new OracleDefinition(PHYSICAL_CAPABILITY_4, 1) });
  }

}
