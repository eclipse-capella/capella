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
package org.polarsys.capella.test.diagram.filters.ju.cm;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForCM extends DiagramObjectFilterTestCase {

  protected final String CAPABILITY_ID = "b57fd423-0df5-47b2-836d-e3aad4087597";
  protected final String CAPABILITY_12_ID = "1b2ce76d-e18d-48fa-9c9c-e815e4c75ada";
  protected final String CAPABILITY_EXPLOITATIONS_ID = "eed53d63-d9c1-4703-82f5-a38f0bdb578e";
  protected final String CAPABILITY_GENERALIZATIONS_ID = "8d2bcfe2-4b9a-45b7-b626-e5238c56c252";
  protected final String ACTOR_3_ID = "7f15675a-dd71-4fca-8cae-aca5b72a7b4c";
  protected final String ACTOR_4_ID = "90596c74-d831-4cd1-a7df-c17101a05f6b";
  protected final String ACTOR_INVOLVEMENTS_ID = "680a9161-20b0-4afb-83aa-78fa28e26d12";
  protected final String ACTOR_GENERALIZATIONS_ID = "c5d9eac9-1ca4-4117-a492-6d20fcb4aa57";
  protected final String PROPERTY_VALUES_ID = "a2f462b7-2c1f-4a73-b9fb-09c52e17fcba";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[CM] Mission 1";
  }

}
