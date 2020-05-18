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
package org.polarsys.capella.test.diagram.filters.ju.crb;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideCapabilityRealizationGeneralizations extends DiagramObjectFilterTestCase {

  private final String GENERALIZATION_LINK_ID = "ce8bed93-7ed5-4585-9b2c-e24afbced44f";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel"; //$NON-NLS-1$
  }

  @Override
  protected String getDiagramName() {
    return "Capabilities Realization Blank Filters Test Diagram"; //$NON-NLS-1$
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_REALIZATION_GENERALIZATIONS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { GENERALIZATION_LINK_ID // $NON-NLS-1$
    });
  }
}
