/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.recrpl.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.recrpl.ju.RecRplTestCase;

public abstract class WholeContent extends RecRplTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "whole-content-actor" }); //$NON-NLS-1$
  }
  public static final String LIBRARY = "e7ef8215-5fa8-4dd3-8048-c99e33413602"; //$NON-NLS-1$
  public static final String LIBRARY_DEPENDENCIES = "a9dce2a9-ed50-41ed-9a1b-b837afb10398"; //$NON-NLS-1$
  public static final String PROGRESSSTATUS = "13ce31e4-ca5e-4546-be29-d89993a74f76"; //$NON-NLS-1$
  public static final String DRAFT = "166131c7-3fbe-4d91-9223-1f6d4def01d0"; //$NON-NLS-1$
  public static final String TO_BE_REVIEWED = "f0d6a2ed-b4cd-441c-9ee4-fb32b250ed57"; //$NON-NLS-1$
  public static final String TO_BE_DISCUSSED = "44ca4400-5724-4ff3-bebe-c7ce9c9eceb3"; //$NON-NLS-1$
  public static final String APPROACH = "2963e1a3-67ec-44ac-8399-9c48a13d9035"; //$NON-NLS-1$
  public static final String OPERATIONAL_ANALYSIS = "6552eb3d-eb81-4ff8-8786-7eb778861485"; //$NON-NLS-1$
  public static final String OPERATIONAL_ACTIVITIES = "8331d38e-2914-46f0-8eab-6dcd94522545"; //$NON-NLS-1$
  public static final String ROOT_OPERATIONAL_ACTIVITY = "dc5df755-3318-45e5-9786-05446557a100"; //$NON-NLS-1$
  public static final String OPERATIONAL_CAPABILITIES = "beeb45ab-9132-4c16-af13-cc09446f8514"; //$NON-NLS-1$
  public static final String OA_INTERFACES = "06bd2554-8647-4d98-bf96-94dd7e42f33c"; //$NON-NLS-1$
  public static final String OA_DATA = "5e68265e-3e23-4477-bd7e-401e2fc71232"; //$NON-NLS-1$
  public static final String ROLES = "fb08647a-5d31-4f80-b241-d0ea26e29e2d"; //$NON-NLS-1$
  public static final String OPERATIONAL_ENTITIES = "fcc2fbba-e9ce-47f0-b124-d5399a527e94"; //$NON-NLS-1$
  public static final String SYSTEM_ANALYSIS = "993f56a0-910d-4040-834f-fe656485ad85"; //$NON-NLS-1$
  public static final String SYSTEM_FUNCTIONS = "956deb66-eb0d-4095-a8ee-b7b115f599cf"; //$NON-NLS-1$
  public static final String ROOT_SYSTEM_FUNCTION = "98f178b5-c403-4116-9f37-4e4ca318146b"; //$NON-NLS-1$
  public static final String SA_CAPABILITIES = "91f5d888-6c88-4546-9d2b-2f6db03348d8"; //$NON-NLS-1$
  public static final String SA_INTERFACES = "51d93507-e5b3-4da6-807a-48e57cd57e2f"; //$NON-NLS-1$
  public static final String SA_DATA = "168c0cc3-2571-40a6-9ff2-5b8764dd07a1"; //$NON-NLS-1$
  public static final String PREDEFINED_TYPES = "405fc060-e0f9-44f4-80d0-c63535d1481b"; //$NON-NLS-1$
  public static final String BOOLEAN = "b623093d-da41-4963-8461-db5ca43dc687"; //$NON-NLS-1$
  public static final String BYTE = "4657174a-762c-4803-ac9c-52769e074500"; //$NON-NLS-1$
  public static final String CHAR = "72213fd6-d130-42b4-aad2-b0fa779f0034"; //$NON-NLS-1$
  public static final String DOUBLE = "e9006d87-cd72-4cae-967e-90f281413ac0"; //$NON-NLS-1$
  public static final String FLOAT = "663f182f-6b38-4fdf-b7b2-3d4bb702d47b"; //$NON-NLS-1$
  public static final String HEXADECIMAL = "85c3e80d-3353-421e-a6c8-fed6d957744f"; //$NON-NLS-1$
  public static final String INTEGER = "85459e31-e18e-45b5-a041-0ac01868fea3"; //$NON-NLS-1$
  public static final String LONG = "e68f35b9-43e5-41f1-9383-dddd77a3c0b2"; //$NON-NLS-1$
  public static final String LONGLONG = "bd9171fc-1e0e-4c33-b65f-67edcaacac13"; //$NON-NLS-1$
  public static final String SHORT = "234bcae7-dd89-4e09-ac2d-e00a3531d73a"; //$NON-NLS-1$
  public static final String STRING = "8b4663ea-8995-4b87-b4d2-3c619dd3e7ad"; //$NON-NLS-1$
  public static final String UNSIGNEDINTEGER = "78670738-b8ae-4a9c-994e-4ca630d34e06"; //$NON-NLS-1$
  public static final String UNSIGNEDSHORT = "7d7b64a9-c840-4dac-806f-4481e894b0fe"; //$NON-NLS-1$
  public static final String UNSIGNEDLONG = "50ea3e6a-78e1-4b6d-a7a3-5faccdc199b8"; //$NON-NLS-1$
  public static final String UNSIGNEDLONGLONG = "ba239e23-4835-410c-958f-e76c48bc66f9"; //$NON-NLS-1$
  public static final String SA_STRUCTURE = "3eac8a14-7797-41eb-a015-5ae8bcf6e001"; //$NON-NLS-1$
  public static final String SYSTEM = "569440d9-748d-413b-9c13-60ba89a172e4"; //$NON-NLS-1$
  public static final String SYSTEM_PART = "f81e4f65-01f1-4af6-bcd0-fbb601e5b094"; //$NON-NLS-1$
  public static final String SA_2_PART = "6224776e-8c49-4642-843d-126c6d25a64b"; //$NON-NLS-1$
  public static final String SA_2 = "dc2e1074-e8a8-40d2-a088-6f4c68c7d547"; //$NON-NLS-1$
  public static final String MISSIONS = "b56985b0-3df3-4dd5-b21f-5fa620869727"; //$NON-NLS-1$
  public static final String LOGICAL_ARCHITECTURE = "683d4bf5-f989-4031-b4e3-395256c3725b"; //$NON-NLS-1$
  public static final String LOGICAL_FUNCTIONS = "f8cdfff4-60d2-41c6-8e94-122c09928db5"; //$NON-NLS-1$
  public static final String ROOT_LOGICAL_FUNCTION = "e544c3f9-e816-42f6-95f9-4e343e41bd59"; //$NON-NLS-1$
  public static final String LA_CAPABILITIES = "699493b5-5ce4-48f3-989b-d3f3f31e49c2"; //$NON-NLS-1$
  public static final String LA_INTERFACES = "345645e4-b933-4454-b68e-f3105d351fbb"; //$NON-NLS-1$
  public static final String LA_DATA = "e17629c6-314a-4183-a03c-4111b98bb4ff"; //$NON-NLS-1$
  public static final String LA_STRUCTURE = "dd16bace-b594-40b7-8ac5-dda8775a6c97"; //$NON-NLS-1$
  public static final String LOGICAL_SYSTEM_PART = "fd1a48a7-9d01-4431-8892-516ad5b978e7"; //$NON-NLS-1$
  public static final String LOGICAL_SYSTEM = "90665f5f-19e0-499d-bf02-a467fe8692ba"; //$NON-NLS-1$
  public static final String PHYSICAL_ARCHITECTURE = "c2761a33-631e-46e3-bad7-1fafdb0db107"; //$NON-NLS-1$
  public static final String PHYSICAL_FUNCTIONS = "17154222-d99d-4911-9605-9216d2abeac1"; //$NON-NLS-1$
  public static final String ROOT_PHYSICAL_FUNCTION = "2e608e2a-953b-4ae5-b941-9c52efb02951"; //$NON-NLS-1$
  public static final String PA_CAPABILITIES = "6b5a2b6d-a6fc-4b34-9a97-a44c4693358e"; //$NON-NLS-1$
  public static final String PA_INTERFACES = "d652277d-37be-4279-80d0-903f7777c4b7"; //$NON-NLS-1$
  public static final String DATA = "b132faf5-389d-4258-b772-8e2a1df261b7"; //$NON-NLS-1$
  public static final String PA_STRUCTURE = "297909b2-6b09-44cd-afa6-9807540d5abb"; //$NON-NLS-1$
  public static final String PHYSICAL_SYSTEM = "eeed3934-d206-477b-b9e4-747532963b38"; //$NON-NLS-1$
  public static final String PHYSICAL_SYSTEM_PART = "89366e4f-d83f-443e-af53-854d148e3c91"; //$NON-NLS-1$
  public static final String EPBS_ARCHITECTURE = "cf5c1f25-53e7-4b8a-8dcc-0911a6a429d2"; //$NON-NLS-1$
  public static final String CAPABILITIES = "a638cc10-9c95-4844-b9fa-b52da61f6a14"; //$NON-NLS-1$
  public static final String EPBS_STRUCTURE = "5855d044-a45c-4b00-a7db-b6e554d9c1cf"; //$NON-NLS-1$
  public static final String EPBS_SYSTEM_PART = "cb286b7d-9115-480a-9bb3-6ac65197719d"; //$NON-NLS-1$
  public static final String EPBS_SYSTEMCI_SYSTEM = "8d14f4d3-dac8-4cb1-9c10-d08845c27f35"; //$NON-NLS-1$
}