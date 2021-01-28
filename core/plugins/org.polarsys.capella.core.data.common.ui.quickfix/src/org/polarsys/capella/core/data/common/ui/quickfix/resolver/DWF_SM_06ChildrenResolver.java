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
package org.polarsys.capella.core.data.common.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;

public class DWF_SM_06ChildrenResolver extends DWF_SM_06Resolver {

  @Override
  protected List<AbstractState> getMixedStates(Object obj) {
    // get mixed children states
    List<AbstractState> lstStates = new ArrayList<>();
    if (obj instanceof State) {
      EList<Region> regions = ((State) obj).getOwnedRegions();
      for (Region region : regions) {
        for (AbstractState state : region.getOwnedStates()) {
          if (areMixedStateMode(obj, state)) {
            lstStates.add(state);
          }
        }
      }
    }
    return lstStates;
  }
}
