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
package org.polarsys.capella.test.diagram.filters.ju.ocb;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForOCB extends DiagramObjectFilterTestCase {

  protected final String COMMUNICATION_MEAN_ID = "2f65fa62-28c2-4c64-a7bd-670102bfed63";
  protected final String INVOLVMENT_LINK_ID = "5a0b7e11-6584-41c4-9d39-74cec505de22";
  protected final String OPERATIONAL_CAPABILITY_EXTENDS = "3ae2ac19-ed35-4f91-b399-a142f043afc9";
  protected final String OPERATIONAL_CAPABILITY_INCLUDES = "17123030-e740-4f1a-8759-bb874c4506bd";
  protected final String OPERATIONAL_CAPABILITY_GENERALIZATIONS = "c36dc56b-7d85-498f-9f20-f6eef0adc718";
  protected final String PROPERTY_VALUES_ID = "67e5ecbe-cfd8-49f5-8947-83d6b5fde730";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[OCB] Operational Capabilities Blank";
  }

}
