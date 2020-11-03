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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_df;

import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

public abstract class Project_DWF_DF_for_SL_CN extends AbstractRulesOnDesignTest {
  /*
   * OA LEVEL
   */
  public static final String OA_SEQUENCE_LINK_1 = "0a801d54-e080-41a8-83b2-62d36f7eb198";
  public static final String OA_SEQUENCE_LINK_2 = "d4042bce-6add-43f7-982f-52f141b689c8";
  public static final String OA_SEQUENCE_LINK_3 = "d93d07cd-c630-49fc-8ad4-4017e479381d";
  public static final String OA_SEQUENCE_LINK_4 = "1d375eba-e888-448e-8138-d6c3d0026c9a";
  public static final String OA_SEQUENCE_LINK_5 = "50b9c758-2b1e-471d-bdd1-91652e186c61";
  public static final String OA_SEQUENCE_LINK_6 = "372eb848-fd25-4780-b4f2-8df1d52b4259";
  public static final String OA_SEQUENCE_LINK_7 = "63b20464-c602-4e36-b0e6-cc1df762b72f";
  public static final String OA_SEQUENCE_LINK_8 = "a52f1cdc-cd37-4758-b29b-1e8e39052566";
  public static final String OA_SEQUENCE_LINK_9 = "9937ab0c-1321-4998-b21b-ff30796dd5fc";

  public static final String OA_CONTROL_NODE_1 = "4597a1d7-2e58-4381-99fb-8209549a2bee";
  public static final String OA_CONTROL_NODE_2 = "6c927df1-3196-450d-9a24-f37284b15587";
  public static final String OA_CONTROL_NODE_3 = "0528db54-de5c-4e30-b457-84f404c5e28c";

  public static final String OA_CONTROL_NODE_4 = "877f2aa4-fa32-4178-ab81-60c2aa5cf840";
  public static final String OA_CONTROL_NODE_5 = "180db9ac-135d-4d8a-9049-6c630e8be3da";
  public static final String OA_CONTROL_NODE_6 = "2ed07b98-2ea2-4645-9570-4d0388dc77fc";

  /*
   * SA LEVEL
   */
  public static final String SA_SEQUENCE_LINK_1 = "c323e8fd-accd-465a-8d63-3361cea50644";
  public static final String SA_SEQUENCE_LINK_2 = "0cfd2816-7f05-4957-b3fb-124219e0a8b4";
  public static final String SA_SEQUENCE_LINK_3 = "f2f08e97-1bc5-4d7a-9b2b-7745d95b7eb2";
  public static final String SA_SEQUENCE_LINK_4 = "a8284f9a-404b-4631-afff-e8dd7f4193eb";
  public static final String SA_SEQUENCE_LINK_5 = "034b6aaf-ded4-40e1-8c85-656ee4ac2e4c";
  public static final String SA_SEQUENCE_LINK_6 = "f5d5db18-77e1-4352-884c-20c6aa37fd99";
  public static final String SA_SEQUENCE_LINK_7 = "1df95134-4638-48d2-9a03-aaad1637366d";
  public static final String SA_SEQUENCE_LINK_8 = "d2e0443b-c47b-4406-8246-722318fc9b7f";
  public static final String SA_SEQUENCE_LINK_9 = "416ab824-37f0-467c-b9af-31fff385cff0";

  public static final String SA_CONTROL_NODE_1 = "2025fc24-9f9e-4d45-b3c3-edddc5cc5594";
  public static final String SA_CONTROL_NODE_2 = "1b5a3e6f-258c-4dd0-9563-490578cc9f5d";
  public static final String SA_CONTROL_NODE_3 = "ba45b40e-eaf4-474a-94a5-ffe73f4ca7a3";

  public static final String SA_CONTROL_NODE_4 = "a34f12ab-c043-488c-90c6-8eb882e08162";
  public static final String SA_CONTROL_NODE_5 = "e1cd6015-8ab9-4ee1-a403-68ebd58b15aa";
  public static final String SA_CONTROL_NODE_6 = "7e3fa472-e39a-41a4-85d5-2ea0422f9556";

  /*
   * LA LEVEL
   */
  public static final String LA_SEQUENCE_LINK_1 = "54492923-446b-4084-babb-367c016e4e10";
  public static final String LA_SEQUENCE_LINK_2 = "88de7727-3ba5-4661-83d1-e8d8091a6b49";
  public static final String LA_SEQUENCE_LINK_3 = "bfd591ad-5033-4a62-91af-370354ab9f73";
  public static final String LA_SEQUENCE_LINK_4 = "2c4270ef-4875-4bd4-ac5a-c4a9b007d42a";
  public static final String LA_SEQUENCE_LINK_5 = "6e3b8674-d268-4205-9610-401f888192a5";
  public static final String LA_SEQUENCE_LINK_6 = "e9c7962a-85d7-4f7b-a907-85a661f242e3";
  public static final String LA_SEQUENCE_LINK_7 = "da3d9bfd-4d57-467a-8d89-f2765608fc64";
  public static final String LA_SEQUENCE_LINK_8 = "3c35c271-9a49-4b76-9db2-60b4f23b5ce7";
  public static final String LA_SEQUENCE_LINK_9 = "4ad21788-f50c-4a9a-84ff-b81d6fa5075f";

  public static final String LA_CONTROL_NODE_1 = "b064ea8f-83cd-4b1d-b21e-433f7e8e5814";
  public static final String LA_CONTROL_NODE_2 = "d7d196dc-9e26-4d36-9d7a-c1713a7a72e1";
  public static final String LA_CONTROL_NODE_3 = "0b66af5c-994b-481b-80fb-5617848e5747";

  public static final String LA_CONTROL_NODE_4 = "68371d25-8c07-403f-a139-f81b3b04b2fc";
  public static final String LA_CONTROL_NODE_5 = "453176b9-6539-4237-ba6d-ea1df5a5b939";
  public static final String LA_CONTROL_NODE_6 = "82ee2158-d06f-4a2b-90e6-072edfb4c5f1";
}
