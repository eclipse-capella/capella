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
package org.polarsys.capella.test.diagram.filters.ju.sab;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForSAB extends DiagramObjectFilterTestCase {

  protected final String SYSTEM_ID = "767f43af-8824-41cc-bdfa-9a418c9cea3b";
  protected final String SYSTEM_FUNCTION_1_ID = "1c6cb7d3-c884-42a3-a08d-29abe183ef9c";
  protected final String SYSTEM_FUNCTION_2_ID = "a5f5c919-6a0a-4b8a-b0a5-7a1c4549a6df";
  protected final String COMPONENT_EXCHANGE_1_ID = "38241993-f4a7-405c-a40b-8fb0614519a4";
  protected final String FUNCTIONAL_EXCHANGE_1_ID = "53b67128-c3f7-4de9-b6cc-d0631b32ba8a";
  protected final String FUNCTIONAL_EXCHANGE_5_ID = "5e91eb7d-ba1f-4f99-ac9b-8d07a3634c57";
  protected final String FUNCTIONAL_EXCHANGE_6_ID = "aecd20ce-8d4d-4bda-b91f-6dbd6a261c09";
  protected final String FUNCTION_OUTPUT_PORT_1_ID = "f1426033-e171-41d2-99cb-4d519f8176e2";
  protected final String FUNCTION_OUTPUT_PORT_2_ID = "6f27406f-6f40-4ea6-8eca-e6e937d4b828";
  protected final String FUNCTION_OUTPUT_PORT_3_ID = "f71dcada-b378-4f01-a249-d8cb0f578254";
  protected final String FUNCTION_INPUT_PORT_1_ID = "48c365a7-4c77-4482-a881-367e4445a6d3";
  protected final String FUNCTION_INPUT_PORT_2_ID = "709fd3bd-9f4c-4a54-b9f3-138636c534f0";
  protected final String FUNCTION_INPUT_PORT_3_ID = "789f24c6-cea6-4105-946b-fbb20ab19ba6";
  protected final String FUNCTION_INPUT_PORT_WITHOUT_EXCHANGES_ID = "be7c1942-3afc-481c-8441-e9c74afa746d";
  protected final String PORT_ALLOCATION_TO_FOP_1_ID = "3a10439c-1ec4-4ba1-b418-2f2ee9fa42d7";
  protected final String PORT_ALLOCATION_TO_FIP_1_ID = "33a0082e-cde7-44c4-b6bb-cef0f28cec56";
  protected final String COMPONENT_PORT_1_ID = "570bcafd-43f7-4ddf-a1f3-79fddb3cd219";
  protected final String COMPONENT_PORT_2_ID = "35acefe3-d7c7-442c-9967-4305ae4bffdb";
  protected final String COMPONENT_PORT_WITHOUT_EXCHANGES_ID = "db1ad759-62bc-45a6-996b-4dca74ef1bae";
  protected final String PROPERTY_VALUE_ID = "a2f462b7-2c1f-4a73-b9fb-09c52e17fcba";
  protected final String PHYSICAL_LINK_ID = "4f8d6183-6136-43ff-aad5-82fddc887aa8";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[SAB] System";
  }

}
