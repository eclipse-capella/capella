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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.test.framework.api.ComposedOracleDefinition;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase;

public class Rule_DWF_DS_24 extends ValidationRulePartialTestCase {

  private static final String OES_OPERATIONALCAPABILITY_1 = "b7609bb4-b030-4224-b1d3-60a5e1a621db"; //$NON-NLS-1$
  private static final String OE_1_WRONG_NAME = "cd07161c-9d5d-45f4-89d6-baa2e8d15e1f"; //$NON-NLS-1$
  private static final String OA_2_WRONG_NAME = "aced94d6-28cf-4053-ab80-b671e7fb0b3a"; //$NON-NLS-1$
  private static final String ROLE_1_WRONG_NAME = "682ee75f-63fc-4d95-be7f-06cf183f6b24"; //$NON-NLS-1$

  private static final OracleDefinition OES_OPERATIONALCAPABILITY_1_ORACLE = //
      ComposedOracleDefinition.create(OES_OPERATIONALCAPABILITY_1, OE_1_WRONG_NAME, OA_2_WRONG_NAME, ROLE_1_WRONG_NAME);

  //

  private static final String OAS_OPERATIONALCAPABILITY_1 = "8591967f-31b0-43e5-8b84-8f6c508ffd61"; //$NON-NLS-1$
  private static final String OPERATIONALACTIVITY_WRONG_NAME = "dc22c60e-7299-46df-8c00-d1f6b3394222"; //$NON-NLS-1$

  private static final OracleDefinition OAS_OPERATIONALCAPABILITY_1_ORACLE = //
      ComposedOracleDefinition.create(OAS_OPERATIONALCAPABILITY_1, OPERATIONALACTIVITY_WRONG_NAME);

  //

  private static final String IS_SYSTEMCAPABILITY_1 = "b96c0e98-0de6-4e52-8f8c-d7cf805b84e4"; //$NON-NLS-1$
  private static final String SA_2_WRONG_NAME = "ccba28c6-9219-47aa-bd99-74e00e14ec4f"; //$NON-NLS-1$
  private static final String EXCHANGEITEM_1_WRONG_NAME = "676b3ffe-8539-4b04-9186-f32273e26117"; //$NON-NLS-1$

  private static final OracleDefinition IS_SYSTEMCAPABILITY_1_ORACLE = //
      ComposedOracleDefinition.create(IS_SYSTEMCAPABILITY_1, SA_2_WRONG_NAME, EXCHANGEITEM_1_WRONG_NAME);

  //

  private static final String ES_SYSTEMCAPABILITY_1 = "7c76c9d9-5ac6-4ee1-8718-47a99c1b833f"; //$NON-NLS-1$
  private static final String SA_6_WRONG_NAME = "2b17f7fc-3022-4f12-849b-0d83236a589a"; //$NON-NLS-1$

  private static final OracleDefinition ES_SYSTEMCAPABILITY_1_ORACLE = //
      ComposedOracleDefinition.create(ES_SYSTEMCAPABILITY_1, SA_6_WRONG_NAME);

  //

  private static final String FS_SYSTEMCAPABILITY_1 = "3e78f2c2-6810-4458-ba35-8f9420a042b1"; //$NON-NLS-1$
  private static final String SYSTEMFUNCTION_2_WRONG_NAME = "e8518ef1-ebef-4a38-b87b-6f6e260f2918"; //$NON-NLS-1$

  private static final OracleDefinition FS_SYSTEMCAPABILITY_1_ORACLE = //
      ComposedOracleDefinition.create(FS_SYSTEMCAPABILITY_1, SYSTEMFUNCTION_2_WRONG_NAME);

  //

  private static final String IS_LOGICALCAPABILITYREALIZATION_1 = "2b458b7d-6bc6-4ae7-8a89-5a87d1033777"; //$NON-NLS-1$
  private static final String LC_2_WRONG_NAME = "6a2a26ae-1537-45f5-904e-739b3d365cfe"; //$NON-NLS-1$
  private static final String LA_2_WRONG_NAME = "257d9457-0260-4cdb-bc02-0f8b6db9ab5a"; //$NON-NLS-1$
  private static final String LA_EXCHANGEITEM_1_WRONG_NAME = "e4e0662c-72d3-4ff3-b280-592b22dd4970"; //$NON-NLS-1$

  private static final OracleDefinition IS_LOGICALCAPABILITYREALIZATION_1_ORACLE = //
      ComposedOracleDefinition.create(IS_LOGICALCAPABILITYREALIZATION_1, LC_2_WRONG_NAME, LA_2_WRONG_NAME,
          LA_EXCHANGEITEM_1_WRONG_NAME);

  //

  private static final String ES_LOGICALCAPABILITYREALIZATION_1 = "89ad0896-7306-4162-adb4-f5c37b5cd8ed"; //$NON-NLS-1$
  private static final String LC_4_WRONG_NAME = "1716b0b1-b5f0-4100-8e7e-a3aeb917d16a"; //$NON-NLS-1$
  private static final String LA_5_WRONG_NAME = "f872e891-5fd5-42a4-8297-fa6ab4b5ee3d"; //$NON-NLS-1$

  private static final OracleDefinition ES_LOGICALCAPABILITYREALIZATION_1_ORACLE = //
      ComposedOracleDefinition.create(ES_LOGICALCAPABILITYREALIZATION_1, LC_4_WRONG_NAME, LA_5_WRONG_NAME);

  //

  private static final String FS_LOGICALCAPABILITYREALIZATION_1 = "e5c3af34-4d1d-46ec-aa78-13a0b905d3b1"; //$NON-NLS-1$
  private static final String LOGICALFUNCTION_2_WRONG_NAME = "56015110-d322-4c68-8c1b-a578ed7764a2"; //$NON-NLS-1$

