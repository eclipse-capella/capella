/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;

public class DWF_SM_06SiblingResolver extends DWF_SM_06Resolver {

  @Override
  protected List<AbstractState> getMixedStates(Object obj) {
    // get mixed siblings states
    List<AbstractState> lstStates = new ArrayList<>();
    if (obj instanceof State && !((State) obj).getInvolverRegions().isEmpty()) {
      Region region = ((State) obj).getInvolverRegions().get(0);
      for (AbstractState state : region.getOwnedStates()) {
        if (areMixedStateMode(obj, state)) {
          lstStates.add(state);
        }
      }
    }
    return lstStates;
  }
}
