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
package org.polarsys.capella.core.projection.scenario.uml2.rules;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_InteractionState extends Rule_CapellaElement {
  /**
   * @param eclass_p
   */
  public Rule_InteractionState() {
    super(InteractionPackage.Literals.INTERACTION_STATE, InteractionPackage.Literals.INTERACTION_STATE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    InteractionState source = (InteractionState) element_p;

    if (isFirstAttach(element_p, result_p, context_p)) {
      InteractionState use = (InteractionState) result_p;
      if (source.getRelatedAbstractState() != null) {
        TigerRelationshipHelper
            .attachElementByRel(use, source.getRelatedAbstractState(), InteractionPackage.Literals.INTERACTION_STATE__RELATED_ABSTRACT_STATE);
      }
      if (source.getRelatedAbstractFunction() != null) {
        TigerRelationshipHelper.attachElementByRel(use, source.getRelatedAbstractFunction(),
            InteractionPackage.Literals.INTERACTION_STATE__RELATED_ABSTRACT_FUNCTION);
      }

      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p,
          InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES, context_p);

    }
  }

}
