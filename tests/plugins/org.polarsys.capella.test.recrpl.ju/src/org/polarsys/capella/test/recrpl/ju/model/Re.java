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
package org.polarsys.capella.test.recrpl.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.recrpl.ju.RecRplTestCase;

public abstract class Re extends RecRplTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "re" }); //$NON-NLS-1$
  }

  public static String LOGICAL_SYSTEM = "3ffa9fef-c04e-4aa9-bdf4-97dcc2ef30cb"; //$NON-NLS-1$ 

  public static String ROOT_LF = "c3da4575-4c41-4517-aa6c-892b36abecb0"; //$NON-NLS-1$ 
  public static String LF1 = "2fb8fdd1-86a6-4f1d-ba6d-6dfc993c9083"; //$NON-NLS-1$ 
  public static String FOP_1 = "608258a6-1b30-46f3-8dde-30bb8513bbbc"; //$NON-NLS-1$ 
  public static String LF2 = "f1bfe95d-b77d-4947-914b-989f426151e7"; //$NON-NLS-1$ 
  public static String FIP_1 = "d2d54b41-43c2-43a1-b992-890a4372acec"; //$NON-NLS-1$ 
  public static String FUNCTIONALEXCHANGE_1 = "1cfe9d88-c3e7-4f8e-b526-644dba34e210"; //$NON-NLS-1$ 
  public static String LF3 = "4f1b5537-0437-43d8-b481-abb1546382f8"; //$NON-NLS-1$ 
  public static String LF4 = "d8849ba7-492a-49be-ad4f-093973999955"; //$NON-NLS-1$ 

  public static String LC_1__LC_1 = "c30260c6-d33d-4e0f-a0de-0d1442941e0a"; //$NON-NLS-1$ 
  public static String LC_1 = "b62f6712-ea35-4565-8f2a-07b07d023c4d"; //$NON-NLS-1$ 

  public static String COMPONENT_FUNCTIONAL_ALLOCATION_TO_LF1 = "133a8c00-0b3d-4faa-8f20-58d336cfdd56"; //$NON-NLS-1$ 
  public static String COMPONENT_FUNCTIONAL_ALLOCATION_TO_LF2 = "db1a304c-c1cc-4ab2-b13a-1abcd6a900d7"; //$NON-NLS-1$ 

  public static String PC_1__PC_1 = "de7d9616-8769-4c23-8dcb-23b8a7a991c0"; //$NON-NLS-1$ 
  public static String PART_DEPLOYMENT_LINK_TO_PC_2 = "73e425ed-a73d-4bd5-8854-9be428d36b24"; //$NON-NLS-1$ 
  public static String PART_DEPLOYMENT_LINK_TO_PC_3 = "05df6602-ff4d-42ef-ad80-57055eb6b97f"; //$NON-NLS-1$ 
  public static String PC_2__PC_2 = "295e1117-4b0d-4b08-8a82-01dfb59ed226"; //$NON-NLS-1$ 
  public static String PC_3__PC_3 = "be9554cf-4080-485e-b0c8-8e30ecae1258"; //$NON-NLS-1$ 
  public static String PC_1 = "c4d199a1-55ee-40a6-bbaa-21f8534114fb"; //$NON-NLS-1$ 
  public static String PC_2 = "26195a7e-8b1b-41d2-b8f8-1fa905a36f24"; //$NON-NLS-1$ 
  public static String PC_3 = "fdc38a4c-17a8-4190-9e3b-742511a5ac84"; //$NON-NLS-1$ 
  public static String EI1 = "fba784bd-16c9-4fa9-950f-6930920a726d"; //$NON-NLS-1$ 
  public static String I1 = "a54ef0dc-7531-4e3e-8527-6639754bbf85"; //$NON-NLS-1$ 
  public static String I2 = "9a8ebbe8-564f-410a-b583-1bd209d38677"; //$NON-NLS-1$ 
  public static String LC_2 = "dc0ff871-494f-416a-bfda-c5486cdcc758"; //$NON-NLS-1$ 
  public static String LC_2_CP_1 = "3c5d3677-9485-4475-a628-1f7f693614cc"; //$NON-NLS-1$ 

  public static String LA__PKG_LIBRARY = "80763cd3-25c8-4f92-9f4c-fda059ea0d4e"; //$NON-NLS-1$ 
  public static String LA__PKG_LIBRARY__PART_CARD_CARD = "bdc174fc-0146-4d3e-8365-2bd10439400f"; //$NON-NLS-1$ 
  public static String LA__PKG_LIBRARY__PART_RACK_RACK = "97d7c068-ec4e-4918-88ee-e88a39a70d62"; //$NON-NLS-1$ 
  public static String LA__PKG_LIBRARY__PART_BAIE_BAIE = "1fa94a5a-2b94-4bd6-ba35-d3485dd32518"; //$NON-NLS-1$ 
  public static String LA__PKG_LIBRARY__CARD = "0221ec33-7e59-4b5d-a8dd-f7abe6b86e02"; //$NON-NLS-1$ 
  public static String LA__PKG_LIBRARY__RACK = "4f56b96f-aee8-462b-890d-a1f0a4a873ba"; //$NON-NLS-1$ 
  public static String LA__PKG_LIBRARY__BAIE = "7ce6ad69-e826-4db2-8426-590d1ce7226d"; //$NON-NLS-1$ 
  public static String LA__PKG_INSTANCE = "29237f10-72fd-403b-a88f-6030f4737b8d"; //$NON-NLS-1$ 
  public static String LC_3_LC_3 = "d2e6eeef-91f0-4067-b559-59f982ba10de"; //$NON-NLS-1$ 
  public static String LC_3_LC_3__LC_3 = "c2100a4a-3e67-4d04-96c8-dadca68ae889"; //$NON-NLS-1$ 

  public static String RPL1 = "c1d01584-d3aa-4f12-974e-926241618e40";
  public static String PC_11 = "c2ac2907-6097-48a7-a52b-b8d8a0e25ff9";//$NON-NLS-1$
  public static String PC_12 = "2dd78730-bd99-407b-8795-f93f8175dd3f";//$NON-NLS-1$
  public static String PF_13 = "cb2f48a4-1b95-4d12-ae0c-80eb75181327";//$NON-NLS-1$

  public static String RPL2 = "19742f93-f555-40d3-ae50-4cc0b593c083";
  public static String PC_21 = "a89b10a9-dd5d-4d88-b65f-0737ca9d6dca";//$NON-NLS-1$
  public static String PC_22 = "0ea3888a-bbf0-496b-ba60-b2ef6e9b046a";//$NON-NLS-1$
  public static String PF_23 = "4045788f-33de-4002-944c-f8ff9a9213dd";//$NON-NLS-1$

  public static String RPL3 = "ec2f1832-73d0-4166-b532-436f7a82892b";
  public static String PC_31 = "2b3f5d6f-d457-4c02-9ab0-d906b93c2ef6";//$NON-NLS-1$
  public static String PC_32 = "b61c5f2b-760b-4b71-836d-c3ced52b248d";//$NON-NLS-1$
  public static String PF_33 = "f6a2aca7-8558-4124-a0ba-78f2bea6a470";//$NON-NLS-1$
  
  public static final String SA_STRUCTURE = "15377f28-3635-4a41-8453-9b79a71e8fb6"; //$NON-NLS-1$
  public static final String SYSTEM = "ce8ec333-f17c-470e-920b-29f0b9906a78"; //$NON-NLS-1$
  
  public static final String SA_2 = "d87a09f4-216f-461e-9bd1-40f2ddf6f448"; //$NON-NLS-1$
  public static final String SA_2__SA_2 = "e35ef7fe-f97c-4ed2-85ed-af0124218a1d"; //$NON-NLS-1$
  
  public static final String LA_2 = "2d7c0d76-82b3-4a92-9e23-ff7687b5bbbe"; //$NON-NLS-1$
  public static final String LA_2__LA_2 = "e0c4339c-4ed5-44de-817e-c2390121c17d"; //$NON-NLS-1$

}