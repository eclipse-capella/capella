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
package org.polarsys.capella.test.diagram.filters.ju.cc;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForCC extends DiagramObjectFilterTestCase {

  protected final String ACTOR_1_ID = "42288bd8-38b0-43c2-811a-2652a9c5a4a6";
  protected final String ACTOR_2_ID = "7a713533-df80-4403-a01d-76c203fb1817";
  protected final String ACTOR_INVOLVEMENTS_ID = "c680b9cf-1549-4927-9fd1-bff5024fcf69";
  protected final String ACTOR_GENERALIZATIONS_ID = "18e0884d-c93f-469d-8ba9-28d29746666e";
  protected final String CAPABILITY_1_ID = "08634c69-39eb-4e1a-b41b-0d324711a67c";
  protected final String CAPABILITY_2_ID = "01f73729-471d-4491-95b5-2611e75069d6";
  protected final String CAPABILITY_3_ID = "f3469df2-1689-4178-9609-f16ee9a8d3a8";
  protected final String MISSION_ID = "86c61051-bfaf-4111-aeae-7cb7f5c0db75";
  protected final String PROPERTY_VALUES_ID = "a2f462b7-2c1f-4a73-b9fb-09c52e17fcba";
  protected final String CAPABILITY_EXTENDS_ID = "6d475058-1bb7-4747-b223-8c4c0272958a";
  protected final String CAPABILITY_INCLUDES_ID = "645b8f09-8ebe-450c-83ed-d072e2ef2c96";
  protected final String CAPABILITY_EXPLOITATION_ID = "46ce981a-129b-40d3-bd11-02c5ac9cc012";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[CC] Capability 1";
  }

}
