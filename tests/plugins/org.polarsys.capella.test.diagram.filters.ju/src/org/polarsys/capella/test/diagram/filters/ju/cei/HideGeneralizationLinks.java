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
package org.polarsys.capella.test.diagram.filters.ju.cei;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideGeneralizationLinks extends DiagramObjectFilterTestCase {

  private final String GENERALIZATION_1_ID = "ac140f68-c07b-4910-9ad3-0f23e544d36d";
  private final String GENERALIZATION_2_ID = "dbaf2330-08b8-4ae4-bb54-410f09a78c20";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel"; //$NON-NLS-1$
  }

  @Override
  protected String getDiagramName() {
    return "Contextual Component External Interfaces Test Diagram"; //$NON-NLS-1$
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_CEI_HIDE_GENERALIZATION_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { GENERALIZATION_1_ID, GENERALIZATION_2_ID, });
  }
}
