/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.helpers;

import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

public class StateExt {

  /**
   * Returns the first region of the given state and create one if none
   */
  public static Region getPrimaryRegion(State state) {
    return getPrimaryRegion(state, true);
  }

  /**
   * Returns the first region of the given state and create one if required
   */
  public static Region getPrimaryRegion(State state, boolean create) {
    if (state.getOwnedRegions().isEmpty() && create) {
      Region region = CapellacommonFactory.eINSTANCE.createRegion(NamingConstants.Region_DefaultRegion);
      state.getOwnedRegions().add(region);
      CapellaElementExt.creationService(region);
    }
    if (state.getOwnedRegions().isEmpty()) {
      return null;
    }
    return state.getOwnedRegions().get(0);
  }

}
