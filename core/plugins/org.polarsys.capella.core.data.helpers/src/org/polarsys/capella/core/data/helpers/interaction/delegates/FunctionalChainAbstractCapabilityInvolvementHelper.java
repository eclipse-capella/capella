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

  public Object doSwitch(FunctionalChainAbstractCapabilityInvolvement element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(InteractionPackage.Literals.FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTIONAL_CHAIN)) {
      ret = getFunctionalChain(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY)) {
      ret = getAbstractCapability(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected FunctionalChain getFunctionalChain(FunctionalChainAbstractCapabilityInvolvement element_p) {
    InvolvedElement elt = element_p.getInvolved();
    if (elt instanceof FunctionalChain)
      return (FunctionalChain) elt;
    return null;
  }

  protected AbstractCapability getAbstractCapability(FunctionalChainAbstractCapabilityInvolvement element_p) {
    InvolverElement elt = element_p.getInvolver();
    if (elt instanceof AbstractCapability)
      return (AbstractCapability) elt;
    return null;
  }
}
