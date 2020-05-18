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
 * test on DWF_CA_07: This rule checks that if the involved Actors in the current Capacity are present in at least one
 * operational process or functional chain or scenario.
 * 
 * @generated
 */
public class Rule_DWF_CA_07 extends AbstractRulesOnDesignTest {
  public static final String OPERATIONAL_CAPABILITY_1 = "ff2e25b3-ad7e-482a-b891-10c317ab2b4b";
  public static final String OPERATIONAL_CAPABILITY_2 = "aed7332d-f0ce-4dae-9949-be56bc1233d6";
  public static final String OPERATIONAL_CAPABILITY_3 = "bea7f95b-ad95-4195-9438-529144986281";
  public static final String OPERATIONAL_CAPABILITY_4 = "4fcc940a-a4b0-4f3f-85b3-8373ad288c3b";
  public static final String OPERATIONAL_CAPABILITY_5 = "553b35eb-5522-4fc2-8534-2d8149a4550f";

  public static final String SYSTEM_CAPABILITY_1 = "62b89e16-edb7-40ac-bcd1-7bd6f81dd6e7";
  public static final String SYSTEM_CAPABILITY_2 = "7fff2fdd-9ace-416a-aa07-e245a83df16e";
  public static final String SYSTEM_CAPABILITY_3 = "d23aa474-fb65-4fa7-a4aa-91f35c3c2cb4";
  public static final String SYSTEM_CAPABILITY_4 = "e0882680-1977-4949-b363-db4fbb2e9774";
  public static final String SYSTEM_CAPABILITY_5 = "89691034-5ab1-4888-8659-dd866bcdc6ec";

  public static final String PHYSICAL_CAPABILITY_1 = "2723861a-d0ad-48bb-871e-d773bde60f9d";
  public static final String PHYSICAL_CAPABILITY_2 = "eba3c2f6-2a3c-4eb0-950c-38ed22cd8b76";
  public static final String PHYSICAL_CAPABILITY_3 = "93157e7d-503c-482c-b149-425fa09411d8";
  public static final String PHYSICAL_CAPABILITY_4 = "b5bc3746-ae3d-4625-a036-f31e01915e80";
  public static final String PHYSICAL_CAPABILITY_5 = "11c37e09-d3c0-4c76-a8d8-b6d6d229a32f";
  public static final String PHYSICAL_CAPABILITY_6 = "368ab322-3287-4d95-bc5e-3921aab2a41d";

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
    return "org.polarsys.capella.core.data.interaction.validation.DWF_CA_07";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    // data to validate
    return Arrays.asList(new String[] { OPERATIONAL_CAPABILITY_1, OPERATIONAL_CAPABILITY_2, OPERATIONAL_CAPABILITY_3,
        OPERATIONAL_CAPABILITY_4, OPERATIONAL_CAPABILITY_5, SYSTEM_CAPABILITY_1, SYSTEM_CAPABILITY_2,
        SYSTEM_CAPABILITY_3, SYSTEM_CAPABILITY_4, SYSTEM_CAPABILITY_5, PHYSICAL_CAPABILITY_1, PHYSICAL_CAPABILITY_2,
        PHYSICAL_CAPABILITY_3, PHYSICAL_CAPABILITY_4, PHYSICAL_CAPABILITY_5, PHYSICAL_CAPABILITY_6 });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition(OPERATIONAL_CAPABILITY_1, 0),
        new OracleDefinition(OPERATIONAL_CAPABILITY_2, 1), new OracleDefinition(OPERATIONAL_CAPABILITY_3, 1),
        new OracleDefinition(OPERATIONAL_CAPABILITY_4, 1), new OracleDefinition(OPERATIONAL_CAPABILITY_5, 1),
        new OracleDefinition(SYSTEM_CAPABILITY_1, 0), new OracleDefinition(SYSTEM_CAPABILITY_2, 1),
        new OracleDefinition(SYSTEM_CAPABILITY_3, 1), new OracleDefinition(SYSTEM_CAPABILITY_4, 1),
        new OracleDefinition(SYSTEM_CAPABILITY_5, 1), new OracleDefinition(PHYSICAL_CAPABILITY_1, 0),
        new OracleDefinition(PHYSICAL_CAPABILITY_2, 1), new OracleDefinition(PHYSICAL_CAPABILITY_3, 1),
        new OracleDefinition(PHYSICAL_CAPABILITY_4, 1), new OracleDefinition(PHYSICAL_CAPABILITY_5, 1),
        new OracleDefinition(PHYSICAL_CAPABILITY_6, 1) });
  }

}
