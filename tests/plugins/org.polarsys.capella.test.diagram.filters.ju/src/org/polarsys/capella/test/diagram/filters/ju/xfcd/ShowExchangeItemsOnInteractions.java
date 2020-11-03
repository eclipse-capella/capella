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
package org.polarsys.capella.test.diagram.filters.ju.xfcd;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowExchangeItemsOnInteractions extends LabelFilterTestCase {

  @Override
  protected String getTestProjectName() {
    return "ElementLabelFilterModel";
  }

  @Override
  protected String getDiagramName() {
    return "Test Operational Process Description Diagram";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_SHOW_EXCHANGEITEMS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { 
        "b39f8dbd-c6ad-43c9-96af-7426a27e4995", //$NON-NLS-1$
        "361932c9-bf20-4bca-9232-a858bf915476" //$NON-NLS-1$
    });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { ";ExchangeItem 1;", ";ExchangeItem 2;" });
  }
}
