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
package org.polarsys.capella.test.diagram.tools.ju.model;

import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;

public abstract class XDFBToolsTestingModel extends AbstractDiagramTestCase {
  
  // Elements IDs
  public static final String PROJECT_XDFBTOOLSTESTINGMODEL = "69fd54cf-6882-4dec-962f-2bb89126e19e";
  
  // Operational Analysis
  public static final String OA__ROOT_OA_ID = "1e2462db-b15c-4705-982d-93562d3e4094";
  public static final String OA__OPERATIONAL_ACTIVITY_TEST_SCENARIO_ID = "494e7703-2af4-4dd3-8ef7-126712ecd52e";
  public static final String OA__TEST_SCENARIO_OPERATIONAL_ACTIVITY_4 = "c6ee49a7-8705-4e66-be81-34dc43b3eeb6";
  public static final String OA__TEST_SCENARIO_OPERATIONAL_ACTIVITY_5 = "4ecc0448-7b32-48e5-94c5-30a303c3d622";
  public static final String OA__TEST_SCENARIO_TEST_INTERACTION = "b003608d-9b5f-4eba-8b08-1833f95cdf7c";
  public static final String OA__PROPERTY_VALUE_ID = "abe7cb39-7291-470a-9ead-5fc0147f1d54";
  public static final String OA__PROPERTY_VALUE_GROUP_ID = "94ec1cd7-ecdc-431f-96be-be117554d980";
  public static final String OA__STATE_MACHINE_MODE_ID = "94a5676f-da61-4cd6-8a59-320451855777";
  
  // System Analysis
  public static final String SA__ROOT_SF_ID = "d560188d-bfa0-4a6b-a7ec-ef1a0a5a3415";
  public static final String SA__SYSTEM_ANALYSIS_TEST_SCENARIO_ID = "6f733d5b-6c51-4581-8844-302473dd8f72";
  public static final String SA__TEST_SCENARIO_FUNCTION_5_ID = "9719e641-42fa-4571-a3a1-71d189d8dbca";
  public static final String SA__TEST_SCENARIO_FUNCTION_6_ID = "cb013381-cb60-46e9-9db0-46819e738b1d";
  public static final String SA__TEST_SCENARIO_TEST_FUNCTIONAL_EXCHANGE_ID = "bc07162f-965a-4274-836a-bdd123d79f17";
  public static final String SA__PROPERTY_VALUE_ID = "d2f217e7-2844-462b-9933-3def72d9a01e";
  public static final String SA__PROPERTY_VALUE_GROUP_ID = "e4e11594-6ed0-4873-a156-6cc12d004626";
  public static final String SA__STATE_MACHINE_MODE_ID = "42779ccf-798d-41bd-9705-cb9fbb0d55da";  
  public static final String SA__EXCHANGE_CATEGORY_1 = "14bb40fb-6356-4fc0-a04d-197e969a7fb7";
  
  // Logical Analysis
  public static final String LA__ROOT_LF_ID = "13a147ab-7547-4fbb-82ef-793acc9f164a";
  public static final String LA__EXCHANGE_CATEGORY_1 = "e8552647-7e45-4c69-9450-410dd807005c";
  public static final String LA__LOGICAL_ANALYSIS_TEST_SCENARIO_ID = "1e48a36c-305b-4410-88be-29722aba2063";
  public static final String LA__TEST_SCENARIO_FUNCTION_5_ID = "748c6678-58dd-4d8b-a8e7-eaf6b32d5530";
  public static final String LA__TEST_SCENARIO_FUNCTION_6_ID = "626819b9-6526-4fd4-8c9b-7b211447ba46";
  public static final String LA__TEST_SCENARIO_TEST_FUNCTIONAL_EXCHANGE_ID = "959846bb-3286-4503-b016-5f259d79787c";
  public static final String LA__PROPERTY_VALUE_ID = "250a7a19-c352-46b8-869b-cd6882973516";
  public static final String LA__PROPERTY_VALUE_GROUP_ID = "23f41ac7-81d7-4b2b-aaf1-781c7ae44f01";
  public static final String LA__STATE_MACHINE_MODE_ID = "add625c1-ffbb-4447-bd28-951c301e7349";
  
  // Physical Analysis
  public static final String PA__ROOT_PF_ID = "4a3b3452-fe9f-4289-b25f-25f02d76428f";
  public static final String PA__PHYSICAL_ANALYSIS_TEST_FUNCTION_SCENARIO_ID = "46ef47a4-d699-4f1d-af0f-066ea4826f9c";
  public static final String PA__TEST_SCENARIO_FUNCTION_5_ID = "b9dce68d-74e2-4ef4-a73d-025d97553601";
  public static final String PA__TEST_SCENARIO_FUNCTION_6_ID = "c57465b9-e633-4b60-90bf-356d72fff39f";
  public static final String PA__TEST_SCENARIO_TEST_FUNCTIONAL_EXCHANGE_ID = "1bf28bad-008b-4d99-88d8-a2cf88394063";
  public static final String PA__PROPERTY_VALUE_ID = "e2650273-8de7-4bcd-bcf6-b065fb146984";
  public static final String PA__PROPERTY_VALUE_GROUP_ID = "936390bf-a271-41f5-bb95-b7851f5cf7c2";
  public static final String PA__STATE_MACHINE_MODE_ID = "ace8a463-4830-490c-8891-f4fcf24043e5";
  public static final String PA__EXCHANGE_CATEGORY_1 = "dbe5bfdb-9678-4b1c-b258-033045af8110";
  
  // Existing Model Elements Names
  // Operational Analysis
  public static final String OA__ROOT_OA_DATA_FLOW_DIAGRAM_NAME = "Operational RA Data Flow Diagram";
  
  // System Analysis
  public static final String SA__ROOT_SF_DATA_FLOW_DIAGRAM_NAME = "System RF Data Flow Diagram";
  
  // Logical Analysis
  public static final String LA__ROOT_LF_DATA_FLOW_DIAGRAM_NAME = "Logical RF Data Flow Diagram";
  
  // Physical Analysis
  public static final String PA__ROOT_PF_DATA_FLOW_DIAGRAM_NAME = "Physical RF Data Flow Diagram";
  
  @Override
  public String getRequiredTestModel() {
    return XDFBToolsTestingModel.class.getSimpleName();
  }
}
