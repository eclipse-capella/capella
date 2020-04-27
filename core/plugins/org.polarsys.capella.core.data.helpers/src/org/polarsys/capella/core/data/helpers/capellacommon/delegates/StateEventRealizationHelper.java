/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

  public Object doSwitch(StateEventRealization element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellacommonPackage.Literals.STATE_EVENT_REALIZATION__REALIZED_EVENT)) {
      ret = getRealizedEvent(element);
    } else if (feature.equals(CapellacommonPackage.Literals.STATE_EVENT_REALIZATION__REALIZING_EVENT)) {
      ret = getRealizingEvent(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected StateEvent getRealizedEvent(StateEventRealization element) {
    if (element.getTargetElement() instanceof StateEvent) {
      return (StateEvent) element.getTargetElement();
    }
    return null;
  }

  protected StateEvent getRealizingEvent(StateEventRealization element) {
    if (element.getSourceElement() instanceof StateEvent) {
      return (StateEvent) element.getSourceElement();
    }
    return null;
  }
}
