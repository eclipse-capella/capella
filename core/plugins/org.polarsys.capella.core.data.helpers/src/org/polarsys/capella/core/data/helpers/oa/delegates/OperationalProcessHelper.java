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

package org.polarsys.capella.core.data.helpers.oa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalChainHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalProcess;

public class OperationalProcessHelper {
  private static OperationalProcessHelper instance;

  private OperationalProcessHelper() {
    // do nothing
  }

  public static OperationalProcessHelper getInstance() {
    if (instance == null)
      instance = new OperationalProcessHelper();
    return instance;
  }

  public Object doSwitch(OperationalProcess element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(OaPackage.Literals.OPERATIONAL_PROCESS__INVOLVING_OPERATIONAL_CAPABILITIES)) {
      ret = getInvolvingOperationalCapabilities(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = FunctionalChainHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<OperationalCapability> getInvolvingOperationalCapabilities(OperationalProcess element) {
    List<OperationalCapability> ret = new ArrayList<>();
    for (Involvement inv : element.getInvolvingInvolvements()) {
      if (inv instanceof FunctionalChainAbstractCapabilityInvolvement) {
        AbstractCapability cap = ((FunctionalChainAbstractCapabilityInvolvement) inv).getCapability();
        if (cap instanceof OperationalCapability) {
          ret.add((OperationalCapability) cap);
        }
      }
    }
    return ret;
  }
}
