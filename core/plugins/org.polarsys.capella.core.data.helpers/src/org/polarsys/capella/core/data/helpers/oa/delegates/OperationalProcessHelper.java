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

  public Object doSwitch(OperationalProcess element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(OaPackage.Literals.OPERATIONAL_PROCESS__INVOLVING_OPERATIONAL_CAPABILITIES)) {
      ret = getInvolvingOperationalCapabilities(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = FunctionalChainHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<OperationalCapability> getInvolvingOperationalCapabilities(OperationalProcess element_p) {
    List<OperationalCapability> ret = new ArrayList<OperationalCapability>();
    for (Involvement inv : element_p.getInvolvingInvolvements()) {
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