  private static final OracleDefinition LOGICALFUNCTION_2_WRONG_NAME_ORACLE = //
      ComposedOracleDefinition.create(FS_LOGICALCAPABILITYREALIZATION_1, LOGICALFUNCTION_2_WRONG_NAME);

  //

  private static final String IS_PHYSICALCAPABILITYREALIZATION_1 = "e15e0c1e-8229-4676-8037-d02e8fedee64"; //$NON-NLS-1$
  private static final String PC_1_WRONG_NAME = "cab4c7f5-1a8e-4370-b639-6c509c54518a"; //$NON-NLS-1$
  private static final String PC_4_WRONG_NAME = "26dc148b-3dbb-4999-bf4b-85ef63d4e481"; //$NON-NLS-1$
  private static final String PA_2_WRONG_NAME = "ce0a95f6-5b2b-44c7-baa6-df021a87c944"; //$NON-NLS-1$

  private static final OracleDefinition IS_PHYSICALCAPABILITYREALIZATION_1_ORACLE = //
      ComposedOracleDefinition.create(IS_PHYSICALCAPABILITYREALIZATION_1, PC_1_WRONG_NAME, PC_4_WRONG_NAME,
          PA_2_WRONG_NAME);

  private static final String ES_PHYSICALCAPABILITYREALIZATION_1 = "f176846c-1a05-4efe-ae01-8264a4d70935"; //$NON-NLS-1$
  private static final String PC_7_WRONG_NAME = "b294fc35-f5f6-48fd-8155-873826191f6a"; //$NON-NLS-1$
  private static final String PC_10_WRONG_NAME = "3cef9291-f04d-4014-808f-a77cfcd10d68"; //$NON-NLS-1$
  private static final String PA_7_WRONG_NAME = "ae4376a0-2d04-48b1-a53d-d43cbcbe32ea"; //$NON-NLS-1$

  private static final OracleDefinition ES_PHYSICALCAPABILITYREALIZATION_1_ORACLE = //
      ComposedOracleDefinition.create(ES_PHYSICALCAPABILITYREALIZATION_1, PC_7_WRONG_NAME, PC_10_WRONG_NAME,
          PA_7_WRONG_NAME);

  //

  private static final String FS_PHYSICALCAPABILITYREALIZATION_1 = "1b24938e-1907-4b2e-85ca-95928c834d8f"; //$NON-NLS-1$
  private static final String PHYSICALFUNCTION_2_WRONG_NAME = "80fd1f88-babf-4a1f-9b06-0fe042e9af5b"; //$NON-NLS-1$

  private static final OracleDefinition FS_PHYSICALCAPABILITYREALIZATION_1_ORACLE = //
      ComposedOracleDefinition.create(FS_PHYSICALCAPABILITYREALIZATION_1, PHYSICALFUNCTION_2_WRONG_NAME);
  //

  private static final String IS_EPBSCAPABILITYREALIZATION_1 = "0b008e59-e044-4699-a373-b2247dfa7406"; //$NON-NLS-1$
  private static final String CSCI_2_WRONG_NAME = "2fb35cf3-5c12-4253-a4f3-632d465ea90b"; //$NON-NLS-1$

  private static final OracleDefinition IS_EPBSCAPABILITYREALIZATION_1_ORACLE = //
      ComposedOracleDefinition.create(IS_EPBSCAPABILITYREALIZATION_1, CSCI_2_WRONG_NAME);

  @Override
  protected String getRequiredTestModel() {
    return "instance-role-same-name-as-represented-instance";
  }

  @Override
  protected EClass getTargetedEClass() {
    return InteractionPackage.Literals.SCENARIO;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.interaction.validation.DWF_DS_24";
  }

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(OES_OPERATIONALCAPABILITY_1, OAS_OPERATIONALCAPABILITY_1, IS_SYSTEMCAPABILITY_1,
        ES_SYSTEMCAPABILITY_1, FS_SYSTEMCAPABILITY_1, IS_LOGICALCAPABILITYREALIZATION_1,
        ES_LOGICALCAPABILITYREALIZATION_1, FS_LOGICALCAPABILITYREALIZATION_1, IS_PHYSICALCAPABILITYREALIZATION_1,
        ES_PHYSICALCAPABILITYREALIZATION_1, FS_PHYSICALCAPABILITYREALIZATION_1, IS_EPBSCAPABILITYREALIZATION_1);
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    List<OracleDefinition> oracles = new ArrayList<>();

    oracles.add(OES_OPERATIONALCAPABILITY_1_ORACLE);
    oracles.add(OAS_OPERATIONALCAPABILITY_1_ORACLE);
    oracles.add(IS_SYSTEMCAPABILITY_1_ORACLE);
    oracles.add(ES_SYSTEMCAPABILITY_1_ORACLE);
    oracles.add(FS_SYSTEMCAPABILITY_1_ORACLE);
    oracles.add(IS_LOGICALCAPABILITYREALIZATION_1_ORACLE);
    oracles.add(ES_LOGICALCAPABILITYREALIZATION_1_ORACLE);
    oracles.add(LOGICALFUNCTION_2_WRONG_NAME_ORACLE);
    oracles.add(IS_PHYSICALCAPABILITYREALIZATION_1_ORACLE);
    oracles.add(ES_PHYSICALCAPABILITYREALIZATION_1_ORACLE);
    oracles.add(FS_PHYSICALCAPABILITYREALIZATION_1_ORACLE);
    oracles.add(IS_EPBSCAPABILITYREALIZATION_1_ORACLE);

    return oracles;
  }

}
