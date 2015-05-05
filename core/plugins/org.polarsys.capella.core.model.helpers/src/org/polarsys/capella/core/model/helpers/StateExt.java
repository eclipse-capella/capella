/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
  public static Region getPrimaryRegion(State state_p) {
    return getPrimaryRegion(state_p, true);
  }

  /**
   * Returns the first region of the given state and create one if required
   */
  public static Region getPrimaryRegion(State state_p, boolean create_p) {
    if (state_p.getOwnedRegions().isEmpty() && create_p) {
      Region region = CapellacommonFactory.eINSTANCE.createRegion(NamingConstants.Region_DefaultRegion);
      state_p.getOwnedRegions().add(region);
      CapellaElementExt.creationService(region);
    }
    if (state_p.getOwnedRegions().isEmpty()) {
      return null;
    }
    return state_p.getOwnedRegions().get(0);
  }

}
