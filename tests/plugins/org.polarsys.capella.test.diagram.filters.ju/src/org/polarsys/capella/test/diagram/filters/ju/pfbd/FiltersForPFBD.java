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
package org.polarsys.capella.test.diagram.filters.ju.pfbd;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForPFBD extends DiagramObjectFilterTestCase {

  protected final String CONTROL_NODE_1_ID = "991ea7ad-c324-4d4b-b623-a62fb7aa3259";
  protected final String CONTROL_NODE_2_ID = "4364f51d-af01-45ea-8199-18edfcf30909";
  protected final String CONTROL_NODE_3_ID = "070f8295-8059-4488-bb4d-b21eb08cb5c7";
  protected final String PROPERTY_VALUES_ID = "347bfb80-3575-422e-a579-938c6632c18b";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[PFBD] Root Physical Function";
  }

}
