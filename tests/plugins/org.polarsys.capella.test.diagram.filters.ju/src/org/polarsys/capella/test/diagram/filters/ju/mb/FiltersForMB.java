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
package org.polarsys.capella.test.diagram.filters.ju.mb;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForMB extends DiagramObjectFilterTestCase {

  protected final String CAPABILITY_ID = "7e9fe16b-6cf9-4c5d-97e9-cf4ca0bf76b7";
  protected final String CAPABILITY_10_ID = "3eccb3ab-6d13-44fa-9c33-67bbb0b7b96c";
  protected final String CAPABILITY_EXPLOITATIONS_ID = "cdc7394c-ad29-4003-b67c-87d739446c66";
  protected final String CAPABILITY_GENERALIZATIONS_ID = "fb5b10f4-8f18-4972-9b4e-32a5d5faa944";
  protected final String MISSION_ID = "68ac1423-b21e-4a22-9296-15bf33224c75";
  protected final String ACTOR_7_ID = "e830c43a-2144-4d9a-bb58-45c5426d2a49";
  protected final String ACTOR_8_ID = "a9875ba5-6ab3-49f5-bb96-88f8d73f78e0";
  protected final String ACTOR_INVOLVEMENTS_ID = "c082a1ad-ea21-4401-abe4-71f4496b408e";
  protected final String ACTOR_GENERALIZATIONS_ID = "a51985a6-b759-4335-951a-5d41aa16acf5";
  protected final String PROPERTY_VALUES_ID = "a2f462b7-2c1f-4a73-b9fb-09c52e17fcba";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[MB] Missions";
  }

}
