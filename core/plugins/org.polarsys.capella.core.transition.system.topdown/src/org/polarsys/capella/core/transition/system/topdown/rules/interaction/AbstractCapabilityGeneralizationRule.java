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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 *
 */
public class AbstractCapabilityGeneralizationRule extends AbstractCapellaElementRule {

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      AbstractCapabilityGeneralization ce = (AbstractCapabilityGeneralization) element_p;

      if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element_p, context_p)) {
        return result;
      }

      if (ce.getSuper() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, "SourceNull");
      }
      if (ce.getSub() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, ".TargetNull");
      }

      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getSuper(), context_p).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation,
            NLS.bind(".SourceBoundNotTransitioned", EObjectLabelProviderHelper.getText(ce.getSuper())));
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getSub(), context_p).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind(".Target bound not transitioned", EObjectLabelProviderHelper.getText(ce.getSub())));
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      AbstractCapabilityGeneralization sourceElement = (AbstractCapabilityGeneralization) source_p;
      if (sourceElement.getSub() != null) {
        result_p.add(sourceElement.getSub());
      }
      if (sourceElement.getSuper() != null) {
        result_p.add(sourceElement.getSuper());
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER,
        context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUB,
        context_p);

  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUB));
  }

}
