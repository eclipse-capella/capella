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
package org.polarsys.capella.core.data.helpers.capellacommon.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.StateEventRealization;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;

public class StateEventRealizationHelper {
  private static StateEventRealizationHelper instance;

  private StateEventRealizationHelper() {
    //
  }

  public static StateEventRealizationHelper getInstance() {
    if (instance == null) {
      instance = new StateEventRealizationHelper();
    }
    return instance;
  }

  public Object doSwitch(StateEventRealization element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CapellacommonPackage.Literals.STATE_EVENT_REALIZATION__REALIZED_EVENT)) {
      ret = getRealizedStateEvent(element_p);
    } else if (feature_p.equals(CapellacommonPackage.Literals.STATE_EVENT_REALIZATION__REALIZING_EVENT)) {
      ret = getRealizingStateEvent(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected StateEvent getRealizedStateEvent(StateEventRealization element_p) {
    if (element_p.getTargetElement() instanceof StateEvent) {
      return (StateEvent) element_p.getTargetElement();
    }
    return null;
  }

  protected StateEvent getRealizingStateEvent(StateEventRealization element_p) {
    if (element_p.getSourceElement() instanceof StateEvent) {
      return (StateEvent) element_p.getSourceElement();
    }
    return null;
  }
}
