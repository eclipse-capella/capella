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
package org.polarsys.capella.test.diagram.filters.ju.lab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class HidePhysicalLinksNamesForLAB extends LabelFilterTestCase {

  protected final String PHYSICAL_LINK_ID = "8765332d-0948-4d89-8781-c77f3d319931";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[LAB] Logical System";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LAB_HIDE_PHYSICAL_LINKS_NAME;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { PHYSICAL_LINK_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { "; ;" });
  }

}
