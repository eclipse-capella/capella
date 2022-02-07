/*******************************************************************************
 * Copyright (c) 2022 OBEO.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Obeo - 2303 Add computed transitions in M&S diagrams
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.msm;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideComputedTransitions extends DiagramObjectFilterTestCase {

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel"; //$NON-NLS-1$
  }

  @Override
  protected String getDiagramName() {
    return "[MSM] Default Region"; //$NON-NLS-1$
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_MSM_HIDECOMPUTEDTRANSITIONS_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { "afe37038-d4c7-4ca2-96c3-5b2bcd3a07df", //$NON-NLS-1$
        "693cadb5-3b79-4eef-a4ed-9f20c19532d0", //$NON-NLS-1$
        "522fcdcf-801a-47de-ba03-4551207accbe", //$NON-NLS-1$
        "97b5a589-23c4-483b-ba68-1914a933244f" //$NON-NLS-1$
    });
  }
}
