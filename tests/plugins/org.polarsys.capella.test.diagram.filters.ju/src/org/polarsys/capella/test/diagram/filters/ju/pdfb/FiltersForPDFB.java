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
package org.polarsys.capella.test.diagram.filters.ju.pdfb;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForPDFB extends DiagramObjectFilterTestCase {

  protected final String FUNCTIONAL_EXCHANGE_4_ID = "7ad3bd11-3149-4952-a616-56613e173810";
  protected final String FUNCTIONAL_EXCHANGE_3_ID = "7668dce8-674b-4288-9b61-eb6bac01805b";
  protected final String FUNCTION_PORT_WITHOUT_EXCHANGES_ID = "ff8f1cde-813a-4442-96d4-84d3552421bf";
  protected final String PROPERTY_VALUE_ID = "7d725b80-73fe-4fcf-b151-3d9b6a58380d";
  protected final String FUNCTION_OUTPUT_PORT_1_ID = "f3e38d96-43b9-4488-ab88-fa304eefb6d1";
  protected final String FUNCTION_OUTPUT_PORT_2_ID = "ec3ab207-3505-4600-ab88-bdad0e1b2611";
  protected final String FUNCTION_OUTPUT_PORT_3_ID = "677ac8cc-e0b0-443c-8132-579f0c5fedcc";
  protected final String FUNCTION_INPUT_PORT_1_ID = "15c87cf3-8dcd-4323-a432-15e69517bb3b";
  protected final String FUNCTION_INPUT_PORT_2_ID = "a05f84c5-d959-447e-9b61-d385d34b6ec4";
  protected final String FUNCTION_INPUT_PORT_3_ID = "cc097f45-580a-41a5-b233-3e0f546fb523";
  protected final String EXCHANGE_CATEGORY_ID = "aceb4434-8e6d-4790-a4ce-764bdb34e40b";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[PDFB] Root Physical Function";
  }

}
