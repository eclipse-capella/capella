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

package org.polarsys.capella.core.data.helpers.interaction.services;

import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.StateFragment;

/**
 */
public class StateFragmentExt {
  /**
   * Get 1 InstanceRole from the given StateFragment.
   * @param stateFragment
   * @return
   */
  public static InstanceRole getCoveredInstanceRole(StateFragment stateFragment) {
    if (stateFragment != null) {
      InteractionFragment ifstart = stateFragment.getStart();
      if (ifstart != null) {
        for (InstanceRole instanceRole : ifstart.getCoveredInstanceRoles()) {
          return instanceRole;
        }
      }
      InteractionFragment iffinish = stateFragment.getFinish();
      if (iffinish != null) {
        for (InstanceRole instanceRole : iffinish.getCoveredInstanceRoles()) {
          return instanceRole;
        }
      }
    }
    return null;
  }
}
