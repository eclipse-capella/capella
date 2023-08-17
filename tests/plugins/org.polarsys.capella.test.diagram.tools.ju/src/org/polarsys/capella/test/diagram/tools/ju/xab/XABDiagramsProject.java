/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;

public abstract class XABDiagramsProject extends AbstractDiagramTestCase {
  public static final String PROJECT_APPROACH_ID = "829c7043-54c5-42f4-807d-18f8ca504130";

  // epbs
  public static final String EPBS__EAB_DIAGRAM = "[EAB] System";
  public static final String EPBS__EAB_CONFIGURATION_ITEM1 = "3efeac2f-61c2-47c9-82c5-3172b1248847";
  public static final String EPBS__EAB_CONFIGURATION_ITEM2 = "f5b61c83-fd03-4df7-bd35-98e098273517";
  public static final String EPBS__EAB_CONFIGURATION_ITEM1_1 = "4a43e871-59ea-4bcf-ba02-2a6097355dca";
  public static final String EPBS__EAB_COTSC1 = "6ffa67ff-1bf1-4ccf-99aa-89623c99a253";
  public static final String EPBS__EAB_HWCI2 = "9874cae1-5b98-4d78-bc3a-611bb3899b24";
  public static final String EPBS__EAB_CONSTRAINT = "7ae23b1a-8f42-4722-8d0a-ab97f4666f8b";
  public static final String EPBS__EAB_PROPERTY_VALUE1 = "306362c3-1b64-4f45-a520-31bd3cc5a04f";
  public static final String EPBS__EAB_PROPERTY_VALUE_GROUP1 = "7fe89aef-e34c-4859-bb68-6b0689792888";
  public static final String EPBS_EAB_PHYSICAL_LINK = "3724a688-bdd7-469e-a0c8-21de49945ddd";
  public static final String EPBS_EAB_PHYSICAL_PORT = "c1cc636b-9254-4fe3-93e0-02f9040ba270";

  // oa
  public static final String OA__OAB_OPERATIONAL_CONTEXT = "64605af7-0cfd-47f4-99ef-0c0afa52304e";
  public static final String OA__OAB_DIAGRAM = "[OAB] Operational Context";
  public static final String OA__OAB_OA1 = "6ce78848-33e3-4cde-96cf-0248225456e4";
  public static final String OA__OAB_OA2 = "00583700-90af-48dc-be0a-dffca68ac815";
  public static final String OA__OAB_OA3 = "37ebde77-c992-4738-b737-43582104de1f";
  public static final String OA__OAB_OA4 = "af77907a-ba3f-41f1-a334-c663e9ae5890";
  public static final String OA__OAB_ROLE1 = "76e008c7-f343-4a7c-8ef9-6602cb3f4512";
  public static final String OA__OAB_FUNCTION_1 = "4741aadb-47b5-4487-a717-7703577a3b06";
  public static final String OA__OAB_FUNCTION_2 = "d7d31666-d9d5-4caa-bf77-cb348efcc248";
  public static final String OA__OAB_FUNCTION_3 = "843281f3-00bc-4c34-b1e9-79f9065049a0";
  public static final String OA__OAB_COMMUNICATION_MEAN1 = "ef3d73de-9cff-4893-bc98-9543077e9a4b";
  public static final String OA__OAB_CONSTRAINT = "c5bbd695-a018-4b35-b6d7-7b5747875c07";
  public static final String OA__OAB_ENTITY1 = "2937d0b4-1d8b-4199-99e0-72605bfd87ca";
  public static final String OA__OAB_INTERACTION1 = "aec1d1e2-5023-4142-bf88-1ecdb64a8200";
  public static final String OA__OAB_OPERATIONAL_PROCESS1 = "0f4ce543-36f9-4753-b7af-f1d869525218";
  public static final String OA__OAB_PROPERTY_VALUE1 = "96a78087-d97a-4a40-a709-af776fd1f2c4";
  public static final String OA__OAB_PROPERTY_VALUE_GROUP1 = "1f2ffac9-4585-4c1b-aa67-83bfc94955ed";
  public static final String OA__OAB_SEQUENCELINK1 = "ca41d42a-5114-438b-9ad2-cd3a1bb9f169";
  public static final String OA__OAB_SEQUENCELINK2 = "7375dc15-f8fd-4d8e-ab08-6720e83bb78c";
  public static final String OA__OAB_CONTROLNODE = "224bab6b-092d-4f67-8c05-9b0e3b0a5f93";

