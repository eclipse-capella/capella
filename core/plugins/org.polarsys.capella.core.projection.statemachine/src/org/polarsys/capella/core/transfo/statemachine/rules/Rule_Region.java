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
package org.polarsys.capella.core.transfo.statemachine.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;

/**
 */
public class Rule_Region extends Rule_CapellaElement {

  public Rule_Region() {
    super(CapellacommonPackage.Literals.REGION, CapellacommonPackage.Literals.REGION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Region sourceElement = (Region) source_p;

    result_p.addAll(sourceElement.getOwnedStates());
    result_p.addAll(sourceElement.getOwnedTransitions());

    if (source_p instanceof Region) {
      EObject containerCurrent = EcoreUtil2.getFirstContainer(sourceElement, CsPackage.Literals.COMPONENT);

      for (AbstractState s : sourceElement.getInvolvedStates()) {
        EObject containerState = EcoreUtil2.getFirstContainer(s, CsPackage.Literals.COMPONENT);
        if (containerCurrent.equals(containerState)) {
          result_p.add(s);
        }
      }

    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (element_p.eContainer() instanceof StateMachine) {
      return CapellacommonPackage.Literals.STATE_MACHINE__OWNED_REGIONS;
    }
    return CapellacommonPackage.Literals.STATE__OWNED_REGIONS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.REGION__INVOLVED_STATES, context_p);
  }

}
