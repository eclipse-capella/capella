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
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_StateFragment extends Rule_CapellaElement {
  /**
   * @param eclass_p
   */
  public Rule_StateFragment() {
    super(InteractionPackage.Literals.STATE_FRAGMENT, InteractionPackage.Literals.STATE_FRAGMENT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    if (isFirstAttach(element_p, result_p, context_p)) {
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.TIME_LAPSE__START, context_p);
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.TIME_LAPSE__FINISH, context_p);

      StateFragment source = (StateFragment) element_p;

      StateFragment use = (StateFragment) result_p;
      if (source.getRelatedAbstractState() != null) {
        TigerRelationshipHelper.attachElementByRel(use, source.getRelatedAbstractState(), InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE);
      }
      if (source.getRelatedAbstractFunction() != null) {
        TigerRelationshipHelper.attachElementByRel(use, source.getRelatedAbstractFunction(),
            InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES;
  }

}