  // elements from scenarios
  public static final String OA__OAB_ENTITY5 = "2b90e4ce-24ee-4fe0-a0df-db44b202bb5e";
  public static final String OA__OAB_ENTITY6 = "4652717e-6c2a-49ad-8b8e-492d9895b1c9";
  public static final String OA__OAB_OA7 = "9c5f2dd7-b0e8-45ab-b4f0-8c9f182b1f01";
  public static final String OA__OAB_OA8 = "6a3be1a3-6f30-4472-b7fd-46ae6187b799";
  public static final String OA__OAB_COMMUNICATION_MEAN2 = "2abbe53d-2167-479f-8777-5f84bd5466a8";
  public static final String OA__OAS_I1 = "99f82939-3673-4104-9ff3-0675b8786af4";
  public static final String OA__SCENARIO_OES1 = "3bb3d86b-96e6-4e32-a74b-54b030b6947e";
  public static final String OA__SCENARIO_OAS1 = "43929e05-3f39-43a9-84b4-aa87d4f8c9e1";

  // elements from state/modes
  public static final String OA__MODE1 = "9dd6247b-60f3-447f-b4be-fd5e65e1c617";
  public static final String OA__STATE1 = "42365d22-d950-4c2c-a00c-d6a2c4f14ca1";

  // sa
  public static final String SA_STRUCTURE = "09d06243-63db-4202-b519-9faf7c82322a";
  public static final String SA__SAB_SYSTEM = "b4fd3a2f-a53d-4da7-bec4-8fd7169d2caa";
  public static final String SA__SAB_SYSTEM_PART = "f280765d-965a-42bd-b886-616dc643361f";
  public static final String SA__SAB_DIAGRAM = "[SAB] System";
  public static final String SA__SAB_A1 = "ff952c1b-4605-414e-892b-7fc1e0886826";
  public static final String SA__SAB_A2 = "325e94ec-99cd-47ef-b1f0-8fa15fb50ca2";
  public static final String SA__SAB_A3 = "f9e2c3c8-346c-4c9c-81d8-533897358ed0";
  public static final String A_1 = "afe019b9-2e81-4363-b09c-1e106b7ca7b4"; //$NON-NLS-1$
  public static final String A_2 = "fecd3e88-fe49-44b0-a6d2-cfc76af572a2"; //$NON-NLS-1$
  public static final String A_3 = "ef5e2fd0-52eb-4dc2-a11e-4d669be1a396"; //$NON-NLS-1$
  public static final String A_4 = "d2d00d4d-3eb6-4198-b7d7-55360f583e37"; //$NON-NLS-1$
  public static final String SA__SAB_FUNCTION_1 = "5eef3339-1e33-4cc4-9968-58b7b128f28a";
  public static final String SA__SAB_FUNCTION_2 = "7111133e-b516-48a4-9c03-93c25054c3b9";
  public static final String SA__SAB_FUNCTION_GATHER = "87d429f5-967e-4e67-97d2-39ff44308a9f";
  public static final String SA__SAB_FUNCTION_ROUTE = "3b17a884-0d28-4498-913a-9bccb4a0df65";
  public static final String SA__SAB_FUNCTION_DUPLICATE = "87d429f5-967e-4e67-97d2-39ff44308a9f";
  public static final String SA__SAB_FUNCTION_SELECT = "82e8f9af-6468-4d2a-b4ea-8197a4ef47f6";
  public static final String SA__SAB_FUNCTION_SPLIT = "55e07c1c-3a2a-45ec-968c-180620bfa0a1";
  public static final String SA__SAB_COMPONENT_PORT_1 = "1976fbc0-8f98-41e3-9987-7893f631b739";
  public static final String SA__SAB_FUNCTION_PORT_1 = "f54ba750-a16d-4d74-89ba-3e1c6a63ad34";
  public static final String SA__SAB_COMPONENT_PORT_A1_CP1 = "1976fbc0-8f98-41e3-9987-7893f631b739";
  public static final String SA__SAB_COMPONENT_PORT_A2_CP1 = "fdb697c6-2c61-4717-8837-6ddc612f8f51";
  public static final String SA__SAB_COMPONENT_PORT_A3_CP1 = "2010abbe-589a-433f-90a2-594a978eb1a3";
  public static final String SA__SAB_COMPONENT_PORT_OUT_A3_CP3 = "aa8324e2-b55a-4e3e-b28b-aef8d6ff0c4e";
  public static final String SA__SAB_COMPONENT_EXCHANGE_C1 = "73677d34-55e4-44cc-8c70-f266a83c36ef";
  public static final String SA__SAB_PHYSICAL_PORT_A1_PP1 = "2e93708e-c467-4448-8fdf-ce6a30058ce5";
  public static final String SA__SAB_PHYSICAL_PORT_A2_PP1 = "e54c1773-1089-444b-9caa-2a5ca1fa9f5e";
  public static final String SA__SAB_PHYSICAL_PORT_A2_PP3 = "d0a52770-b93d-4014-a4a7-54067cf0cfd4";
  public static final String SA__SAB_PHYSICAL_PORT_A3_PP1 = "be182eb3-b416-43d3-bc9c-a971904a8dd4";
  public static final String SA__SAB_PHYSICAL_LINK_PL1 = "ac6fba92-d508-421c-84bf-9b8eb87ddedc";
  public static final String SA__SAB_PHYSICAL_PATH1 = "4797a74b-6abc-44aa-bb72-3516bb891a84";
  public static final String SA__SAB_CONSTRAINT = "ca1d6a5a-b098-423a-bb9d-407e28a1e6a7";
  public static final String SA__SAB_COMPONENT_PORT_ALLOCATION = "3eeae0e2-e068-439a-b40f-640ab9f028ca";
  public static final String SA__SAB_FUNCTIONAL_EXCHANGE1 = "67aacd13-5813-4030-9fec-8a6b8ee536b0";
  public static final String SA__SAB_SYSTEM_FUNCTION1_FOP1 = "f54ba750-a16d-4d74-89ba-3e1c6a63ad34";
  public static final String SA__SAB_SYSTEM_FUNCTION1_FIP1 = "61729889-ddae-403b-be12-cc47a8f6c020";
  public static final String SA__SAB_SYSTEM_FUNCTION1_FIP2 = "e70d2bca-a07b-47ba-bebf-ddb6d2dd157b";
  public static final String SA__SAB_SYSTEM_FUNCTION2_FIP1 = "daf7c0c2-6a07-472d-b058-1e8a4b8c0063";
  public static final String SA__SAB_SYSTEM_FUNCTION2_FIP2 = "fe6e1b19-1031-4a5b-81d2-353506fc1fa1";
  public static final String SA__SAB_SYSTEM_FUNCTION3_FOP1 = "619a5d06-26cb-44b3-b238-1554c2c4f2ed";
  public static final String SA__SAB_SYSTEM_FUNCTION3_FIP1 = "f17f02b6-e588-4ee0-88b5-fdab1b7ce80d";
  public static final String SA__SAB_FUNCTIONAL_CHAIN1 = "6c38aab1-8647-4e08-b97a-7956d98cbf14";
  public static final String SA__SAB_PROPERTY_VALUE1 = "d28e96b8-550e-4d65-a49a-d03d347dc9ad";
  public static final String SA__SAB_PROPERTY_VALUE_GROUP1 = "63d55863-8719-43d6-84a6-7e86336a2306";
  public static final String SA__SAB_EXCHANGE_CATEGORY1 = "9674d2ca-16c0-4b98-99d5-f6b5421dd20a";
  public static final String SA__SAB_FUNCTIONAL_EXCHANGE4 = "5cd86e0b-3cda-4c06-8c05-214b754bd4ce";
  public static final String SA__SAB_EXCHANGE_CATEGORY4 = "b76e3399-f2ed-4b8f-b23e-ea1194662d4b";
  public static final String SA__SAB_SEQUENCELINK1 = "11284360-1d2b-4ba8-b44f-75e2cb95638d";
  public static final String SA__SAB_SEQUENCELINK2 = "5e189564-a912-4d3c-b602-fe3f6182c984";
  public static final String SA__SAB_CONTROLNODE = "68626cbe-0780-4dc4-b35b-a716abf0e286";

