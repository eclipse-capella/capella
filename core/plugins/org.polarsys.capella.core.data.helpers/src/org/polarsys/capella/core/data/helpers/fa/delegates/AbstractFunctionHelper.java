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

  public Object doSwitch(AbstractFunction element_p, EStructuralFeature feature_p) {

    Object ret = null;

    if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTION__ALLOCATION_BLOCKS)) {
      ret = getAllocationBlocks(element_p);
    } else if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS)) {
      ret = getComponentFunctionalAllocations(element_p);
    } else if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTION__OUT_FUNCTION_REALIZATIONS)) {
      ret = getOutFunctionRealizations(element_p);
    } else if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTION__IN_FUNCTION_REALIZATIONS)) {
      ret = getInFunctionRealizations(element_p);
    } else if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTION__INVOLVING_CAPABILITIES)) {
      ret = getInvolvingCapabilities(element_p);
    } else if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS)) {
      ret = getInvolvingCapabilityRealizations(element_p);
    } else if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS)) {
      ret = getInvolvingFunctionalChains(element_p);
    } else if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION)) {
      ret = getLinkedFunctionSpecification(element_p);
    } else if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_STATE_MACHINE)) {
      ret = getLinkedStateMachine(element_p);
    } else if (feature_p.equals(FaPackage.Literals.ABSTRACT_FUNCTION__SUB_FUNCTIONS)) {
      ret = getSubFunctions(element_p);
    } else if (feature_p.equals(ActivityPackage.Literals.ACTIVITY_NODE__INCOMING)) {
      ret = getIncomingEdges(element_p, feature_p);
    } else if (feature_p.equals(ActivityPackage.Literals.ACTIVITY_NODE__OUTGOING)) {
      ret = getOutgoingEdges(element_p, feature_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (ret == null) {
      ret = AbstractInstanceHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = ActivityNodeHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = AbstractTypeHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = NamespaceHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<FunctionRealization> getInFunctionRealizations(AbstractFunction element_p) {
    List<FunctionRealization> ret = new ArrayList<FunctionRealization>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof FunctionRealization) {
        ret.add((FunctionRealization) trace);
      }
    }
    return ret;
  }

  protected List<FunctionRealization> getOutFunctionRealizations(AbstractFunction element_p) {
    List<FunctionRealization> ret = new ArrayList<FunctionRealization>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof FunctionRealization) {
        ret.add((FunctionRealization) trace);
      }
    }
    return ret;
  }

  protected List<ComponentFunctionalAllocation> getComponentFunctionalAllocations(AbstractFunction element_p) {
    List<ComponentFunctionalAllocation> ret = new ArrayList<ComponentFunctionalAllocation>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        ret.add((ComponentFunctionalAllocation) trace);
      }
    }
    return ret;
  }

  protected List<AbstractFunctionalBlock> getAllocationBlocks(AbstractFunction element_p) {
    List<AbstractFunctionalBlock> ret = new ArrayList<AbstractFunctionalBlock>();
    for (ComponentFunctionalAllocation alloc : element_p.getComponentFunctionalAllocations()) {
      AbstractFunctionalBlock block = alloc.getBlock();
      if (null != block)
        ret.add(block);
    }
    return ret;
  }

  protected List<Capability> getInvolvingCapabilities(AbstractFunction element_p) {
    List<Capability> ret = new ArrayList<Capability>();
    for (Involvement inv : element_p.getInvolvingInvolvements()) {
      if (inv instanceof AbstractFunctionAbstractCapabilityInvolvement) {
        AbstractCapability cap = ((AbstractFunctionAbstractCapabilityInvolvement) inv).getCapability();
        if (cap instanceof Capability) {
          ret.add((Capability) cap);
        }
      }
    }
    return ret;
  }

  protected List<CapabilityRealization> getInvolvingCapabilityRealizations(AbstractFunction element_p) {
    List<CapabilityRealization> ret = new ArrayList<CapabilityRealization>();
    for (Involvement inv : element_p.getInvolvingInvolvements()) {
      if (inv instanceof AbstractFunctionAbstractCapabilityInvolvement) {
        AbstractCapability cap = ((AbstractFunctionAbstractCapabilityInvolvement) inv).getCapability();
        if (cap instanceof CapabilityRealization) {
          ret.add((CapabilityRealization) cap);
        }
      }
    }
    return ret;
  }

  protected List<AbstractFunction> getSubFunctions(AbstractFunction element_p) {
    return (List<AbstractFunction>) FunctionExt.getFirstLevelAbstractFunctions(element_p);
  }

  protected List<FunctionalChain> getInvolvingFunctionalChains(AbstractFunction element_p) {
    List<FunctionalChain> result = new ArrayList<FunctionalChain>();
    for (Involvement involvement : element_p.getInvolvingInvolvements()) {
      if(involvement instanceof FunctionalChainInvolvement){
        InvolverElement fc = involvement.getInvolver();
        if (fc instanceof FunctionalChain && !result.contains(fc)){
          result.add((FunctionalChain) fc);
        }
      }
    }
    return result;
  }

  protected FunctionSpecification getLinkedFunctionSpecification(AbstractFunction element_p){
    AbstractBehavior behavior = element_p.getBehavior();
    if(null != behavior && behavior instanceof FunctionSpecification){
      return (FunctionSpecification) behavior;
    }
    return null;
  }

  protected StateMachine getLinkedStateMachine(AbstractFunction element_p){
    AbstractBehavior behavior = element_p.getBehavior();
    if(null != behavior && behavior instanceof StateMachine){
      return (StateMachine) behavior;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  protected List<ActivityEdge> getIncomingEdges(AbstractFunction element_p, EStructuralFeature feature_p) {
    List<ActivityEdge> res = new ArrayList<ActivityEdge>();

    if (element_p.getInputs().isEmpty()) {
      res.addAll((Collection<? extends ActivityEdge>) ActivityNodeHelper.getInstance().doSwitch(element_p, feature_p));
    } else {
      for (InputPin inputPin : element_p.getInputs()) {
        res.addAll((Collection<? extends ActivityEdge>) ActivityNodeHelper.getInstance().doSwitch(inputPin, feature_p));
      }
    }

    return res;
  }

  @SuppressWarnings("unchecked")
  protected List<ActivityEdge> getOutgoingEdges(AbstractFunction element_p, EStructuralFeature feature_p) {
    List<ActivityEdge> res = new ArrayList<ActivityEdge>();

    if (element_p.getOutputs().isEmpty()) {
      res.addAll((Collection<? extends ActivityEdge>) ActivityNodeHelper.getInstance().doSwitch(element_p, feature_p));
    } else {
      for (OutputPin outputPin : element_p.getOutputs()) {
        res.addAll((Collection<? extends ActivityEdge>) ActivityNodeHelper.getInstance().doSwitch(outputPin, feature_p));
      }
    }

    return res;
  }
}
