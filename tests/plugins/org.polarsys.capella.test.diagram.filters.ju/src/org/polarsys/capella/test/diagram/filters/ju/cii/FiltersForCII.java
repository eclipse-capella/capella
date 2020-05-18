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
package org.polarsys.capella.test.diagram.filters.ju.cii;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForCII extends DiagramObjectFilterTestCase {

  protected final String REQUIRED_INTERFACE_ID = "f8fe5fcf-bbf3-4ad0-ab6d-274ad7307091";
  protected final String TEST_IMPLEPENTED_INTERFACE_ID = "771d9de0-21ce-48cf-9d3b-24a57bfd75e7";
  protected final String PROVIDED_INTERFACE_ID = "15cabe28-1fbb-4301-8d68-6f414f56fcc9";
  protected final String TEST_INTERFACE_ID = "1fbbfe28-401d-47cb-8a99-6e7d371f07ef";
  protected final String INTERFACE_1_ID = "eb8b4fb4-8388-4f03-9b3a-6068c8bb27f9";
  protected final String INTERFACE_3_ID = "5228a713-5de0-4826-a1f2-03ba9c45bd36";
  protected final String EXTERNAL_INTERFACE_1_ID = "58c10be9-9542-4a21-ac7e-415593f260c6";
  protected final String INVOLVED_LOGICAL_COMPONENT_ID = "a055b9e7-12f9-4a9f-8e81-2f11f20d45dc";
  protected final String TEST_LOGICAL_COMPONENT_2_ID = "7f42e70a-0601-4eeb-b21c-ed2d2bb475ce";
  protected final String LOGICAL_COMPONENT_3_ID = "2b058f13-6581-44de-a3b9-e72e29b06816";
  protected final String EXCHANGE_ITEM_2_ID = "bda72a9d-c456-417a-9824-16b7a5e2d342";
  protected final String TEST_EXCHANGE_ITEM_3_ID = "695bbde6-942b-4839-b7fc-371c6398742a";
  protected final String EXCHANGE_ITEM_1_ID = "ca8f2bb1-dc9f-4744-b933-f4be6b2a5e16";

  protected final String TEST_LOGICAL_COMPONENT_2_TO_REQUIRED_INTERFACE_ID = "e7c3f00c-9761-4145-99a3-fc764fdcc500";
  protected final String INVOLVED_LOGICAL_COMPONENT_TO_REQUIRED_INTERFACE_ID = "f285a64f-a917-4bc5-9e03-4749f2303f96";
  protected final String PROVIDED_INTERFACE_TO_REQUIRED_INTERFACE_ID = "dbaf2330-08b8-4ae4-bb54-410f09a78c20";

  protected final String TEST_LOGICAL_COMPONENT_2_TO_TEST_IMPLEPENTED_INTERFACE_ID = "8efa373f-2cfc-446f-9346-b8a24d6f13e2";
  protected final String INVOLVED_LOGICAL_COMPONENT_TO_TEST_IMPLEPENTED_INTERFACE_ID = "8abe36ac-484a-4c77-8f3c-a1f0f27395ba";
  protected final String TEST_INTERFACE_TO_TEST_IMPLEPENDED_INTERFACE_ID = "ac140f68-c07b-4910-9ad3-0f23e544d36d";

  protected final String TEST_LOGICAL_COMPONENT_2_TO_PROVIDED_INTERFACE_ID = "ad590566-fb37-46a8-9f7b-3c57353a9fb7";
  protected final String INVOLVED_LOGICAL_COMPONENT_TO_PROVIDED_INTERFACE_ID = "07b6952d-152d-4cd4-8c6a-cae3db3a6d29";

  protected final String TEST_LOGICAL_COMPONENT_2_TO_TEST_INTERFACE_ID = "e6dc15b7-2fb6-43a0-b9e4-59e5ed31e49f";
  protected final String INVOLVED_LOGICAL_COMPONENT_TO_TEST_INTERFACE_ID = "19cf9bf4-d1f4-4870-ab5d-5b9478285039";

  protected final String LOGICAL_COMPONENT_3_TO_INTERFACE_3_USE_ID = "f13e9435-4f89-4b72-917c-d5db41bafdca";
  protected final String LOGICAL_COMPONENT_3_TO_INTERFACE_3_IMPLEMENT_ID = "23a36e1f-79be-482d-9494-c2a3b7c7088a";
  protected final String LOGICAL_COMPONENT_3_TO_INTERFACE_3_PROVIDE_ID = "2da78948-d087-46e4-95e7-a003bfb0ef5d";
  protected final String LOGICAL_COMPONENT_3_TO_INTERFACE_3__REQUIRE_ID = "4fe866eb-e002-433a-a708-3b011d88c9e0";
  protected final String LOGICAL_COMPONENT_3_TO_INTERFACE_1_USE_ID = "78e53971-e83c-42a5-beae-a5b3bea64af7";
  protected final String LOGICAL_COMPONENT_3_TO_INTERFACE_1_IMPLEMENT_ID = "2379948a-e07a-4985-af8f-ee14dc5e5608";
  protected final String LOGICAL_COMPONENT_3_TO_INTERFACE_1_PROVIDE_ID = "e090c7cc-5e79-43b7-9ced-5b5ccc5dce26";
  protected final String LOGICAL_COMPONENT_3_TO_INTERFACE_1_REQUIRE_ID = "a429054a-3cf8-4d90-8b27-78b936dbcc73";

  protected final String CONSUME_ID = "38e97714-4a8c-4d9f-b21f-07e3b5ccb7b2";
  protected final String PRODUCE_ID = "447b09a5-79f5-473f-9e4e-3de8baab524d";
  protected final String SEND_ID = "c616c017-a669-412f-b4fd-ef7bbf9eb11b";
  protected final String LOGICAL_SYSTEM_TO_EXCHANGE_ITEM_SEND_ID = "ce1b0001-c0df-4767-9373-70dff35bcc60";

  protected final String TEST_LOGICAL_COMPONENT_2_COMPONENT_PORT_1_ID = "ad590566-fb37-46a8-9f7b-3c57353a9fb7";
  protected final String TEST_LOGICAL_COMPONENT_2_COMPONENT_PORT_2_ID = "e7c3f00c-9761-4145-99a3-fc764fdcc500";
  protected final String TEST_LOGICAL_COMPONENT_2_COMPONENT_PORT_5_ID = "a12d10e7-469c-4001-a0c6-4e652401259d";
  protected final String LOGICAL_COMPONENT_3_COMPONENT_PORT_1_ID = "06c6decf-ec49-4ffa-818f-17ccb656ffa2";
  protected final String LOGICAL_COMPONENT_3_COMPONENT_PORT_2_ID = "e090c7cc-5e79-43b7-9ced-5b5ccc5dce26";
  protected final String LOGICAL_COMPONENT_3_COMPONENT_PORT_3_ID = "a429054a-3cf8-4d90-8b27-78b936dbcc73";
  protected final String LOGICAL_COMPONENT_3_COMPONENT_PORT_5_ID = "2da78948-d087-46e4-95e7-a003bfb0ef5d";
  protected final String LOGICAL_COMPONENT_3_COMPONENT_PORT_6_ID = "4fe866eb-e002-433a-a708-3b011d88c9e0";
  protected final String INVOLVED_LOGICAL_COMPONENT_COMPONENT_PORT_1_ID = "f285a64f-a917-4bc5-9e03-4749f2303f96";
  protected final String INVOLVED_LOGICAL_COMPONENT_COMPONENT_PORT_2_ID = "07b6952d-152d-4cd4-8c6a-cae3db3a6d29";
  protected final String LOGICAL_COMPONENT_1_COMPONENT_PORT_1_ID = "f5af609b-a9d8-4457-b72b-4e77c54a637c";

  protected final String LOGICAL_SYSTEM_TO_INTERFACE_1_REQUIRE_PORT_ID = "1c767656-007a-48e0-aa38-46942fbf5e45";
  protected final String LOGICAL_SYSTEM_TO_INTERFACE_1_PROVIDE_PORT_ID = "b76c2468-20f7-4502-b913-2adb107a8862";
  protected final String LOGICAL_SYSTEM_TO_INTERFACE_1_USE_LINK_ID = "89cd9df8-3c99-424f-855b-9dd456f62b61";
  protected final String LOGICAL_SYSTEM_TO_INTERFACE_1_IMPLEMENT_LINK_ID = "f99a3a8b-249c-4305-a543-0cff17cd0145";

  protected final String SIMPLIFIED_TEST_LOGICAL_COMPONENT_2_ID = "7f42e70a-0601-4eeb-b21c-ed2d2bb475ce";
  protected final String SIMPLIFIED_LOGICAL_COMPONENT_3_ID = "2b058f13-6581-44de-a3b9-e72e29b06816";
  protected final String SIMPLIFIED_LOGICAL_SYSTEM_ID = "1771e1cb-41af-49da-81a2-5c2bf33df9ac";

  protected final String TECHNICAL_INTERFACE_ID = "3784ab33-1066-4a10-ae86-8eac0af66fef";

  protected final String PROPERTY_VALUE_ID = "23a94bf1-7956-48f9-91f6-631d92909008";

  protected final String EXCHANGE_ITEM_ALLOCATION_ID = "a5192baa-9db5-4976-8a37-f7032018f79d";
  protected final String PORT_DELEGATION_ID = "83e6665a-5733-4222-904a-a8f78bd86439";
  protected final String PORT_DELEGATION_2_ID = "4481098a-9330-4c4d-bcbb-fbb8c017acbf";

  protected final String LOGICAL_COMPONENT_1_PROVIDE_ID = "c6caadf9-73ca-446c-bad2-5430bd74dd4f";
  protected final String LOGICAL_COMPONENT_1_REQUIRE_ID = "69a09066-15d4-4446-99e3-7d427042f72e";
  protected final String DELEGATED_IMPLEMENT_ID = "b0eba9ea-a06f-4bf4-8145-4ee57719f5fe";
  protected final String DELEGATED_USE_ID = "39a576a9-de05-4ace-8204-f197003bcc0f";
  protected final String DELEGATED_PROVIDE_ID = "f9db1445-3e2d-42c3-bcc5-29baef84a03e";
  protected final String DELEGATED_REQUIRE_ID = "1a066a3b-2893-47d9-acdb-2a0e2bb6c686";

  protected final String COMPONENT_PORT_1_ID = "a3baa533-de38-4934-9e7b-5b6ea94ba6ed";
  protected final String COMPONENT_PORT_6_ID = "790c935f-36fa-4b1c-90fb-520a445a0428";
  protected final String COMPONENT_PORT_7_ID = "a948be04-293a-4cbe-8feb-b6f673c58409";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[CII] Contextual Component Internal Interfaces";
  }

}
