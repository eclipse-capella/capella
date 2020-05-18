/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;

public class AbstractStateHelper {
  private static AbstractStateHelper instance;

  public static AbstractStateHelper getInstance() {
    if (instance == null) {
      instance = new AbstractStateHelper();
    }
    return instance;
  }

  public Object doSwitch(AbstractState element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellacommonPackage.Literals.ABSTRACT_STATE__REALIZED_ABSTRACT_STATES)) {
      ret = getRealizedAbstractStates(element);
    } else if (feature.equals(CapellacommonPackage.Literals.ABSTRACT_STATE__REALIZING_ABSTRACT_STATES)) {
      ret = getRealizingAbstractStates(element);
    } else if (feature.equals(CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS)) {
      ret = getInvolverRegions(element);
    } else if (feature.equals(CapellacommonPackage.Literals.ABSTRACT_STATE__INCOMING)) {
      ret = getIncoming(element);
    } else if (feature.equals(CapellacommonPackage.Literals.ABSTRACT_STATE__OUTGOING)) {
      ret = getOutgoing(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<StateTransition> getOutgoing(AbstractState element) {
    return EObjectExt.getReferencers(element, CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE);
  }

  protected List<StateTransition> getIncoming(AbstractState element) {
    return EObjectExt.getReferencers(element, CapellacommonPackage.Literals.STATE_TRANSITION__TARGET);
  }

  protected List<AbstractState> getRealizedAbstractStates(AbstractState element) {
    List<AbstractState> result = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof AbstractStateRealization) {
        AbstractState state = ((AbstractStateRealization) trace).getRealizedAbstractState();
        if (null != state) {
          result.add(state);
        }
      }
    }
    return result;
  }

  protected List<AbstractState> getRealizingAbstractStates(AbstractState element) {
    List<AbstractState> result = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof AbstractStateRealization) {
        AbstractState state = ((AbstractStateRealization) trace).getRealizingAbstractState();
        if (null != state) {
          result.add(state);
        }
      }
    }
    return result;
  }

  protected List<Region> getInvolverRegions(AbstractState element) {
    return EObjectExt.getReferencers(element, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
  }
}
