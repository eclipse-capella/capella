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
package org.polarsys.capella.test.diagram.filters.ju.xfcd;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class MergeFilterPFCDTest extends DiagramObjectFilterTestCase {

  private final String PA_EXCHANGE_5 = "33e5cc5a-ba8d-4759-a2c6-e2763e78b48f";
  private final String PA_EXCHANGE_7 = "a778ee5c-7b72-4a78-886f-fab1f60f008d";
  private final String PA_EXCHANGE_8 = "87b97b1e-ab9f-4e51-8d2e-e350915bfdf5";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_MERGE_ASSOCIATED_FE_AND_SL;
  }

  @Override
  protected String getDiagramName() {
    return "[PFCD] Merge Filter Test Diagram";
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { PA_EXCHANGE_5, PA_EXCHANGE_7, PA_EXCHANGE_8 });
  }
}
