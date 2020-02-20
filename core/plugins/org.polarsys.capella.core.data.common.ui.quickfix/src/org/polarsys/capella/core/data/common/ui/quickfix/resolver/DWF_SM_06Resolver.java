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
package org.polarsys.capella.core.data.common.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.impl.ModeImpl;
import org.polarsys.capella.core.data.capellacommon.impl.StateImpl;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

public class DWF_SM_06Resolver extends AbstractDeleteCommandResolver {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getElementToDelete(Object obj) {
    if (obj != null) {
      return getChildrenMixedStates(obj);
    }
    return null;
  }

  @Override
  public boolean enabled(Collection<IMarker> markers) {
    for (IMarker marker : markers) {
      EObject modelElement = getModelElements(marker).get(0);
      if ((null != modelElement) && (modelElement instanceof AbstractState)) {
        return getChildrenMixedStates(modelElement).size() > 0;
      }
    }
    return false;
  }

  private List<AbstractState> getChildrenMixedStates(Object obj) {
    if (obj instanceof Mode) {
      List<AbstractState> lstStates = new ArrayList<AbstractState>();
      for (AbstractState state : ((Mode) obj).getOwnedRegions().get(0).getOwnedStates()) {
        if (state.getClass() == StateImpl.class)
          lstStates.add(state);
      }
      return lstStates;
    }

    List<AbstractState> lstModes = new ArrayList<AbstractState>();
    for (AbstractState mode : ((State) obj).getOwnedRegions().get(0).getOwnedStates()) {
      if (mode.getClass() == ModeImpl.class)
        lstModes.add(mode);
    }
    return lstModes;
  }
}