  // elements from scenarios
  public static final String SA__SAB_SYSTEM_ACTOR4 = "edb2b0df-081d-4e98-9e79-d10cff4aaf09";
  public static final String SA__SAB_SYSTEM_FUNCTION4 = "8fc3a367-2048-4a8f-a063-50dd20123fa6";
  public static final String SA__SAB_COMPONENT_EXCHANGE2 = "33adc168-5e09-47ed-8c3b-d92e7a3bde05";
  public static final String SA__SAB_FUNCTIONAL_EXCHANGE_ES1 = "1bdc06d5-1a5c-4231-96aa-c1bc3a20de2d";
  public static final String SA__SCENARIO_ES1 = "a146d8e0-a715-4453-9df1-c5272b59a67c";

  // elements from mode/states
  public static final String SA__MODE1 = "74e9176f-b3ab-4fe5-9bc3-c525b25b040f";
  public static final String SA__STATE1 = "cbd3a322-330f-4518-89a7-0ffbd0b6b977";
  public static final String SA__SAB_SYSTEM_FUNCTION5 = "b7e07ca4-2b58-4b93-851b-9377e91dfb9b";
  public static final String SA__SAB_PHYSICAL_LINK_CATEGORY1 = "53571fcc-bd97-44eb-86ca-961a2b8177e7";
  public static final String SA__SAB_COMPONENT_EXCHANGE_CATEGORY1 = "5117e0d8-8319-46c4-862f-6c8c9cdd2197";

