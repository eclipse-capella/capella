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
package org.polarsys.capella.test.diagram.filters.ju.lab;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForLAB extends DiagramObjectFilterTestCase {

  protected final String LOGICAL_SYSTEM_ID = "224cc8f5-e287-4cb4-91d4-f0f9dd61a38f";
  protected final String LOGICAL_FUNCTION_1_ID = "42799e8b-95a3-4ed1-a3e4-2ffa933b1952";
  protected final String LOGICAL_FUNCTION_2_ID = "8d2f7885-211e-45ca-84a6-53d706e78068";
  protected final String LOGICAL_FUNCTION_7_ID = "880cc70f-445a-42dc-89a2-31e3f51dbb0d";
  protected final String LOGICAL_FUNCTION_8_ID = "b4c2cf71-33b6-415b-b334-1e8b4fe1f922";
  protected final String COMPONENT_EXCHANGE_1_ID = "dc6a6a7d-1499-4d1f-9b38-4696840f5022";
  protected final String COMPONENT_EXCHANGE_2_ID = "72379f9d-35de-42de-bce7-9e8a0ed54f47";
  protected final String FUNCTIONAL_EXCHANGE_1_ID = "998a0b1e-5baa-4d8b-84e9-22c13dd22b55";
  protected final String FUNCTIONAL_EXCHANGE_2_ID = "0240c737-9912-4e2f-b4c7-2da14a970b72";
  protected final String FUNCTION_OUTPUT_PORT_1_ID = "2731e93d-8153-41e5-8df8-46a7dd3a062d";
  protected final String FUNCTION_INPUT_PORT_1_ID = "d2634ca3-18d1-4daf-a263-8ad90323b1e8";
  protected final String FUNCTION_OUTPUT_PORT_2_ID = "b5240ec4-6021-4e0f-a8ba-d82388757e05";
  protected final String FUNCTION_INPUT_PORT_2_ID = "8a75fd03-7052-4c49-b40e-86aabddeb85b";
  protected final String FUNCTION_INPUT_PORT_WITHOUT_EXCHANGES_ID = "3187c6f7-47a7-491f-bcf9-baaeb6ea887e";
  protected final String PORT_ALLOCATION_TO_FOP_1_ID = "bb47dfb7-70c8-49d9-bf6a-f397b99f3e3b";
  protected final String PORT_ALLOCATION_TO_FIP_1_ID = "1109f0ca-7342-4a08-b983-0b166423ee9f";
  protected final String PORT_DELEGATION_ID = "4481098a-9330-4c4d-bcbb-fbb8c017acbf";
  protected final String COMPONENT_PORT_1_ID = "790c935f-36fa-4b1c-90fb-520a445a0428";
  protected final String COMPONENT_PORT_2_ID = "c6b93fb1-fd7f-4785-81b3-c10d73cfe0f2";
  protected final String COMPONENT_PORT_3_ID = "a3baa533-de38-4934-9e7b-5b6ea94ba6ed";
  protected final String COMPONENT_PORT_4_ID = "a948be04-293a-4cbe-8feb-b6f673c58409";
  protected final String COMPONENT_PORT_5_ID = "8c006c8b-3993-4e94-a424-cadccbda3051";
  protected final String COMPONENT_PORT_6_ID = "03d2186c-5bd1-4728-b806-a9155edd5c6f";
  protected final String COMPONENT_PORT_7_ID = "b76c2468-20f7-4502-b913-2adb107a8862";
  protected final String COMPONENT_PORT_WITHOUT_EXCHANGES_ID = "1c767656-007a-48e0-aa38-46942fbf5e45";
  protected final String PROPERTY_VALUES_ID = "a2f462b7-2c1f-4a73-b9fb-09c52e17fcba";
  protected final String PHYSICAL_LINK_ID = "8765332d-0948-4d89-8781-c77f3d319931";
  protected final String LOGICAL_COMPONENT_4_ID = "435b9f48-cf55-4445-a6c5-34f7f7a7d4a0";
  protected final String LOGICAL_COMPONENT_5_ID = "d68cb5e8-5c2c-4d10-bd8f-044d8331f1bd";
  protected final String LOGICAL_COMPONENT_4_CHILD_ID = "386ff8a4-d955-45cd-acbb-510f31ec1222";
  protected final String LOGICAL_COMPONENT_5_CHILD_ID = "7873bfcb-fa10-4847-9419-2d336311c43c";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[LAB] Logical System";
  }

}
