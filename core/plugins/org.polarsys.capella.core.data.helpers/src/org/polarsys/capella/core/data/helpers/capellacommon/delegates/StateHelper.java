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

package org.polarsys.capella.core.data.helpers.capellacommon.delegates;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

public class StateHelper {
  private static StateHelper instance;

  private StateHelper() {
    //
  }

  public static StateHelper getInstance() {
    if (instance == null) {
      instance = new StateHelper();
    }
    return instance;
  }

  public Object doSwitch(State element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_FUNCTIONS)) {
      ret = getAvailableAbstractFunctions(element);
    } else if (feature.equals(CapellacommonPackage.Literals.STATE__AVAILABLE_FUNCTIONAL_CHAINS)) {
      ret = getAvailableFunctionalChains(element);
    } else if (feature.equals(CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_CAPABILITIES)) {
      ret = getAvailableAbstractCapabilities(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractStateHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<AbstractFunction> getAvailableAbstractFunctions(State element) {
    return EObjectExt.getReferencers(element, FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
  }

  protected List<FunctionalChain> getAvailableFunctionalChains(State element) {
    return EObjectExt.getReferencers(element, FaPackage.Literals.FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES);
  }

  protected List<AbstractCapability> getAvailableAbstractCapabilities(State element) {
    return EObjectExt.getReferencers(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);
  }
}