  // la
  public static final String LA__LAB_LOGICAL_SYSTEM = "8dae69b8-87f7-45c0-9f95-f72009aa4c1c";
  public static final String LA__LAB_LOGICAL_SYSTEM_PART = "6b64bc6d-7666-4202-8727-bb039a76d172";
  public static final String LA__LAB_DIAGRAM = "[LAB] Logical System";
  public static final String LA__LAB_LC1_PART = "c30f774e-9dd2-406e-a611-14f59ebcb96c";
  public static final String LA__LAB_LC1 = "f8d17c52-5dd0-412d-8f45-cebd58928da0";
  public static final String LA__LAB_A1 = "e7884bff-642d-44ac-bce6-c3cd20aa03b1";
  public static final String LA__LAB_A2 = "e671feb7-b6b3-40c0-8f2c-ff2b5ef349f0";
  public static final String LA_1 = "dc2c6c0f-a55b-431c-a739-79fbd2d243c9"; //$NON-NLS-1$
  public static final String LA_2 = "50574f2c-7b5f-4b8b-b0eb-37126f10dbc4"; //$NON-NLS-1$
  public static final String LA_3 = "5a0a0053-6a38-40ef-a6a2-029062a6c5f8"; //$NON-NLS-1$
  public static final String LA_4 = "4df2b99f-9a2a-4e5f-8a4c-a9a60804495e"; //$NON-NLS-1$
  public static final String LA_5 = "9c86e140-d4f0-4321-b026-bc4effb6c5c2"; //$NON-NLS-1$
  public static final String LA_7_1_1 = "68df53da-c1cc-402c-b8ec-54080384ac5c"; //$NON-NLS-1$
  public static final String LA__LAB_FUNCTION_1 = "02f0848d-2783-4079-b392-96b837442a54";
  public static final String LA__LAB_FUNCTION_2 = "c0f05ce7-ca4d-4994-aaee-5c767e482448";
  public static final String LA__LAB_FUNCTION_5 = "eb928208-79df-4373-ac5d-a207f4c495ff";
  public static final String LA__LAB_FUNCTION_3 = "a11c69aa-21bd-449c-9f43-ae138ec00974";
  public static final String LA__LAB_FUNCTION_4 = "312a5ed0-b4f2-410e-b3e1-5216e84e1346";
  public static final String LA__LAB_FUNCTION_GATHER = "a03d4ace-183f-4bc8-a2e8-056c46ec69a0";
  public static final String LA__LAB_FUNCTION_ROUTE = "818e7a80-b3de-4b3c-8fb8-3214aca2cccf";
  public static final String LA__LAB_FUNCTION_DUPLICATE = "3b831476-45e5-4985-b70c-e9c8e8692437";
  public static final String LA__LAB_FUNCTION_SELECT = "250a9d36-a0dc-408e-8edd-bf91fe670d17";
  public static final String LA__LAB_FUNCTION_SPLIT = "9bdd1c39-f9f0-4c0c-a2a5-270458e0629b";
  public static final String LA__LAB_COMPONENT_PORT_1 = "2c64e0ff-1d69-4348-acc7-ad8ee3d731a2";
  public static final String LA__LAB_LOGICAL_FUNCTION1_FOP1 = "a52fa49d-7ce5-47ed-8083-46bc64052b75";
  public static final String LA__LAB_LOGICAL_FUNCTION1_FIP1 = "2abe9989-bc2f-4c1d-b712-dc6c947ee57d";
  public static final String LA__LAB_LOGICAL_FUNCTION2_FIP1 = "65412419-8651-4efb-ab7c-95c4899b7a4d";
  public static final String LA__LAB_LOGICAL_FUNCTION2_FIP2 = "93940fda-cbff-4077-a70f-893439ff4a1c";
  public static final String LA__LAB_LOGICAL_FUNCTION3_FOP1 = "8bba9fda-9e88-4bbc-8dd9-3d37c86d6071";
  public static final String LA__LAB_LOGICAL_FUNCTION3_FIP1 = "08db5306-7dfd-4536-817e-e73c51680c58";
  public static final String LA__LAB_COMPONENT_PORT_A1_CP1 = "2c64e0ff-1d69-4348-acc7-ad8ee3d731a2";
  public static final String LA__LAB_COMPONENT_PORT_A2_CP1 = "80df6258-ebfd-4cee-8771-1ac8a254fd1a";
  public static final String LA__LAB_COMPONENT_PORT_A3_CP1 = "23c30e2c-88a5-41a2-8410-8352dea321b2";
  public static final String LA__LAB_COMPONENT_PORT_OUT_A3_CP1 = "0c756812-b0c7-4e81-b22f-ddda620e759e";
  public static final String LA__LAB_COMPONENT_EXCHANGE_C1 = "6a2a8c14-a743-464e-9e5b-7cf9b39bdc56";
  public static final String LA__LAB_PHYSICAL_PORT_A1_PP1 = "068e3b40-e980-4801-8337-5041c2a377e6";
  public static final String LA__LAB_PHYSICAL_PORT_A2_PP1 = "fff450d8-2760-4672-a390-4093b2f79721";
  public static final String LA__LAB_PHYSICAL_PORT_A2_PP3 = "eaa53914-b3a5-49b4-9483-041b1e890c69";
  public static final String LA__LAB_PHYSICAL_PORT_A3_PP1 = "1b7cc209-7c72-4774-adfe-97f578e7971e";
  public static final String LA__LAB_PHYSICAL_LINK_PL1 = "343a04f9-1a83-4c09-b712-7cf783899849";
  public static final String LA__LAB_PHYSICAL_PATH1 = "70b81a6e-1ef4-42d5-b94d-838068cd7658";
  public static final String LA__LAB_COMPONENT_PORT_LOGICAL_SYSTEM_CP1 = "5a4ce002-7f24-4d62-a43d-b890afb6cae6";
  public static final String LA__LAB_COMPONENT_PORT_LC1_CP2 = "f5838490-ddc0-4410-aa96-b850f49e2aa9";
  public static final String LA__DATA = "cb34448a-cdbf-46af-b02d-56303ca5e0ce";
  public static final String LA__LAB_CONSTRAINT = "7d41c211-64c9-42bf-8c5b-71ac8f4a6e53";
  public static final String LA__LAB_COMPONENT_PORT_ALLOCATION = "4d2c1505-c859-4957-9ab0-9e89254f7668";
  public static final String LA__LAB_FUNCTIONAL_EXCHANGE1 = "b03f7d07-9f37-4982-a9b0-248d83ed48fe";
  public static final String LA__LAB_FUNCTIONAL_EXCHANGE4 = "48a5e076-40d1-497f-8610-c6f24d818cf5";
  public static final String LA__LAB_FUNCTIONAL_CHAIN1 = "9cabe4bb-cd04-47b9-83b3-29ed69ac87e0";
  public static final String LA__LAB_PROPERTY_VALUE1 = "cd08fe35-9830-494c-96b8-0efe57d4d502";
  public static final String LA__LAB_PROPERTY_VALUE_GROUP1 = "f5315f6e-c1a2-4887-9597-017ed3d15a2e";
  public static final String LA__CR_FUNCTION_SCENARIO1 = "5409a0c1-6386-48a2-bad1-8e77ae2940aa";
  public static final String LA__MODE1 = "8ca06700-2684-49ef-8edb-85360a4f7bbb";
  public static final String LA__STATE1 = "d2d85863-2140-4cfc-8a87-a43bb9affe27";
  public static final String LA__LAB_PHYSICAL_LINK_CATEGORY1 = "08d5ae0a-83d1-40fb-bec2-8beea4b86219";
  public static final String LA__LAB_EXCHANGE_CATEGORY1 = "cfc7de31-fef6-46b7-adf7-3bf6469db536";
  public static final String LA__LAB_EXCHANGE_CATEGORY4 = "eca4de89-fa15-4d1c-b9ee-5b9395b03117";
  public static final String LA__LAB_COMPONENT_EXCHANGE_CATEGORY1 = "60cf855b-4356-41a2-9ffc-5974f481df9f";
  public static final String LA__LAB_STRUCTURE_DIAGRAM = "[LAB] Structure";
  public static final String LA__LAB_SEQUENCELINK1 = "5ef1e40c-891e-4d30-bc69-a90e913eb290";
  public static final String LA__LAB_SEQUENCELINK2 = "59418289-2d2f-4119-8b29-2d2977b2f125";
  public static final String LA__LAB_CONTROLNODE = "c57aa54c-8777-4e2c-bbe1-7086385409e2";
  public static final String LA_7_1 = "09441ac0-4fa8-42c6-8c73-a27d6602b8ed"; //$NON-NLS-1$
  public static final String LA_7_1_PART = "f4e0c504-fc0c-4d0d-b10e-5227cfa0d28f"; //$NON-NLS-1$
  public static final String LA_STRUCTURE = "f98a5409-24f8-4207-bb68-38b721762d2f"; //$NON-NLS-1$
  public static final String LA_7 = "a2673077-7c94-4e97-8261-a9c676aa4b79"; //$NON-NLS-1$
  public static final String LA_7_PART = "16a1fba7-3672-4f4c-85f2-29b870344e2c"; //$NON-NLS-1$
  public static final String LA_LC_1_1 = "1f6b0cc6-e789-4df0-836d-3130bd55e8c4";
  public static final String LA_LA_7_1_1_PART = "0ad59536-7a71-4698-acc6-527aeaccf49b";
  public static final String LC_1_1 = "d4822e98-c68c-4e29-a795-3df5fa98cca9"; //$NON-NLS-1$
  public static final String LC_1_PART = "c30f774e-9dd2-406e-a611-14f59ebcb96c"; //$NON-NLS-1$
  public static final String PC_3_PC_1_PART = "79a754d4-99a4-4c7f-857d-aec9c0388232"; //$NON-NLS-1$
  public static final String PC_4 = "850157c3-5c8f-4c6a-a1bf-29bb146c03cc"; //$NON-NLS-1$
  public static final String PC_4_PC_1 = "b8c583bc-5409-4891-9779-552839d3cc6f"; //$NON-NLS-1$
  public static final String PC_3_1 = "630a2f89-79c9-4b00-8851-5f71ef48b740"; //$NON-NLS-1$
  public static final String PC_7 = "e6934a0a-bc02-4b96-ae58-cf0fdd326e58"; //$NON-NLS-1$
  public static final String PC_10 = "7af9dc10-385f-4b61-97b8-4f4a1a88e700"; //$NON-NLS-1$

