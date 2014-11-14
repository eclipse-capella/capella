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
package org.polarsys.capella.core.projection.actors.oa2ctx.rules.bysystem;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;
import org.polarsys.capella.core.transfo.statemachine.TransformStateMachine;

/**
 */
public class Rule_OperationalEntity extends org.polarsys.capella.core.projection.common.rules.oa.Rule_OperationalEntity {

  /**
   * @param source_p
   * @param target_p
   */
  public Rule_OperationalEntity() {
    super(OaPackage.Literals.ENTITY, CsPackage.Literals.COMPONENT, CtxPackage.Literals.OPERATIONAL_ENTITY_REALIZATION);
  }

  /**
   * @param source_p
   * @param target_p
   */
  public Rule_OperationalEntity(EClass source, EClass target, EClass reference) {
    super(source, target, reference);
  }

  /**
   * @see org.polarsys.capella.core.tiger.ITransfoRule#requireTransformation(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public boolean requireTransformation(EObject element_p, ITransfo transfo_p) {
    Object transformedElement = Query.retrieveTransformedElements(element_p, transfo_p, CtxPackage.Literals.SYSTEM);

    return ((transformedElement == null) || ((transformedElement instanceof List<?>) && (((List<?>) transformedElement).size() == 0)) || ((transformedElement instanceof EObject) && !EcoreUtil2
        .isEqualOrSuperClass(getTargetType(), ((EObject) transformedElement).eClass())));

  }

  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return !(element_p instanceof OperationalActor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    SystemAnalysis sa = (SystemAnalysis) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    return sa.getOwnedSystem();
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
