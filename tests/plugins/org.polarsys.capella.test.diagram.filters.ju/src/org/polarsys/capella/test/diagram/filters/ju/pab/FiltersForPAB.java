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
package org.polarsys.capella.test.diagram.filters.ju.pab;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForPAB extends DiagramObjectFilterTestCase {

  protected final String PHYSICAL_PORT_1_ID = "b0ed6f5f-e503-416e-81c0-aabb509c2175";
  protected final String PHYSICAL_PORT_2_ID = "519d8974-92bb-456b-9acb-a78bff1fa9c5";
  protected final String PHYSICAL_PORT_3_ID = "e63c6849-6c88-4106-873b-3a6801ee2de5";
  protected final String PHYSICAL_PORT_4_ID = "ae23ffc7-fa6a-4416-a3d6-a7bb4f4e31be";
  protected final String PHYSICAL_PORT_5_ID = "ba76b0ff-14fa-4bb0-b8dc-3284f123ad1c";
  protected final String PHYSICAL_PORT_6_ID = "b439b0c7-e61a-4c63-b204-a8e9128bedf6";
  protected final String PHYSICAL_PORT_7_ID = "5f5496ea-4293-4680-93d8-9262d08d2540";
  protected final String COMPONENT_PORT_1_ID = "5c63b5e2-8532-441f-b518-89918c90316f";
  protected final String COMPONENT_PORT_2_ID = "6589dfcf-cc79-47b0-ab94-56b8805a8d28";
  protected final String COMPONENT_PORT_3_ID = "ba6cf3a4-c03e-422d-8021-c8965b51e00f";
  protected final String COMPONENT_PORT_4_ID = "f62f8893-9d94-48aa-94dd-475eb99bf650";
  protected final String COMPONENT_PORT_5_ID = "86a60dd2-ccaa-43bb-bf98-ab6d7ec3cd05";
  protected final String COMPONENT_PORT_6_ID = "ed97ebb5-6d5f-4710-abfd-811bf4deec99";
  protected final String COMPONENT_PORT_7_ID = "d9a3f460-9dfb-462a-aa4e-0a10b4d7e1ad";
  protected final String COMPONENT_PORT_8_ID = "480e695f-6678-486e-93a4-70d97a71aa5d";
  protected final String COMPONENT_PORT_9_ID = "143d7ae2-264d-401f-91e9-1e3e900a1698";
  protected final String COMPONENT_PORT_WITHOUT_EXCHANGES_ID = "77efed1a-bb3c-4407-9b26-671887e32027";
  protected final String FUNCTION_OUTPUT_PORT_1_ID = "af8b8590-5078-4566-8dee-e4267f7a7b31";
  protected final String FUNCTION_INPUT_PORT_1_ID = "c73296a8-7e68-4f86-890a-a74b0c9e9e2b";
  protected final String FUNCTION_OUTPUT_PORT_2_ID = "4e501637-730f-463d-93e8-797ef895c136";
  protected final String FUNCTION_INPUT_PORT_2_ID = "0488e1d7-457d-4b04-b400-7edd41d7163e";
  protected final String FUNCTION_OUTPUT_PORT_3_ID = "31d4d783-26d2-434e-a265-db1687bf1523";
  protected final String FUNCTION_INPUT_PORT_3_ID = "ea5beb0d-0d9e-4632-8d7b-75802621a2a2";
  protected final String FUNCTION_OUTPUT_PORT_4_ID = "b6b502a9-7f09-4247-85da-7ab63c740ebf";
  protected final String FUNCTION_INPUT_PORT_4_ID = "26b5a38e-206a-4ee0-baf5-0dfc2572fac4";
  protected final String FUNCTION_INPUT_PORT_WITHOUT_EXCHANGES_ID = "8934e607-1597-4ec4-bbe7-18a7591fe278";
  protected final String COMPONENT_EXCHANGE_1_ID = "63d4dd01-c52f-4a74-85b4-dcece0ece82f";
  protected final String COMPONENT_EXCHANGE_2_ID = "471385c5-ac2e-44e2-a9f7-29dd3f458061";
  protected final String COMPUTED_COMPONENT_EXCHANGE_1_ID = "760b91de-ac92-4ee1-85ff-74743254aaaf";
  protected final String COMPONENT_EXCHANGE_3_ID = "ccd12a0d-9ab8-42a1-bae6-f5908586c78d";
  protected final String COMPONENT_EXCHANGE_4_ID = "760b91de-ac92-4ee1-85ff-74743254aaaf";
  protected final String PORT_DELEGATION_ID = "e16ca8f1-f866-4dc2-8bc8-59f89170873c";
  protected final String PHYSICAL_COMPONENT_1_ID = "962739bc-8534-406a-a161-f23f27c97cf2";
  protected final String PHYSICAL_COMPONENT_2_ID = "907d4bf9-1d48-4c94-b1b3-08fbdddc7a98";
  protected final String PHYSICAL_COMPONENT_3_ID = "c33f723c-b385-44b9-b61f-5a9c72e1aeb0";
  protected final String PHYSICAL_COMPONENT_4_ID = "ccdd46cd-4a15-427f-952e-bf904f80f824";
  protected final String PHYSICAL_COMPONENT_8_ID = "70bf1bfa-4510-4946-9c1c-cc88eaeaf1f1";
  protected final String PHYSICAL_COMPONENT_9_ID = "b92ee1dc-c1e4-4220-afb1-7724bfe9f38d";
  protected final String FUNCTIONAL_EXCHANGE_1_ID = "a875d373-e646-41fb-84d3-e4d331cf6ffd";
  protected final String FUNCTIONAL_EXCHANGE_2_ID = "31f5c3af-c543-4318-9d04-349f60776c0a";
  protected final String FUNCTIONAL_EXCHANGE_6_ID = "1b2c20b1-8c60-48c6-8535-564b9c889897";
  protected final String FUNCTIONAL_EXCHANGE_7_ID = "25f41c03-6ae6-44af-966c-bb831ba9f6e0";
  protected final String PHYSICAL_LINK_1_ID = "c2cb6453-5612-4546-9466-fbbf6051fedf";
  protected final String PHYSICAL_LINK_2_ID = "ef631259-8baf-436c-a1e7-655a5b0e958b";
  protected final String PHYSICAL_LINK_3_ID = "e9de7e34-ccf7-4b5f-a18b-6cce77799428";
  protected final String PHYSICAL_FUNCTION_1_ID = "cedf2148-c4dd-4455-bec2-28d535b4add5";
  protected final String PHYSICAL_FUNCTION_2_ID = "61c92cb6-d0dc-4567-8b48-7103cb61d4fa";
  protected final String PHYSICAL_FUNCTION_3_ID = "53e219ba-3d3b-4a82-bcd5-212d15256b85";
  protected final String PHYSICAL_FUNCTION_9_ID = "d3f37aad-6126-4465-a66b-33e3823b2023";
  protected final String UNKNOWN_COMPONENT_1_ID = "d63eef61-ef0d-4f00-a4f2-56dd372109e4";
  protected final String PHYSICAL_ACTOR_1_ID = "b965f952-5ee9-40fd-b86a-a27bf5bf8193";
  protected final String PHYSICAL_ACTOR_2_ID = "a7a6e836-bb33-43e8-89e8-550a8511de6c";
  protected final String PORT_ALLOCATION_TO_FIP_2_ID = "143d7ae2-264d-401f-91e9-1e3e900a1698";
  protected final String PROPERTY_VALUES_ID = "347bfb80-3575-422e-a579-938c6632c18b";
  protected final String UNKNOWN_COMPONENT_2_ID = "0b781164-668a-4c8e-96cc-6a066524a374";
  protected final String UNKNOWN_COMPONENT_3_ID = "d7750bc9-9c9c-4fac-8948-83f5fcc80b10";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[PAB] Physical System";
  }
}
