/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_CapellaElement;

/**
 */
public class Rule_CombinedFragment extends Rule_CapellaElement {
  /**
   * @param eclass_p
   */
  public Rule_CombinedFragment() {
    super(InteractionPackage.Literals.COMBINED_FRAGMENT, InteractionPackage.Literals.COMBINED_FRAGMENT);
    registerAttributeUpdate(InteractionPackage.Literals.COMBINED_FRAGMENT__OPERATOR);
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

    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.TIME_LAPSE__START, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.TIME_LAPSE__FINISH, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.COMBINED_FRAGMENT__REFERENCED_OPERANDS,
        context_p);

  }
}
