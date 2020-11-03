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

public class HideUseLinks extends DiagramObjectFilterTestCase {

  private final String USE_LINK_1_ID = "19cf9bf4-d1f4-4870-ab5d-5b9478285039";
  private final String USE_LINK_2_ID = "e6dc15b7-2fb6-43a0-b9e4-59e5ed31e49f";

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
    return IFilterNameConstants.FILTER_CEI_HIDE_USE_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { USE_LINK_1_ID, USE_LINK_2_ID });
  }
}
