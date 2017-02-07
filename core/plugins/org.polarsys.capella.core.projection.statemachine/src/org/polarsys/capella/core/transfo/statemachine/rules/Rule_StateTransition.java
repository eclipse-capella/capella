/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transfo.statemachine.rules;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_StateTransition extends Rule_CapellaElement {

  public Rule_StateTransition() {
    super(CapellacommonPackage.Literals.STATE_TRANSITION, CapellacommonPackage.Literals.STATE_TRANSITION,
          CapellacommonPackage.Literals.STATE_TRANSITION_REALIZATION);

    registerAttributeUpdate(CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGER_DESCRIPTION);
    registerAttributeUpdate(CapellacommonPackage.Literals.STATE_TRANSITION__KIND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CapellacommonPackage.Literals.REGION__OWNED_TRANSITIONS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    StateTransition sourceElement = (StateTransition) element_p;
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE_TRANSITION__GUARD, context_p);

    if (isFirstAttach(element_p, result_p, context_p)) {
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE, context_p);
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE_TRANSITION__TARGET, context_p);
    } else {
      // Link bounds
      if (Query.isOneToOneTransformed(sourceElement, context_p.getTransfo())) {
        StateTransition targetElement = (StateTransition) Query.retrieveFirstTransformedElement(sourceElement, context_p.getTransfo());

        // If the transition bound is not a transitioned element of source.bound, replace the bound
        if (targetElement.getSource() != null && sourceElement.getSource() != null) {
          if (!(Query.retrieveSourceElements(targetElement.getSource(), context_p.getTransfo(), targetElement.getSource().eClass()).contains(sourceElement
              .getSource()))) {
            EObject previousSource = targetElement.getSource();
            TigerRelationshipHelper.attachTransformedRelatedElements(sourceElement, CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE,
                context_p.getTransfo());
            notifyMessage(
                NLS.bind(ProjectionMessages.SourceBoundHasBeenChanged, new Object[] { EObjectLabelProviderHelper.getText(targetElement),
                                                                                     EObjectLabelProviderHelper.getText(previousSource),
                                                                                     EObjectLabelProviderHelper.getText(targetElement.getSource()) }),
                targetElement, ReportManagerConstants.LOG_LEVEL_INFO, context_p.getTransfo());
          }
        }

        // If the transition bound is not a transitioned element of source.bound, replace the bound
        if (targetElement.getTarget() != null && sourceElement.getTarget() != null) {
          if (!(Query.retrieveSourceElements(targetElement.getTarget(), context_p.getTransfo(), targetElement.getTarget().eClass()).contains(sourceElement
              .getTarget()))) {
            EObject previousTarget = targetElement.getTarget();
            TigerRelationshipHelper.attachTransformedRelatedElements(sourceElement, CapellacommonPackage.Literals.STATE_TRANSITION__TARGET,
                context_p.getTransfo());
            notifyMessage(
                NLS.bind(ProjectionMessages.TargetBoundHasBeenChanged, new Object[] { EObjectLabelProviderHelper.getText(targetElement),
                                                                                     EObjectLabelProviderHelper.getText(previousTarget),
                                                                                     EObjectLabelProviderHelper.getText(targetElement.getTarget()) }),
                targetElement, ReportManagerConstants.LOG_LEVEL_INFO, context_p.getTransfo());
          }
        }
      }
    }
  }

}