  // pa
  public static final String PA_STRUCTURE = "aea3f1a1-8d8a-4b5f-ae22-6835b6877895";
  public static final String PA__PAB_PHYSICAL_SYSTEM = "6117356a-8b31-480f-a1ed-07e8da164fbd";
  public static final String PA__PAB_DIAGRAM = "[PAB] Physical System";
  public static final String PA__PAB_DIAGRAM_PC2 = "[PAB] PC 2";
  public static final String PA__PAB_PHYSICAL_PORT_PP1 = "aacd2b79-5511-4de6-b4aa-428d779c28d3";
  public static final String PA__PAB_PHYSICAL_COMPONENT_PC1 = "e1890736-a194-4a4b-8da3-20a4509f3b38";
  public static final String PA__PAB_COMPONENT_PC1 = "f2cb610a-e6a1-4001-9d3e-d9108d6d7091";
  public static final String PA__PAB_COMPONENT_PC2 = "6f03c5ec-9bc4-4ba9-b162-e2b37e757d94";
  public static final String PA__PAB_COMPONENT_PC3 = "5d473c23-8048-400b-97c0-bc8f6b1e58a3";
  public static final String PA__PAB_COMPONENT_PC3_1 = "ce826b8b-19e8-4f83-89cc-74f65b553f79";
  public static final String PA__PAB_COMPONENT_PC3_2 = "79a754d4-99a4-4c7f-857d-aec9c0388232";
  public static final String PA__PAB_COMPONENT_PC3_2_PORT_PC1 = "5330e600-e77a-4bfd-beac-206621b6419c";
  public static final String PA__PAB_COMPONENT_PC4 = "7f2f46d0-9e76-418c-bd18-1d6845221d4b";
  public static final String PA__PAB_COMPONENT_PC6_PART = "77ea8311-e10e-4f70-86ff-bf3260777830";
  public static final String PA__PAB_COMPONENT_PC6 = "0cbac486-3671-45dd-890b-c04d4b226bc6";
  public static final String PA__PAB_COMPONENT_PC7 = "e6934a0a-bc02-4b96-ae58-cf0fdd326e58";
  public static final String PA__PAB_COMPONENT_PC7_PART = "b61cce74-4df1-4c35-b52f-107a1a41236e";
  public static final String PA__PAB_COMPONENT_PC8 = "fdacc723-f8bc-4a99-b678-4c72d24098a9";
  public static final String PA__PAB_COMPONENT_EXCHANGE_1 = "6dde609b-0f07-48fd-9c23-1f991a8f59d6";
  public static final String PA__PAB_NAME_COMPONENT_EXCHANGE_1 = "C 1";
  public static final String PA__PAB_FUNCTION_1 = "7f785891-6327-4536-a532-581c0c46980c";
  public static final String PA__PAB_FUNCTION_2 = "1b2adc09-b471-47d0-bfea-b40c4a1dab49";
  public static final String PA__PAB_FUNCTION_3 = "1a21afe9-0f85-48ff-9ee2-d2e0c070702d";
  public static final String PA__PAB_FUNCTION_4 = "8f1e3963-0e69-4f44-a69c-d410228e2587";
  public static final String PA__PAB_FUNCTION_12 = "26813b22-09dd-4a07-abf4-79d8761c0287";
  public static final String PA__PAB_FUNCTION_13 = "3611c374-dca6-4d3c-ae1b-c2fbb4c26167";
  public static final String PA__PAB_FUNCTION_GATHER = "70e548dc-48d0-48a3-bb13-392b3dcb96ba";
  public static final String PA__PAB_FUNCTION_ROUTE = "f454586e-b814-4123-9cf4-d119a8853e2d";
  public static final String PA__PAB_FUNCTION_DUPLICATE = "818a0f5a-9e4e-4dc2-8d25-6ab01e8f90eb";
  public static final String PA__PAB_FUNCTION_SELECT = "dcb2a213-18bf-4f25-9589-7649d5d0f1cb";
  public static final String PA__PAB_FUNCTION_SPLIT = "8bc04b8e-7bd6-4b29-8cec-e512d5fdd122";
  public static final String PA__PAB_COMPONENT_PORT_1 = "7a718948-aa24-42ab-b057-80327eb1da43";
  public static final String PA__PAB_FUNCTION_PORT_1 = "e9d52a41-5a6c-4943-9d60-2c9e8321207c";
  public static final String PA__PAB_COMPONENT_PORT_PC3_CP1 = "7a718948-aa24-42ab-b057-80327eb1da43";
  public static final String PA__PAB_COMPONENT_PORT_PC3_1_CP1 = "893cb5bf-e42b-4f69-bf1c-3e70bc3996bb";
  public static final String PA__PAB_COMPONENT_PORT_PC4_CP1 = "c668b546-0d61-4ccc-ae08-d694406d1f2c";
  public static final String PA__PAB_COMPONENT_PORT_PC7_CP1 = "6319edba-0886-46b6-8acb-80de02b0e9ba";
  public static final String PA__PAB_COMPONENT_PORT_OUT_PC7_CP1 = "c682884c-7103-4751-ba15-3e1e847b6833";
  public static final String PA__PAB_PHYSICAL_PORT_PC1_PP1 = "aacd2b79-5511-4de6-b4aa-428d779c28d3";
  public static final String PA__PAB_PHYSICAL_PORT_PC2_PP1 = "b424bc65-34a8-419a-97c5-e65ee3f69ca9";
  public static final String PA__PAB_PHYSICAL_PORT_PC2_PP4 = "4ed046fe-dba6-476e-856f-06a9250cf3d8";
  public static final String PA__PAB_PHYSICAL_PORT_PC6_PP1 = "64995914-ab5d-45c4-b09b-85d147efa88c";
  public static final String PA__PAB_PHYSICAL_LINK_PL1 = "6b7f564b-580d-445b-ac86-43a0be249007";
  public static final String PA__PAB_PHYSICAL_PATH1 = "e1f1ad01-c08a-4cbb-bb7b-a7f364098608";
  public static final String PA__PAB_PHYSICAL_FUNCTION1_FOP1 = "e9d52a41-5a6c-4943-9d60-2c9e8321207c";
  public static final String PA__PAB_PHYSICAL_FUNCTION1_FIP1 = "cc6b1f5a-06f9-4556-9bb2-82f62adbca67";
  public static final String PA__PAB_PHYSICAL_FUNCTION2_FIP1 = "653fdd80-43bf-4704-a9f3-54a76b22c2bf";
  public static final String PA__PAB_PHYSICAL_FUNCTION2_FOP1 = "7ff702ec-0c47-4047-9a67-1869fb05b67b";
  public static final String PA__PAB_PHYSICAL_FUNCTION2_FIP2 = "eaec1b08-c8c4-449f-bb73-c106fe3d2414";
  public static final String PA__PAB_PHYSICAL_FUNCTION3_FOP1 = "afce60a0-18a8-4a71-8230-38af597d35fb";
  public static final String PA__PAB_PHYSICAL_FUNCTION3_FIP1 = "ad614c1c-b31c-483c-8fa5-33fc3af6f059";
  public static final String PA__PAB_FUNCTIONAL_EXCHANGE1 = "0e536993-3ef6-4061-9c59-6292a1b0a837";
  public static final String PA__PAB_FUNCTIONAL_EXCHANGE2 = "0e536993-3ef6-4061-9c59-6292a1b0a837";
  public static final String PA__PAB_FUNCTIONAL_EXCHANGE5 = "7f504ddd-e8ce-435c-8cea-0b602f575f8a";
  public static final String PA__PAB_CONSTRAINT = "27df05ef-baea-43c7-af35-ea0f2ce2ebf9";
  public static final String PA__PAB_PHYSICAL_ACTOR1 = "053143f1-d4fe-4f85-8f21-58720afa63db";
  public static final String PA__PAB_COMPONENT_PORT_ALLOCATION = "663535ce-cc83-4e46-a1b4-9a0747810064";
  public static final String PA__PAB_PORT_ALLOCATION = "39c9d81f-5e19-46aa-a65d-fdda80f3ec93";
  public static final String PA__PAB_FUNCTIONAL_CHAIN1 = "6452654d-c7b2-4359-be47-1544eaeca639";
  public static final String PA__PAB_FUNCTIONAL_CHAIN2 = "c06f9196-de5f-43dc-baec-a75faf60fdfe";
  public static final String PA__PAB_PROPERTY_VALUE1 = "19925880-583c-45f4-b26b-934b462bb2c7";
  public static final String PA__PAB_PROPERTY_VALUE_GROUP1 = "195f8eb3-f2c3-4c55-b921-8e71bc519de4";
  public static final String PA__PAB_PHYSICAL_LINK_CATEGORY1 = "2303dc43-b8fc-4bcb-a553-a9d4d63cef45";
  public static final String PA__PAB_EXCHANGE_CATEGORY1 = "0f857490-75f7-4cb8-97bf-cd3e57a669fa";
  public static final String PA__PAB_EXCHANGE_CATEGORY5 = "eb250acb-2da0-48df-b44a-0a1b7acfa645";
  public static final String PA__PAB_COMPONENT_EXCHANGE_CATEGORY1 = "1dabc95c-1980-4905-9045-98dcc4a1c7f3";
  public static final String PA__PORT_PP2 = "c17908ae-66bd-4020-8929-6e9487cb6a6e";
  public static final String PA__PORT_PP1 = "aacd2b79-5511-4de6-b4aa-428d779c28d3";
  public static final String PA__PAB_SEQUENCELINK1 = "9dd2e36f-40b9-4704-b331-838aeff78297";
  public static final String PA__PAB_SEQUENCELINK2 = "5258be94-5776-4b42-9465-79d2c9f8598b";
  public static final String PA__PAB_CONTROLNODE = "c93b8b1f-6d36-4ff9-84fa-0168409967dd";

