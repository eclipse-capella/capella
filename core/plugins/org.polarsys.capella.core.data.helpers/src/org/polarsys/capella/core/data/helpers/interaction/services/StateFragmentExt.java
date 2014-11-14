/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
   * @param stateFragment_p
   * @return
   */
  public static InstanceRole getCoveredInstanceRole(StateFragment stateFragment_p) {
    if (stateFragment_p != null) {
      InteractionFragment ifstart = stateFragment_p.getStart();
      if (ifstart != null) {
        for (InstanceRole instanceRole : ifstart.getCoveredInstanceRoles()) {
          return instanceRole;
        }
      }
      InteractionFragment iffinish = stateFragment_p.getFinish();
      if (iffinish != null) {
        for (InstanceRole instanceRole : iffinish.getCoveredInstanceRoles()) {
          return instanceRole;
        }
      }
    }
    return null;
  }
}
