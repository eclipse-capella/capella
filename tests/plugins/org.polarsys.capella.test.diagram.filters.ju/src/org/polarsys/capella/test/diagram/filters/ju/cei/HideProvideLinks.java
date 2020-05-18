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
package org.polarsys.capella.test.diagram.filters.ju.cei;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideProvideLinks extends DiagramObjectFilterTestCase {

  private final String PROVIDES_LINK_1_ID = "07b6952d-152d-4cd4-8c6a-cae3db3a6d29";
  private final String PROVIDES_LINK_2_ID = "ad590566-fb37-46a8-9f7b-3c57353a9fb7";

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
    return IFilterNameConstants.FILTER_CEI_HIDE_PROVIDE_LINKS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { PROVIDES_LINK_1_ID, PROVIDES_LINK_2_ID });
  }

}
