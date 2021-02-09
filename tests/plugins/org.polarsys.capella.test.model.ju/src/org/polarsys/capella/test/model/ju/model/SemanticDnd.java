/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.NonDirtyTestCase;

public abstract class SemanticDnd extends NonDirtyTestCase {

  public static final String ROOT_OPERATIONAL_ACTIVITY = "aef19562-0b64-49fe-8bd0-be70a166b82a"; //$NON-NLS-1$
  public static final String OA = "851a497d-d269-41e2-ad54-235f89197b7e"; //$NON-NLS-1$
  public static final String OAP = "64e42ec9-8ad6-4c51-abed-9a7f1912aeb4"; //$NON-NLS-1$
  public static final String OAP2 = "7ab98ddc-1b75-4a9a-bd56-7de28349941a"; //$NON-NLS-1$
  public static final String OC = "ef4d4cf6-00a5-4e3e-941f-2f34a901c1f8"; //$NON-NLS-1$
  public static final String OP = "06f12b41-dd13-4cb3-8474-ee9e176e2df8"; //$NON-NLS-1$
  public static final String OP2 = "8caefc54-e572-49c1-9590-782ba94236fa"; //$NON-NLS-1$
  public static final String SYSTEM_FUNCTIONS = "431b873e-70f1-42de-9cee-90e92d3fc2c9"; //$NON-NLS-1$
  public static final String ROOT_SYSTEM_FUNCTION = "ef75bc75-313a-464e-a7f8-76d0d2580b70"; //$NON-NLS-1$
  public static final String SC = "b9a480d8-245a-47b7-923f-8ed1ab79d608"; //$NON-NLS-1$
  public static final String SP = "17676e58-7879-475c-a041-8c3022bf0809"; //$NON-NLS-1$
  public static final String SP2 = "167d6fc7-3625-491c-8f64-c9bff7ec316f"; //$NON-NLS-1$
  public static final String LOGICAL_FUNCTIONS = "721d3b52-2050-442c-9526-dad944c19598"; //$NON-NLS-1$
  public static final String ROOT_LOGICAL_FUNCTION = "cd09a3bc-c48b-4e40-81e7-7ca7a417ef07"; //$NON-NLS-1$
  public static final String LC = "286a6a2d-cde5-4f91-a520-b4521e41eb31"; //$NON-NLS-1$
  public static final String LP = "6f7e8c45-7122-4a90-85b1-de3c0a6f8df9"; //$NON-NLS-1$
  public static final String LP2 = "eec30221-8f32-450f-9824-9456fb16bd9d"; //$NON-NLS-1$
  public static final String PHYSICAL_FUNCTIONS = "d8657fc3-7e3c-4b55-a31a-b21cdbb4ab2e"; //$NON-NLS-1$
  public static final String ROOT_PHYSICAL_FUNCTION = "4a0dfb5a-c588-45b9-bb96-e572bcebb250"; //$NON-NLS-1$
  public static final String PC = "275783b2-29fd-4b0c-a09a-ea240ca0ab85"; //$NON-NLS-1$
  public static final String PP = "9bc40128-a109-468b-9e1a-0afeb771a668"; //$NON-NLS-1$
  public static final String PP2 = "0ba71caf-32de-44d4-aa00-79ccd0146516"; //$NON-NLS-1$
  public static final String EC = "0d53e624-1597-4c7f-b652-63e6a6762b74"; //$NON-NLS-1$
  public static final String EP = "a9c8b2b1-ae3a-44f1-b56f-0c07a232e32e"; //$NON-NLS-1$
  public static final String EP2 = "01917bac-8834-4e3b-bb67-cce03eac8820"; //$NON-NLS-1$

  public static final String OPERATIONAL_INTERFACES = "f3cc2b3c-68ad-4ab2-a01c-ca55b6d8a45f"; //$NON-NLS-1$
  public static final String SYSTEM_INTERFACES = "f298ed8b-a647-4421-91d6-830267108bd3"; //$NON-NLS-1$
  public static final String LOGICAL_INTERFACES = "87e35c7e-09d1-4f4f-a67f-bb49d3b3112f"; //$NON-NLS-1$
  public static final String PHYSICAL_INTERFACES = "6ff501ed-e22a-4d78-9d5e-c754b922c9dd"; //$NON-NLS-1$

  public static final String EPBS_ARCHITECTURE = "3f636bea-e3df-4f51-baff-33b5ee65e16f"; //$NON-NLS-1$

  public static final String OPERATIONAL_CAPABILITIES = "af2cf6b0-e74f-4b5f-82b6-d84e5ea24c13"; //$NON-NLS-1$
  public static final String CAPABILITIES = "dc6b0602-54bd-44fd-90ad-1c2fb7a42131"; //$NON-NLS-1$
  public static final String LOGICAL_CAPABILITIES = "76e84092-c654-4c7d-8289-d6ad3cbb1114"; //$NON-NLS-1$
  public static final String PHYSICAL_CAPABILITIES = "6966da28-06cd-435f-9493-596b7e11d6fb"; //$NON-NLS-1$
  public static final String EPBS_CAPABILITIES = "2599420a-79e6-4455-ab00-72524c250836"; //$NON-NLS-1$

  public static final String SI_USED_IN_LOGICAL = "2e9bb399-e807-4680-abb1-3c5e9d3b9f45"; //$NON-NLS-1$
  public static final String SI_FREE = "75bc9ee6-79ae-4542-88f6-64ccdc01c76e"; //$NON-NLS-1$
  public static final String SI_REALIZED = "33dfaf1d-b258-433b-8427-5b593d796002"; //$NON-NLS-1$
  public static final String SYSTEM_SUBINTERFACEPKG = "73a68a7d-6102-4c59-aba7-20d05e1c21a1"; //$NON-NLS-1$

  public static final String ENUM1 = "4546c934-c0a2-4ddf-85f3-ac6a8bda7561"; //$NON-NLS-1$
  public static final String ENUMERATIONLITERAL_1 = "d6f4a101-3339-4c41-bfab-eb8bd70fc748"; //$NON-NLS-1$
  public static final String ENUM2 = "a30e94c7-0b19-4c07-a192-de1bf09b675b"; //$NON-NLS-1$
  public static final String NUMERIC3 = "35b96e02-2d0d-48a4-a2df-691510af7c7b"; //$NON-NLS-1$

  public static final String BOOLEANTYPE_4 = "2ddfc55e-13ec-4040-b17a-4e777ec7e4b3"; //$NON-NLS-1$
  public static final String LITERALBOOLEANVALUE_1 = "73f4bcf7-7e6c-4be4-afa2-92ad86da295c"; //$NON-NLS-1$
  public static final String BOOLEANTYPE_5 = "62da640d-da74-473f-9ea4-cf342cde5ea1"; //$NON-NLS-1$

  public static final String FUNCTIONALCHAIN_1 = "8207c2ae-9cdd-4ff9-8f80-18800fef5599"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1 = "19f99b07-2fc8-499d-8d05-d6d98cb5104f"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_2 = "83f60636-47fb-457a-a306-cf35fa435887"; //$NON-NLS-1$
  public static final String SYSTEMFUNCTION_1 = "1b8ad191-1e16-4b9d-8fe2-a85bb7a739fe"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("SemanticDnd");
  }

  protected CapellaModel getTestModel() {
    return super.getTestModel(getRequiredTestModels().get(0));
  }

}
