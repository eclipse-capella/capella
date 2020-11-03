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

public class HideInterfaces extends DiagramObjectFilterTestCase {

  private final String INTERFACE_ID = "1fbbfe28-401d-47cb-8a99-6e7d371f07ef";
  private final String IMPLEMENTED_INTERFACE_ID = "771d9de0-21ce-48cf-9d3b-24a57bfd75e7";
  private final String PROVIDED_INTERFACE_ID = "15cabe28-1fbb-4301-8d68-6f414f56fcc9";
  private final String REQUIRED_INTERFACE_ID = "f8fe5fcf-bbf3-4ad0-ab6d-274ad7307091";

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
    return IFilterNameConstants.FILTER_CEI_HIDE_INTERFACES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays
        .asList(new String[] { INTERFACE_ID, IMPLEMENTED_INTERFACE_ID, PROVIDED_INTERFACE_ID, REQUIRED_INTERFACE_ID });
  }
}
