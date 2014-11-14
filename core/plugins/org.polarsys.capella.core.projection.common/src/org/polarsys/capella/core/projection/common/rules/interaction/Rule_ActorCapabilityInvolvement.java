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
package org.polarsys.capella.core.projection.common.rules.interaction;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_Involvement;
import org.polarsys.capella.core.tiger.ITransfo;

/**
 */
public class Rule_ActorCapabilityInvolvement extends Rule_Involvement {

  public Rule_ActorCapabilityInvolvement() {
    super(CapellacorePackage.Literals.INVOLVEMENT, CapellacorePackage.Literals.INVOLVEMENT);
  }

  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return element_p instanceof EntityOperationalCapabilityInvolvement || element_p instanceof ActorCapabilityInvolvement
           || element_p instanceof ActorCapabilityRealizationInvolvement;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    if (CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) element_p)) {
      return CtxFactory.eINSTANCE.createActorCapabilityInvolvement();
    }
    return CsFactory.eINSTANCE.createActorCapabilityRealizationInvolvement();
  }

}
