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
package org.polarsys.capella.core.projection.actors.ctx2la.rules;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transfo.statemachine.TransformStateMachine;

/**
 */
public class Rule_Actor extends org.polarsys.capella.core.projection.common.rules.cs.Rule_Actor {

  public Rule_Actor() {
    super(CtxPackage.Literals.ACTOR, LaPackage.Literals.LOGICAL_ACTOR, LaPackage.Literals.SYSTEM_ACTOR_REALIZATION);
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof LogicalActorPkg) {
      return LaPackage.Literals.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS;
    }
    return super.getTargetContainementFeature(element_p, result_p, container_p, context_p);
  }

  @Override
  protected void runSubTransition(EObject element_p, ITransfo transfo_p) {

    EObject transfoSource = (EObject) getTransfo().get(TransfoEngine.TRANSFO_SOURCE);
    if (EcoreUtil2.isOrIsContainedBy(element_p, transfoSource)) {
      if (element_p instanceof StateMachine) {
        StateMachine sourceElement = (StateMachine) element_p;
        TransformStateMachine transfSM = new TransformStateMachine();
        transfSM.setContext(sourceElement);
        transfSM.execute();

      } else {
        super.runSubTransition(element_p, transfo_p);
      }
    }
  }
}