  // Show elements from scenarios
  public static final String PA__SCENARIO_FS1 = "f1b164d3-ee4b-420e-86c1-75da2b0070eb";
  public static final String PA__PAB_PHYSICAL_ACTOR2 = "ffc0b9bd-b37b-409b-a18f-fa7f885a1b30";
  public static final String PA__PAB_PHYSICAL_FUNCTION5 = "45213fd7-fe1d-4bc9-a4f3-51a446ad5087";
  public static final String PA__PAB_PHYSICAL_LINK2 = "87c7e4d1-86ec-46da-a831-ff37350b1d2d";
  public static final String PA__PAB_PHYSICAL_COMPONENT9 = "e3c9fd95-11cf-4c31-ae38-48aaf549da49";
  public static final String PA__PAB_PC_COMPONENT10 = "c62d92e7-d689-494a-ba07-b8587c132e66";
  public static final String PA__PAB_PHYSICAL_FUNCTION6 = "18a92b85-23cb-45c5-9724-18b782981680";
  public static final String PA__PAB_FUNCTIONAL_EXCHANGE_ES1 = "23b7f33e-a30c-4a77-95dd-962fa81914b7";
  public static final String PA__SCENARIO_ES1 = "5f925d90-d561-46e3-936b-1d264efddd15";
  public static final String PA__MODE1 = "b9f8ebc1-c314-4537-8ef0-efd1b84a1199";
  public static final String PA__STATE1 = "f899ac17-0514-4161-8a25-06cf234dd2f9";

  // for drag and drop
  public static final String PA__PC_PART_PC2 = "6f03c5ec-9bc4-4ba9-b162-e2b37e757d94";
  public static final String PA__PC_PART_PC2_1 = "49f7adeb-3637-4d1e-a216-a9a511a35ccf";
  public static final String PA__PC_PART_PC14 = "d74876b1-f561-46ae-8ee7-638f2c9cacea";

  @Override
  protected String getRequiredTestModel() {
    return "XABDiagrams";
  }
}
