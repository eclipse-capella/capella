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
package org.polarsys.capella.core.transition.system.topdown.rules.interaction;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.transition.system.topdown.rules.common.InvolvementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CapabilityInvolvementRule extends InvolvementRule {

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    if (CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) element_p)) {
      return CtxFactory.eINSTANCE.createCapabilityInvolvement();
    }
    return CapellacommonFactory.eINSTANCE.createCapabilityRealizationInvolvement();
  }

}
