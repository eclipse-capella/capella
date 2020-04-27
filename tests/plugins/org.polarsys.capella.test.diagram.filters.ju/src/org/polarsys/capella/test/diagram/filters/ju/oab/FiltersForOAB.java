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
package org.polarsys.capella.test.diagram.filters.ju.oab;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForOAB extends DiagramObjectFilterTestCase {

  protected final String ALLOCATED_INTERACTION_ID = "3e7ee01c-9a6d-4f79-827d-7fb2cd14ea64";
  protected final String OPERATIONAL_ACTIVITY_3_ID = "f6ddc323-166f-488d-96c0-cf646422b79e";
  protected final String OPERATIONAL_ACTIVITY_4_ID = "b4593ef2-3f8f-4c05-8518-91aaaabbf389";
  protected final String OPERATIONAL_ACTIVITY_5_ID = "b70714f5-5c07-4f61-a567-ea350628e1a3";
  protected final String OPERATIONAL_ACTIVITY_6_ID = "dc3bc0d8-321a-43b7-aa5c-1baac9ad3bf7";
  protected final String OPERATIONAL_ACTIVITY_7_ID = "5645654d-8039-420a-abd5-9dba2dc3edc2";
  protected final String ROLE_5_ID = "21ce9a5d-33c6-43bf-8104-71ba1a2f04da";
  protected final String ROLE_6_ID = "1d57694a-ee8a-4c6c-9c0e-b3e4512c5760";
  protected final String OPERATIONAL_ACTOR_ID = "63ce85a7-1c5b-41a8-9834-10d26d065765";
  protected final String INTERACTION_2_ID = "af2a78bd-81f7-44cd-b9f9-2d0a2bdbc701";
  protected final String INTERACTION_4_ID = "3e7ee01c-9a6d-4f79-827d-7fb2cd14ea64";
  protected final String INTERACTION_5_ID = "9a065225-bfae-4c36-97cc-82a9b5326663";
  protected final String INTERACTION_6_ID = "f2be3e58-70cc-406a-b936-0ffa8fc71309";
  protected final String INTERACTION_7_ID = "874db564-12de-44a8-9420-de5126a28192";
  protected final String COMMUNICATION_MEAN_ID = "9f4e88a5-5980-4622-8717-9a5590a70c49";
  protected final String PROPERTY_VALUES_ID = "67e5ecbe-cfd8-49f5-8947-83d6b5fde730";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[OAB] Operational Architecture Blank";
  }

}
