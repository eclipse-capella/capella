/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.xab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.model.InternalLinks;

public class HideFCInternalLinksLDFB extends InternalLinks {

  @Override
  protected String getDiagramName() {
    return "[LDFB] Root Logical Function";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FUNCTIONAL_CHAINS_INTERNAL_LINKS_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { LA_FUNCTIONALCHAIN_1, LA_FUNCTIONALCHAIN_2, LA_FUNCTIONALCHAIN_3 });
  }
}
