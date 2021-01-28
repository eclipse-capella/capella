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

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

public abstract class DWF_SM_06Resolver extends AbstractDeleteCommandResolver {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getElementToDelete(Object obj) {
    if (obj != null) {
      return getMixedStates(obj);
    }
    return null;
  }

  @Override
  public boolean enabled(Collection<IMarker> markers) {
    for (IMarker marker : markers) {
      EObject modelElement = getModelElements(marker).get(0);
      if ((null != modelElement) && (modelElement instanceof AbstractState)) {
        return getMixedStates(modelElement).size() > 0;
      }
    }
    return false;
  }

  protected abstract List<AbstractState> getMixedStates(Object obj);

  protected boolean areMixedStateMode(Object object, AbstractState state) {
    if ((state instanceof Pseudostate) || (state instanceof FinalState)) {
      return false;
    }

    return !object.getClass().equals(state.getClass());
  }

}
