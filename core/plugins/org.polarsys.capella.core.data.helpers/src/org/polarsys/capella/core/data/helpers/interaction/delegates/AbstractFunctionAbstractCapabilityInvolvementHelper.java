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

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;

public class AbstractFunctionAbstractCapabilityInvolvementHelper {
  private static AbstractFunctionAbstractCapabilityInvolvementHelper instance;

  private AbstractFunctionAbstractCapabilityInvolvementHelper() {
    // do nothing
  }

  public static AbstractFunctionAbstractCapabilityInvolvementHelper getInstance() {
    if (instance == null)
      instance = new AbstractFunctionAbstractCapabilityInvolvementHelper();
    return instance;
  }

  public Object doSwitch(AbstractFunctionAbstractCapabilityInvolvement element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTION)) {
      ret = getAbstractFunction(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY)) {
      ret = getAbstractCapability(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected AbstractFunction getAbstractFunction(AbstractFunctionAbstractCapabilityInvolvement element_p) {
    InvolvedElement elt = element_p.getInvolved();
    if (elt instanceof AbstractFunction)
      return (AbstractFunction) elt;
    return null;
  }

  protected AbstractCapability getAbstractCapability(AbstractFunctionAbstractCapabilityInvolvement element_p) {
    InvolverElement elt = element_p.getInvolver();
    if (elt instanceof AbstractCapability)
      return (AbstractCapability) elt;
    return null;
  }
}
