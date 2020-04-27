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
package org.polarsys.capella.test.diagram.filters.ju.mcb;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForMCB extends DiagramObjectFilterTestCase {

  protected final String CAPABILITY_5_ID = "f601c91d-1122-4324-ad08-d32d2dd45325";
  protected final String CAPABILITY_6_ID = "fb7c17f9-56ae-47b3-8b39-0b0eb05dfdaa";
  protected final String CAPABILITY_7_ID = "1d5404a1-1ce9-4589-b786-66bd6623ff54";
  protected final String CAPABILITY_9_ID = "6badd673-0f64-4580-acd8-a207d60031c3";
  protected final String CAPABILITY_EXPLOITATIONS_ID = "82bc9c71-522f-4aeb-bc01-9ea29b949706";
  protected final String CAPABILITY_EXTENDS_ID = "59d53e98-2e39-4d6f-95d3-8f5731f9b90e";
  protected final String CAPABILITY_INCLUDES_ID = "4a1d1af0-c4ca-42b6-99df-06de12aaf4b4";
  protected final String CAPABILITY_GENERALIZATIONS_ID = "dac4dab4-c0ad-4421-b867-e3a7863fc606";
  protected final String MISSION_ID = "de12d5d3-724c-4b45-b7c4-ff81f36539ff";
  protected final String ACTOR_5_ID = "96b3fa3e-5d25-46ec-8ff6-41dbec8f749a";
  protected final String ACTOR_6_ID = "542d3a52-501c-4940-8e9f-68151a0090a8";
  protected final String ACTOR_MISSION_INVOLVEMENTS_ID = "536c9482-7757-4df3-88b2-34b6f0d1e4af";
  protected final String ACTOR_CAPABILITY_INVOLVEMENTS_ID = "646760be-7550-4d08-a443-de5b7e545a4a";
  protected final String ACTOR_GENERALIZATIONS_ID = "d4cfe9ac-9d9d-471c-a85f-c346ccc72ed1";
  protected final String PROPERTY_VALUES_ID = "a2f462b7-2c1f-4a73-b9fb-09c52e17fcba";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[MCB] Capabilities";
  }

}
