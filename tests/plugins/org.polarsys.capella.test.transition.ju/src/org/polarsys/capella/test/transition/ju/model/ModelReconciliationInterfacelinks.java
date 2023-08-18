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
package org.polarsys.capella.test.transition.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Generated from model/reconciliation.interfacelinks with org.polarsys.capella.core.flexibility.commands.RetrieveIDsConstants
 */
public abstract class ModelReconciliationInterfacelinks extends TopDownTransitionTestCase {

  public static String PROJECT_RECONCILIATION_INTERFACELINKS = "a8a80e78-be72-43e7-8a3f-56cd58930eb0"; //$NON-NLS-1$ 
  public static String PROJECT_RECONCILIATION_INTERFACELINKS__PROJECTAPPROACH = "0fa5cc91-c069-43e0-b932-fc41477d3e71"; //$NON-NLS-1$ 
  public static String RECONCILIATION_INTERFACELINKS = "449d3644-d554-446a-b1bc-f88ae4e0eecf"; //$NON-NLS-1$ 
  public static String OA = "51c7a288-68e1-4c0b-b6d1-9071563c3530"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_ACTIVITIES = "616b9fb7-735a-46c5-ac70-2c4a89e44206"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_ACTIVITIES__ROOT_OA = "58e423a1-1635-4ae1-a9b6-62e3f5394704"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_CAPABILITIES = "504a5cf8-5460-4363-ab09-6427a8015639"; //$NON-NLS-1$ 
  public static String OA__INTERFACES = "66adb845-f5f0-42ab-a173-2fcb6ec6d934"; //$NON-NLS-1$ 
  public static String OA__DATA = "73a3d3c4-49d7-458b-b674-4c70755e9a1a"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_CONTEXT = "8395bf39-6a1c-45b5-8ebe-3090c867d919"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_CONTEXT__PART_OPERATIONAL_ENTITY__OPERATIONAL_ENTITY = "e348c8fe-35c5-41d5-a23e-3cbcd4ae7903"; //$NON-NLS-1$ 
  public static String OA__ROLES = "0d43d6d8-e3e2-4b35-b793-af3835a00f31"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_ENTITIES = "0183c7e2-0e7f-4084-a268-a48d6b13396e"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_ENTITIES__OPERATIONAL_ENTITY = "617595ef-43db-455c-a7b9-5a28ca2681c4"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_ENTITIES__OPERATIONAL_ENTITY__OPERATIONAL_ENTITY_STATE_MACHINE = "d951df10-7247-4a4e-88cc-53e3311e131c"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_ENTITIES__OPERATIONAL_ENTITY__OPERATIONAL_ENTITY_STATE_MACHINE__DEFAULT_REGION = "d2eae359-4719-42da-9674-f9296a888f73"; //$NON-NLS-1$ 
  public static String SA = "991696e2-90ac-4447-91e9-249c7a81563e"; //$NON-NLS-1$ 
  public static String SA__SYSTEM_FUNCTIONS = "171a71a9-c63a-4623-931f-da3ade12e219"; //$NON-NLS-1$ 
  public static String SA__ROOT_SF = "2fd23965-b08f-4356-9c49-ce823e1996de"; //$NON-NLS-1$ 
  public static String SA__ROOT_SF__FUNCTION_REALIZATION_TO_ROOT_OPERATIONAL_ACTIVITY = "70861c52-1276-4143-9b0b-2b8cbdc9fdbc"; //$NON-NLS-1$ 
  public static String SA__CAPABILITIES = "658460f2-76a9-4d90-b64c-f17c62266e7a"; //$NON-NLS-1$ 
  public static String SA__INTERFACES = "d79f743e-b39e-4c50-a6c0-b4e065e9eb7a"; //$NON-NLS-1$ 
  public static String SA__DATA = "50ef854d-7780-4bff-aab6-aac6ceb7518a"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES = "19f9899b-15a9-4761-be7c-37549558c08f"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__BOOLEAN = "1da4b13a-11a4-4b3b-96e3-eb9697e1cf07"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__BOOLEAN__LITERALS_TRUE = "f567c29c-8743-46dd-af1b-a997cf89204d"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__BOOLEAN__LITERALS_FALSE = "cd1903c8-1451-404c-89a4-1afec7cafb58"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__BYTE = "eca26fac-88a3-4016-a28d-ed3f42f92fb9"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__BYTE__MIN = "1cf24ae3-69f6-4bf2-a2cb-1fc80afd1320"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__BYTE__MAX = "3e363a9c-9a8d-41c8-bfd8-898f421bf82d"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__CHAR = "a5f1281f-70f7-4025-bf6d-b30679c84e91"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__CHAR__MINLENGTH = "d42b43ba-bb8a-463d-9047-a8000d634d66"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__CHAR__MAXLENGTH = "397e0086-fc0e-4209-b3b7-203e331fef5f"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__DOUBLE = "f5c81b2e-7ec1-4334-9c85-071629d997b3"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__FLOAT = "45c5e8f6-356c-4585-9b65-e0dda3c3a9aa"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL = "deb89907-6e1e-46b8-9ce9-1fcfe65bb688"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MIN = "282ea880-6dad-468e-95e6-18ad4a4f36b5"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX = "1c52fae0-0a10-4efa-bcba-ad319e0c9d34"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__LEFTOPERAND = "40678697-a534-4766-be92-6a4fd622f4a8"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__LEFTOPERAND__LEFTOPERAND = "57eb3772-a379-46bb-9a93-6d18c9edd928"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__LEFTOPERAND__RIGHTOPERAND = "4c1487e1-346a-47e7-b2c6-3d5bcf29a4d7"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__RIGHTOPERAND = "43eff6c5-9fb2-4de5-975d-fc59f56ce55a"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__INTEGER = "00f31f6e-b40d-4a33-b974-ac146b4bffe9"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__LONG = "c41ed9d3-1584-45c9-a2b3-dbc37c9d3f84"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__LONGLONG = "d36b78f4-8470-455b-88f2-ab446af1135f"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__SHORT = "513d11fb-b467-4cab-b9b6-08259217ffd8"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__STRING = "8770c9f7-ca9d-4b0f-8c2a-1b629bf7fbb8"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__UNSIGNEDINTEGER = "87cce12e-10dc-4bd4-8691-528949f061cc"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__UNSIGNEDINTEGER__MIN = "a1458d6a-e90b-4ccf-83fd-bef4d9bd5ea9"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__UNSIGNEDSHORT = "63d5570a-cf8a-4d6b-9c65-5782c17090e2"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__UNSIGNEDSHORT__MIN = "ff8e0016-e73e-452f-a4f9-f4a2ad6c0de3"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONG = "e4fe43e8-351d-4004-a266-db0d5928f64a"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONG__MIN = "a5bb2c06-aef4-4331-a1e9-0b2926b214f2"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONGLONG = "f63b430e-74af-426f-9788-013e870ce5a5"; //$NON-NLS-1$ 
  public static String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONGLONG__MIN = "8c805085-c421-48ba-9dce-4562b4fb2407"; //$NON-NLS-1$ 
  public static String SA__SYSTEM_CONTEXT = "7b8a4779-5b6a-42bd-ad34-d95653350924"; //$NON-NLS-1$ 
  public static String SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM = "635c5cb9-649c-4ffa-8aee-e44d2c94a2c4"; //$NON-NLS-1$ 
  public static String SA__SYSTEM = "51a4891d-c4e0-4b2c-b984-1bcda6a50ad1"; //$NON-NLS-1$ 
  public static String SA__SYSTEM__SYSTEM_STATE_MACHINE = "cf43f237-4149-418b-a51c-6ac568b403de"; //$NON-NLS-1$ 
  public static String SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION = "8d70a9f5-2546-4df5-aebf-6d0fc59f3dbf"; //$NON-NLS-1$ 
  public static String SA__ACTORS = "9d13245e-234f-498f-8ff4-15793914ab71"; //$NON-NLS-1$ 
  public static String SA__MISSIONS = "bb9ebb5f-51ad-42a5-8110-b126dc41b345"; //$NON-NLS-1$ 
  public static String SA__OPERATIONAL_ANALYSIS_REALIZATION_TO_OPERATIONAL_ANALYSIS = "ddd6eb74-0913-40bf-b9f4-39502f53cd53"; //$NON-NLS-1$ 
  public static String LA = "8f8fc732-4d1e-42b0-9186-f6556f03c623"; //$NON-NLS-1$ 
  public static String LA__LOGICAL_FUNCTIONS = "0cbacaa0-5a68-4e09-93c6-03cda65062cb"; //$NON-NLS-1$ 
  public static String LA__ROOT_LF = "46ba9261-b127-42e6-ae99-551071dc8728"; //$NON-NLS-1$ 
  public static String LA__ROOT_LF__FUNCTION_REALIZATION_TO_ROOT_SYSTEM_FUNCTION = "e37c3a6e-a949-4e64-811f-0095ed8ef96c"; //$NON-NLS-1$ 
  public static String LA__CAPABILITIES = "382fabf4-886d-44a2-b90d-8f4245a29713"; //$NON-NLS-1$ 
  public static String LA__INTERFACES = "7aaea193-6be3-4c80-a2e5-9338d6024e52"; //$NON-NLS-1$ 
  public static String LA__DATA = "808e99db-98f4-434b-8a22-6094d21f1f00"; //$NON-NLS-1$ 
  public static String LA__LOGICAL_CONTEXT = "9212ef42-8b9a-44eb-844a-8e284e3c76d5"; //$NON-NLS-1$ 
  public static String LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM = "d90b9217-b883-49cf-b213-939b6091acbe"; //$NON-NLS-1$ 
  public static String LA__LOGICAL_SYSTEM = "dbe061f7-c93e-4ec6-8343-38725bc1b94a"; //$NON-NLS-1$ 
  public static String LA__IP1 = "a6ac0d6c-210b-4c03-ae00-c81259331793"; //$NON-NLS-1$ 
  public static String LA__IP1__I11 = "b4fda13e-bb96-48f0-afad-9ec689524fb9"; //$NON-NLS-1$ 
  public static String LA__IP1__I12 = "3824ace4-e878-45a0-aff9-12988d34ad6a"; //$NON-NLS-1$ 
  public static String LA__IP1__I13 = "b50cd150-d44a-4280-a7d3-2ef65273a9ca"; //$NON-NLS-1$ 
  public static String LA__IP1__I14 = "0ca2da49-9ecf-43b5-b085-3f0eb04d99c0"; //$NON-NLS-1$ 
  public static String LA__IP1__I15 = "b88786e5-d729-4f03-9b23-2ee569179013"; //$NON-NLS-1$ 
  public static String LA__IP1__I16 = "0267c9b3-ffbd-4c55-a271-c8bc92b80d1c"; //$NON-NLS-1$ 
  public static String LA__PART_LC1__LC1 = "52bc5cdb-0821-4b95-806f-6c9c560aa965"; //$NON-NLS-1$ 
  public static String LA__PART_LC2__LC2 = "57649605-d87c-4abe-a40d-66867167d3ca"; //$NON-NLS-1$ 
  public static String LA__LC1 = "e186e4d6-c769-4e57-af9c-c384f9da4635"; //$NON-NLS-1$ 
  public static String LA__LC1__INTERFACE_USE_TO_I12 = "0e4ca71f-df7c-4f16-9788-31fb2a315e39"; //$NON-NLS-1$ 
  public static String LA__LC1__INTERFACE_USE_TO_I14 = "02a72ca7-49e5-4b0d-bb52-a1e5658bb927"; //$NON-NLS-1$ 
  public static String LA__LC1__INTERFACE_USE_TO_I16 = "fcce218e-add2-45cc-8920-9478fb5b226b"; //$NON-NLS-1$ 
  public static String LA__LC1__INTERFACE_IMPLEMENTATION_TO_I11 = "a41cea44-0279-4918-899a-79d8e7845729"; //$NON-NLS-1$ 
  public static String LA__LC1__INTERFACE_IMPLEMENTATION_TO_I13 = "a3a7425e-f396-4fad-b3c5-2048064b94ca"; //$NON-NLS-1$ 
  public static String LA__LC1__INTERFACE_IMPLEMENTATION_TO_I15 = "bd26283a-2e8b-4f9a-8796-2f1a830cd404"; //$NON-NLS-1$ 
  public static String LA__LC2 = "f1a45921-186b-4f8b-b9bd-4ab0ae272f1d"; //$NON-NLS-1$ 
  public static String LA__LC2__INTERFACE_USE_TO_I12 = "2dead667-87e4-4525-a5f9-08b3729004f9"; //$NON-NLS-1$ 
  public static String LA__LC2__INTERFACE_IMPLEMENTATION_TO_I11 = "6133597c-bb82-4ce4-b887-ed2d4c4b27f3"; //$NON-NLS-1$ 
  public static String LA__SYSTEM_REALIZATION_TO_SYSTEM = "9930baf2-ac39-492f-854b-a380faeed3b1"; //$NON-NLS-1$ 
  public static String LA__ACTORS = "664b4bf5-f102-4b90-927d-2c1cd13f0b8f"; //$NON-NLS-1$ 
  public static String LA__SYSTEM_ANALYSIS_REALIZATION_TO_SYSTEM_ANALYSIS = "b2201929-5aba-4b5c-9a1d-83dec48e01f7"; //$NON-NLS-1$ 
  public static String PA = "a4c45062-1545-420e-92f8-24a0e687fb6f"; //$NON-NLS-1$ 
  public static String PA__PHYSICAL_FUNCTIONS = "193654ac-ad82-44e9-9c28-7b4e95df01af"; //$NON-NLS-1$ 
  public static String PA__ROOT_PF = "ad2dad7e-6f8f-4aa3-a5cb-10d341e6cb07"; //$NON-NLS-1$ 
  public static String PA__ROOT_PF__FUNCTION_REALIZATION_TO_ROOT_LOGICAL_FUNCTION = "050cbedb-040d-4e48-ac07-275e63e0ea20"; //$NON-NLS-1$ 
  public static String PA__CAPABILITIES = "1de79759-58a7-4f2c-b56a-2dfc218ce7da"; //$NON-NLS-1$ 
  public static String PA__INTERFACES = "ef19075e-4915-4b3e-b286-0b6227dec3e6"; //$NON-NLS-1$ 
  public static String PA__DATA = "7a16d500-3bba-4099-943e-30bbfb49fd22"; //$NON-NLS-1$ 
  public static String PA__PHYSICAL_CONTEXT = "df73a552-04d2-4f86-9d97-bba8375cec26"; //$NON-NLS-1$ 
  public static String PA__PHYSICAL_CONTEXT__TRANSFO_LINK_TO_LOGICAL_SYSTEM = "b4940079-e7a5-440b-8fa1-47ccb4f1aa88"; //$NON-NLS-1$ 
  public static String PA__PHYSICAL_CONTEXT__TRANSFO_LINK_TO___TRANSFOUID = "a60ad226-4fc3-47c3-af75-bb2becd0ea14"; //$NON-NLS-1$ 
  public static String PA__PHYSICAL_CONTEXT__PART_PHYSICAL_SYSTEM__PHYSICAL_SYSTEM = "e50aa97c-328b-42e4-ac57-d327f5f7bfa8"; //$NON-NLS-1$ 
  public static String PA__PHYSICAL_SYSTEM = "b65f0e6c-7bb9-45ed-a122-11fe0c000d8f"; //$NON-NLS-1$ 
  public static String PA__TRANSFO_LINK_TO_LC1 = "fd0a8253-40da-46e3-ab33-51a9b3028316"; //$NON-NLS-1$ 
  public static String PA__TRANSFO_LINK_TO_LC1__TRANSFOUID = "00ab7398-97f6-4b0b-bece-3071d7a58d64"; //$NON-NLS-1$ 
  public static String PA__IP1 = "d7291bc7-c63b-4d48-8f5b-ea00d8dd4b48"; //$NON-NLS-1$ 
  public static String PA__IP1__TRANSFO_LINK_TO_IP1 = "792db712-2431-4a9e-bc81-335135d388a3"; //$NON-NLS-1$ 
  public static String PA__IP1__TRANSFO_LINK_TO_IP1__TRANSFOUID = "4bc6271a-8cca-4762-a5c1-70d6b45b4c91"; //$NON-NLS-1$ 
  public static String PA__IP1__PI11 = "6ff4cb1f-af7c-44c3-92cf-a7220bce23d7"; //$NON-NLS-1$ 
  public static String PA__IP1__PI11__LOGICAL_INTERFACE_REALIZATION_TO_I11 = "cee5191d-8d05-40bd-b271-b75823ab7124"; //$NON-NLS-1$ 
  public static String PA__IP1__PI12 = "6a1ea77d-1fca-405e-880e-0e8853d78952"; //$NON-NLS-1$ 
  public static String PA__IP1__PI12__LOGICAL_INTERFACE_REALIZATION_TO_I12 = "b7646ac3-eef9-41bf-84fe-ba12e9fe49bf"; //$NON-NLS-1$ 
  public static String PA__PART_PC1__PC1 = "bee54f88-a5c8-4381-8eb5-f0bbadf0fc01"; //$NON-NLS-1$ 
  public static String PA__PC1 = "ccc0f86e-debf-4194-9fc1-db6697899cfc"; //$NON-NLS-1$ 
  public static String PA__PC1__INTERFACE_USE_TO_PI12 = "0522a707-4bed-475e-b176-77d3cfb10e54"; //$NON-NLS-1$ 
  public static String PA__PC1__INTERFACE_USE_TO_I14 = "01003fa7-59c6-4cc1-a42b-bb83c37013d1"; //$NON-NLS-1$ 
  public static String PA__PC1__INTERFACE_IMPLEMENTATION_TO_PI11 = "63285e53-633f-431a-81b8-dadc2b2f1e75"; //$NON-NLS-1$ 
  public static String PA__PC1__INTERFACE_IMPLEMENTATION_TO_I13 = "854da289-e67b-45bc-acc6-38099a0584ed"; //$NON-NLS-1$ 
  public static String PA__PC1__LOGICAL_COMPONENT_REALIZATION_TO_LC1 = "613522c2-53d6-4c05-a75a-9ddd6cf5abdf"; //$NON-NLS-1$ 
  public static String PA__LOGICAL_COMPONENT_REALIZATION_TO_LOGICAL_SYSTEM = "15bb3aa7-75ed-4b60-9d04-fd54f68a0eb2"; //$NON-NLS-1$ 
  public static String PA__ACTORS = "987abf3c-cbea-45e5-93ab-f2f47464a054"; //$NON-NLS-1$ 
  public static String PA__LOGICAL_ARCHITECTURE_REALIZATION_TO_LOGICAL_ARCHITECTURE = "ace1d619-0766-4074-8fa4-d0b28d48c75a"; //$NON-NLS-1$ 
  public static String EPBS = "8a759974-e706-4146-a069-2697ac259b0a"; //$NON-NLS-1$ 
  public static String EPBS__CAPABILITIES = "0fcaf0ef-eca2-4d05-a5be-c4c00c55c8ba"; //$NON-NLS-1$ 
  public static String EPBS__EPBS_CONTEXT = "cf1eeb61-9543-4d34-bb75-f0cd1a472b20"; //$NON-NLS-1$ 
  public static String EPBS__EPBS_CONTEXT__PART_SYSTEM__SYSTEM = "f0410b03-cc6e-4608-8fa9-e610747392d7"; //$NON-NLS-1$ 
  public static String EPBS__SYSTEMCI_SYSTEM = "3b5c15ae-8555-4d57-9040-2cd950e9b6f2"; //$NON-NLS-1$ 
  public static String EPBS__SYSTEMCI_SYSTEM__PHYSICAL_ARTIFACT_REALIZATION_TO_PHYSICAL_SYSTEM = "737cc6a3-f180-4eab-8963-4f3ac936e76c"; //$NON-NLS-1$ 
  public static String EPBS__PHYSICAL_ARCHITECTURE_REALIZATION_TO_PHYSICAL_ARCHITECTURE = "811d5f47-5b64-4508-9df0-c262e9fa87fe"; //$NON-NLS-1$ 

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.test.framework.api.BasicTestArtefact#getRequiredTestModels()
   */
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("reconciliation.interfacelinks");
  }

}
