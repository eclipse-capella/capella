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
package org.polarsys.capella.test.diagram.filters.ju.sdfb;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForSDFB extends DiagramObjectFilterTestCase {

  protected final String FUNCTIONAL_EXCHANGE_2_ID = "6d3330df-ba65-4be5-a78b-2f2ce54cbc61";
  protected final String FUNCTIONAL_EXCHANGE_3_ID = "1f957ec0-8f03-4774-89ad-769fa7778e5d";
  protected final String FUNCTION_PORT_WITHOUT_EXCHANGES_ID = "1613ca03-bc6c-4511-bf24-f6874a73e794";
  protected final String PROPERTY_VALUE_ID = "a2f462b7-2c1f-4a73-b9fb-09c52e17fcba";
  protected final String FUNCTION_OUTPUT_PORT_1_ID = "62a23e76-90be-4783-aa54-6d8f39f0fa24";
  protected final String FUNCTION_OUTPUT_PORT_2_ID = "9474b8c7-96be-4712-9f03-66edc74c6c41";
  protected final String FUNCTION_OUTPUT_PORT_3_ID = "77c81647-ba4b-4648-aabb-77a68c6ecf7a";
  protected final String FUNCTION_INPUT_PORT_1_ID = "e860efc8-2fed-418b-b295-aa0183736942";
  protected final String FUNCTION_INPUT_PORT_2_ID = "0f4e0664-0aba-431a-bbee-69754f753095";
  protected final String FUNCTION_INPUT_PORT_3_ID = "d539b2cd-7094-4398-840d-4ad52ebf256f";
  protected final String EXCHANGE_CATEGORY_ID = "f4f31c74-dbb4-4c6a-8b35-19e1721eab7a";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[SDFB] Root System Function";
  }

}
