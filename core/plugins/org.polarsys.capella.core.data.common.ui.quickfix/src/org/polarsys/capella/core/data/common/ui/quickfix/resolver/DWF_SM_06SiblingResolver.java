/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.impl.ModeImpl;
import org.polarsys.capella.core.data.capellacommon.impl.StateImpl;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

public class DWF_SM_06SiblingResolver extends AbstractDeleteCommandResolver {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getElementToDelete(Object obj) {
    if (obj != null) {
      if (obj instanceof Mode) {
        List<State> lstStates = new ArrayList<State>();
        for (AbstractState state : ((Mode) obj).getInvolverRegions().get(0).getOwnedStates()) {
          if (state.getClass() == StateImpl.class)
            lstStates.add((State) state);
        }
        return lstStates;
      }

      List<Mode> lstModes = new ArrayList<Mode>();
      for (AbstractState mode : ((State) obj).getInvolverRegions().get(0).getOwnedStates()) {
        if (mode.getClass() == ModeImpl.class)
          lstModes.add((Mode) mode);
      }
      return lstModes;
    }

    return null;
  }
}
