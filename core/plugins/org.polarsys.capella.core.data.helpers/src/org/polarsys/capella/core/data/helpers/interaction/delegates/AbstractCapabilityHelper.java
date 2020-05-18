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

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolverElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class AbstractCapabilityHelper {
  private static AbstractCapabilityHelper instance;

  private AbstractCapabilityHelper() {
    // do nothing
  }

  public static AbstractCapabilityHelper getInstance() {
    if (instance == null)
      instance = new AbstractCapabilityHelper();
    return instance;
  }

  public Object doSwitch(AbstractCapability element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION)) {
      ret = getIncomingCapabilityAllocation(element);
    } else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION)) {
      ret = getOutgoingCapabilityAllocation(element);
    } else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUPER)) {
      ret = getSuper(element);
    } else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUB)) {
      ret = getSub(element);
    } else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES)) {
      ret = getIncludedAbstractCapabilities(element);
    } else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES)) {
      ret = getIncludingAbstractCapabilities(element);
    } else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES)) {
      ret = getExtendedAbstractCapabilities(element);
    } else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES)) {
      ret = getExtendingAbstractCapabilities(element);
    } else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS)) {
      ret = getInvolvedAbstractFunctions(element);
    } else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS)) {
      ret = getInvolvedFunctionalChains(element);
    }else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING)) {
      ret = getExtending(element);
    }else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING)) {
      ret = getIncluding(element);
    }else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS)) {
      ret = getSubGeneralizations(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolverElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = StructureHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }
  
  protected List<AbstractCapabilityGeneralization> getSubGeneralizations(AbstractCapability element){
	  return EObjectExt.getReferencers(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER);
  }
  
  protected List<AbstractCapabilityInclude> getIncluding(AbstractCapability element){
	  return EObjectExt.getReferencers(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED);
  }
  
  protected List<AbstractCapabilityExtend> getExtending(AbstractCapability element){
	  return EObjectExt.getReferencers(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENDED);
  }

  protected List<AbstractCapability> getExtendedAbstractCapabilities(AbstractCapability element) {
    List<AbstractCapability> ret = new ArrayList<>();
    for (AbstractCapabilityExtend extend : element.getExtends()) {
      AbstractCapability extended = extend.getExtended();
      if (null != extended) {
        ret.add(extended);
      }
    }
    return ret;
  }

  protected List<AbstractCapability> getExtendingAbstractCapabilities(AbstractCapability element) {
    List<AbstractCapability> ret = new ArrayList<>();
    for (EObject ref : EObjectExt.getReferencers(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENDED)) {
      if (ref instanceof AbstractCapabilityExtend) {
        AbstractCapability extended = ((AbstractCapabilityExtend) ref).getExtension();
        if (null != extended) {
          ret.add(extended);
        }
      }
    }
    return ret;
  }

  protected List<AbstractCapability> getIncludedAbstractCapabilities(AbstractCapability element) {
    List<AbstractCapability> ret = new ArrayList<>();
    for (AbstractCapabilityInclude abstractCapabilityInclude : element.getIncludes()) {
      AbstractCapability included = abstractCapabilityInclude.getIncluded();
      if (null != included) {
        ret.add(included);
      }
    }
    return ret;
  }

  protected List<AbstractCapability> getIncludingAbstractCapabilities(AbstractCapability element) {
    List<AbstractCapability> ret = new ArrayList<>();
    for (EObject ref : EObjectExt.getReferencers(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED)) {
      if (ref instanceof AbstractCapabilityInclude) {
        AbstractCapability included = ((AbstractCapabilityInclude) ref).getInclusion();
        if (null != included) {
          ret.add(included);
        }
      }
    }
    return ret;
  }

  protected List<AbstractCapabilityRealization> getIncomingCapabilityAllocation(AbstractCapability element) {
    List<AbstractCapabilityRealization> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof AbstractCapabilityRealization) {
        ret.add((AbstractCapabilityRealization) trace);
      }
    }
    return ret;
  }

  protected List<AbstractCapabilityRealization> getOutgoingCapabilityAllocation(AbstractCapability element) {
    List<AbstractCapabilityRealization> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof AbstractCapabilityRealization) {
        ret.add((AbstractCapabilityRealization) trace);
      }
    }
    return ret;
  }

  protected List<AbstractCapability> getSub(AbstractCapability element) {
    List<AbstractCapability> ret = new ArrayList<>();
    for (AbstractCapabilityGeneralization abstractCapabilityGeneralization : element.getSubGeneralizations()) {
      AbstractCapability subC = abstractCapabilityGeneralization.getSub();
      if (null != subC) {
        ret.add(subC);
      }
    }
    return ret;
  }

  protected List<AbstractCapability> getSuper(AbstractCapability element) {
    List<AbstractCapability> ret = new ArrayList<>();
    for (AbstractCapabilityGeneralization abstractCapabilityGeneralization : element.getSuperGeneralizations()) {
      AbstractCapability superC = abstractCapabilityGeneralization.getSuper();
      if (null != superC) {
        ret.add(superC);
      }
    }
    return ret;
  }

  protected List<AbstractFunction> getInvolvedAbstractFunctions(AbstractCapability element) {
    List<AbstractFunction> ret = new ArrayList<>();
    for (Involvement inv : element.getInvolvedInvolvements()) {
      if (inv instanceof AbstractFunctionAbstractCapabilityInvolvement) {
        AbstractFunction function = ((AbstractFunctionAbstractCapabilityInvolvement) inv).getFunction();
        if (function != null) {
          ret.add(function);
        }
      }
    }
    return ret;
  }

  protected List<FunctionalChain> getInvolvedFunctionalChains(AbstractCapability element) {
    List<FunctionalChain> ret = new ArrayList<>();
    for (Involvement inv : element.getInvolvedInvolvements()) {
      if (inv instanceof FunctionalChainAbstractCapabilityInvolvement) {
        FunctionalChain functionalChain = ((FunctionalChainAbstractCapabilityInvolvement) inv).getFunctionalChain();
        if (functionalChain != null) {
          ret.add(functionalChain);
        }
      }
    }
    return ret;
  }
}
