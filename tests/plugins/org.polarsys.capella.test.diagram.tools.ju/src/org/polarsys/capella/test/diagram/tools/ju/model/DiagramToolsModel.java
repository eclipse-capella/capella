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
package org.polarsys.capella.test.diagram.tools.ju.model;

import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;

public abstract class DiagramToolsModel extends AbstractDiagramTestCase {

  public static final String OA__ROOT_OA_ID = "2fb085ee-b7cb-41c0-b26b-8cfac4374e29";
  public static final String OA__OPERATIONAL_ACTIVITIES_PACKAGE = "0e1669cd-75fa-4da3-9eed-68f8c6f86dcf";
  
  public static final String ORB__ROOT_OPERATIONAL_CONTEXT_NAME = "Operational Roles Blank Test Diagram";
  
  public static final String ES_SA_DIAGRAM = "[ES] Capability 1";
  public static final String ES_SA_ARM_TIMER = "dd85e520-1f0c-42e0-be54-832e949db3cc";
  public static final String ES_SA_CANCEL_ARM_TIMER = "04784202-c816-4539-a629-0b925960f90a";
  public static final String ES_SA_DURATION = "290c669d-4d22-4363-8b68-ca4c703a9b75";
  public static final String IS_SA_DIAGRAM = "[IS] Capability 1";
  public static final String IS_SA_ARM_TIMER = "ddc1ac2e-5889-4788-9bd8-ef810e6ac5b6";
  public static final String IS_SA_CANCEL_TIMER = "c45aad4d-036b-44be-952d-d18dacc339fa";
  public static final String IS_SA_DURATION = "8bf0bdaf-e377-4025-9f43-d6ce43b00b26";
  public static final String FS_SA_DIAGRAM = "[FS] Capability 1";
  public static final String FS_SA_DURATION = "546fe1ed-1d3d-4d55-ae36-ac930c7e5a39";
  public static final String ES_LA_DIAGRAM = "[ES] CapabilityRealization 1";
  public static final String ES_LA_ARM_TIMER = "1877fca8-e8dd-4f46-8ea9-03194786d5a8";
  public static final String ES_LA_CANCEL_ARM_TIMER = "1ea2a858-225e-444d-ad97-7e3a1c623f51";
  public static final String ES_LA_DURATION = "7d22e271-a2a2-467e-aeca-214421f4fe28";
  public static final String IS_LA_DIAGRAM = "[IS] CapabilityRealization 1";
  public static final String IS_LA_ARM_TIMER = "0793447a-e50a-4cc4-b2c6-dbbfcf4f3b0d";
  public static final String IS_LA_CANCEL_TIMER = "4c57ec4e-9173-43b0-ad38-900711776da8";
  public static final String IS_LA_DURATION = "360a5ee3-b882-45f8-bddb-51fe76bd4c31";
  public static final String FS_LA_DIAGRAM = "[FS] CapabilityRealization 1";
  public static final String FS_LA_DURATION = "f3feddb4-36fb-4776-a65b-b8622bb4956e";
  public static final String ES_PA_DIAGRAM = "[ES] CapabilityRealization 1 PA";
  public static final String ES_PA_ARM_TIMER = "1c0a8dbb-de41-4c69-bfb9-51858332537a";
  public static final String ES_PA_CANCEL_ARM_TIMER = "3a1868c0-c9ce-4352-8329-63e3936c16bc";
  public static final String ES_PA_DURATION = "fb8b9156-342b-4612-85be-58159e4ef484";
  public static final String IS_PA_DIAGRAM = "[IS] CapabilityRealization 1 PA";
  public static final String IS_PA_ARM_TIMER = "df5a88bf-1a78-4e0c-99d3-e8dfb1e68511";
  public static final String IS_PA_CANCEL_ARM_TIMER = "eb36c262-7634-401f-9977-4d939aab76b3";
  public static final String IS_PA_DURATION = "27c52f45-477c-4691-bb8a-f0dd28244f0f";
  public static final String FS_PA_DIAGRAM = "[FS] CapabilityRealization 1 PA";
  public static final String FS_PA_DURATION = "c0762bc1-75b3-4215-b389-a127dad8b789";
  
  public static final String CDB_DIAGRAM = "[CDB] Data";
  public static final String CDB_UNIT = "2dbe16f4-6735-4b99-bf81-4e772be3ddb3";
  
  public static final String MB_DIAGRAM = "[MB] Missions";
  public static final String MB_GENERALIZATION = "552c488a-97ec-4208-a30e-44abcd3c1655";
  public static final String MB_GENREALIZATION_CAPABILITY = "7eed6446-d46d-4c8f-a808-d73c5c70fe08";
  
  @Override
  public String getRequiredTestModel() {
    return DiagramToolsModel.class.getSimpleName();
  }
}
