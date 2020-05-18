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
package org.polarsys.capella.test.diagram.filters.ju.cii;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideTechnicalInterfacesForCII extends DefaultActivatedFilterTestCase {

  protected final String TECHNICAL_INTERFACE_ID = "3784ab33-1066-4a10-ae86-8eac0af66fef";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[CII] Contextual Component Internal Interfaces";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LCCII_HIDE_TECHNICALS_INTERFACES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { TECHNICAL_INTERFACE_ID });
  }
}
