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

public class MergeFilterOPDTest extends DiagramObjectFilterTestCase {

  private final String OA_INTERACTION_5 = "4f73cb86-1936-4a7c-a1b9-4559ea8a839a";
  private final String OA_INTERACTION_7 = "caf71896-61c5-4e5a-83dd-f17ba2a47ae6";
  private final String OA_INTERACTION_8 = "3a8ffcc0-83b5-4ab3-959d-a38554bb14cb";

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
    return "[OPD] Merge Filter Test Diagram";
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { OA_INTERACTION_5, OA_INTERACTION_7, OA_INTERACTION_8 });
  }

}
