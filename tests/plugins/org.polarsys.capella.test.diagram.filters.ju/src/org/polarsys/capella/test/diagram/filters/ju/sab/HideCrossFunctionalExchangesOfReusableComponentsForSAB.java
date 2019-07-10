/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.sab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HideCrossFunctionalExchangesOfReusableComponentsForSAB extends FiltersForSAB {

  @Override
  protected String getFilterName() {
	  return IFilterNameConstants.FILTER_SAB_HIDE_CROSS_FUNCTIONAL_EXCHANGES_OF_REUSABLE_COMPONENTS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] {});
  }

}
