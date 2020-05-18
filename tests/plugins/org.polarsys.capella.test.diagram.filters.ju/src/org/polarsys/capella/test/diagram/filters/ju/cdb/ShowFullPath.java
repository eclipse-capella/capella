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
package org.polarsys.capella.test.diagram.filters.ju.cdb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowFullPath extends LabelFilterTestCase {

  private final String UNION_ID = "63cec369-f5e4-4397-8019-459f47e4408e";
  private final String CLASS_1_ID = "dd6930fd-548f-4c0a-b272-543f5d81bee1";
  private final String CLASS_2_ID = "4e823e41-98e7-477e-8746-361718e9d1b5";
  private final String CLASS_3_ID = "b33a8d71-e89e-4cfe-862c-67c7a67df2f8";
  private final String COLLECTION_1_ID = "cd9ae5de-f459-49aa-964c-7070848dbdf5";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "Class Diagram Blank Filters Test Diagram";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_CDB_SHOW_FULL_PATH;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { UNION_ID, CLASS_1_ID, CLASS_2_ID, CLASS_3_ID, COLLECTION_1_ID });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { "[SA]::Data::Union 4", "[SA]::Data::Class 1", "[SA]::Data::Class 2",
        "[SA]::Data::Class 3", "[SA]::Data::Collection 1" });
  }

}
