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

  public Object doSwitch(FunctionalChain element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS)) {
      ret = getInvolvedFunctionalChainInvolvements(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS)) {
      ret = getInvolvedElements(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES)) {
      ret = getInvolvedFunctionalExchanges(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS)) {
      ret = getInvolvedFunctions(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS)) {
      ret = getEnactedFunctions(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS)) {
      ret = getEnactedFunctionalBlocks(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS)) {
      ret = getFirstFunctionalChainInvolvements(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES)) {
      ret = getInvolvingCapabilities(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS)) {
      ret = getInvolvingCapabilityRealizations(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS)) {
      ret = getRealizedFunctionalChains(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS)) {
      ret = getRealizingFunctionalChains(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InvolverElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InvolvedElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<FunctionalChainInvolvement> getInvolvedFunctionalChainInvolvements(FunctionalChain element) {
    return new ArrayList<>(element.getOwnedFunctionalChainInvolvements());
  }

  protected List<InvolvedElement> getInvolvedElements(FunctionalChain element) {
    List<InvolvedElement> ret = new ArrayList<>();
    for (FunctionalChainInvolvement involvement : getInvolvedFunctionalChainInvolvements(element)) {
      InvolvedElement elt = involvement.getInvolved();
      if (null != elt) {
        ret.add(elt);
      }
    }
    return ret;
  }

  protected List<FunctionalExchange> getInvolvedFunctionalExchanges(FunctionalChain element) {
    List<FunctionalExchange> ret = new ArrayList<>();
    for (InvolvedElement involvedElement : getInvolvedElements(element)) {
      if (involvedElement instanceof FunctionalExchange) {
        ret.add((FunctionalExchange) involvedElement);
      }
    }
    return ret;
  }

  protected List<AbstractFunction> getInvolvedFunctions(FunctionalChain element) {
    List<AbstractFunction> ret = new ArrayList<>();
    for (InvolvedElement involvedElement : getInvolvedElements(element)) {
      if (involvedElement instanceof AbstractFunction) {
        ret.add((AbstractFunction) involvedElement);
      }
    }

    return ret;
  }

  protected List<AbstractFunction> getEnactedFunctions(FunctionalChain element) {
    return getInvolvedFunctions(element);
  }

  protected List<AbstractFunctionalBlock> getEnactedFunctionalBlocks(FunctionalChain element) {
    List<AbstractFunctionalBlock> ret = new ArrayList<>();
    for (AbstractFunction func : getEnactedFunctions(element)) {
      ret.addAll(func.getAllocationBlocks());
    }
    return ret;
  }

  protected List<FunctionalChainInvolvement> getFirstFunctionalChainInvolvements(FunctionalChain element) {
    List<FunctionalChainInvolvement> ret = new ArrayList<>();
    for (FunctionalChainInvolvement inv : element.getOwnedFunctionalChainInvolvements()) {
      if ((inv.getInvolved() != null) && inv.getPreviousFunctionalChainInvolvements().isEmpty()) {
        ret.add(inv);
      }
    }
    return ret;
  }

  protected List<Capability> getInvolvingCapabilities(FunctionalChain element) {
    List<Capability> ret = new ArrayList<>();
    for (Involvement inv : element.getInvolvingInvolvements()) {
      if (inv instanceof FunctionalChainAbstractCapabilityInvolvement) {
        AbstractCapability cap = ((FunctionalChainAbstractCapabilityInvolvement) inv).getCapability();
        if (cap instanceof Capability) {
          ret.add((Capability) cap);
        }
      }
    }
    return ret;
  }

  protected List<CapabilityRealization> getInvolvingCapabilityRealizations(FunctionalChain element) {
    List<CapabilityRealization> ret = new ArrayList<>();
    for (Involvement inv : element.getInvolvingInvolvements()) {
      if (inv instanceof FunctionalChainAbstractCapabilityInvolvement) {
        AbstractCapability cap = ((FunctionalChainAbstractCapabilityInvolvement) inv).getCapability();
        if (cap instanceof CapabilityRealization) {
          ret.add((CapabilityRealization) cap);
        }
      }
    }
    return ret;
  }

  protected List<FunctionalChain> getRealizedFunctionalChains(FunctionalChain element) {
    List <FunctionalChain> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof FunctionalChainRealization){
        FunctionalChain targetElement = (FunctionalChain) trace.getTargetElement();
        if(targetElement != null) {
          ret.add(targetElement);          
        }
      }
    }
    return ret;
  }

  protected List<FunctionalChain> getRealizingFunctionalChains(FunctionalChain element) {
    List <FunctionalChain> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof FunctionalChainRealization){
        FunctionalChain sourceElement = (FunctionalChain) trace.getSourceElement();
        if(sourceElement != null) {          
          ret.add(sourceElement);
        }
      }
    }
    return ret;
  }
}
