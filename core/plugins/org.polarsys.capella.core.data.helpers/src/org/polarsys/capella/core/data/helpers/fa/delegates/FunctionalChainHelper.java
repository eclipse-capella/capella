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
package org.polarsys.capella.core.data.helpers.fa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainRealization;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolverElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class FunctionalChainHelper {
  private static FunctionalChainHelper instance;

  private FunctionalChainHelper() {
    // do nothing
  }

  public static FunctionalChainHelper getInstance() {
    if (instance == null) {
      instance = new FunctionalChainHelper();
    }
    return instance;
  }

  public Object doSwitch(FunctionalChain element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS)) {
      ret = getInvolvedFunctionalChainInvolvements(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS)) {
      ret = getInvolvedElements(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES)) {
      ret = getInvolvedFunctionalExchanges(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS)) {
      ret = getInvolvedFunctions(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS)) {
      ret = getEnactedFunctions(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS)) {
      ret = getEnactedFunctionalBlocks(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS)) {
      ret = getFirstFunctionalChainInvolvements(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES)) {
      ret = getInvolvingCapabilities(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS)) {
      ret = getInvolvingCapabilityRealizations(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS)) {
      ret = getRealizedFunctionalChains(element_p);
    } else if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS)) {
      ret = getRealizingFunctionalChains(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = InvolverElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = InvolvedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<FunctionalChainInvolvement> getInvolvedFunctionalChainInvolvements(FunctionalChain element_p) {
    return new ArrayList<FunctionalChainInvolvement>(element_p.getOwnedFunctionalChainInvolvements());
  }

  protected List<InvolvedElement> getInvolvedElements(FunctionalChain element_p) {
    List<InvolvedElement> ret = new ArrayList<InvolvedElement>();
    for (FunctionalChainInvolvement involvement : getInvolvedFunctionalChainInvolvements(element_p)) {
      InvolvedElement elt = involvement.getInvolved();
      if (null != elt) {
        ret.add(elt);
      }
    }
    return ret;
  }

  protected List<FunctionalExchange> getInvolvedFunctionalExchanges(FunctionalChain element_p) {
    List<FunctionalExchange> ret = new ArrayList<FunctionalExchange>();
    for (InvolvedElement involvedElement : getInvolvedElements(element_p)) {
      if (involvedElement instanceof FunctionalExchange) {
        ret.add((FunctionalExchange) involvedElement);
      }
    }
    return ret;
  }

  protected List<AbstractFunction> getInvolvedFunctions(FunctionalChain element_p) {
    List<AbstractFunction> ret = new ArrayList<AbstractFunction>();
    for (InvolvedElement involvedElement : getInvolvedElements(element_p)) {
      if (involvedElement instanceof AbstractFunction) {
        ret.add((AbstractFunction) involvedElement);
      }
    }

    return ret;
  }

  protected List<AbstractFunction> getEnactedFunctions(FunctionalChain element_p) {
    return getInvolvedFunctions(element_p);
  }

  protected List<AbstractFunctionalBlock> getEnactedFunctionalBlocks(FunctionalChain element_p) {
    List<AbstractFunctionalBlock> ret = new ArrayList<AbstractFunctionalBlock>();
    for (AbstractFunction func : getEnactedFunctions(element_p)) {
      ret.addAll(func.getAllocationBlocks());
    }
    return ret;
  }

  protected List<FunctionalChainInvolvement> getFirstFunctionalChainInvolvements(FunctionalChain element_p) {
    List<FunctionalChainInvolvement> ret = new ArrayList<FunctionalChainInvolvement>();
    for (FunctionalChainInvolvement inv : element_p.getOwnedFunctionalChainInvolvements()) {
      if ((inv.getInvolved() != null) && inv.getPreviousFunctionalChainInvolvements().isEmpty()) {
        ret.add(inv);
      }
    }
    return ret;
  }

  protected List<Capability> getInvolvingCapabilities(FunctionalChain element_p) {
    List<Capability> ret = new ArrayList<Capability>();
    for (Involvement inv : element_p.getInvolvingInvolvements()) {
      if (inv instanceof FunctionalChainAbstractCapabilityInvolvement) {
        AbstractCapability cap = ((FunctionalChainAbstractCapabilityInvolvement) inv).getCapability();
        if (cap instanceof Capability) {
          ret.add((Capability) cap);
        }
      }
    }
    return ret;
  }

  protected List<CapabilityRealization> getInvolvingCapabilityRealizations(FunctionalChain element_p) {
    List<CapabilityRealization> ret = new ArrayList<CapabilityRealization>();
    for (Involvement inv : element_p.getInvolvingInvolvements()) {
      if (inv instanceof FunctionalChainAbstractCapabilityInvolvement) {
        AbstractCapability cap = ((FunctionalChainAbstractCapabilityInvolvement) inv).getCapability();
        if (cap instanceof CapabilityRealization) {
          ret.add((CapabilityRealization) cap);
        }
      }
    }
    return ret;
  }

  protected List<FunctionalChain> getRealizedFunctionalChains(FunctionalChain element_p) {
    List <FunctionalChain> ret = new ArrayList<FunctionalChain>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof FunctionalChainRealization){
        ret.add((FunctionalChain) trace.getTargetElement());
      }
    }
    return ret;
  }

  protected List<FunctionalChain> getRealizingFunctionalChains(FunctionalChain element_p) {
    List <FunctionalChain> ret = new ArrayList<FunctionalChain>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof FunctionalChainRealization){
        ret.add((FunctionalChain) trace.getSourceElement());
      }
    }
    return ret;
  }
}
