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

  public Object doSwitch(AbstractCapability element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION)) {
      ret = getIncomingCapabilityAllocation(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION)) {
      ret = getOutgoingCapabilityAllocation(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUPER)) {
      ret = getSuper(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUB)) {
      ret = getSub(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES)) {
      ret = getIncludedAbstractCapabilities(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES)) {
      ret = getIncludingAbstractCapabilities(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES)) {
      ret = getExtendedAbstractCapabilities(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES)) {
      ret = getExtendingAbstractCapabilities(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS)) {
      ret = getInvolvedAbstractFunctions(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS)) {
      ret = getInvolvedFunctionalChains(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolverElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = StructureHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<AbstractCapability> getExtendedAbstractCapabilities(AbstractCapability element_p) {
    List<AbstractCapability> ret = new ArrayList<AbstractCapability>();
    for (AbstractCapabilityExtend extend : element_p.getExtends()) {
      AbstractCapability extended = extend.getExtended();
      if (null != extended) {
        ret.add(extended);
      }
    }
    return ret;
  }

  protected List<AbstractCapability> getExtendingAbstractCapabilities(AbstractCapability element_p) {
    List<AbstractCapability> ret = new ArrayList<AbstractCapability>();
    for (EObject ref : EObjectExt.getReferencers(element_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENDED)) {
      if (ref instanceof AbstractCapabilityExtend) {
        AbstractCapability extended = ((AbstractCapabilityExtend) ref).getExtension();
        if (null != extended) {
          ret.add(extended);
        }
      }
    }
    return ret;
  }

  protected List<AbstractCapability> getIncludedAbstractCapabilities(AbstractCapability element_p) {
    List<AbstractCapability> ret = new ArrayList<AbstractCapability>();
    for (AbstractCapabilityInclude abstractCapabilityInclude : element_p.getIncludes()) {
      AbstractCapability included = abstractCapabilityInclude.getIncluded();
      if (null != included) {
        ret.add(included);
      }
    }
    return ret;
  }

  protected List<AbstractCapability> getIncludingAbstractCapabilities(AbstractCapability element_p) {
    List<AbstractCapability> ret = new ArrayList<AbstractCapability>();
    for (EObject ref : EObjectExt.getReferencers(element_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED)) {
      if (ref instanceof AbstractCapabilityInclude) {
        AbstractCapability included = ((AbstractCapabilityInclude) ref).getInclusion();
        if (null != included) {
          ret.add(included);
        }
      }
    }
    return ret;
  }

  protected List<AbstractCapabilityRealization> getIncomingCapabilityAllocation(AbstractCapability element_p) {
    List<AbstractCapabilityRealization> ret = new ArrayList<AbstractCapabilityRealization>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof AbstractCapabilityRealization) {
        ret.add((AbstractCapabilityRealization) trace);
      }
    }
    return ret;
  }

  protected List<AbstractCapabilityRealization> getOutgoingCapabilityAllocation(AbstractCapability element_p) {
    List<AbstractCapabilityRealization> ret = new ArrayList<AbstractCapabilityRealization>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof AbstractCapabilityRealization) {
        ret.add((AbstractCapabilityRealization) trace);
      }
    }
    return ret;
  }

  protected List<AbstractCapability> getSub(AbstractCapability element_p) {
    List<AbstractCapability> ret = new ArrayList<AbstractCapability>();
    for (AbstractCapabilityGeneralization abstractCapabilityGeneralization : element_p.getSubGeneralizations()) {
      AbstractCapability subC = abstractCapabilityGeneralization.getSub();
      if (null != subC) {
        ret.add(subC);
      }
    }
    return ret;
  }

  protected List<AbstractCapability> getSuper(AbstractCapability element_p) {
    List<AbstractCapability> ret = new ArrayList<AbstractCapability>();
    for (AbstractCapabilityGeneralization abstractCapabilityGeneralization : element_p.getSuperGeneralizations()) {
      AbstractCapability superC = abstractCapabilityGeneralization.getSuper();
      if (null != superC) {
        ret.add(superC);
      }
    }
    return ret;
  }

  protected List<AbstractFunction> getInvolvedAbstractFunctions(AbstractCapability element_p) {
    List<AbstractFunction> ret = new ArrayList<AbstractFunction>();
    for (Involvement inv : element_p.getInvolvedInvolvements()) {
      if (inv instanceof AbstractFunctionAbstractCapabilityInvolvement) {
        ret.add(((AbstractFunctionAbstractCapabilityInvolvement) inv).getFunction());
      }
    }
    return ret;
  }

  protected List<FunctionalChain> getInvolvedFunctionalChains(AbstractCapability element_p) {
    List<FunctionalChain> ret = new ArrayList<FunctionalChain>();
    for (Involvement inv : element_p.getInvolvedInvolvements()) {
      if (inv instanceof FunctionalChainAbstractCapabilityInvolvement) {
        ret.add(((FunctionalChainAbstractCapabilityInvolvement) inv).getFunctionalChain());
      }
    }
    return ret;
  }
}
