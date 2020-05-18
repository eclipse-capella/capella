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
package org.polarsys.capella.test.diagram.filters.ju.coc;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForCOC extends DiagramObjectFilterTestCase {

  protected final String OPERATINAL_ACTOR_ID = "70521db2-06fb-4c00-b800-ba2d9b29a0d7";
  protected final String OPERATIONAL_ENTITY_ID = "5407d59c-d537-415b-af7a-b3a72e717c16";
  protected final String INVOLVMENT_LINK_ID = "a4b5e0ac-a9d6-4ff2-abd6-8b036fe1d77f";
  protected final String OPERATIONAL_CAPABILITY_EXTENDS = "37af6968-ef3b-44cd-9bfb-f6f1d82f2354";
  protected final String OPERATIONAL_CAPABILITY_INCLUDES = "11ebd7aa-4689-422a-8a41-cb63b909b140";
  protected final String OPERATIONAL_CAPABILITY_GENERALIZATIONS = "37d65595-2556-4ba7-aaab-5765346cec7e";
  protected final String PROPERTY_VALUES_ID = "67e5ecbe-cfd8-49f5-8947-83d6b5fde730";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[COC] Contextual Operational Capability";
  }

}
