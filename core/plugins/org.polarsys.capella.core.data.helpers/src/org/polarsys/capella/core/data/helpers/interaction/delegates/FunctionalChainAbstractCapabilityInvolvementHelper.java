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

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;

public class FunctionalChainAbstractCapabilityInvolvementHelper {
  private static FunctionalChainAbstractCapabilityInvolvementHelper instance;

  private FunctionalChainAbstractCapabilityInvolvementHelper() {
    // do nothing
  }

  public static FunctionalChainAbstractCapabilityInvolvementHelper getInstance() {
    if (instance == null)
      instance = new FunctionalChainAbstractCapabilityInvolvementHelper();
    return instance;
  }

  public Object doSwitch(FunctionalChainAbstractCapabilityInvolvement element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(InteractionPackage.Literals.FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTIONAL_CHAIN)) {
      ret = getFunctionalChain(element);
    } else if (feature.equals(InteractionPackage.Literals.FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY)) {
      ret = getCapability(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected FunctionalChain getFunctionalChain(FunctionalChainAbstractCapabilityInvolvement element) {
    InvolvedElement elt = element.getInvolved();
    if (elt instanceof FunctionalChain)
      return (FunctionalChain) elt;
    return null;
  }

  protected AbstractCapability getCapability(FunctionalChainAbstractCapabilityInvolvement element) {
    InvolverElement elt = element.getInvolver();
    if (elt instanceof AbstractCapability)
      return (AbstractCapability) elt;
    return null;
  }
}
