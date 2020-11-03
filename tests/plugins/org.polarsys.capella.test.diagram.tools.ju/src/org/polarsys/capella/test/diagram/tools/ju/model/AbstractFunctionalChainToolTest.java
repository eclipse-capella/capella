/*******************************************************************************
 * Copyright (c) 2019, 2020, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.model;

import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;

public abstract class AbstractFunctionalChainToolTest extends AbstractDiagramTestCase {

  public static final String MODEL_NAME = "FunctionalChains"; //$NON-NLS-1$

  // ============================== Operational Analysis ============================== //

  public static final String LEVEL1CHAIN1_OA = "ed576cb9-dea7-4053-8613-95ff551f1e94"; //$NON-NLS-1$
  public static final String LEVEL1CHAIN2_OA = "488b0776-3476-41a1-a7e0-8ffd69d5d9bf"; //$NON-NLS-1$
  public static final String LEVEL2CHAIN1_OA = "43268963-856e-453e-9325-54cd0a5d2163"; //$NON-NLS-1$
  public static final String LEVEL3CHAIN1_OA = "dc69394b-29c2-4bbe-a417-2755dc8f0c39"; //$NON-NLS-1$
  public static final String EMPTYCHAIN1_OA = "cfc6261f-c3be-4be4-8797-54606407900c"; //$NON-NLS-1$

  public static final String OPERATIONAL_ACTIVITY_1_OA = "2d7f1966-7882-46ec-a61a-96047dbd58b2"; //$NON-NLS-1$
  public static final String OPERATIONAL_ACTIVITY_2_OA = "f473040c-a644-4dd9-a07e-c92489ab0765"; //$NON-NLS-1$
  public static final String OPERATIONAL_ACTIVITY_3_OA = "ad8861fb-be4c-4a99-bb8b-681ae2a236bc"; //$NON-NLS-1$
  public static final String OPERATIONAL_ACTIVITY_4_OA = "4d1366fd-002c-4867-8efa-2c47994a24cd"; //$NON-NLS-1$
  public static final String OPERATIONAL_ACTIVITY_5_OA = "561cc4c9-cfd0-4e34-b36d-e066b4775fb8"; //$NON-NLS-1$

  public static final String INTERACTION_1_OA = "5d837df2-2e3a-43eb-af98-36f0da3d28fd"; //$NON-NLS-1$
  public static final String INTERACTION_2_OA = "7440764c-8741-4b2e-b2b5-004638cad0ea"; //$NON-NLS-1$
  public static final String INTERACTION_3_OA = "eeec7a7a-8798-4a59-9fbe-aae52d75df49"; //$NON-NLS-1$
  public static final String INTERACTION_4_OA = "c7b3d754-11f2-46a6-8f90-786334369a1a"; //$NON-NLS-1$
  public static final String INTERACTION_5_OA = "59b4c83d-cee1-45e7-8e65-0c6e2adbfc13"; //$NON-NLS-1$

  public static final String FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_OA = "9cda085b-06fc-4459-a1dd-599e93dbf1ad"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_BIS_OA = "ad3a66a4-614a-4f84-a6d4-3dc02a4c1cf5"; //$NON-NLS-1$

  public static final String OPD_LEVEL1CHAIN1_OA_DIAG = "_2eWg4UDUEemoGalPoH57Ew"; //$NON-NLS-1$
  public static final String OPD_LEVEL1CHAIN2_OA_DIAG = "_3eu_00DUEemoGalPoH57Ew"; //$NON-NLS-1$
  public static final String OPD_LEVEL2CHAIN1_OA_DIAG = "__qgudUDUEemoGalPoH57Ew"; //$NON-NLS-1$
  public static final String OPD_LEVEL3CHAIN1_OA_DIAG = "_CFC3M0DVEemoGalPoH57Ew"; //$NON-NLS-1$
  public static final String OPD_EMPTYCHAIN1_OA_DIAG = "_M4d9hUDVEemoGalPoH57Ew"; //$NON-NLS-1$

  // ================================ System Analysis ================================ //

  public static final String LEVEL1CHAIN1_SA = "b380d24a-2beb-47d5-9360-fe1410c53a43"; //$NON-NLS-1$
  public static final String LEVEL1CHAIN2_SA = "32cdf697-4677-42ee-ad2c-0a8799426a8e"; //$NON-NLS-1$
  public static final String LEVEL2CHAIN1_SA = "cd80263c-a598-40fd-8623-9ea827b8ea68"; //$NON-NLS-1$
  public static final String LEVEL3CHAIN1_SA = "6256b217-e656-4ef5-958d-fde65824bbf9"; //$NON-NLS-1$
  public static final String EMPTYCHAIN1_SA = "f7e6d0ab-c0e3-44d7-bffa-a349b7a7c6d5"; //$NON-NLS-1$

  public static final String SYSTEM_FUNCTION_1_SA = "b976677c-d441-4ed7-be9f-1ca5ca79f27b"; //$NON-NLS-1$
  public static final String SYSTEM_FUNCTION_2_SA = "51945687-1507-4521-8865-0e00a7f5f583"; //$NON-NLS-1$
  public static final String SYSTEM_FUNCTION_3_SA = "6c7946db-94f4-49ef-9fa5-a62a67ca8b22"; //$NON-NLS-1$
  public static final String SYSTEM_FUNCTION_4_SA = "70357a00-afbd-4637-90a9-4f4012832df2"; //$NON-NLS-1$
  public static final String SYSTEM_FUNCTION_5_SA = "913b312c-9966-44d1-b750-7ef0f142c5e8"; //$NON-NLS-1$
  public static final String SYSTEM_FUNCTION_6_SA = "812cd062-f881-45ab-b43d-467baaad9f5f"; //$NON-NLS-1$
  public static final String SYSTEM_FUNCTION_7_SA = "4637a3c7-9998-4bcf-9b60-3db842ff652b"; //$NON-NLS-1$

  public static final String FUNCTIONAL_EXCHANGE_1_SA = "6af9c5ca-b1ab-48ae-9717-8ecbdef59ad0"; //$NON-NLS-1$
  public static final String FUNCTIONAL_EXCHANGE_2_SA = "03d9617a-87a9-4da1-bb21-1599b0609d80"; //$NON-NLS-1$
  public static final String FUNCTIONAL_EXCHANGE_3_SA = "99d3b42e-26ec-43ae-9be7-e750b3f77cc9"; //$NON-NLS-1$
  public static final String FUNCTIONAL_EXCHANGE_4_SA = "a4bed56c-cb00-4d20-92bb-c988fe577711"; //$NON-NLS-1$
  public static final String FUNCTIONAL_EXCHANGE_5_SA = "d6d5a9cf-b2a7-4fc8-8463-b713e6a0eaba"; //$NON-NLS-1$
  public static final String FUNCTIONAL_EXCHANGE_6_SA = "e2d977c8-c0c9-4cf5-a492-c99db722f591"; //$NON-NLS-1$
  public static final String FUNCTIONAL_EXCHANGE_7_SA = "b4fd9f53-2d69-4840-817a-2911fc401594"; //$NON-NLS-1$

  public static final String FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_SA = "0cf9c843-97f5-4c78-9058-059a5ef8e549"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_BIS_SA = "78ec9e15-611d-46cd-b1f6-ac1ca7f75f3d"; //$NON-NLS-1$

  public static final String SFCD_LEVEL1_CHAIN1_SA_DIAG = "_xJmt8T8gEemAMorOl8q0FQ"; //$NON-NLS-1$
  public static final String SFCD_LEVEL1_CHAIN2_SA_DIAG = "_3Ne76j8gEemAMorOl8q0FQ"; //$NON-NLS-1$
  public static final String SFCD_LEVEL2_CHAIN1_SA_DIAG = "_9KXKAD8gEemAMorOl8q0FQ"; //$NON-NLS-1$
  public static final String SFCD_LEVEL3_CHAIN1_SA_DIAG = "_eLIcQz8hEemAMorOl8q0FQ"; //$NON-NLS-1$
  public static final String SFCD_EMPTYCHAIN1_SA_DIAG = "_tn1a1T8hEemAMorOl8q0FQ"; //$NON-NLS-1$
  
  public static final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_EXCHANGE_2_SA = "58987f1d-8306-493c-911b-55f86898dfc9"; //$NON-NLS-1$
  
  public static final String SEQUENCE_LINK_FUNCTION_4_FUNCTION_6_SA = "23c47aca-8f41-40b1-b236-b64fcdba0445"; //$NON-NLS-1$
  public static final String SEQUENCE_LINK_FUNCTION_6_FUNCTION_7_SA = "ae19bf97-e088-463e-89a5-8048b82f6c7e"; //$NON-NLS-1$

  @Override
  protected String getRequiredTestModel() {
    return MODEL_NAME;
  }
}
