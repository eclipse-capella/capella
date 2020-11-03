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
package org.polarsys.capella.test.diagram.filters.ju.idb;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForIDB extends DiagramObjectFilterTestCase {

  protected final String INTERFACE_1_ID = "eb8b4fb4-8388-4f03-9b3a-6068c8bb27f9";
  protected final String INTERFACE_2_ID = "11c7296c-a142-4c63-ab79-5455bbe25972";
  protected final String INTERFACE_3_ID = "5228a713-5de0-4826-a1f2-03ba9c45bd36";
  protected final String EXCHANGE_ITEM_INSIDE_INTERFACE_ID = "1ee57d01-f0c9-4860-9323-e66718503fc1";
  protected final String EXCHANGE_ITEM_OUTSIDE_INTERFACE_ID = "bda72a9d-c456-417a-9824-16b7a5e2d342";
  protected final String ECHANGE_ITEM_ELEMENT_ID = "3058fb2d-53e1-403d-97e1-32f3d52da32f";
  protected final String ECHANGE_ITEM_ID = "bda72a9d-c456-417a-9824-16b7a5e2d342";
  protected final String OUTFLOW_COMPONENT_PORT_ID = "06c6decf-ec49-4ffa-818f-17ccb656ffa2";
  protected final String INFLOW_COMPONENT_PORT_ID = "0800a633-d698-4569-9b96-c4bc80701dab";
  protected final String USE_LINK_ID = "78e53971-e83c-42a5-beae-a5b3bea64af7";
  protected final String IMPLEMENTATION_LINK_ID = "2379948a-e07a-4985-af8f-ee14dc5e5608";
  protected final String PROVIDE_LINK_ID = "e090c7cc-5e79-43b7-9ced-5b5ccc5dce26";
  protected final String REQUIRE_LINK_ID = "a429054a-3cf8-4d90-8b27-78b936dbcc73";
  protected final String COMMUNICATION_LINK_ID = "c616c017-a669-412f-b4fd-ef7bbf9eb11b";
  protected final String GENERALIZATION_LINK_ID = "67a7d422-0d0d-484b-8abd-d9f6dcf13d1a";
  protected final String PORT_DELEGATION_ID = "8b470fce-eeba-4fac-b0ea-699a18d8948a";
  protected final String LOGICAL_COMPONENT_1_SIMPLIFIED_DIAGRAM_INTERACTION_ID = "971c0f72-8e3b-45a6-a1e8-b4488c88c018";
  protected final String LOGICAL_COMPONENT_1_SIMPLIFIED_MODEL_INTERACTION_ID = "971c0f72-8e3b-45a6-a1e8-b4488c88c018";
  protected final String LOGICAL_COMPONENT_3_SIMPLIFIED_MODEL_INTERACTION_ID = "2b058f13-6581-44de-a3b9-e72e29b06816";
  protected final String LOGICAL_COMPONENT_3_SIMPLIFIED_DIAGRAM_INTERACTION_ID = "2b058f13-6581-44de-a3b9-e72e29b06816";
  protected final String TECHNICAL_INTERFACE_ID = "cc5f4d6a-63e1-4c1a-bc7b-1c29366bae8a";
  protected final String DELEGATED_IMPLEMENT_LINK_ID = "23a36e1f-79be-482d-9494-c2a3b7c7088a";
  protected final String DELEGATED_USE_LINK_ID = "f13e9435-4f89-4b72-917c-d5db41bafdca";
  protected final String DELEGATED_PROVIDE_LINK_TO_INTERFACE_3_ID = "2da78948-d087-46e4-95e7-a003bfb0ef5d";
  protected final String DELEGATED_REQUIRE_LINK_TO_INTERFACE_3_ID = "4fe866eb-e002-433a-a708-3b011d88c9e0";
  protected final String DELEGATED_PROVIDE_LINK_TO_INTERFACE_1_ID = "e090c7cc-5e79-43b7-9ced-5b5ccc5dce26";
  protected final String DELEGATED_REQUIRE_LINK_TO_INTERFACE_1_ID = "a429054a-3cf8-4d90-8b27-78b936dbcc73";
  protected final String LOGICAL_COMPONENT_1_DELEGATED_PROVIDE_LINK_ID = "42d5a466-61b7-4bdc-a947-2fd917923e8c";
  protected final String LOGICAL_COMPONENT_1_DELEGATED_REQUIRE_LINK_ID = "5388b091-0ec4-4ac2-a598-c3fb38a565a0";
  protected final String PROPERTY_VALUE_ID = "f2e87e51-1051-4e7d-9e0a-e7cddf94b5dd";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[IDB] Interfaces Diagram Blank Filters Test Diagram";
  }

}
