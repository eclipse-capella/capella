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
package org.polarsys.capella.test.diagram.filters.ju.xfcd;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class MergeFilterSFCDTest extends DiagramObjectFilterTestCase {

  private final String SA_EXCHANGE_5 = "0d5203a8-c8d0-4d2e-9c8d-1f88a1c11ec4";
  private final String SA_EXCHANGE_7 = "b7b65698-e0af-48c6-bbe1-1090cbe3c4e0";
  private final String SA_EXCHANGE_8 = "9ca3daa1-fd42-471d-9cb4-37bb3ee68cf2";

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
    return "[SFCD] Merge Filter Test Diagram";
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { SA_EXCHANGE_5, SA_EXCHANGE_7, SA_EXCHANGE_8 });
  }

}
