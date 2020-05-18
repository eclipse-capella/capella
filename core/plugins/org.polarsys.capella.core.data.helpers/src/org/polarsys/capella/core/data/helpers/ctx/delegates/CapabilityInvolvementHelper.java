/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.ctx.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;

public class CapabilityInvolvementHelper {
  private static CapabilityInvolvementHelper instance;

  private CapabilityInvolvementHelper() {
    // do nothing
  }

  public static CapabilityInvolvementHelper getInstance() {
    if (instance == null)
      instance = new CapabilityInvolvementHelper();
    return instance;
  }

  public Object doSwitch(CapabilityInvolvement element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CtxPackage.Literals.CAPABILITY_INVOLVEMENT__SYSTEM_COMPONENT)) {
      ret = getSystemComponent(element);
    } else if (feature.equals(CtxPackage.Literals.CAPABILITY_INVOLVEMENT__CAPABILITY)) {
      ret = getCapability(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected SystemComponent getSystemComponent(CapabilityInvolvement element) {
    InvolvedElement elt = element.getInvolved();
    if (elt instanceof SystemComponent)
      return (SystemComponent) elt;
    return null;
  }

  protected Capability getCapability(CapabilityInvolvement element) {
    InvolverElement elt = element.getInvolver();
    if (elt instanceof Capability)
      return (Capability) elt;
    return null;
  }
}
