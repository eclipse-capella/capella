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

public class HideInvolvements extends DiagramObjectFilterTestCase {

  private final String INVOLVEMENT_TO_LOGICAL_COMPONENT_ID = "3c5bc0ee-df11-46ac-acee-f15e3735ee98";
  private final String INVOLVEMENT_TO_LOGICAL_ACTOR_ID = "e362dca5-3df5-4f33-afd7-8a381c65734e";

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
    return IFilterNameConstants.FILTER_CRB_HIDE_INVOLVEMENTS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { INVOLVEMENT_TO_LOGICAL_COMPONENT_ID, // $NON-NLS-1$
        INVOLVEMENT_TO_LOGICAL_ACTOR_ID // $NON-NLS-1$
    });
  }

}
