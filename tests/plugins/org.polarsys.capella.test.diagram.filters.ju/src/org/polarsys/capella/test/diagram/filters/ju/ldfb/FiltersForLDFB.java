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
package org.polarsys.capella.test.diagram.filters.ju.ldfb;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForLDFB extends DiagramObjectFilterTestCase {

  protected final String FUNCTIONAL_EXCHANGE_3_ID = "8cf09c8b-08f1-40b7-a624-03ba8a89769d";
  protected final String FUNCTIONAL_EXCHANGE_5_ID = "ed5417a3-b973-41b3-b7d5-e71b2c251ebe";
  protected final String FUNCTION_PORT_WITHOUT_EXCHANGES_ID = "2291dd2d-aa57-4aeb-b320-421c57b2c26e";
  protected final String PROPERTY_VALUE_ID = "7d725b80-73fe-4fcf-b151-3d9b6a58380d";
  protected final String FUNCTION_OUTPUT_PORT_1_ID = "93199ea2-e10f-4698-a81d-1552c3d57aa6";
  protected final String FUNCTION_OUTPUT_PORT_2_ID = "8e5d376a-64a7-4b09-bf2c-6b3b6c861c3e";
  protected final String FUNCTION_OUTPUT_PORT_3_ID = "798aadbe-46e2-466e-800a-9bddaf783a2e";
  protected final String FUNCTION_INPUT_PORT_1_ID = "895780cf-5290-4c08-88a2-4ade731bb1b6";
  protected final String FUNCTION_INPUT_PORT_2_ID = "df8dd3eb-ba8a-4074-9c33-9737e53cd5d8";
  protected final String FUNCTION_INPUT_PORT_3_ID = "dee29cfb-e01d-4da6-882f-b6b58b2f4d2e";
  protected final String EXCHANGE_CATEGORY_ID = "bbb7bf8b-921e-4185-b52e-16460349e60d";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[LDFB] Root Logical Function";
  }

}
