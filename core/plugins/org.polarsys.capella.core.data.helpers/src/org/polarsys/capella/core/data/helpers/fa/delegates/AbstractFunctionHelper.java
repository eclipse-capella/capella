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

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionSpecification;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractInstanceHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamespaceHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.helpers.activity.delegates.ActivityNodeHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractTypeHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class AbstractFunctionHelper {
  private static AbstractFunctionHelper instance;

  private AbstractFunctionHelper() {
    // do nothing
  }

  public static AbstractFunctionHelper getInstance() {
    if (instance == null)
      instance = new AbstractFunctionHelper();
    return instance;
  }

  public Object doSwitch(AbstractFunction element, EStructuralFeature feature) {

    Object ret = null;

    if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTION__ALLOCATION_BLOCKS)) {
      ret = getAllocationBlocks(element);
    } else if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS)) {
      ret = getComponentFunctionalAllocations(element);
    } else if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTION__OUT_FUNCTION_REALIZATIONS)) {
      ret = getOutFunctionRealizations(element);
    } else if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTION__IN_FUNCTION_REALIZATIONS)) {
      ret = getInFunctionRealizations(element);
    } else if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTION__INVOLVING_CAPABILITIES)) {
      ret = getInvolvingCapabilities(element);
    } else if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS)) {
      ret = getInvolvingCapabilityRealizations(element);
    } else if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS)) {
      ret = getInvolvingFunctionalChains(element);
    } else if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION)) {
      ret = getLinkedFunctionSpecification(element);
    } else if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_STATE_MACHINE)) {
      ret = getLinkedStateMachine(element);
    } else if (feature.equals(FaPackage.Literals.ABSTRACT_FUNCTION__SUB_FUNCTIONS)) {
      ret = getSubFunctions(element);
    } else if (feature.equals(ActivityPackage.Literals.ACTIVITY_NODE__INCOMING)) {
      ret = getIncoming(element, feature);
    } else if (feature.equals(ActivityPackage.Literals.ACTIVITY_NODE__OUTGOING)) {
      ret = getOutgoing(element, feature);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvedElementHelper.getInstance().doSwitch(element, feature);
    }
    if (ret == null) {
      ret = AbstractInstanceHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = ActivityNodeHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractTypeHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = NamespaceHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<FunctionRealization> getInFunctionRealizations(AbstractFunction element) {
    List<FunctionRealization> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof FunctionRealization) {
        ret.add((FunctionRealization) trace);
      }
    }
    return ret;
  }

  protected List<FunctionRealization> getOutFunctionRealizations(AbstractFunction element) {
    List<FunctionRealization> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof FunctionRealization) {
        ret.add((FunctionRealization) trace);
      }
    }
    return ret;
  }

  protected List<ComponentFunctionalAllocation> getComponentFunctionalAllocations(AbstractFunction element) {
    List<ComponentFunctionalAllocation> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        ret.add((ComponentFunctionalAllocation) trace);
      }
    }
    return ret;
  }

  protected List<AbstractFunctionalBlock> getAllocationBlocks(AbstractFunction element) {
    List<AbstractFunctionalBlock> ret = new ArrayList<>();
    for (ComponentFunctionalAllocation alloc : element.getComponentFunctionalAllocations()) {
      AbstractFunctionalBlock block = alloc.getBlock();
      if (null != block)
        ret.add(block);
    }
    return ret;
  }

  protected List<Capability> getInvolvingCapabilities(AbstractFunction element) {
    List<Capability> ret = new ArrayList<>();
    for (Involvement inv : element.getInvolvingInvolvements()) {
      if (inv instanceof AbstractFunctionAbstractCapabilityInvolvement) {
        AbstractCapability cap = ((AbstractFunctionAbstractCapabilityInvolvement) inv).getCapability();
        if (cap instanceof Capability) {
          ret.add((Capability) cap);
        }
      }
    }
    return ret;
  }

  protected List<CapabilityRealization> getInvolvingCapabilityRealizations(AbstractFunction element) {
    List<CapabilityRealization> ret = new ArrayList<>();
    for (Involvement inv : element.getInvolvingInvolvements()) {
      if (inv instanceof AbstractFunctionAbstractCapabilityInvolvement) {
        AbstractCapability cap = ((AbstractFunctionAbstractCapabilityInvolvement) inv).getCapability();
        if (cap instanceof CapabilityRealization) {
          ret.add((CapabilityRealization) cap);
        }
      }
    }
    return ret;
  }

  protected List<AbstractFunction> getSubFunctions(AbstractFunction element) {
    return (List<AbstractFunction>) getCache(FunctionExt::getFirstLevelAbstractFunctions, element);
  }

  protected List<FunctionalChain> getInvolvingFunctionalChains(AbstractFunction element) {
    List<FunctionalChain> result = new ArrayList<>();
    for (Involvement involvement : element.getInvolvingInvolvements()) {
      if(involvement instanceof FunctionalChainInvolvement){
        InvolverElement fc = involvement.getInvolver();
        if (fc instanceof FunctionalChain && !result.contains(fc)){
          result.add((FunctionalChain) fc);
        }
      }
    }
    return result;
  }

  protected FunctionSpecification getLinkedFunctionSpecification(AbstractFunction element){
    AbstractBehavior behavior = element.getBehavior();
    if(behavior instanceof FunctionSpecification){
      return (FunctionSpecification) behavior;
    }
    return null;
  }

  protected StateMachine getLinkedStateMachine(AbstractFunction element){
    AbstractBehavior behavior = element.getBehavior();
    if(behavior instanceof StateMachine){
      return (StateMachine) behavior;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  protected List<ActivityEdge> getIncoming(AbstractFunction element, EStructuralFeature feature) {
    List<ActivityEdge> res = new ArrayList<>();

    if (element.getInputs().isEmpty()) {
      res.addAll((Collection<? extends ActivityEdge>) ActivityNodeHelper.getInstance().doSwitch(element, feature));
    } else {
      for (InputPin inputPin : element.getInputs()) {
        res.addAll((Collection<? extends ActivityEdge>) ActivityNodeHelper.getInstance().doSwitch(inputPin, feature));
      }
    }

    return res;
  }

  @SuppressWarnings("unchecked")
  protected List<ActivityEdge> getOutgoing(AbstractFunction element, EStructuralFeature feature) {
    List<ActivityEdge> res = new ArrayList<>();

    if (element.getOutputs().isEmpty()) {
      res.addAll((Collection<? extends ActivityEdge>) ActivityNodeHelper.getInstance().doSwitch(element, feature));
    } else {
      for (OutputPin outputPin : element.getOutputs()) {
        res.addAll((Collection<? extends ActivityEdge>) ActivityNodeHelper.getInstance().doSwitch(outputPin, feature));
      }
    }

    return res;
  }
}
