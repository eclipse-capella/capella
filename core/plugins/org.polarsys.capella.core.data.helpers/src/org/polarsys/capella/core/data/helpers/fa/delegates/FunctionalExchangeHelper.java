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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractEventOperationHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractTypeHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;

public class FunctionalExchangeHelper {
  private static FunctionalExchangeHelper instance;

  private FunctionalExchangeHelper() {
    // do nothing
  }

  public static FunctionalExchangeHelper getInstance() {
    if (instance == null) {
      instance = new FunctionalExchangeHelper();
    }
    return instance;
  }

  public Object doSwitch(FunctionalExchange element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE__INVOLVING_FUNCTIONAL_CHAINS)) {
      ret = getInvolvingFunctionalChains(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE__ALLOCATING_COMPONENT_EXCHANGES)) {
      ret = getAllocatingComponentExchanges(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE__INCOMING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_REALIZATIONS)) {
      ret = getIncomingComponentExchangeFunctionalExchangeRealizations(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE__INCOMING_FUNCTIONAL_EXCHANGE_REALIZATIONS)) {
      ret = getIncomingFunctionalExchangeRealizations(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE__OUTGOING_FUNCTIONAL_EXCHANGE_REALIZATIONS)) {
      ret = getOutgoingFunctionalExchangeRealizations(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE__SOURCE_FUNCTION_OUTPUT_PORT)) {
      ret = getSourceFunctionOutputPort(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE__TARGET_FUNCTION_INPUT_PORT)) {
      ret = getTargetFunctionInputPort(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE__REALIZED_FUNCTIONAL_EXCHANGES)) {
      ret = getRealizedFunctionalExchanges(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE__REALIZING_FUNCTIONAL_EXCHANGES)) {
      ret = getRealizingFunctionalExchanges(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES)) {
      ret = getCategories(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractTypeHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InvolvedElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = RelationshipHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractEventOperationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<EObject> getCategories(FunctionalExchange element) {
    return EObjectExt.getReferencers(element, FaPackage.Literals.EXCHANGE_CATEGORY__EXCHANGES);
  }

  protected List<FunctionalChain> getInvolvingFunctionalChains(FunctionalExchange element) {
    List<FunctionalChain> ret = new ArrayList<>();
    for (Involvement involvement : element.getInvolvingInvolvements()) {
      if (involvement instanceof FunctionalChainInvolvement) {
        InvolverElement fc = involvement.getInvolver();
        if (fc instanceof FunctionalChain) {
          ret.add((FunctionalChain) fc);
        }
      }
    }
    return ret;
  }

  protected List<ComponentExchange> getAllocatingComponentExchanges(FunctionalExchange element) {
    List<ComponentExchange> ret = new ArrayList<>();
    for (ComponentExchangeFunctionalExchangeAllocation item : element.getIncomingComponentExchangeFunctionalExchangeRealizations()) {
      ComponentExchange allocatingComponentExchange = item.getAllocatingComponentExchange();
      if (allocatingComponentExchange != null) {
        ret.add(allocatingComponentExchange);
      }
    }
    return ret;
  }

  protected List<ComponentExchangeFunctionalExchangeAllocation> getIncomingComponentExchangeFunctionalExchangeRealizations(FunctionalExchange element) {
    List<ComponentExchangeFunctionalExchangeAllocation> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentExchangeFunctionalExchangeAllocation) {
        ret.add((ComponentExchangeFunctionalExchangeAllocation) trace);
      }
    }
    return ret;
  }

  protected List<FunctionalExchangeRealization> getIncomingFunctionalExchangeRealizations(FunctionalExchange element) {
    List<FunctionalExchangeRealization> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof FunctionalExchangeRealization) {
        ret.add((FunctionalExchangeRealization) trace);
      }
    }
    return ret;
  }

  protected List<FunctionalExchangeRealization> getOutgoingFunctionalExchangeRealizations(FunctionalExchange element) {
    List<FunctionalExchangeRealization> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof FunctionalExchangeRealization) {
        ret.add((FunctionalExchangeRealization) trace);
      }
    }
    return ret;
  }

  protected FunctionOutputPort getSourceFunctionOutputPort(FunctionalExchange element) {
    ActivityNode activityNode = element.getSource();
    if (activityNode instanceof FunctionOutputPort) {
      return (FunctionOutputPort) activityNode;
    }
    return null;
  }

  protected FunctionInputPort getTargetFunctionInputPort(FunctionalExchange element) {
    ActivityNode activityNode = element.getTarget();
    if (activityNode instanceof FunctionInputPort) {
      return (FunctionInputPort) activityNode;
    }
    return null;
  }

  protected List<FunctionalExchange> getRealizedFunctionalExchanges(FunctionalExchange element) {
    List<FunctionalExchange> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof FunctionalExchangeRealization) {
        TraceableElement tgt = trace.getTargetElement();
        if (tgt instanceof FunctionalExchange) {
          ret.add((FunctionalExchange) tgt);
        }
      }
    }
    return ret;
  }

  protected List<FunctionalExchange> getRealizingFunctionalExchanges(FunctionalExchange element) {
    List<FunctionalExchange> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof FunctionalExchangeRealization) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof FunctionalExchange) {
          ret.add((FunctionalExchange) src);
        }
      }
    }
    return ret;
  }
}
