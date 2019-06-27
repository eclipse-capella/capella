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
package org.polarsys.capella.test.diagram.filters.ju.oab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HideOperationalActivitiesForOAB extends FiltersForOAB {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_OAB_HIDE_OPERATIONAL_ACTIVITIES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { OPERATIONAL_ACTIVITY_3_ID, OPERATIONAL_ACTIVITY_4_ID, OPERATIONAL_ACTIVITY_5_ID,
        OPERATIONAL_ACTIVITY_6_ID, OPERATIONAL_ACTIVITY_7_ID });
  }

}
