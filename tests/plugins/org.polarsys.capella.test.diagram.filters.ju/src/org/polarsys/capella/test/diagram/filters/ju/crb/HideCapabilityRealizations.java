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

public class HideCapabilityRealizations extends DiagramObjectFilterTestCase {

  private final String MAIN_CAPABILITY_REALIZATION_ID = "d26b8f06-03ba-4317-8f8e-4b5074777d4c";
  private final String EXTENDED_CAPABILITY_REALIZATION_ID = "6db7132c-0b59-4d2f-958e-696515962dba";
  private final String INCLUDED_CAPABILITY_REALIZATION_ID = "d23915c2-b299-486d-9238-cc8b90b4c579";
  private final String GENERALIZED_CAPABILITY_REALIZATION_ID = "832fe2f0-8333-4600-8266-4f068024fdc6";

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
    return IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_REALIZATIONS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { MAIN_CAPABILITY_REALIZATION_ID, // $NON-NLS-1$
        EXTENDED_CAPABILITY_REALIZATION_ID, // $NON-NLS-1$
        INCLUDED_CAPABILITY_REALIZATION_ID, // $NON-NLS-1$
        GENERALIZED_CAPABILITY_REALIZATION_ID // $NON-NLS-1$
    });
  }

}
