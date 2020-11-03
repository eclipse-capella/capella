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
package org.polarsys.capella.test.diagram.tools.ju.reuse;

import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;

public abstract class ReuseOfComponentsProject extends AbstractDiagramTestCase {

  public static final String SA__SAB_DIAGRAM = "[SAB] System";
  public static final String LA__LAB_DIAGRAM = "[LAB] Logical System";
  public static final String PA__PAB_DIAGRAM = "[PAB] Physical System";
  public static final String EPBS__EAB_DIAGRAM = "[EAB] System";

  public static final String SA__ACTOR_SA_1 = "45d529c5-c4c7-4c0b-b012-1add54cc7938";
  public static final String SA__ACTOR_SA_1_1 = "8d30491d-f27c-4b0e-8fc6-d35ea69a284b";
  public static final String SA__ACTOR_SA_2 = "f537cc2c-3365-4f07-be56-4ce5b98c1f73";
  public static final String SA__ACTOR_SA_3 = "1cf87c8c-924f-4ffb-81af-f0bcf155f722";

  public static final String SA__PART_ACTOR_SA_1 = "76b9a154-208d-48c1-aa93-e2f009078640";
  public static final String SA__PART_ACTOR_SA_1_1 = "3f997813-c6b1-4309-a063-1fa8f298c7d5";

  public static final String LA__LC_1 = "9e0ed8ef-1aaa-4622-9d40-83cab16957d0";
  public static final String LA__LC_1_1 = "5250c890-0bb6-4436-a68c-d6664214fc19";
  public static final String LA__LC_2 = "7ecfa82f-33c7-43f8-8b91-49481db0c7d9";
  public static final String LA__LC_3 = "47890135-af9c-4b61-9cc7-b1020315c5e0";

  public static final String LA__PART_1_LC_1 = "bbe5a216-d1f1-4adc-bb1e-3b9fa4129bf8";
  public static final String LA__PART_1_LC_1_1 = "bf549818-ef6c-477c-9854-65b1ee40e64e";
  public static final String LA__PART_2_LC_2 = "0d7a1659-3e76-465b-bd02-8b66ee3815dc";
  public static final String LA__PART_3_LC_3 = "b21941f4-41dc-439d-aa14-8c213a157c98";
  public static final String LA__REUSE_PART_2_LC_2_LC_1 = "a9516520-a83e-4a20-a637-defc0a0ea1a5";

  public static final String LA__LA_1 = "1b60b9a3-b8cb-4582-ad47-5c411f72c120";
  public static final String LA__LA_1_1 = "9cf2e682-efd2-4eb4-bd11-6c90f14bf55f";
  public static final String LA__LA_2 = "ac4667ce-d958-4237-8380-5b4449eafafe";
  public static final String LA__LA_3 = "3054d7ad-6fe0-4a09-b896-9378a7bedaa6";

  public static final String LA__PART_1_LA_1_1 = "1840c7a0-fcce-4816-8b65-4a8fffe77b68";
  public static final String LA__PART_2_LA_1 = "276952a7-c1da-4884-8df8-926287f705a2";
  public static final String LA__PART_3_LA_2 = "4e814cc9-e9e5-4ee5-8033-c0750b82c06d";
  public static final String LA__REUSE_PART_2_LA_2_LA_1 = "652a978f-9c4a-43e9-b0dc-38be90edb36a";

  public static final String PA__NODE_PC_1 = "d3d1e2e3-dcfa-4a86-8c43-a1e0ab43801f";
  public static final String PA__NODE_PC_2 = "ebe49c48-ff28-46ca-9cc2-80566008dc94";
  public static final String PA__NODE_PC_3 = "ecf07d2a-5265-421c-9123-f31e510c6bc8";
  public static final String PA__NODE_PC_3_1 = "b3627946-bc24-4725-93a5-d3148b807727";

  public static final String PA__PART_1_NODE_PC_1 = "cb5a5d2e-3f48-4164-8e76-f89aadd93f2a";
  public static final String PA__PART_2_NODE_PC_2 = "a9667785-a8f5-4102-bf82-2c23a59c2aca";
  public static final String PA__PART_3_NODE_PC_3 = "737667c5-d037-4cef-8adb-a13561122308";
  public static final String PA__PART_1_NODE_PC_3_1 = "7d2a2522-078b-40d7-b618-67a0ac2e0b64";

  public static final String PA__BEHAVIOR_PC_5 = "7523a736-ffc9-4190-ba72-2860bef5be75";
  public static final String PA__BEHAVIOR_PC_5_1 = "45750fd1-8e07-4641-927d-f93a14b4a1e1";
  public static final String PA__BEHAVIOR_PC_6 = "f303439d-9778-4d90-ac8b-a4695d5bb800";

  public static final String PA__PART_4_BEHAVIOR_PC_5 = "205b6df3-362d-4840-a59c-f35f565ea8a4";
  public static final String PA__PART_1_BEHAVIOR_PC_5_1 = "97cb7e7d-d1f6-4e41-a324-fee4bc96eb5e";
  public static final String PA__PART_6_BEHAVIOR_PC_6 = "2649e06c-22d9-4eec-9c44-668112286cae";

  public static final String PA__ACTOR_PA_1 = "80be6ca9-6524-4fb9-ad41-5d8cef103009";
  public static final String PA__ACTOR_PA_2 = "2f1542b7-694f-46d0-9b08-ce383dd6872a";
  public static final String PA__ACTOR_PA_2_1 = "8049447b-c041-40e9-8e7e-d2bca08e11bc";
  public static final String PA__ACTOR_PA_3 = "6707fd8b-7750-4d73-a599-efc2a62ce9c6";

  public static final String PA__PART_ACTOR_PA_1 = "7b84bb1a-7ff1-4c19-a621-e74d469d3ebc";
  public static final String PA__PART_ACTOR_PA_2 = "3c0704e0-0123-4889-893e-6a9b5109ece1";
  public static final String PA__PART_ACTOR_PA_2_1 = "1548e6bb-0eaf-40d7-b08c-9279efb7cbb8";

  public static final String EPBS__COTSCI_CI = "9d5ce82d-3c44-46a8-8a0f-c168548ad8c7";
  public static final String EPBS__CSCI_CI = "6f522b74-7ba4-4b4a-b203-1ebd489fb71d";
  public static final String EPBS__HWCI_CI = "b3c55b95-a45d-4ceb-9e81-d6d0ee34a34a";
  public static final String EPBS__INTERFACE_CI = "7b373908-6a53-49c6-b860-37e6f3390013";
  public static final String EPBS__NDICI_CI = "14f19174-385e-49f1-bfac-321e8209eefb";
  public static final String EPBS__PRIMEITEM_CI = "5a9c60ba-4b0d-43aa-9fb9-bfc73d5fd058";
  public static final String EPBS__SYSTEM_CI = "f46336e4-2255-4bd8-928a-414794969376";

  public static final String EPBS__PART_COTSCI_CI = "587f7eb3-4281-4751-a0a9-3c1459e26a0a";
  public static final String EPBS__PART_CSCI_CI = "979fdd06-1e7d-4b32-9f55-1dc5aa0b4e56";

  @Override
  protected String getRequiredTestModel() {
    return "ReuseOfComponentsModel";
  }
}
