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
package org.polarsys.capella.core.projection.scenario.uml2.rules;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_InteractionUse extends Rule_CapellaElement {
  /**
   * @param eclass_p
   */
  public Rule_InteractionUse() {
    super(InteractionPackage.Literals.INTERACTION_USE, InteractionPackage.Literals.INTERACTION_USE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    InteractionUse source = (InteractionUse) element_p;

    if (isFirstAttach(element_p, result_p, context_p)) {
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.TIME_LAPSE__START, context_p);
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.TIME_LAPSE__FINISH, context_p);

      InteractionUse use = (InteractionUse) result_p;
      if (source.getReferencedScenario() != null) {
        TigerRelationshipHelper.attachElementByRel(use, source.getReferencedScenario(), InteractionPackage.Literals.INTERACTION_USE__REFERENCED_SCENARIO);
      }
    }
  }

}
