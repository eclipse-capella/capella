/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

public abstract class CompositeChains extends AbstractDiagramTestCase {
  
  public static final String COMPOUND = "7b949ee1-223b-46ce-a8c7-fd2093028efd"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_2 = "4997babd-a64e-4a21-83df-0282a18202ea"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_3 = "0d599924-3b49-414e-bfba-e74b3be20d77"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_5 = "913d59c7-655f-4031-8d1a-bd43267abe8d"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_7 = "cf3b4da1-ee19-42b6-9eb8-19b354580b30"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_8 = "135c44cb-8e8f-40bc-9823-5b785d94fe8c"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_1 = "1f7d1fff-fca4-4ce0-bbc7-604c1e3dbb7b"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_10 = "896eb099-b141-4c43-a71e-e81a30cac0f9"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_11 = "21c3c15d-97b0-4df9-936b-e215e153921b"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_12 = "9f4234e5-12af-4556-ac61-6e86a72aa982"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_13 = "338359db-8473-4905-8db9-1f7ca58c2286"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_14 = "2346e002-311b-493b-8121-73965c49fe1c"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_2 = "0f55af86-c380-4439-bc8a-dbf8c6b2c550"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_3 = "435fa9c9-f367-476e-9154-659f744f1b67"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_4 = "670739ee-35da-4afb-8ff3-4f82518679fb"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_5 = "b74ebf84-1126-4ff3-8938-657af26fbcc1"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_6 = "646c2172-686f-4ef0-9910-49434ee2541b"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_7 = "2ef1ae07-0cd2-4951-ba00-0c6a48f1ea3b"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_8 = "f00dd799-fcde-442b-bc55-99cc786735c1"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_9 = "11d24634-a580-43d9-9813-485b93edb205"; //$NON-NLS-1$
  
  public static final String LOGICALFUNCTION_10 = "680f0323-2523-4837-bf6c-3b14c36121db"; //$NON-NLS-1$
  public static final String LOGICALFUNCTION_11 = "1a673fc4-1fca-4c54-9dc0-971a78ab4335"; //$NON-NLS-1$
  public static final String LOGICALFUNCTION_12 = "e623170d-bf09-45b3-8121-efe915664e90"; //$NON-NLS-1$
  public static final String LOGICALFUNCTION_13 = "cb7c8a07-caaa-4f89-9c3d-43bc722a68fb"; //$NON-NLS-1$
  public static final String LOGICALFUNCTION_14 = "446028d2-fb79-4a39-8386-dcdbefa94d6e"; //$NON-NLS-1$
  public static final String LOGICALFUNCTION_5 = "9dd1e5ba-58c8-4c3e-b80f-104bbc0f6088"; //$NON-NLS-1$
  public static final String LOGICALFUNCTION_6 = "d0ca948a-1063-49d9-997e-0c333272d346"; //$NON-NLS-1$
  public static final String LOGICALFUNCTION_8 = "36038c9e-ab15-45af-a4f3-d773789373a7"; //$NON-NLS-1$
  public static final String LOGICALFUNCTION_9 = "dfcf416b-195c-4d40-914a-8399997f0684"; //$NON-NLS-1$
  
  public static final String LDFB_ROOT_LOGICAL_FUNCTION = "_AOrTYDUMEemj_efF4T16lg"; //$NON-NLS-1$
  public static final String LDFB_PARENT_FUNCTIONS = "_KVi6EDUqEem8pfs0hO7glw"; //$NON-NLS-1$
  
  public static final String PARENTFUNCTION_5 = "6d1c54ee-eb9c-4439-a5d7-d709a7da16e1"; //$NON-NLS-1$
  public static final String PARENTFUNCTION_9 = "ef49c56f-e14c-4afb-a980-2f761bdb13af"; //$NON-NLS-1$
  public static final String PARENTFUNCTION_12 = "270177ae-539a-45ac-9efd-1ad0053d5e15"; //$NON-NLS-1$
  public static final String PARENTFUNCTION_13 = "724b60a7-ff75-486e-ad5a-d81032f5b917"; //$NON-NLS-1$
  public static final String PARENTFUNCTION_14 = "81372a60-ec47-4601-a95d-bfd08da91f1c"; //$NON-NLS-1$
  
  @Override
  public String getRequiredTestModel() {
    return CompositeChains.class.getSimpleName();
  }
}
